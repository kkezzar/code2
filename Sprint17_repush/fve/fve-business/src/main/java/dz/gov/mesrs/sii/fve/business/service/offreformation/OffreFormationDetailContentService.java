/**
 * [dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDetailContentService.java] 
 * @author rlaib on : 6 f�vr. 2014  14:41:17
 */
package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDetailContentDto;
	 
/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:07:09
 */
public interface  OffreFormationDetailContentService {

		public  OffreFormationDetailContentDto insertOrUpdate( OffreFormationDetailContentDto offreFormationDetailContentDto);
		
		public  void remove( OffreFormationDetailContentDto offreFormationDetailContentDto);	
		
		public  OffreFormationDetailContentDto findById(int id);
		
		public  List<OffreFormationDetailContentDto> findAll() ;	
		
		public  List<OffreFormationDetailContentDto> findOfContentTreeById(int  ofId) ;	


	}
