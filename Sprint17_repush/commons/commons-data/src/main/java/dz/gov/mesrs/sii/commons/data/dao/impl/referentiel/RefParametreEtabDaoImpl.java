/**
 * [dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefParametreEtabDaoImpl.java] 
 * @author MAKERRI Sofiane on : 27 avr. 2014  14:15:55
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefParametreEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametreEtab;

/**
 * @author MAKERRI Sofiane on : 27 avr. 2014 14:15:55
 */
@Service("refParametreEtabDaoImpl")
public class RefParametreEtabDaoImpl implements RefParametreEtabDao {

	private static final Logger log = LoggerFactory.getLogger(RefParametreEtabDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefParametreEtabDaoImpl.RefParametreEtabDaoImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 27 avr. 2014 14:15:55
	 */
	public RefParametreEtabDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefParametreEtab transientInstance) {
		log.debug("persisting RefParametre instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefParametreEtab persistentInstance) {
		log.debug("removing RefParametre instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefParametreEtab merge(RefParametreEtab detachedInstance) {
		log.debug("merging RefParametre instance");
		try {
			RefParametreEtab result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public RefParametreEtab findById(int id) {
		log.debug("getting RefParametre instance with id: " + id);
		try {
			RefParametreEtab instance = entityManager.find(
					RefParametreEtab.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public RefParametreEtab findByKey(Integer id, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RefParametreEtab findByKey(Integer id, String idfEtab, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findValueOfKey(String key) {
		if (key == null) {
			return null;
		}
		key = key.toLowerCase();
		log.debug("getting List of RefParametreEtab instance with : " + key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametreEtab r ");
			request.append("where lower(r.refParametre.key) =:key ");
			// request.append("and r.refEtablissement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findValueOfKey successful");

			return resultList.get(0).getValue();

		} catch (RuntimeException re) {
			log.error("findValueOfKey failed", re);
			throw re;
		}
	}

	@Override
	public String findValueOfKey(Integer idEtab, String key) {
		if (key == null || idEtab == null) {
			return null;
		}
		key = key.toLowerCase();
		
		log.debug("getting List of RefParametreEtab instance with : " + key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametreEtab r ");
			request.append("where lower(r.refParametre.key) =:key ");
			request.append("and ((r.refEtablissement is null ");
			request.append("and r.refParametre.general = TRUE ) ");
			request.append("or (r.refEtablissement.id =:idEtab ");
			request.append("and r.refParametre.general = FALSE )) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			query.setParameter("idEtab", idEtab);
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findValueOfKey successful");
			return resultList.get(0).getValue();

		} catch (RuntimeException re) {
			log.error("findValueOfKey failed", re);
			throw re;
		}
	}

	@Override
	public List<RefParametreEtab> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefParametreEtab> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefParametreEtab> findGeneric(Integer idEtab, String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		
		log.debug("getting List of RefParametreEtab instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefParametreEtab r ");
			if (idEtab == null) {
				request.append("where r.refEtablissement is null ");
			} else {
				request.append("where (r.refEtablissement.id) =:idEtab ");
			}
			request.append("and (lower(r.refParametre.key) like :value ");
			request.append("or lower(r.refParametre.description) like :value ");
			request.append("or lower(r.refParametre.defaultValue) like :value ");
			request.append("or lower(r.value) like :value) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			if (idEtab != null) {
				query.setParameter("idEtab", idEtab);
			}
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefParametreEtab> findAll(int idEtab) {
		if (idEtab == 0) {
			return null;
		}
		//idfEtab = idfEtab.toLowerCase();
		log.debug("getting List of RefParametreEtab instance with : " + idEtab);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefParametreEtab r ");
			request.append("where r.refEtablissement.id =:idEtab ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEtab", idEtab);
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			log.debug("findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAll failed", re);
			throw re;
		}
	}

	@Override
	public RefParametreEtab findByKeyEtab(String idfEtab, String key) {
		if (key == null || idfEtab == null) {
			return null;
		}
		key = key.toLowerCase();
		idfEtab = idfEtab.toLowerCase();
		log.debug("getting RefParametreEtab instance with : " + key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametreEtab r ");
			request.append("where lower(r.refParametre.key) =:key ");
			request.append("and lower(r.refEtablissement.identifiant) =:idfEtab ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			query.setParameter("idfEtab", idfEtab);
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByKeyEtab successful");

			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByKeyEtab failed", re);
			throw re;
		}
	}

	@Override
	public List<RefParametreEtab> findAllNotGeneric() {
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefParametreEtab r ");
			request.append("where r.refEtablissement is null ");
			Query query = entityManager.createQuery(new String(request));
			@SuppressWarnings("unchecked")
			List<RefParametreEtab> resultList = (List<RefParametreEtab>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

}
