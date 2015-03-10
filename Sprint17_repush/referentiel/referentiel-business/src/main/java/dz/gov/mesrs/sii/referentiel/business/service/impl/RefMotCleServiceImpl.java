package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefMotCleDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefMotCle;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefMotCleService;

/**
 * 
 * @author yselmane  on : 9 avr. 2014  10:33:05
 */

@Service("refMotCleServiceImpl")
public class RefMotCleServiceImpl implements RefMotCleService {

	@Autowired
	@Qualifier("refMotCleDao")
	private RefMotCleDao refMotCleDao;

	@Autowired
	@Qualifier("mapper")
	private Mapper mapper;

	public RefMotCleServiceImpl() {

	}

	@Override
	public void save(RefMotCleDto refMotCleDto) {

		RefMotCle refMotCle = mapper.map(refMotCleDto, RefMotCle.class);

		refMotCleDao.persist(refMotCle);
	}

	@Override
	public RefMotCleDto insertOrUpdate(RefMotCleDto refMotCleDto) {

		RefMotCle refMotCle = mapper.map(refMotCleDto, RefMotCle.class);

		if (refMotCle.getId() == 0)
			refMotCleDao.persist(refMotCle);
		else
			refMotCle = refMotCleDao.merge(refMotCle);

		mapper.map(refMotCle, refMotCleDto);

		return refMotCleDto;
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(RefMotCleDto refMotCleDto) {

		RefMotCle refMotCle = new RefMotCle();
		try {
			mapper.map(refMotCleDto, refMotCle);
			refMotCle = refMotCleDao.merge(refMotCle);
			refMotCleDao.remove(refMotCle);

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void removeMotsClesDocument(String idDocument) {

		try {
			refMotCleDao.removeMotsClesDocument(idDocument);

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<RefMotCleDto> findByQuery(String query) {

		List<RefMotCle> refMotCles = refMotCleDao.findByQuery(query);

		List<RefMotCleDto> refMotCleDtos = new ArrayList<RefMotCleDto>();

		for (RefMotCle refMotCle : refMotCles) {
			refMotCleDtos.add(mapper.map(refMotCle, RefMotCleDto.class));
		}

		return refMotCleDtos;

	}

	@Override
	public RefMotCleDto findById(int id) {

		RefMotCle refMotCle = refMotCleDao.findById(id);

		if (refMotCle != null)
			return mapper.map(refMotCle, RefMotCleDto.class);

		return null;
	}

	@Override
	public List<RefMotCleDto> findAll() {

		List<RefMotCle> refMotCles = refMotCleDao.findAll();

		List<RefMotCleDto> refMotCleDtos = new ArrayList<RefMotCleDto>();

		for (RefMotCle refMotCle : refMotCles) {
			refMotCleDtos.add(mapper.map(refMotCle, RefMotCleDto.class));
		}

		return refMotCleDtos;

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<RefMotCleDto> findMotsClesDocument(String idDocument) {

		List<RefMotCleDto> refMotCleListDto = new ArrayList<RefMotCleDto>();

		try {
			List<RefMotCle> refMotCleList = null;
			if (idDocument != null) {
				refMotCleList = (List<RefMotCle>) refMotCleDao
						.findMotsClesDocument(idDocument);
			} else {
				return refMotCleListDto;
			}

			if (refMotCleList != null && !refMotCleList.isEmpty()) {

				for (RefMotCle currentRefMotCle : refMotCleList) {
					RefMotCleDto refMotCleDto = new RefMotCleDto();
					mapper.map(currentRefMotCle, refMotCleDto);
					refMotCleListDto.add(refMotCleDto);
				}

			}
		} catch (RuntimeException e) {

			throw e;
		}
		return refMotCleListDto;
	}

	@Override
	public RefMotCleDto findMotCleDocument(String idDocument, Integer idMotCle) {

		RefMotCle refMotCle = refMotCleDao.findMotCleDocument(idDocument,
				idMotCle);

		if (refMotCle != null)
			return mapper.map(refMotCle, RefMotCleDto.class);

		return null;
	}

	@Override
	public List<NomenclatureDto> findNomenclatureMotsClesDocument(
			String idDocument) {

		List<NomenclatureDto> refNomenclatureListDto = new ArrayList<NomenclatureDto>();

		try {
			List<Nomenclature> nomenclatureList = null;
			if (idDocument != null) {
				nomenclatureList = refMotCleDao
						.findNomenclatureMotsClesDocument(idDocument);
			} else {
				return refNomenclatureListDto;
			}

			if (nomenclatureList != null && !nomenclatureList.isEmpty()) {

				for (Nomenclature currentNomenclature : nomenclatureList) {
					NomenclatureDto nomenclatureDto = new NomenclatureDto();
					mapper.map(currentNomenclature, nomenclatureDto);
					refNomenclatureListDto.add(nomenclatureDto);
				}

			}
		} catch (RuntimeException e) {

			throw e;
		}
		return refNomenclatureListDto;
	}

}
