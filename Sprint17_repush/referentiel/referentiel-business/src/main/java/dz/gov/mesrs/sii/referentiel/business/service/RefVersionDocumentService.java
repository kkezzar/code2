package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefVersionDocumentDto;

/**
 * Classe de services qui nconcerne les versions d'un documents
 * 
 * @author yselmane on : 9 avr. 2014 10:44:36
 */
public interface RefVersionDocumentService {
	/**
	 * Service de sauvegarde d'une version d'un document
	 * [RefVersionDocumentService.save] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:45:04
	 * @param refVersionDocumentDto
	 */
	public void save(RefVersionDocumentDto refVersionDocumentDto);

	/**
	 * Service de sauvegarde ou de mise à jour d'une version d'un document
	 * [RefVersionDocumentService.insertOrUpdate] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:45:27
	 * @param refVersionDocumentDto
	 * @return la version du document
	 */
	public RefVersionDocumentDto insertOrUpdate(
			RefVersionDocumentDto refVersionDocumentDto);

	/**
	 * Service de suppression d'une version d'un document
	 * [RefVersionDocumentService.remove] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:46:07
	 * @param refVersionDocumentDto
	 */
	public void remove(RefVersionDocumentDto refVersionDocumentDto);

	/**
	 * Service de recherche d'une version d'un document à partir d'un
	 * identifiant [RefVersionDocumentService.findById] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:46:33
	 * @param id
	 *            l'identifiant de la version du document
	 * @return la version du docuemnt
	 */
	public RefVersionDocumentDto findById(int id);

	/**
	 * Serrvice de recherche des versions documentsà partir d'une requête HQL
	 * [RefVersionDocumentService.findByQuery] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:47:22
	 * @param query
	 *            la requête HQL
	 * @return la liste des versions documents
	 */
	public List<RefVersionDocumentDto> findByQuery(String query);

	/**
	 * Service de recherche de toutes les versions des documents
	 * [RefVersionDocumentService.findAll] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:49:31
	 * @return la liste des versions document
	 */
	public List<RefVersionDocumentDto> findAll();

	/**
	 * Service de recher des version docuemnst pour un document donné
	 * [RefVersionDocumentService.findVersionsOfDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:50:04
	 * @param idDocument
	 *            l'identifiant du document
	 * @return la liste des versions document
	 */
	public List<RefVersionDocumentDto> findVersionsOfDocument(String idDocument);

}