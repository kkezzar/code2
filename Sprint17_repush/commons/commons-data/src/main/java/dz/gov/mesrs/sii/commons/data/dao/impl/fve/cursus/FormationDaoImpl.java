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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Formation;

/**
 * Dao object for domain model class Formation.
 * 
 * @see dz.gov.mesrs.sii.FormationDto.business.model.bo.Formation
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("formationDao")
@Transactional
public class FormationDaoImpl implements FormationDao {

	private static final Logger log = LoggerFactory.getLogger(FormationDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.FormationDao#persist(dz.gov.mesrs.sii.FormationDto.business.model.bo.Formation)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Formation transientInstance) {
		log.debug("persisting Formation instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.FormationDao#remove(dz.gov.mesrs.sii.FormationDto.business.model.bo.Formation)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Formation persistentInstance) {
		log.debug("removing Formation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.FormationDao#merge(dz.gov.mesrs.sii.FormationDto.business.model.bo.Formation)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Formation merge(Formation detachedInstance) {
		log.debug("merging Formation instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NFormationDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Formation findById(int id) {
		log.debug("getting Formation instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Formation.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Formation> findAll() {
		log.debug("getting all Formation instances");
		try {
			Query query = entityManager.createQuery("from Formation formation");
			log.debug("findAll 'Formation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Formation' failed", re);
			return new ArrayList<Formation>();
		}
	}

	
	@Override
	public List<Formation> findFormationsByIdDossier(int idDossier) {
		try {
			String sqlQuery = "SELECT f FROM Formation f WHERE f.dossierEtudiant.id=:idDossier ";

			TypedQuery<Formation> query = entityManager.createQuery(sqlQuery,
					Formation.class);
			query.setParameter("idDossier", idDossier);

			List<Formation> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findFormationsByIdDossier failed", re);
			throw re;
		}
	}
}
