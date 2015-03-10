package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  DiplomeService {

	public  DiplomeDto insertOrUpdate( DiplomeDto diplomeDto);
	
	public  void remove( DiplomeDto diplomeDto);	
	
	public  DiplomeDto findById(int id);
	
	public  List<DiplomeDto> findAll() ;	
	
	public  List<DiplomeDto> findDiplomesByIdDossier(int idDossier) ;
	
}