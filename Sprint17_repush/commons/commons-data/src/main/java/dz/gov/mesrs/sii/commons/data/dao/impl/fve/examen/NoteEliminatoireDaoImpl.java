/**
 * [dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen.NoteEliminatoireDaoImpl.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:26:26
 */
package dz.gov.mesrs.sii.commons.data.dao.impl.fve.examen;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus.VoeuEtudiantDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:26:26
 */
@Repository("noteEliminatoireDao")
@Transactional
public class NoteEliminatoireDaoImpl implements NoteEliminatoireDao {

	private static final Logger log = LoggerFactory.getLogger(NoteEliminatoireDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#persist
	 * (dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(NoteEliminatoire transientInstance) {
		log.debug("persisting BilanControleContinu instance");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#remove
	 * (dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(NoteEliminatoire persistentInstance) {
		log.debug("removing BilanControleContinu instance");
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
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#merge
	 * (dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public NoteEliminatoire merge(NoteEliminatoire detachedInstance) {
		log.debug("merging NoteEliminatoire instance");
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
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#findById
	 * (int)
	 */
	@Override
	@Transactional(readOnly = true)
	public NoteEliminatoire findById(int id) {
		log.debug("getting NoteEliminatoire instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(NoteEliminatoire.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#findAll
	 * ()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<NoteEliminatoire> findAll() {
		log.debug("getting all NoteEliminatoire instances");
		try {
			Query query = entityManager
					.createQuery("from NoteEliminatoire noteEliminatoire");
			log.debug("findAll 'NoteEliminatoire' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'NoteEliminatoire' failed", re);
			return new ArrayList<NoteEliminatoire>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao#findAdvanced
	 * (dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire)
	 */
	@Override
	public List<NoteEliminatoire> findAdvanced(NoteEliminatoire search) {
		log.debug("getting findAdvanced instances");
		try {
			if (search == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from NoteEliminatoire r ");
			request.append(" where (1=1) ");
			if (search.getOof() != null && search.getOof().getId() != 0) {
				request.append(" and r.oof.id = " + search.getOof().getId());
			}
			if (search.getPeriode() != null && search.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ search.getPeriode().getId());
			}
			if (search.getExamenSession() != null
					&& search.getExamenSession().getId() != 0) {
				request.append(" and r.examenSession.id = "
						+ search.getExamenSession().getId());
			}
			if (search.getExamenSession() != null
					&& search.getExamenSession().getPlanningSession() != null
					&& search.getExamenSession().getPlanningSession().getId() != 0) {
				request.append(" and r.examenSession.planningSession.id = "
						+ search.getExamenSession().getPlanningSession()
								.getId());
			}
			TypedQuery<NoteEliminatoire> query = entityManager.createQuery(
					new String(request), NoteEliminatoire.class);
			List<NoteEliminatoire> resultList = (List<NoteEliminatoire>) query
					.getResultList();
			log.debug("findAdvanced successful");

			return resultList;
		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<NoteEliminatoire>();
		}
	}

}
