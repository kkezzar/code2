package dz.gov.mesrs.sii.grh.business.service.referentiel.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.RubriqueDao;
import dz.gov.mesrs.sii.commons.data.dao.impl.grh.FiliereDaoImpl;
import dz.gov.mesrs.sii.commons.data.model.grh.Rubrique;
import dz.gov.mesrs.sii.commons.data.util.UtilitiesProgres;
import dz.gov.mesrs.sii.grh.business.model.dto.RubriqueDto;
import dz.gov.mesrs.sii.grh.business.service.referentiel.RubriqueService;

/**
 * 
 * @author BELBRIK
 * @version V1.0
 * @date 17-11-2014
 * @description Implementation des services
 * 
 */

@Service("rubriqueService")
public class RubriqueServiceImpl extends CommonServiceImpl<Rubrique,RubriqueDto, Integer> implements RubriqueService  {
	
	private RubriqueDao rubriqueDao;

	private static final Logger log = LoggerFactory.getLogger(RubriqueServiceImpl.class);
	
	@Override
	protected CommonDao<Rubrique, Integer> getDao() {
		return rubriqueDao;
	}

	/**
	 * @return the RubriqueDao
	 */
	public RubriqueDao getRubriquefDao() {
		return rubriqueDao;
	}
	
	
	/**
	 * @param RubriqueDao
	 *            to set
	 */
	@Autowired
	public void setRubriqueDao(RubriqueDao rubriqueDao) {
		this.rubriqueDao = rubriqueDao;
	}
	
	@Override
	public List<RubriqueDto> getListValideRubriques() {
		List<RubriqueDto> rubriqueDtoList = new ArrayList<RubriqueDto>();

		Date curentDate = UtilitiesProgres.getCurrentDate();
		List<Rubrique> rubriqueList = rubriqueDao.findAll();
		for (Rubrique rubrique : rubriqueList) {
			RubriqueDto dto = new RubriqueDto();
				mapper.map(rubrique, dto);
				rubriqueDtoList.add(dto);
			

		}

		return rubriqueDtoList;
	}
	
	
	@Override
	public void remove(RubriqueDto rubriqueDto) {

		Rubrique rubrique = mapper.map(rubriqueDto,
				Rubrique.class);
		
		rubrique = rubriqueDao.merge(rubrique);

		rubriqueDao.remove(rubrique);
	}
//
//	
//	@Override
//	public RubriqueDto findById(int id) {
//
//		Rubrique rubrique = rubriqueDao.findById(id);
//
//		if (rubrique != null)
//			return mapper.map(rubrique, RubriqueDto.class);
//
//		return null;
//	}
//
	@Override
	public List<RubriqueDto> findAll() {
		try {
			List<RubriqueDto> result = new ArrayList<RubriqueDto>();

			List<Rubrique> resultListDao = rubriqueDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Rubrique rubrique : resultListDao) {
					RubriqueDto rubriqueDto = new RubriqueDto();
					mapper.map(rubrique, rubriqueDto);
					result.add(rubriqueDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}

//	@Override
//	public RubriqueDto insertOrUpdate(RubriqueDto rubriqueDto) {
//		try {
//			
//			Rubrique rubrique = mapper.map(rubriqueDto,
//					Rubrique.class);
//
//			if (rubrique.getId() == 0) {
//				rubriqueDao.persist(rubrique);
//			} else {
//				rubrique = rubriqueDao.merge(rubrique);
//			}
//			
//			mapper.map(rubrique, rubriqueDto);
//
//			log.info("insertOrUpdate success");
//			return rubriqueDto;
//		} catch (Exception e) {
//			log.error("insertOrUpdate failed", e);
//			throw e;
//		}
//	}


}



