package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DossierMissionDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DossierMission;
import dz.gov.mesrs.sii.gfc.business.model.dto.DossierMissionDto;
import dz.gov.mesrs.sii.gfc.business.service.DossierMissionService;

/**
 * Service Implementation for domain model class DossierMission.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("dossierMissionService")
@Transactional
public class DossierMissionServiceImpl extends CommonServiceImpl<DossierMission, DossierMissionDto, Integer> implements
		DossierMissionService {

	@Autowired
	@Qualifier("dossierMissionDao")
	private DossierMissionDao dossierMissionDao;

	@Autowired
	private Mapper mapper;

	@Autowired
	@Qualifier("situationService")
	private SituationService situationService;

	public DossierMissionServiceImpl() {

	}

	@Override
	protected CommonDao<DossierMission, Integer> getDao() {
		return dossierMissionDao;
	}
}