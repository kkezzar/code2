/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService.java] 
 * @author rlaib on : 6 f�vr. 2014  14:39:41
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationContratDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:06:58
 */
public interface  OffreFormationContratService {

		public  OffreFormationContratDto insertOrUpdate(OffreFormationContratDto offreFormationEquipeDto) ;
		
		public  void remove( OffreFormationContratDto offreFormationEquipeDto);	
		
		public  OffreFormationContratDto findById(int id);
		
		public  OffreFormationContratDto findByRefCode(String refCode);
		
		public  List<OffreFormationContratDto> findByOfId(int ofId);
		
	    public  List<OffreFormationContratDto> findAll() ;
	    
	    public  OffreFormationContratDto saveContract(
	    		OffreFormationContratDto offreFormationContratDto, 
	    		List<OffreFormationPartenaireDto> offreFormationPartenaireDtos, OffreFormationDto offreFormationDto) ;
	    

	}
