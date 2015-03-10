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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.CongeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.CongeAcademique;

/**
 * Dao object for domain model class CongeAcademique;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.CongeAcademique;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("congeAcademiqueDao")
@Transactional
public class CongeAcademiqueDaoImpl implements CongeAcademiqueDao {

	private static final Logger log = LoggerFactory.getLogger(CongeAcademiqueDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CongeAcademiqueDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      CongeAcademique)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(CongeAcademique transientInstance) {
		log.debug("persisting CongeAcademique instance");
		try {
			entityManager.persist(transientInstance);
			/*
			 * entityManager.flush(); entityManager.refresh(transientInstance);
			 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CongeAcademiqueDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.CongeAcademique)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(CongeAcademique persistentInstance) {
		log.debug("removing CongeAcademique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CongeAcademiqueDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.CongeAcademique)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CongeAcademique merge(CongeAcademique detachedInstance) {
		log.debug("merging CongeAcademique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CongeAcademiqueDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public CongeAcademique findById(int id) {
		log.debug("getting CongeAcademique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(CongeAcademique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CongeAcademique> findAll() {
		log.debug("getting all CongeAcademique instances");
		try {
			Query query = entityManager
					.createQuery("from CongeAcademique congeAcademique");
			log.debug("findAll 'CongeAcademique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'CongeAcademique' failed", re);
			return new ArrayList<CongeAcademique>();
		}
	}

	@Override
	public CongeAcademique findByIdDossier(int idDossier) {
		try {
			String sqlQuery = "SELECT c FROM CongeAcademique c WHERE c.dossierEtudiant.id=:idDossier ";

			TypedQuery<CongeAcademique> query = entityManager.createQuery(
					sqlQuery, CongeAcademique.class);
			query.setParameter("idDossier", idDossier);

			List<CongeAcademique> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	@Override
	public CongeAcademique findByIdDossierInscriptionAdministrative(
			Integer diaId) {
		try {
			String sqlQuery = "SELECT c FROM CongeAcademique c WHERE c.dossierInscriptionAdministrative.id=:diaId";

			TypedQuery<CongeAcademique> query = entityManager.createQuery(
					sqlQuery, CongeAcademique.class);
			query.setParameter("diaId", diaId);

			List<CongeAcademique> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossierInscriptionAdministrative failed", re);
			throw re;
		}
	}
}
