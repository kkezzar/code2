package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

public interface DossierEtudiantService {

	public DossierEtudiantDto insertOrUpdate(
			DossierEtudiantDto dossierEtudiantDto);

	public void remove(DossierEtudiantDto dossierEtudiantDto);

	public DossierEtudiantDto findById(int id);

	public List<DossierEtudiantDto> findByQuery(String query);

	public List<DossierEtudiantDto> findAll();

	public List<DossierEtudiantDto> findAdvanced(DossierEtudiantDto searchDto);

	public List<DossierEtudiantDto> findByIdIndividu(Integer id,
			Integer idEtablissment);

	/**
	 * Rechercher le derniere dossier d'etudiant enregistre par l'id de
	 * l'individu
	 * 
	 * @author Mounir.MESSAOUDI on : 30 oct. 2014 08:21:40
	 * @param idIndividu
	 * @return
	 */
	DossierEtudiantDto findLastByIdIndividu(Integer idIndividu);

	/**
	 * Recherche avancee avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:41:08
	 * @param searchDto
	 * @return
	 */
	public List<DossierEtudiantDto> findAdvanced(DossierEtudiantDto searchDto,
			String sortField, Integer pageSize, Integer first,
			Boolean descending);

	/**
	 * Nombre de resultats de la recherche avancee
	 * 
	 * @author Mounir.MESSAOUDI on : 5 nov. 2014 15:42:53
	 * @param searchDto
	 * @return
	 */
	public Long count(DossierEtudiantDto searchDto);

}