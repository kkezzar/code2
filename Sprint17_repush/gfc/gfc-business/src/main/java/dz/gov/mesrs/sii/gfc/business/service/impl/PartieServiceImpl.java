package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Partie;
import dz.gov.mesrs.sii.gfc.business.model.dto.PartieDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.PartieDao;
import dz.gov.mesrs.sii.gfc.business.service.PartieService;

/**
 * Service Implementation for domain model class Partie.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("partieService")
@Transactional
public class PartieServiceImpl extends CommonServiceImpl<Partie,PartieDto, Integer> implements PartieService {

	@Autowired
	@Qualifier ("partieDao")
	private PartieDao partieDao;

    @Autowired
	private Mapper mapper;
	
	
	public PartieServiceImpl(){

	}
	
	@Override
	protected CommonDao<Partie, Integer> getDao() {
		return partieDao;
	}
}