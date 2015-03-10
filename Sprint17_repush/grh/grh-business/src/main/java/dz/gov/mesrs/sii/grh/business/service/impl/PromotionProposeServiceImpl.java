package dz.gov.mesrs.sii.grh.business.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PromotionPropose;
import dz.gov.mesrs.sii.grh.business.model.dto.PromotionProposeDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.PromotionProposeDao;
import dz.gov.mesrs.sii.grh.business.service.PromotionProposeService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("promotionProposeService")
public class PromotionProposeServiceImpl extends CommonServiceImpl<PromotionPropose,PromotionProposeDto, Integer> implements PromotionProposeService  {
	
	private PromotionProposeDao	promotionProposeDao;

	@Override
	protected CommonDao<PromotionPropose, Integer> getDao() {
		return promotionProposeDao;
	}

	/**
	 * @return the promotionProposeDao
	 */
	public PromotionProposeDao getPromotionProposeDao() {
		return promotionProposeDao;
	}

	/**
	 * @param promotionProposeDao  to set
	 */
	@Autowired
	public void setPromotionProposeDao(PromotionProposeDao promotionProposeDao) {
		this.promotionProposeDao = promotionProposeDao;
	}}



