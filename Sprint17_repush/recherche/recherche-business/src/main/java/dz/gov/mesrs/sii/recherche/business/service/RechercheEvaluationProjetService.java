/**
 * [dz.gov.mesrs.sii.recherche.business.service.RecherchePartenaireService.java] 
 * @author sRamdane  on : 08 fev 2015   17:33:39
 */
package dz.gov.mesrs.sii.recherche.business.service;

import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationProjetDto;


/**
 * @author sRamdane  on : 08 fev 2015  17:33:39
 */
public interface RechercheEvaluationProjetService {

	EvaluationProjetDto saveEvaluationProjet(EvaluationProjetDto evaluationProjetDto);

	EvaluationProjetDto findEvaluationProjetByIdProjet(Long id);


}
