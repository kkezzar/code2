package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Ordonnateur;
import dz.gov.mesrs.sii.gfc.business.model.dto.OrdonnateurDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.OrdonnateurDao;
import dz.gov.mesrs.sii.gfc.business.service.OrdonnateurService;

/**
 * Service Implementation for domain model class Ordonnateur.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("ordonnateurService")
@Transactional
public class OrdonnateurServiceImpl extends CommonServiceImpl<Ordonnateur,OrdonnateurDto, Integer> implements OrdonnateurService {

	@Autowired
	@Qualifier ("ordonnateurDao")
	private OrdonnateurDao ordonnateurDao;

    @Autowired
	private Mapper mapper;
	
	
	public OrdonnateurServiceImpl(){

	}
	
	@Override
	protected CommonDao<Ordonnateur, Integer> getDao() {
		return ordonnateurDao;
	}
}