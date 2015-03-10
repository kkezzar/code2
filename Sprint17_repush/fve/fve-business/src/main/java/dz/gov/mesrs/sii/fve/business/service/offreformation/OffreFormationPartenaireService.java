/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService.java] 
 * @author rlaib on : 6 f�vr. 2014  14:39:41
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeMembreDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationPartenaireDto;

	 
/**
 * @author  Rafik LAIB  on : 5 avr. 2014  21:48:06
 */
public interface  OffreFormationPartenaireService {

		public  OffreFormationPartenaireDto insertOrUpdate(OffreFormationPartenaireDto offreFormationPartenaireDto);
		
		public  void remove(int id);	
		
		public  OffreFormationPartenaireDto findById(int id);
		
	    public  List<OffreFormationPartenaireDto> findAll() ;
	 
	    public  List<OffreFormationPartenaireDto> findPartnersByContractId(int contractId) ;
	    
	

	}
