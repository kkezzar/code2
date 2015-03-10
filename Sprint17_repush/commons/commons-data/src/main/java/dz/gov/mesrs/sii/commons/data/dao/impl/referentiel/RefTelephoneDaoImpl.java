package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefTelephoneDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefTelephone;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

@Service ("refTelephoneDaoImpl")
public class RefTelephoneDaoImpl implements RefTelephoneDao {

	private static final Logger log = LoggerFactory.getLogger(RefTelephoneDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTelephoneDao#persist(dz.gov.mesrs.sii.referentiel.business.model.bo.RefTelephone)
	 */
	@Override
	public void persist(RefTelephone transientInstance) {
		log.debug("persisting RefTelephone instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();/* Sofiane MAKERRI : Bug Ticket0000003*/
			entityManager.refresh(transientInstance); /* Sofiane MAKERRI : Bug Ticket0000003*/
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTelephoneDao#remove(dz.gov.mesrs.sii.referentiel.business.model.bo.RefTelephone)
	 */
	@Override
	public void remove(RefTelephone persistentInstance) {
		log.debug("removing RefTelephone instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTelephoneDao#merge(dz.gov.mesrs.sii.referentiel.business.model.bo.RefTelephone)
	 */
	@Override
	public RefTelephone merge(RefTelephone detachedInstance) {
		log.debug("merging RefTelephone instance");
		try {
			RefTelephone result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.jpa.impl.RefTelephoneDao#findById(int)
	 */
	@Override
	public RefTelephone findById(int id) {
		log.debug("getting RefTelephone instance with id: " + id);
		try {
			RefTelephone instance = entityManager.find(RefTelephone.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	

	@Override
	@SuppressWarnings("unchecked")
	public RefTelephone findPrincipalTelephoneForIndividu(String identifiant) {
		log.debug("getting List of RefCoordonnee for individu instance with : " + identifiant);
		try {
			
			StringBuilder request = new StringBuilder(
					"SELECT r FROM  RefTelephone r ");
			request.append("WHERE r.refCoordonnee.refIndividu.identifiant = " + UtilConstant.quotedString(identifiant));
			request.append("AND r.refCoordonnee.listeRouge = FALSE ");
			request.append("AND r.refCoordonnee.principal = TRUE ");
				
			Query query = entityManager.createQuery(new String(request));
		
			List<RefTelephone> resultList = (List<RefTelephone>) query.getResultList();
			
			if(resultList!=null && resultList.size()>0){
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
	public RefTelephone findPrincipalTelephoneForIndividu(String typeTel,
			String natureTel, Integer idIndividu) {
		log.debug("getting List of RefCoordonnee for individu instance with : " + idIndividu);
		try {
			
			StringBuilder request = new StringBuilder(
					"SELECT r FROM  RefTelephone r ");
			request.append("WHERE r.refCoordonnee.refIndividu.id = " + idIndividu);
			request.append("AND r.refCoordonnee.principal = TRUE ");
			if (typeTel != null) {
				request.append("AND lower(r.nomenclatureByTypeTelephone.libelleLongFr) = "
						+ UtilConstant.quotedString(typeTel.toLowerCase()));
			}
			if (natureTel != null) {
				request.append("AND lower(r.nomenclatureByNatureTelephone.libelleLongFr) = "
						+ UtilConstant.quotedString(natureTel.toLowerCase()));
			}
			Query query = entityManager.createQuery(new String(request));
		
			List<RefTelephone> resultList = (List<RefTelephone>) query.getResultList();
			
			if(resultList!=null && resultList.size()>0){
				return resultList.get(0);
			}
			
			return null;

		} catch (RuntimeException re) {
			log.error("findPrincipalAdresseForIndividu failed", re);
			throw re;
		}
	}

	
}
