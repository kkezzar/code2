package dz.gov.mesrs.sii.commons.data.dao.impl.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.bpm.EtapeProcessusDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.bpm.Etape;
import dz.gov.mesrs.sii.commons.data.model.bpm.Processus;


@Service ("etapeProcessusDao")
public class EtapeProcessusDaoImpl extends GenericFveDaoImpl<Etape> implements EtapeProcessusDao {

	private static final Logger log = LoggerFactory.getLogger(EtapeProcessusDaoImpl.class.getName());

	@Override
	public List<Etape> findByIdProcessus(Integer idProcessus) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Etape> c = cb.createQuery(Etape.class);
			Root<Etape> e = c.from(Etape.class);
			Join<Etape, Processus> ep = e.join("processus");
			c.select(e).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ep.get("id"), idProcessus);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Etape> q = entityManager.createQuery(c);
			List<Etape> result = q.getResultList();
				for(Etape entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByCodeEntite 'Processus' failed", re);
				throw re;
		}
	}

	
}
