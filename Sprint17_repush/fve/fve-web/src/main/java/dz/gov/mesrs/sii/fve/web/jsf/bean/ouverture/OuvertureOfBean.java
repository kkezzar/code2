package dz.gov.mesrs.sii.fve.web.jsf.bean.ouverture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.Utility;

/**
 * @author BELDI Jamel on : 11 mai 2014 18:38:18
 */
@ManagedBean(name = "ouvertureOfBean")
@RequestScoped
public class OuvertureOfBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(OuvertureOfBean.class);
	private ResourceBundle bundleOuverture;
	private int exception;
	@ManagedProperty("#{param.ofId}")
	private String ofId;
	@ManagedProperty("#{param.ouvertureOfId}")
	private String ouvertureOfId;
	private List<OffreFormationDto> ofResultSearch;
	private OuvertureOffreFormationDto ouvertureOffreFormationDto;
	private AnneeAcademiqueDto anneeAcademiqueDto;
	@ManagedProperty(value = "#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty(value = "#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{flash}")
	private Flash flash;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:58
	 */
	public OuvertureOfBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleOuverture = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.OUVERTURE_OF_MESSAGES_FILE_NAME);
		ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:54
	 */
	@PostConstruct
	public void init() {
		exception = 0;
		loadAnneeAcademique();
		loadOuvertureOf();

		findOffres();

	}

	private void loadAnneeAcademique() {
		try {
			log.info("loadOuvertureOf");
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			anneeAcademiqueDto = anneeAcademiqueService
					.findByFirstAndSecondYear(
							Short.valueOf(Integer.toString(currentYear)),
							Short.valueOf(Integer.toString(currentYear + 1)));
			if (anneeAcademiqueDto == null) {
				// save nouvelle annee academique
				anneeAcademiqueDto = new AnneeAcademiqueDto();
				anneeAcademiqueDto.setPremiereAnnee(Short.valueOf(Integer
						.toString(currentYear)));
				anneeAcademiqueDto.setDeuxiemeAnnee(Short.valueOf(Integer
						.toString(currentYear + 1)));
				anneeAcademiqueDto = anneeAcademiqueService
						.insertOrUpdate(anneeAcademiqueDto);
			}
		} catch (Exception e) {
			exception = 5;
			Utility.showErrorMessage(e.getMessage());
		}

	}

	private void loadOuvertureOf() {
		try {
			log.info("loadOuvertureOf");
			if (ouvertureOfId == null || ouvertureOfId.trim().equals("")
					|| ouvertureOfId.trim().equals("null")) {
				ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
				ouvertureOffreFormationDto
						.setAnneeAcademiqueId(anneeAcademiqueDto.getId());
				ouvertureOffreFormationDto
						.setAnneeAcademiquePremiereAnnee(anneeAcademiqueDto
								.getPremiereAnnee());
				ouvertureOffreFormationDto
						.setAnneeAcademiqueDeuxiemeAnnee(anneeAcademiqueDto
								.getDeuxiemeAnnee());
				if (ouvertureOfId != null && !ouvertureOfId.trim().equals("")
						&& !ouvertureOfId.trim().equals("null")) {
					ouvertureOffreFormationDto.setOffreFormationId(Integer
							.valueOf(ofId));
				}
				ouvertureOffreFormationDto.setIdEtablissement(sessionBean
						.getIdEtablissement());
				List<OuvertureOffreFormationDto> listOuverturesOf = ouvertureOffreFormationService
						.findAdvanced(ouvertureOffreFormationDto);
				if (listOuverturesOf != null && listOuverturesOf.size() == 1) {
					ouvertureOffreFormationDto = listOuverturesOf.get(0);
				}

			} else {
				ouvertureOffreFormationDto = new OuvertureOffreFormationDto();
				ouvertureOffreFormationDto = ouvertureOffreFormationService
						.findById(Integer.valueOf(ouvertureOfId));
			}
		} catch (Exception e) {
			exception = 4;
			Utility.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * [OuvertureOfBean.getOfId] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:50
	 * @return
	 */
	public String getOfId() {
		return ofId;
	}

	/**
	 * [OuvertureOfBean.setOfId] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:48
	 * @param ofId
	 */
	public void setOfId(String ofId) {
		this.ofId = ofId;
	}

	/**
	 * [OuvertureOfBean.getOffreFormationService] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:45
	 * @return
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [OuvertureOfBean.setOffreFormationService] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:41
	 * @param offreFormationService
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [OuvertureOfBean.getOfResultSearch] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:38
	 * @return
	 */
	public List<OffreFormationDto> getOfResultSearch() {
		return ofResultSearch;
	}

	/**
	 * [OuvertureOfBean.setOfResultSearch] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:34
	 * @param ofResultSearch
	 */
	public void setOfResultSearch(List<OffreFormationDto> ofResultSearch) {
		this.ofResultSearch = ofResultSearch;
	}

	public void findOffres() {

		try {
			log.info("findOffres");
			List<OffreFormationDto> offresHabilites = offreFormationService
					.findByCodeSituation(UtilConstants.OFFRE_FORMATION_SITUTAION_HABILITEE_CODE);
			// recuperer list offre habilites et non ouvert pour l'annee en
			// cours
			// int currentYear=Calendar.getInstance().get(Calendar.YEAR);
			// AnneeAcademiqueDto anneeAcademiqueDto=
			// anneeAcademiqueService.findByFirstAndSecondYear(Short.valueOf(Integer.toString(currentYear)),
			// Short.valueOf(Integer.toString(currentYear+1)));
			if (anneeAcademiqueDto != null) {
				ofResultSearch = new ArrayList<OffreFormationDto>();
				for (OffreFormationDto off : offresHabilites) {

					OuvertureOffreFormationDto searchDto = new OuvertureOffreFormationDto();
					searchDto.setAnneeAcademiqueId(anneeAcademiqueDto.getId());
					searchDto.setIdEtablissement(sessionBean
							.getIdEtablissement());
					searchDto.setOffreFormationId(off.getId());
					searchDto.setEstOuverte(true);
					List<OuvertureOffreFormationDto> offresOuverts = ouvertureOffreFormationService
							.findAdvanced(searchDto);
					if (offresOuverts == null || offresOuverts.isEmpty()) {

						ofResultSearch.add(off);
					}

				}

			}
		} catch (Exception e) {
			exception = 4;
			Utility.showErrorMessage(e.getMessage());

		}

	}

	/**
	 * [OuvertureOfBean.toOpenOf] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:27
	 * @return
	 */
	public String toOpenOf() {
		if (exception == 0) {
			return "toOpenOf";
		}
		{
			return null;
		}
	}

	/**
	 * [OuvertureOfBean.toOpenOf] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:27
	 * @return
	 */
	public String toUpdate() {
		if (exception == 0) {
			return "toUpdate";
		}
		{
			return null;
		}
	}

	/**
	 * [OuvertureOfBean.goBack] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:36:34
	 * @return
	 */
	public String goBack() {
		if (ouvertureOfId == null || ouvertureOfId.trim().equals("")
				|| ouvertureOfId.trim().equals("null")) {
			return "toSelectOf";
		} else {
			return "toSearch";
		}
	}

	public String save() {
		try {
			//
			// AnneeAcademiqueDto anneeAcademiqueDto=
			// anneeAcademiqueService.findByFirstAndSecondYear(ouvertureOffreFormationDto.getAnneeAcademiquePremiereAnnee(),
			// ouvertureOffreFormationDto.getAnneeAcademiqueDeuxiemeAnnee());
			// if(anneeAcademiqueDto == null){
			// //save nouvelle annee academique
			// anneeAcademiqueDto = new AnneeAcademiqueDto();
			// anneeAcademiqueDto.setPremiereAnnee(ouvertureOffreFormationDto.getAnneeAcademiquePremiereAnnee());
			// anneeAcademiqueDto.setDeuxiemeAnnee(ouvertureOffreFormationDto.getAnneeAcademiqueDeuxiemeAnnee());
			// anneeAcademiqueDto =
			// anneeAcademiqueService.insertOrUpdate(anneeAcademiqueDto);
			// }
			// ouvertureOffreFormationDto.setAnneeAcademiqueId(anneeAcademiqueDto.getId());
			if (ouvertureOffreFormationDto.getEffectifMax() != null
					&& ouvertureOffreFormationDto.getEffectifMin() != null
					&& ouvertureOffreFormationDto.getEffectifMax().intValue() < ouvertureOffreFormationDto
							.getEffectifMin().intValue()) {
				Utility.showErrorMessage(bundleOuverture
						.getString("ouverture_effectif_maximum_error"));
				return null;
			}
			ouvertureOffreFormationDto.setOffreFormationId(Integer
					.valueOf(ofId));
			ouvertureOffreFormationDto.setIdEtablissement(sessionBean
					.getIdEtablissement());
			if (ouvertureOffreFormationDto.getDateOuverture() == null) {
				ouvertureOffreFormationDto.setDateOuverture(new Date());
				ouvertureOffreFormationDto.setEstOuverte(true);
			}
			if (!ouvertureOffreFormationDto.isEstOuverte()
					&& ouvertureOffreFormationDto.getDateFermeture() == null) {
				ouvertureOffreFormationDto.setDateFermeture(new Date());
			}

			ouvertureOffreFormationDto = ouvertureOffreFormationService
					.insertOrUpdate(ouvertureOffreFormationDto);

		} catch (Exception e) {
			exception = 2;
			Utility.showErrorMessage(e.getMessage());
			return null;
		}

		if (ouvertureOfId == null || ouvertureOfId.trim().equals("")
				|| ouvertureOfId.trim().equals("null")) {
			Utility.showSuccessMessage(bundleOuverture
					.getString("ouverturen_success_ouvrir"));
			flash.setKeepMessages(true);
			return "OuvertureOfSelect?faces-redirect=true";
		} else {
			Utility.showSuccessMessage(bundleOuverture
					.getString("ouverturen_success_modification"));
			flash.setKeepMessages(true);
			return "OuvertureOfSearch?faces-redirect=true";
		}

	}

	/**
	 * [OuvertureOfBean.getException] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:36:31
	 * @return
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [OuvertureOfBean.setException] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:36:27
	 * @param exception
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationService] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:49:35
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationDto] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:51:34
	 * @return the ouvertureOffreFormationDto
	 */
	public OuvertureOffreFormationDto getOuvertureOffreFormationDto() {
		return ouvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfBean.ouvertureOffreFormationDto] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:51:34
	 * @param ouvertureOffreFormationDto
	 *            the ouvertureOffreFormationDto to set
	 */
	public void setOuvertureOffreFormationDto(
			OuvertureOffreFormationDto ouvertureOffreFormationDto) {
		this.ouvertureOffreFormationDto = ouvertureOffreFormationDto;
	}

	/**
	 * [OuvertureOfBean.ouvertureOfId] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 16:03:13
	 * @return the ouvertureOfId
	 */
	public String getOuvertureOfId() {
		return ouvertureOfId;
	}

	/**
	 * [OuvertureOfBean.ouvertureOfId] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 16:03:13
	 * @param ouvertureOfId
	 *            the ouvertureOfId to set
	 */
	public void setOuvertureOfId(String ouvertureOfId) {
		this.ouvertureOfId = ouvertureOfId;
	}

	

	/**
	 * [OuvertureOfBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:41:21
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [OuvertureOfBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:41:21
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueDto] Getter
	 * 
	 * @author BELDI Jamel on : 14 mai 2014 18:47:57
	 * @return the anneeAcademiqueDto
	 */
	public AnneeAcademiqueDto getAnneeAcademiqueDto() {
		return anneeAcademiqueDto;
	}

	/**
	 * [OuvertureOfBean.anneeAcademiqueDto] Setter
	 * 
	 * @author BELDI Jamel on : 14 mai 2014 18:47:57
	 * @param anneeAcademiqueDto
	 *            the anneeAcademiqueDto to set
	 */
	public void setAnneeAcademiqueDto(AnneeAcademiqueDto anneeAcademiqueDto) {
		this.anneeAcademiqueDto = anneeAcademiqueDto;
	}

	/**
	 * [OuvertureOfBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 27 juil. 2014 09:46:47
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [OuvertureOfBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 27 juil. 2014 09:46:47
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

}
