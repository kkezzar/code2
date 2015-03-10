/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.bac.DossierBachelierDaoImpl.java] 
 * @author  Rafik LAIB on : 21 mai 2014  14:12:36
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.bac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;




import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.CorrespondanceDomaineDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.bac.DossierBachelierDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.AnneeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.CycleDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.NiveauDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDomaineDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFiliereLmdDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTypePieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.AffectationBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.CorrespondanceDomaine;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Generation;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.Importation;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.PieceConstitutive;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  14:12:36
 */
@Service ("dossierBachelierDao")
public class DossierBachelierDaoImpl implements DossierBachelierDao {

	private static final Logger log = LoggerFactory.getLogger(DossierBachelierDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Autowired
	@Qualifier("correspondanceDomaineDao")
	private CorrespondanceDomaineDao correspondanceDomaineDao;
	
	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier("nomenclatureDaoImpl")
	private NomenclatureDao nomenclatureDaoImpl;
	
	@Autowired
	@Qualifier("dossierEtudiantDao")
	private DossierEtudiantDao dossierEtudiantDao;

	@Autowired
	@Qualifier("anneeAcademiqueDao")
	private AnneeAcademiqueDao anneeAcademiqueDao;
	
	@Autowired
	@Qualifier("refTypePieceConstitutiveDaoImpl")
	private RefTypePieceConstitutiveDao refTypePieceConstitutiveDaoImpl;
	
	@Autowired
	@Qualifier("refFiliereLmdDaoImpl")
	private RefFiliereLmdDao refFiliereLmdDaoImpl;
	
	@Autowired
	@Qualifier("refDomaineDaoImpl")
	private RefDomaineDao refDomaineDaoImpl;
	
	@Autowired
	@Qualifier("cycleDao")
	private CycleDao cycleDao;

	@Autowired
	@Qualifier("niveauDao")
	private NiveauDao niveauDao;
	
	/**
	 * [DossierBachelierDaoImpl.persist] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 21 mai 2014  14:12:36
	 * @param transientInstance
	 */
	@Override
	public void persist(DossierBachelier transientInstance) {
		
		log.debug("persisting DossierBachelier instance");
		
		try {
					entityManager.persist(transientInstance);
					log.debug("persist successful");
					
		} catch (RuntimeException re) {
					
					log.error("persist failed", re);
					throw re;
		}

	}

	/**
	 * [DossierBachelierDaoImpl.remove] Method 
	 * overridden By :  @author  Rafik LAIB  
	 * On : 21 mai 2014  14:12:36
	 * @param persistentInstance
	 */
	@Override
	public void remove(DossierBachelier persistentInstance) {

		log.debug("removing DossierBachelier instance");

		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");

		} catch (RuntimeException re) {

			log.error("remove failed", re);
			throw re;
		}

	}

	/**
	 * [DossierBachelierDaoImpl.merge] Method overridden By : @author Rafik LAIB
	 * On : 21 mai 2014 14:12:36
	 * 
	 * @param detachedInstance
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DossierBachelier merge(DossierBachelier detachedInstance) {

		log.debug("merging DossierBachelier instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);

		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [DossierBachelierDaoImpl.findById] Method overridden By : @author Rafik
	 * LAIB On : 21 mai 2014 14:12:36
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DossierBachelier findById(int id) {

		log.debug("getting DossierBachelier instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DossierBachelier.class, id);

		} catch (RuntimeException re) {

			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * [DossierBachelierDaoImpl.findGeneric] Method overridden By : @author
	 * Rafik LAIB On : 21 mai 2014 14:12:36
	 * 
	 * @param text
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<DossierBachelier> findGeneric(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [DossierBachelierDaoImpl.findAll] Method overridden By : @author Rafik
	 * LAIB On : 21 mai 2014 14:12:36
	 * 
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<DossierBachelier> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * [DossierBachelierDaoImpl.findByMatricule] Method overridden By : @author
	 * Rafik LAIB On : 2 juin 2014 12:07:47
	 * 
	 * @param matricule
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public DossierBachelier findByMatricule(String matricule) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierBachelier> c = cb.createQuery(DossierBachelier.class);
			Root<DossierBachelier> db = c.from(DossierBachelier.class);
			Join<DossierBachelier, Importation> dbi = db.join("importation");
			c.select(db).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(db.get("matricule"), matricule);
			criteria.add(condition1);

			Predicate condition2 = cb.equal(dbi.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			criteria.add(condition2);

			c.where(cb.and(criteria.toArray(new Predicate[0])));

			TypedQuery<DossierBachelier> q = entityManager.createQuery(c);
			/*
			 * try { return q.getSingleResult(); } catch (NoResultException ex)
			 * {
			 * 
			 * return null; }
			 */
			List<DossierBachelier> _resultList = q.getResultList();

