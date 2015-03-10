package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefOccupationLieuDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefOccupationLieu;
@Service("refOccupationLieuDaoImpl")
public class RefOccupationLieuDaoImpl implements RefOccupationLieuDao {

	private static final Logger log = LoggerFactory.getLogger(RefOccupationLieuDaoImpl.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void persist(RefOccupationLieu transientInstance) {
		log.debug("persisting RefOccupationLieu instance");
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


	@Override
	public void remove(RefOccupationLieu persistentInstance) {
		log.debug("removing RefOccupationLieu instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	
	@Override
	public RefOccupationLieu merge(RefOccupationLieu detachedInstance) {
		log.debug("merging RefOccupationLieu instance");
		try {
			RefOccupationLieu result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
	@Override
	public RefOccupationLieu findById(int id) {
		log.debug("getting RefOccupationLieu instance with id: " + id);
		try {
			RefOccupationLieu instance = entityManager.find(RefOccupationLieu.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
	@Override
	public List<RefOccupationLieu> findByIdLieu(int idLieu) {
		
		log.debug("getting List of RefOccupationLieu instance with : " + idLieu);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefOccupationLieu r ");
			request.append("where r.refLieu.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idLieu);
			List<RefOccupationLieu> resultList = (List<RefOccupationLieu>) query
					.getResultList();
			log.debug("findByIdLieu successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdLieu failed", re);
			throw re;
		}

		
	}
	
	
	@Override
	public List<RefOccupationLieu> findByIdEquipement(int idEquipement) {
		
		log.debug("getting List of RefOccupationLieu instance with : " + idEquipement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefOccupationLieu r ");
			request.append("where r.refEquipement.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEquipement);
			List<RefOccupationLieu> resultList = (List<RefOccupationLieu>) query
					.getResultList();
			log.debug("findByIdEquipement successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEquipement failed", re);
			throw re;
		}

		
	} 

	@Override
	public List<RefOccupationLieu> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RefOccupationLieu> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
