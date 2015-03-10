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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPlageAdresseDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPlageAdresse;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefPlageAdresse.
 * 
 * @see dz.gov.mesrs.sii.RefPlageAdresseDto.business.model.bo.RefPlageAdresse
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refPlageAdresseDaoImpl")
@Transactional
public class RefPlageAdresseDaoImpl implements RefPlageAdresseDao {

	private static final Logger log = LoggerFactory.getLogger(RefPlageAdresseDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPlageAdresseDao#persist(dz.gov.mesrs.sii.RefPlageAdresseDto.business.model.bo.RefPlageAdresse)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefPlageAdresse transientInstance) {
		log.debug("persisting RefPlageAdresse instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPlageAdresseDao#remove(dz.gov.mesrs.sii.RefPlageAdresseDto.business.model.bo.RefPlageAdresse)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefPlageAdresse persistentInstance) {
		log.debug("removing RefPlageAdresse instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPlageAdresseDao#merge(dz.gov.mesrs.sii.RefPlageAdresseDto.business.model.bo.RefPlageAdresse)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefPlageAdresse merge(RefPlageAdresse detachedInstance) {
		log.debug("merging RefPlageAdresse instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefPlageAdresseDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefPlageAdresse findById(int id) {
		log.debug("getting RefPlageAdresse instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefPlageAdresse.class, id);
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
	public List<RefPlageAdresse> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefPlageAdresse> findAll() {
		log.debug("getting all RefPlageAdresse instances");
		try {
			Query query = entityManager
					.createQuery("from RefPlageAdresse refPlageAdresse");
			log.debug("findAll 'RefPlageAdresse' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefPlageAdresse' failed", re);
			return new ArrayList<RefPlageAdresse>();
		}
	}

	@Override
	public List<RefPlageAdresse> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of PlageAdresse instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPlageAdresse r ");
			request.append("where r.identifiant like :value ");
			request.append("or r.nom like :value ");
			request.append("or r.adresseDebut like :value ");
			request.append("or r.adresseFin like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			@SuppressWarnings("unchecked")
			List<RefPlageAdresse> resultList = (List<RefPlageAdresse>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPlageAdresse> findAll(Integer etabId) {
		log.debug("getting all RefPlageAdresse instances");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPlageAdresse r ");
			request.append(" where (1=1)");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			Query query = entityManager.createQuery(new String(request));
			log.debug("findAll 'RefPlageAdresse' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefPlageAdresse' failed", re);
			return new ArrayList<RefPlageAdresse>();
		}
	}

	@Override
	public List<RefPlageAdresse> findGeneric(Integer etabId, String value) {
		value = "%" + value + "%";
		log.debug("getting List of PlageAdresse instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPlageAdresse r ");
			request.append(" where (1=1)");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			request.append(" and (r.identifiant like :value ");
			request.append("or r.nom like :value ");
			request.append("or r.adresseDebut like :value ");
			request.append("or r.adresseFin like :value) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			@SuppressWarnings("unchecked")
			List<RefPlageAdresse> resultList = (List<RefPlageAdresse>) query
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
					"select r from RefPlageAdresse r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefPlageAdresse> resultList = (List<RefPlageAdresse>) query
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
