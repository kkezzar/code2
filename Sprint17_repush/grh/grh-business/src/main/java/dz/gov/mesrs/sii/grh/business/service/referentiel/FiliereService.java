package dz.gov.mesrs.sii.grh.business.service.referentiel;

import java.util.List;

import dz.gov.mesrs.sii.commons.business.service.CommonService;
import dz.gov.mesrs.sii.commons.data.model.grh.Filiere;
import dz.gov.mesrs.sii.grh.business.model.dto.FiliereDto;




/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Interface  Service Filiere
 * 
 */
 
public interface FiliereService extends CommonService<Filiere,FiliereDto,Integer>{
	/**
	 * for specific method
	 */
	 
	public  List<FiliereDto> findFiliereByIdRubrique(int idRubrique) ;
	
    public  FiliereDto insertOrUpdate( FiliereDto filiereDto);
	
	public  void remove( FiliereDto filiereDto);	
	
	public  FiliereDto findById(int id);
	
	public  List<FiliereDto> findAll() ;	
}