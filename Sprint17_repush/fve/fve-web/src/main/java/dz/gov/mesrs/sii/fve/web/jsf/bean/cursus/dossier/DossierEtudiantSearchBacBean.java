package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

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

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OuvertureOffreFormationDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;

/**
 * @author BELDI Jamel on : 22 mai 2014 08:55:12
 */
@ManagedBean(name = "dossierEtudiantSearchBacBean")
@RequestScoped
public class DossierEtudiantSearchBacBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 08:55:03
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleDossierEtudiant;
	private ResourceBundle bundleCommon;
	private List<DossierBachelierDto> dossierBacResultSearch;
	private DossierBachelierDto dossierBacSearchDto;
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{bacService}")
	private BacService bacService;
	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:58
	 */
	public DossierEtudiantSearchBacBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDossierEtudiant = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.DOSSIER_ETUDIANT_BUNDLE_MSG_NAME);
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
		loadSearchDto();		
		loadDossierBacResultSearch();
	}

	

	private void loadSearchDto() {
		dossierBacSearchDto = sessionBean.getDossierBachelierSearchDto();
		//dossierBacSearchDto.setNomFr("%");
	}

	private void loadDossierBacResultSearch() {
		FacesMessage msg = new FacesMessage();
		dossierBacResultSearch= new ArrayList<DossierBachelierDto>();
		try{
			if(dossierBacSearchDto!=null && (dossierBacSearchDto.getNomFr()!=null &&  !dossierBacSearchDto.getNomFr().trim().equals("null") &&  !dossierBacSearchDto.getNomFr().trim().equals(""))  ||(dossierBacSearchDto.getPrenomFr()!=null &&  !dossierBacSearchDto.getPrenomFr().trim().equals("null") &&  !dossierBacSearchDto.getPrenomFr().trim().equals("")) || (dossierBacSearchDto.getMatricule()!=null && !dossierBacSearchDto.getMatricule().trim().equals("null") &&  !dossierBacSearchDto.getMatricule().trim().equals(""))){
				List<DossierBachelierDto> dossierBacheliers = bacService.findAdvanced(dossierBacSearchDto, null, false,false,null,null,null) ;	
		
		if(dossierBacheliers!=null && !dossierBacheliers.isEmpty()){
	for (DossierBachelierDto dossierBachelierDto : dossierBacheliers) {
		
//		if (dossierBachelierDto!=null && dossierBachelierDto.getIdDossierEtudiant()==0){
			DossierEtudiantDto _searchDto=new DossierEtudiantDto();
			_searchDto.setDossierBachelierId(dossierBachelierDto.getId());
			List<DossierEtudiantDto> result=dossierEtudiantService.findAdvanced(_searchDto);
			if(result!=null && !result.isEmpty()){
				
			}else{
			
		dossierBacResultSearch.add(dossierBachelierDto);
			}
//	  }
	}
		}
			}
				
		}catch (Exception e) {
			dossierBacResultSearch= new ArrayList<DossierBachelierDto>();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
		
	}
	
	/**
	 * [DossierEtudiantSearchBacBean.dossierBacResultSearch] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @return the dossierBacResultSearch
	 */
	public List<DossierBachelierDto> getDossierBacResultSearch() {
		return dossierBacResultSearch;
	}

	/**
	 * [DossierEtudiantSearchBacBean.dossierBacResultSearch] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @param dossierBacResultSearch the dossierBacResultSearch to set
	 */
	public void setDossierBacResultSearch(
			List<DossierBachelierDto> dossierBacResultSearch) {
		this.dossierBacResultSearch = dossierBacResultSearch;
	}

	/**
	 * [DossierEtudiantSearchBacBean.dossierBacSearchDto] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @return the dossierBacSearchDto
	 */
	public DossierBachelierDto getDossierBacSearchDto() {
	
		return dossierBacSearchDto;
		
	}

	/**
	 * [DossierEtudiantSearchBacBean.dossierBacSearchDto] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @param dossierBacSearchDto the dossierBacSearchDto to set
	 */
	public void setDossierBacSearchDto(DossierBachelierDto dossierBacSearchDto) {
		this.dossierBacSearchDto = dossierBacSearchDto;
	}

	/**
	 * [DossierEtudiantSearchBacBean.flash] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierEtudiantSearchBacBean.flash] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierEtudiantSearchBacBean.sessionBean] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierEtudiantSearchBacBean.sessionBean] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  11:58:01
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierEtudiantSearchBacBean.exception] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  12:10:48
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierEtudiantSearchBacBean.exception] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  12:10:48
	 * @param exception the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DossierEtudiantSearchBacBean.dossierEtudiantService] Getter 
	 * @author BELDI Jamel on : 22 mai 2014  12:15:14
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantSearchBacBean.dossierEtudiantService] Setter 
	 * @author BELDI Jamel on : 22 mai 2014  12:15:14
	 * @param dossierEtudiantService the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantSearchBacBean.bacService] Getter 
	 * @author BELDI Jamel on : 29 mai 2014  18:10:41
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [DossierEtudiantSearchBacBean.bacService] Setter 
	 * @author BELDI Jamel on : 29 mai 2014  18:10:41
	 * @param bacService the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	

	
	
}
