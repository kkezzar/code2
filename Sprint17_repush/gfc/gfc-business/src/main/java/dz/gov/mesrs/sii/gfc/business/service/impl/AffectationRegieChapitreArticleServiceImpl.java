package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationRegieChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationRegieChapitreArticle;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationRegieChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.service.AffectationRegieChapitreArticleService;

/**
 * Service Implementation for domain model class
 * AffectationRegieChapitreArticle.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("affectationRegieChapitreArticleService")
@Transactional
public class AffectationRegieChapitreArticleServiceImpl
		extends
		CommonServiceImpl<AffectationRegieChapitreArticle, AffectationRegieChapitreArticleDto, Integer>
		implements AffectationRegieChapitreArticleService {

	@Autowired
	@Qualifier("affectationRegieChapitreArticleDao")
	private AffectationRegieChapitreArticleDao affectationRegieChapitreArticleDao;

	@Autowired
	private Mapper mapper;

	public AffectationRegieChapitreArticleServiceImpl() {

	}

	@Override
	protected CommonDao<AffectationRegieChapitreArticle, Integer> getDao() {
		return affectationRegieChapitreArticleDao;
	}
}