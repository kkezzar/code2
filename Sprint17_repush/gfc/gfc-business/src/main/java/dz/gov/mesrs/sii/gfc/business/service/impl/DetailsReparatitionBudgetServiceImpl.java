package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsReparatitionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsReparatitionBudgetDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsReparatitionBudgetDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsReparatitionBudgetService;

/**
 * Service Implementation for domain model class DetailsReparatitionBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsReparatitionBudgetService")
@Transactional
public class DetailsReparatitionBudgetServiceImpl extends CommonServiceImpl<DetailsReparatitionBudget,DetailsReparatitionBudgetDto, Integer> implements DetailsReparatitionBudgetService {

	@Autowired
	@Qualifier ("detailsReparatitionBudgetDao")
	private DetailsReparatitionBudgetDao detailsReparatitionBudgetDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsReparatitionBudgetServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsReparatitionBudget, Integer> getDao() {
		return detailsReparatitionBudgetDao;
	}
}