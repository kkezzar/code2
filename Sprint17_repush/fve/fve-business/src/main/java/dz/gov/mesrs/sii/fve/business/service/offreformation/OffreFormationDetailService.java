/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailService.java] 
 * @author rlaib on : 6 f�vr. 2014  14:39:41
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDetail;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailDto;

/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:07:24
 */
public interface  OffreFormationDetailService {

		public  OffreFormationDetailDto insertOrUpdate( OffreFormationDetailDto offreFormationDetailDto);
		
		public  void remove( OffreFormationDetailDto offreFormationDetailDto);	
		
		public  OffreFormationDetailDto findById(int id);
		
	    public  List<OffreFormationDetail> findAll() ;
	 

}
