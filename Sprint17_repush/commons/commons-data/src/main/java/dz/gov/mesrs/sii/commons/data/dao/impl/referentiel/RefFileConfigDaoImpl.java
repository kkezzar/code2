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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFileConfigDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFileConfig;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefFileConfig.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefFileConfig
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refFileConfigDaoImpl")  @Transactional
public class RefFileConfigDaoImpl implements RefFileConfigDao {

	private static final Logger log = LoggerFactory.getLogger(RefFileConfigDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFileConfigDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefFileConfig)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefFileConfig transientInstance) {
		log.debug("persisting RefFileConfig instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFileConfigDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefFileConfig)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefFileConfig persistentInstance) {
		log.debug("removing RefFileConfig instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefFileConfigDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefFileConfig)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefFileConfig merge(RefFileConfig detachedInstance) {
		log.debug("merging RefFileConfig instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefFileConfigDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefFileConfig findById(int id) {
		log.debug("getting RefFileConfig instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefFileConfig.class, id);
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
	public List<RefFileConfig> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefFileConfig> findAll() {
		log.debug("getting all RefFileConfig instances");
		try {
			Query query = entityManager.createQuery("from RefFileConfig refFileConfig");
			log.debug("findAll 'RefFileConfig' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefFileConfig' failed", re);
			return new ArrayList<RefFileConfig>();
		}
	}



	@Override
	public List<RefFileConfig> findByEntity(Integer idDomain, Integer idEntity,
			Integer idLanguage) {
		List<RefFileConfig> resultList = new ArrayList();
		log.debug("findByEntity: getting List of refFileConfig instance : ");
		
//		private RefEntity refEntity;
//		private Nomenclature nomenclature;
//		private RefConfiguration refConfiguration;
//		private String name;
//		private String url;
//		private String type;
//		private String defaultFile;
		String firstOpr = UtilConstant.WHERE_SQL_STR;	
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFileConfig  r  ");
			request.append(firstOpr ).append( " r.type = 'IHM'");
			firstOpr = UtilConstant.AND_SQL_STR;

			if (idDomain != null ) {
				request.append(firstOpr ).append( " r.refEntity.refDomaine.id  = " ).append(idDomain);
				
				firstOpr = UtilConstant.AND_SQL_STR;
			
			}
			if ((idEntity != null) ) {
				request.append(firstOpr ).append( " r.refEntity.id  = " ).append(idEntity);
				firstOpr = UtilConstant.AND_SQL_STR;
			}
			
			if (idLanguage != null) {
				request.append(firstOpr).append( " r.nomenclature.id = " ).append(idLanguage);
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			 resultList = (List<RefFileConfig>) query.getResultList();
			log.debug("findByEntity successful");
			return resultList;
		
		} catch (RuntimeException re) {
			log.error("findByEntity failed", re);
			throw re;
		}
		
		
	}



	@Override
	public List<RefFileConfig> findByConfiguration(Integer idDomain,
			Integer idConfiguration) {
		List<RefFileConfig> resultList = new ArrayList();
		log.debug("findByEntity: getting List of refFileConfig instance : ");
		
//		private RefEntity refEntity;
//		private Nomenclature nomenclature;
//		private RefConfiguration refConfiguration;
//		private String name;
//		private String url;
//		private String type;
//		private String defaultFile;
		String firstOpr = UtilConstant.WHERE_SQL_STR;	
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFileConfig  r  ");
			request.append(firstOpr ).append( " r.type = 'Configuration'");
			firstOpr = UtilConstant.AND_SQL_STR;

			if (idDomain != null ) {
				request.append(firstOpr ).append( " r.refConfiguration.refDomaine.id  = " ).append(idDomain);
				firstOpr = UtilConstant.OR_SQL_STR;
				firstOpr = UtilConstant.AND_SQL_STR;
			
			}
			if ((idConfiguration != null) ) {
				request.append(firstOpr ).append( " r.refConfiguration.id  = " ).append(idConfiguration);
				firstOpr = UtilConstant.AND_SQL_STR;
			}
			
			
			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			 resultList = (List<RefFileConfig>) query.getResultList();
			log.debug("findByEntity successful");
			return resultList;
		
		} catch (RuntimeException re) {
			log.error("findByEntity failed", re);
			throw re;
		}
	}
}
