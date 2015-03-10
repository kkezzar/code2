package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NcNamesDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.NcNames;

/**
 * Dao object for domain model class NcList.
 * @see dz.gov.mesrs.sii.NcNames.business.model.bo.NcList
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("ncNamesDaoImpl") 
public class NcNamesDaoImpl implements NcNamesDao {

	private static final Logger log = LoggerFactory.getLogger(NcNamesDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.NcNamesDao.business.persistence.jpa.impl.NcListDao#persist(dz.gov.mesrs.sii.NcNames.business.model.bo.NcList)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(NcNames transientInstance) {
		log.debug("persisting NcNames instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.NcNamesDao.business.persistence.jpa.impl.NcListDao#remove(dz.gov.mesrs.sii.NcNames.business.model.bo.NcList)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(NcNames persistentInstance) {
		log.debug("removing NcNames instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.NcNamesDao.business.persistence.jpa.impl.NcListDao#merge(dz.gov.mesrs.sii.NcNames.business.model.bo.NcList)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public NcNames merge(NcNames detachedInstance) {
		log.debug("merging NcNames instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NNcListDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public NcNames findById(int id) {
		log.debug("getting NcNames instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(NcNames.class, id);
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
	public List<NcNames> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<NcNames> findAll() {
		log.debug("getting all NcNames instances");
		try {
			Query query = entityManager.createQuery("from NcNames ncNames order by ncNames.nomNomenclature");
			log.debug("findAll 'NcNames' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'NcNames' failed", re);
			return new ArrayList<NcNames>();
		}
	}



	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NcListDao#findGeneric()
	 */
	@Override
	public List<NcNames> findGeneric(String value) {
		value = ("%" + value + "%").toLowerCase();
		log.debug("getting nc list with : " + value);
		try {
			StringBuilder request = new StringBuilder();
			request.append("select r from NcNames r ");
			request.append(" where cast(r.id as text) like :value ");
			request.append(" or lower(r.nomNomenclature) like :value ");
			request.append(" or lower(r.description) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<NcNames> resultList = (List<NcNames>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NcNamesDao#findByName(java.lang.String)
	 */
	@Override
	public NcNames findByName(String ncName) {
		if (ncName == null) return null;
		try {
			ncName = ncName.toLowerCase();
			StringBuilder request = new StringBuilder();
			request.append("select r from NcNames r ");
			request.append(" where lower(r.nomNomenclature) = :ncName ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("ncName", ncName);
			List<NcNames> resultList = (List<NcNames>) query
					.getResultList();
			if  ((resultList != null) && (!resultList.isEmpty())) {
				NcNames result =  resultList.get(0);
				log.debug("get NcNames by name successful");
				return result;
			}
			

		} catch (RuntimeException re) {
			log.error("findByName failed", re);
			throw re;
			//return null;
		}
		return null;
	}



	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NcNamesDao#HasReference(int)
	 */
	@Override
	public boolean hasReference(int id) {
		try {
			StringBuilder request = new StringBuilder();
			request.append("select r from NcNames r ");
			request.append(" where r.id = " + id);
			request.append(" and r.ncNames.id is not null");
			Query query = entityManager.createQuery(new String(request));
			List<NcNames> result = (List<NcNames>) query
					.getResultList();
			log.debug("hasReference successful");
			return (result != null && result.size() > 0) ? true : false;

		} catch (RuntimeException re) {
			log.error("hasReference failed", re);
			//throw re;
			return false;
		}
		
	}



	@Override
	public NcNames findDefaultValue(String ncName) {
		if (ncName == null) return null;
		try {
			ncName = ncName.toLowerCase();
			StringBuilder request = new StringBuilder();
			request.append("select r from NcNames r ");
			request.append(" where lower(r.nomNomenclature) = :ncName ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("ncName", ncName);
			List<NcNames> resultList = (List<NcNames>) query
					.getResultList();
			if  ((resultList != null) && (!resultList.isEmpty())) {
				NcNames result =  resultList.get(0);
				log.debug("get NcNames by name successful");
				return result;
			}
			

		} catch (RuntimeException re) {
			log.error("findByName failed", re);
			throw re;
			//return null;
		}
		return null;
	}


}
