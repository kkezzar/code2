package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.AgentComptable;
import dz.gov.mesrs.sii.gfc.business.model.dto.AgentComptableDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AgentComptableDao;
import dz.gov.mesrs.sii.gfc.business.service.AgentComptableService;

/**
 * Service Implementation for domain model class AgentComptable.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("agentComptableService")
@Transactional
public class AgentComptableServiceImpl extends CommonServiceImpl<AgentComptable,AgentComptableDto, Integer> implements AgentComptableService {

	@Autowired
	@Qualifier ("agentComptableDao")
	private AgentComptableDao agentComptableDao;

    @Autowired
	private Mapper mapper;
	
	
	public AgentComptableServiceImpl(){

	}
	
	@Override
	protected CommonDao<AgentComptable, Integer> getDao() {
		return agentComptableDao;
	}
}