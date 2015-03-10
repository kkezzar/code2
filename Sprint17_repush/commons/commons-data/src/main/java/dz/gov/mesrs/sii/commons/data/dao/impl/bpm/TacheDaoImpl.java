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

import dz.gov.mesrs.sii.commons.data.dao.bpm.TacheDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Tache;

/**
 * Dao object for domain model class Tache.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Tache
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("tacheDao")
public class TacheDaoImpl implements TacheDao {

	private static final Logger log = LoggerFactory.getLogger(TacheDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Tache)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Tache transientInstance) {
		log.debug("persisting Tache instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Tache)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Tache persistentInstance) {
		log.debug("removing Tache instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.TacheDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Tache)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tache merge(Tache detachedInstance) {
		log.debug("merging Tache instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NTacheDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Tache findById(int id) {
		log.debug("getting Tache instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Tache.class, id);
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
	public List<Tache> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Tache> findAll() {
		log.debug("getting all Tache instances");
		try {
			Query query = entityManager.createQuery("from Tache tache");
			log.debug("findAll 'Tache' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Tache' failed", re);
			return new ArrayList<Tache>();
		}
	}



	@Override
	public List<Tache> findCurrentTacheByEtapeId(int etapeId) {
		
		 log.debug("findCurrentTacheByEtapeId: ");
			

			try {
				StringBuilder request = new StringBuilder(
						"select r from Tache r where r.etapeRole.etape.id = ").append(etapeId).append(" and r.situationEntite.id = 20"); 
		
				Query query = entityManager.createQuery(request.toString());
				List<Tache> resultList = (List<Tache>) query
						.getResultList();
				log.debug("findByEtapeId successful");
				return resultList;

			} catch (RuntimeException re) {
				log.error("findByEtapeId failed", re);

				throw re;
			}
	}



	@Override
	public List<Tache> findFinishTacheByEntity(String entityCode,
			int entityInstanceId) {
		log.debug("findCurrentTacheByEtapeId: ");
		List<Tache> resultList = new ArrayList<>();
		
		try {
			StringBuilder request = new StringBuilder(
					"select r from Tache r " );
			if(entityCode!=null && entityCode.equals("OF"))	{	
				request.append(" where r.idOffreFormation = "); 
			}else if(entityCode!=null && entityCode.equals("DM")){
				request.append(" where r.idDemande = "); 
			}else if(entityCode!=null && entityCode.equals("DC")){
				request.append(" where r.idDecision = "); 
			}
			
	        if(!request.equals("select r from Tache r ")){
	        	request.append(entityInstanceId).append(" order by r.dateCloture desc");
			Query query = entityManager.createQuery(request.toString());
			 resultList = (List<Tache>) query
					.getResultList();
			}
			log.debug("findFinishTacheByEntity successful");
			
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFinishTacheByEntity failed", re);
			throw re;
		}
		
	}
}
