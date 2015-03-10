package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ExclusionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Exclusion;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ExclusionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExclusionService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 02-07-2014 16:44:07
 */
 
@Service("exclusionService")
public class ExclusionServiceImpl implements ExclusionService  {

	private static final Log log = LogFactory
			.getLog(ExclusionServiceImpl.class);
	
	@Autowired
	@Qualifier ("exclusionDao")
	private ExclusionDao exclusionDao;


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public ExclusionServiceImpl(){

	}

	
	@Override
	public ExclusionDto  insertOrUpdate(
			ExclusionDto  exclusionDto) {
		try {
			Exclusion  exclusion = mapper.map( exclusionDto, Exclusion.class);

			if ( exclusion.getId() == 0) {
				exclusionDao.persist(exclusion);
			} else {
						
				exclusion = exclusionDao.merge(exclusion);
			}
			mapper.map(exclusion, exclusionDto);
			log.info("insertOrUpdate success");
			return exclusionDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<ExclusionDto> findByQuery(String query) {
	
	    List<Exclusion> exclusions = exclusionDao.findByQuery(query);

		List<ExclusionDto> exclusionDtos = new ArrayList<ExclusionDto>();

		for (Exclusion exclusion : exclusions) {
			exclusionDtos.add(mapper.map(exclusion, ExclusionDto.class));
		}

		return exclusionDtos;
	
	}
	
	
	@Override
	public void remove(ExclusionDto exclusionDto) {

		Exclusion exclusion = mapper.map(exclusionDto,
				Exclusion.class);

		exclusionDao.remove(exclusion);
	}



	@Override
	public ExclusionDto findById(int id) {

		Exclusion exclusion = exclusionDao.findById(id);

		if (exclusion != null)
			return mapper.map(exclusion, ExclusionDto.class);

		return null;
	}

	@Override
	public List<ExclusionDto> findAll() {
		try {
			List<ExclusionDto> result = new ArrayList<ExclusionDto>();

			List<Exclusion> resultListDao = exclusionDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Exclusion exclusion : resultListDao) {
					ExclusionDto exclusionDto = new ExclusionDto();
					mapper.map(exclusion, exclusionDto);
					result.add(exclusionDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}

		return null;
	}

	@Override
	public List<ExclusionDto> findAdvanced(ExclusionDto exclusionSearchDto) {
		try {
			List<ExclusionDto> result = new ArrayList<ExclusionDto>();
			Exclusion searchBo = new Exclusion();
			mapper.map(exclusionSearchDto, searchBo);
			List<Exclusion> resultListDao = exclusionDao
					.findAdvanced(searchBo);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Exclusion exclusion : resultListDao) {
					ExclusionDto exclusionDto = new ExclusionDto();
					mapper.map(exclusion, exclusionDto);
					result.add(exclusionDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}

		return null;

	}
	
	
	@Override
	public ExclusionDto findByIdDossier(int idDossier) {
		
		Exclusion exclusion = exclusionDao
				.findByIdDossier(idDossier);

		if (exclusion != null)
			return mapper.map(exclusion, ExclusionDto.class);

		return null;
	}

	
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(ExclusionDto exclusionDto) {
		Exclusion exclusion = new Exclusion();
		try {
			mapper.map(exclusionDto, exclusion);
			exclusion = exclusionDao.merge(exclusion);
			exclusionDao.remove(exclusion);
			log.debug("remove Exclusion successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}
	
	@Override
	public List<ExclusionDto> findAdvanced2(
			ExclusionDto searchDto) {
		try {

			List<ExclusionDto> exclusionDtoList = new ArrayList<ExclusionDto>();
			Exclusion search = new Exclusion();
			mapper.map(searchDto, search);
			List<Exclusion> exclusionList = exclusionDao
					.findAdvanced2(search);
			if (exclusionList != null) {
				log.debug("Exclusion list success with size =  "
						+ exclusionList.size());

				for (Exclusion currentExclusion : exclusionList) {
					ExclusionDto exclusionDto = new ExclusionDto();
					mapper.map(currentExclusion, exclusionDto);

					exclusionDtoList.add(exclusionDto);
				}
				return exclusionDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findAllSections failed", e);
			throw e;

		}
		return null;
	}
	
	
}



