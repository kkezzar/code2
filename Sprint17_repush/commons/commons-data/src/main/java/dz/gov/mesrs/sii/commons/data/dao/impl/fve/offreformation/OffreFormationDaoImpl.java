package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Situation;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationI18n;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.util.ConstantHelper;

@Service ("offreFormationDao")
public class OffreFormationDaoImpl implements OffreFormationDao {

	private static final Logger log = LoggerFactory.getLogger(OffreFormationDaoImpl.class.getName());
	@PersistenceContext 
	private EntityManager entityManager;
	
	/**
	 * [OffreFormationDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:12
	 * @param transientInstance
	 */
	@Override 
	public void persist(OffreFormation transientInstance) {
		
		log.debug("persisting OffreFormation instance");
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
	 * [OffreFormationDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:18
	 * @param persistentInstance
	 */
	@Override 
	public void remove(OffreFormation persistentInstance) {
		
			log.debug("removing OffreFormation instance");
			try {
						entityManager.remove(persistentInstance);
						log.debug("remove successful");
			
			} catch (RuntimeException re) {
			
						log.error("remove failed", re);
						throw re;
			}
	}
 
	/**
	 * [OffreFormationDaoImpl.merge] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:24
	 * @param detachedInstance
	 * @return
	 */
	@Override 
	public OffreFormation merge(OffreFormation detachedInstance) {
				
				log.debug("merging OffreFormation instance");
				try {
						log.debug("merge successful");
						return entityManager.merge(detachedInstance);
					
				} catch (RuntimeException re) {
				
						log.error("merge failed", re);
						throw re;
				}
	}

	/**
	 * [OffreFormationDaoImpl.findById] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:31
	 * @param id
	 * @return
	 */
	@Override 
	public OffreFormation findById(int id) {
		
			log.debug("getting OffreFormation instance with id: " + id);
			try {			
					log.debug("findById successful");
					return entityManager.find(OffreFormation.class, id);
					
			} catch (RuntimeException re) {
						
						log.error("findById failed", re);
						throw re;
			}
	}

	/**
	 * [OffreFormationDaoImpl.findByCode] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:36
	 * @param codeNat
	 * @return
	 */
	@Override 
	public List<OffreFormation> findByCode(String codeNat) {
		
			log.debug("getting OffreFormation List with code : " + codeNat);
			try {			
					log.debug("findByCode successful");
					StringBuilder sqlQuery = new StringBuilder();
					sqlQuery.append("SELECT o from OffreFormation o ");
					sqlQuery.append(" WHERE o.code = :code ");
					TypedQuery<OffreFormation>  query = entityManager.createQuery(new String(sqlQuery), OffreFormation.class);
					query.setParameter("code", codeNat);
					return query.getResultList();
			
			} catch (RuntimeException re) {
					
					log.error("findByCode failed", re);
					throw re;
			}
	}
	
	/**
	 * [OffreFormationDaoImpl.findByQuery] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:42
	 * @param query
	 * @return
	 */
	@Override 
	public List<OffreFormation> findGeneric(Map<String, String> attributes,  Integer idEtab) {
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<OffreFormation> c = cb.createQuery(OffreFormation.class);
				Root<OffreFormation> off = c.from(OffreFormation.class);
				Join<OffreFormation,RefEtablissement> offEtab = off.join("refEtablissement");
				c.select(off);
				c.distinct(true);
				c.orderBy(cb.asc(off.get("libelleLongFr")));
				List<Predicate> criteria = new ArrayList<Predicate>();
				for(String s : attributes.keySet())
				{
						if(off.get(s) != null){
							criteria.add(cb.like(cb.lower(off.<String>get(s)), "%" + attributes.get(s).toLowerCase() + "%" ));
						}
				}
				Predicate where = cb.conjunction();
				Predicate conditionEtab = null;
				if(idEtab != null) {
					conditionEtab = cb.equal(offEtab.get("id"), idEtab);
				}
				if(conditionEtab != null)
					where = cb.and(conditionEtab);
				if(criteria != null && criteria.size()>0) {
					where = cb.and(where, cb.or(criteria.toArray(new Predicate[0])));
				}
				c.where(where);
				TypedQuery<OffreFormation> q = entityManager.createQuery(c);
				List<OffreFormation> result = q.getResultList();
				for(OffreFormation entity: result){
			        if(entityManager.contains(entity)){
			        	entityManager.refresh(entity);
			        }
			    }
				return result;
		}
		catch (RuntimeException re) {
			log.error("findGeneric 'OffreFormation' failed", re);
			throw re;
		}
		
	}
	
	/**
	 * [OffreFormationDaoImpl.findAdvanced] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 2 mai 2014  20:23:26
	 * @param attributes
	 * @param attributesI18n
	 * @param attributesDto
	 * @return
	 */
	public  List<OffreFormation> findAdvanced(Map<String, String> attributes, Map<String, Object> attributesDto,Integer idEtab) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<OffreFormation> c = cb.createQuery(OffreFormation.class);
					Root<OffreFormation> off = c.from(OffreFormation.class);
					Join<OffreFormation,RefEtablissement> offEtab = off.join("refEtablissement");
					c.select(off);
					c.distinct(true);
					c.orderBy(cb.asc(off.get("libelleLongFr")));

					List<Predicate> criteria = new ArrayList<Predicate>();
					// Criteres sur l'objet parent OffreFormation
					for(String s : attributes.keySet())
					{
							if(off.get(s) != null){
								criteria.add(cb.like(cb.lower(off.<String>get(s)), "%" + attributes.get(s).toString().toLowerCase() + "%" ));
							}
					}
					Predicate conditionEtab = null;
					if(idEtab != null) {
						conditionEtab = cb.equal(offEtab.get("id"), idEtab);
					}
					List<Predicate> criteriaAdv = new ArrayList<Predicate>();
					if(attributesDto.get("idDomaine")!= null) {
						Join<OffreFormation,RefDomaineLMD> offDom = off.join("refDomaineLMD");
						criteriaAdv.add(cb.equal(offDom.get("id"), Integer.parseInt(attributesDto.get("idDomaine").toString())));
					}
					if(attributesDto.get("idFiliere")!= null) {
						Join<OffreFormation,RefFiliereLmd> offFil = off.join("refFiliereLmd");
						criteriaAdv.add(cb.equal(offFil.get("id"), Integer.parseInt(attributesDto.get("idFiliere").toString())));
					}
					if(attributesDto.get("idSpecialite")!= null) {
						Join<OffreFormation,RefSpecialiteLmd> offSpec = off.join("refSpecialiteLmd");
						criteriaAdv.add(cb.equal(offSpec.get("id"), Integer.parseInt(attributesDto.get("idSpecialite").toString())));
					}
				
					Predicate where = cb.conjunction();
					if(conditionEtab != null)
						where = cb.and(conditionEtab);
					if(criteriaAdv != null && criteriaAdv.size()>0) {
						where = cb.and(where, cb.and(criteriaAdv.toArray(new Predicate[0])));
					}
					if(criteria != null && criteria.size()>0)
						where = cb.and(where,cb.or(criteria.toArray(new Predicate[0])));
					c.where(where);
					TypedQuery<OffreFormation> q = entityManager.createQuery(c);
					List<OffreFormation> result = q.getResultList();
					for(OffreFormation entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return q.getResultList();
			}
			catch (RuntimeException re) {
		
					log.error("findAdvanced 'OffreFormation' failed", re);
					throw re;
			}

	}
	
