package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeRoleDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.EtapeRole;
import dz.gov.mesrs.sii.commons.data.model.bpm.Role;

/**
 * Dao object for domain model class EtapeRole.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.EtapeRole
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("etapeRoleDao")
public class EtapeRoleDaoImpl implements EtapeRoleDao {

	private static final Logger log = LoggerFactory.getLogger(EtapeRoleDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeRoleDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeRole)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(EtapeRole transientInstance) {
		log.debug("persisting EtapeRole instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeRoleDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeRole)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(EtapeRole persistentInstance) {
		log.debug("removing EtapeRole instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.EtapeRoleDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.EtapeRole)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EtapeRole merge(EtapeRole detachedInstance) {
		log.debug("merging EtapeRole instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NEtapeRoleDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public EtapeRole findById(int id) {
		log.debug("getting EtapeRole instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(EtapeRole.class, id);
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
	public List<EtapeRole> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<EtapeRole> findAll() {
		log.debug("getting all EtapeRole instances");
		try {
			Query query = entityManager.createQuery("from EtapeRole etapeRole");
			log.debug("findAll 'EtapeRole' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'EtapeRole' failed", re);
			return new ArrayList<EtapeRole>();
		}
	}

	@Override
	public  EtapeRole findByCodeEtapeByCodeRole(String codeEtape, String codeRole) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<EtapeRole> c = cb.createQuery(EtapeRole.class);
					Root<EtapeRole> er = c.from(EtapeRole.class);
					Join<EtapeRole, Etape> ere = er.join("etape");
					Join<EtapeRole, Role> err = er.join("role");
					
					c.select(er).distinct(true);
					List<Predicate> criteria = new ArrayList<Predicate>();
		
					if(codeEtape != null) {
						
							Predicate condition1 = cb.equal(ere.get("code"), codeEtape);
							criteria.add(condition1);
					}
			
					if(codeRole != null) {
							
							Predicate condition2 = cb.equal(err.get("code"), codeRole);
							criteria.add(condition2);
					}
					 
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<EtapeRole> q = entityManager.createQuery(c);
					
					return q.getResultList().get(0);
			
		}
		catch (RuntimeException re) {
		
				log.error("findByCodeEtapeByCodeAction 'SituationI18nDto' failed", re);
				throw re;
		}
	}
}
