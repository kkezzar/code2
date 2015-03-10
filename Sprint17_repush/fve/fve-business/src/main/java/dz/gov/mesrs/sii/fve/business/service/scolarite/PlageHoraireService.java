package dz.gov.mesrs.sii.fve.business.service.scolarite;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.PlageHoraireDto;



/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:54:22
 */
 
 
public interface  PlageHoraireService {

	public  PlageHoraireDto insertOrUpdate( PlageHoraireDto plageHoraireDto);
	
	public  void remove( PlageHoraireDto plageHoraireDto);	
	
	public  PlageHoraireDto findById(int id);
	
	public  List<PlageHoraireDto> findAll() ;	

}