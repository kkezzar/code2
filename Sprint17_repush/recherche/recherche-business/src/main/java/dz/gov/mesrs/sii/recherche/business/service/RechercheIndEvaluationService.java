/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.IndEvaluationDto;

/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheIndEvaluationService {

	IndEvaluationDto saveIndEvaluation(IndEvaluationDto indEvaluationDto);

	List<IndEvaluationDto> findListIndicateurByIdProjet(Long id);

	IndEvaluationDto findById(Long indevaluationId);

	void removeIndEvaluation(Long indevaluationId);

		
	
}
