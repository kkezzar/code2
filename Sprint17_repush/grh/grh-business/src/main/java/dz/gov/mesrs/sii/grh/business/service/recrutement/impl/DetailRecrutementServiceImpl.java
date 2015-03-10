package dz.gov.mesrs.sii.grh.business.service.recrutement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DetailRecrutement;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.DetailRecrutementDao;
import dz.gov.mesrs.sii.grh.business.service.recrutement.DetailRecrutementService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("detailRecrutementService")
public class DetailRecrutementServiceImpl extends CommonServiceImpl<DetailRecrutement,DetailRecrutementDto, Integer> implements DetailRecrutementService  {
	
	private DetailRecrutementDao	detailRecrutementDao;

	@Override
	protected CommonDao<DetailRecrutement, Integer> getDao() {
		return detailRecrutementDao;
	}

	/**
	 * @return the detailRecrutementDao
	 */
	public DetailRecrutementDao getDetailRecrutementDao() {
		return detailRecrutementDao;
	}

	/**
	 * @param detailRecrutementDao  to set
	 */
	@Autowired
	public void setDetailRecrutementDao(DetailRecrutementDao detailRecrutementDao) {
		this.detailRecrutementDao = detailRecrutementDao;
	}
	
}



