package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.CongeAcademiqueDao;
import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.DossierDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.CongeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.CongeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.CongeAcademiqueService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("congeAcademiqueService")
public class CongeAcademiqueServiceImpl implements CongeAcademiqueService {

	private static final Log log = LogFactory
			.getLog(CongeAcademiqueServiceImpl.class);

	@Autowired
	@Qualifier("congeAcademiqueDao")
	private CongeAcademiqueDao congeAcademiqueDao;

	@Autowired
	@Qualifier("dossierDao")
	private DossierDao dossierDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public CongeAcademiqueServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public CongeAcademiqueDto insertOrUpdate(
			CongeAcademiqueDto congeAcademiqueDto) {

		Dossier dossier = new Dossier();
		// mapper.map(congeAcademiqueDto, dossier);

		CongeAcademique congeAcademique = mapper.map(congeAcademiqueDto,
				CongeAcademique.class);
		try {
			if (congeAcademique.getId() == 0) {
				dossier.setDateCreation(new Date());
				dossier.setTypeDossier(congeAcademiqueDto.getTypeDossier());
				dossier = dossierDao.persist(dossier);
				// mapper.map(dossier, congeAcademique);
				congeAcademique.setDossier(dossier);
				congeAcademique.setId(dossier.getId());
				congeAcademiqueDao.persist(congeAcademique);

			} else {
				congeAcademique = congeAcademiqueDao.merge(congeAcademique);
			}

			congeAcademiqueDto = mapper.map(congeAcademique,
					CongeAcademiqueDto.class);

			log.error("insertorupdate CongeAcademique succes..");

			return congeAcademiqueDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate CongeAcademique failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(CongeAcademiqueDto congeAcademiqueDto) {
		try {

			CongeAcademique congeAcademique = mapper.map(congeAcademiqueDto,
					CongeAcademique.class);

			congeAcademique = congeAcademiqueDao.merge(congeAcademique);

			congeAcademiqueDao.remove(congeAcademique);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public CongeAcademiqueDto findById(int id) {

		CongeAcademique congeAcademique = congeAcademiqueDao.findById(id);

		if (congeAcademique != null)
			return mapper.map(congeAcademique, CongeAcademiqueDto.class);

		return null;
	}

	@Override
	public List<CongeAcademiqueDto> findAll() {

		List<CongeAcademique> congeAcademiques = congeAcademiqueDao.findAll();

		List<CongeAcademiqueDto> congeAcademiqueDtos = new ArrayList<CongeAcademiqueDto>();

		for (CongeAcademique congeAcademique : congeAcademiques) {
			congeAcademiqueDtos.add(mapper.map(congeAcademique,
					CongeAcademiqueDto.class));
		}

		return congeAcademiqueDtos;

	}

	@Override
	public CongeAcademiqueDto findByIdDossier(int idDossier) {

		CongeAcademique congeAcademique = congeAcademiqueDao
				.findByIdDossier(idDossier);

		if (congeAcademique != null)
			return mapper.map(congeAcademique, CongeAcademiqueDto.class);

		return null;
	}

	@Override
	public CongeAcademiqueDto findByIdDossierInscriptionAdministrative(
			Integer diaId) {
		CongeAcademique congeAcademique = congeAcademiqueDao
				.findByIdDossierInscriptionAdministrative(diaId);

		if (congeAcademique != null)
			return mapper.map(congeAcademique, CongeAcademiqueDto.class);

		return null;
	}

}
