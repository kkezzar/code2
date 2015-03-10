/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.DossierBachelierDaoImpl.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:12:36
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.AffectationBachelierDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AffectationBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.TypeImportation;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:12:36
 */
@Service ("affectationBachelierDao")
public class AffectationBachelierDaoImpl implements AffectationBachelierDao {

	private static final Logger log = LoggerFactory.getLogger(AffectationBachelierDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public void persist(AffectationBachelier transientInstance) {
		
		log.debug("persisting AffectationBachelier instance");
		
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist failed", re);
					throw re;
		}

	}

	@Override
	public void remove(AffectationBachelier persistentInstance) {

		log.debug("removing DossierBachelier instance");
		
		try {
					entityManager.remove(persistentInstance);
					log.debug("remove successful");
					
		} catch (RuntimeException re) {
					
					log.error("remove failed", re);
					throw re;
		}

	}

	@Override
	public AffectationBachelier merge(AffectationBachelier detachedInstance) {
		
				log.debug("merging AffectationBachelier instance");
				try {
								log.debug("merge successful");
								return entityManager.merge(detachedInstance);
								
				} catch (RuntimeException re) {
								log.error("merge failed", re);
								throw re;
		}
	}

	@Override
	public AffectationBachelier findById(int id) {
		
		log.debug("getting AffectationBachelier instance with id: " + id);
		try {			
					log.debug("get successful");
					return entityManager.find(AffectationBachelier.class, id);
					
		} catch (RuntimeException re) {
			
					log.error("get failed", re);
					throw re;
		}
	}

	@Override
	public  AffectationBachelier findByMatriculeByIdImport(String matricule, int idImport){
		
			try {
					CriteriaBuilder cb = entityManager.getCriteriaBuilder();
					CriteriaQuery<AffectationBachelier> c = cb.createQuery(AffectationBachelier.class);
					Root<AffectationBachelier> aff = c.from(AffectationBachelier.class);
					Join<AffectationBachelier, Importation> affImp = aff.join("importation");
					Join<Importation, TypeImportation> it = affImp.join("typeImportation");

					c.select(aff).distinct(true);
			
					List<Predicate> criteria = new ArrayList<Predicate>();
					Predicate condition1 = cb.equal(aff.get("matriculeBachelier"), matricule);
					Predicate condition2 = cb.equal(affImp.get("id"), idImport);
					Predicate condition3 = cb.equal(affImp.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
					Predicate condition4 = cb.equal(it.get("code"), UtilConstant.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE);
					criteria.add(condition1);
					criteria.add(condition2);
					criteria.add(condition3);
					criteria.add(condition4);
					c.where(cb.and(criteria.toArray(new Predicate[0])));
			
					TypedQuery<AffectationBachelier> q = entityManager.createQuery(c);
					try {
						return q.getSingleResult();
					}
					catch (NoResultException nre) {
							return null;
					}
			}
			catch (RuntimeException re) {
		
					log.error("findByTypeByYear 'Importation' failed", re);
					return null;
			}
	}
	
	@Override
	public List<AffectationBachelier> findByImportId(int idImport) {
		
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AffectationBachelier> c = cb.createQuery(AffectationBachelier.class);
			Root<AffectationBachelier> ab = c.from(AffectationBachelier.class);
			Join<AffectationBachelier, Importation> abi = ab.join("importation");
			c.select(ab).distinct(true);
			
			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(abi.get("id"), idImport);
			criteria.add(condition1);
			c.where(cb.and(criteria.toArray(new Predicate[0])));
			 
			TypedQuery<AffectationBachelier> q = entityManager.createQuery(c);
			return q.getResultList();
			
		}
		catch (RuntimeException re) {
		
				log.error("findByImportId 'AffectationBachelier' failed", re);
				return null;
		}
	}

	@Override
	public List<AffectationBachelier> findByYearAndEtablissement(String year,
			String codeEtablissement) {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AffectationBachelier> c = cb.createQuery(AffectationBachelier.class);
			Root<AffectationBachelier> aff = c.from(AffectationBachelier.class);
			Join<AffectationBachelier, Importation> affImp = aff.join("importation");
			Join<Importation, TypeImportation> it = affImp.join("typeImportation");
			c.select(aff).distinct(true);
			Predicate predicate = null;
			predicate = cb.equal(it.get("code"), UtilConstant.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE);
			predicate = cb.and(predicate, cb.equal(affImp.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE));
			if (year != null) {
				predicate = cb
						.and(predicate, cb.equal(
								affImp.get("anneeBac"), year));
			} 
			if (codeEtablissement != null) {
				predicate = cb
						.and(predicate, cb.equal(
								aff.get("refCodeEtablissementAffectation"), codeEtablissement));
			} 
			
			if (predicate != null)
				c.where(predicate);

			TypedQuery<AffectationBachelier> _query = entityManager
					.createQuery(c);
			List<AffectationBachelier> _resultList = (List<AffectationBachelier>) _query
					.getResultList();

			return _resultList;
	}
	catch (RuntimeException re) {

			log.error("findByTypeByYear 'AffectationBachelier' failed", re);
			return null;
	}
	}
	
	@Override
	public List<AffectationBachelier> findByIdImportByEtablissement(int idImport
			,  String codeEtablissement){
		try {
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AffectationBachelier> c = cb.createQuery(AffectationBachelier.class);
			Root<AffectationBachelier> aff = c.from(AffectationBachelier.class);
			Join<AffectationBachelier, Importation> affImp = aff.join("importation");
			Join<Importation, TypeImportation> it = affImp.join("typeImportation");
			c.select(aff).distinct(true);
			Predicate predicate = null;
			predicate = cb.equal(it.get("code"), UtilConstant.BAC_IMPORT_TYPE_BACHELOR_AFFECTATION_CODE);
			predicate = cb.and(predicate, cb.equal(affImp.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE));
			predicate = cb.and(predicate, cb.equal(affImp.get("id"), idImport));
			if (codeEtablissement != null) {
				predicate = cb
						.and(predicate, cb.equal(
								aff.get("refCodeEtablissementAffectation"), codeEtablissement));
			} 
			
			if (predicate != null)
				c.where(predicate);

			TypedQuery<AffectationBachelier> _query = entityManager
					.createQuery(c);
			List<AffectationBachelier> _resultList = (List<AffectationBachelier>) _query
					.getResultList();

			return _resultList;
	}
	catch (RuntimeException re) {

			log.error("findByIdImportByEtablissement 'AffectationBachelier' failed", re);
			return null;
	}
	}
}
