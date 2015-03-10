package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Fonds;
import dz.gov.mesrs.sii.gfc.business.model.dto.FondsDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.FondsDao;
import dz.gov.mesrs.sii.gfc.business.service.FondsService;

/**
 * Service Implementation for domain model class Fonds.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("fondsService")
@Transactional
public class FondsServiceImpl extends CommonServiceImpl<Fonds,FondsDto, Integer> implements FondsService {

	@Autowired
	@Qualifier ("fondsDao")
	private FondsDao fondsDao;

    @Autowired
	private Mapper mapper;
	
	
	public FondsServiceImpl(){

	}
	
	@Override
	protected CommonDao<Fonds, Integer> getDao() {
		return fondsDao;
	}
}