/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.BilanSessionServiceImpl.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:52:00
 */
package dz.gov.mesrs.sii.fve.business.service.impl.examen;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanMcDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanMc;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanMcDto;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanMcService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
 */
@Service("bilanMcService")
public class BilanMcServiceImpl implements BilanMcService {

	/**
	 * [BilanSessionServiceImpl.BilanSessionServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
	 */
	@Autowired
	@Qualifier("bilanMcDao")
	private BilanMcDao bilanMcDao;

	private static final Log log = LogFactory.getLog(BilanMcServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public BilanMcServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public BilanMcDto insertOrUpdate(BilanMcDto bilanMcDto) {

		BilanMc bilanMc = mapper.map(bilanMcDto, BilanMc.class);
		try {
			if (bilanMc.getId() == 0) {
				bilanMcDao.persist(bilanMc);
			} else {
				bilanMc = bilanMcDao.merge(bilanMc);
			}
			mapper.map(bilanMc, bilanMcDto);

			log.info("insertorupdate BilanMc succes..");

			return bilanMcDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate BilanMc failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(BilanMcDto bilanMcDto) {
		try {

			BilanMc bilanMc = mapper.map(bilanMcDto, BilanMc.class);

			bilanMc = bilanMcDao.merge(bilanMc);

			bilanMcDao.remove(bilanMc);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanMcDto findById(int id) {

		BilanMc bilanMc = bilanMcDao.findById(id);

		if (bilanMc != null)
			return mapper.map(bilanMc, BilanMcDto.class);

		return null;
	}

	@Override
	public List<BilanMcDto> findAll() {

		List<BilanMc> bilanMcs = bilanMcDao.findAll();

		List<BilanMcDto> bilanMcDtos = new ArrayList<BilanMcDto>();

		for (BilanMc bilanMc : bilanMcs) {
			bilanMcDtos.add(mapper.map(bilanMc, BilanMcDto.class));
		}

		return bilanMcDtos;

	}

	@Override
	public BilanMcDto findUniqueSession(int bilanUeId, int rattachementMcId) {
		try {
			return findUnique(bilanUeId, rattachementMcId,
					UtilConstants.BILAN_TYPE_SESSION);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanMcDto findUniquePeriode(int bilanUeId, int rattachementMcId) {
		try {
			return findUnique(bilanUeId, rattachementMcId,
					UtilConstants.BILAN_TYPE_PERIODE);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * [BilanMcServiceImpl.findUnique] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 11:19:27
	 * @param bilanUeId
	 * @param rattachementMcId
	 * @param type
	 * @return
	 */
	private BilanMcDto findUnique(Integer bilanUeId, Integer rattachementMcId,
			int type) {
		try {
			BilanMc bilanMc = bilanMcDao.findUnique(bilanUeId,
					rattachementMcId, type);
			if (bilanMc != null) {
				return mapper.map(bilanMc, BilanMcDto.class);
			}

		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<BilanMcDto> findByBilanUeId(int bilanUeId) {
		try {
			List<BilanMc> bilanMcs = bilanMcDao.findByBilanUeId(bilanUeId);

			List<BilanMcDto> bilanMcDtos = new ArrayList<BilanMcDto>();
			if (bilanMcDtos != null) {
				for (BilanMc bilanMc : bilanMcs) {
					bilanMcDtos.add(mapper.map(bilanMc, BilanMcDto.class));
				}
			}

			return bilanMcDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<BilanMcDto> findByOofAndNiveau(Integer oofId, Integer niveauId) {
		try {
			List<BilanMc> bilanMcs = bilanMcDao.findByOofAndNiveau(oofId, niveauId);

			List<BilanMcDto> bilanMcDtos = new ArrayList<BilanMcDto>();
			if (bilanMcDtos != null) {
				for (BilanMc bilanMc : bilanMcs) {
					bilanMcDtos.add(mapper.map(bilanMc, BilanMcDto.class));
				}
			}

			return bilanMcDtos;

		} catch (RuntimeException e) {
			throw e;
		}
	}
}
