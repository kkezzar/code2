package dz.gov.mesrs.sii.grh.business.service.evaluation.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.EvaluationPeriodeDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.EvaluationPeriode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.EvaluationPeriodeDto;
import dz.gov.mesrs.sii.grh.business.service.evaluation.EvaluationPeriodeService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@Service("evaluationPeriodeService")
@Transactional
public class EvaluationPeriodeServiceImpl extends CommonServiceImpl<EvaluationPeriode, EvaluationPeriodeDto, Long>
		implements EvaluationPeriodeService {

	private final static Logger logger = LoggerFactory.getLogger(EvaluationPeriodeServiceImpl.class.getName());

	private EvaluationPeriodeDao evaluationPeriodeDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntite situationCreee;
	private SituationEntite situationEnregistree;
	private SituationEntite situationCloturee;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;

	@PostConstruct
	public void init() {
		situationCreee = situationEntiteDao.findByCodeSituationByCodeEntite(UtilConstant.ENTITE_GRH_EVALUTION_PERIODE,
				UtilConstant.SITUTAION_CREEE_CODE);
		situationEnregistree = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_GRH_EVALUTION_PERIODE, UtilConstant.SITUTAION_ENREGISTREE_CODE);
		situationCloturee = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_GRH_EVALUTION_PERIODE, UtilConstant.SITUTAION_CLOTUREE_CODE);
	}

	@Override
	public EvaluationPeriodeDto saveEvaluationPeriode(EvaluationPeriodeDto dto) {
		Assert.notNull(dto, "can't save a null entity");
		logger.debug("Saving evaluation periode {}", new Object[] { dto });
		EvaluationPeriode entity = mapper.map(dto, EvaluationPeriode.class);
		if (dto.getId() == null) {
			entity.setSituation(situationCreee);
		} else {
			entity.setSituation(situationEnregistree);
		}

		entity = evaluationPeriodeDao.save(entity);
		logger.debug("Evaluation periode saved {}", new Object[] { entity });
		persistOccurence(entity);
		return mapper.map(entity, EvaluationPeriodeDto.class);
	}

	@Override
	public EvaluationPeriodeDto cloturerEvaluationPeriode(EvaluationPeriodeDto dto) {
		Assert.notNull(dto, "can't save a null entity");
		logger.debug("Cloturation evaluation periode {}", new Object[] { dto });
		EvaluationPeriode entity = mapper.map(dto, EvaluationPeriode.class);
		entity.setSituation(situationCloturee);
		entity = evaluationPeriodeDao.save(entity);
		logger.debug(" Evaluation periode cloturee {}", new Object[] { dto });
		persistOccurence(entity);
		return mapper.map(entity, EvaluationPeriodeDto.class);
	}

	@Override
	public List<EvaluationPeriodeDto> findAllEvalutionPeriodeNonCloture(Date dateDebutPeriode, Date dateFinPeriode,
			Integer etablissementId, SearchMode searchMode) {
		EvaluationPeriodeDto example = createExempleNonCloturee(dateDebutPeriode, dateFinPeriode, etablissementId,
				searchMode);
		return super.findAllByExample(example, searchMode);
	}
	
	@Override
	public EvaluationPeriodeDto findLastPeriodeEvaluation(int etablissementId) {
		EvaluationPeriode periode = evaluationPeriodeDao.findLastPeriodeEvaluation(etablissementId);
		if(periode!=null){
			return mapper.map(periode, EvaluationPeriodeDto.class);
		}else{
			return null;	
		}
	}

	private EvaluationPeriodeDto createExempleNonCloturee(Date dateDebutPeriode, Date dateFinPeriode,
			int etablissementId, SearchMode searchMode) {
		EvaluationPeriodeDto example = new EvaluationPeriodeDto();
		RefEtablissementDto etablissement = new RefEtablissementDto();
		etablissement.setId(etablissementId);
		example.setEtablissementDto(etablissement);
		searchMode.addFilter(new Filter("situation.id", situationCloturee.getId(), FilterMode.NOT));
		if (dateDebutPeriode != null) {
			searchMode.addFilter(new Filter("dateDebutPeriode", dateDebutPeriode, FilterMode.GREATER_OR_EQUAL));
		}
		if (dateFinPeriode != null) {
			searchMode.addFilter(new Filter("dateFinPeriode", dateFinPeriode, FilterMode.LESS_OR_EQUAL));
		}
		return example;
	}

	@Override
	public int countAllEvalutionPeriodeNonCloture(Date dateDebutPeriode, Date dateFinPeriode, int etablissementId) {
		SearchMode searchMode = new SearchMode();
		EvaluationPeriodeDto exampleDto = createExempleNonCloturee(dateDebutPeriode, dateFinPeriode, etablissementId,
				searchMode);
		return super.countAllByExample(exampleDto, searchMode);
	}

	@Override
	protected CommonDao<EvaluationPeriode, Long> getDao() {
		return this.evaluationPeriodeDao;
	}

	// private
	private void persistOccurence(EvaluationPeriode entity) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		Date dateOccurence = new Date();
		occurence.setDateSituation(dateOccurence);
		occurence.setIdOccurrence(entity.getId().intValue());
		occurence.setSituationEntite(entity.getSituation());
		situationEntiteOccurrenceDao.persist(occurence);
		logger.debug("Siutation occurence persiste - date : {} , idOccurence : {} , situationEntiteId : {}",
				new Object[] { dateOccurence, entity.getId().intValue(), entity.getSituation().getId() });

	}

	// Getters & Setters
	@Autowired
	public void setEvaluationPeriodeDao(EvaluationPeriodeDao evaluationPeriodeDao) {
		this.evaluationPeriodeDao = evaluationPeriodeDao;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	@Autowired
	public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	public void setSituationCreee(SituationEntite situationCreee) {
		this.situationCreee = situationCreee;
	}

	public void setSituationEnregistree(SituationEntite situationEnregistree) {
		this.situationEnregistree = situationEnregistree;
	}

	public void setSituationCloturee(SituationEntite situationCloturee) {
		this.situationCloturee = situationCloturee;
	}

	

}
