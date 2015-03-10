package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ProgrammeFormationDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 30-12-2013 16:44:07
 */
 
 
public interface  ProgrammeFormationService {

	public  ProgrammeFormationDto insertOrUpdate( ProgrammeFormationDto programmeFormationDto);
	
	public  void remove( ProgrammeFormationDto programmeFormationDto);	
	
	public  ProgrammeFormationDto findById(int id);
	
	public  List<ProgrammeFormationDto> findByQuery(String query) ;
	
	public  List<ProgrammeFormationDto> findAll() ;	


}