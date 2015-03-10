package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.gfc.AffectationEtabChapitreArticleDao;
import dz.gov.mesrs.sii.commons.data.model.gfc.AffectationEtabChapitreArticle;
import dz.gov.mesrs.sii.gfc.business.model.dto.AffectationEtabChapitreArticleDto;
import dz.gov.mesrs.sii.gfc.business.service.AffectationEtabChapitreArticleService;

/**
 * Service Implementation for domain model class AffectationEtabChapitreArticle.
 * 
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("affectationEtabChapitreArticleService")
@Transactional
public class AffectationEtabChapitreArticleServiceImpl
		extends
		CommonServiceImpl<AffectationEtabChapitreArticle, AffectationEtabChapitreArticleDto, Integer>
		implements AffectationEtabChapitreArticleService {

	@Autowired
	@Qualifier("affectationEtabChapitreArticleDao")
	private AffectationEtabChapitreArticleDao affectationEtabChapitreArticleDao;

	@Autowired
	private Mapper mapper;

	public AffectationEtabChapitreArticleServiceImpl() {

	}

	@Override
	protected CommonDao<AffectationEtabChapitreArticle, Integer> getDao() {
		return affectationEtabChapitreArticleDao;
	}
}