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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite.GenericFveDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.recherche.GroupeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Groupe;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;

/**
 * @author rlaib  on : 16 d�c. 2014  14:07:53
 */
@Service ("groupeDao")
public class GroupeDaoImpl extends GenericFveDaoImpl<Groupe> implements
			GroupeDao {
	
	private static final Logger log = LoggerFactory.getLogger(GroupeDaoImpl.class.getName());

	/**
	 * [GroupeDao.findStructureRechercheGroupes] Method 
	 * @author Rafik  on : 21 déc. 2014  12:31:58
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<Groupe> findStructureRechercheGroupes(Long idStructure) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Groupe> c = cb.createQuery(Groupe.class);
			Root<Groupe> g = c.from(Groupe.class);
			Join<Groupe, Structure> gs = g.join("structure");
			c.select(g).distinct(true);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if(idStructure != null) {
				ParameterExpression<Long> p = cb.parameter(Long.class, "idStructure");
				criteria.add(cb.equal(gs.get("id"), p));
			}
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Groupe> q = entityManager.createQuery(c);
			if (idStructure != null) { 
				q.setParameter("idStructure", idStructure);
			}
			List<Groupe> result = q.getResultList();
				for(Groupe entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findStructureRechercheGroupes 'Groupe' failed", re);
				throw re;
		}
	}


}
