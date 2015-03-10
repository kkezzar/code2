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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierTransfertDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierTransfert;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * @author BELDI Jamel on : 8 juin 2014 12:30:02
 */
@Repository("dossierTransfertDao")
@Transactional
public class DossierTransfertDaoImpl implements DossierTransfertDao {

	private static final Logger log = LoggerFactory.getLogger(DossierTransfertDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierTransfertDao#persist(dz.gov.mesrs.sii.DossierTransfertDto.business.model.bo.DossierTransfert)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(DossierTransfert transientInstance) {
		log.debug("persisting DossierTransfert instance");
		try {
			entityManager.persist(transientInstance);
			// entityManager.flush();
			// entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierTransfertDao#remove(dz.gov.mesrs.sii.DossierTransfertDto.business.model.bo.DossierTransfert)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(DossierTransfert persistentInstance) {
		log.debug("removing DossierTransfert instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierTransfertDao#merge(dz.gov.mesrs.sii.DossierTransfertDto.business.model.bo.DossierTransfert)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DossierTransfert merge(DossierTransfert detachedInstance) {
		log.debug("merging DossierTransfert instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NDossierTransfertDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public DossierTransfert findById(int id) {
		log.debug("getting DossierTransfert instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DossierTransfert.class, id);
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
	public List<DossierTransfert> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DossierTransfert> findAll() {
		log.debug("getting all DossierTransfert instances");
		try {
			Query query = entityManager.createQuery("from DossierTransfert dossierTransfert");
			log.debug("findAll 'DossierTransfert' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DossierTransfert' failed", re);
			return new ArrayList<DossierTransfert>();
		}
	}

	/**
	 * Recherche avancee
	 * 
	 */
	@Override
	public List<DossierTransfert> findAdvanced(DossierTransfert searchBo) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierTransfert> c = cb.createQuery(DossierTransfert.class);
			Root<DossierTransfert> _dossierTransfert = c.from(DossierTransfert.class);

			c.select(_dossierTransfert).distinct(true);
			Predicate predicate = null;

			// TYPE TRANSFERT
			if (searchBo.getRefCodeTypeTransfert() != null && !searchBo.getRefCodeTypeTransfert().trim().equals("")
					&& !searchBo.getRefCodeTypeTransfert().trim().equals("null")) {

				predicate = cb.equal(_dossierTransfert.get("refCodeTypeTransfert"), searchBo.getRefCodeTypeTransfert());

			}

			// ETAB ACCUEIL
			if (searchBo.getRefCodeEtabAccueil() != null && !searchBo.getRefCodeEtabAccueil().trim().equals("")
					&& !searchBo.getRefCodeEtabAccueil().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil()));
				}
			}

			// ETAB D'orgine
			if (searchBo.getRefCodeEtabOrigine() != null && !searchBo.getRefCodeEtabOrigine().trim().equals("")
					&& !searchBo.getRefCodeEtabOrigine().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine()));
				}
			}

			// condition sur le domaine / filiere demande
			if (searchBo.getRefCodeDomaineDemande() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
							searchBo.getRefCodeDomaineDemande());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
									searchBo.getRefCodeDomaineDemande()));
				}
			}
			if (searchBo.getRefCodeFiliereDemandee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
							searchBo.getRefCodeFiliereDemandee());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
									searchBo.getRefCodeFiliereDemandee()));
				}
			}

			// condition sur le domaine / filiere d'origine
			if (searchBo.getRefCodeDomaineOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
							searchBo.getRefCodeDomaineOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
									searchBo.getRefCodeDomaineOrigine()));
				}
			}
			if (searchBo.getRefCodeFiliereOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
							searchBo.getRefCodeFiliereOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
									searchBo.getRefCodeFiliereOrigine()));
				}
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<DossierTransfert, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _dossierTransfert
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId()));
					}
				}
				// ETUDAINT
				if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant() != null) {

					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
							.join("dossierEtudiant");

					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule() != null
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("")
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("null")) {
						if (predicate == null) {
							predicate = cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
									.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
											.getDossierInscriptionAdministrative().getDossierEtudiant()
											.getNumeroMatricule()));
						}

						if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu() != null) {
							Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant.join("individu");
							if (predicate == null) {
								predicate = cb.equal(_individu.get("id"), searchBo
										.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu()
										.getId());
							} else {
								predicate = cb.and(
										predicate,
										cb.equal(_individu.get("id"), searchBo.getDossierInscriptionAdministrative()
												.getDossierEtudiant().getIndividu().getId()));
							}
						}
					}
				}

				// ETABLISSMENT ET OFFRE ORIGINE
				if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation() != null) {
					Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _ouvertureOf = _dossierInscriptionAdministrative
							.join("ouvertureOffreFormation");

					// if (searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement() != null
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("")
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("null")) {
					// if (predicate == null) {
					// predicate = cb
					// .equal(_ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement());
					// } else {
					// predicate = cb
					// .and(predicate,
					// cb.equal(
					// _ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement()));
					// }
					// }
					if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation().getOffreFormation() != null) {
						if (predicate == null) {
							predicate = cb.equal(_ouvertureOf.get("offreFormation"), searchBo
									.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
									.getOffreFormation());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_ouvertureOf.get("offreFormation"), searchBo
											.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
											.getOffreFormation()));
						}
					}
				}

			}

			// condition sur l'annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<DossierTransfert, AnneeAcademique> _anneeAcademique = _dossierTransfert.join("anneeAcademique");
				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId()));
				}
			}

			// Avis de l'etablissement d'accueil
			if (searchBo.getIsEtabAccueilAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
							searchBo.getIsEtabAccueilAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
											searchBo.getIsEtabAccueilAccepte()));
				}
			}

			// Avis de l'etablissement d'origine
			if (searchBo.getIsEtabOrigineAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
							searchBo.getIsEtabOrigineAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
											searchBo.getIsEtabOrigineAccepte()));
				}
			}

			// Avis du responsable de transfert
			if (searchBo.getIsAccordee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee()));
				}
			}

			if (predicate != null)
				c.where(predicate);

			TypedQuery<DossierTransfert> _query = entityManager.createQuery(c);
			List<DossierTransfert> _resultList = _query.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierTransfert>();
		}
	}

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 2 sept. 2014 10:01:50
	 * @param searchBo
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	@Override
	public List<DossierTransfert> findAdvanced(DossierTransfert searchBo,
			List<SituationEntite> searchSitutationEntiteBos) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierTransfert> c = cb.createQuery(DossierTransfert.class);
			Root<DossierTransfert> _dossierTransfert = c.from(DossierTransfert.class);

			c.select(_dossierTransfert).distinct(true);
			Predicate predicate = null;

			// TYPE TRANSFERT
			if (searchBo.getRefCodeTypeTransfert() != null && !searchBo.getRefCodeTypeTransfert().trim().equals("")
					&& !searchBo.getRefCodeTypeTransfert().trim().equals("null")) {

				predicate = cb.equal(_dossierTransfert.get("refCodeTypeTransfert"), searchBo.getRefCodeTypeTransfert());

			}
			// ETAB ACCUEIL
			if (searchBo.getRefCodeEtabAccueil() != null && !searchBo.getRefCodeEtabAccueil().trim().equals("")
					&& !searchBo.getRefCodeEtabAccueil().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil()));
				}
			}

			// ETAB D'orgine
			if (searchBo.getRefCodeEtabOrigine() != null && !searchBo.getRefCodeEtabOrigine().trim().equals("")
					&& !searchBo.getRefCodeEtabOrigine().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine()));
				}
			}

			// condition sur le domaine / filiere demande
			if (searchBo.getRefCodeDomaineDemande() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
							searchBo.getRefCodeDomaineDemande());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
									searchBo.getRefCodeDomaineDemande()));
				}
			}
			if (searchBo.getRefCodeFiliereDemandee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
							searchBo.getRefCodeFiliereDemandee());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
									searchBo.getRefCodeFiliereDemandee()));
				}
			}

			// condition sur le domaine / filiere d'origine
			if (searchBo.getRefCodeDomaineOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
							searchBo.getRefCodeDomaineOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
									searchBo.getRefCodeDomaineOrigine()));
				}
			}
			if (searchBo.getRefCodeFiliereOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
							searchBo.getRefCodeFiliereOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
									searchBo.getRefCodeFiliereOrigine()));
				}
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<DossierTransfert, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _dossierTransfert
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId()));
					}
				}

				// ETUDAINT
				if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant() != null) {

					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
							.join("dossierEtudiant");

					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule() != null
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("")
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("null")) {
						if (predicate == null) {
							predicate = cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
									.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
											.getDossierInscriptionAdministrative().getDossierEtudiant()
											.getNumeroMatricule()));
						}
					}

					// dossier Bachelier
					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getDossierBachelier() != null) {

						Join<DossierEtudiant, DossierBachelier> _dossierBachelier = _dossierEtudiant
								.join("dossierBachelier");

						DossierBachelier dbac = searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
								.getDossierBachelier();
						if (dbac.getMatricule() != null && !dbac.getMatricule().isEmpty()) {
							if (predicate == null) {
								predicate = cb.equal(_dossierBachelier.get("matricule"), dbac.getMatricule());
							} else {
								predicate = cb.and(predicate,
										cb.equal(_dossierBachelier.get("matricule"), dbac.getMatricule()));
							}
						}
						Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant.join("individu");
						if (predicate == null) {
							predicate = cb.equal(_individu.get("id"), searchBo.getDossierInscriptionAdministrative()
									.getDossierEtudiant().getIndividu().getId());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_individu.get("id"), searchBo.getDossierInscriptionAdministrative()
											.getDossierEtudiant().getIndividu().getId()));
						}

					}

				}

				// ETABLISSMENT ET OFFRE ORIGINE
				if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation() != null) {
					Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _ouvertureOf = _dossierInscriptionAdministrative
							.join("ouvertureOffreFormation");

					// if (searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement() != null
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("")
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("null")) {
					// if (predicate == null) {
					// predicate = cb
					// .equal(_ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement());
					// } else {
					// predicate = cb
					// .and(predicate,
					// cb.equal(
					// _ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement()));
					// }
					// }
					if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation().getOffreFormation() != null) {
						if (predicate == null) {
							predicate = cb.equal(_ouvertureOf.get("offreFormation"), searchBo
									.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
									.getOffreFormation());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_ouvertureOf.get("offreFormation"), searchBo
											.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
											.getOffreFormation()));
						}
					}
				}

			}

			// condition sur l'annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<DossierTransfert, AnneeAcademique> _anneeAcademique = _dossierTransfert.join("anneeAcademique");
				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId()));
				}
			}

			// situation
			if (!searchSitutationEntiteBos.isEmpty()) {
				if (searchBo.getDossier() == null) {
					Dossier dossier = new Dossier();
					// dossier.setId(0);
					searchBo.setDossier(dossier);
				}

				Join<DossierTransfert, Dossier> dossier = _dossierTransfert.join("dossier");

				Path<Object> exp = dossier.get("situationEntite");
				if (predicate == null) {
					predicate = exp.in(searchSitutationEntiteBos);
				} else {
					predicate = cb.and(predicate, exp.in(searchSitutationEntiteBos));
				}

				// Join<SituationEntite, Dossier> situationEntite = dossier
				// .join("situationEntite");

				// if (predicate == null) {
				// predicate = cb.equal(dossier.get("id"), searchBo
				// .getDossier().getId());
				// } else {
				// predicate = cb.and(predicate, cb.equal(dossier
				// .get("id"), searchBo.getDossier().getId()));
				// }

				// Path<Object> exp = situationEntite.get("id");
				// List<Integer> r = new ArrayList<Integer>();
				// r.add(44);
				// if (predicate == null) {
				// predicate = exp.in(r);
				// } else {
				// predicate = cb.and(predicate, exp.in(r));
				// }
			}

			// Avis de l'etablissement d'accueil
			if (searchBo.getIsEtabAccueilAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
							searchBo.getIsEtabAccueilAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
											searchBo.getIsEtabAccueilAccepte()));
				}
			}

			// Avis de l'etablissement d'origine
			if (searchBo.getIsEtabOrigineAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
							searchBo.getIsEtabOrigineAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
											searchBo.getIsEtabOrigineAccepte()));
				}
			}

			// Avis du responsable de transfert
			if (searchBo.getIsAccordee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee()));
				}
			}

			if (predicate != null)
				c.where(predicate);

			TypedQuery<DossierTransfert> _query = entityManager.createQuery(c);

			List<DossierTransfert> _resultList = _query.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierTransfert>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.persistence.cursus.DossierTransfertDao#
	 * findCountDossiersTransfert
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.DossierTransfert)
	 */
	@Override
	public Long findCountDossiersTransfert(DossierTransfert searchBo) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			// CriteriaQuery<DossierTransfert> c = cb
			// .createQuery(DossierTransfert.class);
			// Root<DossierTransfert> _dossierTransfert = c
			// .from(DossierTransfert.class);

			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<DossierTransfert> _dossierTransfert = cq.from(DossierTransfert.class);
			// c.select(_dossierTransfert).distinct(true);
			cq.select(cb.count(_dossierTransfert));

			Predicate predicate = null;

			// TYPE TRANSFERT
			if (searchBo.getRefCodeTypeTransfert() != null && !searchBo.getRefCodeTypeTransfert().trim().equals("")
					&& !searchBo.getRefCodeTypeTransfert().trim().equals("null")) {

				predicate = cb.equal(_dossierTransfert.get("refCodeTypeTransfert"), searchBo.getRefCodeTypeTransfert());

			}

			// ETAB ACCUEIL
			if (searchBo.getRefCodeEtabAccueil() != null && !searchBo.getRefCodeEtabAccueil().trim().equals("")
					&& !searchBo.getRefCodeEtabAccueil().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil()));
				}
			}

			// ETAB D'orgine
			if (searchBo.getRefCodeEtabOrigine() != null && !searchBo.getRefCodeEtabOrigine().trim().equals("")
					&& !searchBo.getRefCodeEtabOrigine().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine()));
				}
			}

			// condition sur le domaine / filiere demande
			if (searchBo.getRefCodeDomaineDemande() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
							searchBo.getRefCodeDomaineDemande());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
									searchBo.getRefCodeDomaineDemande()));
				}
			}
			if (searchBo.getRefCodeFiliereDemandee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
							searchBo.getRefCodeFiliereDemandee());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
									searchBo.getRefCodeFiliereDemandee()));
				}
			}

			// condition sur le domaine / filiere d'origine
			if (searchBo.getRefCodeDomaineOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
							searchBo.getRefCodeDomaineOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
									searchBo.getRefCodeDomaineOrigine()));
				}
			}
			if (searchBo.getRefCodeFiliereOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
							searchBo.getRefCodeFiliereOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
									searchBo.getRefCodeFiliereOrigine()));
				}
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<DossierTransfert, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _dossierTransfert
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId()));
					}
				}
				// ETUDAINT
				if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant() != null) {

					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
							.join("dossierEtudiant");

					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule() != null
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("")
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("null")) {
						if (predicate == null) {
							predicate = cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
									.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
											.getDossierInscriptionAdministrative().getDossierEtudiant()
											.getNumeroMatricule()));
						}
						if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu() != null) {
							Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant.join("individu");
							if (predicate == null) {
								predicate = cb.equal(_individu.get("id"), searchBo
										.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu()
										.getId());
							} else {
								predicate = cb.and(
										predicate,
										cb.equal(_individu.get("id"), searchBo.getDossierInscriptionAdministrative()
												.getDossierEtudiant().getIndividu().getId()));
							}
						}
					}

				}

				// ETABLISSMENT ET OFFRE ORIGINE
				if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation() != null) {
					Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _ouvertureOf = _dossierInscriptionAdministrative
							.join("ouvertureOffreFormation");

					// if (searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement() != null
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("")
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("null")) {
					// if (predicate == null) {
					// predicate = cb
					// .equal(_ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement());
					// } else {
					// predicate = cb
					// .and(predicate,
					// cb.equal(
					// _ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement()));
					// }
					// }
					if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation().getOffreFormation() != null) {
						if (predicate == null) {
							predicate = cb.equal(_ouvertureOf.get("offreFormation"), searchBo
									.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
									.getOffreFormation());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_ouvertureOf.get("offreFormation"), searchBo
											.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
											.getOffreFormation()));
						}
					}
				}

			}

			// condition sur l'annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<DossierTransfert, AnneeAcademique> _anneeAcademique = _dossierTransfert.join("anneeAcademique");
				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId()));
				}
			}

			// Avis de l'etablissement d'accueil
			if (searchBo.getIsEtabAccueilAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
							searchBo.getIsEtabAccueilAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
											searchBo.getIsEtabAccueilAccepte()));
				}
			}

			// Avis de l'etablissement d'origine
			if (searchBo.getIsEtabOrigineAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
							searchBo.getIsEtabOrigineAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
											searchBo.getIsEtabOrigineAccepte()));
				}
			}

			// Avis du responsable de transfert
			if (searchBo.getIsAccordee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee()));
				}
			}

			if (predicate != null)
				cq.where(predicate);

			Long count = entityManager.createQuery(cq).getSingleResult();
			return count;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return null;
		}
	}

	/**
	 * Recherche avanncee par situation
	 * 
	 * @author Mounir.MESSAOUDI on : 15 sept. 2014 12:42:12
	 * @param searchBo
	 * @param typesDossiersTransfert
	 * @param searchSitutationEntiteBos
	 * @return
	 */
	@Override
	public List<DossierTransfert> findAdvanced(DossierTransfert searchBo, List<String> typesDossiersTransfert,
			List<SituationEntite> searchSitutationEntiteBos) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<DossierTransfert> c = cb.createQuery(DossierTransfert.class);
			Root<DossierTransfert> _dossierTransfert = c.from(DossierTransfert.class);

			c.select(_dossierTransfert).distinct(true);
			Predicate predicate = null;

			// TYPES DE TRANSFERT
			if (typesDossiersTransfert != null && !typesDossiersTransfert.isEmpty()) {
				Path<Object> exp = _dossierTransfert.get("refCodeTypeTransfert");
				predicate = exp.in(typesDossiersTransfert);
			}

			// ETAB ACCUEIL
			if (searchBo.getRefCodeEtabAccueil() != null && !searchBo.getRefCodeEtabAccueil().trim().equals("")
					&& !searchBo.getRefCodeEtabAccueil().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabAccueil"), searchBo.getRefCodeEtabAccueil()));
				}
			}

			// ETAB D'orgine
			if (searchBo.getRefCodeEtabOrigine() != null && !searchBo.getRefCodeEtabOrigine().trim().equals("")
					&& !searchBo.getRefCodeEtabOrigine().trim().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("refCodeEtabOrigine"), searchBo.getRefCodeEtabOrigine()));
				}
			}

			// condition sur le domaine / filiere demande
			if (searchBo.getRefCodeDomaineDemande() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
							searchBo.getRefCodeDomaineDemande());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineDemande"),
									searchBo.getRefCodeDomaineDemande()));
				}
			}
			if (searchBo.getRefCodeFiliereDemandee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
							searchBo.getRefCodeFiliereDemandee());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereDemandee"),
									searchBo.getRefCodeFiliereDemandee()));
				}
			}

			// condition sur le domaine / filiere d'origine
			if (searchBo.getRefCodeDomaineOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
							searchBo.getRefCodeDomaineOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeDomaineOrigine"),
									searchBo.getRefCodeDomaineOrigine()));
				}
			}
			if (searchBo.getRefCodeFiliereOrigine() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
							searchBo.getRefCodeFiliereOrigine());
				} else {
					predicate = cb.and(
							predicate,
							cb.equal(_dossierTransfert.get("refCodeFiliereOrigine"),
									searchBo.getRefCodeFiliereOrigine()));
				}
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<DossierTransfert, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _dossierTransfert
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_dossierInscriptionAdministrative.get("id"), searchBo
								.getDossierInscriptionAdministrative().getId()));
					}
				}

				// ETUDAINT
				if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant() != null) {

					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dossierInscriptionAdministrative
							.join("dossierEtudiant");

					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule() != null
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("")
							&& !searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
									.getNumeroMatricule().trim().equals("null")) {
						if (predicate == null) {
							predicate = cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
									.getDossierInscriptionAdministrative().getDossierEtudiant().getNumeroMatricule());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_dossierEtudiant.get("numeroMatricule"), searchBo
											.getDossierInscriptionAdministrative().getDossierEtudiant()
											.getNumeroMatricule()));
						}
					}

					// dossier Bachelier
					if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getDossierBachelier() != null) {

						Join<DossierEtudiant, DossierBachelier> _dossierBachelier = _dossierEtudiant
								.join("dossierBachelier");

						DossierBachelier dbac = searchBo.getDossierInscriptionAdministrative().getDossierEtudiant()
								.getDossierBachelier();
						if (dbac.getMatricule() != null && !dbac.getMatricule().isEmpty()) {
							if (predicate == null) {
								predicate = cb.equal(_dossierBachelier.get("matricule"), dbac.getMatricule());
							} else {
								predicate = cb.and(predicate,
										cb.equal(_dossierBachelier.get("matricule"), dbac.getMatricule()));
							}
						}
						if (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu() != null) {
							Join<DossierEtudiant, RefIndividu> _individu = _dossierEtudiant.join("individu");
							if (predicate == null) {
								predicate = cb.equal(_individu.get("id"), searchBo
										.getDossierInscriptionAdministrative().getDossierEtudiant().getIndividu()
										.getId());
							} else {
								predicate = cb.and(
										predicate,
										cb.equal(_individu.get("id"), searchBo.getDossierInscriptionAdministrative()
												.getDossierEtudiant().getIndividu().getId()));
							}
						}

					}

				}

				// ETABLISSMENT ET OFFRE ORIGINE
				if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation() != null) {
					Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _ouvertureOf = _dossierInscriptionAdministrative
							.join("ouvertureOffreFormation");

					// if (searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement() != null
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("")
					// && !searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement().trim()
					// .equals("null")) {
					// if (predicate == null) {
					// predicate = cb
					// .equal(_ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement());
					// } else {
					// predicate = cb
					// .and(predicate,
					// cb.equal(
					// _ouvertureOf
					// .get("refCodeEtablissement"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getOuvertureOffreFormation()
					// .getRefCodeEtablissement()));
					// }
					// }
					if (searchBo.getDossierInscriptionAdministrative().getOuvertureOffreFormation().getOffreFormation() != null) {
						if (predicate == null) {
							predicate = cb.equal(_ouvertureOf.get("offreFormation"), searchBo
									.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
									.getOffreFormation());
						} else {
							predicate = cb.and(
									predicate,
									cb.equal(_ouvertureOf.get("offreFormation"), searchBo
											.getDossierInscriptionAdministrative().getOuvertureOffreFormation()
											.getOffreFormation()));
						}
					}
				}

			}

			// condition sur l'annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<DossierTransfert, AnneeAcademique> _anneeAcademique = _dossierTransfert.join("anneeAcademique");
				if (predicate == null) {
					predicate = cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_anneeAcademique.get("id"), searchBo.getAnneeAcademique().getId()));
				}
			}

			// situation
			if (!searchSitutationEntiteBos.isEmpty()) {
				if (searchBo.getDossier() == null) {
					Dossier dossier = new Dossier();
					// dossier.setId(0);
					searchBo.setDossier(dossier);
				}

				Join<DossierTransfert, Dossier> dossier = _dossierTransfert.join("dossier");

				Path<Object> exp = dossier.get("situationEntite");
				if (predicate == null) {
					predicate = exp.in(searchSitutationEntiteBos);
				} else {
					predicate = cb.and(predicate, exp.in(searchSitutationEntiteBos));
				}

				// Join<SituationEntite, Dossier> situationEntite = dossier
				// .join("situationEntite");

				// if (predicate == null) {
				// predicate = cb.equal(dossier.get("id"), searchBo
				// .getDossier().getId());
				// } else {
				// predicate = cb.and(predicate, cb.equal(dossier
				// .get("id"), searchBo.getDossier().getId()));
				// }

				// Path<Object> exp = situationEntite.get("id");
				// List<Integer> r = new ArrayList<Integer>();
				// r.add(44);
				// if (predicate == null) {
				// predicate = exp.in(r);
				// } else {
				// predicate = cb.and(predicate, exp.in(r));
				// }
			}

			// Avis de l'etablissement d'accueil
			if (searchBo.getIsEtabAccueilAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
							searchBo.getIsEtabAccueilAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabAccueilAccepte"),
											searchBo.getIsEtabAccueilAccepte()));
				}
			}

			// Avis de l'etablissement d'origine
			if (searchBo.getIsEtabOrigineAccepte() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
							searchBo.getIsEtabOrigineAccepte());
				} else {
					predicate = cb
							.and(predicate,
									cb.equal(_dossierTransfert.get("isEtabOrigineAccepte"),
											searchBo.getIsEtabOrigineAccepte()));
				}
			}

			// Avis du responsable de transfert
			if (searchBo.getIsAccordee() != null) {
				if (predicate == null) {
					predicate = cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee());
				} else {
					predicate = cb.and(predicate,
							cb.equal(_dossierTransfert.get("isAccordee"), searchBo.getIsAccordee()));
				}
			}

			if (predicate != null)
				c.where(predicate);

			TypedQuery<DossierTransfert> _query = entityManager.createQuery(c);

			List<DossierTransfert> _resultList = _query.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<DossierTransfert>();
		}
	}

}
