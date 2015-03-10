package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseElectroniqueService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService;
import dz.gov.mesrs.sii.referentiel.ws.service.CoordonneeWS;

@Service("coordonneeWSImpl")
public class CoordonneeWSImpl implements CoordonneeWS {

	private static final Log log = LogFactory.getLog(CoordonneeWSImpl.class);

	// ===================================================================
	// Constructor
	// ===================================================================
	/**
	 * [PpmWSImpl.PpmWSImpl()] Constructor
	 * 
	 * @author rlaib on : 12 mars 2014 14:41:49
	 */
	public CoordonneeWSImpl() {

	}

	@Autowired
	@Qualifier("refCoordonneeServiceImpl")
	private RefCoordonneeService refCoordonneeService;

	@Autowired
	@Qualifier("refAdresseServiceImpl")
	private RefAdresseService refAdresseService;

	@Autowired
	@Qualifier("refAdresseElectroniqueServiceImpl")
	private RefAdresseElectroniqueService refAdresseElectroniqueService;
	@Autowired
	@Qualifier("refTelephoneServiceImpl")
	private RefTelephoneService refTelephoneService;

	/**
	 * [PpmWSImpl.refCoordonneeService] Getter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @return the refCoordonneeService
	 */
	public RefCoordonneeService getRefCoordonneeService() {
		return refCoordonneeService;
	}

	/**
	 * [PpmWSImpl.refCoordonneeService] Setter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @param refCoordonneeService
	 *            the refCoordonneeService to set
	 */
	public void setRefCoordonneeService(
			RefCoordonneeService refCoordonneeService) {
		this.refCoordonneeService = refCoordonneeService;
	}

	/**
	 * [PpmWSImpl.refAdresseService] Getter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @return the refAdresseService
	 */
	public RefAdresseService getRefAdresseService() {
		return refAdresseService;
	}

	/**
	 * [PpmWSImpl.refAdresseService] Setter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @param refAdresseService
	 *            the refAdresseService to set
	 */
	public void setRefAdresseService(RefAdresseService refAdresseService) {
		this.refAdresseService = refAdresseService;
	}

	/**
	 * [PpmWSImpl.refAdresseElectroniqueService] Getter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @return the refAdresseElectroniqueService
	 */
	public RefAdresseElectroniqueService getRefAdresseElectroniqueService() {
		return refAdresseElectroniqueService;
	}

	/**
	 * [PpmWSImpl.refAdresseElectroniqueService] Setter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @param refAdresseElectroniqueService
	 *            the refAdresseElectroniqueService to set
	 */
	public void setRefAdresseElectroniqueService(
			RefAdresseElectroniqueService refAdresseElectroniqueService) {
		this.refAdresseElectroniqueService = refAdresseElectroniqueService;
	}

	/**
	 * [PpmWSImpl.refTelephoneService] Getter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @return the refTelephoneService
	 */
	public RefTelephoneService getRefTelephoneService() {
		return refTelephoneService;
	}

	/**
	 * [PpmWSImpl.refTelephoneService] Setter
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 11:52:28
	 * @param refTelephoneService
	 *            the refTelephoneService to set
	 */
	public void setRefTelephoneService(RefTelephoneService refTelephoneService) {
		this.refTelephoneService = refTelephoneService;
	}

