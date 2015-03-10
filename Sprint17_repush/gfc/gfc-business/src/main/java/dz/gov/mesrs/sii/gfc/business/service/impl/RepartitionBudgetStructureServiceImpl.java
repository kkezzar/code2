package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RepartitionBudgetStructureDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.RepartitionBudgetStructure;
import dz.gov.mesrs.sii.gfc.business.model.dto.RepartitionBudgetStructureDto;
import dz.gov.mesrs.sii.gfc.business.service.RepartitionBudgetStructureService;

/**
 * Service Implementation for domain model class
 * DetailsMouvementBudgetStructure.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 25/02/2015 11:54:25
 */
@Service("repartitionBudgetStructureService")
@Transactional
public class RepartitionBudgetStructureServiceImpl extends
		CommonServiceImpl<RepartitionBudgetStructure, RepartitionBudgetStructureDto, Integer> implements
		RepartitionBudgetStructureService {

	@Autowired
	@Qualifier("repartitionBudgetStructureDao")
	private RepartitionBudgetStructureDao repartitionBudgetStructureDao;

	@Autowired
	private Mapper mapper;

	public RepartitionBudgetStructureServiceImpl() {

	}

	@Override
	protected CommonDao<RepartitionBudgetStructure, Integer> getDao() {
		return repartitionBudgetStructureDao;
	}
}