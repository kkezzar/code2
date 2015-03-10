package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtude;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class DiplomeFinEtude;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.DiplomeFinEtude;
 * @author MESRS CCM
 * @version 1.0
 * @created 02-10-2014 16:44:07
 */

@Repository("diplomeFinEtudeDao")
@Transactional
public class DiplomeFinEtudeDaoImpl implements DiplomeFinEtudeDao {

	private static final Logger log = LoggerFactory
			.getLogger(DiplomeFinEtudeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      DiplomeFinEtude)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DiplomeFinEtude transientInstance) {
		log.debug("persisting DiplomeFinEtude instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeDao#remove(dz.gov.mesrs.sii.DiplomeFinEtudeDto.business.model.bo.cursus.DiplomeFinEtude)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DiplomeFinEtude persistentInstance) {
		log.debug("removing DiplomeFinEtude instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.DiplomeFinEtude)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiplomeFinEtude merge(DiplomeFinEtude detachedInstance) {
		log.debug("merging DiplomeFinEtude instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.DiplomeFinEtudeDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public DiplomeFinEtude findById(int id) {
		log.debug("getting DiplomeFinEtude instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DiplomeFinEtude.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DiplomeFinEtude> findAll() {
		log.debug("getting all DiplomeFinEtude instances");
		try {
			Query query = entityManager
					.createQuery("from DiplomeFinEtude diplomeFinEtude");
			log.debug("findAll 'DiplomeFinEtude' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DiplomeFinEtude' failed", re);
			return new ArrayList<DiplomeFinEtude>();
		}
	}

	@Override
	public DiplomeFinEtude findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		log.debug("getting all findByIdInscriptionAdministrative instances");
		try {
			if (idInscriptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative ");

			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			List<DiplomeFinEtude> result = query.getResultList();
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
	public List<DiplomeFinEtude> findToValidate(String codeEtab) {
		log.debug("getting findToValidate instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.refCodeEtablissement = :codeEtab  and r.situationEntite.id =32");
			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);
			query.setParameter("codeEtab", codeEtab);
			List<DiplomeFinEtude> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findToValidate failed", re);
			throw re;
		}
	}

	@Override
	public List<DiplomeFinEtude> findAdvanced(DiplomeFinEtude searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DiplomeFinEtude> c = cb
					.createQuery(DiplomeFinEtude.class);
			Root<DiplomeFinEtude> _pi = c.from(DiplomeFinEtude.class);
			c.orderBy(cb.asc(_pi.get("dateObtention")));

			c.select(_pi).distinct(true);
			Predicate predicate = null;

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DiplomeFinEtude> _query = entityManager
						.createQuery(c);
				List<DiplomeFinEtude> result = _query.getResultList();
				for (DiplomeFinEtude diplomeFinEtude : result) {
					if (entityManager.contains(diplomeFinEtude))
						entityManager.refresh(diplomeFinEtude);
				}

				return result;
			} else
				return new ArrayList<DiplomeFinEtude>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DiplomeFinEtude>();
		}

	}

	@Override
	public DiplomeFinEtude findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		log.debug("getting all findByIdInscriptionAdministrativeAndPeriode instances");
		try {
			if (idInscriptionAdministrative == 0 || idPeriode == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative  and  r.periode.id = :idPeriode");

			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			query.setParameter("idPeriode", idPeriode);
			List<DiplomeFinEtude> result = query.getResultList();
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
	public List<DiplomeFinEtude> findAdvancedDiplomeNonSigneMinist(
			DiplomeFinEtude searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DiplomeFinEtude> c = cb
					.createQuery(DiplomeFinEtude.class);
			Root<DiplomeFinEtude> _pi = c.from(DiplomeFinEtude.class);
			c.orderBy(cb.asc(_pi.get("dateObtention")));

			c.select(_pi).distinct(true);
			Predicate predicate = null;

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DiplomeFinEtude> _query = entityManager
						.createQuery(c);
				List<DiplomeFinEtude> result = _query.getResultList();
				for (DiplomeFinEtude diplomeFinEtude : result) {
					if (entityManager.contains(diplomeFinEtude))
						entityManager.refresh(diplomeFinEtude);
				}

				return result;
			} else
				return new ArrayList<DiplomeFinEtude>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DiplomeFinEtude>();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao#
	 * findUniqueByBilanSession(int)
	 */
	@Override
	public DiplomeFinEtude findUniqueByBilanSession(long idBilanSession) {
		log.debug("getting all findUniqueByBilanSession instances");
		try {
			if (idBilanSession == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DiplomeFinEtude r ");
			sqlQuery.append("where r.bilanSession.id = " + idBilanSession);

			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);

			List<DiplomeFinEtude> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findUniqueByBilanSession failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao#
	 * generateIdentify(java.lang.String, int)
	 */
	@Override
	public Integer findLastOrder(String prefix, int orderLength) {
		if (prefix == null) {
			return null;
		}
		int prefixLength = prefix.length();
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from DiplomeFinEtude r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(request), DiplomeFinEtude.class);

			List<DiplomeFinEtude> resultList = query.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return 0;
			} else {
				log.debug("findLastOrder successful");
				String lastIdentify = resultList.get(0).getIdentifiant();
				if (lastIdentify == null) {
					return null;
				}
				String lastOrderStr = lastIdentify.substring(prefixLength);
				if (lastOrderStr == null
						|| lastOrderStr.length() != orderLength) {
					return null;
				}
				Integer lastOrder = Integer.parseInt(lastOrderStr);
				return lastOrder;
			}

		} catch (RuntimeException re) {
			log.error("findLastOrder failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao#
	 * findByOofAndCycle(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DiplomeFinEtude> findByOofAndCycle(Integer oofId,
			Integer cycleId) {
		log.debug("getting findByOofAndCycle instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select r from DiplomeFinEtude r ");
			sqlQuery.append(" where (1=1) ");
			if (oofId != null) {
				sqlQuery.append(" and r.bilanSession.deliberationSession.planningSession.ouvertureOffreFormation.id = "
						+ oofId);
			}
			if (cycleId != null) {
				sqlQuery.append(" and r.cycle.id = " + cycleId);
			}
			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);
			List<DiplomeFinEtude> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findByOofAndCycle failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao#
	 * findValidateDiplome(java.lang.Integer)
	 */
	@Override
	public List<DiplomeFinEtude> findValidateDiplome(Integer oofId) {
		log.debug("getting findValidateDiplome instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select r from DiplomeFinEtude r ");
			sqlQuery.append(" where (1=1) ");
			if (oofId != null) {
				sqlQuery.append(" and r.bilanSession.deliberationSession.planningSession.ouvertureOffreFormation.id = "
						+ oofId);
			}

			sqlQuery.append(" and r.estValide = TRUE ");

			TypedQuery<DiplomeFinEtude> query = entityManager.createQuery(
					new String(sqlQuery), DiplomeFinEtude.class);
			List<DiplomeFinEtude> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findValidateDiplome failed", re);
			throw re;
		}
	}

}
