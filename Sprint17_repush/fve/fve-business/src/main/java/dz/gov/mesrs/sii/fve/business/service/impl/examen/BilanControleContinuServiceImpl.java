/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.BilanControleContinuServiceImpl.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:24:48
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

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.BilanControleContinuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.BilanControleContinu;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto;
import dz.gov.mesrs.sii.fve.business.service.examen.BilanControleContinuService;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:24:48
 */
@Service("bilanControleContinuService")
public class BilanControleContinuServiceImpl implements
		BilanControleContinuService {

	@Autowired
	@Qualifier("bilanControleContinuDao")
	private BilanControleContinuDao bilanControleContinuDao;

	private static final Log log = LogFactory
			.getLog(EvaluationControleContinuServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [BilanControleContinuServiceImpl.BilanControleContinuServiceImpl()]
	 * Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:24:48
	 */
	public BilanControleContinuServiceImpl() {
		// final List<String> mappings = new ArrayList<String>(1);
		// mappings.add(UtilConstants.DOZER_BILAN_CONTROLE_CONTINU_MAPPING_NAME);
		// mapper = new DozerBeanMapper();
		// mapper.setMappingFiles(mappings);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public BilanControleContinuDto insertOrUpdate(
			BilanControleContinuDto bilanControleContinuDto) {

		BilanControleContinu bilanControleContinu = mapper.map(
				bilanControleContinuDto, BilanControleContinu.class);
		try {
			if (bilanControleContinu.getId() == 0) {
				bilanControleContinuDao.persist(bilanControleContinu);
			} else {
				bilanControleContinu = bilanControleContinuDao
						.merge(bilanControleContinu);
			}
			mapper.map(bilanControleContinu, bilanControleContinuDto);

			log.error("insertorupdate BilanControleContinu succes..");

			return bilanControleContinuDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate BilanControleContinu failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(BilanControleContinuDto bilanControleContinuDto) {
		try {

			BilanControleContinu bilanControleContinu = mapper.map(
					bilanControleContinuDto, BilanControleContinu.class);

			bilanControleContinu = bilanControleContinuDao
					.merge(bilanControleContinu);

			bilanControleContinuDao.remove(bilanControleContinu);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public BilanControleContinuDto findById(int id) {

		BilanControleContinu bilanControleContinu = bilanControleContinuDao
				.findById(id);

		if (bilanControleContinu != null)
			return mapper.map(bilanControleContinu,
					BilanControleContinuDto.class);

		return null;
	}

	@Override
	public List<BilanControleContinuDto> findAll() {

		List<BilanControleContinu> evaluationControleContinus = bilanControleContinuDao
				.findAll();

		List<BilanControleContinuDto> evaluationControleContinuDtos = new ArrayList<BilanControleContinuDto>();

		for (BilanControleContinu bilanControleContinu : evaluationControleContinus) {
			evaluationControleContinuDtos.add(mapper.map(bilanControleContinu,
					BilanControleContinuDto.class));
		}

		return evaluationControleContinuDtos;

	}

	@Override
	public BilanControleContinuDto findUnique(Integer anneeId, Integer oofId,
			Integer periodeId, Integer affGpId, Integer rattachementMcId) {
		try {

			BilanControleContinu bilanControleContinu = bilanControleContinuDao
					.findUnique(anneeId, oofId, periodeId, affGpId,
							rattachementMcId);
			if (bilanControleContinu != null) {
				return mapper.map(bilanControleContinu,
						BilanControleContinuDto.class);
			}

			log.error("findUnique succes..");

			return null;

		} catch (RuntimeException e) {
			log.error("findUnique failed--", e);
			throw e;
		}
	}

	@Override
	public BilanControleContinuDto findUniqueByDiaId(Integer anneeId,
			Integer oofId, Integer periodeId, Integer diaId,
			Integer rattachementMcId) {
		try {

			BilanControleContinu bilanControleContinu = bilanControleContinuDao
					.findUniqueByDiaId(anneeId, oofId, periodeId, diaId,
							rattachementMcId);
			if (bilanControleContinu != null) {
				return mapper.map(bilanControleContinu,
						BilanControleContinuDto.class);
			}

			log.info("findUniqueByDiaId succes..");

			return null;

		} catch (RuntimeException e) {
			log.error("findUniqueByDiaId failed--", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.BilanControleContinuService
	 * #findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.dto.examen.BilanControleContinuDto)
	 */
	@Override
	public List<BilanControleContinuDto> findAdvanced(
			BilanControleContinuDto searchDto) {
		try {
			BilanControleContinu bilanControleContinu = mapper.map(searchDto,
					BilanControleContinu.class);
			List<BilanControleContinu> bilanControleContinus = bilanControleContinuDao
					.findAdvanced(bilanControleContinu);
			log.info("findAdvanced succes..");
			return Utility.map(mapper, bilanControleContinus,
					BilanControleContinuDto.class);
		} catch (RuntimeException e) {
			log.error("findAdvanced failed--", e);
			throw e;
		}
	}

}
