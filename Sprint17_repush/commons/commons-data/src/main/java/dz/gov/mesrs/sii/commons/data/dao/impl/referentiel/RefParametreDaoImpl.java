package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

// Generated 22 avr. 2014 12:35:32 by Hibernate Tools 3.6.0

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefParametreDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefParametre;

/**
 * Home object for domain model class RefParametre.
 * 
 * @see dz.gov.mesrs.sii.referentiel.RefParametre
 * @author Hibernate Tools
 */
@Service("refParametreDaoImpl")
public class RefParametreDaoImpl implements
		RefParametreDao {

	private static final Logger log = LoggerFactory.getLogger(RefParametreDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RefParametre transientInstance) {
		log.debug("persisting RefParametre instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RefParametre persistentInstance) {
		log.debug("removing RefParametre instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RefParametre merge(
			RefParametre detachedInstance) {
		log.debug("merging RefParametre instance");
		try {
			RefParametre result = entityManager
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RefParametre findById(int id) {
		log.debug("getting RefParametre instance with id: " + id);
		try {
			RefParametre instance = entityManager.find(
					RefParametre.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefParametre> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefParametre> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefParametre> findGeneric(String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		log.debug("getting List of RefParametre instance with : "
				+ value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where lower(r.key) like :value ");
			request.append("or lower(r.description) like :value ");
			request.append("or lower(r.defaultValue) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public RefParametre findByKey(Integer id, String key) {
		if (key == null) {
			return null;
		}
		key = key.toLowerCase();
		log.debug("getting List of RefParametre instance with : "
				+ key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where lower(r.key) =:key ");
			if (id != null) {
				request.append("and r.id !=:id ");
			}
			// request.append("and r.refEtablissement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			if (id != null) {
				query.setParameter("id", id);
			}
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByKey successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByKey failed", re);
			throw re;
		}

	}

	@Override
	public String findValueOfKey(String key) {
		if (key == null) {
			return null;
		}
		key = key.toLowerCase();
		log.debug("getting List of RefParametre instance with : "
				+ key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where lower(r.key) =:key ");
			// request.append("and r.refEtablissement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findValueOfKey successful");
			return "";
			//return resultList.get(0).getValue();

		} catch (RuntimeException re) {
			log.error("findValueOfKey failed", re);
			throw re;
		}
	}

	@Override
	public RefParametre findByKey(Integer id, String idfEtab,
			String key) {
		if (key == null || idfEtab == null) {
			return null;
		}
		key = key.toLowerCase();
		idfEtab = idfEtab.toLowerCase();
		log.debug("getting List of RefParametre instance with : "
				+ key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where lower(r.key) =:key ");
			if (id != null) {
				request.append("and r.id !=:id ");
			}
			request.append("and (lower(r.refEtablissement.identifiant) =:idfEtab ");
			request.append("or r.refEtablissement is null) ");
			Query query = entityManager.createQuery(new String(request));
			if (id != null) {
				query.setParameter("id", id);
			}
			query.setParameter("key", key);
			query.setParameter("idfEtab", idfEtab);
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByKey successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByKey failed", re);
			throw re;
		}
	}

	@Override
	public String findValueOfKey(String idfEtab, String key) {
		if (key == null || idfEtab == null) {
			return null;
		}
		key = key.toLowerCase();
		idfEtab = idfEtab.toLowerCase();
		log.debug("getting List of RefParametre instance with : "
				+ key);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where lower(r.key) =:key ");
			request.append("and (lower(r.refEtablissement.identifiant) =:idfEtab ");
			request.append("or r.refEtablissement is null) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("key", key);
			query.setParameter("idfEtab", idfEtab);
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findValueOfKey successful");
			//return resultList.get(0).getValue();
			return "";

		} catch (RuntimeException re) {
			log.error("findValueOfKey failed", re);
			throw re;
		}
	}

	@Override
	public List<RefParametre> findAllNotGeneric() {
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefParametre r ");
			request.append("where r.general = FALSE");
			Query query = entityManager.createQuery(new String(request));
			@SuppressWarnings("unchecked")
			List<RefParametre> resultList = (List<RefParametre>) query
					.getResultList();
			log.debug("findAllNotGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAllNotGeneric failed", re);
			throw re;
		}
	}
}
