/**
 * [dz.gov.mesrs.sii.recherche.web.jsf.bean.structure.RechercheStructureBB.java] 
 * @author rlaib on : 16 d�c. 2014  16:06:14
 */
package dz.gov.mesrs.sii.recherche.web.jsf.bean.structure;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.commons.web.util.CommonMessagesUtils;
import dz.gov.mesrs.sii.recherche.business.model.dto.GroupeDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.PartenaireDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto;
import dz.gov.mesrs.sii.recherche.business.model.dto.ThemeDto;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.BaseBean;
import dz.gov.mesrs.sii.recherche.web.jsf.bean.RechercheConstantBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * @author rlaib  on : 16 d�c. 2014  16:06:14
 */
@ManagedBean(name = "structureBB")
@ViewScoped
public class RechercheStructureBB extends BaseBean {
	
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 dec. 2014  16:08:20
	 */
	private static final long serialVersionUID = 1L;

	// ===================================================================  
	// Constructors
	// ===================================================================

	public RechercheStructureBB() {
	}
	
	@PostConstruct
	public void init() {
		
			initListeStructuresRecherche();
			listTypesStructuresReferentiel = findStructureTypes(sessionBean.getIdEtablissement());
			if(listTypesStructuresReferentiel != null && !listTypesStructuresReferentiel.isEmpty()) {
				selectedTypeStructureReferentielId = Integer.parseInt(listTypesStructuresReferentiel.get(0).getValue().toString());
			}
	}

	// ===================================================================  
	// Properties Model
	// ===================================================================
	private List<StructureDto> listStructures;
	private StructureDto selectedStructureRecherche;
	private Integer selectedStructureId;
	private List<SelectItem> listTypesStructuresReferentiel;
	private Integer selectedTypeStructureReferentielId;
	private List<RefStructureDto>  listStructuresReferentiel;
	private List<RefGroupeDto>  listGroupesReferentiel;
	private List<RefAffectationDto> listMembresGroupe;
	private RefGroupeDto  selectedGroupeReferentiel;
	private RefStructureDto selectedStructureReferentiel;
	private boolean canShowStructureDetail = false;
	private Integer selectedGroupeReferentielId;
	private List<RefEquipementDto> listStructureRechercheEquipements;
	private List<GroupeDto> listStructureRechercheGroupes;
	private List<ThemeDto> listStructureRechercheThemes;
	private ThemeDto selectedStructureRechercheTheme;
	private List<SelectItem> listNomenclatureThemes;
	private Integer selectedNomenclatureThemeId;
	private List<SelectItem> listStructureRechercheGroupesItems;
	private Long selectedStructureRechercheGroupeId;
	private GroupeDto selectedStructureRechercheGroupe;
	private String refGroupSearchKeyWord;
	private Long selectedStructureRechercheThemeGroupeId;
	private Long selectedStructureRechercheThemeId;
	private boolean toEditThemeDialog = false;
	private List<RefPartenaireDto> listPartnairesSearch;
	private List<RefPartenaireDto> listPartnairesSearchSelection;
	private String refPartnerSearchKeyWord;
	private List<PartenaireDto> listStructureRecherchePartenaires;
	private Long selectedStructureRecherchePartenaireId;
	private boolean canAddPartners = false;
	
	/**
	 * [RechercheStructureBB.listMembresGroupe] Getter 
	 * @author rlaib on : 4 janv. 2015  08:51:50
	 * @return the listMembresGroupe
	 */
	public List<RefAffectationDto> getListMembresGroupe() {
		return listMembresGroupe;
	}
	

	/**
	 * [RechercheStructureBB.listMembresGroupe] Setter 
	 * @author rlaib on : 4 janv. 2015  08:51:50
	 * @param listMembresGroupe the listMembresGroupe to set
	 */
	public void setListMembresGroupe(List<RefAffectationDto> listMembresGroupe) {
		this.listMembresGroupe = listMembresGroupe;
	}

	/**
	 * [RechercheStructureBB.canAddPartners] Getter 
	 * @author Rafik on : 23 déc. 2014  20:11:18
	 * @return the canAddPartners
	 */
	public boolean isCanAddPartners() {
		return canAddPartners;
	}

	/**
	 * [RechercheStructureBB.canAddPartners] Setter 
	 * @author Rafik on : 23 déc. 2014  20:11:18
	 * @param canAddPartners the canAddPartners to set
	 */
	public void setCanAddPartners(boolean canAddPartners) {
		this.canAddPartners = canAddPartners;
	}

	/**
	 * [RechercheStructureBB.listStructureRecherchePartenaires] Getter 
	 * @author rlaib on : 23 d�c. 2014  16:12:52
	 * @return the listStructureRecherchePartenaires
	 */
	public List<PartenaireDto> getListStructureRecherchePartenaires() {
		return listStructureRecherchePartenaires;
	}

