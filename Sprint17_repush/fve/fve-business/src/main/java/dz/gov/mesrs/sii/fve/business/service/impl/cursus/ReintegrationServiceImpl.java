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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ReintegrationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Reintegration;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReintegrationService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 02-07-2014 16:44:07
 */

@Service("reintegrationService")
public class ReintegrationServiceImpl implements ReintegrationService {

	private static final Log log = LogFactory
			.getLog(ReintegrationServiceImpl.class);

	@Autowired
	@Qualifier("reintegrationDao")
	private ReintegrationDao reintegrationDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public ReintegrationServiceImpl() {

	}

	@Override
	public ReintegrationDto insertOrUpdate(ReintegrationDto reintegrationDto) {
		try {
			Reintegration reintegration = mapper.map(reintegrationDto,
					Reintegration.class);

			if (reintegration.getId() == 0) {
				reintegrationDao.persist(reintegration);
			} else {

				reintegration = reintegrationDao.merge(reintegration);
			}
			mapper.map(reintegration, reintegrationDto);
			log.info("insertOrUpdate success");
			return reintegrationDto;
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}
	}

	@Override
	public List<ReintegrationDto> findByQuery(String query) {

		List<Reintegration> reintegrations = reintegrationDao
				.findByQuery(query);

		List<ReintegrationDto> reintegrationDtos = new ArrayList<ReintegrationDto>();

		for (Reintegration reintegration : reintegrations) {
			reintegrationDtos.add(mapper.map(reintegration,
					ReintegrationDto.class));
		}

		return reintegrationDtos;

	}

	@Override
	public void remove(ReintegrationDto reintegrationDto) {

		Reintegration reintegration = mapper.map(reintegrationDto,
				Reintegration.class);
		
		reintegration = reintegrationDao.merge(reintegration);

		reintegrationDao.remove(reintegration);
	}

	@Override
	public ReintegrationDto findById(int id) {

		Reintegration reintegration = reintegrationDao.findById(id);

		if (reintegration != null)
			return mapper.map(reintegration, ReintegrationDto.class);

		return null;
	}

	@Override
	public List<ReintegrationDto> findAll() {
		try {
			List<ReintegrationDto> result = new ArrayList<ReintegrationDto>();

			List<Reintegration> resultListDao = reintegrationDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Reintegration reintegration : resultListDao) {
					ReintegrationDto reintegrationDto = new ReintegrationDto();
					mapper.map(reintegration, reintegrationDto);
					result.add(reintegrationDto);
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
	public List<ReintegrationDto> findAdvanced(
			ReintegrationDto reintegrationSearchDto) {
		try {
			List<ReintegrationDto> result = new ArrayList<ReintegrationDto>();
			Reintegration searchBo = new Reintegration();
			mapper.map(reintegrationSearchDto, searchBo);
			List<Reintegration> resultListDao = reintegrationDao
					.findAdvanced(searchBo);
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Reintegration reintegration : resultListDao) {
					ReintegrationDto reintegrationDto = new ReintegrationDto();
					mapper.map(reintegration, reintegrationDto);
					result.add(reintegrationDto);
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
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void delete(ReintegrationDto reintegrationDto) {
		Reintegration reintegration = new Reintegration();
		try {
			mapper.map(reintegrationDto, reintegration);
			reintegration = reintegrationDao.merge(reintegration);
			reintegrationDao.remove(reintegration);
			log.debug("remove Reintegration successful");
		} catch (RuntimeException e) {
			log.error("remove failed", e);
			throw e;
		}

	}

	@Override
	public List<ReintegrationDto> findGeneric(String value) {
		try {

			List<ReintegrationDto> reintegrationDtoList = new ArrayList<ReintegrationDto>();
			List<Reintegration> reintegrationList = reintegrationDao
					.findGeneric(value);
			if (reintegrationList != null) {
				log.debug("Reintegration list success with size =  "
						+ reintegrationList.size());

				for (Reintegration currentReintegration : reintegrationList) {
					ReintegrationDto reintegrationDto = new ReintegrationDto();
					mapper.map(currentReintegration, reintegrationDto);

					reintegrationDtoList.add(reintegrationDto);
				}
				return reintegrationDtoList;
			}

		} catch (RuntimeException e) {
			log.error("findGeneric failed", e);
			throw e;

		}
		return null;
	}

	@Override
	public ReintegrationDto save(ReintegrationDto reintegrationDto) {
		log.info("service saveReintegration");
		Reintegration reintegration = new Reintegration();
		mapper.map(reintegrationDto, reintegration);
		try {
			reintegrationDao.persist(reintegration);

			mapper.map(reintegration, reintegrationDto);
			log.debug("save successful");
			return reintegrationDto;
		} catch (RuntimeException e) {
			log.error("save failed", e);
			throw e;
		}
	}

	@Override
	public void update(ReintegrationDto reintegrationDto) {
		Reintegration reintegration = new Reintegration();
		mapper.map(reintegrationDto, reintegration);
		try {

			reintegrationDao.merge(reintegration);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed", e);
			throw e;
		}

	}

	@Override
	public List<ReintegrationDto> findReintegrationsByIdExclusion(
			int idExclusion) {

		List<Reintegration> reintegrations = reintegrationDao
				.findReintegrationsByIdExclusion(idExclusion);

		if (reintegrations == null || reintegrations.isEmpty())
			return null;

		List<ReintegrationDto> reintegrationDtos = new ArrayList<ReintegrationDto>();

		for (Reintegration reintegration : reintegrations) {
			reintegrationDtos.add(mapper.map(reintegration,
					ReintegrationDto.class));
		}

		return reintegrationDtos;
	}

	/**
	 * Rechercher les reintegrations dans un etablissement donnee dans une liste
	 * d'offre de formation
	 * 
	 * @author Mounir.MESSAOUDI on : 21 oct. 2014 11:38:23
	 * @param reintegrationSearchDto
	 * @param refCodeEtablissement
	 * @param searchOuverturesOffreFormationDtos
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<ReintegrationDto> findAdvanced(
			ReintegrationDto reintegrationSearchDto,
			String refCodeEtablissement,
			List<OuvertureOffreFormationDto> searchOuverturesOffreFormationDtos) {
		try {

			List<OuvertureOffreFormation> searchOuverturesOffreFormationBos = new ArrayList<OuvertureOffreFormation>();
			if (searchOuverturesOffreFormationDtos != null
					&& !searchOuverturesOffreFormationDtos.isEmpty()) {
				for (OuvertureOffreFormationDto oo : searchOuverturesOffreFormationDtos) {
					OuvertureOffreFormation o = new OuvertureOffreFormation();
					mapper.map(oo, o);
					searchOuverturesOffreFormationBos.add(o);
				}
			}

			List<ReintegrationDto> result = new ArrayList<ReintegrationDto>();
			Reintegration searchBo = new Reintegration();
			mapper.map(reintegrationSearchDto, searchBo);
			List<Reintegration> resultListDao = reintegrationDao.findAdvanced(
					searchBo, refCodeEtablissement,
					searchOuverturesOffreFormationBos);

			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (Reintegration reintegration : resultListDao) {
					ReintegrationDto reintegrationDto = new ReintegrationDto();
					mapper.map(reintegration, reintegrationDto);
					result.add(reintegrationDto);
				}
				return result;
			}

		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}

		return null;

	}
}
