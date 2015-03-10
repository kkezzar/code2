package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefLieuDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository("refLieuDaoImpl")
public class RefLieuDaoImpl implements RefLieuDao {

	private static final Logger log = LoggerFactory.getLogger(RefLieuDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public void persist(RefLieu transientInstance) {
		log.info("dao saveLieu");
		log.debug("persisting RefLieu instance");
		try {
			entityManager.persist(transientInstance);
			log.info("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}


	@Override
	public void remove(RefLieu persistentInstance) {
		log.debug("removing RefLieu instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	
	@Override
	public RefLieu merge(RefLieu detachedInstance) {
		log.debug("merging RefLieu instance");
		try {
			RefLieu result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public RefLieu findById(int id) {
		log.debug("getting RefLieu instance with id: " + id);
		try {
			RefLieu instance = entityManager.find(RefLieu.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefLieu> findAll() {
		log.debug("getting List of RefLieu(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			request.append("order by r.designation ");
			Query query = entityManager.createQuery(new String(request));
			List<RefLieu> resultList = (List<RefLieu>) query
					.getResultList();
			log.debug("RefLieu findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefLieu findAll failed", re);
			throw re;
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefLieu> findGeneric(String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefLieu instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			request.append("left join r.nomenclatureByTypeLieu as ncTypeLieu left join r.nomenclatureByTypeImplantation as ncTypeImplantation left join r.nomenclatureByTypeacces as ncTypeAcces  left join r.nomenclatureByTypesalle as ncTypeSalle");
			
			request.append(" where r.identifiant like :value ");
			request.append(" or r.designation like :value ");
			request.append(" or r.designationArabe like :value ");
			request.append(" or r.description like :value ");
			request.append(" or r.descriptionArabe like :value ");
			request.append(" or r.codePorte like :value ");
			request.append(" or r.superficie like :value ");
			request.append(" or r.capacite like :value ");
			request.append(" or r.etage like :value ");
			request.append(" or r.nomenclatureByTypeLieu.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypeImplantation.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypeacces.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypesalle.libelleLongFr like :value ");
			
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefLieu> resultList = (List<RefLieu>) query
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
	public List<RefLieu> findAdvanced(RefLieu refLieu) {
		log.debug("findAdvanced: getting List of refLieu instance : ");
		if (refLieu == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;
	
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			String identifiant = refLieu.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like " + UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designation = refLieu.getDesignation();
			if ((designation != null) && (!designation.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.designation like ").append(UtilConstant.LikeContain(designation));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designationArabe = refLieu.getDesignationArabe();
			if ((designationArabe != null) && (!designationArabe.trim().isEmpty())) {
				request.append(firstOpr).append( " r.designationArabe like ").append( UtilConstant.LikeContain(designationArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			String description = refLieu.getDescription();
			if ((description != null) && (!description.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.description like ").append(UtilConstant.LikeContain(description));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String descriptionArabe = refLieu.getDescriptionArabe();
			if ((descriptionArabe != null) && (!descriptionArabe.trim().isEmpty())) {
				request.append(firstOpr).append( " r.descriptionArabe like ").append( UtilConstant.LikeContain(descriptionArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			if (refLieu.getNomenclatureByTypeLieu() != null) {
				int typeLieuId = refLieu.getNomenclatureByTypeLieu().getId();
				request.append(firstOpr).append( " r.nomenclatureByTypeLieu.id = " ).append(typeLieuId);
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefLieu> resultList = (List<RefLieu>) query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	
	}


	@Override
	public List<RefLieu> findAll(Integer etabId) {
		log.debug("getting List of RefLieu(all) ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			request.append(" order by r.designation ");
			Query query = entityManager.createQuery(new String(request));
			List<RefLieu> resultList = (List<RefLieu>) query
					.getResultList();
			log.debug("RefLieu findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefLieu findAll failed", re);
			throw re;
		}
	}


	@Override
	public List<RefLieu> findGeneric(Integer etabId, String value) {
		value = "%" + value + "%";
		log.debug("getting List of RefLieu instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			request.append("left join r.nomenclatureByTypeLieu as ncTypeLieu left join r.nomenclatureByTypeImplantation as ncTypeImplantation left join r.nomenclatureByTypeacces as ncTypeAcces  left join r.nomenclatureByTypesalle as ncTypeSalle");
			request.append(" where (1=1)");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			request.append(" and( r.identifiant like :value ");
			request.append(" or r.designation like :value ");
			request.append(" or r.designationArabe like :value ");
			request.append(" or r.description like :value ");
			request.append(" or r.descriptionArabe like :value ");
			request.append(" or r.codePorte like :value ");
			request.append(" or r.superficie like :value ");
			request.append(" or r.capacite like :value ");
			request.append(" or r.etage like :value ");
			request.append(" or r.nomenclatureByTypeLieu.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypeImplantation.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypeacces.libelleLongFr like :value ");
			request.append(" or r.nomenclatureByTypesalle.libelleLongFr like :value) ");
			
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefLieu> resultList = (List<RefLieu>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}


	@Override
	public List<RefLieu> findAdvanced(Integer etabId, RefLieu refLieu) {
		log.debug("findAdvanced: getting List of refLieu instance : ");
		if (refLieu == null || etabId == null) {
			return null;
		}
		String firstOpr = UtilConstant.AND_SQL_STR;
	
		try {
			StringBuilder request = new StringBuilder("select r from RefLieu r ");
			request.append(" where r.refEtablissement.id = " + etabId);
			String identifiant = refLieu.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " r.identifiant like " + UtilConstant.LikeContain(identifiant));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designation = refLieu.getDesignation();
			if ((designation != null) && (!designation.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.designation like ").append(UtilConstant.LikeContain(designation));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			String designationArabe = refLieu.getDesignationArabe();
			if ((designationArabe != null) && (!designationArabe.trim().isEmpty())) {
				request.append(firstOpr).append( " r.designationArabe like ").append( UtilConstant.LikeContain(designationArabe));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			String description = refLieu.getDescription();
			if ((description != null) && (!description.trim().isEmpty())) {
				request.append(firstOpr ).append( " r.description like ").append(UtilConstant.LikeContain(description));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			String descriptionArabe = refLieu.getDescriptionArabe();
			if ((descriptionArabe != null) && (!descriptionArabe.trim().isEmpty())) {
				request.append(firstOpr).append( " r.descriptionArabe like ").append( UtilConstant.LikeContain(descriptionArabe));
				//firstOpr = UtilConstant.OR_SQL_STR;
			}
			
			if (refLieu.getNomenclatureByTypeLieu() != null) {
				int typeLieuId = refLieu.getNomenclatureByTypeLieu().getId();
				request.append(firstOpr).append( " r.nomenclatureByTypeLieu.id = " ).append(typeLieuId);
				//firstOpr = UtilConstant.OR_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefLieu> resultList = (List<RefLieu>) query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	}

 
	@Override
	public RefLieu findByCode(String code) {
		
		if (code == null ) {
			return null;
		}
		try {
			StringBuilder request = new StringBuilder("select r from RefLieu r ");
			request.append(" where r.identifiant = :code");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("code", code);
			List<RefLieu> resultList = (List<RefLieu>) query.getResultList();
			if(resultList==null || resultList.isEmpty()){
				return null;
			}else {
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			
			throw re;
		}
	}


	@Override
	public List<RefLieu> findSalleAndAmphi(Integer etabId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefLieu r ");
			request.append("where (r.nomenclatureByTypeLieu.code=:codeSale or r.nomenclatureByTypeLieu.code=:codeAmphi )");
			
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			
			
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("codeSale", "Sal");
			query.setParameter("codeAmphi", "AMPHI");
			List<RefLieu> resultList = (List<RefLieu>) query
					.getResultList();
			
			return resultList;

		} catch (RuntimeException re) {
			log.error("findSalleAndAmphi failed", re);
			throw re;
		}
	}
}
