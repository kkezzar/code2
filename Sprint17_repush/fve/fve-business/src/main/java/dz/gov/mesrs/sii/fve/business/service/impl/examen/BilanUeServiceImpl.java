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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanUeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanUe;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanUeDto;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
 */
@Service("bilanUeService")
public class BilanUeServiceImpl implements BilanUeService {

	/**
	 * [BilanSessionServiceImpl.BilanSessionServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
	 */
	@Autowired
	@Qualifier("bilanUeDao")
	private BilanUeDao bilanUeDao;

	private static final Log log = LogFactory.getLog(BilanUeServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public BilanUeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public BilanUeDto insertOrUpdate(BilanUeDto bilanUeDto) {

		BilanUe bilanUe = mapper.map(bilanUeDto, BilanUe.class);
		try {
			if (bilanUe.getId() == 0) {
				bilanUeDao.persist(bilanUe);
			} else {
				bilanUe = bilanUeDao.merge(bilanUe);
			}
			mapper.map(bilanUe, bilanUeDto);

			log.info("insertorupdate BilanUe succes..");

			return bilanUeDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate BilanUe failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(BilanUeDto bilanUeDto) {
		try {

			BilanUe bilanUe = mapper.map(bilanUeDto, BilanUe.class);

			bilanUe = bilanUeDao.merge(bilanUe);

			bilanUeDao.remove(bilanUe);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanUeDto findById(int id) {

		BilanUe bilanUe = bilanUeDao.findById(id);

		if (bilanUe != null)
			return mapper.map(bilanUe, BilanUeDto.class);

		return null;
	}

	@Override
	public List<BilanUeDto> findAll() {

		List<BilanUe> bilanUes = bilanUeDao.findAll();

		List<BilanUeDto> bilanUeDtos = new ArrayList<BilanUeDto>();

		for (BilanUe bilanUe : bilanUes) {
			bilanUeDtos.add(mapper.map(bilanUe, BilanUeDto.class));
		}

		return bilanUeDtos;

	}

	@Override
	public BilanUeDto findUniqueSession(long bilanSessionId, int repartitionUeId) {
		try {
			return findUnique(bilanSessionId, repartitionUeId,
					UtilConstants.BILAN_TYPE_SESSION);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanUeDto findUniquePeriode(int bilanSessionId, int repartitionUeId) {
		try {
			return findUnique(bilanSessionId, repartitionUeId,
					UtilConstants.BILAN_TYPE_PERIODE);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * [BilanUeServiceImpl.findUnique] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 11:18:29
	 * @param bilanSessionId
	 * @param repartitionUeId
	 * @param type
	 * @return
	 */
	private BilanUeDto findUnique(long bilanSessionId,
			Integer repartitionUeId, int type) {
		try {
			BilanUe bilanUe = bilanUeDao.findUnique(bilanSessionId,
					repartitionUeId, type);
			if (bilanUe != null) {
				return mapper.map(bilanUe, BilanUeDto.class);
			}

		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<BilanUeDto> findByBilanSessionId(int bilanSessionId) {
		try {
			List<BilanUe> bilanUes = bilanUeDao
					.findByBilanSessionId(bilanSessionId);

			List<BilanUeDto> bilanUeDtos = new ArrayList<BilanUeDto>();
			if (bilanUes != null) {
				for (BilanUe bilanUe : bilanUes) {
					bilanUeDtos.add(mapper.map(bilanUe, BilanUeDto.class));
				}
			}

			return bilanUeDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<BilanUeDto> findByOofAndNiveau(Integer oofId, Integer niveauId) {
		try {
			List<BilanUe> bilanUes = bilanUeDao
					.findByOofAndNiveau(oofId, niveauId);

			List<BilanUeDto> bilanUeDtos = new ArrayList<BilanUeDto>();
			if (bilanUes != null) {
				for (BilanUe bilanUe : bilanUes) {
					bilanUeDtos.add(mapper.map(bilanUe, BilanUeDto.class));
				}
			}

			return bilanUeDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

}
