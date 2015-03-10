package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeEditionDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  DiplomeFinEtudeEditionService {

	public  DiplomeFinEtudeEditionDto insertOrUpdate( DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto);
	
	public  void remove( DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto);	
	
	public  DiplomeFinEtudeEditionDto findById(int id);
	
	public  List<DiplomeFinEtudeEditionDto> findAll() ;	


	void delete(DiplomeFinEtudeEditionDto diplomeFinEtudeEditionDto);

	
	public  List<DiplomeFinEtudeEditionDto> findEditionByIdDiplome(int idDiplomeFinEtude) ;

	List<DiplomeFinEtudeEditionDto> findAdvanced(DiplomeFinEtudeEditionDto dto);



	

}