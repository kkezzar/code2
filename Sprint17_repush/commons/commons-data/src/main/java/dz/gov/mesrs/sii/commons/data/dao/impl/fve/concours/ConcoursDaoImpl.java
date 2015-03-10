package dz.gov.mesrs.sii.commons.data.dao.impl.fve.concours;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.concours.ConcoursDao;
import dz.gov.mesrs.sii.commons.data.model.fve.concours.Concours;

@Repository
public class ConcoursDaoImpl implements ConcoursDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Concours concours) {
	em.persist(concours);
    }

    @Override
    public Concours merge(Concours concours) {
	return em.merge(concours);
    }

    /**
     * Rechercher un concours par id
     * 
     * @author Mounir.MESSAOUDI on : 30 oct. 2014 08:57:22
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Concours findById(Long id) {
	try {
	    return em.find(Concours.class, id);
	} catch (RuntimeException re) {
	    throw re;
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public Concours findUniqueOrNoneByExample(Concours concours) {
	Criteria criteria = getCriteria(concours);
	List<Concours> results = criteria.list();
	if (results != null && results.size() > 0) {
	    if (results.size() == 1) {
		return results.get(0);
	    } else {
		throw new InvalidDataAccessApiUsageException(
			"Developper, you expected at most one result but we found " + results.size());
	    }
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Concours> findAllByExample(Concours concours) {
	return getCriteria(concours).list();
    }

    @Override
    public void delete(Concours concours) {
//	em.refresh(concours);
	em.remove(concours);

    }

    private Criteria getCriteria(Concours concours) {
	Criteria criteria = getSession().createCriteria(Concours.class).add(Example.create(concours).ignoreCase());
	if (concours.getId() != null) {
	    criteria.add(Restrictions.eq("id", concours.getId()));
	}
	if (concours.getAnneeAcademique() != null) {
	    criteria.createAlias("anneeAcademique", "anneeAcademique");
	    criteria.add(Restrictions.eq("anneeAcademique.id", concours.getAnneeAcademique().getId()));
	}
	if (concours.getTypeConcours() != null) {
	    criteria.createAlias("typeConcours", "typeConcours");
	    criteria.add(Restrictions.eq("typeConcours.code", concours.getTypeConcours().getCode()));
	}
	if (concours.getEtablissement() != null) {
	    criteria.createAlias("etablissement", "etablissement");
	    criteria.add(Restrictions.eq("etablissement.id", concours.getEtablissement().getId()));
	}
	return criteria;
    }

    private Session getSession() {
	HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
	return hem.getSession();
    }

    @Override
    public String getNextConcoursIdentifiantSeq() {
	Query q = em.createNativeQuery("SELECT nextval('"+SEQUENCE_IDENTIFIANT_CONCOURS+"')");
	return q.getSingleResult().toString();
    }

}
