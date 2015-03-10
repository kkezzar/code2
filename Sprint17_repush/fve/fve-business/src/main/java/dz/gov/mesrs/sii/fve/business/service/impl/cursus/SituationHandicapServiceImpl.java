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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SituationHandicapDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SituationHandicap;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SituationHandicapDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SituationHandicapService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("situationHandicapService")
public class SituationHandicapServiceImpl implements SituationHandicapService {

	@Autowired
	@Qualifier("situationHandicapDao")
	private SituationHandicapDao situationHandicapDao;
	private static final Log log = LogFactory
			.getLog(SituationHandicapServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public SituationHandicapServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public SituationHandicapDto insertOrUpdate(
			SituationHandicapDto situationHandicapDto) {
		try {
			SituationHandicap situationHandicap = mapper.map(
					situationHandicapDto, SituationHandicap.class);

			if (situationHandicap.getId() == 0)
				situationHandicapDao.persist(situationHandicap);
			else
				situationHandicap = situationHandicapDao
						.merge(situationHandicap);

			mapper.map(situationHandicap, situationHandicapDto);

			return situationHandicapDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(SituationHandicapDto situationHandicapDto) {
		try {
			SituationHandicap situationHandicap = mapper.map(
					situationHandicapDto, SituationHandicap.class);
			situationHandicap = situationHandicapDao.merge(situationHandicap);
			situationHandicapDao.remove(situationHandicap);
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<SituationHandicapDto> findByQuery(String query) {

		List<SituationHandicap> situationHandicaps = situationHandicapDao
				.findByQuery(query);

		List<SituationHandicapDto> situationHandicapDtos = new ArrayList<SituationHandicapDto>();

		for (SituationHandicap situationHandicap : situationHandicaps) {
			situationHandicapDtos.add(mapper.map(situationHandicap,
					SituationHandicapDto.class));
		}

		return situationHandicapDtos;

	}

	@Override
	public SituationHandicapDto findById(int id) {

		SituationHandicap situationHandicap = situationHandicapDao.findById(id);

		if (situationHandicap != null)
			return mapper.map(situationHandicap, SituationHandicapDto.class);

		return null;
	}

	@Override
	public List<SituationHandicapDto> findAll() {

		List<SituationHandicap> situationHandicaps = situationHandicapDao
				.findAll();

		List<SituationHandicapDto> situationHandicapDtos = new ArrayList<SituationHandicapDto>();

		for (SituationHandicap situationHandicap : situationHandicaps) {
			situationHandicapDtos.add(mapper.map(situationHandicap,
					SituationHandicapDto.class));
		}

		return situationHandicapDtos;

	}

	@Override
	public List<SituationHandicapDto> findByIdDossier(Integer id) {
		try {
			List<SituationHandicap> situationHandicaps = situationHandicapDao
					.findByIdDossier(id);

			List<SituationHandicapDto> situationHandicapDtos = new ArrayList<SituationHandicapDto>();

			for (SituationHandicap situationHandicap : situationHandicaps) {
				situationHandicapDtos.add(mapper.map(situationHandicap,
						SituationHandicapDto.class));
			}

			return situationHandicapDtos;
		} catch (RuntimeException e) {
			log.error("findByIdDossier failed", e);
			throw e;
		}
	}

	@Override
	public void remove(int id) {
		try {
			SituationHandicap situationHandicap = situationHandicapDao
					.findById(id);
			situationHandicapDao.remove(situationHandicap);
		} catch (RuntimeException e) {
			log.error("findByIdDossier failed", e);
			throw e;
		}
	}

	@Override
	public SituationHandicapDto findSituationHandicap(int id, Date dateDebut,
			Date dateFin, String refCodeType) {
		try {
			SituationHandicap situationHandicap = situationHandicapDao
					.findSituationHandicap(id, dateDebut, dateFin, refCodeType);
			log.info("findSituationHandicap success");
			if (situationHandicap == null) {
				return null;
			}
			return mapper.map(situationHandicap, SituationHandicapDto.class);
		} catch (RuntimeException e) {
			log.error("findSituationHandicap failed", e);
			throw e;

		}
	}
}
