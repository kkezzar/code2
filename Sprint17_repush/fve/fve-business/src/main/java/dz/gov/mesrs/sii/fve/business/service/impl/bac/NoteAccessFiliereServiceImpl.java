package dz.gov.mesrs.sii.fve.business.service.impl.bac;

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

import dz.gov.mesrs.sii.commons.data.dao.fve.bac.NoteAccessFiliereDao;
import dz.gov.mesrs.sii.commons.data.model.fve.bac.NoteAccessFiliere;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.NoteAccessFiliereDto;
import dz.gov.mesrs.sii.fve.business.service.bac.NoteAccessFiliereService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 12:12:46
 */
@Service("noteAccessFiliereService")
public class NoteAccessFiliereServiceImpl implements NoteAccessFiliereService  {

	@Autowired
	@Qualifier ("noteAccessFiliereDao")
	private NoteAccessFiliereDao noteAccessFiliereDao;

	private static final Log log = LogFactory.getLog(NoteAccessFiliereServiceImpl.class);
	
    @Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;
	
	
	
	public NoteAccessFiliereServiceImpl(){

	}


	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NoteAccessFiliereDto  insertOrUpdate(
			NoteAccessFiliereDto noteAccessFiliereDto) {

		NoteAccessFiliere noteAccessFiliere = mapper.map(noteAccessFiliereDto,
				NoteAccessFiliere.class);
		try {
			if (noteAccessFiliere.getId() == 0){
				noteAccessFiliereDao.persist(noteAccessFiliere);
			}else{
				noteAccessFiliere = noteAccessFiliereDao.merge(noteAccessFiliere);
			}
			mapper.map(noteAccessFiliere, noteAccessFiliereDto);
			
			log.error("insertorupdate NoteAccessFiliere succes..");
			
			return noteAccessFiliereDto;
		
		} catch (RuntimeException e) {
			log.error("insertorupdate NoteAccessFiliere failed--", e);
			throw e;
		}
	}
	

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(NoteAccessFiliereDto noteAccessFiliereDto) {
		try {

		NoteAccessFiliere noteAccessFiliere = mapper.map(noteAccessFiliereDto,
				NoteAccessFiliere.class);
		
		noteAccessFiliere = noteAccessFiliereDao.merge(noteAccessFiliere);

		noteAccessFiliereDao.remove(noteAccessFiliere);
		} catch (RuntimeException e) {
			throw e;
		}
	}


	@Override
	public NoteAccessFiliereDto findById(int id) {

		NoteAccessFiliere noteAccessFiliere = noteAccessFiliereDao.findById(id);

		if (noteAccessFiliere != null)
			return mapper.map(noteAccessFiliere, NoteAccessFiliereDto.class);

		return null;
	}

	
	
	@Override
	public List<NoteAccessFiliereDto> findAll() {		
	
		List<NoteAccessFiliere> noteAccessFilieres = noteAccessFiliereDao.findAll();

		List<NoteAccessFiliereDto> noteAccessFiliereDtos = new ArrayList<NoteAccessFiliereDto>();

		for (NoteAccessFiliere noteAccessFiliere : noteAccessFilieres) {
			noteAccessFiliereDtos.add(mapper.map(noteAccessFiliere, NoteAccessFiliereDto.class));
		}

		return noteAccessFiliereDtos;		
		
	}
}



