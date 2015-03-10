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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ReintegrationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Exclusion;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Reintegration;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;

/**
 * Dao object for domain model class Reintegration.
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.scolformation.business.model.bo.Reintegration.cursus
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("reintegrationDao")
@Transactional
public class ReintegrationDaoImpl implements ReintegrationDao {

	private static final Logger log = LoggerFactory.getLogger(ReintegrationDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#persist(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * 
	 * @param transientInstance
	 */

	@Override
	public void persist(Reintegration transientInstance) {
		log.debug("persisting Reintegration instance");
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
	public void remove(Reintegration persistentInstance) {
		log.debug("removing Reintegration instance");
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
	public Reintegration merge(Reintegration detachedInstance) {
		log.debug("merging Reintegration instance");
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
	public Reintegration findById(int id) {
		log.debug("getting Reintegration instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(Reintegration.class, id);
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
	public List<Reintegration> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Reintegration> findAll() {
		log.debug("getting all Reintegration instances");
		try {
			Query query = entityManager
					.createQuery("from Reintegration reintegration");
			log.debug("findAll 'Reintegration' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Reintegration' failed", re);
			return new ArrayList<Reintegration>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.assiduite findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.bo.assiduite)
	 */

	@Override
	public List<Reintegration> findAdvanced(Reintegration searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Reintegration> c = cb
					.createQuery(Reintegration.class);
			Root<Reintegration> _reintegration = c.from(Reintegration.class);

			c.select(_reintegration).distinct(true);
			Predicate predicate = null;

			if (predicate != null)
				c.where(predicate);

			TypedQuery<Reintegration> _query = entityManager.createQuery(c);
			List<Reintegration> _resultList = (List<Reintegration>) _query
					.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<Reintegration>();
		}

	}

	@Override
	public List<Reintegration> findByCode(String codeNat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reintegration findByIdExclusion(int idExclusion) {

		return null;
	}

	@Override
	public List<Reintegration> findGeneric(String value) {
		if (value == null) {
			return null;
		}
		log.debug("findGeneric with  value: " + value);
		value = '%' + value.toLowerCase() + '%';
		try {
			StringBuilder request = new StringBuilder(
					"select r from reintegration r ");

			request.append(" where lower(r.refCodeEtablissement) = :refCodeEtab ");
			request.append(" and (lower(r.refCodeGroupe) like :value ");
			request.append(" or cast(r.capaciteMin as text) like :value ");
			request.append(" or cast(r.capaciteMax as text) like :value) ");

			TypedQuery<Reintegration> query = entityManager.createQuery(
					new String(request), Reintegration.class);
			query.setParameter("value", value);
			List<Reintegration> resultList = (List<Reintegration>) query
					.getResultList();
			log.debug("findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
	}

	@Override
	public List<Reintegration> findReintegrationsByIdExclusion(int idExclusion) {
		try {
			String sqlQuery = "SELECT d FROM Reintegration d WHERE d.exclusion.id=:idExclusion ";

			TypedQuery<Reintegration> query = entityManager.createQuery(
					sqlQuery, Reintegration.class);
			query.setParameter("idExclusion", idExclusion);

			List<Reintegration> result = query.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result;
		} catch (RuntimeException re) {
			log.error("findReintegrationsByIdExclusion failed", re);
			throw re;
		}
	}

	/**
	 * @author Mounir.MESSAOUDI on : 21 oct. 2014 11:38:42
	 * @param reintegrationSearchBo
	 * @param refCodeEtablissement
	 * @param searchOuverturesOffreFormationBos
	 * @return
	 */
	@Override
	public List<Reintegration> findAdvanced(
			Reintegration reintegrationSearchBo, String refCodeEtablissement,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Reintegration> c = cb
					.createQuery(Reintegration.class);
			Root<Reintegration> _dia = c.from(Reintegration.class);
			c.orderBy(cb.asc(_dia.get("dateReintegration")));

			Join<Reintegration, Exclusion> _exclusion = _dia.join("exclusion");
			Join<Exclusion, AnneeAcademique> _anneeAcademique = _exclusion
					.join("anneeAcademique");

			Join<Exclusion, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _exclusion
					.join("dossierInscriptionAdministrative");

			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
					.join("dossierEtudiant");

			c.select(_dia).distinct(true);
			Predicate predicate = null;

			// Reintegration

			// id
			if (reintegrationSearchBo.getId() != 0) {
				predicate = cb.equal(_dia.get("id"),
						reintegrationSearchBo.getId());
			}

			// reintegration validee
			if (reintegrationSearchBo.getReintegrationValidee() != null) {

				Boolean v = reintegrationSearchBo.getReintegrationValidee();
				if (v != null && v == Boolean.FALSE) {
					if (predicate == null) {
						predicate = cb.isNull(_dia.get("reintegrationValidee"));
					} else {
						predicate = cb.and(predicate,
								cb.isNull(_dia.get("reintegrationValidee")));
					}

				} else {
					if (predicate == null) {
						predicate = cb
								.equal(_dia.get("reintegrationValidee"),
										reintegrationSearchBo
												.getReintegrationValidee());
					} else {
						predicate = cb.and(predicate,
								cb.equal(_dia.get("reintegrationValidee"),
										reintegrationSearchBo
												.getReintegrationValidee()));
					}
				}

			}

			// resultat de la reintegration
			if (reintegrationSearchBo.getResultat() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("resultat"),
							reintegrationSearchBo.getResultat());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_dia.get("resultat"),
							reintegrationSearchBo.getResultat()));
				}
			}

			// condition sur le dossier d'etudiant
			if (reintegrationSearchBo.getExclusion() != null) {
				// id exclusion
				if (reintegrationSearchBo.getExclusion().getId() != 0) {

					if (predicate == null) {
						predicate = cb.equal(_exclusion.get("id"),
								reintegrationSearchBo.getExclusion().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_exclusion
								.get("id"), reintegrationSearchBo
								.getExclusion().getId()));
					}
				}

				// type d'exclusion
				if (reintegrationSearchBo.getExclusion()
						.getRefCodeTypeExclusion() != null) {
					if (predicate == null) {
						predicate = cb.equal(_exclusion
								.get("refCodeTypeExclusion"),
								reintegrationSearchBo.getExclusion()
										.getRefCodeTypeExclusion());
					} else {
						predicate = cb.and(predicate, cb.equal(_exclusion
								.get("refCodeTypeExclusion"),
								reintegrationSearchBo.getExclusion()
										.getRefCodeTypeExclusion()));
					}

				}

				// type de cause
				if (reintegrationSearchBo.getExclusion().getRefCodeTypeCause() != null) {
					if (predicate == null) {
						predicate = cb.equal(
								_exclusion.get("refCodeTypeCause"),
								reintegrationSearchBo.getExclusion()
										.getRefCodeTypeCause());
					} else {
						predicate = cb.and(predicate, cb.equal(_exclusion
								.get("refCodeTypeCause"), reintegrationSearchBo
								.getExclusion().getRefCodeTypeCause()));
					}
				}

				// annne academique
				if (reintegrationSearchBo.getExclusion().getAnneeAcademique() != null) {
					if (predicate == null) {
						predicate = cb.equal(_anneeAcademique.get("id"),
								reintegrationSearchBo.getExclusion()
										.getAnneeAcademique().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(
								_anneeAcademique.get("id"),
								reintegrationSearchBo.getExclusion()
										.getAnneeAcademique().getId()));
					}
				}

				// dossier d'inscription administrative
				if (reintegrationSearchBo.getExclusion()
						.getDossierInscriptionAdministrative() != null) {
					DossierInscriptionAdministrative d = reintegrationSearchBo
							.getExclusion()
							.getDossierInscriptionAdministrative();

					// id
					if (d.getId() != 0) {
						if (predicate == null) {
							predicate = cb
									.equal(_dossierInscriptionAdministrative
											.get("id"), d.getId());
						} else {
							predicate = cb.and(predicate,
									cb.equal(_dossierInscriptionAdministrative
											.get("id"), d.getId()));
						}
					}

					if (d.getDossierEtudiant() != null) {

						// matricule de l'etudiant
						if (d.getDossierEtudiant().getNumeroMatricule() != null) {
							if (predicate == null) {
								predicate = cb
										.equal(_dossierEtudiant
												.get("numeroMatricule"), d
												.getDossierEtudiant()
												.getNumeroMatricule());
							} else {
								predicate = cb.and(predicate,
										cb.equal(_dossierEtudiant
												.get("numeroMatricule"), d
												.getDossierEtudiant()
												.getNumeroMatricule()));
							}
						}

						// matricule de bac
						// if (d.getDossierEtudiant().getRefIndividu() != null)
						// {
						// if (predicate == null) {
						// predicate = cb.equal(_dossierEtudiant
						// .get("refIndividu"), d.getDossierEtudiant()
						// .getRefIndividu());
						// } else {
						// predicate = cb.and(predicate, cb.equal(
						// _dossierEtudiant.get("refIndividu"), d
						// .getDossierEtudiant()
						// .getRefIndividu()));
						// }
						// }

					}
				}

				// etablissement
				if (refCodeEtablissement != null) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative
								.get("refCodeEtablissement"),
								refCodeEtablissement);
					} else {
						predicate = cb.and(predicate, cb.equal(
								_dossierInscriptionAdministrative
										.get("refCodeEtablissement"),
								refCodeEtablissement));
					}
				}

				// offres de formation
				if (searchOuverturesOffreFormationBos != null
						&& !searchOuverturesOffreFormationBos.isEmpty()) {

					Path<Object> exp = _dossierInscriptionAdministrative
							.get("ouvertureOffreFormation");
					if (predicate == null) {
						predicate = exp.in(searchOuverturesOffreFormationBos);
					} else {
						predicate = cb.and(predicate,
								exp.in(searchOuverturesOffreFormationBos));
					}
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<Reintegration> _query = entityManager.createQuery(c);

				return _query.getResultList();
			} else
				return new ArrayList<Reintegration>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<Reintegration>();
		}
	}
}
