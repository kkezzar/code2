package dz.gov.mesrs.sii.commons.data.dao.impl.referentiel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * Dao object for domain model class Nomenclature.
 * @see dz.gov.mesrs.sii.Nomenclature.business.model.bo.Nomenclature
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 



@Service ("nomenclatureDaoImpl")  
public class NomenclatureDaoImpl implements NomenclatureDao {

	private static final Logger log = LoggerFactory.getLogger(NomenclatureDaoImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;


	/**
	 * @see dz.gov.mesrs.sii.NomenclatureDao.business.persistence.jpa.impl.NomenclatureDao#persist(dz.gov.mesrs.sii.Nomenclature.business.model.bo.Nomenclature)
	 * 
	 * @param transientInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void persist(Nomenclature transientInstance) {
		log.debug("persisting Nomenclature instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.NomenclatureDao.business.persistence.jpa.impl.NomenclatureDao#remove(dz.gov.mesrs.sii.Nomenclature.business.model.bo.Nomenclature)
	 * @param persistentInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void remove(Nomenclature persistentInstance) {
		log.debug("removing Nomenclature instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.NomenclatureDao.business.persistence.jpa.impl.NomenclatureDao#merge(dz.gov.mesrs.sii.Nomenclature.business.model.bo.Nomenclature)
	 * @param detachedInstance
	 */
	 
	@Override 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Nomenclature merge(Nomenclature detachedInstance) {
		log.debug("merging Nomenclature instance");
		try {
			log.debug("merge successful");
			return entityManager.merge(detachedInstance);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}



	/**
	 * @see dz.gov.mesrs.sii.lmd.business.persistence.jpa.impl.NNomenclatureDao#findById(int)
	 * @param id
	 */
	@Override 
	@Transactional(readOnly = true)
	public Nomenclature findById(int id) {
		log.debug("getting Nomenclature instance with id: " + id);
		try {			
			log.debug("get successful");
			return entityManager.find(Nomenclature.class, id);
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
	public List<Nomenclature> findByQuery(String query) {
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
	@SuppressWarnings("unchecked") 
	@Override 
	@Transactional(readOnly = true)
	public List<Nomenclature> findAll() {
		log.debug("getting all Nomenclature instances");
		try {
			Query query = entityManager.createQuery("from Nomenclature Nomenclature");
			log.debug("findAll 'Nomenclature' successful");
			return query.getResultList();
		} catch (RuntimeException re) {
			log.error("findAll 'Nomenclature' failed", re);
			return new ArrayList<Nomenclature>();
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection by nomenclature name
	 * @param name
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByName(java.lang.String)
	 */
	@Override
	public List<Nomenclature> findByName(String name) {
		log.debug("getting all Nomenclature instances by name "+name);
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature = "+UtilConstant.quotedString(name));
			buffer.append(" and nomenclature.ncNames = ncNames");
			buffer.append(" and nomenclature.status = TRUE");
			buffer.append(" order by nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by name successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by name failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection by nomenclature name and specified order by parameter
     * @param name
	 * @param orderBy
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByName(java.lang.String, java.lang.String)
	
	 */
	@Override
	public List<Nomenclature> findByName(String name, String orderBy) {
		log.debug("getting all Nomenclature instances by name "+name );
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature = "+UtilConstant.quotedString(name));
			buffer.append(" and nomenclature.ncNames = ncNames");
			if(orderBy != null) {
			    buffer.append(" order by nomenclature." + orderBy);
			}
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by name successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by name failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection by id of nomenclature name
	 * @param id
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByIdNc(int)
	 * 
	 */
	@Override
	public List<Nomenclature> findByIdNc(int id) {
		log.debug("getting all Nomenclature instances by id " + id);
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from Nomenclature nomenclature");
			buffer.append(" where nomenclature.ncNames.id = " + id);
			buffer.append(" order by nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by nc id successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by nc id failed", re);
			
			throw re;
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection by id of nomenclature name and code of the nomenclature
	 * @param idNcName
	 * @param code
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByCode(java.lang.String)
	 */
	@Override
	public Nomenclature findByCode(int idNcName, String code) {
		log.debug("get Nomenclature with code = " + code);
		if(code == null) {
			return null;
		}
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from Nomenclature nomenclature");
			buffer.append(" where lower(nomenclature.code) = " + UtilConstant.quotedString(code.trim()).toLowerCase());
			buffer.append(" and nomenclature.ncNames.id = " + idNcName);
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			if  ((resultList != null) && (!resultList.isEmpty())) {
				Nomenclature result =  resultList.get(0);
				log.debug("get Nomenclature by code successful");
				return result;
			}
		} catch (RuntimeException re) {
			log.error("get Nomenclature by code failed", re);
			return null;
			//throw re;
		}
		return null;
	}



	/* (non-Javadoc)
	 * find Nomenclature by id of nomenclature name and llLatin of the nomenclature
	 * @param idNcName
	 * @param llLatin
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByLlLatin(int, java.lang.String)
	 */
	@Override
	public Nomenclature findByLlLatin(int idNcName, String llLatin) {
		log.debug("get Nomenclature with ll_fr = " + llLatin);
		if(llLatin == null) {
			return null;
		}
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from Nomenclature nomenclature");
			buffer.append(" where lower(nomenclature.libelleLongFr) = " + UtilConstant.quotedString(llLatin.trim()).toLowerCase());
			buffer.append(" and nomenclature.ncNames.id = " + idNcName);
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			if ((resultList != null) && (!resultList.isEmpty())) {
				Nomenclature result =  resultList.get(0);
				log.debug("get Nomenclature by libelle latin successful");
				return result;
			}
		} catch (RuntimeException re) {
			log.error("get Nomenclature by libelle latin failed", re);
			return null;
			//throw re;
		}
		return null;
	}



	/* (non-Javadoc)
	 * find Nomenclature by id of nomenclature name and llArabe of the nomenclature
	 * @param idNcName
	 * @param llLatin
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByLlArabe(java.lang.String)
	 */
	@Override
	public Nomenclature findByLlArabe(int idNcName, String llArabe) {
		log.debug("get Nomenclature with ll_ar = " + llArabe);
		if(llArabe == null) {
			return null;
		}
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from Nomenclature nomenclature");
			buffer.append(" where lower(nomenclature.libelleLongAr) = " + UtilConstant.quotedString(llArabe.trim()).toLowerCase());
			buffer.append(" and nomenclature.ncNames.id = " + idNcName);
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			if ((resultList != null) && (!resultList.isEmpty())) {
				Nomenclature result =  resultList.get(0);
				log.debug("get Nomenclature by libelle arabe successful");
				return result;
			}
			
		} catch (RuntimeException re) {
			log.error("get Nomenclature by libelle arabe failed", re);
			return null;
			//throw re;
		}
		return null;
		
	}



	/* (non-Javadoc)
	 * find Nomenclature collection reference by nomenclature name
	 * @param name
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#getNcReference(dz.gov.mesrs.sii.referentiel.business.model.bo.Nomenclature)
	 */
	@Override
	public List<Nomenclature> findNcReference(String name) {
		
		log.debug("findNcReference with name " + name);
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature = "+UtilConstant.quotedString(name));
			buffer.append(" and nomenclature.ncNames = ncNames.ncNames");
			buffer.append(" order by nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR);
		String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("find Nomenclature by nc id successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("find Nomenclature by nc id failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection compoisite by nomenclature name, value and target name
	 * @param ncName
	 * @param ncValue
	 * @param ncTarget
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findNcCompositeList(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Nomenclature> findNcCompositeList(String ncName,
			String code, String ncTarget) {
		log.debug("findNcCompositeList with ncName " + ncName +" code = "+code+" ncTarget = "+ncTarget);
		if(code == null || ncTarget == null) {
			return null;
		}
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nmcltTarget from NcNames ncNames,NcNames ncTarget, Nomenclature nmclt, Nomenclature nmcltTarget");
			buffer.append(" where lower(ncNames.nomNomenclature) = " + UtilConstant.quotedString(ncName.toLowerCase()));
			buffer.append(" and nmclt.ncNames = ncNames");
			buffer.append(" and lower(nmclt.code) = " + UtilConstant.quotedString(code.toLowerCase()));
			buffer.append(" and lower(ncTarget.nomNomenclature) = " + UtilConstant.quotedString(ncTarget.toLowerCase()));
			buffer.append(" and nmcltTarget.ncNames = ncTarget");
			buffer.append(" and nmclt = nmcltTarget.nomenclature");
			buffer.append(" and nmclt.status = TRUE");
			buffer.append(" order by nmcltTarget." + UtilConstant.NC_VALUES_ORDERBY_LL_FR);
		String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("findNcCompositeList successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findNcCompositeList failed", re);
			throw re;
		}
	}



	/* (non-Javadoc)
	 * find Nomenclature collection compoisite by nomenclature id, value and taget name
	 * @param ncName
	 * @param ncValue
	 * @param ncTarget
	 *
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findNcCompositeList(int, java.lang.String)
	 */
	@Override
	public List<Nomenclature> findNcCompositeList(int id, String ncTarget) {
		log.debug("findNcCompositeList with id " + id +" ncTarget = "+ncTarget);
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nmcltTarget from NcNames ncNames, Nomenclature nmclt, Nomenclature nmcltTarget");
			buffer.append(" where nmclt.id = " + id);
			buffer.append(" and ncNames.nomNomenclature = " + UtilConstant.quotedString(ncTarget));
			buffer.append(" and nmcltTarget.ncNames = ncNames");
			buffer.append(" and nmclt = nmcltTarget.nomenclature");
			buffer.append(" and nmclt.status = TRUE");
			buffer.append(" order by nmcltTarget." + UtilConstant.NC_VALUES_ORDERBY_LL_FR);
		String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("findNcCompositeList successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findNcCompositeList failed", re);
			throw re;
		}
	}



	/**
	 * [NomenclatureDaoImpl.findByDomaine] Find List of Nomenclature by Domain
	 * @author BELDI Jamel on : 23 janv. 2014  18:15:56
	 * @param ncDomaine the Domain of NC
	 * @return List of NC
	 */
	public List<Nomenclature> findByDomaine(String ncDomaine) {
		log.debug("getting  Nomenclature instances by domaine "+ncDomaine );
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature like "+UtilConstant.LikeContain(ncDomaine));
			buffer.append(" and nomenclature.ncNames = ncNames");
			buffer.append(" and nomenclature.status = TRUE");
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("findByDomaine successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findByDomaine failed", re);
			throw re;
		}
	}


	/**
	 * [NomenclatureDaoImpl.findByReference] Find List of Nomenclature by Name and REFERENCE 
	 * @author BELDI Jamel on : 23 janv. 2014  18:15:56
	 * @param ncName the name of NC, ID of Reference 
	 * @return List of NC
	 */
	@Override
	public List<Nomenclature> findByReference(String ncName, Integer idReference) {
		log.debug("getting  Nomenclature instances by Name and REFERENCE  "+idReference );
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature = "+UtilConstant.quotedString(ncName));
			buffer.append(" and nomenclature.nomenclature.id = "+idReference);
			buffer.append(" and nomenclature.ncNames = ncNames");
			buffer.append(" and nomenclature.status = TRUE");
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("findByReference successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("findByReference failed", re);
			throw re;
		}
	}
	
	
	/* (non-Javadoc)
	 * find Nomenclature collection by nomenclature name and like libelle fr 
	 * @param name
	 * @param value
	 * @see dz.gov.mesrs.sii.referentiel.business.persistence.NomenclatureDao#findByName(java.lang.String)
	 */
	@Override
	public List<Nomenclature> findByNcNameLikeLibellefr(String name, String value) {
		log.debug("getting all Nomenclature instances by name and like libellelfr " +value+  "value ---" +name);
		try {
			if (value != null ) 
				value= value.trim().toLowerCase();
			
			StringBuilder buffer = new StringBuilder();
			buffer.append("SELECT nomenclature FROM  Nomenclature nomenclature, NcNames ncNames");
			buffer.append(" WHERE ncNames.nomNomenclature = " + UtilConstant.quotedString(name));
			buffer.append(" AND nomenclature.ncNames = ncNames");
			buffer.append(" AND nomenclature.status = TRUE");
			buffer.append(" AND lower(nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR +") LIKE "+ UtilConstant.LikeContain(value));
			buffer.append(" ORDER BY nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by name, value like  successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by name, value like failed", re);
			throw re;
		}
	}



	@Override
	public List<Nomenclature> findByNameAndValue(String name, String value) {
		log.debug("getting all Nomenclature instances by name "+name + " value " + value);
		if (value != null) {
			value = "%" + value.toLowerCase() + "%";
		}
		
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature = " + UtilConstant.quotedString(name));
			buffer.append(" and nomenclature.ncNames = ncNames");
			buffer.append(" and nomenclature.status = TRUE");
			buffer.append(" and (lower(nomenclature.libelleLongFr) like :value");
			buffer.append(" or lower(nomenclature.libelleLongAr) like :value");
			buffer.append(" or lower(nomenclature.libelleCourtFr) like :value");
			buffer.append(" or lower(nomenclature.libelleCourtAr) like :value)");
			buffer.append(" order by nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			query.setParameter("value", value);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by name successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by name failed", re);
			throw re;
		}
	}



	@Override
	public List<Nomenclature> findTypePiece(int idTypePiece) {
		log.debug("findTypePiecere instances by idTypePiece "+idTypePiece);
		
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select distinct nomenclature from NcNames ncNames, Nomenclature nomenclature");
			buffer.append(" where ncNames.nomNomenclature =:nom " );
			buffer.append(" and nomenclature.ncNames = ncNames");
			buffer.append(" and nomenclature.status = TRUE");
			buffer.append(" and nomenclature.id not in (select tp.nomenclatureByTypePiece.id from RefTypePieceConstitutive tp where tp.nomenclatureByTypeDossier.id =:id)");
			buffer.append(" order by nomenclature." + UtilConstant.NC_VALUES_ORDERBY_LL_FR); //default orderby
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			query.setParameter("nom", UtilConstant.NC_TYPE_PIECE);
			query.setParameter("id", idTypePiece);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			log.debug("get Nomenclature by name successful");
			return resultList;
		} catch (RuntimeException re) {
			log.error("get Nomenclature by name failed", re);
			throw re;
		}
	}



	@Override
	public Nomenclature findByCode(String ncName, String code) {
		log.debug("get Nomenclature with code = " + code);
		if(code == null) {
			return null;
		}
		if(ncName == null) {
			return null;
		}
		
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nomenclature from Nomenclature nomenclature");
			buffer.append(" where lower(nomenclature.code) =:code ");
			buffer.append(" and lower(nomenclature.ncNames.nomNomenclature) =:ncName");
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			query.setParameter("code", code.toLowerCase());
			query.setParameter("ncName", ncName.toLowerCase());
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			if  ((resultList != null) && (!resultList.isEmpty())) {
				Nomenclature result =  resultList.get(0);
				log.debug("get Nomenclature by code successful");
				return result;
			}
		} catch (RuntimeException re) {
			log.error("get Nomenclature by code failed", re);
			return null;
			//throw re;
		}
		return null;
	}



	@Override
	public Nomenclature findReference(String ncCode) {
		log.debug("getting  findReference  " + ncCode );
		if(ncCode == null || ncCode.isEmpty()) {
			return null;
		}
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("select nc.nomenclature from Nomenclature nc");
			buffer.append(" where lower(nc.code) = " + UtilConstant.quotedString(ncCode.toLowerCase()));
			buffer.append(" and nc.status = TRUE");
			buffer.append(" and nc.nomenclature.status = TRUE");
			String request = new String(buffer);
			log.info("execute query "+request);
			Query query = entityManager.createQuery(request);
			List<Nomenclature> resultList = (List<Nomenclature>) query
					.getResultList();
			if(resultList == null || resultList.size() != 1) {
				return null;
			}
			log.debug("findReference successful");
			return resultList.get(0);
		} catch (RuntimeException re) {
			log.error("findReference failed", re);
			throw re;
		}
	}

}
