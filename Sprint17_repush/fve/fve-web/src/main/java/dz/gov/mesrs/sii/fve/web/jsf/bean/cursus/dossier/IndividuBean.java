package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELDI Jamel on : 22 mai 2014 08:55:12
 * @author Mounir.MESSAOUDI on : 11 novombre 2014 10:46:36 (Viewscoped et
 *         refecatoring)
 * 
 */
@ManagedBean(name = "individuBean")
@ViewScoped
public class IndividuBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 08:55:03
	 */
	private static final long serialVersionUID = 1L;
	private RefIndividuDto individuDto;
	private static final Log log = LogFactory.getLog(IndividuBean.class);
	private ResourceBundle bundleIndividu;
	private ResourceBundle bundleCommon;
	private int exception;
	private boolean entityChange;
	private boolean identifiantChange;
	// @ManagedProperty("#{flash}")
	// private Flash flash;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	public boolean updateMode;
	private List<SelectItem> listeGroupeSanguin;
	private List<SelectItem> listeSitSceNat;
	private List<SelectItem> listeSituationFamiliale;
	private List<SelectItem> listeNationalite;
	private List<SelectItem> listeCivilite;

	// ManagedProperty("#{param.individuIdentifiant}")
	private String individuIdentifiant;

	private Integer idIndividu;

	// ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;

	// ManagedProperty("#{param.dossierBacId}")
	private Integer dossierBacId;

	@ManagedProperty("#{bacService}")
	private BacService bacService;

	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	@ManagedProperty("#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	
	private String oldIdentifiant;
	private Boolean individuPresume;

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:58
	 */
	public IndividuBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleIndividu = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.INDIVIDU_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.COMMON_MESSAGES_FILE_NAME);
	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:54
	 */
	@PostConstruct
	public void init() {
		exception = 0;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();

		idIndividu = (Integer) request.getAttribute("idIndividu");

		dossierBacId = (Integer) request.getAttribute("dossierBacId");

		loadIndividuDto();
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 11 nov. 2014 10:25:54
	 */
	private void loadIndividuDto() {
		FacesMessage msg = new FacesMessage();
		try {

			individuDto = new RefIndividuDto();
			if (idIndividu != null) {
				individuDto = refIndividuService.findById(idIndividu);
				updateMode = true;

			} else if (individuIdentifiant != null
					&& !individuIdentifiant.trim().equals("null")) {

				individuDto = refIndividuService
						.findByIdentifiant(individuIdentifiant);

				// sessionBean.setIndividuIdentifiant(individuIdentifiant);

				if (individuDto != null) {
					oldIdentifiant = individuDto.getIdentifiant();
				}
				updateMode = true;

			} else {
				if (dossierBacId != null) {
					try {
						DossierBachelierDto dossierBachelierDto = bacService
								.findBachelorFileById(dossierBacId);
						RefIndividuDto _searchDto = new RefIndividuDto();
						_searchDto.setDateNaissance(dossierBachelierDto
								.getDateNaissance());
						_searchDto.setNomArabe(dossierBachelierDto.getNomAr());
						_searchDto.setPrenomArabe(dossierBachelierDto
								.getPrenomAr());
						_searchDto.setNomLatin(dossierBachelierDto.getNomFr());
						_searchDto.setPrenomLatin(dossierBachelierDto
								.getPrenomFr());
						_searchDto.setPrenomPereLatin(dossierBachelierDto
								.getPrenomPere());
						_searchDto.setPrenomMereLatin(dossierBachelierDto
								.getNomPrenomMere());
						// individuDto = ppmWSClient
						// .findIndividuByRefIndividuDto(_searchDto);
						individuDto = refIndividuService
								.findByRefIndividuDto(_searchDto);
						if (individuDto == null) {
							individuDto = _searchDto;
						} else {

							oldIdentifiant = individuDto.getIdentifiant();
						}
						updateMode = true;
					} catch (Exception e) {
						// erreur chargement dossier bachelier
						exception = -1;
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundleCommon.getString("error_echec")
								+ ": "
								+ exception
								+ ":"
								+ bundleCommon
										.getString("error_echec_inconnue"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}

				// updateMode=false;
			}

		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ exception + ":"
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [IndividuBean.saveIndividu] Method
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:15:01
	 * @return
	 */
	public RefIndividuDto saveIndividu() {
		FacesMessage msg = new FacesMessage();
		try {

			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleCommon.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {

				individuDto.setDateSituation(new Date());

				if (updateMode) {

					// ppmWSClient.updateIndividu(individuDto);
					refIndividuService.saveOrUpdate(individuDto);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundleCommon.getString("msg_success")
							+ ": "
							+ bundleCommon
									.getString("msg_success_modification"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					/*
					 * if (identifiantChange && oldIdentifiant != null) {
					 * List<DossierEtudiantDto> list = dossierEtudiantService
					 * .findByRefIndividu(oldIdentifiant,
					 * sessionBean.getCodeEtablissement()); if (list != null) {
					 * for (DossierEtudiantDto item : list) {
					 * item.setNin(individuDto.getIdentifiant());
					 * dossierEtudiantService.insertOrUpdate(item); } } }
					 */

				} else {
					RefIndividuDto refIndividuDtoFound = null;
					// refIndividuDtoFound = ppmWSClient
					// .findIndividuByRefIndividuDto(individuDto);
					refIndividuDtoFound = refIndividuService
							.findByRefIndividuDto(individuDto);
					if (refIndividuDtoFound != null) {
						refIndividuDtoFound.setIdentifiant(null);
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundleCommon.getString("error_echec")
								+ ": "
								+ bundleIndividu
										.getString("individu_warn_already_exist"));
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						// individuDto = ppmWSClient.saveIndividu(individuDto);
						refIndividuService.saveOrUpdate(individuDto);

						setIndividuIdentifiant(individuDto.getIdentifiant());
						sessionBean.setIndividuIdentifiant(individuDto
								.getIdentifiant());
						setUpdateMode(true);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundleCommon.getString("msg_success")
								+ ": "
								+ bundleCommon
										.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
						// flash.setKeepMessages(true);
						RequestContext.getCurrentInstance().execute(
								"tabviewWidget.enable(1)");
						return individuDto;

					}

				}
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		return individuDto;

	}

	/**
	 * [IndividuBean.individuDto] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:31:05
	 * @return the individuDto
	 */
	public RefIndividuDto getIndividuDto() {
		return individuDto;
	}

	/**
	 * [IndividuBean.individuDto] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:31:05
	 * @param individuDto
	 *            the individuDto to set
	 */
	public void setIndividuDto(RefIndividuDto individuDto) {
		this.individuDto = individuDto;
	}

	/**
	 * [IndividuBean.exception] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:35:02
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [IndividuBean.exception] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:35:02
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [IndividuBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:35:02
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [IndividuBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 16:35:02
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [IndividuBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:12:57
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [IndividuBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:12:57
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [IndividuBean.updateMode] Setter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:41:31
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * [IndividuBean.updateMode] Getter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:42:14
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * [IndividuMgrBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 11:55:01
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	public void identifiantChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
			oldIdentifiant = (String) event.getOldValue();
			identifiantChange = true;
		}
	}

	/**
	 * [IndividuMgrBean.entityChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 11:55:26
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [IndividuBean.listeGroupeSanguin] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @return the listeGroupeSanguin
	 */
	public List<SelectItem> getListeGroupeSanguin() {
		if (listeGroupeSanguin == null || listeGroupeSanguin.isEmpty()) {
			listeGroupeSanguin = new ArrayList<SelectItem>();
			listeGroupeSanguin = getNcGenericList(CursusConstants.NC_GROUPE_SANGUIN_NAME);
		}
		return listeGroupeSanguin;
	}

	/**
	 * [IndividuBean.listeGroupeSanguin] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @param listeGroupeSanguin
	 *            the listeGroupeSanguin to set
	 */
	public void setListeGroupeSanguin(List<SelectItem> listeGroupeSanguin) {
		this.listeGroupeSanguin = listeGroupeSanguin;
	}

	/**
	 * [IndividuBean.listeSitSceNat] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @return the listeSitSceNat
	 */
	public List<SelectItem> getListeSitSceNat() {
		if (listeSitSceNat == null || listeSitSceNat.isEmpty()) {
			listeSitSceNat = new ArrayList<SelectItem>();
			listeSitSceNat = getNcGenericList(CursusConstants.NC_SIT_SERVICE_NAT_NAME);
		}
		return listeSitSceNat;

	}

	/**
	 * [IndividuBean.listeSitSceNat] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @param listeSitSceNat
	 *            the listeSitSceNat to set
	 */
	public void setListeSitSceNat(List<SelectItem> listeSitSceNat) {
		this.listeSitSceNat = listeSitSceNat;
	}

	/**
	 * [IndividuBean.listeSituationFamiliale] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @return the listeSituationFamiliale
	 */
	public List<SelectItem> getListeSituationFamiliale() {
		if (listeSituationFamiliale == null
				|| listeSituationFamiliale.isEmpty()) {
			listeSituationFamiliale = new ArrayList<SelectItem>();
			listeSituationFamiliale = getNcGenericList(CursusConstants.NC_SIT_FAMILLIALE_NAME);
		}
		return listeSituationFamiliale;
	}

	/**
	 * [IndividuBean.listeSituationFamiliale] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @param listeSituationFamiliale
	 *            the listeSituationFamiliale to set
	 */
	public void setListeSituationFamiliale(
			List<SelectItem> listeSituationFamiliale) {
		this.listeSituationFamiliale = listeSituationFamiliale;
	}

	/**
	 * [IndividuBean.listeNationalite] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @return the listeNationalite
	 */
	public List<SelectItem> getListeNationalite() {
		if (listeNationalite == null || listeNationalite.isEmpty()) {
			listeNationalite = new ArrayList<SelectItem>();
			listeNationalite = getNcGenericList(CursusConstants.NC_NATIONALITE_NAME);
		}
		return listeNationalite;
	}

	/**
	 * [IndividuBean.listeNationalite] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @param listeNationalite
	 *            the listeNationalite to set
	 */
	public void setListeNationalite(List<SelectItem> listeNationalite) {
		this.listeNationalite = listeNationalite;
	}

	/**
	 * [IndividuBean.listeCivilite] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @return the listeCivilite
	 */
	public List<SelectItem> getListeCivilite() {
		if (listeCivilite == null || listeCivilite.isEmpty()) {
			listeCivilite = new ArrayList<SelectItem>();
			listeCivilite = getNcGenericList(CursusConstants.NC_CIVILITE_NAME);
		}
		return listeCivilite;
	}

	/**
	 * [IndividuBean.listeCivilite] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 17:44:39
	 * @param listeCivilite
	 *            the listeCivilite to set
	 */
	public void setListeCivilite(List<SelectItem> listeCivilite) {
		this.listeCivilite = listeCivilite;
	}

	/**
	 * [ComboBckBean.getNcGenericList] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 15:25:49
	 * @param nc_name
	 * @return
	 */
	private List<SelectItem> getNcGenericList(String nc_name) {
		FacesMessage msg = new FacesMessage();
		List<SelectItem> nomenclatureDtoList = new ArrayList<SelectItem>();
		try {
			List<NomenclatureDto> list = nomenclatureService
					.findByName(nc_name);
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				nomenclatureDtoList.add(selectItem);
			}
		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ exception + ":"
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		return nomenclatureDtoList;
	}

	/**
	 * [IndividuBean.individuIdentifiant] Getter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:38:36
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [IndividuBean.individuIdentifiant] Setter
	 * 
	 * @author BELDI Jamel on : 25 mai 2014 14:38:36
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	// /**
	// * [IndividuBean.map] Method
	// * @author BELDI Jamel on : 25 mai 2014 12:34:56
	// * @param individuDto
	// * @return
	// */
	// private RefIndividuDto map(IndividuDto individuDto){
	// RefIndividuDto refIndividuDto = new RefIndividuDto();
	// refIndividuDto.setCiviliteCode(individuDto.getCiviliteCode());
	// refIndividuDto.setCiviliteId(individuDto.getCiviliteId());
	// refIndividuDto.setCiviliteLibelleCourtAr(individuDto.getCiviliteLibelleCourtAr());
	// refIndividuDto.setCiviliteLibelleCourtFr(individuDto.getCiviliteLibelleCourtFr());
	// refIndividuDto.setCiviliteLibelleLongFr(individuDto.getCiviliteLibelleLongFr());
	// refIndividuDto.setDateNaissance(XMLCalendarToDate.toXMLGregorianCalendar(individuDto.getDateNaissance()));
	// refIndividuDto.setDateSituation(XMLCalendarToDate.toXMLGregorianCalendar(individuDto.getDateSituation()));
	// refIndividuDto.setGroupeSanguinCode(individuDto.getGroupeSanguinCode());
	// refIndividuDto.setGroupeSanguinId(individuDto.getGroupeSanguinId());
	// refIndividuDto.setGroupeSanguinLibelleCourtAr(individuDto.getGroupeSanguinLibelleCourtAr());
	// refIndividuDto.setGroupeSanguinLibelleCourtFr(individuDto.getGroupeSanguinLibelleCourtFr());
	// refIndividuDto.setGroupeSanguinLibelleLongAr(individuDto.getGroupeSanguinLibelleLongAr());
	// refIndividuDto.setGroupeSanguinLibelleLongFr(individuDto.getGroupeSanguinLibelleLongFr());
	// refIndividuDto.setId(individuDto.getId());
	// refIndividuDto.setIdentifiant(individuDto.getIdentifiant());
	// refIndividuDto.setLlSituationAr(individuDto.getLlSituationAr());
	// refIndividuDto.setLlSituationFr(individuDto.getLlSituationFr());
	// refIndividuDto.setNationaliteCode(individuDto.getNationaliteCode());
	// refIndividuDto.setNationaliteId(individuDto.getNationaliteId());
	// refIndividuDto.setNationaliteLibelleCourtAr(individuDto.getNationaliteLibelleCourtAr());
	// refIndividuDto.setNationaliteLibelleCourtFr(individuDto.getNationaliteLibelleCourtFr());
	// refIndividuDto.setNationaliteLibelleLongAr(individuDto.getNationaliteLibelleLongAr());
	// refIndividuDto.setNationaliteLibelleLongFr(individuDto.getNationaliteLibelleLongFr());
	// refIndividuDto.setNomArabe(individuDto.getNomArabe());
	// refIndividuDto.setNomJeuneFilleArabe(individuDto.getNomJeuneFilleArabe());
	// refIndividuDto.setNomJeuneFilleLatin(individuDto.getNomJeuneFilleLatin());
	// refIndividuDto.setNomLatin(individuDto.getNomLatin());
	// refIndividuDto.setNomMereArabe(individuDto.getNomMereArabe());
	// refIndividuDto.setNomMereLatin(individuDto.getNomMereLatin());
	// refIndividuDto.setPrenomArabe(individuDto.getPrenomArabe());
	// refIndividuDto.setPrenomLatin(individuDto.getPrenomLatin());
	// refIndividuDto.setPrenomMereArabe(individuDto.getPrenomMereArabe());
	// refIndividuDto.setPrenomMereLatin(individuDto.getPrenomMereLatin());
	// refIndividuDto.setPrenomPereArabe(individuDto.getPrenomPereArabe());
	// refIndividuDto.setPrenomPereLatin(individuDto.getPrenomPereLatin());
	// refIndividuDto.setPresume(individuDto.getPresume());
	// refIndividuDto.setQualite(individuDto.getQualite());
	// refIndividuDto.setServiceNatCode(individuDto.getServiceNatCode());
	// refIndividuDto.setServiceNatId( individuDto.getServiceNatId());
	// refIndividuDto.setServiceNatLibelleCourtAr(
	// individuDto.getServiceNatLibelleCourtAr());
	// refIndividuDto.setServiceNatLibelleCourtFr(individuDto.getServiceNatLibelleCourtFr());
	// refIndividuDto.setServiceNatLibelleLongAr(individuDto.getServiceNatLibelleLongAr());
	// refIndividuDto.setServiceNatLibelleLongFr(individuDto.getServiceNatLibelleLongFr());
	// refIndividuDto.setSituationFamilialeCode(individuDto.getSituationFamilialeCode());
	// refIndividuDto.setSituationFamilialeId(individuDto.getSituationFamilialeId());
	// refIndividuDto.setSituationFamilialeLibelleCourtAr(individuDto.getSituationFamilialeLibelleCourtAr());
	// refIndividuDto.setSituationFamilialeLibelleCourtFr(individuDto.getSituationFamilialeLibelleCourtFr());
	// refIndividuDto.setSituationFamilialeLibelleLongAr(individuDto.getSituationFamilialeLibelleLongAr());
	// refIndividuDto.setSituationFamilialeLibelleLongFr(individuDto.getSituationFamilialeLibelleLongFr());
	// refIndividuDto.setTypeIndividuCode(individuDto.getTypeIndividuCode());
	// refIndividuDto.setTypeIndividuId(individuDto.getTypeIndividuId());
	// refIndividuDto.setTypeIndividuLibelleCourtAr(individuDto.getTypeIndividuLibelleCourtAr());
	// refIndividuDto.setTypeIndividuLibelleCourtFr(individuDto.getTypeIndividuLibelleCourtFr());
	// refIndividuDto.setTypeIndividuLibelleLongAr(individuDto.getTypeIndividuLibelleLongAr());
	// refIndividuDto.setTypeIndividuLibelleLongFr(individuDto.getTypeIndividuLibelleLongFr());
	//
	//
	// return refIndividuDto;
	// }
	//
	// /**
	// * [IndividuBean.map] Method
	// * @author BELDI Jamel on : 25 mai 2014 12:34:47
	// * @param refIndividuDto
	// * @return
	// */
	// private IndividuDto map( RefIndividuDto refIndividuDto){
	// IndividuDto individuDto = new IndividuDto();
	// individuDto.setCiviliteCode(refIndividuDto.getCiviliteCode());
	// individuDto.setCiviliteId(refIndividuDto.getCiviliteId());
	// individuDto.setCiviliteLibelleCourtAr(refIndividuDto.getCiviliteLibelleCourtAr());
	// individuDto.setCiviliteLibelleCourtFr(refIndividuDto.getCiviliteLibelleCourtFr());
	// individuDto.setCiviliteLibelleLongFr(refIndividuDto.getCiviliteLibelleLongFr());
	// individuDto.setDateNaissance(XMLCalendarToDate.toDate(refIndividuDto.getDateNaissance()));
	// individuDto.setDateSituation(XMLCalendarToDate.toDate(refIndividuDto.getDateSituation()));
	// individuDto.setGroupeSanguinCode(refIndividuDto.getGroupeSanguinCode());
	// individuDto.setGroupeSanguinId(refIndividuDto.getGroupeSanguinId());
	// individuDto.setGroupeSanguinLibelleCourtAr(refIndividuDto.getGroupeSanguinLibelleCourtAr());
	// individuDto.setGroupeSanguinLibelleCourtFr(refIndividuDto.getGroupeSanguinLibelleCourtFr());
	// individuDto.setGroupeSanguinLibelleLongAr(refIndividuDto.getGroupeSanguinLibelleLongAr());
	// individuDto.setGroupeSanguinLibelleLongFr(refIndividuDto.getGroupeSanguinLibelleLongFr());
	// individuDto.setId(refIndividuDto.getId());
	// individuDto.setIdentifiant(refIndividuDto.getIdentifiant());
	// individuDto.setLlSituationAr(refIndividuDto.getLlSituationAr());
	// individuDto.setLlSituationFr(refIndividuDto.getLlSituationFr());
	// individuDto.setNationaliteCode(refIndividuDto.getNationaliteCode());
	// individuDto.setNationaliteId(refIndividuDto.getNationaliteId());
	// individuDto.setNationaliteLibelleCourtAr(refIndividuDto.getNationaliteLibelleCourtAr());
	// individuDto.setNationaliteLibelleCourtFr(refIndividuDto.getNationaliteLibelleCourtFr());
	// individuDto.setNationaliteLibelleLongAr(refIndividuDto.getNationaliteLibelleLongAr());
	// individuDto.setNationaliteLibelleLongFr(refIndividuDto.getNationaliteLibelleLongFr());
	// individuDto.setNomArabe(refIndividuDto.getNomArabe());
	// individuDto.setNomJeuneFilleArabe(refIndividuDto.getNomJeuneFilleArabe());
	// individuDto.setNomJeuneFilleLatin(refIndividuDto.getNomJeuneFilleLatin());
	// individuDto.setNomLatin(refIndividuDto.getNomLatin());
	// individuDto.setNomMereArabe(refIndividuDto.getNomMereArabe());
	// individuDto.setNomMereLatin(refIndividuDto.getNomMereLatin());
	// individuDto.setPrenomArabe(refIndividuDto.getPrenomArabe());
	// individuDto.setPrenomLatin(refIndividuDto.getPrenomLatin());
	// individuDto.setPrenomMereArabe(refIndividuDto.getPrenomMereArabe());
	// individuDto.setPrenomMereLatin(refIndividuDto.getPrenomMereLatin());
	// individuDto.setPrenomPereArabe(refIndividuDto.getPrenomPereArabe());
	// individuDto.setPrenomPereLatin(refIndividuDto.getPrenomPereLatin());
	// individuDto.setPresume(refIndividuDto.isPresume());
	// individuDto.setQualite(refIndividuDto.getQualite());
	// individuDto.setServiceNatCode(refIndividuDto.getServiceNatCode());
	// individuDto.setServiceNatId(refIndividuDto.getServiceNatId());
	// individuDto.setServiceNatLibelleCourtAr(
	// refIndividuDto.getServiceNatLibelleCourtAr());
	// individuDto.setServiceNatLibelleCourtFr(refIndividuDto.getServiceNatLibelleCourtFr());
	// individuDto.setServiceNatLibelleLongAr(refIndividuDto.getServiceNatLibelleLongAr());
	// individuDto.setServiceNatLibelleLongFr(refIndividuDto.getServiceNatLibelleLongFr());
	// individuDto.setSituationFamilialeCode(refIndividuDto.getSituationFamilialeCode());
	// individuDto.setSituationFamilialeId(refIndividuDto.getSituationFamilialeId());
	// individuDto.setSituationFamilialeLibelleCourtAr(refIndividuDto.getSituationFamilialeLibelleCourtAr());
	// individuDto.setSituationFamilialeLibelleCourtFr(refIndividuDto.getSituationFamilialeLibelleCourtFr());
	// individuDto.setSituationFamilialeLibelleLongAr(refIndividuDto.getSituationFamilialeLibelleLongAr());
	// individuDto.setSituationFamilialeLibelleLongFr(refIndividuDto.getSituationFamilialeLibelleLongFr());
	// individuDto.setTypeIndividuCode(refIndividuDto.getTypeIndividuCode());
	// individuDto.setTypeIndividuId(refIndividuDto.getTypeIndividuId());
	// individuDto.setTypeIndividuLibelleCourtAr(refIndividuDto.getTypeIndividuLibelleCourtAr());
	// individuDto.setTypeIndividuLibelleCourtFr(refIndividuDto.getTypeIndividuLibelleCourtFr());
	// individuDto.setTypeIndividuLibelleLongAr(refIndividuDto.getTypeIndividuLibelleLongAr());
	// individuDto.setTypeIndividuLibelleLongFr(refIndividuDto.getTypeIndividuLibelleLongFr());
	//
	//
	// return individuDto;
	// }

	/**
	 * [IndividuBean.dossierBacId] Getter
	 * 
	 * @author BELDI Jamel on : 1 juin 2014 10:19:42
	 * @return the dossierBacId
	 */
	public Integer getDossierBacId() {
		return dossierBacId;
	}

	/**
	 * [IndividuBean.dossierBacId] Setter
	 * 
	 * @author BELDI Jamel on : 1 juin 2014 10:19:42
	 * @param dossierBacId
	 *            the dossierBacId to set
	 */
	public void setDossierBacId(Integer dossierBacId) {
		this.dossierBacId = dossierBacId;
	}

	/**
	 * [IndividuBean.bacService] Getter
	 * 
	 * @author BELDI Jamel on : 1 juin 2014 10:26:35
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [IndividuBean.bacService] Setter
	 * 
	 * @author BELDI Jamel on : 1 juin 2014 10:26:35
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [IndividuBean.identifiantChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 11:33:09
	 * @return the identifiantChange
	 */
	public boolean isIdentifiantChange() {
		return identifiantChange;
	}

	/**
	 * [IndividuBean.identifiantChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 11:33:09
	 * @param identifiantChange
	 *            the identifiantChange to set
	 */
	public void setIdentifiantChange(boolean identifiantChange) {
		this.identifiantChange = identifiantChange;
	}

	/**
	 * [IndividuBean.dossierEtudiantService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 11:35:17
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [IndividuBean.dossierEtudiantService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 11:35:17
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [IndividuBean.oldIdentifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 13:04:58
	 * @return the olddIdentifiant
	 */
	public String getOldIdentifiant() {
		return oldIdentifiant;
	}

	/**
	 * [IndividuBean.oldIdentifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 juin 2014 13:04:58
	 * @param oldIdentifiant
	 *            the oldIdentifiant to set
	 */
	public void setOldIdentifiant(String oldIdentifiant) {
		this.oldIdentifiant = oldIdentifiant;
	}

	/**
	 * [IndividuBean.individuPresume] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 juil. 2014 14:10:17
	 * @return the individuPresume
	 */
	public Boolean getIndividuPresume() {
		if (individuDto != null) {
			individuPresume = individuDto.getPresume();
		}
		return individuPresume;
	}

	/**
	 * [IndividuBean.individuPresume] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 juil. 2014 14:10:17
	 * @param individuPresume
	 *            the individuPresume to set
	 */
	public void setIndividuPresume(Boolean individuPresume) {
		if (individuDto != null) {
			individuDto.setPresume(individuPresume);
		}
		this.individuPresume = individuPresume;

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 juil. 2014 14:40:57
	 * @return
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 31 juil. 2014 14:41:04
	 * @param dossierEtudiantId
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	public Integer getIdIndividu() {
		return idIndividu;
	}

	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

}
