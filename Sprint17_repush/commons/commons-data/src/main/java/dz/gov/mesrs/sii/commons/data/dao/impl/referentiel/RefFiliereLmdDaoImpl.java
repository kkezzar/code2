package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFiliereLmdDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefFiliereLmd.
 * @see dz.gov.mesrs.sii.RefFiliereLmdDto.business.model.bo.RefFiliereLmd
 * @author MESRS CCM
 * @version 1.0
 * @created 14-04-2014 10:44:07
 */
 

@Repository ("refFiliereLmdDaoImpl")
public class RefFiliereLmdDaoImpl implements RefFiliereLmdDao {

	private static final Logger log = LoggerFactory.getLogger(RefFiliereLmdDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.RefFiliereLmdService.business.persistence.jpa.impl.RefFiliereLmdDao#persist(dz.gov.mesrs.sii.RefFiliereLmdDto.business.model.bo.RefFiliereLmd)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefFiliereLmd transientInstance) {
		log.debug("persisting RefFiliereLmd instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.RefFiliereLmdService.business.persistence.jpa.impl.RefFiliereLmdDao#remove(dz.gov.mesrs.sii.RefFiliereLmdDto.business.model.bo.RefFiliereLmd)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefFiliereLmd persistentInstance) {
		log.debug("removing RefFiliereLmd instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.RefFiliereLmdService.business.persistence.jpa.impl.RefFiliereLmdDao#merge(dz.gov.mesrs.sii.RefFiliereLmdDto.business.model.bo.RefFiliereLmd)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefFiliereLmd merge(RefFiliereLmd detachedInstance) {
		log.debug("merging RefFiliereLmd instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefFiliereLmdDao#findById(int)
	 * @param id
	 */
	@Override 
	public RefFiliereLmd findById(int id) {
		log.debug("getting RefFiliereLmd instance with id: " + id);
		try {			
			log.debug("get successful");
			RefFiliereLmd instance = entityManager.find(RefFiliereLmd.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override 
	public RefFiliereLmd findByIdentifiant(String identifiant) {
		try {			
			
			Query _query = entityManager.createQuery("from RefFiliereLmd filiere where lower(filiere.identifiant) = :identifiant");
			_query.setParameter("identifiant", identifiant.trim().toLowerCase());
			List<RefFiliereLmd> _resultList = (List<RefFiliereLmd>) _query.getResultList();
			
			log.debug("findByIdentifiant successful");
			
			if (_resultList.size() == 1)
				return _resultList.get(0);
			else
				return null;
			
		} catch (RuntimeException re) {
			log.error("findByIdentifiant failed", re);
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
	public List<RefFiliereLmd> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	@Override 
	@Transactional(readOnly = true)
	public List<RefFiliereLmd> findAll() {
		log.debug("getting all RefFiliereLmd instances");
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<RefFiliereLmd> c = cb.createQuery(RefFiliereLmd.class);
					Root<RefFiliereLmd> fil = c.from(RefFiliereLmd.class);
					c.select(fil).distinct(true);
					c.orderBy(cb.asc(fil.get("llFiliere")));
					TypedQuery<RefFiliereLmd> q = entityManager.createQuery(c);
					
					log.debug("findAll 'RefFiliereLmd' successful");
					return q.getResultList();
		}
		catch (RuntimeException re) {
					log.error("findAll 'RefFiliereLmd' failed", re);
					throw re;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RefFiliereLmd> findGeneric(String value) {
	
	log.debug("getting List of RefFiliereLmd instance with : " + value);
	List<RefFiliereLmd> resultList = new ArrayList<RefFiliereLmd>();
	try {

		StringBuilder request = new StringBuilder("select r from RefFiliereLmd r ");
		                               request.append("left join r.refDomaineLMD ");
				

		if (value != null && !value.trim().isEmpty()) {
			value = "%" + value.trim() + "%";
			value = value.toUpperCase();

			request.append("WHERE upper(r.identifiant) like :value ");
			request.append("OR upper(r.lcFiliere) like :value ");
			request.append("OR upper(r.llFiliere) like :value ");
			request.append("OR upper(r.llFiliereArabe) like :value ");
			request.append("OR upper(r.descriptionFiliere) like :value ");
			request.append("OR upper(r.descriptionFiliereArabe) like :value ");
			request.append("OR upper(r.descriptionFiliereArabe) like :value ");
			request.append("OR upper(r.descriptionFiliereArabe) like :value ");
			request.append(" or upper(r.refDomaineLMD.identifiant) like :value ");
			
			Query query = entityManager.createQuery(request.toString());
			query.setParameter("value", value);
			resultList = (List<RefFiliereLmd>) query.getResultList();
		}

		

		log.debug("findGeneric successful");

	} catch (RuntimeException re) {
		log.error("findGeneric failed", re);
		throw re;
	}
	return resultList;
}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<RefFiliereLmd> findAdvanced(RefFiliereLmd refFiliereLmd) {
		log.debug("findAdvanced: getting List of refFiliereLmd instance : ");
		if (refFiliereLmd == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;
	
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefFiliereLmd r ");
			String identifiant = refFiliereLmd.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like " + UtilConstant.LikeContain(identifiant));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LcFiliere = refFiliereLmd.getLcFiliere();
			if ((LcFiliere != null) && (!LcFiliere.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.lcFiliere like ").append(UtilConstant.LikeContain(LcFiliere));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			String LlFiliere = refFiliereLmd.getLlFiliere();
			if ((LlFiliere != null) && (!LlFiliere.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.llFiliere like ").append(UtilConstant.LikeContain(LlFiliere));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			String LcFiliereArabe = refFiliereLmd.getLcFiliereArabe();
			if ((LcFiliereArabe != null) && (!LcFiliereArabe.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.lcFiliereArabe like ").append(UtilConstant.LikeContain(LcFiliereArabe));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			String LlFiliereArabe = refFiliereLmd.getLlFiliereArabe();
			if ((LlFiliereArabe != null) && (!LlFiliereArabe.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.llFiliereArabe like ").append(UtilConstant.LikeContain(LlFiliereArabe));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			if (refFiliereLmd.getRefDomaineLMD() != null) {
				String identifiantDomaine = refFiliereLmd.getRefDomaineLMD().getIdentifiant();
				request.append(firstOpr).append( " r.refDomaineLMD.identifiant = " ).append(UtilConstant.quotedString(identifiantDomaine));
				
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefFiliereLmd> resultList = (List<RefFiliereLmd>) query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefFiliereLmd> findByCodeDomainelmd(String codeDomaine) {	
		log.debug("getting List of RefFiliereLmd instance with : " + codeDomaine);
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefFiliereLmd> c = cb.createQuery(RefFiliereLmd.class);
				Root<RefFiliereLmd> fil = c.from(RefFiliereLmd.class);
				Join<RefFiliereLmd, RefDomaineLMD> filDom = fil.join("refDomaineLMD");
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(filDom.get("identifiant"),codeDomaine);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(fil).distinct(true);
				c.orderBy(cb.asc(fil.get("llFiliere")));
				
				TypedQuery<RefFiliereLmd> q = entityManager.createQuery(c);
				log.debug("findByCodeDomainelmd 'RefFiliereLmd' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findByCodeDomainelmd 'RefFiliereLmd' failed", re);
				throw re;
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefFiliereLmd> findByIdDomainelmd(Integer idDomaine) {
		log.debug("getting List of findByIdDomainelmd instance with : " + idDomaine);
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefFiliereLmd> c = cb.createQuery(RefFiliereLmd.class);
				Root<RefFiliereLmd> fil = c.from(RefFiliereLmd.class);
				Join<RefFiliereLmd, RefDomaineLMD> filDom = fil.join("refDomaineLMD");
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(filDom.get("id"),idDomaine);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(fil).distinct(true);
				c.orderBy(cb.asc(fil.get("llFiliere")));
				TypedQuery<RefFiliereLmd> q = entityManager.createQuery(c);
				log.debug("findByIdDomainelmd 'RefFiliereLmd' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findByIdDomainelmd 'RefFiliereLmd' failed", re);
				throw re;
		}
	}

	@Override
	public List<RefAffectFiliereLmdEtab> findFilieresLMDByEtab(int idEtab,  String codeDomaine) {
		

		log.debug("getting List of RefFiliereLMD instance with : " + idEtab);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefAffectFiliereLmdEtab> c = cb.createQuery(RefAffectFiliereLmdEtab.class);
				Root<RefAffectFiliereLmdEtab> aff = c.from(RefAffectFiliereLmdEtab.class);
				Join<RefAffectFiliereLmdEtab, RefEtablissement> affEtab = aff.join("refEtablissement");
				Join<RefAffectFiliereLmdEtab, RefFiliereLmd> affFil = aff.join("refFiliereLmd");
				Join<RefFiliereLmd, RefDomaineLMD> filDom = affFil.join("refDomaineLMD");
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(affEtab.get("id"),idEtab);
				criteria.add(condition1);
				Predicate condition2 = cb.equal(filDom.get("identifiant"),codeDomaine);
				criteria.add(condition2);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(aff).distinct(true);
				//c.orderBy(cb.asc(aff.get("id")));
				
				TypedQuery<RefAffectFiliereLmdEtab> q = entityManager.createQuery(c);
				log.debug("findFilieresLMDByEtab 'RefAffectFiliereLmdEtab' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findFilieresLMDByEtab 'RefAffectFiliereLmdEtab' failed", re);
				throw re;
		}
	}
	
	
	@Override
	public List<RefAffectFiliereLmdEtab> findFilieresLMDByCodeEtab(String codeEtab) {
		

		log.debug("getting List of RefDomaineLMD instance with : " + codeEtab);

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefAffectFiliereLmdEtab> c = cb.createQuery(RefAffectFiliereLmdEtab.class);
			Root<RefAffectFiliereLmdEtab> aff = c.from(RefAffectFiliereLmdEtab.class);
			Join<RefAffectFiliereLmdEtab, RefEtablissement> affEtab = aff.join("refEtablissement");
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(affEtab.get("id"),codeEtab);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			c.select(aff).distinct(true);
			//c.orderBy(cb.asc(aff.get("id")));
			
			TypedQuery<RefAffectFiliereLmdEtab> q = entityManager.createQuery(c);
			log.debug("findFilieresLMDByEtab 'RefAffectFiliereLmdEtab' successful");
			return q.getResultList();
	}
	catch (RuntimeException re) {
			log.error("findFilieresLMDByEtab 'RefAffectFiliereLmdEtab' failed", re);
			throw re;
		}
	}



	/**
	 * [RefFiliereLmdDao.findByAncienCodeFiliere] Method 
	 * @author rlaib  on : 9 nov. 2014  16:53:34
	 * @param ancienCodeFiliere
	 * @return
	 */
	@Override
	public List<RefFiliereLmd> findByAncienCodeFiliere(String ancienCodeFiliere) {
		
		log.debug("getting List of findByAncienCodeFiliere instance with : " + ancienCodeFiliere);
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefFiliereLmd> c = cb.createQuery(RefFiliereLmd.class);
				Root<RefFiliereLmd> fil = c.from(RefFiliereLmd.class);
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(fil.get("ancienCodeFiliere"),ancienCodeFiliere);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(fil).distinct(true);
				c.orderBy(cb.asc(fil.get("llFiliere")));
				TypedQuery<RefFiliereLmd> q = entityManager.createQuery(c);
				log.debug("findByAncienCodeFiliere 'RefFiliereLmd' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findByAncienCodeFiliere 'RefFiliereLmd' failed", re);
				throw re;
		}
	}
	


}