	@Override
	public List<RefCoordonneeDto> findCoordonneesByIdEntity(String entityName,
			Integer entityId) throws Exception {
		try {
			List<RefCoordonneeDto> result = new ArrayList<RefCoordonneeDto>();
			if (entityName != null && entityName.equals("individu")) {
				result = refCoordonneeService.findByRefIndividuId(entityId);

			} else if (entityName != null && entityName.equals("structure")) {
				result = refCoordonneeService.findByRefStructureId(entityId);

			} else if (entityName != null && entityName.equals("lieu")) {
				result = refCoordonneeService.findByRefLieuId(entityId);
			} else if (entityName != null && entityName.equals("etablissement")) {
				result = refCoordonneeService
						.findByRefEtablissementId(entityId);
			}

			log.debug("findCoordonneesByIdEntity successful");
			return result;
		} catch (Exception e) {
			log.error("findCoordonneesByIdEntity  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAdresseDto> findAdressesByListCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) throws Exception {
		try {
			List<RefAdresseDto> result = new ArrayList<RefAdresseDto>();

			result = refAdresseService.findByRefCoordonnee(refCoordonneeList);

			log.debug("findAdressesByListCoordonnee successful");
			return result;
		} catch (Exception e) {
			log.error("findAdressesByListCoordonnee  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAdresseElectroniqueDto> findAdresseElectroniquesByListCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) throws Exception {
		try {
			List<RefAdresseElectroniqueDto> result = new ArrayList<RefAdresseElectroniqueDto>();

			result = refAdresseElectroniqueService
					.findByRefCoordonnee(refCoordonneeList);

			log.debug("findAdresseElectroniquesByListCoordonnee successful");
			return result;
		} catch (Exception e) {
			log.error("findAdresseElectroniquesByListCoordonnee  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefTelephoneDto> findTelephonesByListCoordonnee(
			List<RefCoordonneeDto> refCoordonneeList) throws Exception {
		try {
			List<RefTelephoneDto> result = new ArrayList<RefTelephoneDto>();

			result = refTelephoneService.findByRefCoordonnee(refCoordonneeList);

			log.debug("findTelephonesByListCoordonnee successful");
			return result;
		} catch (Exception e) {
			log.error("findTelephonesByListCoordonnee  failed", e);
			throw e;
		}
	}

	@Override
	public RefAdresseDto saveAdresse(RefAdresseDto refAdresseDto)
			throws Exception {
		log.debug("start saveAdresse" + refAdresseDto);

		try {

			RefAdresseDto resultDto = refAdresseService.save(refAdresseDto);
			log.debug("saveAdresse successful");
			return resultDto;

		} catch (Exception e) {

			log.error("saveAdresse failed", e);
			throw e;
		}
	}

	@Override
	public void deleteAdresse(RefAdresseDto refAdresseDto) throws Exception {
		log.debug("start deleteAdresse" + refAdresseDto);

		try {

			refAdresseService.delete(refAdresseDto);
			log.debug("deleteAdresse successful");

		} catch (Exception e) {

			log.error("deleteAdresse failed", e);
			throw e;
		}

	}

	@Override
	public RefAdresseElectroniqueDto saveAdresseElectronique(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto)
			throws Exception {
		log.debug("start saveAdresseElectronique" + refAdresseElectroniqueDto);

		try {

			RefAdresseElectroniqueDto resultDto = refAdresseElectroniqueService
					.save(refAdresseElectroniqueDto);
			log.debug("saveAdresseElectronique successful");
			return resultDto;

		} catch (Exception e) {

			log.error("saveAdresseElectronique failed", e);
			throw e;
		}
	}

	@Override
	public void deleteAdresseElectronique(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto)
			throws Exception {
		log.debug("start deleteAdresseElectronique" + refAdresseElectroniqueDto);

		try {

			refAdresseElectroniqueService.delete(refAdresseElectroniqueDto);
			log.debug("deleteAdresseElectronique successful");

		} catch (Exception e) {

			log.error("deleteAdresseElectronique failed", e);
			throw e;
		}

	}

	@Override
	public RefTelephoneDto saveTelephone(RefTelephoneDto refTelephoneDto)
			throws Exception {
		log.debug("start saveTelephone" + refTelephoneDto);

		try {

			RefTelephoneDto resultDto = refTelephoneService
					.save(refTelephoneDto);
			log.debug("saveTelephone successful");
			return resultDto;

		} catch (Exception e) {

			log.error("saveTelephone failed", e);
			throw e;
		}
	}

	@Override
	public void deleteTelephone(RefTelephoneDto refTelephoneDto)
			throws Exception {
		log.debug("start deleteTelephone" + refTelephoneDto);

		try {

			refTelephoneService.delete(refTelephoneDto);
			log.debug("deleteTelephone successful");

		} catch (Exception e) {

			log.error("deleteTelephone failed", e);
			throw e;
		}

	}

	@Override
	public RefAdresseDto findPrincipalAdresseForIndividu(String typeAdresse,
			Integer idIndividu) throws Exception {
		log.debug("start findPrincipalAdresseForIndividu");

		try {

			RefAdresseDto refAdresseDto = refAdresseService
					.findPrincipalAdresseForIndividu(typeAdresse, idIndividu);
			log.debug("findPrincipalAdresseForIndividu successful");
			return refAdresseDto;

		} catch (Exception e) {

			log.error("findPrincipalAdresseForIndividu failed", e);
			throw e;
		}
	}

	@Override
	public RefTelephoneDto findPrincipalTelephoneForIndividu(String typeTel,
			String natureTel, Integer idIndividu) throws Exception {
		log.debug("start findPrincipalTelephoneForIndividu");

		try {

			RefTelephoneDto refTelephoneDto = refTelephoneService
					.findPrincipalTelephoneForIndividu(typeTel, natureTel,
							idIndividu);
			log.debug("findPrincipalTelephoneForIndividu successful");
			return refTelephoneDto;

		} catch (Exception e) {
			log.error("findPrincipalTelephoneForIndividu failed", e);
			throw e;
		}
	}

	@Override
	public RefAdresseElectroniqueDto findPrincipalAdresseElectroniqueForIndividu(
			String typeAdrElectro, String natureAdrElectro, Integer idIndividu)
			throws Exception {
		log.debug("start findPrincipalAdresseElectroniqueForIndividu");

		try {

			RefAdresseElectroniqueDto refAdresseElectroniqueDto = refAdresseElectroniqueService
					.findPrincipalAdresseElectroniqueForIndividu(
							typeAdrElectro, natureAdrElectro, idIndividu);
			log.debug("findPrincipalAdresseElectroniqueForIndividu successful");
			return refAdresseElectroniqueDto;

		} catch (Exception e) {
			log.error("findPrincipalAdresseElectroniqueForIndividu failed", e);
			throw e;
		}
	}

}
