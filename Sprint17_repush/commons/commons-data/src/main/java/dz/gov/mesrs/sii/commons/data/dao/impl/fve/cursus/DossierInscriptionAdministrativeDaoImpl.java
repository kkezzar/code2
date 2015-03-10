package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.Calendar;
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




import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierInscriptionAdministrativeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Repository("dossierInscriptionAdministrativeDao")
@Transactional
public class DossierInscriptionAdministrativeDaoImpl implements
		DossierInscriptionAdministrativeDao {

	private static final Logger log = LoggerFactory.getLogger(DossierInscriptionAdministrativeDaoImpl.class.getName());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DossierInscriptionAdministrative transientInstance) {
		log.debug("persisting DossierInscriptionAdministrative instance");
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DossierInscriptionAdministrative persistentInstance) {
		log.debug("removing DossierInscriptionAdministrative instance");
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
	public DossierInscriptionAdministrative merge(
			DossierInscriptionAdministrative detachedInstance) {
		log.debug("merging DossierInscriptionAdministrative instance");
		try {
			log.debug("merge successful");
			detachedInstance = entityManager.merge(detachedInstance);
			entityManager.flush();
			return detachedInstance;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public DossierInscriptionAdministrative findById(int id) {
		log.debug("getting DossierInscriptionAdministrative instance with id: "
				+ id);
		try {
			log.debug("get successful");
			return entityManager.find(DossierInscriptionAdministrative.class,
					id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DossierInscriptionAdministrative> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DossierInscriptionAdministrative> findAll() {
		log.debug("getting all DossierInscriptionAdministrative instances");
		try {
			Query query = entityManager
					.createQuery("from DossierInscriptionAdministrative dossierInscriptionAdministrative");
			log.debug("findAll 'DossierInscriptionAdministrative' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DossierInscriptionAdministrative' failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DossierInscriptionAdministrative> findAdvanced(
			DossierInscriptionAdministrative diaSearch) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierInscriptionAdministrative> c = cb
					.createQuery(DossierInscriptionAdministrative.class);
			Root<DossierInscriptionAdministrative> _dia = c
					.from(DossierInscriptionAdministrative.class);
			c.orderBy(cb.asc(_dia.get("refCodeDomaine")));
			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
					.join("dossierEtudiant");
			Join<DossierInscriptionAdministrative, AnneeAcademique> _anneeAcademique = _dia
					.join("anneeAcademique");

			Join<DossierInscriptionAdministrative, Dossier> _dossier = _dia
					.join("dossier");
			Join<DossierInscriptionAdministrative, Dossier> _situation = _dossier
					.join("situationEntite");
			c.select(_dia).distinct(true);
			Predicate predicate = null;

			// condition sur le dossier d'étudiant
			if (diaSearch.getDossierEtudiant() != null) {

				if (diaSearch.getDossierEtudiant().getId() != 0) {
					predicate = cb.equal(_dossierEtudiant.get("id"), diaSearch
							.getDossierEtudiant().getId());
				}
				if (diaSearch.getDossierEtudiant().getNumeroMatricule() != null
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("")
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("null")) {
					if (predicate == null) {
						predicate = cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule()));
					}
				}
				if (diaSearch.getDossierEtudiant().getIndividu() != null) {

					if (predicate == null) {
						predicate = cb.equal(_anneeAcademique.get("id"),
								diaSearch.getAnneeAcademique().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_anneeAcademique
								.get("id"), diaSearch.getAnneeAcademique()
								.getId()));
					}
				}

				if (diaSearch.getDossierEtudiant().getIndividu() != null) {
					Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant
							.join("individu");
					if (predicate == null) {
						predicate = cb.equal(_individu.get("id"), diaSearch
								.getDossierEtudiant().getIndividu().getId());
					} else {
						predicate = cb.and(
								predicate,
								cb.equal(_individu.get("id"), diaSearch
										.getDossierEtudiant().getIndividu()
										.getId()));
					}
				}
			}
			// condition sur l'année académique
			if (diaSearch.getAnneeAcademique() != null) {

				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), diaSearch
							.getAnneeAcademique().getId());
				} else {
					predicate = cb
							.and(predicate, cb.equal(
									_anneeAcademique.get("id"), diaSearch
											.getAnneeAcademique().getId()));
				}
			}
			// condition sur l'ouverture d'offre de formation
			if (diaSearch.getOuvertureOffreFormation() != null) {
				Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _oof = _dia
						.join("ouvertureOffreFormation");

				if (diaSearch.getOuvertureOffreFormation().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_oof.get("id"), diaSearch
								.getOuvertureOffreFormation().getId());
					} else {
						predicate = cb
								.and(predicate, cb.equal(_oof.get("id"),
										diaSearch.getOuvertureOffreFormation()
												.getId()));
					}
				}

				// if (diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement() != null
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim().equals("")
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim()
				// .equals("null")) {
				// if (predicate == null) {
				// predicate = cb.equal(_oof.get("refCodeEtablissement"),
				// diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement());
				// } else {
				// predicate = cb.and(predicate, cb.equal(_oof
				// .get("refCodeEtablissement"), diaSearch
				// .getOuvertureOffreFormation()
				// .getRefCodeEtablissement()));
				// }
				// }

				if (diaSearch.getOuvertureOffreFormation().getOffreFormation() != null) {
					Join<OuvertureOffreFormation, OffreFormation> _of = _oof
							.join("offreFormation");
					if (diaSearch.getOuvertureOffreFormation()
							.getOffreFormation().getCycle() != null) {
						Join<OffreFormation, Cycle> _ofc = _of.join("cycle");
						if (predicate == null) {
							predicate = cb.equal(_ofc.get("id"), diaSearch
									.getOuvertureOffreFormation()
									.getOffreFormation().getCycle().getId());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_ofc.get("id"), diaSearch
											.getOuvertureOffreFormation()
											.getOffreFormation().getCycle()
											.getId()));
						}
					}
				}
			}
			// condition sur le type d'inscription
			if (diaSearch.getRefCodeTypeInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription()));
				}
			}

			if (diaSearch.getNumeroInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("numeroInscription"),
							diaSearch.getNumeroInscription());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("numeroInscription"),
									diaSearch.getNumeroInscription()));
				}
			}

			if (diaSearch.getRefEtablissement() != null
					&& diaSearch.getRefEtablissement().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("refEtablissement"),
							diaSearch.getRefEtablissement());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("refEtablissement"),
									diaSearch.getRefEtablissement()));
				}
			}
			// condition sur le niveau
			if (diaSearch.getNiveau() != null
					&& diaSearch.getNiveau().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("niveau"),
							diaSearch.getNiveau());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dia.get("niveau"),
											diaSearch.getNiveau()));
				}
			}

			// condition sur la situation

			if (diaSearch.getDossier() != null
					&& diaSearch.getDossier().getSituationEntite() != null) {

				if (predicate == null) {
					predicate = cb.equal(_situation.get("id"), diaSearch
							.getDossier().getSituationEntite().getId());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_situation.get("id"), diaSearch
											.getDossier().getSituationEntite()
											.getId()));
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DossierInscriptionAdministrative> _query = entityManager
						.createQuery(c);
				return _query.getResultList();
			} else
				return new ArrayList<DossierInscriptionAdministrative>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DossierInscriptionAdministrative> findDossierInscriptionsBy(
			int dossierEtudiantId) {
		try {
			CriteriaBuilder _cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierInscriptionAdministrative> _criteria = _cb
					.createQuery(DossierInscriptionAdministrative.class);
			Root<DossierInscriptionAdministrative> _dia = _criteria
					.from(DossierInscriptionAdministrative.class);

			_criteria.select(_dia).distinct(true);

			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
					.join("dossierEtudiant");
			Predicate predicate = _cb.equal(_dossierEtudiant.get("id"),
					dossierEtudiantId);

			_criteria.where(predicate);

			TypedQuery<DossierInscriptionAdministrative> _query = entityManager
					.createQuery(_criteria);
			List<DossierInscriptionAdministrative> _resultList = (List<DossierInscriptionAdministrative>) _query
					.getResultList();

			return _resultList;
		} catch (RuntimeException re) {
			log.error("findDossierInscriptionsBy  failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}

	}

	@Override
	@Transactional(readOnly = true)
	public DossierInscriptionAdministrative findPresentDossierInscriptionForEtudiant(
			int dossierEtudiantId) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);

		int premiereAnnee;

		// 9 : mois de septembre (pour test on a choisi juin), en supposant que
		// l'annee commence en mois de septembre

		if (month >= Calendar.JUNE) {
			premiereAnnee = year;
		} else {
			premiereAnnee = --year;
		}

		try {
			String hqlQuery = "SELECT d FROM DossierInscriptionAdministrative d WHERE d.dossierEtudiant.id=:dossierEtudiantId "
					+ "AND d.anneeAcademique.premiereAnnee = :premiereAnnee ";

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(hqlQuery,
							DossierInscriptionAdministrative.class);
			query.setParameter("dossierEtudiantId", dossierEtudiantId)
					.setParameter("premiereAnnee", premiereAnnee);

			List<DossierInscriptionAdministrative> result = query
					.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}

	}

	/**
	 * Rechercher le dossier d'inscription de l'annee academique precend d'un
	 * etudiant
	 * 
	 * @param dossierEtudiantId
	 * 
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 10:27:08
	 * @param dossierEtudiantId
	 */
	@Override
	@Transactional(readOnly = true)
	public DossierInscriptionAdministrative findPrecdentDossierInscriptionForEtudiant(
			int dossierEtudiantId) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);

		// annee prcedent
		year = year - 1;

		int premiereAnnee;

		// 9 : mois de septembre (pour test on a choisi juin), en supposant que
		// l'annee commence en mois de septembre

		if (month >= Calendar.JUNE) {
			premiereAnnee = year;
		} else {
			premiereAnnee = --year;
		}

		try {
			String hqlQuery = "SELECT DISTINCT d FROM DossierInscriptionAdministrative d WHERE d.dossierEtudiant.id=:dossierEtudiantId "
					+ "AND d.anneeAcademique.premiereAnnee = :premiereAnnee ";

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(hqlQuery,
							DossierInscriptionAdministrative.class);
			query.setParameter("dossierEtudiantId", dossierEtudiantId)
					.setParameter("premiereAnnee", premiereAnnee);

			List<DossierInscriptionAdministrative> result = query
					.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	/**
	 * Rechercher le derniere dossier d'inscription ajoutee d'un etudiant
	 * 
	 * @param dossierEtudiantId
	 * 
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 10:27:08
	 * @param dossierEtudiantId
	 */
	@Override
	@Transactional(readOnly = true)
	public DossierInscriptionAdministrative findDernierDossierInscriptionForEtudiant(
			int dossierEtudiantId) {

		try {
			String hqlQuery = "SELECT d FROM DossierInscriptionAdministrative d "
					+ "WHERE d.dossierEtudiant.id=:dossierEtudiantId "
					+ "ORDER BY d.dossier.dateCreation DESC";

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(hqlQuery,
							DossierInscriptionAdministrative.class);
			query.setParameter("dossierEtudiantId", dossierEtudiantId);

			List<DossierInscriptionAdministrative> result = query
					.getResultList();
			if (result == null || result.isEmpty()) {
				return null;
			}
			return result.get(0);
		} catch (RuntimeException re) {
			log.error("findByIdDossier failed", re);
			throw re;
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<DossierInscriptionAdministrative> findAdvancedByOuvertureOffres(
			DossierInscriptionAdministrative diaSearch,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBos) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierInscriptionAdministrative> c = cb
					.createQuery(DossierInscriptionAdministrative.class);
			Root<DossierInscriptionAdministrative> _dia = c
					.from(DossierInscriptionAdministrative.class);
			c.orderBy(cb.asc(_dia.get("refCodeDomaine")));
			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
					.join("dossierEtudiant");

			Join<DossierInscriptionAdministrative, AnneeAcademique> _anneeAcademique = _dia
					.join("anneeAcademique");

			Join<DossierInscriptionAdministrative, Dossier> _dossier = _dia
					.join("dossier");
			// Join<DossierInscriptionAdministrative, Dossier> _situation =
			// _dossier
			// .join("situationEntite");

			c.select(_dia).distinct(true);
			Predicate predicate = null;

			// condition sur le dossier d'etudiant
			if (diaSearch.getDossierEtudiant() != null) {

				if (diaSearch.getDossierEtudiant().getId() != 0) {
					predicate = cb.equal(_dossierEtudiant.get("id"), diaSearch
							.getDossierEtudiant().getId());
				}
				if (diaSearch.getDossierEtudiant().getNumeroMatricule() != null
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("")
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("null")) {
					if (predicate == null) {
						predicate = cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule()));
					}
				}

				if (diaSearch.getDossierEtudiant().getIndividu() != null) {
					Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant
							.join("individu");
					if (predicate == null) {
						predicate = cb.equal(_individu.get("id"), diaSearch
								.getDossierEtudiant().getIndividu().getId());
					} else {
						predicate = cb.and(
								predicate,
								cb.equal(_individu.get("id"), diaSearch
										.getDossierEtudiant().getIndividu()
										.getId()));
					}
				}
			}

			// condition sur l'annee academique
			if (diaSearch.getAnneeAcademique() != null) {

				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), diaSearch
							.getAnneeAcademique().getId());
				} else {
					predicate = cb
							.and(predicate, cb.equal(
									_anneeAcademique.get("id"), diaSearch
											.getAnneeAcademique().getId()));
				}
			}
			// condition sur l'ouverture d'offre de formation

			// condition sur la situation
			if (searchOuverturesOffreFormationBos != null
					&& !searchOuverturesOffreFormationBos.isEmpty()) {

				Path<Object> exp = _dia.get("ouvertureOffreFormation");
				if (predicate == null) {
					predicate = exp.in(searchOuverturesOffreFormationBos);
				} else {
					predicate = cb.and(predicate,
							exp.in(searchOuverturesOffreFormationBos));
				}
				// }
				//
				// if (searchOuverturesOffreFormationBos != null
				// && !searchOuverturesOffreFormationBos.isEmpty()) {
				// Join<DossierInscriptionAdministrative,
				// OuvertureOffreFormation> _oof = _dia
				// .join("ouvertureOffreFormation");
				//
				// // if (diaSearch.getOuvertureOffreFormation().getId() != 0) {
				// if (predicate == null) {
				// predicate = cb.in(_oof.get("id"),
				// idsOuvertureOffreFormation);
				// } else {
				// predicate = cb.and(predicate, cb.i(_oof.get("id"),
				// diaSearch.getOuvertureOffreFormation().getId()));
				// }
				// }

				// if (diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement() != null
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim().equals("")
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim()
				// .equals("null")) {
				// if (predicate == null) {
				// predicate = cb.equal(_oof.get("refCodeEtablissement"),
				// diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement());
				// } else {
				// predicate = cb.and(predicate, cb.equal(_oof
				// .get("refCodeEtablissement"), diaSearch
				// .getOuvertureOffreFormation()
				// .getRefCodeEtablissement()));
				// }
				// }
				//
				// if
				// (diaSearch.getOuvertureOffreFormation().getOffreFormation()
				// != null) {
				// Join<OuvertureOffreFormation, OffreFormation> _of = _oof
				// .join("offreFormation");
				// if (diaSearch.getOuvertureOffreFormation()
				// .getOffreFormation().getRefCodeCycle() != null
				// && !diaSearch.getOuvertureOffreFormation()
				// .getOffreFormation().getRefCodeCycle()
				// .trim().equals("")
				// && !diaSearch.getOuvertureOffreFormation()
				// .getOffreFormation().getRefCodeCycle()
				// .trim().equals("null")) {
				// if (predicate == null) {
				// predicate = cb.equal(_of.get("refCodeCycle"),
				// diaSearch.getOuvertureOffreFormation()
				// .getOffreFormation()
				// .getRefCodeCycle());
				// } else {
				// predicate = cb.and(
				// predicate,
				// cb.equal(_of.get("refCodeCycle"), diaSearch
				// .getOuvertureOffreFormation()
				// .getOffreFormation()
				// .getRefCodeCycle()));
				// }
				// }
				// }
			}

			// condition sur le type d'inscription
			if (diaSearch.getRefCodeTypeInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription()));
				}
			}

			if (diaSearch.getNumeroInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("numeroInscription"),
							diaSearch.getNumeroInscription());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("numeroInscription"),
									diaSearch.getNumeroInscription()));
				}
			}

			if (diaSearch.getRefEtablissement() != null
					&& diaSearch.getRefEtablissement().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("refEtablissement"),
							diaSearch.getRefEtablissement());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("refEtablissement"),
									diaSearch.getRefEtablissement()));
				}
			}
			// condition sur le niveau

			if (diaSearch.getNiveau() != null
					&& diaSearch.getNiveau().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("niveau"),
							diaSearch.getNiveau());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dia.get("niveau"),
											diaSearch.getNiveau()));
				}
			}

			// condition sur la situation
			if (searchSitutationEntiteBos != null
					&& !searchSitutationEntiteBos.isEmpty()) {

				Path<Object> exp = _dossier.get("situationEntite");
				if (predicate == null) {
					predicate = exp.in(searchSitutationEntiteBos);
				} else {
					predicate = cb.and(predicate,
							exp.in(searchSitutationEntiteBos));
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DossierInscriptionAdministrative> _query = entityManager
						.createQuery(c);
				return _query.getResultList();
			} else
				return new ArrayList<DossierInscriptionAdministrative>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DossierInscriptionAdministrative> findByDia(
			DossierInscriptionAdministrative dia, String sortField,
			Integer pageSize, Integer first, Boolean descending) {
		// Criteria criteria = getHibernateCriteria(dia);
		// if (sortField != null) {
		// if (Boolean.TRUE.equals(descending)) {
		// criteria.addOrder(Order.desc(sortField));
		// } else {
		// criteria.addOrder(Order.asc(sortField));
		// }
		//
		// }
		// if (pageSize != null) {
		// criteria.setMaxResults(pageSize);
		// criteria.setFirstResult(first);
		// }
		// return criteria.list();

		// CriteriaQuery<DossierInscriptionAdministrative> criteria =
		// getHibernateCriteria(dia);

		// CriteriaBuilder criteriaBuilder = getHibernateCriteria(dia);

		CriteriaQuery<DossierInscriptionAdministrative> criteria = (CriteriaQuery<DossierInscriptionAdministrative>) getJPACriteria(
				dia, null, null, false);

		// CriteriaQuery<DossierInscriptionAdministrative> criteria =
		// criteriaBuilder
		// .createQuery(DossierInscriptionAdministrative.class);
		// Root<DossierInscriptionAdministrative> entityRoot = criteria
		// .from(DossierInscriptionAdministrative.class);
		//
		// criteria.select(entityRoot).distinct(true);
		// criteria.where(r.getValue());

		if (sortField != null) {
			if (Boolean.TRUE.equals(descending)) {
				criteria = criteria
						.orderBy((List<javax.persistence.criteria.Order>) Order
								.desc(sortField));
			} else {
				criteria = criteria
						.orderBy((List<javax.persistence.criteria.Order>) Order
								.asc(sortField));
			}
		}

		TypedQuery<DossierInscriptionAdministrative> _query = entityManager
				.createQuery(criteria);

		if (pageSize != null) {
			_query.setMaxResults(pageSize);
			_query.setFirstResult(first);
		}

		return _query.getResultList();

	}

	@Override
	public Long countByDia(DossierInscriptionAdministrative diaSearch) {
		try {

			CriteriaQuery<Long> criteria = (CriteriaQuery<Long>) getJPACriteria(
					diaSearch, null, null, true);

			TypedQuery<Long> t = entityManager.createQuery(criteria);

			return t.getSingleResult();

			// return entityManager.createQuery(cqCount).getSingleResult();

			// Entry<CriteriaQuery<DossierInscriptionAdministrative>, Predicate>
			// r = getHibernateCriteria(dia);
			//
			// CriteriaQuery<DossierInscriptionAdministrative> criteria = r
			// .getKey();
			//

			// final CriteriaBuilder builder =
			// entityManager.getCriteriaBuilder();
			// final CriteriaQuery<Long> countCriteria = builder
			// .createQuery(Long.class);
			//
			// countCriteria.s
			// countCriteria.select(criteria.getSelection().getCompoundSelectionItems().);
			//
			// Root<DossierInscriptionAdministrative> entityRoot = countCriteria
			// .from(DossierInscriptionAdministrative.class);
			//
			// countCriteria.select(builder.count(entityRoot));
			// final Predicate groupRestriction =
			// criteria.getGroupRestriction(), fromRestriction = criteria
			// .getRestriction();
			// if (groupRestriction != null) {
			// countCriteria.having(groupRestriction);
			// }
			// if (fromRestriction != null) {
			// countCriteria.where(fromRestriction);
			// }
			// countCriteria.groupBy(criteria.getGroupList());
			// countCriteria.distinct(criteria.isDistinct());
			//
			// TypedQuery<Long> t = entityManager.createQuery(countCriteria);
			//
			// return t.getSingleResult();

			// *---------------------

			// CriteriaQuery<DossierInscriptionAdministrative> criteria = r
			// .getKey();
			//
			// CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			//
			// CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
			//
			// Root<DossierInscriptionAdministrative> entityRoot = cqCount
			// .from(DossierInscriptionAdministrative.class);
			//
			// criteria.getParameters();
			// cqCount.subquery(criteria.)
			// cqCount.select(cb.count(entityRoot));
			// cqCount.where(r.getValue());
			//
			// TypedQuery<Long> t = entityManager.createQuery(cqCount);
			// return t.getSingleResult();

			// CriteriaQuery<DossierInscriptionAdministrative> criteria =
			// getHibernateCriteria(dia);

			// CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			// CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);

			// CriteriaBuilder criteriaBuilder = getHibernateCriteria(dia);

			// Entry<CriteriaBuilder, Predicate> r = getHibernateCriteria(dia);
			//
			// CriteriaBuilder criteriaBuilder = r.getKey();
			//
			// CriteriaQuery<Long> criteria = criteriaBuilder
			// .createQuery(Long.class);
			// Root<DossierInscriptionAdministrative> entityRoot = criteria
			// .from(DossierInscriptionAdministrative.class);
			//
			// criteria.select(criteriaBuilder.count(entityRoot));
			// // criteria.where(predicate);
			// TypedQuery<Long> t = entityManager.createQuery(criteria);
			// return t.getSingleResult();

			// Root<?> entityRoot = cqCount
			// .from(criteria.getResultType());
			// Root<DossierInscriptionAdministrative> entityRoot = criteria
			// .from(DossierInscriptionAdministrative.class);
			//
			// CriteriaBuilder criteriaBuilder =
			// entityManager.getCriteriaBuilder();
			// criteria.select(criteriaBuilder.count(entityRoot));
			// cqCount.where(criteria.getRestriction());

			// TypedQuery<Long> t = entityManager.createQuery(criteria);
			//
			// return t.getSingleResult();

			// return entityManager.createQuery(cqCount).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;

	}

	/**
	 * Recherche avancee avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 11:40:37
	 * @param dia
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBo
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DossierInscriptionAdministrative> findByDia(
			DossierInscriptionAdministrative dia,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBo, String sortField,
			Integer pageSize, Integer first, Boolean descending) {

		CriteriaQuery<DossierInscriptionAdministrative> criteria = (CriteriaQuery<DossierInscriptionAdministrative>) getJPACriteria(
				dia, searchOuverturesOffreFormationBos,
				searchSitutationEntiteBo, false);

		if (sortField != null) {
			if (Boolean.TRUE.equals(descending)) {
				criteria = criteria
						.orderBy((List<javax.persistence.criteria.Order>) Order
								.desc(sortField));
			} else {
				criteria = criteria
						.orderBy((List<javax.persistence.criteria.Order>) Order
								.asc(sortField));
			}
		}

		TypedQuery<DossierInscriptionAdministrative> _query = entityManager
				.createQuery(criteria);

		if (pageSize != null) {
			_query.setMaxResults(pageSize);
			_query.setFirstResult(first);
		}

		return _query.getResultList();

	}

	/**
	 * Recherche avancee avec pagination ( le count )
	 * 
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 11:40:37
	 * @param dia
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteBo
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	@Override
	public Long countByDia(DossierInscriptionAdministrative diaSearch,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBo) {
		try {

			@SuppressWarnings("unchecked")
			CriteriaQuery<Long> criteria = (CriteriaQuery<Long>) getJPACriteria(
					diaSearch, searchOuverturesOffreFormationBos,
					searchSitutationEntiteBo, true);

			TypedQuery<Long> t = entityManager.createQuery(criteria);

			return t.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;

	}

	/**
	 * Retourne un CriteriaQuery pour une requette de recherche ou pour count
	 * 
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 11:40:37
	 * @param diaSearch
	 * @param forCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private CriteriaQuery<?> getJPACriteria(
			DossierInscriptionAdministrative diaSearch,
			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos,
			List<SituationEntite> searchSitutationEntiteBos, boolean forCount) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<?> c;
		Root<DossierInscriptionAdministrative> _dia;

		if (forCount) {
			c = cb.createQuery(Long.class);
			_dia = c.from(DossierInscriptionAdministrative.class);
			((CriteriaQuery<Long>) c).select(cb.count(_dia));

		} else {
			c = cb.createQuery(DossierInscriptionAdministrative.class);
			_dia = c.from(DossierInscriptionAdministrative.class);
			((CriteriaQuery<DossierInscriptionAdministrative>) c).select(_dia)
					.distinct(true);

		}

		Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
				.join("dossierEtudiant");
		Join<DossierInscriptionAdministrative, AnneeAcademique> _anneeAcademique = _dia
				.join("anneeAcademique");
		Join<DossierInscriptionAdministrative, Dossier> _dossier = _dia
				.join("dossier");
		Join<DossierInscriptionAdministrative, Dossier> _situation = _dossier
				.join("situationEntite");

		Predicate predicate = null;

		// condition sur le dossier d'etudiant
		if (diaSearch.getDossierEtudiant() != null) {

			if (diaSearch.getDossierEtudiant().getId() != 0) {
				predicate = cb.equal(_dossierEtudiant.get("id"), diaSearch
						.getDossierEtudiant().getId());
			}
			if (diaSearch.getDossierEtudiant().getNumeroMatricule() != null
					&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
							.trim().equals("")
					&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
							.trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierEtudiant
							.get("numeroMatricule"), diaSearch
							.getDossierEtudiant().getNumeroMatricule());
				} else {
					predicate = cb.and(predicate, cb.equal(_dossierEtudiant
							.get("numeroMatricule"), diaSearch
							.getDossierEtudiant().getNumeroMatricule()));
				}
			}

			// individu
			RefIndividu individu = diaSearch.getDossierEtudiant().getIndividu();
			if (individu != null) {

				Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant
						.join("individu");

				// NIN(Identifiant)
				if (individu.getIdentifiant() != null
						&& !individu.getIdentifiant().isEmpty()) {
					if (predicate == null) {
						predicate = cb.equal(_individu.get("identifiant"),
								individu.getIdentifiant());
					} else {
						predicate = cb.and(
								predicate,
								cb.equal(_individu.get("identifiant"),
										individu.getIdentifiant()));
					}
				}

				// nom
				if (individu.getNomLatin() != null
						&& !individu.getNomLatin().isEmpty()) {

					if (predicate == null) {
						predicate = cb.like(
								cb.lower(_individu.<String> get("nomLatin"))
								// _individu.get("nomLatin").as(String.class)
								, individu.getNomLatin().toLowerCase());

						predicate = cb.or(predicate, cb.like(
								cb.lower(_individu.<String> get("nomArabe")),
								individu.getNomArabe().toLowerCase()));
					} else {
						predicate = cb.and(predicate, cb.like(
								cb.lower(_individu.<String> get("nomLatin"))
								// _individu.get("nomLatin").as(String.class)
								, individu.getNomLatin().toLowerCase()));

						predicate = cb.or(predicate, cb.like(
								cb.lower(_individu.<String> get("nomArabe")),
								individu.getNomArabe().toLowerCase()));
					}
				}

				// prenom
				if (individu.getPrenomLatin() != null
						&& !individu.getPrenomLatin().isEmpty()) {
					if (predicate == null) {
						predicate = cb.like(
								cb.lower(_individu.<String> get("prenomLatin"))
								// _individu.get("nomLatin").as(String.class)
								, individu.getPrenomLatin().toLowerCase());

						predicate = cb.or(predicate,
								cb.like(cb.lower(_individu
										.<String> get("prenomArabe")), individu
										.getPrenomArabe().toLowerCase()));
					} else {
						predicate = cb.and(predicate, cb.like(
								cb.lower(_individu.<String> get("prenomLatin"))
								// _individu.get("nomLatin").as(String.class)
								, individu.getPrenomLatin().toLowerCase()));

						predicate = cb.or(predicate,
								cb.like(cb.lower(_individu
										.<String> get("prenomArabe")), individu
										.getPrenomArabe().toLowerCase()));
					}

					// matricule de bac

				}
			}

			/*
			 * if (diaSearch.getDossierEtudiant().getRefIndividu() != null) { if
			 * (predicate == null) { predicate =
			 * cb.equal(_dossierEtudiant.get("refIndividu"),
			 * diaSearch.getDossierEtudiant().getRefIndividu()); } else {
			 * predicate = cb.and(predicate, cb.equal(_dossierEtudiant
			 * .get("refIndividu"), diaSearch.getDossierEtudiant()
			 * .getRefIndividu())); } }
			 */
		}
		// condition sur l'annee academique
		if (diaSearch.getAnneeAcademique() != null) {

			if (predicate == null) {
				predicate = cb.equal(_anneeAcademique.get("id"), diaSearch
						.getAnneeAcademique().getId());
			} else {
				predicate = cb.and(predicate, cb.equal(_anneeAcademique
						.get("id"), diaSearch.getAnneeAcademique().getId()));
			}
		}

		// condition sur l'ouverture d'offre de formation
		if (diaSearch.getOuvertureOffreFormation() != null) {
			Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _oof = _dia
					.join("ouvertureOffreFormation");

			if (diaSearch.getOuvertureOffreFormation().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_oof.get("id"), diaSearch
							.getOuvertureOffreFormation().getId());
				} else {
					predicate = cb.and(predicate, cb.equal(_oof.get("id"),
							diaSearch.getOuvertureOffreFormation().getId()));
				}
			}

			// if (diaSearch.getOuvertureOffreFormation()
			// .getRefCodeEtablissement() != null
			// && !diaSearch.getOuvertureOffreFormation()
			// .getRefCodeEtablissement().trim().equals("")
			// && !diaSearch.getOuvertureOffreFormation()
			// .getRefCodeEtablissement().trim().equals("null")) {
			// if (predicate == null) {
			// predicate = cb.equal(_oof.get("refCodeEtablissement"),
			// diaSearch.getOuvertureOffreFormation()
			// .getRefCodeEtablissement());
			// } else {
			// predicate = cb.and(predicate, cb.equal(_oof
			// .get("refCodeEtablissement"), diaSearch
			// .getOuvertureOffreFormation()
			// .getRefCodeEtablissement()));
			// }
			// }

			if (diaSearch.getOuvertureOffreFormation().getOffreFormation() != null) {
				Join<OuvertureOffreFormation, OffreFormation> _of = _oof
						.join("offreFormation");
				if (diaSearch.getOuvertureOffreFormation().getOffreFormation()
						.getCycle() != null) {
					Join<OffreFormation, Cycle> _ofc = _of.join("cycle");
					if (predicate == null) {
						predicate = cb.equal(_ofc.get("id"), diaSearch
								.getOuvertureOffreFormation()
								.getOffreFormation().getCycle().getId());
					} else {
						predicate = cb
								.and(predicate,
										cb.equal(_ofc.get("id"), diaSearch
												.getOuvertureOffreFormation()
												.getOffreFormation().getCycle()
												.getId()));
					}
				}
			}
		}

		// condition sur le type d'inscription
		if (diaSearch.getRefCodeTypeInscription() != null) {
			if (predicate == null) {
				predicate = cb.equal(_dia.get("refCodeTypeInscription"),
						diaSearch.getRefCodeTypeInscription());
			} else {
				predicate = cb.and(
						predicate,
						cb.equal(_dia.get("refCodeTypeInscription"),
								diaSearch.getRefCodeTypeInscription()));
			}
		}

		// condition sur le numero d'inscription
		if (diaSearch.getNumeroInscription() != null) {
			if (predicate == null) {
				predicate = cb.equal(_dia.get("numeroInscription"),
						diaSearch.getNumeroInscription());
			} else {
				predicate = cb.and(
						predicate,
						cb.equal(_dia.get("numeroInscription"),
								diaSearch.getNumeroInscription()));
			}
		}

		// condition sur le code etablissement
		if (diaSearch.getRefEtablissement() != null
				&& diaSearch.getRefEtablissement().getId() != 0) {
			if (predicate == null) {
				predicate = cb.equal(_dia.get("refEtablissement"),
						diaSearch.getRefEtablissement());
			} else {
				predicate = cb.and(
						predicate,
						cb.equal(_dia.get("refEtablissement"),
								diaSearch.getRefEtablissement()));
			}
		}

		// condition sur le niveau
		if (diaSearch.getNiveau() != null && diaSearch.getNiveau().getId() != 0) {
			if (predicate == null) {
				predicate = cb.equal(_dia.get("niveau"), diaSearch.getNiveau());
			} else {
				predicate = cb.and(predicate,
						cb.equal(_dia.get("niveau"), diaSearch.getNiveau()));
			}
		}

		// condition sur la situation
		if (diaSearch.getDossier() != null
				&& diaSearch.getDossier().getSituationEntite() != null) {

			if (predicate == null) {
				predicate = cb.equal(_situation.get("id"), diaSearch
						.getDossier().getSituationEntite().getId());
			} else {
				predicate = cb.and(
						predicate,
						cb.equal(_situation.get("id"), diaSearch.getDossier()
								.getSituationEntite().getId()));
			}
		}

		// condition sur les offres de formation
		if (searchOuverturesOffreFormationBos != null
				&& !searchOuverturesOffreFormationBos.isEmpty()) {

			Path<Object> exp = _dia.get("ouvertureOffreFormation");
			if (predicate == null) {
				predicate = exp.in(searchOuverturesOffreFormationBos);
			} else {
				predicate = cb.and(predicate,
						exp.in(searchOuverturesOffreFormationBos));
			}
		}

		// condition sur la situation
		if (searchSitutationEntiteBos != null
				&& !searchSitutationEntiteBos.isEmpty()) {

			Path<Object> exp = _dossier.get("situationEntite");
			if (predicate == null) {
				predicate = exp.in(searchSitutationEntiteBos);
			} else {
				predicate = cb
						.and(predicate, exp.in(searchSitutationEntiteBos));
			}
		}

		// condition sur la decision de l'ONOU
		if (diaSearch.getDecisionOnouValide() != null) {
			if (diaSearch.getDecisionOnouValide() == Boolean.FALSE) {
				if (predicate == null) {
					predicate = cb.isNull(_dia.get("decisionOnouValide"));
				} else {
					predicate = cb.and(predicate,
							cb.isNull(_dia.get("decisionOnouValide")));
				}
			} else {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("decisionOnouValide"),
							diaSearch.getDecisionOnouValide());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("decisionOnouValide"),
									diaSearch.getDecisionOnouValide()));
				}
			}
		}

		c.where(predicate);

		return c;

	}

	@Override
	public List<Object[]> findFiliere(DossierInscriptionAdministrative dia) {
		log.debug("findFiliereByCodeNiveau instances");
		try {
			if (dia == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r.refCodeFiliere, count(r.refCodeFiliere) from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where r.refCodeDomaine is null");
			/***
			 * MAKERRI : TODO : enlever le niveau temporairement pour pouvoir
			 * gerer le classic
			 *******/
			// if (dia.getRefCodeNiveau() != null) {
			// sqlQuery.append(" and lower(r.refCodeNiveau) = "
			// + UtilConstant.quotedString(dia.getRefCodeNiveau()
			// .toLowerCase()));
			//
			// }
			if (dia.getAnneeAcademique() != null) {
				sqlQuery.append(" and r.anneeAcademique.id = "
						+ dia.getAnneeAcademique().getId());

			}
			if (dia.getRefEtablissement() != null
					&& dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" r.refEtablissement.id = "
						+ dia.getRefEtablissement().getId());
			}

			if (dia.getRefCodeFiliere() != null) {
				sqlQuery.append(" and lower(r.refCodeFiliere) = "
						+ UtilConstant.quotedString(dia.getRefCodeFiliere()
								.toLowerCase()));
			}

			if (dia.getDossier() != null
					&& dia.getDossier().getSituationEntite() != null) {
				sqlQuery.append(" and r.dossier.situationEntite.id = "
						+ dia.getDossier().getSituationEntite().getId());
			}

			sqlQuery.append(" group by r.refCodeFiliere");
			Query query = entityManager.createQuery(new String(sqlQuery));

			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findFiliereByCodeNiveau failed", re);
			throw re;
		}
	}

	@Override
	public List<Object[]> findDomaine(DossierInscriptionAdministrative dia) {
		log.debug("findDomaineByCodeNiveau instances");
		try {
			if (dia == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r.refCodeDomaine, count(r.refCodeDomaine) from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where r.refCodeFiliere is null ");
			/***
			 * MAKERRI : TODO : enlever le niveau temporairement pour pouvoir
			 * gerer le classic
			 *******/
			// if (dia.getRefCodeNiveau() != null) {
			// sqlQuery.append(" and lower(r.refCodeNiveau) = "
			// + UtilConstant.quotedString(dia.getRefCodeNiveau()
			// .toLowerCase()));
			//
			// }
			if (dia.getAnneeAcademique() != null) {
				sqlQuery.append(" and r.anneeAcademique.id = "
						+ dia.getAnneeAcademique().getId());

			}
			if (dia.getRefEtablissement() != null
					&& dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" r.refEtablissement.id = "
						+ dia.getRefEtablissement().getId());
			}

			if (dia.getRefCodeDomaine() != null) {
				sqlQuery.append(" and lower(r.refCodeDomaine) = "
						+ UtilConstant.quotedString(dia.getRefCodeDomaine()
								.toLowerCase()));
			}

			if (dia.getDossier() != null
					&& dia.getDossier().getSituationEntite() != null) {
				sqlQuery.append(" and r.dossier.situationEntite.id = "
						+ dia.getDossier().getSituationEntite().getId());
			}

			sqlQuery.append(" group by r.refCodeDomaine ");
			Query query = entityManager.createQuery(new String(sqlQuery));

			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findDomaineByCodeNiveau failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findByMatriculeBac(
			DossierInscriptionAdministrative diaSearch) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierInscriptionAdministrative> c = cb
					.createQuery(DossierInscriptionAdministrative.class);
			Root<DossierInscriptionAdministrative> _dia = c
					.from(DossierInscriptionAdministrative.class);
			c.orderBy(cb.asc(_dia.get("refCodeDomaine")));
			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
					.join("dossierEtudiant").join("dossierBachelier");
			Join<DossierInscriptionAdministrative, RefEtablissement> _refEtablissement = _dia
					.join("refEtablissement");
			Join<DossierInscriptionAdministrative, Niveau> _niveau = _dia
					.join("niveau");

			c.select(_dia).distinct(true);
			Predicate predicate = null;

			// condition sur le dossier d'étudiant
			if (diaSearch.getDossierEtudiant() != null
					&& diaSearch.getDossierEtudiant().getDossierBachelier() != null) {

				if (diaSearch.getDossierEtudiant().getDossierBachelier()
						.getMatricule() != null) {
					if (predicate == null) {
						predicate = cb.equal(_dossierEtudiant.get("matricule"),
								diaSearch.getDossierEtudiant()
										.getDossierBachelier().getMatricule());
					}
				}
			}

			if (diaSearch.getRefEtablissement() != null
					&& diaSearch.getRefEtablissement().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_refEtablissement.get("id"), diaSearch
							.getRefEtablissement().getId());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_refEtablissement.get("id"), diaSearch
									.getRefEtablissement().getId()));
				}
			}
			// condition sur le niveau

			if (diaSearch.getNiveau() != null
					&& diaSearch.getNiveau().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_niveau.get("id"), diaSearch
							.getNiveau().getId());
				} else {
					predicate = cb.and(predicate, cb.equal(_niveau.get("id"),
							diaSearch.getNiveau().getId()));
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DossierInscriptionAdministrative> _query = entityManager
						.createQuery(c);

				return _query.getResultList();
			} else
				return new ArrayList<DossierInscriptionAdministrative>();

		} catch (RuntimeException re) {
			log.error("findByMatriculeBac  failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findByAnneeAcademiqueAndEtab(
			int annee, Integer idEtab) {
		log.debug("findByAnneeAcademiqueAndEtab instances");
		try {
			if (annee == 0 || idEtab == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where r.anneeAcademique.id = " + annee);
			sqlQuery.append(" and r.refEtablissement.id = " + idEtab);
			sqlQuery.append(" order by r.refCodeFiliere, r.refCodeDomaine");
			Query query = entityManager.createQuery(new String(sqlQuery));

			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByAnneeAcademiqueAndEtab failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findEtudiant(
			DossierInscriptionAdministrative _dia, Integer gpId,
			Integer sectionId) {
		log.debug("findEtudiant instances");
		try {
			if (_dia == null || gpId == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct r from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where (1=1)");
			sqlQuery.append(" and r.id NOT IN(select af.dossierInscriptionAdministrative.id from AffectationGroupePedagogique af where af.groupePedagogique.id IN(select g2.id from GroupePedagogique g1, GroupePedagogique g2  where g1.id =:gpId and g1.ap.id =  g2.ap.id and g1.mc.id =  g2.mc.id and g1.ue.id =  g2.ue.id and g1.oof.id =  g2.oof.id))");
			if (sectionId != null) {
				// sqlQuery.append(" and r.id NOT IN(select af.dossierInscriptionAdministrative.id from GroupePedagogiqueAp ap, AffectationGroupePedagogique af  where ap.groupePedagogique.id =:gpId)");
				sqlQuery.append(" and r.id IN(select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a where a.groupePedagogique.id =:sectionId)");
				sqlQuery.append(" and r.id NOT IN(select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a where a.groupePedagogique.section.id =:sectionId)");
			}
			if (_dia.getRefEtablissement() != null
					&& _dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" and r.refEtablissement.id = "
						+ _dia.getRefEtablissement().getId());
			}
			if (_dia.getNiveau() != null && _dia.getNiveau().getId() != 0) {
				sqlQuery.append(" r.niveau = " + _dia.getNiveau().getId());
			}
			if (_dia.getOuvertureOffreFormation() != null
					&& _dia.getOuvertureOffreFormation().getId() != 0) {
				sqlQuery.append(" and r.ouvertureOffreFormation.id = "
						+ _dia.getOuvertureOffreFormation().getId());
			}
			if (_dia.getOuvertureOffreFormation() != null
					&& _dia.getOuvertureOffreFormation().getAnneeAcademique() != null) {
				sqlQuery.append(" and r.ouvertureOffreFormation.anneeAcademique.id = "
						+ _dia.getOuvertureOffreFormation()
								.getAnneeAcademique().getId());
			}

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			query.setParameter("gpId", gpId);
			if (sectionId != null) {
				query.setParameter("sectionId", sectionId);
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findEtudiant failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findAdvanced(
			DossierInscriptionAdministrative diaSearch, String codeDoamine,
			String codefiliere) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierInscriptionAdministrative> c = cb
					.createQuery(DossierInscriptionAdministrative.class);
			Root<DossierInscriptionAdministrative> _dia = c
					.from(DossierInscriptionAdministrative.class);
			c.orderBy(cb.asc(_dia.get("refCodeDomaine")));
			Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
					.join("dossierEtudiant");

			Join<DossierInscriptionAdministrative, AnneeAcademique> _anneeAcademique = _dia
					.join("anneeAcademique");

			Join<DossierInscriptionAdministrative, Dossier> _dossier = _dia
					.join("dossier");
			Join<DossierInscriptionAdministrative, Dossier> _situation = _dossier
					.join("situationEntite");
			c.select(_dia).distinct(true);
			Predicate predicate = null;

			// condition sur le dossier d'étudiant
			if (diaSearch.getDossierEtudiant() != null) {

				if (diaSearch.getDossierEtudiant().getId() != 0) {
					predicate = cb.equal(_dossierEtudiant.get("id"), diaSearch
							.getDossierEtudiant().getId());
				}
				if (diaSearch.getDossierEtudiant().getNumeroMatricule() != null
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("")
						&& !diaSearch.getDossierEtudiant().getNumeroMatricule()
								.trim().equals("null")) {
					if (predicate == null) {
						predicate = cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierEtudiant
								.get("numeroMatricule"), diaSearch
								.getDossierEtudiant().getNumeroMatricule()));
					}
				}

				if (diaSearch.getDossierEtudiant().getIndividu() != null) {
					Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant
							.join("individu");
					if (predicate == null) {
						predicate = cb.equal(_individu.get("id"), diaSearch
								.getDossierEtudiant().getIndividu().getId());
					} else {
						predicate = cb.and(
								predicate,
								cb.equal(_individu.get("id"), diaSearch
										.getDossierEtudiant().getIndividu()
										.getId()));
					}
				}
			}
			// condition sur l'année académique
			if (diaSearch.getAnneeAcademique() != null) {

				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), diaSearch
							.getAnneeAcademique().getId());
				} else {
					predicate = cb
							.and(predicate, cb.equal(
									_anneeAcademique.get("id"), diaSearch
											.getAnneeAcademique().getId()));
				}
			}
			// condition sur l'ouverture d'offre de formation
			if (diaSearch.getOuvertureOffreFormation() != null) {
				Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _oof = _dia
						.join("ouvertureOffreFormation");

				if (diaSearch.getOuvertureOffreFormation().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_oof.get("id"), diaSearch
								.getOuvertureOffreFormation().getId());
					} else {
						predicate = cb
								.and(predicate, cb.equal(_oof.get("id"),
										diaSearch.getOuvertureOffreFormation()
												.getId()));
					}
				}

				// if (diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement() != null
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim().equals("")
				// && !diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement().trim()
				// .equals("null")) {
				// if (predicate == null) {
				// predicate = cb.equal(_oof.get("refCodeEtablissement"),
				// diaSearch.getOuvertureOffreFormation()
				// .getRefCodeEtablissement());
				// } else {
				// predicate = cb.and(predicate, cb.equal(_oof
				// .get("refCodeEtablissement"), diaSearch
				// .getOuvertureOffreFormation()
				// .getRefCodeEtablissement()));
				// }
				// }

				if (codeDoamine != null && codefiliere != null) {
					Join<OuvertureOffreFormation, OffreFormation> _of = _oof
							.join("offreFormation");

					if (predicate == null) {
						predicate = cb.equal(_of.get("refCodeDomaine"),
								codeDoamine);
						predicate = cb.or(predicate, cb.equal(
								_of.get("refCodeFiliere"), codefiliere));
					} else {
						predicate = cb.and(predicate, cb.equal(
								_of.get("refCodeDomaine"), codeDoamine));
						predicate = cb.or(predicate, cb.equal(
								_of.get("refCodeFiliere"), codefiliere));
					}

				}
			}
			// condition sur le type d'inscription
			if (diaSearch.getRefCodeTypeInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_dia.get("refCodeTypeInscription"),
							diaSearch.getRefCodeTypeInscription()));
				}
			}

			if (diaSearch.getNumeroInscription() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("numeroInscription"),
							diaSearch.getNumeroInscription());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("numeroInscription"),
									diaSearch.getNumeroInscription()));
				}
			}

			if (diaSearch.getRefEtablissement() != null
					&& diaSearch.getRefEtablissement().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("reEtablissement"),
							diaSearch.getRefEtablissement());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dia.get("reEtablissement"),
									diaSearch.getRefEtablissement()));
				}
			}
			// condition sur le niveau

			if (diaSearch.getNiveau() != null
					&& diaSearch.getNiveau().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_dia.get("niveau"),
							diaSearch.getNiveau());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dia.get("niveau"),
											diaSearch.getNiveau()));
				}
			}
			// condition sur la situation

			if (diaSearch.getDossier() != null
					&& diaSearch.getDossier().getSituationEntite() != null) {

				if (predicate == null) {
					predicate = cb.equal(_situation.get("id"), diaSearch
							.getDossier().getSituationEntite().getId());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_situation.get("id"), diaSearch
											.getDossier().getSituationEntite()
											.getId()));
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<DossierInscriptionAdministrative> _query = entityManager
						.createQuery(c);

				return _query.getResultList();
			} else
				return new ArrayList<DossierInscriptionAdministrative>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierInscriptionAdministrative>();
		}
	}

	@Override
	public void flushAndClear() {

		try {
			entityManager.flush();
			entityManager.clear();
		} catch (RuntimeException e) {
			log.error("flushAndClear failed", e);
			throw e;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> _findEtudiant(
			DossierInscriptionAdministrative _dia,
			GroupePedagogique groupePedagogique) {
		log.debug("findEtudiant instances");
		try {
			if (_dia == null || groupePedagogique == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct r from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where (1=1)");
			if (groupePedagogique.getOof() != null) {
				sqlQuery.append(" and r.ouvertureOffreFormation.id = "
						+ groupePedagogique.getOof().getId());
			}
			sqlQuery.append(" and r.id NOT IN ");
			sqlQuery.append("(select af.dossierInscriptionAdministrative.id from AffectationGroupePedagogique af ");
			sqlQuery.append(" where (1=1)");
			if (groupePedagogique.getAnneeAcademique() != null
					&& groupePedagogique.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and af.groupePedagogique.anneeAcademique.id  = "
						+ groupePedagogique.getAnneeAcademique().getId());
			}
			if (groupePedagogique.getPeriode() != null
					&& groupePedagogique.getPeriode().getId() != 0) {
				sqlQuery.append(" and af.groupePedagogique.periode.id  = "
						+ groupePedagogique.getPeriode().getId());
			}
			sqlQuery.append(" and af.groupePedagogique.id IN ");
			sqlQuery.append(" (select ap2.groupePedagogique.id from AssociationGroupePedagogique ap1, AssociationGroupePedagogique ap2 ");
			sqlQuery.append(" where (1=1)");
			if (groupePedagogique.getAnneeAcademique() != null
					&& groupePedagogique.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and ap1.groupePedagogique.anneeAcademique.id  = "
						+ groupePedagogique.getAnneeAcademique().getId());
			}
			if (groupePedagogique.getPeriode() != null
					&& groupePedagogique.getPeriode().getId() != 0) {
				sqlQuery.append(" and ap1.groupePedagogique.periode.id  = "
						+ groupePedagogique.getPeriode().getId());
			}
			sqlQuery.append(" and ap1.groupePedagogique.id =:gpId ");
			sqlQuery.append(" and ap1.ap.id =  ap2.ap.id ");
			sqlQuery.append(" and ap1.rattachementMc.id =  ap2.rattachementMc.id ))");
			if (groupePedagogique.getSection() != null
					&& groupePedagogique.getSection().getId() != 0) {
				sqlQuery.append(" and r.id IN ");
				sqlQuery.append(" (select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a ");
				sqlQuery.append(" where (1=1)");
				if (groupePedagogique.getAnneeAcademique() != null
						&& groupePedagogique.getAnneeAcademique().getId() != 0) {
					sqlQuery.append(" and  a.groupePedagogique.anneeAcademique.id  = "
							+ groupePedagogique.getAnneeAcademique().getId());
				}
				if (groupePedagogique.getPeriode() != null
						&& groupePedagogique.getPeriode().getId() != 0) {
					sqlQuery.append(" and  a.groupePedagogique.periode.id  = "
							+ groupePedagogique.getPeriode().getId());
				}

				sqlQuery.append(" and a.groupePedagogique.id =:sectionId)");

				sqlQuery.append(" and r.id NOT IN ");
				sqlQuery.append("(select aff.dossierInscriptionAdministrative.id from AffectationGroupePedagogique aff ");
				sqlQuery.append(" where (1=1)");
				if (groupePedagogique.getAnneeAcademique() != null
						&& groupePedagogique.getAnneeAcademique().getId() != 0) {
					sqlQuery.append(" and aff.groupePedagogique.section.anneeAcademique.id  = "
							+ groupePedagogique.getAnneeAcademique().getId());
				}
				sqlQuery.append("and aff.groupePedagogique.section.id =:sectionId)");
			}

			// if (groupePedagogique.getAp() != null
			// && groupePedagogique.getRattachementMc() != null
			// && groupePedagogique.getOof() != null) {
			// sqlQuery.append(" and r.id NOT IN");
			// sqlQuery.append("(select af.dossierInscriptionAdministrative.id from AffectationGroupePedagogique af where af.groupePedagogique.id != "
			// + groupePedagogique.getId()
			// + " and af.groupePedagogique.ap.id ="
			// + groupePedagogique.getAp().getId()
			// + " and af.groupePedagogique.rattachementMc.id = "
			// + groupePedagogique.getRattachementMc().getId()
			// + " and af.groupePedagogique.oof.id ="
			// + groupePedagogique.getOof().getId() + ")");
			// }
			// if (groupePedagogique.getSection() != null) {
			// sqlQuery.append(" and r.id IN");
			// sqlQuery.append("(select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a where a.groupePedagogique.id ="
			// + groupePedagogique.getSection().getId() + ")");
			// sqlQuery.append(" and r.id NOT IN");
			// sqlQuery.append("(select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a where a.groupePedagogique.section.id ="
			// + groupePedagogique.getSection().getId()
			// + " and a.groupePedagogique.ap.id ="
			// + groupePedagogique.getAp().getId()
			// + " and a.groupePedagogique.rattachementMc.id = "
			// + groupePedagogique.getRattachementMc().getId()
			// + " and a.groupePedagogique.oof.id ="
			// + groupePedagogique.getOof().getId() + ")");
			// }
			if (_dia.getRefEtablissement() != null
					&& _dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" and r.refEtablissement.id = "
						+ _dia.getRefEtablissement().getId());
			}
			if (_dia.getNiveau() != null && _dia.getNiveau().getId() != 0) {
				sqlQuery.append(" and r.niveau.id  = "
						+ _dia.getNiveau().getId());
			}
			if (_dia.getOuvertureOffreFormation() != null
					&& _dia.getOuvertureOffreFormation().getId() != 0) {
				sqlQuery.append(" and r.ouvertureOffreFormation.id = "
						+ _dia.getOuvertureOffreFormation().getId());
			}
			if (_dia.getAnneeAcademique() != null
					&& _dia.getAnneeAcademique().getId() != 0) {
				sqlQuery.append(" and r.anneeAcademique.id = "
						+ _dia.getAnneeAcademique().getId());
			}

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			query.setParameter("gpId", groupePedagogique.getId());
			if (groupePedagogique.getSection() != null
					&& groupePedagogique.getSection().getId() != 0) {
				query.setParameter("sectionId", groupePedagogique.getSection()
						.getId());
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findEtudiant failed", re);
			throw re;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.
	 * DossierInscriptionAdministrativeDao
	 * #findStudentAdvanced(dz.gov.mesrs.sii.commons
	 * .data.model.fve.cursus.DossierInscriptionAdministrative)
	 */
	@Override
	public List<DossierInscriptionAdministrative> findStudentAdvanced(
			DossierInscriptionAdministrative _dia) {
		log.debug("findStudentAdvanced instances");
		try {
			if (_dia == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DossierInscriptionAdministrative r ");
			sqlQuery.append(" where (1=1) ");
			if (_dia.getDossierEtudiant() != null) {
				RefIndividu refIndividu = _dia.getDossierEtudiant()
						.getIndividu();
				if (refIndividu != null) {
					if (refIndividu.getIdentifiant() != null) {
						sqlQuery.append(" and lower(r.dossierEtudiant.individu.identifiant) like "
								+ UtilConstant.LikeContain(refIndividu
										.getIdentifiant()));
					}
					if (refIndividu.getNomArabe() != null) {
						sqlQuery.append(" and lower(r.dossierEtudiant.individu.nomArabe) like "
								+ UtilConstant.LikeContain(refIndividu
										.getNomArabe()));
					}
					if (refIndividu.getPrenomArabe() != null) {
						sqlQuery.append(" and lower(r.dossierEtudiant.individu.prenomArabe) like "
								+ UtilConstant.LikeContain(refIndividu
										.getPrenomArabe()));
					}
					if (refIndividu.getNomLatin() != null) {
						sqlQuery.append(" and lower(r.dossierEtudiant.individu.nomLatin) like "
								+ UtilConstant.LikeContain(refIndividu
										.getPrenomArabe()));
					}
					if (refIndividu.getPrenomLatin() != null) {
						sqlQuery.append(" and lower(r.dossierEtudiant.individu.prenomLatin) like "
								+ UtilConstant.LikeContain(refIndividu
										.getPrenomArabe()));
					}
				}
			}
			if (_dia.getRefEtablissement() != null
					&& _dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" and r.refEtablissement.id = "
						+ _dia.getRefEtablissement().getId());
			}
			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(sqlQuery.toString(),
							DossierInscriptionAdministrative.class);

			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findStudentAdvanced failed", re);
			throw re;
		}
	}
}
