package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefContratDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.data.util.UtilDate;
@Repository("refContratDaoImpl")
public class RefContratDaoImpl implements RefContratDao {

	private static final Logger log = LoggerFactory.getLogger(RefContratDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void persist(RefContrat transientInstance) {
		log.debug("persisting RefContrat instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void remove(RefContrat persistentInstance) {
		log.debug("removing RefContrat instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	public RefContrat merge(RefContrat detachedInstance) {
		log.debug("merging RefContrat instance");
		try {
			RefContrat result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public RefContrat findById(int id) {
		log.debug("getting RefContrat instance with id: " + id);
		try {
			RefContrat instance = entityManager.find(RefContrat.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<RefContrat> findGeneric(String value, boolean avenant) {
		if (value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		log.debug("getting List of RefContrat instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefContrat r ");
			if (avenant) {
				request.append("where r.avenant = TRUE ");
			} else {
				request.append("where r.avenant = FALSE ");
			}
			request.append("and (lower(r.identifiant) like :value ");
			request.append("or lower(r.objetContratFr) like :value ");
			request.append("or cast(r.dateCreation as text) like :value ");
			request.append("or cast(r.dateDebutValidite as text) like :value ");
			request.append("or cast(r.dateFinValidite as text) like :value ");
			request.append("or cast(r.duree as text) like :value ");
			request.append("or cast(r.montantHt as text) like :value ");
			request.append("or cast(r.montantTva as text) like :value ");
			request.append("or cast(r.montantTtc as text) like :value ");
			request.append("or lower(r.objetContratAr) like :value ");
			request.append("or lower(r.observation) like :value ");
			request.append("or lower(r.referenceDocumentaire) like :value ");
			request.append("or cast(r.dateSignatureGestionnaire as text) like :value )");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefContrat> resultList = (List<RefContrat>) query
					.getResultList();
			log.debug("findByName successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByName failed", re);
			throw re;
		}
	}

	@Override
	public List<RefContrat> findAdvanced(RefContrat refContrat, boolean avenant) {

		log.debug("getting List of RefContrat instance : ");
		if (refContrat == null) {
			return null;
		}
		String opr = UtilConstant.WHERE_SQL_STR;

		boolean select = false;
		try {
			StringBuilder buffer = new StringBuilder();

			String identifiant = refContrat.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				buffer.append("select r from RefContrat r ");
				buffer.append(" where lower(r.identifiant) like "
						+ UtilConstant.LikeContain(identifiant.toLowerCase()));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			String objetContratFr = refContrat.getObjetContratFr();
			if ((objetContratFr != null) && (!objetContratFr.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");

				}
				buffer.append(opr
						+ " lower(r.objetContratFr) like "
						+ UtilConstant.LikeContain(objetContratFr.toLowerCase()));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Date dateCreation = refContrat.getDateCreation();

			if (dateCreation != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.dateCreation as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateCreation)));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Date dateDebutValidite = refContrat.getDateDebutValidite();

			if (dateDebutValidite != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.dateDebutValidite as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateDebutValidite)));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Date dateFinValidite = refContrat.getDateFinValidite();
			if (dateFinValidite != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.dateFinValidite as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateFinValidite)));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Short duree = refContrat.getDuree();
			if (duree != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.duree as text) like "
						+ UtilConstant.LikeContain(duree));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			BigDecimal montantHt = refContrat.getMontantHt();
			if (montantHt != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.montantHt as text) like "
						+ UtilConstant.LikeContain(montantHt));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			BigDecimal montantTva = refContrat.getMontantTva();
			if (montantTva != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.montantTva as text) like "
						+ UtilConstant.LikeContain(montantTva));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			BigDecimal montantTtc = refContrat.getMontantTtc();
			if (montantTtc != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " cast(r.montantTtc as text) like "
						+ UtilConstant.LikeContain(montantTtc));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			String objetContratAr = refContrat.getObjetContratAr();
			if (objetContratAr != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " r.objetContratAr like "
						+ UtilConstant.LikeContain(objetContratAr));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			String observation = refContrat.getObservation();
			if ((observation != null) && (!observation.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr + " lower(r.observation) like "
						+ UtilConstant.LikeContain(observation.toLowerCase()));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			String referenceDocumentaire = refContrat
					.getReferenceDocumentaire();
			if ((referenceDocumentaire != null)
					&& (!referenceDocumentaire.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr
						+ " lower(r.referenceDocumentaire) like "
						+ UtilConstant.LikeContain(referenceDocumentaire
								.toLowerCase()));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Date dateSigantureGestionnaire = refContrat
					.getDateSignatureGestionnaire();
			if (dateSigantureGestionnaire != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				buffer.append(opr
						+ " cast(r.dateSignatureGestionnaire as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateSigantureGestionnaire)));
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}

			Boolean reconductible = refContrat.getReconductible();
			if (reconductible != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
				}
				if (reconductible) {

					buffer.append(opr + " r.reconductible = TRUE");
				} else {
					buffer.append(opr + " r.reconductible = FALSE ");
				}
				select = true;
				opr = UtilConstant.AND_SQL_STR;
			}
			if (select) {
				if (avenant) {
					buffer.append(" and r.avenant = TRUE");
				} else {
					buffer.append(" and r.avenant = FALSE ");
				}
			}
			String request = new String(buffer);
			if (request.trim().isEmpty()) {
				return null;
			}
			log.debug("request successful " + request);
			Query query = entityManager.createQuery(request);
			List<RefContrat> resultList = (List<RefContrat>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	}

	@Override
	public List<RefContrat> findGeneric(Integer etabId, String value,
			boolean avenant) {
		if (value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		log.debug("getting List of RefContrat instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefContrat r ");
			request.append(" where (1=1)");
			if (etabId != null) {
				request.append(" and r.refEtablissement.id = " + etabId);
			}
			
			if (avenant) {
				request.append(" and r.avenant = TRUE ");
			} else {
				request.append(" and r.avenant = FALSE ");
			}
			
			request.append("and (lower(r.identifiant) like :value ");
			request.append("or lower(r.objetContratFr) like :value ");
			request.append("or cast(r.dateCreation as text) like :value ");
			request.append("or cast(r.dateDebutValidite as text) like :value ");
			request.append("or cast(r.dateFinValidite as text) like :value ");
			request.append("or cast(r.duree as text) like :value ");
			request.append("or cast(r.montantHt as text) like :value ");
			request.append("or cast(r.montantTva as text) like :value ");
			request.append("or cast(r.montantTtc as text) like :value ");
			request.append("or lower(r.objetContratAr) like :value ");
			request.append("or lower(r.observation) like :value ");
			request.append("or lower(r.referenceDocumentaire) like :value ");
			request.append("or cast(r.dateSignatureGestionnaire as text) like :value )");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefContrat> resultList = (List<RefContrat>) query
					.getResultList();
			log.debug("findByName successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findByName failed", re);
			throw re;
		}
	}

	@Override
	public List<RefContrat> findAdvanced(Integer etabId,
			RefContrat refContrat, boolean avenant) {
		log.debug("getting List of RefContrat instance : ");
		if (refContrat == null || etabId == null) {
			return null;
		}
	
		String opr = UtilConstant.AND_SQL_STR;

		boolean select = false;
		try {
			StringBuilder buffer = new StringBuilder();

			String identifiant = refContrat.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				buffer.append("select r from RefContrat r ");
				buffer.append(" where r.refEtablissement.id = " + etabId);
				buffer.append(" and lower(r.identifiant) like "
						+ UtilConstant.LikeContain(identifiant.toLowerCase()));
				select = true;
			}

			String objetContratFr = refContrat.getObjetContratFr();
			if ((objetContratFr != null) && (!objetContratFr.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);

				}
				buffer.append(opr
						+ " lower(r.objetContratFr) like "
						+ UtilConstant.LikeContain(objetContratFr.toLowerCase()));
				select = true;
			}

			Date dateCreation = refContrat.getDateCreation();

			if (dateCreation != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.dateCreation as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateCreation)));
				select = true;
			}

			Date dateDebutValidite = refContrat.getDateDebutValidite();

			if (dateDebutValidite != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.dateDebutValidite as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateDebutValidite)));
				select = true;
			}

			Date dateFinValidite = refContrat.getDateFinValidite();
			if (dateFinValidite != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.dateFinValidite as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateFinValidite)));
				select = true;
			}

			Short duree = refContrat.getDuree();
			if (duree != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.duree as text) like "
						+ UtilConstant.LikeContain(duree));
				select = true;
			}

			BigDecimal montantHt = refContrat.getMontantHt();
			if (montantHt != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.montantHt as text) like "
						+ UtilConstant.LikeContain(montantHt));
				select = true;
			}

			BigDecimal montantTva = refContrat.getMontantTva();
			if (montantTva != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.montantTva as text) like "
						+ UtilConstant.LikeContain(montantTva));
				select = true;
			}

			BigDecimal montantTtc = refContrat.getMontantTtc();
			if (montantTtc != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " cast(r.montantTtc as text) like "
						+ UtilConstant.LikeContain(montantTtc));
				select = true;
			}

			String objetContratAr = refContrat.getObjetContratAr();
			if (objetContratAr != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " r.objetContratAr like "
						+ UtilConstant.LikeContain(objetContratAr));
				select = true;
			}

			String observation = refContrat.getObservation();
			if ((observation != null) && (!observation.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr + " lower(r.observation) like "
						+ UtilConstant.LikeContain(observation.toLowerCase()));
				select = true;
			}

			String referenceDocumentaire = refContrat
					.getReferenceDocumentaire();
			if ((referenceDocumentaire != null)
					&& (!referenceDocumentaire.trim().isEmpty())) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr
						+ " lower(r.referenceDocumentaire) like "
						+ UtilConstant.LikeContain(referenceDocumentaire
								.toLowerCase()));
				select = true;
				
			}

			Date dateSigantureGestionnaire = refContrat
					.getDateSignatureGestionnaire();
			if (dateSigantureGestionnaire != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				buffer.append(opr
						+ " cast(r.dateSignatureGestionnaire as text) like "
						+ UtilConstant.LikeContain(UtilDate.convertToString(dateSigantureGestionnaire)));
				select = true;
				
			}

			Boolean reconductible = refContrat.getReconductible();
			if (reconductible != null) {
				if (!select) {
					buffer.append("select r from RefContrat r ");
					buffer.append(" where r.refEtablissement.id = " + etabId);
				}
				if (reconductible) {

					buffer.append(opr + " r.reconductible = TRUE");
				} else {
					buffer.append(opr + " r.reconductible = FALSE ");
				}
				select = true;
				
			}
			if (select) {
				if (avenant) {
					buffer.append(" and r.avenant = TRUE");
				} else {
					buffer.append(" and r.avenant = FALSE ");
				}
			}
			String request = new String(buffer);
			if (request.trim().isEmpty()) {
				return null;
			}
			log.debug("request successful " + request);
			Query query = entityManager.createQuery(request);
			List<RefContrat> resultList = (List<RefContrat>) query
					.getResultList();
			log.debug("findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			throw re;
		}
	}

	@Override
	public RefContrat findByCode(String identifiant) {
		if (identifiant == null) {
			return null;
		}
		identifiant = "%" + identifiant.toLowerCase() + "%";
		log.debug("getting List of RefContrat instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefContrat r ");
			request.append("where (lower(r.identifiant)) like :identifiant ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("identifiant", identifiant);
			List<RefContrat> resultList = (List<RefContrat>) query
					.getResultList();
			log.debug("findByCode successful");
			if(resultList != null && !resultList.isEmpty()) {
				return resultList.get(0);
			}
			return null;

		} catch (RuntimeException re) {
			log.error("findByCode failed", re);
			throw re;
		}
	}
}
