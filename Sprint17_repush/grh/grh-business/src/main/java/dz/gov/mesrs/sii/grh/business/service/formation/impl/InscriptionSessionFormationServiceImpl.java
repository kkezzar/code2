package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.InscriptionSessionFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.InscriptionSessionFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.InscriptionSessionFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.InscriptionSessionFormationService;


@Service("inscriptionSessionFormationService")
public class InscriptionSessionFormationServiceImpl extends CommonServiceImpl<InscriptionSessionFormation , InscriptionSessionFormationDto,Long> implements InscriptionSessionFormationService  {
	
	private InscriptionSessionFormationDao	inscriptionSessionFormationDao;

	@Override
	protected CommonDao<InscriptionSessionFormation, Long> getDao() {
		return inscriptionSessionFormationDao;
	}

	/**
	 * @return the InscriptionSessionFormationDao
	 */
	public InscriptionSessionFormationDao getInscriptionSessionFormationDao() {
		return inscriptionSessionFormationDao;
	}

	/**
	 * @param InscriptionSessionFormationDao  to set
	 */
	@Autowired
	public void setInscriptionSessionFormationDao(InscriptionSessionFormationDao inscriptionSessionFormationDao) {
		this.inscriptionSessionFormationDao = inscriptionSessionFormationDao;
	}

	
	
}



