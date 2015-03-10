package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Regie;
import dz.gov.mesrs.sii.gfc.business.model.dto.RegieDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RegieDao;
import dz.gov.mesrs.sii.gfc.business.service.RegieService;

/**
 * Service Implementation for domain model class Regie.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("regieService")
@Transactional
public class RegieServiceImpl extends CommonServiceImpl<Regie,RegieDto, Integer> implements RegieService {

	@Autowired
	@Qualifier ("regieDao")
	private RegieDao regieDao;

    @Autowired
	private Mapper mapper;
	
	
	public RegieServiceImpl(){

	}
	
	@Override
	protected CommonDao<Regie, Integer> getDao() {
		return regieDao;
	}
}