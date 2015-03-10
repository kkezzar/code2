package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Chapitre;
import dz.gov.mesrs.sii.gfc.business.model.dto.ChapitreDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ChapitreDao;
import dz.gov.mesrs.sii.gfc.business.service.ChapitreService;

/**
 * Service Implementation for domain model class Chapitre.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("chapitreService")
@Transactional
public class ChapitreServiceImpl extends CommonServiceImpl<Chapitre,ChapitreDto, Integer> implements ChapitreService {

	@Autowired
	@Qualifier ("chapitreDao")
	private ChapitreDao chapitreDao;

    @Autowired
	private Mapper mapper;
	
	
	public ChapitreServiceImpl(){

	}
	
	@Override
	protected CommonDao<Chapitre, Integer> getDao() {
		return chapitreDao;
	}
}