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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefStructureDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository("refStructureDaoImpl")
public class RefStructureDaoImpl implements RefStructureDao {

	private static final Logger log = LoggerFactory.getLogger(RefStructureDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefStructureDao
	 * #persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefStructure)
	 */
	@Override
	public void persist(RefStructure transientInstance) {
		log.info("dao saveStructure");
		log.debug("persisting RefStructure instance");
		try {
			entityManager.persist(transientInstance);
			log.info("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefStructureDao
	 * #remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefStructure)
	 */
	@Override
	public void remove(RefStructure persistentInstance) {
		log.debug("removing RefStructure instance");
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
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefStructureDao
	 * #merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefStructure)
	 */
	@Override
	public RefStructure merge(RefStructure detachedInstance) {
		log.debug("merging RefStructure instance");
		try {
			RefStructure result = entityManager.merge(detachedInstance);
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
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefStructureDao
	 * #findById(java.lang.String)
	 */
	@Override
	public RefStructure findById(Integer id) {
		log.debug("getting RefStructure instance with id: " + id);
		try {
			RefStructure instance = entityManager.find(RefStructure.class, id);
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
	 * dz.gov.mesrs.sii.referentiel.business.persistence.RefStructureDao#findAll
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RefStructure> findAll() {
		log.debug("getting List of RefStructure(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append("order by r.llStructureLatin ");
			Query query = entityManager.createQuery(new String(request));
			List<RefStructure> resultList = (List<RefStructure>) query
					.getResultList();
			log.debug("RefStructure findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefStructure findAll failed", re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefStructure> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefStructure instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append("left join r.nomenclatureByNcformejuridiqueid as ncformeJuridique left join r.nomenclatureByNcstatutstructureid as ncStatut left join r.nomenclatureByTypeStructure as ncTypeStructure ");

			request.append("where r.identifiant like :value ");
			request.append("or r.lcStructureArabe like :value ");
			request.append("or r.lcStructureLatin like :value ");
			request.append("or r.llStructureArabe like :value ");
			request.append("or r.llStructureLatin like :value ");
			request.append("or r.nif like :value ");
			request.append("or r.nis like :value ");
			request.append("or r.nrc like :value ");
			request.append("or r.nss like :value ");
			request.append("or r.decret like :value ");
			request.append("or r.nomenclatureByNcformejuridiqueid.libelleLongFr like :value ");
			request.append("or r.nomenclatureByNcstatutstructureid.libelleLongFr like :value ");
			request.append("or r.nomenclatureByTypeStructure.libelleLongFr like :value ");

			// request.append("or cast(r.dateNaissance as text) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefStructure> resultList = (List<RefStructure>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefStructure> findAdvanced(RefStructure refStructure) {
		log.debug("findAdvanced: getting List of refStructure instance : ");
		if (refStructure == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			String identifiant = refStructure.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcStructureArabe = refStructure.getLcStructureArabe();
			if ((lcStructureArabe != null)
					&& (!lcStructureArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcStructureArabe like ")
						.append(UtilConstant.LikeContain(lcStructureArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}
			/*
			 * Date dateNaissance = refStructure.getDateNaissance();
			 * 
			 * if (dateNaissance != null) { request.append(firstOpr).append(
			 * " cast(r.dateNaissance as text) like "
			 * ).append(ConverterService.LikeContain(dateNaissance)); firstOpr =
			 * ConverterService.OR_SQL_STR; }
			 */
			String lcStructureLatin = refStructure.getLcStructureLatin();
			if ((lcStructureLatin != null)
					&& (!lcStructureLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcStructureLatin like ")
						.append(UtilConstant.LikeContain(lcStructureLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llStructureArabe = refStructure.getLlStructureArabe();
			if ((llStructureArabe != null)
					&& (!llStructureArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llStructureArabe like ")
						.append(UtilConstant.LikeContain(llStructureArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llStructureLatin = refStructure.getLlStructureLatin();
			if ((llStructureLatin != null)
					&& (!llStructureLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llStructureLatin like ")
						.append(UtilConstant.LikeContain(llStructureLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String decret = refStructure.getDecret();
			if ((decret != null) && (!decret.trim().isEmpty())) {
				request.append(firstOpr).append(" r.decret like ")
						.append(UtilConstant.LikeContain(decret));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nif = refStructure.getNif();
			if ((nif != null) && (!nif.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nif like ")
						.append(UtilConstant.LikeContain(nif));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nis = refStructure.getNis();
			if ((nis != null) && (!nis.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nis like ")
						.append(UtilConstant.LikeContain(nis));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nrc = refStructure.getNrc();
			if ((nrc != null) && (!nrc.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nrc like ")
						.append(UtilConstant.LikeContain(nrc));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nss = refStructure.getNss();
			if ((nss != null) && (!nss.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nss like ")
						.append(UtilConstant.LikeContain(nss));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refStructure.getNomenclatureByTypeStructure() != null) {
				int typeStructureId = refStructure
						.getNomenclatureByTypeStructure().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureByTypeStructure.id = ")
						.append(typeStructureId);
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefStructure> resultList = (List<RefStructure>) query
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
	public List<RefStructure> findAll(Integer etabId) {
		log.debug("getting List of RefStructure(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			request.append(" order by r.llStructureLatin ");
			Query query = entityManager.createQuery(new String(request));
			List<RefStructure> resultList = (List<RefStructure>) query
					.getResultList();
			log.debug("RefStructure findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefStructure findAll failed", re);
			throw re;
		}
	}

	@Override
	public List<RefStructure> findGeneric(Integer etabId, String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefStructure instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append("left join r.nomenclatureByNcformejuridiqueid as ncformeJuridique left join r.nomenclatureByNcstatutstructureid as ncStatut left join r.nomenclatureByTypeStructure as ncTypeStructure ");
			request.append(" where(1=1) ");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			request.append(" and (r.identifiant like :value ");
			request.append("or r.lcStructureArabe like :value ");
			request.append("or r.lcStructureLatin like :value ");
			request.append("or r.llStructureArabe like :value ");
			request.append("or r.llStructureLatin like :value ");
			request.append("or r.nif like :value ");
			request.append("or r.nis like :value ");
			request.append("or r.nrc like :value ");
			request.append("or r.nss like :value ");
			request.append("or r.decret like :value ");
			request.append("or r.nomenclatureByNcformejuridiqueid.libelleLongFr like :value ");
			request.append("or r.nomenclatureByNcstatutstructureid.libelleLongFr like :value ");
			request.append("or r.nomenclatureByTypeStructure.libelleLongFr like :value) ");

			// request.append("or cast(r.dateNaissance as text) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefStructure> resultList = (List<RefStructure>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefStructure> findAdvanced(Integer etabId,
			RefStructure refStructure) {
		log.debug("findAdvanced: getting List of refStructure instance : ");
		if (refStructure == null) {
			return null;
		}
		String firstOpr = UtilConstant.AND_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			String identifiant = refStructure.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcStructureArabe = refStructure.getLcStructureArabe();
			if ((lcStructureArabe != null)
					&& (!lcStructureArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcStructureArabe like ")
						.append(UtilConstant.LikeContain(lcStructureArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcStructureLatin = refStructure.getLcStructureLatin();
			if ((lcStructureLatin != null)
					&& (!lcStructureLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" r.lcStructureLatin like ")
						.append(UtilConstant.LikeContain(lcStructureLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llStructureArabe = refStructure.getLlStructureArabe();
			if ((llStructureArabe != null)
					&& (!llStructureArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llStructureArabe like ")
						.append(UtilConstant.LikeContain(llStructureArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llStructureLatin = refStructure.getLlStructureLatin();
			if ((llStructureLatin != null)
					&& (!llStructureLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" r.llStructureLatin like ")
						.append(UtilConstant.LikeContain(llStructureLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String decret = refStructure.getDecret();
			if ((decret != null) && (!decret.trim().isEmpty())) {
				request.append(firstOpr).append(" r.decret like ")
						.append(UtilConstant.LikeContain(decret));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nif = refStructure.getNif();
			if ((nif != null) && (!nif.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nif like ")
						.append(UtilConstant.LikeContain(nif));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nis = refStructure.getNis();
			if ((nis != null) && (!nis.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nis like ")
						.append(UtilConstant.LikeContain(nis));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nrc = refStructure.getNrc();
			if ((nrc != null) && (!nrc.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nrc like ")
						.append(UtilConstant.LikeContain(nrc));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nss = refStructure.getNss();
			if ((nss != null) && (!nss.trim().isEmpty())) {
				request.append(firstOpr).append(" r.nss like ")
						.append(UtilConstant.LikeContain(nss));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refStructure.getNomenclatureByTypeStructure() != null) {
				int typeStructureId = refStructure
						.getNomenclatureByTypeStructure().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureByTypeStructure.id = ")
						.append(typeStructureId);
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefStructure> resultList = (List<RefStructure>) query
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
	public Integer findLastOrder(String prefix, int orderLength) {
		if (prefix == null) {
			return null;
		}
		int prefixLength = prefix.length();
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefStructure r ");
			request.append(" where lower(r.identifiant) like "
					+ UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefStructure> resultList = (List<RefStructure>) query
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

	/**
	 * [RefStructureDaoImpl.findStrcuturesByTypeByEtab] Method 
	 * @author rlaib  on : 11 oct. 2014  10:43:55
	 * @param etabId
	 * @param typeStructureId
	 * @return
	 */
	@Override
	public List<RefStructure> findStructuresByTypeByEtab(Integer etabId, int typeStructureId) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefStructure> c = cb.createQuery(RefStructure.class);
			Root<RefStructure> s = c.from(RefStructure.class);
			Join<RefStructure, RefEtablissement> se = s.join("refEtablissement");
			Join<RefStructure, Nomenclature> sn = s.join("nomenclatureByTypeStructure");
			c.select(s).distinct(true);
			c.orderBy(cb.asc(s.get("llStructureLatin")));
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(se.get("id"), etabId);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(sn.get("id"), typeStructureId);
			criteria.add(condition2);
			
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefStructure> q = entityManager.createQuery(c);
			List<RefStructure> result = q.getResultList();
			for(RefStructure entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findStrcuturesByTypeByEtab 'RefStructure' failed", re);
			throw re;
		}
	}

	/**
	 * [RefStructureDao.findStructuresOfMasterStructureByEtab] Method 
	 * @author Rafik  on : 5 déc. 2014  22:03:38
	 * @param etabId
	 * @param masterStructureId
	 * @return
	 */
	@Override
	public List<RefStructure> findStructuresOfMasterStructureByEtab(
			Integer etabId, Integer masterStructureId) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefStructure> c = cb.createQuery(RefStructure.class);
			Root<RefStructure> rs = c.from(RefStructure.class);
			Join<RefStructure, RefEtablissement> rsRe = rs.join("refEtablissement");
			Join<RefStructure, RefStructure> rsRs = rs.join("refStructure");
			c.select(rs).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(rsRe.get("id"), etabId);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(rsRs.get("id"), masterStructureId);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefStructure> q = entityManager.createQuery(c);
			List<RefStructure> result = q.getResultList();
			for(RefStructure entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findStructuresOfMasterStructureByEtab 'RefStructure' failed", re);
			throw re;
		}
	}
}

