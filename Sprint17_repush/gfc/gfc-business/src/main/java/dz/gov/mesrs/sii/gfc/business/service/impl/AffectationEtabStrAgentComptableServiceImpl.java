package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationEtabStrAgentComptableDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationEtabStrAgentComptable;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabStrAgentComptableDto;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabStrAgentComptableService;

/**
 * Service Implementation for domain model class
 * AffectationEtabStrAgentComptable.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("affectationEtabStrAgentComptableService")
@Transactional
public class AffectationEtabStrAgentComptableServiceImpl
		extends
		CommonServiceImpl<AffectationEtabStrAgentComptable, AffectationEtabStrAgentComptableDto, Integer>
		implements AffectationEtabStrAgentComptableService {

	@Autowired
	@Qualifier("affectationEtabStrAgentComptableDao")
	private AffectationEtabStrAgentComptableDao affectationEtabStrAgentComptableDao;

	@Autowired
	private Mapper mapper;

	public AffectationEtabStrAgentComptableServiceImpl() {

	}

	@Override
	protected CommonDao<AffectationEtabStrAgentComptable, Integer> getDao() {
		return affectationEtabStrAgentComptableDao;
	}
}