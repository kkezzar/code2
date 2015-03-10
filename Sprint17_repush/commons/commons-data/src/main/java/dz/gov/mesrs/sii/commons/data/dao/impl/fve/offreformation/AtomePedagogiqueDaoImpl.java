package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AtomePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Repository("atomePedagogiqueDao")
@Transactional
public class AtomePedagogiqueDaoImpl implements AtomePedagogiqueDao {
	private static final Logger log = LoggerFactory.getLogger(AtomePedagogiqueDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AtomePedagogique transientInstance) {
		log.debug("persisting AtomePedagogique instance");
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AtomePedagogique persistentInstance) {
		log.debug("removing AtomePedagogique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(int id) {

		AtomePedagogique _ap = entityManager.find(AtomePedagogique.class, id);

		remove(_ap);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AtomePedagogique merge(AtomePedagogique detachedInstance) {
		log.debug("merging AtomePedagogique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AtomePedagogique findById(int id) {
		log.debug("getting AtomePedagogique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AtomePedagogique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AtomePedagogique> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AtomePedagogique> findAll() {
		log.debug("getting all AtomePedagogique instances");
		try {
			Query query = entityManager
					.createQuery("from AtomePedagogique atomePedagogique");
			log.debug("findAll 'AtomePedagogique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AtomePedagogique' failed", re);
			return new ArrayList<AtomePedagogique>();
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<AtomePedagogique> find(String code,
			Integer idMatiereConstitutive, String libelleFr, String libelleAr) {
		log.debug("Recherche multi crit�tes des APs");
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("from AtomePedagogique atomePedagogique ");
			sb.append("where (1=1) ");

			if (code != null)
				sb.append("and atomePedagogique.code = :code ");
			if (idMatiereConstitutive != null)
				sb.append("and atomePedagogique.matiereConstitutive.id = :idMatiereConstitutive ");
			if (libelleFr != null)
				sb.append("and atomePedagogique.libelleFr = :libelleFr ");
			if (libelleAr != null)
				sb.append("and atomePedagogique.libelleAr = :libelleAr ");

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des param�tres
			if (code != null)
				query.setParameter("code", code);
			if (idMatiereConstitutive != null)
				query.setParameter("idMatiereConstitutive",
						idMatiereConstitutive);
			if (libelleFr != null)
				query.setParameter("libelleFr", libelleFr);
			if (libelleAr != null)
				query.setParameter("libelleAr", libelleAr);

			// Ex�cution de la requete.
			List<AtomePedagogique> _apList = query.getResultList();

			log.debug("Recherche multi crit�tes des APs : successful");

			return _apList;
		} catch (RuntimeException re) {
			log.error("Recherche multi crit�tes des APs : failed", re);
			return new ArrayList<AtomePedagogique>();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AtomePedagogique> findByRattachementMC(int idRattachementMc) {
		log.debug("Recherche multi critètes des APs");
		try {

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT ap from AtomePedagogique ap, RattachementMc r ");
			sb.append("where (r.matiereConstitutive.id = ap.matiereConstitutive.id) ");
			sb.append("and (r.id =:idRattachementMc) ");

			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idRattachementMc", idRattachementMc);

			List<AtomePedagogique> _apList = query.getResultList();

			log.debug("findByRattachementMC : successful");

			return _apList;
		} catch (RuntimeException re) {
			log.error("findByRattachementMC : failed", re);
			throw re;
		}

	}

	@Override
	public AtomePedagogique findByTypeAndRattachementMC(String codeType,
			int idRattachementMc) {
		log.debug("findByTypeAndRattachementMC");
		try {

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT ap from AtomePedagogique ap, RattachementMc r ");
			sb.append("where (r.matiereConstitutive.id = ap.matiereConstitutive.id) ");
			sb.append("and (r.id =:idRattachementMc) ");
			if (codeType != null) {
				sb.append("and lower(ap.refCodeTypeAp) =:codeType ");
			}

			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idRattachementMc", idRattachementMc);
			query.setParameter("codeType", codeType);
			List<AtomePedagogique> _apList = query.getResultList();
			if (_apList == null || _apList.isEmpty()) {
				return null;
			}
			log.debug("findByTypeAndRattachementMC : successful");

			return _apList.get(0);
		} catch (RuntimeException re) {
			log.error("findByTypeAndRattachementMC : failed", re);
			throw re;
		}
	}

	@Override
	public List<AtomePedagogique> findApByGroupeId(int sectionId) {
		try {
			StringBuilder sb = new StringBuilder();

			sb.append(" select distinct ap from AtomePedagogique ap, AssociationGroupePedagogique r ");
			sb.append(" where ap.matiereConstitutive.id IN ");
			sb.append(" (select r.rattachementMc.matiereConstitutive.id ");
			sb.append(" where r.groupePedagogique.id =:sectionId )");
			TypedQuery<AtomePedagogique> query = entityManager.createQuery(
					new String(sb), AtomePedagogique.class);
			query.setParameter("sectionId", sectionId);
			List<AtomePedagogique> resultList = (List<AtomePedagogique>) query
					.getResultList();
			log.debug("findApByGroupeId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findApByGroupeId failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AtomePedagogiqueDao
	 * #findUniqueByType(int, int)
	 */
	@Override
	public AtomePedagogique findUniqueByType(int idMc, int ncTypeId) {
		try {
			StringBuilder sb = new StringBuilder();

			sb.append(" select distinct ap from AtomePedagogique ap");
			sb.append(" where ap.matiereConstitutive.id  = " + idMc);
			sb.append(" and  ap.ncTypeAp.id = " + ncTypeId);
			TypedQuery<AtomePedagogique> query = entityManager.createQuery(
					new String(sb), AtomePedagogique.class);

			List<AtomePedagogique> resultList = (List<AtomePedagogique>) query
					.getResultList();
			log.debug("findUniqueByType successful");
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findUniqueByType failed", re);
			throw re;
		}
		return null;
	}

}
