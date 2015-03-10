package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.VoeuEtudiantDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.VoeuEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;

/**
 * 
 * @author Mounir.MESSAOUDI on : 28 sept. 2014 17:08:04
 */
@Repository("voeuEtudiantDao")
@Transactional
public class VoeuEtudiantDaoImpl implements VoeuEtudiantDao {

	private static final Logger log = LoggerFactory.getLogger(VoeuEtudiantDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(VoeuEtudiant transientInstance) {
		log.debug("persisting VoeuEtudiant transientInstance instance");
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
	public void remove(VoeuEtudiant persistentInstance) {
		log.debug("removing VoeuEtudiant instance");
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
	public VoeuEtudiant merge(VoeuEtudiant detachedInstance) {
		log.debug("merging VoeuEtudiant instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public VoeuEtudiant findById(int id) {
		log.debug("getting VoeuEtudiant instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(VoeuEtudiant.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiant> findAdvanced(VoeuEtudiant searchBo,
			List<SituationEntite> searchSituationEntiteBos) {
		try {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<VoeuEtudiant> c = cb.createQuery(VoeuEtudiant.class);
			Root<VoeuEtudiant> voeuEtudiantRoot = c.from(VoeuEtudiant.class);

			c.select(voeuEtudiantRoot).distinct(true);
			Predicate predicate = null;

			// Annee academique
			if (searchBo.getAnneeAcademique() != null) {
				Join<VoeuEtudiant, AnneeAcademique> anneeAcademique = voeuEtudiantRoot
						.join("anneeAcademique");

				predicate = cb.equal(anneeAcademique.get("id"), searchBo
						.getAnneeAcademique().getId());

			}

			// domaine
			if (searchBo.getDomaine() != null) {
				Join<VoeuEtudiant, RefDomaineLMD> d = voeuEtudiantRoot
						.join("domaine");

				if (predicate == null) {
					predicate = cb.equal(d.get("id"), searchBo.getDomaine()
							.getId());
				} else {
					predicate = cb.and(predicate, cb.equal(d.get("id"),
							searchBo.getDomaine().getId()));
				}
			}

			// filiere
			if (searchBo.getFiliere() != null) {
				Join<VoeuEtudiant, RefFiliereLmd> d = voeuEtudiantRoot
						.join("filiere");

				if (predicate == null) {
					predicate = cb.equal(d.get("id"), searchBo.getFiliere()
							.getId());
				} else {
					predicate = cb.and(predicate, cb.equal(d.get("id"),
							searchBo.getFiliere().getId()));
				}
			}

			// specialite
			if (searchBo.getSpecialite() != null) {
				Join<VoeuEtudiant, RefSpecialiteLmd> d = voeuEtudiantRoot
						.join("specialite");

				if (predicate == null) {
					predicate = cb.equal(d.get("id"), searchBo.getSpecialite()
							.getId());
				} else {
					predicate = cb.and(predicate, cb.equal(d.get("id"),
							searchBo.getSpecialite().getId()));
				}
			}

			// etablissement
			// if (searchBo.getRefCodeEtablissement() != null) {
			// if (predicate == null) {
			// predicate = cb.equal(
			// voeuEtudiantRoot.get("refCodeEtablissement"),
			// searchBo.getRefCodeEtablissement());
			// } else {
			// predicate = cb.and(predicate, cb.equal(
			// voeuEtudiantRoot.get("refCodeEtablissement"),
			// searchBo.getRefCodeEtablissement()));
			// }
			// }

			// etudiant
			if (searchBo.getDossierEtudiant() != null) {
				Join<VoeuEtudiant, DossierEtudiant> d = voeuEtudiantRoot
						.join("dossierEtudiant");

				if (predicate == null) {
					predicate = cb.equal(d.get("id"), searchBo
							.getDossierEtudiant().getId());
				} else {
					predicate = cb.and(predicate, cb.equal(d.get("id"),
							searchBo.getDossierEtudiant().getId()));
				}
			}

			// situation
			if (searchSituationEntiteBos != null
					&& !searchSituationEntiteBos.isEmpty()) {

				Path<Object> exp = voeuEtudiantRoot.get("situationEntite");
				if (predicate == null) {
					predicate = exp.in(searchSituationEntiteBos);
				} else {
					predicate = cb.and(predicate,
							exp.in(searchSituationEntiteBos));
				}
			}

			if (predicate != null) {
				c.where(predicate);
			}
			TypedQuery<VoeuEtudiant> query = entityManager.createQuery(c);
			return (List<VoeuEtudiant>) query.getResultList();

		} catch (RuntimeException re) {
			log.error("findAdvanced  failed", re);
			return new ArrayList<VoeuEtudiant>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiant> findByQuery(String query) {
		log.debug("findByQuery VoeuEtudiant instances");
		try {
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findByQuery 'VoeuEtudiant' failed", re);
			return new ArrayList<VoeuEtudiant>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<VoeuEtudiant> findAll() {
		log.debug("getting all VoeuEtudiant instances");
		try {
			Query query = entityManager
					.createQuery("from VoeuEtudiant voeuEtudiant");
			log.debug("findAll 'VoeuEtudiant' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'VoeuEtudiant' failed", re);
			return new ArrayList<VoeuEtudiant>();
		}
	}

	/**
	 * rechercher une fiche de voeux par annee academqiue et dossier etudiant
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 10:39:53
	 * @param searchBo
	 * @return
	 */
	public VoeuEtudiant findByIdDosEtudIdAnnAcad(Integer idDossierEtudiant,
			Integer idAnneeAcademique) {
		log.debug("findByIdDosEtudIdAnnAcad VoeuEtudiant instance with idDossierEtudiant: "
				+ idDossierEtudiant + " idAnneeAcademique " + idAnneeAcademique);

		try {

			String sqlQuery = "SELECT v FROM VoeuEtudiant v WHERE v.anneeAcademique.id=:idAnneeAcademique AND v.dossierEtudiant.id = :idDossierEtudiant";

			TypedQuery<VoeuEtudiant> query = entityManager.createQuery(
					sqlQuery, VoeuEtudiant.class);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			query.setParameter("idDossierEtudiant", idDossierEtudiant);

			log.debug("findByIdDosEtudIdAnnAcad 'VoeuEtudiant' successful");

			List<VoeuEtudiant> results = query.getResultList();
			if (results.isEmpty()) {
				return null; // handle no-results case
			} else {
				return results.get(0);
			}
		} catch (NoResultException nre) {
			log.error("findByIdDosEtudIdAnnAcad 'VoeuEtudiant' failed", nre);
			// Ignore this because as per your logic this is ok!
		} catch (RuntimeException re) {
			log.error("findByIdDosEtudIdAnnAcad 'VoeuEtudiant' failed", re);
		}
		return null;
	}
}