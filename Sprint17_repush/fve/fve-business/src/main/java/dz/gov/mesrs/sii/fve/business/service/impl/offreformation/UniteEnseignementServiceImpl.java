package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.UniteEnseignementDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.UniteEnseignement;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.UniteEnseignementDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService;

@Service("uniteEnseignementService")
public class UniteEnseignementServiceImpl implements UniteEnseignementService {

	@Autowired
	@Qualifier("uniteEnseignementDao")
	private UniteEnseignementDao uniteEnseignementDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory
			.getLog(UniteEnseignementServiceImpl.class);

	public UniteEnseignementServiceImpl() {

	}

	@Override
	public UniteEnseignementDto insertOrUpdate(
			UniteEnseignementDto uniteEnseignementDto) {

		UniteEnseignement uniteEnseignement = mapper.map(uniteEnseignementDto,
				UniteEnseignement.class);

		if (uniteEnseignement.getId() == 0) {
			uniteEnseignement.setDateCreation(new Date());
			uniteEnseignement.setDateModification(new Date());
			uniteEnseignementDao.persist(uniteEnseignement);
		} else {
			uniteEnseignement.setDateModification(new Date());
			uniteEnseignement = uniteEnseignementDao.merge(uniteEnseignement);
		}

		mapper.map(uniteEnseignement, uniteEnseignementDto);

		return uniteEnseignementDto;
	}

	@Override
	public void remove(UniteEnseignementDto uniteEnseignementDto) {

		UniteEnseignement uniteEnseignement = mapper.map(uniteEnseignementDto,
				UniteEnseignement.class);

		uniteEnseignement = uniteEnseignementDao.merge(uniteEnseignement);
		uniteEnseignementDao.remove(uniteEnseignement);
	}

	@Override
	public UniteEnseignementDto findById(int id) {

		UniteEnseignement uniteEnseignement = uniteEnseignementDao.findById(id);

		if (uniteEnseignement != null)
			return mapper.map(uniteEnseignement, UniteEnseignementDto.class);

		return null;
	}

	@Override
	public List<UniteEnseignementDto> find(String code, String libelleFr,
			String libelleAr, String abreviationFr, String abreviationAr) {

		List<UniteEnseignement> uniteEnseignements = uniteEnseignementDao.find(
				code, libelleFr, libelleAr, abreviationFr, abreviationAr);

		List<UniteEnseignementDto> uniteEnseignementDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement uniteEnseignement : uniteEnseignements) {
			uniteEnseignementDtos.add(mapper.map(uniteEnseignement,
					UniteEnseignementDto.class));
		}

		return uniteEnseignementDtos;
	}

	@Override
	public List<UniteEnseignementDto> findWithFullText(String fullTextKeyword) {

		List<UniteEnseignement> uniteEnseignements = uniteEnseignementDao
				.findWithFullText(fullTextKeyword);

		List<UniteEnseignementDto> uniteEnseignementDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement uniteEnseignement : uniteEnseignements) {
			uniteEnseignementDtos.add(mapper.map(uniteEnseignement,
					UniteEnseignementDto.class));
		}

		return uniteEnseignementDtos;
	}

	@Override
	public UniteEnseignementDto validate(int uniteEnseignementId) {
		UniteEnseignement _uniteEnseignement = uniteEnseignementDao
				.validate(uniteEnseignementId);
		return mapper.map(_uniteEnseignement, UniteEnseignementDto.class);
	}

	@Override
	public UniteEnseignementDto invalidate(int uniteEnseignementId) {
		UniteEnseignement _uniteEnseignement = uniteEnseignementDao
				.invalidate(uniteEnseignementId);
		return mapper.map(_uniteEnseignement, UniteEnseignementDto.class);
	}

	@Override
	public List<UniteEnseignementDto> findAll() {

		List<UniteEnseignement> uniteEnseignements = uniteEnseignementDao
				.findAll();

		List<UniteEnseignementDto> uniteEnseignementDtos = new ArrayList<UniteEnseignementDto>();

		for (UniteEnseignement uniteEnseignement : uniteEnseignements) {
			uniteEnseignementDtos.add(mapper.map(uniteEnseignement,
					UniteEnseignementDto.class));
		}

		return uniteEnseignementDtos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.offreformation.UniteEnseignementService
	 * #findByCode(java.lang.String)
	 */
	@Override
	public UniteEnseignementDto findByCode(String code) {
		try {
			UniteEnseignement _uniteEnseignement = uniteEnseignementDao
					.findByCode(code);
			if (_uniteEnseignement != null) {
				return mapper.map(_uniteEnseignement,
						UniteEnseignementDto.class);
			}
		} catch (RuntimeException e) {
			log.error("findByCode failed", e);
			throw e;
		}
		return null;
	}
}
