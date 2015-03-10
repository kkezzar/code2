package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEntiteSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntiteSituation;

@Repository("refEntiteSituationDaoImpl")
public class RefEntiteSituationDaoImpl implements RefEntiteSituationDao {

	private static final Logger log = LoggerFactory.getLogger(RefEntiteSituationDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefEntiteSituationDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefEntiteSituation)
	 */
	@Override
	public void persist(RefEntiteSituation transientInstance) {
		log.debug("persisting RefEntiteSituation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefEntiteSituationDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefEntiteSituation)
	 */
	@Override
	public void remove(RefEntiteSituation persistentInstance) {
		log.debug("removing RefEntiteSituation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefEntiteSituationDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefEntiteSituation)
	 */
	@Override
	public RefEntiteSituation merge(RefEntiteSituation detachedInstance) {
		log.debug("merging RefEntiteSituation instance");
		try {
			RefEntiteSituation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefEntiteSituationDao#findById(int)
	 */
	@Override
	public RefEntiteSituation findById(int id) {
		log.debug("getting RefEntiteSituation instance with id: " + id);
		try {
			RefEntiteSituation instance = entityManager.find(
					RefEntiteSituation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefEntiteSituation> findListSituationByEntityName(
			String entityName) {
		log.debug("getting List RefEntiteSituation of RefEntiteSituation instance with entityName : " + entityName);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEntiteSituation r ");
			request.append("where r.nomEntite = :entityName ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("entityName", entityName);
			List<RefEntiteSituation> resultList =  query
					.getResultList();
			log.debug("findListSituationByEntityName(String entityName)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
		
	
}
