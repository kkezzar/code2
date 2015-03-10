package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.MatiereConstitutiveDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.MatiereConstitutive;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;

/**
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Service("matiereConstitutiveService")
public class MatiereConstitutiveServiceImpl implements
		MatiereConstitutiveService {

	@Autowired
	@Qualifier("matiereConstitutiveDao")
	private MatiereConstitutiveDao matiereConstitutiveDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public MatiereConstitutiveServiceImpl() {
	}

	@Override
	public MatiereConstitutiveDto insertOrUpdate(
			MatiereConstitutiveDto matiereConstitutiveDto) {

		MatiereConstitutive matiereConstitutive = mapper.map(
				matiereConstitutiveDto, MatiereConstitutive.class);

		if (matiereConstitutive.getId() == 0) {

			matiereConstitutive.setDateCreation(new Date());
			matiereConstitutive.setDateModification(new Date());
			matiereConstitutiveDao.persist(matiereConstitutive);
		} else {
			matiereConstitutive.setDateModification(new Date());
			matiereConstitutive = matiereConstitutiveDao
					.merge(matiereConstitutive);
		}
		mapper.map(matiereConstitutive, matiereConstitutiveDto);

		return matiereConstitutiveDto;

	}

	@Override
	public void remove(MatiereConstitutiveDto matiereConstitutiveDto) {

		MatiereConstitutive matiereConstitutive = mapper.map(
				matiereConstitutiveDto, MatiereConstitutive.class);

		matiereConstitutive = matiereConstitutiveDao.merge(matiereConstitutive);
		matiereConstitutiveDao.remove(matiereConstitutive);
	}

	@Override
	public MatiereConstitutiveDto invalidate(int matiereConstitutiveId) {
		MatiereConstitutive _matiereConstitutive = matiereConstitutiveDao
				.validate(matiereConstitutiveId);
		return mapper.map(_matiereConstitutive, MatiereConstitutiveDto.class);
	}

	@Override
	public MatiereConstitutiveDto validate(int matiereConstitutiveId) {
		MatiereConstitutive _matiereConstitutive = matiereConstitutiveDao
				.invalidate(matiereConstitutiveId);
		return mapper.map(_matiereConstitutive, MatiereConstitutiveDto.class);
	}

	@Override
	public MatiereConstitutiveDto findById(int id) {

		MatiereConstitutive matiereConstitutive = matiereConstitutiveDao
				.findById(id);

		if (matiereConstitutive != null)
			return mapper
					.map(matiereConstitutive, MatiereConstitutiveDto.class);

		return null;
	}

	// @Override
	// public List<MatiereConstitutiveDto> findOfUe(int ueId) {
	//
	// List<MatiereConstitutive> matiereConstitutives = matiereConstitutiveDao
	// .findOfUe(ueId);
	//
	// List<MatiereConstitutiveDto> matiereConstitutiveDtos = new
	// ArrayList<MatiereConstitutiveDto>();
	//
	// for (MatiereConstitutive matiereConstitutive : matiereConstitutives) {
	// matiereConstitutiveDtos.add(mapper.map(matiereConstitutive,
	// MatiereConstitutiveDto.class));
	// }
	//
	// return matiereConstitutiveDtos;
	// }
	//
	// @Override
	// public void addToUe(int mcId, int ueId) {
	// matiereConstitutiveDao.addToUe(mcId, ueId);
	// }
	//
	// @Override
	// public void removeFromUe(int mcId, int ueId) {
	// matiereConstitutiveDao.removeFromUe(mcId, ueId);
	//
	// }

	@Override
	public List<MatiereConstitutiveDto> findWithFullText(String fullTextKeyword) {

		List<MatiereConstitutive> matiereConstitutives = matiereConstitutiveDao
				.findWithFullText(fullTextKeyword);

		List<MatiereConstitutiveDto> matiereConstitutiveDtos = new ArrayList<MatiereConstitutiveDto>();

		for (MatiereConstitutive matiereConstitutive : matiereConstitutives) {
			matiereConstitutiveDtos.add(mapper.map(matiereConstitutive,
					MatiereConstitutiveDto.class));
		}

		return matiereConstitutiveDtos;
	}

	@Override
	public List<MatiereConstitutiveDto> findAll() {

		List<MatiereConstitutive> matiereConstitutives = matiereConstitutiveDao
				.findAll();

		List<MatiereConstitutiveDto> matiereConstitutiveDtos = new ArrayList<MatiereConstitutiveDto>();

		for (MatiereConstitutive matiereConstitutive : matiereConstitutives) {
			matiereConstitutiveDtos.add(mapper.map(matiereConstitutive,
					MatiereConstitutiveDto.class));
		}

		return matiereConstitutiveDtos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dz.gov.mesrs.sii.fve.business.service.offreformation.
	 * MatiereConstitutiveService#findByCode(java.lang.String)
	 */
	@Override
	public MatiereConstitutiveDto findByCode(String code) {
		try {
			MatiereConstitutive matiereConstitutive = matiereConstitutiveDao
					.findByCode(code);

			if (matiereConstitutive != null)
				return mapper.map(matiereConstitutive,
						MatiereConstitutiveDto.class);
		} catch (RuntimeException e) {
			throw e;
		}

		return null;
	}

}
