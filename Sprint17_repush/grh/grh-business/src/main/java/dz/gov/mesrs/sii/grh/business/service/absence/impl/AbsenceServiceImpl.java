package dz.gov.mesrs.sii.grh.business.service.absence.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.grh.AbsenceDao;
import dz.gov.mesrs.sii.commons.data.model.grh.Absence;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.absence.AbsenceService;

@Service("absenceService")
@Transactional
public class AbsenceServiceImpl extends CommonServiceImpl<Absence, AbsenceDto, Integer> implements AbsenceService {

	private AbsenceDao absenceDao;
	private SituationService situationService;

	@Override
	public AbsenceDto enregisterAbsence(AbsenceDto dto) {
		SituationEntiteDto situation;
		if (dto.getId() == null) {
			situation = situationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
					UtilConstant.SITUTAION_CREEE_CODE);
		} else {
			situation = situationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_GRH_ABSENCE,
					UtilConstant.SITUTAION_ENREGISTREE_CODE);
		}
		dto.setSituationDto(situation);
		return super.save(dto);
	}

	@Override
	public void confirmerAbsence(AbsenceDto dto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_GRH_ABSENCE, UtilConstant.SITUTAION_VALIDEE_CODE);
		dto.setSituationDto(situation);
		super.save(dto);
	}

	@Override
	public List<AbsenceDto> findAllAbsencesEnregistrees(int etablissmentId, SearchMode searchMode, String searKeyWords) {
		applyEtablissementFilter(searchMode, etablissmentId);
		applyFilterNonValidee(searchMode);
		return super.findAllByKeyWord(searKeyWords, searchMode);
	}

	@Override
	public int countAllAbsencesEnregistrees(int etablissmentId, String searKeyWords) {
		SearchMode searchMode = new SearchMode();
		applyEtablissementFilter(searchMode, etablissmentId);
		applyFilterNonValidee(searchMode);
		return super.countByKeyWord(searKeyWords, searchMode);
	}

	@Override
	public boolean isEmployeAbsent(Long employeId, Date dateDebut, Date dateFin) {
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("employe.id", employeId, FilterMode.EQUALS));
		applyFilterValidee(searchMode);
		//FIXME pas bon pour dateFin
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		int count = super.countByKeyWord(null, searchMode);
		return count > 0;
	}
	
	@Override
	public List<AbsenceDto> findAllAbsencesEmploye(Long id) {
		AbsenceDto example = new AbsenceDto();
		DossierEmployeDto employe = new DossierEmployeDto(id);
		example.setDossierEmployeDto(employe);
		return super.findAllByExample(example);
	}
	
	// private
	private void applyEtablissementFilter(SearchMode searchMode, int etablissementId) {
		searchMode.addFilter(new Filter("etablissement.id", etablissementId, FilterMode.EQUALS));
	}

	private void applyFilterNonValidee(SearchMode searchMode) {
		SituationEntiteDto situationValidee = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_GRH_ABSENCE, UtilConstant.SITUTAION_VALIDEE_CODE);
		searchMode.addFilter(new Filter("situation.id", situationValidee.getId(), FilterMode.NOT));

	}
	
	private void applyFilterValidee(SearchMode searchMode) {
		SituationEntiteDto situationValidee = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_GRH_ABSENCE, UtilConstant.SITUTAION_VALIDEE_CODE);
		searchMode.addFilter(new Filter("situation.id", situationValidee.getId(), FilterMode.EQUALS));

	}
	

	// setters

	@Override
	protected CommonDao<Absence, Integer> getDao() {
		return absenceDao;
	}

	@Autowired
	public void setAbsenceDao(AbsenceDao absenceDao) {
		this.absenceDao = absenceDao;
	}

	@Autowired
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	
}
