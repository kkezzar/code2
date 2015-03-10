/**
 * [dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.AssociationGroupePedagogiqueDaoImpl.java] 
 * @author MAKERRI Sofiane on : 2 oct. 2014  09:49:56
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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AssociationGroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AssociationGroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;

/**
 * Dao object for domain model class AssociationGroupePedagogiqueDaoImpl;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.AssociationGroupePedagogiqueDto
 *      ;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("associationGroupePedagogiqueDao")
@Transactional
public class AssociationGroupePedagogiqueDaoImpl implements
		AssociationGroupePedagogiqueDao {
	private static final Logger log = LoggerFactory.getLogger(AssociationGroupePedagogiqueDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.fve.business.persistence.cursus.GroupePedagogiqueApDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.offreformation.
	 *      AssociationGroupePedagogiqueDto)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AssociationGroupePedagogique transientInstance) {
		log.debug("persisting AssociationGroupePedagogique instance");
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
	 * @see dz.gov.dz.gov.mesrs.sii.fve.business.persistence.cursus.GroupePedagogiqueApDao#remove(dz.gov.mesrs.sii.fve.business.model.bo.cursus.lmd.business.model.bo.offreformation.AssociationGroupePedagogiqueDto)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AssociationGroupePedagogique persistentInstance) {
		log.debug("removing AssociationGroupePedagogique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.fve.business.persistence.cursus.GroupePedagogiqueApDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.AssociationGroupePedagogiqueDto)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AssociationGroupePedagogique merge(
			AssociationGroupePedagogique detachedInstance) {
		log.debug("merging AssociationGroupePedagogique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.dz.gov.mesrs.sii.fve.business.persistence.cursus.GroupePedagogiqueApDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public AssociationGroupePedagogique findById(int id) {
		log.debug("getting AssociationGroupePedagogique instance with id: "
				+ id);
		try {
			log.debug("get successful");
			return entityManager.find(AssociationGroupePedagogique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AssociationGroupePedagogique> findAll() {
		log.debug("getting all AssociationGroupePedagogique instances");
		try {
			Query query = entityManager
					.createQuery("from AssociationGroupePedagogique associationGroupePedagogique");
			log.debug("findAll 'AssociationGroupePedagogique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AssociationGroupePedagogique' failed", re);
			return new ArrayList<AssociationGroupePedagogique>();
		}
	}

	@Override
	public List<AssociationGroupePedagogique> findByGroupePedagogiqueId(int gpId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from AssociationGroupePedagogique r ");
			request.append(" where r.groupePedagogique.id = " + gpId);
			request.append(" order by r.ap.code ");
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);

			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			log.debug("findByGroupePedagogiqueId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByGroupePedagogiqueId failed", re);
			throw re;
		}
	}

	@Override
	public boolean associationExist(Integer id, Integer gpId,
			Integer rattachementMcId, Integer apId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from AssociationGroupePedagogique r ");
			request.append(" where r.groupePedagogique.id = " + gpId);
			request.append(" and r.rattachementMc.id = " + rattachementMcId);
			request.append(" and r.ap.id = " + apId);
			if (id != null) {
				request.append(" and r.id != " + id);
			}
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);
			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			log.debug("associationExist successful");
			return (resultList != null && !resultList.isEmpty());
		} catch (RuntimeException re) {
			log.error("associationExist failed", re);
			throw re;
		}

	}

	@Override
	public boolean groupeNameExist(Integer oofId, Integer periodeId,
			String nom,
			AssociationGroupePedagogique associationGroupePedagogique) {
		try {
			if (nom == null) {
				return true;
			}
			nom = nom.toLowerCase();
			StringBuilder request = new StringBuilder(
					"select r from AssociationGroupePedagogique r ");
			request.append(" where (1=1)");
			request.append(" and lower(r.groupePedagogique.nomAffichage) =:nom ");
			if (associationGroupePedagogique.getRattachementMc() != null) {
				request.append(" and r.rattachementMc.id = "
						+ associationGroupePedagogique.getRattachementMc()
								.getId());
			}
			request.append(" and r.ap.id = "
					+ associationGroupePedagogique.getAp().getId());
			if (periodeId != null) {
				request.append(" and r.groupePedagogique.periode.id = "
						+ periodeId);
			}
			if (oofId != null) {
				request.append(" and r.groupePedagogique.oof.id = " + oofId);
			}
			if (associationGroupePedagogique.getId() != 0) {
				request.append(" and r.id != "
						+ associationGroupePedagogique.getId());
			}
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);
			query.setParameter("nom", nom);
			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			log.debug("groupeNameExist successful");
			return (resultList != null && !resultList.isEmpty());
		} catch (RuntimeException re) {
			log.error("groupeNameExist failed", re);
			throw re;
		}
	}

	@Override
	public List<UniteEnseignement> findUeByGroupePedagogiqueId(Integer gpId) {
		try {
			if (gpId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select distinct r.rattachementMc.uniteEnseignement from AssociationGroupePedagogique r ");
			request.append(" where r.groupePedagogique.id = " + gpId);
			request.append(" order by r.rattachementMc.uniteEnseignement.libelleFr ");
			TypedQuery<UniteEnseignement> query = entityManager.createQuery(
					new String(request), UniteEnseignement.class);

			List<UniteEnseignement> resultList = (List<UniteEnseignement>) query
					.getResultList();
			log.debug("findUeByGroupePedagogiqueId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findUeByGroupePedagogiqueId failed", re);
			throw re;
		}
	}

	@Override
	public List<RattachementMc> findRattachementMcByGroupePedagogiqueId(
			Integer gpId) {
		try {
			if (gpId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select distinct r.rattachementMc from AssociationGroupePedagogique r ");
			request.append(" where r.groupePedagogique.id = " + gpId);
			// request.append(" order by r.rattachementMc.matiereConstitutive.libelleFr ");
			TypedQuery<RattachementMc> query = entityManager.createQuery(
					new String(request), RattachementMc.class);

			List<RattachementMc> resultList = (List<RattachementMc>) query
					.getResultList();
			log.debug("findRattachementMcByGroupePedagogiqueId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findRattachementMcByGroupePedagogiqueId failed", re);
			throw re;
		}
	}

	@Override
	public AssociationGroupePedagogique findUnique(int gpId,
			int rattachementMc, int apId) {
		try {

			StringBuilder request = new StringBuilder(
					"select distinct r from AssociationGroupePedagogique r ");
			request.append(" where r.groupePedagogique.id = " + gpId);
			request.append(" and r.rattachementMc.id = " + rattachementMc);
			request.append(" and r.ap.id = " + apId);
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);

			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			log.debug("findUnique successful");
			return resultList.get(0);

		} catch (RuntimeException re) {
			log.error("findUnique failed", re);
			throw re;
		}
	}

	@Override
	public List<AssociationGroupePedagogique> findByRattachementMcAndAp(
			Integer rattachementMcId, Integer apId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from AssociationGroupePedagogique r ");
			request.append(" where r.rattachementMc.id = " + rattachementMcId);
			if (apId != null) {
				request.append(" and r.ap.id = " + apId);
			}
			request.append(" order by r.groupePedagogique.nomAffichage ");
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);

			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			log.debug("findByRattachementMcAndAp successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRattachementMcAndAp failed", re);
			throw re;
		}
	}

	@Override
	public List<GroupePedagogique> findByRattachementMcId(Integer oofId,
			Integer periodeId, Integer rattachementMcId) {
		try {
			StringBuilder request = new StringBuilder(
					"select distinct r.groupePedagogique from AssociationGroupePedagogique r ");
			request.append(" where r.rattachementMc.id = " + rattachementMcId);
			if (oofId != null) {
				request.append(" and r.groupePedagogique.oof.id = " + oofId);
			}
			if (periodeId != null) {
				request.append(" and r.groupePedagogique.periode.id = "
						+ periodeId);
			}
			request.append(" order by r.groupePedagogique.nomAffichage ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);

			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findByRattachementMcId successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByRattachementMcId failed", re);
			throw re;
		}
	}

	@Override
	public List<AssociationGroupePedagogique> findAdvanced(
			Integer rattachementMcId, Integer apId, Integer oofId,
			Integer periodeId) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from AssociationGroupePedagogique r ");
			request.append(" where r.rattachementMc.id = " + rattachementMcId);
			if (apId != null) {
				request.append(" and r.ap.id = " + apId);
			}
			if (oofId != null) {
				request.append(" and r.groupePedagogique.oof.id = " + oofId);
			}

			if (periodeId != null) {
				request.append(" and r.groupePedagogique.periode.id = "
						+ periodeId);
			}
			request.append(" order by r.groupePedagogique.nomAffichage ");
			TypedQuery<AssociationGroupePedagogique> query = entityManager
					.createQuery(new String(request),
							AssociationGroupePedagogique.class);

			List<AssociationGroupePedagogique> resultList = (List<AssociationGroupePedagogique>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

}
