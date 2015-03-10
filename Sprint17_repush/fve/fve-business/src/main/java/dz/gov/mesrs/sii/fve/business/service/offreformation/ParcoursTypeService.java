package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParcoursTypeDto;

/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:08:11
 */
public interface  ParcoursTypeService {

	public  ParcoursTypeDto insertOrUpdate( ParcoursTypeDto parcoursTypeDto);
	
	public  void remove( ParcoursTypeDto parcoursTypeDto);	
	
	public  ParcoursTypeDto findById(int id);
	
	public  List<ParcoursTypeDto> findByQuery(String query) ;
	
	public  List<ParcoursTypeDto> findAll() ;	
	
	public  List<ParcoursTypeDto> findByOfId(int ofId);	


}