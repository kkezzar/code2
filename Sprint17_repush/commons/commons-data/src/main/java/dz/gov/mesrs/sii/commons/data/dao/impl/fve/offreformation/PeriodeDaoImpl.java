/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.offreformation.NiveauDaoImpl.java] 
 * @author rlaib on : 16 juil. 2014  10:26:17
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
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.PeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.PeriodeParam;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author rlaib  on : 16 juil. 2014  10:26:17
 */
@Service ("periodeDao")
public class PeriodeDaoImpl implements PeriodeDao {

	private static final Logger log = LoggerFactory.getLogger(PeriodeDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	/**
	 * [PeriodeDaoImpl.persist] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @param transientInstance
	 */
	@Override
	public void persist(Periode transientInstance) {

		log.debug("persisting Periode instance");
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist Periode failed", re);
					throw re;
		}
	}

	/**
	 * [PeriodeDaoImpl.remove] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @param persistentInstance
	 */
	@Override
	public void remove(int id) {
		
		log.debug("removing Periode instance");
		try {
					Periode persistentInstance = entityManager.find(Periode.class, id);
					entityManager.remove(persistentInstance);
					log.debug("remove successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove Periode failed", re);
					throw re;
		}
	}

	/**
	 * [PeriodeDaoImpl.merge] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @param detachedInstance
	 * @return
	 */
	@Override
	public Periode merge(Periode detachedInstance) {
		
		log.debug("merging Periode instance");
		try {
						log.debug("merge successful");
						return entityManager.merge(detachedInstance);
						
		} catch (RuntimeException re) {
						log.error("merge Periode failed", re);
						throw re;
		}
	}

	/**
	 * [PeriodeDaoImpl.findById] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @param id
	 * @return
	 */
	@Override
	public Periode findById(int id) {
		
		log.debug("getting Periode instance with id: " + id);
		try {			
					log.debug("get successful");
					return entityManager.find(Periode.class, id);
					
		} catch (RuntimeException re) {
			
					log.error("findById Periode failed", re);
					throw re;
		}
	}

