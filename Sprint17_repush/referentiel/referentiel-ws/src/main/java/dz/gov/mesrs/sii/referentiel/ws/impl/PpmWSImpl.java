/**
 * [dz.gov.mesrs.sii.referentiel.ws.impl.PpmWSImpl.java] 
 * @author rlaib on : 12 mars 2014  14:38:52
 */
package dz.gov.mesrs.sii.referentiel.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDomLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectFiliereLmdEtabDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParamDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefContratService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtablissementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;
import dz.gov.mesrs.sii.referentiel.ws.service.PpmWS;

/**
 * @author JAMEL, RAFIK, YAZID, SOFIANE, KHEREDINNE, OUALID on : 25 JAN 2014
 *         11:39:34
 */
@Service("ppmWSImpl")
public class PpmWSImpl implements PpmWS {

	private static final Log log = LogFactory.getLog(PpmWSImpl.class);

	// ===================================================================
	// Constructor
	// ===================================================================
	/**
	 * [PpmWSImpl.PpmWSImpl()] Constructor
	 * 
	 * @author rlaib on : 12 mars 2014 14:41:49
	 */
	public PpmWSImpl() {

	}

	// ===================================================================
	// Properties
	// ===================================================================
	@Autowired
	@Qualifier("refIndividuServiceImpl")
	private RefIndividuService refIndividuServiceImpl;

	@Autowired
	@Qualifier("refStructureServiceImpl")
	private RefStructureService refStructureServiceImpl;

	@Autowired
	@Qualifier("refGroupeServiceImpl")
	private RefGroupeService refGroupeServiceImpl;

	@Autowired
	@Qualifier("refContratServiceImpl")
	private RefContratService refContratServiceImpl;

	@Autowired
	@Qualifier("refParametrageServiceImpl")
	private RefParametrageService refParametrageServiceImpl;

	@Autowired
	@Qualifier("refLieuServiceImpl")
	private RefLieuService refLieuService;

	@Autowired
	@Qualifier("refEtablissementServiceImpl")
	private RefEtablissementService refEtablissementService;

	@Autowired
	@Qualifier("refDomaineLMDServiceImpl")
	private RefDomaineLMDService refDomaineLMDService;

	@Autowired
	@Qualifier("refFiliereLmdServiceImpl")
	private RefFiliereLmdService refFiliereLmdService;

	@Autowired
	@Qualifier("refSpecialiteLmdServiceImpl")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	// Added By Rafik On Monday 07/04/2014 : 09:55.
	@Autowired
	@Qualifier("refPartenaireServiceImpl")
	private RefPartenaireService refPartenaireService;

	@Autowired
	@Qualifier("refTypePieceConstitutiveServiceImpl")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveService;

	@Autowired
	@Qualifier("refAffectationServiceImpl")
	private RefAffectationService refAffectationService;

	// ===================================================================
	// Properties Getters / Setters
	// ===================================================================

	/**
	 * [PpmWSImpl.refIndividuServiceImpl] Getter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [PpmWSImpl.refIndividuServiceImpl] Setter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [PpmWSImpl.refStructureServiceImpl] Getter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [PpmWSImpl.refStructureServiceImpl] Setter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [PpmWSImpl.refGroupeServiceImpl] Getter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @return the refGroupeServiceImpl
	 */
	public RefGroupeService getRefGroupeServiceImpl() {
		return refGroupeServiceImpl;
	}

	/**
	 * [PpmWSImpl.refGroupeServiceImpl] Setter
	 * 
	 * @author rlaib on : 12 mars 2014 14:43:35
	 * @param refGroupeServiceImpl
	 *            the refGroupeServiceImpl to set
	 */
	public void setRefGroupeServiceImpl(RefGroupeService refGroupeServiceImpl) {
		this.refGroupeServiceImpl = refGroupeServiceImpl;
	}

	/**
	 * [PpmWSImpl.refContratServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 15:38:35
	 * @return the refContratServiceImpl
	 */
	public RefContratService getRefContratServiceImpl() {
		return refContratServiceImpl;
	}

	/**
	 * [PpmWSImpl.refContratServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 15:38:35
	 * @param refContratServiceImpl
	 *            the refContratServiceImpl to set
	 */

	public void setRefContratServiceImpl(RefContratService refContratServiceImpl) {
		this.refContratServiceImpl = refContratServiceImpl;
	}

	public RefPartenaireService getRefPartenaireService() {
		return refPartenaireService;
	}

	public void setRefPartenaireService(
			RefPartenaireService refPartenaireService) {
		this.refPartenaireService = refPartenaireService;
	}

	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	public RefLieuService getRefLieuService() {
		return refLieuService;
	}

	public void setRefLieuService(RefLieuService refLieuService) {
		this.refLieuService = refLieuService;
	}

	public RefEtablissementService getRefEtablissementService() {
		return refEtablissementService;
	}

