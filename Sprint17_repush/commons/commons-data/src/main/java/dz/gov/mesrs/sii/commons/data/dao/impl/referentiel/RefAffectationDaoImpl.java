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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.ResponsableExamen;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

@Repository("refAffectationDaoImpl")
public class RefAffectationDaoImpl implements RefAffectationDao {

	private static final Logger log = LoggerFactory
			.getLogger(RefAffectationDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * [RefAffectationDaoImpl.persist] save new RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectation
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefAffectation transientInstance) {
		log.debug("persisting RefAffectation instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();/* Sofiane MAKERRI : Bug Ticket0000003 */
			entityManager.refresh(transientInstance); /*
													 * Sofiane MAKERRI : Bug
													 * Ticket0000003
													 */
			log.debug("persist successful");
		} catch (javax.persistence.PersistenceException cve) {
			throw cve;

		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.remove] remove RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectation
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefAffectation persistentInstance) {
		log.debug("removing RefAffectation instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.merge] update RefAffectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            RefAffectation
	 * @return the RefAffectation
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefAffectation merge(RefAffectation detachedInstance) {
		log.debug("merging RefAffectation instance");
		try {
			RefAffectation result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findById] find the RefAffectation by Identity
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            Id
	 * @return the RefAffectationDto
	 */
	@Override
	public RefAffectation findById(int id) {
		log.debug("getting RefAffectation instance with id: " + id);
		try {
			RefAffectation instance = entityManager.find(RefAffectation.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findStructuresForIndividu] Find the STRUCTURES
	 * Affected to INDIVIDU
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            id of INDIVIDU
	 * @return List of RefAffectation
	 */
	@Override
	public List<RefAffectation> findStructuresForIndividu(Integer idEntity) {
		log.debug("getting List Structures of RefAffectation instance with idIndividu : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refIndividu.id = :idEntity and r.refGroupeByGroupe is null  and r.refGroupeByGroupeAffecte is null    AND r.refLieu is null  and r.refEvenement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findStructuresForIndividu(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}

	}

	/**
	 * [RefAffectationDaoImpl.findStructuresOfGroupe] Find the STRUCTURES of
	 * GROUPE
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            id of GROUPE
	 * @return List of RefAffectation
	 */
	public List<RefAffectation> findStructuresOfGroupe(Integer idEntity) {
		log.debug("getting List Structures of RefAffectation instance with idGroupe : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refIndividu is null and r.refGroupeByGroupe.id = :idEntity  and r.refGroupeByGroupeAffecte is null AND r.refLieu is null  and r.refEvenement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findStructuresForGroupe(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findGroupesForIndividu] Find the GROUPES Affected
	 * to INDIVIDU
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            id of INDIVIDU
	 * @return List of RefAffectation
	 */
	@Override
	public List<RefAffectation> findGroupesForIndividu(Integer idEntity) {
		log.debug("getting List Groupes of RefAffectation instance with idIndividu : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refIndividu.id = :idEntity and r.refStructure is null  and r.refGroupeByGroupeAffecte is null    AND r.refLieu is null and r.refEvenement is null");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupesForIndividu(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findGroupesForStructure] Find the GROUPES Affected
	 * to STRUCTURE
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 17:40:26
	 * @param the
	 *            id of STRUCTURE
	 * @return List of RefAffectation
	 */
	@Override
	public List<RefAffectation> findGroupesForStructure(Integer idEntity) {
		log.debug("getting List Groupes of RefAffectation instance with idStructure : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refStructure.id = :idEntity and r.refIndividu is null  and r.refGroupeByGroupeAffecte is null   AND r.refLieu is null and r.refEvenement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupesForStructure(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findDomainesForStructure] Find the Domaines
	 * Affected to STRUCTURE
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 11:40:26
	 * @param the
	 *            id of STRUCTURE
	 * @return List of RefAffectation
	 */
	@Override
	public List<RefAffectation> findDomainesForStructure(Integer idEntity) {
		log.debug("getting List Domaines of RefAffectation instance with idStructure : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refStructure.id = :idEntity and r.refDomaineLMD is not null");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findDomainesForStructure(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findFilieresForStructure] Find the Filieres
	 * Affected to STRUCTURE
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 16:00:26
	 * @param the
	 *            id of STRUCTURE
	 * @return List of RefAffectation
	 */
	@Override
	public List<RefAffectation> findFilieresForStructure(Integer idEntity) {
		log.debug("getting List Filieres of RefAffectation instance with idStructure : "
				+ idEntity);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refStructure.id = :idEntity and r.refFiliereLmd is not null");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEntity);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findFilieresForStructure(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findGroupesofGroupe(Integer idGroupe) {
		log.debug("getting List Groupes of RefAffectation instance with idGroupe : "
				+ idGroupe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refGroupeByGroupeAffecte.id = :idEntity and r.refStructure is null  and r.refIndividu is null    AND r.refLieu is null and r.refEvenement is null");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idGroupe);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupesofGroupe(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RefAffectation> findIndividusOfGroupe(Integer idGroupe) {
		log.debug("getting List Individu of RefAffectation instance with idGroupe : "
				+ idGroupe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refStructure is null and r.refGroupeByGroupe.id = :idEntity  and r.refGroupeByGroupeAffecte is null   AND r.refLieu is null and r.refEvenement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idGroupe);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfGroupe(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findIndividusOfStructure(Integer idStructure) {
		log.debug("getting List Individus of RefAffectation instance with idStructure : "
				+ idStructure);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refGroupeByGroupe is null and r.refStructure.id = :idEntity  and r.refGroupeByGroupeAffecte is null   AND r.refLieu is null and r.refEvenement is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idStructure);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfStructure(String idEntity)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findAffectationByIdIndividu(Integer id) {
		log.debug("get RefAffectation instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefAffectation r ");
			request.append("where r.refIndividu.id  =:id ");
			request.append("and (r.refStructure is not null or r.refGroupeByGroupe is not null) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefAffectation> resultList = (List<RefAffectation>) query
					.getResultList();
			log.debug("findAll 'RefAffectation' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefAffectation failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findStructuresForEvenement(Integer idEvenement) {
		log.debug("getting List Groupes of RefAffectation instance with idEvenement : "
				+ idEvenement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refEvenement.id = :idEntity and r.refGroupeByGroupe is null  and r.refGroupeByGroupeAffecte is null and r.refLieu is null and r.refIndividu is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEvenement);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupesForEvenement(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findGroupesForEvenement(Integer idEvenement) {
		log.debug("getting List Groupes of RefAffectation instance with idEvenement : "
				+ idEvenement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refEvenement.id = :idEntity and r.refStructure is null  and r.refGroupeByGroupeAffecte is null and r.refLieu is null and r.refIndividu is null ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEvenement);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupesForEvenement(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findIndividusOfEvenement(Integer idEvenement) {
		log.debug("getting List Individus of RefAffectation instance with idEvenement : "
				+ idEvenement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refGroupeByGroupe is null and r.refEvenement.id = :idEntity  and r.refGroupeByGroupeAffecte is null and r.refStructure is null and r.refLieu is null");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", idEvenement);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfEvenement(String idEvenement)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findAffectationByIdIndividu(Integer etabId,
			Integer id) {
		log.debug("get RefAffectation instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r from RefAffectation r, RefEtablissement e  ");
			request.append("where r.refIndividu.id  =:id ");
			request.append("and (r.refGroupeByGroupe is null ");
			if (etabId != null) {
				request.append(" and  r.refStructure.refEtablissement.id =:etabId ");
			}
			request.append(" and r.refLieu is null ");
			request.append(" and r.refEvenement is null ");
			request.append(" and r.refGroupeByGroupeAffecte is null ");
			request.append(" and r.refStructure is not null ");
			request.append(" or (r.refStructure is null");
			if (etabId != null) {
				request.append(" and r.refGroupeByGroupe.refEtablissement.id =:etabId  ");
			}
			request.append(" and r.refLieu is null ");
			request.append(" and r.refEvenement is null ");
			// request.append(" and r.refGroupeByGroupeAffecte is null ");
			request.append(" and r.refGroupeByGroupe is not null)) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			query.setParameter("etabId", etabId);
			List<RefAffectation> resultList = (List<RefAffectation>) query
					.getResultList();
			log.debug("findAll 'RefAffectation' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefAffectation failed", re);
			throw re;
		}
	}

	@Override
	public RefAffectation findGroupeParentForGroupe(String codeGroupe) {
		log.debug("findGroupeParentForGroupe with codeGroupe : " + codeGroupe);
		try {
			StringBuilder request = new StringBuilder(
					"select i from RefIndividu i, RefAffectation r ");
			request.append("where r.refGroupeByGroupe.identifiant = :codeGroupe and r.refStructure is null  and r.refIndividu is null    AND r.refLieu is null and r.refEvenement is null ");
			TypedQuery<RefAffectation> query = entityManager.createQuery(
					new String(request), RefAffectation.class);
			query.setParameter("codeGroupe", codeGroupe);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupeParentForGroupe");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findGroupeParentForGroupe failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findIndividusOfGroupe(String codeGroupe) {
		log.debug("getting List Individu of RefAffectation instance with codeGroupe : "
				+ codeGroupe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refStructure is null and r.refGroupeByGroupe.identifiant = :codeGroupe  and r.refGroupeByGroupeAffecte is null   AND r.refLieu is null and r.refEvenement is null ");
			TypedQuery<RefAffectation> query = entityManager.createQuery(
					new String(request), RefAffectation.class);
			query.setParameter("codeGroupe", codeGroupe);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfGroupe");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findIndividusOfGroupe failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findIndividusOfStructure(String codeStructure) {
		log.debug("getting List Individus of RefAffectation instance with codeStructure : "
				+ codeStructure);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refGroupeByGroupe is null and r.refStructure.identifiant = :codeStructure  and r.refGroupeByGroupeAffecte is null   AND r.refLieu is null and r.refEvenement is null ");
			TypedQuery<RefAffectation> query = entityManager.createQuery(
					new String(request), RefAffectation.class);
			query.setParameter("codeStructure", codeStructure);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfStructure(String idEntity)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findIndividusOfEvenement(String codeEvenement) {
		log.debug("getting List Individus of RefAffectation instance with codeEvenement : "
				+ codeEvenement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refGroupeByGroupe is null and r.refEvenement.identifiant = :codeEvenement  and r.refGroupeByGroupeAffecte is null and r.refStructure is null and r.refLieu is null");
			TypedQuery<RefAffectation> query = entityManager.createQuery(
					new String(request), RefAffectation.class);
			query.setParameter("codeEvenement", codeEvenement);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findIndividusOfEvenement(String idEvenement)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public RefAffectation findGroupeParentForGroupe(Integer idGroupe) {
		log.debug("findGroupeParentForGroupe with codeGroupe : " + idGroupe);
		try {
			StringBuilder request = new StringBuilder(
					"select i from RefIndividu i, RefAffectation r ");
			request.append("where r.refGroupeByGroupe.id = :idGroupe and r.refStructure is null  and r.refIndividu is null    AND r.refLieu is null and r.refEvenement is null ");
			TypedQuery<RefAffectation> query = entityManager.createQuery(
					new String(request), RefAffectation.class);
			query.setParameter("idGroupe", idGroupe);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findGroupeParentForGroupe");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findGroupeParentForGroupe failed", re);
			throw re;
		}
	}

	@Override
	public List<RefAffectation> findStructuresByIndividuAndRole(
			Integer idIndividu, Integer idRole) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectation r ");
			request.append("where r.refIndividu.id = :idIndividu and r.nomenclature.id = :idRole and r.refGroupeByGroupe is null  and r.refGroupeByGroupeAffecte is null  and r.refLieu is null  and r.refEvenement is null and r.refDomaineLMD is null  and r.refFiliereLmd is null ");

			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idIndividu", idIndividu);
			query.setParameter("idRole", idRole);
			List<RefAffectation> resultList = query.getResultList();
			log.debug("findStructuresForIndividu(String idEntity)");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [RefAffectationDaoImpl.findAffectationByIdGroupe] Method Overridden By : @author
	 * Rafik on : 30 déc. 2014 14:16:52
	 * 
	 * @param etabId
	 * @param idGroupe
	 * @return
	 */
	@Override
	public List<RefAffectation> findAffectationByIdGroupe(Integer etabId,
			Integer idGroupe) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefAffectation> c = cb
					.createQuery(RefAffectation.class);
			Root<RefAffectation> a = c.from(RefAffectation.class);
			Join<RefAffectation, RefGroupe> ag = a.join("refGroupeByGroupe");
			c.select(a).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ag.get("id"), idGroupe);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefAffectation> q = entityManager.createQuery(c);
			List<RefAffectation> result = q.getResultList();
			for (RefAffectation entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findAffectationByIdGroupe 'RefAffectation' failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectationDao#
	 * findIndividuAndRole(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<RefIndividu> findIndividuByRole(Integer idEtab,
			Integer idRole) {
		try {
			if (idRole == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select distinct r.refIndividu from RefAffectation r ");
			request.append(" where r.nomenclature.id = " + idRole);
			if (idEtab != null) {
				request.append(" and (r.refGroupeByGroupe.refEtablissement.id = "
						+ idEtab);
				request.append(" or r.refStructure.refEtablissement.id = "
						+ idEtab + ")");
			}

			TypedQuery<RefIndividu> query = entityManager.createQuery(
					new String(request), RefIndividu.class);
			List<RefIndividu> resultList = query.getResultList();
			log.debug("findIndividuAndRole success");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findIndividuAndRole failed", re);
			throw re;
		}
	}

}
