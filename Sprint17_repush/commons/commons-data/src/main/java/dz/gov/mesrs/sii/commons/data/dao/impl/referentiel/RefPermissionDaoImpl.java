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

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefPermissionDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAffectation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefPermission;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefPermission.
 * 
 * @see dz.gov.mesrs.sii.RefPermissionDto.business.model.bo.RefPermission
 * @author MESRS CCM
 * 
 */

@Repository("refPermissionDaoImpl")
@Transactional
public class RefPermissionDaoImpl implements RefPermissionDao {

	private static final Logger log = LoggerFactory.getLogger(RefPermissionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPermissionDao#persist(dz.gov.mesrs.sii.RefPermissionDto.business.model.bo.RefPermission)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefPermission transientInstance) {
		log.debug("persisting RefPermission instance");
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
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPermissionDao#remove(dz.gov.mesrs.sii.RefPermissionDto.business.model.bo.RefPermission)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefPermission persistentInstance) {
		log.debug("removing RefPermission instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefPermissionDao#merge(dz.gov.mesrs.sii.RefPermissionDto.business.model.bo.RefPermission)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefPermission merge(RefPermission detachedInstance) {
		log.debug("merging RefPermission instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefPermissionDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefPermission findById(int id) {
		log.debug("getting RefPermission instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefPermission.class, id);
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
	public List<RefPermission> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefPermission> findAll() {
		log.debug("getting all RefPermission instances");
		try {
			Query query = entityManager
					.createQuery("from RefPermission refPermission");
			log.debug("findAll 'RefPermission' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefPermission' failed", re);
			return new ArrayList<RefPermission>();
		}
	}

	@Override
	public List<RefPermission> findGeneric(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RefPermission> findByIdfIndividu(String identifiant) {
		log.debug("getting all RefPermission instances for individu with idf = "
				+ identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refAffectation.refIndividu.identifiant = :identifiant ");
			request.append("group by r.refFonction.refModule.refDomaine.rang, r.refFonction.refModule.rang, r.refFonction.rang, r.id ");
			request.append("order by r.refFonction.refModule.refDomaine.rang, r.refFonction.refModule.rang, r.refFonction.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("identifiant", identifiant);
			;
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;
		} catch (RuntimeException e) {
			log.error("findAll 'RefPermission' failed", e);
			throw e;
		}

	}

	@Override
	public RefPermission findByIdFonction(int id) {

		log.debug("get RefPermission instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refFonction.id =:id");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefPermission successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdModule(int idRole, int id) {
		log.debug("get RefPermission instance with role id = "
				+ idRole + "id module = " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.role.id =:idRole ");
			request.append("and r.refFonction.refModule.id =:id ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idRole", idRole);
			query.setParameter("id", id);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdDomaine(int _idRole, int id) {
		log.debug("get RefPermission instance with role id = "
				+ _idRole + "id domaine = " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.role.id =:_idRole ");
			request.append("and r.refFonction.refModule.refDomaine.id =:id ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("_idRole", _idRole);
			query.setParameter("id", id);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdfGroupe(String idf) {
		log.debug("get RefPermission instance with : " + idf);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refAffectation.refGroupeByGroupe.identifiant =:idf ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idf", idf);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdfStructure(String idf) {
		log.debug("get RefPermission instance with : " + idf);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refAffectation.refStructure.identifiant =:idf ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idf", idf);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdRole(String domaine, int idf) {
		log.debug("get RefPermission instance with : " + idf);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.role.id =:idf ");
			request.append("and r.refFonction.refFonction is null ");
			request.append("and lower(r.refFonction.refModule.refDomaine.nom) = " + UtilConstant.quotedString(domaine.toLowerCase()));
			request.append(" order by r.refFonction.refModule.refDomaine.rang, r.refFonction.refModule.rang, r.refFonction.rang");
			//request.append("order by r.refFonction.rang");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idf", idf);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public RefPermission findByIdFonction(int idRole, int id) {
		log.debug("get RefPermission instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refFonction.id =:id ");
			request.append("and r.role.id =:idRole ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			query.setParameter("idRole", idRole);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefPermission successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefIndividu> findModele(String identifiant, int idfAffectation) {
		log.debug("get RefIndividu instance with : ");
		try {
			StringBuilder request = new StringBuilder(
					"select distinct p2.refAffectation.refIndividu from RefPermission p1, RefPermission p2 ");
			request.append("where p2.refAffectation.refIndividu.identifiant !=:identifiant ");
			request.append("and p1.refAffectation.identifiant =:idfAffectation ");
			request.append("and (p1.refAffectation.refGroupeByGroupe = p2.refAffectation.refGroupeByGroupe ");
			request.append("or p1.refAffectation.refStructure = p2.refAffectation.refStructure) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("identifiant", identifiant);
			query.setParameter("idfAffectation", idfAffectation);
			List<RefIndividu> resultList = (List<RefIndividu>) query
					.getResultList();
			log.debug("findAll 'RefIndividu' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefIndividu failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdfIndividu(int idAffectation,
			String identifiant) {
		log.debug("get RefPermission instance with affectation id = "
				+ idAffectation + "individu id = " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refAffectation.identifiant =:idAffectation ");
			request.append("and r.refAffectation.refIndividu.identifiant =:identifiant ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idAffectation", idAffectation);
			query.setParameter("identifiant", identifiant);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public RefAffectation findAffectationModele(String identifiant,
			int idfAffectation) {
		log.debug("get RefIndividu instance with : ");
		try {
			StringBuilder request = new StringBuilder(
					"select distinct p2.refAffectation from RefPermission p1, RefPermission p2 ");
			request.append("where p2.refAffectation.refIndividu.identifiant =:identifiant ");
			request.append("and p1.refAffectation.identifiant =:idfAffectation ");
			request.append("and (p1.refAffectation.refGroupeByGroupe = p2.refAffectation.refGroupeByGroupe ");
			request.append("or p1.refAffectation.refStructure = p2.refAffectation.refStructure) ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("identifiant", identifiant);
			query.setParameter("idfAffectation", idfAffectation);
			List<RefAffectation> resultList = (List<RefAffectation>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefAffectation successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("get RefAffectation failed", re);
			throw re;
		}
		return null;
	}

	@Override
	public RefPermission findByIdFonction(String idfIndividu, int id) {
		log.debug("get RefPermission instance with : " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.refFonction.id =:id ");
			request.append("and r.refAffectation.refIndividu.identifiant =:idfIndividu ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("id", id);
			query.setParameter("idfIndividu", idfIndividu);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				log.debug("get RefPermission successful");
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findByIdAction(int idRole, int id) {
		log.debug("get RefPermission instance with role id = "
				+ idRole + "id module = " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.role.id =:idRole ");
			request.append("and r.refFonction.refFonction.id =:id ");
			request.append("order by r.refFonction.nom ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idRole", idRole);
			query.setParameter("id", id);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}

	@Override
	public List<RefPermission> findActions(int idRole, int id) {
		log.debug("get RefPermission instance with role id = "
				+ idRole + "id module = " + id);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefPermission r ");
			request.append("where r.role.id =:idRole ");
			request.append("and r.refFonction.refFonction.id =:id ");
			request.append("and r.consulter = TRUE ");
			request.append("order by r.refFonction.rang ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("idRole", idRole);
			query.setParameter("id", id);
			List<RefPermission> resultList = (List<RefPermission>) query
					.getResultList();
			log.debug("findAll 'RefPermission' successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("get RefPermission failed", re);
			throw re;
		}
	}
}
