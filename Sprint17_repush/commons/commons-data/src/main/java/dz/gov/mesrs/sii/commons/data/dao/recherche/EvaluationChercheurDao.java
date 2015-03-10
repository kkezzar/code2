package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationChercheur;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluation;

public interface EvaluationChercheurDao extends GenericFveDao<EvaluationChercheur> {
	
	List<EvaluationChercheur> findByKeyWords(String keyWord);
}
