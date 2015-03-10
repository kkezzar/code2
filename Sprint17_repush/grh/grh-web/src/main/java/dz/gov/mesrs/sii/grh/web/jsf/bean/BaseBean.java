package dz.gov.mesrs.sii.grh.web.jsf.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.util.UtilDate;
import dz.gov.mesrs.sii.commons.web.jsf.bean.CommonBaseBean;
import dz.gov.mesrs.sii.grh.business.model.dto.AnneeGrhDto;
import dz.gov.mesrs.sii.grh.business.model.dto.BesoinRecrutementDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CatalogueFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CategorieProfessionnelleDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CorpsDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CycleFormationDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DetailRecrutementDto;
import dz.gov.mesrs.sii.grh.business.model.dto.FormationCatalogueDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GradeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.GrilleIndiciaireDto;
import dz.gov.mesrs.sii.grh.business.model.dto.PosteSuperieureDto;
import dz.gov.mesrs.sii.grh.business.model.dto.ProgrammeFormationGrhDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.locator.ServiceLocatorBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author Salem
 * @version V1.0
 * @date 27-10-2014
 * @description classe abstraite pour les instanciations courantes
 * 
 */
public abstract class BaseBean extends CommonBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// the logger for this class
	protected final Log logger = LogFactory.getLog(this.getClass());
	// the service locator of the business services
	@ManagedProperty(value = "#{serviceLocatorBean}")
	protected ServiceLocatorBean serviceLocator;
	protected String MSG_BUNDLE_FIN_ACTIVITE = "finActiviteMgs";
	protected String MSG_BUNDLE_ABSENCE = "absenceMsgs";
	protected String MSG_BUNDLE_POSITION = "positionMgs";
	protected String MSG_BUNDLE_POSTES = "posteMsgs";
	protected String MSG_BUNDLE_EVALUATION = "evalEmpMsgs";
	
	/**
	 * @author Salem
	 * @version 1.0
	 * @description Constructeur du bean de base
	 */
	public BaseBean() {

	}

	/**
	 * @return the logger
	 */
	public Log getLogger() {
		return logger;
	}

	/**
	 * @return the serviceLocator
	 */
	public ServiceLocatorBean getServiceLocator() {
		return serviceLocator;
	}

	/**
	 * 
	 * @param serviceLocatorBean
	 */
	public void setServiceLocator(ServiceLocatorBean serviceLocatorBean) {
		this.serviceLocator = serviceLocatorBean;
	}

	protected List<SelectItem> findFiliereProferssionnelleList() {
		return findNomenclatureList(UtilConstant.NC_GRH_FILIERE_PRO_NAME);
	}

	protected List<SelectItem> findMotifMiseEnDispoList() {
		return findNomenclatureList(UtilConstant.NC_GRH_CHGT_POSITION_MOTIF);
	}

	protected List<SelectItem> findCorpsProfessionnelsList() {
		return findNomenclatureList(UtilConstant.NC_GRH_CORPS_PRO_NAME);
	}

	private List<SelectItem> findNomenclatureList(
			String ncFiliereProfessionnelleName) {
		List<NomenclatureDto> dtos = this.getServiceLocator()
				.getNomenclatureService()
				.findByName(ncFiliereProfessionnelleName);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (NomenclatureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		return results;
	}

	private List<SelectItem> findNomenclatureListWithExclusion(
			String ncFiliereProfessionnelleName, String... excludes) {
		List<NomenclatureDto> dtos = this.getServiceLocator()
				.getNomenclatureService()
				.findByName(ncFiliereProfessionnelleName);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (NomenclatureDto dto : dtos) {
				boolean add = true;
				for (String exclude : excludes) {
					if (exclude.equalsIgnoreCase(dto.getCode())) {
						add = false;
						break;
					}

				}
				if (add) {
					results.add(new SelectItem(dto.getId(), dto
							.getLibelleLongFr()));
				}

			}
		}
		return results;
	}

	public List<SelectItem> findGradeList() {
		return findNomenclatureList(UtilConstant.NC_GRH_GRADE_NAME);
	}

	public List<SelectItem> findGroupeList() {
		return findNomenclatureList(UtilConstant.NC_GRH_GROUPE_PRO_NAME);
	}

	public List<SelectItem> findTypeCategorieList() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_CATEGORIE_PRO_NAME);
	}

	public List<SelectItem> findTypeCessationList() {
		return findNomenclatureListWithExclusion(
				UtilConstant.NC_GRH_TYPE_CESSATION,
				UtilConstant.NC_GRH_TYPE_CESSATION_DEMISSION_VALUE,
				UtilConstant.NC_GRH_TYPE_CESSATION_RETRAITE_VALUE);
	}

	public List<SelectItem> findCategorieProCode() {
		List<NomenclatureDto> dtos = this.getServiceLocator()
				.getNomenclatureService()
				.findByName(UtilConstant.NC_GRH_CATEGORIE_PRO_NAME);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (NomenclatureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}
		return result;
	}

	protected List<SelectItem> findNcRubriqueList() {
		return findNomenclatureList(UtilConstant.NC_GRH_RUBRIQUE_NAME);

	}

	protected List<SelectItem> findNcTypeRubriqueList() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_RUBRIQUE_NAME);

	}

	protected List<SelectItem> findNcPriodeciteList() {
		return findNomenclatureList(UtilConstant.NC_GRH_PERIODECITE_NAME);

	}

	public List<SelectItem> findListeCivilite() {
		return findNomenclatureList(UtilConstant.NC_CIVILITE_NAME);
	}

	public List<SelectItem> findListeFonction() {
		return findNomenclatureList(UtilConstant.NC_FONCTION_NAME);
	}

	public List<SelectItem> findListeNationalite() {
		return findNomenclatureList(UtilConstant.NC_NATIONALITE_NAME);
	}

	public List<SelectItem> findListeSituationFamiliale() {
		return findNomenclatureList(UtilConstant.NC_SIT_FAMILLIALE_NAME);
	}

	public List<SelectItem> findListeSitSceNat() {
		return findNomenclatureList(UtilConstant.NC_SIT_SERVICE_NAT_NAME);
	}

	public List<SelectItem> findListeGroupeSanguin() {
		return findNomenclatureList(UtilConstant.NC_GROUPE_SANGUIN_NAME);
	}

	public List<SelectItem> findListeTypeCompte() {
		return findNomenclatureList(UtilConstant.NC_TYPE_COMPTE);
	}

	public List<SelectItem> findListeCategorie() {
		return findNomenclatureList(UtilConstant.NC_CATEGORIE);
	}

	public List<SelectItem> findListeFiliation() {
		return findNomenclatureList(UtilConstant.NC_GRH_FILIATION);
	}

	public List<SelectItem> findListeDistinction() {
		return findNomenclatureList(UtilConstant.NC_GRH_DISTINCTION);
	}

	public List<SelectItem> findListeNcStatut() {
		return findNomenclatureList(UtilConstant.NC_GRH_STATUT);
	}

	public List<SelectItem> findListeStructure() {
		List<RefStructureDto> dtos = this.getServiceLocator()
				.getRefStructureService().findAll();
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (RefStructureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto
						.getLlStructureLatin()));
			}
		}
		return results;
	}

	protected List<SelectItem> findPosteSuperieurTypeList() {
		return findNomenclatureList(UtilConstant.NC_GRH_POSTE_SUPERIEUR_NAME);

	}

	public List<SelectItem> findListeNcModeRecrutement() {
		return findNomenclatureList(UtilConstant.NC_GRH_MODE_RECRUTEMENT);
	}

	public List<SelectItem> findListeNcTypeRecrutement() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_RECRUTEMENT);
	}

	public List<SelectItem> findListeStructure(Integer etabId) {
		List<RefStructureDto> dtos = this.getServiceLocator()
				.getRefStructureService().findAll(etabId);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (RefStructureDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto
						.getLlStructureLatin()));
			}
		}
		return results;
	}

	public List<SelectItem> findListeCorps() {
		List<CorpsDto> dtos = this.getServiceLocator().getCorpsService()
				.findAll();
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (CorpsDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto
						.getNomenclatureByIdNomenclatureCorps()
						.getLibelleLongFr()));
			}
		}
		return result;
	}

	public List<SelectItem> findListeGrade(Integer corpsId) {
		GradeDto example = new GradeDto();
		example.setCorpsDto(new CorpsDto(corpsId, null));
		List<GradeDto> dtos = this.getServiceLocator().getGradeService()
				.findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (GradeDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getNomenclatureDto()
						.getLibelleLongFr()));
			}
		}
		return result;
	}

	public List<SelectItem> findListePosteSuperieur(PosteSuperieureDto example) {

		List<PosteSuperieureDto> dtos = this.getServiceLocator()
				.getPosteSuperieureService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (PosteSuperieureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto
						.getTypePosteSuperieurDto().getLibelleLongFr()));
			}
		}
		return result;
	}

	public List<SelectItem> findListeEtablissment() {
		List<RefEtablissementDto> dtos = this.getServiceLocator()
				.getRefEtablissementService().findAll();
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (RefEtablissementDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto
						.getLlEtablissementLatin()));
			}
		}
		return results;

	}

	public List<SelectItem> findListeNcTypeDiplomeGroupedByNiveau() {

		List<NomenclatureDto> niveauList = this.getServiceLocator()
				.getNomenclatureService()
				.findByName(UtilConstant.NC_FVE_NIVEAUX_DIPLOMES);
		List<SelectItem> results = null;
		if (niveauList != null) {
			results = new ArrayList<SelectItem>();
			for (NomenclatureDto niveauDto : niveauList) {
				SelectItemGroup niveauGroupe = new SelectItemGroup(
						niveauDto.getLibelleLongFr());
				List<NomenclatureDto> typeList = this
						.getServiceLocator()
						.getNomenclatureService()
						.findByReference(UtilConstant.NC_FVE_TYPES_DIPLOMES,
								niveauDto.getId());
				if (typeList != null) {
					List<SelectItem> typeItems = new ArrayList<SelectItem>();
					for (NomenclatureDto typeDto : typeList) {
						SelectItem typeItem = new SelectItem(typeDto.getId(),
								typeDto.getLibelleLongFr());
						typeItems.add(typeItem);
					}
					SelectItem[] typeItemsArray = new SelectItem[typeItems
							.size()];
					typeItemsArray = typeItems.toArray(typeItemsArray);
					niveauGroupe.setSelectItems(typeItemsArray);
				}
				results.add(niveauGroupe);
			}
		}
		return results;
	}

	public List<SelectItem> findListePosteRecrutement(
			BesoinRecrutementDto besoinRecrutementDto) {
		DetailRecrutementDto example = new DetailRecrutementDto();
		example.setBesoinRecrutementDto(besoinRecrutementDto);
		List<DetailRecrutementDto> dtos = this.getServiceLocator()
				.getDetailRecrutementService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (DetailRecrutementDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getReference()));
			}
		}
		return result;
	}

	public List<SelectItem> findListTypeStatut() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_STATUT);
	}

	public List<SelectItem> findListeNcDecisionStage() {
		return findNomenclatureList(UtilConstant.NC_GRH_DECISION_STAGE);
	}

	public List<SelectItem> findListeGrilleIndiciaire(Integer categorieId) {
		GrilleIndiciaireDto example = new GrilleIndiciaireDto();
		example.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto(
				categorieId));
		List<GrilleIndiciaireDto> dtos = this.getServiceLocator()
				.getGrilleIndiciaireService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (GrilleIndiciaireDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getEchlon()));
			}
		}
		return result;
	}

	public List<SelectItem> findListeGrilleIndiciaireByencientEchelon(
			Integer categorieId, Long encientIndice) {
		GrilleIndiciaireDto example = new GrilleIndiciaireDto();
		example.setCategorieProfessionnelleDto(new CategorieProfessionnelleDto(
				categorieId));
		List<GrilleIndiciaireDto> dtos = this.getServiceLocator()
				.getGrilleIndiciaireService().findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (GrilleIndiciaireDto dto : dtos) {
				if (dto.getIndice() > encientIndice)
					result.add(new SelectItem(dto.getId(), dto.getEchlon()));
			}
		}
		return result;
	}

	public List<SelectItem> findListeGradeSuperieurByencientGrade(
			GradeDto gradeActuel, Integer corpsId) {
		GradeDto example = new GradeDto();
		example.setCorpsDto(new CorpsDto(corpsId, null));
		List<GradeDto> dtos = this.getServiceLocator().getGradeService()
				.findAllByExample(example);
		List<SelectItem> result = new ArrayList<>();
		if (dtos != null) {
			for (GradeDto dto : dtos) {
				if ((gradeActuel.getCategorieProfessionnelleDto() != null)
						&& (gradeActuel.getCategorieProfessionnelleDto()
								.getIndiceMin() != null))
					if ((dto.getCategorieProfessionnelleDto() != null)
							&& (dto.getCategorieProfessionnelleDto()
									.getIndiceMin() != null))
						if (dto.getCategorieProfessionnelleDto().getIndiceMin()
								.longValue() > gradeActuel
								.getCategorieProfessionnelleDto()
								.getIndiceMin().longValue())
							result.add(new SelectItem(dto.getId(), dto
									.getNomenclatureDto().getLibelleLongFr()));

			}
		}
		return result;

	}

	public List<SelectItem> findTypeDemandeChgtPositionList() {
		return findNomenclatureListWithExclusion(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_SERVICE_NATIONAL_CODE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE);
	}

	public List<SelectItem> findTypeChgtPositionDroitList() {
		return findNomenclatureListWithExclusion(
				UtilConstant.NC_GRH_POSITION_EMPLOYE_VALUE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_ACTIVE_CODE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_FIN_ACTIVITE_CODE,
				UtilConstant.NC_GRH_POSITION_EMPLOYE_HORS_CADRE_CODE);
	}

	public AnneeGrhDto deduceCurrentYear() throws ParseException {

		int month = Calendar.getInstance().get(Calendar.MONTH);
		int year;
		if (month < 6) {
			year = Calendar.getInstance().get(Calendar.YEAR) - 1;
		} else {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}
		Calendar debut = UtilDate.convertToDateOnly(year + "-07-01", "GMT+1");
		Calendar fin = UtilDate.convertToDateOnly((year + 1) + "-06-30",
				"GMT+1");
		String libelle = "Juillet " + year + " / Juin " + (year + 1);

		return new AnneeGrhDto(year, libelle, debut.getTime(), fin.getTime());

	}

	protected List<SelectItem> findListeFrequence() {
		return findNomenclatureList(UtilConstant.NC_GRH_FREQUENCE_ABSENCE);
	}

	protected List<SelectItem> findListeTypeAbsence() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_ABSENCE);
	}

	public List<SelectItem> findListeTypeConge() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_CONGE);
	}

	public List<SelectItem> findListeNcAntecedents() {
		return findNomenclatureList(UtilConstant.NC_GRH_ANTECEDENT);
	}

	public List<SelectItem> findListeNcAllergies() {
		return findNomenclatureList(UtilConstant.NC_GRH_ALLERGIE);
	}

	public List<SelectItem> findListeNcPathologies() {
		return findNomenclatureList(UtilConstant.NC_GRH_PATHOLOGIE);
	}

	public List<SelectItem> findListeNcVaccins() {
		return findNomenclatureList(UtilConstant.NC_GRH_VACCIN);
	}

	public List<SelectItem> findListeNcMedicament() {
		return findNomenclatureList(UtilConstant.NC_GRH_MEDICAMENT);
	}

	public List<SelectItem> findListeNcExamenMedical() {
		return findNomenclatureList(UtilConstant.NC_GRH_EXAMEN_MEDICAL);
	}

	public List<SelectItem> getNiveauCompetenceList() {
		return findNomenclatureList(UtilConstant.NC_GRH_EMPLOYE_NIVEAU_COMPETENCE);
	}

	public List<SelectItem> getTypeConnaissanceList() {
		return findNomenclatureList(UtilConstant.NC_GRH_EMPLOYE_TYPE_CONNAISSANCE);
	}

	public List<SelectItem> getNiveauHabileteList() {
		return findNomenclatureList(UtilConstant.NC_GRH_EMPLOYE_NIVEAU_HABILETE);
	}

	public List<SelectItem> getTypeHabileteList() {
		return findNomenclatureList(UtilConstant.NC_GRH_EMPLOYE_TYPE_HABILETE);
	}

	public List<SelectItem> getNiveauQualificationList() {
		return findNomenclatureList(UtilConstant.NC_GRH_CATEGORIE_NIVEAU_QUALIFICATION);
	}

	public List<SelectItem> findListeNcThemeFormation() {
		return findNomenclatureList(UtilConstant.NC_GRH_THEME_FORMATION);
	}

	public List<SelectItem> findListeNcMethodeFormation() {
		return findNomenclatureList(UtilConstant.NC_GRH_METHODE_FORMATION);
	}

	public List<SelectItem> findListeNcTypeBesoinFormation() {
		return findNomenclatureList(UtilConstant.NC_GRH_TYPE_BESOIN_FORMATION);
	}

	public List<SelectItem> findStructureTypes(Integer idEtablissement) {
		List<NomenclatureDto> dtos = this.serviceLocator
				.getNomenclatureService().findByName(
						UtilConstant.NC_TYPE_STRUCTURE_NAME);

		List<SelectItem> result = new ArrayList<>();
		if (dtos != null && !dtos.isEmpty()) {
			for (NomenclatureDto dto : dtos) {
				result.add(new SelectItem(dto.getId(), dto.getLibelleLongFr()));
			}
		}

		return result;
	}

	public List<SelectItem> findListFormationGroupedByCatalogue() {
		List<CatalogueFormationDto> listCatalogues = serviceLocator
				.getCatalogueFormationService().findAll();
		List<SelectItem> listFormations = null;
		if (listCatalogues != null) {
			listFormations = new ArrayList<SelectItem>();
			for (CatalogueFormationDto catalogueDto : listCatalogues) {
				SelectItemGroup catalogue = new SelectItemGroup(
						catalogueDto.getObjet());
				List<FormationCatalogueDto> formationsList = catalogueDto
						.getFormationCatalogueDtos();

				if (formationsList != null) {
					List<SelectItem> items = new ArrayList<SelectItem>();

					for (FormationCatalogueDto formationCatalogueDto : formationsList) {
						SelectItem formation = new SelectItem(
								formationCatalogueDto.getId(),
								formationCatalogueDto.getIntitule());
						items.add(formation);
					}
					SelectItem[] itemsTable = new SelectItem[items.size()];
					 itemsTable = items.toArray(itemsTable);
					catalogue.setSelectItems(itemsTable);
				}
				listFormations.add(catalogue);
			}
		}
		return listFormations;

	}
	
	public List<SelectItem> findListGradeGroupedByCorps() {
		List<CorpsDto> listCorps = serviceLocator
				.getCorpsService().findAll();
		List<SelectItem> listGrades = null;
		if (listCorps != null) {
			listGrades = new ArrayList<SelectItem>();
			for (CorpsDto corpsDto : listCorps) {
				SelectItemGroup corps = new SelectItemGroup(
						corpsDto.getNomenclatureByIdNomenclatureCorps().getLibelleLongFr());
				List<GradeDto> gradesList = new ArrayList<GradeDto> (corpsDto.getGrades());
				if (gradesList != null) {
					List<SelectItem> items = new ArrayList<SelectItem>();
					for (GradeDto gradeDto : gradesList) {
						SelectItem grade = new SelectItem(
								gradeDto.getId(),
								gradeDto.getNomenclatureDto().getLibelleLongFr());
						items.add(grade);
					}
					SelectItem[] itemsTable = new SelectItem[items.size()];
					 itemsTable = items.toArray(itemsTable);
					corps.setSelectItems(itemsTable);
				}
				listGrades.add(corps);
			}
		}
		return listGrades;

	}
	
	protected List<SelectItem> findNiveauAppreciationList(){
		return findNomenclatureList(UtilConstant.NC_GRH_NIVEAU_EVALUATION);
	}
	
	public List<SelectItem> findListeProgrammeFormationGrh() {
		List<ProgrammeFormationGrhDto> dtos = serviceLocator.getProgrammeFormationGrhService().findAll();
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (ProgrammeFormationGrhDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto.getIntitule()));
			}
		}
		return results;
	}
	
	
	public List<SelectItem> findListCycleFormationGroupedByProgramme() {
		List<ProgrammeFormationGrhDto> listProgrammeFormationGrhs = serviceLocator.getProgrammeFormationGrhService().findAll();
		List<SelectItem> listCycleFormations = null;
		if (listProgrammeFormationGrhs != null) {
			listCycleFormations = new ArrayList<SelectItem>();
			for (ProgrammeFormationGrhDto programmeDto : listProgrammeFormationGrhs) {
				SelectItemGroup programme = new SelectItemGroup(
						programmeDto.getIntitule());
				List<CycleFormationDto> cyclesList = new ArrayList<CycleFormationDto> (programmeDto.getCycleFormationDtos());
				if (cyclesList != null) {
					List<SelectItem> items = new ArrayList<SelectItem>();
					for (CycleFormationDto cycleDto : cyclesList) {
						if(cycleDto.getSituationEntiteDto().getCodeSituation().equals(UtilConstants.SITUATION_CREEE_CODE)){
						SelectItem cycle = new SelectItem(
								cycleDto.getId(),
								cycleDto.getIntitule());
						items.add(cycle);
						}
					}
					SelectItem[] itemsTable = new SelectItem[items.size()];
					 itemsTable = items.toArray(itemsTable);
					 programme.setSelectItems(itemsTable);
				}
				listCycleFormations.add(programme);
			}
		}
		return listCycleFormations;

	}
	
	
	public List<SelectItem> findListeLieu(Integer etabId) {
		List<RefLieuDto> dtos = this.getServiceLocator()
				.getRefLieuService().findAll(etabId);
		List<SelectItem> results = new ArrayList<>();
		if (dtos != null) {
			for (RefLieuDto dto : dtos) {
				results.add(new SelectItem(dto.getId(), dto
						.getDesignation()));
			}
		}
		return results;
	}
	
}
