package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContextType;
//import javax.persistence.Query;
//
//
//
//
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import dz.gov.mesrs.sii.fve.business.model.bo.demande.Demande;
//import dz.gov.mesrs.sii.fve.business.persistence.demande.DemandeDao;
//
///**
// * Dao object for domain model class LmdDemande.
// * @see dz.gov.mesrs.sii.DemandeDto.business.model.bo.LmdDemande
// * @author MESRS CCM
// * @version 1.0
// * @created 07-01-2014 16:44:07
// */
// 
//
//
//
//@Repository ("demandeDao")  
//public class DemandeDaoImpl implements DemandeDao {
//
//	private static final Log log = LogFactory.getLog(DemandeDaoImpl.class);
//
//	@PersistenceContext (type = PersistenceContextType.EXTENDED)
//	private EntityManager entityManager;
//
//
//	/**
//	 * @see dz.gov.mesrs.sii.DemandeDao.business.persistence.jpa.impl.LmdDemandeDao#persist(dz.gov.mesrs.sii.DemandeDto.business.model.bo.LmdDemande)
//	 * 
//	 * @param transientInstance
//	 */
//	 
//	@Override 
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void persist(Demande transientInstance) {
//		log.debug("persisting LmdDemande instance");
//		try {
//			entityManager.persist(transientInstance);
//			log.debug("persist successful");
//		} catch (RuntimeException re) {
//			log.error("persist failed", re);
//			throw re;
//		}
//	}
//
//
//
//	/**
//	 * @see dz.gov.mesrs.sii.DemandeDao.business.persistence.jpa.impl.LmdDemandeDao#remove(dz.gov.mesrs.sii.DemandeDto.business.model.bo.LmdDemande)
//	 * @param persistentInstance
//	 */
//	 
//	@Override 
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public void remove(Demande persistentInstance) {
//		log.debug("removing LmdDemande instance");
//		try {
//			entityManager.remove(persistentInstance);
//			log.debug("remove successful");
//		} catch (RuntimeException re) {
//			log.error("remove failed", re);
//			throw re;
//		}
//	}
//
//
//
//	/**
//	 * @see dz.gov.mesrs.sii.DemandeDao.business.persistence.jpa.impl.LmdDemandeDao#merge(dz.gov.mesrs.sii.DemandeDto.business.model.bo.LmdDemande)
//	 * @param detachedInstance
//	 */
//	 
//	@Override 
//	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//	public Demande merge(Demande detachedInstance) {
//		log.debug("merging LmdDemande instance");
//		try {
//			log.debug("merge successful");
//			return entityManager.merge(detachedInstance);
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//
//
//
//	/**
//	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NLmdDemandeDao#findById(int)
//	 * @param id
//	 */
//	@Override 
//	@Transactional(readOnly = true)
//	public Demande findById(int id) {
//		log.debug("getting LmdDemande instance with id: " + id);
//		try {			
//			log.debug("get successful");
//			return entityManager.find(Demande.class, id);
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}
//	
//	
//	
//	/**
//	 * 
//	 * @param query
//	 */
//	
//	@SuppressWarnings("unchecked") 
//	@Override 
//	@Transactional(readOnly = true)
//	public List<Demande> findByQuery(String query) {
//		return entityManager.createQuery(query).getResultList();
//	}
//	
//	
//	
//	
//	@SuppressWarnings("unchecked") 
//	@Override 
//	@Transactional(readOnly = true)
//	public List<Demande> findAll() {
//		log.debug("getting all LmdDemande instances");
//		try {
//			Query query = entityManager.createQuery("from LmdDemande lmdDemande");
//			log.debug("findAll 'LmdDemande' successful");
//			return query.getResultList();
//		} catch (RuntimeException re) {
//			log.error("findAll 'LmdDemande' failed", re);
//			return new ArrayList<Demande>();
//		}
//	}
//}

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.DemandeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Demande;
import dz.gov.mesrs.sii.commons.data.util.ConstantHelper;



/**
 * Dao object for domain model class Demande.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.Demande
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("demandeDao")  
public class DemandeDaoImpl implements DemandeDao {

	private static final Logger log = LoggerFactory.getLogger(DemandeDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.Demande)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	public void persist(Demande transientInstance) {
		log.debug("persisting Demande instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.Demande)
	 * @param persistentInstance
	 */
	 
	@Override 
	public void remove(Demande persistentInstance) {
		log.debug("removing Demande instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.Demande)
	 * @param detachedInstance
	 */
	 
	@Override 
	public Demande merge(Demande detachedInstance) {
		log.debug("merging Demande instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDemandeDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Demande findById(int id) {
		log.debug("getting Demande instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Demande.class, id);
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
	public List<Demande> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Demande> findAll() {
		log.debug("getting all Demande instances");
		try {
			Query query = entityManager.createQuery("from Demande demande");
			log.debug("findAll 'Demande' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Demande' failed", re);
			return new ArrayList<Demande>();
		}
	}



	@Override
	public List<Demande> findAdvanced(Demande demande) {
		log.debug("findAdvanced: getting List of Demande instance : ");
		if (demande == null) {
			return null;
		}
		String firstOpr = ConstantHelper.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from Demande r left join r.offreFormation as offreFormation ");
			//request.append("");
			int id = demande.getId();
			if ((id != 0) ) {
				request.append(firstOpr + " r.id = "
						+ id);
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			String code = demande.getCode();
			if ((code != null) && (!code.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.code) like ")
						.append(ConstantHelper.LikeContain(code.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			Date dateDemande = demande.getDateDemande();

			if (dateDemande != null) {
				request.append(firstOpr)
						.append(" cast(r.dateDemande as text) like ")
						.append(ConstantHelper.LikeContain(dateDemande));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
			 if(demande.getTypeDemande()!=null){
			Integer typeDemande = demande.getTypeDemande().getId();
			if ((typeDemande != null)
					&& (typeDemande.intValue()!=0)) {
				request.append(firstOpr).append(" r.typeDemande.id = ")
						.append(typeDemande);
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
			 }
            if(demande.getOffreFormation()!=null){
			int idOffreFormation = demande.getOffreFormation().getId();
			if (idOffreFormation != 0){
				request.append(firstOpr).append(" offreFormation.id = ")
						.append(ConstantHelper.LikeContain(idOffreFormation));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
           }
            if(demande.getSituationEntite()!=null){
            Integer idSituation =  demande.getSituationEntite().getId();
            if ((idSituation != null)
					&& (idSituation.intValue()!=0)) {
				request.append(firstOpr).append(" r.situationEntite.id=")
						.append(idSituation);
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
            }
		
//			if(request.toString().equals("select r from Demande r left join r.offreFormation as offreFormation ")) {
//				return null;
//			}
			log.info("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<Demande> resultList = (List<Demande>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);

			throw re;
		}
		
	}
	
	
}
