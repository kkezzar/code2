package dz.gov.mesrs.sii.grh.business.service.recrutement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.BesoinRecrutement;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinRecrutementDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.BesoinRecrutementDao;
import dz.gov.mesrs.sii.grh.business.service.recrutement.BesoinRecrutementService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("besoinRecrutementService")
public class BesoinRecrutementServiceImpl extends CommonServiceImpl<BesoinRecrutement, BesoinRecrutementDto,Integer>  implements BesoinRecrutementService  {
	


	private BesoinRecrutementDao	besoinRecrutementDao;

	@Override
	protected CommonDao<BesoinRecrutement, Integer> getDao() {
		return besoinRecrutementDao;
	}

	/**
	 * @return the besoinRecrutementDao
	 */
	public BesoinRecrutementDao getBesoinRecrutementDao() {
		return besoinRecrutementDao;
	}

	/**
	 * @param besoinRecrutementDao  to set
	 */
	@Autowired
	public void setBesoinRecrutementDao(BesoinRecrutementDao besoinRecrutementDao) {
		this.besoinRecrutementDao = besoinRecrutementDao;
	}
	
}



