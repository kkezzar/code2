package dz.gov.mesrs.sii.fve.business.service.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ParamTypeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  22:07:59
 */
public interface  PeriodeService {

	public  PeriodeDto insertOrUpdate(PeriodeDto periodeDto);
	
	public  void remove(Integer idPeriode);	
	
	public  PeriodeDto findById(Integer id);
	
	public  List<PeriodeDto> findAll() ;	
	
	public  List<PeriodeDto> findByLevelId(Integer idLevel);
	
	public  List<PeriodeDto> findCurrentPeriodsByLevelId(Integer idLevel, Integer idAnnee);

	public  List<PeriodeDto> findByCycleId(Integer idCycle);
	
	public  PeriodeParamDto findPeriodParamById(Integer idPeriodeParam);
	
	public  List<PeriodeParamDto> findPeriodParamByPeriodByYear(Integer idYear, Integer idPeriod);
	
	public  List<PeriodeParamDto> findPeriodParamByPeriodiciteByYear(Integer idYear, String codePeriodicite);
	
	public  PeriodeParamDto insertOrUpdate(PeriodeParamDto periodeParamDto);

	public  void removePeriodeParam(Integer idPeriodeParam);	
    
	public  List<ParamTypeDto> getAllParamsTypes() ;	
	
	public  ParamTypeDto findParamTypeById(Integer idType) ;	
	
	public  PeriodeParamDto findParamByCodeByPeriodByYear(String codeParam, Integer idYear, Integer idPeriod);
	
	public  PeriodeParamDto findParamByCodeByPeriodiciteByYear(String codeParam, Integer idYear, String codePeriodicite);
	
	public PeriodeDto findByLevelAndYearPeriod(Integer idLevel, Integer idNcPeriode );
	
	public PeriodeDto findByLevelIdByIdNcPeriode(Integer idLevel, String idNcPeriode);
}