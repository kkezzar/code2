package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Section;
import dz.gov.mesrs.sii.gfc.business.model.dto.SectionDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.SectionDao;
import dz.gov.mesrs.sii.gfc.business.service.SectionService;

/**
 * Service Implementation for domain model class Section.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("sectionService")
@Transactional
public class SectionServiceImpl extends CommonServiceImpl<Section,SectionDto, Integer> implements SectionService {

	@Autowired
	@Qualifier ("sectionDao")
	private SectionDao sectionDao;

    @Autowired
	private Mapper mapper;
	
	
	public SectionServiceImpl(){

	}
	
	@Override
	protected CommonDao<Section, Integer> getDao() {
		return sectionDao;
	}
}