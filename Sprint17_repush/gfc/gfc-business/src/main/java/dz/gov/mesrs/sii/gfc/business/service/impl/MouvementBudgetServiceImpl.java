package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.MouvementBudget;
import dz.gov.mesrs.sii.gfc.business.model.dto.MouvementBudgetDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.MouvementBudgetDao;
import dz.gov.mesrs.sii.gfc.business.service.MouvementBudgetService;

/**
 * Service Implementation for domain model class MouvementBudget.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("mouvementBudgetService")
@Transactional
public class MouvementBudgetServiceImpl extends CommonServiceImpl<MouvementBudget,MouvementBudgetDto, Integer> implements MouvementBudgetService {

	@Autowired
	@Qualifier ("mouvementBudgetDao")
	private MouvementBudgetDao mouvementBudgetDao;

    @Autowired
	private Mapper mapper;
	
	
	public MouvementBudgetServiceImpl(){

	}
	
	@Override
	protected CommonDao<MouvementBudget, Integer> getDao() {
		return mouvementBudgetDao;
	}
}