package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.CongeAcademiqueDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

public interface CongeAcademiqueService {

	public CongeAcademiqueDto insertOrUpdate(
			CongeAcademiqueDto congeAcademiqueDto);

	public void remove(CongeAcademiqueDto congeAcademiqueDto);

	public CongeAcademiqueDto findById(int id);

	public List<CongeAcademiqueDto> findAll();

	public CongeAcademiqueDto findByIdDossier(int idDossier);

	/**
	 * @author Mounir.MESSAOUDI on : 26 oct. 2014 13:46:29
	 * @param diaId
	 * @return
	 */
	CongeAcademiqueDto findByIdDossierInscriptionAdministrative(Integer diaId);

}