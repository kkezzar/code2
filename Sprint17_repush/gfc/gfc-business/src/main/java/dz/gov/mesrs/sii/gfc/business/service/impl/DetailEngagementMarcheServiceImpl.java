package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailEngagementMarcheDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailEngagementMarche;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailEngagementMarcheDto;
import dz.gov.mesrs.sii.gfc.business.service.DetailEngagementMarcheService;

/**
 * Service Implementation for domain model class DetailEngagementMarche.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailEngagementMarcheService")
@Transactional
public class DetailEngagementMarcheServiceImpl extends
		CommonServiceImpl<DetailEngagementMarche, DetailEngagementMarcheDto, Integer> implements
		DetailEngagementMarcheService {

	@Autowired
	@Qualifier("detailEngagementMarcheDao")
	private DetailEngagementMarcheDao detailEngagementMarcheDao;

	@Autowired
	private Mapper mapper;

	public DetailEngagementMarcheServiceImpl() {

	}

	@Override
	protected CommonDao<DetailEngagementMarche, Integer> getDao() {
		return detailEngagementMarcheDao;
	}
}