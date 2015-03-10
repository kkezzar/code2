package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsNotificationDecisionBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsNotificationDecisionBudgetDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsNotificationDecisionBudgetDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsNotificationDecisionBudgetService;

/**
 * Service Implementation for domain model class DetailsNotificationDecisionBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsNotificationDecisionBudgetService")
@Transactional
public class DetailsNotificationDecisionBudgetServiceImpl extends CommonServiceImpl<DetailsNotificationDecisionBudget,DetailsNotificationDecisionBudgetDto, Integer> implements DetailsNotificationDecisionBudgetService {

	@Autowired
	@Qualifier ("detailsNotificationDecisionBudgetDao")
	private DetailsNotificationDecisionBudgetDao detailsNotificationDecisionBudgetDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsNotificationDecisionBudgetServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsNotificationDecisionBudget, Integer> getDao() {
		return detailsNotificationDecisionBudgetDao;
	}
}