package dz.gov.mesrs.sii.grh.business.service.mutation.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.business.service.impl.CommonServiceImpl;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.CommonDao;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteDao;
import dz.gov.mesrs.sii.commons.data.dao.bpm.SituationEntiteOccurrenceDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.DossierEmployeDao;
import dz.gov.mesrs.sii.commons.data.dao.grh.MutationDao;
import dz.gov.mesrs.sii.commons.data.dao.referentiel.NomenclatureDao;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntiteOccurrence;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.grh.Mutation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.grh.business.model.dto.ConjointDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DiplomeEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DistinctionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EnfantDto;
import dz.gov.mesrs.sii.grh.business.model.dto.MutationDto;
import dz.gov.mesrs.sii.grh.business.service.mutation.MutationService;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 22-10-2014
 * @description Implementation des services
 * 
 */
@Service("mutationService")
public class MutationServiceImpl extends CommonServiceImpl<Mutation, MutationDto, Integer> implements MutationService {

	private MutationDao mutationDao;
	private DossierEmployeDao dossierEmployeDao;
	private SituationEntiteDao situationEntiteDao;
	private SituationEntiteOccurrenceDao situationEntiteOccurrenceDao;
	private NomenclatureDao nomenclatureDao;

	@Override
	public MutationDto saveInstallationEmployeMute(MutationDto mutationDto, Date dateInstallation, Integer affectation) {
		DossierEmployeDto dossierEmployeDto = mutationDto.getDossierEmployeDto();
		beforeSaveDossierEmploye(dossierEmployeDto);
		Mutation mutation = mapper.map(mutationDto, Mutation.class);
		DossierEmploye dossierEmploye = mapper.map(dossierEmployeDto, DossierEmploye.class);
		 dossierEmploye.setDateInstallation(dateInstallation);
		RefStructure refStructure = new RefStructure();
		refStructure.setId(affectation.intValue());
		dossierEmploye.setRefStructure(refStructure);
		dossierEmploye.setRefEtablissement(mutation.getEtabDemande());
		dossierEmployeDao.merge(dossierEmploye);

		SituationEntite situationEntite = situationEntiteDao.findByCodeSituationByCodeEntite(
				UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE, UtilConstants.SITUATION_INSTALLEE_CODE);
		mutation.setSituationEntite(situationEntite);

		mutation = mutationDao.save(mutation);

		persistSituationOccurence(mutation);
		return mapper.map(mutation, MutationDto.class);
	}

	private void persistSituationOccurence(Mutation mutation) {
		SituationEntiteOccurrence occurence = new SituationEntiteOccurrence();
		occurence.setDateSituation(new Date());
		occurence.setIdOccurrence(mutation.getId());
		occurence.setSituationEntite(mutation.getSituationEntite());
		situationEntiteOccurrenceDao.persist(occurence);
	}

	private void beforeSaveDossierEmploye(DossierEmployeDto dossierEmployeDto) {
		if (dossierEmployeDto.getNomenclatureByTypeCompteDto().getId() == null
				|| dossierEmployeDto.getNomenclatureByTypeCompteDto().getId() == 0) {
			dossierEmployeDto.setNomenclatureByTypeCompteDto(null);
		}
		if (dossierEmployeDto.getNomenclatureByTypePermisDto().getId() == null
				|| dossierEmployeDto.getNomenclatureByTypePermisDto().getId() == 0) {
			dossierEmployeDto.setNomenclatureByTypePermisDto(null);
		}
		if (dossierEmployeDto.getRefStructureDto().getId() == null
				|| dossierEmployeDto.getRefStructureDto().getId() == 0) {
			dossierEmployeDto.setRefStructureDto(null);
		}
		List<EnfantDto> listEnfants = dossierEmployeDto.getEnfantDtos();

		if (listEnfants != null) {

			List<EnfantDto> enfantDtos = new ArrayList<EnfantDto>();
			for (EnfantDto enfantDto : listEnfants) {
				if (enfantDto.getPrenomLatin() != null) {
					if (enfantDto.getNomenclatureByLienFiliationDto().getId() == null
							|| enfantDto.getNomenclatureByLienFiliationDto().getId() == 0) {
						enfantDto.setNomenclatureByLienFiliationDto(null);
					}
					enfantDtos.add(enfantDto);
				}

			}
			dossierEmployeDto.setEnfantDtos(enfantDtos);
		}
		List<DistinctionDto> listDistinctions = dossierEmployeDto.getDistinctionDtos();
		if (listDistinctions != null) {
			List<DistinctionDto> distinctionDtos = new ArrayList<DistinctionDto>();
			for (DistinctionDto distinctionDto : listDistinctions) {
				if (distinctionDto.getNomenclatureByDistinctionDto().getId() != null) {
					distinctionDtos.add(distinctionDto);
				}
			}
			dossierEmployeDto.setDistinctionDtos(distinctionDtos);
		}
		List<ConjointDto> listConjoints = dossierEmployeDto.getConjointDtos();
		if (listConjoints != null) {
			List<ConjointDto> conjointDtos = new ArrayList<ConjointDto>();
			for (ConjointDto conjointDto : listConjoints) {

				if (conjointDto.getRefIndividuDto().getId() != null && conjointDto.getRefIndividuDto().getId() != 0) {
					if (conjointDto.getNomenclatureByStatutDto().getId() == null
							|| conjointDto.getNomenclatureByStatutDto().getId() == 0) {
						conjointDto.setNomenclatureByStatutDto(null);
					}
					if (conjointDto.getRefStructureDto().getId() == null
							|| conjointDto.getRefStructureDto().getId() == 0) {
						conjointDto.setRefStructureDto(null);
					}
					conjointDtos.add(conjointDto);
				}

			}
			dossierEmployeDto.setConjointDtos(conjointDtos);
		}
		List<DiplomeEmployeDto> listDiplomes = dossierEmployeDto.getDiplomeEmployeDtos();
		if (listDiplomes != null) {
			List<DiplomeEmployeDto> diplomeEmployeDtos = new ArrayList<DiplomeEmployeDto>();
			for (DiplomeEmployeDto diplomeDto : listDiplomes) {

				if (diplomeDto.getAnneeObtention() != null && diplomeDto.getAnneeObtention() != 0) {
					if (diplomeDto.getNomenclatureByTypeDiplomeDto().getId() == null
							|| diplomeDto.getNomenclatureByTypeDiplomeDto().getId() == 0) {
						diplomeDto.setNomenclatureByTypeDiplomeDto(null);
					}
					if (diplomeDto.getRefEtablissementDto().getId() == null
							|| diplomeDto.getRefEtablissementDto().getId() == 0) {
						diplomeDto.setRefEtablissementDto(null);
					}
					diplomeEmployeDtos.add(diplomeDto);
				}

			}
			dossierEmployeDto.setDiplomeEmployeDtos(diplomeEmployeDtos);
		}
	}

