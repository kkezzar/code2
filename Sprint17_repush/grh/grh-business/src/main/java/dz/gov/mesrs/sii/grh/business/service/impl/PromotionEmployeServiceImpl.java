package dz.gov.mesrs.sii.grh.business.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PromotionEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.PromotionEmployeDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.PromotionEmployeDao;
import dz.gov.mesrs.sii.grh.business.service.PromotionEmployeService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("promotionEmployeService")
public class PromotionEmployeServiceImpl extends CommonServiceImpl<PromotionEmploye,PromotionEmployeDto, Integer> implements PromotionEmployeService  {
	
	private PromotionEmployeDao	promotionEmployeDao;

	@Override
	protected CommonDao<PromotionEmploye, Integer> getDao() {
		return promotionEmployeDao;
	}

	/**
	 * @return the promotionEmployeDao
	 */
	public PromotionEmployeDao getPromotionEmployeDao() {
		return promotionEmployeDao;
	}

	/**
	 * @param promotionEmployeDao  to set
	 */
	@Autowired
	public void setPromotionEmployeDao(PromotionEmployeDao promotionEmployeDao) {
		this.promotionEmployeDao = promotionEmployeDao;
	}

}


   
