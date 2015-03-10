package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.Date;
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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTypePieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTypePieceConstitutive;

/**
 * Dao object for domain model class RefTypePieceConstitutive.
 * @see dz.gov.mesrs.sii.referentiel.business.model.bo.RefTypePieceConstitutive
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 



@Repository ("refTypePieceConstitutiveDaoImpl")  @Transactional
public class RefTypePieceConstitutiveDaoImpl implements RefTypePieceConstitutiveDao {

	private static final Logger log = LoggerFactory.getLogger(RefTypePieceConstitutiveDaoImpl.class.getName());

	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTypePieceConstitutiveDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefTypePieceConstitutive)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefTypePieceConstitutive transientInstance) {
		log.debug("persisting RefTypePieceConstitutive instance");
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTypePieceConstitutiveDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefTypePieceConstitutive)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefTypePieceConstitutive persistentInstance) {
		log.debug("removing RefTypePieceConstitutive instance");
		try {
			persistentInstance = entityManager.merge(persistentInstance);
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTypePieceConstitutiveDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefTypePieceConstitutive)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefTypePieceConstitutive merge(RefTypePieceConstitutive detachedInstance) {
		log.debug("merging RefTypePieceConstitutive instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTypePieceConstitutiveDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefTypePieceConstitutive findById(int id) {
		log.debug("getting RefTypePieceConstitutive instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefTypePieceConstitutive.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	
	/**
	 * 
	 * @param query
	 */
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefTypePieceConstitutive> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefTypePieceConstitutive> findAll() {
		log.debug("getting all RefTypePieceConstitutive instances");
		try {
			Query query = entityManager.createQuery("from RefTypePieceConstitutive refTypePieceConstitutive");
			log.debug("findAll 'RefTypePieceConstitutive' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefTypePieceConstitutive' failed", re);
			return new ArrayList<RefTypePieceConstitutive>();
		}
	}



	@Override
	public List<RefTypePieceConstitutive> findByIdTypeDossierDate(
			int idTypeDossier, Date annee) {
		log.debug("findPieceConstitutive");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefTypePieceConstitutive r ");
			request.append("where r.ncTypeDossier.id =:id ");
			request.append("and (r.dateDebut <=:annee ");
			request.append("and r.dateFin is null) ");
			request.append("or (r.dateDebut <=:annee ");
			request.append("and r.dateFin >=:annee ");
			request.append("and r.dateFin is not null) ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", idTypeDossier);
			query.setParameter("annee", annee);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			log.debug("findPieceConstitutive successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findPieceConstitutive failed", re);
			throw re;
		}
		
	}



	@Override
	public List<RefTypePieceConstitutive> findByTypeDossierDate(
			String typeDossier, Date annee) {
		log.debug("findPieceConstitutive");
		if(typeDossier == null) {
			return null;
		}
		typeDossier = typeDossier.trim().toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefTypePieceConstitutive r ");
			request.append("where lower(r.ncTypeDossier.libelleLongFr) =:typeDossier");
			request.append("and (r.dateDebut <=:annee ");
			request.append("and r.dateFin is null) ");
			request.append("or (r.dateDebut <=:annee ");
			request.append("and r.dateFin >=:annee ");
			request.append("and r.dateFin is not null) ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("typeDossier", typeDossier);
			query.setParameter("annee", annee);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			log.debug("findPieceConstitutive successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findPieceConstitutive failed", re);
			throw re;
		}
	}



	@Override
	public List<RefTypePieceConstitutive> findByCodeTypeDossierDate(
			String codeTypeDossier, Date annee) {
		log.debug("findPieceConstitutive");
		if(codeTypeDossier == null) {
			return null;
		}
		codeTypeDossier = codeTypeDossier.trim().toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select DISTINCT r from RefTypePieceConstitutive r ");
			request.append("where lower(r.ncTypeDossier.code) =:codeTypeDossier ");
			request.append("and (r.dateDebut <=:annee ");
			request.append("and r.dateFin is null) ");
			request.append("or (r.dateDebut <=:annee ");
			request.append("and r.dateFin >=:annee ");
			request.append("and r.dateFin is not null) ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("codeTypeDossier", codeTypeDossier);
			query.setParameter("annee", annee);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			log.debug("findPieceConstitutive successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findPieceConstitutive failed", re);
			throw re;
		}
	}


	@Override
	public List<RefTypePieceConstitutive> findByIdTypeDossier(int idTypeDossier, boolean aFournir) {
		log.debug("findPieceConstitutive");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefTypePieceConstitutive r ");
			request.append("where r.ncTypeDossier.id =:id ");
			request.append("and r.AFournir =:aFournir ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", idTypeDossier);
			query.setParameter("aFournir", aFournir);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			log.debug("findPieceConstitutive successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findPieceConstitutive failed", re);
			throw re;
		}
	}



	@Override
	public int findLastRang(int idTypeDossier, boolean aFournir) {
		log.debug("findLastRang");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefTypePieceConstitutive r ");
			request.append("where r.ncTypeDossier.id =:id ");
			request.append("and r.AFournir =:aFournir ");
			request.append("order by r.rang desc");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", idTypeDossier);
			query.setParameter("aFournir", aFournir);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				log.debug("findLastRang successful");
				return resultList.get(0).getRang();
			}
		} catch (RuntimeException re) {
			log.error("findLastRang failed", re);
			throw re;
		}
		return 0;
	}



	@Override
	public List<RefTypePieceConstitutive> findByIdTypeDossier(int idTypeDossier) {
		log.debug("findPieceConstitutive");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefTypePieceConstitutive r ");
			request.append("where r.ncTypeDossier.id =:id ");
			request.append("order by r.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", idTypeDossier);
			List<RefTypePieceConstitutive> resultList = (List<RefTypePieceConstitutive>) query
					.getResultList();
			log.debug("findPieceConstitutive successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findPieceConstitutive failed", re);
			throw re;
		}
	}
}
