package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefJourOuvreDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefJourOuvre;

/**
 * 
 * @author BELDI Jamel
 * 
 */

@Repository("refJourOuvreDao")
@Transactional
public class RefJourOuvreDaoImpl implements RefJourOuvreDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public void persist(RefJourOuvre transientInstance) {
		entityManager.persist(transientInstance);
		entityManager.flush();
		entityManager.refresh(transientInstance);

	}

	@Override
	public void remove(RefJourOuvre persistentInstance) {
		entityManager.remove(persistentInstance);
	}

	@Override
	public RefJourOuvre merge(RefJourOuvre detachedInstance) {
		return entityManager.merge(detachedInstance);
	}

	@Override
	public RefJourOuvre findById(int id) {
		return entityManager.find(RefJourOuvre.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefJourOuvre> findAll() {
		Query query = entityManager.createQuery("from RefJourOuvre jour");
		return query.getResultList();

	}

	@Override
	public RefJourOuvre findByDate(Date date) {
		Criteria criteria = getSession().createCriteria(RefJourOuvre.class);
			criteria.add(Restrictions.eq("date", date));
		// fix duplicate results (MM)
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<RefJourOuvre> results = criteria.list();
		if (results != null && results.size() > 1) {
			throw new InvalidDataAccessApiUsageException("Developper, you expected at most one result but we found "
					+ String.valueOf(results.size()));
		}
		if (results == null || results.size() == 0) {
			return null;
		} else {
			return results.get(0);
		}
		
	}
	
	
	@Override
	public List<RefJourOuvre> findBetweenTwoDate(Date dateDebut, Date dateFin) {
		List<RefJourOuvre> results = null;
		Criteria criteria = getSession().createCriteria(RefJourOuvre.class);
		criteria.add(Restrictions.between("date", dateDebut, dateFin));
	// fix duplicate results (MM)
	criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	 results = criteria.list();
	 return results;
	}
	
	protected Session getSession() {
		HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		return hem.getSession();
	}

	
}
