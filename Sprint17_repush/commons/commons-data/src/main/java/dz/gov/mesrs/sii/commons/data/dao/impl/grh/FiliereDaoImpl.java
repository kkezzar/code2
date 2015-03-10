package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


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

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtudeEdition;
import dz.gov.mesrs.sii.commons.data.model.grh.Filiere;
import dz.gov.mesrs.sii.commons.data.dao.grh.FiliereDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.DiplomeFinEtudeEditionDaoImpl;

/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Implementation DAO Filiere
 * 
 */



@Repository ("filiereDao") 
@Transactional
public class FiliereDaoImpl  extends CommonDaoImpl<Filiere,Integer> implements FiliereDao{

	private static final Logger log = LoggerFactory.getLogger(FiliereDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FiliereDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      Filiere)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Filiere transientInstance) {
		log.debug("persisting Filiere instance");
		try {
			entityManager.persist(transientInstance);
			/*
			 * entityManager.flush(); entityManager.refresh(transientInstance);
			 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FiliereDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.cursus.FiliereDto.business.model.bo.cursus.Filiere)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Filiere persistentInstance) {
		log.debug("removing Filiere instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FiliereDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.cursus.Filiere)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Filiere merge(Filiere detachedInstance) {
		log.debug("merging Filiere instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FiliereDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Filiere findById(int id) {
		log.debug("getting Filiere instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Filiere.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Filiere> findAll() {
		log.debug("getting all Filiere instances");
		try {
			Query query = entityManager
					.createQuery("from Filiere filiere");
			log.debug("findAll 'Filiere' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Filiere' failed", re);
			return new ArrayList<Filiere>();
		}
	}
	
	
	
	
	
	@Override
	protected Class<Filiere> getDomainClass() {
		return Filiere.class;
	}
	
	@Override
	public List<Filiere> findFiliereByIdRubrique(int idRubrique) {
		try {
			String sqlQuery = "SELECT d FROM Filiere d WHERE d.rubrique.id=:idRubrique ";

			TypedQuery<Filiere> query = entityManager.createQuery(sqlQuery,
					Filiere.class);
			query.setParameter("idRubrique", idRubrique);

			List<Filiere> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findFiliereByIdRubrique failed", re);
			throw re;
		}
	}




	
}
