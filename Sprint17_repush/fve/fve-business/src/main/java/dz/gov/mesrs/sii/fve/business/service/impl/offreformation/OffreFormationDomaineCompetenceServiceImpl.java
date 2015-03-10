package dz.gov.mesrs.sii.fve.business.service.impl.offreformation;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.offreformation.OffreFormationDomaineCompetenceDao;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationDomaineCompetence;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDomaineCompetenceDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationDomaineCompetenceService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("offreFormationDomaineCompetenceService")
public class OffreFormationDomaineCompetenceServiceImpl implements OffreFormationDomaineCompetenceService  {

	@Autowired
	@Qualifier ("offreFormationDomaineCompetenceDao")
	private OffreFormationDomaineCompetenceDao offreFormationDomaineCompetenceDao;


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	public OffreFormationDomaineCompetenceServiceImpl(){

	}

	@Override
	public OffreFormationDomaineCompetenceDto  insertOrUpdate(
			OffreFormationDomaineCompetenceDto offreFormationDomaineCompetenceDto) {

		OffreFormationDomaineCompetence offreFormationDomaineCompetence = mapper.map(offreFormationDomaineCompetenceDto,
				OffreFormationDomaineCompetence.class);
		
		if (offreFormationDomaineCompetence.getId() == 0)
			offreFormationDomaineCompetenceDao.persist(offreFormationDomaineCompetence);
		else
			offreFormationDomaineCompetence = offreFormationDomaineCompetenceDao.merge(offreFormationDomaineCompetence);

		mapper.map(offreFormationDomaineCompetence, offreFormationDomaineCompetenceDto);
		
		return offreFormationDomaineCompetenceDto;
	}

	@Override
	public void remove(OffreFormationDomaineCompetenceDto offreFormationDomaineCompetenceDto) {

		OffreFormationDomaineCompetence offreFormationDomaineCompetence = mapper.map(offreFormationDomaineCompetenceDto,
				OffreFormationDomaineCompetence.class);

		offreFormationDomaineCompetenceDao.remove(offreFormationDomaineCompetence);
	}

	@Override
	public List<OffreFormationDomaineCompetenceDto> findByQuery(String query) {
	
	    List<OffreFormationDomaineCompetence> offreFormationDomaineCompetences = offreFormationDomaineCompetenceDao.findByQuery(query);

		List<OffreFormationDomaineCompetenceDto> offreFormationDomaineCompetenceDtos = new ArrayList<OffreFormationDomaineCompetenceDto>();

		for (OffreFormationDomaineCompetence offreFormationDomaineCompetence : offreFormationDomaineCompetences) {
			offreFormationDomaineCompetenceDtos.add(mapper.map(offreFormationDomaineCompetence, OffreFormationDomaineCompetenceDto.class));
		}

		return offreFormationDomaineCompetenceDtos;
	
	}

	@Override
	public OffreFormationDomaineCompetenceDto findById(int id) {

		OffreFormationDomaineCompetence offreFormationDomaineCompetence = offreFormationDomaineCompetenceDao.findById(id);

		if (offreFormationDomaineCompetence != null)
			return mapper.map(offreFormationDomaineCompetence, OffreFormationDomaineCompetenceDto.class);

		return null;
	}
	
	@Override
	public List<OffreFormationDomaineCompetenceDto> findAll() {		
	
		List<OffreFormationDomaineCompetence> offreFormationDomaineCompetences = offreFormationDomaineCompetenceDao.findAll();

		List<OffreFormationDomaineCompetenceDto> offreFormationDomaineCompetenceDtos = new ArrayList<OffreFormationDomaineCompetenceDto>();

		for (OffreFormationDomaineCompetence offreFormationDomaineCompetence : offreFormationDomaineCompetences) {
			offreFormationDomaineCompetenceDtos.add(mapper.map(offreFormationDomaineCompetence, OffreFormationDomaineCompetenceDto.class));
		}

		return offreFormationDomaineCompetenceDtos;		
		
	}
}



