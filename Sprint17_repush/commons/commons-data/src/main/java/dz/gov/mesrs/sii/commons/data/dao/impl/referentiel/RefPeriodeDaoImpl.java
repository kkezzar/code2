package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPeriode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefPeriode.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefPeriode
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refPeriodeDaoImpl")
@Transactional
public class RefPeriodeDaoImpl implements RefPeriodeDao {

	private static final Logger log = LoggerFactory.getLogger(RefPeriodeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPeriodeDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefPeriode)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefPeriode transientInstance) {
		log.debug("persisting RefPeriode instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPeriodeDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefPeriode)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefPeriode persistentInstance) {
		log.debug("removing RefPeriode instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPeriodeDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefPeriode)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefPeriode merge(RefPeriode detachedInstance) {
		log.debug("merging RefPeriode instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefPeriodeDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefPeriode findById(int id) {
		log.debug("getting RefPeriode instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefPeriode.class, id);
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
	public List<RefPeriode> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefPeriode> findAll() {
		log.debug("getting all RefPeriode instances");
		try {
			Query query = entityManager
					.createQuery("from RefPeriode refPeriode");
			log.debug("findAll 'RefPeriode' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefPeriode' failed", re);
			return new ArrayList<RefPeriode>();
		}
	}

	@Override
	public List<RefPeriode> findGeneric(String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		log.debug("getting List of RefPeriode instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPeriode r ");
			request.append("where lower(r.identifiant) like :value ");
			request.append("or lower(r.nom) like :value ");
			request.append("or cast(r.dateDebut as text) like :value ");
			request.append("or cast(r.dateFin as text) like :value ");
			request.append("or cast(r.periodique as text) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefPeriode> resultList = (List<RefPeriode>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByName failed", re);
			throw re;
		}
	}

	@Override
	public RefPeriode findByIdentifiant(String identifiant) {
		if (identifiant == null) {
			return null;
		}

		log.debug("get RefPeriode instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPeriode r ");
			request.append("where lower(r.identifiant) = "
					+ UtilConstant.quotedString(identifiant.trim())
							.toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefPeriode> resultList = (List<RefPeriode>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefPeriode successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefPeriode failed", re);
			throw re;
		}
	}

	@Override
	public RefPeriode findByName(String name) {
		if (name == null) {
			return null;
		}

		log.debug("get RefPeriode instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPeriode r ");
			request.append("where lower(r.nom) = "
					+ UtilConstant.quotedString(name.trim()).toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefPeriode> resultList = (List<RefPeriode>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefPeriode successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefPeriode failed", re);
			throw re;
		}
	}

	@Override
	public Integer findLastOrder(String prefix, int orderLength) {
		if (prefix == null) {
			return null;
		}
		int prefixLength = prefix.length();
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPeriode r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefPeriode> resultList = (List<RefPeriode>) query
					.getResultList();
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
}
