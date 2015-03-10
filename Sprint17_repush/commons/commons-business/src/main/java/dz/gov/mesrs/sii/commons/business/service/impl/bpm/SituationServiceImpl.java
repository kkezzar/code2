/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.habilitation.SituationEntiteServiceImpl.java] 
 * @author  Rafik LAIB on : 3 mai 2014  12:23:12
 */
package dz.gov.mesrs.sii.commons.business.service.impl.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteOccurrenceDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.security.userdetails.ProgresUser;

/**
 * @author Rafik LAIB on : 3 mai 2014 12:23:12
 */
@Service("situationService")
public class SituationServiceImpl implements SituationService {

	private static final Log log = LogFactory.getLog(SituationServiceImpl.class);

	@Autowired
	@Qualifier("situationEntiteDao")
	private SituationEntiteDao situationEntiteDao;

	@Autowired
	@Qualifier("situationEntiteOccurrenceDao")
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

	@Autowired
	@Qualifier("mapper")
	private DozerBeanMapper mapper;

	@Override
	@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
	public SituationEntiteOccurrenceDto saveSituationOccurrence(
			SituationEntiteOccurrenceDto situationEntiteOccurrenceDto) {
		try {
			if (situationEntiteOccurrenceDto.getRefCompteId() == null) {
				// inject l'id du compte de l'utilisateur en cours
				ProgresUser principal = (ProgresUser) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				situationEntiteOccurrenceDto.setRefCompteId(principal.getCompte().getIdCompte());

			}

			SituationEntiteOccurrence situationEntiteOccurrence = mapper.map(situationEntiteOccurrenceDto,
					SituationEntiteOccurrence.class);
			if (situationEntiteOccurrence.getId() == 0)
				situationEntiteOccurrenceDao.persist(situationEntiteOccurrence);
			else
				situationEntiteOccurrence = situationEntiteOccurrenceDao.merge(situationEntiteOccurrence);
			mapper.map(situationEntiteOccurrence, situationEntiteOccurrenceDto);
			return situationEntiteOccurrenceDto;
		} catch (RuntimeException e) {
			log.error("findByIdSituationEntite failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true)
	public SituationEntiteDto findBySituationEntiteByCodeSituation(String codeEntite, String codeSituation) {

		try {

			SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(codeEntite,
					codeSituation);
			return mapper.map(situationEntite, SituationEntiteDto.class);

		} catch (RuntimeException e) {

			log.error("findBySituationEntiteByCodeSituation failed", e);
			throw e;
		}

	}

	@Override
	@Transactional(readOnly = true, value = "transactionManager", propagation = Propagation.REQUIRED)
	public List<SituationEntiteOccurrenceDto> getEntityOccurrenceHistory(String entiteCode, Integer entiteOccurrenceId) {
		try {
			List<SituationEntiteOccurrence> situationEntiteOccurrences = situationEntiteOccurrenceDao
					.getEntityOccurrenceHistory(entiteCode, entiteOccurrenceId);
			List<SituationEntiteOccurrenceDto> situationEntiteOccurrenceDtos = new ArrayList<SituationEntiteOccurrenceDto>();
			for (SituationEntiteOccurrence situationEntiteOccurrence : situationEntiteOccurrences) {
				SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
				mapper.map(situationEntiteOccurrence, situationEntiteOccurrenceDto);
				situationEntiteOccurrenceDtos.add(situationEntiteOccurrenceDto);
			}
			return situationEntiteOccurrenceDtos;
		} catch (RuntimeException e) {
			log.error("getEntityOccurrenceHistory failed", e);
			throw e;
		}

	}

	/**
	 * [SituationService.findSituationsByEntiteCode] Method
	 * 
	 * @author rlaib on : 10 nov. 2014 15:11:49
	 * @param codeEntite
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SituationEntiteDto> findSituationsByEntiteCode(String codeEntite) {
		try {
			List<SituationEntite> situationEntites = situationEntiteDao.findSituationsByEntiteCode(codeEntite);
			List<SituationEntiteDto> situationEntiteDtos = new ArrayList<SituationEntiteDto>();
			for (SituationEntite _situationEntite : situationEntites) {

				SituationEntiteDto _situationEntiteDto = new SituationEntiteDto();
				// SituationI18nDto situationI18nDto = new SituationI18nDto();
				// SituationI18n situationI18n = new SituationI18n();
				// List<SituationI18n> situationI18ns =
				// situationEntiteDao.findByIdSituationEntite(_situationEntite.getId());
				// for (SituationI18n sI18n : situationI18ns) {
				// if(sI18n.getLangue().equals("fr"))
				// situationI18n = sI18n;
				// }
				// mapper.map(situationI18n, situationI18nDto);
				// mapper.map(situationI18nDto, _situationEntiteDto);
				mapper.map(_situationEntite, _situationEntiteDto);
				situationEntiteDtos.add(_situationEntiteDto);
			}
			return situationEntiteDtos;
		} catch (RuntimeException e) {
			log.error("findSituationsByEntiteCode failed", e);
			throw e;
		}
	}

	/**
	 * @author salem chouikh
	 * @pram situationEntiteDto ,idEntite enregistrer une ligne dans la
	 *       situation occurence
	 */
	@Override
	public void saveSituationOccurrence(SituationEntiteDto situationEntiteDto, Integer idEntite) {
		SituationEntiteOccurrenceDto situationEntiteOccurrenceDto = new SituationEntiteOccurrenceDto();
		situationEntiteOccurrenceDto.setDateSituation(new Date());
		situationEntiteOccurrenceDto.setIdOccurrence(idEntite);
		situationEntiteOccurrenceDto.setIdSituation(situationEntiteDto.getId());
		saveSituationOccurrence(situationEntiteOccurrenceDto);
	}
}