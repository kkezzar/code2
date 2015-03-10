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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.GroupePedagogiqueDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class GroupePedagogique.
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.scolformation.business.model.bo.GroupePedagogique.cursus
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("groupePedagogiqueDao")
@Transactional
public class GroupePedagogiqueDaoImpl implements GroupePedagogiqueDao {

	private static final Logger log = LoggerFactory.getLogger(GroupePedagogiqueDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.lmd.business.persistence.jpa.impl.GroupePedagogiqueDao#persist(dz.gov.mesrs.sii.fve.business.model.bo.cursus.lmd.business.model.bo.GroupePedagogique)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public GroupePedagogique persist(GroupePedagogique transientInstance) {
		log.debug("persisting GroupePedagogique instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
			return transientInstance;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.lmd.business.persistence.jpa.impl.GroupePedagogiqueDao#remove(dz.gov.mesrs.sii.fve.business.model.bo.cursus.lmd.business.model.bo.GroupePedagogique)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(GroupePedagogique persistentInstance) {
		log.debug("removing GroupePedagogique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.lmd.business.persistence.jpa.impl.GroupePedagogiqueDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.lmd.business.model.bo.GroupePedagogique)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public GroupePedagogique merge(GroupePedagogique detachedInstance) {
		log.debug("merging GroupePedagogique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NGroupePedagogiqueDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public GroupePedagogique findById(int id) {
		log.debug("getting GroupePedagogique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(GroupePedagogique.class, id);
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
	public List<GroupePedagogique> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<GroupePedagogique> findAll() {
		log.debug("getting all GroupePedagogique instances");
		try {
			Query query = entityManager
					.createQuery("from GroupePedagogique groupePedagogique");
			log.debug("findAll 'GroupePedagogique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'GroupePedagogique' failed", re);
			return new ArrayList<GroupePedagogique>();
		}
	}

	@Override
	public List<GroupePedagogique> findGeneric(String value, String refCodeEtab) {
		if (value == null || refCodeEtab == null) {
			return null;
		}
		log.debug("findGeneric with  value: " + value);
		value = '%' + value.toLowerCase() + '%';
		refCodeEtab = refCodeEtab.toLowerCase();
		try {
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");

			request.append(" where lower(r.refEtablissement.identifiant) = :refCodeEtab ");
			request.append(" and (lower(r.refGroupe.identifiant) like :value ");
			request.append(" or cast(r.capaciteMin as text) like :value ");
			request.append(" or cast(r.capaciteMax as text) like :value) ");
			request.append(" order by r.ncTypeGroupe, r.refGroupe.identifiant ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			query.setParameter("value", value);
			query.setParameter("refCodeEtab", refCodeEtab);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	// @Override
	// public List<GroupePedagogique> findAllSections(String refCodeEtab,
	// Integer typeGroupe, Integer idPeriode) {
	// if (refCodeEtab == null) {
	// return null;
	// }
	// log.debug("findAllSections with  refCodeEtab: " + refCodeEtab);
	// refCodeEtab = refCodeEtab.toLowerCase();
	// try {
	// StringBuilder request = new StringBuilder(
	// "select r from GroupePedagogique r ");
	// request.append(" where lower(r.oof.refCodeEtablissement) = :refCodeEtab ");
	// if (typeGroupe != null) {
	// request.append(" and r.ncTypeGroupe.id =  " + typeGroupe);
	// }
	// if (idPeriode != null) {
	// request.append(" and r.periode = " + idPeriode);
	// }
	// request.append(" order by r.refGroupe.identifiant ");
	// TypedQuery<GroupePedagogique> query = entityManager.createQuery(
	// new String(request), GroupePedagogique.class);
	// query.setParameter("code",
	// UtilConstant.REF_TYPE_GROUPE_PEDAGOGIQUE_CODE
	// .toLowerCase());
	// query.setParameter("refCodeEtab", refCodeEtab);
	// List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
	// .getResultList();
	// log.debug("findAllSections successful");
	// return resultList;
	//
	// } catch (RuntimeException re) {
	// log.error("findAllSections failed", re);
	// throw re;
	// }
	// }

	@Override
	public List<GroupePedagogique> findByOf(Integer oofId, String refCodeEtab) {
		try {
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where (1=1) ");
			if (refCodeEtab != null && !refCodeEtab.isEmpty()) {
				refCodeEtab = UtilConstant.quotedString(refCodeEtab
						.toLowerCase());
				request.append(" and lower(r.refEtablissement.identifiant) = "
						+ refCodeEtab);
			}
			if (oofId != null) {
				request.append(" and r.of.id = " + oofId);
			}
			request.append(" order by r.ncTypeGroupe, r.refGroupe.identifiant ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findByOf successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByOf failed", re);
			throw re;
		}
	}

	@Override
	public List<GroupePedagogique> findAdvanced(GroupePedagogique gp) {
		try {
			if (gp == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where (1=1) ");
			if (gp.getRefEtablissement() != null
					&& gp.getRefEtablissement().getId() != 0) {
				request.append(" and r.refEtablissement.id = "
						+ gp.getRefEtablissement().getId());
			}
			if (gp.getOof() != null && gp.getOof().getId() != 0) {
				request.append(" and r.oof.id = " + gp.getOof().getId());
			}
			if (gp.getOof() != null && gp.getOof().getOffreFormation() != null
					&& gp.getOof().getOffreFormation().getId() != 0) {
				request.append(" and r.oof.offreFormation.id = "
						+ gp.getOof().getOffreFormation().getId());
			}

			if (gp.getAnneeAcademique() != null
					&& gp.getAnneeAcademique().getId() != 0) {
				request.append(" and r.anneeAcademique.id = "
						+ gp.getAnneeAcademique().getId());
			}

			if (gp.getRefGroupe() != null && gp.getRefGroupe().getId() != 0) {
				request.append(" and r.refGroupe.id = "
						+ gp.getRefGroupe().getId());
			}
			if (gp.getNcTypeGroupe() != null
					&& gp.getNcTypeGroupe().getId() != 0) {
				request.append(" and r.ncTypeGroupe.id = "
						+ gp.getNcTypeGroupe().getId());
			}

			// if (gp.getAp() != null) {
			// request.append(" and r.ap.id = " + gp.getAp().getId());
			// }
			if (gp.getPeriode() != null && gp.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = " + gp.getPeriode().getId());
			}
			request.append(" order by r.ncTypeGroupe.id, r.refGroupe.id ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

	@Override
	public GroupePedagogique groupeNameExist(GroupePedagogique groupePedagogique) {
		try {
			if (groupePedagogique == null
					|| groupePedagogique.getNomAffichage() == null) {
				return null;
			}
			if (groupePedagogique.getOof() == null
					|| groupePedagogique.getOof().getId() == 0) {
				return null;
			}
			if (groupePedagogique.getPeriode() == null
					|| groupePedagogique.getPeriode().getId() == 0) {
				return null;
			}

			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where (1=1)");
			if (groupePedagogique.getOof() != null) {
				request.append(" and r.oof.id = "
						+ groupePedagogique.getOof().getId());
			}
			if (groupePedagogique.getPeriode() != null
					&& groupePedagogique.getPeriode().getId() != 0) {
				request.append(" and r.periode.id = "
						+ groupePedagogique.getPeriode().getId());
			}
			if (groupePedagogique.getSection() != null
					&& groupePedagogique.getSection().getId() != 0) {
				request.append(" and r.section.id = "
						+ groupePedagogique.getSection().getId());
			}
			if (groupePedagogique.getAnneeAcademique() != null
					&& groupePedagogique.getAnneeAcademique().getId() != 0) {
				request.append(" and r.anneeAcademique.id = "
						+ groupePedagogique.getAnneeAcademique().getId());
			}
			if (groupePedagogique.getNomAffichage() != null) {
				request.append(" and lower(r.nomAffichage) = "
						+ UtilConstant.quotedString(groupePedagogique
								.getNomAffichage().trim().toLowerCase()));
			}

			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return null;
			}
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("groupeNameExist failed", re);
			throw re;
		}

	}

	@Override
	public List<GroupePedagogique> findGroupesOfSection(
			GroupePedagogique groupePedagogique) {
		if (groupePedagogique.getSection() == null
				|| groupePedagogique.getSection().getId() == 0) {
			return null;
		}
		log.debug("findGroupesOfSection with  sectionId: "
				+ groupePedagogique.getSection().getId());

		try {
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where r.section.id = "
					+ groupePedagogique.getSection().getId());
			if (groupePedagogique.getId() != 0) {
				request.append(" and r.id != " + groupePedagogique.getId());
			}
			if (groupePedagogique.getOof() != null) {
				request.append(" and r.oof.id = "
						+ groupePedagogique.getOof().getId());
			}
			if (groupePedagogique.getAnneeAcademique() != null
					&& groupePedagogique.getAnneeAcademique().getId() != 0) {
				request.append(" and r.anneeAcademique.id = "
						+ groupePedagogique.getAnneeAcademique().getId());
			}
			// if (groupePedagogique.getMc() != null) {
			// request.append(" and r.mc.id != " +
			// groupePedagogique.getMc().getId());
			// }
			// if (groupePedagogique.getUe() != null) {
			// request.append(" and r.ue.id != " +
			// groupePedagogique.getUe().getId());
			// }
			// if (groupePedagogique.getAp() != null) {
			// request.append(" and r.ap.id != "
			// + groupePedagogique.getAp().getId());
			// }
			request.append(" order by r.refGroupe.identifiant ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			// query.setParameter("sectionId",
			// groupePedagogique.getSection().getId());
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findGroupesOfSection successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGroupesOfSection failed", re);
			throw re;
		}
	}

	@Override
	public List<GroupePedagogique> findGroupesOfSection(Integer idSection) {
		if (idSection == null) {
			return null;
		}
		log.debug("findGroupesOfSection with  sectionId: " + idSection);

		try {
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where r.section.id = " + idSection);
			request.append(" order by r.refGroupe.identifiant ");
			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findGroupesOfSection successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGroupesOfSection failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.commons.data.dao.fve.cursus.GroupePedagogiqueDao#findSection
	 * (java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<GroupePedagogique> findSection(Integer oofId, Integer idPeriode) {
		try {
			if (oofId == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from GroupePedagogique r ");
			request.append(" where (1=1) ");
			if (oofId != 0) {
				request.append(" and r.oof.id = " + oofId);
			}
			if (idPeriode != null && idPeriode != 0) {
				request.append(" and r.periode.id = " + idPeriode);
			}
			request.append(" and r.section is null");

			TypedQuery<GroupePedagogique> query = entityManager.createQuery(
					new String(request), GroupePedagogique.class);
			List<GroupePedagogique> resultList = (List<GroupePedagogique>) query
					.getResultList();
			log.debug("findSection successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findSection failed", re);
			throw re;
		}
	}


}
