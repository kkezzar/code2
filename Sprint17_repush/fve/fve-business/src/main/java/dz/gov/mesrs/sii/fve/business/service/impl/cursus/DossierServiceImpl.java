package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("dossierService")
public class DossierServiceImpl implements DossierService {

	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	private static final Log log = LogFactory.getLog(DossierServiceImpl.class);

	public DossierServiceImpl() {

	}

	@Override
	public DossierDto insertOrUpdate(DossierDto dossierDto) {

		Dossier dossier = mapper.map(dossierDto, Dossier.class);

		if (dossier.getId() == 0)
			dossierDao.persist(dossier);
		else
			dossier = dossierDao.merge(dossier);

		mapper.map(dossier, dossierDto);

		return dossierDto;
	}

	@Override
	public void remove(DossierDto dossierDto) {

		Dossier dossier = mapper.map(dossierDto, Dossier.class);

		dossierDao.remove(dossier);
	}

	@Override
	public List<DossierDto> findByQuery(String query) {

		List<Dossier> dossiers = dossierDao.findByQuery(query);

		List<DossierDto> dossierDtos = new ArrayList<DossierDto>();

		for (Dossier dossier : dossiers) {
			dossierDtos.add(mapper.map(dossier, DossierDto.class));
		}

		return dossierDtos;

	}

	@Override
	public DossierDto findById(int id) {
		try {
			Dossier dossier = dossierDao.findById(id);

			if (dossier != null)
				return mapper.map(dossier, DossierDto.class);
		} catch (RuntimeException e) {
			log.error("insertOrUpdate failed", e);
			throw e;

		}

		return null;
	}

	@Override
	public List<DossierDto> findAll() {

		List<Dossier> dossiers = dossierDao.findAll();

		List<DossierDto> dossierDtos = new ArrayList<DossierDto>();

		for (Dossier dossier : dossiers) {
			dossierDtos.add(mapper.map(dossier, DossierDto.class));
		}

		return dossierDtos;

	}

	@Override
	public DossierDto findByCode(String code) {
		try {
			Dossier dossier = dossierDao.findByCode(code);

			if (dossier != null)
				return mapper.map(dossier, DossierDto.class);
		} catch (RuntimeException e) {
			log.error("findByCode failed", e);
			throw e;

		}

		return null;
	}
}
