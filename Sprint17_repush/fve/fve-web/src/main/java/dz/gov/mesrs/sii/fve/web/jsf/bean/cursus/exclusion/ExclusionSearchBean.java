package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.exclusion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ExclusionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExclusionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

@ManagedBean(name = "exclusionSearchBean")
@RequestScoped
public class ExclusionSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ExclusionSearchBean.class);
	private ResourceBundle exclusionBundle;
	private ResourceBundle bundleCommon;
	private int exception;
	private List<ExclusionDto> exclusionResultSearch;
	private ExclusionDto exclusionSearchDto;
	@ManagedProperty(value = "#{exclusionService}")
	private ExclusionService exclusionService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private List<SelectItem> listeAnneeAcademiques;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private ExclusionDto current;
	private boolean showDetail;
	@ManagedProperty("#{param.anneeId}")
	private String anneeId;
	private Integer idAnnee;
	private List<ExclusionDto> listExclusionDto;
	private ResourceBundle bundle;

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	public ExclusionSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		exclusionBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXCLUSION_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		exception = 0;
		loadSearchDto();
		loadExclusionResultSearch();

	}

	private void loadSearchDto() {
		exclusionSearchDto = sessionBean.getExclusionSearchDto();
	}

	private void loadExclusionResultSearch() {
		FacesMessage msg = new FacesMessage();
		exclusionResultSearch = new ArrayList<ExclusionDto>();
		try {
			if (exclusionSearchDto != null) {
				RefIndividuDto refIndividuSearch = new RefIndividuDto();
				refIndividuSearch = map(exclusionSearchDto);
				List<RefIndividuDto> listIndividu = refIndividuService
						.findAdvanced(refIndividuSearch);
				if (listIndividu != null && !listIndividu.isEmpty()) {
					for (RefIndividuDto refIndividuDto : listIndividu) {
						exclusionSearchDto.setIndividuNin(refIndividuDto
								.getIdentifiant());
						List<ExclusionDto> exclusions = exclusionService
								.findAdvanced(exclusionSearchDto);
						exclusionSearchDto.setIndividuNin(null);

					}
				} else {
					List<ExclusionDto> exclusions = exclusionService
							.findAdvanced(exclusionSearchDto);

					if (exclusions != null && !exclusions.isEmpty()) {
						for (ExclusionDto exclusionDto : exclusions) {

							// Etudiant (Individu)
							RefIndividuDto refIndividuDto = refIndividuService
									.findByIdentifiant(exclusionDto
											.getIndividuNin());
							map(refIndividuDto, exclusionDto);

							exclusionResultSearch.add(exclusionDto);
						}

					}
				}
			}
		} catch (Exception e) {
			exclusionResultSearch = new ArrayList<ExclusionDto>();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [ExclusionSearchBean.exception] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [ExclusionSearchBean.exception] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [ExclusionSearchBean.ExclusionResultSearch] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the ExclusionResultSearch
	 */
	public List<ExclusionDto> getExclusionResultSearch() {
		return exclusionResultSearch;
	}

	/**
	 * [ExclusionSearchBean.ExclusionResultSearch] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param ExclusionResultSearch
	 *            the ExclusionResultSearch to set
	 */
	public void setExclusionResultSearch(
			List<ExclusionDto> exclusionResultSearch) {
		this.exclusionResultSearch = exclusionResultSearch;
	}

	/**
	 * [ExclusionSearchBean.ExclusionSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the ExclusionSearchDto
	 */
	public ExclusionDto getExclusionSearchDto() {
		return exclusionSearchDto;
	}

	/**
	 * [ExclusionSearchBean.ExclusionSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param ExclusionSearchDto
	 *            the ExclusionSearchDto to set
	 */
	public void setExclusionSearchDto(ExclusionDto exclusionSearchDto) {
		this.exclusionSearchDto = exclusionSearchDto;
	}

	/**
	 * [ExclusionSearchBean.ExclusionService] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the ExclusionService
	 */
	public ExclusionService getExclusionService() {
		return exclusionService;
	}

	/**
	 * [ExclusionSearchBean.ExclusionService] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param ExclusionService
	 *            the ExclusionService to set
	 */
	public void setExclusionService(ExclusionService exclusionService) {
		this.exclusionService = exclusionService;
	}

	/**
	 * [ExclusionSearchBean.flash] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [ExclusionSearchBean.flash] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [ExclusionSearchBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [ExclusionSearchBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	/**
	 * [ExclusionSearchBean.map] Method
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 11:28:54
	 * @param ExclusionDto
	 * @return
	 */
	private RefIndividuDto map(ExclusionDto exclusionDto) {
		RefIndividuDto refIndividuDto = new RefIndividuDto();
		refIndividuDto.setId(exclusionDto.getIndividuId());
		refIndividuDto.setIdentifiant(exclusionDto.getIndividuNin());
		refIndividuDto.setNomLatin(exclusionDto.getIndividuNomLatin());
		refIndividuDto.setNomArabe(exclusionDto.getIndividuNomArabe());
		refIndividuDto.setPrenomArabe(exclusionDto.getIndividuPrenomArabe());
		refIndividuDto.setPrenomLatin(exclusionDto.getIndividuPrenomLatin());
		return refIndividuDto;
	}

	/**
	 * [ExclusionSearchBean.map] Method
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 11:33:53
	 * @param refIndividuDto
	 * @return
	 */
	@SuppressWarnings("unused")
	private ExclusionDto map(RefIndividuDto refIndividuDto) {
		ExclusionDto exclusionDto = new ExclusionDto();
		exclusionDto.setIndividuId(refIndividuDto.getId());
		exclusionDto.setIndividuNin(refIndividuDto.getIdentifiant());
		exclusionDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		exclusionDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		exclusionDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		exclusionDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			exclusionDto.setIndividuDateNaissance(refIndividuDto
					.getDateNaissance());
		}
		return exclusionDto;
	}

	private void map(RefIndividuDto refIndividuDto, ExclusionDto exclusionDto) {
		if (exclusionDto == null) {
			exclusionDto = new ExclusionDto();
		}
		exclusionDto.setIndividuId(refIndividuDto.getId());
		exclusionDto.setIndividuNin(refIndividuDto.getIdentifiant());
		exclusionDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		exclusionDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		exclusionDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		exclusionDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			exclusionDto.setIndividuDateNaissance(refIndividuDto
					.getDateNaissance());
		}

	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 10:40:10
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 10:40:10
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [ExclusionSearchBean.listeAnneeAcademiques] Getter
	 * 
	 * @author BELBRIK Oualid on : 13 mai 2014 16:21:47
	 * @return the listeAnneeAcademiques
	 */
	public List<SelectItem> getListeAnneeAcademiques() {
		FacesMessage msg = new FacesMessage();
		try {
			if (listeAnneeAcademiques == null
					|| listeAnneeAcademiques.isEmpty()) {
				listeAnneeAcademiques = new ArrayList<SelectItem>();
				List<AnneeAcademiqueDto> liste = anneeAcademiqueService
						.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
						SelectItem si = new SelectItem(
								anneeAcademiqueDto.getId(),
								anneeAcademiqueDto.getPremiereAnnee() + "/"
										+ anneeAcademiqueDto.getDeuxiemeAnnee());
						listeAnneeAcademiques.add(si);
					}
				}
			}

		} catch (Exception e) {
			exception = 3;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		return listeAnneeAcademiques;
	}

	/**
	 * [ExclusionSearchBean.listeAnneeAcademiques] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 10:39:37
	 * @param listeAnneeAcademiques
	 *            the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [ExclusionSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [ExclusionSearchBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [ExclusionSearchBean.offreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [ExclusionSearchBean.offreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = null;
		} else {
			this.searchValue = searchValue;

		}

	}

	public void onRowSelect(SelectEvent event) {
		this.current = ((ExclusionDto) event.getObject());
		setShowDetail(true);

	}

	public ExclusionDto getCurrent() {
		return current;
	}

	public void setCurrent(ExclusionDto current) {
		this.current = current;
	}

	public String toEdit(ExclusionDto current) {
		this.current = current;
		return "toEdit";
	}

	public boolean isShowDetail() {
		return showDetail;
	}

	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}

	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();

		try {

			if (idAnnee != null) {
				setAnneeId(idAnnee.toString());
			}
			if (idAnnee != null) {

				ExclusionDto searchDto = new ExclusionDto();

				searchDto.setAnneeAcademiqueId(idAnnee);
				List<ExclusionDto> result = exclusionService
						.findAdvanced2(searchDto);
				if (result == null || result.isEmpty()) {
					setListExclusionDto(null);
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundle.getString("warn_info") + ": "
							+ bundle.getString("warn_info_aucun_result"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {

					setListExclusionDto(result);

				}
			}
		} catch (Exception e) {
			setListExclusionDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		setShowDetail(false);

	}

	/**
	 * [ExclusionSearchBean.anneeId] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 juil. 2014 13:43:18
	 * @return the anneeId
	 */
	public String getAnneeId() {
		return anneeId;
	}

	/**
	 * [ExclusionSearchBean.anneeId] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 juil. 2014 13:43:18
	 * @param anneeId
	 *            the anneeId to set
	 */
	public void setAnneeId(String anneeId) {
		this.anneeId = anneeId;
	}

	/**
	 * [ExclusionSearchBean.idAnnee] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 juil. 2014 13:44:01
	 * @return the idAnnee
	 */
	public Integer getIdAnnee() {
		return idAnnee;
	}

	/**
	 * [ExclusionSearchBean.idAnnee] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 juil. 2014 13:44:01
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	public void setIdAnnee(Integer idAnnee) {
		this.idAnnee = idAnnee;
	}

	public List<ExclusionDto> getListExclusionDto() {
		return listExclusionDto;
	}

	public void setListExclusionDto(List<ExclusionDto> listExclusionDto) {
		this.listExclusionDto = listExclusionDto;
	}

	/**
	 * [ExclusionSearchBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:34:18
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ExclusionSearchBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:34:18
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}
	

}
