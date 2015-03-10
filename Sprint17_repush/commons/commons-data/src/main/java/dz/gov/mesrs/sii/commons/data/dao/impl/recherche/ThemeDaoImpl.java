package dz.gov.mesrs.sii.commons.data.dao.impl.recherche;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
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
import dz.gov.mesrs.sii.commons.data.dao.recherche.ThemeDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.Structure;
import dz.gov.mesrs.sii.commons.data.model.recherche.Theme;

/**
 * @author rlaib  on : 16 déc. 2014  14:07:53
 */
@Service ("themeDao")
public class ThemeDaoImpl extends GenericFveDaoImpl<Theme> implements
ThemeDao {
	
	private static final Logger log = LoggerFactory.getLogger(ThemeDaoImpl.class.getName());

	/**
	 * [ThemeDao.findStructureRechercheThemes] Method 
	 * Overridden By : @author rlaib  on : 22 déc. 2014  14:28:01
	 * @param idStructure
	 * @return
	 */
	@Override
	public List<Theme> findStructureRechercheThemes(Long idStructure) {
	
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Theme> c = cb.createQuery(Theme.class);
				Root<Theme> t = c.from(Theme.class);
				Join<Theme, Structure> ts= t.join("structure");
				c.select(t).distinct(true);
				List<Predicate> criteria = new ArrayList<Predicate>();
				if(idStructure != null) {
					ParameterExpression<Long> param = cb.parameter(Long.class, "idStructure");
					criteria.add(cb.equal(ts.get("id"), param));
				}
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				TypedQuery<Theme> q = entityManager.createQuery(c);
				if (idStructure != null) { 
					q.setParameter("idStructure", idStructure);
				}
				List<Theme> result = q.getResultList();
				for(Theme entity: result){
					if(entityManager.contains(entity)){
							entityManager.refresh(entity);
					}
				}
				return result;
		}
		catch (RuntimeException re) {

				log.error("findStructureRechercheThemes 'Theme' failed", re);
				throw re;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theme> findThemesByStructureGroup(Long structureId,Long groupeId) {
		if(structureId!= null && groupeId != null){
		Query query= entityManager.createQuery("select c from Theme c where c.structure.id = :structureId and c.groupe.id = :groupeId");
		query.setParameter("structureId",structureId).setParameter("groupeId",groupeId);
		if( query.getResultList()!= null)return query.getResultList();
		else return new ArrayList<Theme>();
		}else return new ArrayList<Theme>();
	
	
	}


}
