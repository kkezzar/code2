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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.AttestationFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.AttestationFinEtude;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * Dao object for domain model class attestationFinEtude;
 * 
 * @see dz.gov.mesrs.sii.fve.business.model.bo.cursus.attestationFinEtude;
 * @author MESRS CCM
 * @version 1.0
 * @created 02-10-2014 16:44:07
 */

@Repository("attestationFinEtudeDao")
@Transactional
public class AttestationFinEtudeDaoImpl implements AttestationFinEtudeDao {

	private static final Logger log = LoggerFactory.getLogger(AttestationFinEtudeDaoImpl.class.getName());
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.attestationFinEtudeDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.cursus.
	 *      attestationFinEtude)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(AttestationFinEtude transientInstance) {
		log.debug("persisting AttestationFinEtude instance");
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
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.attestationFinEtudeDao#remove(dz.gov.mesrs.sii.attestationFinEtudeDto.business.model.bo.cursus.attestationFinEtude)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(AttestationFinEtude persistentInstance) {
		log.debug("removing AttestationFinEtude instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.attestationFinEtudeDao#merge(dz.gov.mesrs.sii.fve.business.model.bo.cursus.attestationFinEtude)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AttestationFinEtude merge(AttestationFinEtude detachedInstance) {
		log.debug("merging AttestationFinEtude instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.fve.business.persistence.jpa.impl.cursus.attestationFinEtudeDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public AttestationFinEtude findById(int id) {
		log.debug("getting AttestationFinEtude instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(AttestationFinEtude.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<AttestationFinEtude> findAll() {
		log.debug("getting all AttestationFinEtude instances");
		try {
			Query query = entityManager
					.createQuery("from AttestationFinEtude attestationFinEtude");
			log.debug("findAll 'AttestationFinEtude' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'AttestationFinEtude' failed", re);
			return new ArrayList<AttestationFinEtude>();
		}
	}

	@Override
	public AttestationFinEtude findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		log.debug("getting all findByIdInscriptionAdministrative instances");
		try {
			if (idInscriptionAdministrative == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from AttestationFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative ");

			TypedQuery<AttestationFinEtude> query = entityManager
					.createQuery(new String(sqlQuery),
							AttestationFinEtude.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			List<AttestationFinEtude> result = query.getResultList();
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
	public List<AttestationFinEtude> findToValidate(String codeEtab) {
		log.debug("getting findToValidate instances");
		try {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from AttestationFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.refCodeEtablissement = :codeEtab  and r.situationEntite.id =32");
			TypedQuery<AttestationFinEtude> query = entityManager
					.createQuery(new String(sqlQuery),
							AttestationFinEtude.class);
			query.setParameter("codeEtab", codeEtab);
			List<AttestationFinEtude> result = query.getResultList();
			return result;
		} catch (RuntimeException re) {
			log.error("findToValidate failed", re);
			throw re;
		}
	}

	@Override
	public List<AttestationFinEtude> findAdvanced(
			AttestationFinEtude searchBo) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<AttestationFinEtude> c = cb
					.createQuery(AttestationFinEtude.class);
			Root<AttestationFinEtude> _pi = c
					.from(AttestationFinEtude.class);
			c.orderBy(cb.asc(_pi.get("dateObtention")));

			c.select(_pi).distinct(true);
			Predicate predicate = null;
			if (searchBo.getDossierInscriptionAdministrative() != null) {

				Join<AttestationFinEtude, DossierInscriptionAdministrative> _dia = _pi
						.join("dossierInscriptionAdministrative");
				if (searchBo.getDossierInscriptionAdministrative()
						.getRefEtablissement() != null) {
					Join<DossierInscriptionAdministrative, RefEtablissement> _refEtablissement = _dia
							.join("refEtablissement");
					if (predicate == null) {
						predicate = cb.equal(_refEtablissement.get("id"),
								searchBo.getDossierInscriptionAdministrative()
										.getRefEtablissement().getId());
					} 
				}

				// le dossier d'étudiant
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

							}
				// l'année académique
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
			

			if (predicate != null) {
				c.where(predicate);
				TypedQuery<AttestationFinEtude> _query = entityManager
						.createQuery(c);
				List<AttestationFinEtude> result = _query.getResultList();
				for (AttestationFinEtude attestationFinEtude : result) {
					if(entityManager.contains(attestationFinEtude))
						entityManager.refresh(attestationFinEtude);
				}
				return result;
			} else
				return new ArrayList<AttestationFinEtude>();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<AttestationFinEtude>();
		}
	}

	@Override
	public AttestationFinEtude findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		log.debug("getting all findByIdInscriptionAdministrativeAndPeriode instances");
		try {
			if (idInscriptionAdministrative == 0 || idPeriode == 0) {
				return null;
			}
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from AttestationFinEtude r ");
			sqlQuery.append("where r.dossierInscriptionAdministrative.id = :idInscriptionAdministrative  and  r.periode.id = :idPeriode");

			TypedQuery<AttestationFinEtude> query = entityManager
					.createQuery(new String(sqlQuery),
							AttestationFinEtude.class);
			query.setParameter("idInscriptionAdministrative",
					idInscriptionAdministrative);
			query.setParameter("idPeriode", idPeriode);
			List<AttestationFinEtude> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findByIdInscriptionAdministrative failed", re);
			throw re;
		}
	}


	
	
	

}
