package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.exclusion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.ExclusionDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.cursus.ExclusionService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELBRIK Oualid on : 6 juliet 2014 08:55:12
 */
@ManagedBean(name = "exclusionBean")
@RequestScoped
public class ExclusionBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 6 juliet 2014 08:55:03
	 */
	
	private static final long serialVersionUID = 1L;
	private ResourceBundle bundleCommon;
	@ManagedProperty("#{ouvertureOffreFormationService}")
	private OuvertureOffreFormationService ouvertureOffreFormationService;
	@ManagedProperty("#{offreFormationService}")
	private OffreFormationService offreFormationService;
	@ManagedProperty("#{anneeAcademiqueService}")
	private AnneeAcademiqueService anneeAcademiqueService;
	@ManagedProperty("#{param.dossierInscriptionId}")
	private String dossierInscriptionId;
	@ManagedProperty("#{param.exclusionId}")
	public boolean updateMode;
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{exclusionService}")
	private ExclusionService exclusionService;
	private ExclusionDto exclusionDto;
	@ManagedProperty("#{param.exclusionId}")
	private String exclusionId;
	@ManagedProperty("#{param.individuIdentifiant}")
	private String individuIdentifiant;
	private Integer id_Exclusion;
	private boolean entityChange;
	private static final Log log = LogFactory.getLog(ExclusionBean.class);
	private ResourceBundle exclusionBundle;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;
	private DossierDto dossierDto;
	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;
	@ManagedProperty(value = "#{referentielHelper}")
	private ReferentielHelper referentielHelper;
	@ManagedProperty(value = "#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	private List<SelectItem> listeTypeExclusion;
	private List<SelectItem> listeTypeCause;
	private List<SelectItem> listeModeEnvoi;
	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;
	@ManagedProperty(value = "#{dossierInscriptionAdministrativeService}")
	private DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService;
	private DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto;
	private List<DossierInscriptionAdministrativeDto> listDdossierInscriptionAdministrativeDto;
	private DossierEtudiantDto dossierEtudiantDto;
	@Autowired
	@ManagedProperty(value = "#{matiereConstitutiveService}")
	private MatiereConstitutiveService matiereConstitutiveService;
	private List<MatiereConstitutiveDto> matiereConstitutiveList;
	private String paramSearch;
	@ManagedProperty(value = "#{matiereConstitutiveServiceImpl}")
	private MatiereConstitutiveService matiereConstitutiveServiceImpl;
	private List<SelectItem> listMatiere;
	
	@ManagedProperty(value = "#{exclusionServiceImpl}")
	private ExclusionService exclusionServiceImpl;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;
	 private boolean typecause;
	


	/**
	 * [DossierEtudiantBean.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 07 2014 18:14:44
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DossierEtudiantBean.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 18:14:44
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 18:37:58
	 */
	public ExclusionBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		exclusionBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.EXCLUSION_BUNDLE_MSG_NAME);
	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 18:37:54
	 */

	@PostConstruct
	public void init() {
		loadExclusionDto();
			}

	private void loadExclusionDto() {
		FacesMessage msg = new FacesMessage();
		exclusionDto = new ExclusionDto();
		try {
			   if(exclusionId!=null && !exclusionId.trim().equals("null") ){
				   exclusionDto = exclusionService.findById(Integer.valueOf(exclusionId));
				   setDossierInscriptionId(exclusionDto.getDossierInscriptionId().toString());
				   loadByDossierInscriptionId();
			   } else {
				   loadByDossierInscriptionId();
			   }  
			   
		 } catch (Exception e) {
				exception=1;
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "+exception+":"
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
	}
	
	
	
	 private void loadByDossierInscriptionId() throws Exception{
			if(dossierInscriptionId!=null && !dossierInscriptionId.trim().equals("null") ){
				 
				DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService.findById(Integer.valueOf(dossierInscriptionId)); 
				 if(exclusionId==null || exclusionId.trim().equals("null")){
					 ExclusionDto exclusionSearchDto = new ExclusionDto();
					 exclusionSearchDto.setAnneeAcademiqueId(dossierInscriptionAdministrativeDto.getAnneeAcademiqueId());
					 exclusionSearchDto.setDossierInscriptionId(Integer.valueOf(dossierInscriptionId));
					 List listExclusion= exclusionService.findAdvanced(exclusionSearchDto);
					 if(listExclusion!=null && !listExclusion.isEmpty()){				 
						 exclusionDto=(ExclusionDto) listExclusion.get(0);
						 //setExclusionId(exclusionDto.getId().toString());
					 }
					 else {
						 exclusionDto.setDateDecision(new Date());
					 }
				 }
			    mapper.map(dossierInscriptionAdministrativeDto, exclusionDto);
			    AnneeAcademiqueDto anneeAcademiqueDto = anneeAcademiqueService.findById(dossierInscriptionAdministrativeDto.getAnneeAcademiqueId());
			    mapper.map(anneeAcademiqueDto, exclusionDto);
			    DossierEtudiantDto dossierEtudiantDto = dossierEtudiantService.findById(dossierInscriptionAdministrativeDto.getDossierEtudiantId());
			    mapper.map(dossierEtudiantDto, exclusionDto);
			    RefIndividuDto individuDto = refIndividuService.findByIdentifiant(dossierEtudiantDto.getNin());
				
				 mapper.map(individuDto, exclusionDto); 
				
			   }
		}

	/**
	 * [DossierEtudiantBean.exception] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierEtudiantBean.exception] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DossierEtudiantBean.flash] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierEtudiantBean.flash] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 14:48:26
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 14:48:26
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @return the dossierEtudiantService
	 */
	public ExclusionService getExclusionService() {
		return exclusionService;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 14:48:26
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setExclusionService(
			ExclusionService exclusionService) {
		this.exclusionService = exclusionService;
	}


	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 15:21:34
	 * @return the dossierEtudiantDto
	 */
	public ExclusionDto getExclusionDto() {
		return exclusionDto;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 15:21:34
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setExclusionDto(ExclusionDto exclusionDto) {
		this.exclusionDto = exclusionDto;
	}
	
	

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Getter 
	 * @author BELBRIK Oualid on : 06 juliet 2014  15:28:07
	 * @return the dossierEtudiantId
	 */
	public String getExclusionId() {
		return exclusionId;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Setter 
	 * @author BELBRIK Oualid on : 06 juliet 2014  15:28:07
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setExclusionId(String exclusionId) {
		if (exclusionId != null && exclusionId.equals("null")) {
			this.exclusionId = null;
		} else {
			this.exclusionId = exclusionId;
			try {
				setId_Exclusion(Integer.parseInt(exclusionId));
			} catch (Exception e) {

			}
		}
	}


	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 06 juliet 2014 16:36:19
	 * @return the id_DossierEtudiant
	 */
	public Integer getId_Exclusion() {
		return id_Exclusion;
	}

	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 16:36:19
	 * @param id_DossierEtudiant
	 *            the id_DossierEtudiant to set
	 */
	public void setId_Exclusion(Integer id_Exclusion) {
		this.id_Exclusion = id_Exclusion;
	}

	/**
	 * [DossierEtudiantBean.entityChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 15:47:34
	 * @param event
	 */
	public void entityChanged(ValueChangeEvent event) {
		if (event.getOldValue() == null
				|| !event.getOldValue().equals(event.getNewValue())) {
			entityChange = true;
		}
	}

	/**
	 * [DossierEtudiantBean.entityChange] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 15:47:58
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [DossierEtudiantBean.entityChange] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 15:47:58
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [DossierEtudiantBean.save] Method
	 * 
	 * @author BELBRIK Oualid on : 9 juliet 2014 15:51:40
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		
	}

	
	/**
	 * [AssiduiteBean.individuIdentifiant] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [AssiduiteBean.individuIdentifiant] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param individuIdentifiant the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [AssiduiteBean.dossierDto] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierDto
	 */
	public DossierDto getDossierDto() {
		return dossierDto;
	}

	/**
	 * [AssiduiteBean.dossierDto] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierDto the dossierDto to set
	 */
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantId] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantId] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierEtudiantId the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [AssiduiteBean.referentielHelper] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [AssiduiteBean.referentielHelper] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param referentielHelper the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}
	
	/**
	 * [AssiduiteBean.dossierService] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [AssiduiteBean.dossierService] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierService the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantService] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantService] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierEtudiantService the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	
	/**
	 * [AssiduiteBean.dossierEtudiantBean] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierEtudiantBean
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantBean] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierEtudiantBean the dossierEtudiantBean to set
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeService] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeService] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierInscriptionAdministrativeService the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeDto] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeDto] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierInscriptionAdministrativeDto the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.listDdossierInscriptionAdministrativeDto] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the listDdossierInscriptionAdministrativeDto
	 */
	public List<DossierInscriptionAdministrativeDto> getListDdossierInscriptionAdministrativeDto() {
		return listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.listDdossierInscriptionAdministrativeDto] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param listDdossierInscriptionAdministrativeDto the listDdossierInscriptionAdministrativeDto to set
	 */
	public void setListDdossierInscriptionAdministrativeDto(
			List<DossierInscriptionAdministrativeDto> listDdossierInscriptionAdministrativeDto) {
		this.listDdossierInscriptionAdministrativeDto = listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantDto] Getter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @return the dossierEtudiantDto
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantDto] Setter 
	 * @author BELBRIK Oualid on : 15 juliet 2014  13:43:36
	 * @param dossierEtudiantDto the dossierEtudiantDto to set
	 */
	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}


	public List<SelectItem> getListeTypeExclusion() {
		if (listeTypeExclusion == null || listeTypeExclusion.isEmpty()) {
			listeTypeExclusion = new ArrayList<SelectItem>();
			listeTypeExclusion = referentielHelper
					.getNomenclatureList(CursusConstants.NC_TYPE_EXCLUSION);
		}
		return listeTypeExclusion;
	}

	
	public void setListeTypeExclusion(List<SelectItem> listeTypeExclusion) {
		this.listeTypeExclusion = listeTypeExclusion;
	}
	
	
	public List<SelectItem> getListeTypeCause() {
		if (listeTypeCause == null || listeTypeCause.isEmpty()) {
			listeTypeCause = new ArrayList<SelectItem>();
			listeTypeCause = referentielHelper
					.getNomenclatureList(CursusConstants.NC_TYPE_CAUSE_EXCLUSION);
		}
		return listeTypeCause;
	}

	
	public void setListeTypeCause(List<SelectItem> listeTypeCause) {
		this.listeTypeCause = listeTypeCause;
	}
	
	
	public List<SelectItem> getListeModeEnvoi() {
		if (listeModeEnvoi == null || listeModeEnvoi.isEmpty()) {
			listeModeEnvoi = new ArrayList<SelectItem>();
			listeModeEnvoi = referentielHelper
					.getNomenclatureList(CursusConstants.NC_MODE_ENVOI);
		}
		return listeModeEnvoi;
	}

	
	public void setListeModeEnvoi(List<SelectItem> listeModeEnvoi) {
		this.listeModeEnvoi = listeModeEnvoi;
	}
	
	
	public void saveExclusion() {
		FacesMessage msg = new FacesMessage();
		try {
			exclusionService.insertOrUpdate(exclusionDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			String summary = (exclusionId==null || exclusionId.trim().equals("null")) ? "msg_success_enregistrement"
					: "msg_success_modification";
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		//	setExclusionId(exclusionDto.getId().toString());
	                

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	
	public void saveExclu() {

		FacesMessage msg = new FacesMessage();

		try {

						
			if (exclusionId != null && !exclusionId.equals("null")
					&& !exclusionId.isEmpty()) {

				log.info(" ---------- update exclusion ----");

				// update exclusion

				exclusionDto = exclusionService.insertOrUpdate(exclusionDto);

				setExclusionId(exclusionDto.getId() + "");


				// message succes update reintegration
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(exclusionBundle.getString("exclusion_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

			else {
				log.info(" ---------- save exclusion  ------ ");

				// save 
				exclusionDto = exclusionService.insertOrUpdate(exclusionDto);

				setExclusionId(exclusionDto.getId() + "");

				// message succes enregistrement nouvel exclusion
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(exclusionBundle.getString("exclusion_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(exclusionBundle
					.getString("exclusion_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	
	
	public void saveExcl() {

		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(exclusionBundle
						.getString("Exclusion_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (exclusionDto.getId() != 0) {

				log.info(" ---------- update Exclusion ----");

				// update Exclusion matiere
				exclusionDto = exclusionService
						.insertOrUpdate(exclusionDto);

				// message succes update Exclusion
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(exclusionBundle
						.getString("exclusion_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

			else {
				log.info(" ---------- save absence  ------ ");

				exclusionDto.setDossierEtudiantId(Integer
						.parseInt(dossierEtudiantId));
				
				// save Exclusion
				exclusionDto = exclusionService
						.insertOrUpdate(exclusionDto);

				// message succes enregistrement nouvel Exclusion
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(exclusionBundle
						.getString("exclusion_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(exclusionBundle
					.getString("exclusion_error_persistence_titre"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e.getMessage());
		}
	}

	
	public MatiereConstitutiveDto findById(int id) {
		return matiereConstitutiveService.findById(id);
	}

	
	public List<MatiereConstitutiveDto> findAll() {
		return matiereConstitutiveService.findAll();
	}


	public List<MatiereConstitutiveDto> find(String code, String libelleFr,
			String abriviationFr, String libelleAr, String abriviationAr) {
			
		return null;
	}

	public List<MatiereConstitutiveDto> getMatiereConstitutiveList() {
		return matiereConstitutiveList;
	}

	public void setMatiereConstitutiveList(List<MatiereConstitutiveDto> matiereConstitutiveList) {
		this.matiereConstitutiveList = matiereConstitutiveList;
	}
	
	
	public void search(){
		
		if(paramSearch != null && paramSearch.trim()!=""){
			matiereConstitutiveList = new ArrayList<MatiereConstitutiveDto>();		
			matiereConstitutiveList.add(matiereConstitutiveService.findById(Integer.parseInt(paramSearch.trim())));
		}
		else 
			matiereConstitutiveList = findAll();
	
	}

	/**
	 * [AssiduiteBean.assiduiteBundle] Getter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @return the assiduiteBundle
	 */
	public ResourceBundle getExclusionBundle() {
		return exclusionBundle;
	}

	/**
	 * [AssiduiteBean.assiduiteBundle] Setter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @param assiduiteBundle the assiduiteBundle to set
	 */
	public void setExclusionBundle(ResourceBundle exclusionBundle) {
		this.exclusionBundle = exclusionBundle;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveService] Getter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @return the matiereConstitutiveService
	 */
	public MatiereConstitutiveService getMatiereConstitutiveService() {
		return matiereConstitutiveService;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveService] Setter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @param matiereConstitutiveService the matiereConstitutiveService to set
	 */
	public void setMatiereConstitutiveService(
			MatiereConstitutiveService matiereConstitutiveService) {
		this.matiereConstitutiveService = matiereConstitutiveService;
	}

	/**
	 * [AssiduiteBean.paramSearch] Getter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @return the paramSearch
	 */
	public String getParamSearch() {
		return paramSearch;
	}

	/**
	 * [AssiduiteBean.paramSearch] Setter 
	 * @author BELBRIK Oualid on : 17 juliet 2014  15:08:59
	 * @param paramSearch the paramSearch to set
	 */
	public void setParamSearch(String paramSearch) {
		this.paramSearch = paramSearch;
	}
	
	
	
	
	
	public List<SelectItem> getListMatiere() {
		FacesMessage msg = new FacesMessage();
		try{
		if(listMatiere==null || listMatiere.isEmpty()){
			listMatiere = new ArrayList<SelectItem>();
			List<MatiereConstitutiveDto> liste=matiereConstitutiveService.findAll();
			if(liste!=null && !liste.isEmpty()){
			for (MatiereConstitutiveDto matiereConstitutiveDto : liste) {
				SelectItem si= new SelectItem(
						matiereConstitutiveDto.getId(),
						matiereConstitutiveDto.getLibelleFr());
				listMatiere.add(si);
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
		return listMatiere;
	}


	public void setListMatiere(List<SelectItem> listMatiere) {
		this.listMatiere = listMatiere;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  12:17:32
	 * @return the matiereConstitutiveServiceImpl
	 */
	public MatiereConstitutiveService getMatiereConstitutiveServiceImpl() {
		return matiereConstitutiveServiceImpl;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  12:17:32
	 * @param matiereConstitutiveServiceImpl the matiereConstitutiveServiceImpl to set
	 */
	public void setMatiereConstitutiveServiceImpl(
			MatiereConstitutiveService matiereConstitutiveServiceImpl) {
		this.matiereConstitutiveServiceImpl = matiereConstitutiveServiceImpl;
	}
	
	public String getDossierInscriptionId() {
		return dossierInscriptionId;
	}

	public void setDossierInscriptionId(String dossierInscriptionId) {
		this.dossierInscriptionId = dossierInscriptionId;
	}
	


	

	/**
	 * [AssiduiteBean.ouvertureOffreFormationService] Getter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [AssiduiteBean.ouvertureOffreFormationService] Setter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @param ouvertureOffreFormationService the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [AssiduiteBean.offreFormationService] Getter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [AssiduiteBean.offreFormationService] Setter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @param offreFormationService the offreFormationService to set
	 */
	public void setOffreFormationService(OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [AssiduiteBean.anneeAcademiqueService] Getter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [AssiduiteBean.anneeAcademiqueService] Setter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:28:42
	 * @param anneeAcademiqueService the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [AssiduiteBean.updateMode] Getter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:30:20
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * [AssiduiteBean.updateMode] Setter 
	 * @author BELBRIK Oualid on : 22 juliet 2014  16:30:20
	 * @param updateMode the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	
	
	public void delete() {
		FacesMessage msg = new FacesMessage();
		try {
			exclusionService.delete(exclusionDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			String summary = (exclusionId==null || exclusionId.trim().equals("null")) ? "msg_success_suppression"
					: "msg_success_modification";
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);
	                

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
		
	}

	/**
	 * [AssiduiteBean.bundleCommon] Getter 
	 * @author BELBRIK Oualid on : 26 juliet 2014  17:27:54
	 * @return the bundleCommon
	 */
	public ResourceBundle getBundleCommon() {
		return bundleCommon;
	}

	/**
	 * [AssiduiteBean.bundleCommon] Setter 
	 * @author BELBRIK Oualid on : 26 juliet 2014  17:27:54
	 * @param bundleCommon the bundleCommon to set
	 */
	public void setBundleCommon(ResourceBundle bundleCommon) {
		this.bundleCommon = bundleCommon;
	}

	/**
	 * [AssiduiteBean.ExclusionServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 26 juliet 2014  17:27:54
	 * @return the ExclusionServiceImpl
	 */
	public ExclusionService getExclusionServiceImpl() {
		return exclusionServiceImpl;
	}

	/**
	 * [AssiduiteBean.ExclusionServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 26 juliet 2014  17:27:54
	 * @param ExclusionServiceImpl the ExclusionServiceImpl to set
	 */
	public void setExclusionServiceImpl(
			ExclusionService exclusionServiceImpl) {
		this.exclusionServiceImpl = exclusionServiceImpl;
	}
	
	public boolean isTypecause() {
		return typecause;
	}


	public void setTypecause(boolean typecause) {
		this.typecause = typecause;
	}

	/**
	 * [ExclusionBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:32:53
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [ExclusionBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:32:53
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [ExclusionBean.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:32:53
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [ExclusionBean.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:32:53
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	
}