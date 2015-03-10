package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.DetailsFicheBesoins;
import dz.gov.mesrs.sii.gfc.business.model.dto.DetailsFicheBesoinsDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.DetailsFicheBesoinsDao;
import dz.gov.mesrs.sii.gfc.business.service.DetailsFicheBesoinsService;

/**
 * Service Implementation for domain model class DetailsFicheBesoins.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("detailsFicheBesoinsService")
@Transactional
public class DetailsFicheBesoinsServiceImpl extends CommonServiceImpl<DetailsFicheBesoins,DetailsFicheBesoinsDto, Integer> implements DetailsFicheBesoinsService {

	@Autowired
	@Qualifier ("detailsFicheBesoinsDao")
	private DetailsFicheBesoinsDao detailsFicheBesoinsDao;

    @Autowired
	private Mapper mapper;
	
	
	public DetailsFicheBesoinsServiceImpl(){

	}
	
	@Override
	protected CommonDao<DetailsFicheBesoins, Integer> getDao() {
		return detailsFicheBesoinsDao;
	}
}