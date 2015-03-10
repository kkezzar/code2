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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Diplome;

/**
 * Dao object for domain model class Diplome.
 * 
 * @see dz.gov.mesrs.sii.DiplomeDto.business.model.bo.Diplome
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */


@Repository ("diplomeDao")
@Transactional
public class DiplomeDaoImpl implements DiplomeDao {

	private static final Logger log = LoggerFactory.getLogger(DiplomeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DiplomeDao#persist(dz.gov.mesrs.sii.DiplomeDto.business.model.bo.Diplome)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Diplome transientInstance) {
		log.debug("persisting Diplome instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DiplomeDao#remove(dz.gov.mesrs.sii.DiplomeDto.business.model.bo.Diplome)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Diplome persistentInstance) {
		log.debug("removing Diplome instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DiplomeDao#merge(dz.gov.mesrs.sii.DiplomeDto.business.model.bo.Diplome)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Diplome merge(Diplome detachedInstance) {
		log.debug("merging Diplome instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDiplomeDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Diplome findById(int id) {
		log.debug("getting Diplome instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Diplome.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Diplome> findAll() {
		log.debug("getting all Diplome instances");
		try {
			Query query = entityManager.createQuery("from Diplome diplome");
			log.debug("findAll 'Diplome' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Diplome' failed", re);
			return new ArrayList<Diplome>();
		}
	}

	@Override
	public List<Diplome> findDiplomesByIdDossier(int idDossier) {
		try {
			String sqlQuery = "SELECT d FROM Diplome d WHERE d.dossierEtudiant.id=:idDossier ";

			TypedQuery<Diplome> query = entityManager.createQuery(sqlQuery,
					Diplome.class);
			query.setParameter("idDossier", idDossier);

			List<Diplome> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findDiplomesByIdDossier failed", re);
			throw re;
		}
	}
}
