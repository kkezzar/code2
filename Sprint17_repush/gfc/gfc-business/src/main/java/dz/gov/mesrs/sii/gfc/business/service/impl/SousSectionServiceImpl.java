package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.SousSection;
import dz.gov.mesrs.sii.gfc.business.model.dto.SousSectionDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.SousSectionDao;
import dz.gov.mesrs.sii.gfc.business.service.SousSectionService;

/**
 * Service Implementation for domain model class SousSection.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("sousSectionService")
@Transactional
public class SousSectionServiceImpl extends CommonServiceImpl<SousSection,SousSectionDto, Integer> implements SousSectionService {

	@Autowired
	@Qualifier ("sousSectionDao")
	private SousSectionDao sousSectionDao;

    @Autowired
	private Mapper mapper;
	
	
	public SousSectionServiceImpl(){

	}
	
	@Override
	protected CommonDao<SousSection, Integer> getDao() {
		return sousSectionDao;
	}
}