package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  DossierService {

	public  DossierDto insertOrUpdate( DossierDto dossierDto);
	
	public  void remove( DossierDto dossierDto);	
	
	public  DossierDto findById(int id);
	
	public  DossierDto findByCode(String code);
	
	public  List<DossierDto> findByQuery(String query) ;
	
	public  List<DossierDto> findAll() ;	


}