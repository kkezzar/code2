/**
 * [dz.gov.mesrs.sii.referentiel.ws.service.DocumentWS.java] 
 * @author rlaib on : 12 mars 2014  14:36:39
 */
package dz.gov.mesrs.sii.referentiel.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;

/**
 * 
 * @author yselmane  on : 11 juin 2014  10:58:05
 */
@WebService
public interface DocumentWS {

	// ===================================================================
	// Methodes [Document]
	// ===================================================================
	
	/**
	 * Permet de faire une recherche générique [DocumentWS.findGenericDocument]
	 * Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:18:45
	 * @param value
	 *            texte de recherche
	 * @return liste des documents trouvés
	 * @throws Exception
	 */
	@WebMethod
	public List<RefDocumentDto> findGenericDocument(
			@WebParam(name = "value") String value) throws Exception;

	/**
	 * Permet de faire une recherche avancée [DocumentWS.findAdvancedDocument]
	 * Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:18:24
	 * @param refDocumentDto
	 * @param nomenclatureListDto
	 *            liste des mots clés
	 * @return liste des documents trouvés
	 * @throws Exception
	 */
	@WebMethod
	public List<RefDocumentDto> findAdvancedDocument(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto,
			@WebParam(name = "nomenclatureListDto") List<NomenclatureDto> nomenclatureListDto)
			throws Exception;

	/**
	 * Permet de chercher un document par son ID [DocumentWS.findById] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:20:32
	 * @param id
	 * @return le document trouvé par l'ID
	 * @throws Exception
	 */
	@WebMethod
	public RefDocumentDto findByIdDocument(@WebParam(name = "id") int id)
			throws Exception;

	/**
	 * Permets de chercher les documents d'une entité donnée
	 * [DocumentWS.findDocumentsOfEntity] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:21:10
	 * @param entity
	 *            nom de l'entité comme offreformation
	 * @param idEntity
	 *            l'identifiant dans l'entité
	 * @return la liste des documents trouvés
	 * @throws Exception
	 */
	@WebMethod
	public List<RefDocumentDto> findDocumentsOfEntity(
			@WebParam(name = "entity") String entity,
			@WebParam(name = "idEntity") String idEntity) throws Exception;

	/**
	 * Permet de générer le code d'un document [DocumentWS.generateCodeDocument]
	 * Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:22:11
	 * @param refDocumentDto
	 * @return le code
	 * @throws Exception
	 */

	@WebMethod
	public String generateCodeDocument(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto)
			throws Exception;

	/**
	 * Permet de vérifier l'existance d'un document qui a un titre ou référence
	 * déjà existante [DocumentWS.findByRefDocumentDto] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:31:43
	 * @param refDocumentDto
	 * @return bolléen trouvé ou non trouvé
	 * @throws Exception
	 */
	@WebMethod
	public Boolean findByRefDocumentDto(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto)
			throws Exception;

	/**
	 * Permet de persister un document [DocumentWS.save] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:22:58
	 * @param refDocumentDto
	 * @return l'ID du document après persistance
	 * @throws Exception
	 */
	@WebMethod
	public Integer saveDocument(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto)
			throws Exception;

	/**
	 * Permet de mettre à jour un document [DocumentWS.update] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:24:54
	 * @param refDocumentDto
	 * @throws Exception
	 */
	@WebMethod
	public void updateDocument(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto)
			throws Exception;

	/**
	 * Permet de supprimer un document [DocumentWS.remove] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 12:25:16
	 * @param refDocumentDto
	 * @throws Exception
	 */
	@WebMethod
	public void removeDocument(
			@WebParam(name = "refDocumentDto") RefDocumentDto refDocumentDto)
			throws Exception;

	/**
	 * Retourne tous les mots clés d'un document
	 * [DocumentWS.findMotsClesDocument] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 14:27:48
	 * @param idDocument
	 * @return liste des mots clés
	 * @throws Exception
	 */
	@WebMethod
	public List<RefMotCleDto> findMotsClesDocument(
			@WebParam(name = "idDocument") String idDocument) throws Exception;

	/**
	 * Service recherche des nomenclature mots clés d'un document, il est
	 * utilisé dans l'autocomplete
	 * [RefMotCleService.findNomenclatureMotsClesDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:52:05
	 * @param idDocument
	 *            identifiant du document
	 * @return la liste des mots clés nomenclature
	 */
	@WebMethod
	public List<NomenclatureDto> findNomenclatureMotsClesDocument(
			@WebParam(name = "idDocument") String idDocument) throws Exception;

	/**
	 * Service de suppression de tous les mots clés d'un document
	 * [RefMotCleService.removeMotsClesDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:35
	 * @param idDocument
	 *            identifiant du document
	 */
	@WebMethod
	public void removeMotsClesDocument(
			@WebParam(name = "idDocument") String idDocument) throws Exception;

	/**
	 * Sauvegarder un mot clé [DocumentWS.saveMotCle] Method
	 * 
	 * @author yselmane on : 24 avr. 2014 15:11:44
	 * @param refMotCleDto
	 * @throws Exception
	 */
	@WebMethod
	public void saveMotCle(
			@WebParam(name = "refMotCleDto") RefMotCleDto refMotCleDto)
			throws Exception;

	/**
	 * chercher un document par son identifiant [DocumentWS.findDocumentByCode]
	 * Method
	 * 
	 * @author yselmane on : 6 mai 2014 10:14:54
	 * @param code
	 * @return le document trouvé ou null
	 */
	@WebMethod
	public RefDocumentDto findDocumentByCode(
			@WebParam(name = "code") String code) throws Exception;


}
