package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

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




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CapaciteEtabDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CapaciteEtab;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;

/**
 * Dao object for domain model class CapaciteEtab;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.CapaciteEtab;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("capaciteEtabDao")
@Transactional
public class CapaciteEtabDaoImpl implements CapaciteEtabDao {

	private static final Logger log = LoggerFactory.getLogger(CapaciteEtabDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CapaciteEtabDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.CapaciteEtab
	 *      )
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(CapaciteEtab transientInstance) {
		log.debug("persisting CapaciteEtab instance");
		try {
			entityManager.persist(transientInstance);
            entityManager.flush();
            entityManager.refresh(transientInstance);
			/*
			 * entityManager.flush(); entityManager.refresh(transientInstance);
			 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CapaciteEtabDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.CapaciteEtab)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(CapaciteEtab persistentInstance) {
		log.debug("removing CapaciteEtab instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CapaciteEtabDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.CapaciteEtab)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CapaciteEtab merge(CapaciteEtab detachedInstance) {
		log.debug("merging CapaciteEtab instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CapaciteEtabDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public CapaciteEtab findById(int id) {
		log.debug("getting CapaciteEtab instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(CapaciteEtab.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<CapaciteEtab> findAll() {
		log.debug("getting all CapaciteEtab instances");
		try {
			Query query = entityManager
					.createQuery("from CapaciteEtab capaciteEtab");
			log.debug("findAll 'CapaciteEtab' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'CapaciteEtab' failed", re);
			return new ArrayList<CapaciteEtab>();
		}
	}

	/**
	 * Renvoi la liste des capcistes par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	public List<CapaciteEtab> findByIdAnneeAcademique(int idAnneeAcademique) {
		try {
			String sqlQuery = "SELECT a FROM CapaciteEtab a WHERE a.anneeAcademique.id=:idAnneeAcademique ";

			TypedQuery<CapaciteEtab> query = entityManager.createQuery(
					sqlQuery, CapaciteEtab.class);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

			// List<CapaciteEtab> result = query.getResultList();
			// if (result == null || result.isEmpty()) {
			// return null;
			// }
			// return result;
		} catch (RuntimeException re) {
			log.error("findFormationsByIdDossier failed", re);
			throw re;
		}
	}

	/**
	 * Recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 09:24:24
	 * @param searchBo
	 * @return
	 */
	public List<CapaciteEtab> findAdvanced(CapaciteEtab searchBo) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CapaciteEtab> c = cb.createQuery(CapaciteEtab.class);
			Root<CapaciteEtab> capaciteEtabRoot = c.from(CapaciteEtab.class);

			c.select(capaciteEtabRoot).distinct(true);
			Predicate predicate = null;

			// Annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<AccessWilaya, AnneeAcademique> anneeAcademique = capaciteEtabRoot
						.join("anneeAcademique");

				predicate = cb.equal(anneeAcademique.get("id"), searchBo
						.getAnneeAcademique().getId());

			}
			
			// domaine
			if (searchBo.getRefCodeDomaine() != null) {
				if (predicate == null) {
					predicate = cb.equal(
							capaciteEtabRoot.get("refCodeDomaine"),
							searchBo.getRefCodeDomaine());
				} else {
					predicate = cb.and(predicate, cb.equal(
							capaciteEtabRoot.get("refCodeDomaine"),
							searchBo.getRefCodeDomaine()));
				}
			}
			
			// filiere
			if (searchBo.getRefCodeFiliere() != null) {
				if (predicate == null) {
					predicate = cb.equal(
							capaciteEtabRoot.get("refCodeFiliere"),
							searchBo.getRefCodeFiliere());
				} else {
					predicate = cb.and(predicate, cb.equal(
							capaciteEtabRoot.get("refCodeFiliere"),
							searchBo.getRefCodeFiliere()));
				}
			}
			
			// etablissement
			if (searchBo.getRefCodeEtablissement() != null) {
				if (predicate == null) {
					predicate = cb.equal(
							capaciteEtabRoot.get("refCodeEtablissement"),
							searchBo.getRefCodeEtablissement());
				} else {
					predicate = cb.and(predicate, cb.equal(
							capaciteEtabRoot.get("refCodeEtablissement"),
							searchBo.getRefCodeEtablissement()));
				}
			}

			if (predicate != null) {
				c.where(predicate);
			}
			TypedQuery<CapaciteEtab> query = entityManager.createQuery(c);
			return (List<CapaciteEtab>) query.getResultList();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<CapaciteEtab>();
		}
	}
}
