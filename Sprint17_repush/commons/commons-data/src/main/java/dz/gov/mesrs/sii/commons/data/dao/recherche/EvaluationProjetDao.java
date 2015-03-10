package dz.gov.mesrs.sii.commons.data.dao.recherche;

import dz.gov.mesrs.sii.commons.data.dao.fve.scolarite.GenericFveDao;
import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationProjet;

/**
 * @author rlaib  on : 16 d�c. 2014  13:58:17
 */
public interface EvaluationProjetDao extends GenericFveDao<EvaluationProjet> {

	EvaluationProjet findEvaluationProjetByIdProjet(Long id);

	

	
}
