package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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




import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.DossierBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierEtudiant;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class DossierEtudiant.
 * 
 * @see dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Repository("dossierEtudiantDao")
@Transactional
public class DossierEtudiantDaoImpl implements DossierEtudiantDao {

	private static final Logger log = LoggerFactory.getLogger(DossierEtudiantDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.DossierEtudiantDao#persist(dz.gov.mesrs.sii.DossierEtudiantDto.business.model.bo.DossierEtudiant)
	 * 
	 * @param transientInstance
	 */

	@Override
	public void persist(DossierEtudiant transientInstance) {
		log.debug("persisting DossierEtudiant instance");
		try {
			if (transientInstance.getNumeroMatricule() == null
					|| transientInstance.getNumeroMatricule().isEmpty()) {
				String matricule = generateMatricule(transientInstance);
				if (matricule == null
						|| matricule.length() != UtilConstant.CODIFICATION_MATRICULE_ETUDIANT_LENGTH) {
					return;
				}

				transientInstance.setNumeroMatricule(matricule);
			}
			// transientInstance.setDossierBachelier(null);
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
	public void remove(DossierEtudiant persistentInstance) {
		log.debug("removing DossierEtudiant instance");
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
	public DossierEtudiant merge(DossierEtudiant detachedInstance) {
		log.debug("merging DossierEtudiant instance");
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
	public DossierEtudiant findById(int id) {
		log.debug("getting DossierEtudiant instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(DossierEtudiant.class, id);
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
	public List<DossierEtudiant> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<DossierEtudiant> findAll() {
		log.debug("getting all DossierEtudiant instances");
		try {
			Query query = entityManager
					.createQuery("from DossierEtudiant dossierEtudiant");
			log.debug("findAll 'DossierEtudiant' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'DossierEtudiant' failed", re);
			return new ArrayList<DossierEtudiant>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.persistence.cursus.DossierEtudiantDao#
	 * findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.bo.cursus.DossierEtudiant)
	 */
	@Override
	public List<DossierEtudiant> findAdvanced(DossierEtudiant searchBo) {
		try {
			if (searchBo != null) {
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<DossierEtudiant> c = cb
						.createQuery(DossierEtudiant.class);
				Root<DossierEtudiant> dossierEtudiant = c
						.from(DossierEtudiant.class);

				c.select(dossierEtudiant).distinct(true);
				Predicate predicate = null;
				if (searchBo.getNumeroMatricule() != null
						&& !searchBo.getNumeroMatricule().trim().equals("")
						&& !searchBo.getNumeroMatricule().trim().equals("null")) {
					predicate = cb.equal(
							dossierEtudiant.get("numeroMatricule"),
							searchBo.getNumeroMatricule());
				}

				if (searchBo.getRefEtablissement() != null) {
					Join<DossierEtudiant, RefEtablissement> _refEtablissement = dossierEtudiant
							.join("refEtablissement");
					if (searchBo.getIndividu().getId() != 0) {
						if (predicate == null) {
							predicate = cb.equal(_refEtablissement.get("id"),
									searchBo.getRefEtablissement().getId());
						} else {
							predicate = cb.and(predicate, cb.equal(
									_refEtablissement.get("id"), searchBo
											.getRefEtablissement().getId()));
						}
					}

				}

				if (searchBo.getIndividu() != null) {
					Join<DossierEtudiant, RefIndividu> _individu = dossierEtudiant
							.join("individu");
					if (searchBo.getIndividu().getId() != 0) {
						if (predicate == null) {
							predicate = cb.equal(_individu.get("id"), searchBo
									.getIndividu().getId());
						} else {
							predicate = cb
									.and(predicate, cb.equal(_individu
											.get("id"), searchBo.getIndividu()
											.getId()));
						}
					}

				}
				if (predicate != null) {
					c.where(predicate);
				}

				if (searchBo.getDossierBachelier() != null) {
					Join<DossierEtudiant, DossierBachelier> _bachelier = dossierEtudiant
							.join("dossierBachelier");
					if (searchBo.getDossierBachelier().getId() != 0) {
						if (predicate == null) {
							predicate = cb.equal(_bachelier.get("id"), searchBo
									.getDossierBachelier().getId());
						} else {
							predicate = cb.and(predicate, cb.equal(_bachelier
									.get("id"), searchBo.getDossierBachelier()
									.getId()));
						}
					}

				}
				if (predicate != null) {
					c.where(predicate);
				}

				TypedQuery<DossierEtudiant> q = entityManager.createQuery(c);
				List<DossierEtudiant> resultList = (List<DossierEtudiant>) q
						.getResultList();
				if (resultList != null && !resultList.isEmpty()) {
					return resultList;
				}
			}

			return null;

		} catch (RuntimeException re) {

			log.error("findAdvanced  failed", re);
			throw re;
		}
	}

	/**
	 * [DossierEtudiantDaoImpl.generateMatricule] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 16:46:28
	 * @param dossierEtudiant
	 * @return
	 */
	public String generateMatricule(DossierEtudiant dossierEtudiant) {
		try {
			if (dossierEtudiant == null) {
				return null;
			}
			if (dossierEtudiant.getDossierBachelier() == null
					|| dossierEtudiant.getDossierBachelier().getMatricule() == null
					|| dossierEtudiant.getDossierBachelier().getImportation() == null
					|| dossierEtudiant.getDossierBachelier().getImportation()
							.getAnneeBac() == null) {
				return null;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Integer ai = cal.get(Calendar.YEAR);
			String anneeInscription = ai.toString().substring(2);
			String anneeBac = dossierEtudiant.getDossierBachelier()
					.getImportation().getAnneeBac().substring(2, 4);
			String matricule = anneeInscription + anneeBac
					+ dossierEtudiant.getDossierBachelier().getMatricule();
			return matricule;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * Rechercher le derniere dossier d'etudiant enregistr� par l'id de
	 * l'individu
	 * 
	 * @author Mounir.MESSAOUDI on : 30 oct. 2014 08:21:40
	 * @param idIndividu
	 * @return
	 */
	@Override
	@Transactional
	public DossierEtudiant findLastByIdIndividu(Integer idIndividu) {
		try {
			if (idIndividu == null) {
				return null;
			}

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DossierEtudiant r ");
			sqlQuery.append("where r.individu.id = :idIndividu ");
			sqlQuery.append(" order by r.dossier.dateCreation desc");
			TypedQuery<DossierEtudiant> query = entityManager.createQuery(
					new String(sqlQuery), DossierEtudiant.class);
			query.setParameter("idIndividu", idIndividu);
			List<DossierEtudiant> results = query.getResultList();

			if (results.size() >= 1) {
				return results.get(0);
			}
		} catch (RuntimeException re) {
			log.error("findByRefIndividu failed", re);
			throw re;
		}
		return null;
	}

	/**
	 * Recherche avancee avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:41:08
	 * @param searchDto
	 * @return
	 */
	public List<DossierEtudiant> findAdvanced(DossierEtudiant searchDto,
			String sortField, Integer pageSize, Integer first,
			Boolean descending) {

		CriteriaQuery<DossierEtudiant> criteria = (CriteriaQuery<DossierEtudiant>) getJPACriteria(
				searchDto, false);

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

		TypedQuery<DossierEtudiant> _query = entityManager
				.createQuery(criteria);

		if (pageSize != null) {
			_query.setMaxResults(pageSize);
			_query.setFirstResult(first);
		}

		return _query.getResultList();

	}

	/**
	 * Nombre de resultats de la recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:42:53
	 * @param searchDto
	 * @return
	 */
	public Long count(DossierEtudiant searchDto) {
		try {
			@SuppressWarnings("unchecked")
			CriteriaQuery<Long> criteria = (CriteriaQuery<Long>) getJPACriteria(
					searchDto, true);

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
	private CriteriaQuery<?> getJPACriteria(DossierEtudiant searchBo,
			boolean forCount) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<?> c;
		Root<DossierEtudiant> dossierEtudiant;

		if (forCount) {
			c = cb.createQuery(Long.class);
			dossierEtudiant = c.from(DossierEtudiant.class);
			((CriteriaQuery<Long>) c).select(cb.count(dossierEtudiant));

		} else {
			c = cb.createQuery(DossierInscriptionAdministrative.class);
			dossierEtudiant = c.from(DossierEtudiant.class);
			((CriteriaQuery<DossierEtudiant>) c).select(dossierEtudiant)
					.distinct(true);

		}

		Predicate predicate = null;

		// numero de matricule
		if (searchBo.getNumeroMatricule() != null
				&& !searchBo.getNumeroMatricule().isEmpty()) {

			predicate = cb.equal(dossierEtudiant.get("numeroMatricule"),
					searchBo.getNumeroMatricule());
		}

		// individu
		RefIndividu individu = searchBo.getIndividu();
		if (individu != null) {

			// if (predicate == null) {
			// predicate = cb.equal(dossierEtudiant.get("refIndividu"),
			// searchBo.getRefIndividu());
			// } else {
			// predicate = cb.and(
			// predicate,
			// cb.equal(dossierEtudiant.get("refIndividu"),
			// searchBo.getRefIndividu()));
			// }

			Join<DossierEtudiant, RefIndividu> _individu = dossierEtudiant
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

					predicate = cb.or(predicate, cb.like(
							cb.lower(_individu.<String> get("prenomArabe")),
							individu.getPrenomArabe().toLowerCase()));
				} else {
					predicate = cb.and(predicate, cb.like(
							cb.lower(_individu.<String> get("prenomLatin"))
							// _individu.get("nomLatin").as(String.class)
							, individu.getPrenomLatin().toLowerCase()));

					predicate = cb.or(predicate, cb.like(
							cb.lower(_individu.<String> get("prenomArabe")),
							individu.getPrenomArabe().toLowerCase()));
				}

				// matricule de bac

			}
		}

		// etablissement
		if (searchBo.getRefEtablissement() != null) {
			Join<DossierEtudiant, RefEtablissement> _refEtablissement = dossierEtudiant
					.join("refEtablissement");
			if (searchBo.getRefEtablissement().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_refEtablissement.get("id"), searchBo
							.getRefEtablissement().getId());
				} else {
					predicate = cb
							.and(predicate, cb.equal(_refEtablissement
									.get("id"), searchBo.getRefEtablissement()
									.getId()));
				}
			}

		}

		// dossier bachelier
		if (searchBo.getDossierBachelier() != null) {
			Join<DossierEtudiant, DossierBachelier> _bachelier = dossierEtudiant
					.join("dossierBachelier");
			if (searchBo.getDossierBachelier().getId() != 0) {
				if (predicate == null) {
					predicate = cb.equal(_bachelier.get("id"), searchBo
							.getDossierBachelier().getId());
				} else {
					predicate = cb.and(predicate, cb.equal(
							_bachelier.get("id"), searchBo
									.getDossierBachelier().getId()));
				}
			}

		}

		if (predicate != null) {
			c.where(predicate);
		}

		c.where(predicate);

		return c;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierEtudiantDao#
	 * findByIdIndividu(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<DossierEtudiant> findByIdIndividu(Integer id,
			Integer idEtablissment) {
		try {
			if (id == null) {
				return null;
			}
			boolean etablissement = false;
			if (idEtablissment != null
					) {
				etablissement = true;
				
			}

			// TODO a mettre a jours refIndividu par idIndividu
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select r from DossierEtudiant r ");
			sqlQuery.append("where r.individu.id = " + id);
			if (etablissement) {
				sqlQuery.append(" and r.refEtablissement.id = " + idEtablissment);
			}
			sqlQuery.append(" order by r.dossier.dateCreation desc");
			TypedQuery<DossierEtudiant> query = entityManager.createQuery(
					new String(sqlQuery), DossierEtudiant.class);

			if (etablissement) {
				sqlQuery.append(" and r.refEtablissement.id = " + idEtablissment);
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findByIdIndividu failed", re);
			throw re;
		}
	}

}
