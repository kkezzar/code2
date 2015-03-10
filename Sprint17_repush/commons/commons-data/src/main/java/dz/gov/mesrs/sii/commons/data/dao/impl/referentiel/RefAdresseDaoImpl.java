package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAdresseDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresse;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Service("refAdresseDaoImpl")
public class RefAdresseDaoImpl implements RefAdresseDao {

	private static final Logger log = LoggerFactory.getLogger(RefAdresseDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAdresseDao
	 * #persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefAdresse)
	 */
	@Override
	public void persist(RefAdresse transientInstance) {
		log.debug("persisting RefAdresse instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush(); /* Sofiane MAKERRI : Bug Ticket0000003 */
			entityManager.refresh(transientInstance); /*
													 * Sofiane MAKERRI : Bug
													 * Ticket0000003
													 */
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAdresseDao
	 * #remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefAdresse)
	 */
	@Override
	public void remove(RefAdresse persistentInstance) {
		log.debug("removing RefAdresse instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAdresseDao
	 * #merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefAdresse)
	 */
	@Override
	public RefAdresse merge(RefAdresse detachedInstance) {
		log.debug("merging RefAdresse instance");
		try {
			RefAdresse result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefAdresseDao
	 * #findById(int)
	 */
	@Override
	public RefAdresse findById(int id) {
		log.debug("getting RefAdresse instance with id: " + id);
		try {
			RefAdresse instance = entityManager.find(RefAdresse.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public RefAdresse findPrincipalAdresseForIndividu(String identifiant) {
		log.debug("getting List of RefCoordonnee for individu instance with : "
				+ identifiant);
		try {

			StringBuilder request = new StringBuilder(
					"SELECT r FROM  RefAdresse r ");
			request.append("WHERE r.refCoordonnee.refIndividu.identifiant = "
					+ UtilConstant.quotedString(identifiant));
			request.append("AND r.refCoordonnee.listeRouge = FALSE ");
			request.append("AND r.refCoordonnee.principal = TRUE ");

			Query query = entityManager.createQuery(new String(request));

			List<RefAdresse> resultList = (List<RefAdresse>) query
					.getResultList();

			if (resultList != null && resultList.size() > 0) {
				return resultList.get(0);
			}

			return null;

		} catch (RuntimeException re) {
			log.error("findPrincipalAdresseForIndividu failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public RefAdresse findPrincipalAdresseForIndividu(String typeAdresse,
			Integer idIndividu) {
		log.debug("getting List of RefCoordonnee for individu instance with : "
				+ idIndividu);
		try {
			if (idIndividu == null) {
				return null;
			}
			StringBuilder request = new StringBuilder(
					"SELECT r FROM  RefAdresse r ");
			request.append("WHERE r.refCoordonnee.refIndividu.id = "
					+ idIndividu);
			request.append("AND r.refCoordonnee.principal = TRUE ");
			if (typeAdresse != null) {
				request.append("AND lower(r.nomenclatureByTypeAdresse.libelleLongFr) = "
						+ UtilConstant.quotedString(typeAdresse.toLowerCase()));
			}
			Query query = entityManager.createQuery(new String(request));
			List<RefAdresse> resultList = (List<RefAdresse>) query
					.getResultList();

			if (resultList != null && resultList.size() > 0) {
				return resultList.get(0);
			}

			return null;

		} catch (RuntimeException re) {
			log.error("findPrincipalAdresseForIndividu failed", re);
			throw re;
		}
	}
}
