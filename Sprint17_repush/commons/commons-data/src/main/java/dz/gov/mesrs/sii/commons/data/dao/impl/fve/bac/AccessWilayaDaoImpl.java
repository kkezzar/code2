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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.AccessWilayaDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DroitAccessWilaya;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;

/**
 * Dao object for domain model class AccessWilaya;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.AccessWilaya;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
@Repository("accessWilayaDao")
@Transactional
public class AccessWilayaDaoImpl implements AccessWilayaDao {

	private static final Logger log = LoggerFactory.getLogger(AccessWilayaDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AccessWilayaDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.AccessWilaya
	 *      )
	 * 
	 * @param transientInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AccessWilaya transientInstance) {
		log.debug("persisting AccessWilaya instance");
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

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AccessWilayaDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.AccessWilaya)
	 * @param persistentInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AccessWilaya persistentInstance) {
		log.debug("removing AccessWilaya instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AccessWilayaDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.AccessWilaya)
	 * @param detachedInstance
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AccessWilaya merge(AccessWilaya detachedInstance) {
		log.debug("merging AccessWilaya instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AccessWilayaDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public AccessWilaya findById(int id) {
		log.debug("getting AccessWilaya instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AccessWilaya.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AccessWilaya> findAll() {
		log.debug("getting all AccessWilaya instances");
		try {
			Query query = entityManager.createQuery("from AccessWilaya accessWilaya");
			log.debug("findAll 'AccessWilaya' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AccessWilaya' failed", re);
			return new ArrayList<AccessWilaya>();
		}
	}

	/**
	 * Renvoi la liste des access wilayas par annee academique
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param idAnneeAcademique
	 * @return
	 */
	@Override
	public List<AccessWilaya> findByIdAnneeAcademique(int idAnneeAcademique) {
		try {
			String sqlQuery = "SELECT a FROM AccessWilaya a WHERE a.anneeAcademique.id=:idAnneeAcademique ";

			TypedQuery<AccessWilaya> query = entityManager.createQuery(sqlQuery, AccessWilaya.class);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();
			// if (result == null || result.isEmpty()) {
			// return new;
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
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 15:25:30
	 * @param searchBo
	 * @return
	 */
	@Override
	public List<AccessWilaya> findAdvanced(AccessWilaya searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AccessWilaya> c = cb.createQuery(AccessWilaya.class);
			Root<AccessWilaya> accessWilayaRoot = c.from(AccessWilaya.class);

			c.select(accessWilayaRoot).distinct(true);
			Predicate predicate = null;

			// Annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<AccessWilaya, AnneeAcademique> anneeAcademique = accessWilayaRoot.join("anneeAcademique");

				predicate = cb.equal(anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId());

			}

			// domaine
			if (searchBo.getRefCodeDomaine() != null) {
				if (predicate == null) {
					predicate = cb.equal(accessWilayaRoot.get("refCodeDomaine"), searchBo.getRefCodeDomaine());
				} else {
					predicate = cb.and(predicate,
							cb.equal(accessWilayaRoot.get("refCodeDomaine"), searchBo.getRefCodeDomaine()));
				}
			}

			// filiere
			if (searchBo.getRefCodeFiliere() != null) {
				if (predicate == null) {
					predicate = cb.equal(accessWilayaRoot.get("refCodeFiliere"), searchBo.getRefCodeFiliere());
				} else {
					predicate = cb.and(predicate,
							cb.equal(accessWilayaRoot.get("refCodeFiliere"), searchBo.getRefCodeFiliere()));
				}
			}

			// etablissement
			if (searchBo.getRefCodeEtablissement() != null) {
				if (predicate == null) {
					predicate = cb.equal(accessWilayaRoot.get("refCodeEtablissement"),
							searchBo.getRefCodeEtablissement());
				} else {
					predicate = cb.and(predicate,
							cb.equal(accessWilayaRoot.get("refCodeEtablissement"), searchBo.getRefCodeEtablissement()));
				}
			}

			if (predicate != null) {
				c.where(predicate);
			}
			TypedQuery<AccessWilaya> query = entityManager.createQuery(c);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<AccessWilaya>();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.bac.AccessWilayaDao#remove(dz.gov
	 * .mesrs.sii.commons.data.model.fve.bac.DroitAccessWilaya)
	 */
	@Override
	public void remove(DroitAccessWilaya persistentInstance) {
		log.debug("removing DroitAccessWilaya instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}

	}

}