	/**
	 * [RechercheStructureBB.listStructureRecherchePartenaires] Setter 
	 * @author rlaib on : 23 d�c. 2014  16:12:52
	 * @param listStructureRecherchePartenaires the listStructureRecherchePartenaires to set
	 */
	public void setListStructureRecherchePartenaires(
			List<PartenaireDto> listStructureRecherchePartenaires) {
		this.listStructureRecherchePartenaires = listStructureRecherchePartenaires;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRecherchePartenaireId] Getter 
	 * @author rlaib on : 23 d�c. 2014  16:12:52
	 * @return the selectedStructureRecherchePartenaireId
	 */
	public Long getSelectedStructureRecherchePartenaireId() {
		return selectedStructureRecherchePartenaireId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRecherchePartenaireId] Setter 
	 * @author rlaib on : 23 d�c. 2014  16:12:52
	 * @param selectedStructureRecherchePartenaireId the selectedStructureRecherchePartenaireId to set
	 */
	public void setSelectedStructureRecherchePartenaireId(
			Long selectedStructureRecherchePartenaireId) {
		this.selectedStructureRecherchePartenaireId = selectedStructureRecherchePartenaireId;
	}

	/**
	 * [RechercheStructureBB.refPartnerSearchKeyWord] Getter 
	 * @author rlaib on : 23 d�c. 2014  16:08:46
	 * @return the refPartnerSearchKeyWord
	 */
	public String getRefPartnerSearchKeyWord() {
		return refPartnerSearchKeyWord;
	}

	/**
	 * [RechercheStructureBB.refPartnerSearchKeyWord] Setter 
	 * @author rlaib on : 23 d�c. 2014  16:08:46
	 * @param refPartnerSearchKeyWord the refPartnerSearchKeyWord to set
	 */
	public void setRefPartnerSearchKeyWord(String refPartnerSearchKeyWord) {
		this.refPartnerSearchKeyWord = refPartnerSearchKeyWord;
	}

	/**
	 * [RechercheStructureBB.listPartnairesSearch] Getter 
	 * @author rlaib on : 23 d�c. 2014  16:04:30
	 * @return the listPartnairesSearch
	 */
	public List<RefPartenaireDto> getListPartnairesSearch() {
		return listPartnairesSearch;
	}

	/**
	 * [RechercheStructureBB.listPartnairesSearch] Setter 
	 * @author rlaib on : 23 d�c. 2014  16:04:30
	 * @param listPartnairesSearch the listPartnairesSearch to set
	 */
	public void setListPartnairesSearch(List<RefPartenaireDto> listPartnairesSearch) {
		this.listPartnairesSearch = listPartnairesSearch;
	}

	/**
	 * [RechercheStructureBB.listPartnairesSearchSelection] Getter 
	 * @author rlaib on : 23 d�c. 2014  16:04:30
	 * @return the listPartnairesSearchSelection
	 */
	public List<RefPartenaireDto> getListPartnairesSearchSelection() {
		return listPartnairesSearchSelection;
	}

	/**
	 * [RechercheStructureBB.listPartnairesSearchSelection] Setter 
	 * @author rlaib on : 23 d�c. 2014  16:04:30
	 * @param listPartnairesSearchSelection the listPartnairesSearchSelection to set
	 */
	public void setListPartnairesSearchSelection(
			List<RefPartenaireDto> listPartnairesSearchSelection) {
		this.listPartnairesSearchSelection = listPartnairesSearchSelection;
	}

	/**
	 * [RechercheStructureBB.toEditThemeDialog] Getter 
	 * @author rlaib on : 22 d�c. 2014  14:05:23
	 * @return the toEditThemeDialog
	 */
	public boolean isToEditThemeDialog() {
		return toEditThemeDialog;
	}

	/**
	 * [RechercheStructureBB.toEditThemeDialog] Setter 
	 * @author rlaib on : 22 d�c. 2014  14:05:23
	 * @param toEditThemeDialog the toEditThemeDialog to set
	 */
	public void setToEditThemeDialog(boolean toEditThemeDialog) {
		this.toEditThemeDialog = toEditThemeDialog;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheTheme] Getter 
	 * @author rlaib on : 22 d�c. 2014  11:32:34
	 * @return the selectedStructureRechercheTheme
	 */
	public ThemeDto getSelectedStructureRechercheTheme() {
		return selectedStructureRechercheTheme;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheTheme] Setter 
	 * @author rlaib on : 22 d�c. 2014  11:32:34
	 * @param selectedStructureRechercheTheme the selectedStructureRechercheTheme to set
	 */
	public void setSelectedStructureRechercheTheme(
			ThemeDto selectedStructureRechercheTheme) {
		this.selectedStructureRechercheTheme = selectedStructureRechercheTheme;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheThemes] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @return the listStructureRechercheThemes
	 */
	public List<ThemeDto> getListStructureRechercheThemes() {
		return listStructureRechercheThemes;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheThemes] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @param listStructureRechercheThemes the listStructureRechercheThemes to set
	 */
	public void setListStructureRechercheThemes(
			List<ThemeDto> listStructureRechercheThemes) {
		this.listStructureRechercheThemes = listStructureRechercheThemes;
	}

