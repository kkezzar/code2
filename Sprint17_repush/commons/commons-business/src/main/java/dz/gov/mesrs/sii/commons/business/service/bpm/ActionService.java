
 
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.HashMap;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.ActionI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;


/**
 * @author BELDI Jamel  on : 4 mai 2014  14:17:31
 */
public interface ActionService {
	public  ActionDto findById(int id);
	public  HashMap<String, ActionI18nDto> findListActionI18nByAction(ActionDto actionDto) ;
    public  List<ActionI18nDto> findListActioni18nByEtape(EtapeActionDto etapeActionDto) ;
    public  List<ActionI18nDto> findListActionI18nByCodeEtape(String codeEtape) ;
    public  ActionDto findByCode(String code);
    
}
