/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.OffreFormationDetailDaoImpl.java] 
 * @author rlaib on : 6 f�vr. 2014  13:06:20
 */
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDetailDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;

/**
 * @author rlaib  on : 6 f�vr. 2014  13:06:20
 */
@Repository ("offreFormationDetailDao")  @Transactional
public class OffreFormationDetailDaoImpl implements OffreFormationDetailDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationDetailDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * [OffreFormationDetailDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:15:34
	 * @param transientInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(OffreFormationDetail transientInstance) {
		log.debug("persisting OffreFormationDetail  instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationDetailDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:15:49
	 * @param persistentInstance
	 */
	@Override 
	public void remove(OffreFormationDetail persistentInstance) {
		log.debug("removing OffreFormationDetail instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationDetailDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:16:01
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormationDetail merge(OffreFormationDetail detachedInstance) {
		log.debug("merging OffreFormationDetail instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [OffreFormationDetailDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:16:19
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormationDetail findById(int id) {
		log.debug("getting OffreFormationDetail instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(OffreFormationDetail.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
		
	/**
	 * [OffreFormationDetailDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:16:31
	 * @return
	 */
	@SuppressWarnings("unchecked") 
	@Override 
	public List<OffreFormationDetail> findAll() {
		log.debug("getting all OffreFormationDetail instances");
		try {
			Query query = entityManager.createQuery("from  OffreFormationDetail offreFormationDetail ORDER BY offreFormationDetail.libelleFr asc");
			log.debug("findAll 'OffreFormation' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'OffreFormationDetail' failed", re);
			return new ArrayList<OffreFormationDetail>();
		}
	}
	
	
}
