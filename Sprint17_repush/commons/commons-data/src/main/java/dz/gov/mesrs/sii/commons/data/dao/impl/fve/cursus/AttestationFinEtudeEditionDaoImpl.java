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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AttestationFinEtudeEditionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtudeEdition;

/**
 * Dao object for domain model class AttestationFinEtudeEdition;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.AttestationFinEtudeEdition;
 * @author MESRS CCM
 * @version 1.0
 * @created 02-10-2014 16:44:07
 */

@Repository("attestationFinEtudeEditionDao")
@Transactional
public class AttestationFinEtudeEditionDaoImpl implements AttestationFinEtudeEditionDao {

	private static final Logger log = LoggerFactory.getLogger(AttestationFinEtudeEditionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AttestationFinEtudeEditionDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      AttestationFinEtudeEdition)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AttestationFinEtudeEdition transientInstance) {
		log.debug("persisting AttestationFinEtudeEdition instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AttestationFinEtudeEditionDao#remove(dz.gov.mesrs.sii.AttestationFinEtudeEditionDto.business.model.bo.cursus.AttestationFinEtudeEdition)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AttestationFinEtudeEdition persistentInstance) {
		log.debug("removing AttestationFinEtudeEdition instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AttestationFinEtudeEditionDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.AttestationFinEtudeEdition)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AttestationFinEtudeEdition merge(AttestationFinEtudeEdition detachedInstance) {
		log.debug("merging AttestationFinEtudeEdition instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AttestationFinEtudeEditionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public AttestationFinEtudeEdition findById(int id) {
		log.debug("getting AttestationFinEtudeEdition instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AttestationFinEtudeEdition.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AttestationFinEtudeEdition> findAll() {
		log.debug("getting all AttestationFinEtudeEdition instances");
		try {
			Query query = entityManager
					.createQuery("from AttestationFinEtudeEdition attestationFinEtudeEdition");
			log.debug("findAll 'AttestationFinEtudeEdition' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AttestationFinEtudeEdition' failed", re);
			return new ArrayList<AttestationFinEtudeEdition>();
		}
	}

	@Override
	public AttestationFinEtudeEdition findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		log.debug("getting all findByIdInscriptionAdministrative instances");
		try {
			if (idInscriptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from AttestationFinEtudeEdition r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative ");

			TypedQuery<AttestationFinEtudeEdition> query = entityManager
					.createQuery(new String(sqlQuery),
							AttestationFinEtudeEdition.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			List<AttestationFinEtudeEdition> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findByIdInscriptionAdministrative failed", re);
			throw re;
		}
	}

	@Override
	public List<AttestationFinEtudeEdition> findToValidate(String codeEtab) {
		log.debug("getting findToValidate instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from AttestationFinEtudeEdition r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.refCodeEtablissement = :codeEtab  and r.situationEntite.id =32");
			TypedQuery<AttestationFinEtudeEdition> query = entityManager
					.createQuery(new String(sqlQuery),
							AttestationFinEtudeEdition.class);
			query.setParameter("codeEtab", codeEtab);
			List<AttestationFinEtudeEdition> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findToValidate failed", re);
			throw re;
		}
	}

	@Override
	public List<AttestationFinEtudeEdition> findAdvanced(AttestationFinEtudeEdition searchBo) {
		return null;
	}

	@Override
	public AttestationFinEtudeEdition findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<AttestationFinEtudeEdition> findEditionByIdAttestation(int idAttestation) {
		try {
			String sqlQuery = "SELECT d FROM AttestationFinEtudeEdition d WHERE d.attestationFinEtude.id=:idAttestation ";

			TypedQuery<AttestationFinEtudeEdition> query = entityManager.createQuery(sqlQuery,
					AttestationFinEtudeEdition.class);
			query.setParameter("idAttestation", idAttestation);

			List<AttestationFinEtudeEdition> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findEditionByIdAttestation failed", re);
			throw re;
		}
	}
	
	
	
	

}
