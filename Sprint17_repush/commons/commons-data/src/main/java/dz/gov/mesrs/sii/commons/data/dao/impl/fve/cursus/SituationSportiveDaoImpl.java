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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SituationSportiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationSportive;

/**
 * Dao object for domain model class SituationSportive.
 * 
 * @see dz.gov.mesrs.sii.SituationSportiveDto.business.model.bo.SituationSportive
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository ("situationSportiveDao")  
@Transactional
public class SituationSportiveDaoImpl implements SituationSportiveDao {

	private static final Logger log = LoggerFactory.getLogger(SituationSportiveDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationSportiveDao#persist(dz.gov.mesrs.sii.SituationSportiveDto.business.model.bo.SituationSportive)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SituationSportive transientInstance) {
		log.debug("persisting SituationSportive instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationSportiveDao#remove(dz.gov.mesrs.sii.SituationSportiveDto.business.model.bo.SituationSportive)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SituationSportive persistentInstance) {
		log.debug("removing SituationSportive instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.SituationSportiveDao#merge(dz.gov.mesrs.sii.SituationSportiveDto.business.model.bo.SituationSportive)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SituationSportive merge(SituationSportive detachedInstance) {
		log.debug("merging SituationSportive instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NSituationSportiveDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public SituationSportive findById(int id) {
		log.debug("getting SituationSportive instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(SituationSportive.class, id);
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
	public List<SituationSportive> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SituationSportive> findAll() {
		log.debug("getting all SituationSportive instances");
		try {
			Query query = entityManager
					.createQuery("from SituationSportive situationSportive");
			log.debug("findAll 'SituationSportive' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SituationSportive' failed", re);
			return new ArrayList<SituationSportive>();
		}
	}

	@Override
	public List<SituationSportive> findByIdDossier(int id) {
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from SituationSportive r ");
			sqlQuery.append(" WHERE r.dossierEtudiant.id = :id ");
			TypedQuery<SituationSportive> query = entityManager.createQuery(
					new String(sqlQuery), SituationSportive.class);
			query.setParameter("id", id);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	@Override
	public SituationSportive findSituationSportive(int id, Date dateDebut,
			Date dateFin, String refCodeType) {
		try {
			if (refCodeType == null) {
				return null;
			}
			refCodeType = refCodeType.toLowerCase();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("SELECT r from SituationSportive r ");
			sqlQuery.append(" WHERE r.dossierEtudiant.id = :id ");
			sqlQuery.append(" and lower(r.refCodeDisciplineSportive) = :refCodeType ");
			if (dateFin == null) {
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
			TypedQuery<SituationSportive> query = entityManager.createQuery(
					new String(sqlQuery), SituationSportive.class);
			query.setParameter("id", id);
			query.setParameter("refCodeType", refCodeType);
			query.setParameter("dateDebut", dateDebut);
			if (dateFin != null) {
				query.setParameter("dateFin", dateFin);
			}
			List<SituationSportive> resultList = (List<SituationSportive>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.info("findSituationSportive success");
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findSituationSportive failed", re);
			throw re;
		}
	}
}
