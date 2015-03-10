package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.inscription;


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

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;


@ManagedBean(name = "dossierInscriptionSearchCmpBean")
@RequestScoped
public class DossierInscriptionSearchCmpBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DossierInscriptionSearchCmpBean.class);
	private ResourceBundle bundleDossierInscription;
	private ResourceBundle bundleCommon;
	private int exception;
	private List<DossierInscriptionAdministrativeDto> dossierInscriptionResultSearch;
	private DossierInscriptionAdministrativeDto dossierInscriptionSearchDto;
	//private AnneeAcademiqueDto anneeAcademiqueDto;
	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private String codeTypeDossier;
	@ManagedProperty("#{param.renderedDetailContent}")
   private String renderedDetailContent;
	private List<SelectItem> listeAnneeAcademiques;
	
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	public DossierInscriptionSearchCmpBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDossierInscription = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.DOSSIER_INSCRIPTION_MESSAGES_FILE_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.COMMON_MESSAGES_FILE_NAME);
	

	}

	@PostConstruct
	public void init() {
		exception = 0;
		//loadAnneeAcademique();
		loadSearchDto();
	    loadDossierInscriptionResultSearch();

	}
//	private void loadAnneeAcademique() {
//		FacesMessage msg = new FacesMessage();
//		try {
//		log.info("loadOuvertureOf");
//		int currentYear=Calendar.getInstance().get(Calendar.YEAR);
//         int month = Calendar.getInstance().get(Calendar.MONTH);
//		 
//		 int premiereAnnee;		 
//		 //9 : mois de septembre, en supposant que l'annee commence en mois de septembre
//		 if(month >= 9
//				 ) {
//			 premiereAnnee = currentYear;
//		 }
//		 else{
//			 premiereAnnee = --currentYear;
//		 }
//		 anneeAcademiqueDto= anneeAcademiqueService.findByFirstAndSecondYear(Short.valueOf(Integer.toString(currentYear)), Short.valueOf(Integer.toString(currentYear+1)));
//		if(anneeAcademiqueDto == null){
//			//save nouvelle annee academique
//			anneeAcademiqueDto = new AnneeAcademiqueDto();
//			anneeAcademiqueDto.setPremiereAnnee(Short.valueOf(Integer.toString(currentYear)));
//			anneeAcademiqueDto.setDeuxiemeAnnee( Short.valueOf(Integer.toString(currentYear+1)));
//			anneeAcademiqueDto = anneeAcademiqueService.insertOrUpdate(anneeAcademiqueDto);
//		}
//		}catch (Exception e) {
//			exception = 5;
//			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			msg.setSummary(bundleCommon.getString("error_echec") + exception
//					+ ": " + bundleCommon.getString("error_echec_inconnue"));
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//
//		}
//	}
	private void loadSearchDto() {
		dossierInscriptionSearchDto = sessionBean.getDossierInscriptionSearchCmpDto();
		//dossierInscriptionSearchDto.setAnneeAcademiqueId(anneeAcademiqueDto.getId());
	}

	public void loadDossierInscriptionResultSearch() {
		FacesMessage msg = new FacesMessage();
		dossierInscriptionResultSearch= new ArrayList<DossierInscriptionAdministrativeDto>();
		try{
			if(dossierInscriptionSearchDto!=null){
				if(dossierInscriptionSearchDto.getNumeroMatricule()!=null&& !dossierInscriptionSearchDto.getNumeroMatricule().trim().equals("")
						&& !dossierInscriptionSearchDto.getNumeroMatricule().trim().equals("null")) {	
					List<DossierInscriptionAdministrativeDto> dossierinscriptions=dossierInscriptionAdministrativeService.findAdvanced(dossierInscriptionSearchDto, true);
					if(dossierinscriptions!=null && !dossierinscriptions.isEmpty()){
						DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = dossierinscriptions.get(0);
						RefIndividuDto refIndividuDto=refIndividuService.findByIdentifiant(dossierInscriptionAdministrativeDto.getNin());
						map(refIndividuDto, dossierInscriptionAdministrativeDto);
						dossierInscriptionResultSearch.add(dossierInscriptionAdministrativeDto);
					}
				}
				else{
		RefIndividuDto refIndividuSearch= new RefIndividuDto();	
		refIndividuSearch = map(dossierInscriptionSearchDto);
		List<RefIndividuDto> listIndividu=refIndividuService.findAdvanced(refIndividuSearch);
		if(listIndividu!=null && !listIndividu.isEmpty()){
			
		for (RefIndividuDto refIndividuDto : listIndividu) {
		dossierInscriptionSearchDto.setNin(refIndividuDto.getIdentifiant());
		//List<DossierEtudiantDto> dossierEtudiants=dossierEtudiantService.findAdvanced(dossierInscriptionSearchDto);
		List<DossierInscriptionAdministrativeDto> dossierinscriptions=dossierInscriptionAdministrativeService.findAdvanced(dossierInscriptionSearchDto, true);
		dossierInscriptionSearchDto.setNin(null);
		
		if(dossierinscriptions!=null && !dossierinscriptions.isEmpty()){
	for (DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto : dossierinscriptions) {
		map(refIndividuDto, dossierInscriptionAdministrativeDto);
		dossierInscriptionResultSearch.add(dossierInscriptionAdministrativeDto);
	}
		}
		
		}
		}
			}}	
		}catch (Exception e) {
			dossierInscriptionResultSearch= new ArrayList<DossierInscriptionAdministrativeDto>();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	
	public int getException() {
		return exception;
	}

	
	public void setException(int exception) {
		this.exception = exception;
	}

	
	
	
	
	

	
	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionResultSearch] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  10:18:24
	 * @return the dossierInscriptionResultSearch
	 */
	public List<DossierInscriptionAdministrativeDto> getDossierInscriptionResultSearch() {
		return dossierInscriptionResultSearch;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionResultSearch] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  10:18:24
	 * @param dossierInscriptionResultSearch the dossierInscriptionResultSearch to set
	 */
	public void setDossierInscriptionResultSearch(
			List<DossierInscriptionAdministrativeDto> dossierInscriptionResultSearch) {
		this.dossierInscriptionResultSearch = dossierInscriptionResultSearch;
	}

	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	
	public Flash getFlash() {
		return flash;
	}

	
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
		
	private RefIndividuDto map(DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto){
		RefIndividuDto refIndividuDto = new RefIndividuDto();
		refIndividuDto.setId(dossierInscriptionAdministrativeDto.getIndividuId());
		refIndividuDto.setIdentifiant(dossierInscriptionAdministrativeDto.getNin());
		refIndividuDto.setNomLatin(dossierInscriptionAdministrativeDto.getIndividuNomLatin());
		refIndividuDto.setNomArabe(dossierInscriptionAdministrativeDto.getIndividuNomArabe());
		refIndividuDto.setPrenomArabe(dossierInscriptionAdministrativeDto.getIndividuPrenomArabe());
		refIndividuDto.setPrenomLatin(dossierInscriptionAdministrativeDto.getIndividuPrenomLatin());
		if(dossierInscriptionAdministrativeDto.getIndividuDateNaissance()!=null){
		refIndividuDto.setDateNaissance(dossierInscriptionAdministrativeDto.getIndividuDateNaissance());
		}
		return refIndividuDto;
	}
	
	
	
	private DossierInscriptionAdministrativeDto  map(RefIndividuDto refIndividuDto ){
		DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		dossierInscriptionAdministrativeDto.setIndividuId(refIndividuDto.getId());
		dossierInscriptionAdministrativeDto.setNin(refIndividuDto.getIdentifiant());
		dossierInscriptionAdministrativeDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierInscriptionAdministrativeDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if(refIndividuDto.getDateNaissance()!=null){
			dossierInscriptionAdministrativeDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}
		return dossierInscriptionAdministrativeDto;
	}
	
	private void map(RefIndividuDto refIndividuDto , DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto){
		if(dossierInscriptionAdministrativeDto==null){
			dossierInscriptionAdministrativeDto = new DossierInscriptionAdministrativeDto();
		}
		dossierInscriptionAdministrativeDto.setIndividuId(refIndividuDto.getId());
		dossierInscriptionAdministrativeDto.setNin(refIndividuDto.getIdentifiant());
		dossierInscriptionAdministrativeDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierInscriptionAdministrativeDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierInscriptionAdministrativeDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if(refIndividuDto.getDateNaissance()!=null){
			dossierInscriptionAdministrativeDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}
		
	}

	public String getCodeTypeDossier() {
		codeTypeDossier = CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT;
		return codeTypeDossier;
	}

	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}
	
	public void selectDossierInscription(){
		setRenderedDetailContent("true");
	}

	/**
	 * [DossierInscriptionSearchCmpBean.renderedDetailContent] Getter 
	 * @author BELDI Jamel on : 11 juin 2014  17:50:28
	 * @return the renderedDetailContent
	 */
	public String getRenderedDetailContent() {
		return renderedDetailContent;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.renderedDetailContent] Setter 
	 * @author BELDI Jamel on : 11 juin 2014  17:50:28
	 * @param renderedDetailContent the renderedDetailContent to set
	 */
	public void setRenderedDetailContent(String renderedDetailContent) {
		this.renderedDetailContent = renderedDetailContent;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionSearchDto] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  10:17:29
	 * @return the dossierInscriptionSearchDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionSearchDto() {
		return dossierInscriptionSearchDto;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionSearchDto] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  10:17:29
	 * @param dossierInscriptionSearchDto the dossierInscriptionSearchDto to set
	 */
	public void setDossierInscriptionSearchDto(
			DossierInscriptionAdministrativeDto dossierInscriptionSearchDto) {
		this.dossierInscriptionSearchDto = dossierInscriptionSearchDto;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionAdministrativeService] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  10:17:29
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.dossierInscriptionAdministrativeService] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  10:17:29
	 * @param dossierInscriptionAdministrativeService the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

