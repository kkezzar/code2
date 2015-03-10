package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SituationSportiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationSportive;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationSportiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationSportiveService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("situationSportiveService")
public class SituationSportiveServiceImpl implements SituationSportiveService {

	@Autowired
	@Qualifier("situationSportiveDao")
	private SituationSportiveDao situationSportiveDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory
			.getLog(SituationSportiveServiceImpl.class);

	public SituationSportiveServiceImpl() {
//		final List<String> mappings = new ArrayList<String>(1);
//		mappings.add(UtilConstants.DOZER_SITUATION_SPORTIVE_MAPPING_NAME);
//		mapper = new DozerBeanMapper();
//		mapper.setMappingFiles(mappings);
	}

	@Override
	public SituationSportiveDto insertOrUpdate(
			SituationSportiveDto situationSportiveDto) {
		try {
			SituationSportive situationSportive = mapper.map(
					situationSportiveDto, SituationSportive.class);

			if (situationSportive.getId() == 0)
				situationSportiveDao.persist(situationSportive);
			else
				situationSportive = situationSportiveDao
						.merge(situationSportive);

			mapper.map(situationSportive, situationSportiveDto);

			return situationSportiveDto;
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}
	}

	@Override
	public void remove(SituationSportiveDto situationSportiveDto) {
		try {
			SituationSportive situationSportive = mapper.map(
					situationSportiveDto, SituationSportive.class);
			situationSportive = situationSportiveDao.merge(situationSportive);
			situationSportiveDao.remove(situationSportive);
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}
	}

	@Override
	public List<SituationSportiveDto> findByQuery(String query) {
		try {
			List<SituationSportive> situationSportives = situationSportiveDao
					.findByQuery(query);

			List<SituationSportiveDto> situationSportiveDtos = new ArrayList<SituationSportiveDto>();

			for (SituationSportive situationSportive : situationSportives) {
				situationSportiveDtos.add(mapper.map(situationSportive,
						SituationSportiveDto.class));
			}

			return situationSportiveDtos;
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}

	}

	@Override
	public SituationSportiveDto findById(int id) {
		try {
			SituationSportive situationSportive = situationSportiveDao
					.findById(id);

			if (situationSportive != null)
				return mapper
						.map(situationSportive, SituationSportiveDto.class);

			return null;
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}
	}

	@Override
	public List<SituationSportiveDto> findAll() {
		try {
			List<SituationSportive> situationSportives = situationSportiveDao
					.findAll();

			List<SituationSportiveDto> situationSportiveDtos = new ArrayList<SituationSportiveDto>();

			for (SituationSportive situationSportive : situationSportives) {
				situationSportiveDtos.add(mapper.map(situationSportive,
						SituationSportiveDto.class));
			}

			return situationSportiveDtos;
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}

	}

	@Override
	public List<SituationSportiveDto> findByIdDossier(Integer id) {
		try {

			List<SituationSportive> situationSportives = situationSportiveDao
					.findByIdDossier(id);

			List<SituationSportiveDto> situationSportiveDtos = new ArrayList<SituationSportiveDto>();

			for (SituationSportive situationSportive : situationSportives) {
				situationSportiveDtos.add(mapper.map(situationSportive,
						SituationSportiveDto.class));
			}

			return situationSportiveDtos;
		} catch (RuntimeException e) {
			log.error("findByIdDossiers failed", e);
			throw e;
		}
	}

	@Override
	public SituationSportiveDto findSituationSportive(int id, Date dateDebut,
			Date dateFin, String refCodeType) {
		try {
			SituationSportive situationSportive = situationSportiveDao
					.findSituationSportive(id, dateDebut, dateFin, refCodeType);
			log.info("findSituationSportive success");
			if (situationSportive == null) {
				return null;
			}
			return mapper.map(situationSportive, SituationSportiveDto.class);
		} catch (RuntimeException e) {
			log.error("findSituationSportive failed", e);
			throw e;

		}
	}

	/**
	 * [SituationSportiveServiceImpl.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 mai 2014 16:58:54
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [SituationSportiveServiceImpl.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 mai 2014 16:58:54
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

}
