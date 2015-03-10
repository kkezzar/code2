package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.FormationDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  FormationService {

	public  FormationDto insertOrUpdate( FormationDto formationDto);
	
	public  void remove( FormationDto formationDto);	
	
	public  FormationDto findById(int id);
	
	public  List<FormationDto> findAll() ;	
	
	public List<FormationDto> findFormationsByIdDossier(int idDossier);

}