package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;

/**
 * Dao object for domain model class Dossier.
 * 
 * @see dz.gov.mesrs.sii.DossierDto.business.model.bo.Dossier
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("dossierDao")
@Transactional
public class DossierDaoImpl implements DossierDao {

	private static final Logger log = LoggerFactory.getLogger(DossierDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierDao#persist(dz.gov.mesrs.sii.DossierDto.business.model.bo.Dossier)
	 * 
	 * @param transientInstance
	 */

	@Override
	public Dossier persist(Dossier transientInstance) {
		log.debug("persisting Dossier instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierDao#remove(dz.gov.mesrs.sii.DossierDto.business.model.bo.Dossier)
	 * @param persistentInstance
	 */

	@Override
	public void remove(Dossier persistentInstance) {
		log.debug("removing Dossier instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierDao#merge(dz.gov.mesrs.sii.DossierDto.business.model.bo.Dossier)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public Dossier merge(Dossier detachedInstance) {
		log.debug("merging Dossier instance");
		try {
			log.debug("merge successful");
			detachedInstance = entityManager.merge(detachedInstance);
			entityManager.flush();
			return detachedInstance;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDossierDao#findById(int)
	 * @param id
	 */
	@Override
	public Dossier findById(int id) {
		log.debug("getting Dossier instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Dossier.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param query
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Dossier> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dossier> findAll() {
		log.debug("getting all Dossier instances");
		try {
			Query query = entityManager.createQuery("from Dossier dossier");
			log.debug("findAll 'Dossier' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Dossier' failed", re);
			return new ArrayList<Dossier>();
		}
	}

	@Override
	public Dossier findByCode(String code) {
		log.debug("findByCode instances");
		if (code == null) {
			return null;
		}
		try {
			code = code.toLowerCase();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from Dossier r ");
			sqlQuery.append(" WHERE lower(r.typeDossier) = :code ");
			TypedQuery<Dossier> query = entityManager.createQuery(new String(sqlQuery), Dossier.class);
			query.setParameter("code", code);
			List<Dossier> resultList = query.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByCode successful");
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findByCode failed", re);
			throw re;
		}
	}

	@Override
	public void flushAndClear() {

		try {
			entityManager.flush();
			entityManager.clear();
		} catch (RuntimeException e) {
			log.error("flushAndClear failed", e);
			throw e;
		}
	}
}
