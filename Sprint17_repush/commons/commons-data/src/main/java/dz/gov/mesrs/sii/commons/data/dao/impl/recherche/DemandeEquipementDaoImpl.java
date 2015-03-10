/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.recherche.ProgrammeDaoImpl.java] 
 * @author rlaib on : 25 janv. 2015  11:14:12
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.DemandeEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeBudget;
import dz.gov.mesrs.sii.commons.data.model.recherche.DemandeEquipement;

/**
 * @author rlaib  on : 25 janv. 2015  11:14:12
 */
@Service ("demandeEquipementDao")
public class DemandeEquipementDaoImpl extends GenericFveDaoImpl<DemandeEquipement> implements DemandeEquipementDao {

	private static final Log log = LogFactory.getLog(DemandeEquipementDaoImpl.class);
	
	@Override
	public List<DemandeEquipement> findByIdDemand(Long idDemand) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DemandeEquipement> c = cb.createQuery(DemandeEquipement.class);
			Root<DemandeEquipement> de = c.from(DemandeEquipement.class);
			Join<DemandeEquipement, DemandeBudget> dep= de.join("demandeBudget");
			c.select(de).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idDemand != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idDemand");
				criteria.add(cb.equal(dep.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));                            
			TypedQuery<DemandeEquipement> q = entityManager.createQuery(c);
			if (idDemand != null) { 
				q.setParameter("idDemand", idDemand);
			}
			List<DemandeEquipement> result = q.getResultList();
			for(DemandeEquipement entity: result){
				if(entityManager.contains(entity)){
						entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			log.error("findByIdDemand 'DemandeEquipement' failed", re);
			throw re;
		}
	}
	
}
