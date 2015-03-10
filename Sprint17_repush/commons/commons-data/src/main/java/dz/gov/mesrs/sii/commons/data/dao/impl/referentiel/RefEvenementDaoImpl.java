package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEvenementDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEvenement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefEvenement.
 * 
 * @see dz.gov.mesrs.sii.RefEvenementDto.business.model.bo.RefEvenement
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("refEvenementDaoImpl")
public class RefEvenementDaoImpl implements RefEvenementDao {

	private static final Logger log = LoggerFactory.getLogger(RefEvenementDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.RefEvenementService.business.persistence.jpa.impl.RefEvenementDao#persist(dz.gov.mesrs.sii.RefEvenementDto.business.model.bo.RefEvenement)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefEvenement transientInstance) {
		log.debug("persisting RefEvenement instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.RefEvenementService.business.persistence.jpa.impl.RefEvenementDao#remove(dz.gov.mesrs.sii.RefEvenementDto.business.model.bo.RefEvenement)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefEvenement persistentInstance) {
		log.debug("removing RefEvenement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.RefEvenementService.business.persistence.jpa.impl.RefEvenementDao#merge(dz.gov.mesrs.sii.RefEvenementDto.business.model.bo.RefEvenement)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefEvenement merge(RefEvenement detachedInstance) {
		log.debug("merging RefEvenement instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefEvenementDao#findById(int)
	 * @param id
	 */
	@Override
	public RefEvenement findById(int id) {
		log.debug("getting RefEvenement instance with id: " + id);
		try {
			log.debug("get successful");
			RefEvenement instance = entityManager.find(RefEvenement.class, id);
			log.debug("get successful");
			return instance;
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
	@Transactional(readOnly = true)
	public List<RefEvenement> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefEvenement> findAll() {
		log.debug("getting all RefEvenement instances");
		try {
			Query query = entityManager
					.createQuery("from RefEvenement refEvenement");
			log.debug("findAll 'RefEvenement' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefEvenement' failed", re);
			return new ArrayList<RefEvenement>();
		}
	}

	@Override
	public List<RefEvenement> findGeneric(String value) {

		log.debug("getting List of RefEvenement instance with : " + value);
		List<RefEvenement> resultList = new ArrayList();
		try {
			int id;
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException formatException) {
				id = 0;
			}
			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value + "%";
				StringBuilder request = new StringBuilder(
						"select r from RefEvenement r ");
				request.append("where r.id = :id ");
				request.append("or r.objet like :value ");
				request.append("or r.description like :value ");
				request.append("or r.observation like :value ");
				request.append("or cast(r.dateDebut as text) like :value ");
				request.append("or cast(r.dateFin as text) like :value ");
				Query query = entityManager.createQuery(new String(request));
				query.setParameter("id", id);
				query.setParameter("value", value);
				resultList = (List<RefEvenement>) query.getResultList();
				log.debug("findGeneric successful");
				return resultList;
			}

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefEvenement> findAdvanced(RefEvenement refEvenement) {
		List<RefEvenement> resultList = new ArrayList();
		log.debug("findAdvanced: getting List of refEvenement instance : ");

		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			if (refEvenement != null
					&& (((refEvenement.getObjet() != null) && (!refEvenement
							.getObjet().trim().isEmpty()))
							|| ((refEvenement.getDateDebut() != null))
							|| ((refEvenement.getDescription() != null) && (!refEvenement
									.getDescription().trim().isEmpty())) || (refEvenement
							.getNomenclatureByType() != null))) {
				StringBuilder request = new StringBuilder(
						"select r from RefEvenement r ");

				if ((refEvenement.getObjet() != null)
						&& (!refEvenement.getObjet().trim().isEmpty())) {
					request.append(firstOpr)
							.append(" r.objet like ")
							.append(UtilConstant.LikeContain(refEvenement
									.getObjet()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refEvenement.getDateDebut() != null) {
					request.append(firstOpr)
							.append(" r.dateDebut = ")
							.append(UtilConstant.quotedString(refEvenement
									.getDateDebut().toString()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if ((refEvenement.getDescription() != null)
						&& (!refEvenement.getDescription().trim().isEmpty())) {
					request.append(firstOpr)
							.append(" r.description like ")
							.append(UtilConstant.LikeContain(refEvenement
									.getDescription()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refEvenement.getNomenclatureByType() != null) {
					int typeEvenementId = refEvenement.getNomenclatureByType()
							.getId();
					request.append(firstOpr)
							.append(" r.nomenclatureByType.id = ")
							.append(typeEvenementId);
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				log.debug("request successful " + new String(request));
				Query query = entityManager.createQuery(new String(request));
				resultList = (List<RefEvenement>) query.getResultList();
				log.debug("findAdvanced successful");
				return resultList;
			}
		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefEvenement> findAll(String etabLcLatin) {
		log.debug("getting all RefEvenement instances");
		try {
			Query query = entityManager.createQuery("from RefEvenement r "
					+ " where r.refEtablissement.lcEtablissementLatin = "
					+ UtilConstant.quotedString(etabLcLatin));
			log.debug("findAll 'RefEvenement' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefEvenement' failed", re);
			return new ArrayList<RefEvenement>();
		}
	}

	@Override
	public List<RefEvenement> findGeneric(Integer etabId, String value) {
		log.debug("getting List of RefEvenement instance with : " + value);
		List<RefEvenement> resultList = new ArrayList();
		try {
			int id;
			try {
				id = Integer.parseInt(value);
			} catch (NumberFormatException formatException) {
				id = 0;
			}
			if (value != null) {
				value = "%" + value + "%";
				StringBuilder request = new StringBuilder(
						"select r from RefEvenement r ");
				request.append(" where (1=1)");
				if (etabId != null) {
					request.append(" and r.refEtablissement.id = " + etabId);
				}
				request.append(" and (r.id = :id ");
				request.append("or r.objet like :value ");
				request.append("or r.description like :value ");
				request.append("or r.observation like :value ");
				request.append("or cast(r.dateDebut as text) like :value ");
				request.append("or cast(r.dateFin as text) like :value) ");
				Query query = entityManager.createQuery(new String(request));
				query.setParameter("id", id);
				query.setParameter("value", value);
				resultList = (List<RefEvenement>) query.getResultList();
				log.debug("findGeneric successful");
				return resultList;
			}

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefEvenement> findAdvanced(Integer etabId,
			RefEvenement refEvenement) {
		List<RefEvenement> resultList = new ArrayList();
		log.debug("findAdvanced: getting List of refEvenement instance : ");

		String firstOpr = UtilConstant.AND_SQL_STR;

		try {
			if (refEvenement != null
					&& (((refEvenement.getObjet() != null) && (!refEvenement
							.getObjet().trim().isEmpty()))
							|| ((refEvenement.getDateDebut() != null))
							|| ((refEvenement.getDescription() != null) && (!refEvenement
									.getDescription().trim().isEmpty())) || (refEvenement
							.getNomenclatureByType() != null))) {
				StringBuilder request = new StringBuilder(
						"select r from RefEvenement r ");
				request.append(" where r.refEtablissement.id = " + etabId);

				if ((refEvenement.getObjet() != null)
						&& (!refEvenement.getObjet().trim().isEmpty())) {
					request.append(firstOpr)
							.append(" r.objet like ")
							.append(UtilConstant.LikeContain(refEvenement
									.getObjet()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refEvenement.getDateDebut() != null) {
					request.append(firstOpr)
							.append(" r.dateDebut = ")
							.append(UtilConstant.quotedString(refEvenement
									.getDateDebut().toString()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if ((refEvenement.getDescription() != null)
						&& (!refEvenement.getDescription().trim().isEmpty())) {
					request.append(firstOpr)
							.append(" r.description like ")
							.append(UtilConstant.LikeContain(refEvenement
									.getDescription()));
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				if (refEvenement.getNomenclatureByType() != null) {
					int typeEvenementId = refEvenement.getNomenclatureByType()
							.getId();
					request.append(firstOpr)
							.append(" r.nomenclatureByType.id = ")
							.append(typeEvenementId);
					firstOpr = UtilConstant.OR_SQL_STR;
				}

				log.debug("request successful " + new String(request));
				Query query = entityManager.createQuery(new String(request));
				resultList = (List<RefEvenement>) query.getResultList();
				log.debug("findAdvanced successful");
				return resultList;
			}
		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefEvenement> findByCodeType(Integer etabId, String codeType,
			Integer year) {
		List<RefEvenement> resultList = new ArrayList<RefEvenement>();
		log.debug("findByCodeType: getting List of refEvenement instance : ");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEvenement r ");
			request.append(" where (1=1) ");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = ").append(etabId);
			}

			if (codeType != null) {
				request.append(" and lower(r.nomenclatureByType.code) = ")
						.append(UtilConstant.quotedString(codeType
								.toLowerCase()));
			}

			if (year != null) {
				request.append(" and to_char(r.dateDebut,'YYYY') = ").append(
						UtilConstant.quotedString(year.toString()));
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			resultList = (List<RefEvenement>) query.getResultList();
			log.debug("findByCodeType successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByCodeType failed", re);
			throw re;
		}

	}
}
