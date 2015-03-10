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
import dz.gov.mesrs.sii.commons.data.dao.grh.AutorisationAbsenceDao;
import dz.gov.mesrs.sii.commons.data.model.grh.AutorisationAbsence;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.AutorisationAbsenceDto;
import dz.gov.mesrs.sii.grh.business.service.absence.AutorisationAbsenceService;

@Service("autorisationAbsenceService")
@Transactional
public class AutorisationAbsenceServiceImpl extends
		CommonServiceImpl<AutorisationAbsence, AutorisationAbsenceDto, Integer> implements AutorisationAbsenceService {

	private AutorisationAbsenceDao autorisationAbsenceDao;
	private SituationService situationService;

	@Override
	public AutorisationAbsenceDto enregisterDemande(AutorisationAbsenceDto dto) {
		SituationEntiteDto situation;
		if (dto.getId() == null) {
			situation = situationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
					UtilConstant.SITUTAION_CREEE_CODE);
		} else {
			situation = situationService.findBySituationEntiteByCodeSituation(UtilConstant.ENTITE_DEMANDE_ABSENCE,
					UtilConstant.SITUTAION_ENREGISTREE_CODE);
		}
		dto.setSituationDto(situation);
		return super.save(dto);
	}

	@Override
	public AutorisationAbsenceDto enregisterResultat(AutorisationAbsenceDto dto) {
		SituationEntiteDto situation = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_DEMANDE_ABSENCE, UtilConstant.SITUTAION_TRAITEE_CODE);
		dto.setSituationDto(situation);
		return super.save(dto);
	}

	@Override
	public List<AutorisationAbsenceDto> findAllPendingRequest(int etablissementId, SearchMode searchMode,
			String searchKeyWord) {
		applySituationNonTraiteeFilter(searchMode);
		applyEtablissementFilter(searchMode, etablissementId);
		return super.findAllByKeyWord(searchKeyWord, searchMode);
	}

	@Override
	public int countAllPendingRequest(int etablissementId, String searchKeyWord) {
		SearchMode searchMode = new SearchMode();
		applyEtablissementFilter(searchMode, etablissementId);
		applySituationNonTraiteeFilter(searchMode);
		return super.countByKeyWord(searchKeyWord, searchMode);
	}

	@Override
	public boolean isEmployeAutoriseAbsence(Long employeId, Date dateDebut, Date dateFin) {
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("employe.id", employeId, FilterMode.EQUALS));
		applySituationTraiteeFilter(searchMode);
		searchMode.addFilter(new Filter("acceptee", Boolean.TRUE, FilterMode.EQUALS));
		//FIXME pas bon pour dateFin
		searchMode.addFilter(new Filter("dateDebut", dateDebut, dateFin, FilterMode.BETWEEN));
		int count = super.countByKeyWord(null, searchMode);
		return count > 0;
	}

	// private
	private void applySituationNonTraiteeFilter(SearchMode searchMode) {
		SituationEntiteDto situationTraite = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_DEMANDE_ABSENCE, UtilConstant.SITUTAION_TRAITEE_CODE);
		searchMode.addFilter(new Filter("situation.id", situationTraite.getId(), FilterMode.NOT));

	}

	private void applySituationTraiteeFilter(SearchMode searchMode) {
		SituationEntiteDto situationTraite = situationService.findBySituationEntiteByCodeSituation(
				UtilConstant.ENTITE_DEMANDE_ABSENCE, UtilConstant.SITUTAION_TRAITEE_CODE);
		searchMode.addFilter(new Filter("situation.id", situationTraite.getId(), FilterMode.EQUALS));

	}

	private void applyEtablissementFilter(SearchMode searchMode, int etablissementId) {
		searchMode.addFilter(new Filter("etablissement.id", etablissementId, FilterMode.EQUALS));
	}

	// setters
	@Override
	protected CommonDao<AutorisationAbsence, Integer> getDao() {
		return autorisationAbsenceDao;
	}

	@Autowired
	public void setAutorisationAbsenceDao(AutorisationAbsenceDao autorisationAbsenceDao) {
		this.autorisationAbsenceDao = autorisationAbsenceDao;
	}

	@Autowired
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

}
