package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.ReleveDeNotesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.ReleveDeNotes;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReleveDeNotesService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */
 

@Service("releveDeNotesService")
public class ReleveDeNotesServiceImpl implements ReleveDeNotesService  {

	@Autowired
	@Qualifier ("releveDeNotesDao")
	private ReleveDeNotesDao releveDeNotesDao;


    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public ReleveDeNotesServiceImpl(){

	}

   

	@Override
	public ReleveDeNotesDto  insertOrUpdate(
			ReleveDeNotesDto releveDeNotesDto) {

		ReleveDeNotes releveDeNotes = mapper.map(releveDeNotesDto,
				ReleveDeNotes.class);
		
		if (releveDeNotes.getId() == 0)
			releveDeNotesDao.persist(releveDeNotes);
		else
			releveDeNotes = releveDeNotesDao.merge(releveDeNotes);

		mapper.map(releveDeNotes, releveDeNotesDto);
		
		return releveDeNotesDto;
	}
	

	@Override
	public void remove(ReleveDeNotesDto releveDeNotesDto) {

		ReleveDeNotes releveDeNotes = mapper.map(releveDeNotesDto,
				ReleveDeNotes.class);
		
		releveDeNotes = releveDeNotesDao.merge(releveDeNotes);
		
		releveDeNotesDao.remove(releveDeNotes);
	}



	@Override
	public ReleveDeNotesDto findById(int id) {

		ReleveDeNotes releveDeNotes = releveDeNotesDao.findById(id);

		if (releveDeNotes != null)
			return mapper.map(releveDeNotes, ReleveDeNotesDto.class);

		return null;
	}

	
	
	@Override
	public List<ReleveDeNotesDto> findAll() {		
	
		List<ReleveDeNotes> releveDeNotess = releveDeNotesDao.findAll();

		List<ReleveDeNotesDto> releveDeNotesDtos = new ArrayList<ReleveDeNotesDto>();

		for (ReleveDeNotes releveDeNotes : releveDeNotess) {
			releveDeNotesDtos.add(mapper.map(releveDeNotes, ReleveDeNotesDto.class));
		}

		return releveDeNotesDtos;		
		
	}
}



