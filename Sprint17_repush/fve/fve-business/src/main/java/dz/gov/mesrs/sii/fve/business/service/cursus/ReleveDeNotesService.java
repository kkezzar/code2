package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReleveDeNotesDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  ReleveDeNotesService {

	public  ReleveDeNotesDto insertOrUpdate( ReleveDeNotesDto releveDeNotesDto);
	
	public  void remove( ReleveDeNotesDto releveDeNotesDto);	
	
	public  ReleveDeNotesDto findById(int id);
	
	public  List<ReleveDeNotesDto> findAll() ;	

}