	/**
	 * [OffreFormationDaoImpl.findAll] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 5 avr. 2014  22:12:57
	 * @return
	 */
	@Override 
	public List<OffreFormation> findAll() {
		
		log.debug("getting all OffreFormation instances");
		
		try {
				
				TypedQuery<OffreFormation> query = entityManager.createQuery("SELECT of FROM OffreFormation of", OffreFormation.class);
				log.debug("findAll 'OffreFormation' successful");
				return query.getResultList();
				
		} catch (RuntimeException re) {
				
				log.error("findAll 'OffreFormation' failed", re);
				return new ArrayList<OffreFormation>();	
		}
	}
	
	@Override
	public List<OffreFormation> findOffreToHabilitation(
			OffreFormation offreFormation) {
		log.debug("findOffreToHabilitation: getting List of OffreFormationDto instance : ");
		if (offreFormation
				== null) {
			return null;
		}
		String firstOpr = ConstantHelper.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from OffreFormation r  where r.estValidee = ");
			request.append(ConstantHelper.quotedString("true"));
			/*String codeDomaine = offreFormation.getRefCodeDomaine();
			if ((codeDomaine != null) && (!codeDomaine.trim().isEmpty())) {
				request.append(firstOpr + " lower(r.refCodeDomaine) = ")
						.append(ConstantHelper.quotedString(codeDomaine.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			String codeFiliere = offreFormation.getRefCodeFiliere();
			if ((codeFiliere != null) && (!codeFiliere.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.refCodeFiliere) = ")
						.append(ConstantHelper.quotedString(codeFiliere.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}

			String codeSpecialite = offreFormation.getRefCodeSpecialite();
			if ((codeSpecialite != null) && (!codeSpecialite.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.codeSpecialite) = ")
						.append(ConstantHelper.quotedString(codeSpecialite.toLowerCase()));
				firstOpr = ConstantHelper.AND_SQL_STR;
			}*/

			//Ajouter les conditions pour recuperer suelement que les offres à Habiliter

			log.info("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			query.setFirstResult(0);
			List<OffreFormation> resultList = (List<OffreFormation>) query
					.getResultList();
			log.debug("findOffreToHabilitation successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findOffreToHabilitation failed", re);

			throw re;
		}
	}

	
	public  List<OffreFormation> findByCodeSituation(String codeSituation) {
		
			try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<OffreFormation> c = cb.createQuery(OffreFormation.class);
					Root<OffreFormation> off = c.from(OffreFormation.class);
					Join<OffreFormation, SituationEntite> ose = off.join("situationEntite");
					Join<SituationEntite, Situation> ses = ose.join("situation");
					c.select(off).distinct(true);
					Predicate condition = cb.equal(ses.get("code"), codeSituation);
					c.where(condition);
					TypedQuery<OffreFormation> q = entityManager.createQuery(c);
					return q.getResultList();
			}
			catch (RuntimeException re) {
		
					log.error("findByCodeSituation 'OffreFormation' failed", re);
					throw re;
			}
	}
	
	@Override
	public  int getLastOfIndex(Integer idEtab)  {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<Long> c = cb.createQuery(Long.class);
					Root<OffreFormation> off = c.from(OffreFormation.class);
					Join<OffreFormation,RefEtablissement> offEtab = off.join("refEtablissement");
					c.select(cb.countDistinct(off.get("id")));
					Predicate where = cb.conjunction();
					Predicate criteriaEtab = null;
					if(idEtab != null) {
						criteriaEtab = cb.equal(offEtab.get("id"), idEtab);
					}
					if(criteriaEtab != null)
						where = cb.and(criteriaEtab);
					
					c.where(where);
					Long size = entityManager.createQuery(c).getSingleResult();
					return size.intValue();
	}
	catch (RuntimeException re) {

					log.error("getLastOfIndex 'OffreFormation' failed", re);
					throw re;
	}

	}

	@Override
	public void flushAndClear() {
		
		try {
			entityManager.flush();
			entityManager.clear();
		}
		catch (RuntimeException e) {
			log.error("flushAndClear failed", e);
			throw e;
		}
	}

	@Override
	public  OffreFormationI18n findI18nByOfId(int ofId , String langue) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<OffreFormationI18n> c = cb.createQuery(OffreFormationI18n.class);
					Root<OffreFormationI18n> i18n = c.from(OffreFormationI18n.class);
					Join<OffreFormationI18n, OffreFormation> i18nOff = i18n.join("offreFormation");
					c.select(i18n).distinct(true);
									
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(i18nOff.get("id"), ofId);
					criteria.add(condition1);
					Predicate condition2 = cb.equal(i18n.get("langue"), langue);
					criteria.add(condition2);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<OffreFormationI18n> q = entityManager.createQuery(c);
					List<OffreFormationI18n> result = q.getResultList();
					if(result != null && result.size()==1)
						return result.get(0);
					return null;
			
		}
		catch (RuntimeException re) {

				log.error("findByOfId 'OffreFormationI18n' failed", re);
				throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDao#findByIdEtablissement()
	 */
	@Override
	public List<OffreFormation> findByIdEtablissement(int idEtab) {
		log.debug("getting OffreFormation List with code : " + idEtab);
		try {			
			
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT o from OffreFormation o ");
				sqlQuery.append(" WHERE o.refEtablissement.id =  " + idEtab);
				TypedQuery<OffreFormation>  query = entityManager.createQuery(new String(sqlQuery), OffreFormation.class);
				log.debug("findByIdEtablissement successful");
				return query.getResultList();
		
		} catch (RuntimeException re) {
				
				log.error("findByIdEtablissement failed", re);
				throw re;
		}
	}

}
