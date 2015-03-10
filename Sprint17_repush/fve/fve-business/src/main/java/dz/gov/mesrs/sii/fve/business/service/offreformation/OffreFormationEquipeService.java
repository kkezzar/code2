/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService.java] 
 * @author rlaib on : 6 f�vr. 2014  14:39:41
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto;

	/**
	 * @author MESRS CCM
	 * @version 1.0
	 * @created 20-03-2014 16:44:07
	 */
	 
	 
public interface  OffreFormationEquipeService {

		public  OffreFormationEquipeDto insertOrUpdate( OffreFormationEquipeDto offreFormationEquipeDto);
		
		public  void remove( OffreFormationEquipeDto offreFormationEquipeDto);	
		
		public  OffreFormationEquipeDto findById(int id);
		
	    public  List<OffreFormationEquipeDto> findAll() ;
	    
	    public  OffreFormationEquipeDto findOfEquipeById(int ofId) ;

	}
