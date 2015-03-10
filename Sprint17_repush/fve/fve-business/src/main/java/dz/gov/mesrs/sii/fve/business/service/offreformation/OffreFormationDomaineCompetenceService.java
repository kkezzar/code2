package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDomaineCompetenceDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 30-12-2013 16:44:07
 */
 
 
public interface  OffreFormationDomaineCompetenceService {

	public  OffreFormationDomaineCompetenceDto insertOrUpdate( OffreFormationDomaineCompetenceDto offreFormationDomaineCompetenceDto);
	
	public  void remove( OffreFormationDomaineCompetenceDto offreFormationDomaineCompetenceDto);	
	
	public  OffreFormationDomaineCompetenceDto findById(int id);
	
	public  List<OffreFormationDomaineCompetenceDto> findByQuery(String query) ;
	
	public  List<OffreFormationDomaineCompetenceDto> findAll() ;	


}