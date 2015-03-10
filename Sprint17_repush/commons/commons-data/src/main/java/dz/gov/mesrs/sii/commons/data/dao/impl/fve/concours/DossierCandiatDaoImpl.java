package dz.gov.mesrs.sii.commons.data.dao.impl.fve.concours;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.DossierCandidatDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.Concours;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.DossierCandidat;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.EtablissementAdmission;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Repository
public class DossierCandiatDaoImpl implements DossierCandidatDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public DossierCandidat findUniqueOrNoneByExample(DossierCandidat dossierCandidat) {
	Criteria criteria = getCriteria(dossierCandidat);
	List<DossierCandidat> entities = criteria.list();
	if (entities != null) {
	    if (entities.size() == 0) {
		return null;
	    } else if (entities.size() == 1) {
		return entities.get(0);
	    } else {
		throw new InvalidDataAccessApiUsageException("Developper , you expected one result but we found "
			+ entities.size());
	    }
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DossierCandidat> findAllByExample(DossierCandidat dossierCandidat) {
	return getCriteria(dossierCandidat).list();
    }

    @Override
    public void persist(DossierCandidat dossierCandidat) {
	em.persist(dossierCandidat);

    }

    @Override
    public DossierCandidat merge(DossierCandidat dossierCandidat) {
	return em.merge(dossierCandidat);

    }

    @Override
    public void delete(DossierCandidat dossierCandidat) {
	em.remove(dossierCandidat);
    }

    private Criteria getCriteria(DossierCandidat dossierCandidat) {
	Criteria criteria = getSession().createCriteria(DossierCandidat.class).add(
		Example.create(dossierCandidat).ignoreCase());
	if (dossierCandidat.getId() != null) {
	    criteria.add(Restrictions.eq("id", dossierCandidat.getId()));
	}
	EtablissementAdmission etablissementAdmission = dossierCandidat.getEtablissementAdmission();
	if (etablissementAdmission != null && etablissementAdmission.getId() != null) {
	    criteria.createAlias("etablissementAdmission", "etablissementAdmission");
	    criteria.add(Restrictions.eq("etablissementAdmission.id", etablissementAdmission.getId()));
	}
	Concours concours = dossierCandidat.getConcours();
	if (concours != null) {
	    criteria.createAlias("concours", "concours");
	    if (concours.getId() != null) {
		criteria.add(Restrictions.eq("concours.id", concours.getId()));
	    }
	    if (concours.getAnneeAcademique() != null) {
		criteria.add(Restrictions.eq("concours.anneeAcademique.id", concours.getAnneeAcademique().getId()));
	    }
	}
	RefIndividu individu = dossierCandidat.getIndividu();
	criteria.createAlias("individu", "individu");
	if (individu != null) {
	    
	    criteria.add(Restrictions.eq("individu.id", individu.getId()));
	}

	return criteria;
    }

    private Session getSession() {
	HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
	return hem.getSession();
    }

    /**
     * @author Mounir.MESSAOUDI on : 2 nov. 2014 09:26:41
     * @param idConcours
     * @param idEtabAdmission
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<DossierCandidat> findAllAdmisByIdConcoursIdEtabAdmission(Long idConcours, Integer idEtabAdmission) {
	try {
	    String hqlQuery = "SELECT d FROM DossierCandidat d WHERE d.concours.id=:idConcours" + " AND d.admis=TRUE "
		    + " AND d.desistement is null" + "  OR d.desistement = FALSE";

	    if (idEtabAdmission != null) {
		hqlQuery += " AND d.etablissementAdmission.id=:idEtabAdmission";
	    }

	    TypedQuery<DossierCandidat> query = em.createQuery(hqlQuery, DossierCandidat.class);
	    query.setParameter("idConcours", idConcours);
	    if (idEtabAdmission != null) {
		query.setParameter("idEtabAdmission", idEtabAdmission);
	    }

	    return query.getResultList();
	} catch (Exception e) {
	    // TODO: handle exception
	}

	return null;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DossierCandidat> findAllByExample(DossierCandidat dossierCandidat, String sortField, Integer pageSize,
	    Integer first, Boolean descending) {
	Criteria criteria = getCriteria(dossierCandidat);
	if (sortField != null) {
	    if (Boolean.TRUE.equals(descending)) {
		criteria.addOrder(Order.desc(sortField));
	    } else {
		criteria.addOrder(Order.asc(sortField));
	    }

	}
	if (pageSize != null) {
	    criteria.setMaxResults(pageSize);
	    criteria.setFirstResult(first);
	}
	return criteria.list();
    }

    @Override
    public int countByExample(DossierCandidat dossierCandidat) {
	Criteria criteria = getCriteria(dossierCandidat);
	criteria.setProjection(Projections.rowCount());
	return ((Long) criteria.uniqueResult()).intValue();
    }

}
