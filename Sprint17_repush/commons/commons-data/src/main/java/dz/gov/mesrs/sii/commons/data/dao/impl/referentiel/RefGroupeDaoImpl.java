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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefGroupeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository("refGroupeDaoImpl")
public class RefGroupeDaoImpl implements RefGroupeDao {

	private static final Logger log = LoggerFactory
			.getLogger(RefGroupeDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefGroupeDao
	 * #persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefGroupe)
	 */
	@Override
	public void persist(RefGroupe transientInstance) {
		log.info("dao saveGroupe");
		log.debug("persisting RefGroupe instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefGroupeDao
	 * #remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefGroupe)
	 */
	@Override
	public void remove(RefGroupe persistentInstance) {
		log.debug("removing RefGroupe instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefGroupeDao
	 * #merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefGroupe)
	 */
	@Override
	public RefGroupe merge(RefGroupe detachedInstance) {
		log.debug("merging RefGroupe instance");
		try {
			RefGroupe result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefGroupeDao
	 * #findById(java.lang.String)
	 */
	@Override
	public RefGroupe findById(Integer id) {
		log.debug("getting RefGroupe instance with id: " + id);
		try {
			RefGroupe instance = entityManager.find(RefGroupe.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefGroupeDao
	 * #findAll()
	 */
	@Override
	public List<RefGroupe> findAll() {
		log.debug("getting List of RefGroupe(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			request.append("order by r.llGroupe ");
			Query query = entityManager.createQuery(new String(request));
			List<RefGroupe> resultList = (List<RefGroupe>) query
					.getResultList();
			log.debug("RefGroupe findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefGroupe findAll failed", re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefGroupe> findGeneric(String value) {

		log.debug("getting List of RefGroupe instance with : " + value);
		List<RefGroupe> resultList = new ArrayList<RefGroupe>();
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");

			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value.trim() + "%";
				value = value.toUpperCase();

				request.append("WHERE upper(r.identifiant) like :value ");
				request.append("OR  upper(r.lcGroupe) like :value ");
				request.append("OR  upper(r.lcGroupeArabe) like :value ");
				request.append("OR  upper(r.llGroupe) like :value ");
				request.append("OR  upper(r.llGroupeArabe) like :value ");

				Query query = entityManager.createQuery(request.toString());
				query.setParameter("value", value);
				resultList = (List<RefGroupe>) query.getResultList();
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
	public List<RefGroupe> findAdvanced(RefGroupe refGroupe) {
		log.debug("findAdvanced: getting List of refGroupe instance : ");
		if (refGroupe == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			String identifiant = refGroupe.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcGroupeArabe = refGroupe.getLcGroupeArabe();
			if ((lcGroupeArabe != null) && (!lcGroupeArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcGroupeArabe like ")
						.append(UtilConstant.LikeContain(lcGroupeArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcGroupe = refGroupe.getLcGroupe();
			if ((lcGroupe != null) && (!lcGroupe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcGroupe like ")
						.append(UtilConstant.LikeContain(lcGroupe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llGroupeArabe = refGroupe.getLlGroupeArabe();
			if ((llGroupeArabe != null) && (!llGroupeArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llGroupeArabe like ")
						.append(UtilConstant.LikeContain(llGroupeArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llGroupe = refGroupe.getLlGroupe();
			if ((llGroupe != null) && (!llGroupe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llGroupe like ")
						.append(UtilConstant.LikeContain(llGroupe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefGroupe> resultList = (List<RefGroupe>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefGroupe> findGeneric(Integer etabId, String value) {

		log.debug("getting List of RefGroupe instance with : " + value);
		List<RefGroupe> resultList = new ArrayList<RefGroupe>();
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefGroupe r ");
			request.append(" where (1=1) ");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}

			if (value != null) {
				value = "%" + value.trim() + "%";
				value = value.toUpperCase();

				request.append("AND ((upper(r.identifiant) like :value ");
				request.append("OR  upper(r.lcGroupe) like :value ");
				request.append("OR  upper(r.lcGroupeArabe) like :value ");
				request.append("OR  upper(r.llGroupe) like :value ");
				request.append("OR  upper(r.llGroupeArabe) like :value)) ");

				Query query = entityManager.createQuery(request.toString());
				query.setParameter("value", value);
				resultList = (List<RefGroupe>) query.getResultList();
			}
			log.debug("findGeneric successful");

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefGroupe> findAdvanced(Integer etabId, RefGroupe refGroupe) {
		log.debug("findAdvanced: getting List of refGroupe instance : ");
		if (refGroupe == null) {
			return null;
		}
		String firstOpr = UtilConstant.AND_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			request.append("where (1=1)  ");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			String identifiant = refGroupe.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(" and lower(r.identifiant)  "
						+ identifiant.toLowerCase());
			}

			String lcGroupeArabe = refGroupe.getLcGroupeArabe();
			if ((lcGroupeArabe != null) && (!lcGroupeArabe.trim().isEmpty())) {
				request.append(" and lower(r.lcGroupeArabe)  "
						+ lcGroupeArabe.toLowerCase());
			}

			String lcGroupe = refGroupe.getLcGroupe();
			if ((lcGroupe != null) && (!lcGroupe.trim().isEmpty())) {
				request.append(" and lower(r.lcGroupe)  "
						+ lcGroupe.toLowerCase());
			}

			String llGroupeArabe = refGroupe.getLlGroupeArabe();
			if ((llGroupeArabe != null) && (!llGroupeArabe.trim().isEmpty())) {
				request.append(" and lower(r.llGroupeArabe)  "
						+ llGroupeArabe.toLowerCase());
			}

			String llGroupe = refGroupe.getLlGroupe();
			if ((llGroupe != null) && (!llGroupe.trim().isEmpty())) {
				request.append(" and lower(r.llGroupe)  "
						+ llGroupe.toLowerCase());
			}
			
			log.debug("request successful " + new String(request));
			TypedQuery<RefGroupe> query = entityManager.createQuery(new String(
					request), RefGroupe.class);
			List<RefGroupe> resultList = (List<RefGroupe>) query
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
	public List<RefGroupe> findAll(Integer etabId) {
		log.debug("getting List of RefGroupe(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			request.append(" order by r.llGroupe ");
			Query query = entityManager.createQuery(new String(request));
			List<RefGroupe> resultList = (List<RefGroupe>) query
					.getResultList();
			log.debug("RefGroupe findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefGroupe findAll failed", re);
			throw re;
		}

	}

	@Override
	public Integer findLastOrder(String prefix, int orderLength) {
		if (prefix == null) {
			return null;
		}
		int prefixLength = prefix.length();
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefGroupe> resultList = (List<RefGroupe>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return 0;
			} else {
				log.debug("findLastOrder successful");
				String lastIdentify = resultList.get(0).getIdentifiant();
				if (lastIdentify == null) {
					return null;
				}
				String lastOrderStr = lastIdentify.substring(prefixLength);
				if (lastOrderStr == null
						|| lastOrderStr.length() != orderLength) {
					return null;
				}
				Integer lastOrder = Integer.parseInt(lastOrderStr);
				return lastOrder;
			}

		} catch (RuntimeException re) {
			log.error("findLastOrder failed", re);
			throw re;
		}
	}

	@Override
	public RefGroupe findByCode(String code) {
		if (code == null) {
			return null;
		}
		code = code.toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefGroupe r ");
			request.append(" where lower(r.identifiant) =:value ");
			TypedQuery<RefGroupe> query = entityManager.createQuery(new String(
					request), RefGroupe.class);
			query.setParameter("value", code);
			List<RefGroupe> resultList = (List<RefGroupe>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByCode successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findByCode failed", re);
			throw re;
		}

	}

	/**
	 * [RefGroupeDao.findByEtablissement] Method Overridden By : @author rlaib
	 * on : 18 déc. 2014 14:44:57
	 * 
	 * @param idEtab
	 * @return
	 */
	@Override
	public List<RefGroupe> findByEtablissement(Integer idEtab) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefGroupe> c = cb.createQuery(RefGroupe.class);
			Root<RefGroupe> gr = c.from(RefGroupe.class);
			Join<RefGroupe, RefEtablissement> grEtab = gr
					.join("refEtablissement");
			c.select(gr).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(grEtab.get("id"), idEtab);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefGroupe> q = entityManager.createQuery(c);
			List<RefGroupe> result = q.getResultList();
			for (RefGroupe entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findByEtablissement 'RefGroupe' failed", re);
			throw re;
		}
	}

	/**
	 * [RefGroupeDao.findGeneric] Method Overridden By : @author rlaib on : 13
	 * janv. 2015 10:15:34
	 * 
	 * @param etabId
	 * @param value
	 * @param idTypeGroupe
	 * @return
	 */
	@Override
	public List<RefGroupe> findGeneric(Integer etabId, String value,
			Integer idTypeGroupe) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefGroupe> c = cb.createQuery(RefGroupe.class);
			Root<RefGroupe> gr = c.from(RefGroupe.class);
			Join<RefGroupe, Nomenclature> grNc = gr.join("typeGroupe");
			Join<RefGroupe, RefEtablissement> grEtab = gr
					.join("refEtablissement");
			c.select(gr).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			if (value != null) {
				criteria.add(cb.like(cb.lower(gr.<String> get("lcGroupe")), "%"
						+ value.toLowerCase() + "%"));
				criteria.add(cb.like(
						cb.lower(gr.<String> get("lcGroupeArabe")),
						"%" + value.toLowerCase() + "%"));
				criteria.add(cb.like(cb.lower(gr.<String> get("llGroupe")), "%"
						+ value.toLowerCase() + "%"));
				criteria.add(cb.like(
						cb.lower(gr.<String> get("llGroupeArabe")),
						"%" + value.toLowerCase() + "%"));
			}
			Predicate where = cb.conjunction();
			Predicate conditionEtab = null;
			if (etabId != null) {
				conditionEtab = cb.equal(grEtab.get("id"), etabId);
			}
			if (conditionEtab != null)
				where = cb.and(conditionEtab);

			Predicate conditionType = null;
			if (idTypeGroupe != null) {
				conditionType = cb.equal(grNc.get("id"), idTypeGroupe);
			}
			if (conditionType != null)
				where = cb.and(conditionType);

			if (criteria != null && criteria.size() > 0) {
				where = cb
						.and(where, cb.or(criteria.toArray(new Predicate[0])));
			}
			c.where(where);
			TypedQuery<RefGroupe> q = entityManager.createQuery(c);
			List<RefGroupe> result = q.getResultList();
			for (RefGroupe entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findGeneric 'RefGroupe' failed", re);
			throw re;
		}
	}

}
