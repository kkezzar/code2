package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Compte;
import dz.gov.mesrs.sii.gfc.business.model.dto.CompteDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.CompteDao;
import dz.gov.mesrs.sii.gfc.business.service.CompteService;

/**
 * Service Implementation for domain model class Compte.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("compteService")
@Transactional
public class CompteServiceImpl extends CommonServiceImpl<Compte, CompteDto, Integer> implements CompteService {

	@Autowired
	@Qualifier("compteDao")
	private CompteDao compteDao;

	@Autowired
	private Mapper mapper;

	public CompteServiceImpl() {

	}

	@Override
	protected CommonDao<Compte, Integer> getDao() {
		return compteDao;
	}
}