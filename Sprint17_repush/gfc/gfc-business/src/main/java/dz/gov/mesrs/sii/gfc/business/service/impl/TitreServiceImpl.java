package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Titre;
import dz.gov.mesrs.sii.gfc.business.model.dto.TitreDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.TitreDao;
import dz.gov.mesrs.sii.gfc.business.service.TitreService;

/**
 * Service Implementation for domain model class Titre.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("titreService")
@Transactional
public class TitreServiceImpl extends CommonServiceImpl<Titre,TitreDto, Integer> implements TitreService {

	@Autowired
	@Qualifier ("titreDao")
	private TitreDao titreDao;

    @Autowired
	private Mapper mapper;
	
	
	public TitreServiceImpl(){

	}
	
	@Override
	protected CommonDao<Titre, Integer> getDao() {
		return titreDao;
	}
}