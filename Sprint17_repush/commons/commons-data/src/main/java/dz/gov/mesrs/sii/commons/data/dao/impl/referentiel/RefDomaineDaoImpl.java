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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaine;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefDomaine.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefDomaine
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refDomaineDaoImpl")  @Transactional
public class RefDomaineDaoImpl implements RefDomaineDao {

	private static final Logger log = LoggerFactory.getLogger(RefDomaineDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefDomaineDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefDomaine)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefDomaine transientInstance) {
		log.debug("persisting RefDomaine instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefDomaineDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefDomaine)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefDomaine persistentInstance) {
		log.debug("removing RefDomaine instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefDomaineDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefDomaine)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefDomaine merge(RefDomaine detachedInstance) {
		log.debug("merging RefDomaine instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefDomaineDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefDomaine findById(int id) {
		log.debug("getting RefDomaine instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefDomaine.class, id);
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
	public List<RefDomaine> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefDomaine> findAll() {
		log.debug("getting all RefDomaine instances");
		try {
			Query query = entityManager.createQuery("from RefDomaine refDomaine");
			log.debug("findAll 'RefDomaine' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefDomaine' failed", re);
			return new ArrayList<RefDomaine>();
		}
	}



	@Override
	public List<RefDomaine> findGeneric(String value) {
		if(value == null) {
			return null;
		}
		value = "%" + value + "%".toLowerCase();;
		log.debug("getting List of RefDomaine instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select DISTINCT r from RefDomaine r ");
			request.append("where (r.refDomaine is null) ");
			request.append("and (lower(r.identifiant) like :value ");
			request.append("or lower(r.nom) like :value ");
			request.append("or cast(r.rang as text) like :value ");
			request.append("or lower(r.refDomaine.identifiant) like :value ");
			request.append("or lower(r.refDomaine.nom) like :value) ");
			request.append("group by r.refDomaine.nom, r.id ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			log.debug("findGeneric of RefDomaine successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric of RefDomaine failed", re);
			throw re;
		}
	}



	@Override
	public RefDomaine findByIdentifiant(String identifiant) {
		if(identifiant == null) {
			return null;
		}
		
		log.debug("get RefDomaine instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where lower(r.identifiant) = " + UtilConstant.quotedString(identifiant.trim()).toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			if(resultList != null && resultList.size() > 0) {
				log.debug("get RefDomaine successful");
				return resultList.get(0);
			} else 	return null;

		} catch (RuntimeException re) {
			log.error("get RefDomainee failed", re);
			throw re;
		}
		
	}



	@Override
	public List<RefDomaine> findSubDomaine(int id) {
		log.debug("get RefDomaine instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where r.refDomaine.id = :id ");
			request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefDomainee failed", re);
			throw re;
		}
	}



	@Override
	public int findSubDomaineLastRang(int id) {
		
		log.debug("get last rang od RefDomaine instance id : " + id);
		int rang = 1;
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where r.refDomaine.id = :id ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			if(resultList != null && resultList.size() > 0) {
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
	public RefDomaine findByName(String name) {
		if(name == null) {
			return null;
		}
		
		log.debug("get RefDomaine instance with : " + name);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where lower(r.nom) = " + UtilConstant.quotedString(name.trim()).toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			if(resultList != null && resultList.size() > 0) {
				log.debug("get RefDomaine successful");
				return resultList.get(0);
			} else 	return null;

		} catch (RuntimeException re) {
			log.error("get RefDomaine failed", re);
			throw re;
		}
	}



	@Override
	public List<RefDomaine> findDomaines() {
		
		log.debug("getting all RefDomaine instance");
		try {
			StringBuilder request = new StringBuilder(
					"select DISTINCT r from RefDomaine r ");
			request.append("where (r.refDomaine is null) ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			log.debug("getting all RefDomaine successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("getting all RefDomaine failed", re);
			throw re;
		}
	}



	@Override
	public int findDomaineLastRang() {
		log.debug("get last rang od RefDomaine instance id : ");
		int rang = 1;
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where r.refDomaine is null ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			if(resultList != null && resultList.size() > 0) {
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
	public List<RefDomaine> findSubDomaine(String identifiant) {
		log.debug("get subDomaine instance with identifiant : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where r.refDomaine.identifiant = :identifiant ");
			request.append("order by r.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("identifiant", identifiant);
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			return resultList;

		} catch (RuntimeException re) {
			log.error("get subDomaine failed", re);
			throw re;
		}
	}



	@Override
	public List<RefDomaine> findDomainesAndSubDomaine() {
		log.debug("getting all RefDomaine and SubDomaine instance");
		try {
			StringBuilder request = new StringBuilder(
					"select DISTINCT r from RefDomaine r ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			log.debug("getting all RefDomaine and SubDomaine successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("getting all RefDomaine and SubDomaine failed", re);
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
					"select r from RefDomaine r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
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
	public RefDomaine findByNameArabe(String nameArabe) {
		if(nameArabe == null) {
			return null;
		}
		
		log.debug("get RefDomaine instance with : " + nameArabe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaine r ");
			request.append("where lower(r.nomArabe) = " + UtilConstant.quotedString(nameArabe.trim()).toLowerCase());
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaine> resultList = (List<RefDomaine>) query
					.getResultList();
			if(resultList != null && resultList.size() > 0) {
				log.debug("get RefDomaine successful");
				return resultList.get(0);
			} else 	return null;

		} catch (RuntimeException re) {
			log.error("get RefDomaine failed", re);
			throw re;
		}
	}
}
