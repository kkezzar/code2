package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;

/**
 * Services concernant les mots clés
 * 
 * @author yselmane on : 9 avr. 2014 10:51:00
 */
public interface RefMotCleService {
	/**
	 * 
	 * [RefMotCleService.save] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:22
	 * @param refMotCleDto
	 */
	public void save(RefMotCleDto refMotCleDto);

	/**
	 * 
	 * [RefMotCleService.insertOrUpdate] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:26
	 * @param refMotCleDto
	 * @return
	 */
	public RefMotCleDto insertOrUpdate(RefMotCleDto refMotCleDto);

	/**
	 * Service de suppression d'un mot clé [RefMotCleService.remove] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:30
	 * @param refMotCleDto
	 */
	public void remove(RefMotCleDto refMotCleDto);

	/**
	 * Service de suppression de tous les mots clés d'un document
	 * [RefMotCleService.removeMotsClesDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:35
	 * @param idDocument
	 *            identifiant du document
	 */
	public void removeMotsClesDocument(String idDocument);

	/**
	 * Srrvice de recherche d'un mot clé à partir de son identifiant
	 * [RefMotCleService.findById] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:39
	 * @param id
	 *            identifiant
	 * @return mot clé DTO
	 */
	public RefMotCleDto findById(int id);

	/**
	 * Service de recherche des mots clés à partir d'un requête HQL
	 * [RefMotCleService.findByQuery] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:44
	 * @param query
	 *            la requête HQL
	 * @return la liste de smots clés
	 */
	public List<RefMotCleDto> findByQuery(String query);

	/**
	 * service de recherche de tous les mots clés [RefMotCleService.findAll]
	 * Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:49
	 * @return la liste des mots clés
	 */
	public List<RefMotCleDto> findAll();

	/**
	 * Service de recherche des mots clés d'un document
	 * [RefMotCleService.findMotsClesDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:55
	 * @param idDocument
	 *            identifiant du document
	 * @return la liste de s mots clés
	 */
	public List<RefMotCleDto> findMotsClesDocument(String idDocument);

	/**
	 * Service de recherche d'un mot clé d'un document
	 * [RefMotCleService.findMotCleDocument] Method
	 * 
	 * @author yselmane on : 9 avr. 2014 10:51:59
	 * @param idDocument
	 *            identifiant du document
	 * @param idMotCle
	 *            identifiant du mot clé
	 * @return le mot clé trouvé
	 */
	public RefMotCleDto findMotCleDocument(String idDocument, Integer idMotCle);

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
	public List<NomenclatureDto> findNomenclatureMotsClesDocument(
			String idDocument);
}