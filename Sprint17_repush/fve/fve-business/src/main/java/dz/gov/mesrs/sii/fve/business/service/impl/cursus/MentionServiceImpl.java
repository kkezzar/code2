/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.MentionServiceImpl.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:52:00
 */
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

import dz.gov.mesrs.sii.commons.data.dao.fve.cursus.MentionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Mention;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.MentionDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.MentionService;
import dz.gov.mesrs.sii.fve.business.service.impl.examen.EvaluationControleContinuServiceImpl;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
 */
@Service("mentionService")
public class MentionServiceImpl implements MentionService {

	/**
	 * [MentionServiceImpl.MentionServiceImpl()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:52:00
	 */
	@Autowired
	@Qualifier("mentionDao")
	private MentionDao mentionDao;

	private static final Log log = LogFactory
			.getLog(EvaluationControleContinuServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	public MentionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public MentionDto insertOrUpdate(MentionDto mentionDto) {

		Mention mention = mapper.map(mentionDto, Mention.class);
		try {
			if (mention.getId() == 0) {
				mentionDao.persist(mention);
			} else {
				mention = mentionDao.merge(mention);
			}
			mapper.map(mention, mentionDto);

			log.error("insertorupdate Mention succes..");

			return mentionDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate Mention failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(MentionDto mentionDto) {
		try {

			Mention mention = mapper.map(mentionDto, Mention.class);

			mention = mentionDao.merge(mention);

			mentionDao.remove(mention);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public MentionDto findById(int id) {

		Mention mention = mentionDao.findById(id);

		if (mention != null)
			return mapper.map(mention, MentionDto.class);

		return null;
	}

	@Override
	public List<MentionDto> findAll() {

		List<Mention> mentions = mentionDao.findAll();

		List<MentionDto> mentionDtos = new ArrayList<MentionDto>();

		for (Mention mention : mentions) {
			mentionDtos.add(mapper.map(mention, MentionDto.class));
		}

		return mentionDtos;

	}

	@Override
	public MentionDto findByNote(Integer anneeId, double note) {
		try {

			Mention mention = mentionDao.findByNote(anneeId, note);

			if (mention != null) {
				return mapper.map(mention, MentionDto.class);
			}
		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}

	@Override
	public List<MentionDto> findByAnneeAcademique(Integer anneeId) {
		try {

			List<Mention> mentionList = mentionDao.findByAnneeAcademique(anneeId);
			List<MentionDto> result = new ArrayList<MentionDto>();
			if (mentionList != null) {
				for(Mention mention : mentionList) {
					result.add(mapper.map(mention, MentionDto.class));
				}
				return result;
			}
		} catch (RuntimeException e) {
			throw e;
		}
		return null;
	}
}
