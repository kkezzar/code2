package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsMouvementBudgetStructureDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsMouvementBudgetStructure;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsMouvementBudgetStructureDto;
import dz.gov.mesrs.sii.gfc.business.service.DetailsMouvementBudgetStructureService;

/**
 * Service Implementation for domain model class
 * DetailsMouvementBudgetStructure.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 25/02/2015 11:54:25
 */
@Service("detailsMouvementBudgetStructureService")
@Transactional
public class DetailsMouvementBudgetStructureServiceImpl extends
		CommonServiceImpl<DetailsMouvementBudgetStructure, DetailsMouvementBudgetStructureDto, Integer> implements
		DetailsMouvementBudgetStructureService {

	@Autowired
	@Qualifier("detailsMouvementBudgetStructureDao")
	private DetailsMouvementBudgetStructureDao detailsMouvementBudgetStructureDao;

	@Autowired
	private Mapper mapper;

	public DetailsMouvementBudgetStructureServiceImpl() {

	}

	@Override
	protected CommonDao<DetailsMouvementBudgetStructure, Integer> getDao() {
		return detailsMouvementBudgetStructureDao;
	}
}