package dz.gov.mesrs.sii.fve.business.service.impl.cursus;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.LigneReleveDeNotesDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.LigneReleveDeNotes;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.LigneReleveDeNotesDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.LigneReleveDeNotesService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 07-01-2014 16:44:07
 */

@Service("ligneReleveDeNotesService")
public class LigneReleveDeNotesServiceImpl implements LigneReleveDeNotesService {

	@Autowired
	@Qualifier("ligneReleveDeNotesDao")
	private LigneReleveDeNotesDao ligneReleveDeNotesDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public LigneReleveDeNotesServiceImpl() {

	}

	@Override
	public LigneReleveDeNotesDto insertOrUpdate(
			LigneReleveDeNotesDto ligneReleveDeNotesDto) {

		LigneReleveDeNotes ligneReleveDeNotes = new LigneReleveDeNotes();
		mapper.map(ligneReleveDeNotesDto, ligneReleveDeNotes);

		if (ligneReleveDeNotes.getId() == 0) {
			ligneReleveDeNotesDao.persist(ligneReleveDeNotes);
		} else {
			ligneReleveDeNotes = ligneReleveDeNotesDao
					.merge(ligneReleveDeNotes);
		}
		mapper.map(ligneReleveDeNotes, ligneReleveDeNotesDto);

		return ligneReleveDeNotesDto;
	}

	@Override
	public void remove(LigneReleveDeNotesDto ligneReleveDeNotesDto) {

		LigneReleveDeNotes ligneReleveDeNotes = mapper.map(
				ligneReleveDeNotesDto, LigneReleveDeNotes.class);
		ligneReleveDeNotes = ligneReleveDeNotesDao.merge(ligneReleveDeNotes);

		ligneReleveDeNotesDao.remove(ligneReleveDeNotes);
	}

	
	@Override
	public LigneReleveDeNotesDto findById(int id) {

		LigneReleveDeNotes ligneReleveDeNotes = ligneReleveDeNotesDao
				.findById(id);

		if (ligneReleveDeNotes != null)
			return mapper.map(ligneReleveDeNotes, LigneReleveDeNotesDto.class);

		return null;
	}

	@Override
	public List<LigneReleveDeNotesDto> findAll() {

		List<LigneReleveDeNotes> ligneReleveDeNotess = ligneReleveDeNotesDao
				.findAll();

		List<LigneReleveDeNotesDto> ligneReleveDeNotesDtos = new ArrayList<LigneReleveDeNotesDto>();

		for (LigneReleveDeNotes ligneReleveDeNotes : ligneReleveDeNotess) {
			ligneReleveDeNotesDtos.add(mapper.map(ligneReleveDeNotes,
					LigneReleveDeNotesDto.class));
		}

		return ligneReleveDeNotesDtos;

	}

	@Override
	public List<LigneReleveDeNotesDto> findLignesOfReleve(int idReleve) {

		List<LigneReleveDeNotes> listeLignes = ligneReleveDeNotesDao
				.findLignesOfReleve(idReleve);

		if (listeLignes != null && !listeLignes.isEmpty()) {
			List<LigneReleveDeNotesDto> listeLignesDto = new ArrayList<LigneReleveDeNotesDto>();

			for (LigneReleveDeNotes ligneReleveDeNotes : listeLignes) {
				listeLignesDto.add(mapper.map(ligneReleveDeNotes,
						LigneReleveDeNotesDto.class));
			}

			return listeLignesDto;
		} else
			return null;

	}
	
	
	public void deleteAllLignesOfReleve (int idReleve){
		
		ligneReleveDeNotesDao.deleteAllLignesOfReleve(idReleve);
		
	}

}
