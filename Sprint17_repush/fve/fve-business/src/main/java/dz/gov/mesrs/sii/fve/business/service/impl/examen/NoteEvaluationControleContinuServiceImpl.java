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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.NoteEvaluationControleContinuDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEvaluationControleContinu;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AtomePedagogique;
import dz.gov.mesrs.sii.commons.data.util.NoteMoyenneAp;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.service.examen.NoteEvaluationControleContinuService;

/**
 * @author MESRS CCM
 * @version 1.0
 * @created 20-04-2014 16:44:07
 */

@Service("noteEvaluationControleContinuService")
public class NoteEvaluationControleContinuServiceImpl implements
		NoteEvaluationControleContinuService {

	@Autowired
	@Qualifier("noteEvaluationControleContinuDao")
	private NoteEvaluationControleContinuDao noteEvaluationControleContinuDao;

	private static final Log log = LogFactory
			.getLog(NoteEvaluationControleContinuServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public NoteEvaluationControleContinuServiceImpl() {
//		final List<String> mappings = new ArrayList<String>(1);
//		mappings.add(UtilConstants.DOZER_EVAL_CONTROLE_CONTINU_MAPPING_NAME);
//		mapper = new DozerBeanMapper();
//		mapper.setMappingFiles(mappings);
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public NoteEvaluationControleContinuDto insertOrUpdate(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {

		NoteEvaluationControleContinu noteEvaluationControleContinu = mapper
				.map(noteEvaluationControleContinuDto,
						NoteEvaluationControleContinu.class);
		try {
			if (noteEvaluationControleContinu.getId() == 0) {
				noteEvaluationControleContinuDao
						.persist(noteEvaluationControleContinu);
			} else {
				noteEvaluationControleContinu = noteEvaluationControleContinuDao
						.merge(noteEvaluationControleContinu);
			}
			mapper.map(noteEvaluationControleContinu,
					noteEvaluationControleContinuDto);

			log.info("insertorupdate NoteEvaluationControleContinu succes..");

			return noteEvaluationControleContinuDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate NoteEvaluationControleContinu failed--",
					e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {
		try {

			NoteEvaluationControleContinu noteEvaluationControleContinu = mapper
					.map(noteEvaluationControleContinuDto,
							NoteEvaluationControleContinu.class);

			noteEvaluationControleContinu = noteEvaluationControleContinuDao
					.merge(noteEvaluationControleContinu);

			noteEvaluationControleContinuDao
					.remove(noteEvaluationControleContinu);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public NoteEvaluationControleContinuDto findById(long id) {
		try {
			NoteEvaluationControleContinu noteEvaluationControleContinu = noteEvaluationControleContinuDao
					.findById(id);

			if (noteEvaluationControleContinu != null)
				return mapper.map(noteEvaluationControleContinu,
						NoteEvaluationControleContinuDto.class);

			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<NoteEvaluationControleContinuDto> findAll() {
		try {
			List<NoteEvaluationControleContinu> noteEvaluationControleContinus = noteEvaluationControleContinuDao
					.findAll();

			List<NoteEvaluationControleContinuDto> noteEvaluationControleContinuDtos = new ArrayList<NoteEvaluationControleContinuDto>();

			for (NoteEvaluationControleContinu noteEvaluationControleContinu : noteEvaluationControleContinus) {
				noteEvaluationControleContinuDtos.add(mapper.map(
						noteEvaluationControleContinu,
						NoteEvaluationControleContinuDto.class));
			}

			return noteEvaluationControleContinuDtos;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public NoteEvaluationControleContinuDto findByEvalIdAndAffGp(
			Long evalId, Integer agpId) {
		try {
			NoteEvaluationControleContinu noteEvaluationControleContinu = noteEvaluationControleContinuDao
					.findByEvalIdAndAffGp(evalId, agpId);
			if (noteEvaluationControleContinu == null) {
				return null;
			}
			return mapper.map(noteEvaluationControleContinu,
					NoteEvaluationControleContinuDto.class);
		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Override
	public List<NoteEvaluationControleContinuDto> findByEvalId(Long evalId) {
		try {
			List<NoteEvaluationControleContinu> noteEvaluationControleContinus = noteEvaluationControleContinuDao
					.findByEvalId(evalId);

			List<NoteEvaluationControleContinuDto> noteEvaluationControleContinuDtos = new ArrayList<NoteEvaluationControleContinuDto>();

			for (NoteEvaluationControleContinu noteEvaluationControleContinu : noteEvaluationControleContinus) {
				noteEvaluationControleContinuDtos.add(mapper.map(
						noteEvaluationControleContinu,
						NoteEvaluationControleContinuDto.class));
			}

			return noteEvaluationControleContinuDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<NoteMoyenneAp> calculMoyenne(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {
		try {

			NoteEvaluationControleContinu noteEvaluationControleContinu = mapper
					.map(noteEvaluationControleContinuDto,
							NoteEvaluationControleContinu.class);

			List<NoteMoyenneAp> noteMoyenneAps = noteEvaluationControleContinuDao
					.calculMoyenne(noteEvaluationControleContinu);
			return noteMoyenneAps;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<NoteEvaluationControleContinuDto> findAdvanced(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {
		try {

			NoteEvaluationControleContinu noteEvaluationControleContinu = mapper
					.map(noteEvaluationControleContinuDto,
							NoteEvaluationControleContinu.class);

			List<NoteEvaluationControleContinu> noteEvaluationControleContinus = noteEvaluationControleContinuDao
					.findAdvanced(noteEvaluationControleContinu);
			List<NoteEvaluationControleContinuDto> noteEvaluationControleContinuDtos = new ArrayList<NoteEvaluationControleContinuDto>();
			if (noteEvaluationControleContinus != null) {
				for (NoteEvaluationControleContinu current : noteEvaluationControleContinus) {
					noteEvaluationControleContinuDtos.add(mapper.map(current,
							NoteEvaluationControleContinuDto.class));
				}
			}

			return noteEvaluationControleContinuDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<AtomePedagogiqueDto> findApOfCC(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {
		try {
			NoteEvaluationControleContinu noteEvaluationControleContinu = mapper
					.map(noteEvaluationControleContinuDto,
							NoteEvaluationControleContinu.class);

			List<AtomePedagogique> atomePedagogiques = noteEvaluationControleContinuDao
					.findApOfCC(noteEvaluationControleContinu);

			List<AtomePedagogiqueDto> atomePedagogiqueDtos = new ArrayList<AtomePedagogiqueDto>();
			if (atomePedagogiques != null) {
				for (AtomePedagogique atomePedagogique : atomePedagogiques) {
					atomePedagogiqueDtos.add(mapper.map(atomePedagogique,
							AtomePedagogiqueDto.class));
				}
			}

			return atomePedagogiqueDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
