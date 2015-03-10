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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ParcoursIndividualiseDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.GroupePedagogique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ParcoursIndividualise;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;

/**
 * Dao object for domain model class ParcoursIndividualise;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.ParcoursIndividualise;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("parcoursIndividualiseDao")
@Transactional
public class ParcoursIndividualiseDaoImpl implements ParcoursIndividualiseDao {

	private static final Logger log = LoggerFactory.getLogger(ParcoursIndividualiseDaoImpl.class.getName());
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      ParcoursIndividualise)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(ParcoursIndividualise transientInstance) {
		log.debug("persisting ParcoursIndividualise instance");
		try {
			entityManager.persist(transientInstance);
			/*
			 * entityManager.flush(); entityManager.refresh(transientInstance);
			 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseDao#remove(dz.gov.mesrs.sii.ParcoursIndividualiseDto.business.model.bo.cursus.ParcoursIndividualise)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(ParcoursIndividualise persistentInstance) {
		log.debug("removing ParcoursIndividualise instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.ParcoursIndividualise)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ParcoursIndividualise merge(ParcoursIndividualise detachedInstance) {
		log.debug("merging ParcoursIndividualise instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.ParcoursIndividualiseDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public ParcoursIndividualise findById(int id) {
		log.debug("getting ParcoursIndividualise instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(ParcoursIndividualise.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<ParcoursIndividualise> findAll() {
		log.debug("getting all ParcoursIndividualise instances");
		try {
			Query query = entityManager
					.createQuery("from ParcoursIndividualise parcoursIndividualise");
			log.debug("findAll 'ParcoursIndividualise' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'ParcoursIndividualise' failed", re);
			return new ArrayList<ParcoursIndividualise>();
		}
	}

	@Override
	public ParcoursIndividualise findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		log.debug("getting all findByIdInscriptionAdministrative instances");
		try {
			if (idInscriptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from ParcoursIndividualise r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative ");

			TypedQuery<ParcoursIndividualise> query = entityManager
					.createQuery(new String(sqlQuery),
							ParcoursIndividualise.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			List<ParcoursIndividualise> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findByIdInscriptionAdministrative failed", re);
			throw re;
		}
	}

	@Override
	public List<ParcoursIndividualise> findToValidate(String codeEtab) {
		log.debug("getting findToValidate instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from ParcoursIndividualise r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.refCodeEtablissement = :codeEtab  and r.situationEntite.id =32");
			TypedQuery<ParcoursIndividualise> query = entityManager
					.createQuery(new String(sqlQuery),
							ParcoursIndividualise.class);
			query.setParameter("codeEtab", codeEtab);
			List<ParcoursIndividualise> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findToValidate failed", re);
			throw re;
		}
	}

	@Override
	public List<ParcoursIndividualise> findAdvanced(
			ParcoursIndividualise searchBo) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ParcoursIndividualise> c = cb
					.createQuery(ParcoursIndividualise.class);
			Root<ParcoursIndividualise> _pi = c
					.from(ParcoursIndividualise.class);
			c.orderBy(cb.asc(_pi.get("dateCreation")));

			c.select(_pi).distinct(true);
			Predicate predicate = null;
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<ParcoursIndividualise, DossierInscriptionAdministrative> _dia = _pi
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative()
						.getRefEtablissement() != null
						&& searchBo.getDossierInscriptionAdministrative()
								.getRefEtablissement().getId() != 0) {
					predicate = cb.equal(_dia.get("reEtablissement"),
							searchBo.getDossierInscriptionAdministrative()
							.getRefEtablissement());
				}

				// condition sur le dossier d'étudiant
				if (searchBo.getDossierInscriptionAdministrative()
						.getDossierEtudiant() != null) {
					Join<DossierInscriptionAdministrative, DossierEtudiant> _dossierEtudiant = _dia
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

					// if
					// (searchBo.getDossierInscriptionAdministrative().getDossierEtudiant().getRefIndividu()
					// != null) {
					// if (predicate == null) {
					// predicate = cb.equal(_dossierEtudiant
					// .get("refIndividu"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getDossierEtudiant().getRefIndividu());
					// } else {
					// predicate = cb.and(predicate, cb.equal(_dossierEtudiant
					// .get("refIndividu"),
					// searchBo.getDossierInscriptionAdministrative()
					// .getDossierEtudiant().getRefIndividu()));
					// }
					// }
				}
				// condition sur l'année académique
				if (searchBo.getDossierInscriptionAdministrative()
						.getAnneeAcademique() != null) {
					Join<DossierInscriptionAdministrative, AnneeAcademique> _anneeAcademique = _dia
							.join("anneeAcademique");
					if (predicate == null) {
						predicate = cb.equal(_anneeAcademique.get("id"),
								searchBo.getDossierInscriptionAdministrative()
										.getAnneeAcademique().getId());
					} else {
						predicate = cb.and(
								predicate,
								cb.equal(_anneeAcademique.get("id"), searchBo
										.getDossierInscriptionAdministrative()
										.getAnneeAcademique().getId()));
					}
				}
				// condition sur l'ouverture d'offre de formation
				if (searchBo.getDossierInscriptionAdministrative()
						.getOuvertureOffreFormation() != null) {
					Join<DossierInscriptionAdministrative, OuvertureOffreFormation> _oof = _dia
							.join("ouvertureOffreFormation");

					if (searchBo.getDossierInscriptionAdministrative()
							.getOuvertureOffreFormation().getId() != 0) {
						if (predicate == null) {
							predicate = cb.equal(_oof.get("id"), searchBo
									.getDossierInscriptionAdministrative()
									.getOuvertureOffreFormation().getId());
						} else {
							predicate = cb
									.and(predicate,
											cb.equal(
													_oof.get("id"),
													searchBo.getDossierInscriptionAdministrative()
															.getOuvertureOffreFormation()
															.getId()));
						}
					}

				}

			}
			// periode
			if (searchBo.getPeriode() != null) {

				Join<ParcoursIndividualise, Periode> _periode = _pi
						.join("periode");
				if (searchBo.getPeriode().getId() != 0) {
					if (predicate == null) {
						predicate = cb.equal(_periode.get("id"), searchBo
								.getPeriode().getId());
					} else {
						predicate = cb.and(predicate, cb.equal(_periode
								.get("id"), searchBo.getPeriode().getId()));
					}
				}
			}

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<ParcoursIndividualise> _query = entityManager
						.createQuery(c);
				List<ParcoursIndividualise> result = _query.getResultList();
				for (ParcoursIndividualise parcoursIndividualise : result) {
					if (entityManager.contains(parcoursIndividualise))
						entityManager.refresh(parcoursIndividualise);
				}
				return result;
			} else
				return new ArrayList<ParcoursIndividualise>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<ParcoursIndividualise>();
		}
	}

	@Override
	public ParcoursIndividualise findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		log.debug("getting all findByIdInscriptionAdministrativeAndPeriode instances");
		try {
			if (idInscriptionAdministrative == 0 || idPeriode == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from ParcoursIndividualise r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative  and  r.periode.id = :idPeriode");

			TypedQuery<ParcoursIndividualise> query = entityManager
					.createQuery(new String(sqlQuery),
							ParcoursIndividualise.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			query.setParameter("idPeriode", idPeriode);
			List<ParcoursIndividualise> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findByIdInscriptionAdministrative failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findDossierInscription(
			DossierInscriptionAdministrative _dia,
			GroupePedagogique groupePedagogique, Integer idSituation) {
		log.debug("findEtudiant instances");
		try {
			if (_dia == null || groupePedagogique == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct p.dossierInscriptionAdministrative from ParcoursIndividualise p, ParcoursIndividualiseMatiere pmc, AssociationGroupePedagogique agp ");
			sqlQuery.append(" where (p.id = pmc.parcoursIndividualise.id)");
			sqlQuery.append(" and pmc.rattachementMc.id = agp.rattachementMc.id ");
			sqlQuery.append(" and agp.groupePedagogique.id = "
					+ groupePedagogique.getId());
			if (idSituation != null) {
				sqlQuery.append(" and p.situationEntite.id = " + idSituation);
			}

			if (groupePedagogique.getPeriode() != null
					&& groupePedagogique.getPeriode().getId() != 0) {
				sqlQuery.append(" and p.periode.id = "
						+ groupePedagogique.getPeriode().getId());
			}

			sqlQuery.append(" and p.dossierInscriptionAdministrative.id NOT IN");
			sqlQuery.append("(select af.dossierInscriptionAdministrative.id from AffectationGroupePedagogique af ");
			sqlQuery.append(" where af.groupePedagogique.id IN ");
			sqlQuery.append(" (select ap2.groupePedagogique.id from AssociationGroupePedagogique ap1, AssociationGroupePedagogique ap2 ");
			sqlQuery.append(" where ap1.groupePedagogique.id = "
					+ groupePedagogique.getId());
			sqlQuery.append(" and ap1.ap.id =  ap2.ap.id ");
			sqlQuery.append(" and ap1.rattachementMc.id =  ap2.rattachementMc.id )");

			// sqlQuery.append("(select af.dossierInscriptionAdministrative.id from AffectationGroupePedagogique af where af.groupePedagogique.id != "
			// + groupePedagogique.getId());
			// if(groupePedagogique.getAp() != null) {
			// sqlQuery.append(" and af.groupePedagogique.ap.id ="
			// + groupePedagogique.getAp().getId());
			// }
			//
			// if(groupePedagogique.getRattachementMc() != null) {
			// sqlQuery.append(" and af.groupePedagogique.rattachementMc.id = "
			// + groupePedagogique.getRattachementMc().getId());
			// }
			//
			// if(groupePedagogique.getRattachementMc() != null &&
			// groupePedagogique.getRattachementMc().getOptionnelle() != null) {
			// sqlQuery.append(" and af.groupePedagogique.rattachementMc.optionnelle = "
			// +
			// groupePedagogique.getRattachementMc().getOptionnelle().toString());
			// }
			if (groupePedagogique.getOof() != null) {

				sqlQuery.append(" and af.groupePedagogique.oof.id ="
						+ groupePedagogique.getOof().getId());
			}
			sqlQuery.append(")");

			if (groupePedagogique.getSection() != null && groupePedagogique.getSection().getId() != 0) {
				sqlQuery.append(" and p.dossierInscriptionAdministrative.id IN ");
				sqlQuery.append(" (select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a ");
				sqlQuery.append(" where a.groupePedagogique.id =:sectionId)");
				sqlQuery.append(" and p.dossierInscriptionAdministrative.id NOT IN ");
				sqlQuery.append("(select a.dossierInscriptionAdministrative.id from AffectationGroupePedagogique a ");
				sqlQuery.append("where a.groupePedagogique.section.id =:sectionId)");
			}

			if (_dia.getRefEtablissement() != null && _dia.getRefEtablissement().getId() != 0) {
				sqlQuery.append(" and p.dossierInscriptionAdministrative.refEtablissement.id = "
						+ _dia.getRefEtablissement().getId());
			}
			if (_dia.getNiveau() != null) {
				sqlQuery.append(" and p.dossierInscriptionAdministrative.niveau.id = "
						+ _dia.getNiveau().getId());
			}
			if (_dia.getOuvertureOffreFormation() != null
					&& _dia.getOuvertureOffreFormation().getId() != 0) {
				sqlQuery.append(" and p.dossierInscriptionAdministrative.ouvertureOffreFormation.id = "
						+ _dia.getOuvertureOffreFormation().getId());
			}
			if (_dia.getOuvertureOffreFormation() != null
					&& _dia.getOuvertureOffreFormation().getAnneeAcademique() != null) {
				sqlQuery.append(" and p.dossierInscriptionAdministrative.ouvertureOffreFormation.anneeAcademique.id = "
						+ _dia.getOuvertureOffreFormation()
								.getAnneeAcademique().getId());
			}

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			if (groupePedagogique.getSection() != null && groupePedagogique.getSection().getId() != 0) {
				query.setParameter("sectionId", groupePedagogique.getSection().getId());
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findDossierInscription failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findDossierInscriptions(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation) {
		log.debug("findEtudiant instances");
		try {
			if (idRattachementMc == null || idPeriode == null
					|| idAnneeAcademique == null || idSituation == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct p.dossierInscriptionAdministrative from ParcoursIndividualise p, ParcoursIndividualiseMatiere pmc ");
			sqlQuery.append(" where (p.id = pmc.parcoursIndividualise.id)");
			sqlQuery.append(" and p.dossierInscriptionAdministrative.anneeAcademique.id= "
					+ idAnneeAcademique);
			sqlQuery.append(" and p.situationEntite.id = " + idSituation);

			sqlQuery.append(" and pmc.rattachementMc.id = " + idRattachementMc);

			sqlQuery.append(" and p.periode.id = " + idPeriode);

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findDossierInscription failed", re);
			throw re;
		}
	}

	@Override
	public List<DossierInscriptionAdministrative> findDossierInscriptionsNotSubscribedOnExamen(
			Integer idRattachementMc, Integer idPeriode,
			Integer idAnneeAcademique, Integer idSituation, Integer idExamen,
			Integer idNiveau, String refCodeEtablissement) {
		log.debug("findEtudiant instances");
		try {
			if (idRattachementMc == null || idPeriode == null
					|| idAnneeAcademique == null || idSituation == null
					|| idExamen == null || idNiveau == null
					|| refCodeEtablissement == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select distinct p.dossierInscriptionAdministrative from ParcoursIndividualise p, ParcoursIndividualiseMatiere pmc ");
			sqlQuery.append(" where (p.id = pmc.parcoursIndividualise.id)");
			sqlQuery.append(" and p.dossierInscriptionAdministrative.anneeAcademique.id= "
					+ idAnneeAcademique);
			sqlQuery.append(" and p.situationEntite.id = " + idSituation);

			sqlQuery.append(" and p.dossierInscriptionAdministrative.niveau.id = "
					+ idNiveau);
			sqlQuery.append(" and p.dossierInscriptionAdministrative.refCodeEtablissement = :refCodeEtablissement");

			sqlQuery.append(" and pmc.rattachementMc.id = " + idRattachementMc);

			sqlQuery.append(" and p.periode.id = " + idPeriode);

			sqlQuery.append(" and p.dossierInscriptionAdministrative.id NOT IN");
			sqlQuery.append("(select ie.dossierInscriptionAdministrative.id from InscriptionExamen ie where ie.id ="
					+ idExamen + ")");

			TypedQuery<DossierInscriptionAdministrative> query = entityManager
					.createQuery(new String(sqlQuery),
							DossierInscriptionAdministrative.class);
			query.setParameter("refCodeEtablissement", refCodeEtablissement);
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findDossierInscription failed", re);
			throw re;
		}

	}

}
