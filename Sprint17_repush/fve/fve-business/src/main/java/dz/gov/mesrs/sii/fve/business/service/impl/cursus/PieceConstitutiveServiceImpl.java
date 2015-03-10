package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.PieceConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.PieceConstitutive;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.PieceConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.PieceConstitutiveService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("pieceConstitutiveService")
public class PieceConstitutiveServiceImpl implements PieceConstitutiveService {

	private static final Log log = LogFactory
			.getLog(PieceConstitutiveServiceImpl.class);

	@Autowired
	@Qualifier("pieceConstitutiveDao")
	private PieceConstitutiveDao pieceConstitutiveDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public PieceConstitutiveServiceImpl() {

	}

	@Override
	public PieceConstitutiveDto insertOrUpdate(
			PieceConstitutiveDto pieceConstitutiveDto) {

		PieceConstitutive pieceConstitutive = mapper.map(pieceConstitutiveDto,
				PieceConstitutive.class);

		if (pieceConstitutive.getId() == 0)
			pieceConstitutiveDao.persist(pieceConstitutive);
		else
			pieceConstitutive = pieceConstitutiveDao.merge(pieceConstitutive);

		mapper.map(pieceConstitutive, pieceConstitutiveDto);

		return pieceConstitutiveDto;
	}

	@Override
	public void remove(PieceConstitutiveDto pieceConstitutiveDto) {

		PieceConstitutive pieceConstitutive = mapper.map(pieceConstitutiveDto,
				PieceConstitutive.class);

		pieceConstitutiveDao.remove(pieceConstitutive);
	}

	@Override
	public List<PieceConstitutiveDto> findByQuery(String query) {

		List<PieceConstitutive> pieceConstitutives = pieceConstitutiveDao
				.findByQuery(query);

		List<PieceConstitutiveDto> pieceConstitutiveDtos = new ArrayList<PieceConstitutiveDto>();

		for (PieceConstitutive pieceConstitutive : pieceConstitutives) {
			pieceConstitutiveDtos.add(mapper.map(pieceConstitutive,
					PieceConstitutiveDto.class));
		}

		return pieceConstitutiveDtos;

	}

	@Override
	public PieceConstitutiveDto findById(int id) {

		PieceConstitutive pieceConstitutive = pieceConstitutiveDao.findById(id);

		if (pieceConstitutive != null)
			return mapper.map(pieceConstitutive, PieceConstitutiveDto.class);

		return null;
	}

	@Override
	public List<PieceConstitutiveDto> findAll() {

		List<PieceConstitutive> pieceConstitutives = pieceConstitutiveDao
				.findAll();

		List<PieceConstitutiveDto> pieceConstitutiveDtos = new ArrayList<PieceConstitutiveDto>();

		for (PieceConstitutive pieceConstitutive : pieceConstitutives) {
			pieceConstitutiveDtos.add(mapper.map(pieceConstitutive,
					PieceConstitutiveDto.class));
		}

		return pieceConstitutiveDtos;

	}

	@Override
	public List<PieceConstitutiveDto> findByIdDossier(Integer id) {
		List<PieceConstitutiveDto> pieceConstitutiveDtos = new ArrayList<PieceConstitutiveDto>();
		try {
			List<PieceConstitutive> pieceConstitutiveList = pieceConstitutiveDao
					.findByIdDossier(id);
			if (pieceConstitutiveList != null) {
				log.debug("findByIdDossier list success with size =  "
						+ pieceConstitutiveList.size());

				for (PieceConstitutive currentPieceConstitutive : pieceConstitutiveList) {
					PieceConstitutiveDto pieceConstitutiveDto = new PieceConstitutiveDto();
					mapper.map(currentPieceConstitutive, pieceConstitutiveDto);
					pieceConstitutiveDtos.add(pieceConstitutiveDto);
				}
			}
		} catch (RuntimeException e) {
			log.error("findByIdDossier list failed", e);
			throw e;
		}
		return pieceConstitutiveDtos;
	}

	@Override
	public PieceConstitutiveDto findByIdDossierAndCodePiece(Integer id,
			Integer pieceId) {
		try {
			PieceConstitutive pieceConstitutive = pieceConstitutiveDao
					.findByIdDossierAndCodePiece(id, pieceId);

			if (pieceConstitutive != null)
				return mapper
						.map(pieceConstitutive, PieceConstitutiveDto.class);
		} catch (RuntimeException e) {
			log.error("findByIdDossierAndCodePiece list failed", e);
			throw e;
		}
		return null;
	}

	@Override
	public boolean isAllValid(Integer dossierId) {
		try {
			return pieceConstitutiveDao.isValid(dossierId, null);

		} catch (RuntimeException e) {
			log.error("isValid list failed", e);
			return false;
		}

	}

	@Override
	public boolean isPieceEntreeValid(Integer dossierId) {
		try {
			return pieceConstitutiveDao.isValid(dossierId, new Boolean(true));

		} catch (RuntimeException e) {
			log.error("isValid list failed", e);
			return false;
		}
	}

	@Override
	public boolean isInRequiredValid(Integer pieceId, Integer dossierId) {
		try {
			return pieceConstitutiveDao
					.isInRequiredValid(pieceId, dossierId);

		} catch (RuntimeException e) {
			log.error("isValid list failed", e);
			return false;
		}
	}
}
