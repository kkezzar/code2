package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.bpm.ActionEntiteDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.Action;
import dz.gov.mesrs.sii.commons.data.model.bpm.ActionEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.Entite;

@Service ("actionEntiteDao")
public class ActionEntiteDaoImpl implements ActionEntiteDao {

	private static final Logger log = LoggerFactory.getLogger(ActionDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@Override
	public ActionEntite findById(int id) {
		log.debug("getting ActionEntite instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(ActionEntite.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ActionEntite> findByEntityCode(String entityCode) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ActionEntite> c = cb.createQuery(ActionEntite.class);
			Root<ActionEntite> ae = c.from(ActionEntite.class);
			Join<ActionEntite, Entite> aee = ae.join("entite");
			c.select(ae).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(aee.get("code"), entityCode);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<ActionEntite> q = entityManager.createQuery(c);
			List<ActionEntite> result = q.getResultList();
			return result;
		}
		catch (RuntimeException re) {

				log.error("findByEntityCode 'ActionEntite' failed", re);
				throw re;
		}
	}

	@Override
	public ActionEntite findByEntityCodeByActionCode(String entityCode,
			String actionCode) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ActionEntite> c = cb.createQuery(ActionEntite.class);
			Root<ActionEntite> ae = c.from(ActionEntite.class);
			Join<ActionEntite, Entite> aee = ae.join("entite");
			Join<ActionEntite, Action> aea = ae.join("action");
			c.select(ae).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate c1 = cb.equal(aee.get("code"), entityCode);
			criteria.add(c1);
			Predicate c2 = cb.equal(aea.get("code"), actionCode);
			criteria.add(c2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<ActionEntite> q = entityManager.createQuery(c);
			List<ActionEntite> result = q.getResultList();
			if(result != null && result.size()==1)
				return result.get(0);
			return null;
		}
		catch (RuntimeException re) {

				log.error("findByEntityCodeByActionCode 'ActionEntite' failed", re);
				throw re;
		}
	}

}
