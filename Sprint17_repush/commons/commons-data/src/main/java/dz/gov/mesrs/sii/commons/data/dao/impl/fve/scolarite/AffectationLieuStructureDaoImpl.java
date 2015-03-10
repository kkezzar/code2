package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.AffectationLieuStructureDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.AffectationLieuStructure;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.CelluleHoraire;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.PlageHoraire;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Jour;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:52:44
 */


@Repository ("affectationLieuStructureDao")  @Transactional
public class AffectationLieuStructureDaoImpl implements AffectationLieuStructureDao {

	private static final Logger log = LoggerFactory.getLogger(AffectationLieuStructureDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AffectationLieuStructureDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.AffectationLieuStructure)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AffectationLieuStructure transientInstance) {
		log.debug("persisting AffectationLieuStructure instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AffectationLieuStructureDao#remove(dz.gov.mesrs.sii.AffectationLieuStructureDto.business.model.bo.cursus.AffectationLieuStructure)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AffectationLieuStructure persistentInstance) {
		log.debug("removing AffectationLieuStructure instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AffectationLieuStructureDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.AffectationLieuStructureDto)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AffectationLieuStructure merge(AffectationLieuStructure detachedInstance) {
		log.debug("merging AffectationLieuStructure instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AffectationLieuStructureDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public AffectationLieuStructure findById(int id) {
		log.debug("getting AffectationLieuStructure instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(AffectationLieuStructure.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<AffectationLieuStructure> findAll() {
		log.debug("getting all AffectationLieuStructure instances");
		try {
			Query query = entityManager.createQuery("from AffectationLieuStructure affectationLieuStructure");
			log.debug("findAll 'AffectationLieuStructure' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AffectationLieuStructure' failed", re);
			return new ArrayList<AffectationLieuStructure>();
		}
	}



	@Override
	public List<AffectationLieuStructure> findAdvanced(
			AffectationLieuStructure searchBo) {
	
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AffectationLieuStructure> c = cb
					.createQuery(AffectationLieuStructure.class);
			Root<AffectationLieuStructure> _affectationLieuStructure = c
					.from(AffectationLieuStructure.class);
			//c.orderBy(cb.asc(_pi.get("dateCreation")));

			c.select(_affectationLieuStructure).distinct(true);
			Predicate predicate = null;
			if (searchBo.getAnneeAcademique()!= null) {
				Join<AffectationLieuStructure, AnneeAcademique> _anneeAcademique = _affectationLieuStructure
						.join("anneeAcademique");				
					predicate = cb.equal(_anneeAcademique.get("id"),
							searchBo.getAnneeAcademique().getId());
				
			}
			// periodicite
			if (searchBo.getNomenclatureByIdNcPeriodicite() != null) {
				
				
				
				Join<AffectationLieuStructure, Nomenclature> _periodicite = _affectationLieuStructure
						.join("nomenclatureByIdNcPeriodicite");
				if (searchBo.getNomenclatureByIdNcPeriodicite().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_periodicite.get("id"), searchBo
								.getNomenclatureByIdNcPeriodicite().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_periodicite
								.get("id"), searchBo.getNomenclatureByIdNcPeriodicite().getId()));
					}
				}
			}
			
           if (searchBo.getNomenclatureByIdNcPeriode() != null) {
				Join<AffectationLieuStructure, Nomenclature> _periode = _affectationLieuStructure
						.join("nomenclatureByIdNcPeriode");
				if (searchBo.getNomenclatureByIdNcPeriode().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_periode.get("id"), searchBo
								.getNomenclatureByIdNcPeriodicite().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_periode
								.get("id"), searchBo.getNomenclatureByIdNcPeriode().getId()));
					}
				}
			}
           
           if (searchBo.getCelluleHoraire() != null) {
								
				Join<AffectationLieuStructure, CelluleHoraire> _celluleHoraire = _affectationLieuStructure
						.join("celluleHoraire");
				if (searchBo.getCelluleHoraire().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_celluleHoraire.get("id"), searchBo
								.getCelluleHoraire().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_celluleHoraire
								.get("id"), searchBo.getCelluleHoraire().getId()));
					}
				}
				else{
				if (searchBo.getCelluleHoraire().getJour() != null && searchBo.getCelluleHoraire().getJour().getId()!=0 ) {
					Join<CelluleHoraire, Jour> _jour = _celluleHoraire
							.join("jour");
					if (predicate == null) {
						predicate = cb.equal(_jour.get("id"), searchBo.getCelluleHoraire().getJour()
								.getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_celluleHoraire
								.get("id"), searchBo.getCelluleHoraire().getJour().getId()));
					}
				}
				
				if (searchBo.getCelluleHoraire().getPlageHoraire() != null && searchBo.getCelluleHoraire().getPlageHoraire().getId()!=0 ) {
					Join<CelluleHoraire, PlageHoraire> _plageHoraire = _celluleHoraire
							.join("plageHoraire");
					if (predicate == null) {
						predicate = cb.equal(_plageHoraire.get("id"), searchBo.getCelluleHoraire().getPlageHoraire()
								.getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_plageHoraire
								.get("id"), searchBo.getCelluleHoraire().getPlageHoraire().getId()));
					}
				}
				}
			}
			   
           
//           private RefStructure refStructure;
           if (searchBo.getRefStructure() != null) {
				Join<AffectationLieuStructure, RefStructure> _structure = _affectationLieuStructure
						.join("refStructure");
				if (searchBo.getRefStructure().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_structure.get("id"), searchBo.getRefStructure().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_structure
								.get("id"), searchBo.getRefStructure().getId()));
					}
				}
				
				if (searchBo.getRefStructure().getRefEtablissement() != null) {
					
					if (predicate == null) {
						predicate = cb.equal(_structure.get("refEtablissement").get("identifiant"), searchBo.getRefStructure().getRefEtablissement().getIdentifiant());
					} else {
						predicate = cb.and(predicate, cb.equal(_structure.get("refEtablissement").get("identifiant"), searchBo.getRefStructure().getRefEtablissement().getIdentifiant()));
					}
				}
			}
//			private RefLieu refLieu;
           if (searchBo.getRefLieu() != null) {
				Join<AffectationLieuStructure, RefStructure> _lieu = _affectationLieuStructure
						.join("refLieu");
				if (searchBo.getRefLieu().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_lieu.get("id"), searchBo.getRefLieu().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_lieu
								.get("id"), searchBo.getRefLieu().getId()));
					}
				}
			}
    //private OuvertureOffreFormation  ouvertureOffreFormation     
           if (searchBo.getOuvertureOffreFormation() != null) {
     				Join<AffectationLieuStructure, OuvertureOffreFormation> _ouvertureOffreFormation= _affectationLieuStructure
     						.join("ouvertureOffreFormation");
     				if (searchBo.getOuvertureOffreFormation().getId() != 0) {
     					if (predicate == null) {
     						predicate = cb.equal(_ouvertureOffreFormation.get("id"), searchBo.getOuvertureOffreFormation().getId());
     					} else {
     						predicate = cb.and(predicate, cb.equal(_ouvertureOffreFormation
     								.get("id"), searchBo.getOuvertureOffreFormation().getId()));
     					}
     				}

     			}   
           
			if (predicate != null) {
				c.where(predicate);
				TypedQuery<AffectationLieuStructure> _query = entityManager
						.createQuery(c);
				return _query.getResultList();
			}
				return null;
			
		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<AffectationLieuStructure>();
		}
	}
}
