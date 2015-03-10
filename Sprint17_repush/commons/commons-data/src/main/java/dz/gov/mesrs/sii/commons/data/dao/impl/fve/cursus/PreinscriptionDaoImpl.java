package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PreinscriptionDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefIndividuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DecisionDemandePreinscription;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DemandePreinscription;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TitreAcces;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCoordonnee;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Repository
public class PreinscriptionDaoImpl implements PreinscriptionDao {

    private final static Logger logger = LoggerFactory.getLogger(PreinscriptionDaoImpl.class.getName());

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Autowired
    RefIndividuDao refIndividuDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<DemandePreinscription> findByExample(DemandePreinscription demandePreinscription) {
	logger.debug("searching for {}", new Object[] { demandePreinscription });
	Criteria criteria = getCriteria(demandePreinscription);
	return criteria.list();
    }

    @Override
    @Transactional
    public DemandePreinscription save(DemandePreinscription demandePreinscription) {
	Assert.notNull(demandePreinscription, "can't persist null entity");
	if (demandePreinscription.getId() == null) {
	    em.persist(demandePreinscription);
	} else {
	    DecisionDemandePreinscription decision = demandePreinscription.getDecision();
	    if (decision != null) {
		decision.setDemandePreinscription(demandePreinscription);
		if (decision.getId() == null) {
		    em.persist(decision);
		} else {
		    decision = em.merge(decision);
		}
	    }
	    demandePreinscription = em.merge(demandePreinscription);
	    
	}
	return demandePreinscription;
    }

    @Transactional
    @Override
    public void saveDemandeur(DemandePreinscription demandePreinscription) {
	RefIndividu refIndividu = demandePreinscription.getRefIndividu();
	if (refIndividu != null) {
	    RefCoordonnee refCoordonnee = refIndividu.getRefCoordonnees().iterator().next();
	    if (refIndividu.getId() <= 0) {
		em.persist(refIndividu);
		em.persist(refCoordonnee);
		refCoordonnee.getRefAdresse().setId(refCoordonnee.getId());
		em.persist(refCoordonnee.getRefAdresse());
		refCoordonnee.getRefAdresseElectronique().setId(refCoordonnee.getId());
		em.persist(refCoordonnee.getRefAdresseElectronique());

	    } else {
		em.merge(refIndividu);
		em.merge(refCoordonnee.getRefAdresse());
		em.merge(refCoordonnee.getRefAdresseElectronique());

	    }
	}
    }

    private Criteria getCriteria(DemandePreinscription demandePreinscription) {
	Assert.notNull(demandePreinscription, "can't search null entity");
	HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
	Session session = hem.getSession();
	Criteria criteria = session.createCriteria(DemandePreinscription.class, "demandePreinscription");
	RefIndividu individu = demandePreinscription.getRefIndividu();
	if (individu != null) {
	    criteria.createAlias("demandePreinscription.refIndividu", "refIndividu");
	    if (!org.apache.commons.lang3.StringUtils.isEmpty(individu.getNomLatin())) {
		criteria.add(Restrictions.eq("refIndividu.nomLatin", individu.getNomLatin()));
	    }
	    if (!org.apache.commons.lang3.StringUtils.isEmpty(individu.getPrenomLatin())) {
		criteria.add(Restrictions.eq("refIndividu.prenomLatin", individu.getPrenomLatin()));
	    }
	    Nomenclature nomenclature = individu.getNomenclatureByNationalite();
	    if (nomenclature != null) {
		criteria.createAlias("refIndividu.nomenclatureByNationalite", "nationalite");
		if (nomenclature.getId() > 0) {
		    criteria.add(Restrictions.eq("nationalite.id", nomenclature.getId()));
		}
	    }
	    
	}
	RefEtablissement etablissement = demandePreinscription.getEtablissement();
	if (etablissement != null) {
	    criteria.createAlias("demandePreinscription.etablissement", "etablissement");
	    if (etablissement.getId() > 0) {
		criteria.add(Restrictions.eq("etablissement.id", etablissement.getId()));
	    }
	}

	AnneeAcademique anneeAcademique = demandePreinscription.getAnneeAcademique();
	if (anneeAcademique != null) {
	    criteria.createAlias("demandePreinscription.anneeAcademique", "anneeAcademique");
	    if (anneeAcademique.getId() > 0) {
		criteria.add(Restrictions.eq("anneeAcademique.id", anneeAcademique.getId()));
	    }
	}
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	// criteria.setProjection(Projections.distinct(Projections.property("id")));
	return criteria;
    }

    @Override
    public DemandePreinscription finById(Long demandeId) {
	return em.find(DemandePreinscription.class, demandeId);
    }

    @Transactional
    @Override
    public void saveTitreAcces(DemandePreinscription demandePreinscription) {
	TitreAcces titreAcces = demandePreinscription.getTitreAcces();

	if (titreAcces != null) {
	    if (titreAcces.getId() <= 0) {
		em.persist(titreAcces);
	    } else {
		em.merge(titreAcces);
	    }
	}
    }

    @Override
    public void remove(DemandePreinscription demande) {
	if(!demande.getRefIndividu().getRefCoordonnees().isEmpty()){
	    RefCoordonnee refCoordonnee =demande.getRefIndividu().getRefCoordonnees().iterator().next();
	    em.remove(refCoordonnee.getRefAdresse());
	    em.remove(refCoordonnee.getRefAdresseElectronique());
	    em.remove(refCoordonnee);
	}
	em.remove(demande.getRefIndividu());
	if(demande.getTitreAcces() != null) {
	    em.remove(demande.getTitreAcces());
	}
	
	if(demande.getDecision()!=null){
	    em.remove(demande.getDecision());
	}
	em.remove(demande);
    }

}
