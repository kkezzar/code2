package dz.gov.mesrs.sii.grh.business.service.evaluation;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationPeriodeDto;

public interface EvaluationPeriodeService {

	EvaluationPeriodeDto saveEvaluationPeriode(EvaluationPeriodeDto dto);

	EvaluationPeriodeDto cloturerEvaluationPeriode(EvaluationPeriodeDto dto);

	List<EvaluationPeriodeDto> findAllEvalutionPeriodeNonCloture(Date dateDebutPeriode, Date dateFinPeriode,
			Integer etablissementId, SearchMode searchMode);

	int countAllEvalutionPeriodeNonCloture(Date dateDebutPeriode, Date dateFinPeriode, int etablissementId);

	EvaluationPeriodeDto findLastPeriodeEvaluation(int etablissementId);
}
