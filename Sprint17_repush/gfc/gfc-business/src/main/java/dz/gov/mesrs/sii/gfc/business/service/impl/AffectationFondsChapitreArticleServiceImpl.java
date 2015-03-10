package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationFondsChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationFondsChapitreArticle;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationFondsChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.service.AffectationFondsChapitreArticleService;

/**
 * Service Implementation for domain model class
 * AffectationFondsChapitreArticle.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("affectationFondsChapitreArticleService")
@Transactional
public class AffectationFondsChapitreArticleServiceImpl
		extends
		CommonServiceImpl<AffectationFondsChapitreArticle, AffectationFondsChapitreArticleDto, Integer>
		implements AffectationFondsChapitreArticleService {

	@Autowired
	@Qualifier("affectationFondsChapitreArticleDao")
	private AffectationFondsChapitreArticleDao affectationFondsChapitreArticleDao;

	@Autowired
	private Mapper mapper;

	public AffectationFondsChapitreArticleServiceImpl() {

	}

	@Override
	protected CommonDao<AffectationFondsChapitreArticle, Integer> getDao() {
		return affectationFondsChapitreArticleDao;
	}
}