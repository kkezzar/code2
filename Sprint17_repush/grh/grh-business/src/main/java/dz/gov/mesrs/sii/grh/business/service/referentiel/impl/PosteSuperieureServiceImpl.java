package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.model.grh.PosteSuperieure;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteSuperieureDto;
import dz.gov.mesrs.sii.commons.data.dao.grh.PosteSuperieureDao;
import dz.gov.mesrs.sii.grh.business.service.referentiel.PosteSuperieureService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
 

@Service("posteSuperieureService")
public class PosteSuperieureServiceImpl extends CommonServiceImpl<PosteSuperieure,PosteSuperieureDto, Integer> implements PosteSuperieureService  {
	
	private PosteSuperieureDao	posteSuperieureDao;

	@Override
	protected CommonDao<PosteSuperieure, Integer> getDao() {
		return posteSuperieureDao;
	}

	/**
	 * @return the posteSuperieureDao
	 */
	public PosteSuperieureDao getPosteSuperieureDao() {
		return posteSuperieureDao;
	}

	/**
	 * @param posteSuperieureDao  to set
	 */
	@Autowired
	public void setPosteSuperieureDao(PosteSuperieureDao posteSuperieureDao) {
		this.posteSuperieureDao = posteSuperieureDao;
	}}



