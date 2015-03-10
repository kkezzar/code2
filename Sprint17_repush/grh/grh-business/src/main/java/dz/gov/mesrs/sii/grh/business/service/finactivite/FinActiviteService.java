package dz.gov.mesrs.sii.grh.business.service.finactivite;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.model.grh.FinActivite;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FinActiviteDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface Service FinActivite
 * 
 */
public interface FinActiviteService extends CommonService<FinActivite, FinActiviteDto, Long> {

	Date calculAgeRetraite(DossierEmployeDto dto);

	List<FinActiviteDto> findAllAdmisRetraite(Integer idEtablissement, String searchKeyWords, SearchMode searchMode);

	Integer countAllAdmisRetraite(Integer idEtablissement, String searchKeyWords);

	List<FinActiviteDto> findAllPendingDemission(Integer idEtablissement, String searchKeyWords, SearchMode searchMode);

	Integer countAllPendingDemission(Integer idEtablissement, String searchKeyWords);

	FinActiviteDto saveDemandeDemission(FinActiviteDto dto);

	FinActiviteDto saveDemandeRetraiteAnticipe(FinActiviteDto dto);

	List<FinActiviteDto> findAllPendingRetraiteAnticipe(Integer idEtablissement, String searchKeyWords,
			SearchMode searchMode);

	Integer countAllPendingRetraiteAnticipe(Integer idEtablissement, String searchKeyWords);

	void saveAdmissionRetraite(List<FinActiviteDto> toSaveDtos, List<FinActiviteDto> toDeleteDtos);

	FinActiviteDto saveCessationFinal(FinActiviteDto cessationActiviteDto);
	
	void validerCessationFinal(FinActiviteDto cessationActiviteDto);
	
	List<FinActiviteDto> findAllCessationFinalCree(Integer idEtablissement, String searchKeyWords, SearchMode searchMode);
	
	int countAllCessationFinalCree(Integer idEtablissement, String searchKeyWords);
	
	

}