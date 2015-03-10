package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.ProgrammeNational;
import dz.gov.mesrs.sii.gfc.business.model.dto.ProgrammeNationalDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ProgrammeNationalDao;
import dz.gov.mesrs.sii.gfc.business.service.ProgrammeNationalService;

/**
 * Service Implementation for domain model class ProgrammeNational.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("programmeNationalService")
@Transactional
public class ProgrammeNationalServiceImpl extends CommonServiceImpl<ProgrammeNational,ProgrammeNationalDto, Integer> implements ProgrammeNationalService {

	@Autowired
	@Qualifier ("programmeNationalDao")
	private ProgrammeNationalDao programmeNationalDao;

    @Autowired
	private Mapper mapper;
	
	
	public ProgrammeNationalServiceImpl(){

	}
	
	@Override
	protected CommonDao<ProgrammeNational, Integer> getDao() {
		return programmeNationalDao;
	}
}