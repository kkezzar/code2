package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Regisseur;
import dz.gov.mesrs.sii.gfc.business.model.dto.RegisseurDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.RegisseurDao;
import dz.gov.mesrs.sii.gfc.business.service.RegisseurService;

/**
 * Service Implementation for domain model class Regisseur.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("regisseurService")
@Transactional
public class RegisseurServiceImpl extends CommonServiceImpl<Regisseur,RegisseurDto, Integer> implements RegisseurService {

	@Autowired
	@Qualifier ("regisseurDao")
	private RegisseurDao regisseurDao;

    @Autowired
	private Mapper mapper;
	
	
	public RegisseurServiceImpl(){

	}
	
	@Override
	protected CommonDao<Regisseur, Integer> getDao() {
		return regisseurDao;
	}
}