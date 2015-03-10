package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsProjetBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsProjetBudgetDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsProjetBudgetDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsProjetBudgetService;

/**
 * Service Implementation for domain model class DetailsProjetBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsProjetBudgetService")
@Transactional
public class DetailsProjetBudgetServiceImpl extends CommonServiceImpl<DetailsProjetBudget,DetailsProjetBudgetDto, Integer> implements DetailsProjetBudgetService {

	@Autowired
	@Qualifier ("detailsProjetBudgetDao")
	private DetailsProjetBudgetDao detailsProjetBudgetDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsProjetBudgetServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsProjetBudget, Integer> getDao() {
		return detailsProjetBudgetDao;
	}
}