/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.scolarite.EnseignantVoeuDaoImpl.java] 
 * @author rlaib on : 12 oct. 2014  15:54:23
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EnseignantVoeuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantFicheVoeux;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.EnseignantVoeu;

/**
 * @author rlaib  on : 12 oct. 2014  15:54:23
 */
@Service ("enseignantVoeuDao")
public class EnseignantVoeuDaoImpl extends GenericFveDaoImpl<EnseignantVoeu> implements
		EnseignantVoeuDao {
	private static final Logger log = LoggerFactory.getLogger(EnseignantVoeuDaoImpl.class.getName());
	/**
	 * [EnseignantVoeuDao.findByIdFiche] Method 
	 * @author rlaib  on : 14 oct. 2014  14:12:01
	 * @param idFiche
	 * @return
	 */
	@Override
	public List<EnseignantVoeu> findByIdFiche(int idFiche) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<EnseignantVoeu> c = cb.createQuery(EnseignantVoeu.class);
			Root<EnseignantVoeu> ev = c.from(EnseignantVoeu.class);
			Join<EnseignantVoeu, EnseignantFicheVoeux> eev = ev.join("enseignantFicheVoeux");
			c.select(ev).distinct(true);
			c.orderBy(cb.asc(ev.get("priorite")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(eev.get("id"), idFiche);
			criteria.add(condition1);
	
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			
			TypedQuery<EnseignantVoeu> q = entityManager.createQuery(c);
			List<EnseignantVoeu> result = q.getResultList();
				for(EnseignantVoeu entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findByIdFiche 'EnseignantVoeu' failed", re);
				throw re;
		}
	}

	
}
