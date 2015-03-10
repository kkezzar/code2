package dz.gov.mesrs.sii.grh.business.service.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Rubrique;
import dz.gov.mesrs.sii.grh.business.model.dto.RubriqueDto;




/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Interface  Service Rubrique
 * 
 */
 
public interface RubriqueService extends CommonService<Rubrique,RubriqueDto,Integer>{
	/**
	 * for specific method
	 */
	
	public List<RubriqueDto> getListValideRubriques();
	
//	   public  RubriqueDto insertOrUpdate( RubriqueDto rubriqueDto);
//		
		public  void remove( RubriqueDto rubriqueDto);	
//		
//		public  RubriqueDto findById(int id);
//		
		public  List<RubriqueDto> findAll() ;	
}
