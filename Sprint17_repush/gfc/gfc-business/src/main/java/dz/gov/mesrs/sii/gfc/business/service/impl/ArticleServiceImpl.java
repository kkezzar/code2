package dz.gov.mesrs.sii.gfc.business.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;

import dz.gov.mesrs.sii.commons.data.model.gfc.Article;
import dz.gov.mesrs.sii.gfc.business.model.dto.ArticleDto;
import dz.gov.mesrs.sii.commons.data.dao.gfc.ArticleDao;
import dz.gov.mesrs.sii.gfc.business.service.ArticleService;

/**
 * Service Implementation for domain model class Article.
 * @author Mounir.MESSAOUDI (MESRS CCM)
 * @version 1.0
 * @created 24/11/2014 18:54:25
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl extends CommonServiceImpl<Article,ArticleDto, Integer> implements ArticleService {

	@Autowired
	@Qualifier ("articleDao")
	private ArticleDao articleDao;

    @Autowired
	private Mapper mapper;
	
	
	public ArticleServiceImpl(){

	}
	
	@Override
	protected CommonDao<Article, Integer> getDao() {
		return articleDao;
	}
}