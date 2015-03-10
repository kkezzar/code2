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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectDomLmdEtabDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDomLmdEtab;

/**
 * Dao object for domain model class RefAffectDomLmdEtab.
 * @see dz.gov.mesrs.sii.RefAffectDomLmdEtabDto.business.model.bo.RefAffectDomLmdEtab
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Repository ("refAffectDomLmdEtabDaoImpl")  @Transactional
public class RefAffectDomLmdEtabDaoImpl implements RefAffectDomLmdEtabDao {

	private static final Logger log = LoggerFactory.getLogger(RefAffectDomLmdEtabDaoImpl.class.getName());
	@PersistenceContext (type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectDomLmdEtabDao#persist(dz.gov.mesrs.sii.RefAffectDomLmdEtabDto.business.model.bo.RefAffectDomLmdEtab)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefAffectDomLmdEtab transientInstance) {
		log.debug("persisting RefAffectDomLmdEtab instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectDomLmdEtabDao#remove(dz.gov.mesrs.sii.RefAffectDomLmdEtabDto.business.model.bo.RefAffectDomLmdEtab)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefAffectDomLmdEtab persistentInstance) {
		log.debug("removing RefAffectDomLmdEtab instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefAffectDomLmdEtabDao#merge(dz.gov.mesrs.sii.RefAffectDomLmdEtabDto.business.model.bo.RefAffectDomLmdEtab)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefAffectDomLmdEtab merge(RefAffectDomLmdEtab detachedInstance) {
		log.debug("merging RefAffectDomLmdEtab instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefAffectDomLmdEtabDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefAffectDomLmdEtab findById(int id) {
		log.debug("getting RefAffectDomLmdEtab instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefAffectDomLmdEtab.class, id);
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
	public List<RefAffectDomLmdEtab> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefAffectDomLmdEtab> findAll() {
		log.debug("getting all RefAffectDomLmdEtab instances");
		try {
			Query query = entityManager.createQuery("from RefAffectDomLmdEtab refAffectDomLmdEtab");
			log.debug("findAll 'RefAffectDomLmdEtab' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefAffectDomLmdEtab' failed", re);
			return new ArrayList<RefAffectDomLmdEtab>();
		}
	}



	


	@Override
	public List<RefAffectDomLmdEtab> findByCodeDomainelmd(String codeDomainelmd) {
		
		log.debug("getting List of RefAffectDomLmdEtab instance with : " + codeDomainelmd);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDomLmdEtab r ");
			request.append("where r.refDomaineLMD.identifiant = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", codeDomainelmd);
			List<RefAffectDomLmdEtab> resultList = (List<RefAffectDomLmdEtab>) query.getResultList();
			log.debug("findByIdDomainelmd successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdDomainelmd failed", re);
			throw re;
		}
	}



	@Override
	public List<RefAffectDomLmdEtab> findByIdDomainelmd(Integer idDomainelmd) {
		log.debug("getting List of RefAffectDomLmdEtab instance with : " + idDomainelmd);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDomLmdEtab r ");
			request.append("where r.refDomaineLMD.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idDomainelmd);
			List<RefAffectDomLmdEtab> resultList = (List<RefAffectDomLmdEtab>) query.getResultList();
			log.debug("findByIdDomainelmd successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdDomainelmd failed", re);
			throw re;
		}
	}
	

	@Override
	public List<RefAffectDomLmdEtab> findByCodeEtablissement(String codeEtablissement) {
		log.debug("getting List of RefAffectDomLmdEtab instance with : " + codeEtablissement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDomLmdEtab r ");
			request.append("where r.refEtablissement.identifiant = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", codeEtablissement);
			List<RefAffectDomLmdEtab> resultList = (List<RefAffectDomLmdEtab>) query.getResultList();
			log.debug("findByCodeEtablissementt successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByCodeEtablissement failed", re);
			throw re;
		}
	}
	
	@Override
	public List<RefAffectDomLmdEtab> findByIdEtablissement(String idEtablissement) {
		log.debug("getting List of RefAffectDomLmdEtab instance with : " + idEtablissement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDomLmdEtab r ");
			request.append("where r.refEtablissement.identifiant = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEtablissement);
			List<RefAffectDomLmdEtab> resultList = (List<RefAffectDomLmdEtab>) query.getResultList();
			log.debug("findByIdEtablissementt successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEtablissement failed", re);
			throw re;
		}
	}
	
	@Override
	public List<RefAffectDomLmdEtab> findByIdEtablissement(Integer idEtablissement) {
		log.debug("getting List of RefAffectDomLmdEtab instance with : " + idEtablissement);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDomLmdEtab r ");
			request.append("where r.refEtablissement.id = :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", idEtablissement);
			List<RefAffectDomLmdEtab> resultList = (List<RefAffectDomLmdEtab>) query.getResultList();
			log.debug("findByIdEtablissementt successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByIdEtablissement failed", re);
			throw re;
		}
	}

	
}
