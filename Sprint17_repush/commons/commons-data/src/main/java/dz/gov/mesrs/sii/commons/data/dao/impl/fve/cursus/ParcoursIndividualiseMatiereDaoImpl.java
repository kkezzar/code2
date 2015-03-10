package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ParcoursIndividualiseMatiereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualiseMatiere;

/**
 * Dao object for domain model class ParcoursIndividualiseMatiere;
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.ParcoursIndividualiseMatiere;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 



@Repository ("parcoursIndividualiseMatiereDao")  @Transactional
public class ParcoursIndividualiseMatiereDaoImpl implements ParcoursIndividualiseMatiereDao {

	private static final Logger log = LoggerFactory.getLogger(ParcoursIndividualiseMatiereDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseMatiereDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.ParcoursIndividualiseMatiere)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ParcoursIndividualiseMatiere transientInstance) {
		log.debug("persisting ParcoursIndividualiseMatiere instance");
		try {
			entityManager.persist(transientInstance);
			/*entityManager.flush();
			entityManager.refresh(transientInstance);*/ 
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseMatiereDao#remove(dz.gov.mesrs.sii.ParcoursIndividualiseMatiereDto.business.model.bo.cursus.ParcoursIndividualiseMatiere)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ParcoursIndividualiseMatiere persistentInstance) {
		log.debug("removing ParcoursIndividualiseMatiere instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseMatiereDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.ParcoursIndividualiseMatiere)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ParcoursIndividualiseMatiere merge(ParcoursIndividualiseMatiere detachedInstance) {
		log.debug("merging ParcoursIndividualiseMatiere instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseMatiereDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public ParcoursIndividualiseMatiere findById(int id) {
		log.debug("getting ParcoursIndividualiseMatiere instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ParcoursIndividualiseMatiere.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<ParcoursIndividualiseMatiere> findAll() {
		log.debug("getting all ParcoursIndividualiseMatiere instances");
		try {
			Query query = entityManager.createQuery("from ParcoursIndividualiseMatiere parcoursIndividualiseMatiere");
			log.debug("findAll 'ParcoursIndividualiseMatiere' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ParcoursIndividualiseMatiere' failed", re);
			return new ArrayList<ParcoursIndividualiseMatiere>();
		}
	}



	@Override
	public List<ParcoursIndividualiseMatiere> findByIdParcours(
			int idParcoursIndividualise) {
		log.debug("getting all ParcoursIndividualiseMatiere instances");
		try {
			if(idParcoursIndividualise == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from ParcoursIndividualiseMatiere r ");
			sqlQuery.append("where r.parcoursIndividualise.id = :idParcoursIndividualise ");
			
			TypedQuery<ParcoursIndividualiseMatiere> query = entityManager.createQuery(
					new String(sqlQuery), ParcoursIndividualiseMatiere.class);
			query.setParameter("idParcoursIndividualise", idParcoursIndividualise);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByRefIndividu failed", re);
			throw re;
		}
	}
}
