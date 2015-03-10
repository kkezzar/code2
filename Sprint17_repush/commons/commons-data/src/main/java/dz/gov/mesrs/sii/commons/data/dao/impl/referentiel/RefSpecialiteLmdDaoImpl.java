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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefSpecialiteLmdDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefSpecialiteLmd.
 * 
 * @see dz.gov.mesrs.sii.RefSpecialiteLmdDto.business.model.bo.RefSpecialiteLmd
 * @author MESRS CCM
 * @version 1.0
 * @created 24-04-2014 10:44:07
 */

@Repository("refSpecialiteLmdDaoImpl")
public class RefSpecialiteLmdDaoImpl implements RefSpecialiteLmdDao {

	private static final Logger log = LoggerFactory.getLogger(RefSpecialiteLmdDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.RefSpecialiteLmdService.business.persistence.jpa.impl.RefSpecialiteLmdDao#persist(dz.gov.mesrs.sii.RefSpecialiteLmdDto.business.model.bo.RefSpecialiteLmd)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefSpecialiteLmd transientInstance) {
		log.debug("persisting RefSpecialiteLmd instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.RefSpecialiteLmdService.business.persistence.jpa.impl.RefSpecialiteLmdDao#remove(dz.gov.mesrs.sii.RefSpecialiteLmdDto.business.model.bo.RefSpecialiteLmd)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefSpecialiteLmd persistentInstance) {
		log.debug("removing RefSpecialiteLmd instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.RefSpecialiteLmdService.business.persistence.jpa.impl.RefSpecialiteLmdDao#merge(dz.gov.mesrs.sii.RefSpecialiteLmdDto.business.model.bo.RefSpecialiteLmd)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefSpecialiteLmd merge(RefSpecialiteLmd detachedInstance) {
		log.debug("merging RefSpecialiteLmd instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefSpecialiteLmdDao#findById(int)
	 * @param id
	 */
	@Override
	public RefSpecialiteLmd findById(int id) {
		log.debug("getting RefSpecialiteLmd instance with id: " + id);
		try {
			log.debug("get successful");
			RefSpecialiteLmd instance = entityManager.find(
					RefSpecialiteLmd.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RefSpecialiteLmd findByIdentifiant(String identifiant) {
		
		try {
			Query _query = entityManager
					.createQuery("from RefSpecialiteLmd specialite where lower(specialite.identifiant) = :identifiant");
			_query.setParameter("identifiant", identifiant.trim().toLowerCase());
			List<RefSpecialiteLmd> _resultList = (List<RefSpecialiteLmd>) _query
					.getResultList();

			log.debug("findByCode successful");

			if (_resultList.size() == 1)
				return _resultList.get(0);
			else
				return null;

		} catch (RuntimeException re) {
			log.error("findByCode failed", re);
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
	public List<RefSpecialiteLmd> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RefSpecialiteLmd> findAll() {
		log.debug("getting all RefSpecialiteLmd instances");
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<RefSpecialiteLmd> c = cb.createQuery(RefSpecialiteLmd.class);
					Root<RefSpecialiteLmd> spec = c.from(RefSpecialiteLmd.class);
					c.select(spec).distinct(true);
					c.orderBy(cb.asc(spec.get("llSpecialite")));
					TypedQuery<RefSpecialiteLmd> q = entityManager.createQuery(c);
					log.debug("findAll 'RefSpecialiteLmd' successful");
					return q.getResultList();
					
		} catch (RuntimeException re) {
			log.error("findAll 'RefSpecialiteLmd' failed", re);
			return new ArrayList<RefSpecialiteLmd>();
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<RefSpecialiteLmd> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefSpecialiteLmd instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefSpecialiteLmd r ");
			request.append("left join r.refDomaineLMD as domaine left join r.refFiliereLmd as filiere");

			request.append(" where r.identifiant like :value ");
			request.append("or r.lcSpecialite like :value ");
			request.append("or r.lcSpecialiteArabe like :value ");
			request.append("or r.llSpecialite like :value ");
			request.append("or r.llSpecialiteArabe like :value ");
			request.append("or r.descriptionSpecialite like :value ");
			request.append("or r.descriptionSpecialiteArabe like :value ");
			request.append(" or r.refDomaineLMD.identifiant like :value ");
			request.append(" or r.refFiliereLmd.identifiant like :value ");

			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefSpecialiteLmd> resultList = (List<RefSpecialiteLmd>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}
	
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<RefSpecialiteLmd> findGeneric(String value) {
//		
//		log.debug("getting List of RefSpecialiteLmd instance with : " + value);
//		List<RefSpecialiteLmd> resultList = new ArrayList<RefSpecialiteLmd>();
//		try {
//			StringBuilder request = new StringBuilder(
//					"select r from RefSpecialiteLmd r ");
//			request.append("left join r.refDomaineLMD as domaine left join r.refFiliereLmd as filiere");
//
//			
//			if (value != null && !value.trim().isEmpty()) {
//				value = "%" + value.trim() + "%";
//				value = value.toUpperCase();
//				request.append("WHERE upper(r.identifiant) like :value ");
//				request.append("OR upper(r.lcSpecialite) like :value ");
//				request.append("OR upper(r.lcSpecialiteArabe) like :value ");
//				request.append("OR upper(r.llSpecialite) like :value ");
//				request.append("OR upper(r.llSpecialiteArabe) like :value ");
//				request.append("OR upper(r.descriptionSpecialite) like :value ");
//				request.append("OR upper(r.descriptionSpecialiteArabe) like :value ");
//				request.append(" or r.refDomaineLMD.identifiant like :value ");
//				request.append(" or r.refFiliereLmd.identifiant like :value ");
//				Query query = entityManager.createQuery(request.toString());
//				query.setParameter("value", value);
//				resultList = (List<RefSpecialiteLmd>) query.getResultList();
//			}
//			
//
//			log.debug("findGeneric successful");
//		
//
//		} catch (RuntimeException re) {
//			log.error("findGeneric failed", re);
//			throw re;
//		}
//		return resultList;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefSpecialiteLmd> findAdvanced(RefSpecialiteLmd refSpecialiteLmd) {
		log.debug("findAdvanced: getting List of refSpecialiteLmd instance : ");
		if (refSpecialiteLmd == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefSpecialiteLmd r ");
			String identifiant = refSpecialiteLmd.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LcSpecialite = refSpecialiteLmd.getLcSpecialite();
			if ((LcSpecialite != null) && (!LcSpecialite.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcSpecialite like ")
						.append(UtilConstant.LikeContain(LcSpecialite));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LlSpecialite = refSpecialiteLmd.getLlSpecialite();
			if ((LlSpecialite != null) && (!LlSpecialite.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llSpecialite like ")
						.append(UtilConstant.LikeContain(LlSpecialite));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LcSpecialiteArabe = refSpecialiteLmd.getLcSpecialiteArabe();
			if ((LcSpecialiteArabe != null)
					&& (!LcSpecialiteArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcSpecialiteArabe like ")
						.append(UtilConstant.LikeContain(LcSpecialiteArabe));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String LlSpecialiteArabe = refSpecialiteLmd.getLlSpecialiteArabe();
			if ((LlSpecialiteArabe != null)
					&& (!LlSpecialiteArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llSpecialiteArabe like ")
						.append(UtilConstant.LikeContain(LlSpecialiteArabe));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refSpecialiteLmd.getRefDomaineLMD() != null) {
				String identifiantDomaine = refSpecialiteLmd.getRefDomaineLMD()
						.getIdentifiant();
				request.append(firstOpr)
						.append(" r.refDomaineLMD.identifiant = ")
						.append(UtilConstant.quotedString(identifiantDomaine));
			}

			if (refSpecialiteLmd.getRefFiliereLmd() != null) {
				String identifiantFiliere = refSpecialiteLmd.getRefFiliereLmd()
						.getIdentifiant();
				request.append(firstOpr)
						.append(" r.refFiliereLmd.identifiant = ")
						.append(UtilConstant.quotedString(identifiantFiliere));
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefSpecialiteLmd> resultList = (List<RefSpecialiteLmd>) query
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
	public List<RefSpecialiteLmd> findByCodeFilierelmd(String codeFiliere) {

		log.debug("getting List of RefSpecialiteLmd instance with : " + codeFiliere);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefSpecialiteLmd> c = cb.createQuery(RefSpecialiteLmd.class);
				Root<RefSpecialiteLmd> spec = c.from(RefSpecialiteLmd.class);
				Join<RefSpecialiteLmd, RefFiliereLmd> specFil = spec.join("refFiliereLmd");
				
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(specFil.get("identifiant"),codeFiliere);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(spec).distinct(true);
				c.orderBy(cb.asc(spec.get("llSpecialite")));
				
				TypedQuery<RefSpecialiteLmd> q = entityManager.createQuery(c);
				log.debug("findByCodeFilierelmd 'RefSpecialiteLmd' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findByCodeFilierelmd 'RefSpecialiteLmd' failed", re);
				throw re;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefSpecialiteLmd> findByIdFilierelmd(Integer idFiliere) {
		log.debug("getting List of RefSpecialiteLmd instance with : " + idFiliere);

		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<RefSpecialiteLmd> c = cb.createQuery(RefSpecialiteLmd.class);
				Root<RefSpecialiteLmd> spec = c.from(RefSpecialiteLmd.class);
				Join<RefSpecialiteLmd, RefFiliereLmd> specFil = spec.join("refFiliereLmd");
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(specFil.get("id"),idFiliere);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				c.select(spec).distinct(true);
				c.orderBy(cb.asc(spec.get("llSpecialite")));
				TypedQuery<RefSpecialiteLmd> q = entityManager.createQuery(c);
				log.debug("findByIdFilierelmd 'RefSpecialiteLmd' successful");
				return q.getResultList();
		}
		catch (RuntimeException re) {
				log.error("findByIdFilierelmd 'RefSpecialiteLmd' failed", re);
				throw re;
		}
	}

}
