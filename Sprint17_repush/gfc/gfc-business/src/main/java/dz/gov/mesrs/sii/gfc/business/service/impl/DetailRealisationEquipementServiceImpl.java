package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailRealisationEquipementDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailRealisationEquipement;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailRealisationEquipementDto;
import dz.gov.mesrs.sii.gfc.business.service.DetailRealisationEquipementService;

/**
 * Service Implementation for domain model class DetailRealisationEquipement.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailRealisationEquipementService")
@Transactional
public class DetailRealisationEquipementServiceImpl extends
		CommonServiceImpl<DetailRealisationEquipement, DetailRealisationEquipementDto, Integer> implements
		DetailRealisationEquipementService {

	@Autowired
	@Qualifier("detailRealisationEquipementDao")
	private DetailRealisationEquipementDao detailRealisationEquipementDao;

	@Autowired
	private Mapper mapper;

	public DetailRealisationEquipementServiceImpl() {

	}

	@Override
	protected CommonDao<DetailRealisationEquipement, Integer> getDao() {
		return detailRealisationEquipementDao;
	}
}