			if (_resultList != null && !_resultList.isEmpty()) {
				return _resultList.get(0);
			}
			return null;
		} catch (RuntimeException re) {

			log.error("findByMatricule 'DossierBachelier' failed", re);
			throw re;
		}
	}

	/**
	 * [DossierBachelierDaoImpl.findAdvanced] Method overridden By : @author
	 * Rafik LAIB On : 2 juin 2014 12:07:57
	 * 
	 * @param attributesForKeyWordSearch
	 * @param attributesForAdvancedSearch
	 * @param withStudentFile
	 * @return
	 */
	@Override
	public  List<DossierBachelier> findAdvanced(Map<String, String> attributesForKeyWordSearch, 
						Map<String, String> attributesDossierBachelier,
						Map<String, String> attributesAffectationBachelier
			, boolean withStudentFile, boolean hasAffectationCorrespondanceCode, String year
			, Integer start, Integer pageSize){
		
		try {
						CriteriaBuilder cb = entityManager.getCriteriaBuilder();
						CriteriaQuery<DossierBachelier> c = cb.createQuery(DossierBachelier.class);
						Root<DossierBachelier> db = c.from(DossierBachelier.class);
						Join<DossierBachelier, Importation> dbi = db.join("importationAffectation");
						c.select(db).distinct(true);
						c.orderBy(cb.asc(db.get("nomFr")), cb.asc(db.get("prenomFr")));
						Predicate yearPredicate = null;
						if(year != null) {
								yearPredicate = cb.equal(dbi.get("anneeBac"), year);
						}
						List<Predicate> criteriaOr = new ArrayList<Predicate>();
						for(String s : attributesForKeyWordSearch.keySet())
						{
								if(db.get(s) != null){
									criteriaOr.add(cb.like(cb.lower(db.<String>get(s)), "%" + attributesForKeyWordSearch.get(s).toLowerCase() + "%" ));
								}
						}
						List<Predicate> criteriaAnd = new ArrayList<Predicate>();
						for(String s : attributesDossierBachelier.keySet())
						{
							if(db.get(s) != null){
								criteriaAnd.add(cb.equal(db.<String>get(s), attributesDossierBachelier.get(s)));
							}
						}
					
						List<Predicate> criteriaBool = new ArrayList<Predicate>();
						if(withStudentFile) {
							Subquery<DossierEtudiant> subquery = c.subquery(DossierEtudiant.class);
							Root<DossierEtudiant> de = subquery.from(DossierEtudiant.class);
							subquery.select(de);
							Predicate p = cb.equal(de.get("dossierBachelier"),db);
							subquery.where(p);
							criteriaBool.add(cb.not(cb.exists(subquery))); 
						}
						List<Predicate> criteriaBool1 = new ArrayList<Predicate>();
						if(hasAffectationCorrespondanceCode) {
							Subquery<CorrespondanceDomaine> subquery1 = c.subquery(CorrespondanceDomaine.class);
							Root<CorrespondanceDomaine> cd = subquery1.from(CorrespondanceDomaine.class);
							subquery1.select(cd);
							Predicate p1 = cb.equal(cd.get("ancienCode"),db.get("refCodeFiliereAffectation"));
							subquery1.where(p1);
							criteriaBool1.add(cb.not(cb.exists(subquery1))); 
						}
						Predicate where = cb.conjunction();
						if(yearPredicate != null)
							where = cb.and(yearPredicate);
						
						if(criteriaOr != null && criteriaOr.size()>0)
							where = cb.and(where,cb.or(criteriaOr.toArray(new Predicate[0])));
								
						if(criteriaAnd != null && criteriaAnd.size()>0) {
							
							where = cb.and(where, cb.and(criteriaAnd.toArray(new Predicate[0])));
						}
								
						if(criteriaBool != null && criteriaBool.size()>0)
							where = cb.and(where, cb.and(criteriaBool.toArray(new Predicate[0])));
						
						if(criteriaBool1 != null && criteriaBool1.size()>0)
							where = cb.and(where, cb.and(criteriaBool1.toArray(new Predicate[0])));
						
						Predicate confirmedPredicate = cb.equal(dbi.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
						if(confirmedPredicate != null)
							where = cb.and(where,cb.and(confirmedPredicate));
						
						c.where(where);
						TypedQuery<DossierBachelier> q = entityManager.createQuery(c);
						if(start != null && pageSize !=  null) {
							q.setFirstResult(start);
							q.setMaxResults(pageSize);
						}
						List<DossierBachelier> result = q.getResultList();
						for(DossierBachelier entity: result){
					        if(entityManager.contains(entity)){
					        	entityManager.refresh(entity);
					        }
					    }
						return result;
			}
			catch (RuntimeException re) {

					log.error("findAdvanced 'DossierBachelier' failed", re);
					throw re;
			}

			
	}

	@Override
	public List<DossierBachelier> findAdvancedWithPaging(Map<String, String> attributesForKeyWordSearch,
			Map<String, String> attributesDossierBachelier, Map<String, String> attributesAffectationBachelier,
			boolean withStudentFile, String year, int start, int page) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierBachelier> c = cb.createQuery(DossierBachelier.class);
			Root<DossierBachelier> db = c.from(DossierBachelier.class);
			Join<DossierBachelier, Importation> dbi = db.join("importation");
			Join<DossierBachelier, AffectationBachelier> dbab = db.join("affectationBachelier", JoinType.LEFT);
			c.select(db).distinct(true);
			c.orderBy(cb.asc(db.get("nomFr")), cb.asc(db.get("prenomFr")));
			Predicate yearPredicate = null;
			if (year != null) {
				yearPredicate = cb.equal(dbi.get("anneeBac"), year);
			}
			List<Predicate> criteriaOr = new ArrayList<Predicate>();
			for (String s : attributesForKeyWordSearch.keySet()) {
				if (db.get(s) != null) {
					criteriaOr.add(cb.like(cb.lower(db.<String> get(s)), "%"
							+ attributesForKeyWordSearch.get(s).toLowerCase() + "%"));
				}
			}
			List<Predicate> criteriaAnd = new ArrayList<Predicate>();
			for (String s : attributesDossierBachelier.keySet()) {
				if (db.get(s) != null) {
					criteriaAnd.add(cb.equal(db.<String> get(s), attributesDossierBachelier.get(s)));
				}
			}
			List<Predicate> criteriaAnd1 = new ArrayList<Predicate>();
			for (String s1 : attributesAffectationBachelier.keySet()) {
				if (dbab.get(s1) != null) {
					criteriaAnd1.add(cb.equal(dbab.<String> get(s1), attributesAffectationBachelier.get(s1)));
				}
			}
			List<Predicate> criteriaBool = new ArrayList<Predicate>();
			if (withStudentFile) {
				Subquery<DossierEtudiant> subquery = c.subquery(DossierEtudiant.class);
				Root<DossierEtudiant> de = subquery.from(DossierEtudiant.class);
				subquery.select(de);
				Predicate p = cb.equal(de.get("dossierBachelier"), db);
				subquery.where(p);
				criteriaBool.add(cb.not(cb.exists(subquery)));
			}
			Predicate where = cb.conjunction();
			if (yearPredicate != null)
				where = cb.and(yearPredicate);

			if (criteriaOr != null && criteriaOr.size() > 0)
				where = cb.and(where, cb.or(criteriaOr.toArray(new Predicate[0])));

			if (criteriaAnd != null && criteriaAnd.size() > 0) {

				where = cb.and(where, cb.and(criteriaAnd.toArray(new Predicate[0])));
				if (criteriaAnd1 != null && criteriaAnd1.size() > 0)
					where = cb.or(where, cb.and(criteriaAnd1.toArray(new Predicate[0])));
			} else if (criteriaAnd1 != null && criteriaAnd1.size() > 0)
				where = cb.and(where, cb.and(criteriaAnd1.toArray(new Predicate[0])));

			if (criteriaBool != null && criteriaBool.size() > 0)
				where = cb.and(where, cb.and(criteriaBool.toArray(new Predicate[0])));

			Predicate confirmedPredicate = cb.equal(dbi.get("situation"),
					UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			if (confirmedPredicate != null)
				where = cb.and(where, cb.and(confirmedPredicate));

			c.where(where);
			TypedQuery<DossierBachelier> q = entityManager.createQuery(c).setFirstResult(start).setMaxResults(page);
			List<DossierBachelier> result = q.getResultList();
			for (DossierBachelier entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;
		} catch (RuntimeException re) {

			log.error("findAdvanced 'DossierBachelier' failed", re);
			throw re;
		}
	}

	@Override
	public  Integer getFindAdvancedCount(Map<String, String> attributesForKeyWordSearch, 
			Map<String, String> attributesDossierBachelier,
			Map<String, String> attributesAffectationBachelier,
			boolean withStudentFile, boolean hasAffectationCorrespondanceCode, 
			String year){

		try {
			
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<DossierBachelier> db = cq.from(DossierBachelier.class);
			Join<DossierBachelier, Importation> dbi = db.join("importationAffectation");
			cq.select(cb.countDistinct(db.get("id")));

			Predicate yearPredicate = null;
			if (year != null) {
				yearPredicate = cb.equal(dbi.get("anneeBac"), year);
			}
			List<Predicate> criteriaOr = new ArrayList<Predicate>();
			for (String s : attributesForKeyWordSearch.keySet()) {
				if (db.get(s) != null) {
					criteriaOr.add(cb.like(cb.lower(db.<String> get(s)), "%"
							+ attributesForKeyWordSearch.get(s).toLowerCase() + "%"));
				}
			}
			List<Predicate> criteriaAnd = new ArrayList<Predicate>();
			for (String s : attributesDossierBachelier.keySet()) {
				if (db.get(s) != null) {
					criteriaAnd.add(cb.equal(db.<String> get(s), attributesDossierBachelier.get(s)));
				}
			}
		
			List<Predicate> criteriaBool = new ArrayList<Predicate>();
			if (withStudentFile) {
				Subquery<DossierEtudiant> subquery = cq.subquery(DossierEtudiant.class);
				Root<DossierEtudiant> de = subquery.from(DossierEtudiant.class);
				subquery.select(de);
				Predicate p = cb.equal(de.get("dossierBachelier"), db);
				subquery.where(p);
				criteriaBool.add(cb.not(cb.exists(subquery)));
			}
			List<Predicate> criteriaBool1 = new ArrayList<Predicate>();
			if(hasAffectationCorrespondanceCode) {
				Subquery<CorrespondanceDomaine> subquery1 = cq.subquery(CorrespondanceDomaine.class);
				Root<CorrespondanceDomaine> cd = subquery1.from(CorrespondanceDomaine.class);
				subquery1.select(cd);
				Predicate p1 = cb.equal(cd.get("ancienCode"),db.get("refCodeFiliereAffectation"));
				subquery1.where(p1);
				criteriaBool1.add(cb.not(cb.exists(subquery1))); 
			}
			Predicate where = cb.conjunction();
			if (yearPredicate != null)
				where = cb.and(yearPredicate);

			if (criteriaOr != null && criteriaOr.size() > 0)
				where = cb.and(where, cb.or(criteriaOr.toArray(new Predicate[0])));

			if (criteriaAnd != null && criteriaAnd.size() > 0) {
				where = cb.and(where, cb.and(criteriaAnd.toArray(new Predicate[0])));
			}

			if(criteriaOr != null && criteriaOr.size()>0)
				where = cb.and(where,cb.or(criteriaOr.toArray(new Predicate[0])));
					
			if(criteriaAnd != null && criteriaAnd.size()>0) {
				
				where = cb.and(where, cb.and(criteriaAnd.toArray(new Predicate[0])));
			}

			if (criteriaBool != null && criteriaBool.size() > 0)
				where = cb.and(where, cb.and(criteriaBool.toArray(new Predicate[0])));

			Predicate confirmedPredicate = cb.equal(dbi.get("situation"),
					UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			if (confirmedPredicate != null)
				where = cb.and(where, cb.and(confirmedPredicate));

			cq.where(where);
			Long size = entityManager.createQuery(cq).getSingleResult();
			return size.intValue();

		} catch (RuntimeException re) {
			log.error("getFindAdvancedCount 'DossierBachelier' failed", re);
			throw re;
		}
	}

	/**
	 * [DossierBachelierDaoImpl.findByImportId] Method overridden By : @author
	 * Rafik LAIB On : 2 juin 2014 12:08:08
	 * 
	 * @param idImport
	 * @return
	 */
	@Override
	public List<DossierBachelier> findByImportId(int idImport) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierBachelier> c = cb.createQuery(DossierBachelier.class);
			Root<DossierBachelier> db = c.from(DossierBachelier.class);
			Join<DossierBachelier, Importation> dbi = db.join("importation");
			c.select(db).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();
			Predicate condition1 = cb.equal(dbi.get("id"), idImport);
			criteria.add(condition1);
			Predicate condition2 = cb.equal(dbi.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			criteria.add(condition2);
			c.where(cb.and(criteria.toArray(new Predicate[0])));

			TypedQuery<DossierBachelier> q = entityManager.createQuery(c);
			List<DossierBachelier> result = q.getResultList();
			for (DossierBachelier entity : result) {
				if (entityManager.contains(entity)) {
					entityManager.refresh(entity);
				}
			}
			return result;

		} catch (RuntimeException re) {

			log.error("findByImportId 'DossierBachelier' failed", re);
			throw re;
		}
	}

	@Override
	public DossierBachelier findByMatriculeByIdImportation(String matricule, int idImport) {

		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierBachelier> c = cb.createQuery(DossierBachelier.class);
			Root<DossierBachelier> db = c.from(DossierBachelier.class);
			Join<DossierBachelier, Importation> dbi = db.join("importation");
			c.select(db).distinct(true);

			List<Predicate> criteria = new ArrayList<Predicate>();

			Predicate condition1 = cb.equal(db.get("matricule"), matricule);
			criteria.add(condition1);

			Predicate condition2 = cb.equal(dbi.get("id"), idImport);
			criteria.add(condition2);

			Predicate condition3 = cb.equal(dbi.get("situation"), UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			criteria.add(condition3);

			c.where(cb.and(criteria.toArray(new Predicate[0])));

			TypedQuery<DossierBachelier> q = entityManager.createQuery(c);
			try {
				return q.getSingleResult();
			} catch (NoResultException ex) {

				return null;
			}

		} catch (RuntimeException re) {

			log.error("findByMatriculeByIdImportation 'DossierBachelier' failed", re);
			throw re;
		}
	}

	@Override
	public void flushAndClear() {

		try {
			entityManager.flush();
			entityManager.clear();
		} catch (RuntimeException re) {
			log.error("flushAndClear failed", re);
			throw re;
		}
	}

	/**
	 * [DossierBachelierDao.generateStudentsFiles] Method
	 * 
	 * @author rlaib on : 8 nov. 2014 11:38:34
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> generateStudentsFiles(String anneeAcademique, String ancienCodeEtablissement,
			String nouveauCodeEtablissement , Integer idEtab) {
		try {

			Map<String, Object> result = new HashMap<String, Object>();

			StatelessSession statelessSession = ((Session) entityManager.getDelegate()).getSessionFactory()
					.openStatelessSession();
			statelessSession.beginTransaction();
			Map<String, CorrespondanceDomaine> _correspondanceList = getCorrespondanceDomaines();

			// recupere l annee academique
			Integer firstYear = Integer.parseInt(anneeAcademique.substring(0, 4));
			Integer secondYear = Integer.parseInt(anneeAcademique.substring(5, 9));
			AnneeAcademique _anneeAcademique = anneeAcademiqueDao.findByFirstAndSecondYear(firstYear, secondYear);
			List<RefTypePieceConstitutive> _pieces = refTypePieceConstitutiveDaoImpl.findByCodeTypeDossierDate(
					UtilConstant.CODE_TYPE_DOSSIER_INSCRIPTION_PREMIERE_ANNEE, new Date());

			// recupere le 1ere niveau du cycle
			List<Cycle> _cyclesLMD = cycleDao.findByTypeFormationCode(UtilConstant.TYPE_FORMATION_LMD_CODE);
			List<Cycle> _cyclesClassique = cycleDao.findByTypeFormationCode(UtilConstant.TYPE_FORMATION_CLASSIQUE_CODE);
			Niveau permierNiveauLMD = new Niveau();
			Niveau permierNiveauClasssique = new Niveau();

			if (_cyclesLMD != null && _cyclesLMD.size() > 0) {
				Integer _idCycle = _cyclesLMD.get(0).getId();
				List<Niveau> _niveauxLMD = niveauDao.findByCycleId(_idCycle);
				if (_niveauxLMD != null && _niveauxLMD.size() > 0)
					permierNiveauLMD = _niveauxLMD.get(0);
			}

			if (_cyclesClassique != null && _cyclesClassique.size() > 0) {
				Integer _idCycle = _cyclesClassique.get(0).getId();
				List<Niveau> _niveauxClassiques = niveauDao.findByCycleId(_idCycle);
				if (_niveauxClassiques != null && _niveauxClassiques.size() > 0)
					permierNiveauClasssique = _niveauxClassiques.get(0);
			}

			// recuperation du nouveau code de la filiere affectation
			Nomenclature _nationalite = new Nomenclature();
			_nationalite = nomenclatureDaoImpl.findByCode(UtilConstant.NC_NAME_NATIONALITE, UtilConstant.NC_NATIONALITE_ALG);

			// Recuperation de situation (GENEREE_AUTO) du
			// dossier a generer
			SituationEntite _se = situationEntiteDao.findByCodeSituationByCodeEntite(
					UtilConstant.ENTITE_DOSSIER_CODE, UtilConstant.SITUTAION_GENEREE_AUTO_CODE);
			
			List<DossierBachelier> _listBacheliers = new ArrayList<DossierBachelier>();
			List<DossierEtudiant> _listEtudiants = new ArrayList<DossierEtudiant>();
			List<DossierInscriptionAdministrative> _listInscriptions = new ArrayList<DossierInscriptionAdministrative>();
			List<RefIndividu> _listIndividusGeneres = new ArrayList<RefIndividu>();
			List<RefIndividu> _listIndividusExistants = new ArrayList<RefIndividu>();
			Generation _generation = new Generation();
			_generation.setAnneeAcademique(anneeAcademique);
			_generation.setRefCodeEtablissement(nouveauCodeEtablissement);
			_generation.setDateDebutGeneration(new Date());
			_generation.setSituation(UtilConstant.BAC_IMPORT_STATUS_LAUNCHED_CODE);

			StringBuilder queryCheckIndividu = new StringBuilder("SELECT ind FROM RefIndividu ind ");
			queryCheckIndividu.append(" WHERE (ind.identifiant = :identifiant )");
			StringBuilder query1 = new StringBuilder("SELECT db FROM DossierBachelier db ");
			query1.append(" JOIN  db.importation as im ");
			query1.append(" WHERE (im.anneeBac = '" + anneeAcademique + "' )");
			query1.append(" AND (db.refCodeEtablissementAffectation = '" + ancienCodeEtablissement + "' )");

			Integer batchSize = 100;
			Query query = statelessSession.createQuery(query1.toString());
			query.setFetchSize(batchSize);
			query.setReadOnly(true);
			query.setLockMode("db", LockMode.NONE);
			ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
			
			int count = 0;
			Integer nextVal = null;
			while (results.next()) {
				
				DossierBachelier _dossierBachelier = (DossierBachelier) results.get()[0];
				_listBacheliers.add(_dossierBachelier);
				CorrespondanceDomaine _cd;
				String _currentCode = _dossierBachelier.getRefCodeFiliereAffectation();
				if (_currentCode != null) {
					_cd = _correspondanceList.get(_currentCode);

					if (_cd != null) {

						// verification Individu
						TypedQuery<RefIndividu> queryIndividu = entityManager.createQuery(new String(queryCheckIndividu), RefIndividu.class);
						queryIndividu.setParameter("identifiant", _dossierBachelier.getMatricule());
						List<RefIndividu> resultList = queryIndividu.getResultList();
						RefIndividu _refIndividu = null;
						if (resultList != null && resultList.size() > 0) {
							// individu existant, on le recupere
							_refIndividu = resultList.get(0);
							_listIndividusExistants.add(_refIndividu);
							// System.err.println(" Dossier Bachelier EXISTANT : "
							// + _dossierBachelier.getMatricule());
						} else {
							// individu inexistant, on le cree
							_refIndividu = new RefIndividu();
							_refIndividu.setIdentifiant(_dossierBachelier.getMatricule());
							_refIndividu.setNomArabe(_dossierBachelier.getNomAr());
							_refIndividu.setPrenomArabe(_dossierBachelier.getPrenomAr());
							_refIndividu.setNomLatin(_dossierBachelier.getNomFr());
							_refIndividu.setPrenomLatin(_dossierBachelier.getPrenomFr());
							_refIndividu.setLieuNaissance(_dossierBachelier.getLibelleVilleNaissance());
							_refIndividu.setDateNaissance(_dossierBachelier.getDateNaissance());
							_refIndividu.setNomMereLatin(_dossierBachelier.getNomPrenomMere());
							_refIndividu.setPrenomPereLatin(_dossierBachelier.getPrenomPere());
							_refIndividu.setNomenclatureByNationalite(_nationalite);
							statelessSession.insert(_refIndividu);
							// System.err.println(" Dossier Bachelier CREE : " +
							// _dossierBachelier.getMatricule());
							_listIndividusGeneres.add(_refIndividu);
						}
						// Generation du dossier etudiant
						Dossier _dossier = new Dossier();
						_dossier.setTypeDossier(UtilConstant.TYPE_DOSSIER_ETUDIANT_CODE);

						_dossier.setSituationEntite(_se);
						_dossier.setDateCreation(new Date());
						statelessSession.insert(_dossier);

						DossierEtudiant _dossierEtudiant = new DossierEtudiant();
						_dossierEtudiant.setDossierBachelier(_dossierBachelier);
						
						// generation matricule etudiant
						String matriculeEtudiant = dossierEtudiantDao.generateMatricule(_dossierEtudiant);
						_dossierEtudiant.setNumeroMatricule(matriculeEtudiant);
						RefEtablissement _refEtablissement = new RefEtablissement();
						_refEtablissement.setId(idEtab);
						_dossierEtudiant.setEtablissementOrigineAr(null);
						_dossierEtudiant.setEtablissementOrigineFr(null);
						_dossierEtudiant.setIndividu(_refIndividu);
					
						// _dossierEtudiant.setRefIndividu(_refIndividu.getIdentifiant());
						_dossier.setDossierEtudiant(_dossierEtudiant);
						_dossierEtudiant.setId(_dossier.getId());
						_dossierEtudiant.setRefEtablissement(_refEtablissement);
						statelessSession.insert(_dossierEtudiant);
						_listEtudiants.add(_dossierEtudiant);

						// Generation dossier Inscription
						DossierInscriptionAdministrative _dossierInscriptionAdministrative = new DossierInscriptionAdministrative();
						_dossierInscriptionAdministrative.setDossierEtudiant(_dossierEtudiant);
						_dossierInscriptionAdministrative.setNumeroInscription(nouveauCodeEtablissement
								+ matriculeEtudiant);
						_dossierInscriptionAdministrative.setAnneeAcademique(_anneeAcademique);
						_dossierInscriptionAdministrative.setRefEtablissement(_refEtablissement);

						// Type inscription 1ere annee LMD ou Classique
						if (_cd.getEstClassique() != null) {
							if (_cd.getEstClassique()) {
								_dossierInscriptionAdministrative
										.setRefCodeTypeInscription(UtilConstant.TYPE_INSCRIPTION_CLASSIQUE_CODE);
								_dossierInscriptionAdministrative.setNiveau(permierNiveauClasssique);
							} else {
								_dossierInscriptionAdministrative
										.setRefCodeTypeInscription(UtilConstant.TYPE_INSCRIPTION_LMD_LICENCE_CODE);
								_dossierInscriptionAdministrative.setNiveau(permierNiveauLMD);
							}
						} else {
							_dossierInscriptionAdministrative
									.setRefCodeTypeInscription(UtilConstant.TYPE_INSCRIPTION_LMD_LICENCE_CODE);
						}
						if (_cd.getCodeNiveau2() != null) {

							// recupere le nouveau code domaine de l ancienne
							// filiere
							List<RefFiliereLmd> _list = refFiliereLmdDaoImpl.findByAncienCodeFiliere(_cd
									.getNouveauCode());
							if (_list != null && _list.size() > 0) {
								_dossierInscriptionAdministrative.setRefCodeDomaine(_list.get(0).getRefDomaineLMD()
										.getIdentifiant());
								_dossierInscriptionAdministrative.setRefCodeFiliere(_cd.getNouveauCode());
							}
						} else {
							_dossierInscriptionAdministrative.setRefCodeDomaine(_cd.getNouveauCode());
						}
						_dossierInscriptionAdministrative.setId(_dossier.getId());
						statelessSession.insert(_dossierInscriptionAdministrative);
						_listInscriptions.add(_dossierInscriptionAdministrative);
						
						for (RefTypePieceConstitutive _piece : _pieces) {

							PieceConstitutive pieceConstitutive = new PieceConstitutive();
							pieceConstitutive.setDossier(_dossier);
							Nomenclature ncTypePiece = new Nomenclature();
							ncTypePiece.setId(_piece.getNcTypePiece().getId());
							pieceConstitutive.setRefTypePieceConstitutive(_piece);
							pieceConstitutive.setFournie(false);
							statelessSession.insert(pieceConstitutive);
						}
						
						 if(++count >0 && count % batchSize == 0) {
							 System.err.println("Fetched " + count + " dossiers ");
							 
							// Updating Sequences
								
								DefaultPostgresKeyServer defaultPostgresKeyServer1  = new DefaultPostgresKeyServer(statelessSession,batchSize, "cursus.dossier_id_seq");
								nextVal = defaultPostgresKeyServer1.getNextKey();
								DefaultPostgresKeyServer defaultPostgresKeyServer2  = new DefaultPostgresKeyServer(statelessSession,batchSize, "cursus.dossier_etudiant_id_seq");
								nextVal = defaultPostgresKeyServer2.getNextKey();
								DefaultPostgresKeyServer defaultPostgresKeyServer3  = new DefaultPostgresKeyServer(statelessSession,batchSize, "cursus.dossier_inscription_administravtive_id_seq");
								nextVal = defaultPostgresKeyServer3.getNextKey();
								DefaultPostgresKeyServer defaultPostgresKeyServer4  = new DefaultPostgresKeyServer(statelessSession, batchSize, "cursus.piece_ent_sort_id_seq");
								nextVal = defaultPostgresKeyServer4.getNextKey();
								DefaultPostgresKeyServer defaultPostgresKeyServer5  = new DefaultPostgresKeyServer(statelessSession, batchSize, "ppm.ref_individu_id_seq");
								nextVal= defaultPostgresKeyServer5.getNextKey();
							 }
					}
				}
				// System.err.println(" Dossier Bachelier GENERE  : " +
				// _dossierBachelier.getMatricule());
			}
			
			_generation.setDateFinGeneration(new Date());
			_generation.setSituation(UtilConstant.BAC_IMPORT_STATUS_TERMINATED_CODE);
			_generation.setNbBacheliers(_listBacheliers.size());
			_generation.setNbDossiersEtudiants(_listEtudiants.size());
			_generation.setNbDossiersInscriptions(_listInscriptions.size());
			_generation.setNbIndividusGeneres(_listIndividusGeneres.size());
			_generation.setNbIndividusExistants(_listIndividusExistants.size());
			statelessSession.insert(_generation);

			results.close();
			statelessSession.getTransaction().commit();
			statelessSession.close();

			result.put(DossierBachelier.class.getSimpleName(), _listBacheliers);
			result.put(DossierEtudiant.class.getSimpleName(), _listEtudiants);
			result.put(DossierInscriptionAdministrative.class.getSimpleName(), _listInscriptions);
			result.put(RefIndividu.class.getSimpleName(), _listIndividusGeneres);
			result.put(RefIndividu.class.getSimpleName() + "1", _listIndividusExistants);
			return result;
		} catch (RuntimeException re) {

			log.error("generateStudentsFiles 'DossierEtudiant' failed", re);
			throw re;
		}

	}

	private Map<String, CorrespondanceDomaine> getCorrespondanceDomaines() {

		Map<String, CorrespondanceDomaine> _result = null;
		try {
			List<CorrespondanceDomaine> _list = correspondanceDomaineDao.findAll();
			_result = new HashMap<String, CorrespondanceDomaine>();
			for (CorrespondanceDomaine correspondanceDomaine : _list) {
				if (correspondanceDomaine.getAncienCode() != null)
					_result.put(correspondanceDomaine.getAncienCode(), correspondanceDomaine);
			}
			return _result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	private class DefaultPostgresKeyServer {
		private final StatelessSession _session;
		private final String _sequenceName;
		private Iterator<BigInteger> _iter;
		private final long _batchSize;

		public DefaultPostgresKeyServer(StatelessSession statelessSession, long batchSize, String sequenceName) {
			this._session = statelessSession;
			_batchSize = batchSize;
			_sequenceName =sequenceName;
			_iter = Collections.<BigInteger> emptyList().iterator();
		}

		@SuppressWarnings({ "unchecked", "unused" })
		public Integer getNextKey() {
			if (!_iter.hasNext()) {
				Query query = _session
						.createSQLQuery("SELECT nextval( '"+_sequenceName+"' ) FROM generate_series( 1, "
								+ _batchSize + " )");
				_iter = query.list().iterator();
			}
			return _iter.next().intValue();
		}

	}
}
