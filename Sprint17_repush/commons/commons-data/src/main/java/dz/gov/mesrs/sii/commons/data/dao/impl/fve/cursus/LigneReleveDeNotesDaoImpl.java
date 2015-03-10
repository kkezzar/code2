package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.LigneReleveDeNotesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.LigneReleveDeNotes;

/**
 * Dao object for domain model class LigneReleveDeNotes.
 * 
 * @see dz.gov.mesrs.sii.LigneReleveDeNotesDto.business.model.bo.LigneReleveDeNotes
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("ligneReleveDeNotesDao")
@Transactional
public class LigneReleveDeNotesDaoImpl implements LigneReleveDeNotesDao {

	private static final Logger log = LoggerFactory.getLogger(LigneReleveDeNotesDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.LigneReleveDeNotesDao#persist(dz.gov.mesrs.sii.LigneReleveDeNotesDto.business.model.bo.LigneReleveDeNotes)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(LigneReleveDeNotes transientInstance) {
		log.debug("persisting LigneReleveDeNotes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.LigneReleveDeNotesDao#remove(dz.gov.mesrs.sii.LigneReleveDeNotesDto.business.model.bo.LigneReleveDeNotes)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(LigneReleveDeNotes persistentInstance) {
		log.debug("removing LigneReleveDeNotes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.LigneReleveDeNotesDao#merge(dz.gov.mesrs.sii.LigneReleveDeNotesDto.business.model.bo.LigneReleveDeNotes)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public LigneReleveDeNotes merge(LigneReleveDeNotes detachedInstance) {
		log.debug("merging LigneReleveDeNotes instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NLigneReleveDeNotesDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public LigneReleveDeNotes findById(int id) {
		log.debug("getting LigneReleveDeNotes instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(LigneReleveDeNotes.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<LigneReleveDeNotes> findAll() {
		log.debug("getting all LigneReleveDeNotes instances");
		try {
			Query query = entityManager
					.createQuery("from LigneReleveDeNotes ligneReleveDeNotes");
			log.debug("findAll 'LigneReleveDeNotes' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'LigneReleveDeNotes' failed", re);
			return new ArrayList<LigneReleveDeNotes>();
		}
	}

	@Override	
	public List<LigneReleveDeNotes> findLignesOfReleve(int idReleve) {
		log.info("getting findLignesOfReleve = : ");

		try {

			Query query = entityManager
					.createQuery("SELECT l FROM LigneReleveDeNotes l  WHERE l.releveDeNotes.id =:idReleve");

			query.setParameter("idReleve", idReleve);

			List<LigneReleveDeNotes> resultList = query.getResultList();

			log.debug("findLignesOfReleve successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFilsOfDocuments failed", re);
			return null;
		}

	}
	
	
	@Override
	public void deleteAllLignesOfReleve(int idReleve){
		List<LigneReleveDeNotes> lis = findLignesOfReleve(idReleve);
		
		for (LigneReleveDeNotes ligneReleveDeNotes : lis) {
			remove(ligneReleveDeNotes);
		}
	}
	
	
}
