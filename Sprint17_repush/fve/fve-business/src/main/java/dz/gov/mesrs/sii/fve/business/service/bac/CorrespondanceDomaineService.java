/**
 * [dz.gov.mesrs.sii.fve.business.service.bac.BacService.java] 
 * @author  Rafik LAIB on : 21 mai 2014  15:11:07
 */
package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.CorrespondanceDomaineDto;

/**
 * @author  Rafik LAIB  on : 21 mai 2014  15:11:07
 */
public interface CorrespondanceDomaineService {

	
	public  List<CorrespondanceDomaineDto> getTableCorrespondanceDomaines();
	public  List<CorrespondanceDomaineDto> getFilieres();
	public  List<CorrespondanceDomaineDto> getFilieresByEtab(String codeEtab, String year);
	

}