	/**
	 * [RechercheStructureBB.listNomenclatureThemes] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @return the listNomenclatureThemes
	 */
	public List<SelectItem> getListNomenclatureThemes() {
		return listNomenclatureThemes;
	}

	/**
	 * [RechercheStructureBB.listNomenclatureThemes] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @param listNomenclatureThemes the listNomenclatureThemes to set
	 */
	public void setListNomenclatureThemes(List<SelectItem> listNomenclatureThemes) {
		this.listNomenclatureThemes = listNomenclatureThemes;
	}

	/**
	 * [RechercheStructureBB.selectedNomenclatureThemeId] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @return the selectedNomenclatureThemeId
	 */
	public Integer getSelectedNomenclatureThemeId() {
		return selectedNomenclatureThemeId;
	}

	/**
	 * [RechercheStructureBB.selectedNomenclatureThemeId] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @param selectedNomenclatureThemeId the selectedNomenclatureThemeId to set
	 */
	public void setSelectedNomenclatureThemeId(Integer selectedNomenclatureThemeId) {
		this.selectedNomenclatureThemeId = selectedNomenclatureThemeId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheThemeGroupeId] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @return the selectedStructureRechercheThemeGroupeId
	 */
	public Long getSelectedStructureRechercheThemeGroupeId() {
		return selectedStructureRechercheThemeGroupeId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheThemeGroupeId] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @param selectedStructureRechercheThemeGroupeId the selectedStructureRechercheThemeGroupeId to set
	 */
	public void setSelectedStructureRechercheThemeGroupeId(
			Long selectedStructureRechercheThemeGroupeId) {
		this.selectedStructureRechercheThemeGroupeId = selectedStructureRechercheThemeGroupeId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheThemeId] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @return the selectedStructureRechercheThemeId
	 */
	public Long getSelectedStructureRechercheThemeId() {
		return selectedStructureRechercheThemeId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheThemeId] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:39:45
	 * @param selectedStructureRechercheThemeId the selectedStructureRechercheThemeId to set
	 */
	public void setSelectedStructureRechercheThemeId(
			Long selectedStructureRechercheThemeId) {
		this.selectedStructureRechercheThemeId = selectedStructureRechercheThemeId;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheGroupesItems] Getter 
	 * @author rlaib on : 22 d�c. 2014  10:33:28
	 * @return the listStructureRechercheGroupesItems
	 */
	public List<SelectItem> getListStructureRechercheGroupesItems() {
		return listStructureRechercheGroupesItems;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheGroupesItems] Setter 
	 * @author rlaib on : 22 d�c. 2014  10:33:28
	 * @param listStructureRechercheGroupesItems the listStructureRechercheGroupesItems to set
	 */
	public void setListStructureRechercheGroupesItems(
			List<SelectItem> listStructureRechercheGroupesItems) {
		this.listStructureRechercheGroupesItems = listStructureRechercheGroupesItems;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheEquipements] Getter 
	 * @author rlaib on : 21 d�c. 2014  16:22:28
	 * @return the listStructureRechercheEquipements
	 */
	public List<RefEquipementDto> getListStructureRechercheEquipements() {
		return listStructureRechercheEquipements;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheEquipements] Setter 
	 * @author rlaib on : 21 d�c. 2014  16:22:28
	 * @param listStructureRechercheEquipements the listStructureRechercheEquipements to set
	 */
	public void setListStructureRechercheEquipements(
			List<RefEquipementDto> listStructureRechercheEquipements) {
		this.listStructureRechercheEquipements = listStructureRechercheEquipements;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheGroupeId] Getter 
	 * @author rlaib on : 21 d�c. 2014  15:19:46
	 * @return the selectedStructureRechercheGroupeId
	 */
	public Long getSelectedStructureRechercheGroupeId() {
		return selectedStructureRechercheGroupeId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheGroupeId] Setter 
	 * @author rlaib on : 21 d�c. 2014  15:19:46
	 * @param selectedStructureRechercheGroupeId the selectedStructureRechercheGroupeId to set
	 */
	public void setSelectedStructureRechercheGroupeId(
			Long selectedStructureRechercheGroupeId) {
		this.selectedStructureRechercheGroupeId = selectedStructureRechercheGroupeId;
	}

	/**
	 * [RechercheStructureBB.refGroupSearchKeyWord] Getter 
	 * @author Rafik on : 21 déc. 2014  12:11:02
	 * @return the refGroupSearchKeyWord
	 */
	public String getRefGroupSearchKeyWord() {
		return refGroupSearchKeyWord;
	}

