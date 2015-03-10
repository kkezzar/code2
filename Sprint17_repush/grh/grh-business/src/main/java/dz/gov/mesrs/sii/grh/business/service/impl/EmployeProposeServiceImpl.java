package dz.gov.mesrs.sii.grh.business.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.EmployePropose;
import dz.gov.mesrs.sii.commons.data.model.grh.PropostionAvancement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EmployeProposeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PropostionAvancementDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.EmployeProposeDao;
import dz.gov.mesrs.sii.grh.business.service.EmployeProposeService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */

@Service("employeProposeService")
public class EmployeProposeServiceImpl extends CommonServiceImpl<EmployePropose, EmployeProposeDto,Long> implements EmployeProposeService  {
	
	private EmployeProposeDao	employeProposeDao;

	@Override
	protected CommonDao<EmployePropose, Long> getDao() {
		return employeProposeDao;
	}

	/**
	 * @return the employeProposeDao
	 */
	public EmployeProposeDao getEmployeProposeDao() {
		return employeProposeDao;
	}

	/**
	 * @param employeProposeDao  to set
	 */
	@Autowired
	
	
	
	
	public void setEmployeProposeDao(EmployeProposeDao employeProposeDao) {
		this.employeProposeDao = employeProposeDao;
	}
	
	@Override
	public List<DossierEmployeDto> findlistEmployesDernierAvancementPromotionParList(RefEtablissementDto refEtablissementDto){
		
		List<DossierEmployeDto> listDossierEmployeDto = new ArrayList<DossierEmployeDto>();
		RefEtablissement refEtablissement = mapper.map(refEtablissementDto, RefEtablissement.class);
		
		List<DossierEmploye> listDossierEmploye = employeProposeDao.findlistEmployesDernierAvancementPromotionParList(refEtablissement);			
		if(listDossierEmploye != null)if(!listDossierEmploye.isEmpty())
		for (DossierEmploye dossierEmploye : listDossierEmploye) {
			DossierEmployeDto dossierEmployeDto = mapper.map(dossierEmploye, DossierEmployeDto.class);
			listDossierEmployeDto.add(dossierEmployeDto);
		}			
		return listDossierEmployeDto;
		
		
	}
	
	
	
	
	
}



