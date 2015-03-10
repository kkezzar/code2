package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailRealisationPrestationDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailRealisationPrestation;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailRealisationPrestationDto;
import dz.gov.mesrs.sii.gfc.business.service.DetailRealisationPrestationService;

/**
 * Service Implementation for domain model class DetailRealisationPrestation.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailRealisationPrestationService")
@Transactional
public class DetailRealisationPrestationServiceImpl extends
		CommonServiceImpl<DetailRealisationPrestation, DetailRealisationPrestationDto, Integer> implements
		DetailRealisationPrestationService {

	@Autowired
	@Qualifier("detailRealisationPrestationDao")
	private DetailRealisationPrestationDao detailRealisationPrestationDao;

	@Autowired
	private Mapper mapper;

	public DetailRealisationPrestationServiceImpl() {

	}

	@Override
	protected CommonDao<DetailRealisationPrestation, Integer> getDao() {
		return detailRealisationPrestationDao;
	}
}