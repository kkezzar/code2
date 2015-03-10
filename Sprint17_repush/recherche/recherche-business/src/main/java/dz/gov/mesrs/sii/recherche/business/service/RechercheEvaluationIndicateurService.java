/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationIndicateurDto;


/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheEvaluationIndicateurService {

	EvaluationIndicateurDto saveEvaluationIndicateur(EvaluationIndicateurDto evaluationIndicateurDto);

	List<EvaluationIndicateurDto> findListEvaluationIndicateurByIdProjet(Long id);

	EvaluationIndicateurDto findById(Long evaluationIndicateurDtoId);

	EvaluationIndicateurDto saveIndEvaluation(EvaluationIndicateurDto evaluationIndicateurDto);


}
