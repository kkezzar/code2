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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFonctionDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFonction;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefModule;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefFonction.
 * 
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefFonction
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refFonctionDaoImpl")
@Transactional
public class RefFonctionDaoImpl implements RefFonctionDao {

	private static final Logger log = LoggerFactory.getLogger(RefFonctionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFonctionDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefFonction)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefFonction transientInstance) {
		log.debug("persisting RefFonction instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFonctionDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefFonction)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefFonction persistentInstance) {
		log.debug("removing RefFonction instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFonctionDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefFonction)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefFonction merge(RefFonction detachedInstance) {
		log.debug("merging RefFonction instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefFonctionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefFonction findById(int id) {
		log.debug("getting RefFonction instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefFonction.class, id);
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
	public List<RefFonction> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefFonction> findAll() {
		log.debug("getting all RefFonction instances");
		try {
			Query query = entityManager
					.createQuery("from RefFonction refFonction where refFonction.refFonction is null ");
			log.debug("findAll 'RefFonction' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefFonction' failed", re);
			return new ArrayList<RefFonction>();
		}
	}

	@Override
	public List<RefFonction> findGeneric(String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value + "%".toLowerCase();
		;
		log.debug("getting List of RefFonction instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refFonction is null ");
			request.append("and (lower(r.identifiant) like :value ");
			request.append("or lower(r.nom) like :value ");
			request.append("or cast(r.rang as text) like :value) ");
			request.append("group by r.refModule.refDomaine.rang, r.refModule.rang, r.rang, r.id ");
			request.append("order by r.refModule.refDomaine.rang, r.refModule.rang, r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.debug("findGeneric of RefFonction successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric of RefFonction failed", re);
			throw re;
		}
	}

	@Override
	public RefFonction findByIdentifiant(String identifiant) {
		if (identifiant == null) {
			return null;
		}

		log.debug("get RefFonction instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where lower(r.identifiant) = "
					+ UtilConstant.quotedString(identifiant.trim())
							.toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefFonction successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefFonction failed", re);
			throw re;
		}
	}

	@Override
	public List<RefModule> findModules(int id) {
		log.debug("get all modules af domaine instance with id : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refModule.id = :id ");
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
	public List<RefFonction> findFonctions(int id) {
		log.debug("get all fonction af module instance with id : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefFonction r ");
			request.append("where r.refModule.id = :id ");
			request.append("and r.refFonction is null");
			// request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.info("findFonctions successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFonctions failed", re);
			throw re;
		}
	}

	@Override
	public int findFonctionLastRang(int id) {
		log.debug("get last rang od RefFonction instance id : " + id);
		int rang = 1;
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refModule.id = :id ");
			request.append("and r.refFonction is null ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				rang = resultList.get(0).getRang();
				rang++;
			}

		} catch (RuntimeException re) {
			log.error("get RefDomainee failed", re);
			throw re;
		}

		return rang;
	}

	@Override
	public RefFonction findByName(Integer id, String name) {
		if (name == null) {
			return null;
		}

		log.debug("get RefFonction instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where lower(r.nom) = "
					+ UtilConstant.quotedString(name.trim()).toLowerCase());
			if (id != null) {
				request.append(" and r.id !=  " + id);
			}
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefFonction successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefFonction failed", re);
			throw re;
		}
	}

	@Override
	public List<RefFonction> findFonctionsOfDomaine(int idDomaine) {
		log.debug("get all fonction af domaine instance with id : " + idDomaine);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refModule.refDomaine.id = :idDomaine ");
			request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idDomaine", idDomaine);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.info("findFonctions successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFonctions failed", re);
			throw re;
		}
	}

	@Override
	public List<RefFonction> findAllFonctions(String domaine) {
		log.debug("get all fonctions ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refFonction is null ");
			request.append("and lower(r.refModule.refDomaine.nom) = "
					+ UtilConstant.quotedString(domaine.toLowerCase()));
			request.append(" order by r.refModule.refDomaine.rang, r.refModule.rang, r.rang");
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.info("findFonctions successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFonctions failed", re);
			throw re;
		}
	}

	@Override
	public List<RefFonction> findActionGeneric(String value) {
		if (value == null) {
			return null;
		}
		value = "%" + value + "%".toLowerCase();
		;
		log.debug("getting List of action instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refFonction is not null ");
			request.append("and lower(r.identifiant) like :value ");
			request.append("or lower(r.nom) like :value ");
			request.append("or cast(r.rang as text) like :value ");
			request.append("group by r.refFonction.rang, r.rang, r.id ");
			request.append("order by r.refFonction.rang, r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.debug("findGeneric of action successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric of action failed", re);
			throw re;
		}
	}

	@Override
	public int findActionLastRang(int id) {
		log.debug("get last rang of RefFonction instance id : " + id);
		int rang = 1;
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refFonction.id = :id ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				rang = resultList.get(0).getRang();
				rang++;
			}

		} catch (RuntimeException re) {
			log.error("get RefDomainee failed", re);
			throw re;
		}

		return rang;
	}

	@Override
	public List<RefFonction> findActions(int id) {
		log.debug("get all actions af fonction instance with id : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefFonction r ");
			request.append("where r.refFonction.id = :id ");
			request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.info("findFonctions successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFonctions failed", re);
			throw re;
		}
	}

	@Override
	public List<RefFonction> findAllActions() {
		log.debug("get all fonctions ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("order by r.refModule.refDomaine.rang, r.refModule.rang, r.rang");
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			log.info("findFonctions successed");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFonctions failed", re);
			throw re;
		}
	}

	@Override
	public RefFonction findPeriode(int idPeriode) {
		log.debug("get RefFonction instance with periode id : " + idPeriode);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where r.refPeriode =:idPeriode ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idPeriode", idPeriode);
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefFonction successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefFonction failed", re);
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
					"select r from RefFonction r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
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
	public RefFonction findByNameArabe(Integer id, String name) {
		if (name == null) {
			return null;
		}

		log.debug("get RefFonction instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFonction r ");
			request.append("where lower(r.nomArabe) = "
					+ UtilConstant.quotedString(name.trim()).toLowerCase());
			if (id != null) {
				request.append(" and r.id !=  " + id);
			}
			Query query = entityManager.createQuery(new String(request));
			List<RefFonction> resultList = (List<RefFonction>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefFonction successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefFonction failed", re);
			throw re;
		}
	}
}
