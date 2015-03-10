/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService.java] 
 * @author rlaib on : 6 fevr. 2014  14:39:41
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeMembreDto;

	/**
	 * @author MESRS CCM
	 * @version 1.0
	 * @created 20-03-2014 16:44:07
	 */
	 
	 
public interface  OffreFormationEquipeMembreService {

		public  OffreFormationEquipeMembreDto insertOrUpdate( OffreFormationEquipeMembreDto offreFormationEquipeMembreDto);
		
		public  void remove(int id);	
		
		public  OffreFormationEquipeMembreDto findById(int id);
		
	    public  List<OffreFormationEquipeMembreDto> findAll() ;
	 
	    public  List<OffreFormationEquipeMembreDto> findMembersByTeamId(int teamId) ;
	    

	}
