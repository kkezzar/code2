package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.PlanningSessionDto;



/**
 * 
 * @author BELDI Jamel  on : 17 sept. 2014  17:08:45
 */
 
 
public interface  PlanningSessionService {

	public  PlanningSessionDto insertOrUpdate( PlanningSessionDto planningSessionDto);
	
	public  void remove( PlanningSessionDto planningSessionDto);	
	
	public  PlanningSessionDto findById(long id);
	
	public  List<PlanningSessionDto> findAll() ;
	
	public  PlanningSessionDto findByYearAndOfAndLevelAndPeriodAndTypeSession(PlanningSessionDto searchDto );
	
	public boolean publish(long id);
	
	public boolean fence(long id);
	
	public List<PlanningSessionDto> findAdvanced(PlanningSessionDto searchDto);
	
	public List<PlanningSessionDto> findSessionforStudent(int  idDossierInsciptionAdministrative);

}
