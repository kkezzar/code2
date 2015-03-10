package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ProgrammeFormationGrhDao;
import dz.gov.mesrs.sii.commons.data.model.grh.ProgrammeFormationGrh;
import dz.gov.mesrs.sii.grh.business.model.dto.ProgrammeFormationGrhDto;
import dz.gov.mesrs.sii.grh.business.service.formation.ProgrammeFormationGrhService;


@Service("programmeFormationGrhService")
public class ProgrammeFormationGrhServiceImpl extends CommonServiceImpl<ProgrammeFormationGrh , ProgrammeFormationGrhDto,Long> implements ProgrammeFormationGrhService  {
	
	private ProgrammeFormationGrhDao	programmeFormationGrhDao;

	@Override
	protected CommonDao<ProgrammeFormationGrh, Long> getDao() {
		return programmeFormationGrhDao;
	}

	/**
	 * @return the ProgrammeFormationGrhDao
	 */
	public ProgrammeFormationGrhDao getProgrammeFormationGrhDao() {
		return programmeFormationGrhDao;
	}

	/**
	 * @param ProgrammeFormationGrhDao  to set
	 */
	@Autowired
	public void setProgrammeFormationGrhDao(ProgrammeFormationGrhDao programmeFormationGrhDao) {
		this.programmeFormationGrhDao = programmeFormationGrhDao;
	}

	
	
}



