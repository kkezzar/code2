package dz.gov.mesrs.sii.grh.web.jsf.bean.dossieremploye;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import dz.gov.mesrs.sii.commons.business.util.FileUtility;
import dz.gov.mesrs.sii.commons.business.util.UtilConstants;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.Filter;
import dz.gov.mesrs.sii.commons.data.dao.SearchMode.FilterMode;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.SearchModeMapper;
import dz.gov.mesrs.sii.grh.business.model.dto.AbsenceDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CarriereDto;
import dz.gov.mesrs.sii.grh.business.model.dto.ChangementPositionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.CongeEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.ConjointDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DiplomeEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DistinctionDto;
import dz.gov.mesrs.sii.grh.business.model.dto.DossierEmployeDto;
import dz.gov.mesrs.sii.grh.business.model.dto.EnfantDto;
import dz.gov.mesrs.sii.grh.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.grh.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

@ManagedBean(name = "dossierEmployeBean")
@ViewScoped
/**
 * 
 * @author BELDI Jamel  on : 16 nov. 2014  14:51:27
 */
public class DossierEmployeBean extends BaseBean {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1L;
	private LazyDataModel<RefIndividuDto> individuModel;
	private LazyDataModel<DossierEmployeDto> dossierEmployeModel;
	private DossierEmployeDto dossierEmployeDto;
	private RefIndividuDto individuSearchDto;
	private DossierEmployeDto employeSearchDto;
	private List<SelectItem> listeGroupeSanguin;
	private List<SelectItem> listeSitSceNat;
	private List<SelectItem> listeSituationFamiliale;
	private List<SelectItem> listeNationalite;
	private List<SelectItem> listeFonction;
	private List<SelectItem> listeCivilite;
	private List<SelectItem> listeTypeCompte;
	private List<SelectItem> listeCategorie;
	private List<SelectItem> listeFiliation;
	private List<SelectItem> listeDistinction;
	private List<SelectItem> listeStatut;
	private List<SelectItem> listeStructure;
	private List<SelectItem> listeTypeDiplome;
	private List<SelectItem> listeEtablissements;
	private List<EnfantDto> listEnfants;
	private Integer selectedEnfantIndex;
	private List<DistinctionDto> listDistinctions;
	private Integer selectedDistinctionIndex;
	private List<ConjointDto> listConjoints;
	private Integer selectedConjointIndex;
	private int nbreConjointAutorise;
	private boolean addConjointDisabled;
	private boolean married;
	private List<DiplomeEmployeDto> listDiplomes;
	private Integer selectedDiplomeIndex;
	private List<CarriereDto> listCarrieres;
	private List<ChangementPositionDto> listPositions;
	private ChangementPositionDto positionDto;
	private List<CongeEmployeDto> listConges;
	private List<AbsenceDto> listAbsences;

	public DossierEmployeBean() {

	}

	@PostConstruct
	public void init() {
		employeSearchDto = new DossierEmployeDto();
		employeSearchDto.setRefEtablissementDto(sessionBean
				.getRefEtablissementDto());
		onSearchDossierEmploye();
		initSelectItemLists();
	}

	private void initDetail() {
		dossierEmployeDto = null;
		individuSearchDto = new RefIndividuDto();
		positionDto = null;
	}

	private void initSelectItemLists() {
		listeCivilite = findListeCivilite();
		listeGroupeSanguin = findListeGroupeSanguin();
		listeSitSceNat = findListeSitSceNat();
		listeSituationFamiliale = findListeSituationFamiliale();
		listeNationalite = findListeNationalite();
		listeFonction = findListeFonction();
		listeTypeCompte = findListeTypeCompte();
		listeCategorie = findListeCategorie();
		listeFiliation = findListeFiliation();
		listeDistinction = findListeDistinction();
		listeStatut = findListeNcStatut();
		listeStructure = findListeStructure();
		listeTypeDiplome = findListeNcTypeDiplomeGroupedByNiveau();
		listeEtablissements = findListeEtablissment();
	}

	public void onSearchDossierEmploye() {
		loaddossierEmployeModel();
		initDetail();
	}

