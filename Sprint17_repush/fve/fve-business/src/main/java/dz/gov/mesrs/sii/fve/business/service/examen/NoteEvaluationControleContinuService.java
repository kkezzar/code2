package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.util.NoteMoyenneAp;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  NoteEvaluationControleContinuService {

	public  NoteEvaluationControleContinuDto insertOrUpdate( NoteEvaluationControleContinuDto noteEvaluationControleContinuDto);
	
	public  void remove( NoteEvaluationControleContinuDto noteEvaluationControleContinuDto);	
	
	public  NoteEvaluationControleContinuDto findById(long id);
	
	public  List<NoteEvaluationControleContinuDto> findAll() ;
	
	public  NoteEvaluationControleContinuDto findByEvalIdAndAffGp(Long evalId, Integer agpId);
	
	public  List<NoteEvaluationControleContinuDto> findByEvalId(Long evalId);
	
	public  List<NoteMoyenneAp> calculMoyenne(NoteEvaluationControleContinuDto noteEvaluationControleContinuDto);
	
	public  List<AtomePedagogiqueDto> findApOfCC(NoteEvaluationControleContinuDto noteEvaluationControleContinuDto);
	
	public  List<NoteEvaluationControleContinuDto> findAdvanced(NoteEvaluationControleContinuDto noteEvaluationControleContinuDto);

}