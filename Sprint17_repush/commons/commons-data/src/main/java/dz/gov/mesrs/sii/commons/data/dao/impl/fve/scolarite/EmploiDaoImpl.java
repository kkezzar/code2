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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.EmploiDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.Emploi;



/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:19:10
 */


@Repository ("emploiDao")  @Transactional
public class EmploiDaoImpl implements EmploiDao {

	private static final Logger log = LoggerFactory.getLogger(EmploiDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.EmploiDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.Emploi)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Emploi transientInstance) {
		log.debug("persisting Emploi instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.EmploiDao#remove(dz.gov.mesrs.sii.EmploiDto.business.model.bo.cursus.Emploi)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Emploi persistentInstance) {
		log.debug("removing Emploi instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.EmploiDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.EmploiDto)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Emploi merge(Emploi detachedInstance) {
		log.debug("merging Emploi instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.EmploiDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Emploi findById(int id) {
		log.debug("getting Emploi instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Emploi.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Emploi> findAll() {
		log.debug("getting all Emploi instances");
		try {
			Query query = entityManager.createQuery("from Emploi emploi");
			log.debug("findAll 'Emploi' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Emploi' failed", re);
			return new ArrayList<Emploi>();
		}
	}



	@Override
	public List<Emploi> findAdvanced(Emploi emploiSearch) {
		log.debug("findAdvanced instances");
		try {
			if (emploiSearch == null) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select p from Emploi p ");
			sqlQuery.append(" where (1=1) ");
			if (emploiSearch.getAnneeAcademique() != null
					&& emploiSearch.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and p.anneeAcademique.id = "
						+ emploiSearch.getAnneeAcademique().getId());
			}
			if (emploiSearch.getOuvertureOffreFormation() != null
					 ) {
				if(emploiSearch.getOuvertureOffreFormation().getId() != 0){
				sqlQuery.append(" and p.ouvertureOffreFormation.id = "
						+ emploiSearch.getOuvertureOffreFormation().getId());
				}
				if (emploiSearch.getOuvertureOffreFormation().getRefEtablissement() != null
						&& emploiSearch.getOuvertureOffreFormation().getRefEtablissement().getId() != 0) {
					sqlQuery.append(" p.ouvertureOffreFormation.refEtablissement.id = "
							+ emploiSearch.getOuvertureOffreFormation().getRefEtablissement().getId());
				}
			}
		
			if (emploiSearch.getNiveau() != null
					&& emploiSearch.getNiveau().getId() != 0) {
				sqlQuery.append(" and p.niveau.id = "
						+ emploiSearch.getNiveau().getId());
			}
			if (emploiSearch.getNomenclatureByIdNcPeriodicite() != null
					&& emploiSearch.getNomenclatureByIdNcPeriodicite().getId() != 0) {
				sqlQuery.append(" and p.nomenclatureByIdNcPeriodicite.id = "
						+ emploiSearch.getNomenclatureByIdNcPeriodicite().getId());
			}
			
			if (emploiSearch.getNomenclatureByIdNcPeriode() != null
					&& emploiSearch.getNomenclatureByIdNcPeriode().getId() != 0) {
				sqlQuery.append(" and p.nomenclatureByIdNcPeriode.id = "
						+ emploiSearch.getNomenclatureByIdNcPeriode().getId());
			}
			
			TypedQuery<Emploi> query = entityManager.createQuery(
					new String(sqlQuery), Emploi.class);
			List<Emploi> result = query.getResultList();
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
