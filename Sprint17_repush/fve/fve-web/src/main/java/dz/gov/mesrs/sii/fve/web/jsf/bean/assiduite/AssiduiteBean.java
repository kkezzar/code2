package dz.gov.mesrs.sii.fve.web.jsf.bean.assiduite;

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

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AbsenceMatiereDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AnneeAcademiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.AtomePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.MatiereConstitutiveDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.AbsenceMatiereService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierInscriptionAdministrativeService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AnneeAcademiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.AtomePedagogiqueService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.MatiereConstitutiveService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OffreFormationService;
import dz.gov.mesrs.sii.fve.business.service.offreformation.OuvertureOffreFormationService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.utils.ReferentielHelper;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELBRIK Oualid on : 9 juin 2014 08:55:12
 */
@ManagedBean(name = "assiduiteBean")
@RequestScoped
public class AssiduiteBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 08:55:03
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
	@ManagedProperty("#{param.absenceMatiereId}")
	public boolean updateMode;
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{absenceMatiereService}")
	private AbsenceMatiereService absenceMatiereService;
	private AbsenceMatiereDto absenceMatiereDto;
	@ManagedProperty("#{param.absenceMatiereId}")
	private String absenceMatiereId;
	@ManagedProperty("#{param.individuIdentifiant}")
	private String individuIdentifiant;
	private Integer id_AbsenceMatiere;
	private boolean entityChange;
	private static final Log log = LogFactory.getLog(AssiduiteBean.class);
	private ResourceBundle assiduiteBundle;
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
	private List<SelectItem> listeTypeAbsence;
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
	private List<SelectItem> listeAtome;
	@ManagedProperty(value = "#{atomePedagogiqueService}")
	private AtomePedagogiqueService atomePedagogiqueService;
	@ManagedProperty(value = "#{atomePedagogiqueServiceImpl}")
	private AtomePedagogiqueService atomePedagogiqueServiceImpl;
	@ManagedProperty(value = "#{absenceMatiereServiceImpl}")
	private AbsenceMatiereService absenceMatiereServiceImpl;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	/**
	 * [AssiduiteBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  10:40:53
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [AssiduiteBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  10:40:53
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierEtudiantBean.mapper] Getter
	 * 
	 * @author BELBRIK Oualid on : 27 mai 2014 18:14:44
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DossierEtudiantBean.mapper] Setter
	 * 
	 * @author BELBRIK Oualid on : 27 mai 2014 18:14:44
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 11 mai 2014 18:37:58
	 */
	public AssiduiteBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		assiduiteBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.ASSIDUITE_BUNDLE_MSG_NAME);
	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELBRIK Oualid on : 11 mai 2014 18:37:54
	 */

	@PostConstruct
	public void init() {
		loadAbsenceMatiereDto();
	}

	private void loadAbsenceMatiereDto() {
		FacesMessage msg = new FacesMessage();
		absenceMatiereDto = new AbsenceMatiereDto();
		try {
			if (absenceMatiereId != null
					&& !absenceMatiereId.trim().equals("null")) {
				absenceMatiereDto = absenceMatiereService.findById(Integer
						.valueOf(absenceMatiereId));
				setDossierInscriptionId(absenceMatiereDto
						.getDossierInscriptionId().toString());
				loadByDossierInscriptionId();
			} else {
				loadByDossierInscriptionId();
			}

		} catch (Exception e) {
			exception = 1;
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ exception + ":"
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	private void loadByDossierInscriptionId() throws Exception {
		if (dossierInscriptionId != null
				&& !dossierInscriptionId.trim().equals("null")) {

			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeService
					.findById(Integer.valueOf(dossierInscriptionId));
			if (absenceMatiereId == null
					|| absenceMatiereId.trim().equals("null")) {
				AbsenceMatiereDto absenceMatiereSearchDto = new AbsenceMatiereDto();
				absenceMatiereSearchDto
						.setAnneeAcademiqueId(dossierInscriptionAdministrativeDto
								.getAnneeAcademiqueId());
				absenceMatiereSearchDto.setDossierInscriptionId(Integer
						.valueOf(dossierInscriptionId));
				List listAbsenceMatiere = absenceMatiereService
						.findAdvanced(absenceMatiereSearchDto);
				if (listAbsenceMatiere != null && !listAbsenceMatiere.isEmpty()) {
					absenceMatiereDto = (AbsenceMatiereDto) listAbsenceMatiere
							.get(0);
					// setAbsenceMatiereId(absenceMatiereDto.getId().toString());
				} else {
					absenceMatiereDto.setDateAbsence(new Date());
				}
			}
			mapper.map(dossierInscriptionAdministrativeDto, absenceMatiereDto);
			AnneeAcademiqueDto anneeAcademiqueDto = anneeAcademiqueService
					.findById(dossierInscriptionAdministrativeDto
							.getAnneeAcademiqueId());
			mapper.map(anneeAcademiqueDto, absenceMatiereDto);
			DossierEtudiantDto dossierEtudiantDto = dossierEtudiantService
					.findById(dossierInscriptionAdministrativeDto
							.getDossierEtudiantId());
			mapper.map(dossierEtudiantDto, absenceMatiereDto);
			RefIndividuDto individuDto = refIndividuService
					.findByIdentifiant(dossierEtudiantDto.getNin());

			mapper.map(individuDto, absenceMatiereDto);

		}
	}

	/**
	 * [DossierEtudiantBean.exception] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierEtudiantBean.exception] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DossierEtudiantBean.flash] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierEtudiantBean.flash] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @return the dossierEtudiantService
	 */
	public AbsenceMatiereService getAbsenceMatiereService() {
		return absenceMatiereService;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 14:48:26
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setAbsenceMatiereService(
			AbsenceMatiereService absenceMatiereService) {
		this.absenceMatiereService = absenceMatiereService;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:21:34
	 * @return the dossierEtudiantDto
	 */
	public AbsenceMatiereDto getAbsenceMatiereDto() {
		return absenceMatiereDto;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:21:34
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setAbsenceMatiereDto(AbsenceMatiereDto absenceMatiereDto) {
		this.absenceMatiereDto = absenceMatiereDto;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:28:07
	 * @return the dossierEtudiantId
	 */
	public String getAbsenceMatiereId() {
		return absenceMatiereId;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Setter
	 * 
	 * @author BELBRIK Oualid on : 28 mai 2014 15:28:07
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setAbsenceMatiereId(String absenceMatiereId) {
		if (absenceMatiereId != null && absenceMatiereId.equals("null")) {
			this.absenceMatiereId = null;
		} else {
			this.absenceMatiereId = absenceMatiereId;
			try {
				setId_AbsenceMatiere(Integer.parseInt(absenceMatiereId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 16:36:19
	 * @return the id_DossierEtudiant
	 */
	public Integer getId_AbsenceMatiere() {
		return id_AbsenceMatiere;
	}

	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 16:36:19
	 * @param id_DossierEtudiant
	 *            the id_DossierEtudiant to set
	 */
	public void setId_AbsenceMatiere(Integer id_AbsenceMatiere) {
		this.id_AbsenceMatiere = id_AbsenceMatiere;
	}

	/**
	 * [DossierEtudiantBean.entityChanged] Method
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:47:34
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
	 * @author BELBRIK Oualid on : 9 juin 2014 15:47:58
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [DossierEtudiantBean.entityChange] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:47:58
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [DossierEtudiantBean.save] Method
	 * 
	 * @author BELBRIK Oualid on : 9 juin 2014 15:51:40
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();

	}

	/**
	 * [AssiduiteBean.individuIdentifiant] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [AssiduiteBean.individuIdentifiant] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [AssiduiteBean.dossierDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierDto
	 */
	public DossierDto getDossierDto() {
		return dossierDto;
	}

	/**
	 * [AssiduiteBean.dossierDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierDto
	 *            the dossierDto to set
	 */
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantId] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantId] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [AssiduiteBean.referentielHelper] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the referentielHelper
	 */
	public ReferentielHelper getReferentielHelper() {
		return referentielHelper;
	}

	/**
	 * [AssiduiteBean.referentielHelper] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param referentielHelper
	 *            the referentielHelper to set
	 */
	public void setReferentielHelper(ReferentielHelper referentielHelper) {
		this.referentielHelper = referentielHelper;
	}

	/**
	 * [AssiduiteBean.dossierService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [AssiduiteBean.dossierService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierEtudiantBean
	 */
	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierEtudiantBean
	 *            the dossierEtudiantBean to set
	 */
	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeService] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierInscriptionAdministrativeService
	 */
	public DossierInscriptionAdministrativeService getDossierInscriptionAdministrativeService() {
		return dossierInscriptionAdministrativeService;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeService] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierInscriptionAdministrativeService
	 *            the dossierInscriptionAdministrativeService to set
	 */
	public void setDossierInscriptionAdministrativeService(
			DossierInscriptionAdministrativeService dossierInscriptionAdministrativeService) {
		this.dossierInscriptionAdministrativeService = dossierInscriptionAdministrativeService;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierInscriptionAdministrativeDto
	 */
	public DossierInscriptionAdministrativeDto getDossierInscriptionAdministrativeDto() {
		return dossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.dossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierInscriptionAdministrativeDto
	 *            the dossierInscriptionAdministrativeDto to set
	 */
	public void setDossierInscriptionAdministrativeDto(
			DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto) {
		this.dossierInscriptionAdministrativeDto = dossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.listDdossierInscriptionAdministrativeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the listDdossierInscriptionAdministrativeDto
	 */
	public List<DossierInscriptionAdministrativeDto> getListDdossierInscriptionAdministrativeDto() {
		return listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.listDdossierInscriptionAdministrativeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param listDdossierInscriptionAdministrativeDto
	 *            the listDdossierInscriptionAdministrativeDto to set
	 */
	public void setListDdossierInscriptionAdministrativeDto(
			List<DossierInscriptionAdministrativeDto> listDdossierInscriptionAdministrativeDto) {
		this.listDdossierInscriptionAdministrativeDto = listDdossierInscriptionAdministrativeDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @return the dossierEtudiantDto
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	/**
	 * [AssiduiteBean.dossierEtudiantDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 13:43:36
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	/**
	 * [AssiduiteBean.listeAtome] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 16:38:27
	 * @return the listeAtome
	 */
	public List<SelectItem> getListeAtome() {

		FacesMessage msg = new FacesMessage();
		try {
			if (listeAtome == null || listeAtome.isEmpty()) {
				listeAtome = new ArrayList<SelectItem>();
				List<AtomePedagogiqueDto> liste = atomePedagogiqueService
						.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (AtomePedagogiqueDto atomePedagogiqueDto : liste) {
						SelectItem si = new SelectItem(
								atomePedagogiqueDto.getId(),
								atomePedagogiqueDto.getCode());
						listeAtome.add(si);
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
		return listeAtome;
	}

	/**
	 * [AssiduiteBean.listeAtome] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 juin 2014 16:38:27
	 * @param listeAtome
	 *            the listeAtome to set
	 */
	public void setListeAtome(List<SelectItem> listeAtome) {
		this.listeAtome = listeAtome;
	}

	public List<SelectItem> getListeTypeAbsence() {
		if (listeTypeAbsence == null || listeTypeAbsence.isEmpty()) {
			listeTypeAbsence = new ArrayList<SelectItem>();
			listeTypeAbsence = referentielHelper
					.getNomenclatureList(CursusConstants.NC_TYPE_ABSENCE);
		}
		return listeTypeAbsence;
	}

	public void setListeTypeAbsence(List<SelectItem> listeTypeAbsence) {
		this.listeTypeAbsence = listeTypeAbsence;
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

	public void saveAbsence() {
		FacesMessage msg = new FacesMessage();
		try {
			absenceMatiereService.insertOrUpdate(absenceMatiereDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			String summary = (absenceMatiereId == null || absenceMatiereId
					.trim().equals("null")) ? "msg_success_enregistrement"
					: "msg_success_modification";
			msg.setSummary(bundleCommon.getString("msg_success") + ": "
					+ bundleCommon.getString(summary));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// setAbsenceMatiereId(absenceMatiereDto.getId().toString());

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	public void saveAssiduite() {

		FacesMessage msg = new FacesMessage();

		try {

			if (dossierEtudiantId == null || dossierEtudiantId.equals("null")
					|| dossierEtudiantId.isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(assiduiteBundle
						.getString("assiduite_error_persistence_titre"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			if (absenceMatiereDto.getId() != 0) {

				log.info(" ---------- update absence ----");

				// update absence matiere
				absenceMatiereDto = absenceMatiereService
						.insertOrUpdate(absenceMatiereDto);

				// message succes update absenceMatiere
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(assiduiteBundle
						.getString("absence_maitiere_success_updated"));

				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

			else {
				log.info(" ---------- save absence  ------ ");

				absenceMatiereDto.setDossierEtudiantId(Integer
						.parseInt(dossierEtudiantId));

				// save absence
				absenceMatiereDto = absenceMatiereService
						.insertOrUpdate(absenceMatiereDto);

				// message succes enregistrement nouvel absence
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(assiduiteBundle
						.getString("absence_maitiere_success_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(assiduiteBundle
					.getString("absence_maitiere_error_persistence_titre"));
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

	public void setMatiereConstitutiveList(
			List<MatiereConstitutiveDto> matiereConstitutiveList) {
		this.matiereConstitutiveList = matiereConstitutiveList;
	}

	public void search() {

		if (paramSearch != null && paramSearch.trim() != "") {
			matiereConstitutiveList = new ArrayList<MatiereConstitutiveDto>();
			matiereConstitutiveList.add(matiereConstitutiveService
					.findById(Integer.parseInt(paramSearch.trim())));
		} else
			matiereConstitutiveList = findAll();

	}

	/**
	 * [AssiduiteBean.matiereConstitutiveService] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 juin 2014 15:08:59
	 * @return the matiereConstitutiveService
	 */
	public MatiereConstitutiveService getMatiereConstitutiveService() {
		return matiereConstitutiveService;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveService] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 juin 2014 15:08:59
	 * @param matiereConstitutiveService
	 *            the matiereConstitutiveService to set
	 */
	public void setMatiereConstitutiveService(
			MatiereConstitutiveService matiereConstitutiveService) {
		this.matiereConstitutiveService = matiereConstitutiveService;
	}

	/**
	 * [AssiduiteBean.paramSearch] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 juin 2014 15:08:59
	 * @return the paramSearch
	 */
	public String getParamSearch() {
		return paramSearch;
	}

	/**
	 * [AssiduiteBean.paramSearch] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 juin 2014 15:08:59
	 * @param paramSearch
	 *            the paramSearch to set
	 */
	public void setParamSearch(String paramSearch) {
		this.paramSearch = paramSearch;
	}

	public List<SelectItem> getListMatiere() {
		FacesMessage msg = new FacesMessage();
		try {
			if (listMatiere == null || listMatiere.isEmpty()) {
				listMatiere = new ArrayList<SelectItem>();
				List<MatiereConstitutiveDto> liste = matiereConstitutiveService
						.findAll();
				if (liste != null && !liste.isEmpty()) {
					for (MatiereConstitutiveDto matiereConstitutiveDto : liste) {
						SelectItem si = new SelectItem(
								matiereConstitutiveDto.getId(),
								matiereConstitutiveDto.getLibelleFr());
						listMatiere.add(si);
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
		return listMatiere;
	}

	public void setListMatiere(List<SelectItem> listMatiere) {
		this.listMatiere = listMatiere;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 12:17:32
	 * @return the matiereConstitutiveServiceImpl
	 */
	public MatiereConstitutiveService getMatiereConstitutiveServiceImpl() {
		return matiereConstitutiveServiceImpl;
	}

	/**
	 * [AssiduiteBean.matiereConstitutiveServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 12:17:32
	 * @param matiereConstitutiveServiceImpl
	 *            the matiereConstitutiveServiceImpl to set
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
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @return the ouvertureOffreFormationService
	 */
	public OuvertureOffreFormationService getOuvertureOffreFormationService() {
		return ouvertureOffreFormationService;
	}

	/**
	 * [AssiduiteBean.ouvertureOffreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @param ouvertureOffreFormationService
	 *            the ouvertureOffreFormationService to set
	 */
	public void setOuvertureOffreFormationService(
			OuvertureOffreFormationService ouvertureOffreFormationService) {
		this.ouvertureOffreFormationService = ouvertureOffreFormationService;
	}

	/**
	 * [AssiduiteBean.offreFormationService] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @return the offreFormationService
	 */
	public OffreFormationService getOffreFormationService() {
		return offreFormationService;
	}

	/**
	 * [AssiduiteBean.offreFormationService] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @param offreFormationService
	 *            the offreFormationService to set
	 */
	public void setOffreFormationService(
			OffreFormationService offreFormationService) {
		this.offreFormationService = offreFormationService;
	}

	/**
	 * [AssiduiteBean.anneeAcademiqueService] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @return the anneeAcademiqueService
	 */
	public AnneeAcademiqueService getAnneeAcademiqueService() {
		return anneeAcademiqueService;
	}

	/**
	 * [AssiduiteBean.anneeAcademiqueService] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:28:42
	 * @param anneeAcademiqueService
	 *            the anneeAcademiqueService to set
	 */
	public void setAnneeAcademiqueService(
			AnneeAcademiqueService anneeAcademiqueService) {
		this.anneeAcademiqueService = anneeAcademiqueService;
	}

	/**
	 * [AssiduiteBean.updateMode] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:30:20
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * [AssiduiteBean.updateMode] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 juin 2014 16:30:20
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * [AssiduiteBean.atomePedagogiqueService] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 juin 2014 20:06:38
	 * @return the atomePedagogiqueService
	 */
	public AtomePedagogiqueService getAtomePedagogiqueService() {
		return atomePedagogiqueService;
	}

	/**
	 * [AssiduiteBean.atomePedagogiqueService] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 juin 2014 20:06:38
	 * @param atomePedagogiqueService
	 *            the atomePedagogiqueService to set
	 */
	public void setAtomePedagogiqueService(
			AtomePedagogiqueService atomePedagogiqueService) {
		this.atomePedagogiqueService = atomePedagogiqueService;
	}

	/**
	 * [AssiduiteBean.atomePedagogiqueServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 juin 2014 20:06:38
	 * @return the atomePedagogiqueServiceImpl
	 */
	public AtomePedagogiqueService getAtomePedagogiqueServiceImpl() {
		return atomePedagogiqueServiceImpl;
	}

	/**
	 * [AssiduiteBean.atomePedagogiqueServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 juin 2014 20:06:38
	 * @param atomePedagogiqueServiceImpl
	 *            the atomePedagogiqueServiceImpl to set
	 */
	public void setAtomePedagogiqueServiceImpl(
			AtomePedagogiqueService atomePedagogiqueServiceImpl) {
		this.atomePedagogiqueServiceImpl = atomePedagogiqueServiceImpl;
	}

	public void delete() {
		FacesMessage msg = new FacesMessage();
		try {
			absenceMatiereService.delete(absenceMatiereDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			String summary = (absenceMatiereId == null || absenceMatiereId
					.trim().equals("null")) ? "msg_success_suppression"
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
	 * 
	 * @author BELBRIK Oualid on : 26 juin 2014 17:27:54
	 * @return the bundleCommon
	 */
	public ResourceBundle getBundleCommon() {
		return bundleCommon;
	}

	/**
	 * [AssiduiteBean.bundleCommon] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 juin 2014 17:27:54
	 * @param bundleCommon
	 *            the bundleCommon to set
	 */
	public void setBundleCommon(ResourceBundle bundleCommon) {
		this.bundleCommon = bundleCommon;
	}

	/**
	 * [AssiduiteBean.absenceMatiereServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 juin 2014 17:27:54
	 * @return the absenceMatiereServiceImpl
	 */
	public AbsenceMatiereService getAbsenceMatiereServiceImpl() {
		return absenceMatiereServiceImpl;
	}

	/**
	 * [AssiduiteBean.absenceMatiereServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 juin 2014 17:27:54
	 * @param absenceMatiereServiceImpl
	 *            the absenceMatiereServiceImpl to set
	 */
	public void setAbsenceMatiereServiceImpl(
			AbsenceMatiereService absenceMatiereServiceImpl) {
		this.absenceMatiereServiceImpl = absenceMatiereServiceImpl;
	}

}