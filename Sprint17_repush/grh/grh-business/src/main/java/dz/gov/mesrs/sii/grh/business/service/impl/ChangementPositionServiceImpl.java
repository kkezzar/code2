package dz.gov.mesrs.sii.grh.business.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortField;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.SortOrder;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.ChangementPositionDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.grh.ChangementPosition;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.service.ChangementPositionService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

@Service("changementPositionService")
@Transactional
public class ChangementPositionServiceImpl extends CommonServiceImpl<ChangementPosition, ChangementPositionDto, Long>
		implements ChangementPositionService {

	private ChangementPositionDao changementPositionDao;
	private DossierEmployeDao dossierEmployeDao;
	private SituationEntiteDao situationEntiteDao;
	private NomenclatureDao nomenclatureDao;

	@Override
	protected CommonDao<ChangementPosition, Long> getDao() {
		return changementPositionDao;
	}

	@Override
	public List<DossierEmployeDto> findListEmployesParPositionDto(RefEtablissementDto refEtablissementDto,
			List<NomenclatureDto> listNomenclatureByPositionAgentDto) {
		List<DossierEmployeDto> listDossierEmployeDto = new ArrayList<DossierEmployeDto>();
		RefEtablissement refEtablissement = mapper.map(refEtablissementDto, RefEtablissement.class);
		List<Nomenclature> listNomenclatureByPositionAgent = new ArrayList<Nomenclature>();
		if (listNomenclatureByPositionAgentDto != null)
			if (!listNomenclatureByPositionAgentDto.isEmpty())
				for (NomenclatureDto nomenclatureDto : listNomenclatureByPositionAgentDto) {
					Nomenclature nomenclature = mapper.map(nomenclatureDto, Nomenclature.class);
					listNomenclatureByPositionAgent.add(nomenclature);
				}
		List<DossierEmploye> listDossierEmploye = changementPositionDao.findListEmployesParPosition(refEtablissement,
				listNomenclatureByPositionAgent);
		if (listDossierEmploye != null)
			if (!listDossierEmploye.isEmpty())
				for (DossierEmploye dossierEmploye : listDossierEmploye) {
					DossierEmployeDto dossierEmployeDto = mapper.map(dossierEmploye, DossierEmployeDto.class);
					listDossierEmployeDto.add(dossierEmployeDto);
				}
		return listDossierEmployeDto;
	}

	@Override
	public NomenclatureDto findPosition(Long id) {
		Nomenclature nomenclature = changementPositionDao.findPosition(id);
		NomenclatureDto nomenclatureDto = mapper.map(nomenclature, NomenclatureDto.class);
		return nomenclatureDto;
	}

	@Override
	public ChangementPositionDto findPendingChangementPosition(Long dossierEmployeId) {
		Assert.notNull(dossierEmployeId, "Can't search for a null dossierEmployeId");
		DossierEmploye dossierEmploye = dossierEmployeDao.findById(dossierEmployeId);
		List<ChangementPosition> changementPositions = dossierEmploye.getChangementPositions();
		if (changementPositions != null && !changementPositions.isEmpty()) {
			if (changementPositions.get(0).getAcceptee() == null) {
				return mapper.map(changementPositions.get(0), ChangementPositionDto.class);
			} else {
				return null;
			}
		} else {
			return null;
		}

	}
	
	

	@Override
	public List<ChangementPositionDto> findAllPendingRequest(Integer etablissementId, String searchKeyWords,
			SearchMode searchMode) {
		searchMode = applyGenericFilters(etablissementId, searchMode);
		applyPendingRequestFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllPendingRequest(Integer etablissementId, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(etablissementId, null);
		applyPendingRequestFilter(searchMode);
		return this.countByKeyWord(searchKeyWords, searchMode);
	}

	public ChangementPositionDao getChangementPositionDao() {
		return changementPositionDao;
	}

	@Override
	public ChangementPositionDto saveDemande(ChangementPositionDto dto) {
		Assert.notNull(dto, "can't save a null dto");
		ChangementPosition changementPosition = mapper.map(dto, ChangementPosition.class);
		if (changementPosition.getId() == null) {
			changementPosition.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
					UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_CREEE_CODE));
		} else {
			changementPosition.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
					UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_ENREGISTREE_CODE));
		}

		return mapper.map(changementPositionDao.save(changementPosition), ChangementPositionDto.class);
	}

	@Override
	public void traiterDemande(ChangementPositionDto changementPositionDto) {
		Assert.notNull(changementPositionDto, "can't save a null dto");
		ChangementPosition changementPosition = mapper.map(changementPositionDto, ChangementPosition.class);
		changementPosition.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE));
		changementPositionDao.save(changementPosition);
	}

	@Override
	public int countAllAcceptedRequest(int etablissementId, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(etablissementId, null);
		applyAccepteeFilter(searchMode);
		return this.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<ChangementPositionDto> findAllAcceptedRequest(int etablissementId, String searchKeyWords,
			SearchMode searchMode) {
		searchMode = applyGenericFilters(etablissementId, searchMode);
		applyAccepteeFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public void validate(ChangementPositionDto changementPositionDto) {
		Assert.notNull(changementPositionDto, "can't save a null dto");
		ChangementPosition changementPosition = mapper.map(changementPositionDto, ChangementPosition.class);
		changementPosition.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE));
		changementPosition = changementPositionDao.save(changementPosition);
		DossierEmploye dossierEmploye = changementPosition.getDossierEmploye();
		dossierEmploye.setCurrentPosition(changementPosition);
		dossierEmployeDao.save(dossierEmploye);

	}

	@Override
	public void reintegrer(ChangementPositionDto changementPositionDto) {
		ChangementPosition changementPosition = mapper.map(changementPositionDto, ChangementPosition.class);
		changementPosition.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_REINTEGRER_CODE));
		changementPositionDao.save(changementPosition);
		ChangementPosition positionActive = new ChangementPosition();
		positionActive.setDossierEmploye(changementPosition.getDossierEmploye());
		positionActive.setDateDebut(changementPosition.getDateReintegration());
		positionActive.setSituation(situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE));
		positionActive.setNomenclatureByPosition(nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE));
		changementPositionDao.save(positionActive);
		DossierEmploye dossierEmploye = positionActive.getDossierEmploye();
		dossierEmploye.setCurrentPosition(positionActive);
		dossierEmployeDao.save(dossierEmploye);
	}

	@Override
	public int countAllValidatedRequest(int etablissementId, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(etablissementId, null);
		applyValidatedFilter(searchMode);
		return super.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<ChangementPositionDto> findAllValidatedRequest(int etablissementId, String searchKeyWords,
			SearchMode searchMode) {
		applyGenericFilters(etablissementId, searchMode);
		applyValidatedFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<ChangementPositionDto> findAllPendingChangementDroit(Integer etablissementId, String searchKeyWords,
			SearchMode searchMode) {
		searchMode = applyGenericFilters(etablissementId, searchMode);
		applyPendingChangementDroitFilter(searchMode);
		return super.findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllPendingChangementDroit(Integer etablissementId, String searchKeyWords) {
		SearchMode searchMode = applyGenericFilters(etablissementId, null);
		applyPendingChangementDroitFilter(searchMode);
		return this.countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public ChangementPositionDto saveChangementDroit(ChangementPositionDto changementPositionDto) {
		Assert.notNull(changementPositionDto, "can't save a null dto");
		ChangementPosition changementPosition = mapper.map(changementPositionDto, ChangementPosition.class);
		SituationEntite situation = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_TRAITEE_CODE);
		changementPosition.setSituation(situation);
		changementPosition.setDroit(true);
		changementPosition.setAcceptee(true);
		changementPosition = changementPositionDao.save(changementPosition);
		return mapper.map(changementPosition, ChangementPositionDto.class);
	}

	@Override
	public void validateChangementDroit(ChangementPositionDto changementPositionDto) {
		Assert.notNull(changementPositionDto, "can't validate a null dto");
		ChangementPosition changementPosition = mapper.map(changementPositionDto, ChangementPosition.class);
		SituationEntite situation = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstant.ENTITE_DEMANDE_CHANGEMEMENT_POSITION, UtilConstant.SITUTAION_VALIDEE_CODE);
		changementPosition.setSituation(situation);
		changementPosition = changementPositionDao.save(changementPosition);
		DossierEmploye dossierEmploye = changementPosition.getDossierEmploye();
		dossierEmploye.setCurrentPosition(changementPosition);
		dossierEmployeDao.save(dossierEmploye);

	}

	@Override
	public List<ChangementPositionDto> findHistorique(Long dossierEmployeId) {
		Assert.notNull(dossierEmployeId, "can't search for a null dossierEmployeId");
		SearchMode searchMode = new SearchMode();
		
		searchMode.addFilter(new Filter("situationCode.code", new String[]{UtilConstant.SITUTAION_VALIDEE_CODE , UtilConstant.SITUTAION_REINTEGRER_CODE}  , FilterMode.IN));
		searchMode.addFilter(new Filter("employe.id", dossierEmployeId, FilterMode.EQUALS));
		SortField sortField = new SortField("dateDebut", SortOrder.DESC);
		searchMode.addSortField(sortField);
		return super.findAllByKeyWord(null, searchMode);
	}

	// private
	private void applyValidatedFilter(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_VALIDEE_CODE, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("this.droit", FilterMode.IS_NULL));
	}

	private void applyAccepteeFilter(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_TRAITEE_CODE, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("this.acceptee", Boolean.TRUE, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("this.droit", FilterMode.IS_NULL));
	}

	private void applyPendingRequestFilter(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_TRAITEE_CODE, FilterMode.NOT));
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_VALIDEE_CODE, FilterMode.NOT));
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_REINTEGRER_CODE, FilterMode.NOT));
		searchMode.addFilter(new Filter("this.droit", FilterMode.IS_NULL));

	}

	private void applyPendingChangementDroitFilter(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationCode.code", UtilConstant.SITUTAION_TRAITEE_CODE, FilterMode.EQUALS));
		searchMode.addFilter(new Filter("this.droit", Boolean.TRUE, FilterMode.EQUALS));

	}

	private SearchMode applyGenericFilters(Integer etablissementId, SearchMode searchMode) {
		if (searchMode == null) {
			searchMode = new SearchMode();
		}
		searchMode.addFilter(new Filter("etablissement.id", etablissementId, FilterMode.EQUALS));
		Nomenclature nc = nomenclatureDao.findByCode(UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE);
		searchMode.addFilter(new Filter("nomenclatureByPosition.id", nc.getId(), FilterMode.NOT));
		return searchMode;
	}

	// getters & setters
	@Autowired
	public void setChangementPositionDao(ChangementPositionDao changementPositionDao) {
		this.changementPositionDao = changementPositionDao;
	}

	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

}
