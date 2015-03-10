/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.examen.NoteEliminatoireServiceImpl.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:35:23
 */
package dz.gov.mesrs.sii.fve.business.service.impl.examen;

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

import dz.gov.mesrs.sii.commons.business.util.Utility;
import dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEliminatoireDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:35:23
 */
@Service("noteEliminatoireService")
public class NoteEliminatoireServiceImpl implements NoteEliminatoireService {

	@Autowired
	@Qualifier("noteEliminatoireDao")
	private NoteEliminatoireDao noteEliminatoireDao;

	private static final Log log = LogFactory
			.getLog(NoteEliminatoireServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService#
	 * insertOrUpdate
	 * (dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto)
	 */

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NoteEliminatoireDto insertOrUpdate(
			NoteEliminatoireDto noteEliminatoireDto) {
		NoteEliminatoire noteEliminatoire = mapper.map(noteEliminatoireDto,
				NoteEliminatoire.class);
		try {
			if (noteEliminatoire.getId() == 0) {
				noteEliminatoireDao.persist(noteEliminatoire);
			} else {
				noteEliminatoire = noteEliminatoireDao.merge(noteEliminatoire);
			}
			mapper.map(noteEliminatoire, noteEliminatoireDto);

			log.error("insertorupdate NoteEliminatoireDto succes..");

			return noteEliminatoireDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate NoteEliminatoireDto failed--", e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService#
	 * remove
	 * (dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto)
	 */
	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(NoteEliminatoireDto noteEliminatoireDto) {
		try {

			NoteEliminatoire noteEliminatoire = mapper.map(noteEliminatoireDto,
					NoteEliminatoire.class);

			noteEliminatoire = noteEliminatoireDao.merge(noteEliminatoire);

			noteEliminatoireDao.remove(noteEliminatoire);
		} catch (RuntimeException e) {
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService#
	 * findById(int)
	 */
	@Override
	public NoteEliminatoireDto findById(int id) {
		NoteEliminatoire noteEliminatoire = noteEliminatoireDao.findById(id);

		if (noteEliminatoire != null)
			return mapper.map(noteEliminatoire, NoteEliminatoireDto.class);

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService#
	 * findAll()
	 */
	@Override
	public List<NoteEliminatoireDto> findAll() {
		List<NoteEliminatoire> evaluationControleContinus = noteEliminatoireDao
				.findAll();

		List<NoteEliminatoireDto> noteEliminatoireDtos = new ArrayList<NoteEliminatoireDto>();

		for (NoteEliminatoire noteEliminatoire : evaluationControleContinus) {
			noteEliminatoireDtos.add(mapper.map(noteEliminatoire,
					NoteEliminatoireDto.class));
		}

		return noteEliminatoireDtos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dz.gov.mesrs.sii.fve.business.service.examen.NoteEliminatoireService#
	 * findAdvanced
	 * (dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto)
	 */
	@Override
	public List<NoteEliminatoireDto> findAdvanced(NoteEliminatoireDto searchDto) {
		try {

			NoteEliminatoire noteEliminatoire = mapper.map(searchDto,
					NoteEliminatoire.class);
			List<NoteEliminatoire> noteEliminatoires = noteEliminatoireDao
					.findAdvanced(noteEliminatoire);
			return Utility.map(mapper, noteEliminatoires,
					NoteEliminatoireDto.class);
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