//	/**
//	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueDto] Getter 
//	 * @author BELDI Jamel on : 12 juin 2014  10:40:10
//	 * @return the anneeAcademiqueDto
//	 */
//	public AnneeAcademiqueDto getAnneeAcademiqueDto() {
//		return anneeAcademiqueDto;
//	}
//
//	/**
//	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueDto] Setter 
//	 * @author BELDI Jamel on : 12 juin 2014  10:40:10
//	 * @param anneeAcademiqueDto the anneeAcademiqueDto to set
//	 */
//	public void setAnneeAcademiqueDto(AnneeAcademiqueDto anneeAcademiqueDto) {
//		this.anneeAcademiqueDto = anneeAcademiqueDto;
//	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  10:40:10
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.anneeAcademiqueService] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  10:40:10
	 * @param anneeAcademiqueService the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}
	
	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Getter 
	 * @author BELDI Jamel on : 13 mai 2014  16:21:47
	 * @return the listeAnneeAcademiques
	 */
	public List<SelectItem> getListeAnneeAcademiques() {
		FacesMessage msg = new FacesMessage();
		try{
		if(listeAnneeAcademiques==null || listeAnneeAcademiques.isEmpty()){
			listeAnneeAcademiques = new ArrayList<SelectItem>();
			List<AnneeAcademiqueDto> liste=anneeAcademiqueService.findAll();
			if(liste!=null && !liste.isEmpty()){
			for (AnneeAcademiqueDto anneeAcademiqueDto : liste) {
				SelectItem si= new SelectItem(anneeAcademiqueDto.getId(), anneeAcademiqueDto.getPremiereAnnee()+"/"+anneeAcademiqueDto.getDeuxiemeAnnee());
				listeAnneeAcademiques.add(si);
			}
			}
		}
		
	}catch (Exception e) {		
		exception = 3;
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleCommon.getString("error_echec") + exception
				+ ": " + bundleCommon.getString("error_echec_inconnue"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
		return listeAnneeAcademiques;
	}

	/**
	 * [OuvertureOfSearchBean.listeAnneeAcademiques] Setter 
	 * @author BELDI Jamel on : 13 mai 2014  16:21:47
	 * @param listeAnneeAcademiques the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:55:10
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierInscriptionSearchCmpBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:55:10
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}
	
	
	
}

