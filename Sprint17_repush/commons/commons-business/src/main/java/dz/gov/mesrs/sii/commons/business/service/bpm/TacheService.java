/**
 * [dz.gov.mesrs.sii.commons.business.service.habilitation.TacheService.java] 
 * @author rlaib on : 29 avr. 2014  16:33:43
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheDto;

/**
 * @author rlaib  on : 29 avr. 2014  16:33:43
 */
public interface TacheService {

	public  TacheDto insertOrUpdate( TacheDto tacheDto);
	
	public  void remove( TacheDto tacheDto);	
	
	public  TacheDto findById(int id);
		
	public  List<TacheDto> findAll() ;
	
	public  List<TacheDto> findCurrentTacheByEtape(EtapeDto etapeDto);
	
	public  List<TacheDto> findFinishTacheByEntity(String entityCode, int entityInstanceId);

    
}
