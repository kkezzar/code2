/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.CycleDaoImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:16:27
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDiplomeDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.CycleDiplome;

@Service ("cycleDiplomeDao")	
public class CycleDiplomeDaoImpl extends GenericFveDaoImpl<CycleDiplome> implements CycleDiplomeDao {

	private static final Logger log = LoggerFactory.getLogger(CycleDiplomeDaoImpl.class.getName());	/**
	 * [CycleDiplomeDao.findDiplomesByCycle] Method 
	 * @author rlaib  on : 24 nov. 2014  14:25:47
	 * @param cycleId
	 * @return
	 */
	@Override
	public List<CycleDiplome> findDiplomesByCycle(Integer cycleId) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CycleDiplome> c = cb.createQuery(CycleDiplome.class);
			Root<CycleDiplome> cd = c.from(CycleDiplome.class);
			Join<CycleDiplome,Cycle> cdc = cd.join("cycle"); 
			c.select(cd).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition = cb.equal(cdc.get("id"), cycleId);
			criteria.add(condition);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			c.orderBy(cb.asc(cd.get("rang")));
			TypedQuery<CycleDiplome> q = entityManager.createQuery(c);
			List<CycleDiplome> result = q.getResultList();
			for(CycleDiplome entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findByTypeFormationCode 'Cycle' failed", re);
			throw re;
		}
	}
	
}
