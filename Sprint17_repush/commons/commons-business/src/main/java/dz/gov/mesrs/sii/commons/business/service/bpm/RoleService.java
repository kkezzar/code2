
 
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.HashMap;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.RoleI18nDto;





/**
 * @author BELDI Jamel  on : 5 mai 2014  11:19:37
 */
public interface RoleService {
    public  HashMap<String, RoleI18nDto> findListRoleI18nByRole(RoleDto roleDto) ;
    
  
}