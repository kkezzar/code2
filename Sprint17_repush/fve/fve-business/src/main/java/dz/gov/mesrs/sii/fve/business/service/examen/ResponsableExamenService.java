package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.ResponsableExamenDto;



/**
 * 
 * @author BELDI Jamel  on : 1 oct. 2014  17:29:42
 */
 
 
public interface  ResponsableExamenService {

	public  ResponsableExamenDto insertOrUpdate( ResponsableExamenDto absenceResponsableDto);
	
	public  void remove( ResponsableExamenDto absenceResponsableDto);	
	
	public  ResponsableExamenDto findById(int id);
	
	public  List<ResponsableExamenDto> findAll() ;	
	
	public  List<ResponsableExamenDto> findByExamen(long idExamen);
	
	public  void removeByExamen( int idExamen);

	public List<ResponsableExamenDto> findNotSubscribedOnExamen(
			Long examenSessionId,Integer etabId, Integer roleId);

	public List<ResponsableExamenDto> findBySalleExamen(Integer idSalleExamen);	
	
	//public void saveResponsablesByExamenAndGroupe(int idExamen, int idGroupe);

}