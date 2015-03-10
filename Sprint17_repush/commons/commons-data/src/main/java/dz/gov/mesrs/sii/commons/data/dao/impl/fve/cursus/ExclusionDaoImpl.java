package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ExclusionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Exclusion;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class Exclusion.
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.scolformation.business.model.bo.Exclusion.cursus
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("exclusionDao")
@Transactional
public class ExclusionDaoImpl implements ExclusionDao {

	private static final Logger log = LoggerFactory.getLogger(ExclusionDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#persist(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * 
	 * @param transientInstance
	 */

	@Override
	public void persist(Exclusion transientInstance) {
		log.debug("persisting Exclusion instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#remove(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * @param persistentInstance
	 */

	@Override
	public void remove(Exclusion persistentInstance) {
		log.debug("removing Exclusion instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#merge(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * @param detachedInstance
	 */

	@Override
	public Exclusion merge(Exclusion detachedInstance) {
		log.debug("merging Exclusion instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDossierEtudiantDao#findById(int)
	 * @param id
	 */
	@Override
	public Exclusion findById(int id) {
		log.debug("getting Exclusion instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Exclusion.class, id);
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
	public List<Exclusion> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Exclusion> findAll() {
		log.debug("getting all Exclusion instances");
		try {
			Query query = entityManager.createQuery("from Exclusion exclusion");
			log.debug("findAll 'Exclusion' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Exclusion' failed", re);
			return new ArrayList<Exclusion>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.assiduite findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.bo.assiduite)
	 */

	@Override
	public List<Exclusion> findAdvanced(Exclusion searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Exclusion> c = cb.createQuery(Exclusion.class);
			Root<Exclusion> _exclusion = c.from(Exclusion.class);

			c.select(_exclusion).distinct(true);
			Predicate predicate = null;

			// Ann�e
			if (searchBo.getAnneeAcademique() != null) {
				Join<Exclusion, AnneeAcademique> _anneeAcademique = _exclusion
						.join("anneeAcademique");

				predicate = cb.equal(_anneeAcademique.get("id"), searchBo
						.getAnneeAcademique().getId());

			}

			// date Decision
			if (searchBo.getDateDecision() != null
					&& !searchBo.getDateDecision().equals("")
					&& !searchBo.getDateDecision().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_exclusion.get("dateDecision"),
							searchBo.getDateDecision());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_exclusion.get("dateDecision"),
									searchBo.getDateDecision()));
				}
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<Exclusion, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _exclusion
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative
								.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(
								_dossierInscriptionAdministrative.get("id"),
								searchBo.getDossierInscriptionAdministrative()
										.getId()));
					}
				}

				// ETUDAINT
				if (searchBo.getDossierInscriptionAdministrative()
						.getDossierEtudiant() != null) {

					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
							.join("dossierEtudiant");

					if (searchBo.getDossierInscriptionAdministrative()
							.getDossierEtudiant().getNumeroMatricule() != null
							&& !searchBo.getDossierInscriptionAdministrative()
									.getDossierEtudiant().getNumeroMatricule()
									.trim().equals("")
							&& !searchBo.getDossierInscriptionAdministrative()
									.getDossierEtudiant().getNumeroMatricule()
									.trim().equals("null")) {
						if (predicate == null) {
							predicate = cb
									.equal(_dossierEtudiant
											.get("numeroMatricule"),
											searchBo.getDossierInscriptionAdministrative()
													.getDossierEtudiant()
													.getNumeroMatricule());
						} else {
							predicate = cb
									.and(predicate,
											cb.equal(
													_dossierEtudiant
															.get("numeroMatricule"),
													searchBo.getDossierInscriptionAdministrative()
															.getDossierEtudiant()
															.getNumeroMatricule()));
						}
					}
					if (searchBo.getDossierInscriptionAdministrative()
							.getDossierEtudiant().getIndividu() != null) {
						Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant
								.join("individu");
						if (predicate == null) {
							predicate = cb
									.equal(_individu.get("id"),
											searchBo.getDossierInscriptionAdministrative()
													.getDossierEtudiant()
													.getIndividu().getId());
						} else {
							predicate = cb
									.and(predicate,
											cb.equal(
													_individu.get("id"),
													searchBo.getDossierInscriptionAdministrative()
															.getDossierEtudiant()
															.getIndividu()
															.getId()));
						}
					}

				}

			}

			if (predicate != null)
				c.where(predicate);

			TypedQuery<Exclusion> _query = entityManager.createQuery(c);
			List<Exclusion> _resultList = (List<Exclusion>) _query
					.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<Exclusion>();
		}

	}

	@Override
	public List<Exclusion> findByCode(String codeNat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exclusion findByIdDossier(int idDossier) {
		// try {
		// String sqlQuery =
		// "SELECT c FROM Exclusion c WHERE c.dossierEtudiant.id=:idDossier ";
		//
		// TypedQuery<Exclusion> query = entityManager.createQuery(sqlQuery,
		// Exclusion.class);
		// query.setParameter("idDossier", idDossier);
		//
		// List<Exclusion> result = query.getResultList();
		// if (result == null || result.isEmpty()) {
		// return null;
		// }
		// return result.get(0);
		// } catch (RuntimeException re) {
		// log.error("findByIdDossier failed", re);
		// throw re;
		// }
		return null;
	}

	@Override
	public List<Exclusion> findAdvanced2(Exclusion searchBo) {
		try {
			if (searchBo == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"select r from Exception r ");
			request.append(" where (1=1) ");

			if (searchBo.getMc() != null) {
				request.append(" and lower(r.mc) = "
						+ UtilConstant.quotedString(searchBo.getMc()));
			}

			TypedQuery<Exclusion> query = entityManager.createQuery(new String(
					request), Exclusion.class);
			List<Exclusion> resultList = (List<Exclusion>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			throw re;
		}
	}

}
