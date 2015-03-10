/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.NomenclatureWSImpl.java] 
 * @author BELDI Jamel on : 23 janv. 2014  18:10:56
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.ws.service.NomenclatureWS;

/**
 * @author BELDI Jamel on : 23 janv. 2014 18:10:56
 */
@Service("nomenclatureWSImpl")
public class NomenclatureWSImpl implements NomenclatureWS {
	private static final Log log = LogFactory.getLog(NomenclatureWSImpl.class);

	@Autowired
	@Qualifier("nomenclatureServiceImpl")
	private NomenclatureService nomenclatureServiceImpl;

	/**
	 * [NomenclatureWSImpl.nomenclatureServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [NomenclatureWSImpl.nomenclatureServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [NomenclatureWSImpl.NomenclatureWSImpl()] Constructor
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:10:56
	 */
	public NomenclatureWSImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [NomenclatureWSImpl.findByName] Find List of Nomenclature by Name
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param ncName
	 *            the name of NC
	 * @return List of NC
	 */
	@Override
	public List<NomenclatureDto> findByName(String ncName) throws Exception {
		log.debug("start findByName" + ncName);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findByName(ncName);
			log.debug("findByName successful");
		} catch (Exception e) {
			log.error("findByName failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [NomenclatureWSImpl.findByReference] Find List of Nomenclature by Name
	 * and REFERENCE
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param ncName
	 *            the name of NC, ID of Reference
	 * @return List of NC
	 */
	public List<NomenclatureDto> findByReference(String ncName,
			Integer idReference) throws Exception {
		log.debug("start findByReference nc: " + ncName + " ID Reference: "
				+ idReference);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findByReference(ncName,
					idReference);
			log.debug("findByReference successful");
		} catch (Exception e) {
			log.error("findByReference failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [NomenclatureWSImpl.findByDomaine] Find List of Nomenclature by Domain
	 * 
	 * @author BELDI Jamel on : 23 janv. 2014 18:15:56
	 * @param ncDomaine
	 *            the Domain of NC
	 * @return List of NC
	 */
	public List<NomenclatureDto> findByDomaine(String ncDomaine)
			throws Exception {
		log.debug("start findByDomaine nc: " + ncDomaine);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findByDomaine(ncDomaine);
			log.debug("findByDomaine successful");
		} catch (Exception e) {
			log.error("findByDomaine failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [NomenclatureWSImpl.findByIdNomenclature] Find Nomenclature by ID
	 * 
	 * @author SELMANE YAZID on : 27 avr. 2014 18:15:56
	 * @param id
	 *            the ID of NC
	 * @return
	 */
	public NomenclatureDto findByIdNomenclature(int id) throws Exception {
		log.debug("start findByIdNomenclature nc: " + id);
		NomenclatureDto result = null;
		try {
			result = nomenclatureServiceImpl.findById(id);
			log.debug("findByIdNomenclature successful");
		} catch (Exception e) {
			log.error("findByIdNomenclature failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [NomenclatureWSImpl.findByNcNameLikeLibellefr] Find generic Nomenclature
	 * by text search
	 * 
	 * @author SELMANE YAZID on : 27 avr. 2014 18:15:56
	 * @param ncName
	 *            name of Nomenclature
	 * @param value
	 *            text of search
	 * @return List of NC
	 */
	public List<NomenclatureDto> findByNcNameLikeLibellefr(String ncName,
			String value) throws Exception {
		log.debug("start findByNcNameLikeLibellefr nc: " + ncName);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findByNcNameLikeLibellefr(ncName,
					value);
			log.debug("findByNcNameLikeLibellefr successful");
		} catch (Exception e) {
			log.error("findByNcNameLikeLibellefr failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public List<NomenclatureDto> findNcCompositeList(int id, String ncTarget)
			throws Exception {
		log.debug("start findNcCompositeList nc: " + ncTarget);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findNcCompositeList(id, ncTarget);
			log.debug("findNcCompositeList successful");
		} catch (Exception e) {
			log.error("findNcCompositeList failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public NomenclatureDto findByCode(String ncName, String code)
			throws Exception {
		log.debug("start findByCode");
		NomenclatureDto result = null;
		try {
			result = nomenclatureServiceImpl.findByCode(ncName, code);
			log.debug("findByCode successful");
		} catch (Exception e) {
			log.error("findByCode failed", e);

			throw e;

		}
		return result;
	}

	@Override
	public List<NomenclatureDto> findNcCompositeListByCode(String ncName,
			String code, String ncTarget) throws Exception {
		log.debug("start findNcCompositeListByCode nc: " + ncTarget);
		List<NomenclatureDto> result = new ArrayList<NomenclatureDto>();
		try {
			result = nomenclatureServiceImpl.findNcCompositeList(ncName, code,
					ncTarget);
			log.debug("findNcCompositeListByCode successful");
		} catch (Exception e) {
			log.error("findNcCompositeListByCode failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public NomenclatureDto findReference(String ncCode) throws Exception {
		log.debug("start findReference");
		NomenclatureDto result = null;
		try {
			result = nomenclatureServiceImpl.findReference(ncCode);
			log.debug("findReference successful");
		} catch (Exception e) {
			log.error("findReference failed", e);

			throw e;

		}
		return result;
	}

}
