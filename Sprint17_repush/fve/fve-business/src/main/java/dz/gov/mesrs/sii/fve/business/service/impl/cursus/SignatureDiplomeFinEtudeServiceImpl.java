/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.SignatureDiplomeFinEtudeServiceImpl.java] 
 * @author MAKERRI Sofiane on : 18 févr. 2015  10:10:02
 */
package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.SignatureDiplomeFinEtudeDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.SignatureDiplomeFinEtude;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.SignatureDiplomeFinEtudeDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService;

/**
 * @author MAKERRI Sofiane on : 18 févr. 2015 10:10:02
 */
@Service("signatureDiplomeFinEtudeService")
public class SignatureDiplomeFinEtudeServiceImpl implements
		SignatureDiplomeFinEtudeService {

	@Autowired
	@Qualifier("signatureDiplomeFinEtudeDao")
	private SignatureDiplomeFinEtudeDao signatureDiplomeFinEtudeDao;
	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory
			.getLog(SignatureDiplomeFinEtudeServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService
	 * #insertOrUpdate(dz.gov.mesrs.sii.fve.business.model.dto.cursus.
	 * SignatureDiplomeFinEtudeDto)
	 */
	@Override
	public SignatureDiplomeFinEtudeDto insertOrUpdate(
			SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto) {
		try {

			SignatureDiplomeFinEtude signatureDiplomeFinEtude = mapper
					.map(signatureDiplomeFinEtudeDto,
							SignatureDiplomeFinEtude.class);

			if (signatureDiplomeFinEtude.getId() == 0) {
				signatureDiplomeFinEtudeDao.persist(signatureDiplomeFinEtude);
			} else {
				signatureDiplomeFinEtude = signatureDiplomeFinEtudeDao
						.merge(signatureDiplomeFinEtude);
			}

			mapper.map(signatureDiplomeFinEtude, signatureDiplomeFinEtudeDto);

			log.info("insertOrUpdate success");
			return signatureDiplomeFinEtudeDto;
		} catch (Exception e) {
			log.error("insertOrUpdate failed", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService
	 * #remove(dz.gov.mesrs.sii.fve.business.model.dto.cursus.
	 * SignatureDiplomeFinEtudeDto)
	 */
	@Override
	public void remove(SignatureDiplomeFinEtudeDto signatureDiplomeFinEtudeDto) {
		SignatureDiplomeFinEtude signatureDiplomeFinEtude = mapper.map(
				signatureDiplomeFinEtudeDto, SignatureDiplomeFinEtude.class);

		signatureDiplomeFinEtudeDao.remove(signatureDiplomeFinEtude);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService
	 * #findById(int)
	 */
	@Override
	public SignatureDiplomeFinEtudeDto findById(int id) {
		SignatureDiplomeFinEtude signatureDiplomeFinEtude = signatureDiplomeFinEtudeDao
				.findById(id);

		if (signatureDiplomeFinEtude != null)
			return mapper.map(signatureDiplomeFinEtude,
					SignatureDiplomeFinEtudeDto.class);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService
	 * #findAll()
	 */
	@Override
	public List<SignatureDiplomeFinEtudeDto> findAll() {
		try {
			List<SignatureDiplomeFinEtude> resultListDao = signatureDiplomeFinEtudeDao
					.findAll();
			return Utility.map(mapper, resultListDao,
					SignatureDiplomeFinEtudeDto.class);
		} catch (RuntimeException e) {
			log.error("findAll failed", e);
			throw e;

		}
	}

	/* (non-Javadoc)
	 * @see dz.gov.mesrs.sii.fve.business.service.cursus.SignatureDiplomeFinEtudeService#findUnique(int, long)
	 */
	@Override
	public SignatureDiplomeFinEtudeDto findUnique(int ncSignatureDiplomeId,
			long diplomeFineEtudeId, boolean attestation) {
		try {

			SignatureDiplomeFinEtude signatureDiplomeFinEtude = signatureDiplomeFinEtudeDao
					.findUnique(ncSignatureDiplomeId, diplomeFineEtudeId, attestation);

			if (signatureDiplomeFinEtude != null)
				return mapper.map(signatureDiplomeFinEtude,
						SignatureDiplomeFinEtudeDto.class);

			return null;
		} catch (Exception e) {
			log.error("findUnique failed", e);
			throw e;
		}
	}

}
