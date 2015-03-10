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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefVersionDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefVersionDocument;

/**
 * 
 * @author yselmane  on : 9 avr. 2014  10:59:31
 */

@Repository("refVersionDocumentDao")
@Transactional
public class RefVersionDocumentDaoImpl implements RefVersionDocumentDao {

	private static final Logger log = LoggerFactory.getLogger(RefVersionDocumentDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefVersionDocumentDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefVersionDocument)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefVersionDocument transientInstance) {
		log.debug("persisting RefVersionDocument instance");
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefVersionDocumentDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefVersionDocument)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefVersionDocument persistentInstance) {
		log.debug("removing RefVersionDocument instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefVersionDocumentDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefVersionDocument)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefVersionDocument merge(RefVersionDocument detachedInstance) {
		log.debug("merging RefVersionDocument instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.NRefVersionDocumentDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefVersionDocument findById(int id) {
		log.debug("getting RefVersionDocument instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefVersionDocument.class, id);
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
	public List<RefVersionDocument> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefVersionDocument> findAll() {
		log.debug("getting all RefVersionDocument instances");
		try {
			Query query = entityManager
					.createQuery("from RefVersionDocument refVersionDocument");
			log.debug("findAll 'RefVersionDocument' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefVersionDocument' failed", re);
			return new ArrayList<RefVersionDocument>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefVersionDocument> findVersionsOfDocument(String idDocument) {
		log.debug("getting List versions document  instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefVersionDocument r ");
			request.append("WHERE r.refDocument.id = :idDocument");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idDocument", Integer.parseInt(idDocument));
			List<RefVersionDocument> resultList = query.getResultList();
			log.debug("findIndividusOfDocument(String idDocument)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
