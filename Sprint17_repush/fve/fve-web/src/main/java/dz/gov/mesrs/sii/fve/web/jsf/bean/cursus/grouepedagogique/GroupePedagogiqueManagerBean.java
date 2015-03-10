/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.GroupePedagogiqueManagerBean.java] 
 * @author MAKERRI Sofiane on : 8 juin 2014  15:39:33
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AssociationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.RattachementMcDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AffectationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.AssociationGroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.cursus.GroupePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.NiveauService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.PeriodeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RattachementMcService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.RepartitionUeService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SearchBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Const;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefGroupeDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametreEtabService;

/**
 * @author MAKERRI Sofiane on : 8 juin 2014 15:39:33
 */
/**
 * @author MAKERRI Sofiane on : 12 juin 2014 18:01:57
 */
@ManagedBean(name = "groupePedagogiqueManagerBean")
@ViewScoped
public class GroupePedagogiqueManagerBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:39:54
	 */
	private static final long serialVersionUID = 1L;

	private Integer idGroupe;
	private Integer idGp;
	// private Integer idAnnee;
	// private Integer idOof;
	private Integer idCycle;
	// private Integer idPeriode;
	private String typeGroupe;
	private Integer idNiveau;
	private Integer idSectionType;
	private Integer idGroupeType;
	private Boolean groupeOfSection;
	/****************************************************
	 * Services
	 ****************************************************/
	@ManagedProperty(value = "#{groupePedagogiqueService}")
	private GroupePedagogiqueService groupePedagogiqueService;
	@ManagedProperty(value = "#{refGroupeServiceImpl}")
	private RefGroupeService refGroupeService;
	@ManagedProperty(value = "#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationService;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty(value = "#{associationGroupePedagogiqueService}")
	private AssociationGroupePedagogiqueService associationGroupePedagogiqueService;
	@ManagedProperty(value = "#{affectationGroupePedagogiqueService}")
	private AffectationGroupePedagogiqueService affectationGroupePedagogiqueService;
	@ManagedProperty(value = "#{periodeService}")
	private PeriodeService periodeService;
	@ManagedProperty(value = "#{refParametreEtabServiceImpl}")
	private RefParametreEtabService refParametreEtabService;
	@ManagedProperty(value = "#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty(value = "#{searchBean}")
	private SearchBean searchBean;
	/****************************************************
	 * List
	 ****************************************************/
	private List<SelectItem> ncTypeGroupeList;
	private List<SelectItem> sectionList;
	/****************************************************/
	private GroupePedagogiqueDto groupePedagogiqueDto;
	private AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto;
	private boolean entityGroupeChange;
	private boolean sectionChange;
	private boolean showDetail;
	private boolean showTable;
	private ResourceBundle bundleCommon;
	private ResourceBundle bundleGroupe;
	private static final Log log = LogFactory
			.getLog(GroupePedagogiqueManagerBean.class);
	private Map<String, AssociationGroupePedagogiqueDto> tmpApSectionList;
	private List<String> apSectionList;
	private List<String> selectedAp;
	private List<String> tmpSelectedAp;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	private Integer idOf;
	@ManagedProperty(value = "#{repartitionUeService}")
	private RepartitionUeService repartitionUeService;
	@ManagedProperty(value = "#{rattachementMcService}")
	private RattachementMcService rattachementMcService;
	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;
	private boolean entityAssociationChange;
	@ManagedProperty(value = "#{groupeAffectationBean}")
	private GroupeAffectationBean groupeAffectationBean;
	private List<GroupePedagogiqueDto> listGroupePedagogiqueDto;
	private List<GroupeComposit> listSectionGroupe;
	private List<SelectItem> offreFormationList;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{niveauService}")
	private NiveauService niveauService;
	private List<SelectItem> anneeAcademiqueList;
	private List<SelectItem> periodeList;
	private List<SelectItem> niveauList;
	private boolean editMode;
	private boolean checkAll;
	private List<AssociationGroupePedagogiqueDto> associatedApDetailList;

	/**
	 * [GroupePedagogiqueManagerBean.GroupePedagogiqueManagerBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:39:33
	 */
	public GroupePedagogiqueManagerBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		bundleGroupe = facesContext.getApplication().getResourceBundle(
				facesContext,
				CursusConstants.GROUPE_PEDAGOGIQUE_BUNDLE_MSG_NAME);
		String viewId = facesContext.getViewRoot().getViewId();
		editMode = false;
		if (viewId != null && viewId.toLowerCase().contains("edit")) {
			editMode = true;
		}
		groupePedagogiqueDto = new GroupePedagogiqueDto();
	}

	@PostConstruct
	public void init() {
		// loadGroupePedagogiqueList();
		if (editMode) {
			// idAnnee = sessionBean.getIdAnneeAcademique();
			// loadOffreFormationOuverte();
		} else {
			// loadAnneeAcademique();
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadGroupePedagogiqueList] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:07:24
	 */
	public void loadGroupePedagogiqueList() {
		if (searchBean.getOofId() != null
				&& searchBean.getAnneeAcademiqueId() != null) {
			GroupePedagogiqueDto searchDto = new GroupePedagogiqueDto();
			searchDto.setOofId(searchBean.getOofId());
			searchDto.setCodeEtablissement(sessionBean.getCodeEtablissement());
			searchDto.setIdAnneeAcademique(searchBean.getAnneeAcademiqueId());
			searchDto.setPeriodeId(searchBean.getPeriodeId());
			listGroupePedagogiqueDto = groupePedagogiqueService
					.findAdvanced(searchDto);
			prepareTree();

		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.prepareTree] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 11:39:51
	 */
	public void prepareTree() {
		listSectionGroupe = new ArrayList<>();

		if (listGroupePedagogiqueDto != null) {
			for (GroupePedagogiqueDto _section : listGroupePedagogiqueDto) {
				if (_section.getIdSection() == null) {
					GroupeComposit groupeComposit = new GroupeComposit();
					groupeComposit.setSection(_section);
					List<GroupePedagogiqueDto> groupes = new ArrayList<GroupePedagogiqueDto>();
					for (GroupePedagogiqueDto _groupe : listGroupePedagogiqueDto) {
						if (_groupe.getIdSection() != null
								&& _groupe.getIdSection().equals(
										_section.getId())) {
							groupes.add(_groupe);
						}
					}
					groupeComposit.setGroupes(groupes);
					listSectionGroupe.add(groupeComposit);
				}
			}
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:07:40
	 */
	public void loadAnneeAcademique() {
		try {
			List<AnneeAcademiqueDto> list = anneeAcademiqueService.findAll();
			anneeAcademiqueList = new ArrayList<SelectItem>();
			for (AnneeAcademiqueDto annee : list) {
				SelectItem item = new SelectItem(annee.getId(), annee.getCode());
				anneeAcademiqueList.add(item);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadPeriodeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:05:44
	 */
	public void loadPeriodeList() {
		try {
			periodeList = new ArrayList<SelectItem>();
			if (idNiveau == null) {
				if (periodeService != null && idCycle != null) {
					List<PeriodeDto> _periodes = periodeService
							.findByCycleId(idCycle);
					for (PeriodeDto item : _periodes) {
						periodeList.add(new SelectItem(item.getId(), item
								.getLibelleLongLt()));
					}
				}
			} else {

				List<PeriodeDto> list = periodeService.findByLevelId(idNiveau);

				for (PeriodeDto periodeDto : list) {
					SelectItem item = new SelectItem(periodeDto.getId(),
							periodeDto.getLibelleLongLt());
					periodeList.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.loading] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:57:01
	 */
	public void loading() {
		try {
			groupeOfSection = false;
			if (groupePedagogiqueDto == null) {
				groupePedagogiqueDto = new GroupePedagogiqueDto();
			}
			apSectionList = null;
			selectedAp = new ArrayList<String>();
			loadNcTypeGroupeList();
			loadSectionList();
			if (editMode) {
				loadingAssociation();
			} else {
				loadAssociatedAp();
			}
		} catch (Exception e) {

		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.loadingAssociation] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 09:32:35
	 */
	public void loadingAssociation() {
		associationGroupePedagogiqueDto = new AssociationGroupePedagogiqueDto();
		associationGroupePedagogiqueDto
				.setGroupePedagogiqueId(groupePedagogiqueDto.getId());
		apSectionList = null;
		selectedAp = new ArrayList<String>();
		if (editMode) {
			loadSectionAp();
		}

		checkAll = selectedAp.size() == apSectionList.size();
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadAssociatedAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 15:20:33
	 */
	public void loadAssociatedAp() {
		if (groupePedagogiqueDto.getId() != null) {
			associatedApDetailList = associationGroupePedagogiqueService
					.findByGroupePedagogiqueId(groupePedagogiqueDto.getId());
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadNcTypeGroupeList] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:40:23
	 */
	public void loadNcTypeGroupeList() {
		try {
			if (nomenclatureService != null
					&& (ncTypeGroupeList == null || ncTypeGroupeList.isEmpty())) {
				ncTypeGroupeList = new ArrayList<SelectItem>();
				List<NomenclatureDto> _typeGroupe = nomenclatureService
						.findByName(CursusConstants.NC_LMD_TYPE_GROUPE);
				for (NomenclatureDto item : _typeGroupe) {
					ncTypeGroupeList.add(new SelectItem(item.getId(), item
							.getLibelleLongFr()));
					if (item.getLibelleLongFr().toLowerCase().equals("section")) {
						// sectionType = item;
						idSectionType = item.getId();
						// codeSectionType = item.getCode();
					}
					if (item.getLibelleLongFr().toLowerCase().equals("groupe")) {
						// sectionType = item;
						idGroupeType = item.getId();
						// codeSectionType = item.getCode();
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.getIdGroupeType] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 13:35:33
	 * @param type
	 * @return
	 */
	// public Integer getIdGroupeType(String type) {
	// try {
	// if (type == null) {
	// return null;
	// }
	// if (ncTypeGroupeList != null) {
	//
	// for (SelectItem item : ncTypeGroupeList) {
	// if (item.getLabel() != null
	// && item.getLabel().toLowerCase()
	// .equals(type.toLowerCase()))
	// return (Integer) item.getValue();
	// }
	// }
	// } catch (Exception e) {
	// return null;
	// }
	// return null;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.loadSectionList] Method
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 18:45:10
	 */
	public void loadSectionList() {
		try {
			String codeEtab = sessionBean.getCodeEtablissement();
			if (codeEtab != null) {
				GroupePedagogiqueDto sectionDto = new GroupePedagogiqueDto();
				sectionDto.setCodeEtablissement(codeEtab);
				sectionDto.setPeriodeId(searchBean.getPeriodeId());
				sectionDto.setOofId(groupePedagogiqueDto.getOofId());
				sectionDto.setNcTypeGroupeId(idSectionType);
				sectionDto.setIdAnneeAcademique(groupePedagogiqueDto
						.getIdAnneeAcademique());
				List<GroupePedagogiqueDto> list = groupePedagogiqueService
						.findAdvanced(sectionDto);
				sectionList = new ArrayList<SelectItem>();
				for (GroupePedagogiqueDto item : list) {
					sectionList.add(new SelectItem(item.getId(), item
							.getNomAffichage()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:41:56
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:41:56
	 * @param idGroupe
	 *            the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:43:29
	 * @return the groupePedagogiqueService
	 */
	public GroupePedagogiqueService getGroupePedagogiqueService() {
		return groupePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:43:29
	 * @param groupePedagogiqueService
	 *            the groupePedagogiqueService to set
	 */
	public void setGroupePedagogiqueService(
			GroupePedagogiqueService groupePedagogiqueService) {
		this.groupePedagogiqueService = groupePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupePedagogiqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:27:59
	 * @return the groupePedagogiqueDto
	 */
	public GroupePedagogiqueDto getGroupePedagogiqueDto() {
		return groupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupePedagogiqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:27:59
	 * @param groupePedagogiqueDto
	 *            the groupePedagogiqueDto to set
	 */
	public void setGroupePedagogiqueDto(
			GroupePedagogiqueDto groupePedagogiqueDto) {
		this.groupePedagogiqueDto = groupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.ncTypeGroupeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:56:18
	 * @return the ncTypeGroupeList
	 */
	public List<SelectItem> getNcTypeGroupeList() {
		return ncTypeGroupeList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.ncTypeGroupeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 15:56:18
	 * @param ncTypeGroupeList
	 *            the ncTypeGroupeList to set
	 */
	public void setNcTypeGroupeList(List<SelectItem> ncTypeGroupeList) {
		this.ncTypeGroupeList = ncTypeGroupeList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 16:03:43
	 * @param event
	 */
	public void entityGroupeChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityGroupeChange = true;
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.sectionChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 11:19:03
	 * @param event
	 */
	public void sectionChanged() {
		entityGroupeChange = true;
		sectionChange = true;
		groupePedagogiqueDto.setRattachementMcIdSection(null);
		if (groupePedagogiqueDto.getIdSection() != null) {
			GroupePedagogiqueDto section = groupePedagogiqueService
					.findById(groupePedagogiqueDto.getIdSection());
			if (section != null) {
				groupePedagogiqueDto.setCapaciteMinSection(section
						.getCapaciteMin());
				groupePedagogiqueDto.setCapaciteMaxSection(section
						.getCapaciteMax());

			}
		}
		// loadAp();

	}

	/**
	 * [GroupePedagogiqueManagerBean.entityGroupeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 18:04:20
	 * @return the entityGroupeChange
	 */
	public boolean isEntityGroupeChange() {
		return entityGroupeChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.entityGroupeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 18:04:20
	 * @param entityGroupeChange
	 *            the entityGroupeChange to set
	 */
	public void setEntityGroupeChange(boolean entityGroupeChange) {
		this.entityGroupeChange = entityGroupeChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 17:58:26
	 */
	public void saveGroupe() {
		try {
			if (!entityGroupeChange) {
				Utility.showWarningMessage(bundleCommon
						.getString("warn_info_update_none"));
				return;
			}
			String erreur = null;

			if (searchBean.getAnneeAcademiqueId() == null) {
				erreur = "groupepedagogique_erreur_annee_academique_vide";
			} else if (searchBean.getPeriodeId() == null
					&& groupePedagogiqueDto.getPeriodeId() == null) {
				erreur = "groupepedagogique_erreur_periode_vide";
			} else if (searchBean.getOofId() == null) {
				erreur = "groupepedagogique_erreur_offre_formation_vide";
			} else if (groupePedagogiqueDto.getIdSection() != null
					&& groupePedagogiqueDto.getCapaciteMaxSection() != null
					&& groupePedagogiqueDto.getCapaciteMax() > groupePedagogiqueDto
							.getCapaciteMaxSection()) {
				erreur = "groupepedagogique_erreur_capacite_max_superieur_capacite_max_section";
			}
			if (erreur != null) {
				Utility.showErrorMessage(bundleGroupe.getString(erreur));
				return;

			}
			GroupePedagogiqueDto _gp = groupePedagogiqueService
					.groupeNameExist(groupePedagogiqueDto);
			if (_gp != null
					&& !_gp.getId().equals(groupePedagogiqueDto.getId())) {
				Utility.showErrorMessage(bundleGroupe
						.getString("groupepedagogique_erreur_groupe_nom_exist"));
				return;
			}
			groupePedagogiqueDto.setNomAffichageArabe(groupePedagogiqueDto
					.getNomAffichageArabe().trim());
			groupePedagogiqueDto.setNomAffichage(groupePedagogiqueDto
					.getNomAffichage().trim());
			groupePedagogiqueDto.setDateAssociation(new Date());
			groupePedagogiqueDto.setIdAnneeAcademique(searchBean
					.getAnneeAcademiqueId());
			if (searchBean.getPeriodeId() != null) {
				groupePedagogiqueDto.setPeriodeId(searchBean.getPeriodeId());
			}
			groupePedagogiqueDto.setOofId(searchBean.getOofId());
			groupePedagogiqueDto.setIdEtablissement(sessionBean
					.getIdEtablissement());
			groupePedagogiqueDto.setCodeEtablissement(sessionBean
					.getCodeEtablissement());
			String result = fillRefGroupeDto(groupePedagogiqueDto, null);
			if (result != null) {
				Utility.showErrorMessage(bundleGroupe.getString("result"));
				return;
			}

			if (groupePedagogiqueDto.getIdSection() != null) {
				saveAffecation();

			}
			String code = refGroupeService.generateIdentify(
					groupePedagogiqueDto.getDateCreation(),
					groupePedagogiqueDto.getIdEtablissement(),
					groupePedagogiqueDto.getCodeEtablissement());
			if (code == null) {
				Utility.showErrorMessage("erreur de generation de code groupe");
				return;
			}
			groupePedagogiqueDto.setCodeGroupe(code);
			Integer gpId = groupePedagogiqueDto.getId();
			groupePedagogiqueDto = groupePedagogiqueService
					.insertOrUpdate(groupePedagogiqueDto);

			if (groupePedagogiqueDto.getId().equals(gpId)) {
				Utility.showSuccessUpdateMessage();
			} else {
				Utility.showSuccessSaveMessage();
			}
			setGroupeOfSection(groupePedagogiqueDto.getIdSection() == null ? false
					: true);
			setIdGp(groupePedagogiqueDto.getId());
			setEntityGroupeChange(false);
			setSectionChange(false);
			loadGroupePedagogiqueList();
			loadingAssociation();
			groupeAffectationBean.setIdSectionType(idSectionType);
			groupeAffectationBean.loading();

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.fillRefGroupeDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 11:04:42
	 * @param groupePedagogiqueDto
	 * @param refGroupeDto
	 */
	public String fillRefGroupeDto(GroupePedagogiqueDto groupePedagogiqueDto,
			RefGroupeDto refGroupeDto) {
		groupePedagogiqueDto
				.setLcGroupe(groupePedagogiqueDto.getNomAffichage());
		groupePedagogiqueDto.setLcGroupeArabe(groupePedagogiqueDto
				.getNomAffichageArabe());
		// if (groupePedagogiqueDto.getApId() == null) {
		// return "groupePedagogique_attachement_ap_vide";
		// }
		// AtomePedagogiqueDto ap = atomePedagogiqueService
		// .findById(groupePedagogiqueDto.getApId());
		// // if (ap == null || ap.getCode() == null) {
		// // return "groupePedagogique_attachement_ap_vide";
		// // }
		// String codeAp = "";
		// if (ap.getCode() != null) {
		// codeAp = ap.getCode().trim();
		// }
		// if (groupePedagogiqueDto.getRattachementMcId() == null) {
		// return "groupePedagogique_attachement_mc_vide";
		// }
		// RattachementMcDto rattachementMcDto = rattachementMcService
		// .findById(groupePedagogiqueDto.getRattachementMcId());
		// String _mcLibelleFr = "";
		// String _ueLibelleFr = "";
		// if (rattachementMcDto != null
		// && rattachementMcDto.getMcLibelleFr() != null) {
		// _mcLibelleFr = rattachementMcDto.getMcLibelleFr().trim();
		// }
		// if (rattachementMcDto != null
		// && rattachementMcDto.getUeLibelleFr() != null) {
		// _ueLibelleFr = rattachementMcDto.getUeLibelleFr().trim();
		// }
		// String _mcLibelleAr = "";
		// String _ueLibelleAr = "";
		// if (rattachementMcDto.getMcLibelleAr() != null) {
		// _mcLibelleAr = rattachementMcDto.getMcLibelleAr().trim();
		// }
		// if (rattachementMcDto.getUeLibelleAr() != null) {
		// _ueLibelleAr = rattachementMcDto.getUeLibelleAr().trim();
		// }
		// if (groupePedagogiqueDto.getPeriodeId() == null) {
		// return "groupepedagogique_erreur_periode_vide";
		// }
		// PeriodeDto periode = periodeService.findById(groupePedagogiqueDto
		// .getPeriodeId());
		// if (periode == null || periode.getLibelleLongLt() == null) {
		// return "groupepedagogique_erreur_libelle_periode_vide";
		// }
		// String periodeLibelleLongLt = "";
		// String periodeLibelleLongAr = "";
		// if (periode.getLibelleLongLt() != null) {
		// periodeLibelleLongLt = periode.getLibelleLongLt().trim();
		// }
		// if (periode.getLibelleLongAr() != null) {
		// periodeLibelleLongAr = periode.getLibelleLongAr().trim();
		// }
		// if (groupePedagogiqueDto.getOofId() == null) {
		// return "groupepedagogique_erreur_offre_formation_vide";
		// }
		// OuvertureOffreFormationDto oof = ouvertureOffreFormationService
		// .findById(groupePedagogiqueDto.getOofId());
		// if (oof == null || oof.getOffreFormationId() == null) {
		// return "groupepedagogique_erreur_offre_formation_vide";
		// }
		// String ofLibelleFr = "";
		// String ofLibelleAr = "";
		// Map<String, OffreFormationI18nDto> ofDtoI18ns = loadOfI18n(oof
		// .getOffreFormationId());
		// if (ofDtoI18ns != null) {
		//
		// OffreFormationI18nDto offreFormationI18nDtoFr = ofDtoI18ns
		// .get("fr");
		// if (offreFormationI18nDtoFr != null) {
		//
		// ofLibelleFr = offreFormationI18nDtoFr.getLibelle();
		// offreFormationI18nDtoFr = ofDtoI18ns.get("ar");
		//
		// if (offreFormationI18nDtoFr != null) {
		// ofLibelleAr = offreFormationI18nDtoFr.getLibelle();
		// }
		// }
		// }
		// if (ofLibelleFr != null) {
		// ofLibelleFr = ofLibelleFr.trim();
		// }
		// if (ofLibelleAr != null) {
		// ofLibelleAr = ofLibelleAr.trim();
		// }
		// if (groupePedagogiqueDto.getIdAnneeAcademique() == null) {
		// return "groupepedagogique_erreur_annee_academique_vide";
		// }
		// AnneeAcademiqueDto aac = anneeAcademiqueService
		// .findById(groupePedagogiqueDto.getIdAnneeAcademique());
		// String aacCode = "";
		// if (aac != null && aac.getCode() != null) {
		// aacCode = aac.getCode().replace("/", "");
		// }
		//
		// String llFrGroupe = groupePedagogiqueDto.getNomAffichage() + "_"
		// + codeAp + "_" + _mcLibelleFr + "_" + _ueLibelleFr + "_"
		// + periodeLibelleLongLt + "_" + ofLibelleFr + "_" + aacCode;
		// String llArGroupe = groupePedagogiqueDto.getNomAffichageArabe() + "_"
		// + codeAp + "_" + _mcLibelleAr + "_" + _ueLibelleAr + "_"
		// + periodeLibelleLongAr + "_" + ofLibelleAr + "_" + aacCode;
		// refGroupeDto.setLlGroupe(llFrGroupe);
		// refGroupeDto.setLlGroupeArabe(llArGroupe);
		groupePedagogiqueDto
				.setLlGroupe(groupePedagogiqueDto.getNomAffichage());
		groupePedagogiqueDto.setLlGroupeArabe(groupePedagogiqueDto
				.getNomAffichageArabe());
		return null;
	}

	/**
	 * [GroupePedagogiqueManagerBean.saveAffecation] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 10:12:38
	 */
	public void saveAffecation() {
		try {
			RefAffectationDto refAffectationDto = refAffectationService
					.findGroupeParent("groupe",
							groupePedagogiqueDto.getIdGroupe());

			if (refAffectationDto != null
					&& groupePedagogiqueDto.getIdSection() == null) {
				refAffectationService.delete(refAffectationDto);
				groupePedagogiqueDto.setNomSection(null);

			} else {
				if (refAffectationDto == null) {
					refAffectationDto = new RefAffectationDto();
				}

				refAffectationDto.setDateDebut(new Date());
				if (nomenclatureService != null) {
					NomenclatureDto role = nomenclatureService.findByCode(
							Const.NC_ROLE, Const.REF_CODE_ROLE_MEMBRE);
					if (role != null) {
						refAffectationDto.setRoleId(role.getId());

					}
					refAffectationDto.setIdGroupe(groupePedagogiqueDto
							.getIdGroupe());
					if (groupePedagogiqueDto.getIdSection() != null) {
						RefGroupeDto groupeParent = refGroupeService
								.findById(groupePedagogiqueDto.getIdSection());
						if (groupeParent != null) {
							refAffectationDto.setIdGroupeAffecte(groupeParent
									.getId());
						}
					}
				}

				refAffectationDto = refAffectationService
						.saveOrUpdate(refAffectationDto);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.bundleCommon] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 16:07:27
	 * @return the bundleCommon
	 */
	public ResourceBundle getBundleCommon() {
		return bundleCommon;
	}

	/**
	 * [GroupePedagogiqueManagerBean.bundleCommon] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 16:07:27
	 * @param bundleCommon
	 *            the bundleCommon to set
	 */
	public void setBundleCommon(ResourceBundle bundleCommon) {
		this.bundleCommon = bundleCommon;
	}

	/**
	 * [GroupePedagogiqueManagerBean.bundleGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 16:07:27
	 * @return the bundleGroupe
	 */
	public ResourceBundle getBundleGroupe() {
		return bundleGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.bundleGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 16:07:27
	 * @param bundleGroupe
	 *            the bundleGroupe to set
	 */
	public void setBundleGroupe(ResourceBundle bundleGroupe) {
		this.bundleGroupe = bundleGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 juin 2014 12:47:49
	 * @return the idGp
	 */
	public Integer getIdGp() {
		return idGp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 juin 2014 12:47:49
	 * @param idGp
	 *            the idGp to set
	 */
	public void setIdGp(Integer idGp) {
		this.idGp = idGp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sessionBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:11:13
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sessionBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 juin 2014 17:11:13
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.showDetail] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 17:48:47
	 * @return the showDetail
	 */
	public boolean getShowDetail() {
		return showDetail;
	}

	/**
	 * [GroupePedagogiqueManagerBean.showDetail] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 17:48:47
	 * @param showDetail
	 *            the showDetail to set
	 */
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;

	}

	/**
	 * [GroupePedagogiqueManagerBean.showTable] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 09:08:46
	 * @return the showTable
	 */
	public boolean getShowTable() {
		return showTable;
	}

	/**
	 * [GroupePedagogiqueManagerBean.showTable] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 09:08:46
	 * @param showTable
	 *            the showTable to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * [GroupePedagogiqueManagerBean.typeGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:36:50
	 * @return the typeGroupe
	 */
	public String getTypeGroupe() {
		typeGroupe = UtilConstants.REF_TYPE_GROUPE_PEDAGOGIQUE_CODE;
		return typeGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.typeGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:36:50
	 * @param typeGroupe
	 *            the typeGroupe to set
	 */
	public void setTypeGroupe(String typeGroupe) {
		this.typeGroupe = typeGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sectionChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 11:19:33
	 * @return the sectionChange
	 */
	public boolean isSectionChange() {
		return sectionChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sectionChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 11:19:33
	 * @param sectionChange
	 *            the sectionChange to set
	 */
	public void setSectionChange(boolean sectionChange) {
		this.sectionChange = sectionChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sectionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 18:42:02
	 * @return the sectionList
	 */
	public List<SelectItem> getSectionList() {
		return sectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.sectionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 18:42:02
	 * @param sectionList
	 *            the sectionList to set
	 */
	public void setSectionList(List<SelectItem> sectionList) {
		this.sectionList = sectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.typeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 11:11:35
	 */
	public void typeChanged() {
		if (groupePedagogiqueDto.getNcTypeGroupeId().equals(idSectionType)) {
			groupePedagogiqueDto.setIdSection(null);
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.idPeriode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 09:55:07
	 * @return the idPeriode
	 */
	// public Integer getIdPeriode() {
	// return idPeriode;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.idPeriode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 09:55:07
	 * @param idPeriode
	 *            the periodeId to set
	 */
	// public void setIdPeriode(Integer idPeriode) {
	// if (idPeriode != null && idPeriode.equals("null")) {
	// this.idPeriode = null;
	// } else {
	// this.idPeriode = idPeriode;
	// }
	// }

	/**
	 * [GroupePedagogiqueManagerBean.idAnnee] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juil. 2014 12:22:03
	 * @return the idAnnee
	 */
	// public Integer getIdAnnee() {
	// // idAnnee = groupePedagogiqueSearchBean.getIdAnnee();
	// return idAnnee;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.idAnnee] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juil. 2014 12:22:03
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	// public void setIdAnnee(Integer idAnnee) {
	// this.idAnnee = idAnnee;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.idOof] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juil. 2014 12:22:03
	 * @return the idOof
	 */
	// public Integer getIdOof() {
	// // idOof = groupePedagogiqueSearchBean.getIdOof();
	// return idOof;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.idOof] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juil. 2014 12:22:03
	 * @param idOof
	 *            the idOof to set
	 */
	// public void setIdOof(Integer idOof) {
	// this.idOof = idOof;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.deleteGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 ao�t 2014 09:03:38
	 */
	public void deleteGroupe() {

		try {
			List<AssociationGroupePedagogiqueDto> apResult = associationGroupePedagogiqueService
					.findByGroupePedagogiqueId(groupePedagogiqueDto.getId());
			if (apResult != null && apResult.size() > 0) {
				Utility.showErrorMessage(bundleGroupe
						.getString("groupePedagogique_suppression_groupe_association_existe"));
				return;
			}
			List<AffectationGroupePedagogiqueDto> AffResult = affectationGroupePedagogiqueService
					.findByGroupePedagogiqueId(groupePedagogiqueDto.getId());
			if (AffResult != null && AffResult.size() > 0) {
				Utility.showErrorMessage(bundleGroupe
						.getString("groupePedagogique_suppression_groupe_affectation_etudiant_existe"));
				return;
			}
			groupePedagogiqueService.remove(groupePedagogiqueDto);
			Utility.showSuccessDeleteMessage();
			setEntityGroupeChange(false);
			setSectionChange(false);
			loadGroupePedagogiqueList();
			loadSectionList();
			newGroupe();
			// if (groupePedagogiqueSearchBean != null) {
			// groupePedagogiqueSearchBean.loadGroupePedagogiqueList();
			// groupePedagogiqueSearchBean.newGroupe();
			// }

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.affectationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 10:16:43
	 * @return the affectationGroupePedagogiqueService
	 */
	public AffectationGroupePedagogiqueService getAffectationGroupePedagogiqueService() {
		return affectationGroupePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.affectationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 10:16:43
	 * @param affectationGroupePedagogiqueService
	 *            the affectationGroupePedagogiqueService to set
	 */
	public void setAffectationGroupePedagogiqueService(
			AffectationGroupePedagogiqueService affectationGroupePedagogiqueService) {
		this.affectationGroupePedagogiqueService = affectationGroupePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadSectionAp] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:17:35
	 */
	public void loadSectionAp() {
		apSectionList = new ArrayList<String>();
		tmpApSectionList = new HashMap<String, AssociationGroupePedagogiqueDto>();
		selectedAp = new ArrayList<String>();
		if (groupePedagogiqueDto.getOfId() != null
				&& searchBean.getPeriodeId() != null) {
			List<RattachementMcDto> rMcList = rattachementMcService
					.findByOffreFormationAndPeriode(
							groupePedagogiqueDto.getOfId(),
							searchBean.getPeriodeId());
			if (rMcList != null) {
				for (RattachementMcDto rMc : rMcList) {
					AssociationGroupePedagogiqueDto _assoc = new AssociationGroupePedagogiqueDto();
					_assoc.setGroupePedagogiqueId(groupePedagogiqueDto.getId());
					_assoc.setRattachementMcId(rMc.getId());
					_assoc.setGroupePedagogiqueId(groupePedagogiqueDto.getId());
					_assoc.setRattachementMcOptionnelle(rMc.getOptionnelle());
					if (groupePedagogiqueDto.getIdSection() != null) {
						/***** cas d'un groupe d'une section ******/
						fillApList(_assoc, false);
					} else if (groupePedagogiqueDto.getNcTypeGroupeId() == null
							|| idSectionType == null) {
						/***** cas d'un groupe sans section ******/
						fillApList(_assoc, null);
					} else if (groupePedagogiqueDto.getNcTypeGroupeId().equals(
							idSectionType)) {
						/***** cas d'une section ******/
						fillApList(_assoc, true);
					} else {
						/***** cas d'un groupe sans section ******/
						fillApList(_assoc, null);
					}
				}
			}
		}
		tmpSelectedAp = selectedAp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.fillApList] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 oct. 2014 07:36:31
	 * @param _assoc
	 * @param mc
	 */
	public void fillApList(AssociationGroupePedagogiqueDto _assocInit,
			Boolean onlyMc) {
		List<AtomePedagogiqueDto> _list = atomePedagogiqueService
				.findByRattachementMC(_assocInit.getRattachementMcId());
		for (AtomePedagogiqueDto _atome : _list) {
			if (onlyMc != null && _atome.getNcTypeApCode() != null) {
				if (onlyMc
						&& !_atome
								.getNcTypeApCode()
								.toLowerCase()
								.equals(Const.NC_LMD_TYPE_AP_MC_CODE_VALUE
										.toLowerCase())) {
					continue;
				} else if (!onlyMc
						&& _atome
								.getNcTypeApCode()
								.toLowerCase()
								.equals(Const.NC_LMD_TYPE_AP_MC_CODE_VALUE
										.toLowerCase())) {
					continue;
				}
			}
			NomenclatureDto _ncAp = nomenclatureService.findByCode(
					Const.NC_LMD_TYPE_AP, _atome.getNcTypeApCode());
			if (_ncAp != null) {
				AssociationGroupePedagogiqueDto _assoc = new AssociationGroupePedagogiqueDto();
				_assoc.setGroupePedagogiqueId(_assocInit
						.getGroupePedagogiqueId());
				_assoc.setRattachementMcId(_assocInit.getRattachementMcId());
				_assoc.setRattachementMcId(_assocInit.getRattachementMcId());
				_assoc.setRattachementMcOptionnelle(_assocInit
						.getRattachementMcOptionnelle());
				_assoc.setApId(_atome.getId());
				_assoc.setId(null);
				String key = _ncAp.getCode() + " " + _atome.getMcLibelleFr();
				// key = key.replace(" ", "");
				apSectionList.add(key);

				AssociationGroupePedagogiqueDto assocExist = associationGroupePedagogiqueService
						.findUnique(_assoc.getGroupePedagogiqueId(),
								_assoc.getRattachementMcId(), _assoc.getApId());
				if (assocExist != null) {
					_assoc.setId(assocExist.getId());
					selectedAp.add(key);
				}

				tmpApSectionList.put(key, _assoc);
			}
		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:44:25
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:44:25
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idOf] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:08
	 * @return the idOf
	 */
	public Integer getIdOf() {
		return idOf;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idOf] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:08
	 * @param idOf
	 *            the idOf to set
	 */
	public void setIdOf(Integer idOf) {
		this.idOf = idOf;
	}

	/**
	 * [GroupePedagogiqueManagerBean.repartitionUeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:39
	 * @return the repartitionUeService
	 */
	public RepartitionUeService getRepartitionUeService() {
		return repartitionUeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.repartitionUeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:39
	 * @param repartitionUeService
	 *            the repartitionUeService to set
	 */
	public void setRepartitionUeService(
			RepartitionUeService repartitionUeService) {
		this.repartitionUeService = repartitionUeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.rattachementMcService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:39
	 * @return the rattachementMcService
	 */
	public RattachementMcService getRattachementMcService() {
		return rattachementMcService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.rattachementMcService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:45:39
	 * @param rattachementMcService
	 *            the rattachementMcService to set
	 */
	public void setRattachementMcService(
			RattachementMcService rattachementMcService) {
		this.rattachementMcService = rattachementMcService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.atomePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:46:06
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.atomePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:46:06
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.entityAssociationChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:47:16
	 * @param event
	 */
	public void entityAssociationChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityAssociationChange = true;
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.entityAssociationChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:47:37
	 * @return the entityAssociationChange
	 */
	public boolean isEntityAssociationChange() {
		return entityAssociationChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.entityAssociationChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:47:37
	 * @param entityAssociationChange
	 *            the entityAssociationChange to set
	 */
	public void setEntityAssociationChange(boolean entityAssociationChange) {
		this.entityAssociationChange = entityAssociationChange;
	}

	/**
	 * [GroupePedagogiqueManagerBean.saveAssociation] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:49:04
	 */
	public void saveAssociation() {

		try {
			if (selectedAp != null) {
				/*****
				 * v�rifier s'il ya un m�lange entre les mati�re
				 * optionnelle, et les mati�re obligatoire
				 ***/
				List<Boolean> mcOptionelList = new ArrayList<Boolean>();
				for (String _assoGp : selectedAp) {
					AssociationGroupePedagogiqueDto _assoc = tmpApSectionList
							.get(_assoGp);
					mcOptionelList.add(_assoc.getRattachementMcOptionnelle());
				}
				Boolean mix = null;
				for (Boolean bol : mcOptionelList) {
					if (mix == null) {
						mix = bol;
					} else if (bol != mix) {
						Utility.showErrorMessage(bundleGroupe
								.getString("groupePedagogique_association_melange_mc_optionnelle_obligatoire"));
						return;
					}
				}
				for (String _assoGp : selectedAp) {
					AssociationGroupePedagogiqueDto _assoc = tmpApSectionList
							.get(_assoGp);
					if (_assoc != null && _assoc.getId() == null) {
						// if (associationGroupePedagogiqueService
						// .groupeNameExist(idOof, idPeriode,
						// groupePedagogiqueDto.getNomAffichage(),
						// _assoc)) {
						// Utility.showErrorMessage(bundleGroupe
						// .getString("groupepedagogique_erreur_association_nom_existe"));
						// return;
						// }
						associationGroupePedagogiqueService
								.insertOrUpdate(_assoc);
					}
					groupePedagogiqueDto.setMcOptionnelle(mix);
					groupePedagogiqueService
							.insertOrUpdate(groupePedagogiqueDto);
				}
				for (Entry<String, AssociationGroupePedagogiqueDto> entry : tmpApSectionList
						.entrySet()) {
					String key = entry.getKey();
					AssociationGroupePedagogiqueDto value = entry.getValue();
					boolean toRemove = true;
					for (String _assoGp : selectedAp) {
						if (_assoGp.equals(key)) {
							toRemove = false;
							break;
						}
					}

					if (value.getId() != null && toRemove) {
						if (selectedAp.isEmpty()) {
							List<AffectationGroupePedagogiqueDto> AffResult = affectationGroupePedagogiqueService
									.findByGroupePedagogiqueId(groupePedagogiqueDto
											.getId());
							if (AffResult != null && AffResult.size() > 0) {
								Utility.showErrorMessage(bundleGroupe
										.getString("groupePedagogique_suppression_groupe_affectation_etudiant_existe"));
								selectedAp = tmpSelectedAp;
								return;
							}
						}

						AssociationGroupePedagogiqueDto _assocRemove = associationGroupePedagogiqueService
								.findById(value.getId());
						if (_assocRemove != null) {
							associationGroupePedagogiqueService
									.remove(_assocRemove);

						}
					}

				}
				Utility.showSuccessSaveMessage();
				loadGroupePedagogiqueList();
				loadingAssociation();
				setEntityAssociationChange(false);
				RequestContext.getCurrentInstance().execute(
						"gpWidget.enable(2)");
				groupeAffectationBean.setEditMode(editMode);
				groupeAffectationBean.setIdSectionType(idSectionType);
				groupeAffectationBean.loading();
				return;
			}

			// if (groupePedagogiqueDto.getCapaciteMax() != null
			// && associationGroupePedagogiqueDto.getApSeuil() != null
			// && groupePedagogiqueDto.getCapaciteMax() <
			// associationGroupePedagogiqueDto
			// .getApSeuil()) {
			// groupePedagogiqueDto
			// .setCapaciteMax(associationGroupePedagogiqueDto
			// .getApSeuil());
			// }
			//
			// RattachementMcDto _rMc = rattachementMcService
			// .findById(associationGroupePedagogiqueDto
			// .getRattachementMcId());
			// if (_rMc == null || _rMc.getOptionnelle() == null) {
			// groupePedagogiqueDto.setMcOptionnelle(false);
			// } else {
			// groupePedagogiqueDto.setMcOptionnelle(_rMc.getOptionnelle());
			// }
			// groupePedagogiqueService.insertOrUpdate(groupePedagogiqueDto);

		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
			log.info(e.getMessage());
		}

	}

	/**
	 * [GroupePedagogiqueManagerBean.groupeAffectationBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:49:47
	 * @return the groupeAffectationBean
	 */
	public GroupeAffectationBean getGroupeAffectationBean() {
		return groupeAffectationBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupeAffectationBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 16:49:47
	 * @param groupeAffectationBean
	 *            the groupeAffectationBean to set
	 */
	public void setGroupeAffectationBean(
			GroupeAffectationBean groupeAffectationBean) {
		this.groupeAffectationBean = groupeAffectationBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.listGroupePedagogiqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:02:50
	 * @return the listGroupePedagogiqueDto
	 */
	public List<GroupePedagogiqueDto> getListGroupePedagogiqueDto() {
		return listGroupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.listGroupePedagogiqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:02:50
	 * @param listGroupePedagogiqueDto
	 *            the listGroupePedagogiqueDto to set
	 */
	public void setListGroupePedagogiqueDto(
			List<GroupePedagogiqueDto> listGroupePedagogiqueDto) {
		this.listGroupePedagogiqueDto = listGroupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:04:27
	 */
	// public void anneeChanged() {
	// loadOffreFormationOuverte();
	// if (idAnnee != null) {
	// // setAnneeId(idAnnee.toString());
	// }
	// }

	/**
	 * [GroupePedagogiqueManagerBean.ofChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:51:53
	 */
	// public void ofChanged() {
	// loadNiveaux();
	// loadPeriodeList();
	// if (offreFormationList != null) {
	// for (SelectItem item : offreFormationList) {
	// if (item.getValue().equals(idOof)) {
	// ofLibelleFr = item.getLabel();
	// }
	// }
	// }
	//
	// }

	/**
	 * [GroupePedagogiqueManagerBean.loadOffreFormationOuverte] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:04:45
	 */
	// public void loadOffreFormationOuverte() {
	// offreFormationList = new ArrayList<SelectItem>();
	//
	// try {
	// if (idAnnee == null) {
	// return;
	// }
	// offreFormationList = utilBean.loadOffreFormationOuverte(idAnnee);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * [GroupePedagogiqueManagerBean.loadCycle] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 09:25:59
	 */
	public void loadCycle() {
		if (searchBean.getOofId() != null) {
			OuvertureOffreFormationDto oofDto = ouvertureOffreFormationService
					.findById(searchBean.getOofId());
			if (oofDto != null) {
				setIdCycle(oofDto.getCycleId());
			}
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.findOfItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:05:13
	 * @param label
	 * @return
	 */
	// private SelectItem findOfItem(String label) {
	// for (SelectItem _item : offreFormationList) {
	// if (_item.getLabel().equals(label)) {
	// return _item;
	// }
	// }
	// return null;
	// }

	/**
	 * [GroupePedagogiqueManagerBean.offreFormationList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:05:37
	 * @return the offreFormationList
	 */
	public List<SelectItem> getOffreFormationList() {
		return offreFormationList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.offreFormationList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:05:37
	 * @param offreFormationList
	 *            the offreFormationList to set
	 */
	public void setOffreFormationList(List<SelectItem> offreFormationList) {
		this.offreFormationList = offreFormationList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.offreFormationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:06:53
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.offreFormationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:06:53
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.anneeAcademiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:08:18
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.anneeAcademiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:08:18
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.anneeAcademiqueList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:08:41
	 * @return the anneeAcademiqueList
	 */
	public List<SelectItem> getAnneeAcademiqueList() {
		return anneeAcademiqueList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.anneeAcademiqueList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:08:41
	 * @param anneeAcademiqueList
	 *            the anneeAcademiqueList to set
	 */
	public void setAnneeAcademiqueList(List<SelectItem> anneeAcademiqueList) {
		this.anneeAcademiqueList = anneeAcademiqueList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.periodeList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:02:37
	 * @return the periodeList
	 */
	public List<SelectItem> getPeriodeList() {
		return periodeList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.periodeList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:02:37
	 * @param periodeList
	 *            the periodeList to set
	 */
	public void setPeriodeList(List<SelectItem> periodeList) {
		this.periodeList = periodeList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.findAdvanced] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:09:09
	 */
	public void findAdvanced() {
		showDetail = false;
		setShowTable(false);
		try {

			if (searchBean.getOofId() != null
					&& searchBean.getAnneeAcademiqueId() != null) {

				GroupePedagogiqueDto searchDto = new GroupePedagogiqueDto();
				searchDto.setOofId(searchBean.getOofId());
				searchDto.setCodeEtablissement(sessionBean
						.getCodeEtablissement());
				searchDto.setIdAnneeAcademique(searchBean
						.getAnneeAcademiqueId());
				searchDto.setPeriodeId(searchBean.getPeriodeId());
				List<GroupePedagogiqueDto> result = groupePedagogiqueService
						.findAdvanced(searchDto);
				if (result == null || result.isEmpty()) {
					setListGroupePedagogiqueDto(null);
					Utility.showWarningMessage(bundleCommon
							.getString("warn_info_aucun_result"));

				} else {

					setListGroupePedagogiqueDto(result);
					prepareTree();
					setShowTable(true);
				}
			}
		} catch (Exception e) {
			setListGroupePedagogiqueDto(null);
			Utility.showErrorMessage(e.getMessage());
		}
		// setShowDetail(false);

	}

	/**
	 * [GroupePedagogiqueManagerBean.newGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:10:01
	 */
	public void newGroupe() {
		Integer defaultSection = groupePedagogiqueDto.getId();
		this.groupePedagogiqueDto = new GroupePedagogiqueDto();
		groupePedagogiqueDto.setIdAnneeAcademique(searchBean
				.getAnneeAcademiqueId());
		groupePedagogiqueDto.setOofId(searchBean.getOofId());
		groupePedagogiqueDto.setPeriodeId(searchBean.getPeriodeId());
		groupePedagogiqueDto.setDateCreation(new Date());
		groupePedagogiqueDto.setCapaciteMin(1);
		groupePedagogiqueDto.setIdSection(defaultSection);
		groupePedagogiqueDto.setNcTypeGroupeId(idGroupeType);
		sectionChanged();
		groupeAffectationBean.setGroupePedagogiqueDto(groupePedagogiqueDto);
		loadingAssociation();
		groupeAffectationBean.setIdSectionType(idSectionType);
		groupeAffectationBean.loading();
		setShowDetail(true);
		loadNcTypeGroupeList();
		loadSectionList();
	}

	/**
	 * [GroupePedagogiqueManagerBean.onRowSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 13 ao�t 2014 17:11:38
	 * @param event
	 */
	// public void onRowTogglerSelect(AjaxBehaviorEvent event) {
	// System.out.println(event);
	// GroupePedagogiqueDto _gp = ((GroupePedagogiqueDto) event.getObject());
	// if (_gp != null && _gp.getId() == groupePedagogiqueDto.getId()) {
	// return;
	// }
	// this.groupePedagogiqueDto = _gp;
	// groupePedagogiqueDto.setOfLibelleFr(ofLibelleFr);
	// loading();
	// groupeAffectationBean
	// .setGroupePedagogiqueDto(this.groupePedagogiqueDto);
	// groupeAffectationBean.setEditMode(editMode);
	// groupeAffectationBean.setIdSectionType(idSectionType);
	// groupeAffectationBean.loading();
	//
	// setShowDetail(true);
	// }

	/**
	 * [GroupePedagogiqueManagerBean.onRowTogglerSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 12:44:49
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		GroupeComposit _gc = (GroupeComposit) event.getObject();
		if (_gc != null) {
			GroupePedagogiqueDto _gp = _gc.getSection();
			if (_gp != null && _gp.getId() == groupePedagogiqueDto.getId()) {
				return;
			}
			setShowDetail(false);
			this.groupePedagogiqueDto = _gp;
			loading();
			groupeAffectationBean
					.setGroupePedagogiqueDto(this.groupePedagogiqueDto);
			groupeAffectationBean.setEditMode(editMode);
			groupeAffectationBean.setIdSectionType(idSectionType);
			groupeAffectationBean.loading();

			setShowDetail(true);
		}
	}

	// public void onRowSelect(AjaxBehaviorEvent event) {
	// System.out.println(event);
	//
	// setShowDetail(true);
	// }

	/**
	 * [GroupePedagogiqueManagerBean.periodeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:04:02
	 * @return the periodeService
	 */
	public PeriodeService getPeriodeService() {
		return periodeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.periodeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 sept. 2014 10:04:02
	 * @param periodeService
	 *            the periodeService to set
	 */
	public void setPeriodeService(PeriodeService periodeService) {
		this.periodeService = periodeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idCycle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 09:14:40
	 * @return the idCycle
	 */
	public Integer getIdCycle() {
		return idCycle;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idCycle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 09:14:40
	 * @param idCycle
	 *            the idCycle to set
	 */
	public void setIdCycle(Integer idCycle) {
		this.idCycle = idCycle;
	}

	/**
	 * [GroupePedagogiqueManagerBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 17:29:45
	 * @return the editMode
	 */
	public boolean getEditMode() {
		return editMode;
	}

	/**
	 * [GroupePedagogiqueManagerBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 sept. 2014 17:29:45
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idNiveau] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:22:12
	 * @return the idNiveau
	 */
	public Integer getIdNiveau() {
		return idNiveau;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idNiveau] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:22:12
	 * @param idNiveau
	 *            the idNiveau to set
	 */
	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}

	/**
	 * [GroupePedagogiqueManagerBean.niveauChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:24:46
	 */
	public void niveauChanged() {
		loadPeriodeList();
	}

	/**
	 * [GroupePedagogiqueManagerBean.periodeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 24 déc. 2014 12:02:55
	 */
	public void periodeChanged() {
		groupePedagogiqueDto = new GroupePedagogiqueDto();
		findAdvanced();
	}

	/**
	 * [GroupePedagogiqueManagerBean.niveauList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:24:57
	 * @return the niveauList
	 */
	public List<SelectItem> getNiveauList() {
		return niveauList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.niveauList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:24:57
	 * @param niveauList
	 *            the niveauList to set
	 */
	public void setNiveauList(List<SelectItem> niveauList) {
		this.niveauList = niveauList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.loadNiveaux] Method
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:25:16
	 */
	// private void loadNiveaux() {
	// try {
	// niveauList = new ArrayList<SelectItem>();
	// if (idOof == null) {
	// return;
	// }
	// OuvertureOffreFormationDto _oof = ouvertureOffreFormationService
	// .findById(idOof);
	// List<NiveauDto> list = niveauService.findByCycleId(_oof
	// .getCycleId());
	//
	// for (NiveauDto niveauDto : list) {
	// SelectItem item = new SelectItem(niveauDto.getId(),
	// niveauDto.getLibelleLongLt());
	// niveauList.add(item);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * [GroupePedagogiqueManagerBean.niveauService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:25:49
	 * @return the niveauService
	 */
	public NiveauService getNiveauService() {
		return niveauService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.niveauService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 10:25:49
	 * @param niveauService
	 *            the niveauService to set
	 */
	public void setNiveauService(NiveauService niveauService) {
		this.niveauService = niveauService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refGroupeService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:16:57
	 * @return the refGroupeService
	 */
	public RefGroupeService getRefGroupeService() {
		return refGroupeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refGroupeService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:16:57
	 * @param refGroupeService
	 *            the refGroupeService to set
	 */
	public void setRefGroupeService(RefGroupeService refGroupeService) {
		this.refGroupeService = refGroupeService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.nomenclatureService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:22:08
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.nomenclatureService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:22:08
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refAffectationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:27:49
	 * @return the refAffectationService
	 */
	public RefAffectationService getRefAffectationService() {
		return refAffectationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refAffectationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 11:27:49
	 * @param refAffectationService
	 *            the refAffectationService to set
	 */
	public void setRefAffectationService(
			RefAffectationService refAffectationService) {
		this.refAffectationService = refAffectationService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refParametreEtabService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 15:07:43
	 * @return the refParametreEtabService
	 */
	public RefParametreEtabService getRefParametreEtabService() {
		return refParametreEtabService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.refParametreEtabService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 oct. 2014 15:07:43
	 * @param refParametreEtabService
	 *            the refParametreEtabService to set
	 */
	public void setRefParametreEtabService(
			RefParametreEtabService refParametreEtabService) {
		this.refParametreEtabService = refParametreEtabService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associationGroupePedagogiqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 10:37:52
	 * @return the associationGroupePedagogiqueDto
	 */
	public AssociationGroupePedagogiqueDto getAssociationGroupePedagogiqueDto() {
		return associationGroupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associationGroupePedagogiqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 10:37:52
	 * @param associationGroupePedagogiqueDto
	 *            the associationGroupePedagogiqueDto to set
	 */
	public void setAssociationGroupePedagogiqueDto(
			AssociationGroupePedagogiqueDto associationGroupePedagogiqueDto) {
		this.associationGroupePedagogiqueDto = associationGroupePedagogiqueDto;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associationGroupePedagogiqueService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 10:44:59
	 * @return the associationGroupePedagogiqueService
	 */
	public AssociationGroupePedagogiqueService getAssociationGroupePedagogiqueService() {
		return associationGroupePedagogiqueService;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associationGroupePedagogiqueService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 10:44:59
	 * @param associationGroupePedagogiqueService
	 *            the associationGroupePedagogiqueService to set
	 */
	public void setAssociationGroupePedagogiqueService(
			AssociationGroupePedagogiqueService associationGroupePedagogiqueService) {
		this.associationGroupePedagogiqueService = associationGroupePedagogiqueService;
	}

	/**
	 * [GroupePedagogique.onRowAssociationSelect] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 11:12:14
	 * @param event
	 */
	public void onRowAssociationSelect(SelectEvent event) {
		this.associationGroupePedagogiqueDto = ((AssociationGroupePedagogiqueDto) event
				.getObject());
		entityAssociationChange = false;
		editMode = true;
	}

	/**
	 * [GroupePedagogiqueManagerBean.addAssociation] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 11:22:04
	 */
	public void addAssociation() {
		// loadingAssociation();
		associationGroupePedagogiqueDto = new AssociationGroupePedagogiqueDto();
		associationGroupePedagogiqueDto
				.setGroupePedagogiqueId(groupePedagogiqueDto.getId());

	}

	/**
	 * [GroupePedagogiqueManagerBean.apSectionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:24:48
	 * @return the apSectionList
	 */
	public List<String> getApSectionList() {
		return apSectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.apSectionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:24:48
	 * @param apSectionList
	 *            the apSectionList to set
	 */
	public void setApSectionList(List<String> apSectionList) {
		this.apSectionList = apSectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.tmpApSectionList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 17:18:59
	 * @return the tmpApSectionList
	 */
	public Map<String, AssociationGroupePedagogiqueDto> getTmpApSectionList() {
		return tmpApSectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.tmpApSectionList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 17:18:59
	 * @param tmpApSectionList
	 *            the tmpApSectionList to set
	 */
	public void setTmpApSectionList(
			Map<String, AssociationGroupePedagogiqueDto> tmpApSectionList) {
		this.tmpApSectionList = tmpApSectionList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.selectedAp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:27:01
	 * @return the selectedAp
	 */
	public List<String> getSelectedAp() {
		return selectedAp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.selectedAp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:27:01
	 * @param selectedAp
	 *            the selectedAp to set
	 */
	public void setSelectedAp(List<String> selectedAp) {
		this.selectedAp = selectedAp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupeOfSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:29:14
	 * @return the groupeOfSection
	 */
	public Boolean getGroupeOfSection() {
		return groupeOfSection;
	}

	/**
	 * [GroupePedagogiqueManagerBean.groupeOfSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 15:29:14
	 * @param groupeOfSection
	 *            the groupeOfSection to set
	 */
	public void setGroupeOfSection(Boolean groupeOfSection) {
		this.groupeOfSection = groupeOfSection;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idSectionType] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 16:38:25
	 * @return the idSectionType
	 */
	public Integer getIdSectionType() {
		return idSectionType;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idSectionType] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 oct. 2014 16:38:25
	 * @param idSectionType
	 *            the idSectionType to set
	 */
	public void setIdSectionType(Integer idSectionType) {
		this.idSectionType = idSectionType;
	}

	/**
	 * [GroupePedagogiqueManagerBean.apCheckAll] Method
	 * 
	 * @author MAKERRI Sofiane on : 8 oct. 2014 08:59:16
	 * @param check
	 */
	public void apCheckAll() {
		if (tmpApSectionList != null) {
			selectedAp = new ArrayList<String>();
			if (checkAll) {
				for (Entry<String, AssociationGroupePedagogiqueDto> entry : tmpApSectionList
						.entrySet()) {
					String key = entry.getKey();
					selectedAp.add(key);
				}
			}
		}
	}

	/**
	 * [GroupePedagogiqueManagerBean.checkAll] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 oct. 2014 09:11:17
	 * @return the checkAll
	 */
	public boolean isCheckAll() {
		return checkAll;
	}

	/**
	 * [GroupePedagogiqueManagerBean.checkAll] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 oct. 2014 09:11:17
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * [GroupePedagogiqueManagerBean.tmpSelectedAp] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 14:59:49
	 * @return the tmpSelectedAp
	 */
	public List<String> getTmpSelectedAp() {
		return tmpSelectedAp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.tmpSelectedAp] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 14:59:49
	 * @param tmpSelectedAp
	 *            the tmpSelectedAp to set
	 */
	public void setTmpSelectedAp(List<String> tmpSelectedAp) {
		this.tmpSelectedAp = tmpSelectedAp;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associatedApDetailList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 15:21:47
	 * @return the associatedApDetailList
	 */
	public List<AssociationGroupePedagogiqueDto> getAssociatedApDetailList() {
		return associatedApDetailList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.associatedApDetailList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 oct. 2014 15:21:47
	 * @param associatedApDetailList
	 *            the associatedApDetailList to set
	 */
	public void setAssociatedApDetailList(
			List<AssociationGroupePedagogiqueDto> associatedApDetailList) {
		this.associatedApDetailList = associatedApDetailList;
	}

	/**
	 * [GroupePedagogiqueManagerBean.listSectionGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 12:04:17
	 * @return the listSectionGroupe
	 */
	public List<GroupeComposit> getListSectionGroupe() {
		return listSectionGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.listSectionGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 oct. 2014 12:04:17
	 * @param listSectionGroupe
	 *            the listSectionGroupe to set
	 */
	public void setListSectionGroupe(List<GroupeComposit> listSectionGroupe) {
		this.listSectionGroupe = listSectionGroupe;
	}

	/**
	 * [GroupePedagogiqueManagerBean.selectCurrent] Method
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 09:48:49
	 * @param current
	 */
	public void selectCurrent(GroupePedagogiqueDto current) {
		this.groupePedagogiqueDto = current;
		loading();
		groupeAffectationBean
				.setGroupePedagogiqueDto(this.groupePedagogiqueDto);
		groupeAffectationBean.setEditMode(editMode);
		groupeAffectationBean.setIdSectionType(idSectionType);
		groupeAffectationBean.loading();

		setShowDetail(true);
	}

	/**
	 * [GroupePedagogiqueManagerBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 déc. 2014 17:35:32
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 déc. 2014 17:35:32
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGroupeType] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 déc. 2014 11:45:19
	 * @return the idGroupeType
	 */
	public Integer getIdGroupeType() {
		return idGroupeType;
	}

	/**
	 * [GroupePedagogiqueManagerBean.idGroupeType] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 déc. 2014 11:45:19
	 * @param idGroupeType
	 *            the idGroupeType to set
	 */
	public void setIdGroupeType(Integer idGroupeType) {
		this.idGroupeType = idGroupeType;
	}

	/**
	 * [GroupePedagogiqueManagerBean.searchBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 31 d�c. 2014 16:32:51
	 * @return the searchBean
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * [GroupePedagogiqueManagerBean.searchBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 31 d�c. 2014 16:32:51
	 * @param searchBean
	 *            the searchBean to set
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

}
