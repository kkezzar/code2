package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.EvaluationControleContinuDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  EvaluationControleContinuService {

	public  EvaluationControleContinuDto insertOrUpdate( EvaluationControleContinuDto evaluationControleContinuDto);
	
	public  void remove( EvaluationControleContinuDto evaluationControleContinuDto);	
	
	public  EvaluationControleContinuDto findById(long id);
	
	public  List<EvaluationControleContinuDto> findAll() ;	
	
	public List<EvaluationControleContinuDto> findAdvanced(EvaluationControleContinuDto evaluationControleContinuDto);
	
	public  EvaluationControleContinuDto findByGpIdAndDateEval(Long evalId, Integer gpId, Date dateEval);
	
	public List<EvaluationControleContinuDto> findByGpAssociation(Long evalId, Integer gpAssocId);

}