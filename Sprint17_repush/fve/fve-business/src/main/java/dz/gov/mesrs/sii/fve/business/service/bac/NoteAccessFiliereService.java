package dz.gov.mesrs.sii.fve.business.service.bac;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.NoteAccessFiliereDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  NoteAccessFiliereService {

	public  NoteAccessFiliereDto insertOrUpdate( NoteAccessFiliereDto noteAccessFiliereDto);
	
	public  void remove( NoteAccessFiliereDto noteAccessFiliereDto);	
	
	public  NoteAccessFiliereDto findById(int id);
	
	public  List<NoteAccessFiliereDto> findAll() ;	

}