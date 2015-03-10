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

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.UniteEnseignementDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * Classe d'implémentation de l'interface de persistance des unités
 * d'enseignement en utilisant JPA/Hibernate comme framework d'accés aux données
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Repository("uniteEnseignementDao")
@Transactional
public class UniteEnseignementDaoImpl implements UniteEnseignementDao {

	private static final Logger log = LoggerFactory.getLogger(UniteEnseignementDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(UniteEnseignement transientInstance) {
		log.debug("persisting UniteEnseignement instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(UniteEnseignement persistentInstance) {
		log.debug("removing UniteEnseignement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UniteEnseignement merge(UniteEnseignement detachedInstance) {
		log.debug("merging UniteEnseignement instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public UniteEnseignement validate(int uniteEnseignementId) {

		log.debug("validate UniteEnseignement");

		try {
			UniteEnseignement _ue = findById(uniteEnseignementId);
			if (_ue != null) {
				_ue.setEstValide(true);
				_ue.setDateModification(new Date());
			}

			_ue = entityManager.merge(_ue);

			log.debug("validate UniteEnseignement successful");
			return _ue;
		} catch (RuntimeException re) {
			log.error("validate UniteEnseignement failed", re);
			throw re;
		}
	}

	@Override
	public UniteEnseignement invalidate(int uniteEnseignementId) {

		log.debug("invalidate UniteEnseignement");

		try {
			UniteEnseignement _ue = findById(uniteEnseignementId);
			if (_ue != null) {
				_ue.setEstValide(false);
				_ue.setDateModification(new Date());
			}

			_ue = entityManager.merge(_ue);

			log.debug("invalidate UniteEnseignement successful");
			return _ue;
		} catch (RuntimeException re) {
			log.error("invalidate UniteEnseignement failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UniteEnseignement findById(int id) {
		log.debug("getting UniteEnseignement instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(UniteEnseignement.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<UniteEnseignement> find(String code, String libelleFr,
			String libelleAr, String abreviationFr, String abreviationAr) {

		log.debug("Recherche multi critères des unites enseignements");
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("from UniteEnseignement uniteEnseignement ");
			sb.append("where (1=1) ");

			if (code != null)
				sb.append("and uniteEnseignement.code = :code ");
			if (libelleFr != null)
				sb.append("and uniteEnseignement.libelleFr like :libelleFr ");
			if (libelleAr != null)
				sb.append("and uniteEnseignement.libelleAr like :libelleAr ");
			if (abreviationFr != null)
				sb.append("and uniteEnseignement.abreviationFr like :abreviationFr ");
			if (abreviationAr != null)
				sb.append("and uniteEnseignement.abreviationAr like :abreviationAr ");

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des paramètres
			if (code != null)
				query.setParameter("code", code);
			if (libelleFr != null)
				query.setParameter("libelleFr", libelleFr);
			if (libelleAr != null)
				query.setParameter("libelleAr", libelleAr);
			if (abreviationFr != null)
				query.setParameter("abreviationFr", abreviationFr);
			if (abreviationAr != null)
				query.setParameter("abreviationAr", abreviationAr);

			// Exécution de la requete.
			List<UniteEnseignement> _ueList = query.getResultList();

			log.debug("Recherche multi critères des unites enseignements : Avec succés");

			return _ueList;
		} catch (RuntimeException re) {
			log.error(
					"Recherche multi critères des unites enseignements : Avec échec",
					re);
			return new ArrayList<UniteEnseignement>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<UniteEnseignement> findWithFullText(String fullTextKeyword) {
		log.debug("Recherche par text intégrale des unites enseignements");
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("from UniteEnseignement uniteEnseignement ");

			sb.append("where lower(uniteEnseignement.code) like :fullTextKeyword ");
			sb.append("or lower(uniteEnseignement.libelleFr) like :fullTextKeyword ");
			sb.append("or lower(uniteEnseignement.libelleAr) like :fullTextKeyword ");
			sb.append("or lower(uniteEnseignement.abreviationFr) like :fullTextKeyword ");
			sb.append("or lower(uniteEnseignement.abreviationAr) like :fullTextKeyword ");

			Query query = entityManager.createQuery(sb.toString());

			// Ajout des paramétres
			query.setParameter("fullTextKeyword",
					'%' + fullTextKeyword.toLowerCase() + '%');

			// Exécution de la requete.
			List<UniteEnseignement> _ueList = query.getResultList();

			log.debug("Recherche par text intégrale des unites enseignements : Avec succés");

			return _ueList;
		} catch (RuntimeException re) {
			log.error(
					"Recherche par text intégrale des unites enseignements : Avec échec",
					re);
			return new ArrayList<UniteEnseignement>();
		}

	}

	@Override
	public List<UniteEnseignement> findAll() {

		log.debug("getting all UniteEnseignement instances");

		try {

			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					"SELECT ue FROM UniteEnseignement ue",
					UniteEnseignement.class);
			log.debug("findAll 'UniteEnseignement' successful");
			return query.getResultList();

		} catch (RuntimeException re) {

			log.error("findAll 'UniteEnseignement' failed", re);
			return new ArrayList<UniteEnseignement>();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.UniteEnseignementDao
	 * #findByCode(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UniteEnseignement findByCode(String code) {
		log.debug("getting all findByCode instances");
		try {
			if (code == null) {
				return null;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT ue FROM UniteEnseignement ue");
			sb.append(" where lower(ue.code) =:code  ");
			
			
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(sb.toString(), UniteEnseignement.class);
			query.setParameter("code", code.trim().toLowerCase());
			//query.setMaxResults(1);
			List<UniteEnseignement>  result = query.getResultList();
			log.debug("findByCode 'UniteEnseignement' successful");
			if(result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {

			log.error("findByCode failed", re);
			return null;
		}
	}

}