	/**
	 * [PeriodeDaoImpl.findAll] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @return
	 */
	@Override
	public List<Periode> findAll() {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("libelleLongLt")));
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
		}
		catch (RuntimeException re) {

			log.error("findAll 'Periode' failed", re);
			throw re;
		}
	}

	/**
	 * [PeriodeDaoImpl.findByCycleId] Method 
	 * @author rlaib  on : 16 juil. 2014  10:26:17
	 * @param idCycle
	 * @return
	 */
	@Override
	public List<Periode> findByLevelId(int idLevel) {
		
		try {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
				Root<Periode> p = c.from(Periode.class);
				Join<Periode, Niveau> pn = p.join("niveau");
				c.select(p).distinct(true);
				c.orderBy(cb.asc(p.get("rang")));
				List<Predicate> criteria = new ArrayList<Predicate>();
				Predicate condition1 = cb.equal(pn.get("id"), idLevel);
				criteria.add(condition1);
				c.where(cb.and(criteria.toArray(new Predicate[0])));
				TypedQuery<Periode> q = entityManager.createQuery(c);
				List<Periode> result = q.getResultList();
				for(Periode entity: result){
			        if(entityManager.contains(entity)){
			        	entityManager.refresh(entity);
			        }
			    }
				return result;
		}
		catch (RuntimeException re) {
		
				log.error("findByLevelId 'Periode' failed", re);
				throw re;
		}
	}
	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.persistence.offreformation.PeriodeDao#findByCycleId(int)
	 */
	@Override
	public  List<Periode> findByCycleId(int idCycle) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			Join<Periode, Niveau> pn = p.join("niveau");
			Join<Niveau, Cycle> nc = pn.join("cycle");
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(nc.get("id"), idCycle);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findByCycleId 'Periode' failed", re);
			throw re;
		}
	}

	/**
	 * [PeriodeDao.findCurrentsByLevelId] Method 
	 * @author rlaib  on : 21 oct. 2014  07:59:34
	 * @param idLevel
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public List<Periode> findCurrentsByLevelId(int idLevel) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			Join<Periode, Niveau> pn = p.join("niveau");
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("rang")));
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition0 = cb.equal(pn.get("id"), idLevel);
			criteria.add(condition0);
			
					Subquery<PeriodeParam> subquery = c.subquery(PeriodeParam.class);
					Root<PeriodeParam> pp = subquery.from(PeriodeParam.class);
					subquery.select(pp);
					
					List<Predicate> subCriteria = new ArrayList<Predicate>();
					List<Predicate> criteria1 = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(pp.get("periode"),p);
					criteria1.add(condition1);
					
					Predicate condition2= cb.equal(pp.get("code"), UtilConstant.PERIODE_PARAM_CODE_DATE_DEBUT);
					criteria1.add(condition2);
			
					subquery.where(cb.and(criteria1.toArray(new Predicate[0])));
					subCriteria.add(cb.exists(subquery)); 
				
			Predicate where = cb.conjunction();
			if(criteria != null && criteria.size()>0) {
				where = cb.and(where,cb.and(criteria.toArray(new Predicate[0])));
			}
			if(subCriteria != null && subCriteria.size()>0) {
				where = cb.and(where, cb.and(subCriteria.toArray(new Predicate[0])));
			}
		
			c.where(where);
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			return result;
	}
	catch (RuntimeException re) {
	
			log.error("findCurrentsByLevelId 'Periode' failed", re);
			throw re;
	}
	}

	/**
	 * [PeriodeDao.findByPeriodiciteCode] Method 
	 * @author rlaib  on : 1 nov. 2014  16:45:45
	 * @param periodiciteCode
	 * @return
	 */
	@Override
	public List<Periode> findByPeriodiciteCode(String periodiciteCode) {
		// TODO Auto-generated method stub

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(p.<String>get("refCodePeriodicite"), periodiciteCode);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
				if(entityManager.contains(entity)){
					entityManager.refresh(entity);
				}
			}
			return result;
		}
		catch (RuntimeException re) {
			
			log.error("findByPeriodiciteCode 'Periode' failed", re);
			throw re;
		}
	}

	/**
	 * [PeriodeDao.findByLevelIdByRefPeriodeCode] Method 
	 * @author rlaib  on : 2 nov. 2014  09:46:39
	 * @param idLevel
	 * @param refCodePeriode
	 * @return
	 */
	@Override
	public Periode findByLevelIdByRefPeriodeCode(int idLevel,
			String refCodePeriode) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			Join<Periode, Niveau> pn = p.join("niveau");
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(pn.get("id"), idLevel);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(p.get("refCodePeriode"), refCodePeriode);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			if(result != null && result.size() == 1)
				return result.get(0);
			return null;
	}
	catch (RuntimeException re) {
	
			log.error("findByLevelIdByRefPeriodeCode 'Periode' failed", re);
			throw re;
	}
	}

	
	@Override
	public Periode findByLevelIdByIdNcPeriode(Integer idLevel, String idNcPeriode) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Periode> c = cb.createQuery(Periode.class);
			Root<Periode> p = c.from(Periode.class);
			Join<Periode, Niveau> pn = p.join("niveau");
			Join<Periode, Nomenclature> pnc = p.join("ncPeriode");
			c.select(p).distinct(true);
			c.orderBy(cb.asc(p.get("rang")));
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(pn.get("id"), idLevel);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(pnc.get("id"), idNcPeriode);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			TypedQuery<Periode> q = entityManager.createQuery(c);
			List<Periode> result = q.getResultList();
			for(Periode entity: result){
		        if(entityManager.contains(entity)){
		        	entityManager.refresh(entity);
		        }
		    }
			if(result != null && result.size() == 1)
				return result.get(0);
			return null;
	}
	catch (RuntimeException re) {
	
			log.error("findByLevelIdByIdNcPeriode 'Periode' failed", re);
			throw re;
	}
	}
}
