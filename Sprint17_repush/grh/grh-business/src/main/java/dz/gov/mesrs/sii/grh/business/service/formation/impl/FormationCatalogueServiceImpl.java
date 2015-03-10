package dz.gov.mesrs.sii.grh.business.service.formation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.FormationCatalogueDao;
import dz.gov.mesrs.sii.commons.data.model.grh.FormationCatalogue;
import dz.gov.mesrs.sii.grh.business.model.dto.FormationCatalogueDto;
import dz.gov.mesrs.sii.grh.business.service.formation.FormationCatalogueService;


@Service("formationCatalogueService")
public class FormationCatalogueServiceImpl extends CommonServiceImpl<FormationCatalogue , FormationCatalogueDto,Integer> implements FormationCatalogueService  {
	
	private FormationCatalogueDao	formationCatalogueDao;

	@Override
	protected CommonDao<FormationCatalogue, Integer> getDao() {
		return formationCatalogueDao;
	}

	/**
	 * @return the FormationCatalogueDao
	 */
	public FormationCatalogueDao getFormationCatalogueDao() {
		return formationCatalogueDao;
	}

	/**
	 * @param FormationCatalogueDao  to set
	 */
	@Autowired
	public void setFormationCatalogueDao(FormationCatalogueDao formationCatalogueDao) {
		this.formationCatalogueDao = formationCatalogueDao;
	}

	
	
}



