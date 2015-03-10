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
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineLMDDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefDomaineLMD.
 * 
 * @see dz.gov.mesrs.sii.RefDomaineLMDDto.business.model.bo.RefDomaineLMD
 * @author MESRS CCM
 * @version 1.0
 * @created 14-04-2014 10:44:07
 */

@Repository("refDomaineLMDDaoImpl")
public class RefDomaineLMDDaoImpl implements RefDomaineLMDDao {

	private static final Logger log = LoggerFactory.getLogger(RefDomaineLMDDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.RefDomaineLMDService.business.persistence.jpa.impl.RefDomaineLMDDao#persist(dz.gov.mesrs.sii.RefDomaineLMDDto.business.model.bo.RefDomaineLMD)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefDomaineLMD transientInstance) {
		log.debug("persisting RefDomaineLMD instance");
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
	 * @see dz.gov.mesrs.sii.RefDomaineLMDService.business.persistence.jpa.impl.RefDomaineLMDDao#remove(dz.gov.mesrs.sii.RefDomaineLMDDto.business.model.bo.RefDomaineLMD)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefDomaineLMD persistentInstance) {
		log.debug("removing RefDomaineLMD instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.RefDomaineLMDService.business.persistence.jpa.impl.RefDomaineLMDDao#merge(dz.gov.mesrs.sii.RefDomaineLMDDto.business.model.bo.RefDomaineLMD)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefDomaineLMD merge(RefDomaineLMD detachedInstance) {
		log.debug("merging RefDomaineLMD instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefDomaineLMDDao#findById(int)
	 * @param id
	 */
	@Override
	public RefDomaineLMD findById(int id) {
		log.debug("getting RefDomaineLMD instance with id: " + id);
		try {
			log.debug("get successful");
			RefDomaineLMD instance = entityManager
					.find(RefDomaineLMD.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RefDomaineLMD findByIdentifiant(String identifiant) {

		try {
			Query _query = entityManager
					.createQuery("from RefDomaineLMD domaine where lower(domaine.identifiant) = :identifiant");
			_query.setParameter("identifiant", identifiant.trim().toLowerCase());
			List<RefDomaineLMD> _resultList = (List<RefDomaineLMD>) _query
					.getResultList();

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
	public List<RefDomaineLMD> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RefDomaineLMD> findAll() {
		
		log.debug("getting all RefDomaineLMD instances");
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<RefDomaineLMD> c = cb.createQuery(RefDomaineLMD.class);
					Root<RefDomaineLMD> dm = c.from(RefDomaineLMD.class);
					c.select(dm).distinct(true);
					c.orderBy(cb.asc(dm.get("llDomaine")));
					TypedQuery<RefDomaineLMD> q = entityManager.createQuery(c);
					
					log.debug("findAll 'RefDomaineLMD' successful");
					return q.getResultList();
		}
		catch (RuntimeException re) {
					log.error("findAll 'RefDomaineLMD' failed", re);
					return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefDomaineLMD> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefDomaineLMD instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaineLMD r ");

			request.append(" where r.identifiant like :value ");
			request.append("or r.lcDomaine like :value ");
			request.append("or r.lcDomaineArabe like :value ");
			request.append("or r.llDomaine like :value ");
			request.append("or r.llDomaineArabe like :value ");
			request.append("or r.description like :value ");
			request.append("or r.descriptionArabe like :value ");

			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefDomaineLMD> resultList = (List<RefDomaineLMD>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefDomaineLMD> findAdvanced(RefDomaineLMD refDomaineLMD) {
		log.debug("findAdvanced: getting List of refDomaineLMD instance : ");
		if (refDomaineLMD == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefDomaineLMD r ");
			String identifiant = refDomaineLMD.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LcDomaine = refDomaineLMD.getLcDomaine();
			if ((LcDomaine != null) && (!LcDomaine.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcDomaine like ")
						.append(UtilConstant.LikeContain(LcDomaine));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LlDomaine = refDomaineLMD.getLlDomaine();
			if ((LlDomaine != null) && (!LlDomaine.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llDomaine like ")
						.append(UtilConstant.LikeContain(LlDomaine));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LcDomaineArabe = refDomaineLMD.getLcDomaineArabe();
			if ((LcDomaineArabe != null) && (!LcDomaineArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcDomaineArabe like ")
						.append(UtilConstant.LikeContain(LcDomaineArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LlDomaineArabe = refDomaineLMD.getLlDomaineArabe();
			if ((LlDomaineArabe != null) && (!LlDomaineArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llDomaineArabe like ")
						.append(UtilConstant.LikeContain(LlDomaineArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefDomaineLMD> resultList = (List<RefDomaineLMD>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}

	}

	@Override
	public List<RefAffectDomLmdEtab> findDomainesLMDByEtab(Integer idEtab) {
		

		log.debug("getting List of RefDomaineLMD instance with : " + idEtab);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefAffectDomLmdEtab> c = cb.createQuery(RefAffectDomLmdEtab.class);
				Root<RefAffectDomLmdEtab> aff = c.from(RefAffectDomLmdEtab.class);
				Join<RefAffectDomLmdEtab, RefEtablissement> affEtab = aff.join("refEtablissement");
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(affEtab.get("id"),idEtab);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(aff).distinct(true);
				//c.orderBy(cb.asc(aff.get("id")));
				
				TypedQuery<RefAffectDomLmdEtab> q = entityManager.createQuery(c);
				log.debug("findDomainesLMDByEtab 'RefAffectDomLmdEtab' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findDomainesLMDByEtab 'RefAffectDomLmdEtab' failed", re);
				throw re;
		}
	}
	
	
	@Override
	public List<RefAffectDomLmdEtab> findDomainesLMDByCodeEtab(String codeEtab) {
		

		log.debug("getting List of RefDomaineLMD instance with : " + codeEtab);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefAffectDomLmdEtab> c = cb.createQuery(RefAffectDomLmdEtab.class);
				Root<RefAffectDomLmdEtab> aff = c.from(RefAffectDomLmdEtab.class);
				Join<RefAffectDomLmdEtab, RefEtablissement> affEtab = aff.join("refEtablissement");
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(affEtab.get("identifiant"),codeEtab);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(aff).distinct(true);
				
				TypedQuery<RefAffectDomLmdEtab> q = entityManager.createQuery(c);
				log.debug("findDomainesLMDByCodeEtab 'RefAffectDomLmdEtab' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findDomainesLMDByCodeEtab 'RefAffectDomLmdEtab' failed", re);
				throw re;
		}
	}

	/**
	 * [RefDomaineLMDDao.findDomainesByEtab] Method 
	 * @author rlaib  on : 18 nov. 2014  11:44:15
	 * @param idEtab
	 * @return
	 */
	@Override
	public List<RefDomaineLMD> findDomainesByEtab(Integer idEtab) {
		log.debug("getting List of RefDomaineLMD instance with : " + idEtab);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefDomaineLMD> c = cb.createQuery(RefDomaineLMD.class);
				Root<RefDomaineLMD> dom = c.from(RefDomaineLMD.class);
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				
				Subquery<RefAffectDomLmdEtab> subquery = c.subquery(RefAffectDomLmdEtab.class);
				Root<RefAffectDomLmdEtab> aff = subquery.from(RefAffectDomLmdEtab.class);
				Join<RefAffectDomLmdEtab, RefEtablissement> affEtab = aff.join("refEtablissement");
				subquery.select(aff);
				List<Predicate> criteriaSubQuery = new ArrayList<Predicate>();
				Predicate p1 = cb.equal(aff.get("refDomaineLMD"),dom);
				criteriaSubQuery.add(p1);
				Predicate p2 = cb.equal(affEtab.get("id"),idEtab);
				criteriaSubQuery.add(p2);
				subquery.where(cb.and(criteriaSubQuery.toArray(new Predicate[0])));
				
				criteria.add(cb.exists(subquery)); 
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(dom).distinct(true);
				c.orderBy(cb.asc(dom.get("llDomaine")));
				
				TypedQuery<RefDomaineLMD> q = entityManager.createQuery(c);
				log.debug("findDomainesLMDByEtab 'RefAffectDomLmdEtab' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findDomainesLMDByEtab 'RefAffectDomLmdEtab' failed", re);
				throw re;
		}
	}
	
}
