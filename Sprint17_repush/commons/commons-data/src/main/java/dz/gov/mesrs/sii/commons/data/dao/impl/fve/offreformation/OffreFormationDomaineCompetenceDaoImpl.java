package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDomaineCompetenceDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDomaineCompetence;

/**
 * Dao object for domain model class OffreFormationDomaineCompetence.
 * @see dz.gov.mesrs.sii.fve.business.model.bo.OffreFormationDomaineCompetence
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("offreFormationDomaineCompetenceDao")  @Transactional
public class OffreFormationDomaineCompetenceDaoImpl implements OffreFormationDomaineCompetenceDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationDomaineCompetenceDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OffreFormationDomaineCompetenceDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.OffreFormationDomaineCompetence)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(OffreFormationDomaineCompetence transientInstance) {
		log.debug("persisting OffreFormationDomaineCompetence instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OffreFormationDomaineCompetenceDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.OffreFormationDomaineCompetence)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(OffreFormationDomaineCompetence persistentInstance) {
		log.debug("removing OffreFormationDomaineCompetence instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.OffreFormationDomaineCompetenceDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.OffreFormationDomaineCompetence)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public OffreFormationDomaineCompetence merge(OffreFormationDomaineCompetence detachedInstance) {
		log.debug("merging OffreFormationDomaineCompetence instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NOffreFormationDomaineCompetenceDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public OffreFormationDomaineCompetence findById(int id) {
		log.debug("getting OffreFormationDomaineCompetence instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(OffreFormationDomaineCompetence.class, id);
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
	public List<OffreFormationDomaineCompetence> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<OffreFormationDomaineCompetence> findAll() {
		log.debug("getting all OffreFormationDomaineCompetence instances");
		try {
			Query query = entityManager.createQuery("from OffreFormationDomaineCompetence offreFormationDomaineCompetence");
			log.debug("findAll 'OffreFormationDomaineCompetence' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'OffreFormationDomaineCompetence' failed", re);
			return new ArrayList<OffreFormationDomaineCompetence>();
		}
	}
}
