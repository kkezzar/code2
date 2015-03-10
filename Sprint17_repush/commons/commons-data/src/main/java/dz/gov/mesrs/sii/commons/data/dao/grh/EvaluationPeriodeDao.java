package dz.gov.mesrs.sii.commons.data.dao.grh;

import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationPeriode;

public interface EvaluationPeriodeDao extends CommonDao<EvaluationPeriode, Long> {

	EvaluationPeriode findLastPeriodeEvaluation(int etablissementId);

}
