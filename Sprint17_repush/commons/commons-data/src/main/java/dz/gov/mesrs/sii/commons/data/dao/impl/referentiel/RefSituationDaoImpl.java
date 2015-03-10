package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSituationDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEvenement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSituation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
@Repository("refSituationDaoImpl")
public class RefSituationDaoImpl implements RefSituationDao {

	private static final Logger log = LoggerFactory.getLogger(RefSituationDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefSituationDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefSituation)
	 */
	@Override
	public void persist(RefSituation transientInstance) {
		log.debug("persisting RefSituation instance");
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
	 * [RefSituationDaoImpl.entityManager] Getter 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014  15:57:20
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * [RefSituationDaoImpl.entityManager] Setter 
	 * @author BELBRIK Oualid on : 3 f�vr. 2014  15:57:20
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefSituationDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefSituation)
	 */
	@Override
	public void remove(RefSituation persistentInstance) {
		log.debug("removing RefSituation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefSituationDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefSituation)
	 */
	@Override
	public RefSituation merge(RefSituation detachedInstance) {
		log.debug("merging RefSituation instance");
		try {
			RefSituation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefSituationDao#findById(int)
	 */
	@Override
	public RefSituation findById(String id) {
		log.debug("getting RefSituation instance with id: " + id);
		try {
			RefSituation instance = entityManager.find(RefSituation.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public RefSituation findCurrentSituation(Integer entityId, String entityName) {
		log.info("getting situation instance with id = : " + entityId + "and EntityName = "+entityName);
		RefSituation refSituation = null;
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :entityId and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("entityId", entityId).setParameter("EntityName", entityName);
			List<RefSituation> refSituationList = (List<RefSituation>)query.getResultList();
			if ((refSituationList != null) && (refSituationList.size() > 0)) {
			   log.info("findCurrentSituation successful list size = "+ refSituationList.size());
			   refSituation = refSituationList.get(0);
			   return refSituation;
			}
			
			
		} catch (RuntimeException re) {
			log.error("findCurrentSituation failed", re);
			throw re;
		}
		return refSituation;
	}

	


	@Override
	public List<RefSituation> findAll() {
		log.debug("getting List of RefSituation(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefSituation r ");
			request.append("order by r.llSituationLatin ");
			Query query = entityManager.createQuery(new String(request));
			List<RefSituation> resultList = (List<RefSituation>) query
					.getResultList();
			log.debug("RefSituation findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefStructure findAll failed", re);
			throw re;
		}

		
	}

	@Override
	public List<RefSituation> findSituationsForIndividu(Integer idEntity) {
		log.info("getting findSituationsForIndividu = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefIndividu.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForIndividu successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForIndividu failed", re);
			throw re;
		}
		
	}

	@Override
	public List<RefSituation> findSituationsForStructure(Integer idEntity) {
		log.info("getting findSituationsForStructure = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefStructure.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForStructure successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForStructure failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForGroupe(Integer idEntity) {
		log.info("getting findSituationsForGroupe = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefGroupe.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForGroupe successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForGroupe failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForContrat(Integer idEntity) {
		log.info("getting findSituationsForContrat = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefContrat.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForContrat successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForContrat failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForCompte(Integer idEntity) {
		log.info("getting findSituationsForCompte = : " + idEntity );
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefCompte.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForCompte successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForCompte failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForLieu(Integer idEntity) {
		log.info("getting findSituationsForLieu = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefLieu.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForLieu successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForLieu failed", re);
			throw re;
		}
	}
	
	@Override
	public List<RefSituation> findSituationsForEvenement(Integer idEntity) {
		log.info("getting findSituationsForCompte = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefEvenement.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForCompte successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForCompte failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForEquipement(Integer idEntity) {
		log.info("getting findSituationsForEquipement = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefEquipement.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForEquipement successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForEquipement failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForReservation(Integer idEntity) {
		log.info("getting findSituationsForReservation = : " + idEntity );
		
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefReservation.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForReservation successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForReservation failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForEtablissement(Integer idEntity) {
		log.info("getting findSituationsForEtablissement = : " + idEntity );
		try {
			Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
			query.setParameter("idEntity", idEntity).setParameter("EntityName", RefEtablissement.class.getSimpleName());
			List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
			log.debug("findSituationsForEtablissement successful");
			   return resultList;	
			
		} catch (RuntimeException re) {
			log.error("findSituationsForEtablissement failed", re);
			throw re;
		}
	}

	@Override
	public List<RefSituation> findSituationsForDomaineLMD(Integer idEntity) {
		
			log.info("getting findSituationsForDomaineLMD = : " + idEntity );
			try {
				Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
				query.setParameter("idEntity", idEntity).setParameter("EntityName", RefDomaineLMD.class.getSimpleName());
				List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
				log.debug("findSituationsForDomaineLMD successful");
				   return resultList;	
				
			} catch (RuntimeException re) {
				log.error("findSituationsForDomaineLMD failed", re);
				throw re;
			}
	}
	
	@Override
	public List<RefSituation> findSituationsForFiliereLmd(Integer idEntity) {
		
			log.info("getting findSituationsForFiliereLmd = : " + idEntity );
			try {
				Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
				query.setParameter("idEntity", idEntity).setParameter("EntityName", RefFiliereLmd.class.getSimpleName());
				List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
				log.debug("findSituationsForFiliereLmd successful");
				   return resultList;	
				
			} catch (RuntimeException re) {
				log.error("findSituationsForFiliereLmd failed", re);
				throw re;
			}
	}
	
	@Override
	public List<RefSituation> findSituationsForSpecialiteLmd(Integer idEntity) {
		
			log.info("getting findSituationsForSpecialiteLmd = : " + idEntity );
			try {
				Query query = entityManager.createQuery("select r from RefSituation r WHERE r.idEntite = :idEntity and r.nomEntite = :EntityName order by r.dateSituation desc");
				query.setParameter("idEntity", idEntity).setParameter("EntityName", RefSpecialiteLmd.class.getSimpleName());
				List<RefSituation> resultList = (List<RefSituation>)query.getResultList();
				log.debug("findSituationsForSpecialiteLmd successful");
				   return resultList;	
				
			} catch (RuntimeException re) {
				log.error("findSituationsForSpecialiteLmd failed", re);
				throw re;
			}
	}
	
}
