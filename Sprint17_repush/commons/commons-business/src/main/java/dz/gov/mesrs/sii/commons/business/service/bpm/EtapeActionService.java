/**
 * [dz.gov.mesrs.sii.commons.business.service.habilitation.TacheService.java] 
 * @author rlaib on : 29 avr. 2014  16:33:43
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeActionDto;

/**
 * @author rlaib  on : 29 avr. 2014  16:33:43
 */
public interface EtapeActionService {

	public  EtapeActionDto insertOrUpdate( EtapeActionDto etapeActionDto);
	
	public  void remove( EtapeActionDto etapeActionDto);	
	
	public  EtapeActionDto findById(int id);
		
	public  List<EtapeActionDto> findAll() ;	

    
}
