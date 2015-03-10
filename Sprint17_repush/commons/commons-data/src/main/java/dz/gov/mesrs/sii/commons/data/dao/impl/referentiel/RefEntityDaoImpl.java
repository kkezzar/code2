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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEntityDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEntity;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefEntity.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefEntity
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refEntityDaoImpl")  @Transactional
public class RefEntityDaoImpl implements RefEntityDao {

	private static final Logger log = LoggerFactory.getLogger(RefEntityDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEntityDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefEntity)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefEntity transientInstance) {
		log.debug("persisting RefEntity instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEntityDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefEntity)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefEntity persistentInstance) {
		log.debug("removing RefEntity instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEntityDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefEntity)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefEntity merge(RefEntity detachedInstance) {
		log.debug("merging RefEntity instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefEntityDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefEntity findById(int id) {
		log.debug("getting RefEntity instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefEntity.class, id);
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
	public List<RefEntity> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefEntity> findAll() {
		log.debug("getting all RefEntity instances");
		try {
			Query query = entityManager.createQuery("from RefEntity refEntity");
			log.debug("findAll 'RefEntity' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefEntity' failed", re);
			return new ArrayList<RefEntity>();
		}
	}



	@Override
	public List<RefEntity> findByDomain(Integer idDomain) {
		List<RefEntity> resultList = new ArrayList();
		log.debug("findByDomain: getting List of refEntity instance : ");
		

		String firstOpr = UtilConstant.WHERE_SQL_STR;	
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEntity  r  ");
			

			if (idDomain != null ) {
				request.append(firstOpr ).append( " r.refDomaine.id  = " ).append(idDomain);
			}
			

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			 resultList = (List<RefEntity>) query.getResultList();
			log.debug("findByDomain successful");
			return resultList;
		
		} catch (RuntimeException re) {
			log.error("findByDomain failed", re);
			throw re;
		}
	}
}
