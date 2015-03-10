package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.ProgrammeFormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ProgrammeFormation;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.ProgrammeFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.ProgrammeFormationService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("programmeFormationService")
public class ProgrammeFormationServiceImpl implements ProgrammeFormationService  {

	@Autowired
	@Qualifier ("programmeFormationDao")
	private ProgrammeFormationDao programmeFormationDao;


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public ProgrammeFormationServiceImpl(){

	}


   
   
   

	@Override
	public ProgrammeFormationDto  insertOrUpdate(
			ProgrammeFormationDto programmeFormationDto) {

		ProgrammeFormation programmeFormation = mapper.map(programmeFormationDto,
				ProgrammeFormation.class);
		
		if (programmeFormation.getId() == 0)
			programmeFormationDao.persist(programmeFormation);
		else
			programmeFormation = programmeFormationDao.merge(programmeFormation);

		mapper.map(programmeFormation, programmeFormationDto);
		
		return programmeFormationDto;
	}
	

	@Override
	public void remove(ProgrammeFormationDto programmeFormationDto) {

		ProgrammeFormation programmeFormation = mapper.map(programmeFormationDto,
				ProgrammeFormation.class);

		programmeFormationDao.remove(programmeFormation);
	}



	@Override
	public List<ProgrammeFormationDto> findByQuery(String query) {
	
	    List<ProgrammeFormation> programmeFormations = programmeFormationDao.findByQuery(query);

		List<ProgrammeFormationDto> programmeFormationDtos = new ArrayList<ProgrammeFormationDto>();

		for (ProgrammeFormation programmeFormation : programmeFormations) {
			programmeFormationDtos.add(mapper.map(programmeFormation, ProgrammeFormationDto.class));
		}

		return programmeFormationDtos;
	
	}
	
	

	@Override
	public ProgrammeFormationDto findById(int id) {

		ProgrammeFormation programmeFormation = programmeFormationDao.findById(id);

		if (programmeFormation != null)
			return mapper.map(programmeFormation, ProgrammeFormationDto.class);

		return null;
	}

	
	
	@Override
	public List<ProgrammeFormationDto> findAll() {		
	
		List<ProgrammeFormation> programmeFormations = programmeFormationDao.findAll();

		List<ProgrammeFormationDto> programmeFormationDtos = new ArrayList<ProgrammeFormationDto>();

		for (ProgrammeFormation programmeFormation : programmeFormations) {
			programmeFormationDtos.add(mapper.map(programmeFormation, ProgrammeFormationDto.class));
		}

		return programmeFormationDtos;		
		
	}
}



