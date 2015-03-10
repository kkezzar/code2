package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.PrioriteSerieBacDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  PrioriteSerieBacService {

	public  PrioriteSerieBacDto insertOrUpdate( PrioriteSerieBacDto prioriteSerieBacDto);
	
	public  void remove( PrioriteSerieBacDto prioriteSerieBacDto);	
	
	public  PrioriteSerieBacDto findById(int id);
	
	public  List<PrioriteSerieBacDto> findAll() ;	

}