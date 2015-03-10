package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DetailBesoinFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DetailBesoinFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailBesoinFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.DetailBesoinFormationService;


@Service("detailBesoinFormationService")
public class DetailBesoinFormationServiceImpl extends CommonServiceImpl<DetailBesoinFormation , DetailBesoinFormationDto,Integer> implements DetailBesoinFormationService  {
	
	private DetailBesoinFormationDao	detailBesoinFormationDao;

	@Override
	protected CommonDao<DetailBesoinFormation, Integer> getDao() {
		return detailBesoinFormationDao;
	}

	/**
	 * @return the DetailBesoinFormationDao
	 */
	public DetailBesoinFormationDao getDetailBesoinFormationDao() {
		return detailBesoinFormationDao;
	}

	/**
	 * @param DetailBesoinFormationDao  to set
	 */
	@Autowired
	public void setDetailBesoinFormationDao(DetailBesoinFormationDao detailBesoinFormationDao) {
		this.detailBesoinFormationDao = detailBesoinFormationDao;
	}

	
	
}



