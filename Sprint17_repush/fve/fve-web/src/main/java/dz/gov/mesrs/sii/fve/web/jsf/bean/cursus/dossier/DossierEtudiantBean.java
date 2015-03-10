package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.business.service.bpm.SituationService;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierDto;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.service.bac.BacService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierService;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELDI Jamel on : 22 mai 2014 08:55:12
 */
@ManagedBean(name = "dossierEtudiantBean")
@ViewScoped
public class DossierEtudiantBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 08:55:03
	 */
	private static final long serialVersionUID = 1L;
	private int exception;
	@ManagedProperty("#{flash}")
	private Flash flash;
	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;
	@ManagedProperty("#{dossierService}")
	private DossierService dossierService;
	@ManagedProperty("#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;
	@ManagedProperty("#{bacService}")
	private BacService bacService;
	private String codeTypeDossier;
	private DossierEtudiantDto dossierEtudiantDto;
	private String dossierEtudiantId;
	private String dossierBacId;
	private String individuIdentifiant;
	private Integer id_DossierEtudiant;
	private Integer id_DossierBachelier;
	private boolean entityChange;
	private ResourceBundle bundleCommon;
	private ResourceBundle etudiantBundle;
	private static final Log log = LogFactory.getLog(DossierEtudiantBean.class);
	private DossierDto dossierDto;
	@ManagedProperty("#{mapper}")
	private DozerBeanMapper mapper;

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuService;
	
	// ManagedProperty("#{individuBean}")
	private IndividuBean individuBean;
	private Integer handicapIndex;
	private Integer sportIndex;
	private Integer titreIndex;
	private Integer diplomeIndex;
	private Integer formationIndex;
	private Integer docuementIndex;
	private DossierBachelierDto dossierBachelierDto;
	@ManagedProperty(value = "#{situationService}")
	private SituationService situationService;
	private RefIndividuDto refIndividuDto;

	/**
	 * [DossierEtudiantBean.refIndividuService] Getter 
	 * @author rlaib on : 20 nov. 2014  15:10:40
	 * @return the refIndividuService
	 */
	public RefIndividuService getRefIndividuService() {
		return refIndividuService;
	}

	/**
	 * [DossierEtudiantBean.refIndividuService] Setter 
	 * @author rlaib on : 20 nov. 2014  15:10:40
	 * @param refIndividuService the refIndividuService to set
	 */
	public void setRefIndividuService(RefIndividuService refIndividuService) {
		this.refIndividuService = refIndividuService;
	}

	/**
	 * [DossierEtudiantBean.mapper] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:14:44
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * [DossierEtudiantBean.mapper] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:14:44
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * [OuvertureOfBean.OuvertureOfBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:58
	 */
	public DossierEtudiantBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COMMON_BUNDLE_MSG_NAME);
		etudiantBundle = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.DOSSIER_ETUDIANT_BUNDLE_MSG_NAME);

	}

	/**
	 * [OuvertureOfBean.init] Method
	 * 
	 * @author BELDI Jamel on : 11 mai 2014 18:37:54
	 */
	@PostConstruct
	public void init() {
		try {
			if (id_DossierBachelier != null) {
				dossierBachelierDto = bacService
						.findBachelorFileById(id_DossierBachelier);
			}
			if (id_DossierEtudiant != null) {
				dossierEtudiantDto = dossierEtudiantService
						.findById(id_DossierEtudiant);
				dossierDto = dossierService.findById(id_DossierEtudiant);
				if (dossierDto != null) {
					mapper.map(dossierDto, dossierEtudiantDto);
				}
			}
			if (dossierEtudiantDto == null) {
				dossierEtudiantDto = new DossierEtudiantDto();
				dossierEtudiantDto
						.setTypeDossier(CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT);
				dossierEtudiantDto.setDossierBachelierId(id_DossierBachelier);
				// dossierEtudiantDto.setNin(individuIdentifiant);

				dossierEtudiantDto.setDateCreation(new Date());
				if (dossierBachelierDto != null) {
					dossierEtudiantDto.setAnneeBac(dossierBachelierDto
							.getAnnee());
					dossierEtudiantDto
							.setDossierBachelierMatricule(dossierBachelierDto
									.getMatricule());
				}
			}

			dossierEtudiantDto.setNin(getIndividuIdentifiant());

			dossierEtudiantDto.setRefLlEtablissementLatin(sessionBean
					.getLlLatinEtablissement());
			dossierEtudiantDto.setRefCodeEtablissement(sessionBean
					.getCodeEtablissement());
			dossierEtudiantDto.setRefEtablissementId(sessionBean
					.getIdEtablissement());
			dossierEtudiantDto.setRefAncienCodeEtablissement(sessionBean
					.getAncienCodeEtablissement());
			if (individuIdentifiant != null) {
				refIndividuDto = refIndividuService
						.findByIdentifiant(individuIdentifiant);
			}
			if (refIndividuDto == null) {
				refIndividuDto = new RefIndividuDto();
			}
		} catch (Exception e) {

		}
	}

	public String toNewDossier() {
		if (exception == 0) {
			return "toNewDossierEtudiant";
		}
		{
			return null;
		}
	}

	public String toEditDossier() {
		if (exception == 0) {
			return "toEditDossierEtudiant";
		}
		{
			return null;
		}
	}

	public String toDetailDossier() {
		if (exception == 0) {
			return "toDetailDossierEtudiant";
		}
		{
			return null;
		}
	}

	/**
	 * [DossierEtudiantBean.exception] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @return the exception
	 */
	public int getException() {
		return exception;
	}

	/**
	 * [DossierEtudiantBean.exception] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @param exception
	 *            the exception to set
	 */
	public void setException(int exception) {
		this.exception = exception;
	}

	/**
	 * [DossierEtudiantBean.flash] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @return the flash
	 */
	public Flash getFlash() {
		return flash;
	}

	/**
	 * [DossierEtudiantBean.flash] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @param flash
	 *            the flash to set
	 */
	public void setFlash(Flash flash) {
		this.flash = flash;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [DossierEtudiantBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Getter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantService] Setter
	 * 
	 * @author BELDI Jamel on : 22 mai 2014 14:48:26
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(
			DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * [DossierEtudiantBean.codeTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 11:06:11
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		codeTypeDossier = CursusConstants.CODE_TYPE_DOSSIER_ETUDIANT;
		return codeTypeDossier;
	}

	/**
	 * [DossierEtudiantBean.codeTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 11:06:11
	 * @param codeTypeDossier
	 *            the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:21:34
	 * @return the dossierEtudiantDto
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:21:34
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:28:07
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [DossierEtudiantBean.dossierEtudiantId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 mai 2014 15:28:07
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		if (dossierEtudiantId != null && dossierEtudiantId.equals("null")) {
			this.dossierEtudiantId = null;
		} else {
			this.dossierEtudiantId = dossierEtudiantId;
			try {
				setId_DossierEtudiant(Integer.parseInt(dossierEtudiantId));
			} catch (Exception e) {

			}
		}
	}

	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:36:19
	 * @return the id_DossierEtudiant
	 */
	public Integer getId_DossierEtudiant() {
		return id_DossierEtudiant;
	}

	/**
	 * [DossierEtudiantBean.id_DossierEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 16:36:19
	 * @param id_DossierEtudiant
	 *            the id_DossierEtudiant to set
	 */
	public void setId_DossierEtudiant(Integer id_DossierEtudiant) {
		this.id_DossierEtudiant = id_DossierEtudiant;
	}

	/**
	 * [DossierEtudiantBean.entityChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:47:34
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
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:47:58
	 * @return the entityChange
	 */
	public boolean isEntityChange() {
		return entityChange;
	}

	/**
	 * [DossierEtudiantBean.entityChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:47:58
	 * @param entityChange
	 *            the entityChange to set
	 */
	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}

	/**
	 * [DossierEtudiantBean.save] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 15:51:40
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {
			if (dossierEtudiantDto.getNin() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec")
						+ ": "
						+ etudiantBundle
								.getString("dossier_etudiant_nin_required"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (!entityChange) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleCommon.getString("warn_info") + ": "
						+ bundleCommon.getString("warn_info_update_none"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			SituationEntiteDto sitEnregistre = situationService
					.findBySituationEntiteByCodeSituation(
							UtilConstants.ENTITE_DOSSIER_CODE,
							UtilConstants.SITUTAION_ENREGISTREE_CODE);
			if (sitEnregistre != null) {
				dossierEtudiantDto.setSituationId(sitEnregistre.getId());
				dossierEtudiantService.insertOrUpdate(dossierEtudiantDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				String summary = (id_DossierEtudiant == null) ? "msg_success_enregistrement"
						: "msg_success_modification";
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString(summary));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setDossierEtudiantId(dossierEtudiantDto.getId().toString());

				RequestContext.getCurrentInstance().execute(
						"tabviewWidget.enable(2);tabviewWidget.enable(3);"
								+ "tabviewWidget.enable(4);"
								+ "tabviewWidget.enable(5);"
								+ "tabviewWidget.enable(6);"
								+ "tabviewWidget.enable(7)");
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec")
						+ ": "
						+ etudiantBundle
								.getString("dossier_etudiant_erreur_situation"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec") + ": "
					+ bundleCommon.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}

	/**
	 * [DossierEtudiantBean.dossierBacId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:45:33
	 * @return the dossierBacId
	 */
	public String getDossierBacId() {
		return dossierBacId;
	}

	/**
	 * [DossierEtudiantBean.dossierBacId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:45:33
	 * @param dossierBacId
	 *            the dossierBacId to set
	 */
	public void setDossierBacId(String dossierBacId) {
		if (dossierBacId != null && dossierBacId.equals("null")) {
			this.dossierBacId = null;
		} else {
			this.dossierBacId = dossierBacId;
			try {
				setId_DossierBachelier(Integer.parseInt(dossierBacId));
			} catch (Exception e) {

			}
		}

	}

	/**
	 * [DossierEtudiantBean.id_DossierBachelier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:34:17
	 * @return the id_DossierBachelier
	 */
	public Integer getId_DossierBachelier() {
		return id_DossierBachelier;
	}

	/**
	 * [DossierEtudiantBean.id_DossierBachelier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:34:17
	 * @param id_DossierBachelier
	 *            the id_DossierBachelier to set
	 */
	public void setId_DossierBachelier(Integer id_DossierBachelier) {
		this.id_DossierBachelier = id_DossierBachelier;
	}

	/**
	 * [DossierEtudiantBean.individuIdentifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:42:56
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		if (individuIdentifiant == null) {
			this.individuIdentifiant = sessionBean.getIndividuIdentifiant();
		}
		return individuIdentifiant;
	}

	/**
	 * [DossierEtudiantBean.individuIdentifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 17:42:56
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		if (individuIdentifiant != null && individuIdentifiant.equals("null")) {
			this.individuIdentifiant = null;
		} else {
			this.individuIdentifiant = individuIdentifiant;
		}
	}

	/**
	 * [DossierEtudiantBean.dossierService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:11:31
	 * @return the dossierService
	 */
	public DossierService getDossierService() {
		return dossierService;
	}

	/**
	 * [DossierEtudiantBean.dossierService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:11:31
	 * @param dossierService
	 *            the dossierService to set
	 */
	public void setDossierService(DossierService dossierService) {
		this.dossierService = dossierService;
	}

	/**
	 * [DossierEtudiantBean.dossierDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:13:36
	 * @return the dossierDto
	 */
	public DossierDto getDossierDto() {
		return dossierDto;
	}

	/**
	 * [DossierEtudiantBean.dossierDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mai 2014 18:13:36
	 * @param dossierDto
	 *            the dossierDto to set
	 */
	public void setDossierDto(DossierDto dossierDto) {
		this.dossierDto = dossierDto;
	}

	/**
	 * [DossierEtudiantBean.individuBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 11:13:54
	 * @return the individuBean
	 */
	public IndividuBean getIndividuBean() {
		return individuBean;
	}

	/**
	 * [DossierEtudiantBean.individuBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 11:13:54
	 * @param individuBean
	 *            the individuBean to set
	 */
	public void setIndividuBean(IndividuBean individuBean) {
		// this.individuBean = individuBean;
		// if (individuBean != null) {
		// if (individuIdentifiant == null) {
		// setIndividuIdentifiant(individuBean.getIndividuIdentifiant());
		// }
		// if (dossierBacId == null) {
		// // TODO a mettre a jours MM
		// setDossierBacId(individuBean.getDossierBacId().toString());
		// }
		// }
	}

	/**
	 * [DossierEtudiantBean.handicapIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:13:06
	 * @return the handicapIndex
	 */
	public Integer getHandicapIndex() {
		handicapIndex = 5;
		return handicapIndex;
	}

	/**
	 * [DossierEtudiantBean.handicapIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:13:06
	 * @param handicapIndex
	 *            the handicapIndex to set
	 */
	public void setHandicapIndex(Integer handicapIndex) {
		this.handicapIndex = handicapIndex;
	}

	/**
	 * [DossierEtudiantBean.sportIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:44:39
	 * @return the sportIndex
	 */
	public Integer getSportIndex() {
		sportIndex = 6;
		return sportIndex;
	}

	/**
	 * [DossierEtudiantBean.sportIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:44:39
	 * @param sportIndex
	 *            the sportIndex to set
	 */
	public void setSportIndex(Integer sportIndex) {
		this.sportIndex = sportIndex;
	}

	/**
	 * [DossierEtudiantBean.titreIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:44:39
	 * @return the titreIndex
	 */
	public Integer getTitreIndex() {
		titreIndex = 2;
		return titreIndex;
	}

	/**
	 * [DossierEtudiantBean.titreIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 juin 2014 16:44:39
	 * @param titreIndex
	 *            the titreIndex to set
	 */
	public void setTitreIndex(Integer titreIndex) {
		this.titreIndex = titreIndex;
	}

	/**
	 * [DossierEtudiantBean.bacService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 16:19:18
	 * @return the bacService
	 */
	public BacService getBacService() {
		return bacService;
	}

	/**
	 * [DossierEtudiantBean.bacService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 16:19:18
	 * @param bacService
	 *            the bacService to set
	 */
	public void setBacService(BacService bacService) {
		this.bacService = bacService;
	}

	/**
	 * [DossierEtudiantBean.dossierBachelierDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 16:25:08
	 * @return the dossierBachelierDto
	 */
	public DossierBachelierDto getDossierBachelierDto() {
		return dossierBachelierDto;
	}

	/**
	 * [DossierEtudiantBean.dossierBachelierDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 juin 2014 16:25:08
	 * @param dossierBachelierDto
	 *            the dossierBachelierDto to set
	 */
	public void setDossierBachelierDto(DossierBachelierDto dossierBachelierDto) {
		this.dossierBachelierDto = dossierBachelierDto;
	}

	/**
	 * [DossierEtudiantBean.diplomeIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @return the diplomeIndex
	 */
	public Integer getDiplomeIndex() {
		diplomeIndex = 3;
		return diplomeIndex;
	}

	/**
	 * [DossierEtudiantBean.diplomeIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @param diplomeIndex
	 *            the diplomeIndex to set
	 */
	public void setDiplomeIndex(Integer diplomeIndex) {
		this.diplomeIndex = diplomeIndex;
	}

	/**
	 * [DossierEtudiantBean.formationIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @return the formationIndex
	 */
	public Integer getFormationIndex() {
		formationIndex = 4;
		return formationIndex;
	}

	/**
	 * [DossierEtudiantBean.formationIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @param formationIndex
	 *            the formationIndex to set
	 */
	public void setFormationIndex(Integer formationIndex) {
		this.formationIndex = formationIndex;
	}

	/**
	 * [DossierEtudiantBean.docuementIndex] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @return the docuementIndex
	 */
	public Integer getDocuementIndex() {
		docuementIndex = 7;
		return docuementIndex;
	}

	/**
	 * [DossierEtudiantBean.docuementIndex] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 14:18:28
	 * @param docuementIndex
	 *            the docuementIndex to set
	 */
	public void setDocuementIndex(Integer docuementIndex) {
		this.docuementIndex = docuementIndex;
	}

	/**
	 * [DossierEtudiantBean.situationService] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 15:42:50
	 * @return the situationService
	 */
	public SituationService getSituationService() {
		return situationService;
	}

	/**
	 * [DossierEtudiantBean.situationService] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 juil. 2014 15:42:50
	 * @param situationService
	 *            the situationService to set
	 */
	public void setSituationService(SituationService situationService) {
		this.situationService = situationService;
	}

	/**
	 * [DossierEtudiantBean.refIndividuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 13:35:40
	 * @return the refIndividuDto
	 */
	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	/**
	 * [DossierEtudiantBean.refIndividuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 juil. 2014 13:35:40
	 * @param refIndividuDto
	 *            the refIndividuDto to set
	 */
	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	public String cancel() {
		return "toSearchDossierEtudiant";
	}

}