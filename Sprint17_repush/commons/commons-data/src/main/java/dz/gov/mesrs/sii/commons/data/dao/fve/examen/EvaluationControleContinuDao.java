package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.EvaluationControleContinu;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface EvaluationControleContinuDao {

	public  void persist(EvaluationControleContinu transientInstance);

	public  void remove(EvaluationControleContinu persistentInstance);

	public  EvaluationControleContinu merge(EvaluationControleContinu detachedInstance);

	public  EvaluationControleContinu findById(long id);
		
	public  List<EvaluationControleContinu> findAll();
	
	public  List<EvaluationControleContinu> findAdvanced(EvaluationControleContinu evaluationControleContinu);
	
	public  EvaluationControleContinu findByGpIdAndDateEval(Long evalId, Integer gpId, Date dateEval);
	
	public  List<EvaluationControleContinu>findByGpAssociation(Long evalId, Integer gpAssocId);
	

}