	/**
	 * [RechercheStructureBB.refGroupSearchKeyWord] Setter 
	 * @author Rafik on : 21 déc. 2014  12:11:02
	 * @param refGroupSearchKeyWord the refGroupSearchKeyWord to set
	 */
	public void setRefGroupSearchKeyWord(String refGroupSearchKeyWord) {
		this.refGroupSearchKeyWord = refGroupSearchKeyWord;
	}

	/**
	 * [RechercheStructureBB.selectedGroupeReferentiel] Getter 
	 * @author rlaib on : 18 d�c. 2014  15:11:20
	 * @return the selectedGroupeReferentiel
	 */
	public RefGroupeDto getSelectedGroupeReferentiel() {
		return selectedGroupeReferentiel;
	}

	/**
	 * [RechercheStructureBB.selectedGroupeReferentiel] Setter 
	 * @author rlaib on : 18 d�c. 2014  15:11:20
	 * @param selectedGroupeReferentiel the selectedGroupeReferentiel to set
	 */
	public void setSelectedGroupeReferentiel(RefGroupeDto selectedGroupeReferentiel) {
		this.selectedGroupeReferentiel = selectedGroupeReferentiel;
	}

	/**
	 * [RechercheStructureBB.listGroupesReferentiel] Getter 
	 * @author rlaib on : 18 d�c. 2014  14:36:02
	 * @return the listGroupesReferentiel
	 */
	public List<RefGroupeDto> getListGroupesReferentiel() {
		return listGroupesReferentiel;
	}

	/**
	 * [RechercheStructureBB.listGroupesReferentiel] Setter 
	 * @author rlaib on : 18 d�c. 2014  14:36:02
	 * @param listGroupesReferentiel the listGroupesReferentiel to set
	 */
	public void setListGroupesReferentiel(List<RefGroupeDto> listGroupesReferentiel) {
		this.listGroupesReferentiel = listGroupesReferentiel;
	}

	/**
	 * [RechercheStructureBB.selectedGroupeReferentielId] Getter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @return the selectedGroupeReferentielId
	 */
	public Integer getSelectedGroupeReferentielId() {
		return selectedGroupeReferentielId;
	}

	/**
	 * [RechercheStructureBB.selectedGroupeReferentielId] Setter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @param selectedGroupeReferentielId the selectedGroupeReferentielId to set
	 */
	public void setSelectedGroupeReferentielId(Integer selectedGroupeReferentielId) {
		this.selectedGroupeReferentielId = selectedGroupeReferentielId;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheGroupes] Getter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @return the listStructureRechercheGroupes
	 */
	public List<GroupeDto> getListStructureRechercheGroupes() {
		return listStructureRechercheGroupes;
	}

	/**
	 * [RechercheStructureBB.listStructureRechercheGroupes] Setter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @param listStructureRechercheGroupes the listStructureRechercheGroupes to set
	 */
	public void setListStructureRechercheGroupes(
			List<GroupeDto> listStructureRechercheGroupes) {
		this.listStructureRechercheGroupes = listStructureRechercheGroupes;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheGroupe] Getter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @return the selectedStructureRechercheGroupe
	 */
	public GroupeDto getSelectedStructureRechercheGroupe() {
		return selectedStructureRechercheGroupe;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRechercheGroupe] Setter 
	 * @author rlaib on : 18 d�c. 2014  13:36:23
	 * @param selectedStructureRechercheGroupe the selectedStructureRechercheGroupe to set
	 */
	public void setSelectedStructureRechercheGroupe(
			GroupeDto selectedStructureRechercheGroupe) {
		this.selectedStructureRechercheGroupe = selectedStructureRechercheGroupe;
	}

	/**
	 * [RechercheStructureBB.canShowStructureDetail] Getter 
	 * @author rlaib on : 18 d�c. 2014  10:04:18
	 * @return the canShowStructureDetail
	 */
	public boolean isCanShowStructureDetail() {
		return canShowStructureDetail;
	}

	/**
	 * [RechercheStructureBB.canShowStructureDetail] Setter 
	 * @author rlaib on : 18 d�c. 2014  10:04:18
	 * @param canShowStructureDetail the canShowStructureDetail to set
	 */
	public void setCanShowStructureDetail(boolean canShowStructureDetail) {
		this.canShowStructureDetail = canShowStructureDetail;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRecherche] Getter 
	 * @author rlaib on : 18 d�c. 2014  10:03:23
	 * @return the selectedStructureRecherche
	 */
	public StructureDto getSelectedStructureRecherche() {
		return selectedStructureRecherche;
	}

	/**
	 * [RechercheStructureBB.selectedStructureRecherche] Setter 
	 * @author rlaib on : 18 d�c. 2014  10:03:23
	 * @param selectedStructureRecherche the selectedStructureRecherche to set
	 */
	public void setSelectedStructureRecherche(
			StructureDto selectedStructureRecherche) {
		this.selectedStructureRecherche = selectedStructureRecherche;
	}


