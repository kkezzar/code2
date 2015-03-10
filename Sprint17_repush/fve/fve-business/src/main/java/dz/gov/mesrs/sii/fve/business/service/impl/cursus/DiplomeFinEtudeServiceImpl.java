package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DiplomeFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DiplomeFinEtude;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel on : 14 octo. 2014 15:38:11
 */
@Service("diplomeFinEtudeService")
public class DiplomeFinEtudeServiceImpl implements DiplomeFinEtudeService {

	@Autowired
	@Qualifier("diplomeFinEtudeDao")
	private DiplomeFinEtudeDao diplomeFinEtudeDao;

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;
	private static final Log log = LogFactory
			.getLog(DiplomeFinEtudeServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public DiplomeFinEtudeServiceImpl() {

	}

	@Override
	public DiplomeFinEtudeDto /* List<diplomeFinEtudeMatiereDto> */findByIdInscriptionAdministrative(
			int idInscriptionAdministrative) {
		try {
			DiplomeFinEtude diplomeFinEtude = diplomeFinEtudeDao
					.findByIdInscriptionAdministrative(idInscriptionAdministrative);
			if (diplomeFinEtude != null && diplomeFinEtude.getId() != 0) {
				return findById(diplomeFinEtude.getId());
			}
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}
		return null;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.diplomeFinEtudeDao] Getter
	 * 
	 * @author BELDI Jamel on : 15 octo. 2014 14:47:09
	 * @return the diplomeFinEtudeDao
	 */
	public DiplomeFinEtudeDao getDiplomeFinEtudeDao() {
		return diplomeFinEtudeDao;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.diplomeFinEtudeDao] Setter
	 * 
	 * @author BELDI Jamel on : 15 octo. 2014 14:47:09
	 * @param diplomeFinEtudeDao
	 *            the diplomeFinEtudeDao to set
	 */
	public void setDiplomeFinEtudeDao(DiplomeFinEtudeDao diplomeFinEtudeDao) {
		this.diplomeFinEtudeDao = diplomeFinEtudeDao;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.mapper] Getter
	 * 
	 * @author BELDI Jamel on : 15 octo. 2014 14:47:09
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.mapper] Setter
	 * 
	 * @author BELDI Jamel on : 15 octo. 2014 14:47:09
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.situationEntiteDao] Getter
	 * 
	 * @author BELDI Jamel on : 17 octo. 2014 11:30:27
	 * @return the situationEntiteDao
	 */
	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	/**
	 * [diplomeFinEtudeServiceImpl.situationEntiteDao] Setter
	 * 
	 * @author BELDI Jamel on : 17 octo. 2014 11:30:27
	 * @param situationEntiteDao
	 *            the situationEntiteDao to set
	 */
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	@Override
	public DiplomeFinEtudeDto/* List<diplomeFinEtudeMatiereDto> */findByIdInscriptionAdministrativeAndPeriode(
			int idInscriptionAdministrative, int idPeriode) {
		try {
			DiplomeFinEtude diplomeFinEtude = diplomeFinEtudeDao
					.findByIdInscriptionAdministrativeAndPeriode(
							idInscriptionAdministrative, idPeriode);
			if (diplomeFinEtude != null && diplomeFinEtude.getId() != 0) {
				return findById(diplomeFinEtude.getId());
			}
		} catch (RuntimeException e) {
			log.error("findByIdInscriptionAdministrative failed", e);
			throw e;

		}
		return null;
	}

	@Override
	public List<DiplomeFinEtudeDto> findBySession(int idSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiplomeFinEtudeDto> findAdvancedDiplomeNonSigneMinist(
			DiplomeFinEtudeDto dto) {
		try {
			DiplomeFinEtude _searchBo = mapper.map(dto, DiplomeFinEtude.class);
			List<DiplomeFinEtude> _diplomeFinEtudeList = diplomeFinEtudeDao
					.findAdvanced(_searchBo);
			if (_diplomeFinEtudeList != null && !_diplomeFinEtudeList.isEmpty()) {
				List<DiplomeFinEtudeDto> result = new ArrayList<DiplomeFinEtudeDto>();
				for (DiplomeFinEtude diplomeFinEtude : _diplomeFinEtudeList) {
					DiplomeFinEtudeDto diplomeFinEtudeDto = new DiplomeFinEtudeDto();
					mapper.map(diplomeFinEtude, diplomeFinEtudeDto);

					result.add(diplomeFinEtudeDto);
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
	public DiplomeFinEtudeDto findByIdDossier(int idDossier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiplomeFinEtudeDto> findByQuery(String query) {

		// List<DiplomeFinEtude> diplomeFinEtudes =
		// diplomeFinEtudeDao.findByQuery(query);

		List<DiplomeFinEtudeDto> diplomeFinEtudeDtos = new ArrayList<DiplomeFinEtudeDto>();

		// for (DiplomeFinEtude diplomeFinEtude : diplomeFinEtudes) {
		// diplomeFinEtudeDtos.add(mapper.map(diplomeFinEtude,
		// DiplomeFinEtudeDto.class));
		// }

		return diplomeFinEtudeDtos;

	}

	@Override
	public void remove(DiplomeFinEtudeDto diplomeFinEtudeDto) {

		DiplomeFinEtude diplomeFinEtude = mapper.map(diplomeFinEtudeDto,
				DiplomeFinEtude.class);

		diplomeFinEtudeDao.remove(diplomeFinEtude);
	}

	@Override
	public DiplomeFinEtudeDto findById(int id) {

		DiplomeFinEtude diplomeFinEtude = diplomeFinEtudeDao.findById(id);

		if (diplomeFinEtude != null)
			return mapper.map(diplomeFinEtude, DiplomeFinEtudeDto.class);

		return null;
	}

	@Override
	public List<DiplomeFinEtudeDto> findAll() {
		try {
			List<DiplomeFinEtudeDto> result = new ArrayList<DiplomeFinEtudeDto>();

			List<DiplomeFinEtude> resultListDao = diplomeFinEtudeDao.findAll();
			if (resultListDao != null && !resultListDao.isEmpty()) {
				for (DiplomeFinEtude diplomeFinEtude : resultListDao) {
					DiplomeFinEtudeDto diplomeFinEtudeDto = new DiplomeFinEtudeDto();
					mapper.map(diplomeFinEtude, diplomeFinEtudeDto);
					result.add(diplomeFinEtudeDto);
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
	public void delete(DiplomeFinEtudeDto diplomeFinEtudeDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DiplomeFinEtudeDto> findAdvanced(DiplomeFinEtudeDto dto) {
		try {
			DiplomeFinEtude _searchBo = mapper.map(dto, DiplomeFinEtude.class);
			List<DiplomeFinEtude> _diplomeFinEtudeList = diplomeFinEtudeDao
					.findAdvanced(_searchBo);
			if (_diplomeFinEtudeList != null && !_diplomeFinEtudeList.isEmpty()) {
				List<DiplomeFinEtudeDto> result = new ArrayList<DiplomeFinEtudeDto>();
				for (DiplomeFinEtude diplomeFinEtude : _diplomeFinEtudeList) {
					DiplomeFinEtudeDto diplomeFinEtudeDto = new DiplomeFinEtudeDto();
					mapper.map(diplomeFinEtude, diplomeFinEtudeDto);
					result.add(diplomeFinEtudeDto);
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
	public List<DiplomeFinEtudeDto> findDiplometoValidate(String codeEtab) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiplomeFinEtudeDto insertOrUpdate(
			DiplomeFinEtudeDto diplomeFinEtudeDto) {
		try {
			Dossier dossier = new Dossier();
			mapper.map(diplomeFinEtudeDto, dossier);
			DiplomeFinEtude diplomeFinEtude = mapper.map(diplomeFinEtudeDto,
					DiplomeFinEtude.class);

			if (diplomeFinEtude.getId() == 0) {
				diplomeFinEtudeDao.persist(diplomeFinEtude);
			} else {
				diplomeFinEtude = diplomeFinEtudeDao.merge(diplomeFinEtude);
			}

			mapper.map(diplomeFinEtude, diplomeFinEtudeDto);

			log.info("insertOrUpdate success");
			return diplomeFinEtudeDto;
		} catch (Exception e) {
			log.error("insertOrUpdate failed", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService#
	 * findUniqueByBilanSession(int)
	 */
	@Override
	public DiplomeFinEtudeDto findUniqueByBilanSession(long idBilanSession) {
		try {

			DiplomeFinEtude diplomeFinEtude = diplomeFinEtudeDao
					.findUniqueByBilanSession(idBilanSession);
			if (diplomeFinEtude != null) {
				return mapper.map(diplomeFinEtude, DiplomeFinEtudeDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findUniqueByBilanSession failed", e);
			throw e;

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService#generateIdentify(java.lang.String, int)
	 */
	@Override
	public String generateIdentify(String prefix, int orderLength) {
		try {
			if (prefix == null) {
				return null;
			}

			Integer lastOrder = diplomeFinEtudeDao.findLastOrder(prefix, orderLength);
			if (lastOrder == null) {
				return null;
			}
			lastOrder++;
			prefix = prefix
					+ RefUtilConstant.formatOrder(lastOrder.toString(),
							orderLength);
			log.debug("calculateIdentify successful");
		} catch (RuntimeException e) {
			log.error("calculateIdentify failed", e);
			throw e;
		}
		return prefix;
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService#findByOofAndCycle(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DiplomeFinEtudeDto> findByOofAndCycle(Integer oofId,
			Integer cycleId) {
		try {
			 List<DiplomeFinEtude> diplomeFinEtudes = diplomeFinEtudeDao
					.findByOofAndCycle(
							oofId, cycleId);
			return Utility.map(mapper, diplomeFinEtudes, DiplomeFinEtudeDto.class);
		} catch (RuntimeException e) {
			log.error("findByOofAndCycle failed", e);
			throw e;

		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.DiplomeFinEtudeService#findValidateDiplome(java.lang.Integer)
	 */
	@Override
	public List<DiplomeFinEtudeDto> findValidateDiplome(Integer oofId) {
		try {
			
			List<DiplomeFinEtude> _diplomeFinEtudeList = diplomeFinEtudeDao
					.findValidateDiplome(oofId);
			return Utility.map(mapper, _diplomeFinEtudeList, DiplomeFinEtudeDto.class);
			
		} catch (RuntimeException e) {
			log.error("findAdvanced failed", e);
			throw e;

		}
	
	}
}
