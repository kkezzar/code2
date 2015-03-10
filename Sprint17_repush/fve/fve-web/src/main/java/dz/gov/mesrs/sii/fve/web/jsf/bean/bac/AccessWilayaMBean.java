/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:07:38
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.bac;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.AccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.bac.AccessWilayaService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:07:38
 */
@ManagedBean(name = "accessWilayaMBean")
@ViewScoped
public class AccessWilayaMBean {

	@ManagedProperty(value = "#{accessWilayaService}")
	private AccessWilayaService accessWilayaService;

	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;

	@ManagedProperty(value = "#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty(value = "#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty(value = "#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	/**
	 * [AccessWilayaMBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:31:41
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [AccessWilayaMBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:31:41
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [AccessWilayaMBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:30:08
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [AccessWilayaMBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:30:08
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [AccessWilayaMBean.refIndividuService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:02:53
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [AccessWilayaMBean.refIndividuService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:02:53
	 * @param refIndividuService
	 *            the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [AccessWilayaMBean.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:02:53
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [AccessWilayaMBean.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 14:02:53
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	// ===================================================================
	// Web Services for Lists
	// ===================================================================
	// id =
	// Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
	private String keyWords;

	private List<AccessWilayaDto> accessWilayaList;
	private AccessWilayaDto accessWilayaDto;

	// UI
	private String refCodeCurrEtablissement;
	private Integer idCurrAnneeAcademique;
	private String refCodeCurrAnneeAcademique;
	private Integer idAnneeAcademique;
	private List<SelectItem> listAnneeAcademique;
	private List<SelectItem> listDomaines;
	private List<SelectItem> listFilieres;
	private DualListModel<DroitAccessWilayaDto> defaultWilayas;
	private DualListModel<DroitAccessWilayaDto> wilayas;
	private boolean isCrud;
	private boolean isView;
	private ResourceBundle bundleCommon;
	private AccessWilayaDto searchDto;

	/**
	 * Constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:02:39
	 */
	public AccessWilayaMBean() {

	}

	/**
	 * Post constructeur
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:37:42
	 */
	@PostConstruct
	public void init() {
		try {

			FacesContext facesContext = FacesContext.getCurrentInstance();
			bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
					CursusConstants.COMMON_BUNDLE_MSG_NAME);

			this.isCrud = false;
			this.refCodeCurrEtablissement = sessionBean.getCodeEtablissement();
			this.idCurrAnneeAcademique = sessionBean.getIdAnneeAcademique();
			this.refCodeCurrAnneeAcademique = sessionBean.getCodeAnneeAcademique();

			this.idAnneeAcademique = this.idCurrAnneeAcademique;

			this.resetAccessWilayaDto();
			searchDto = new AccessWilayaDto(this.idAnneeAcademique, this.refCodeCurrEtablissement);

			this.loadSearchResults();

			// preparer la liste des domaines
			listDomaines = new ArrayList<SelectItem>();

			// recuperer la liste des domaines affectes a l'etablissement en
			// cours
			// TODO a remplacer par le code en bas
			List<RefDomaineLMDDto> listDomainesDto = refDomaineLMDService.findAdvanced(new RefDomaineLMDDto());
			for (RefDomaineLMDDto ld : listDomainesDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(), ld.getLlDomaine() + " (" + ld.getIdentifiant()
						+ ")");
				listDomaines.add(itemFr);
			}

			// List<RefAffectDomLmdEtabDto> listDomainesDto = ppmWSClient
			// .getAllDomainesLMDByCodeEtablissement(this.refCodeCurrEtablissement);
			// for (RefAffectDomLmdEtabDto ld : listDomainesDto) {
			// SelectItem itemFr = new SelectItem(
			// ld.getIdentifiantDomaineLMD(), ld.getLlDomaine() + " ("
			// + ld.getIdentifiantDomaineLMD() + ")");
			// listDomaines.add(itemFr);
			// }

			// preparer la liste des wilaya
			List<DroitAccessWilayaDto> wilayasTarget = new ArrayList<DroitAccessWilayaDto>();
			List<DroitAccessWilayaDto> wilayasSource = new ArrayList<DroitAccessWilayaDto>();

			List<NomenclatureDto> listWilayas = nomenclatureService.findByName(CursusConstants.NC_WILAYA_NAME);
			for (NomenclatureDto nomenclatureDto : listWilayas) {
				DroitAccessWilayaDto d = new DroitAccessWilayaDto();
				d.setRefCodeWilaya(nomenclatureDto.getCode());
				d.setWilayaLibelleLt(nomenclatureDto.getLibelleLongFr());
				wilayasSource.add(d);
			}

			this.defaultWilayas = new DualListModel<DroitAccessWilayaDto>(wilayasSource, wilayasTarget);
			this.wilayas = defaultWilayas;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 ao�t 2014 09:42:05
	 */
	public void handleAnneeAcademiqueChange() {
		this.isView = false;
		loadSearchResults();
	}

	/**
	 * Charger les resultats de la recherche
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 10:20:12
	 */
	public void loadSearchResults() {
		try {
			// liste des access wilayas
			this.accessWilayaList = accessWilayaService.findAdvanced(searchDto);
			// .findByIdAnneeAcademique(this.idAnneeAcademique);
			for (AccessWilayaDto awdto : accessWilayaList) {
				// RefFiliereLmdDto refFiliereLmdDto =
				// referentielHelper.findFiliere(accessWilayaDto.getRefCodeFiliere());
				if (awdto.getRefCodeFiliere() != null) {
					RefFiliereLmdDto refFiliereLmdDto = refFiliereLmdService.findByIdentifiant(awdto
							.getRefCodeFiliere());
					if (refFiliereLmdDto != null) {
						awdto.setFiliereLibelleLt(refFiliereLmdDto.getLlFiliere());
						awdto.setFiliereLibelleAr(refFiliereLmdDto.getLlDomaineArabe());
					}
				}

				// RefDomaineLMDDto refDomaineLMDDto =
				// referentielHelper.findDomaine(accessWilayaDto.getRefCodeDomaine());
				RefDomaineLMDDto refDomaineLMDDto = refDomaineLMDService.findByIdentifiant(awdto.getRefCodeDomaine());
				if (refDomaineLMDDto != null) {
					awdto.setDomaineLibelleLt(refDomaineLMDDto.getLlDomaine());
					awdto.setDomaineLibelleAr(refDomaineLMDDto.getLlDomaineArabe());
				}
			}

			// preparer la liste des annees academique
			listAnneeAcademique = new ArrayList<SelectItem>();
			List<AnneeAcademiqueDto> liste = anneeAcademiqueService.findAll();
			if (liste != null && !liste.isEmpty()) {
				for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
					SelectItem si = new SelectItem(
					/* anneeAcademiqueDto.getPremiereAnnee() */anneeAcademiqueDto.getId(),
							anneeAcademiqueDto.getPremiereAnnee() + "/" + anneeAcademiqueDto.getDeuxiemeAnnee());
					listAnneeAcademique.add(si);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:37:04
	 * @return
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:37:07
	 * @param sessionBean
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:37:22
	 * @return
	 */
	public String getKeyWords() {
		return keyWords;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 ao�t 2014 09:37:26
	 * @param keyWords
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:51:23
	 * @return
	 */
	public AccessWilayaService getAccessWilayaService() {
		return accessWilayaService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:51:26
	 * @param accessWilayaService
	 */
	public void setAccessWilayaService(AccessWilayaService accessWilayaService) {
		this.accessWilayaService = accessWilayaService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:53:59
	 * @return
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:54:03
	 * @param anneeAcademiqueService
	 */
	public void setAnneeAcademiqueService(AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:54:09
	 * @return
	 */
	public List<AccessWilayaDto> getAccessWilayaList() {
		return accessWilayaList;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 14 ao�t 2014 15:54:13
	 * @param accessWilayaList
	 */
	public void setAccessWilayaList(List<AccessWilayaDto> accessWilayaList) {
		this.accessWilayaList = accessWilayaList;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:43:13
	 * @return
	 */
	public AccessWilayaDto getAccessWilayaDto() {
		return accessWilayaDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:43:16
	 * @param accessWilayaDto
	 */
	public void setAccessWilayaDto(AccessWilayaDto accessWilayaDto) {
		this.accessWilayaDto = accessWilayaDto;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:43
	 * @return
	 */
	public List<SelectItem> getListDomaines() {
		return listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:30
	 * @return
	 */
	public String getRefCodeCurrEtablissement() {
		return refCodeCurrEtablissement;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:26
	 * @param refCodeCurrEtablissement
	 */
	public void setRefCodeCurrEtablissement(String refCodeCurrEtablissement) {
		this.refCodeCurrEtablissement = refCodeCurrEtablissement;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:20
	 * @param codeCurrAnneeAcademique
	 */
	public void setCodeCurrAnneeAcademique(String codeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = codeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:17
	 * @return
	 */
	public String getRefCodeCurrAnneeAcademique() {
		return refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:14
	 * @return
	 */
	public Integer getIdCurrAnneeAcademique() {
		return idCurrAnneeAcademique;
	}

	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:11
	 * @param idCurrAnneeAcademique
	 */
	public void setIdCurrAnneeAcademique(Integer idCurrAnneeAcademique) {
		this.idCurrAnneeAcademique = idCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:09
	 * @return
	 */
	public List<SelectItem> getListAnneeAcademique() {
		return listAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:05
	 * @param listAnneeAcademique
	 */
	public void setListAnneeAcademique(List<SelectItem> listAnneeAcademique) {
		this.listAnneeAcademique = listAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:25:02
	 * @param listDomaines
	 */
	public void setListDomaines(List<SelectItem> listDomaines) {
		this.listDomaines = listDomaines;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:24:59
	 * @return
	 */
	public List<SelectItem> getListFilieres() {
		return listFilieres;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:24:56
	 * @param listFilieres
	 */
	public void setListFilieres(List<SelectItem> listFilieres) {
		this.listFilieres = listFilieres;
	}

	/**
	 * Handle changement du domaine
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 15:14:20
	 * @param event
	 * @throws Exception_Exception
	 */
	public void handleDomainesListChange(AjaxBehaviorEvent event) throws Exception {
		// TODO liste des filieres du domaine accessWilayaDto
		// .getRefCodeDomaine() et l'etaablissement en cour
		if (this.listDomaines != null) {
			listFilieres = new ArrayList<SelectItem>();

			List<RefFiliereLmdDto> listFilieresDto = refFiliereLmdService.findByCodeDomainelmd(accessWilayaDto
					.getRefCodeDomaine());

			for (RefFiliereLmdDto ld : listFilieresDto) {
				SelectItem itemFr = new SelectItem(ld.getIdentifiant(), ld.getLlFiliere() + " (" + ld.getIdentifiant()
						+ ")");
				listFilieres.add(itemFr);

			}
		}

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:24:42
	 * @return
	 */
	public DualListModel<DroitAccessWilayaDto> getWilayas() {
		return wilayas;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 17:24:46
	 * @param wilayas
	 */
	public void setWilayas(DualListModel<DroitAccessWilayaDto> wilayas) {
		this.wilayas = wilayas;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:31
	 * @return
	 */
	public boolean isCrud() {
		return isCrud;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:28
	 * @param isCrud
	 */
	public void setCrud(boolean isCrud) {
		this.isCrud = isCrud;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:25
	 * @param refCodeCurrAnneeAcademique
	 */
	public void setRefCodeCurrAnneeAcademique(String refCodeCurrAnneeAcademique) {
		this.refCodeCurrAnneeAcademique = refCodeCurrAnneeAcademique;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:21
	 */
	public void onTransferWilayas() {
		this.accessWilayaDto.setDroitAccessWilayaDto(wilayas.getTarget());
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 21 ao�t 2014 12:13:48
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		this.accessWilayaDto = (AccessWilayaDto) event.getObject();

		// preparer la liste des wilaya par rapport a la liste des access
		// wilayas deja selectionne
		List<DroitAccessWilayaDto> listWilayasDto = accessWilayaDto.getDroitAccessWilayaDto();

		List<DroitAccessWilayaDto> wilayasTarget = new ArrayList<DroitAccessWilayaDto>();
		List<DroitAccessWilayaDto> wilayasSource = new ArrayList<DroitAccessWilayaDto>();

		try {

			this.handleDomainesListChange(null);

			List<NomenclatureDto> listWilayas = nomenclatureService.findByName(CursusConstants.NC_WILAYA_NAME);
			for (NomenclatureDto nomenclatureDto : listWilayas) {

				DroitAccessWilayaDto d = new DroitAccessWilayaDto();
				d.setRefCodeWilaya(nomenclatureDto.getCode());
				d.setWilayaLibelleLt(nomenclatureDto.getLibelleLongFr());
				// verifier si la wilaya n'existe pas dans la liste des wilayas
				// deja selectionne
				if (!listWilayasDto.contains(d))
					wilayasSource.add(d);
				else
					wilayasTarget.add(d);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		this.wilayas = new DualListModel<DroitAccessWilayaDto>(wilayasSource, wilayasTarget);

		this.editAccessWilayaAction();

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:18
	 */
	public void addAccessWilayaAction() {
		this.resetAccessWilayaDto();
		this.isCrud = true;

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:16
	 */
	public void editAccessWilayaAction() {
		this.isCrud = true;
		this.isView = true;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:48:12
	 */
	public void cancelAccessWilayaAction() {
		this.resetAccessWilayaDto();
		this.isCrud = false;
		this.isView = false;
	}

	/**
	 * Reinitialiser le dto accessWilaya
	 * 
	 * @author Mounir.MESSAOUDI on : 19 ao�t 2014 09:47:47
	 */
	private void resetAccessWilayaDto() {
		this.accessWilayaDto = null;
		this.accessWilayaDto = new AccessWilayaDto();
		// accessWilayaDto.setId(1);
		this.accessWilayaDto.setIdAnneeAcademique(this.getIdCurrAnneeAcademique());
		this.accessWilayaDto.setRefCodeEtablissement(this.getRefCodeCurrEtablissement());

		this.wilayas = defaultWilayas;
	}

	/**
	 * Ajouter/Modifier un access wilaya
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:17:05
	 */
	public void saveAccessWilaya() {
		FacesMessage msg = new FacesMessage();
		try {
			List<DroitAccessWilayaDto> ll = this.accessWilayaDto.getDroitAccessWilayaDto();
			if (ll != null && ll.size() > 0) {
				for (DroitAccessWilayaDto droitAccessWilayaDto : ll) {
					accessWilayaService.remove(droitAccessWilayaDto);
				}
			}
			this.accessWilayaDto.setDroitAccessWilayaDto(wilayas.getTarget());
			accessWilayaService.insertOrUpdate(this.accessWilayaDto);
			this.resetAccessWilayaDto();
			this.loadSearchResults();

			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_enregistrement"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		this.isCrud = false;

	}

	/**
	 * Supprimer un access wilaya
	 * 
	 * @author Mounir.MESSAOUDI on : 17 ao�t 2014 12:17:29
	 */
	public void deleteAccessWilaya() {
		FacesMessage msg = new FacesMessage();
		try {
			accessWilayaService.remove(this.accessWilayaDto);

			this.resetAccessWilayaDto();
			this.loadSearchResults();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		this.isCrud = false;

	}

	/**
	 * [AccessWilayaMBean.searchBo] Getter
	 * 
	 * @author BELDI Jamel on : 6 sept. 2014 12:22:11
	 * @return the searchBo
	 */
	public AccessWilayaDto getSearchDto() {
		return searchDto;
	}

	/**
	 * [AccessWilayaMBean.searchBo] Setter
	 * 
	 * @author BELDI Jamel on : 6 sept. 2014 12:22:11
	 * @param searchBo
	 *            the searchBo to set
	 */
	public void setSearchBo(AccessWilayaDto searchDto) {
		this.searchDto = searchDto;
	}

}
