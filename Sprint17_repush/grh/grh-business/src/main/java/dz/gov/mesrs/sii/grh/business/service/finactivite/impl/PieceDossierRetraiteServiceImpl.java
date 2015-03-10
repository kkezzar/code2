package dz.gov.mesrs.sii.grh.business.service.finactivite.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PieceDossierRetraite;
import dz.gov.mesrs.sii.grh.business.model.dto.PieceDossierRetraiteDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.PieceDossierRetraiteDao;
import dz.gov.mesrs.sii.grh.business.service.finactivite.PieceDossierRetraiteService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("pieceDossierRetraiteService")
public class PieceDossierRetraiteServiceImpl extends CommonServiceImpl<PieceDossierRetraite,PieceDossierRetraiteDto, Integer> implements PieceDossierRetraiteService  {
	
	private PieceDossierRetraiteDao	pieceDossierRetraiteDao;

	@Override
	protected CommonDao<PieceDossierRetraite, Integer> getDao() {
		return pieceDossierRetraiteDao;
	}

	/**
	 * @return the pieceDossierRetraiteDao
	 */
	public PieceDossierRetraiteDao getPieceDossierRetraiteDao() {
		return pieceDossierRetraiteDao;
	}

	/**
	 * @param pieceDossierRetraiteDao  to set
	 */
	@Autowired
	public void setPieceDossierRetraiteDao(PieceDossierRetraiteDao pieceDossierRetraiteDao) {
		this.pieceDossierRetraiteDao = pieceDossierRetraiteDao;
	}}



