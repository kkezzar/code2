package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.Date;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SituationHandicapDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationHandicap;

/**
 * Dao object for domain model class SituationHandicap.
 * 
 * @see dz.gov.mesrs.sii.SituationHandicapDto.business.model.bo.SituationHandicap
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("situationHandicapDao")
@Transactional
public class SituationHandicapDaoImpl implements SituationHandicapDao {

	private static final Logger log = LoggerFactory.getLogger(SituationHandicapDaoImpl.class.getName());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationHandicapDao#persist(dz.gov.mesrs.sii.SituationHandicapDto.business.model.bo.SituationHandicap)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SituationHandicap transientInstance) {
		log.debug("persisting SituationHandicap instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationHandicapDao#remove(dz.gov.mesrs.sii.SituationHandicapDto.business.model.bo.SituationHandicap)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SituationHandicap persistentInstance) {
		log.debug("removing SituationHandicap instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationHandicapDao#merge(dz.gov.mesrs.sii.SituationHandicapDto.business.model.bo.SituationHandicap)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SituationHandicap merge(SituationHandicap detachedInstance) {
		log.debug("merging SituationHandicap instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NSituationHandicapDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public SituationHandicap findById(int id) {
		log.debug("getting SituationHandicap instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(SituationHandicap.class, id);
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
	public List<SituationHandicap> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SituationHandicap> findAll() {
		log.debug("getting all SituationHandicap instances");
		try {
			Query query = entityManager
					.createQuery("from SituationHandicap situationHandicap");
			log.debug("findAll 'SituationHandicap' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SituationHandicap' failed", re);
			return new ArrayList<SituationHandicap>();
		}
	}

	@Override
	public List<SituationHandicap> findByIdDossier(int id) {

		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from SituationHandicap r ");
			sqlQuery.append(" WHERE r.dossierEtudiant.id = :id ");
			TypedQuery<SituationHandicap> query = entityManager.createQuery(
					new String(sqlQuery), SituationHandicap.class);
			query.setParameter("id", id);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	@Override
	public SituationHandicap findSituationHandicap(int id, Date dateDebut,
			Date dateFin, String refCodeType) {
		try {
			if(refCodeType == null) {
				return null;
			}
			refCodeType = refCodeType.toLowerCase();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from SituationHandicap r ");
			sqlQuery.append(" WHERE r.dossierEtudiant.id = :id ");
			sqlQuery.append(" and lower(r.refCodeTypeHandicap) = :refCodeType ");
			if(dateFin == null) {
				sqlQuery.append("and (r.dateFin is null ");
				sqlQuery.append("or (r.dateDebut <=:dateDebut ");
				sqlQuery.append("and r.dateFin >=:dateDebut ");
				sqlQuery.append("and r.dateFin is not null)) ");
			} else {
				sqlQuery.append("and ((r.dateFin is null ");
				sqlQuery.append("and r.dateDebut <=:dateFin) ");
				sqlQuery.append("or (r.dateFin is not null ");
				sqlQuery.append("and r.dateDebut <=:dateDebut ");
				sqlQuery.append("and r.dateFin >=:dateDebut) ");
				sqlQuery.append("or (r.dateDebut <=:dateFin ");
				sqlQuery.append("and r.dateFin >=:dateFin) ");
				sqlQuery.append("or (r.dateDebut >=:dateDebut ");
				sqlQuery.append("and r.dateFin <=:dateFin)) ");
			}
			TypedQuery<SituationHandicap> query = entityManager.createQuery(
					new String(sqlQuery), SituationHandicap.class);
			query.setParameter("id", id);
			query.setParameter("refCodeType", refCodeType);
			query.setParameter("dateDebut", dateDebut);
			if (dateFin != null) {
				query.setParameter("dateFin", dateFin);
			}
			List<SituationHandicap> resultList = (List<SituationHandicap>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.info("findSituationHandicap success");
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findSituationHandicap failed", re);
			throw re;
		}
	}

}
