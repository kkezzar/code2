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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAffectDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectDocument;

/**
 * Dao object for domain model class RefAffectDocument.
 * 
 * @see dz.gov.mesrs.sii.referentiel.business.model.bo.RefAffectDocument
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("refAffectDocumentDaoImpl")
@Transactional
public class RefAffectDocumentDaoImpl implements RefAffectDocumentDao {

	private static final Logger log = LoggerFactory.getLogger(RefAffectDocumentDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAffectDocumentDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefAffectDocument)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefAffectDocument transientInstance) {
		log.debug("persisting RefAffectDocument instance");
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAffectDocumentDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefAffectDocument)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefAffectDocument persistentInstance) {
		log.debug("removing RefAffectDocument instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAffectDocumentDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefAffectDocument)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefAffectDocument merge(RefAffectDocument detachedInstance) {
		log.debug("merging RefAffectDocument instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAffectDocumentDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefAffectDocument findById(int id) {
		log.debug("getting RefAffectDocument instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefAffectDocument.class, id);
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
	public List<RefAffectDocument> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefAffectDocument> findAll() {
		log.debug("getting all RefAffectDocument instances");
		try {
			Query query = entityManager
					.createQuery("from RefAffectDocument refAffectDocument");
			log.debug("findAll 'RefAffectDocument' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefAffectDocument' failed", re);
			return new ArrayList<RefAffectDocument>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefAffectDocument> findIndividusOfDocument(String idDocument) {
		log.debug("getting List Individus of RefAffectation instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefAffectDocument r ");
			request.append("WHERE r.refGroupe is null  AND r.refStructure is null AND r.refDocument.id = :idEntity");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", Integer.parseInt(idDocument));
			List<RefAffectDocument> resultList = query.getResultList();
			log.debug("findIndividusOfDocument(String idDocument)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefAffectDocument> findStructuresOfDocument(String idDocument) {
		log.debug("getting List structures of RefAffectDocument instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r from RefAffectDocument r ");
			request.append("WHERE r.refIndividu is null  AND r.refGroupe is null AND r.refDocument.id = :idEntity ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", Integer.parseInt(idDocument));
			List<RefAffectDocument> resultList = query.getResultList();
			log.debug("findStructuresOfDocument(String idDocument)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefAffectDocument> findGroupesOfDocument(String idDocument) {
		log.debug("getting List groupes of RefAffectDocument instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r from RefAffectDocument r ");
			request.append("WHERE r.refIndividu is null AND r.refStructure is null AND r.refDocument.id = :idEntity");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idEntity", Integer.parseInt(idDocument));
			List<RefAffectDocument> resultList = query.getResultList();
			log.debug("findGroupesOfDocument(String idDocument)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
