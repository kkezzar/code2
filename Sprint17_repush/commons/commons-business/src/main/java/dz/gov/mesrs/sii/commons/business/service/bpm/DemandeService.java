/**
 * [dz.gov.mesrs.sii.fve.business.service.demande.DemandeService.java] 
 * @author BELDI Jamel on : 20 avr. 2014  11:06:55
 */
package dz.gov.mesrs.sii.commons.business.service.bpm;

import java.util.HashMap;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeI18nDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.TypeDemandeDto;




/**
 * @author BELDI Jamel  on : 20 avr. 2014  11:06:55
 */
public interface DemandeService {
	
	public  DemandeDto saveOrUpdate( DemandeDto demandeDto, 	HashMap<String, DemandeI18nDto>  mapDemandeI18n);	
	
	public  void remove( DemandeDto demandeDto);	
	
	public  DemandeDto findById(int id);
	
	public  List<DemandeDto> findDemandeByQueryAndType(String query, int type) ;
	
	public  List<DemandeDto> findByType(int type) ;
	
    public  List<DemandeDto> findAll() ;
    
    public  HashMap<String, DemandeI18nDto> findListDemandei18nByDemande(DemandeDto demandeDto) ;
    
    public  List<DemandeI18nDto> findAdvanced(DemandeI18nDto demandeI18nDto) ;
    
	public  TypeDemandeDto findTypeDemandeByCode(String codeType);
	
	public  List<DemandeI18nDto> findListDemandsByTypeBySituaion(String codeTypeDemande, String codeSituation , Integer idDemande);


}
