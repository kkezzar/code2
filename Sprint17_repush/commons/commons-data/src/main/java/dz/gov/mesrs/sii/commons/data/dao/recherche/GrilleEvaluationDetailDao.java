package dz.gov.mesrs.sii.commons.data.dao.recherche;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluationDetail;

public interface GrilleEvaluationDetailDao extends GenericFveDao<GrilleEvaluationDetail> {
	
	List<GrilleEvaluationDetail> findGridDetails(Long idEvaluationGrid);
}
