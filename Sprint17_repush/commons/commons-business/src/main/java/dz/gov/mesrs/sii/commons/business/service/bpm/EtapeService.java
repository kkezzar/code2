
 
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.HashMap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeRoleDto;





/**
 * @author BELDI Jamel  on : 5 mai 2014  11:19:37
 */
public interface EtapeService {
    public  HashMap<String, EtapeI18nDto> findListEtapeI18nByEtape(EtapeDto etapeDto) ;
    
    public EtapeDto findEtapeByCode(String etapeCode);
    
    public EtapeActionDto findByCodeEtapeByCodeAction(String codeEtape, String codeAction);
    
    public EtapeRoleDto findByCodeEtapeByCodeRole(String codeEtape, String codeRole);
}