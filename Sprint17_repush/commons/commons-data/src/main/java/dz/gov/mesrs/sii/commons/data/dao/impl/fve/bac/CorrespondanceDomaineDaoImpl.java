/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.CorrespondanceDomaineDaoImpl.java] 
 * @author rlaib on : 18 juin 2014  14:25:38
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CorrespondanceDomaineDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CorrespondanceDomaine;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;

/**
 * @author rlaib  on : 18 juin 2014  14:25:38
 */
@Service ("correspondanceDomaineDao")
public class CorrespondanceDomaineDaoImpl implements CorrespondanceDomaineDao {

	private static final Logger log = LoggerFactory.getLogger(CorrespondanceDomaineDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	
	/**
	 * [CorrespondanceDomaineDaoImpl.findAll] Method 
	 * @author rlaib  on : 18 juin 2014  14:25:38
	 * @return
	 */
	@Override
	public List<CorrespondanceDomaine> findAll() {

		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<CorrespondanceDomaine> c = cb.createQuery(CorrespondanceDomaine.class);
					Root<CorrespondanceDomaine> cd = c.from(CorrespondanceDomaine.class);
					c.select(cd).distinct(true);
					c.orderBy(cb.asc(cd.get("id")));
					TypedQuery<CorrespondanceDomaine> q = entityManager.createQuery(c);
					return q.getResultList();
		}
		catch (RuntimeException re) {

					log.error("findAll 'CorrespondanceDomaine' failed", re);
					throw re;
		}
	}

	/**
	 * [CorrespondanceDomaineDaoImpl.findAdvanced] Method 
	 * @author rlaib  on : 24 juin 2014  15:15:06
	 * @return
	 */
	@Override
	public  List<CorrespondanceDomaine> findAdvanced(Map<String, String> attributesDto) {
		
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<CorrespondanceDomaine> c = cb.createQuery(CorrespondanceDomaine.class);
				Root<CorrespondanceDomaine> cd = c.from(CorrespondanceDomaine.class);
				c.select(cd).distinct(true);
				
				List<Predicate> criteriaAnd = new ArrayList<Predicate>();
				for(String s : attributesDto.keySet())
				{
						if(cd.get(s) != null){
							criteriaAnd.add(cb.equal(cd.<String>get(s), attributesDto.get(s)));
		
						}
				}
				Predicate where = cb.conjunction();
				if(criteriaAnd != null && criteriaAnd.size()>0)
					where = cb.and(where,cb.and(criteriaAnd.toArray(new Predicate[0])));
			
				c.where(where);
				TypedQuery<CorrespondanceDomaine> q = entityManager.createQuery(c);
				List<CorrespondanceDomaine> result = q.getResultList();
				for(CorrespondanceDomaine entity: result){
			        if(entityManager.contains(entity)){
			        	entityManager.refresh(entity);
			        }
			    }
				return q.getResultList();
		
		}
		catch (RuntimeException re) {
	
					log.error("findAll 'CorrespondanceDomaine' failed", re);
					throw re;
		}
	}

	/**
	 * [CorrespondanceDomaineDaoImpl.getFilieres] Method 
	 * @author rlaib  on : 29 sept. 2014  10:33:47
	 * @return
	 */
	@Override
	public List<CorrespondanceDomaine> getFilieres() {

		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<CorrespondanceDomaine> c = cb.createQuery(CorrespondanceDomaine.class);
					Root<CorrespondanceDomaine> cd = c.from(CorrespondanceDomaine.class);
					c.select(cd).distinct(true);
					c.orderBy(cb.asc(cd.get("libelleNiveau2")));
					TypedQuery<CorrespondanceDomaine> q = entityManager.createQuery(c);
					return q.getResultList();
		}
		catch (RuntimeException re) {

					log.error("findAll 'CorrespondanceDomaine' failed", re);
					throw re;
		}
	}

	/**
	 * [CorrespondanceDomaineDaoImpl.getFilieresByEtab] Method 
	 * @author rlaib  on : 29 sept. 2014  10:33:53
	 * @param codeEtab
	 * @param year
	 * @return
	 */
	@Override
	public  List<CorrespondanceDomaine> getFilieresByEtab(String codeEtab, String year) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<CorrespondanceDomaine> c = cb.createQuery(CorrespondanceDomaine.class);
			Root<CorrespondanceDomaine> cd = c.from(CorrespondanceDomaine.class);
			Root<DossierBachelier> db = c.from(DossierBachelier.class);
			Join<DossierBachelier,Importation> dbi = db.join("importation");
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(cd.get("ancienCode"), db.get("refCodeFiliereAffectation"));
			Predicate condition2 = cb.equal(db.get("refCodeEtablissementAffectation"),codeEtab);
			Predicate condition3 = cb.equal(dbi.get("anneeBac"),year);
			criteria.add(condition1);
			criteria.add(condition2);
			criteria.add(condition3);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			c.select(cd).distinct(true);
			c.orderBy(cb.asc(cd.get("libelleNiveau2")));
			TypedQuery<CorrespondanceDomaine> q = entityManager.createQuery(c);
			return q.getResultList();
		}
		catch (RuntimeException re) {
			log.error("findAll 'getFilieresByEtab' failed", re);
			throw re;
		}
	}

}
