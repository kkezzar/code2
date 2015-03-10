package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AbsenceMatiereDto;





/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  AbsenceMatiereService {

		
	public  AbsenceMatiereDto insertOrUpdate( AbsenceMatiereDto absenceMatiereDto);
	
	public  void remove( AbsenceMatiereDto absenceMatiereDto);	
	
	public  AbsenceMatiereDto findById(int id);
	
	public  List<AbsenceMatiereDto> findByQuery(String query) ;
	
	public  List<AbsenceMatiereDto> findAll() ;	

	public List<AbsenceMatiereDto> findAdvanced(AbsenceMatiereDto searchDto);
	
	public AbsenceMatiereDto findByIdDossier(int idDossier);
	
	void delete(AbsenceMatiereDto absenceMatiereDto);


}