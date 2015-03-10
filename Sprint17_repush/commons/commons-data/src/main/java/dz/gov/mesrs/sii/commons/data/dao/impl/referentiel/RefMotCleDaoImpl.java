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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefMotCleDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefMotCle;

/**
 * 
 * @author yselmane on : 9 avr. 2014 10:59:40
 */
@Repository("refMotCleDao")
@Transactional
public class RefMotCleDaoImpl implements RefMotCleDao {

	private static final Logger log = LoggerFactory.getLogger(RefMotCleDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefMotCleDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefMotCle)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefMotCle transientInstance) {
		log.debug("persisting RefMotCle instance");
		try {
			entityManager.persist(transientInstance);
			//entityManager.flush();
			//entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefMotCleDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefMotCle)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefMotCle persistentInstance) {
		log.debug("removing RefMotCle instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefMotCleDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefMotCle)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeMotsClesDocument(String idDocument) {

		log.debug("delete mots cles of document id= " + idDocument);
		try {

			List<RefMotCle> list = findMotsClesDocument(idDocument);

			for (RefMotCle refMotCle : list) {
				remove(refMotCle);
			}

			log.debug("delete " + list.size()
					+ "Mots Cles of Document success.....");

		} catch (RuntimeException re) {
			log.error("delete Mots Cles of Document...", re);

		}

	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefMotCleDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefMotCle)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefMotCle merge(RefMotCle detachedInstance) {
		log.debug("merging RefMotCle instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.NRefMotCleDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefMotCle findById(int id) {
		log.debug("getting RefMotCle instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefMotCle.class, id);
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
	public List<RefMotCle> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefMotCle> findAll() {
		log.debug("getting all RefMotCle instances");
		try {
			Query query = entityManager.createQuery("from RefMotCle refMotCle");
			log.debug("findAll 'RefMotCle' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefMotCle' failed", re);
			return new ArrayList<RefMotCle>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RefMotCle> findMotsClesDocument(String idDocument) {
		log.debug("getting List mots cles  instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefMotCle r ");
			request.append("WHERE r.refDocument.id = :idDocument");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idDocument", Integer.parseInt(idDocument));
			List<RefMotCle> resultList = query.getResultList();
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
	public RefMotCle findMotCleDocument(String idDocument, Integer idMotCle) {
		log.debug("getting mot cle of document  instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefMotCle r ");
			request.append("WHERE r.refDocument.id = :idDocument AND r.nomenclature.id = :idMotCle");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idDocument", Integer.parseInt(idDocument));
			query.setParameter("idMotCle", idMotCle);

			List<RefMotCle> resultList = query.getResultList();

			log.debug("findMotCleOfDocument(String idDocument, Integer idMotCle)");

			if (resultList != null && resultList.size() > 0) {
				return resultList.get(0);
			}

			return null;

		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Nomenclature> findNomenclatureMotsClesDocument(String idDocument) {
		log.debug("getting List mots cles nomenclature instance with idDocument : "
				+ idDocument);
		try {
			StringBuilder request = new StringBuilder(
					"SELECT r.nomenclature FROM RefMotCle r ");
			request.append("WHERE r.refDocument.id = :idDocument");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idDocument", Integer.parseInt(idDocument));
			List<Nomenclature> resultList = query.getResultList();
			log.debug("findNomenclatureMotsClesDocument (String idDocument)");

			return resultList;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
