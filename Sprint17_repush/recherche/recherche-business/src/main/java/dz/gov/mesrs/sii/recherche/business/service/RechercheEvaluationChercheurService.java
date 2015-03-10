package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationChercheurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.EvaluationValeurDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDetailDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.GrilleEvaluationDto;

public interface RechercheEvaluationChercheurService {
	
	GrilleEvaluationDto insertOrUpdate(GrilleEvaluationDto grilleEvaluationDto);
	GrilleEvaluationDto findById(Long id);
	GrilleEvaluationDetailDto findOneDetailById(Long id);
	List<GrilleEvaluationDto> findByKeyWords(String keyWord);
	List<GrilleEvaluationDto> findAllGrids();
	GrilleEvaluationDetailDto saveDetail(GrilleEvaluationDetailDto grilleEvaluationDetailDto);
	void removeDetail(Long idDetail);
	void removeOneGrid(Long idEvaluationGrid);
	List<GrilleEvaluationDetailDto> findGridDetails(Long idGrid);
	List<EvaluationChercheurDto> findEvaluationsByKeyWords(String keyWord);
	EvaluationChercheurDto findEvaluationById(Long idEvaluation);
	List<EvaluationValeurDto> findEvaluationValuesByEvaluationId(Long idEvaluation);
	EvaluationValeurDto findOneEvaluationValue(Long idEvaluationValue);
	EvaluationChercheurDto saveOneEvaluation(EvaluationChercheurDto evaluationChercheurDto);
	EvaluationValeurDto saveOneEvaluationValue(EvaluationValeurDto evaluationValeurDto);
	void removeOneEvaluationValue(Long idEvaluationValue);
}

