package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefAdresseElectroniqueDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefAdresseElectronique;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Service("refAdresseElectroniqueDaoImpl")
public class RefAdresseElectroniqueDaoImpl implements RefAdresseElectroniqueDao {

	private static final Logger log = LoggerFactory.getLogger(RefAdresseElectroniqueDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.
	 * RefAdresseElectroniqueDao
	 * #persist(dz.gov.mesrs.sii.referentiel.business.model
	 * .bo.RefAdresseElectronique)
	 */
	@Override
	public void persist(RefAdresseElectronique transientInstance) {
		log.debug("persisting RefAdresseElectronique instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();/* Sofiane MAKERRI : Bug Ticket0000003 */
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.
	 * RefAdresseElectroniqueDao
	 * #remove(dz.gov.mesrs.sii.referentiel.business.model
	 * .bo.RefAdresseElectronique)
	 */
	@Override
	public void remove(RefAdresseElectronique persistentInstance) {
		log.debug("removing RefAdresseElectronique instance");
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.
	 * RefAdresseElectroniqueDao
	 * #merge(dz.gov.mesrs.sii.referentiel.business.model
	 * .bo.RefAdresseElectronique)
	 */
	@Override
	public RefAdresseElectronique merge(RefAdresseElectronique detachedInstance) {
		log.debug("merging RefAdresseElectronique instance");
		try {
			RefAdresseElectronique result = entityManager
					.merge(detachedInstance);
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
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.
	 * RefAdresseElectroniqueDao#findById(int)
	 */
	@Override
	public RefAdresseElectronique findById(int id) {
		log.debug("getting RefAdresseElectronique instance with id: " + id);
		try {
			RefAdresseElectronique instance = entityManager.find(
					RefAdresseElectronique.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public RefAdresseElectronique findPrincipalAdresseElectroniqueForIndividu(
			String typeAdrElectro, String natureAdrElectro, Integer idIndividu) {
		log.debug("findPrincipalAdresseForIndividu  with : "
				+ idIndividu);
		try {

			StringBuilder request = new StringBuilder(
					"SELECT r FROM  RefAdresseElectronique r ");
			request.append("WHERE r.refCoordonnee.refIndividu.id = "
					+ idIndividu);
			request.append("AND r.refCoordonnee.principal = TRUE ");
			if (typeAdrElectro != null) {
				request.append("AND lower(r.nomenclatureByTypeAdresseElectronique.libelleLongFr) = "
						+ UtilConstant.quotedString(typeAdrElectro
								.toLowerCase()));
			}
			if (natureAdrElectro != null) {
				request.append("AND lower(r.nomenclatureByNatureAdresseElectronique.libelleLongFr) = "
						+ UtilConstant.quotedString(natureAdrElectro
								.toLowerCase()));
			}
			Query query = entityManager.createQuery(new String(request));

			List<RefAdresseElectronique> resultList = (List<RefAdresseElectronique>) query
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
