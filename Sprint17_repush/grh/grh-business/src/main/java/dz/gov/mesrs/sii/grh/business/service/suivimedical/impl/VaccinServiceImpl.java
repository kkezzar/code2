package dz.gov.mesrs.sii.grh.business.service.suivimedical.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.VaccinDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Vaccin;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.VaccinDto;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.VaccinService;


@Service("vaccinService")
public class VaccinServiceImpl extends CommonServiceImpl<Vaccin , VaccinDto,Integer> implements VaccinService  {
	
	private VaccinDao	vaccinDao;

	@Override
	protected CommonDao<Vaccin, Integer> getDao() {
		return vaccinDao;
	}

	/**
	 * @return the vaccinDao
	 */
	public VaccinDao getVaccinDao() {
		return vaccinDao;
	}

	/**
	 * @param vaccinDao  to set
	 */
	@Autowired
	public void setVaccinDao(VaccinDao vaccinDao) {
		this.vaccinDao = vaccinDao;
	}

	@Override
	public List<VaccinDto> findAllVaccinsEmploye(Long employeId) {
		VaccinDto example = new VaccinDto();
		DossierEmployeDto employe = new DossierEmployeDto(employeId);
		example.setDossierEmployeDto(employe);
		return super.findAllByExample(example);
	}
	
}



