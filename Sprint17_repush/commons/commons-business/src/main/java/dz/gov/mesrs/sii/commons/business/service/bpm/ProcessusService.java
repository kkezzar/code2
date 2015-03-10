/**
 * [dz.gov.mesrs.sii.commons.business.service.bpm.ProcessusService.java] 
 * @author rlaib on : 15 janv. 2015  13:56:44
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ProcessusDto;

/**
 * @author rlaib  on : 15 janv. 2015  13:56:44
 */
public interface ProcessusService {

	ProcessusDto insertOrUpdate(ProcessusDto processusDto);
	void remove(Integer id);	
	ProcessusDto findById(int id);
	List<ProcessusDto> findByCodeDomaine(String codeDomaine);
	List<ProcessusDto> findByCodeEntite(String codeEntite);
	List<ProcessusDto> findAll() ;
	List<ProcessusDto> findByKeyWords(String keyWord) ;
	void removeOneStep(Integer idStep);	
	EtapeDto saveOneStepValidation(EtapeDto etapeDto);
	EtapeDto findStepById(Integer id);
	List<EtapeDto> findStepsOfProcess(Integer idProcessus);
	EntiteDto findEntityByCode(String code);
	List<EntiteDto> findEntityByCodeDomaine(String codeDomaine);
	List<ActionEntiteDto> findActionsOfEntity(String entityCode);
}
