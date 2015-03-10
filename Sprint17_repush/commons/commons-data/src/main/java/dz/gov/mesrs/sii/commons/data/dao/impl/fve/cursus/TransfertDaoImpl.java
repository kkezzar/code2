package dz.gov.mesrs.sii.commons.data.dao.impl.fve.cursus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.TransfertDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Transfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneSortanteBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiques;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiquesTypeTransfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertTypeTransfert;

/**
 * Dao object for domain model class TitreAcces.
 * 
 * @see dz.gov.mesrs.sii.TitreAccesDto.business.model.bo.TitreAcces
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
@Repository("transfertDao")
@Transactional
public class TransfertDaoImpl implements TransfertDao {

	private static final Logger log = LoggerFactory.getLogger(TransfertDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	/**
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public Transfert findById(int id) {
		log.debug("getting TitreAcces instance with id: " + id);
		try {
			entityManager.flush();
			entityManager.clear();
			log.debug("get successful");
			return entityManager.find(Transfert.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Transfert> findAll() {
		log.debug("getting all Transfert instances");
		try {
			entityManager.flush();
			entityManager.clear();
			Query query = entityManager.createQuery("from Transfert t");
			log.debug("findAll 'Transfert' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Transfert' failed", re);
			return new ArrayList<Transfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Transfert> findByQuery(String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findByQuery failed", re);
			return new ArrayList<Transfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Transfert> findByRefCodeEtablissement(String refCodeEtab) {

		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM Transfert t WHERE t.refCodeEtabAccueil =:refCodeEtab";

			TypedQuery<Transfert> query = entityManager.createQuery(sqlQuery,
					Transfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findByRefCodeEtablissement failed", re);
			return new ArrayList<Transfert>();
		}
	}

	/**
	 * Recherche par annnee academique et code etablissement
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 12:54:57
	 * @param idAnneeAcademique
	 * @param refCodeEtab
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Transfert> findByIdAnneeAcademiqueRefCodeEtablissement(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM Transfert t WHERE t.refCodeEtabAccueil =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";

			TypedQuery<Transfert> query = entityManager.createQuery(sqlQuery,
					Transfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			
			return query.getResultList();
			

		} catch (RuntimeException re) {
			log.error("findByIdAnneeAcademiqueRefCodeEtablissement failed", re);
			return new ArrayList<Transfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertStatistiques> findStatsByIdAnneeAcademiqueRefCodeEtablissement(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			String sqlQuery = "SELECT t FROM TransfertStatistiques t WHERE t.refCodeEtabAccueil =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";

			TypedQuery<TransfertStatistiques> query = entityManager
					.createQuery(sqlQuery, TransfertStatistiques.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error(
					"findStatsByIdAnneeAcademiqueRefCodeEtablissement failed",
					re);
			return new ArrayList<TransfertStatistiques>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertStatistiquesTypeTransfert> findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert(
			Integer idAnneeAcademique, String refCodeEtab,
			String refCodeTypeTransfert) {
		try {
			entityManager.flush();
			String sqlQuery = "SELECT t FROM TransfertStatistiquesTypeTransfert t WHERE t.refCodeEtabAccueil =:refCodeEtab "
					+ "	AND t.anneeAcademiqueId = :idAnneeAcademique "
					+ " AND t.refCodeTypeTransfert = :refCodeTypeTransfert";

			TypedQuery<TransfertStatistiquesTypeTransfert> query = entityManager
					.createQuery(sqlQuery,
							TransfertStatistiquesTypeTransfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			query.setParameter("refCodeTypeTransfert", refCodeTypeTransfert);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error(
					"findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert failed",
					re);
			return new ArrayList<TransfertStatistiquesTypeTransfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterne> findTransfertInterneByQuery(String query) {
		try {
			entityManager.flush();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findTransfertInterneByQuery failed", re);
			return new ArrayList<TransfertInterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterne> findTransfertInterneByRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			String sqlQuery = "SELECT t FROM TransfertInterne t WHERE t.refCodeEtab =:refCodeEtab";
			TypedQuery<TransfertInterne> query = entityManager.createQuery(
					sqlQuery, TransfertInterne.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertInterneByRefCodeEtab failed", re);
			return new ArrayList<TransfertInterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterne> findTransfertInterneByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertInterne t WHERE t.refCodeEtab =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";
			TypedQuery<TransfertInterne> query = entityManager.createQuery(
					sqlQuery, TransfertInterne.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			query.setHint("org.hibernate.cacheable", Boolean.FALSE);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertInterneByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertInterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterne> findTransfertExterneByQuery(String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findByQuery failed", re);
			return new ArrayList<TransfertExterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterne> findTransfertExterneByRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterne t WHERE t.refCodeEtab =:refCodeEtab";
			TypedQuery<TransfertExterne> query = entityManager.createQuery(
					sqlQuery, TransfertExterne.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneByRefCodeEtab failed", re);
			return new ArrayList<TransfertExterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterne> findTransfertExterneByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterne t WHERE t.refCodeEtab =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";
			TypedQuery<TransfertExterne> query = entityManager.createQuery(
					sqlQuery, TransfertExterne.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertExterne>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterneBachelier> findTransfertInterneBachelierByQuery(
			String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findTransfertInterneBachelierByQuery failed", re);
			return new ArrayList<TransfertInterneBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterneBachelier> findTransfertInterneBachelierByRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertInterneBachelier t WHERE t.refCodeEtab =:refCodeEtab";
			TypedQuery<TransfertInterneBachelier> query = entityManager
					.createQuery(sqlQuery, TransfertInterneBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertInterneBachelierByRefCodeEtab failed", re);
			return new ArrayList<TransfertInterneBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertInterneBachelier> findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertInterneBachelier t WHERE t.refCodeEtab =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";
			TypedQuery<TransfertInterneBachelier> query = entityManager
					.createQuery(sqlQuery, TransfertInterneBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertInterneBachelierByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertInterneBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneBachelier> findTransfertExterneBachelierByQuery(
			String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findTransfertExterneBachelierByQuery failed", re);
			return new ArrayList<TransfertExterneBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneBachelier> findTransfertExterneBachelierByRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterneBachelier t WHERE t.refCodeEtab =:refCodeEtab";
			TypedQuery<TransfertExterneBachelier> query = entityManager
					.createQuery(sqlQuery, TransfertExterneBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneBachelierByRefCodeEtab failed", re);
			return new ArrayList<TransfertExterneBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneBachelier> findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterneBachelier t WHERE t.refCodeEtab =:refCodeEtab AND t.anneeAcademiqueId = :idAnneeAcademique";
			TypedQuery<TransfertExterneBachelier> query = entityManager
					.createQuery(sqlQuery, TransfertExterneBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneBachelierByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertExterneBachelier>();
		}
	}

	// transferts par type transfert

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertTypeTransfert> findTransfertTypeTransfertByQuery(
			String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findTransfertTypeTransfertByQuery failed", re);
			return new ArrayList<TransfertTypeTransfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertTypeTransfert> findTransfertTypeTransfertyRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertTypeTransfert t WHERE t.refCodeEtab =:refCodeEtab";
			TypedQuery<TransfertTypeTransfert> query = entityManager
					.createQuery(sqlQuery, TransfertTypeTransfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertTypeTransfertyRefCodeEtab failed", re);
			return new ArrayList<TransfertTypeTransfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertTypeTransfert> findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertTypeTransfert t WHERE t.refCodeEtab =:refCodeEtab "
					+ "AND t.anneeAcademiqueId = :idAnneeAcademique";
			TypedQuery<TransfertTypeTransfert> query = entityManager
					.createQuery(sqlQuery, TransfertTypeTransfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			
			return query.getResultList();
		

		} catch (RuntimeException re) {
			log.error("findTransfertTypeTransfertByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertTypeTransfert>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertTypeTransfert> findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert(
			Integer idAnneeAcademique, String refCodeEtab,
			String refCodeTypeTransdfert) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertTypeTransfert t WHERE t.refCodeEtab =:refCodeEtab "
					+ "AND t.anneeAcademiqueId = :idAnneeAcademique "
					+ "AND  t.refCodeTypeTransfert = :refCodeTypeTransdfert";

			TypedQuery<TransfertTypeTransfert> query = entityManager
					.createQuery(sqlQuery, TransfertTypeTransfert.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);
			query.setParameter("refCodeTypeTransdfert", refCodeTypeTransdfert);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert failed", re);
			return new ArrayList<TransfertTypeTransfert>();
		}
	}

	// transferts sortantes

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByQuery(
			String query) {
		try {
			entityManager.flush();
			entityManager.clear();
			return entityManager.createQuery(query).getResultList();
		} catch (RuntimeException re) {
			log.error("findTransfertExterneSortanteByQuery failed", re);
			return new ArrayList<TransfertExterneSortanteBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByRefCodeEtab(
			String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterneSortanteBachelier t WHERE t.refCodeEtab =:refCodeEtab";

			TypedQuery<TransfertExterneSortanteBachelier> query = entityManager
					.createQuery(sqlQuery,
							TransfertExterneSortanteBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneSortanteByRefCodeEtab failed", re);
			return new ArrayList<TransfertExterneSortanteBachelier>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(
			Integer idAnneeAcademique, String refCodeEtab) {
		try {
			entityManager.flush();
			entityManager.clear();
			String sqlQuery = "SELECT t FROM TransfertExterneSortanteBachelier t WHERE t.refCodeEtab =:refCodeEtab "
					+ "AND t.anneeAcademiqueId = :idAnneeAcademique";

			TypedQuery<TransfertExterneSortanteBachelier> query = entityManager
					.createQuery(sqlQuery,
							TransfertExterneSortanteBachelier.class);
			query.setParameter("refCodeEtab", refCodeEtab);
			query.setParameter("idAnneeAcademique", idAnneeAcademique);

			return query.getResultList();

		} catch (RuntimeException re) {
			log.error("findTransfertExterneSortanteByIdAnnAcadRefCodeEtab failed", re);
			return new ArrayList<TransfertExterneSortanteBachelier>();

		}
	}
}
