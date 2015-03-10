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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac.AccessWilayaDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.PieceConstitutive;

/**
 * Dao object for domain model class PieceConstitutive.
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PieceConstitutiveDto.business.model.bo.PieceConstitutive
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("pieceConstitutiveDao")
@Transactional
public class PieceConstitutiveDaoImpl implements PieceConstitutiveDao {
	private static final Logger log = LoggerFactory.getLogger(PieceConstitutiveDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.PieceConstitutiveDao#persist(dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PieceConstitutiveDto.business.model.bo.PieceConstitutive)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(PieceConstitutive transientInstance) {
		log.debug("persisting PieceConstitutive instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.PieceConstitutiveDao#remove(dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PieceConstitutiveDto.business.model.bo.PieceConstitutive)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(PieceConstitutive persistentInstance) {
		log.debug("removing PieceConstitutive instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.PieceConstitutiveDao#merge(dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PieceConstitutiveDto.business.model.bo.PieceConstitutive)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PieceConstitutive merge(PieceConstitutive detachedInstance) {
		log.debug("merging PieceConstitutive instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NPieceConstitutiveDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public PieceConstitutive findById(int id) {
		log.debug("getting PieceConstitutive instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(PieceConstitutive.class, id);
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
	@Transactional(readOnly = true)
	public List<PieceConstitutive> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<PieceConstitutive> findAll() {
		log.debug("getting all PieceConstitutive instances");
		try {
			Query query = entityManager
					.createQuery("from PieceConstitutive pieceConstitutive");
			log.debug("findAll 'PieceConstitutive' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'PieceConstitutive' failed", re);
			return new ArrayList<PieceConstitutive>();
		}
	}

	@Override
	public List<PieceConstitutive> findByIdDossier(int id) {
		log.debug("findByIdDossier List with dossier id : " + id);

		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from PieceConstitutive r ");
			sqlQuery.append(" WHERE r.dossier.id = :id ");
			TypedQuery<PieceConstitutive> query = entityManager.createQuery(
					new String(sqlQuery), PieceConstitutive.class);
			query.setParameter("id", id);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	@Override
	public PieceConstitutive findByIdDossierAndCodePiece(int id, Integer pieceId) {
		if (pieceId == null) {
			return null;
		}
		
		log.debug("findByIdDossierAndCodePiece List with dossier id : " + id
				+ "and piece id " + pieceId);

		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from PieceConstitutive r ");
			sqlQuery.append(" WHERE r.dossier.id = " + id);
			sqlQuery.append(" AND r.refTypePieceConstitutive.ncTypePiece.id =  " + pieceId);

			TypedQuery<PieceConstitutive> query = entityManager.createQuery(
					new String(sqlQuery), PieceConstitutive.class);
			List<PieceConstitutive> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossierAndCodePiece failed", re);
			throw re;
		}
	}

	@Override
	public boolean isValid(int dossierId, Boolean es) {
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from PieceConstitutive r ");
			sqlQuery.append(" where r.dossier.id = :dossierId ");
			sqlQuery.append(" and r.obligatoire = TRUE ");
			sqlQuery.append(" and r.fournie = FALSE ");
//			if (es != null) {
//				if (es)
//					sqlQuery.append(" and r.aFournie = TRUE ");
//				else
//					sqlQuery.append(" and r.aFournie = FALSE ");
//			}

			TypedQuery<PieceConstitutive> query = entityManager.createQuery(
					new String(sqlQuery), PieceConstitutive.class);
			query.setParameter("dossierId", dossierId);
			List<PieceConstitutive> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return true;
			}

		} catch (RuntimeException re) {
			log.error("isValid failed", re);
			throw re;
		}
		return false;
	}

	@Override
	public boolean isInRequiredValid(Integer pieceId, Integer dossierId) {
		try {
			if(pieceId == null || dossierId == null) {
				return false;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from PieceConstitutive r ");
			sqlQuery.append(" where r.dossier.id = :dossierId ");
			//sqlQuery.append(" and r.obligatoire = TRUE ");
			sqlQuery.append(" and r.fournie = TRUE ");
			sqlQuery.append(" and r.refTypePieceConstitutive.ncTypePiece.id = " + pieceId);

			TypedQuery<PieceConstitutive> query = entityManager.createQuery(
					new String(sqlQuery), PieceConstitutive.class);
			query.setParameter("dossierId", dossierId);
			List<PieceConstitutive> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return true;
			}

		} catch (RuntimeException re) {
			log.error("isValid failed", re);
			throw re;
		}
		return false;
	}

	@Override
	public void flushAndClear() {
		
		try {
			entityManager.flush();
			entityManager.clear();
		}
		catch (RuntimeException e) {
			log.error("flushAndClear failed", e);
			throw e;
		}
	}
}
