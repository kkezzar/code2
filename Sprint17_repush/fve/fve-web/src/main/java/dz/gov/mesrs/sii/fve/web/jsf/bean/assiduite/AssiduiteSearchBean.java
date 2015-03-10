package dz.gov.mesrs.sii.fve.web.jsf.bean.assiduite;

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

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AbsenceMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AbsenceMatiereService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

@ManagedBean(name = "assiduiteSearchBean")
@RequestScoped
public class AssiduiteSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AssiduiteSearchBean.class);
	private ResourceBundle assiduiteBundle;
	private ResourceBundle bundleCommon;
	private int exception;
	private List<AbsenceMatiereDto> absenceMatiereResultSearch;
	private AbsenceMatiereDto absenceMatiereSearchDto;
	@ManagedProperty(value = "#{absenceMatiereService}")
	private AbsenceMatiereService absenceMatiereService;
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
	@ManagedProperty("#{param.mcId}")
	private String mcId;
	@ManagedProperty("#{param.anneeId}")
	private String anneeId;
	@ManagedProperty("#{param.matricule}")
	private String matricule;
	private Integer idAnnee;
	private List<AbsenceMatiereDto> listAbsenceMatiereDto;
	private ResourceBundle bundle;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	/**
	 * [AssiduiteSearchBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  13:59:35
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [AssiduiteSearchBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  13:59:35
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [AssiduiteSearchBean.idAnnee] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:30:48
	 * @return the idAnnee
	 */
	public Integer getIdAnnee() {
		return idAnnee;
	}

	/**
	 * [AssiduiteSearchBean.idAnnee] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:30:48
	 * @param idAnnee
	 *            the idAnnee to set
	 */
	public void setIdAnnee(Integer idAnnee) {
		this.idAnnee = idAnnee;
	}

	public AssiduiteSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		assiduiteBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.ASSIDUITE_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {
		exception = 0;
		loadSearchDto();
		loadAbsenceMatiereResultSearch();

	}

	private void loadSearchDto() {
		absenceMatiereSearchDto = sessionBean.getAbsenceMatiereSearchDto();
	}

	private void loadAbsenceMatiereResultSearch() {
		FacesMessage msg = new FacesMessage();
		absenceMatiereResultSearch = new ArrayList<AbsenceMatiereDto>();
		try {
			if (absenceMatiereSearchDto != null) {
				RefIndividuDto refIndividuSearch = new RefIndividuDto();
				refIndividuSearch = map(absenceMatiereSearchDto);
				List<RefIndividuDto> listIndividu = refIndividuService
						.findAdvanced(refIndividuSearch);
				if (listIndividu != null && !listIndividu.isEmpty()) {
					for (RefIndividuDto refIndividuDto : listIndividu) {
						absenceMatiereSearchDto.setIndividuNin(refIndividuDto
								.getIdentifiant());
						List<AbsenceMatiereDto> absenceMatieres = absenceMatiereService
								.findAdvanced(absenceMatiereSearchDto);
						absenceMatiereSearchDto.setIndividuNin(null);

					}
				} else {
					List<AbsenceMatiereDto> absenceMatieres = absenceMatiereService
							.findAdvanced(absenceMatiereSearchDto);

					if (absenceMatieres != null && !absenceMatieres.isEmpty()) {
						for (AbsenceMatiereDto absenceMatiereDto : absenceMatieres) {

							// Etudiant (Individu)
							RefIndividuDto refIndividuDto = refIndividuService
									.findByIdentifiant(absenceMatiereDto
											.getIndividuNin());
							map(refIndividuDto, absenceMatiereDto);

							absenceMatiereResultSearch.add(absenceMatiereDto);
						}

					}
				}
			}
		} catch (Exception e) {
			absenceMatiereResultSearch = new ArrayList<AbsenceMatiereDto>();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	/**
	 * [AbsenceMatiereSearchBean.exception] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [AbsenceMatiereSearchBean.exception] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereResultSearch] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the AbsenceMatiereResultSearch
	 */
	public List<AbsenceMatiereDto> getAbsenceMatiereResultSearch() {
		return absenceMatiereResultSearch;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereResultSearch] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param AbsenceMatiereResultSearch
	 *            the AbsenceMatiereResultSearch to set
	 */
	public void setAbsenceMatiereResultSearch(
			List<AbsenceMatiereDto> absenceMatiereResultSearch) {
		this.absenceMatiereResultSearch = absenceMatiereResultSearch;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereSearchDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the AbsenceMatiereSearchDto
	 */
	public AbsenceMatiereDto getAbsenceMatiereSearchDto() {
		return absenceMatiereSearchDto;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereSearchDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param AbsenceMatiereSearchDto
	 *            the AbsenceMatiereSearchDto to set
	 */
	public void setAbsenceMatiereSearchDto(
			AbsenceMatiereDto absenceMatiereSearchDto) {
		this.absenceMatiereSearchDto = absenceMatiereSearchDto;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereService] Getter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @return the AbsenceMatiereService
	 */
	public AbsenceMatiereService getAbsenceMatiereService() {
		return absenceMatiereService;
	}

	/**
	 * [AbsenceMatiereSearchBean.AbsenceMatiereService] Setter
	 * 
	 * @author BELBRIK Oualid on : 12 juin 2014 18:43:38
	 * @param AbsenceMatiereService
	 *            the AbsenceMatiereService to set
	 */
	public void setAbsenceMatiereService(
			AbsenceMatiereService absenceMatiereService) {
		this.absenceMatiereService = absenceMatiereService;
	}

	/**
	 * [AbsenceMatiereSearchBean.flash] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [AbsenceMatiereSearchBean.flash] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:21:37
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [AbsenceMatiereSearchBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [AbsenceMatiereSearchBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 12:29:37
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [AbsenceMatiereSearchBean.map] Method
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 11:28:54
	 * @param AbsenceMatiereDto
	 * @return
	 */
	private RefIndividuDto map(AbsenceMatiereDto absenceMatiereDto) {
		RefIndividuDto refIndividuDto = new RefIndividuDto();
		refIndividuDto.setId(absenceMatiereDto.getIndividuId());
		refIndividuDto.setIdentifiant(absenceMatiereDto.getIndividuNin());
		refIndividuDto.setNomLatin(absenceMatiereDto.getIndividuNomLatin());
		refIndividuDto.setNomArabe(absenceMatiereDto.getIndividuNomArabe());
		refIndividuDto.setPrenomArabe(absenceMatiereDto
				.getIndividuPrenomArabe());
		refIndividuDto.setPrenomLatin(absenceMatiereDto
				.getIndividuPrenomLatin());
		return refIndividuDto;
	}

	/**
	 * [AbsenceMatiereSearchBean.map] Method
	 * 
	 * @author BELBRIK Oualid on : 16 juin 2014 11:33:53
	 * @param refIndividuDto
	 * @return
	 */
	private AbsenceMatiereDto map(RefIndividuDto refIndividuDto) {
		AbsenceMatiereDto absenceMatiereDto = new AbsenceMatiereDto();
		absenceMatiereDto.setIndividuId(refIndividuDto.getId());
		absenceMatiereDto.setIndividuNin(refIndividuDto.getIdentifiant());
		absenceMatiereDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		absenceMatiereDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		absenceMatiereDto.setIndividuPrenomArabe(refIndividuDto
				.getPrenomArabe());
		absenceMatiereDto.setIndividuPrenomLatin(refIndividuDto
				.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			absenceMatiereDto.setIndividuDateNaissance(refIndividuDto
					.getDateNaissance());
		}
		return absenceMatiereDto;
	}

	private void map(RefIndividuDto refIndividuDto,
			AbsenceMatiereDto absenceMatiereDto) {
		if (absenceMatiereDto == null) {
			absenceMatiereDto = new AbsenceMatiereDto();
		}
		absenceMatiereDto.setIndividuId(refIndividuDto.getId());
		absenceMatiereDto.setIndividuNin(refIndividuDto.getIdentifiant());
		absenceMatiereDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		absenceMatiereDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		absenceMatiereDto.setIndividuPrenomArabe(refIndividuDto
				.getPrenomArabe());
		absenceMatiereDto.setIndividuPrenomLatin(refIndividuDto
				.getPrenomLatin());
		if (refIndividuDto.getDateNaissance() != null) {
			absenceMatiereDto.setIndividuDateNaissance(refIndividuDto
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
	 * [AbsenceMatiereSearchBean.listeAnneeAcademiques] Getter
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
	 * [AbsenceMatiereSearchBean.listeAnneeAcademiques] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 10:39:37
	 * @param listeAnneeAcademiques
	 *            the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [AbsenceMatiereSearchBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [AbsenceMatiereSearchBean.ouvertureOffreFormationService] Setter
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
	 * [AbsenceMatiereSearchBean.offreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [AbsenceMatiereSearchBean.offreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 15:14:06
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();

		try {

			if (idAnnee != null) {
				setAnneeId(idAnnee.toString());
			}
			if (idAnnee != null) {

				AbsenceMatiereDto searchDto = new AbsenceMatiereDto();
				searchDto.setAnneeAcademiqueId(idAnnee);
				List<AbsenceMatiereDto> result = absenceMatiereService
						.findAdvanced(searchDto);
				if (result == null || result.isEmpty()) {
					setListAbsenceMatiereDto(null);
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundle.getString("warn_info") + ": "
							+ bundle.getString("warn_info_aucun_result"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {

					setListAbsenceMatiereDto(result);

				}
			}
		} catch (Exception e) {
			setListAbsenceMatiereDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [AssiduiteSearchBean.mcId] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @return the mcId
	 */
	public String getMcId() {
		return mcId;
	}

	/**
	 * [AssiduiteSearchBean.mcId] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @param mcId
	 *            the mcId to set
	 */
	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	/**
	 * [AssiduiteSearchBean.anneeId] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @return the anneeId
	 */
	public String getAnneeId() {
		return anneeId;
	}

	/**
	 * [AssiduiteSearchBean.anneeId] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @param anneeId
	 *            the anneeId to set
	 */
	public void setAnneeId(String anneeId) {
		this.anneeId = anneeId;
	}

	/**
	 * [AssiduiteSearchBean.matricule] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * [AssiduiteSearchBean.matricule] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 juil. 2014 15:29:58
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public List<AbsenceMatiereDto> getListAbsenceMatiereDto() {
		return listAbsenceMatiereDto;
	}

	public void setListAbsenceMatiereDto(
			List<AbsenceMatiereDto> listAbsenceMatiereDto) {
		this.listAbsenceMatiereDto = listAbsenceMatiereDto;
	}

}