	/**
	 * [RechercheStructureBB.listTypesStructuresReferentiel] Getter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @return the listTypesStructuresReferentiel
	 */
	public List<SelectItem> getListTypesStructuresReferentiel() {
		return listTypesStructuresReferentiel;
	}

	/**
	 * [RechercheStructureBB.listTypesStructuresReferentiel] Setter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @param listTypesStructuresReferentiel the listTypesStructuresReferentiel to set
	 */
	public void setListTypesStructuresReferentiel(
			List<SelectItem> listTypesStructuresReferentiel) {
		this.listTypesStructuresReferentiel = listTypesStructuresReferentiel;
	}

	/**
	 * [RechercheStructureBB.selectedTypeStructureReferentielId] Getter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @return the selectedTypeStructureReferentielId
	 */
	public Integer getSelectedTypeStructureReferentielId() {
		return selectedTypeStructureReferentielId;
	}

	/**
	 * [RechercheStructureBB.selectedTypeStructureReferentielId] Setter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @param selectedTypeStructureReferentielId the selectedTypeStructureReferentielId to set
	 */
	public void setSelectedTypeStructureReferentielId(
			Integer selectedTypeStructureReferentielId) {
		this.selectedTypeStructureReferentielId = selectedTypeStructureReferentielId;
	}

	/**
	 * [RechercheStructureBB.listStructuresReferentiel] Getter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @return the listStructuresReferentiel
	 */
	public List<RefStructureDto> getListStructuresReferentiel() {
		return listStructuresReferentiel;
	}

	/**
	 * [RechercheStructureBB.listStructuresReferentiel] Setter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @param listStructuresReferentiel the listStructuresReferentiel to set
	 */
	public void setListStructuresReferentiel(
			List<RefStructureDto> listStructuresReferentiel) {
		this.listStructuresReferentiel = listStructuresReferentiel;
	}

	/**
	 * [RechercheStructureBB.selectedStructureReferentiel] Getter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @return the selectedStructureReferentiel
	 */
	public RefStructureDto getSelectedStructureReferentiel() {
		return selectedStructureReferentiel;
	}

	/**
	 * [RechercheStructureBB.selectedStructureReferentiel] Setter 
	 * @author rlaib on : 17 d�c. 2014  11:17:47
	 * @param selectedStructureReferentiel the selectedStructureReferentiel to set
	 */
	public void setSelectedStructureReferentiel(
			RefStructureDto selectedStructureReferentiel) {
		this.selectedStructureReferentiel = selectedStructureReferentiel;
	}

	/**
	 * [RechercheStructureBB.listStructures] Getter 
	 * @author rlaib on : 16 d�c. 2014  17:20:08
	 * @return the listStructures
	 */
	public List<StructureDto> getListStructures() {
		return listStructures;
	}

	/**
	 * [RechercheStructureBB.listStructures] Setter 
	 * @author rlaib on : 16 d�c. 2014  17:20:08
	 * @param listStructures the listStructures to set
	 */
	public void setListStructures(List<StructureDto> listStructures) {
		this.listStructures = listStructures;
	}

	/**
	 * [RechercheStructureBB.selectedStructureId] Getter 
	 * @author rlaib on : 16 d�c. 2014  16:32:18
	 * @return the selectedStructureId
	 */
	public Integer getSelectedStructureId() {
		return selectedStructureId;
	}

	/**
	 * [RechercheStructureBB.selectedStructureId] Setter 
	 * @author rlaib on : 16 d�c. 2014  16:32:18
	 * @param selectedStructureId the selectedStructureId to set
	 */
	public void setSelectedStructureId(Integer selectedStructureId) {
		this.selectedStructureId = selectedStructureId;
	}
	
	// ===================================================================  
	// Actions and Methods
	// ===================================================================

	public void prepareSearchResearchStructure() {
		initStrcuturesReferentielParType();
	}
	public void prepareSearchStructureGroupes() {
	}
	public void prepareAddStructureTheme() {
		
		selectedStructureRechercheTheme = new ThemeDto();
		selectedStructureRechercheTheme.setStructureId(selectedStructureRecherche.getId());
		toEditThemeDialog = false;
	}
	public void prepareAddStructurePartners() {
		
		listPartnairesSearchSelection = new ArrayList<RefPartenaireDto>();
		searchPartners();
	
	}
	public void prepareEditStructureTheme() {
		
			if (selectedStructureRechercheThemeId != null) {
				toEditThemeDialog = true;
				selectedStructureRechercheTheme = serviceLocator.getRechercheThemeService().findById(selectedStructureRechercheThemeId);
			}
	}
	public void prepareGroupeMembres() {
		initMemebresGroupeReferentiel();
	}

