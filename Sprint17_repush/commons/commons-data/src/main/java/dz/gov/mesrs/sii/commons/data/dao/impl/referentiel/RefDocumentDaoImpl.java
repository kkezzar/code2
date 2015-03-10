package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefDocumentDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDocument;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * 
 * @author yselmane on : 9 avr. 2014 10:59:54
 */
@Repository("refDocumentDao")
@Transactional
public class RefDocumentDaoImpl implements RefDocumentDao {

	private static final Logger log = LoggerFactory.getLogger(RefDocumentDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefDocumentDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefDocument)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefDocument transientInstance) {
		log.debug("persisting RefDocument instance");
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

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefDocumentDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefDocument)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefDocument persistentInstance) {
		log.debug("removing RefDocument instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefDocumentDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefDocument)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefDocument merge(RefDocument detachedInstance) {
		log.debug("merging RefDocument instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}

	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.NRefDocumentDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefDocument findById(int id) {
		log.debug("getting RefDocument instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefDocument.class, id);
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
	public List<RefDocument> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefDocument> findAll() {
		log.debug("getting all RefDocument instances");
		try {
			Query query = entityManager
					.createQuery("from RefDocument refDocument");
			log.debug("findAll 'RefDocument' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefDocument' failed", re);
			return new ArrayList<RefDocument>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefDocument> findGeneric(String value) {

		log.debug("getting List of RefDocument instance with : " + value);
		List<RefDocument> resultList = new ArrayList<RefDocument>();
		try {

			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefDocument r ")
					.append(" LEFT JOIN r.nomenclatureByTypeDocument as ncTypeDoc ")
					.append(" LEFT JOIN r.nomenclatureByNatureDocument as ncNatureDoc")
					.append(" LEFT JOIN r.nomenclatureByClassement as ncClassement")
					.append(" LEFT JOIN r.nomenclatureByLangue as ncLangue ");

			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value.trim() + "%";
				value = value.toUpperCase();

				request.append("WHERE upper(r.objetDocument) like :value ");
				request.append("OR upper(r.description) like :value ");
				request.append("OR upper(r.titreDocument) like :value ");
				request.append("OR upper(r.referenceDocument) like :value ");
				request.append("OR cast(r.dateDocument as text) like :value ");
				request.append("OR cast(r.dateCreation as text) like :value ");

				request.append(" OR upper(ncTypeDoc.libelleLongFr) like :value ");
				request.append(" OR upper(ncNatureDoc.libelleLongFr) like :value ");
				request.append(" OR upper(ncClassement.libelleLongFr) like :value ");
				request.append(" OR upper(ncLangue.libelleLongFr) like :value ");

				request.append(" ORDER BY r.titreDocument"); // orderby

				Query query = entityManager.createQuery(request.toString());
				query.setParameter("value", value);
				resultList = (List<RefDocument>) query.getResultList();
			}

			// le cas où le texte saisi est vide
			/*
			 * else { request.append(" ORDER BY r.titreDocument"); // orderby
			 * Query query = entityManager.createQuery(request.toString());
			 * resultList = (List<RefDocument>) query.getResultList(); }
			 */

			log.debug("findGeneric successful");

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefDocument> findAdvanced(RefDocument refDocument,
			List<Nomenclature> nomenclatureList) {

		if (refDocument == null
				&& (nomenclatureList == null || nomenclatureList.isEmpty())) {
			return null;
		}
		String firstOpr = "WHERE";

		StringBuilder request = new StringBuilder(
				"SELECT DISTINCT  r FROM RefDocument r ");

		if (nomenclatureList != null && !nomenclatureList.isEmpty()) {

			request.append(", RefMotCle mc ");
			request.append(firstOpr);
			request.append(" r = mc.refDocument AND mc.nomenclature in (:nomenclatureList) ");
			firstOpr = " OR";
			System.err.println("Find advanced with MOTS CLES....");
		}

		String identifantDoc = refDocument.getIdentifiant();

		if ((identifantDoc != null) && (!identifantDoc.trim().isEmpty())) {
			identifantDoc = identifantDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.identifiant) like "
					+ UtilConstant.LikeContain(identifantDoc));
			firstOpr = " OR";
		}

		String titreDoc = refDocument.getTitreDocument();

		if ((titreDoc != null) && (!titreDoc.trim().isEmpty())) {
			titreDoc = titreDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.titreDocument) like "
					+ UtilConstant.LikeContain(titreDoc));
			firstOpr = " OR";
		}

		String refDoc = refDocument.getReferenceDocument();

		if ((refDoc != null) && (!refDoc.trim().isEmpty())) {
			refDoc = refDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.referenceDocument) like "
					+ UtilConstant.LikeContain(refDoc));
			firstOpr = " OR";
		}

		String nomEntite = refDocument.getNomEntite();

		if ((nomEntite != null) && (!nomEntite.trim().isEmpty())) {
			nomEntite = nomEntite.trim().toUpperCase();
			request.append(firstOpr + " upper(r.nomEntite) = "
					+ UtilConstant.quotedString(nomEntite));
			firstOpr = " AND";
		}

		// String referenceDoc = refDocument.getReferenceDocument();
		// if ((referenceDoc != null) && (!referenceDoc.trim().isEmpty())) {
		// referenceDoc = referenceDoc.trim().toUpperCase();
		// request.append(firstOpr + " upper(r.referenceDocument) like "
		// + UtilConstant.LikeContain(referenceDoc));
		// firstOpr = " AND";
		// }

		String objetDoc = refDocument.getObjetDocument();
		if ((objetDoc != null) && (!objetDoc.trim().isEmpty())) {
			objetDoc = objetDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.objetDocument) like "
					+ UtilConstant.LikeContain(objetDoc));
			firstOpr = " AND";
		}

		// Recherche by nomenclature
		if (refDocument.getNomenclatureByTypeDocument() != null
				&& refDocument.getNomenclatureByTypeDocument().getId() > 0) {
			int typeDocumentId = refDocument.getNomenclatureByTypeDocument()
					.getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByTypeDocument.id = ")
					.append(typeDocumentId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByLangue() != null
				&& refDocument.getNomenclatureByLangue().getId() > 0) {
			int langueId = refDocument.getNomenclatureByLangue().getId();
			request.append(firstOpr).append(" r.nomenclatureByLangue.id = ")
					.append(langueId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByNatureDocument() != null
				&& refDocument.getNomenclatureByNatureDocument().getId() > 0) {
			int natureDocumentId = refDocument
					.getNomenclatureByNatureDocument().getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByNatureDocument.id = ")
					.append(natureDocumentId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByClassement() != null
				&& refDocument.getNomenclatureByClassement().getId() > 0) {
			int classementId = refDocument.getNomenclatureByClassement()
					.getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByClassement.id = ")
					.append(classementId);
			firstOpr = " AND";
		}

		request.append(" ORDER BY r.titreDocument"); // tri du resultat de la
														// requete

		log.debug("findAdvanced: getting List of RefDocument instance : ");

		List<RefDocument> resultList;
		try {

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(request.toString());

			if (nomenclatureList != null && !nomenclatureList.isEmpty()) {
				query.setParameter("nomenclatureList", nomenclatureList);
			}

			resultList = (List<RefDocument>) query.getResultList();
			log.debug("findAdvanced successful");

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			return new ArrayList<RefDocument>();
		}
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean findByRefDocument(RefDocument refDocument) {

		if (refDocument == null) {
			return false;
		}

		log.debug("findByRefDocument  titre doc = "
				+ refDocument.getTitreDocument() + "reference doc ="
				+ refDocument.getReferenceDocument());
		String firstOpr = "WHERE";

		try {
			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefDocument r ");

			// String titreDoc = refDocument.getTitreDocument();
			// if ((titreDoc != null) && (!titreDoc.trim().isEmpty())) {
			// titreDoc = UtilConstant.quotedString(titreDoc.trim()
			// .toLowerCase());
			// request.append(firstOpr).append(" lower(r.titreDocument) = ")
			// .append(titreDoc);
			// firstOpr = "OR";
			// }

			String referenceDoc = refDocument.getReferenceDocument();
			if ((referenceDoc != null) && (!referenceDoc.trim().isEmpty())) {
				referenceDoc = UtilConstant.quotedString(referenceDoc.trim()
						.toLowerCase());
				request.append(firstOpr)
						.append(" lower(r.referenceDocument) = ")
						.append(referenceDoc);
				firstOpr = "OR";
			}

			log.debug("request successful " + request.toString());
			Query query = entityManager.createQuery(request.toString());
			List<RefDocument> resultList = (List<RefDocument>) query
					.getResultList();
			if (resultList != null && resultList.size() > 0) {
				return true;
			}
			log.debug("findByRefDocument successful");
			return false;

		} catch (RuntimeException re) {
			log.error("findByRefDocument failed", re);

			throw re;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefDocument> findDocumentsOfEntity(String entity,
			String idEntity) {
		log.info("getting findDocumentsOfEntity : " + entity + " idEntity:"
				+ idEntity);

		try {
			Query query = entityManager
					.createQuery("SELECT r FROM RefDocument r WHERE r.identifiantEntite =:idEntite AND r.nomEntite =:nomEntite ORDER BY r.titreDocument");
			// String entityName = entity.getSimpleName();
			query.setParameter("nomEntite", entity).setParameter("idEntite",
					idEntity);
			List<RefDocument> resultList = (List<RefDocument>) query
					.getResultList();
			log.debug("findDocumentsOfEntity successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findDocumentsOfEntity failed", re);
			return new ArrayList<RefDocument>();
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RefDocument> findFilsOfDocument(RefDocument refDocument) {

		log.info("getting findFilsOfDocuments = : ");

		if (refDocument == null) {
			return null;
		}

		try {

			Query query = entityManager
					.createQuery("SELECT r FROM RefDocument r  WHERE r.refDocument =:refDocument ORDER BY r.titreDocument");

			query.setParameter("refDocument", refDocument);

			List<RefDocument> resultList = (List<RefDocument>) query
					.getResultList();

			log.debug("findFilsOfDocuments successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("findFilsOfDocuments failed", re);
			return new ArrayList<RefDocument>();
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public RefDocument findDocumentByCode(String code) {

		log.info("getting findDocumentsByCode: " + code);

		try {
			Query query = entityManager
					.createQuery("SELECT r FROM RefDocument r WHERE r.identifiant = :code");
			// String entityName = entity.getSimpleName();
			query.setParameter("code", code);
			query.setMaxResults(1);

			List<RefDocument> resultList = (List<RefDocument>) query
					.getResultList();

			if (resultList != null && !resultList.isEmpty()) {
				System.err.println("trouvé documlent avec code----" + code);
				return resultList.get(0);
			} else
				return null;

		} catch (RuntimeException re) {
			log.error("findDocumentByCode failed", re);
			return null;
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public RefDocument findMaxDocumentByType(RefDocument refDocument) {
		log.info("getting findMaxDocumentByType : typeDocument ");

		try {

			int typeDocumentId = refDocument.getNomenclatureByTypeDocument()
					.getId();
			Calendar cal = Calendar.getInstance();
			String year = cal.get(Calendar.YEAR) % 100 + ""; // 2 chiffres de
																// l'année

			String identifiant = refDocument.getIdentifiant();

			String req = "SELECT r FROM RefDocument r WHERE r.nomenclatureByTypeDocument.id = :typeDocumentId "
					+ " AND SUBSTRING(r.identifiant,4,2)   = :year ";

			if (identifiant != null) {
				req += " AND SUBSTRING (r.identifiant,0,5) <> SUBSTRING(:identifiant,0,5) ";
			}

			req += " ORDER BY r.identifiant DESC ";

			Query query = entityManager.createQuery(req);

			query.setParameter("typeDocumentId", typeDocumentId);
			query.setParameter("year", year);

			if (identifiant != null)
				query.setParameter("identifiant", identifiant);

			query.setMaxResults(1);

			List<RefDocument> result = (List<RefDocument>) query
					.getResultList();

			if (result != null && !result.isEmpty()) {
				log.debug("findMaxDocumentByType successful");
				String ident = result.get(0).getIdentifiant();
				System.err.println("identif maxxxxxxxxxxxxxxxx   " + ident);
				return result.get(0);
			} else
				return null;

		} catch (Exception re) {
			log.error("findMaxDocumentByType failed", re);
			return null;
		}

	}

	@Override
	public List<RefDocument> findGeneric(String etabCode, String value) {
		log.debug("getting List of RefDocument instance with : " + value);
		List<RefDocument> resultList = new ArrayList<RefDocument>();
		try {

			StringBuilder request = new StringBuilder(
					"SELECT r FROM RefDocument r ")
					.append(" LEFT JOIN r.nomenclatureByTypeDocument as ncTypeDoc ")
					.append(" LEFT JOIN r.nomenclatureByNatureDocument as ncNatureDoc")
					.append(" LEFT JOIN r.nomenclatureByClassement as ncClassement")
					.append(" LEFT JOIN r.nomenclatureByLangue as ncLangue ");

			if (value != null && !value.trim().isEmpty()) {
				value = "%" + value.trim() + "%";
				value = value.toUpperCase();
				request.append(" where (1=1)");
				if (etabCode != null) {
					request.append(" and upper(r.refEtablissement.identifiant) = "
							+ UtilConstant.quotedString(etabCode.toUpperCase()));
				}

				request.append(" AND (upper(r.objetDocument) like :value ");
				request.append("OR upper(r.description) like :value ");
				request.append("OR upper(r.titreDocument) like :value ");
				request.append("OR upper(r.referenceDocument) like :value ");
				request.append("OR cast(r.dateDocument as text) like :value ");
				request.append("OR cast(r.dateCreation as text) like :value ");

				request.append(" OR upper(ncTypeDoc.libelleLongFr) like :value ");
				request.append(" OR upper(ncNatureDoc.libelleLongFr) like :value ");
				request.append(" OR upper(ncClassement.libelleLongFr) like :value ");
				request.append(" OR upper(ncLangue.libelleLongFr) like :value) ");

				request.append(" ORDER BY r.titreDocument"); // orderby

				Query query = entityManager.createQuery(request.toString());
				query.setParameter("value", value);
				resultList = (List<RefDocument>) query.getResultList();
			}

			// le cas où le texte saisi est vide
			/*
			 * else {
			 * request.append(" where r.refEtablissement.lcEtablissementLatin = "
			 * + UtilConstant.quotedString(etabLcLatin));
			 * request.append(" ORDER BY r.titreDocument"); // orderby Query
			 * query = entityManager.createQuery(request.toString()); resultList
			 * = (List<RefDocument>) query.getResultList(); }
			 */

			log.debug("findGeneric successful");

		} catch (RuntimeException re) {
			log.error("findGeneric failed", re);
			throw re;
		}
		return resultList;
	}

	@Override
	public List<RefDocument> findAdvanced(String etabCode,
			RefDocument refDocument, List<Nomenclature> nomenclatureList) {

		if (refDocument == null
				&& (nomenclatureList == null || nomenclatureList.isEmpty())) {
			return null;
		}
		String firstOpr = "WHERE";

		StringBuilder request = new StringBuilder(
				"SELECT DISTINCT  r FROM RefDocument r ");

		if (nomenclatureList != null && !nomenclatureList.isEmpty()) {

			request.append(", RefMotCle mc ");
			request.append(firstOpr);
			request.append(" r = mc.refDocument AND mc.nomenclature in (:nomenclatureList) ");
			firstOpr = " AND";
			System.err.println("Find advanced with MOTS CLES....");
		}

		String identifiant = refDocument.getIdentifiant();

		if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
			identifiant = identifiant.trim().toUpperCase();
			request.append(firstOpr + " upper(r.identifiant) like "
					+ UtilConstant.LikeContain(identifiant));
			firstOpr = " OR";
		}

		String titreDoc = refDocument.getTitreDocument();

		if ((titreDoc != null) && (!titreDoc.trim().isEmpty())) {
			titreDoc = titreDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.titreDocument) like "
					+ UtilConstant.LikeContain(titreDoc));
			firstOpr = " OR";
		}

		String refDoc = refDocument.getReferenceDocument();

		if ((refDoc != null) && (!refDoc.trim().isEmpty())) {
			refDoc = refDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.referenceDocument) like "
					+ UtilConstant.LikeContain(refDoc));
			firstOpr = " AND";
		}

		String nomEntite = refDocument.getNomEntite();

		if ((nomEntite != null) && (!nomEntite.trim().isEmpty())) {
			nomEntite = nomEntite.trim().toUpperCase();
			request.append(firstOpr + " upper(r.nomEntite) = "
					+ UtilConstant.quotedString(nomEntite));
			firstOpr = " AND";
		}

		// String referenceDoc = refDocument.getReferenceDocument();
		// if ((referenceDoc != null) && (!referenceDoc.trim().isEmpty())) {
		// referenceDoc = referenceDoc.trim().toUpperCase();
		// request.append(firstOpr + " upper(r.referenceDocument) like "
		// + UtilConstant.LikeContain(referenceDoc));
		// firstOpr = " AND";
		// }

		String objetDoc = refDocument.getObjetDocument();
		if ((objetDoc != null) && (!objetDoc.trim().isEmpty())) {
			objetDoc = objetDoc.trim().toUpperCase();
			request.append(firstOpr + " upper(r.objetDocument) like "
					+ UtilConstant.LikeContain(objetDoc));
			firstOpr = " AND";
		}

		// Recherche by nomenclature
		if (refDocument.getNomenclatureByTypeDocument() != null
				&& refDocument.getNomenclatureByTypeDocument().getId() > 0) {
			int typeDocumentId = refDocument.getNomenclatureByTypeDocument()
					.getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByTypeDocument.id = ")
					.append(typeDocumentId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByLangue() != null
				&& refDocument.getNomenclatureByLangue().getId() > 0) {
			int langueId = refDocument.getNomenclatureByLangue().getId();
			request.append(firstOpr).append(" r.nomenclatureByLangue.id = ")
					.append(langueId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByNatureDocument() != null
				&& refDocument.getNomenclatureByNatureDocument().getId() > 0) {
			int natureDocumentId = refDocument
					.getNomenclatureByNatureDocument().getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByNatureDocument.id = ")
					.append(natureDocumentId);
			firstOpr = " AND";
		}

		if (refDocument.getNomenclatureByClassement() != null
				&& refDocument.getNomenclatureByClassement().getId() > 0) {
			int classementId = refDocument.getNomenclatureByClassement()
					.getId();
			request.append(firstOpr)
					.append(" r.nomenclatureByClassement.id = ")
					.append(classementId);
			firstOpr = " AND";
		}

		request.append(firstOpr).append(
				" r.refEtablissement.identifiant = "
						+ UtilConstant.quotedString(etabCode));

		request.append(" ORDER BY r.titreDocument"); // tri du resultat de la
														// requete

		log.debug("findAdvanced: getting List of RefDocument instance : ");

		List<RefDocument> resultList;
		try {

			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(request.toString());

			if (nomenclatureList != null && !nomenclatureList.isEmpty()) {
				query.setParameter("nomenclatureList", nomenclatureList);
			}

			resultList = (List<RefDocument>) query.getResultList();
			log.debug("findAdvanced successful");

		} catch (RuntimeException re) {
			log.error("findAdvanced failed", re);
			System.out.println("findAdvanced failed");
			return new ArrayList<RefDocument>();
		}
		return resultList;
	}

	@Override
	public List<RefDocument> findAll(String etabCode) {
		log.debug("getting all RefDocument instances");
		try {
			Query query = entityManager.createQuery("from RefDocument r "
					+ "where r.refEtablissement.identifiant = "
					+ UtilConstant.quotedString(etabCode));
			log.debug("findAll 'RefDocument' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefDocument' failed", re);
			return new ArrayList<RefDocument>();
		}
	}

}
