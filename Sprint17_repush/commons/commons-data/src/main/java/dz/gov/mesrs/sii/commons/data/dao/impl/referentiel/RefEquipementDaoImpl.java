package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository("refEquipementDaoImpl")
public class RefEquipementDaoImpl implements RefEquipementDao {

	private static final Logger log = LoggerFactory.getLogger(RefEquipementDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void persist(RefEquipement transientInstance) {
		log.info("dao saveEquipement");
		log.debug("persisting RefEquipement instance");
		try {
			entityManager.persist(transientInstance);
			log.info("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void remove(RefEquipement persistentInstance) {
		log.debug("removing RefEquipement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	public RefEquipement merge(RefEquipement detachedInstance) {
		log.debug("merging RefEquipement instance");
		try {
			RefEquipement result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public RefEquipement findById(String id) {
		log.debug("getting RefEquipement instance with id: " + id);
		try {
			RefEquipement instance = entityManager
					.find(RefEquipement.class, 2);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefEquipement> findAll() {
		log.debug("getting List of RefEquipement(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			request.append("order by r.designation ");
			Query query = entityManager.createQuery(new String(request));
			List<RefEquipement> resultList = (List<RefEquipement>) query
					.getResultList();
			log.debug("RefEquipement findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefEquipement findAll failed", re);
			throw re;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefEquipement> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefEquipement instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			request.append(" where r.identifiant like :value ");
			request.append(" or r.designation like :value ");
			request.append(" or r.code like :value ");
			request.append(" or r.referenceexterne like :value ");
			request.append(" or cast(r.dateMiseDispo as text) like :value ");

			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefEquipement> resultList = (List<RefEquipement>) query
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
	public List<RefEquipement> findAdvanced(RefEquipement refEquipement) {
		log.debug("findAdvanced: getting List of refEquipement instance : ");
		if (refEquipement == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			String identifiant = refEquipement.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String code = refEquipement.getCode();
			if ((code != null) && (!code.trim().isEmpty())) {
				request.append(firstOpr).append(" r.code like ")
						.append(UtilConstant.LikeContain(code));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designation = refEquipement.getDesignation();
			if ((designation != null) && (!designation.trim().isEmpty())) {
				request.append(firstOpr).append(" r.designation like ")
						.append(UtilConstant.LikeContain(designation));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String referenceexterne = refEquipement.getReferenceexterne();
			if ((referenceexterne != null)
					&& (!referenceexterne.trim().isEmpty())) {
				request.append(firstOpr).append(" r.referenceexterne like ")
						.append(UtilConstant.LikeContain(referenceexterne));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			Date dateMiseDispo = (Date) refEquipement.getDateMiseDispo();
			if (dateMiseDispo != null) {
				request.append(firstOpr
						+ " cast(r.dateMiseDispo as text) like "
						+ UtilConstant.LikeContain(dateMiseDispo));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refEquipement.getNomenclatureByFamilleEquipement() != null) {
				int familleequipementId = refEquipement
						.getNomenclatureByFamilleEquipement().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureByfamilleequipementId.id = ")
						.append(familleequipementId);
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refEquipement.getNomenclatureBySousfamilleEquipement() != null) {
				int sousfamilleequipementId = refEquipement
						.getNomenclatureBySousfamilleEquipement().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureBysousfamilleequipementId.id = ")
						.append(sousfamilleequipementId);
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefEquipement> resultList = (List<RefEquipement>) query
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
	public List<RefEquipement> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefEquipement> findAll(Integer etabId) {
		log.debug("getting List of RefEquipement(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			request.append(" order by r.designation ");
			Query query = entityManager.createQuery(new String(request));
			List<RefEquipement> resultList = (List<RefEquipement>) query
					.getResultList();
			log.debug("RefEquipement findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefEquipement findAll failed", re);
			throw re;
		}
	}

	@Override
	public List<RefEquipement> findGeneric(Integer etabId, String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefEquipement instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			request.append(" where (1=1)");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			request.append(" and (r.identifiant like :value ");
			request.append(" or r.designation like :value ");
			request.append(" or r.code like :value ");
			request.append(" or r.referenceexterne like :value ");
			request.append(" or cast(r.dateMiseDispo as text) like :value) ");

			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefEquipement> resultList = (List<RefEquipement>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefEquipement> findAdvanced(Integer etabId,
			RefEquipement refEquipement) {
		log.debug("findAdvanced: getting List of refEquipement instance : ");
		if (refEquipement == null) {
			return null;
		}
		String firstOpr = UtilConstant.AND_SQL_STR;

		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEquipement r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			String identifiant = refEquipement.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like "
						+ UtilConstant.LikeContain(identifiant));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String code = refEquipement.getCode();
			if ((code != null) && (!code.trim().isEmpty())) {
				request.append(firstOpr).append(" r.code like ")
						.append(UtilConstant.LikeContain(code));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designation = refEquipement.getDesignation();
			if ((designation != null) && (!designation.trim().isEmpty())) {
				request.append(firstOpr).append(" r.designation like ")
						.append(UtilConstant.LikeContain(designation));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			String referenceexterne = refEquipement.getReferenceexterne();
			if ((referenceexterne != null)
					&& (!referenceexterne.trim().isEmpty())) {
				request.append(firstOpr).append(" r.referenceexterne like ")
						.append(UtilConstant.LikeContain(referenceexterne));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			Date dateMiseDispo = (Date) refEquipement.getDateMiseDispo();
			if (dateMiseDispo != null) {
				request.append(firstOpr
						+ " cast(r.dateMiseDispo as text) like "
						+ UtilConstant.LikeContain(dateMiseDispo));
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refEquipement.getNomenclatureByFamilleEquipement() != null) {
				int familleequipementId = refEquipement
						.getNomenclatureByFamilleEquipement().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureByfamilleequipementId.id = ")
						.append(familleequipementId);
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			if (refEquipement.getNomenclatureBySousfamilleEquipement() != null) {
				int sousfamilleequipementId = refEquipement
						.getNomenclatureBySousfamilleEquipement().getId();
				request.append(firstOpr)
						.append(" r.nomenclatureBysousfamilleequipementId.id = ")
						.append(sousfamilleequipementId);
				// firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefEquipement> resultList = (List<RefEquipement>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	}
}
