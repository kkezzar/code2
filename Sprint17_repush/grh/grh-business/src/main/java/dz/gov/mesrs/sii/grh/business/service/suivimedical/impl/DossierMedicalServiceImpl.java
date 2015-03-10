package dz.gov.mesrs.sii.grh.business.service.suivimedical.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierMedicalDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierMedical;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierMedicalDto;
import dz.gov.mesrs.sii.grh.business.service.suivimedical.DossierMedicalService;


@Service("dossierMedicalService")
public class DossierMedicalServiceImpl extends CommonServiceImpl<DossierMedical , DossierMedicalDto,Integer> implements DossierMedicalService  {
	
	private DossierMedicalDao	dossierMedicalDao;

	@Override
	protected CommonDao<DossierMedical, Integer> getDao() {
		return dossierMedicalDao;
	}

	/**
	 * @return the DossierMedicalDao
	 */
	public DossierMedicalDao getDossierMedicalDao() {
		return dossierMedicalDao;
	}

	/**
	 * @param DossierMedicalDao  to set
	 */
	@Autowired
	public void setConjointDao(DossierMedicalDao dossierMedicalDao) {
		this.dossierMedicalDao = dossierMedicalDao;
	}

	@Override
	public List<DossierMedicalDto> findAllAntcedentsEmploye(Long employeId) {
		DossierMedicalDto example = new DossierMedicalDto();
		example.setAntecedent(true);
		DossierEmployeDto employe = new DossierEmployeDto(employeId);
		example.setDossierEmployeDto(employe);
		return super.findAllByExample(example);
	
	}

	@Override
	public List<DossierMedicalDto> findAllAllergiesEmploye(Long employeId) {
		DossierMedicalDto example = new DossierMedicalDto();
		example.setAllergie(true);
		DossierEmployeDto employe = new DossierEmployeDto(employeId);
		example.setDossierEmployeDto(employe);
		return super.findAllByExample(example);
	}

	@Override
	public List<DossierMedicalDto> findAllPathologiesEmploye(Long employeId) {
		DossierMedicalDto example = new DossierMedicalDto();
		example.setPathologie(true);
		DossierEmployeDto employe = new DossierEmployeDto(employeId);
		example.setDossierEmployeDto(employe);
		return super.findAllByExample(example);
	}
	
}



