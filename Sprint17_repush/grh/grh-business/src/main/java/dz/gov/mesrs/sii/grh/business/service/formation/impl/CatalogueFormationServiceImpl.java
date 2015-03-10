package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.CatalogueFormationDao;
import dz.gov.mesrs.sii.commons.data.model.grh.CatalogueFormation;
import dz.gov.mesrs.sii.grh.business.model.dto.CatalogueFormationDto;
import dz.gov.mesrs.sii.grh.business.service.formation.CatalogueFormationService;


@Service("catalogueFormationService")
public class CatalogueFormationServiceImpl extends CommonServiceImpl<CatalogueFormation , CatalogueFormationDto,Integer> implements CatalogueFormationService  {
	
	private CatalogueFormationDao	catalogueFormationDao;

	@Override
	protected CommonDao<CatalogueFormation, Integer> getDao() {
		return catalogueFormationDao;
	}

	/**
	 * @return the CatalogueFormationDao
	 */
	public CatalogueFormationDao getCatalogueFormationDao() {
		return catalogueFormationDao;
	}

	/**
	 * @param CatalogueFormationDao  to set
	 */
	@Autowired
	public void setCatalogueFormationDao(CatalogueFormationDao catalogueFormationDao) {
		this.catalogueFormationDao = catalogueFormationDao;
	}

	
	
}



