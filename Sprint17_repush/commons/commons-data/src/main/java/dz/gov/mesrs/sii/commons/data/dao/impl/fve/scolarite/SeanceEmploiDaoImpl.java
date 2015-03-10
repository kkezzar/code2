package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.SeanceEmploiDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.SeanceEmploi;



/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:19:48
 */
 



@Repository ("seanceEmploiDao")  @Transactional
public class SeanceEmploiDaoImpl implements SeanceEmploiDao {

	private static final Logger log = LoggerFactory.getLogger(SeanceEmploiDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.SeanceEmploiDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.SeanceEmploi)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SeanceEmploi transientInstance) {
		log.debug("persisting SeanceEmploi instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.SeanceEmploiDao#remove(dz.gov.mesrs.sii.SeanceEmploiDto.business.model.bo.cursus.SeanceEmploi)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SeanceEmploi persistentInstance) {
		log.debug("removing SeanceEmploi instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.SeanceEmploiDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.SeanceEmploiDto)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SeanceEmploi merge(SeanceEmploi detachedInstance) {
		log.debug("merging SeanceEmploi instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.SeanceEmploiDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public SeanceEmploi findById(int id) {
		log.debug("getting SeanceEmploi instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(SeanceEmploi.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<SeanceEmploi> findAll() {
		log.debug("getting all SeanceEmploi instances");
		try {
			Query query = entityManager.createQuery("from SeanceEmploi seanceEmploi");
			log.debug("findAll 'SeanceEmploi' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SeanceEmploi' failed", re);
			return new ArrayList<SeanceEmploi>();
		}
	}



	@Override
	public List<SeanceEmploi> findByEmploiIdAndCelluleId(int idEmploi, int idCellule) {
		if (idEmploi == 0 ||idCellule == 0 ) {
			return null;
		}
		try {
			StringBuilder request = new StringBuilder("select r from SeanceEmploi r ");
			request.append(" where r.emploi.id = :idEmploi and r.affectationLieuStructure.celluleHoraire.id =:idCellule");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEmploi", idEmploi);
			query.setParameter("idCellule", idCellule);
			List<SeanceEmploi> resultList = (List<SeanceEmploi>) query.getResultList();
			if(resultList==null || resultList.isEmpty()){
				return null;
			}else {
				return resultList;
			}

		} catch (RuntimeException re) {
			
			throw re;
		}
	}



	@Override
	public List<SeanceEmploi> findAdvanced(SeanceEmploi seanceEmploi) {
		log.debug("findAdvanced instances");
		try {
			if (seanceEmploi == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select p from SeanceEmploi p ");
			sqlQuery.append(" where (1=1) ");
			if(seanceEmploi.getId()!=0){
				sqlQuery.append(" and p.id = "
						+ seanceEmploi.getId());
			}
			if (seanceEmploi.getEmploi()!= null){
			if (seanceEmploi.getEmploi().getId() != 0) {
					sqlQuery.append(" and p.emploi.id = "
							+ seanceEmploi.getEmploi().getId());
				}
			if (seanceEmploi.getEmploi().getAnneeAcademique() != null
					&& seanceEmploi.getEmploi().getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and p.emploi.anneeAcademique.id = "
						+ seanceEmploi.getEmploi().getAnneeAcademique().getId());
			}
			if (seanceEmploi.getEmploi().getOuvertureOffreFormation() != null
					 ) {
				if(seanceEmploi.getEmploi().getOuvertureOffreFormation().getId() != 0){
				sqlQuery.append(" and p.emploi.ouvertureOffreFormation.id = "
						+ seanceEmploi.getEmploi().getOuvertureOffreFormation().getId());
				}
				
			}
			if (seanceEmploi.getEmploi().getNiveau() != null
					&& seanceEmploi.getEmploi().getNiveau().getId() != 0) {
				sqlQuery.append(" and p.emploi.niveau.id = "
						+ seanceEmploi.getEmploi().getNiveau().getId());
			}
			if (seanceEmploi.getEmploi().getNomenclatureByIdNcPeriodicite() != null
					&& seanceEmploi.getEmploi().getNomenclatureByIdNcPeriodicite().getId() != 0) {
				sqlQuery.append(" and p.emploi.nomenclatureByIdNcPeriodicite.id = "
						+ seanceEmploi.getEmploi().getNomenclatureByIdNcPeriodicite().getId());
			}
			
			if (seanceEmploi.getEmploi().getNomenclatureByIdNcPeriode() != null
					&& seanceEmploi.getEmploi().getNomenclatureByIdNcPeriode().getId() != 0) {
				sqlQuery.append(" and p.emploi.nomenclatureByIdNcPeriode.id = "
						+ seanceEmploi.getEmploi().getNomenclatureByIdNcPeriode().getId());
			}
			}
			if(seanceEmploi.getAffectationLieuStructure()!=null){
				if(seanceEmploi.getAffectationLieuStructure().getId()!=0){
					sqlQuery.append(" and p.affectationLieuStructure.id = "
							+ seanceEmploi.getAffectationLieuStructure().getId());
				}
				
				if(seanceEmploi.getAffectationLieuStructure().getCelluleHoraire()!=null){
					if(seanceEmploi.getAffectationLieuStructure().getCelluleHoraire().getId()!=0){
						sqlQuery.append(" and p.affectationLieuStructure.celluleHoraire.id = "
								+ seanceEmploi.getAffectationLieuStructure().getCelluleHoraire().getId());
					}
					
				}
			}
			
			if(seanceEmploi.getAssociationGroupePedagogique()!=null){
				if(seanceEmploi.getAssociationGroupePedagogique().getId()!=0){
					sqlQuery.append(" and p.associationGroupePedagogique.id = "
							+ seanceEmploi.getAssociationGroupePedagogique().getId());
				}
				
				if(seanceEmploi.getAssociationGroupePedagogique().getGroupePedagogique()!=null){
					if(seanceEmploi.getAssociationGroupePedagogique().getGroupePedagogique().getId()!=0){
						sqlQuery.append(" and p.associationGroupePedagogique.groupePedagogique.id = "
								+ seanceEmploi.getAssociationGroupePedagogique().getGroupePedagogique().getId());
					}
					
				}
			}
			TypedQuery<SeanceEmploi> query = entityManager.createQuery(
					new String(sqlQuery), SeanceEmploi.class);
			List<SeanceEmploi> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

}