	public void setRefEtablissementService(
			RefEtablissementService refEtablissementService) {
		this.refEtablissementService = refEtablissementService;
	}

	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	public void setRefDomaineLMDService(
			RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	public void setRefFiliereLmdService(
			RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [PpmWSImpl.refTypePieceConstitutiveService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mai 2014 18:07:20
	 * @return the refTypePieceConstitutiveService
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveService() {
		return refTypePieceConstitutiveService;
	}

	/**
	 * [PpmWSImpl.refTypePieceConstitutiveService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mai 2014 18:07:20
	 * @param refTypePieceConstitutiveService
	 *            the refTypePieceConstitutiveService to set
	 */
	public void setRefTypePieceConstitutiveService(
			RefTypePieceConstitutiveService refTypePieceConstitutiveService) {
		this.refTypePieceConstitutiveService = refTypePieceConstitutiveService;
	}

	/**
	 * [PpmWSImpl.refAffectationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 09:19:28
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [PpmWSImpl.refAffectationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 09:19:28
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(
			RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	// ===================================================================
	// Implementation methods [Individu]
	// ===================================================================
	/**
	 * [PpmWSImpl.findGenericIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:22:52
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefIndividuDto> findGenericIndividu(String value)
			throws Exception {
		List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();

		try {
			result = refIndividuServiceImpl.findGeneric(value);
			log.debug("findGenericIndividu successful");

		} catch (Exception e) {

			log.error("findGenericIndividu  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * [PpmWSImpl.findIndividuById] Method
	 * 
	 * @author rlaib on : 10 avr. 2014 17:34:08
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefIndividuDto findIndividuById(String value) throws Exception {
		RefIndividuDto result = new RefIndividuDto();

		try {
			result = refIndividuServiceImpl.findById(Integer.valueOf(value));
			log.debug("findIndividuById successful");

		} catch (Exception e) {

			log.error("findIndividuById  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * [PpmWSImpl.findAdvancedIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:22:59
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefIndividuDto> findAdvancedIndividu(
			RefIndividuDto refIndividuDto) throws Exception {

		List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();

		try {
			result = refIndividuServiceImpl.findAdvanced(refIndividuDto);
			log.debug("findAdvancedIndividu successful");

		} catch (Exception e) {

			log.error("findAdvancedIndividu  failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.saveIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:23:07
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefIndividuDto saveIndividu(RefIndividuDto refIndividuDto)
			throws Exception {

		log.debug("start saveIndividu" + refIndividuDto);

		try {

			RefIndividuDto resultDto = refIndividuServiceImpl
					.save(refIndividuDto);
			log.debug("saveIndividu successful");
			return resultDto;

		} catch (Exception e) {

			log.error("saveIndividu failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.saveIndividus] Method
	 * 
	 * @author rlaib on : 25 juin 2014 08:40:57
	 * @param refIndividuDtos
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefIndividuDto> saveIndividus(
			List<RefIndividuDto> refIndividuDtos) throws Exception {

		log.debug("start saveIndividus" + refIndividuDtos);
		List<RefIndividuDto> result = new ArrayList<RefIndividuDto>();
		try {
			for (RefIndividuDto refIndividuDto : refIndividuDtos) {
				// Test si l individu existe deja
				if (refIndividuServiceImpl.findByIdentifiant(refIndividuDto
						.getIdentifiant()) == null) {

					RefIndividuDto resultDto = refIndividuServiceImpl
							.save(refIndividuDto);
					if (resultDto instanceof RefIndividuDto)
						result.add(resultDto);
				}
			}
			log.debug("saveIndividus successful");
			return result;

		} catch (Exception e) {

			log.error("saveIndividus failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.findByRefIndividuDto] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 11:37:08
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefIndividuDto findIndividuByRefIndividuDto(
			RefIndividuDto refIndividuDto) throws Exception {
		log.debug("start findByRefIndividuDto" + refIndividuDto);

		try {

			RefIndividuDto resultDto = refIndividuServiceImpl
					.findByRefIndividuDto(refIndividuDto);
			log.debug("findByRefIndividuDto successful");
			return resultDto;

		} catch (Exception e) {

			log.error("findByRefIndividuDto failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.updateIndividu] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 11:45:21
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	public RefIndividuDto updateIndividu(RefIndividuDto refIndividuDto)
			throws Exception {
		log.debug("start updateIndividu" + refIndividuDto);
		try {
			refIndividuServiceImpl.update(refIndividuDto);
			log.debug("updateIndividu successful");
			return refIndividuDto;

		} catch (Exception e) {

			log.error("updateIndividu failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.findIndividuByIdentifiant] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 11:45:21
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefIndividuDto findIndividuByIdentifiant(String identifiant)
			throws Exception {
		log.debug("start findIndividuByIdentifiant" + identifiant);

		try {

			RefIndividuDto resultDto = refIndividuServiceImpl
					.findByIdentifiant(identifiant);
			log.debug("findIndividuByIdentifiant successful");
			return resultDto;

		} catch (Exception e) {

			log.error("findIndividuByIdentifiant failed", e);
			throw e;
		}
	}

	// ===================================================================
	// Implementation methods [Structure]
	// ===================================================================
	//
	// /**
	// * [PpmWSImpl.findGenericStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:23:19
	// * @param value
	// * @return
	// * @throws Exception
	// */
	// public List<RefStructureDto> findGenericStructure(String value)
	// throws Exception {
	//
	// List<RefStructureDto> result = new ArrayList<RefStructureDto>();
	//
	// try {
	// result = refStructureServiceImpl.findGeneric(value);
	// log.debug("findGenericStructure successful");
	//
	// } catch (Exception e) {
	//
	// log.error("findGenericStructure  failed", e);
	// throw e;
	//
	// }
	// return result;
	//
	// }
	//
	// /**
	// * [PpmWSImpl.findAdvancedStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:23:47
	// * @param refStructureDto
	// * @return
	// * @throws Exception
	// */
	// public List<RefStructureDto> findAdvancedStructure(
	// RefStructureDto refStructureDto) throws Exception {
	//
	// List<RefStructureDto> result = new ArrayList<RefStructureDto>();
	//
	// try {
	// result = refStructureServiceImpl.findAdvanced(refStructureDto);
	// log.debug("findAdvancedStructure successful");
	//
	// } catch (Exception e) {
	//
	// log.error("findAdvancedStructure  failed", e);
	// throw e;
	//
	// }
	// return result;
	//
	// }
	//
	// /**
	// * [PpmWSImpl.saveStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:23:51
	// * @param refStructureDto
	// * @return
	// * @throws Exception
	// */
	// @Override
	// public String saveStructure(RefStructureDto refStructureDto)
	// throws Exception {
	//
	// log.debug("start saveStructure" + refStructureDto);
	//
	// try {
	//
	// refStructureServiceImpl.save(refStructureDto);
	// log.debug("saveStructure successful");
	// return "OK";
	//
	// } catch (Exception e) {
	//
	// log.error("saveStructure failed", e);
	// throw e;
	// }
	// }

	// ===================================================================
	// Implementation methods [Groupe]
	// ===================================================================

	/**
	 * [PpmWSImpl.findGenericGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:23:57
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List<RefGroupeDto> findGenericGroupe(String value) throws Exception {

		List<RefGroupeDto> result = new ArrayList<RefGroupeDto>();

		try {
			result = refGroupeServiceImpl.findGeneric(value);
			log.debug("findGenericGroupe successful");

		} catch (Exception e) {

			log.error("findGenericGroupe  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * [PpmWSImpl.findAdvancedGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:24:00
	 * @param refGroupeDto
	 * @return
	 * @throws Exception
	 */
	public List<RefGroupeDto> findAdvancedGroupe(RefGroupeDto refGroupeDto)
			throws Exception {

		List<RefGroupeDto> result = new ArrayList<RefGroupeDto>();

		try {
			result = refGroupeServiceImpl.findAdvanced(refGroupeDto);
			log.debug(" findAdvancedGroupe successful");

		} catch (Exception e) {

			log.error("findAdvancedGroupe  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * [PpmWSImpl.saveGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:24:04
	 * @param refGroupeDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public String saveGroupe(RefGroupeDto refGroupeDto) throws Exception {

		log.debug("start saveGroupe" + refGroupeDto);

		try {

			refGroupeServiceImpl.save(refGroupeDto);
			log.debug("saveGroupe successful");
			return "OK";

		} catch (Exception e) {

			log.error("saveGroupe failed", e);
			throw e;
		}
	}

	// ===================================================================
	// Implementation methods [Contrat]
	// ===================================================================
	/**
	 * [PpmWSImpl.findGenericContrat] Method Find list of contrat by character
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:06:49
	 * @param value
	 * @return list of contrat
	 * @throws Exception
	 */
	@Override
	public List<RefContratDto> findGenericContrat(String value)
			throws Exception {
		log.debug("start findGenericContrat" + value);
		List<RefContratDto> result = new ArrayList<RefContratDto>();
		try {
			result = refContratServiceImpl.findGeneric(value, false);
			log.debug("findGenericContrat successful");
		} catch (Exception e) {
			log.error("findGenericContrat failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.getContractByCode] Method
	 * 
	 * @author rlaib on : 10 avr. 2014 17:36:08
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefContratDto getContractByCode(String value) throws Exception {
		RefContratDto result = new RefContratDto();

		try {
			result = refContratServiceImpl.findByCode(value);
			log.debug("findIndividuById successful");

		} catch (Exception e) {

			log.error("findIndividuById  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * [PpmWSImpl.findAdvancedContrat] Method find contrat by criteria
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:07:18
	 * @param refContratDto
	 * @return list of contrat
	 * @throws Exception
	 */
	@Override
	public List<RefContratDto> findAdvancedContrat(RefContratDto refContratDto)
			throws Exception {

		log.debug("start findAdvancedContrat" + refContratDto);
		List<RefContratDto> result = new ArrayList<RefContratDto>();
		try {
			result = refContratServiceImpl.findAdvanced(refContratDto, false);
			log.debug("findAdvancedContrat successful");
		} catch (Exception e) {
			log.error("findAdvancedContrat failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.getPartnersByContractId] Method
	 * 
	 * @author rlaib on : 7 avr. 2014 10:01:19
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefPartenaireDto> getPartnersByContractCode(String value)
			throws Exception {

		log.debug("start getPartnersByContractId" + value);
		List<RefPartenaireDto> result = new ArrayList<RefPartenaireDto>();

		try {
			result = refPartenaireService.findByCodeContrat(value);
			log.debug("getPartnersByContractId successful");

		} catch (Exception e) {

			log.error("getPartnersByContractId failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.saveContrat] Method save a contrat
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:07:23
	 * @param refContratDto
	 * @return OK
	 * @throws Exception
	 */
	@Override
	public String saveContrat(RefContratDto refContratDto) throws Exception {
		log.debug("start saveContrat" + refContratDto);

		try {
			refContratServiceImpl.save(refContratDto, false);
			log.debug("saveContrat successful");
			return "OK";
		} catch (Exception e) {
			log.error("saveContrat failed", e);
			throw e;

		}

	}

	// ===================================================================
	// Implementation methods [Avenant]
	// ===================================================================
	/**
	 * [PpmWSImpl.findGenericAvenant] Method list of avenant by character
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:07:29
	 * @param value
	 * @return list of avanenat
	 * @throws Exception
	 */
	@Override
	public List<RefContratDto> findGenericAvenant(String value)
			throws Exception {
		log.debug("start findGenericAvenant" + value);
		List<RefContratDto> result = new ArrayList<RefContratDto>();
		try {
			result = refContratServiceImpl.findGeneric(value, true);
			log.debug("findGenericAvenant successful");
		} catch (Exception e) {
			log.error("findGenericAvenant failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.findAdvancedAvenant] Method find list of avenant by criteria
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:07:36
	 * @param refContratDto
	 * @return list of avenant
	 * @throws Exception
	 */
	@Override
	public List<RefContratDto> findAdvancedAvenant(RefContratDto refContratDto)
			throws Exception {
		log.debug("start findAdvancedAvenant" + refContratDto);
		List<RefContratDto> result = new ArrayList<RefContratDto>();
		try {
			result = refContratServiceImpl.findAdvanced(refContratDto, true);
			log.debug("findAdvancedAvenant successful");
		} catch (Exception e) {
			log.error("findAdvancedAvenant failed", e);
			throw e;

		}
		return result;
	}

	/**
	 * [PpmWSImpl.saveAvenant] Method Save an avenant
	 * 
	 * @author BELDI Jamel on : 17 mars 2014 17:07:41
	 * @param refContratDto
	 * @return Ok
	 * @throws Exception
	 */
	@Override
	public String saveAvenant(RefContratDto refContratDto) throws Exception {
		log.debug("start saveContrat" + refContratDto);
		try {
			refContratServiceImpl.save(refContratDto, false);
			log.debug("saveContrat successful");
			return "OK";
		} catch (Exception e) {
			log.error("saveContrat failed", e);
			throw e;

		}
	}

	// ===================================================================
	// Implementation methods [Evenement]
	// ===================================================================
	// /**
	// * Permet de rechercher par texte integrales des evenements
	// */
	// @Override
	// public List<RefEvenementDto> findGenericEvenement(String value)
	// throws Exception {
	//
	// List<RefEvenementDto> result = new ArrayList<RefEvenementDto>();
	// try {
	// result = refEvenementService.findGeneric(value);
	// log.debug("findGeneric Evenement successful");
	// } catch (Exception e) {
	// log.error("findGeneric Evenement  failed", e);
	// throw e;
	//
	// }
	// return result;
	//
	// }
	//
	// /**
	// * Permet de rechercher des �v�nements qui ressemblent � l'objet �v�nement
	// * dto pass� en param�tre.
	// */
	// @Override
	// public List<RefEvenementDto> findAdvancedEvenement(
	// RefEvenementDto refEvenementDto) throws Exception {
	//
	// List<RefEvenementDto> result = new ArrayList<RefEvenementDto>();
	// try {
	// result = refEvenementService.findAdvanced(refEvenementDto);
	// log.debug("findAdvanced Evenement successful");
	// } catch (Exception e) {
	// log.error("findAdvanced Evenement  failed", e);
	// throw e;
	// }
	// return result;
	// }

	// ===================================================================
	// Implementation methods [Permission]
	// ===================================================================

	// ===================================================================
	// Implementation methods [Lieu]
	// ===================================================================

	/**
	 * Permet de rechercher par texte intégrales des lieux
	 * 
	 * @param value
	 *            le text à rechercher dans les propriétés des lieux.
	 * @return liste des lieux contenant le text passé en paramètre dans une ou
	 *         plusieurs propriétés.
	 * @throws Exception
	 */
	@Override
	public List<RefLieuDto> findGenericLieu(String value) throws Exception {

		List<RefLieuDto> result = new ArrayList<RefLieuDto>();
		try {
			result = refLieuService.findGeneric(value);
			log.debug("findGeneric Lieu successful");
		} catch (Exception e) {
			log.error("findGeneric Lieu  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * Permet de rechercher des lieux qui ressemblent à l'objet lieu dto passé
	 * en paramètre.
	 * 
	 * @param refLieuDto
	 *            l'objet de contenant les crit�res de recherche
	 * @return liste des lieux qui répondent aux crit�res de recherche contenus
	 *         dans l'objet dto passé en paramètre.
	 * @throws Exception
	 *             une exception est lev�e si l'appel au web service echou.
	 */
	@Override
	public List<RefLieuDto> findAdvancedLieu(RefLieuDto refLieuDto)
			throws Exception {

		List<RefLieuDto> result = new ArrayList<RefLieuDto>();
		try {
			result = refLieuService.findAdvanced(refLieuDto);
			log.debug("findAdvanced Lieu successful");
		} catch (Exception e) {
			log.error("findAdvanced Lieu  failed", e);
			throw e;
		}
		return result;
	}

//	/**
//	 * Récupére le lieu dont l'id est égale à celui passé en paramètre.
//	 * 
//	 * @param id
//	 *            : l'identifiant du lieu rechercher
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public RefLieuDto findLieuById(Integer id) throws Exception {
//
//		try {
//			RefLieuDto _lieu = refLieuService.findById(id);
//			log.debug("findLieuById Lieu successful");
//			return _lieu;
//		} catch (Exception e) {
//			log.error("findLieuById Lieu  failed", e);
//			throw e;
//		}
//	}

	/**
	 * Récupére la liste des lieus par Etablissment et critère de recherche
	 * 
	 * @param etabId
	 * @param refLieuDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefLieuDto> findAdvancedLieuByEtab(Integer etabId, RefLieuDto refLieuDto)
			throws Exception {
		List<RefLieuDto> result = new ArrayList<RefLieuDto>();
		try {
			result = refLieuService.findAdvanced(etabId, refLieuDto);
			log.debug("findAdvanced Lieu successful");
		} catch (Exception e) {
			log.error("findAdvanced Lieu  failed", e);
			throw e;
		}
		return result;
	}
	
	
	/**
	 * Récupére le lieu dont le code est égale à celui passé en paramètre.
	 * 
	 * @param code
	 *            : le code du lieu rechercher
	 * @return
	 * @throws Exception
	 */
	@Override
	public RefLieuDto findLieuByCode(String code) throws Exception {

		try {
			RefLieuDto _lieu = refLieuService.findByCode(code);
			return _lieu;
		} catch (Exception e) {
			
			throw e;
		}
	}
	// ===================================================================
	// Implementation methods [Etablissement]
	// ===================================================================

	/**
	 * Permet de rechercher par texte des Etablissement
	 * 
	 * @param value
	 *            le text rechercher dans les Etablissement.
	 * @return liste des Etablissement contenant le text en paramtre dans une ou
	 *         plusieurs proprt�s
	 * @throws Exception
	 */
	@Override
	public List<RefEtablissementDto> findGenericEtablissement(String value)
			throws Exception {

		List<RefEtablissementDto> result = new ArrayList<RefEtablissementDto>();
		try {
			result = refEtablissementService.findGeneric(value);
			log.debug("findGeneric Etablissement successful");
		} catch (Exception e) {
			log.error("findGeneric Etablissement  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * Permet de rechercher des Etablissements qui ressemblent a l'objet
	 * Etablissement dto pass� en param�tre.
	 * 
	 * @param refEtablissementDto
	 *            l'objet de contenant les crit�res de recherche
	 * @return liste des Etablissements qui r�pondent aux crit�res de recherche
	 *         contenus dans l'objet dto pass� en param�tre.
	 * @throws Exception
	 *             une exception est lev�e si l'appel au web service echou.
	 */
	// @Override
	// public List<RefEtablissementDto> findAdvancedEtablissement(
	// RefEtablissementDto refEtablissementDto) throws Exception {
	//
	// List<RefEtablissementDto> result = new ArrayList<RefEtablissementDto>();
	// try {
	// result = refEtablissementService.findAdvanced(refEtablissementDto);
	// log.debug("findAdvanced Etablissement successful");
	// } catch (Exception e) {
	// log.error("findAdvanced Etablissement  failed", e);
	// throw e;
	// }
	// return result;
	// }

	// ===================================================================
	// Implementation methods [DomaineLMD]
	// ===================================================================

	/**
	 * Permet de rechercher par texte des DomaineLMD
	 * 
	 * @param value
	 *            le text rechercher dans les DomaineLMD.
	 * @return liste des DomaineLMD contenant le text en paramtre dans une ou
	 *         plusieurs proprt�s
	 * @throws Exception
	 */
	@Override
	public List<RefDomaineLMDDto> findGenericDomaineLMD(String value)
			throws Exception {

		List<RefDomaineLMDDto> result = new ArrayList<RefDomaineLMDDto>();
		try {
			result = refDomaineLMDService.findGeneric(value);
			log.debug("findGeneric DomaineLMD successful");
		} catch (Exception e) {
			log.error("findGeneric DomaineLMD  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * Permet de rechercher des Domaines LMD qui ressemblent a l'objet
	 * DomaineLMD dto pass� en param�tre.
	 * 
	 * @param refDomaineLMDDto
	 *            l'objet de contenant les crit�res de recherche
	 * @return liste des Domaines LMD qui r�pondent aux crit�res de recherche
	 *         contenus dans l'objet dto pass� en param�tre.
	 * @throws Exception
	 *             une exception est lev�e si l'appel au web service echou.
	 */
	@Override
	public List<RefDomaineLMDDto> findAdvancedDomaineLMD(
			RefDomaineLMDDto refDomaineLMDDto) throws Exception {

		List<RefDomaineLMDDto> result = new ArrayList<RefDomaineLMDDto>();
		try {
			result = refDomaineLMDService.findAdvanced(refDomaineLMDDto);
			log.debug("findAdvanced DomaineLMD successful");
		} catch (Exception e) {
			log.error("findAdvanced DomaineLMD  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public RefDomaineLMDDto findDomaineLMDByIdentifiant(String identifiant)
			throws Exception {

		try {
			return refDomaineLMDService.findByIdentifiant(identifiant);
		} catch (Exception e) {
			log.error("findAdvanced DomaineLMD  failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.getAllDomainesLMD] Method
	 * 
	 * @author rlaib on : 12 août 2014 10:08:12
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefDomaineLMDDto> getAllDomainesLMD() throws Exception {

		try {
			return refDomaineLMDService.findAll();
		} catch (Exception e) {
			log.error("getAllDomainesLMD DomaineLMD  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectDomLmdEtabDto> getAllDomainesLMDByEtablissement(
			Integer idEtab) throws Exception {

		try {
			return refDomaineLMDService.findDomainesLMDByEtablissement(idEtab);
		} catch (Exception e) {
			log.error("getAllDomainesLMD DomaineLMD  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectDomLmdEtabDto> getAllDomainesLMDByCodeEtablissement(
			String codeEtab) throws Exception {

		try {
			return refDomaineLMDService
					.findDomainesLMDByCodeEtablissement(codeEtab);
		} catch (Exception e) {
			log.error("getAllDomainesLMD DomaineLMD  failed", e);
			throw e;
		}
	}

	// ===================================================================
	// Implementation methods [FiliereLmd]
	// ===================================================================

	/**
	 * Permet de rechercher par texte des FiliereLmd
	 * 
	 * @param value
	 *            le text rechercher dans les FiliereLmd.
	 * @return liste des FiliereLmd contenant le text en paramtre dans une ou
	 *         plusieurs proprt�s
	 * @throws Exception
	 */
	@Override
	public List<RefFiliereLmdDto> findGenericFiliereLmd(String value)
			throws Exception {

		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			result = refFiliereLmdService.findGeneric(value);
			log.debug("findGeneric FiliereLmd successful");
		} catch (Exception e) {
			log.error("findGeneric FiliereLmd  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * Permet de rechercher des Filieres Lmd qui ressemblent a l'objetFiliereLmd
	 * dto pass� en param�tre.
	 * 
	 * @param refFiliereLmdDto
	 *            l'objet de contenant les crit�res de recherche
	 * @return liste des Filieres Lmd LMD qui r�pondent aux crit�res de
	 *         recherche contenus dans l'objet dto pass� en param�tre.
	 * @throws Exception
	 *             une exception est lev�e si l'appel au web service echou.
	 */
	@Override
	public List<RefFiliereLmdDto> findAdvancedFiliereLmd(
			RefFiliereLmdDto refFiliereLmdDto) throws Exception {

		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			result = refFiliereLmdService.findAdvanced(refFiliereLmdDto);
			log.debug("findAdvanced FiliereLmd successful");
		} catch (Exception e) {
			log.error("findAdvanced FiliereLmd  failed", e);
			throw e;
		}
		return result;
	}

	/**
	 * Cherche toutes les filières d'un domaine donné.
	 * 
	 */
	@Override
	public List<RefFiliereLmdDto> findFiliereByIdDomainelmd(Integer idDomaine)
			throws Exception {

		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			result = refFiliereLmdService.findByIdDomainelmd(idDomaine);
			log.debug("findFiliereByIdDomainelmd successful");
		} catch (Exception e) {
			log.error("findFiliereByIdDomainelmd failed", e);
			throw e;
		}
		return result;
	}

	/**
	 * Recherche les filières d'un domaine par son identifiant (le code métier)
	 * donnée.
	 * 
	 * @param domaineIdentifiant
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefFiliereLmdDto> findFiliereByCodeDomainelmd(String codeDomaine)
			throws Exception {
		List<RefFiliereLmdDto> result = new ArrayList<RefFiliereLmdDto>();
		try {
			result = refFiliereLmdService.findByCodeDomainelmd(codeDomaine);
			log.debug("findFiliereByCodeDomainelmd by identifiant successful");
		} catch (Exception e) {
			log.error("findFiliereByCodeDomainelmd by identifiant  failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public RefFiliereLmdDto findFiliereByIdentifiant(String identifiant)
			throws Exception {
		try {
			return refFiliereLmdService.findByIdentifiant(identifiant);
		} catch (Exception e) {
			log.error("findFiliereByIdentifiant by identifiant  failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.getAllFilieresLMD] Method
	 * 
	 * @author rlaib on : 12 août 2014 13:50:00
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefFiliereLmdDto> getAllFilieresLMD() throws Exception {

		try {
			return refFiliereLmdService.findAll();
		} catch (Exception e) {
			log.error("getAllFilieresLMD RefFiliereLmdDto  failed", e);
			throw e;
		}
	}

	// ===================================================================
	// Implementation methods [SpecialiteLmd]
	// ===================================================================

	/**
	 * Permet de rechercher par texte des SpecialiteLmd
	 * 
	 * @param value
	 *            le text rechercher dans les Specialite.
	 * @return liste des Specialité contenant le text en paramtre dans une ou
	 *         plusieurs proprt�s
	 * @throws Exception
	 */
	@Override
	public List<RefSpecialiteLmdDto> findGenericSpecialiteLmd(String value)
			throws Exception {

		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			result = refSpecialiteLmdService.findGeneric(value);
			log.debug("findGeneric SpecialiteLmd successful");
		} catch (Exception e) {
			log.error("findGeneric SpecialiteLmd  failed", e);
			throw e;

		}
		return result;

	}

	/**
	 * Permet de rechercher des Specialites Lmd qui ressemblent a
	 * l'objetSpecialiteLmd dto passé en paramètre.
	 * 
	 * @param refSpecialiteLmdDto
	 *            l'objet de contenant les crit�res de recherche
	 * @return liste des Specialites Lmd LMD qui r�pondent aux crit�res de
	 *         recherche contenus dans l'objet dto pass� en param�tre.
	 * @throws Exception
	 *             une exception est levée si l'appel au web service echou.
	 */
	@Override
	public List<RefSpecialiteLmdDto> findAdvancedSpecialiteLmd(
			RefSpecialiteLmdDto refSpecialiteLmdDto) throws Exception {

		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			result = refSpecialiteLmdService.findAdvanced(refSpecialiteLmdDto);
			log.debug("findAdvanced SpecialiteLmd successful");
		} catch (Exception e) {
			log.error("findAdvanced SpecialiteLmd  failed", e);
			throw e;
		}
		return result;
	}

	/**
	 * Recherche les Specialités d'une filière donnée.
	 * 
	 * @param idFiliere
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefSpecialiteLmdDto> findSpecialiteByIdFilierelmd(
			Integer idFiliere) throws Exception {

		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			result = refSpecialiteLmdService.findByIdFilierelmd(idFiliere);
			log.debug("findSpecialiteByIdFilierelmd successful");
		} catch (Exception e) {
			log.error("findSpecialiteByIdFilierelmd  failed", e);
			throw e;
		}
		return result;
	}

	/**
	 * Recherche les Specialités d'une filière par son identifiant (le code
	 * métier) donnée.
	 * 
	 * @param filiereIdentifiant
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefSpecialiteLmdDto> findSpecialiteByCodeFilierelmd(
			String codeFiliere) throws Exception {
		List<RefSpecialiteLmdDto> result = new ArrayList<RefSpecialiteLmdDto>();
		try {
			result = refSpecialiteLmdService.findByCodeFilierelmd(codeFiliere);
			log.debug("findSpecialiteByCodeFilierelmd successful");
		} catch (Exception e) {
			log.error("findSpecialiteByCodeFilierelmd failed", e);
			throw e;
		}
		return result;
	}

	@Override
	public RefSpecialiteLmdDto findSpecialiteByIdentifiant(String identifiant)
			throws Exception {
		try {
			return refSpecialiteLmdService.findByIdentifiant(identifiant);
		} catch (Exception e) {
			log.error("findSpecialiteByIdentifiant failed", e);
			throw e;
		}
	}

	/**
	 * [PpmWSImpl.getAllSpecialitesLMD] Method
	 * 
	 * @author rlaib on : 12 août 2014 13:50:55
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RefSpecialiteLmdDto> getAllSpecialitesLMD() throws Exception {

		try {
			return refSpecialiteLmdService.findAll();
		} catch (Exception e) {
			log.error("getAllSpecialitesLMD RefSpecialiteLmdDto  failed", e);
			throw e;
		}
	}

	// ===================================================================
	// Implementation methods [paramConfiguration]
	// ===================================================================

	@Override
	public RefParamDto getParamConfiguration(String key, String configuration)
			throws Exception {
		try {
			RefParamDto refParamDto = refParametrageServiceImpl
					.getParamConfiguration(key, configuration);
			log.debug("getParamConfiguration successful");
			return refParamDto;
		} catch (Exception e) {
			log.error("getParamConfiguration  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefTypePieceConstitutiveDto> findPieceConstitutiveByCodeTypeDossierDate(
			String codeTypeDossier, Date annee) throws Exception {

		try {
			List<RefTypePieceConstitutiveDto> result = new ArrayList<RefTypePieceConstitutiveDto>();
			result = refTypePieceConstitutiveService.findByCodeTypeDossierDate(
					codeTypeDossier, annee);
			log.debug("findPieceConstitutiveByCodeTypeDossierDate successful");
			return result;
		} catch (Exception e) {
			log.error("findPieceConstitutiveByCodeTypeDossierDate  failed", e);
			throw e;
		}

	}

	@Override
	public RefEtablissementDto findEtablissementByLcLatin(String lcLatin)
			throws Exception {
		RefEtablissementDto result = new RefEtablissementDto();
		try {
			result = refEtablissementService.findByLcLatin(lcLatin);
			log.debug("findEtablissementByLcLatin successful");
		} catch (Exception e) {
			log.error("findEtablissementByLcLatin failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public RefEtablissementDto findEtablissementByIdentifiant(String identifiant)
			throws Exception {
		RefEtablissementDto result = new RefEtablissementDto();
		try {
			result = refEtablissementService.findByIdentifiant(identifiant);
			log.debug("findEtablissementByIdentifiant successful");
		} catch (Exception e) {
			log.error("findEtablissementByIdentifiant failed", e);
			throw e;

		}
		return result;
	}

	@Override
	public RefGroupeDto findGroupeByCode(String code) throws Exception {
		try {
			RefGroupeDto refGroupeDto = refGroupeServiceImpl.findByCode(code);
			log.debug("findGroupeByCode successful");
			return refGroupeDto;
		} catch (Exception e) {
			log.error("findGroupeByCode  failed", e);
			throw e;
		}
	}

	@Override
	public RefGroupeDto saveOrUpdateGroupe(RefGroupeDto refGroupeDto)
			throws Exception {

		log.debug("start saveOrUpdateGroupe" + refGroupeDto);

		try {

			refGroupeDto = refGroupeServiceImpl.saveOrUpdate(refGroupeDto);
			log.debug("saveOrUpdateGroupe successful");
			return refGroupeDto;

		} catch (Exception e) {

			log.error("saveOrUpdateGroupe failed", e);
			throw e;
		}
	}

	@Override
	public RefAffectationDto saveOrUpdateAffectation(
			RefAffectationDto refAffectationDto) throws Exception {
		try {

			refAffectationDto = refAffectationService
					.saveOrUpdate(refAffectationDto);
			log.debug("saveOrUpdateAffectation successful");
			return refAffectationDto;

		} catch (Exception e) {

			log.error("saveOrUpdateAffectation failed", e);
			throw e;
		}

	}

	@Override
	public RefAffectationDto findGroupeParent(String entity, String codeGroupe)
			throws Exception {
		try {

			RefAffectationDto refAffectationDto = refAffectationService
					.findGroupeParent(entity, codeGroupe);
			log.debug("findGroupeParent successful");
			return refAffectationDto;

		} catch (Exception e) {

			log.error("findGroupeParent failed", e);
			throw e;
		}

	}

	@Override
	public void deleteAffectation(RefAffectationDto refAffectationDto)
			throws Exception {
		try {
			refAffectationService.delete(refAffectationDto);
			log.debug("deleteAffectation successful");
		} catch (Exception e) {
			log.error("deleteAffectation failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectationDto> findIndividuAffectation(String entityName,
			Integer idEntity) throws Exception {
		try {

			List<RefAffectationDto> list = refAffectationService.findIndividus(
					entityName, idEntity);
			log.debug("findIndividuAffectation successful");
			return list;

		} catch (Exception e) {

			log.error("findIndividuAffectation failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectationDto> findGroupeAffectation(String entityName,
			Integer idEntity) throws Exception {
		try {

			List<RefAffectationDto> list = refAffectationService.findGroupes(
					entityName, idEntity);
			log.debug("findGroupeAffectation successful");
			return list;

		} catch (Exception e) {

			log.error("findGroupeAffectation failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectFiliereLmdEtabDto> getAllFilieresLMDByEtablissement(
			int idEtab,String codeDomaine) throws Exception {

		try {
			return refFiliereLmdService.findFilieresLMDByEtablissement(idEtab, codeDomaine);
		} catch (Exception e) {
			log.error("getAllFilieresLMD FiliereLMD  failed", e);
			throw e;
		}
	}

	@Override
	public List<RefAffectationDto> findAffectationOfIndividu(Integer idIndividu)
			throws Exception {
		try {
			List<RefAffectationDto> list = refAffectationService
					.findAffectationByIdIndividu(idIndividu);
			log.debug("findAffectationOfIndividu successful");
			return list;

		} catch (Exception e) {

			log.error("findAffectationOfIndividu failed", e);
			throw e;
		}
	}

	

}
