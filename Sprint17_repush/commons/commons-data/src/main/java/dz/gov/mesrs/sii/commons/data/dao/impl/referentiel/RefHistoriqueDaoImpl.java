package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefHistoriqueDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefHistorique;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefHistorique;
 * 
 * @see dz.gov.mesrs.sii.referentiel.business.model.bo.nc.RefHistorique;
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Repository("refHistoriqueDaoImpl")
@Transactional
public class RefHistoriqueDaoImpl implements RefHistoriqueDao {
	private static final Logger log = LoggerFactory.getLogger(RefHistoriqueDaoImpl.class.getName());

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.nc.RefHistoriqueDao
	 *      ;#persist(dz.gov.mesrs.sii.lmd.business.model.bo.nc.RefHistorique)
	 * 
	 * @param transientInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefHistorique transientInstance) {
		log.debug("persisting RefHistorique instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.nc.RefHistoriqueDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.nc.RefHistorique)
	 * @param persistentInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefHistorique persistentInstance) {
		log.debug("removing RefHistorique instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.nc.RefHistoriqueDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.nc.RefHistorique)
	 * @param detachedInstance
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefHistorique merge(RefHistorique detachedInstance) {
		log.debug("merging RefHistorique instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.nc.RefHistoriqueDao#findById(int)
	 * @param id
	 */
	@Override
	@Transactional(readOnly = true)
	public RefHistorique findById(int id) {
		log.debug("getting RefHistorique instance with id: " + id);
		try {
			log.debug("get successful");
			return entityManager.find(RefHistorique.class, id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<RefHistorique> findAll() {
		log.debug("getting all RefHistorique instances");
		try {
			Query query = entityManager
					.createQuery("from RefHistorique refHistorique");
			log.debug("findAll 'RefHistorique' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefHistorique' failed", re);
			return new ArrayList<RefHistorique>();
		}
	}

	@Override
	public List<RefHistorique> findAdvanced(RefHistorique refHistorique) {
		log.debug("findAdvanced instances");
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefHistorique r ");
			request.append(" where (1=1)");
			if (refHistorique.getDateAction() != null) {
				request.append(" and r.dateAction = " + "'"
						+ refHistorique.getDateAction() + "'");
			}
			if (refHistorique.getNomEntite() != null) {
				request.append(" and lower(r.nomEntite) = " + "'"
						+ refHistorique.getNomEntite().toLowerCase() + "'");
			}
			if (refHistorique.getRefCompte() != null
					&& refHistorique.getRefCompte().getId() != 0) {
				request.append(" and r.refCompte.id = "
						+ refHistorique.getRefCompte().getId());
			}
			
			if (refHistorique.getRefFonction() != null
					&& refHistorique.getRefFonction().getId() != 0) {
				request.append(" and r.refFonction.id = "
						+ refHistorique.getRefFonction().getId());
			}
			
			if (refHistorique.getRefCompte() != null
					&& refHistorique.getRefCompte().getRefIndividu() != null
					&& refHistorique.getRefCompte().getRefIndividu().getId() != 0) {
				request.append(" and r.refCompte.refIndividu.id = "
						+ refHistorique.getRefCompte().getRefIndividu().getId());
			}
			
			if (refHistorique.getRefEtablissement() != null
					&& refHistorique.getRefEtablissement().getId() != 0) {
				request.append(" and r.refEtablissement.id = "
						+ refHistorique.getRefEtablissement().getId());
			}
			
			if (refHistorique.getTypeAction() != null) {
				request.append(" and r.typeAction = "
						+ refHistorique.getTypeAction());
			}
			
			TypedQuery<RefHistorique> query = entityManager.createQuery(
					new String(request), RefHistorique.class);
			List<RefHistorique> resultList = (List<RefHistorique>) query
					.getResultList();
			log.debug("findAdvanced 'RefHistorique' successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findAdvanced 'RefHistorique' failed", re);
			return null;
		}
	}

	@Override
	public List<RefHistorique> findByPeriode(RefHistorique refHistorique,
			Date dateDebut, Date dateFin) {
		log.debug("findAdvanced instances");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date _dateDebut = null;
			Date _dateFin = null;
			if (dateDebut != null) {
				String dateStr = sdf.format(dateDebut);
				_dateDebut = sdf.parse(dateStr);
			}
			if (dateFin != null) {
				String dateStr = sdf.format(dateFin);
				_dateFin = sdf.parse(dateStr);
			}

			StringBuilder request = new StringBuilder(
					"select r from RefHistorique r ");
			request.append(" where (1=1)");

			if (refHistorique.getNomEntite() != null) {
				request.append(" and lower(r.nomEntite) = "
						+ UtilConstant.quotedString(refHistorique
								.getNomEntite().toLowerCase()));

			}
			if (refHistorique.getRefCompte() != null
					&& refHistorique.getRefCompte().getId() != 0) {
				request.append(" and r.refCompte.id = "
						+ refHistorique.getRefCompte().getId());
			}
			if (refHistorique.getRefFonction() != null
					&& refHistorique.getRefFonction().getId() != 0) {
				request.append(" and r.refFonction.id = "
						+ refHistorique.getRefFonction().getId());
			}
			if (refHistorique.getRefCompte() != null
					&& refHistorique.getRefCompte().getRefIndividu() != null
					&& refHistorique.getRefCompte().getRefIndividu().getId() != 0) {
				request.append(" and r.refCompte.refIndividu.id = "
						+ refHistorique.getRefCompte().getRefIndividu().getId());
			}
			if (refHistorique.getRefEtablissement() != null
					&& refHistorique.getRefEtablissement().getId() != 0) {
				request.append(" and r.refEtablissement.id = "
						+ refHistorique.getRefEtablissement().getId());
			}
			if (_dateDebut != null) {
				request.append(" and r.dateAction >= "
						+ UtilConstant.quotedString(_dateDebut));
			}

			if (_dateFin != null) {
				request.append(" and r.dateAction <= "
						+ UtilConstant.quotedString(_dateFin));
			}
			
			if (refHistorique.getTypeAction() != null) {
				request.append(" and r.typeAction = "
						+ refHistorique.getTypeAction());
			}
			
			TypedQuery<RefHistorique> query = entityManager.createQuery(
					new String(request), RefHistorique.class);
			List<RefHistorique> resultList = (List<RefHistorique>) query
					.getResultList();
			log.debug("findAdvanced 'RefHistorique' successful");
			return resultList;
		} catch (ParseException re) {
			log.error("findAdvanced 'RefHistorique' failed", re);
			return null;
		} catch (RuntimeException re) {
			log.error("findAdvanced 'RefHistorique' failed", re);
			return null;
		}
	}
}