	@Override
	protected CommonDao<Mutation, Integer> getDao() {
		return mutationDao;
	}

	/**
	 * @return the mutationDao
	 */
	public MutationDao getMutationDao() {
		return mutationDao;
	}

	/**
	 * @param mutationDao
	 *            to set
	 */
	@Autowired
	public void setMutationDao(MutationDao mutationDao) {
		this.mutationDao = mutationDao;
	}

	public DossierEmployeDao getDossierEmployeDao() {
		return dossierEmployeDao;
	}

	@Autowired
	public void setDossierEmployeDao(DossierEmployeDao dossierEmployeDao) {
		this.dossierEmployeDao = dossierEmployeDao;
	}

	public SituationEntiteDao getSituationEntiteDao() {
		return situationEntiteDao;
	}

	@Autowired
	public void setSituationEntiteDao(SituationEntiteDao situationEntiteDao) {
		this.situationEntiteDao = situationEntiteDao;
	}

	public SituationEntiteOccurrenceDao getSituationEntiteOccurrenceDao() {
		return situationEntiteOccurrenceDao;
	}

	@Autowired
	public void setSituationEntiteOccurrenceDao(SituationEntiteOccurrenceDao situationEntiteOccurrenceDao) {
		this.situationEntiteOccurrenceDao = situationEntiteOccurrenceDao;
	}

	public NomenclatureDao getNomenclatureDao() {
		return nomenclatureDao;
	}

	@Autowired
	public void setNomenclatureDao(NomenclatureDao nomenclatureDao) {
		this.nomenclatureDao = nomenclatureDao;
	}

	@Override
	public List<MutationDto> findAllDemandesMutation(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.FALSE);
		return findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllDemandesMutation(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.FALSE);
		return countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<MutationDto> findAllDemandesSoumisePourValidation(
			int etablissmentId, SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterSoumisePourValidation(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.FALSE);
		return findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllDemandesSoumisePourValidation(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterSoumisePourValidation(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.FALSE);
		return countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<MutationDto> findAllDecisionsMutation(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.TRUE);
		return findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllDecisionsMutation(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		applyFilterCree(searchMode);
		applyFilterNecessiteService(searchMode, Boolean.TRUE);
		return countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<MutationDto> findAllMutationsTraites(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementDemande(searchMode, etablissmentId);
		applyFilterTraitee(searchMode);
		return findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllMutationsTraites(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementDemande(searchMode, etablissmentId);
		applyFilterTraitee(searchMode);
		return countByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public List<MutationDto> findAllMutations(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		return findAllByKeyWord(searchKeyWords, searchMode);
	}

	@Override
	public int countAllMutations(int etablissmentId,
			SearchMode searchMode, String searchKeyWords) {
		applyFilterEtablissementOrigine(searchMode, etablissmentId);
		return countByKeyWord(searchKeyWords, searchMode);
	}
	
	private void applyFilterEtablissementOrigine(SearchMode searchMode, int etablissmentId) {
		searchMode.addFilter(new Filter("etabOrigine.id", etablissmentId, FilterMode.EQUALS));
	}
	
	private void applyFilterEtablissementDemande(SearchMode searchMode, int etablissmentId) {
		searchMode.addFilter(new Filter("etabDemande.id", etablissmentId, FilterMode.EQUALS));
	}
	private void applyFilterCree(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE,
						UtilConstants.SITUATION_CREEE_CODE).getId(),
				FilterMode.EQUALS));
	}
	
	private void applyFilterNecessiteService(SearchMode searchMode,Boolean  necessiteService) {
		searchMode.addFilter(new Filter("necessiteService", necessiteService, FilterMode.EQUALS));
	}
	
	private void applyFilterSoumisePourValidation(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE,
						UtilConstants.SITUATION_SOUMISE_VALIDATION_CODE).getId(),
				FilterMode.EQUALS));
	}
	
	private void applyFilterTraitee(SearchMode searchMode) {
		searchMode.addFilter(new Filter("situationEntite.id",
				situationEntiteDao.findByCodeSituationByCodeEntite(
						UtilConstants.ENTITE_MUTATION_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE).getId(),
				FilterMode.EQUALS));
	}
}
