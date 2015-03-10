package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefIndividuDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Service("refIndividuDaoImpl")
public class RefIndividuDaoImpl implements RefIndividuDao {

	private static final Logger log = LoggerFactory.getLogger(RefIndividuDaoImpl.class.getName());
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * persist RefIndividu instance
	 */
	@Override
	public void persist(RefIndividu transientInstance) {
		log.debug("persisting RefIndividu instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/*
	 * remove RefIndividu instance
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefIndividuDao
	 * #persiste(dz.gov.mesrs.sii.referentiel.business.model.bo.RefIndividu)
	 */
	@Override
	public void remove(RefIndividu persistentInstance) {
		log.debug("removing RefIndividu instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/*
	 * merge RefIndividu instance
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefIndividuDao
	 * #merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefIndividu)
	 */
	@Override
	public RefIndividu merge(RefIndividu detachedInstance) {
		log.debug("merging RefIndividu instance");
		try {
			RefIndividu result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * find an individu with id value
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefIndividuDao
	 * #findById(java.lang.String)
	 */
	@Override
	public RefIndividu findByIdentifiant(String id) {
		log.debug("getting List of findByIdentifiant instance with : " + id);
		try {
			StringBuilder request = new StringBuilder();
			if (id == null || id.trim().isEmpty()) {
				return null;
			}

			id = UtilConstant.quotedString(id.toLowerCase());
			request.append("SELECT r FROM RefIndividu r ");
			request.append("where lower(r.identifiant) =").append(id);
			Query query = entityManager.createQuery(new String(request));
			// query.setParameter("value", id);
			List<RefIndividu> resultList = query.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findByIdentifiant successful");
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdentifiant failed", re);
			throw re;
		}
	}

	/*
	 * get all 'individu'
	 * 
	 * @return {@link RefIndividu} collection
	 * 
	 * @see RefIndividu
	 */
	@Override
	public List<RefIndividu> findAll() {
		log.debug("getting List of RefIndividu(all) ");
		try {
			StringBuilder request = new StringBuilder("select r from RefIndividu r ");
			request.append("order by r.nomLatin ");
			Query query = entityManager.createQuery(new String(request));
			List<RefIndividu> resultList = query.getResultList();
			log.debug("RefIndividu findAll successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefIndividu findAll failed", re);
			throw re;
		}
	}

	/*
	 * get all individu with fulltext searching
	 * 
	 * @param String value
	 * 
	 * @return {@link RefIndividu} collection
	 * 
	 * @see RefIndividu
	 */
	@Override
	public List<RefIndividu> findGeneric(String value) {

		log.debug("getting List of RefIndividu instance with : " + value);
		try {
			StringBuilder request = new StringBuilder();
			if (value == null || value.trim().isEmpty()) {
				return null;
			}

			value = value.trim().toLowerCase();
			value = "%" + value + "%";

			request.append("SELECT distinct r FROM RefIndividu r ");
			request.append("Where (lower(r.identifiant) like :value ");
			request.append("OR lower(r.nomArabe) like :value ");
			request.append("OR r.nomJeuneFilleArabe like :value ");
			request.append("OR lower(r.nomJeuneFilleLatin) like :value ");
			request.append("OR lower(r.nomLatin) like :value ");
			request.append("OR r.nomMereArabe like :value ");
			request.append("OR lower(r.nomMereLatin) like :value ");
			request.append("OR r.prenomArabe like :value ");
			request.append("OR lower(r.prenomLatin) like :value ");
			request.append("OR r.prenomMereArabe like :value ");
			request.append("OR lower(r.prenomMereLatin) like :value ");
			request.append("OR r.prenomPereArabe like :value ");
			request.append("OR lower(r.prenomPereLatin) like :value ");
			request.append("OR cast(r.dateNaissance as text) like :value )");

			/**
			 * if (UtilConstant.getIdGroupe() != null) { request.append(
			 * "SELECT distinct a.refIndividu FROM RefIndividu r, RefAffectation a "
			 * ); request.append("WHERE a.refIndividu = r ");
			 * request.append(" AND a.refGroupeByGroupe.identifiant =  " +
			 * UtilConstant.quotedString(UtilConstant.getIdGroupe()));
			 * request.append(" AND a.refStructure is null ");
			 * request.append(" AND a.refGroupeByGroupeAffecte is null ");
			 * request.append(" AND a.refDocument is null ");
			 * request.append(" AND a.refEvenement is null ");
			 * request.append("AND (lower(r.identifiant) like :value "); } else
			 * if (UtilConstant.getIdStructure() != null) { request.append(
			 * "SELECT distinct a.refIndividu FROM RefIndividu r, RefAffectation a "
			 * ); request.append("WHERE a.refIndividu = r ");
			 * request.append(" AND a.refStructure.identifiant =  " +
			 * UtilConstant.quotedString(UtilConstant .getIdStructure()));
			 * request.append(" AND a.refGroupeByGroupe is null ");
			 * request.append(" AND a.refGroupeByGroupeAffecte is null ");
			 * request.append(" AND a.refDocument is null ");
			 * request.append(" AND a.refEvenement is null ");
			 * request.append("AND (lower(r.identifiant) like :value "); } else
			 * { request.append("SELECT distinct r FROM RefIndividu r ");
			 * request.append("Where (lower(r.identifiant) like :value "); }
			 * request.append("OR lower(r.nomArabe) like :value ");
			 * request.append("OR r.nomJeuneFilleArabe like :value ");
			 * request.append("OR lower(r.nomJeuneFilleLatin) like :value ");
			 * request.append("OR lower(r.nomLatin) like :value ");
			 * request.append("OR r.nomMereArabe like :value ");
			 * request.append("OR lower(r.nomMereLatin) like :value ");
			 * request.append("OR r.prenomArabe like :value ");
			 * request.append("OR lower(r.prenomLatin) like :value ");
			 * request.append("OR r.prenomMereArabe like :value ");
			 * request.append("OR lower(r.prenomMereLatin) like :value ");
			 * request.append("OR r.prenomPereArabe like :value ");
			 * request.append("OR lower(r.prenomPereLatin) like :value ");
			 * request.append("OR cast(r.dateNaissance as text) like :value )");
			 ***/

			Query query = entityManager.createQuery(new String(request));

			query.setParameter("value", value);

			List<RefIndividu> resultList = query.getResultList();

			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}

	}

	/*
	 * get all individu with advanced searching check all columns values of
	 * RefIndividu
	 * 
	 * @ RefIndividu instance
	 * 
	 * @return {@link RefIndividu} collection
	 * 
	 * @see RefIndividu
	 */
	@Override
	public List<RefIndividu> findAdvanced(RefIndividu refIndividu) {
		log.debug("findAdvanced: getting List of RefIndividu instance : ");
		if (refIndividu == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder("select r from RefIndividu r ");
			String identifiant = refIndividu.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " lower(r.identifiant) like "
						+ UtilConstant.LikeContain(identifiant.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomArabe = refIndividu.getNomArabe();
			if ((nomArabe != null) && (!nomArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomArabe) like ")
						.append(UtilConstant.LikeContain(nomArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			Date dateNaissance = refIndividu.getDateNaissance();

			if (dateNaissance != null) {
				request.append(firstOpr).append(" cast(r.dateNaissance as text) like ")
						.append(UtilConstant.LikeContain(dateNaissance));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomJeuneFilleArabe = refIndividu.getNomJeuneFilleArabe();
			if ((nomJeuneFilleArabe != null) && (!nomJeuneFilleArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomJeuneFilleArabe) like ")
						.append(UtilConstant.LikeContain(nomJeuneFilleArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomJeuneFilleLatin = refIndividu.getNomJeuneFilleLatin();
			if ((nomJeuneFilleLatin != null) && (!nomJeuneFilleLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomJeuneFilleLatin) like ")
						.append(UtilConstant.LikeContain(nomJeuneFilleLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomLatin = refIndividu.getNomLatin();
			if ((nomLatin != null) && (!nomLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomLatin) like ")
						.append(UtilConstant.LikeContain(nomLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomMereArabe = refIndividu.getNomMereArabe();
			if ((nomMereArabe != null) && (!nomMereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomMereArabe) like ")
						.append(UtilConstant.LikeContain(nomMereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomMereLatin = refIndividu.getNomMereLatin();
			if ((nomMereLatin != null) && (!nomMereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomMereLatin) like ")
						.append(UtilConstant.LikeContain(nomMereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomArabe = refIndividu.getPrenomArabe();
			if ((prenomArabe != null) && (!prenomArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomArabe) like ")
						.append(UtilConstant.LikeContain(prenomArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomLatin = refIndividu.getPrenomLatin();
			if ((prenomLatin != null) && (!prenomLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomLatin) like ")
						.append(UtilConstant.LikeContain(prenomLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomMereArabe = refIndividu.getPrenomMereArabe();
			if ((prenomMereArabe != null) && (!prenomMereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomMereArabe) like ")
						.append(UtilConstant.LikeContain(prenomMereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomMereLatin = refIndividu.getPrenomMereLatin();
			if ((prenomMereLatin != null) && (!prenomMereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomMereLatin) like ")
						.append(UtilConstant.LikeContain(prenomMereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomPereArabe = refIndividu.getPrenomPereArabe();
			if ((prenomPereArabe != null) && (!prenomPereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomPereArabe) like ")
						.append(UtilConstant.LikeContain(prenomPereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomPereLatin = refIndividu.getPrenomPereLatin();
			if ((prenomPereLatin != null) && (!prenomPereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomPereLatin) like ")
						.append(UtilConstant.LikeContain(prenomPereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			Boolean presume = refIndividu.getPresume();
			if (presume != null) {

				if (presume) {
					request.append(firstOpr).append(" r.presume = TRUE");
				} else {
					request.append(firstOpr).append(" r.presume = FALSE ");
				}
				firstOpr = UtilConstant.AND_SQL_STR;
			}
			if (request.toString().equals("select r from RefIndividu r ")) {
				return null;
			}
			log.info("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefIndividu> resultList = query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);

			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefIndividuDao#
	 * findByRefIndividu
	 * (dz.gov.mesrs.sii.referentiel.business.model.bo.RefIndividu)
	 */
	@Override
	public RefIndividu findByRefIndividu(RefIndividu refIndividu) {

		if (refIndividu == null) {
			return null;
		}

		log.debug("findByRefIndividu  identifiant = " + refIndividu.getIdentifiant() + "nom = "
				+ refIndividu.getNomLatin() + "pr�nom = " + refIndividu.getPrenomLatin());
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder("select r from RefIndividu r ");
			/*
			 * String identifiant = refIndividu.getIdentifiant(); if
			 * ((identifiant != null) && (!identifiant.trim().isEmpty())) {
			 * identifiant = UtilConstant.quotedString(identifiant.trim()
			 * .toLowerCase()); request.append(firstOpr +
			 * " lower(r.identifiant) = ").append( identifiant); firstOpr =
			 * UtilConstant.AND_SQL_STR; }
			 */

			String nomArabe = refIndividu.getNomArabe();
			if ((nomArabe != null) && (!nomArabe.trim().isEmpty())) {
				nomArabe = UtilConstant.quotedString(nomArabe.trim().toLowerCase());
				request.append(firstOpr).append(" lower(r.nomArabe) = ").append(nomArabe);
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomLatin = refIndividu.getNomLatin();
			if ((nomLatin != null) && (!nomLatin.trim().isEmpty())) {
				nomLatin = UtilConstant.quotedString(nomLatin.trim().toLowerCase());
				request.append(firstOpr).append(" lower(r.nomLatin) = ").append(nomLatin);
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomArabe = refIndividu.getPrenomArabe();
			if ((prenomArabe != null) && (!prenomArabe.trim().isEmpty())) {
				prenomArabe = UtilConstant.quotedString(prenomArabe.trim().toLowerCase());
				request.append(firstOpr).append(" lower(r.prenomArabe) = ").append(prenomArabe);
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomLatin = refIndividu.getPrenomLatin();
			if ((prenomLatin != null) && (!prenomLatin.trim().isEmpty())) {
				prenomLatin = UtilConstant.quotedString(prenomLatin.trim().toLowerCase());
				request.append(firstOpr).append(" lower(r.prenomLatin) = ").append(prenomLatin);
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefIndividu> resultList = query.getResultList();
			if (resultList != null && resultList.size() > 0) {
				return resultList.get(0);
			}
			log.debug("findByRefIndividu successful");
			return null;

		} catch (RuntimeException re) {
			log.error("findByRefIndividu failed", re);

			throw re;
		}

	}

	@Override
	public List<RefIndividu> findGeneric(String value, int max) {
		log.debug("getting List of RefIndividu instance with : " + value);
		try {
			StringBuilder request = new StringBuilder();
			if (value == null || value.trim().isEmpty()) {
				return null;
			}

			value = value.trim().toLowerCase();
			value = "%" + value + "%";

			request.append("SELECT distinct r FROM RefIndividu r ");
			request.append("Where (lower(r.identifiant) like :value ");
			request.append("OR lower(r.nomArabe) like :value ");
			request.append("OR r.nomJeuneFilleArabe like :value ");
			request.append("OR lower(r.nomJeuneFilleLatin) like :value ");
			request.append("OR lower(r.nomLatin) like :value ");
			request.append("OR r.nomMereArabe like :value ");
			request.append("OR lower(r.nomMereLatin) like :value ");
			request.append("OR r.prenomArabe like :value ");
			request.append("OR lower(r.prenomLatin) like :value ");
			request.append("OR r.prenomMereArabe like :value ");
			request.append("OR lower(r.prenomMereLatin) like :value ");
			request.append("OR r.prenomPereArabe like :value ");
			request.append("OR lower(r.prenomPereLatin) like :value ");
			request.append("OR cast(r.dateNaissance as text) like :value )");

			Query query = entityManager.createQuery(new String(request));

			query.setParameter("value", value);

			query.setFirstResult(0);
			query.setMaxResults(max);

			List<RefIndividu> resultList = query.getResultList();

			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<RefIndividu> findAdvanced(RefIndividu refIndividu, int max) {
		log.debug("findAdvanced: getting List of RefIndividu instance : ");
		if (refIndividu == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;

		try {
			StringBuilder request = new StringBuilder("select r from RefIndividu r ");
			String identifiant = refIndividu.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " lower(r.identifiant) like "
						+ UtilConstant.LikeContain(identifiant.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomArabe = refIndividu.getNomArabe();
			if ((nomArabe != null) && (!nomArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomArabe) like ")
						.append(UtilConstant.LikeContain(nomArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			Date dateNaissance = refIndividu.getDateNaissance();

			if (dateNaissance != null) {
				request.append(firstOpr).append(" cast(r.dateNaissance as text) like ")
						.append(UtilConstant.LikeContain(dateNaissance));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomJeuneFilleArabe = refIndividu.getNomJeuneFilleArabe();
			if ((nomJeuneFilleArabe != null) && (!nomJeuneFilleArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomJeuneFilleArabe) like ")
						.append(UtilConstant.LikeContain(nomJeuneFilleArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomJeuneFilleLatin = refIndividu.getNomJeuneFilleLatin();
			if ((nomJeuneFilleLatin != null) && (!nomJeuneFilleLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomJeuneFilleLatin) like ")
						.append(UtilConstant.LikeContain(nomJeuneFilleLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomLatin = refIndividu.getNomLatin();
			if ((nomLatin != null) && (!nomLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomLatin) like ")
						.append(UtilConstant.LikeContain(nomLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomMereArabe = refIndividu.getNomMereArabe();
			if ((nomMereArabe != null) && (!nomMereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomMereArabe) like ")
						.append(UtilConstant.LikeContain(nomMereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String nomMereLatin = refIndividu.getNomMereLatin();
			if ((nomMereLatin != null) && (!nomMereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nomMereLatin) like ")
						.append(UtilConstant.LikeContain(nomMereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomArabe = refIndividu.getPrenomArabe();
			if ((prenomArabe != null) && (!prenomArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomArabe) like ")
						.append(UtilConstant.LikeContain(prenomArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomLatin = refIndividu.getPrenomLatin();
			if ((prenomLatin != null) && (!prenomLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomLatin) like ")
						.append(UtilConstant.LikeContain(prenomLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomMereArabe = refIndividu.getPrenomMereArabe();
			if ((prenomMereArabe != null) && (!prenomMereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomMereArabe) like ")
						.append(UtilConstant.LikeContain(prenomMereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomMereLatin = refIndividu.getPrenomMereLatin();
			if ((prenomMereLatin != null) && (!prenomMereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomMereLatin) like ")
						.append(UtilConstant.LikeContain(prenomMereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomPereArabe = refIndividu.getPrenomPereArabe();
			if ((prenomPereArabe != null) && (!prenomPereArabe.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomPereArabe) like ")
						.append(UtilConstant.LikeContain(prenomPereArabe.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			String prenomPereLatin = refIndividu.getPrenomPereLatin();
			if ((prenomPereLatin != null) && (!prenomPereLatin.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.prenomPereLatin) like ")
						.append(UtilConstant.LikeContain(prenomPereLatin.toLowerCase()));
				firstOpr = UtilConstant.AND_SQL_STR;
			}

			Boolean presume = refIndividu.getPresume();
			if (presume != null) {

				if (presume) {
					request.append(firstOpr).append(" r.presume = TRUE");
				} else {
					request.append(firstOpr).append(" r.presume = FALSE ");
				}
				firstOpr = UtilConstant.AND_SQL_STR;
			}
			if (request.toString().equals("select r from RefIndividu r ")) {
				return null;
			}
			log.info("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			query.setFirstResult(0);
			query.setMaxResults(max);
			List<RefIndividu> resultList = query.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);

			throw re;
		}
	}

	@Override
	public List<RefIndividu> findByNames(String name, String surname) {
		log.debug("getting List of RefIndividu instance with name = " + name + " and surname = " + surname);
		try {
			StringBuilder request = new StringBuilder();
			if (name == null || name.trim().isEmpty() || surname == null || surname.trim().isEmpty()) {
				return null;
			}

			name = name.trim().toLowerCase();
			name = "%" + name + "%";
			surname = surname.trim().toLowerCase();
			surname = "%" + surname + "%";
			request.append("SELECT distinct r FROM RefIndividu r ");
			request.append("Where (lower(r.nomArabe) like :name ");
			request.append("AND lower(r.prenomArabe) like :surname ");
			request.append("OR (lower(r.nomLatin) like :name ");
			request.append("AND lower(r.prenomLatin) like :surname)) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("name", name);
			query.setParameter("surname", surname);
			List<RefIndividu> resultList = query.getResultList();

			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}

	}

	@Override
	public List<RefIndividu> findByNames(String name, String surname, int max) {
		log.debug("getting List of RefIndividu instance with name = " + name + " and surname = " + surname);
		try {
			StringBuilder request = new StringBuilder();
			if (name == null || name.trim().isEmpty() || surname == null || surname.trim().isEmpty()) {
				return null;
			}

			name = name.trim().toLowerCase();
			name = "%" + name + "%";
			surname = surname.trim().toLowerCase();
			surname = "%" + surname + "%";
			request.append("SELECT distinct r FROM RefIndividu r ");
			request.append("Where (lower(r.nomArabe) like :name ");
			request.append("AND lower(r.prenomArabe) like :surname ");
			request.append("OR (lower(r.nomLatin) like :name ");
			request.append("AND lower(r.prenomLatin) like :surname)) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("name", name);
			query.setParameter("surname", surname);
			query.setFirstResult(0);
			query.setMaxResults(max);
			List<RefIndividu> resultList = query.getResultList();

			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}

	}

	@Override
	public RefIndividu findById(Integer id) {
		log.debug("getting RefAdresse instance with id: " + id);
		try {
			RefIndividu instance = entityManager.find(RefIndividu.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public boolean checkIfExist(RefIndividu refIndividu) {

		log.debug("checkIfExist RefIndividuDto : " + refIndividu.getId());
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefIndividu> c = cb.createQuery(RefIndividu.class);
			Root<RefIndividu> ind = c.from(RefIndividu.class);
			c.select(ind).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ind.get("identifiant"), refIndividu.getIdentifiant());
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefIndividu> q = entityManager.createQuery(c);

			List<RefIndividu> _resultList = q.getResultList();

			if (_resultList != null && !_resultList.isEmpty()) {
				return true;
			}
			log.debug("checkIfExist RefIndividu successful");
			return false;

		} catch (RuntimeException re) {
			log.error("checkIfExist RefIndividu failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RefIndividu> findByExample(RefIndividu example, String sortField, Integer pageSize, Integer first,
			Boolean descending) {
		Criteria criteria = getHibernateCriteria(example);
		if (sortField != null) {
			if (Boolean.TRUE.equals(descending)) {
				criteria.addOrder(Order.desc(sortField));
			} else {
				criteria.addOrder(Order.asc(sortField));
			}

		}
		if (pageSize != null) {
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(first);
		}
		return criteria.list();
	}

	@Override
	public int countByExample(RefIndividu example) {
		Criteria criteria = getHibernateCriteria(example);
		return ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	private Criteria getHibernateCriteria(RefIndividu example) {
		HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		Session session = hem.getSession();
		Criteria criteria = session.createCriteria(RefIndividu.class);
		if (!StringUtils.isEmpty(example.getNomLatin())) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("nomLatin", example.getNomLatin(), MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("nomArabe", example.getNomLatin(), MatchMode.ANYWHERE)));
		}
		if (!StringUtils.isEmpty(example.getPrenomLatin())) {
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("prenomLatin", example.getPrenomLatin(), MatchMode.ANYWHERE).ignoreCase())
					.add(Restrictions.like("prenomArabe", example.getPrenomLatin(), MatchMode.ANYWHERE)));
		}
		if (!StringUtils.isEmpty(example.getPrenomArabe())) {
			criteria.add(Restrictions.like("prenomArabe", example.getPrenomArabe(), MatchMode.ANYWHERE));
		}
		if (!StringUtils.isEmpty(example.getIdentifiant())) {
			criteria.add(Restrictions.like("identifiant", example.getIdentifiant(), MatchMode.ANYWHERE));
		}
		Nomenclature nomenclatureCivilite = example.getNomenclatureByCivilite();
		if (nomenclatureCivilite != null) {
			criteria.createAlias("nomenclatureByCivilite", "nomenclatureByCivilite");
			if (!StringUtils.isEmpty(nomenclatureCivilite.getCode())) {
				criteria.add(Restrictions.eq("nomenclatureByCivilite.code", nomenclatureCivilite.getCode()));
			}
		}

		return criteria;
	}

	@Override
	public List<RefIndividu> findByNameOrSurname(String query) {
		try {
			StringBuilder request = new StringBuilder();
			if (query == null || query.trim().isEmpty() ) {
				return null;
			}

			query = query.trim().toLowerCase();
			query = "%" + query + "%";
			
			request.append("SELECT distinct r FROM RefIndividu r ");
			request.append("Where (lower(r.nomArabe) like :query ");
			request.append("OR lower(r.prenomArabe) like :query ");
			request.append("OR (lower(r.nomLatin) like :query ");
			request.append("OR lower(r.prenomLatin) like :query)) ");
			Query querySql = entityManager.createQuery(new String(request));
			querySql.setParameter("query", query);
			
			List<RefIndividu> resultList = querySql.getResultList();

			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}
}
