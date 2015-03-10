package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.PropostionAvancementDao;
import dz.gov.mesrs.sii.grh.business.service.PropostionAvancementService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("propostionAvancementService")
public class PropostionAvancementServiceImpl extends CommonServiceImpl<PropostionAvancement,PropostionAvancementDto, Long> implements PropostionAvancementService  {
	
	private PropostionAvancementDao	propostionAvancementDao;

	@Override
	protected CommonDao<PropostionAvancement, Long> getDao() {
		return propostionAvancementDao;
	}

	/**
	 * @return the propostionAvancementDao
	 */
	public PropostionAvancementDao getPropostionAvancementDao() {
		return propostionAvancementDao;
	}

	/**
	 * @param propostionAvancementDao  to set
	 */
	@Autowired
	public void setPropostionAvancementDao(PropostionAvancementDao propostionAvancementDao) {
		this.propostionAvancementDao = propostionAvancementDao;
	}
	
	
	
	
	
	@Override
	public List<String> maxDatepropositionAvancement(Integer situation,	Integer refetablicement, Integer typeavancement){
		return  propostionAvancementDao.maxDatepropositionAvancement(situation,refetablicement,typeavancement);

	}
	@Override
public void deleteAllEmployProposition(PropostionAvancementDto propostionAvancementDto){	
	PropostionAvancement propostionAvancement = mapper.map(propostionAvancementDto, PropostionAvancement.class);
	propostionAvancementDao.deleteAllEmployProposition(propostionAvancement);
	
	}
	



}



