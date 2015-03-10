/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.PpmWS.java] 
 * @author rlaib on : 12 mars 2014  14:36:39
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

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

/**
 * @author rlaib on : 26 mars 2014 12:21:13
 */
@WebService
public interface PpmWS {

	// ===================================================================
	// Methodes [Individu]
	// ===================================================================
	/**
	 * [PpmWS.findGenericIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:21:31
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefIndividuDto> findGenericIndividu(
			@WebParam(name = "value") String value) throws Exception;

	/**
	 * [PpmWS.findIndividuById] Method
	 * 
	 * @author rlaib on : 10 avr. 2014 17:32:15
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public RefIndividuDto findIndividuById(
			@WebParam(name = "value") String value) throws Exception;

	/**
	 * [PpmWS.findAdvancedIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:21:39
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefIndividuDto> findAdvancedIndividu(
			@WebParam(name = "refIndividuDto") RefIndividuDto refIndividuDto)
			throws Exception;

	/**
	 * [PpmWS.saveIndividu] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:21:48
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public RefIndividuDto saveIndividu(
			@WebParam(name = "refIndividuDto") RefIndividuDto refIndividuDto)
			throws Exception;
	
	/**
	 * [PpmWS.saveIndividu] Method 
	 * @author rlaib  on : 25 juin 2014  08:26:28
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefIndividuDto> saveIndividus(
			@WebParam(name = "refIndividuDtos") List<RefIndividuDto> refIndividuDtos)
					throws Exception;

	/**
	 * [PpmWS.findByRefIndividuDto] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 11:37:08
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public RefIndividuDto findIndividuByRefIndividuDto(
			@WebParam(name = "refIndividuDto") RefIndividuDto refIndividuDto)
			throws Exception;

	/**
	 * [PpmWS.updateIndividu] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 11:45:21
	 * @param refIndividuDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public RefIndividuDto updateIndividu(
			@WebParam(name = "refIndividuDto") RefIndividuDto refIndividuDto)
			throws Exception;

	/**
	 * [PpmWS.findIndividuByIdentifiant] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:48:49
	 * @param identifiant
	 * @return
	 */
	@WebMethod
	public RefIndividuDto findIndividuByIdentifiant(
			@WebParam(name = "identifiant") String identifiant)
			throws Exception;

	// ===================================================================
	// Methodes [Structure]
	// ===================================================================
	// /**
	// * [PpmWS.findGenericStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:22:00
	// * @param value
	// * @return
	// * @throws Exception
	// */
	// @WebMethod
	// public List<RefStructureDto> findGenericStructure(
	// @WebParam(name = "value") String value) throws Exception;
	//
	// /**
	// * [PpmWS.findAdvancedStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:22:10
	// * @param refStructureDto
	// * @return
	// * @throws Exception
	// */
	// @WebMethod
	// public List<RefStructureDto> findAdvancedStructure(
	// @WebParam(name = "refStructureDto") RefStructureDto refStructureDto)
	// throws Exception;
	//
	// /**
	// * [PpmWS.saveStructure] Method
	// *
	// * @author rlaib on : 26 mars 2014 12:22:18
	// * @param refStructureDto
	// * @return
	// * @throws Exception
	// */
	// @WebMethod
	// public String saveStructure(
	// @WebParam(name = "refStructureDto") RefStructureDto refStructureDto)
	// throws Exception;

	// ===================================================================
	// Methodes [Groupe]
	// ===================================================================
	/**
	 * [PpmWS.findGenericGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:20:32
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefGroupeDto> findGenericGroupe(
			@WebParam(name = "value") String value) throws Exception;

	/**
	 * [PpmWS.findAdvancedGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:20:22
	 * @param refGroupeDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefGroupeDto> findAdvancedGroupe(
			@WebParam(name = "refGroupeDto") RefGroupeDto refGroupeDto)
			throws Exception;

	/**
	 * [PpmWS.saveGroupe] Method
	 * 
	 * @author rlaib on : 26 mars 2014 12:20:05
	 * @param refGroupeDto
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public String saveGroupe(
			@WebParam(name = "refGroupeDto") RefGroupeDto refGroupeDto)
			throws Exception;

	/**
	 * [PpmWS.findGroupeByCode] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 14:19:13
	 * @param code
	 * @return RefGroupeDto
	 * @throws Exception
	 */
	@WebMethod
	public RefGroupeDto findGroupeByCode(@WebParam(name = "code") String code)
			throws Exception;

