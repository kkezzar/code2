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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AbsenceMatiereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AbsenceMatiere;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;

/**
 * Dao object for domain model class AbsenceMatiere.
 * 
 * @see dz.gov.mesrs.sii.commons.data.model.fve.cursus.fve.business.model.bo.cursus.scolformation.business.model.bo.AbsenceMatiere.cursus
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("absenceMatiereDao")
@Transactional
public class AbsenceMatiereDaoImpl implements AbsenceMatiereDao {
	private static final Logger log = LoggerFactory.getLogger(AbsenceMatiereDaoImpl.class.getName());


	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#persist(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * 
	 * @param transientInstance
	 */

	@Override
	public void persist(AbsenceMatiere transientInstance) {
		log.debug("persisting AbsenceMatiere instance");
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
	public void remove(AbsenceMatiere persistentInstance) {
		log.debug("removing AbsenceMatiere instance");
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
	public AbsenceMatiere merge(AbsenceMatiere detachedInstance) {
		log.debug("merging AbsenceMatiere instance");
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
	public AbsenceMatiere findById(int id) {
		log.debug("getting AbsenceMatiere instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AbsenceMatiere.class, id);
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
	public List<AbsenceMatiere> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AbsenceMatiere> findAll() {
		log.debug("getting all AbsenceMatiere instances");
		try {
			Query query = entityManager
					.createQuery("from AbsenceMatiere absenceMatiere");
			log.debug("findAll 'AbsenceMatiere' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AbsenceMatiere' failed", re);
			return new ArrayList<AbsenceMatiere>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.assiduite findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.bo.assiduite)
	 */

	@Override
	public List<AbsenceMatiere> findAdvanced(AbsenceMatiere searchBo) {

		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AbsenceMatiere> c = cb
					.createQuery(AbsenceMatiere.class);
			Root<AbsenceMatiere> _absenceMatiere = c.from(AbsenceMatiere.class);

			c.select(_absenceMatiere).distinct(true);
			Predicate predicate = null;

			// Ann�e
			if (searchBo.getAnneeAcademique() != null) {
				Join<AbsenceMatiere, AnneeAcademique> _anneeAcademique = _absenceMatiere
						.join("anneeAcademique");

				predicate = cb.equal(_anneeAcademique.get("id"), searchBo
						.getAnneeAcademique().getId());

			}

			// Date absence
			if (searchBo.getDateAbsence() != null
					&& !searchBo.getDateAbsence().equals("")
					&& !searchBo.getDateAbsence().equals("null")) {
				if (predicate == null) {
					predicate = cb.equal(_absenceMatiere.get("dateAbsence"),
							searchBo.getDateAbsence());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_absenceMatiere.get("dateAbsence"),
							searchBo.getDateAbsence()));
				}
			}

			// //matiere
			// if(searchBo.getMc()!=null && !searchBo.getMc().equals("") &&
			// !searchBo.getMc().equals("null")){
			//
			// predicate = cb.equal(_absenceMatiere.get("mc"),
			// searchBo.getMc());
			//
			// }

			// Date absence
			if (searchBo.getDateAbsence() != null
					&& !searchBo.getDateAbsence().equals("")
					&& !searchBo.getDateAbsence().equals("null")) {

				predicate = cb.equal(_absenceMatiere.get("date_absence"),
						searchBo.getDateAbsence());
			}

			// condition sur le dossier d'inscription
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<AbsenceMatiere, DossierInscriptionAdministrative> _dossierInscriptionAdministrative = _absenceMatiere
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
							.getDossierEtudiant().getIndividu() != null
							&& searchBo.getDossierInscriptionAdministrative()
									.getDossierEtudiant().getIndividu().getId() != 0) {
						if (predicate == null) {
							predicate = cb
									.equal(_dossierEtudiant.get("individu"),
											searchBo.getDossierInscriptionAdministrative()
													.getDossierEtudiant()
													.getIndividu());
						} else {
							predicate = cb
									.and(predicate,
											cb.equal(
													_dossierEtudiant
															.get("individu"),
													searchBo.getDossierInscriptionAdministrative()
															.getDossierEtudiant()
															.getIndividu()));
						}
					}
				}

			}

			if (predicate != null)
				c.where(predicate);

			TypedQuery<AbsenceMatiere> _query = entityManager.createQuery(c);
			List<AbsenceMatiere> _resultList = (List<AbsenceMatiere>) _query
					.getResultList();

			return _resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<AbsenceMatiere>();
		}

	}

	@Override
	public List<AbsenceMatiere> findByCode(String codeNat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbsenceMatiere findByIdDossier(int idDossier) {
		// try {
		// String sqlQuery =
		// "SELECT c FROM AbsenceMatiere c WHERE c.dossierEtudiant.id=:idDossier ";
		//
		// TypedQuery<AbsenceMatiere> query =
		// entityManager.createQuery(sqlQuery,
		// AbsenceMatiere.class);
		// query.setParameter("idDossier", idDossier);
		//
		// List<AbsenceMatiere> result = query.getResultList();
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

}
