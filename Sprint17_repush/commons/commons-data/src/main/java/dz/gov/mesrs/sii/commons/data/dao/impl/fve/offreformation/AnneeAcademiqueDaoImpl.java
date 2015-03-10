package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:16:02
 */
@Repository("anneeAcademiqueDao")
public class AnneeAcademiqueDaoImpl implements AnneeAcademiqueDao {

	private static final Logger log = LoggerFactory.getLogger(AnneeAcademiqueDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.AnneeAcademiqueDao#persist(dz.gov.mesrs.sii.AnneeAcademiqueService.business.model.bo.AnneeAcademique)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AnneeAcademique transientInstance) {
		log.debug("persisting AnneeAcademique instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.AnneeAcademiqueDao#remove(dz.gov.mesrs.sii.AnneeAcademiqueService.business.model.bo.AnneeAcademique)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AnneeAcademique persistentInstance) {
		log.debug("removing AnneeAcademique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.AnneeAcademiqueDao#merge(dz.gov.mesrs.sii.AnneeAcademiqueService.business.model.bo.AnneeAcademique)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AnneeAcademique merge(AnneeAcademique detachedInstance) {
		log.debug("merging AnneeAcademique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NAnneeAcademiqueDao#findById(int)
	 * @param id
	 */
	@Override
	public AnneeAcademique findById(int id) {
		log.debug("getting AnneeAcademique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AnneeAcademique.class, id);
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
	public List<AnneeAcademique> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<AnneeAcademique> findAll() {
		log.debug("getting all AnneeAcademique instances");
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AnneeAcademique> c = cb
					.createQuery(AnneeAcademique.class);
			Root<AnneeAcademique> aa = c.from(AnneeAcademique.class);
			c.select(aa).distinct(true);
			c.orderBy(cb.desc(aa.get("premiereAnnee")));
			TypedQuery<AnneeAcademique> q = entityManager.createQuery(c);
			List<AnneeAcademique> result = q.getResultList();
			for (AnneeAcademique entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findAll 'AnneeAcademique' failed", re);
			throw re;
		}

	}

	@Override
	public AnneeAcademique findByFirstAndSecondYear(int premiereAnnee,
			int deuxiemeAnnee) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AnneeAcademique> c = cb
					.createQuery(AnneeAcademique.class);
			Root<AnneeAcademique> t = c.from(AnneeAcademique.class);

			c.select(t).distinct(true);
			Predicate predicate = cb.equal(t.get("premiereAnnee"),
					premiereAnnee);
			predicate = cb.and(predicate,
					cb.equal(t.get("deuxiemeAnnee"), deuxiemeAnnee));
			c.where(predicate);

			TypedQuery<AnneeAcademique> q = entityManager.createQuery(c);
			List<AnneeAcademique> resultList = (List<AnneeAcademique>) q
					.getResultList();
			if (resultList != null && resultList.size() == 1) {
				return resultList.get(0);
			}
			return null;

		} catch (RuntimeException re) {

			log.error("findByFirstAndSecondYear  failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao
	 * #findCurrentAnneeAcademique()
	 */
	@Override
	public AnneeAcademique findCurrentAnneeAcademique() {
		try {
			Date currentDate = new Date();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AnneeAcademique> c = cb
					.createQuery(AnneeAcademique.class);
			Root<AnneeAcademique> t = c.from(AnneeAcademique.class);
			final Path<Date> dateDebutPath = t.get("dateDebut");
			final Path<Date> dateFinPath = t.get("dateFin");
			c.select(t).distinct(true);
			Predicate predicate = cb.lessThanOrEqualTo(dateDebutPath,
					currentDate);
			predicate = cb.and(predicate,
					cb.greaterThanOrEqualTo(dateFinPath, currentDate));
			c.where(predicate);

			TypedQuery<AnneeAcademique> q = entityManager.createQuery(c);
			List<AnneeAcademique> resultList = (List<AnneeAcademique>) q
					.getResultList();
			if (resultList != null && resultList.size() == 1) {
				return resultList.get(0);
			}
			return null;

		} catch (RuntimeException re) {

			log.error("findCurrentAnneeAcademique  failed", re);
			throw re;
		}
	}
}
