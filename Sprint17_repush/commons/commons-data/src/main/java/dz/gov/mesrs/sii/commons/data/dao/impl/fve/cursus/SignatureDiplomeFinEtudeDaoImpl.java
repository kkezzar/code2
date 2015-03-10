/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.SignatureDiplomeFinEtudeDaoImpl.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  10:01:18
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude;

/**
 * @author MAKERRI Sofiane on : 18 févr. 2015 10:01:18
 */
@Repository("signatureDiplomeFinEtudeDao")
@Transactional
public class SignatureDiplomeFinEtudeDaoImpl implements
		SignatureDiplomeFinEtudeDao {

	private static final Logger log = LoggerFactory
			.getLogger(SignatureDiplomeFinEtudeDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #persist
	 * (dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(SignatureDiplomeFinEtude transientInstance) {
		log.debug("persisting DiplomeFinEtude instance");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #remove
	 * (dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(SignatureDiplomeFinEtude persistentInstance) {
		log.debug("removing DiplomeFinEtude instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #merge
	 * (dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public SignatureDiplomeFinEtude merge(
			SignatureDiplomeFinEtude detachedInstance) {
		log.debug("merging DiplomeFinEtude instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #findById(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public SignatureDiplomeFinEtude findById(int id) {
		log.debug("getting SignatureDiplomeFinEtude instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(SignatureDiplomeFinEtude.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<SignatureDiplomeFinEtude> findAll() {
		log.debug("getting all SignatureDiplomeFinEtude instances");
		try {
			Query query = entityManager
					.createQuery("from SignatureDiplomeFinEtude signatureDiplomeFinEtude");
			log.debug("findAll 'SignatureDiplomeFinEtude' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'SignatureDiplomeFinEtude' failed", re);
			return new ArrayList<SignatureDiplomeFinEtude>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao
	 * #findUnique(int, long)
	 */
	@Override
	@Transactional(readOnly = true)
	public SignatureDiplomeFinEtude findUnique(int ncSignatureDiplomeId,
			long diplomeFineEtudeId, boolean attestation) {
		log.debug("getting all SignatureDiplomeFinEtude instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select r from SignatureDiplomeFinEtude r ");
			sqlQuery.append(" where r.ncSignatureDiplome.id = "
					+ ncSignatureDiplomeId);
			sqlQuery.append(" and r.diplomeFinEtude.id = " + diplomeFineEtudeId);
            if(attestation) {
            	sqlQuery.append(" and r.attestation = TRUE ");
            } else {
            	sqlQuery.append(" and r.attestation = FALSE ");
            }
			TypedQuery<SignatureDiplomeFinEtude> query = entityManager
					.createQuery(new String(sqlQuery),
							SignatureDiplomeFinEtude.class);

			List<SignatureDiplomeFinEtude> results = query.getResultList();

			if (results == null || results.isEmpty()) {
				return null;
			}
			return results.get(0);

		} catch (RuntimeException re) {
			log.error("findAll 'findUnique' failed", re);
			return null;
		}
	}

}
