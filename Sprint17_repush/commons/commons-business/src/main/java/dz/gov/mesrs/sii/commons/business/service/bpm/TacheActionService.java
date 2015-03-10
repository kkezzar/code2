/**
 * TacheActionService
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TacheActionDto;

/**
 * 
 * @author BELDI Jamel  on : 30 avr. 2014  16:07:40
 */
public interface TacheActionService {

	public  TacheActionDto insertOrUpdate( TacheActionDto tacheActionDto);
	
	public  void remove( TacheActionDto tacheActionDto);	
	
	public  TacheActionDto findById(int id);
		
	public  List<TacheActionDto> findAll() ;
	
	public  List<TacheActionDto> findByEntity(String entityCode, int entityInstanceId);

    
}
