package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ExclusionDto;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 01-07-2014 16:44:07
 */
 
 
public interface  ExclusionService {

		
	public  ExclusionDto insertOrUpdate( ExclusionDto exclusionDto);
	
	public  void remove( ExclusionDto exclusionDto);	
	
	public  ExclusionDto findById(int id);
	
	public  List<ExclusionDto> findByQuery(String query) ;
	
	public  List<ExclusionDto> findAll() ;	

	public List<ExclusionDto> findAdvanced(ExclusionDto searchDto);
	
	public List<ExclusionDto> findAdvanced2(ExclusionDto searchDto);
	
	public ExclusionDto findByIdDossier(int idDossier);
	
	void delete(ExclusionDto exclusionDto);


}