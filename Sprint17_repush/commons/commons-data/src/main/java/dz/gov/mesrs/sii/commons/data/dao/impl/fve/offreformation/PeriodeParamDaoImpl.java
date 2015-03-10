/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.PeriodeParamDaoImpl.java] 
 * @author rlaib on : 2 oct. 2014  14:14:55
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeParamDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.PeriodeParam;

/**
 * @author rlaib  on : 2 oct. 2014  14:14:55
 */
@Service ("periodeParamDao")
public class PeriodeParamDaoImpl implements PeriodeParamDao {

	private static final Logger log = LoggerFactory.getLogger(PeriodeParamDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	/**
	 * [PeriodeParamDaoImpl.persist] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @param transientInstance
	 */
	@Override
	public void persist(PeriodeParam transientInstance) {

		log.debug("persisting PeriodeParam instance");
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist PeriodeParam failed", re);
					throw re;
		}

	}

	/**
	 * [PeriodeParamDaoImpl.remove] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @param id
	 */
	@Override
	public void remove(int id) {

		log.debug("removing Periode instance");
		try {
					PeriodeParam persistentInstance = entityManager.find(PeriodeParam.class, id);
					entityManager.remove(persistentInstance);
					log.debug("remove PeriodeParam successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove PeriodeParam failed", re);
					throw re;
		}

	}

	/**
	 * [PeriodeParamDaoImpl.merge] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public PeriodeParam merge(PeriodeParam detachedInstance) {
		log.debug("merging PeriodeParam instance");
		try {
						log.debug("merge successful");
						return entityManager.merge(detachedInstance);
						
		} catch (RuntimeException re) {
						log.error("merge PeriodeParam failed", re);
						throw re;
		}
	}

	/**
	 * [PeriodeParamDaoImpl.findById] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @param id
	 * @return
	 */
	@Override
	public PeriodeParam findById(int id) {
		log.debug("getting PeriodeParam instance with id: " + id);
		try {			
					log.debug("get successful");
					return entityManager.find(PeriodeParam.class, id);
					
		} catch (RuntimeException re) {
			
					log.error("findById PeriodeParam failed", re);
					throw re;
		}
	}

	/**
	 * [PeriodeParamDaoImpl.findAll] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @return
	 */
	@Override
	public List<PeriodeParam> findAll() {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<PeriodeParam> c = cb.createQuery(PeriodeParam.class);
			Root<PeriodeParam> pp = c.from(PeriodeParam.class);
			c.select(pp).distinct(true);
			c.orderBy(cb.asc(pp.get("libelle")));
			TypedQuery<PeriodeParam> q = entityManager.createQuery(c);
			List<PeriodeParam> result = q.getResultList();
			for(PeriodeParam entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
		}
		catch (RuntimeException re) {

			log.error("findAll 'PeriodeParam' failed", re);
			throw re;
		}
	}

	/**
	 * [PeriodeParamDaoImpl.findAdvanced] Method 
	 * @author rlaib  on : 2 oct. 2014  14:14:55
	 * @param periodeParam
	 * @return
	 */
	@Override
	public List<PeriodeParam> findByPeriodByYear(int idPeriod ,int idYear) {
		
		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<PeriodeParam> c = cb.createQuery(PeriodeParam.class);
					Root<PeriodeParam> pp = c.from(PeriodeParam.class);
					Join<PeriodeParam, Periode> ppp = pp.join("periode");
					Join<PeriodeParam, AnneeAcademique> ppaa = pp.join("anneeAcademique");
					c.select(pp).distinct(true);
					c.orderBy(cb.asc(pp.get("libelle")));
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(ppp.get("id"), idPeriod);
					criteria.add(condition1);
					Predicate condition2 = cb.equal(ppaa.get("id"), idYear);
					criteria.add(condition2);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<PeriodeParam> q = entityManager.createQuery(c);
					List<PeriodeParam> result = q.getResultList();
					for(PeriodeParam entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return result;
			}
			catch (RuntimeException re) {

				log.error("findAdvanced 'findByPeriodByYear' failed", re);
				throw re;
			}
	}
	@Override
	public  PeriodeParam findByCodeByPeriodByYear(String codeParam, int idYear, int idPeriod) {
		
		try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<PeriodeParam> c = cb.createQuery(PeriodeParam.class);
			Root<PeriodeParam> pp = c.from(PeriodeParam.class);
			Join<PeriodeParam, Periode> ppp = pp.join("periode");
			Join<PeriodeParam, AnneeAcademique> ppaa = pp.join("anneeAcademique");
			c.select(pp).distinct(true);
			c.orderBy(cb.asc(pp.get("libelle")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ppp.get("id"), idPeriod);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(ppaa.get("id"), idYear);
			criteria.add(condition2);
			Predicate condition3 = cb.equal(pp.get("code"), codeParam);
			criteria.add(condition3);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<PeriodeParam> q = entityManager.createQuery(c);
			List<PeriodeParam> result = q.getResultList();
			if(result != null && result.size()>0)
				return result.get(0);
			else 
				return null;
		}
		catch (RuntimeException re) {
	
			log.error("findByCodeByPeriodByYear 'PeriodeParam' failed", re);
			throw re;
		}
	}

