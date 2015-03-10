package dz.gov.mesrs.sii.recherche.business.service;

import java.util.List;

import dz.gov.mesrs.sii.recherche.business.model.dto.AxeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ProgrammeDto;

/**
 * @author rlaib  on : 25 janv. 2015  12:11:16
 */
public interface RechercheProgrammeService {
	
	ProgrammeDto insertOrUpdate(ProgrammeDto programmeDto);
	AxeDto saveOneAxe(AxeDto axeDto);
	void remove(Long id);
	void removeOneAxe(Long id);
	public  ProgrammeDto findById(Long id);
	public  AxeDto findOneAxeById(Long id);
	List<ProgrammeDto> findByKeyWords(String keyWord);
	List<AxeDto> findAxesOfProgram(Long idProgramme);
	List<ProgrammeDto> findByPeriod(Short startYear,Short endYear);
	List<ProgrammeDto> findActifProgramme();
}
