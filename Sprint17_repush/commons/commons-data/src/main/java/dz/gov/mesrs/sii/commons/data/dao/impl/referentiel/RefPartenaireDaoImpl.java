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
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPartenaireDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPartenaire;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
@Service("refPartenaireDaoImpl")
public class RefPartenaireDaoImpl implements RefPartenaireDao {

	private static final Logger log = LoggerFactory.getLogger(RefPartenaireDaoImpl.class.getName());
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefPartenaireDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefPartenaire)
	 */
	@Override
	public void persist(RefPartenaire transientInstance) {
		log.debug("persisting RefPartenaire instance");
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

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefPartenaireDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefPartenaire)
	 */
	@Override
	public void remove(RefPartenaire persistentInstance) {
		log.debug("removing RefPartenaire instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefPartenaireDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefPartenaire)
	 */
	@Override
	public RefPartenaire merge(RefPartenaire detachedInstance) {
		log.debug("merging RefPartenaire instance");
		try {
			RefPartenaire result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefPartenaireDao#findById(int)
	 */
	@Override
	public RefPartenaire findById(int id) {
		log.debug("getting RefPartenaire instance with id: " + id);
		try {
			RefPartenaire instance = entityManager.find(RefPartenaire.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.RefPartenaireDao#findByIdContrat(int, boolean)
	 */
	@Override
	public List<RefPartenaire> findByIdContrat(int idContrat) {
		
		log.debug("getting List of RefPartenaire instance with : " + idContrat);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPartenaire r ");
			request.append("where r.refContrat.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idContrat);
			List<RefPartenaire> resultList = (List<RefPartenaire>) query
					.getResultList();
			log.debug("findByIdContrat successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdContrat failed", re);
			throw re;
		}

		
	}

	@Override
	public List<RefPartenaire> findByCodeContrat(String idfContrat) {
		if(idfContrat == null) {
			return null;
		}
		log.debug("getting List of RefPartenaire instance with : " + idfContrat);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPartenaire r ");
			request.append("where lower(r.refContrat.identifiant) = " + UtilConstant.quotedString(idfContrat.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefPartenaire> resultList = (List<RefPartenaire>) query
					.getResultList();
			log.debug("findByIdContrat successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdContrat failed", re);
			throw re;
		}
	}

	/**
	 * [RefPartenaireDao.findPartenairesStructure] Method 
	 * Overridden By : @author rlaib  on : 7 janv. 2015  10:00:00
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<RefPartenaire> findPartenairesStructure(Integer idStructure) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<RefPartenaire> c = cb.createQuery(RefPartenaire.class);
			Root<RefPartenaire> p = c.from(RefPartenaire.class);
			Join<RefPartenaire, RefContrat> pc = p.join("refContrat");
			Join<RefContrat, RefStructure> cs = pc.join("refStructureByService");
			c.select(p).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(cs.get("id"), idStructure);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<RefPartenaire> q = entityManager.createQuery(c);
			List<RefPartenaire> result = q.getResultList();
				for(RefPartenaire entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findPartenairesStructure 'RefPartenaire' failed", re);
				throw re;
		}
	}
}