	private void loaddossierEmployeModel() {
		dossierEmployeModel = new LazyDataModel<DossierEmployeDto>() {
			private static final long serialVersionUID = -3025574151722364485L;

			@Override
			public Object getRowKey(DossierEmployeDto dossierEmployeDto) {
				return dossierEmployeDto.getId();
			}

			@Override
			public List<DossierEmployeDto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				SearchMode searchMode =new SearchMode();
				dossierEmployeModel.setRowCount(serviceLocator
						.getDossierEmployeService().countAllByExample(
								employeSearchDto, searchMode));
				searchMode = SearchModeMapper.map(first, pageSize,
						sortField, sortOrder);
				List<DossierEmployeDto> dtos = serviceLocator
						.getDossierEmployeService().findAllByExample(
								employeSearchDto, searchMode);
				return dtos;
			}
		};

	}

	public void onRowSelect(SelectEvent event) throws Exception {
		dossierEmployeDto = (DossierEmployeDto) event.getObject();
		listEnfants = dossierEmployeDto.getEnfantDtos();
		listDistinctions = dossierEmployeDto.getDistinctionDtos();
		listConjoints = dossierEmployeDto.getConjointDtos();
		listDiplomes = dossierEmployeDto.getDiplomeEmployeDtos();
		listCarrieres = dossierEmployeDto.getCarriereDtos();
		listPositions = serviceLocator.getChangementPositionService()
				.findHistorique(dossierEmployeDto.getId());
		listAbsences = serviceLocator.getAbsenceService().findAllAbsencesEmploye(dossierEmployeDto.getId());
		positionDto = null;
		findConges();
		

		if (dossierEmployeDto.getRefIndividuDto().getCiviliteCode() == null) {
			throw new Exception(
					"Civilité est Null! Veuillez renseigner la civilité de "
							+ dossierEmployeDto.getRefIndividuDto()
									.getNomLatin()
							+ " "
							+ dossierEmployeDto.getRefIndividuDto()
									.getPrenomLatin());
		} else if (dossierEmployeDto.getRefIndividuDto().getCiviliteCode()
				.equals(UtilConstant.CIVILITE_MR_CODE)) {
			individuSearchDto.setCiviliteCode(UtilConstant.CIVILITE_MME_CODE);
			nbreConjointAutorise = 4;

		} else if (dossierEmployeDto.getRefIndividuDto().getCiviliteCode()
				.equals(UtilConstant.CIVILITE_MME_CODE)) {
			individuSearchDto.setCiviliteCode(UtilConstant.CIVILITE_MR_CODE);
			nbreConjointAutorise = 1;
		}
		if (listConjoints != null && !listConjoints.isEmpty()
				&& listConjoints.size() == nbreConjointAutorise) {
			addConjointDisabled = true;
		}
		if (dossierEmployeDto.getRefIndividuDto().getSituationFamilialeCode() != null
				&& dossierEmployeDto.getRefIndividuDto()
						.getSituationFamilialeCode()
						.equals(UtilConstant.SITUATION_MARIE_CODE)) {
			married = true;
		} else {
			married = false;
		}
	}

	public void onSave() {
		beforeSave();
		dossierEmployeDto = this.serviceLocator.getDossierEmployeService()
				.save(dossierEmployeDto);
		afterSave();
		loaddossierEmployeModel();
		CommonMessagesUtils.showSuccessSaveMessage();
	}

	public void addEnfant() {
		EnfantDto enfantDto = new EnfantDto();
		if (this.listEnfants == null) {
			this.listEnfants = new ArrayList<EnfantDto>();
		}
		enfantDto.setDossierEmployeDto(this.dossierEmployeDto);
		this.listEnfants.add(enfantDto);
	}

	public void removeEnfant() {
		EnfantDto enfantDto = this.listEnfants.get(selectedEnfantIndex);
		this.listEnfants.remove(enfantDto);

	}

	public void addDistinction() {
		DistinctionDto distinctionDto = new DistinctionDto();
		if (this.listDistinctions == null) {
			this.listDistinctions = new ArrayList<DistinctionDto>();
		}
		distinctionDto.setDossierEmployeDto(this.dossierEmployeDto);
		this.listDistinctions.add(distinctionDto);
	}

	public void removeDistinction() {
		DistinctionDto distinctionDto = this.listDistinctions
				.get(selectedDistinctionIndex);
		this.listDistinctions.remove(distinctionDto);

	}

	public void onSearchIndividu() {
		try {

			individuModel = new LazyDataModel<RefIndividuDto>() {
				private static final long serialVersionUID = -3025574151722364485L;

				@Override
				public Object getRowKey(RefIndividuDto refIndividuDto) {
					return refIndividuDto.getId();
				}

				@Override
				public List<RefIndividuDto> load(int first, int pageSize,
						String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					Boolean descending = null;
					if (sortOrder != null) {
						if (sortOrder.equals(SortOrder.DESCENDING)) {
							descending = Boolean.TRUE;
						} else {
							descending = Boolean.FALSE;
						}

					}

					individuModel.setRowCount(serviceLocator
							.getRefIndividuService().countByExample(
									individuSearchDto));
					List<RefIndividuDto> dtos = serviceLocator
							.getRefIndividuService().findByExample(
									individuSearchDto, sortField, pageSize,
									first, descending);
					return dtos;
				}
			};

		} catch (Exception e) {

		}
	}

	public void onIndividuSelect(SelectEvent event) {
		try {
			ConjointDto conjointDto = new ConjointDto();
			conjointDto.setRefIndividuDto((RefIndividuDto) event.getObject());
			addConjoint(conjointDto);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void addConjoint(ConjointDto conjointDto) {
		if (this.listConjoints == null) {
			this.listConjoints = new ArrayList<ConjointDto>();
		}
		conjointDto.setDossierEmployeDto(this.dossierEmployeDto);
		this.listConjoints.add(conjointDto);
		if (this.listConjoints != null && !this.listConjoints.isEmpty()
				&& this.listConjoints.size() == nbreConjointAutorise) {
			addConjointDisabled = true;
		}
	}

	public void removeConjoint() {
		ConjointDto conjointDto = this.listConjoints.get(selectedConjointIndex);
		this.listConjoints.remove(conjointDto);
		addConjointDisabled = false;
	}

	public void addDiplome() {
		DiplomeEmployeDto diplomeEmployeDto = new DiplomeEmployeDto();
		if (listDiplomes == null) {
			this.listDiplomes = new ArrayList<DiplomeEmployeDto>();
		}
		diplomeEmployeDto.setDossierEmployeDto(dossierEmployeDto);
		this.listDiplomes.add(diplomeEmployeDto);

	}

	public void removeDiplome() {
		DiplomeEmployeDto diplomeDto = this.listDiplomes
				.get(selectedDiplomeIndex);
		this.listDiplomes.remove(diplomeDto);

	}

	public void onPositionSelected(SelectEvent event) {
		positionDto = (ChangementPositionDto) event.getObject();
	}

	private void beforeSave() {
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

		if (listEnfants != null) {
			List<EnfantDto> enfantDtos = new ArrayList<EnfantDto>();
			for (EnfantDto enfantDto : listEnfants) {
				if (enfantDto.getPrenomLatin() != null) {
					if (enfantDto.getNomenclatureByLienFiliationDto().getId() == null
							|| enfantDto.getNomenclatureByLienFiliationDto()
									.getId() == 0) {
						enfantDto.setNomenclatureByLienFiliationDto(null);
					}
					enfantDtos.add(enfantDto);
				}

			}
			dossierEmployeDto.setEnfantDtos(enfantDtos);
		}

		if (listDistinctions != null) {
			List<DistinctionDto> distinctionDtos = new ArrayList<DistinctionDto>();
			for (DistinctionDto distinctionDto : listDistinctions) {
				if (distinctionDto.getNomenclatureByDistinctionDto().getId() != null) {
					distinctionDtos.add(distinctionDto);
				}
			}
			dossierEmployeDto.setDistinctionDtos(distinctionDtos);
		}

		if (listConjoints != null) {
			List<ConjointDto> conjointDtos = new ArrayList<ConjointDto>();
			for (ConjointDto conjointDto : listConjoints) {

				if (conjointDto.getRefIndividuDto().getId() != null
						&& conjointDto.getRefIndividuDto().getId() != 0) {
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

		if (listDiplomes != null) {
			List<DiplomeEmployeDto> diplomeEmployeDtos = new ArrayList<DiplomeEmployeDto>();
			for (DiplomeEmployeDto diplomeDto : listDiplomes) {

				if (diplomeDto.getAnneeObtention() != null
						&& diplomeDto.getAnneeObtention() != 0) {
					if (diplomeDto.getNomenclatureByTypeDiplomeDto().getId() == null
							|| diplomeDto.getNomenclatureByTypeDiplomeDto()
									.getId() == 0) {
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

	private void afterSave() {
		listEnfants = dossierEmployeDto.getEnfantDtos();
		listDistinctions = dossierEmployeDto.getDistinctionDtos();
		listConjoints = dossierEmployeDto.getConjointDtos();
		listDiplomes = dossierEmployeDto.getDiplomeEmployeDtos();

	}

	private void findConges() {
		SearchMode searchMode = new SearchMode();
		searchMode.addFilter(new Filter("situationEntite.id", serviceLocator
				.getSituationService()
				.findBySituationEntiteByCodeSituation(
						UtilConstants.ENTITE_CONGE_EMPLOYE_CODE,
						UtilConstants.SITUATION_TRAITEE_CODE).getId(),
				FilterMode.EQUALS));
		searchMode.addFilter(new Filter("resultat", true, FilterMode.EQUALS));
		CongeEmployeDto example = new CongeEmployeDto(dossierEmployeDto);
		listConges = serviceLocator.getCongeEmployeService().findAllByExample(
				example, searchMode);
	}

	

	public void onHandleFileUpload(FileUploadEvent event) {
		try {
			File targetFolder = new File(sessionBean.getFolderPhoto());

			InputStream inputStream = event.getFile().getInputstream();

			// generer un nom de fichier unique
			String fileName = FileUtility.getUidFileName(event.getFile()
					.getFileName());

			OutputStream out = new FileOutputStream(new File(targetFolder,
					fileName));

			int read = 0;
			byte[] bytes = new byte[4096];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			inputStream.close();
			out.flush();
			out.close();

			dossierEmployeDto.setPhoto(fileName);
			onSave();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public List<SelectItem> getListeGroupeSanguin() {
		return listeGroupeSanguin;
	}

	public void setListeGroupeSanguin(List<SelectItem> listeGroupeSanguin) {
		this.listeGroupeSanguin = listeGroupeSanguin;
	}

	public List<SelectItem> getListeSitSceNat() {
		return listeSitSceNat;
	}

	public void setListeSitSceNat(List<SelectItem> listeSitSceNat) {
		this.listeSitSceNat = listeSitSceNat;
	}

	public List<SelectItem> getListeSituationFamiliale() {
		return listeSituationFamiliale;
	}

	public void setListeSituationFamiliale(
			List<SelectItem> listeSituationFamiliale) {
		this.listeSituationFamiliale = listeSituationFamiliale;
	}

	public List<SelectItem> getListeNationalite() {
		return listeNationalite;
	}

	public void setListeNationalite(List<SelectItem> listeNationalite) {
		this.listeNationalite = listeNationalite;
	}

	public List<SelectItem> getListeFonction() {
		return listeFonction;
	}

	public void setListeFonction(List<SelectItem> listeFonction) {
		this.listeFonction = listeFonction;
	}

	public List<SelectItem> getListeCivilite() {
		return listeCivilite;
	}

	public void setListeCivilite(List<SelectItem> listeCivilite) {
		this.listeCivilite = listeCivilite;
	}

	public List<SelectItem> getListeTypeCompte() {
		return listeTypeCompte;
	}

	public void setListeTypeCompte(List<SelectItem> listeTypeCompte) {
		this.listeTypeCompte = listeTypeCompte;
	}

	public List<SelectItem> getListeCategorie() {
		return listeCategorie;
	}

	public void setListeCategorie(List<SelectItem> listeCategorie) {
		this.listeCategorie = listeCategorie;
	}

	public RefIndividuDto getIndividuSearchDto() {
		return individuSearchDto;
	}

	public void setIndividuSearchDto(RefIndividuDto individuSearchDto) {
		this.individuSearchDto = individuSearchDto;
	}

	public LazyDataModel<RefIndividuDto> getIndividuModel() {
		return individuModel;
	}

	public void setIndividuModel(LazyDataModel<RefIndividuDto> individuModel) {
		this.individuModel = individuModel;
	}

	public List<EnfantDto> getListEnfants() {
		return listEnfants;
	}

	public void setListEnfants(List<EnfantDto> listEnfants) {
		this.listEnfants = listEnfants;
	}

	public Integer getSelectedEnfantIndex() {
		return selectedEnfantIndex;
	}

	public void setSelectedEnfantIndex(Integer selectedEnfantIndex) {
		this.selectedEnfantIndex = selectedEnfantIndex;
	}

	public List<SelectItem> getListeFiliation() {
		return listeFiliation;
	}

	public void setListeFiliation(List<SelectItem> listeFiliation) {
		this.listeFiliation = listeFiliation;
	}

	public List<SelectItem> getListeDistinction() {
		return listeDistinction;
	}

	public void setListeDistinction(List<SelectItem> listeDistinction) {
		this.listeDistinction = listeDistinction;
	}

	public List<DistinctionDto> getListDistinctions() {
		return listDistinctions;
	}

	public void setListDistinctions(List<DistinctionDto> listDistinctions) {
		this.listDistinctions = listDistinctions;
	}

	public Integer getSelectedDistinctionIndex() {
		return selectedDistinctionIndex;
	}

	public void setSelectedDistinctionIndex(Integer selectedDistinctionIndex) {
		this.selectedDistinctionIndex = selectedDistinctionIndex;
	}

	public List<SelectItem> getListeStatut() {
		return listeStatut;
	}

	public void setListeStatut(List<SelectItem> listeStatut) {
		this.listeStatut = listeStatut;
	}

	public List<SelectItem> getListeStructure() {
		return listeStructure;
	}

	public void setListeStructure(List<SelectItem> listeStructure) {
		this.listeStructure = listeStructure;
	}

	public List<ConjointDto> getListConjoints() {
		return listConjoints;
	}

	public void setListConjoints(List<ConjointDto> listConjoints) {
		this.listConjoints = listConjoints;
	}

	public Integer getSelectedConjointIndex() {
		return selectedConjointIndex;
	}

	public void setSelectedConjointIndex(Integer selectedConjointIndex) {
		this.selectedConjointIndex = selectedConjointIndex;
	}

	public int getNbreConjointAutorise() {
		return nbreConjointAutorise;
	}

	public void setNbreConjointAutorise(int nbreConjointAutorise) {
		this.nbreConjointAutorise = nbreConjointAutorise;
	}

	public boolean isAddConjointDisabled() {
		return addConjointDisabled;
	}

	public void setAddConjointDisabled(boolean addConjointDisabled) {
		this.addConjointDisabled = addConjointDisabled;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public DossierEmployeDto getEmployeSearchDto() {
		return employeSearchDto;
	}

	public void setEmployeSearchDto(DossierEmployeDto employeSearchDto) {
		this.employeSearchDto = employeSearchDto;
	}

	public LazyDataModel<DossierEmployeDto> getDossierEmployeModel() {
		return dossierEmployeModel;
	}

	public void setDossierEmployeModel(
			LazyDataModel<DossierEmployeDto> dossierEmployeModel) {
		this.dossierEmployeModel = dossierEmployeModel;
	}

	public List<DiplomeEmployeDto> getListDiplomes() {
		return listDiplomes;
	}

	public void setListDiplomes(List<DiplomeEmployeDto> listDiplomes) {
		this.listDiplomes = listDiplomes;
	}

	public Integer getSelectedDiplomeIndex() {
		return selectedDiplomeIndex;
	}

	public void setSelectedDiplomeIndex(Integer selectedDiplomeIndex) {
		this.selectedDiplomeIndex = selectedDiplomeIndex;
	}

	public List<SelectItem> getListeTypeDiplome() {
		return listeTypeDiplome;
	}

	public void setListeTypeDiplome(List<SelectItem> listeTypeDiplome) {
		this.listeTypeDiplome = listeTypeDiplome;
	}

	public List<SelectItem> getListeEtablissements() {
		return listeEtablissements;
	}

	public void setListeEtablissements(List<SelectItem> listeEtablissements) {
		this.listeEtablissements = listeEtablissements;
	}

	public List<CarriereDto> getListCarrieres() {
		return listCarrieres;
	}

	public void setListCarrieres(List<CarriereDto> listCarrieres) {
		this.listCarrieres = listCarrieres;
	}

	public List<ChangementPositionDto> getListPositions() {
		return listPositions;
	}

	public ChangementPositionDto getPositionDto() {
		return positionDto;
	}


	public List<CongeEmployeDto> getListConges() {
		return listConges;
	}

	public void setListConges(List<CongeEmployeDto> listConges) {
		this.listConges = listConges;
	}

	public List<AbsenceDto> getListAbsences() {
		return listAbsences;
	}

	public void setListAbsences(List<AbsenceDto> listAbsences) {
		this.listAbsences = listAbsences;
	}


}
