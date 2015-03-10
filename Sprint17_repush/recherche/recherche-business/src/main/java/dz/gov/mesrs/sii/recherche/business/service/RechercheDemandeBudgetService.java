package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.recherche.business.model.dto.ActiviteBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeBudgetDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeCreditDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeEquipementDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.DemandeOperationDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ResultatAttenduDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;

public interface RechercheDemandeBudgetService {
	
	DemandeBudgetDto insertOrUpdate(DemandeBudgetDto demandeBudgetDto, Integer idCompte);
	DemandeBudgetDto findById(Long id);
	List<DemandeBudgetDto> findByKeyWords(String keyWord);
	List<DemandeBudgetDto> findDemandsOfProgram(Long idProgramme);
	Object saveItem(Object item, Integer idCompte);
	ActiviteBudgetDto findDemandActivityById(Long activityId);
	void removeDemandActivityById(Long activityId, Long idDemand, Integer idCompte);
	List<ActiviteBudgetDto> findDemandActivities(Long demandId);
	DemandeCreditDto findDemandCreditById(Long creditId);
	void removeDemandCreditById(Long creditId, Long idDemand, Integer idCompte);
	List<DemandeCreditDto> findDemandCredits(Long demandId);
	DemandeEquipementDto findDemandEquipmentById(Long equipmentId);
	void removeDemandEquipmentById(Long equipmentId, Long idDemand, Integer idCompte);
	List<DemandeEquipementDto> findDemandEquipments(Long demandId);
	List<Chapitre> findChapters();
	List<Article> findArticlesByChapterId(Integer chapterId);
	DemandeOperationDto findDemandOperationById(Long operationId);
	void removeDemandOperationById(Long operationId, Long idDemand, Integer idCompte);
	List<DemandeOperationDto> findDemandOperations(Long demandId);
	ResultatAttenduDto findDemandExpectedResultById(Long resultatId);
	void removeDemandExpectedResultById(Long resultatId, Long idDemand, Integer idCompte);
	List<ResultatAttenduDto> findDemandExpectedResults(Long demandId);
	DemandeBudgetDto submitDemand(DemandeBudgetDto demandeBudgetDto, Integer idCompte);
	DemandeBudgetDto validateDemand(DemandeBudgetDto demandeBudgetDto, Integer idCompte);
	List<DemandeBudgetDto> findDemandsToValidate(String roleCode);
}
