/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.cursus.BilanControleContinuServiceImpl.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  17:24:48
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

import dz.gov.mesrs.sii.commons.data.dao.fve.examen.DeliberationSessionDao;
import dz.gov.mesrs.sii.commons.data.model.fve.examen.DeliberationSession;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.DeliberationSessionDto;
import dz.gov.mesrs.sii.fve.business.service.examen.DeliberationSessionService;

/**
 * @author MAKERRI Sofiane on : 16 oct. 2014 17:24:48
 */
@Service("deliberationSessionService")
public class DeliberationSessionServiceImpl implements DeliberationSessionService {

	@Autowired
	@Qualifier("deliberationSessionDao")
	private DeliberationSessionDao deliberationSessionDao;

	private static final Log log = LogFactory.getLog(EvaluationControleContinuServiceImpl.class);

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	/**
	 * [BilanControleContinuServiceImpl.BilanControleContinuServiceImpl()]
	 * Constructor
	 * 
	 * @author MAKERRI Sofiane on : 16 oct. 2014 17:24:48
	 */
	public DeliberationSessionServiceImpl() {

	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public DeliberationSessionDto insertOrUpdate(DeliberationSessionDto deliberationSessionDto) {

		DeliberationSession deliberationSession = mapper.map(deliberationSessionDto, DeliberationSession.class);
		try {
			if (deliberationSession.getId() == 0) {
				deliberationSessionDao.persist(deliberationSession);
			} else {
				deliberationSession = deliberationSessionDao.merge(deliberationSession);
			}
			mapper.map(deliberationSession, deliberationSessionDto);

			log.info("insertOrUpdate DeliberationSession succes..");

			return deliberationSessionDto;

		} catch (RuntimeException e) {
			log.error("insertorupdate DeliberationSession failed--", e);
			throw e;
		}
	}

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public void remove(DeliberationSessionDto deliberationSessionDto) {
		try {

			DeliberationSession deliberationSession = mapper.map(deliberationSessionDto, DeliberationSession.class);

			deliberationSession = deliberationSessionDao.merge(deliberationSession);

			deliberationSessionDao.remove(deliberationSession);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public DeliberationSessionDto findById(int id) {

		DeliberationSession deliberationSession = deliberationSessionDao.findById(id);

		if (deliberationSession != null)
			return mapper.map(deliberationSession, DeliberationSessionDto.class);

		return null;
	}

	@Override
	public List<DeliberationSessionDto> findAll() {

		List<DeliberationSession> deliberationSessions = deliberationSessionDao.findAll();

		List<DeliberationSessionDto> deliberationSessionDtos = new ArrayList<DeliberationSessionDto>();

		for (DeliberationSession deliberationSession : deliberationSessions) {
			deliberationSessionDtos.add(mapper.map(deliberationSession, DeliberationSessionDto.class));
		}

		return deliberationSessionDtos;

	}

	@Override
	public DeliberationSessionDto findByPlanningId(Long planningId) {
		try {
			if (planningId == null) {
				return null;
			}
			DeliberationSession deliberationSession = deliberationSessionDao.findByPlanningId(planningId);
			if (deliberationSession != null) {
				return mapper.map(deliberationSession, DeliberationSessionDto.class);
			}
			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<DeliberationSessionDto> findAdvanced(DeliberationSessionDto searchDto) {
		try {
			if (searchDto == null) {
				return null;
			}
			DeliberationSession searchBo = mapper.map(searchDto, DeliberationSession.class);

			List<DeliberationSession> deliberationSessions = deliberationSessionDao.findAdvanced(searchBo);

			List<DeliberationSessionDto> deliberationSessionDtos = new ArrayList<DeliberationSessionDto>();
			if (deliberationSessions != null) {
				for (DeliberationSession deliberationSession : deliberationSessions) {
					deliberationSessionDtos.add(mapper.map(deliberationSession, DeliberationSessionDto.class));
				}
			}

			return deliberationSessionDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<DeliberationSessionDto> findBilanPeriode(Integer anneeId, int oofId, Integer niveauId) {
		try {
			List<DeliberationSession> deliberationSessions = deliberationSessionDao.findBilanPeriode(anneeId, oofId,
					niveauId);

			List<DeliberationSessionDto> deliberationSessionDtos = new ArrayList<DeliberationSessionDto>();
			if (deliberationSessions != null) {
				for (DeliberationSession deliberationSession : deliberationSessions) {
					deliberationSessionDtos.add(mapper.map(deliberationSession, DeliberationSessionDto.class));
				}
			}

			return deliberationSessionDtos;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public DeliberationSessionDto bilanPeriodeExist(int anneeId, int oofId, int periodeId) {
		try {
			DeliberationSession deliberationSession = deliberationSessionDao.bilanPeriodeExist(anneeId, oofId,
					periodeId);
			if (deliberationSession != null) {

				return mapper.map(deliberationSession, DeliberationSessionDto.class);
			}

			return null;

		} catch (RuntimeException e) {
			throw e;

		}
	}

}
