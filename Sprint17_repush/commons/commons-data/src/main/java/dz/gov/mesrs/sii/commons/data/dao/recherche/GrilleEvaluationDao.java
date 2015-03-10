package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluation;

public interface GrilleEvaluationDao extends GenericFveDao<GrilleEvaluation> {
	
	List<GrilleEvaluation> findByKeyWords(String keyWord);
}
