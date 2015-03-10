package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.transfert;


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

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierTransfertDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierTransfertService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;


@ManagedBean(name = "dossierTransfertSearchBean")
@RequestScoped
public class DossierTransfertSearchBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DossierTransfertSearchBean.class);
	private ResourceBundle transfertBundle;
	private ResourceBundle bundleCommon;
	private int exception;
	private List<DossierTransfertDto> dossierTransfertResultSearch;
	private DossierTransfertDto dossierTransfertSearchDto;
	@ManagedProperty(value = "#{dossierTransfertService}")
	private DossierTransfertService dossierTransfertService;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	private String codeTypeDossier;
	private List<SelectItem> listeAnneeAcademiques;
	@ManagedProperty(value = "#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	

	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty("#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	public DossierTransfertSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		transfertBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.DOSSIER_TRANSFERT_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
	

	}

	@PostConstruct
	public void init() {
		exception = 0;
		loadSearchDto();
		loadDossierTransfertResultSearch();

	}
	
	private void loadSearchDto() {
		dossierTransfertSearchDto = sessionBean.getDossierTransfertSearchDto();
	}

	private void loadDossierTransfertResultSearch() {
		FacesMessage msg = new FacesMessage();
		dossierTransfertResultSearch= new ArrayList<DossierTransfertDto>();
		try{
			if(dossierTransfertSearchDto!=null){				
		RefIndividuDto refIndividuSearch= new RefIndividuDto();	
		refIndividuSearch = map(dossierTransfertSearchDto);
		List<RefIndividuDto> listIndividu=refIndividuService.findAdvanced(refIndividuSearch);
		if(listIndividu!=null && !listIndividu.isEmpty()){			
		for (RefIndividuDto refIndividuDto : listIndividu) {
			dossierTransfertSearchDto.setIndividuNin(refIndividuDto.getIdentifiant());
		List<DossierTransfertDto> dossierTransferts=dossierTransfertService.findAdvanced(dossierTransfertSearchDto);
		dossierTransfertSearchDto.setIndividuNin(null);
		
		if(dossierTransferts!=null && !dossierTransferts.isEmpty()){
	for (DossierTransfertDto dossierTransfertDto : dossierTransferts) {
		loadInfosFilieres(dossierTransfertDto);
		map(refIndividuDto, dossierTransfertDto);
		
		dossierTransfertResultSearch.add(dossierTransfertDto);
	}
		}
		
		}
		}else {
				List<DossierTransfertDto> dossierTransferts=dossierTransfertService.findAdvanced(dossierTransfertSearchDto);
				
			if(dossierTransferts!=null && !dossierTransferts.isEmpty()){
			for (DossierTransfertDto dossierTransfertDto : dossierTransferts) {
//				//Offre de formation d'origine
//				 OuvertureOffreFormationDto ouvertureOffreFormationOrigine = ouvertureOffreFormationService.findById(dossierTransfertDto.getOuvertureOfOrigineId());		
//				 OffreFormationDto offreFormationDto = offreFormationService.findById(ouvertureOffreFormationOrigine.getOffreFormationId());
//				 dossierTransfertDto.setOfOrigineLibelleFr(offreFormationDto.getI18nDtos().get("fr").getLibelle());
//				 dossierTransfertDto.setOfOrigineLibelleFiliereFr(offreFormationDto.getI18nDtos().get("fr").getLibelleFiliere());
//				 ////Offre de formation Demande
//				 OuvertureOffreFormationDto ouvertureOffreFormationDemande = ouvertureOffreFormationService.findById(dossierTransfertDto.getOuvertureOfDemandeeId());
//				 OffreFormationDto offreFormationDto2 = offreFormationService.findById(ouvertureOffreFormationDemande.getOffreFormationId());
//				 dossierTransfertDto.setOfDemandeeLibelleFr(offreFormationDto2.getI18nDtos().get("fr").getLibelle());
//				 dossierTransfertDto.setOfDemandeeLibelleFiliereFr(offreFormationDto2.getI18nDtos().get("fr").getLibelleFiliere());
				
				loadInfosFilieres(dossierTransfertDto);
				 //Individu
				RefIndividuDto refIndividuDto=refIndividuService.findByIdentifiant(dossierTransfertDto.getIndividuNin());
				map(refIndividuDto, dossierTransfertDto);
				
				dossierTransfertResultSearch.add(dossierTransfertDto);
			}
		
			}
		}
			}
		}catch (Exception e) {
			dossierTransfertResultSearch= new ArrayList<DossierTransfertDto>();
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + exception
					+ ": " + bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	
	private void loadInfosFilieres(DossierTransfertDto dossierTransfertDto) throws Exception{
		
		/**Offre de formation d'origine*/
//				DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService.findById(Integer.valueOf(dossierTransfertDto.getDossierInscriptionId())); 				
//							if (dossierInscriptionAdministrativeDto
//									.getRefCodeFiliere() != null) {
//							RefFiliereLmdDto _filiere = ppmWSClient
//									.findFiliereByIdentifiant(dossierInscriptionAdministrativeDto
//											.getRefCodeFiliere());
//							if(_filiere!=null){
//								 dossierTransfertDto.setOfOrigineLibelleFiliereFr
//											(_filiere
//													.getLlFiliere());
//								
//
//							}
//							
//							}else 	if (dossierInscriptionAdministrativeDto
//									.getRefCodeDomaine() != null) {
//								RefDomaineLMDDto _domaine = ppmWSClient
//										.findDomaineLMDByIdentifiant(dossierInscriptionAdministrativeDto
//												.getRefCodeDomaine());
//								if(_domaine!=null){
//									 dossierTransfertDto.setOfOrigineLibelleFiliereFr(_domaine
//														.getLlDomaine());
//								}
//							} 
//		if(dossierTransfertDto.getOuvertureOfDemandeeId()!= null){
//							 OuvertureOffreFormationDto ouvertureOffreFormationOrigine = ouvertureOffreFormationService.findById(dossierTransfertDto.getOuvertureOfOrigineId());		
//							 OffreFormationDto _offreFormationDto = offreFormationService.findById(ouvertureOffreFormationOrigine.getOffreFormationId());
//							 dossierTransfertDto.setOfOrigineLibelleFr(_offreFormationDto.getI18nDtos().get("fr").getLibelle());
//							 
//		}
//							
//				 /**Offre de formation Demande*/
//							if(dossierTransfertDto.getOuvertureOfDemandeeId()!= null){
//								 OuvertureOffreFormationDto _ouvertureOfDemandee = ouvertureOffreFormationService.findById(dossierTransfertDto.getOuvertureOfDemandeeId());			
//								 OffreFormationDto _ofDemandee = offreFormationService.findById(_ouvertureOfDemandee.getOffreFormationId());
//								 dossierTransfertDto.setOfDemandeeLibelleFr(_ofDemandee.getI18nDtos().get("fr").getLibelle());
//								}			
				
	}
	
	/**
	 * [DossierTransfertSearchBean.exception] Getter 
	 * @author BELDI Jamel on : 25 mai 2014  12:21:37
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierTransfertSearchBean.exception] Setter 
	 * @author BELDI Jamel on : 25 mai 2014  12:21:37
	 * @param exception the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	

	
	


	/**
	 * [DossierTransfertSearchBean.dossierTransfertResultSearch] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @return the dossierTransfertResultSearch
	 */
	public List<DossierTransfertDto> getDossierTransfertResultSearch() {
		return dossierTransfertResultSearch;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertResultSearch] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @param dossierTransfertResultSearch the dossierTransfertResultSearch to set
	 */
	public void setDossierTransfertResultSearch(
			List<DossierTransfertDto> dossierTransfertResultSearch) {
		this.dossierTransfertResultSearch = dossierTransfertResultSearch;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertSearchDto] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @return the dossierTransfertSearchDto
	 */
	public DossierTransfertDto getDossierTransfertSearchDto() {
		return dossierTransfertSearchDto;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertSearchDto] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @param dossierTransfertSearchDto the dossierTransfertSearchDto to set
	 */
	public void setDossierTransfertSearchDto(
			DossierTransfertDto dossierTransfertSearchDto) {
		this.dossierTransfertSearchDto = dossierTransfertSearchDto;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertService] Getter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @return the dossierTransfertService
	 */
	public DossierTransfertService getDossierTransfertService() {
		return dossierTransfertService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierTransfertService] Setter 
	 * @author BELDI Jamel on : 12 juin 2014  18:43:38
	 * @param dossierTransfertService the dossierTransfertService to set
	 */
	public void setDossierTransfertService(
			DossierTransfertService dossierTransfertService) {
		this.dossierTransfertService = dossierTransfertService;
	}

	/**
	 * [DossierTransfertSearchBean.flash] Getter 
	 * @author BELDI Jamel on : 25 mai 2014  12:21:37
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierTransfertSearchBean.flash] Setter 
	 * @author BELDI Jamel on : 25 mai 2014  12:21:37
	 * @param flash the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierTransfertSearchBean.sessionBean] Getter 
	 * @author BELDI Jamel on : 25 mai 2014  12:29:37
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierTransfertSearchBean.sessionBean] Setter 
	 * @author BELDI Jamel on : 25 mai 2014  12:29:37
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierTransfertSearchBean.map] Method 
	 * @author BELDI Jamel  on : 26 mai 2014  11:28:54
	 * @param dossierTransfertDto
	 * @return
	 */
	private RefIndividuDto map(DossierTransfertDto dossierTransfertDto){
		RefIndividuDto refIndividuDto = new RefIndividuDto();
		refIndividuDto.setId(dossierTransfertDto.getIndividuId());
		refIndividuDto.setIdentifiant(dossierTransfertDto.getIndividuNin());
		refIndividuDto.setNomLatin(dossierTransfertDto.getIndividuNomLatin());
		refIndividuDto.setNomArabe(dossierTransfertDto.getIndividuNomArabe());
		refIndividuDto.setPrenomArabe(dossierTransfertDto.getIndividuPrenomArabe());
		refIndividuDto.setPrenomLatin(dossierTransfertDto.getIndividuPrenomLatin());
		if(dossierTransfertDto.getIndividuDateNaissance()!=null){
		refIndividuDto.setDateNaissance(dossierTransfertDto.getIndividuDateNaissance());
		}
		return refIndividuDto;
	}
	
	
	/**
	 * [DossierTransfertSearchBean.map] Method 
	 * @author BELDI Jamel  on : 26 mai 2014  11:33:53
	 * @param refIndividuDto
	 * @return
	 */
	private DossierTransfertDto map(RefIndividuDto refIndividuDto ){
		DossierTransfertDto dossierTransfertDto = new DossierTransfertDto();
		dossierTransfertDto.setIndividuId(refIndividuDto.getId());
		dossierTransfertDto.setIndividuNin(refIndividuDto.getIdentifiant());
		dossierTransfertDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierTransfertDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierTransfertDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierTransfertDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if(refIndividuDto.getDateNaissance()!=null){
		dossierTransfertDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}
		return dossierTransfertDto;
	}
	
	private void map(RefIndividuDto refIndividuDto , DossierTransfertDto dossierTransfertDto){
		if(dossierTransfertDto==null){
			dossierTransfertDto = new DossierTransfertDto();
		}
		dossierTransfertDto.setIndividuId(refIndividuDto.getId());
		dossierTransfertDto.setIndividuNin(refIndividuDto.getIdentifiant());
		dossierTransfertDto.setIndividuNomLatin(refIndividuDto.getNomLatin());
		dossierTransfertDto.setIndividuNomArabe(refIndividuDto.getNomArabe());
		dossierTransfertDto.setIndividuPrenomArabe(refIndividuDto.getPrenomArabe());
		dossierTransfertDto.setIndividuPrenomLatin(refIndividuDto.getPrenomLatin());
		if(refIndividuDto.getDateNaissance()!=null){
		dossierTransfertDto.setIndividuDateNaissance(refIndividuDto.getDateNaissance());
		}
		
	}

	/**
	 * [DossierTransfertSearchBean.codeTypeDossier] Getter 
	 * @author BELDI Jamel on : 28 mai 2014  08:34:11
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		codeTypeDossier = CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT;
		return codeTypeDossier;
	}

	/**
	 * [DossierTransfertSearchBean.codeTypeDossier] Setter 
	 * @author BELDI Jamel on : 28 mai 2014  08:34:11
	 * @param codeTypeDossier the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}
	

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
	 * [DossierTransfertSearchBean.listeAnneeAcademiques] Getter 
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
	 * [DossierTransfertSearchBean.listeAnneeAcademiques] Setter 
	 * @author BELDI Jamel on : 15 juin 2014  10:39:37
	 * @param listeAnneeAcademiques the listeAnneeAcademiques to set
	 */
	public void setListeAnneeAcademiques(List<SelectItem> listeAnneeAcademiques) {
		this.listeAnneeAcademiques = listeAnneeAcademiques;
	}

	/**
	 * [DossierTransfertSearchBean.ouvertureOffreFormationService] Getter 
	 * @author BELDI Jamel on : 15 juin 2014  15:14:06
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.ouvertureOffreFormationService] Setter 
	 * @author BELDI Jamel on : 15 juin 2014  15:14:06
	 * @param ouvertureOffreFormationService the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.offreFormationService] Getter 
	 * @author BELDI Jamel on : 15 juin 2014  15:14:06
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.offreFormationService] Setter 
	 * @author BELDI Jamel on : 15 juin 2014  15:14:06
	 * @param offreFormationService the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierInscriptionAdministrativeService] Getter 
	 * @author BELDI Jamel on : 13 juil. 2014  11:17:43
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierTransfertSearchBean.dossierInscriptionAdministrativeService] Setter 
	 * @author BELDI Jamel on : 13 juil. 2014  11:17:43
	 * @param dossierInscriptionAdministrativeService the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [DossierTransfertSearchBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:48:48
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DossierTransfertSearchBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:48:48
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DossierTransfertSearchBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  16:48:48
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierTransfertSearchBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  16:48:48
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}
	
}

