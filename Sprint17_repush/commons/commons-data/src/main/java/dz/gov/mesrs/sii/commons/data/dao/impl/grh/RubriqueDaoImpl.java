package dz.gov.mesrs.sii.commons.data.dao.impl.grh;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.CommonDaoImpl;
import dz.gov.mesrs.sii.commons.data.dao.grh.RubriqueDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Rubrique;

/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Implementation DAO Rubrique
 * 
 */



@Repository ("rubriqueDao") 
public class RubriqueDaoImpl  extends CommonDaoImpl<Rubrique,Integer> implements RubriqueDao{

	private static final Logger log = LoggerFactory.getLogger(RubriqueDaoImpl.class.getName());
	@Override
	protected Class<Rubrique> getDomainClass() {
		return Rubrique.class;
	}
	
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
	public void persist(Rubrique transientInstance) {
		log.debug("persisting Rubrique instance");
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
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.RubriqueDao#remove(dz.gov.mesrs.sii.commons.data.model.fve.cursus.RubriqueDto.business.model.bo.cursus.Rubrique)
	 * @param persistentInstance
	 */

	@Override
	//Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Rubrique persistentInstance) {
		log.debug("removing Rubrique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.RubriqueDao#merge(dz.gov.mesrs.sii.commons.data.model.fve.cursus.Rubrique)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Rubrique merge(Rubrique detachedInstance) {
		log.debug("merging Rubrique instance");
		try {
			log.debug("merge successful");
			detachedInstance = entityManager.merge(detachedInstance);
			entityManager.flush();entityManager.refresh(entityManager);
			return detachedInstance;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.commons.data.dao.fve.cursus.RubriqueDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Rubrique findById(int id) {
		log.debug("getting Rubrique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Rubrique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Rubrique> findAll() {
		log.debug("getting all Rubrique instances");
		try {
			Query query = entityManager
					.createQuery("from Rubrique rubrique");
			log.debug("findAll 'Rubrique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Rubrique' failed", re);
			return new ArrayList<Rubrique>();
		}
	}
	

	

	
}
