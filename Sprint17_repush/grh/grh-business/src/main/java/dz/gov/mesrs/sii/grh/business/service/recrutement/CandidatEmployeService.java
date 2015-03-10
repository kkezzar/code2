package dz.gov.mesrs.sii.grh.business.service.recrutement;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.CandidatEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.CandidatEmployeDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Interface Service CandidatEmploye
 * 
 */

public interface CandidatEmployeService extends CommonService<CandidatEmploye, CandidatEmployeDto, Integer> {
	/**
	 * Valider les candidats et créer leurs dossiers
	 */
	public void validateAll(List<CandidatEmployeDto> listCandidatEmployeDtos);

}