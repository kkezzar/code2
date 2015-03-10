package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsMouvementBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsMouvementBudgetDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsMouvementBudgetDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsMouvementBudgetService;

/**
 * Service Implementation for domain model class DetailsMouvementBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsMouvementBudgetService")
@Transactional
public class DetailsMouvementBudgetServiceImpl extends CommonServiceImpl<DetailsMouvementBudget,DetailsMouvementBudgetDto, Integer> implements DetailsMouvementBudgetService {

	@Autowired
	@Qualifier ("detailsMouvementBudgetDao")
	private DetailsMouvementBudgetDao detailsMouvementBudgetDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsMouvementBudgetServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsMouvementBudget, Integer> getDao() {
		return detailsMouvementBudgetDao;
	}
}