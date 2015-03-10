package dz.gov.mesrs.sii.fve.business.service.cursus;

import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationHandicapDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 
 
public interface  SituationHandicapService {

	public  SituationHandicapDto insertOrUpdate( SituationHandicapDto situationHandicapDto);
	
	public  void remove( SituationHandicapDto situationHandicapDto);	
	
	public  void remove(int id);
	
	public  SituationHandicapDto findById(int id);
	
	public  List<SituationHandicapDto> findByQuery(String query) ;
	
	public  List<SituationHandicapDto> findAll() ;	
	
	public  List<SituationHandicapDto> findByIdDossier(Integer id) ;
	
	public  SituationHandicapDto findSituationHandicap(int id, Date dateDebut, Date dateFin, String refCodeType);


}