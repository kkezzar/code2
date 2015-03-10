/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EtapeValidationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProjetDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheEtapeValidationService {

	
	List<EtapeDto> findListEtapesByIdProcesus(Integer processusId);	

	void removeListEtapeValidationByProjet(Long selectedProjet);

	void saveListEtapeValidationProjet(List<EtapeDto> listEtapes,
			ProjetDto selectedProjet);

	List<EtapeValidationDto> findListEtapeValidationByProjet(Long selectedProjet);

	EtapeValidationDto findById(Long validationId);

	EtapeValidationDto saveValidation(EtapeValidationDto etapeValidationDto);

	boolean findNonAcceptationValidation(Long selectedProjet,String codeValidation);
	
	
}