	/**
	 * [PpmWS.findGroupeByCode] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 14:19:13
	 * @param code
	 * @return RefGroupeDto
	 * @throws Exception
	 */
	@WebMethod
	public RefGroupeDto saveOrUpdateGroupe(
			@WebParam(name = "refGroupeDto") RefGroupeDto refGroupeDto)
			throws Exception;

	// ===================================================================
	// Methodes [Contrat]
	// ===================================================================
	@WebMethod
	public List<RefContratDto> findGenericContrat(
			@WebParam(name = "value") String value) throws Exception;

	/**
	 * [PpmWS.getContractByCode] Method
	 * 
	 * @author rlaib on : 10 avr. 2014 17:35:54
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public RefContratDto getContractByCode(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefContratDto> findAdvancedContrat(
			@WebParam(name = "refContratDto") RefContratDto refContratDto)
			throws Exception;

	// Added By Rafik On Monday 07/04/2014 : 09:55.
	public List<RefPartenaireDto> getPartnersByContractCode(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public String saveContrat(
			@WebParam(name = "refContratDto") RefContratDto refContratDto)
			throws Exception;

	// ===================================================================
	// Methodes [Avenant]
	// ===================================================================
	@WebMethod
	public List<RefContratDto> findGenericAvenant(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefContratDto> findAdvancedAvenant(
			@WebParam(name = "refContratDto") RefContratDto refContratDto)
			throws Exception;

	@WebMethod
	public String saveAvenant(
			@WebParam(name = "refContratDto") RefContratDto refContratDto)
			throws Exception;

	// ===================================================================
	// Methodes [Evenement]
	// ===================================================================

	// @WebMethod
	// public List<RefEvenementDto> findGenericEvenement(
	// @WebParam(name = "value") String value) throws Exception;
	//
	// @WebMethod
	// public List<RefEvenementDto> findAdvancedEvenement(
	// @WebParam(name = "refEvenementDto") RefEvenementDto refEvenementDto)
	// throws Exception;

	// ===================================================================
	// Methodes [Permission]
	// ===================================================================
	//
	// @WebMethod
	// public List<RefPermissionDto> findGenericPermission(
	// @WebParam(name = "value") String value) throws Exception;
	//
	// @WebMethod
	// public RefPermissionDto findByIdPermission(@WebParam(name = "id") Integer
	// id);
	//
	// @WebMethod
	// public void saveOrUpdatePermission(
	// @WebParam(name = "refPermissionDto") RefPermissionDto refPermissionDto);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByNamePermission(
	// @WebParam(name = "ncName") String ncName) throws Exception;
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdfIndividuPermission(
	// @WebParam(name = "identifiant") String identifiant);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdfIndividuAndRolePermission(
	// @WebParam(name = "idRole") int idRole,
	// @WebParam(name = "identifiant") String identifiant);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdModulePermission(
	// @WebParam(name = "idRole") int idRole, @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdDomainePermission(
	// @WebParam(name = "idRole") int idRole, @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdfGroupePermission(
	// @WebParam(name = "idf") String idf);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdfStructurePermission(
	// @WebParam(name = "idf") String idf);
	//
	// @WebMethod
	// public RefPermissionDto findByIdFonctionPermission(
	// @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public RefPermissionDto findByIdFonctionAndRolePermission(
	// @WebParam(name = "idRole") int idRole, @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public RefPermissionDto findByIdFonctionAndIndividuPermission(
	// @WebParam(name = "idfIndividu") String idfIndividu,
	// @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdActionPermission(
	// @WebParam(name = "idRole") int idRole, @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public List<RefPermissionDto> findActionsPermission(
	// @WebParam(name = "idRole") int idRole, @WebParam(name = "id") int id);
	//
	// @WebMethod
	// public List<RefPermissionDto> findByIdRolePermission(
	// @WebParam(name = "idf") int idf);
	//
	// @WebMethod
	// public List<RefIndividuDto> findModelePermission(
	// @WebParam(name = "identifiant") String identifiant,
	// @WebParam(name = "idfAffectation") int idfAffectation);
	//
	// @WebMethod
	// public RefAffectationDto findAffectationModelePermission(
	// @WebParam(name = "identifiant") String identifiant,
	// @WebParam(name = "idfAffectation") int idfAffectation);

	// ===================================================================
	// Methodes [Lieu]
	// ===================================================================

	@WebMethod
	public List<RefLieuDto> findGenericLieu(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefLieuDto> findAdvancedLieu(
			@WebParam(name = "refLieuDto") RefLieuDto refLieuDto)
			throws Exception;

//	@WebMethod
//	public RefLieuDto findLieuById(@WebParam(name = "id") Integer id)
//			throws Exception;
	@WebMethod
	public List<RefLieuDto> findAdvancedLieuByEtab(@WebParam(name = "etabId")Integer etabId, @WebParam(name = "refLieuDto") RefLieuDto refLieuDto) throws Exception;
	
	@WebMethod
	public RefLieuDto findLieuByCode(@WebParam(name = "code") String Code)
			throws Exception;

	// ===================================================================
	// Methodes [Etablissement]
	// ===================================================================

	@WebMethod
	public List<RefEtablissementDto> findGenericEtablissement(
			@WebParam(name = "value") String value) throws Exception;

	// @WebMethod
	// public List<RefEtablissementDto> findAdvancedEtablissement(
	// @WebParam(name = "refEtablissementDto") RefEtablissementDto
	// refEtablissementDto)
	// throws Exception;
	//
	@WebMethod
	public RefEtablissementDto findEtablissementByLcLatin(
			@WebParam(name = "lcLatin") String lcLatin) throws Exception;

	@WebMethod
	public RefEtablissementDto findEtablissementByIdentifiant(
			@WebParam(name = "identifiant") String identifiant)
			throws Exception;

	// ===================================================================
	// Methodes [DomaineLMd]
	// ===================================================================

	@WebMethod
	public List<RefDomaineLMDDto> findGenericDomaineLMD(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefDomaineLMDDto> findAdvancedDomaineLMD(
			@WebParam(name = "refDomaineLMDDto") RefDomaineLMDDto refDomaineLMDDto)
			throws Exception;

	@WebMethod
	public RefDomaineLMDDto findDomaineLMDByIdentifiant(
			@WebParam(name = "identifiant") String identifiant)
			throws Exception;
	
	/**
	 * [PpmWS.getAllDomainesLMD] Method 
	 * @author rlaib  on : 12 août 2014  13:49:41
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefDomaineLMDDto> getAllDomainesLMD()
					throws Exception;
	
	@WebMethod
	public List<RefAffectDomLmdEtabDto> getAllDomainesLMDByEtablissement(Integer idEtab)
			throws Exception;

	// ===================================================================
	// Methodes [FiliereLmd]
	// ===================================================================

	@WebMethod
	public List<RefFiliereLmdDto> findGenericFiliereLmd(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefFiliereLmdDto> findAdvancedFiliereLmd(
			@WebParam(name = "refFiliereLmdDto") RefFiliereLmdDto refFiliereLmdDto)
			throws Exception;

	/**
	 * Recherche les filières d'un domaine de formation donné.
	 * 
	 * @param idDomaine
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	List<RefFiliereLmdDto> findFiliereByIdDomainelmd(
			@WebParam(name = "idDomaine") Integer idDomaine) throws Exception;

	/**
	 * Recherche les filières d'un domaine par son identifiant (le code métier)
	 * donnée.
	 * 
	 * @param domaineIdentifiant
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	List<RefFiliereLmdDto> findFiliereByCodeDomainelmd(
			@WebParam(name = "codeDomaine") String codeDomaine)
			throws Exception;

	/**
	 * Obtient la filière dont le code est passé en paramètre.
	 * 
	 * @param codeFiliere
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	RefFiliereLmdDto findFiliereByIdentifiant(
			@WebParam(name = "codeFiliere") String identifiant)
			throws Exception;

	/**
	 * [PpmWS.getAllFilieresLMD] Method 
	 * @author rlaib  on : 12 août 2014  13:49:35
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefFiliereLmdDto> getAllFilieresLMD()
					throws Exception;
	
	@WebMethod
	public List<RefAffectFiliereLmdEtabDto> getAllFilieresLMDByEtablissement(int idEtab, String codeDomaine)
			throws Exception;
	// ===================================================================
	// Methodes [SpecialiteLmd]
	// ===================================================================

	@WebMethod
	public List<RefSpecialiteLmdDto> findGenericSpecialiteLmd(
			@WebParam(name = "value") String value) throws Exception;

	@WebMethod
	public List<RefSpecialiteLmdDto> findAdvancedSpecialiteLmd(
			@WebParam(name = "refSpecialiteLmdDto") RefSpecialiteLmdDto refSpecialiteLmdDto)
			throws Exception;

	/**
	 * Recherche les Specialités d'une filière donnée.
	 * 
	 * @param idFiliere
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	List<RefSpecialiteLmdDto> findSpecialiteByIdFilierelmd(
			@WebParam(name = "idFiliere") Integer idFiliere) throws Exception;

	/**
	 * Recherche les Specialités d'une filière par son identifiant (le code
	 * métier) donnée.
	 * 
	 * @param filiereIdentifiant
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	List<RefSpecialiteLmdDto> findSpecialiteByCodeFilierelmd(
			@WebParam(name = "codeFiliere") String codeFiliere)
			throws Exception;

	/**
	 * Obtient la spécialité dont le code est passé en paramètre
	 * 
	 * @param codeSpecialite
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	RefSpecialiteLmdDto findSpecialiteByIdentifiant(
			@WebParam(name = "identifiant") String identifiant)
			throws Exception;

	/**
	 * [PpmWS.getAllSpecialitesLMD] Method 
	 * @author rlaib  on : 12 août 2014  13:49:27
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	public List<RefSpecialiteLmdDto> getAllSpecialitesLMD()
					throws Exception;
	// ===================================================================
	// Methodes [Param de Config]
	// ===================================================================

	@WebMethod
	public RefParamDto getParamConfiguration(
			@WebParam(name = "key") String key,
			@WebParam(name = "configuration") String configuration)
			throws Exception;

	// ===================================================================
	// Methodes [Pièce constitutives]
	// ===================================================================
	@WebMethod
	public List<RefTypePieceConstitutiveDto> findPieceConstitutiveByCodeTypeDossierDate(
			@WebParam(name = "codeTypeDossier") String codeTypeDossier,
			@WebParam(name = "annee") Date annee) throws Exception;

	// ===================================================================
	// Methodes [Affectations]
	// ===================================================================
	@WebMethod
	public RefAffectationDto saveOrUpdateAffectation(
			@WebParam(name = "refAffectationDto") RefAffectationDto refAffectationDto)
			throws Exception;
	
	@WebMethod  
	public RefAffectationDto findGroupeParent(
			@WebParam(name = "entity") String entity,
			@WebParam(name = "codeGroupe") String codeGroupe) throws Exception;
	
	@WebMethod  
	public void deleteAffectation(
			@WebParam(name = "refAffectationDto") RefAffectationDto refAffectationDto)
					throws Exception;
	
	@WebMethod
	public List<RefAffectationDto> findIndividuAffectation(
			@WebParam(name = "entityName") String entityName,
			@WebParam(name = "idEntity") Integer idEntity)	throws Exception;
	
	@WebMethod
	public List<RefAffectationDto> findGroupeAffectation(
			@WebParam(name = "entityName") String entityName,
			@WebParam(name = "idEntity") Integer idEntity)	throws Exception;
	
	@WebMethod
	public List<RefAffectationDto> findAffectationOfIndividu(
			@WebParam(name = "idIndividu") Integer idIndividu)	throws Exception;

	List<RefAffectDomLmdEtabDto> getAllDomainesLMDByCodeEtablissement(
			String codeEtab) throws Exception;
 
}
