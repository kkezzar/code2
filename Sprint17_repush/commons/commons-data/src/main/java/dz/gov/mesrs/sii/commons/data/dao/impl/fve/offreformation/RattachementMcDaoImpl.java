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
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RattachementMcDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Repository("rattachementMcDao")
public class RattachementMcDaoImpl implements RattachementMcDao {

	private static final Logger log = LoggerFactory.getLogger(RattachementMcDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public void persist(RattachementMc transientInstance) {
		log.debug("persisting rattachementMc instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			
			log.debug("persisting rattachementMc instance : succés");

		} catch (RuntimeException re) {
			log.error("persisting rattachementMc instance :echec", re);
			throw re;
		}
	}

	@Override
	public void remove(RattachementMc persistentInstance) {
		log.debug("removing rattachementMc instance");
		try {
			entityManager.remove(persistentInstance);
			// entityManager.refresh(persistentInstance);
			// entityManager.flush();
			log.debug("removing rattachementMc instance successful");

		} catch (RuntimeException re) {
			log.error("removing rattachementMc instance failed", re);
			throw re;
		}
	}

	/**
	 * 
	 */
	@Override
	public RattachementMc merge(RattachementMc detachedInstance) {
		log.debug("merging RattachementMc instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public void remove(int ueId, int mcId) {
		log.debug("removing rattachementMc instance whit udId & mcId");
		try {

			List<RattachementMc> _rattachements = this.find(ueId, mcId);

			if (_rattachements.size() == 1)
				entityManager.remove(_rattachements.get(0));

			log.debug("removing rattachementMc instance whit udId & mcId successful");
		} catch (RuntimeException re) {
			log.error(
					"removing rattachementMc instance whit udId & mcId failed",
					re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public RattachementMc findById(int id) {
		log.debug("getting RattachementMc instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RattachementMc.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RattachementMc> find(Integer ueId, Integer mcId) {
		log.debug("Recherche multi critétes des rattachements des mcs dans à une ue");
		try {
			StringBuilder sb = new StringBuilder();

			sb.append("select rattachement from RattachementMc rattachement ");
			if (ueId != null)
				sb.append("join rattachement.uniteEnseignement ue ");
			if (mcId != null)
				sb.append("join rattachement.matiereConstitutive mc ");

			sb.append(" where (1=1) ");

			if (ueId != null)
				sb.append(" and ue.id = :ueId ");
			if (mcId != null)
				sb.append(" and mc.id = :mcId ");
			sb.append(" order by rattachement.matiereConstitutive.libelleFr ");
			Query query = entityManager.createQuery(sb.toString());

			// Ajout des paramétres
			if (ueId != null)
				query.setParameter("ueId", ueId);
			if (mcId != null)
				query.setParameter("mcId", mcId);

			// Exécution de la requete.
			List<RattachementMc> _repartitionList = query.getResultList();

			return _repartitionList;

		} catch (RuntimeException re) {
			log.error(
					"Recherche multi critétes des rattachements des mcs dans à une ue failed",
					re);
			return new ArrayList<RattachementMc>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RattachementMc> findOptionnelle(Integer ueId, Integer mcId,
			Boolean optionnelle) {
		log.debug("Recherche multi critétes des rattachements des mcs dans à une ue");
		try {
			StringBuilder sb = new StringBuilder();

			sb.append("select rattachement from RattachementMc rattachement ");
			if (ueId != null)
				sb.append("join rattachement.uniteEnseignement ue ");
			if (mcId != null)
				sb.append("join rattachement.matiereConstitutive mc ");

			sb.append("where (1=1) ");

			if (ueId != null)
				sb.append("and ue.id = :ueId ");
			if (mcId != null)
				sb.append("and mc.id = :mcId ");
			if (optionnelle != null) {
				if (optionnelle)
					sb.append("and rattachement.optionnelle = TRUE ");
				else
					sb.append("and rattachement.optionnelle = FALSE ");
			}

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des paramétres
			if (ueId != null)
				query.setParameter("ueId", ueId);
			if (mcId != null)
				query.setParameter("mcId", mcId);

			// Exécution de la requete.
			List<RattachementMc> _repartitionList = query.getResultList();

			return _repartitionList;

		} catch (RuntimeException re) {
			log.error(
					"Recherche multi critétes des rattachements des mcs dans à une ue failed",
					re);
			return new ArrayList<RattachementMc>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.RattachementMcDao
	 * #findUnique(int, int)
	 */
	@Override
	public RattachementMc findUnique(int ueId, int mcId) {
		log.debug("findUnique");
		try {
			StringBuilder sb = new StringBuilder();

			sb.append(" select r from RattachementMc r ");
			sb.append(" where r.uniteEnseignement.id = " + ueId);
			sb.append(" and r.matiereConstitutive.id = " + mcId);
			TypedQuery<RattachementMc> query = entityManager.createQuery(
					sb.toString(),
					RattachementMc.class);
			// Exécution de la requete.
			List<RattachementMc> result = query.getResultList();
			log.info("findUnique success");
			if(result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);

		} catch (RuntimeException re) {
			log.error("findUnique failed", re);
			return null;
		}
	}

}
