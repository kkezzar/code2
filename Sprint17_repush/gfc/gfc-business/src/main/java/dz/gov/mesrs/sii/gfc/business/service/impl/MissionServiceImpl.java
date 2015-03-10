package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.MissionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.Mission;
import dz.gov.mesrs.sii.gfc.business.model.dto.MissionDto;
import dz.gov.mesrs.sii.gfc.business.service.MissionService;

/**
 * Service Implementation for domain model class Titre.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("missionService")
@Transactional
public class MissionServiceImpl extends CommonServiceImpl<Mission, MissionDto, Integer> implements MissionService {

	@Autowired
	@Qualifier("missionDao")
	private MissionDao missionDao;

	@Autowired
	private Mapper mapper;

	public MissionServiceImpl() {

	}

	@Override
	protected CommonDao<Mission, Integer> getDao() {
		return missionDao;
	}
}