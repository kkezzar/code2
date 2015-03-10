package dz.gov.mesrs.sii.grh.business.service.dossieremploye.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DiplomeEmployeDao;
import dz.gov.mesrs.sii.commons.data.model.grh.DiplomeEmploye;
import dz.gov.mesrs.sii.grh.business.model.dto.DiplomeEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.dossieremploye.DiplomeEmployeService;

@Service("diplomeEmployeService")
public class DiplomeEmployeServiceImpl extends CommonServiceImpl<DiplomeEmploye, DiplomeEmployeDto, Integer> implements
		DiplomeEmployeService {

	private DiplomeEmployeDao diplomeEmployeDao;

	@Override
	protected CommonDao<DiplomeEmploye, Integer> getDao() {
		return diplomeEmployeDao;
	}

	/**
	 * @return the diplomeEmployeDao
	 */
	public DiplomeEmployeDao getDiplomeEmployeDao() {
		return diplomeEmployeDao;
	}

	/**
	 * @param diplomeEmployeDao
	 *            to set
	 */
	@Autowired
	public void setDiplomeEmployeDao(DiplomeEmployeDao diplomeEmployeDao) {
		this.diplomeEmployeDao = diplomeEmployeDao;
	}

}
