package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantDto;

/**
 * @author Mounir.messaoudi
 * @version 1.0
 * @created 30-09-2014 16:44:07
 */
public interface VoeuEtudiantService {

	public VoeuEtudiantDto insertOrUpdate(VoeuEtudiantDto accessWilayaDto);

	public void remove(VoeuEtudiantDto accessWilayaDto);

	public VoeuEtudiantDto findById(int id);

	public List<VoeuEtudiantDto> findAll();

	public List<VoeuEtudiantDto> findAdvanced(VoeuEtudiantDto searchBo, List<SituationEntiteDto> searchSitutationEntiteBos);
		
	/**
	 * rechercher une fiche de voeux par annee academqiue et dossier etudiant
	 * @author Mounir.MESSAOUDI on : 2 oct. 2014 10:39:53 
	 * @param searchBo
	 * @return
	 */
	public VoeuEtudiantDto findByIdDosEtudIdAnnAcad(Integer idDossierEtudiant, Integer idAnneeAcademique);
}