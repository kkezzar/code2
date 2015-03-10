package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtatEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtatEquipement;
@Repository("refEtatEquipementDaoImpl")
public class RefEtatEquipementDaoImpl implements RefEtatEquipementDao {
	private static final Logger log = LoggerFactory.getLogger(RefEtatEquipementDaoImpl.class.getName());
@PersistenceContext
private EntityManager entityManager;

	/**
	 * [RefEtatEquipementDaoImpl.persist] save new RefEtatEquipement
	 * @author BELBRIK Oualid on : 16 janv. 2014 17:40:26
	 * @param the RefEtatEquipement
	 * @return
	 */
	@Override
	public void persist(RefEtatEquipement transientInstance) {
		log.debug("persisting RefEtatEquipement instance");
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
	 * [RefEtatEquipementDaoImpl.remove] remove RefEtatEquipement
	 * @author BELBRIK Oualid on : 16 janv. 2014 17:40:26
	 * @param the RefEtatEquipement
	 * @return
	 */
	@Override
	public void remove(RefEtatEquipement persistentInstance) {
		log.debug("removing RefEtatEquipement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * [RefEtatEquipementDaoImpl.merge] update RefEtatEquipement
	 * @author BELBRIK Oualid on : 16 janv. 2014 17:40:26
	 * @param the RefEtatEquipement
	 * @return the RefEtatEquipement
	 */
	@Override
	public RefEtatEquipement merge(RefEtatEquipement detachedInstance) {
		log.debug("merging RefEtatEquipement instance");
		try {
			RefEtatEquipement result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * [RefEtatEquipementDaoImpl.findById] find the RefEtatEquipement  by Identity
	 * @author BELBRIK Oualid on : 16 janv. 2014 17:40:26
	 * @param the Id
	 * @return the RefEtatEquipementDto
	 */
	@Override
	public RefEtatEquipement findById(int id) {
		log.debug("getting RefEtatEquipement instance with id: " + id);
		try {
			RefEtatEquipement instance = entityManager.find(RefEtatEquipement.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	



	@Override
	public List<RefEtatEquipement> findByIdEquipement(int idEquipement) {
		
		log.debug("getting List of RefEtatEquipement instance with : " + idEquipement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtatEquipement r ");
			request.append("where r.refEquipement.id =:value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEquipement);
			List<RefEtatEquipement> resultList = (List<RefEtatEquipement>) query.getResultList();
			log.debug("findByIdEquipement successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEquipement failed", re);
			throw re;
		}

		
	}



}