	/**
	 * [PeriodeParamDao.findByPeriodiciteByYear] Method 
	 * @author rlaib  on : 1 nov. 2014  16:08:31
	 * @param idYear
	 * @param codePeriodicite
	 * @return
	 */
	@Override
	public List<PeriodeParam> findByPeriodiciteByYear(int idYear,
			String codePeriodicite) {


		try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<PeriodeParam> c = cb.createQuery(PeriodeParam.class);
					Root<PeriodeParam> pp = c.from(PeriodeParam.class);
					Join<PeriodeParam, Periode> ppp = pp.join("periode");
					Join<PeriodeParam, AnneeAcademique> ppaa = pp.join("anneeAcademique");
					c.select(pp).distinct(true);
					c.orderBy(cb.asc(pp.get("libelle")));
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(ppp.get("refCodePeriodicite"), codePeriodicite);
					criteria.add(condition1);
					Predicate condition2 = cb.equal(ppaa.get("id"), idYear);
					criteria.add(condition2);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
					TypedQuery<PeriodeParam> q = entityManager.createQuery(c);
					List<PeriodeParam> result = q.getResultList();
					for(PeriodeParam entity: result){
				        if(entityManager.contains(entity)){
				        	entityManager.refresh(entity);
				        }
				    }
					return result;
			}
			catch (RuntimeException re) {

				log.error("findByPeriodiciteByYear 'PeriodeParam' failed", re);
				throw re;
			}
	}

	/**
	 * [PeriodeParamDao.findByCodeByPeriodiciteByYear] Method 
	 * @author rlaib  on : 1 nov. 2014  16:13:14
	 * @param codeParam
	 * @param idYear
	 * @param codePeriodicite
	 * @return
	 */
	@Override
	public PeriodeParam findByCodeByPeriodiciteByYear(String codeParam,
			int idYear, String codePeriodicite) {
try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<PeriodeParam> c = cb.createQuery(PeriodeParam.class);
			Root<PeriodeParam> pp = c.from(PeriodeParam.class);
			Join<PeriodeParam, Periode> ppp = pp.join("periode");
			Join<PeriodeParam, AnneeAcademique> ppaa = pp.join("anneeAcademique");
			c.select(pp).distinct(true);
			c.orderBy(cb.asc(pp.get("libelle")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(ppp.get("refCodePeriodicite"), codePeriodicite);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(ppaa.get("id"), idYear);
			criteria.add(condition2);
			Predicate condition3 = cb.equal(pp.get("code"), codeParam);
			criteria.add(condition3);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<PeriodeParam> q = entityManager.createQuery(c);
			List<PeriodeParam> result = q.getResultList();
			if(result != null && result.size()>0)
				return result.get(0);
			else 
				return null;
		}
		catch (RuntimeException re) {
	
			log.error("findByCodeByPeriodiciteByYear 'PeriodeParam' failed", re);
			throw re;
		}
	}
}
