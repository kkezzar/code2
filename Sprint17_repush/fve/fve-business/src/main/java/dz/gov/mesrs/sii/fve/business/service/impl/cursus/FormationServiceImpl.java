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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.FormationDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Formation;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.FormationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.FormationService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("formationService")
public class FormationServiceImpl implements FormationService {

	@Autowired
	@Qualifier("formationDao")
	private FormationDao formationDao;
	

	private static final Log log = LogFactory
			.getLog(FormationServiceImpl.class);


	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public FormationServiceImpl() {

	}

	@Override
	public FormationDto insertOrUpdate(FormationDto formationDto) {

		try{
			
			Formation formation = mapper.map(formationDto, Formation.class);

		  if (formation.getId() == 0){
			formationDao.persist(formation);
		  }
		  else{
			formation = formationDao.merge(formation);
		  }

		  mapper.map(formation, formationDto);
		  log.error("insertorupdate FormationDto succes..");

		  return formationDto;
		
	   } catch (RuntimeException e) {
		log.error("insertorupdate FormationDto failed--", e);
	 	throw e;
	  }
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(FormationDto formationDto) {

		Formation formation = mapper.map(formationDto, Formation.class);
		formation = formationDao.merge(formation);
		formationDao.remove(formation);
	}

	
	@Override
	public FormationDto findById(int id) {

		Formation formation = formationDao.findById(id);

		if (formation != null)
			return mapper.map(formation, FormationDto.class);

		return null;
	}

	
	@Override
	public List<FormationDto> findAll() {

		List<Formation> formations = formationDao.findAll();

		List<FormationDto> formationDtos = new ArrayList<FormationDto>();

		for (Formation formation : formations) {
			formationDtos.add(mapper.map(formation, FormationDto.class));
		}

		return formationDtos;

	}

	@Override
	public List<FormationDto> findFormationsByIdDossier(int idDossier) {
		List<Formation> formations = formationDao
				.findFormationsByIdDossier(idDossier);

		if (formations == null || formations.isEmpty())
			return null;

		List<FormationDto> formationDtos = new ArrayList<FormationDto>();

		for (Formation formation : formations) {
			formationDtos.add(mapper.map(formation, FormationDto.class));
		}

		return formationDtos;
	}
}
