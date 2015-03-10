package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefEtablissementDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class RefEtablissement.
 * @see dz.gov.mesrs.sii.scolformation.business.model.bo.RefEtablissement
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
@Repository ("refEtablissementDaoImpl")  
public class RefEtablissementDaoImpl implements RefEtablissementDao {

	private static final Logger log = LoggerFactory.getLogger(RefEtablissementDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEtablissementDao#persist(dz.gov.mesrs.sii.lmd.business.model.bo.RefEtablissement)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(RefEtablissement transientInstance) {
		log.debug("persisting RefEtablissement instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEtablissementDao#remove(dz.gov.mesrs.sii.lmd.business.model.bo.RefEtablissement)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(RefEtablissement persistentInstance) {
		log.debug("removing RefEtablissement instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.RefEtablissementDao#merge(dz.gov.mesrs.sii.lmd.business.model.bo.RefEtablissement)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RefEtablissement merge(RefEtablissement detachedInstance) {
		log.debug("merging RefEtablissement instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NRefEtablissementDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public RefEtablissement findById(int id) {
		log.debug("getting RefEtablissement instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(RefEtablissement.class, id);
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
	public List<RefEtablissement> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<RefEtablissement> findAll() {
		log.debug("getting all RefEtablissement instances");
		try {
			Query query = entityManager.createQuery("from RefEtablissement refEtablissement");
			log.debug("findAll 'RefEtablissement' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'RefEtablissement' failed", re);
			return new ArrayList<RefEtablissement>();
		}
	}



	@Override
	public List<RefEtablissement> findGeneric(String value) {
		if(value == null) {
			return null;
		}
		value = "%" + value.toLowerCase() + "%";
		log.debug("getting List of RefEtablissement instance with : " + value);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.identifiant) like :value ");
			request.append("or lower(r.lcEtablissementArabe) like :value ");
			request.append("or lower(r.lcEtablissementLatin) like :value ");
			request.append("or lower(r.llEtablissementArabe) like :value ");
			request.append("or lower(r.llEtablissementLatin) like :value ");
			request.append("or lower(r.nif) like :value ");
			request.append("or lower(r.nis) like :value ");
			request.append("or lower(r.nrc) like :value ");
			request.append("or lower(r.nss) like :value ");
			request.append("or lower(r.decret) like :value ");
			request.append("or cast(r.dateCreation as text) like :value ");
			Query query = entityManager.createQuery(new String(request));
			query.setParameter("value", value);
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			log.debug("RefEtablissement findGeneric successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefEtablissement findGeneric failed", re);
			throw re;
		}
	}



	@Override
	public List<RefEtablissement> findAdvanced(RefEtablissement refEtablissement) {
		log.debug("findAdvanced: getting List of RefEtablissement instance : ");
		if (refEtablissement == null) {
			return null;
		}
		String firstOpr = UtilConstant.WHERE_SQL_STR;
	
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			String identifiant = refEtablissement.getIdentifiant();
			if ((identifiant != null) && (!identifiant.trim().isEmpty())) {
				request.append(firstOpr + " lower(r.identifiant) like " + UtilConstant.LikeContain(identifiant));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcEtablissementArabe = refEtablissement.getLcEtablissementArabe();
			if ((lcEtablissementArabe != null) && (!lcEtablissementArabe.trim().isEmpty())) {
				request.append(firstOpr ).append( " lower(r.lcEtablissementArabe) like ").append(UtilConstant.LikeContain(lcEtablissementArabe.toLowerCase()));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			Date dateCreation = refEtablissement.getDateCreation();

			if (dateCreation != null) {
				request.append(firstOpr).append( " cast(r.dateCreation as text) like ").append(UtilConstant.LikeContain(dateCreation));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String lcEtablissementLatin = refEtablissement.getLcEtablissementLatin();
			if ((lcEtablissementLatin != null) && (!lcEtablissementLatin.trim().isEmpty())) {
				request.append(firstOpr).append( " lower(r.lcEtablissementLatin) like ").append( UtilConstant.LikeContain(lcEtablissementLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llEtablissementArabe = refEtablissement.getLlEtablissementArabe();
			if ((llEtablissementArabe != null) && (!llEtablissementArabe.trim().isEmpty())) {
				request.append(firstOpr).append( " lower(r.llEtablissementArabe) like " ).append( UtilConstant.LikeContain(llEtablissementArabe));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String llEtablissementLatin = refEtablissement.getLlEtablissementLatin();
			if ((llEtablissementLatin != null) && (!llEtablissementLatin.trim().isEmpty())) {
				request.append(firstOpr).append( " lower(r.llEtablissementLatin) like " ).append( UtilConstant.LikeContain(llEtablissementLatin));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String decret = refEtablissement.getDecret();
			if ((decret != null) && (!decret.trim().isEmpty())) {
				request.append(firstOpr).append( " lower(r.decret) like ").append( UtilConstant.LikeContain(decret));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nif = refEtablissement.getNif();
			if ((nif != null) && (!nif.trim().isEmpty())) {
				request.append(firstOpr).append( " lower(r.nif) like ").append( UtilConstant.LikeContain(nif));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nis = refEtablissement.getNis();
			if ((nis != null) && (!nis.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nis) like ").append(UtilConstant.LikeContain(nis));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nrc = refEtablissement.getNrc();
			if ((nrc != null) && (!nrc.trim().isEmpty())) {
				request.append(firstOpr).append(" lower(r.nrc) like ").append( UtilConstant.LikeContain(nrc));
				firstOpr = UtilConstant.OR_SQL_STR;
			}

			String nss = refEtablissement.getNss();
			if ((nss != null) && (!nss.trim().isEmpty())) {
				request.append(firstOpr ).append( " lower(r.nss) like " ).append( UtilConstant.LikeContain(nss));
				firstOpr = UtilConstant.OR_SQL_STR;
			}
			log.debug("request successful " + new String(request));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query.getResultList();
			log.debug("RefEtablissement findAdvanced successful");
			return resultList;

		} catch (RuntimeException re) {
			log.error("RefEtablissement findAdvanced failed", re);
			throw re;
		}
	
	}



	@Override
	public RefEtablissement findByLlLatin(String llLatin) {
		if(llLatin == null) {
			return null;
		}
		log.debug("findByLlLatin instance with : " + llLatin);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.llEtablissementLatin) = " +UtilConstant.quotedString(llLatin.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if(resultList == null || resultList.isEmpty()) {
				return null;
			} else {
				log.debug("findByLlLatin successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("findByLlLatin failed", re);
			throw re;
		}
	}



	@Override
	public RefEtablissement findByLlArabe(String llArabe) {
		if(llArabe == null) {
			return null;
		}
		log.debug("findByLlArabe instance with : " + llArabe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.llEtablissementArabe) = " + UtilConstant.quotedString(llArabe.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if(resultList == null || resultList.isEmpty()) {
				return null;
			} else {
				log.debug("findByLlArabe successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("findByLlArabe failed", re);
			throw re;
		}
	}



	@Override
	public RefEtablissement findByLcLatin(String lcLatin) {
		if(lcLatin == null) {
			return null;
		}
		log.debug("findByLcLatin instance with : " + lcLatin);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.lcEtablissementLatin) = " + UtilConstant.quotedString(lcLatin.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if(resultList == null || resultList.isEmpty()) {
				return null;
			} else {
				log.debug("findByLcLatin successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("findByLcLatin failed", re);
			throw re;
		}
	}



	@Override
	public RefEtablissement findByLcArabe(String lcArabe) {
		if(lcArabe == null) {
			return null;
		}
		log.debug("findByLcArabe instance with : " + lcArabe);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.lcEtablissementArabe) = " + UtilConstant.quotedString(lcArabe.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if(resultList == null || resultList.isEmpty()) {
				return null;
			} else {
				log.debug("findByLcArabe successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("findByLcArabe failed", re);
			throw re;
		}
	}



	@Override
	public RefEtablissement findByIdentifiant(String identifiant) {
		if(identifiant == null) {
			return null;
		}
		log.debug("findByIdentifiant instance with : " + identifiant);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append("where lower(r.identifiant) = " + UtilConstant.quotedString(identifiant.toLowerCase()));
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if(resultList == null || resultList.isEmpty()) {
				return null;
			} else {
				log.debug("findByIdentifiant successful");
				return resultList.get(0);
			}

		} catch (RuntimeException re) {
			log.error("findByIdentifiant failed", re);
			throw re;
		}
	}



	@Override
	public Integer findLastOrder(String prefix, int orderLength) {
		if(prefix == null) {
			return null;
		}
		int prefixLength = prefix.length();
		log.debug("findLastOrder instance with : " + prefix);
		try {
			StringBuilder request = new StringBuilder(
					"select r from RefEtablissement r ");
			request.append(" where lower(r.identifiant) like " + UtilConstant.startWith(prefix.toLowerCase()));
			request.append(" order by r.identifiant desc");
			Query query = entityManager.createQuery(new String(request));
			List<RefEtablissement> resultList = (List<RefEtablissement>) query
					.getResultList();
			if (resultList == null || resultList.isEmpty()) {
				return 0;
			} else {
				log.debug("findLastOrder successful");
				String lastIdentify = resultList.get(0).getIdentifiant();
				if (lastIdentify == null) {
					return null;
				}
				String lastOrderStr = lastIdentify.substring(prefixLength);
				if (lastOrderStr == null
						|| lastOrderStr.length() != orderLength) {
					return null;
				}
				Integer lastOrder = Integer.parseInt(lastOrderStr);
				return lastOrder;
			}

		} catch (RuntimeException re) {
			log.error("findLastOrder failed", re);
			throw re;
		}
		
	}
	
}
