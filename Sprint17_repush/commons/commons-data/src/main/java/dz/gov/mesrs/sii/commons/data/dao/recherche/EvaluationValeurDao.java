package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationValeur;

public interface EvaluationValeurDao extends GenericFveDao<EvaluationValeur> {
	
	List<EvaluationValeur> findEvaluationValues(Long idEvaluation);
}
