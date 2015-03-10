package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.RoleI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.RoleI18n;

/**
 * Dao object for domain model class RoleI18n.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RoleI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("roleI18nDao")
public class RoleI18nDaoImpl implements RoleI18nDao {

	private static final Logger log = LoggerFactory.getLogger(RoleI18nDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RoleI18nDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RoleI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RoleI18n transientInstance) {
		log.debug("persisting RoleI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RoleI18nDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RoleI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RoleI18n persistentInstance) {
		log.debug("removing RoleI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RoleI18nDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RoleI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RoleI18n merge(RoleI18n detachedInstance) {
		log.debug("merging RoleI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRoleI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RoleI18n findById(int id) {
		log.debug("getting RoleI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RoleI18n.class, id);
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
	public List<RoleI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RoleI18n> findAll() {
		log.debug("getting all RoleI18n instances");
		try {
			Query query = entityManager.createQuery("from RoleI18n roleI18n");
			log.debug("findAll 'RoleI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RoleI18n' failed", re);
			return new ArrayList<RoleI18n>();
		}
	}



	@Override
	public List<RoleI18n> findListRoleI18nByRole(int idRole) {
     log.debug("findListRoleI18nByRole: getting List of RoleI18n instance : ");
		

		try {
			StringBuilder request = new StringBuilder(
					"select r from RoleI18n r where r.role.id = ").append(idRole);
	
			Query query = entityManager.createQuery(request.toString());
			List<RoleI18n> resultList = (List<RoleI18n>) query
					.getResultList();
			log.debug("findListRoleI18nByRole successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findListRoleI18nByRole failed", re);

			throw re;
		}
	}
}
