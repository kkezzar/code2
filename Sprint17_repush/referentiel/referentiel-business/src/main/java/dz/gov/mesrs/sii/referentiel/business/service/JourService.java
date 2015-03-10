package dz.gov.mesrs.sii.referentiel.business.service;

import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.JourDto;



/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:43:25
 */
 
 
public interface  JourService {

	public  JourDto insertOrUpdate( JourDto jourDto);
	
	public  void remove( JourDto jourDto);	
	
	public  JourDto findById(int id);
	
	public  List<JourDto> findAll() ;	

}