/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture.OuvertureOfSearchBean.java] 
 * @author BELDI Jamel on : 13 mai 2014  12:18:07
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.UtilBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;
import dz.gov.mesrs.sii.referentiel.business.service.RefDomaineLMDService;
import dz.gov.mesrs.sii.referentiel.business.service.RefFiliereLmdService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSpecialiteLmdService;

/**
 * @author BELDI Jamel on : 13 mai 2014 12:18:07
 */
@ManagedBean(name = "ouvertureOfMgrBean")
@ViewScoped
public class OuvertureOfMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:19:04
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleOuverture;
	private List<OuvertureOffreFormationDto> ouvertureOfResultSearch;
	private OuvertureOffreFormationDto ouvertureOfSearchDto;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{utilBean}")
	private UtilBean utilBean;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{refSpecialiteLmdServiceImpl}")
	private RefSpecialiteLmdService refSpecialiteLmdService;

	@ManagedProperty("#{refDomaineLMDServiceImpl}")
	private RefDomaineLMDService refDomaineLMDService;

	@ManagedProperty("#{refFiliereLmdServiceImpl}")
	private RefFiliereLmdService refFiliereLmdService;

	private List<SelectItem> listeOffres;
	private List<SelectItem> listeAnneeAcademiques;
	private List<SelectItem> listeEtabs;
	private OuvertureOffreFormationDto selectedOuvertureOffreFormationDto;
	private OffreFormationDto selectedOffreFormationDto;

	/**
	 * [OuvertureOfSearchBean.OuvertureOfSearchBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:18:07
	 */
	public OuvertureOfMgrBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleOuverture = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.OUVERTURE_OF_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		selectedOuvertureOffreFormationDto = new OuvertureOffreFormationDto();
		selectedOffreFormationDto = new OffreFormationDto();
		ouvertureOfSearchDto = new OuvertureOffreFormationDto();
		loadAnneeAcademique();
		// loadSearchDto();
		// loadOffres();
		// loadOuvertureOfResultSearch();

	}

	/**
	 * [OuvertureOfMgrBean.anneeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 16:55:13
	 */
	public void anneeChanged() {
		loadSearchDto();
		loadOffres();
	}

	private void loadSearchDto() {
		ouvertureOfSearchDto.setIdEtablissement(sessionBean
				.getIdEtablissement());
		ouvertureOfSearchDto.setAnneeAcademiqueId(sessionBean
				.getIdAnneeAcademique());
		ouvertureOfSearchDto.setEstOuverte(true);
	}

	private void loadOffres() {
		try {
			listeOffres = new ArrayList<SelectItem>();
			List<OffreFormationDto> offresHabilites = offreFormationService
					.findByIdEtablissement(sessionBean.getIdEtablissement());// findByCodeSituation(UtilConstants.OFFRE_FORMATION_SITUTAION_HABILITEE_CODE);
			if (offresHabilites != null && !offresHabilites.isEmpty()) {
				for (OffreFormationDto ofDto : offresHabilites) {

					SelectItem si = new SelectItem(ofDto.getId(),
							ofDto.getLibelleLongFr());
					listeOffres.add(si);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	public void loadOuvertureOfResultSearch() {
		selectedOuvertureOffreFormationDto = null;
		selectedOffreFormationDto = null;
		ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
		try {
			if (ouvertureOfSearchDto != null) {
				List<OuvertureOffreFormationDto> ouvertureOfs = ouvertureOffreFormationService
						.findAdvanced(ouvertureOfSearchDto);
				if (ouvertureOfs != null && !ouvertureOfs.isEmpty()) {
					for (OuvertureOffreFormationDto selectedOuvertureOffreFormationDto : ouvertureOfs) {
						OffreFormationDto ofDto = offreFormationService
								.findById(selectedOuvertureOffreFormationDto
										.getOffreFormationId().intValue());
						selectedOuvertureOffreFormationDto
								.setOffreFormationlibelle(ofDto
										.getLibelleLongFr());
						ouvertureOfResultSearch
								.add(selectedOuvertureOffreFormationDto);
					}
					if (ouvertureOfResultSearch.size() == 1) {
						selectedOuvertureOffreFormationDto = ouvertureOfResultSearch.get(0);
					}
				}
			}
		} catch (Exception e) {
			ouvertureOfResultSearch = new ArrayList<OuvertureOffreFormationDto>();
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}

	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfResultSearch] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOfResultSearch
	 */
	public List<OuvertureOffreFormationDto> getOuvertureOfResultSearch() {
		return ouvertureOfResultSearch;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfResultSearch] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOfResultSearch
	 *            the ouvertureOfResultSearch to set
	 */
	public void setOuvertureOfResultSearch(
			List<OuvertureOffreFormationDto> ouvertureOfResultSearch) {
		this.ouvertureOfResultSearch = ouvertureOfResultSearch;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfSearchDto] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOfSearchDto
	 */
	public OuvertureOffreFormationDto getOuvertureOfSearchDto() {
		return ouvertureOfSearchDto;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOfSearchDto] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOfSearchDto
	 *            the ouvertureOfSearchDto to set
	 */
	public void setOuvertureOfSearchDto(
			OuvertureOffreFormationDto ouvertureOfSearchDto) {
		this.ouvertureOfSearchDto = ouvertureOfSearchDto;
	}

	/**
	 * [OuvertureOfSearchBean.offreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.offreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfSearchBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:21:37
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfSearchBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [OuvertureOfSearchBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [OuvertureOfSearchBean.listeOffres] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeOffres
	 */
	public List<SelectItem> getListeOffres() {
		return listeOffres;
	}

	/**
	 * [OuvertureOfSearchBean.listeOffres] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeOffres
	 *            the listeOffres to set
	 */
	public void setListeOffres(List<SelectItem> listeOffres) {
		this.listeOffres = listeOffres;
	}

	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeAnneeAcademiques
	 */
	public List<SelectItem> getListeAnneeAcademiques() {
		return this.listeAnneeAcademiques;
	}

	/**
	 * [OuvertureOfMgrBean.loadAnneeAcademique] Method
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 16:50:28
	 */
	public void loadAnneeAcademique() {
		try {
			listeAnneeAcademiques = utilBean.loadAnneeAcademique();
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeAnneeAcademiques
	 *            the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [OuvertureOfSearchBean.listeEtabs] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @return the listeEtabs
	 */
	public List<SelectItem> getListeEtabs() {
		// TODO list etablissement
		listeEtabs = new ArrayList<SelectItem>();
		SelectItem si = new SelectItem("esi", "ESI");
		listeEtabs.add(si);
		return listeEtabs;
	}

	/**
	 * [OuvertureOfSearchBean.listeEtabs] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 16:21:47
	 * @param listeEtabs
	 *            the listeEtabs to set
	 */
	public void setListeEtabs(List<SelectItem> listeEtabs) {
		this.listeEtabs = listeEtabs;
	}

	/**
	 * 
	 * [OuvertureOfOpenBean.onSelectOf] Method
	 * 
	 * @author BELDI Jamel on : 10 sept. 2014 15:33:10
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {

		try {
			selectedOuvertureOffreFormationDto = (OuvertureOffreFormationDto) event
					.getObject();
			selectedOffreFormationDto = offreFormationService
					.findById(selectedOuvertureOffreFormationDto
							.getOffreFormationId());
		} catch (Exception e) {
			e.printStackTrace();
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [OuvertureOfMgrBean.selectedOuvertureOffreFormationDto] Getter
	 * 
	 * @author BELDI Jamel on : 16 sept. 2014 15:24:11
	 * @return the selectedOuvertureOffreFormationDto
	 */
	public OuvertureOffreFormationDto getSelectedOuvertureOffreFormationDto() {
		return selectedOuvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfMgrBean.selectedOuvertureOffreFormationDto] Setter
	 * 
	 * @author BELDI Jamel on : 16 sept. 2014 15:24:11
	 * @param selectedOuvertureOffreFormationDto
	 *            the selectedOuvertureOffreFormationDto to set
	 */
	public void setSelectedOuvertureOffreFormationDto(
			OuvertureOffreFormationDto selectedOuvertureOffreFormationDto) {
		this.selectedOuvertureOffreFormationDto = selectedOuvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfMgrBean.selectedOffreFormationDto] Getter
	 * 
	 * @author BELDI Jamel on : 16 sept. 2014 15:31:23
	 * @return the selectedOffreFormationDto
	 */
	public OffreFormationDto getSelectedOffreFormationDto() {
		return selectedOffreFormationDto;
	}

	/**
	 * [OuvertureOfMgrBean.selectedOffreFormationDto] Setter
	 * 
	 * @author BELDI Jamel on : 16 sept. 2014 15:31:23
	 * @param selectedOffreFormationDto
	 *            the selectedOffreFormationDto to set
	 */
	public void setSelectedOffreFormationDto(
			OffreFormationDto selectedOffreFormationDto) {
		this.selectedOffreFormationDto = selectedOffreFormationDto;
	}

	public void save() {

		try {

			if (selectedOuvertureOffreFormationDto.getEffectifMax() != null
					&& selectedOuvertureOffreFormationDto.getEffectifMin() != null
					&& selectedOuvertureOffreFormationDto.getEffectifMax()
							.intValue() < selectedOuvertureOffreFormationDto
							.getEffectifMin().intValue()) {
				Utility.showErrorMessage(bundleOuverture
						.getString("ouverture_effectif_maximum_error"));
				return;
			}
			selectedOuvertureOffreFormationDto
					.setRefLibelleEtablissement(sessionBean
							.getLlLatinEtablissement());
			if (selectedOuvertureOffreFormationDto.getDateOuverture() == null) {
				selectedOuvertureOffreFormationDto.setDateOuverture(new Date());
				selectedOuvertureOffreFormationDto.setEstOuverte(true);
				selectedOuvertureOffreFormationDto.setDateFermeture(null);
			}
			if (!selectedOuvertureOffreFormationDto.isEstOuverte()
					&& selectedOuvertureOffreFormationDto.getDateFermeture() == null) {
				selectedOuvertureOffreFormationDto.setDateFermeture(new Date());
			}

			selectedOuvertureOffreFormationDto = ouvertureOffreFormationService
					.insertOrUpdate(selectedOuvertureOffreFormationDto);
			Utility.showSuccessMessage(bundleOuverture
					.getString("ouverturen_success_modification"));
		} catch (Exception e) {
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [OuvertureOfMgrBean.refSpecialiteLmdService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @return the refSpecialiteLmdService
	 */
	public RefSpecialiteLmdService getRefSpecialiteLmdService() {
		return refSpecialiteLmdService;
	}

	/**
	 * [OuvertureOfMgrBean.refSpecialiteLmdService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @param refSpecialiteLmdService
	 *            the refSpecialiteLmdService to set
	 */
	public void setRefSpecialiteLmdService(
			RefSpecialiteLmdService refSpecialiteLmdService) {
		this.refSpecialiteLmdService = refSpecialiteLmdService;
	}

	/**
	 * [OuvertureOfMgrBean.refDomaineLMDService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @return the refDomaineLMDService
	 */
	public RefDomaineLMDService getRefDomaineLMDService() {
		return refDomaineLMDService;
	}

	/**
	 * [OuvertureOfMgrBean.refDomaineLMDService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @param refDomaineLMDService
	 *            the refDomaineLMDService to set
	 */
	public void setRefDomaineLMDService(
			RefDomaineLMDService refDomaineLMDService) {
		this.refDomaineLMDService = refDomaineLMDService;
	}

	/**
	 * [OuvertureOfMgrBean.refFiliereLmdService] Getter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @return the refFiliereLmdService
	 */
	public RefFiliereLmdService getRefFiliereLmdService() {
		return refFiliereLmdService;
	}

	/**
	 * [OuvertureOfMgrBean.refFiliereLmdService] Setter
	 * 
	 * @author rlaib on : 23 nov. 2014 10:39:55
	 * @param refFiliereLmdService
	 *            the refFiliereLmdService to set
	 */
	public void setRefFiliereLmdService(
			RefFiliereLmdService refFiliereLmdService) {
		this.refFiliereLmdService = refFiliereLmdService;
	}

	/**
	 * [OuvertureOfMgrBean.utilBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 16:49:42
	 * @return the utilBean
	 */
	public UtilBean getUtilBean() {
		return utilBean;
	}

	/**
	 * [OuvertureOfMgrBean.utilBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 nov. 2014 16:49:42
	 * @param utilBean
	 *            the utilBean to set
	 */
	public void setUtilBean(UtilBean utilBean) {
		this.utilBean = utilBean;
	}

}
