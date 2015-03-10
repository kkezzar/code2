package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;

/**
 * 
 * @author yselmane on : 9 avr. 2014 10:44:10
 */
public interface RefDocumentService {
	/**
	 * Service de sauvegarde ou de mise à jour d'un document
	 * [RefDocumentService.insertOrUpdate] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:43:20
	 * @param refDocumentDto
	 *            le document objet de persistance ou de mise à jour
	 * @return le document persisté
	 */
	public RefDocumentDto insertOrUpdate(RefDocumentDto refDocumentDto);

	/**
	 * Service de suppression d'un document [RefDocumentService.remove] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:43:03
	 * @param refDocumentDto
	 */
	public void remove(RefDocumentDto refDocumentDto);

	/**
	 * Service de recherche d'un document à partir d'un identifiant
	 * [RefDocumentService.findById] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:42:41
	 * @param id
	 * @return
	 */
	public RefDocumentDto findById(int id);

	/**
	 * Service de rechercche des documents à partir d'une requête HQL
	 * [RefDocumentService.findByQuery] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:41:59
	 * @param query
	 *            la requête HQl
	 * @return la liste des documents
	 */
	public List<RefDocumentDto> findByQuery(String query);

	/**
	 * Service de recherche de tous les documents [RefDocumentService.findAll]
	 * Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:41:31
	 * @return la liste de tous les documents
	 */
	public List<RefDocumentDto> findAll();

	/**
	 * Service de recherche générique d'un document à partir d'un texte libre
	 * [RefDocumentService.findGeneric] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:40:36
	 * @param value
	 *            le paramètre de recherche
	 * @return la liste des documents trouvés
	 */
	public List<RefDocumentDto> findGeneric(String value);

	/**
	 * Service de recherche avancée d'un document
	 * [RefDocumentService.findAdvanced] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:39:40
	 * @param refDocumentDto
	 *            les paramètres de recherche qui sont dans l'objet document DTO
	 * @param nomenclatureListDto
	 *            la liste des mots clés
	 * @return la liste des documents DTO
	 */
	public List<RefDocumentDto> findAdvanced(RefDocumentDto refDocumentDto,
			List<NomenclatureDto> nomenclatureListDto);

	/**
	 * Service de recherche d'un document X, il renvoie un booléen
	 * [RefDocumentService.findByRefDocumentDto] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:38:00
	 * @param refDocumentDto
	 *            document DTO, objet de cette recherche
	 * @return Booléen
	 */
	public Boolean findByRefDocumentDto(RefDocumentDto refDocumentDto);

	/**
	 * Service de sauvegarde d'un document [RefDocumentService.save] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:38:56
	 * @param refDocumentDto
	 * @return l'identifiant du document sauvegardé
	 */
	public Integer save(RefDocumentDto refDocumentDto);

	/***
	 * Service de mise à jour d'un document [RefDocumentService.update] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:37:21
	 * @param refDocumentDto
	 *            document DTO
	 */
	public void update(RefDocumentDto refDocumentDto);

	/**
	 * Service qui renvoie la liste des document d'une entité donnée, par
	 * exemple la liste des documents de l'individu XXX
	 * [RefDocumentService.findDocumentsOfEntity] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:35:21
	 * @param entity
	 *            le nom de l'entite : individu, structure , contrat...
	 * @param idEntity
	 *            identifiant de l'entité
	 * @return
	 */
	public List<RefDocumentDto> findDocumentsOfEntity(String entity,
			String idEntity);

	/**
	 * Renvoie la liste des documents [RefDocumentService.findFilsOfDocument]
	 * Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:34:21
	 * @param refDocumentDto
	 * @return liste des fils d'un document
	 */
	public List<RefDocumentDto> findFilsOfDocument(RefDocumentDto refDocumentDto);
	
	
	/**
	 * Renvoi un Document DTO qui a le code code
	 * [RefDocumentService.findDocumentByCode] Method 
	 * @author yselmane  on : 14 avr. 2014  15:13:05
	 * @param code code document
	 * @return Document DTO
	 */
	public RefDocumentDto findDocumentByCode(String code);
	
	
	/**
	 * Renvoie le dernier document enregistré par type document
	 * [RefDocumentService.findMaxDocumentByType] Method
	 * 
	 * @author yselmane on : 13 avr. 2014 17:57:06
	 * @param typeDocumentId
	 *            typedocument Id dans la nomenclature
	 * @return Document trouvé
	 */
	public RefDocumentDto findMaxDocumentByType(RefDocumentDto refDocumentDto);
	
	
	/**
	 * renvoie le code du document [DocumentComponentBean.getCodeDocument]
	 * Method
	 * 
	 * @author yselmane on : 13 avr. 2014 18:20:12
	 * @param typeDocumentId
	 * @return code document
	 */
	
	public String generateCodeDocument(RefDocumentDto refDocumentDto);
	
	public List<RefDocumentDto> findGeneric(String etabCode, String value);
	
	public List<RefDocumentDto> findAdvanced(String etabCode, RefDocumentDto refDocumentDto,
			List<NomenclatureDto> nomenclatureListDto);

}