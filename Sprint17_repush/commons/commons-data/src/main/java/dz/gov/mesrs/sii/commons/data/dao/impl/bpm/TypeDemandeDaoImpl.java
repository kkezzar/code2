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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.TypeDemandeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemande;

/**
 * Dao object for domain model class TypeDemande.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.TypeDemande
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
@Service ("typeDemandeDao")
public class TypeDemandeDaoImpl implements TypeDemandeDao {

	private static final Logger log = LoggerFactory.getLogger(TypeDemandeDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDemandeDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDemande)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(TypeDemande transientInstance) {
		log.debug("persisting TypeDemande instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDemandeDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDemande)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(TypeDemande persistentInstance) {
		log.debug("removing TypeDemande instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TypeDemandeDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.TypeDemande)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TypeDemande merge(TypeDemande detachedInstance) {
		log.debug("merging TypeDemande instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NTypeDemandeDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public TypeDemande findById(int id) {
		log.debug("getting TypeDemande instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(TypeDemande.class, id);
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
	public List<TypeDemande> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<TypeDemande> findAll() {
		log.debug("getting all TypeDemande instances");
		try {
			Query query = entityManager.createQuery("from TypeDemande typeDemande");
			log.debug("findAll 'TypeDemande' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'TypeDemande' failed", re);
			return new ArrayList<TypeDemande>();
		}
	}

	@Override
	public  TypeDemande findByCode(String codeType) {
			
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<TypeDemande> c = cb.createQuery(TypeDemande.class);
					Root<TypeDemande> t  = c.from(TypeDemande.class);
					
					c.select(t).distinct(true);
					Predicate condition = cb.equal(t.get("code"), codeType);
					c.where(condition);
					
					TypedQuery<TypeDemande> q = entityManager.createQuery(c);
					return q.getResultList().get(0);
			
		}
		catch (RuntimeException re) {
		
					log.error("findByIdSituationEntite 'SituationI18nDto' failed", re);
					throw re;
		}
		
	}

}
