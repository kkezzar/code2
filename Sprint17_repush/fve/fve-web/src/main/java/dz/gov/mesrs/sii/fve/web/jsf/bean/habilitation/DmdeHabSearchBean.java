/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.habilitation.DmdeHabSearchBean.java] 
 * @author BELDI Jamel on : 27 avr. 2014  12:08:39
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.habilitation;

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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.DemandeI18nDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.DemandeService;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;

/**
 * @author BELDI Jamel  on : 27 avr. 2014  12:08:39
 */
@ManagedBean(name = "dmdeHabSearchBean")
@RequestScoped
public class DmdeHabSearchBean implements Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 27 avr. 2014  12:09:27
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DmdeHabSearchBean.class);
	private ResourceBundle bundleDmde;
	private ResourceBundle bundleCommon;
	private int exception;
	@ManagedProperty(value="#{demandeService}")
	private DemandeService demandeService;
	private List<DemandeI18nDto> demandeResultSearch = new ArrayList<DemandeI18nDto>();
	private String fonctionParam;
	private DemandeI18nDto searchDemandeI18nDto;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	
	/**
	 * [DmdeHabSearchBean.DmdeHabSearchBean()] Constructor
	 * @author BELDI Jamel  on : 27 avr. 2014  12:08:39	
	 */
	public DmdeHabSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDmde = facesContext.getApplication().getResourceBundle(facesContext,
				OfConstants.DEMANDE_HAB_MESSAGES_FILE_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(facesContext,
				OfConstants.COMMON_MESSAGES_FILE_NAME);
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		String fonction = (String) request.getAttribute("fonctionParam");
		log.info("var request*******************************************fonctionParam: "
				+ fonction);
		if (fonction != null) {
			this.fonctionParam = fonction;
		}
	}
	
	@PostConstruct
	public void init(){
		if(fonctionParam !=null && fonctionParam.trim()!=""){
			loadDemandeResultSearch();
		}
	}
	
	
	private  void loadDemandeResultSearch(){
		try{
		if(bundleDmde.getString(OfConstants.HABILITATION_FONCTION_SOUMISSION_KEY).equals(fonctionParam)){
			searchDemandeI18nDto = new DemandeI18nDto();
			searchDemandeI18nDto.setLangue("fr");
			searchDemandeI18nDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE).getId());// ETAT Créé de l'entité demande
			searchDemandeI18nDto.setTypeDemandeId(1);
			demandeResultSearch = demandeService.findAdvanced(searchDemandeI18nDto);
		}else if(bundleDmde.getString(OfConstants.HABILITATION_FONCTION_GESTION_KEY).equals(fonctionParam)){
			searchDemandeI18nDto = new DemandeI18nDto();
			searchDemandeI18nDto.setLangue("fr");
			searchDemandeI18nDto.setTypeDemandeId(1);
			demandeResultSearch = demandeService.findAdvanced(searchDemandeI18nDto);//FindAll
		}else if(bundleDmde.getString(OfConstants.HABILITATION_FONCTION_VALIDATION_KEY).equals(fonctionParam)){
			searchDemandeI18nDto = new DemandeI18nDto();
			searchDemandeI18nDto.setLangue("fr");
			searchDemandeI18nDto.setIdSituation(situationService.findBySituationEntiteByCodeSituation(UtilConstants.ENTITE_DEMANDE_CODE, UtilConstants.DEMANDE_SITUTAION_SOUMISE_VALIDATION_CODE).getId());//2 Soumise pour validation
			searchDemandeI18nDto.setTypeDemandeId(1);
			demandeResultSearch = demandeService.findAdvanced(searchDemandeI18nDto);
			
		}
		
		}catch(Exception e){
			FacesMessage msg = new FacesMessage();
			exception=1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")+exception + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	

	/**
	 * [DmdeHabSearchBean.demandeService] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  12:20:03
	 * @return the demandeService
	 */
	public DemandeService getDemandeService() {
		return demandeService;
	}

	/**
	 * [DmdeHabSearchBean.demandeService] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  12:20:03
	 * @param demandeService the demandeService to set
	 */
	public void setDemandeService(DemandeService demandeService) {
		this.demandeService = demandeService;
	}

	/**
	 * [DmdeHabSearchBean.demandeResultSearch] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  12:22:25
	 * @return the demandeResultSearch
	 */
	public List<DemandeI18nDto> getDemandeResultSearch() {
		return demandeResultSearch;
	}

	/**
	 * [DmdeHabSearchBean.demandeResultSearch] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  12:22:25
	 * @param demandeResultSearch the demandeResultSearch to set
	 */
	public void setDemandeResultSearch(List<DemandeI18nDto> demandeResultSearch) {
		this.demandeResultSearch = demandeResultSearch;
	}

	/**
	 * [DmdeHabSearchBean.fonctionParam] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  15:32:20
	 * @return the fonctionParam
	 */
	public String getFonctionParam() {
		return fonctionParam;
	}

	/**
	 * [DmdeHabSearchBean.fonctionParam] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  15:32:20
	 * @param fonctionParam the fonctionParam to set
	 */
	public void setFonctionParam(String fonctionParam) {
		this.fonctionParam = fonctionParam;
	}

	/**
	 * [DmdeHabSearchBean.searchDemandeI18nDto] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  16:15:27
	 * @return the searchDemandeI18nDto
	 */
	public DemandeI18nDto getSearchDemandeI18nDto() {
		return searchDemandeI18nDto;
	}

	/**
	 * [DmdeHabSearchBean.searchDemandeI18nDto] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  16:15:27
	 * @param searchDemandeI18nDto the searchDemandeI18nDto to set
	 */
	public void setSearchDemandeI18nDto(DemandeI18nDto searchDemandeI18nDto) {
		this.searchDemandeI18nDto = searchDemandeI18nDto;
	}

	/**
	 * [DmdeHabSearchBean.exception] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  17:12:05
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DmdeHabSearchBean.exception] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  17:12:05
	 * @param exception the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DmdeHabSearchBean.situationService] Getter 
	 * @author BELDI Jamel on : 11 mai 2014  14:16:38
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DmdeHabSearchBean.situationService] Setter 
	 * @author BELDI Jamel on : 11 mai 2014  14:16:38
	 * @param situationService the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}
	
	
	

}
