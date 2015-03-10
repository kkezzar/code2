package dz.gov.mesrs.sii.fve.business.service.examen;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.SalleExamenDto;



/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */
 
 
public interface  SalleExamenService {

	public  SalleExamenDto insertOrUpdate( SalleExamenDto salleExamenDto);
	
	public  void remove( SalleExamenDto salleExamenDto);	
	
	public  SalleExamenDto findById(int id);
	
	public  List<SalleExamenDto> findAll() ;
	
	public  List<SalleExamenDto> findByExamen(Long idExamen) ;
	
	public List<SalleExamenDto> findSalleNotaffectedToExamen(Long idExamen,Integer etabId) ;
	
	public  List<SalleExamenDto> findBySession(long idSession,boolean withStudentList,boolean withListResponsableList) ;
	
}