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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.TitreAccesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TitreAcces;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.TitreAccesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.TitreAccesService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("titreAccesService")
public class TitreAccesServiceImpl implements TitreAccesService {

	private static final Log log = LogFactory
			.getLog(TitreAccesServiceImpl.class);

	@Autowired
	@Qualifier("titreAccesDao")
	private TitreAccesDao titreAccesDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public TitreAccesServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public TitreAccesDto insertOrUpdate(TitreAccesDto titreAccesDto) {

		TitreAcces titreAcces = mapper.map(titreAccesDto, TitreAcces.class);

		try {
			if (titreAcces.getId() == 0) {
				titreAccesDao.persist(titreAcces);
			} else {
				titreAcces = titreAccesDao.merge(titreAcces);
			}

			mapper.map(titreAcces, titreAccesDto);
			log.debug("insertorupdate TitreAcces succes..");
			return titreAccesDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate TitreAcces failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(TitreAccesDto titreAccesDto) {
		try {
			TitreAcces titreAcces = mapper.map(titreAccesDto, TitreAcces.class);
			titreAcces = titreAccesDao.merge(titreAcces);
			titreAccesDao.remove(titreAcces);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	

	@Override
	public TitreAccesDto findById(int id) {

		TitreAcces titreAcces = titreAccesDao.findById(id);

		if (titreAcces != null)
			return mapper.map(titreAcces, TitreAccesDto.class);

		return null;
	}

	@Override
	public List<TitreAccesDto> findAll() {

		List<TitreAcces> titreAccess = titreAccesDao.findAll();

		List<TitreAccesDto> titreAccesDtos = new ArrayList<TitreAccesDto>();

		for (TitreAcces titreAcces : titreAccess) {
			titreAccesDtos.add(mapper.map(titreAcces, TitreAccesDto.class));
		}

		return titreAccesDtos;

	}

	@Override
	public TitreAccesDto findByIdDossier(int idDossier) {
		TitreAcces titreAcces = titreAccesDao.findByIdDossier(idDossier);

		if (titreAcces != null)
			return mapper.map(titreAcces, TitreAccesDto.class);

		return null;
	}

}
