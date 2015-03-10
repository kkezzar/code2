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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeEditionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;

/**
 * Dao object for domain model class DiplomeFinEtudeEdition;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.DiplomeFinEtudeEdition;
 * @author MESRS CCM
 * @version 1.0
 * @created 02-10-2014 16:44:07
 */

@Repository("diplomeFinEtudeEditionDao")
@Transactional
public class DiplomeFinEtudeEditionDaoImpl implements DiplomeFinEtudeEditionDao {

	private static final Logger log = LoggerFactory.getLogger(DiplomeFinEtudeEditionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeEditionDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      DiplomeFinEtudeEdition)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DiplomeFinEtudeEdition transientInstance) {
		log.debug("persisting DiplomeFinEtudeEdition instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeEditionDao#remove(dz.gov.mesrs.sii.DiplomeFinEtudeEditionDto.business.model.bo.cursus.DiplomeFinEtudeEdition)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DiplomeFinEtudeEdition persistentInstance) {
		log.debug("removing DiplomeFinEtudeEdition instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeEditionDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.DiplomeFinEtudeEdition)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiplomeFinEtudeEdition merge(DiplomeFinEtudeEdition detachedInstance) {
		log.debug("merging DiplomeFinEtudeEdition instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeEditionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public DiplomeFinEtudeEdition findById(int id) {
		log.debug("getting DiplomeFinEtudeEdition instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DiplomeFinEtudeEdition.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DiplomeFinEtudeEdition> findAll() {
		log.debug("getting all DiplomeFinEtudeEdition instances");
		try {
			Query query = entityManager
					.createQuery("from DiplomeFinEtudeEdition diplomeFinEtudeEdition");
			log.debug("findAll 'DiplomeFinEtudeEdition' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DiplomeFinEtudeEdition' failed", re);
			return new ArrayList<DiplomeFinEtudeEdition>();
		}
	}

	@Override
	public DiplomeFinEtudeEdition findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		log.debug("getting all findByIdInscriptionAdministrative instances");
		try {
			if (idInscriptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtudeEdition r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative ");

			TypedQuery<DiplomeFinEtudeEdition> query = entityManager
					.createQuery(new String(sqlQuery),
							DiplomeFinEtudeEdition.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			List<DiplomeFinEtudeEdition> result = query.getResultList();
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
	public List<DiplomeFinEtudeEdition> findToValidate(String codeEtab) {
		log.debug("getting findToValidate instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtudeEdition r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.refCodeEtablissement = :codeEtab  and r.situationEntite.id =32");
			TypedQuery<DiplomeFinEtudeEdition> query = entityManager
					.createQuery(new String(sqlQuery),
							DiplomeFinEtudeEdition.class);
			query.setParameter("codeEtab", codeEtab);
			List<DiplomeFinEtudeEdition> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findToValidate failed", re);
			throw re;
		}
	}

	@Override
	public List<DiplomeFinEtudeEdition> findAdvanced(DiplomeFinEtudeEdition searchBo) {
		return null;
	}

	@Override
	public DiplomeFinEtudeEdition findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<DiplomeFinEtudeEdition> findEditionByIdDiplome(int idDiplome) {
		try {
			String sqlQuery = "SELECT d FROM DiplomeFinEtudeEdition d WHERE d.diplomeFinEtude.id=:idDiplome ";

			TypedQuery<DiplomeFinEtudeEdition> query = entityManager.createQuery(sqlQuery,
					DiplomeFinEtudeEdition.class);
			query.setParameter("idDiplome", idDiplome);

			List<DiplomeFinEtudeEdition> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findEditionByIdDiplome failed", re);
			throw re;
		}
	}
	
	
	
	

}
