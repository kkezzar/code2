package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.ConsolidationBesoins;
import dz.gov.mesrs.sii.gfc.business.model.dto.ConsolidationBesoinsDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ConsolidationBesoinsDao;
import dz.gov.mesrs.sii.gfc.business.service.ConsolidationBesoinsService;

/**
 * Service Implementation for domain model class ConsolidationBesoins.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("consolidationBesoinsService")
@Transactional
public class ConsolidationBesoinsServiceImpl extends CommonServiceImpl<ConsolidationBesoins,ConsolidationBesoinsDto, Integer> implements ConsolidationBesoinsService {

	@Autowired
	@Qualifier ("consolidationBesoinsDao")
	private ConsolidationBesoinsDao consolidationBesoinsDao;

    @Autowired
	private Mapper mapper;
	
	
	public ConsolidationBesoinsServiceImpl(){

	}
	
	@Override
	protected CommonDao<ConsolidationBesoins, Integer> getDao() {
		return consolidationBesoinsDao;
	}
}