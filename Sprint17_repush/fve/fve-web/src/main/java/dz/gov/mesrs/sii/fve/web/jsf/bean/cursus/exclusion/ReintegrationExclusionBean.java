package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.exclusion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ReintegrationDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.ReintegrationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;

@ManagedBean(name = "reintegrationexclusionBean")
@RequestScoped
public class ReintegrationExclusionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ReintegrationExclusionBean.class);

	private ResourceBundle reintegrationBundle;

	private ReintegrationDto reintegrationDto;

	private List<ReintegrationDto> listeReintegrationsDto;
	
	private List<ReintegrationDto> listeAllReintegrationsDto;

	@ManagedProperty("#{param.exclusionId}")
	private String exclusionId;

	@ManagedProperty("#{param.reintegrationId}")
	private String reintegrationId;

	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;

	@ManagedProperty(value = "#{reintegrationService}")
	private ReintegrationService reintegrationService;

	@ManagedProperty("#{exclusionBean}")
	private ExclusionBean exclusionBean;
	

	@ManagedProperty("#{param.ligneId}")
	private String ligneId;


	public ReintegrationExclusionBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		reintegrationBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.REINTEGRATION_BUNDLE_MSG_NAME);

	}

	@PostConstruct
	public void init() {

		loadParams();

	}

	private void loadParams() {
		
		listeAllReintegrationsDto = reintegrationService.findAll();
		if (exclusionId != null && !exclusionId.equals("null")
				&& !exclusionId.isEmpty()) {

			listeReintegrationsDto = reintegrationService.findReintegrationsByIdExclusion(Integer
					.parseInt(exclusionId));
			
			listeAllReintegrationsDto = reintegrationService.findAll();


			if (reintegrationId != null && !reintegrationId.equals("null")
					&& !reintegrationId.isEmpty()) {

				reintegrationDto = reintegrationService.findById(Integer
						.parseInt(reintegrationId));

			}

			if (reintegrationDto != null) {

			} else {
				reintegrationDto = new ReintegrationDto();
			}

			
		} else {
			reintegrationDto = new ReintegrationDto();
		}

	}

	/**
	 * [ReintegrationBean.ReintegrationDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 10:59:45
	 * @return the ReintegrationDto
	 */
	public ReintegrationDto getReintegrationDto() {
		return reintegrationDto;
	}

	/**
	 * [ReintegrationBean.ReintegrationDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 10:59:45
	 * @param ReintegrationDto
	 *            the ReintegrationDto to set
	 */
	public void setReintegrationDto(ReintegrationDto reintegrationDto) {
		this.reintegrationDto = reintegrationDto;
	}

	/**
	 * [ReintegrationBean.listeReintegrations] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 11:17:09
	 * @return the listeReintegrations
	 */
	public List<ReintegrationDto> getListeReintegrationsDto() {
		return listeReintegrationsDto;
	}

	/**
	 * [ReintegrationBean.listeReintegrations] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 11:17:09
	 * @param listeReintegrations
	 *            the listeReintegrations to set
	 */
	public void setListeReintegrationsDto(List<ReintegrationDto> listeReintegrationsDto) {
		this.listeReintegrationsDto = listeReintegrationsDto;
	}

	/**
	 * [ReintegrationBean.ReintegrationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 10:59:45
	 * @return the ReintegrationService
	 */
	public ReintegrationService getReintegrationService() {
		return reintegrationService;
	}

	/**
	 * [ReintegrationBean.ReintegrationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 10:59:45
	 * @param ReintegrationService
	 *            the ReintegrationService to set
	 */
	public void setReintegrationService(ReintegrationService reintegrationService) {
		this.reintegrationService = reintegrationService;
	}

	

	public String getExclusionId() {
		return exclusionId;
	}

	/**
	 * [ReintegrationBean.idDossierEtudiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 juillet 2014 09:39:48
	 * @param idDossierEtudiant
	 *            the idDossierEtudiant to set
	 */
	public void setExclusionId(String exclusionId) {
		this.exclusionId = exclusionId;
	}

	/**
	 * [ReintegrationBean.ReintegrationId] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 15:59:53
	 * @return the ReintegrationId
	 */
	public String getReintegrationId() {
		return reintegrationId;
	}

	/**
	 * [ReintegrationBean.ReintegrationId] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 juillet 2014 15:59:53
	 * @param ReintegrationId
	 *            the ReintegrationId to set
	 */
	public void setReintegrationId(String reintegrationId) {
		this.reintegrationId = reintegrationId;
	}

	/**
	 * [ReintegrationBean.idfLigne] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 juillet 2014 10:53:39
	 * @return the idfLigne
	 */
	public String getLigneId() {
		return ligneId;
	}

	/**
	 * [ReintegrationBean.idfLigne] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 juillet 2014 10:53:39
	 * @param idfLigne
	 *            the idfLigne to set
	 */
	public void setLigneId(String ligneId) {
		if ((ligneId != null) && (ligneId.equals("null"))) {
			this.ligneId = null;
		} else
			this.ligneId = ligneId;
	}

	/**
	 * Save or update titre acces Dto [ReintegrationBean.saveReintegration] Method
	 * 
	 * @author BELBRIK Oualid on : 26 juillet 2014 10:53:43
	 */
	public void saveReintegration() {

		FacesMessage msg = new FacesMessage();

		try {

			if (exclusionId == null || exclusionId.equals("null")
					|| exclusionId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(reintegrationBundle
						.getString("reintegration_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}
			
			if (reintegrationId != null && !reintegrationId.equals("null")
					&& !reintegrationId.isEmpty()) {

				log.info(" ---------- update reintegration ----");

				// update reintegration

				reintegrationDto = reintegrationService.insertOrUpdate(reintegrationDto);

				setReintegrationId(reintegrationDto.getId() + "");

				listeReintegrationsDto = reintegrationService.findReintegrationsByIdExclusion(Integer.parseInt(exclusionId));

				// message succes update reintegration
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(reintegrationBundle.getString("reintegration_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

			else {
				log.info(" ---------- save reintegration  ------ ");

				reintegrationDto.setExclusionId(Integer
						.valueOf(exclusionId));
				// save 
				reintegrationDto = reintegrationService.insertOrUpdate(reintegrationDto);

				setReintegrationId(reintegrationDto.getId() + "");

				listeReintegrationsDto = reintegrationService
						.findReintegrationsByIdExclusion(Integer
								.parseInt(exclusionId));

				// message succes enregistrement nouvel reintegration
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(reintegrationBundle.getString("reintegration_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(reintegrationBundle
					.getString("reintegration_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	
		
	/**
	 * l'ann�e actuelle [ReintegrationBean.getNowYear] Method
	 * 
	 * @author BELBRIK Oualid on : 28 juillet 2014 17:46:16
	 * @return
	 */
	public int getPresentYear() {

		return Calendar.getInstance().get(Calendar.YEAR);
	}

	

	/**
	 * [ReintegrationBean.referentielHelper] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 juillet 2014 09:18:03
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [ReintegrationBean.referentielHelper] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 juillet 2014 09:18:03
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	

	/**
	 * evenement lors de la mise � jour du Reintegration [ReintegrationBean.openReintegration]
	 * Method
	 * 
	 * @author BELBRIK Oualid on : 15 juillet 2014 11:39:25
	 * @param selectedReintegrationDto
	 */
	public void openReintegration(ReintegrationDto selectedReintegrationDto) {

		setReintegrationDto(selectedReintegrationDto);
		setReintegrationId(selectedReintegrationDto.getId() + "");


	}

	/**
	 * supprime un Reintegration de la liste des Reintegrations [ReintegrationBean.removeReintegration]
	 * Method
	 * 
	 * @author BELBRIK Oualid on : 15 juillet 2014 11:39:02
	 * @param selectedReintegrationDto
	 */
	public void removeReintegration(ReintegrationDto selectedReintegrationDto) {

		FacesMessage msg = new FacesMessage();

		if ((selectedReintegrationDto != null)
				&& (selectedReintegrationDto.getId() > 0)) {
			try {

				reintegrationService.remove(selectedReintegrationDto);

				// rafraichir la liste des Reintegrations 

				listeReintegrationsDto = reintegrationService
						.findReintegrationsByIdExclusion(Integer
								.parseInt(exclusionId));
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(reintegrationBundle
						.getString("reintegration_success_delete_reintegration"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(reintegrationBundle
						.getString("reintegration_error_delete_reintegration"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}



	/**
	 * 
	 * [ReintegrationBean.getDossierEtudiantBean] Method 
	 * @author BELBRIK Oualid  on : 15 juillet 2014  10:30:24
	 * @return
	 */
	public ExclusionBean getExclusionBean() {
		return exclusionBean;
	}

	/**
	 * 
	 * [ReintegrationBean.setDossierEtudiantBean] Method 
	 * @author BELBRIK Oualid  on : 15 juillet 2014  10:30:28
	 * @param dossierEtudiantBean
	 */
	public void setExclusionBean(ExclusionBean exclusionBean) {
		if (exclusionBean != null && exclusionId == null) {
			setExclusionId(exclusionBean.getExclusionId());
		}
		this.exclusionBean = exclusionBean;
	}

	/**
	 * [ReintegrationExclusionBean.listeAllReintegrationsDto] Getter 
	 * @author BELBRIK Oualid on : 24 juil. 2014  15:20:32
	 * @return the listeAllReintegrationsDto
	 */
	public List<ReintegrationDto> getListeAllReintegrationsDto() {
		return listeAllReintegrationsDto;
	}

	/**
	 * [ReintegrationExclusionBean.listeAllReintegrationsDto] Setter 
	 * @author BELBRIK Oualid on : 24 juil. 2014  15:20:32
	 * @param listeAllReintegrationsDto the listeAllReintegrationsDto to set
	 */
	public void setListeAllReintegrationsDto(
			List<ReintegrationDto> listeAllReintegrationsDto) {
		this.listeAllReintegrationsDto = listeAllReintegrationsDto;
	}

	

}

