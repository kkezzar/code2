package dz.gov.mesrs.sii.commons.data.dao.impl.fve.offreformation;

import java.util.ArrayList;
import java.util.Date;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.MatiereConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.MatiereConstitutive;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Repository("matiereConstitutiveDao")
@Transactional
public class MatiereConstitutiveDaoImpl implements MatiereConstitutiveDao {

	private static final Logger log = LoggerFactory.getLogger(MatiereConstitutiveDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(MatiereConstitutive transientInstance) {
		log.debug("persisting MatiereConstitutive instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	  * 
	  */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(MatiereConstitutive persistentInstance) {
		log.debug("removing MatiereConstitutive instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MatiereConstitutive merge(MatiereConstitutive detachedInstance) {
		log.debug("merging MatiereConstitutive instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public MatiereConstitutive findById(int id) {
		log.debug("getting MatiereConstitutive instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(MatiereConstitutive.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public MatiereConstitutive validate(int matiereConstitutiveId) {
		log.debug("validate MatiereConstitutive");

		try {
			MatiereConstitutive _mc = findById(matiereConstitutiveId);
			if (_mc != null) {
				_mc.setEstValide(true);
				_mc.setDateModification(new Date());
			}

			_mc = entityManager.merge(_mc);

			log.debug("validate MatiereConstitutive successful");
			return _mc;
		} catch (RuntimeException re) {
			log.error("validate MatiereConstitutive failed", re);
			throw re;
		}
	}

	@Override
	public MatiereConstitutive invalidate(int matiereConstitutiveId) {
		log.debug("invalidate MatiereConstitutive");

		try {
			MatiereConstitutive _mc = findById(matiereConstitutiveId);
			if (_mc != null) {
				_mc.setEstValide(false);
				_mc.setDateModification(new Date());
			}

			_mc = entityManager.merge(_mc);

			log.debug("invalidate MatiereConstitutive successful");
			return _mc;
		} catch (RuntimeException re) {
			log.error("invalidate MatiereConstitutive failed", re);
			throw re;
		}
	}

	// /**
	// *
	// */
	// @SuppressWarnings("unchecked")
	// @Override
	// @Transactional(readOnly = true)
	// public List<MatiereConstitutive> findOfUe(int ueId) {
	// log.debug("Recherche des Matières Constitutives d'une UE");
	// try {
	//
	// StringBuilder sb = new StringBuilder();
	// sb.append("select mc from MatiereConstitutive mc join mc.uniteEnseignements ue ");
	// sb.append("where ue.id = :ueId");
	//
	// Query query = entityManager.createQuery(sb.toString());
	// query.setParameter("ueId", ueId);
	//
	// List<MatiereConstitutive> _mcList = query.getResultList();
	//
	// log.debug("Recherche des MCs d'une UE : avec succés");
	//
	// return _mcList;
	//
	// } catch (RuntimeException re) {
	// log.error("Recherche des MCs d'une UE : avec échec", re);
	// throw re;
	// }
	// }
	//
	// @Override
	// public void addToUe(int mcId, int ueId) {
	//
	// MatiereConstitutive _mc = entityManager.find(MatiereConstitutive.class,
	// mcId);
	//
	// UniteEnseignement _ue = entityManager.find(UniteEnseignement.class,
	// ueId);
	//
	// if (_mc != null && _ue != null) {
	// _ue.getMatiereConstitutives().add(_mc);
	// _mc.getUniteEnseignements().add(_ue);
	//
	// entityManager.merge(_ue);
	// entityManager.merge(_mc);
	// }
	// }
	//
	// @Override
	// public void removeFromUe(int mcId, int ueId) {
	// // TODO Auto-generated method stub
	// }

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<MatiereConstitutive> findWithFullText(String fullTextKeyword) {
		log.debug("Recherche par text intégrale des matières constitutives");
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("from MatiereConstitutive matiereConstitutive ");

			sb.append("where lower(matiereConstitutive.code) like :fullTextKeyword ");
			sb.append("or lower(matiereConstitutive.libelleFr) like :fullTextKeyword ");
			sb.append("or lower(matiereConstitutive.libelleAr) like :fullTextKeyword ");
			sb.append("or lower(matiereConstitutive.abreviationFr) like :fullTextKeyword ");
			sb.append("or lower(matiereConstitutive.abreviationAr) like :fullTextKeyword ");

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des param�tres
			query.setParameter("fullTextKeyword",
					'%' + fullTextKeyword.toLowerCase() + '%');

			// Ex�cution de la requete.
			List<MatiereConstitutive> _ueList = query.getResultList();

			log.debug("Recherche par text intégrale des matières constitutives : Avec succés");

			return _ueList;
		} catch (RuntimeException re) {
			log.error(
					"Recherche par text intégrale des mati�res constitutives : Avec échec",
					re);
			return new ArrayList<MatiereConstitutive>();
		}
	}

	@Override
	public List<MatiereConstitutive> findAll() {
		log.debug("getting all MatiereConstitutive instances");
		try {
			TypedQuery<MatiereConstitutive> query = entityManager.createQuery(
					"from MatiereConstitutive matiereConstitutive",
					MatiereConstitutive.class);
			log.debug("findAll 'PlanningSession' successful");
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findAll 'MatiereConstitutive' failed", re);
			throw re;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.MatiereConstitutiveDao
	 * #findByCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public MatiereConstitutive findByCode(String code) {
		log.debug("findByCode");
		try {
			if (code == null) {
				return null;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("from MatiereConstitutive matiereConstitutive ");
			sb.append("where lower(matiereConstitutive.code) =:code ");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("code",  code.trim().toLowerCase());
			// Ex�cution de la requete.
			List<MatiereConstitutive> _ueList = query.getResultList();

			log.debug("findByCode : Avec succés");
			if (_ueList == null || _ueList.isEmpty()) {
				return null;
			}

			return _ueList.get(0);
		} catch (RuntimeException re) {
			log.error("findByCode :  échec", re);
			return null;
		}
	}

}
