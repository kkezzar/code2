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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefModuleDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefModule;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefModule.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefModule
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refModuleDaoImpl")
@Transactional
public class RefModuleDaoImpl implements RefModuleDao {

	private static final Logger log = LoggerFactory.getLogger(RefModuleDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefModuleDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefModule)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefModule transientInstance) {
		log.debug("persisting RefModule instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefModuleDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefModule)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefModule persistentInstance) {
		log.debug("removing RefModule instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefModuleDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefModule)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefModule merge(RefModule detachedInstance) {
		log.debug("merging RefModule instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefModuleDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefModule findById(int id) {
		log.debug("getting RefModule instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefModule.class, id);
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
	public List<RefModule> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefModule> findAll() {
		log.debug("getting all RefModule instances");
		try {
			Query query = entityManager.createQuery("from RefModule refModule");
			log.debug("findAll 'RefModule' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefModule' failed", re);
			return new ArrayList<RefModule>();
		}
	}

	@Override
	public List<RefModule> findGeneric(String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value + "%".toLowerCase();
		;
		log.debug("getting List of RefModule instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where lower(r.identifiant) like :value ");
			request.append("or lower(r.nom) like :value ");
			request.append("or cast(r.rang as text) like :value ");
			request.append("group by r.refDomaine.rang, r.rang, r.id ");
			request.append("order by r.rang, r.refDomaine.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			if (resultList != null && resultList.size() == 0) {
				return null;
			}
			log.debug("findGeneric of RefModule successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric of RefModule failed", re);
			throw re;
		}
	}

	@Override
	public RefModule findByIdentifiant(String identifiant) {
		if (identifiant == null) {
			return null;
		}

		log.debug("get RefModule instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where lower(r.identifiant) = "
					+ UtilConstant.quotedString(identifiant.trim())
							.toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefModule successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefModule failed", re);
			throw re;
		}
	}

	@Override
	public RefModule findByName(Integer id, String name) {
		if (name == null) {
			return null;
		}

		log.debug("get RefModule instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where lower(r.nom) = "
					+ UtilConstant.quotedString(name.trim()).toLowerCase());
			if (id != null) {
				request.append("and r.id != " + id);
			}
			Query query = entityManager.createQuery(new String(request));
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefModule successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefDModule failed", re);
			throw re;
		}
	}

	@Override
	public int findModuleLastRang(int id) {
		log.debug("get last rang od RefModule instance id : " + id);
		int rang = 1;
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where r.refDomaine.id = :id ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				rang = resultList.get(0).getRang();
				rang++;
			}
			log.info("get findModuleLastRang successes");

		} catch (RuntimeException re) {
			log.error("get findModuleLastRang failed", re);
			throw re;
		}

		return rang;
	}

	@Override
	public List<RefModule> findModules(int id) {
		log.debug("get all modules af domaine instance with id : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where r.refDomaine.id = :id ");
			request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			log.info("findDomaines successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findDomaines failed", re);
			throw re;
		}
	}

	@Override
	public List<RefModule> findModules() {
		log.debug("getting all RefModule instance");
		try {
			StringBuilder request = new StringBuilder(
					"select DISTINCT r from RefModule r ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			log.debug("getting all RefModule successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("getting all RefModule failed", re);
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
					"select r from RefModule r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefModule> resultList = (List<RefModule>) query
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

	@Override
	public RefModule findByNameArabe(Integer id, String name) {
		if (name == null) {
			return null;
		}

		log.debug("get RefModule instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefModule r ");
			request.append("where lower(r.nomArabe) = "
					+ UtilConstant.quotedString(name.trim()).toLowerCase());
			if (id != null) {
				request.append("and r.id != " + id);
			}
			Query query = entityManager.createQuery(new String(request));
			List<RefModule> resultList = (List<RefModule>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefModule successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefDModule failed", re);
			throw re;
		}
	}

}
