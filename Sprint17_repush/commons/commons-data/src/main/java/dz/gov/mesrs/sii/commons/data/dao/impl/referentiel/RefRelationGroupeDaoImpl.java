package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefRelationGroupeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefRelationGroupe;

public class RefRelationGroupeDaoImpl implements RefRelationGroupeDao {

	private static final Logger log = LoggerFactory.getLogger(RefRelationGroupeDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefRelationGroupeDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefRelationGroupe)
	 */
	@Override
	public void persist(RefRelationGroupe transientInstance) {
		log.debug("persisting RefRelationGroupe instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefRelationGroupeDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefRelationGroupe)
	 */
	@Override
	public void remove(RefRelationGroupe persistentInstance) {
		log.debug("removing RefRelationGroupe instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefRelationGroupeDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefRelationGroupe)
	 */
	@Override
	public RefRelationGroupe merge(RefRelationGroupe detachedInstance) {
		log.debug("merging RefRelationGroupe instance");
		try {
			RefRelationGroupe result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefRelationGroupeDao#findById(int)
	 */
	@Override
	public RefRelationGroupe findById(int id) {
		log.debug("getting RefRelationGroupe instance with id: " + id);
		try {
			RefRelationGroupe instance = entityManager.find(
					RefRelationGroupe.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
