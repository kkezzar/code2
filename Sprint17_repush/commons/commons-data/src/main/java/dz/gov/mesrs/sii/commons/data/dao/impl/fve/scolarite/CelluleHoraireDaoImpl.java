package dz.gov.mesrs.sii.commons.data.dao.impl.fve.scolarite;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.CelluleHoraireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.scolarite.CelluleHoraire;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:52:58
 */



@Repository ("celluleHoraireDao")  @Transactional
public class CelluleHoraireDaoImpl implements CelluleHoraireDao {

	private static final Logger log = LoggerFactory.getLogger(CelluleHoraireDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CelluleHoraireDao;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.CelluleHoraire)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(CelluleHoraire transientInstance) {
		log.debug("persisting CelluleHoraire instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CelluleHoraireDao#remove(dz.gov.mesrs.sii.CelluleHoraireDto.business.model.bo.cursus.CelluleHoraire)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(CelluleHoraire persistentInstance) {
		log.debug("removing CelluleHoraire instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CelluleHoraireDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.CelluleHoraireDto)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CelluleHoraire merge(CelluleHoraire detachedInstance) {
		log.debug("merging CelluleHoraire instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.CelluleHoraireDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public CelluleHoraire findById(int id) {
		log.debug("getting CelluleHoraire instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(CelluleHoraire.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
		
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<CelluleHoraire> findAll() {
		log.debug("getting all CelluleHoraire instances");
		try {
			Query query = entityManager.createQuery("from CelluleHoraire celluleHoraire");
			log.debug("findAll 'CelluleHoraire' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'CelluleHoraire' failed", re);
			return new ArrayList<CelluleHoraire>();
		}
	}



	@Override
	public CelluleHoraire findByJourAndPlageHoraire(int idJour,
			int idPlageHoraire) {
		if (idJour == 0 ||idPlageHoraire == 0 ) {
			return null;
		}
		try {
			StringBuilder request = new StringBuilder("select r from CelluleHoraire r ");
			request.append(" where r.jour.id = :idJour and r.plageHoraire.id =:idPlageHoraire");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idJour", idJour);
			query.setParameter("idPlageHoraire", idPlageHoraire);
			List<CelluleHoraire> resultList = (List<CelluleHoraire>) query.getResultList();
			if(resultList==null || resultList.isEmpty()){
				return null;
			}else {
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			
			throw re;
		}
	}
}
