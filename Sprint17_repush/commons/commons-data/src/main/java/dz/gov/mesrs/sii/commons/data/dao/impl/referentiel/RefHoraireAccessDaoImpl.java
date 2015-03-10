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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefHoraireAccessDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHoraireAccess;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefHoraireAccess.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefHoraireAccess
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refHoraireAccessDaoImpl")
@Transactional
public class RefHoraireAccessDaoImpl implements RefHoraireAccessDao {

	private static final Logger log = LoggerFactory.getLogger(RefHoraireAccessDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefHoraireAccessDao#persist(dz.gov.mesrs.sii.RefHoraireAccessDto.business.model.bo.RefHoraireAccess)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefHoraireAccess transientInstance) {
		log.debug("persisting RefHoraireAccess instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefHoraireAccessDao#remove(dz.gov.mesrs.sii.RefHoraireAccessDto.business.model.bo.RefHoraireAccess)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefHoraireAccess persistentInstance) {
		log.debug("removing RefHoraireAccess instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefHoraireAccessDao#merge(dz.gov.mesrs.sii.RefHoraireAccessDto.business.model.bo.RefHoraireAccess)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefHoraireAccess merge(RefHoraireAccess detachedInstance) {
		log.debug("merging RefHoraireAccess instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefHoraireAccessDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefHoraireAccess findById(int id) {
		log.debug("getting RefHoraireAccess instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefHoraireAccess.class, id);
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
	public List<RefHoraireAccess> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefHoraireAccess> findAll() {
		log.debug("getting all RefHoraireAccess instances");
		try {
			Query query = entityManager
					.createQuery("from RefHoraireAccess refHoraireAccess");
			log.debug("findAll 'RefHoraireAccess' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefHoraireAccess' failed", re);
			return new ArrayList<RefHoraireAccess>();
		}
	}

	@Override
	public List<RefHoraireAccess> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of Plageheure instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefHoraireAccess r ");
			request.append("where r.identifiant like :value ");
			request.append("or r.nom like :value ");
			request.append("or cast(r.heureDebut as text) like :value ");
			request.append("or cast(r.heureFin as text) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			@SuppressWarnings("unchecked")
			List<RefHoraireAccess> resultList = (List<RefHoraireAccess>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
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
					"select r from RefHoraireAccess r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefHoraireAccess> resultList = (List<RefHoraireAccess>) query
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
