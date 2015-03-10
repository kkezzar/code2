package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectFiliereLmdEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectFiliereLmdEtab;

/**
 * Dao object for Filiere model class RefAffectFiliereLmdEtab.
 * @see dz.gov.mesrs.sii.RefAffectFiliereLmdEtabDto.business.model.bo.RefAffectFiliereLmdEtab
 * @author MESRS CCM
 * @version 1.0
 * @created 18-08-2014 16:44:07
 */
 



@Repository ("refAffectFiliereLmdEtabDaoImpl")  @Transactional
public class RefAffectFiliereLmdEtabDaoImpl implements RefAffectFiliereLmdEtabDao {

	private static final Logger log = LoggerFactory.getLogger(RefAffectFiliereLmdEtabDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectFiliereLmdEtabDao#persist(dz.gov.mesrs.sii.RefAffectFiliereLmdEtabDto.business.model.bo.RefAffectFiliereLmdEtab)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefAffectFiliereLmdEtab transientInstance) {
		log.debug("persisting RefAffectFiliereLmdEtab instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectFiliereLmdEtabDao#remove(dz.gov.mesrs.sii.RefAffectFiliereLmdEtabDto.business.model.bo.RefAffectFiliereLmdEtab)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefAffectFiliereLmdEtab persistentInstance) {
		log.debug("removing RefAffectFiliereLmdEtab instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectFiliereLmdEtabDao#merge(dz.gov.mesrs.sii.RefAffectFiliereLmdEtabDto.business.model.bo.RefAffectFiliereLmdEtab)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefAffectFiliereLmdEtab merge(RefAffectFiliereLmdEtab detachedInstance) {
		log.debug("merging RefAffectFiliereLmdEtab instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefAffectFiliereLmdEtabDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefAffectFiliereLmdEtab findById(int id) {
		log.debug("getting RefAffectFiliereLmdEtab instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefAffectFiliereLmdEtab.class, id);
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
	public List<RefAffectFiliereLmdEtab> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefAffectFiliereLmdEtab> findAll() {
		log.debug("getting all RefAffectFiliereLmdEtab instances");
		try {
			Query query = entityManager.createQuery("from RefAffectFiliereLmdEtab refAffectFiliereLmdEtab");
			log.debug("findAll 'RefAffectFiliereLmdEtab' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefAffectFiliereLmdEtab' failed", re);
			return new ArrayList<RefAffectFiliereLmdEtab>();
		}
	}



	@Override
	public List<RefAffectFiliereLmdEtab> findByIdEtablissement(String idEtablissement) {
		log.debug("getting List of RefAffectFiliereLmdEtab instance with : " + idEtablissement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectFiliereLmdEtab r ");
			request.append("where r.refEtablissement.identifiant = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEtablissement);
			List<RefAffectFiliereLmdEtab> resultList = (List<RefAffectFiliereLmdEtab>) query.getResultList();
			log.debug("findByIdEtablissementt successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEtablissement failed", re);
			throw re;
		}
	}
	
	@Override
	public List<RefAffectFiliereLmdEtab> findByIdEtablissement(Integer idEtablissement) {
		log.debug("getting List of RefAffectFiliereLmdEtab instance with : " + idEtablissement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectFiliereLmdEtab r ");
			request.append("where r.refEtablissement.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEtablissement);
			List<RefAffectFiliereLmdEtab> resultList = (List<RefAffectFiliereLmdEtab>) query.getResultList();
			log.debug("findByIdEtablissementt successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEtablissement failed", re);
			throw re;
		}
	}



	@Override
	public List<RefAffectFiliereLmdEtab> findByCodeFilierelmd(String codeFilierelmd) {
		
		log.debug("getting List of RefAffectFiliereLmdEtab instance with : " + codeFilierelmd);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectFiliereLmdEtab r ");
			request.append("where r.refFiliereLmd.identifiant = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", codeFilierelmd);
			List<RefAffectFiliereLmdEtab> resultList = (List<RefAffectFiliereLmdEtab>) query.getResultList();
			log.debug("findByIdFilierelmd successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdFilierelmd failed", re);
			throw re;
		}
	}



	@Override
	public List<RefAffectFiliereLmdEtab> findByIdFilierelmd(Integer idFilierelmd) {
		log.debug("getting List of RefAffectFiliereLmdEtab instance with : " + idFilierelmd);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectFiliereLmdEtab r ");
			request.append("where r.refFiliereLmd.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idFilierelmd);
			List<RefAffectFiliereLmdEtab> resultList = (List<RefAffectFiliereLmdEtab>) query.getResultList();
			log.debug("findByIdFilierelmd successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdFiliereainelmd failed", re);
			throw re;
		}
	}

}