	/**
	 * [RechercheStructureBB.initMemebresGroupeReferentiel] Method 
	 * @author rlaib  on : 4 janv. 2015  08:52:49
	 */
	private void initMemebresGroupeReferentiel() {
	
		if(this.sessionBean.getIdEtablissement() != null && selectedStructureRechercheGroupeId!= null) {
			GroupeDto selectedGroupeRecherche = this.serviceLocator.getRechercheGroupeService().findById(selectedStructureRechercheGroupeId);
			if(selectedGroupeRecherche != null) {
				listMembresGroupe =  serviceLocator.getRefAffectationService().findAffectationByIdGroupe(this.sessionBean.getIdEtablissement()
						, selectedGroupeRecherche.getRefGroupeId());
			}
		}
		
	}

	public void saveTheme() {
		
		RequestContext context = RequestContext.getCurrentInstance();
				if(selectedStructureRechercheTheme == null)
					return;
				else {
					if(!toEditThemeDialog) {
						// Test for already existing theme
								for (ThemeDto _themeDto : listStructureRechercheThemes) {
									if(_themeDto.getNcThemeId().equals(selectedNomenclatureThemeId)) {
										CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
												.getStringValueFromBundleResource(
														RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
														RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_THEME_EXISTING_CONTROL),
										CommonMessagesUtils.getStringValueFromBundleResource(
														RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
														RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_THEME_EXISTING_CONTROL));
										return;
									}
								}
					}
					if(selectedStructureRechercheTheme.getDateDebut().compareTo(selectedStructureRechercheTheme.getDateFin())>=0) {
					// La date fin doit etre superieure a la date debut
						CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_THEMES_DIALOG_DATE_CONTROL),
						CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_THEMES_DIALOG_DATE_CONTROL));
						context.addCallbackParam("isValid", false);
						return;
					}
				}
				selectedStructureRechercheTheme.setStructureId(selectedStructureRecherche.getId());
				selectedStructureRechercheTheme.setNcThemeId(selectedNomenclatureThemeId);
				if(selectedStructureRechercheThemeGroupeId == 0)
					selectedStructureRechercheThemeGroupeId = null;
				selectedStructureRechercheTheme.setGroupeId(selectedStructureRechercheThemeGroupeId);
				serviceLocator.getRechercheThemeService().insertOrUpdate(selectedStructureRechercheTheme);
				initListThemes();
				
				// Ajout groupe reussi
				if(!toEditThemeDialog) {
						CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
									.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_THEME_SUCCESS),
						CommonMessagesUtils.getStringValueFromBundleResource(
								RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
								RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_THEME_SUCCESS));
				}
				else {
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_UPDATE_THEME_SUCCESS),
				CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_UPDATE_THEME_SUCCESS));
				}
			
				context.addCallbackParam("isValid", true);
	}
	
	/**
	 * [RechercheStructureBB.initStrcuturesReferentielParType] Method 
	 * @author rlaib  on : 17 d�c. 2014  11:22:50
	 */
	private void initStrcuturesReferentielParType() {
		
		if(sessionBean.getIdEtablissement() != null && selectedTypeStructureReferentielId != null) {
			listStructuresReferentiel = serviceLocator.getRefStructureService().findStructuresByTypeByEtab(sessionBean.getIdEtablissement(), selectedTypeStructureReferentielId);
		}
	}

	/**
	 * [RechercheStructureBB.initListeStructuresRecherche] Method 
	 * @author rlaib  on : 17 d�c. 2014  14:53:17
	 */
	private void initListeStructuresRecherche() {
		
		listStructures = serviceLocator.getRechercheStructureService().findAll();
		
	}
	
	public void removeStructureRecherche() {
	
		if(selectedStructureId== null)
				return;
			serviceLocator.getRechercheStructureService().remove(selectedStructureId);
			initListeStructuresRecherche();
			
			// Suppression reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_STRUCTURE_SUCCESS),
			CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_STRUCTURE_SUCCESS));
	}
	
	public void removeStructureGroupe() {
			
			if(selectedStructureRechercheGroupeId== null)
				return;
			serviceLocator.getRechercheGroupeService().remove(selectedStructureRechercheGroupeId);
			initListGroupes();
			
			// Suppression groupe reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_GROUPE_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_GROUPE_SUCCESS));

	}
	
	public void removeStructureTheme() {

		if(selectedStructureRechercheThemeId== null)
				return;
			serviceLocator.getRechercheThemeService().remove(selectedStructureRechercheThemeId);
			initListThemes();
			
			// Suppression theme reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_THEME_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_THEME_SUCCESS));
	}
	
	public void removeStructurePartner() {
	
		if(selectedStructureRecherchePartenaireId== null)
				return;
			serviceLocator.getRecherchePartenaireService().remove(selectedStructureRecherchePartenaireId);
			initListPartenaires();
			
			// Suppression partnaire reussie
			CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
					RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_PARTNER_SUCCESS),
					CommonMessagesUtils.getStringValueFromBundleResource(RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
							RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_REMOVE_PARTNER_SUCCESS));
	
	}
	
	/**
	 * [RechercheStructureBB.loadStructureRechercheDetail] Method 
	 * @author rlaib  on : 18 d�c. 2014  10:37:13
	 */
	private void loadStructureRechercheDetail() {

		initListGroupes();
		initListEquipements();
		initListNomeclature();
		initListThemes();
		initListPartenaires();
	}

	/**
	 * [RechercheStructureBB.initListPartenaires] Method 
	 * @author rlaib  on : 24 d�c. 2014  08:36:26
	 */
	private void initListPartenaires() {
		
		listStructureRecherchePartenaires = serviceLocator.getRecherchePartenaireService().findStructureRecherchePartenaires(selectedStructureRecherche.getId());
		
	}

	/**
	 * [RechercheStructureBB.initListThemes] Method 
	 * @author rlaib  on : 22 d�c. 2014  15:32:39
	 */
	private void initListThemes() {
		listStructureRechercheThemes= serviceLocator.getRechercheThemeService().findStructureRechercheThemes(selectedStructureRecherche.getId());
		
		
	}

	/**
	 * [RechercheStructureBB.initListNomeclature] Method 
	 * @author rlaib  on : 22 d�c. 2014  10:40:19
	 */
	private void initListNomeclature() {

			listNomenclatureThemes = this.findNomenclatureByName(UtilConstant.NC_RCH_THEMES_NAME);
	}

	/**
	 * [RechercheStructureBB.initListEquipements] Method 
	 * @author rlaib  on : 21 d�c. 2014  16:22:56
	 */
	private void initListEquipements() {
		listStructureRechercheEquipements = serviceLocator.getRefEquipementService().findAll();
		
	}

	/**
	 * [RechercheStructureBB.initListGroupes] Method 
	 * @author Rafik  on : 21 déc. 2014  11:41:19
	 */
	private void initListGroupes() {
			listStructureRechercheGroupes = serviceLocator.getRechercheGroupeService().findStructureRechercheGroupes(selectedStructureRecherche.getId());
			SelectItem defaultSelection = null;
			if (listStructureRechercheGroupes != null && !listStructureRechercheGroupes.isEmpty()) {
				listStructureRechercheGroupesItems = new ArrayList<SelectItem>();
				defaultSelection = new SelectItem(0, CommonMessagesUtils.getStringValueFromBundleResource(
						RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
						RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_THEMES_GROUPES_LISTE_DEFAULT), null, false, false, true);
				for (GroupeDto dto : listStructureRechercheGroupes) {
					listStructureRechercheGroupesItems.add(new SelectItem(dto.getId(), dto.getRefGroupeLibelleFr()));
				}
				listStructureRechercheGroupesItems.add(0, defaultSelection);
			}
	}

	/**
	 * [RechercheStructureBB.searchGroup] Method 
	 * @author Rafik  on : 21 déc. 2014  11:40:31
	 * @throws java.lang.Exception 
	 */
	public void searchGroup() {
			
			NomenclatureDto typeGroupeRecherche = serviceLocator.getNomenclatureService().findByCode(UtilConstant.NC_TYPE_GROUPE_NAME, 
					UtilConstant.NC_TYPE_GROUPE_RECHERCHE_CODE); 
			if(typeGroupeRecherche !=null) {
				Integer idTypeGroupeRecherche = typeGroupeRecherche.getId();
				if(idTypeGroupeRecherche != null) {
						listGroupesReferentiel = serviceLocator.getRefGroupeService().findGeneric(sessionBean.getIdEtablissement(),refGroupSearchKeyWord, idTypeGroupeRecherche);
				}
			}
	}
	
	/**
	 * [RechercheStructureBB.searchPartners] Method 
	 * @author rlaib  on : 24 d�c. 2014  10:55:41
	 */
	public void searchPartners() {
		if(sessionBean.getIdEtablissement() != null) {
			listPartnairesSearch= serviceLocator.getRefPartenaireService().findPartenairesStructure(selectedStructureRecherche.getRefStructureId());
			if(listPartnairesSearch != null && !listPartnairesSearch.isEmpty()) {
				canAddPartners = true;
			}
			else {
				canAddPartners = false;
			}
		}
	}
	
	/**
	 * [RechercheStructureBB.addPartners] Method 
	 * @author rlaib  on : 24 d�c. 2014  10:55:52
	 */
	public void addPartners() {
			
		RequestContext context = RequestContext.getCurrentInstance();
					// Test for already existing partner
					for (RefPartenaireDto _refPartenaireDto : listPartnairesSearchSelection) {
						for (PartenaireDto _partenaireDto : listStructureRecherchePartenaires) {
							if(_partenaireDto.getPartenaireId().equals(_refPartenaireDto.getId())) {
								
								String _partnerName ="";
								if(_refPartenaireDto.getLlGroupe()!= null)
									_partnerName = _partnerName + _refPartenaireDto.getLlGroupe();
								if(_refPartenaireDto.getLlStructureLatin()!= null)
									_partnerName = _partnerName + _refPartenaireDto.getLlStructureLatin();
								if(_refPartenaireDto.getPrenomLatin()!= null && _refPartenaireDto.getNomLatin()!=null)
									_partnerName = _partnerName + _refPartenaireDto.getPrenomLatin()+_refPartenaireDto.getNomLatin();
								
								Object[] params = {_partnerName};
								CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
										.getStringValueFromBundleResource(
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_PARTNER_EXISTING_CONTROL),
								CommonMessagesUtils.getStringValueFromBundleResourceWithParams(
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
												RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_PARTNER_EXISTING_CONTROL, params,"fr"));
								return;
							}
						}
						
					}
					
					if(listPartnairesSearchSelection != null && !listPartnairesSearchSelection.isEmpty()) {
						for (RefPartenaireDto _refPartenaireDto : listPartnairesSearchSelection) {
							PartenaireDto _partenaireDto = new PartenaireDto();
							_partenaireDto.setContratId(_refPartenaireDto.getIdContrat());
							_partenaireDto.setPartenaireId(_refPartenaireDto.getId());
							_partenaireDto.setStructureId(selectedStructureRecherche.getId());
							_partenaireDto.setDateDebut(_partenaireDto.getContratDateDebutValidite());
							_partenaireDto.setDateFin(_partenaireDto.getContratDateFinValidite());
							serviceLocator.getRecherchePartenaireService().insertOrUpdate(_partenaireDto);
						}
						initListPartenaires();
						CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
								.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_PARTNER_SUCCESS),
								CommonMessagesUtils.getStringValueFromBundleResource(
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
										RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_PARTNER_SUCCESS));
					}
					context.addCallbackParam("isValid", true);
		
	}

	// ===================================================================  
	// Listeners
	// ===================================================================

	public void onRowGroupSelect(SelectEvent event) {  
		
					// Test for already existing group
					if(listGroupesReferentiel != null && selectedGroupeReferentiel!= null) {
							for (GroupeDto _groupeDto : listStructureRechercheGroupes) {
								if(_groupeDto.getRefGroupeId().equals(selectedGroupeReferentiel.getId())) {
									CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
											.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_GROUPE_EXISTING_STRUCTURE),
									CommonMessagesUtils.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_GROUPE_EXISTING_STRUCTURE));
									return;
								}
							}
					}
					else
						return;
					selectedStructureRechercheGroupe = new GroupeDto();
					selectedStructureRechercheGroupe.setRefGroupeId(selectedGroupeReferentiel.getId());
					selectedStructureRechercheGroupe.setStructureId(selectedStructureRecherche.getId());
					serviceLocator.getRechercheGroupeService().insertOrUpdate(selectedStructureRechercheGroupe);
					initListGroupes();
					selectedStructureRechercheGroupe = null;
					selectedGroupeReferentiel = null;
					
					// Successfull add group
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_GROUPE_SUCCESS),
							CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_GROUPE_SUCCESS));
					
	}
	
	public void onStrcutureReferentielRowSelect(SelectEvent event) {  
		
					// Test for already existing structure
					if(listStructures != null && selectedStructureReferentiel != null) {
							for (StructureDto _structureDto : listStructures) {
								if(_structureDto.getRefStructureId().equals(selectedStructureReferentiel.getId())) {
									CommonMessagesUtils.addErrorMessageWithoutKey(CommonMessagesUtils
											.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_STRUCTURE_EXISTING_STRUCTURE),
									CommonMessagesUtils.getStringValueFromBundleResource(
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
													RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_STRUCTURE_EXISTING_STRUCTURE));
									return;
								}
							}
					}
					else
						return;
					selectedStructureRecherche = new StructureDto();
					selectedStructureRecherche.setRefStructureId(selectedStructureReferentiel.getId());
					selectedStructureRecherche = serviceLocator.getRechercheStructureService().insertOrUpdate(selectedStructureRecherche);
					initListeStructuresRecherche();
					selectedStructureRecherche = null;
					selectedStructureReferentiel = null;
					canShowStructureDetail = false;
					// Ajout reussi
					CommonMessagesUtils.addInfoMessageWithoutKey(CommonMessagesUtils
							.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_STRUCTURE_SUCCESS),
							CommonMessagesUtils.getStringValueFromBundleResource(
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_FILE_NAME,
									RechercheConstantBean.RECHERCHE_STRUCTURE_RESSOURCE_MESSAGE_KEY_ADD_STRUCTURE_SUCCESS));
	
	}  
	
	public void onStrcutureRechercheRowSelect(SelectEvent event) {  
		
			canShowStructureDetail = true;
			loadStructureRechercheDetail();
	}
	
	public void handleOnRowSelect(SelectEvent event) {  
		
			canShowStructureDetail = true;
			selectedGroupeReferentiel.setLcGroupeArabe("jlkkj");
	}

	public void handleTypeStructureChange(AjaxBehaviorEvent event) {
		initStrcuturesReferentielParType();
	}
	
}
