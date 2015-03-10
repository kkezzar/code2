package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;

public interface DossierInscriptionAdministrativeService {

	public DossierInscriptionAdministrativeDto insertOrUpdate(
			DossierInscriptionAdministrativeDto dossierInscriptionAdminDto);

	public void remove(
			DossierInscriptionAdministrativeDto dossierInscriptionAdminDto);

	public DossierInscriptionAdministrativeDto findById(int id);

	public List<DossierInscriptionAdministrativeDto> findAdvanced(
			DossierInscriptionAdministrativeDto dto, boolean loadOfInfo);

	public List<DossierInscriptionAdministrativeDto> findAdvanced(
			DossierInscriptionAdministrativeDto dto, String codeDoamine,
			String codeFiliere);

	public List<DossierInscriptionAdministrativeDto> findByMatriculeBac(
			DossierInscriptionAdministrativeDto dto);

	public List<DossierInscriptionAdministrativeDto> findDossierInscriptionsBy(
			int dossierEtudiantId);

	public DossierInscriptionAdministrativeDto findPresentDossierInscriptionForEtudiant(
			int dossierEtudiantId);

	/**
	 * Rechercher le dossier d'inscription administrative de l'annee academique
	 * precedente
	 * 
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 08:44:28
	 * @param dossierEtudiantId
	 * @return
	 */
	public DossierInscriptionAdministrativeDto findPrecdentDossierInscriptionForEtudiant(
			int dossierEtudiantId);

	public List<Object[]> findFiliere(DossierInscriptionAdministrativeDto dto);

	public List<Object[]> findDomaine(DossierInscriptionAdministrativeDto dto);

	public List<DossierInscriptionAdministrativeDto> findByAnneeAcademiqueAndEtab(
			int annee, Integer idEtab);

	public List<DossierInscriptionAdministrativeDto> findEtudiant(
			DossierInscriptionAdministrativeDto dto, Integer gpId,
			Integer sectionId);

	public List<DossierInscriptionAdministrativeDto> _findEtudiant(
			DossierInscriptionAdministrativeDto dto,
			GroupePedagogiqueDto groupePedagogiqueDto);

	/**
	 * @author Mounir.MESSAOUDI on : 20 oct. 2014 10:44:00
	 * @param diaSearch
	 * @param searchOuverturesOffreFormationBos
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	List<DossierInscriptionAdministrativeDto> findAdvancedByOuvertureOffres(
			DossierInscriptionAdministrativeDto diaSearch,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos);

	// public List<DossierInscriptionAdministrativeDto>
	// findEtudiantOfSection(DossierInscriptionAdministrativeDto dto, Integer
	// sectionId);

	/**
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 08:15:23
	 * @param diaDto
	 * @return
	 */
	public Long countByDia(DossierInscriptionAdministrativeDto diaDto);

	/**
	 * Recherche avancee multi criteres avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 08:16:32
	 * @param diaDto
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	public List<DossierInscriptionAdministrativeDto> findByDia(
			DossierInscriptionAdministrativeDto diaDto, String sortField,
			Integer pageSize, Integer first, Boolean descending);

	/**
	 * Recherche avancee multi criteres avec pagination
	 * 
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 08:16:32
	 * @param diaDto
	 * @param searchOuverturesOffreFormationDtos
	 * @param searchSitutationEntiteDtos
	 * @param sortField
	 * @param pageSize
	 * @param first
	 * @param descending
	 * @return
	 */
	public List<DossierInscriptionAdministrativeDto> findByDia(
			DossierInscriptionAdministrativeDto diaDto,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos,
			String sortField, Integer pageSize, Integer first,
			Boolean descending);

	/**
	 * @author Mounir.MESSAOUDI on : 29 oct. 2014 12:07:03
	 * @param diaDto
	 * @param searchOuverturesOffreFormationDtos
	 * @param searchSitutationEntiteDtos
	 * @return
	 */
	Long countByDia(
			DossierInscriptionAdministrativeDto diaDto,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos,
			List<SituationEntiteDto> searchSitutationEntiteDtos);

	/**
	 * @author Mounir.MESSAOUDI on : 6 nov. 2014 14:57:31
	 * @param dossierEtudiantId
	 * @return
	 */
	DossierInscriptionAdministrativeDto findDernierDossierInscriptionForEtudiant(
			int dossierEtudiantId);
	
	public List<DossierInscriptionAdministrativeDto> findStudentAdvanced(
			DossierInscriptionAdministrativeDto dto);
}