package dz.gov.mesrs.sii.commons.data.dao.fve.examen;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEvaluationControleContinu;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;
import dz.gov.mesrs.sii.commons.data.util.NoteMoyenneAp;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 

public interface NoteEvaluationControleContinuDao {

	public  void persist(NoteEvaluationControleContinu transientInstance);

	public  void remove(NoteEvaluationControleContinu persistentInstance);

	public  NoteEvaluationControleContinu merge(NoteEvaluationControleContinu detachedInstance);

	public  NoteEvaluationControleContinu findById(long id);
		
	public  List<NoteEvaluationControleContinu> findAll();
	
	public  NoteEvaluationControleContinu findByEvalIdAndAffGp(Long evalId, Integer agpId);
	
	public  List<NoteEvaluationControleContinu> findByEvalId(long evalId);
	
	public  List<NoteMoyenneAp> calculMoyenne(NoteEvaluationControleContinu necc);
	
	public  List<AtomePedagogique> findApOfCC(NoteEvaluationControleContinu necc);
	
	public  List<NoteEvaluationControleContinu> findAdvanced(NoteEvaluationControleContinu necc);

}