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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.TitreAccesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TitreAcces;

/**
 * Dao object for domain model class TitreAcces.
 * 
 * @see dz.gov.mesrs.sii.TitreAccesDto.business.model.bo.TitreAcces
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("titreAccesDao")
@Transactional
public class TitreAccesDaoImpl implements TitreAccesDao {

	private static final Logger log = LoggerFactory.getLogger(TitreAccesDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.TitreAccesDao#persist(dz.gov.mesrs.sii.TitreAccesDto.business.model.bo.TitreAcces)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(TitreAcces transientInstance) {
		log.debug("persisting TitreAcces instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.TitreAccesDao#remove(dz.gov.mesrs.sii.TitreAccesDto.business.model.bo.TitreAcces)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(TitreAcces persistentInstance) {
		log.debug("removing TitreAcces instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.TitreAccesDao#merge(dz.gov.mesrs.sii.TitreAccesDto.business.model.bo.TitreAcces)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TitreAcces merge(TitreAcces detachedInstance) {
		log.debug("merging TitreAcces instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.TitreAccesDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public TitreAcces findById(int id) {
		log.debug("getting TitreAcces instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(TitreAcces.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TitreAcces> findAll() {
		log.debug("getting all TitreAcces instances");
		try {
			Query query = entityManager
					.createQuery("from TitreAcces titreAcces");
			log.debug("findAll 'TitreAcces' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'TitreAcces' failed", re);
			return new ArrayList<TitreAcces>();
		}
	}

	@Override
	public TitreAcces findByIdDossier(int idDossier) {

		try {
			String sqlQuery = "SELECT t FROM TitreAcces t WHERE t.dossierEtudiant.id=:idDossier ";

			TypedQuery<TitreAcces> query = entityManager.createQuery(sqlQuery,
					TitreAcces.class);
			query.setParameter("idDossier", idDossier);

			List<TitreAcces> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

}
