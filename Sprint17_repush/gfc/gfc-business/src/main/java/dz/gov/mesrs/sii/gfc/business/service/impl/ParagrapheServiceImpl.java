package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Paragraphe;
import dz.gov.mesrs.sii.gfc.business.model.dto.ParagrapheDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ParagrapheDao;
import dz.gov.mesrs.sii.gfc.business.service.ParagrapheService;

/**
 * Service Implementation for domain model class Paragraphe.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("paragrapheService")
@Transactional
public class ParagrapheServiceImpl extends CommonServiceImpl<Paragraphe,ParagrapheDto, Integer> implements ParagrapheService {

	@Autowired
	@Qualifier ("paragrapheDao")
	private ParagrapheDao paragrapheDao;

    @Autowired
	private Mapper mapper;
	
	
	public ParagrapheServiceImpl(){

	}
	
	@Override
	protected CommonDao<Paragraphe, Integer> getDao() {
		return paragrapheDao;
	}
}