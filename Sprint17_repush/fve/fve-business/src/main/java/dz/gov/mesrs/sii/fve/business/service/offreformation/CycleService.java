package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDiplomeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDto;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:07:59
 */
public interface  CycleService {

	CycleDto insertOrUpdate(CycleDto cycleDto);
	
	void remove(int idCycle);	
	
	void removeCycleDiplome(int idCycleDiplome);	
	
	CycleDto findById(int id);
	
	CycleDiplomeDto findCycleDiplomeById(int id);
	
	CycleDto findByCode(String codeCycle);
	
	List<CycleDto> findAll() ;	
	
	List<CycleDto> findByTypeFormationCode(String codeTypeFormation);	
	
	CycleDto findByTypeFormationByCode(String codeTypeFormation,String codeCycle);	
    
	List<CycleDiplomeDto> findDiplomesByCycle(Integer idCycle);
	
	CycleDiplomeDto insertOrUpdate(CycleDiplomeDto cycleDiplomeDto);
}