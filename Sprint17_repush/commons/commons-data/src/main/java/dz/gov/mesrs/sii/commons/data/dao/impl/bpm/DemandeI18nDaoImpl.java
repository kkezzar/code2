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
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.bpm.DemandeI18nDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Demande;
import dz.gov.mesrs.sii.commons.data.model.bpm.DemandeI18n;
import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemande;
import dz.gov.mesrs.sii.commons.data.util.ConstantHelper;



/**
 * Dao object for domain model class DemandeI18n.
 * @see dz.gov.mesrs.sii.fve.business.model.dto.habilitation.DemandeI18nDto.business.model.bo.DemandeI18n
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("demandeI18nDao")  
public class DemandeI18nDaoImpl implements DemandeI18nDao {

	private static final Logger log = LoggerFactory.getLogger(DemandeI18nDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeI18nDao#persist(dz.gov.mesrs.sii.fve.business.model.dto.habilitation.DemandeI18nDto.business.model.bo.DemandeI18n)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	public void persist(DemandeI18n transientInstance) {
		log.debug("persisting DemandeI18n instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeI18nDao#remove(dz.gov.mesrs.sii.fve.business.model.dto.habilitation.DemandeI18nDto.business.model.bo.DemandeI18n)
	 * @param persistentInstance
	 */
	 
	@Override 
	public void remove(DemandeI18n persistentInstance) {
		log.debug("removing DemandeI18n instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.habilitation.lmd.business.persistence.jpa.impl.DemandeI18nDao#merge(dz.gov.mesrs.sii.fve.business.model.dto.habilitation.DemandeI18nDto.business.model.bo.DemandeI18n)
	 * @param detachedInstance
	 */
	 
	@Override 
	public DemandeI18n merge(DemandeI18n detachedInstance) {
		log.debug("merging DemandeI18n instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDemandeI18nDao#findById(int)
	 * @param id
	 */
	@Override 
	public DemandeI18n findById(int id) {
		log.debug("getting DemandeI18n instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(DemandeI18n.class, id);
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
	public List<DemandeI18n> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	public List<DemandeI18n> findAll() {
		log.debug("getting all DemandeI18n instances");
		try {
			Query query = entityManager.createQuery("from DemandeI18n demandeI18n");
			log.debug("findAll 'DemandeI18n' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DemandeI18n' failed", re);
			return new ArrayList<DemandeI18n>();
		}
	}



	@SuppressWarnings("unchecked") 
	@Override 
	public List<DemandeI18n> findListDemandeI18nByDemande(int idDemande) {
		log.debug("findListDemandeI18nByDemande: getting List of DemandeI18 instance : ");
		

		try {
			StringBuilder request = new StringBuilder(
					"select r from DemandeI18n r where r.demande.id = ").append(idDemande);
	
			Query query = entityManager.createQuery(request.toString());
			List<DemandeI18n> resultList = (List<DemandeI18n>) query
					.getResultList();
			log.debug("findListDemandeI18nByDemande successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findListDemandeI18nByDemande failed", re);

			throw re;
		}
	}



	@Override
	public List<DemandeI18n> findAdvanced(DemandeI18n demandeI18n) {
		log.debug("findAdvanced: getting List of DemandeI18n instance : ");
		if (demandeI18n == null) {
			return null;
		}
		String firstOpr = ConstantHelper.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from DemandeI18n r left join r.demande as demande ");
			//request.append("");
			int id = demandeI18n.getId();
			if ((id != 0) ) {
				request.append(firstOpr + " r.id = "
						+ id);
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			String langue = demandeI18n.getLangue();
			if ((langue != null) && (!langue.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.langue) =")
						.append(ConstantHelper.quotedString(langue.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			
			String objet = demandeI18n.getObjet();
			if ((objet != null) && (!objet.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.objet) like ")
						.append(ConstantHelper.LikeContain(objet.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
			
			String observations = demandeI18n.getObservation();
			if ((observations != null) && (!observations.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.observation) like ")
						.append(ConstantHelper.LikeContain(observations.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
			
            if(demandeI18n.getDemande()!=null){
			int idDemande = demandeI18n.getDemande().getId();
			if (idDemande != 0){
				request.append(firstOpr).append(" demande.id = ")
						.append(idDemande);
				firstOpr = ConstantHelper.AND_SQL_STR;
			}
           }
           
           
		
			if(request.toString().equals("select r from DemandeI18n r left join r.demande as demande ")) {
				return null;
			}
			log.info("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<DemandeI18n> resultList = (List<DemandeI18n>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);

			throw re;
		}
	}
	
	@Override
	public List<DemandeI18n> findListDemandsByTypeBySituaion(String codeTypeDemande, String codeSituation, Integer idDemande) {
		
			try {
				
						CriteriaBuilder cb = entityManager.getCriteriaBuilder();
						CriteriaQuery<DemandeI18n> c = cb.createQuery(DemandeI18n.class);
						Root<DemandeI18n> di18 = c.from(DemandeI18n.class);
						Join<DemandeI18n, Demande> dd = di18.join("demande");
						Join<Demande, TypeDemande> ddt = dd.join("typeDemande");
						Join<Demande, SituationEntite> dse = dd.join("situationEntite");
						Join<SituationEntite, Situation> ses = dse.join("situation");
						c.select(di18).distinct(true);
						
						List<Predicate> criteria = new ArrayList<Predicate>();
					
						if(codeTypeDemande != null) {
							Predicate condition1 = cb.equal(ddt.get("code"), codeTypeDemande);
							criteria.add(condition1);
						}
						
						if(codeSituation != null) {
							Predicate condition2 = cb.equal(ses.get("code"), codeSituation);
							criteria.add(condition2);
						}
						
						if(idDemande != null) {
							Predicate condition3 = cb.equal(dd.get("id"), idDemande);
							criteria.add(condition3);
						}
					
						 c.where(cb.and(criteria.toArray(new Predicate[0])));
						TypedQuery<DemandeI18n> q = entityManager.createQuery(c);
						return q.getResultList();
		}
		catch (RuntimeException re) {
				
						log.error("findListDemandsByTypeBySituaion 'DemandeI18n' failed", re);
						throw re;
						
			}
	}
}
