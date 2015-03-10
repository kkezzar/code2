package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RepartitionUeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.GroupeRepartitionUeAChoix;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RepartitionUe;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Repository("repartitionUeDao")
@Transactional
public class RepartitionUeDaoImpl implements RepartitionUeDao {

	private static final Logger log = LoggerFactory.getLogger(RepartitionUeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RepartitionUe transientInstance) {
		log.debug("persisting RepartitionUe instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persisting RepartitionUe : succés");

		} catch (RuntimeException re) {
			log.error("persisting RepartitionUe :echec", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RepartitionUe merge(RepartitionUe detachedInstance) {

		log.debug("merging RepartitionUe instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);

		} catch (RuntimeException re) {
			log.error("merge RepartitionUe failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(int id) {
		log.debug("removing RepartitionUe instance id : " + id);
		try {
			RepartitionUe persistentInstance = entityManager.find(
					RepartitionUe.class, id);
			entityManager.remove(persistentInstance);
			log.debug("remove RepartitionUe successful");

		} catch (RuntimeException re) {

			log.error("remove RepartitionUe failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(int parcoursId, String refCodeSemestre, int ueId) {
		log.debug("Supreesion de repartition avec id id");
		try {
			List<RepartitionUe> repartitions = find(refCodeSemestre,
					parcoursId, ueId);
			if (repartitions.size() == 1)
				entityManager.remove(repartitions.get(0));
			log.debug("Supreesion de repartition avec id : Avec succ�s");
		} catch (RuntimeException re) {
			log.error("Supreesion de repartition avec id : Avec echec", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public RepartitionUe findById(int id) {
		log.debug("getting RepartitionUe instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RepartitionUe.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RepartitionUe> find(String refCodeSemestre, Integer parcoursId,
			Integer ueId) {
		log.debug("Recherche multi critétes des repartition des UEs dans des parcours");
		try {
			StringBuilder sb = new StringBuilder();

			sb.append("select repartition from RepartitionUe repartition ");
			if (parcoursId != null)
				sb.append("join repartition.parcoursType parcours ");
			if (ueId != null)
				sb.append("join repartition.uniteEnseignement ue ");

			sb.append("where (1=1) ");

			if (refCodeSemestre != null)
				sb.append("and repartition.refCodeSemestre = :refCodeSemestre ");
			if (parcoursId != null)
				sb.append("and parcours.id = :parcoursId ");
			if (ueId != null)
				sb.append("and ue.id = :ueId ");

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des param�tres
			if (refCodeSemestre != null)
				query.setParameter("refCodeSemestre", refCodeSemestre);
			if (parcoursId != null)
				query.setParameter("parcoursId", parcoursId);
			if (ueId != null)
				query.setParameter("ueId", ueId);

			// Ex�cution de la requete.
			List<RepartitionUe> _repartitionList = query.getResultList();

			return _repartitionList;

		} catch (RuntimeException re) {
			log.error("findAll 'RepartitionUe' failed", re);
			return new ArrayList<RepartitionUe>();
		}
	}

	@Override
	public List<UniteEnseignement> findUniteEnseignement(int parcoursId,
			String refCodeSemestre) {
		if (parcoursId == 0 || refCodeSemestre == null) {
			return null;
		}
		log.debug("findUniteEnseignement with  offId: " + parcoursId
				+ " and refCodeSemestre = " + refCodeSemestre);
		refCodeSemestre = refCodeSemestre.toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select r.uniteEnseignement from RepartitionUe r");
			request.append(" where r.parcoursType.id =:parcoursId ");
			request.append(" and lower(r.refCodeSemestre) =:refCodeSemestre ");
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					new String(request), UniteEnseignement.class);
			query.setParameter("parcoursId", parcoursId);
			query.setParameter("refCodeSemestre", refCodeSemestre);
			List<UniteEnseignement> resultList = (List<UniteEnseignement>) query
					.getResultList();
			log.debug("findUniteEnseignement successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findUniteEnseignement failed", re);
			throw re;
		}
	}

	@Override
	public List<UniteEnseignement> findUeOof(int oofId, String refCodeSemestre) {
		if (oofId == 0 || refCodeSemestre == null) {
			return null;
		}
		log.debug("findUniteEnseignement with  offId: " + oofId
				+ " and refCodeSemestre = " + refCodeSemestre);
		refCodeSemestre = refCodeSemestre.toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r.uniteEnseignement from RepartitionUe r");

			// request.append(" and  r.parcoursType.offreFormation.id IN ");
			request.append(" where  r.parcoursType.offreFormation.id IN ");
			request.append(" (SELECT oof.offreFormation.id FROM OuvertureOffreFormation oof WHERE oof.id =:oofId");
			request.append(" and oof.estOuverte = TRUE) ");
			request.append(" and lower(r.refCodeSemestre) =:refCodeSemestre ");
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					new String(request), UniteEnseignement.class);
			query.setParameter("oofId", oofId);
			query.setParameter("refCodeSemestre", refCodeSemestre);
			List<UniteEnseignement> resultList = (List<UniteEnseignement>) query
					.getResultList();
			log.debug("findUeOof successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findUeOof failed", re);
			throw re;
		}
	}

	@Override
	public List<RepartitionUe> findByParcours(int parcoursId) {
		if (parcoursId == 0) {
			return null;
		}
		log.debug("findUniteEnseignement with  parcoursId: " + parcoursId);

		try {
			StringBuilder request = new StringBuilder(
					"select r from RepartitionUe r");
			request.append(" where r.parcoursType.id =:parcoursId ");

			TypedQuery<RepartitionUe> query = entityManager.createQuery(
					new String(request), RepartitionUe.class);
			query.setParameter("parcoursId", parcoursId);
			List<RepartitionUe> resultList = (List<RepartitionUe>) query
					.getResultList();
			log.debug("findByParcours successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByParcours failed", re);
			throw re;
		}
	}

	@Override
	public List<UniteEnseignement> findUeOof(int oofId, Integer periodeId) {
		if (oofId == 0 || periodeId == null) {
			return null;
		}
		log.debug("findUniteEnseignement with  offId: " + oofId
				+ " and periodeId = " + periodeId);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r.uniteEnseignement from RepartitionUe r");

			request.append(" where  r.parcoursType.offreFormation.id IN ");
			request.append(" (SELECT oof.offreFormation.id FROM OuvertureOffreFormation oof WHERE oof.id =:oofId");
			request.append(" and oof.estOuverte = TRUE) ");
			request.append(" and r.periode.id =:periodeId ");
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					new String(request), UniteEnseignement.class);
			query.setParameter("oofId", oofId);
			query.setParameter("periodeId", periodeId);
			List<UniteEnseignement> resultList = (List<UniteEnseignement>) query
					.getResultList();
			log.debug("findUeOof successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findUeOof failed", re);
			throw re;
		}
	}

	@Override
	public List<RepartitionUe> findByParcoursAndPeriode(int parcoursId,
			int idPeriode) {
		if (parcoursId == 0 || idPeriode == 0) {
			return null;
		}

		try {
			StringBuilder request = new StringBuilder(
					"select r from RepartitionUe r");
			request.append(" where r.parcoursType.id =:parcoursId and  r.periode.id =:idPeriode");

			TypedQuery<RepartitionUe> query = entityManager.createQuery(
					new String(request), RepartitionUe.class);
			query.setParameter("parcoursId", parcoursId);
			query.setParameter("idPeriode", idPeriode);
			List<RepartitionUe> resultList = (List<RepartitionUe>) query
					.getResultList();
			log.debug("findByParcoursAndPeriode successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByParcoursAndPeriode failed", re);
			throw re;
		}
	}

	@Override
	public List<UniteEnseignement> findUeOoffindUeOofAndPeriode(int oofId,
			int idPeriode) {
		if (oofId == 0 || idPeriode == 0) {
			return null;
		}
		log.debug("findUniteEnseignement with  offId: " + oofId
				+ " and periode = " + idPeriode);

		try {
			StringBuilder request = new StringBuilder(
					"select distinct r.uniteEnseignement from RepartitionUe r");
			request.append(" where  r.parcoursType.offreFormation.id IN ");
			request.append(" (SELECT oof.offreFormation.id FROM OuvertureOffreFormation oof WHERE oof.id =:oofId");
			request.append(" and oof.estOuverte = TRUE) ");

			request.append(" and r.periode.id =:idPeriode ");
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					new String(request), UniteEnseignement.class);
			query.setParameter("oofId", oofId);
			query.setParameter("idPeriode", idPeriode);
			List<UniteEnseignement> resultList = (List<UniteEnseignement>) query
					.getResultList();
			log.debug("findUeOof successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findUeOof failed", re);
			throw re;
		}
	}

	@Override
	public GroupeRepartitionUeAChoix findGroupeRepartitionUeAChoixById(
			int IdGroupe) {

		log.debug("getting findGroupeRepartitionUeAChoix instance with id: "
				+ IdGroupe);
		try {
			log.debug("get successful");
			return entityManager
					.find(GroupeRepartitionUeAChoix.class, IdGroupe);
		} catch (RuntimeException re) {
			log.error("get findGroupeRepartitionUeAChoixById failed", re);
			throw re;
		}
	}

	@Override
	public List<UniteEnseignement> getAvailableUesPeriod(int parcoursId,
			int idPeriode) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<UniteEnseignement> c = cb
					.createQuery(UniteEnseignement.class);
			Root<UniteEnseignement> ue = c.from(UniteEnseignement.class);
			c.select(ue).distinct(true);
			c.orderBy(cb.asc(ue.get("libelleFr")));

			Subquery<RepartitionUe> subquery = c.subquery(RepartitionUe.class);
			Root<RepartitionUe> repUe = subquery.from(RepartitionUe.class);
			Join<RepartitionUe, ParcoursType> repartitionUeParcoursType = repUe
					.join("parcoursType");
			Join<RepartitionUe, Periode> repartitionUePeriode = repUe
					.join("periode");
			subquery.select(repUe);

			List<Predicate> subCriteria = new ArrayList<Predicate>();

			List<Predicate> conditions = new ArrayList<Predicate>();
			Predicate condition0 = cb.equal(repUe.get("uniteEnseignement"), ue);
			conditions.add(condition0);
			Predicate condition1 = cb.equal(
					repartitionUeParcoursType.get("id"), parcoursId);
			conditions.add(condition1);
			Predicate condition2 = cb.equal(repartitionUePeriode.get("id"),
					idPeriode);
			conditions.add(condition2);
			subquery.where(cb.and(conditions.toArray(new Predicate[0])));
			subCriteria.add(cb.not(cb.exists(subquery)));

			c.where(subCriteria.toArray(new Predicate[0]));
			TypedQuery<UniteEnseignement> q = entityManager.createQuery(c);
			List<UniteEnseignement> result = q.getResultList();
			for (UniteEnseignement entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;

		} catch (RuntimeException re) {

			log.error("getAvailableUesPeriod 'UniteEnseignement' failed", re);
			throw re;
		}
	}

	@Override
	public List<RepartitionUe> findReparationUe(Integer oofId, Integer periodeId) {
		if (oofId == null) {
			return null;
		}
		log.debug("findReparationUe with  offId: " + oofId
				+ " and periodeId = " + periodeId);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RepartitionUe r");

			request.append(" where  r.parcoursType.offreFormation.id IN ");
			request.append(" (SELECT oof.offreFormation.id FROM OuvertureOffreFormation oof WHERE oof.id =:oofId");
			request.append(" and oof.estOuverte = TRUE) ");
			if (periodeId != null) {
				request.append(" and r.periode.id =:periodeId ");
			}
			TypedQuery<RepartitionUe> query = entityManager.createQuery(
					new String(request), RepartitionUe.class);
			query.setParameter("oofId", oofId);
			query.setParameter("periodeId", periodeId);
			List<RepartitionUe> resultList = (List<RepartitionUe>) query
					.getResultList();
			log.debug("findReparationUe successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findReparationUe failed", re);
			throw re;
		}
	}
}
