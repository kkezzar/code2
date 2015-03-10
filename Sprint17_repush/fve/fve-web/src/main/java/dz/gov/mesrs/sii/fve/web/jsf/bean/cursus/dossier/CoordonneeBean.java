package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.fve.web.jsf.bean.SessionBean;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.CursusConstants;
import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.OfConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseElectroniqueService;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefCoordonneeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService;

/**
 * @author BELDI Jamel on : 27 mai 2014 17:46:36 Refactored by
 * @author Mounir.MESSAOUDI on : 11 novombre 2014 10:46:36 (Viewscoped et
 *         refecatoring)
 */
@ManagedBean(name = "coordonneeBean")
@ViewScoped
public class CoordonneeBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 17:46:20
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(CoordonneeBean.class);
	private RefAdresseDto refAdresseDto;
	private RefTelephoneDto refTelephoneDto;
	private RefAdresseElectroniqueDto refAdresseElectroniqueDto;
	private List<RefCoordonneeDto> listRefCoordonneeDto;
	private List<RefAdresseDto> listRefAdresseDto;
	private List<RefTelephoneDto> listRefTelephoneDto;
	private List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto;
	private String entity;
	// ManagedProperty("#{param.individuIdentifiant}")
	private String individuIdentifiant;
	// ManagedProperty("#{param.structureIdentifiant}")
	private String structureIdentifiant;
	// ManagedProperty("#{param.etabIdentifiant}")
	private String etabIdentifiant;
	// ManagedProperty("#{param.lieuIdentifiant}")
	private String lieuIdentifiant;

	// @ManagedProperty("#{param.idf}")
	// private String idf;
	// @ManagedProperty("#{param.id}")
	// private String id;
	private Integer idEntity;

	private Integer idIndividu;
	private Integer idStructure;
	private Integer idLieu;

	private ResourceBundle bundleCoordonnee;
	private ResourceBundle bundleCommon;
	private int exception;
	private List<SelectItem> listWilaya;
	private List<SelectItem> listDaira;
	private List<SelectItem> listCommune;

	@ManagedProperty("#{sessionBeanFve}")
	private SessionBean sessionBean;

	@ManagedProperty("#{refCoordonneeServiceImpl}")
	private RefCoordonneeService refCoordonneeService;

	@ManagedProperty("#{refAdresseServiceImpl}")
	private RefAdresseService refAdresseService;

	@ManagedProperty("#{refAdresseElectroniqueServiceImpl}")
	private RefAdresseElectroniqueService refAdresseElectroniqueService;

	@ManagedProperty("#{refTelephoneServiceImpl}")
	private RefTelephoneService refTelephoneService;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;


	// ManagedProperty("#{individuBean}")
	private IndividuBean individuBean;

	private List<SelectItem> listTypeAdresse;
	private List<SelectItem> listPays;
	private List<SelectItem> listNatTel;
	private List<SelectItem> listTypeTel;
	private List<SelectItem> listTypeMail;
	private List<SelectItem> listNatureAdresseElectronique;

	/**
	 * [CoordonneeBean.CoordonneeBean()] Constructor
	 * 
	 * @author BELDI Jamel on : 27 mai 2014 17:46:31
	 */
	public CoordonneeBean() {
		super();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		refAdresseDto = new RefAdresseDto();
		refTelephoneDto = new RefTelephoneDto();
		refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
		bundleCoordonnee = facesContext.getApplication().getResourceBundle(
				facesContext, CursusConstants.COORDONNEE_BUNDLE_MSG_NAME);
		bundleCommon = facesContext.getApplication().getResourceBundle(
				facesContext, OfConstants.COMMON_MESSAGES_FILE_NAME);
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		entity = (String) request.getAttribute("entity");

	}

	@PostConstruct
	public void init() {

		// individuIdentifiant = "10000";//TODO Test
		// loadIdEntity();
		//
		// loadCoordonnee();
		//
		// loadAdresses();
		//
		//
		// loadAdressesElectronique();
		//
		// loadTelephones();

		// setDefaultValues();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();

		idIndividu = (Integer) request.getAttribute("idIndividu");
		if (idIndividu != null)
			idEntity = idIndividu;

		idStructure = (Integer) request.getAttribute("idStructure");
		if (idStructure != null)
			idEntity = idStructure;

		idLieu = (Integer) request.getAttribute("idLieu");
		if (idLieu != null)
			idEntity = idLieu;

		if (idEntity != null && idEntity != 0) {
			listRefCoordonneeDto = refCoordonneeService
					.findByRefIndividuId(idEntity);
			listRefAdresseDto = refAdresseService
					.findByRefCoordonnee(listRefCoordonneeDto);

			listRefAdresseElectroniqueDto = refAdresseElectroniqueService
					.findByRefCoordonnee(listRefCoordonneeDto);
			listRefTelephoneDto = refTelephoneService
					.findByRefCoordonnee(listRefCoordonneeDto);

		}

	}

//	private void loadIdEntity() {
//		FacesMessage msg = new FacesMessage();
//		try {
//			if (entity != null
//					&& entity.equals(CursusConstants.INDIVIDU_ENTITY_NAME)) {
//				if (individuIdentifiant != null
//						&& !individuIdentifiant.equals("null")) {
//					// refIndividuDto = ppmWSClient
//					// .findIndividuByIdentifiant(individuIdentifiant);
//					// idEntity = refIndividuDto.getId();
//
//				}
//
//			} else if (entity != null
//					&& entity.equals(CursusConstants.STRUCTURE_ENTITY_NAME)) {
//				if (individuIdentifiant != null
//						&& !individuIdentifiant.equals("null")) {
//					// RefStructureDto refStructureDto =
//					// ppmWSClient.findStructureByIdentifiant((structureIdentifiant);
//					// idEntity = refStructureDto.getId();
//
//				}
//			} else if (entity != null
//					&& entity.equals(CursusConstants.ETABLISSEMENT_ENTITY_NAME)) {
//				// RefEtablissementDto refEtablissementDto =
//				// ppmWSClient.findEtablissementByIdentifiant(etabIdentifiant);
//				// idEntity = refEtablissementDto.getId();
//
//			} else if (entity != null
//					&& entity.equals(CursusConstants.ETABLISSEMENT_ENTITY_NAME)) {
//				// RefLieuDto refLieuDto =
//				// ppmWSClient.findLieuByIdentifiant(lieuIdentifiant);
//				// idEntity = refLieuDto.getId();
//			}
//
//		} catch (Exception e) {
//			exception = 1;
//			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			msg.setSummary(bundleCommon.getString("error_echec") + ": "
//					+ exception + ":"
//					+ bundleCommon.getString("error_echec_inconnue"));
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
//
//	}

	public void loadCoordonnee() {
		
		// try {
		// if (entity != null && idEntity != null) {
		// listRefCoordonneeDto = coordonneeWSClient
		// .findCoordonneesByIdEntity(entity, idEntity);
		// }
		//
		// } catch (Exception e) {
		//
		// exception = 2;
		// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		// msg.setSummary(bundleCommon.getString("error_echec") + ": "
		// + exception + ":"
		// + bundleCommon.getString("error_echec_inconnue"));
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }
	}

	public void loadAdresses() {
		// FacesMessage msg = new FacesMessage();
		// try {
		// if (listRefCoordonneeDto != null && !listRefCoordonneeDto.isEmpty())
		// {
		// listRefAdresseDto = coordonneeWSClient
		// .findAdressesByListCoordonnee(listRefCoordonneeDto);
		// }
		// } catch (Exception e) {
		//
		// exception = 3;
		// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		// msg.setSummary(bundleCommon.getString("error_echec") + ": "
		// + exception + ":"
		// + bundleCommon.getString("error_echec_inconnue"));
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }
	}

	public void loadTelephones() {
		// FacesMessage msg = new FacesMessage();
		// try {
		// if (listRefCoordonneeDto != null && !listRefCoordonneeDto.isEmpty())
		// {
		// listRefTelephoneDto = coordonneeWSClient
		// .findTelephonesByListCoordonnee(listRefCoordonneeDto);
		// }
		// } catch (Exception e) {
		//
		// exception = 4;
		// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		// msg.setSummary(bundleCommon.getString("error_echec") + ": "
		// + exception + ":"
		// + bundleCommon.getString("error_echec_inconnue"));
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }
	}

	public void loadAdressesElectronique() {
		// FacesMessage msg = new FacesMessage();
		// try {
		// if (listRefCoordonneeDto != null && !listRefCoordonneeDto.isEmpty())
		// {
		// listRefAdresseElectroniqueDto = coordonneeWSClient
		// .findAdresseElectroniquesByListCoordonnee(listRefCoordonneeDto);
		// }
		// } catch (Exception e) {
		//
		// exception = 5;
		// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		// msg.setSummary(bundleCommon.getString("error_echec") + ": "
		// + exception + ":"
		// + bundleCommon.getString("error_echec_inconnue"));
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }
	}

	public List<RefAdresseDto> getListRefAdresseDto() {
		return listRefAdresseDto;
	}

	public void setListRefAdresseDto(List<RefAdresseDto> listRefAdresseDto) {
		this.listRefAdresseDto = listRefAdresseDto;
	}

	public void deleteAdresse(RefAdresseDto refAdresseDto) {
		FacesMessage msg = new FacesMessage();
		if (refAdresseDto != null) {
			try {
				log.info("deleting adresse id = " + refAdresseDto.getId());

				// coordonneeWSClient.deleteAdresse(refAdresseDto);
				refAdresseService.delete(refAdresseDto);
				loadCoordonnee();

				loadAdresses();

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	public void addAdresse() {

		FacesMessage msg = new FacesMessage();
		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if ((refAdresseDto != null)
				&& (refAdresseDto.getTypeAdresseId() != null)
				&& (refAdresseDto.getPaysId() != null)) {
			try {

				log.info("saving adresse for entity = " + getEntity()
						+ " with id = " + idEntity);
				setAdresseRelationShip();

				// coordonneeWSClient.saveAdresse(refAdresseDto);
				refAdresseService.saveOrUpdate(refAdresseDto);
				loadCoordonnee();

				loadAdresses();

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		}

	}

	public void addTelephone() {

		FacesMessage msg = new FacesMessage();

		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refTelephoneDto != null)
				&& (refTelephoneDto.getNatureTelephoneId() != null)
				&& (refTelephoneDto.getTypeTelephoneId() != null)) {
			try {

				log.info("saving telephone for entity = " + getEntity()
						+ " with id = " + idEntity);
				setTelephoneRelationShip();
				// coordonneeWSClient.saveTelephone(refTelephoneDto);
				refTelephoneService.saveOrUpdate(refTelephoneDto);
				loadCoordonnee();

				loadTelephones();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	public RefCoordonneeService getRefCoordonneeService() {
		return refCoordonneeService;
	}

	public void setRefCoordonneeService(
			RefCoordonneeService refCoordonneeService) {
		this.refCoordonneeService = refCoordonneeService;
	}

	public void deleteTelephone(RefTelephoneDto refTelephoneDto) {
		FacesMessage msg = new FacesMessage();
		if (refTelephoneDto != null) {
			try {
				log.info("deleting Telephone id = " + refTelephoneDto.getId());
				// coordonneeWSClient.deleteTelephone(refTelephoneDto);
				refTelephoneService.delete(refTelephoneDto);
				loadCoordonnee();

				loadTelephones();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	public void addAdresseElectronique() {
		FacesMessage msg = new FacesMessage();
		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleCommon.getString("error_echec")
					+ ": "
					+ bundleCoordonnee
							.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if ((refAdresseElectroniqueDto != null)
				&& (refAdresseElectroniqueDto.getNatureAdresseElectroniqueId() != null)
				&& (refAdresseElectroniqueDto.getTypeAdresseElectroniqueId() != null)
				&& (refAdresseElectroniqueDto.getNomAdresse() != null)) {
			try {

				log.info("saving adresseElectronique for entity = "
						+ getEntity() + " with id = " + idEntity);
				setAdresseElectroniqueRelationShip();
				// coordonneeWSClient.saveAdresseElectronique(refAdresseElectroniqueDto);
				refAdresseElectroniqueService
						.saveOrUpdate(refAdresseElectroniqueDto);
				loadCoordonnee();

				loadAdressesElectronique();

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	public void deleteAdresseElectronique(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		FacesMessage msg = new FacesMessage();
		if (refAdresseElectroniqueDto != null) {
			try {
				log.info("deleting adresseElectronique id = "
						+ refAdresseElectroniqueDto.getId());
				// coordonneeWSClient.deleteAdresseElectronique(refAdresseElectroniqueDto);
				refAdresseElectroniqueService.delete(refAdresseElectroniqueDto);
				loadCoordonnee();
				loadAdressesElectronique();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleCommon.getString("msg_success") + ": "
						+ bundleCommon.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleCommon.getString("error_echec") + ": "
						+ bundleCommon.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	public void setAdresseRelationShip() {
		if (refAdresseDto != null) {
			refAdresseDto.setIdIndividu(null);
			refAdresseDto.setIdStructure(null);
			refAdresseDto.setIdLieu(null);
			refAdresseDto.setIdEtablissement(null);
			refAdresseDto
					.setTypeCoordonnee(CursusConstants.COORDONNEE_ADRESSE_TYPE);
			if (getEntity().equals(CursusConstants.INDIVIDU_ENTITY_NAME)) {
				refAdresseDto.setIdIndividu(idEntity);
			} else if (getEntity()
					.equals(CursusConstants.STRUCTURE_ENTITY_NAME)) {
				refAdresseDto.setIdStructure(idEntity);
			} else if (getEntity().equals(CursusConstants.LIEU_ENTITY_NAME)) {
				refAdresseDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					CursusConstants.ETABLISSEMENT_ENTITY_NAME)) {
				refAdresseDto.setIdEtablissement(idEntity);
			}
		}
	}

	public void setTelephoneRelationShip() {
		if (refTelephoneDto != null) {
			refTelephoneDto.setIdIndividu(null);
			refTelephoneDto.setIdStructure(null);
			refTelephoneDto.setIdLieu(null);
			refTelephoneDto.setIdEtablissement(null);
			refTelephoneDto
					.setTypeCoordonnee(CursusConstants.COORDONNEE_TELEPHONE_TYPE);
			if (getEntity().equals(CursusConstants.INDIVIDU_ENTITY_NAME)) {
				refTelephoneDto.setIdIndividu(idEntity);
			} else if (getEntity()
					.equals(CursusConstants.STRUCTURE_ENTITY_NAME)) {
				refTelephoneDto.setIdStructure(idEntity);
			} else if (getEntity().equals(CursusConstants.LIEU_ENTITY_NAME)) {
				refTelephoneDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					CursusConstants.ETABLISSEMENT_ENTITY_NAME)) {
				refTelephoneDto.setIdEtablissement(idEntity);
			}
		}
	}

	public void setAdresseElectroniqueRelationShip() {
		if (refAdresseElectroniqueDto != null) {
			refAdresseElectroniqueDto.setIdIndividu(null);
			refAdresseElectroniqueDto.setIdStructure(null);
			refAdresseElectroniqueDto.setIdLieu(null);
			refAdresseElectroniqueDto.setIdEtablissement(null);
			refAdresseElectroniqueDto
					.setTypeCoordonnee(CursusConstants.COORDONNEE_EMAIL_TYPE);
			if (getEntity().equals(CursusConstants.INDIVIDU_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdIndividu(idEntity);
			} else if (getEntity()
					.equals(CursusConstants.STRUCTURE_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdStructure(idEntity);
			} else if (getEntity().equals(CursusConstants.LIEU_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					CursusConstants.ETABLISSEMENT_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdEtablissement(idEntity);
			}
		}
	}

	public List<SelectItem> getListWilaya() {
		return loadWilaya();
	}

	public void setListWilaya(List<SelectItem> listWilaya) {
		this.listWilaya = listWilaya;
	}

	public List<SelectItem> getListCommune() {
		return loadCommune();
	}

	public void setListCommune(List<SelectItem> listCommune) {
		this.listCommune = listCommune;
	}

	public List<SelectItem> getListDaira() {
		return loadDaira();
	}

	public void setListDaira(List<SelectItem> listDaira) {
		this.listDaira = listDaira;
	}

	public List<SelectItem> loadDaira() {
		try {
			if (listDaira == null || listDaira.isEmpty()) {
				listDaira = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				if (refAdresseDto != null
						&& refAdresseDto.getWilayaId() != null) {

					list = nomenclatureService.findNcCompositeList(
							refAdresseDto.getWilayaId(),
							CursusConstants.NC_DAIRA_NAME);

				} else {
					list = nomenclatureService
							.findByName(CursusConstants.NC_DAIRA_NAME);

				}
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listDaira.add(selectItem);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return listDaira;
	}

	public List<SelectItem> loadWilaya() {
		try {
			if (listWilaya == null || listWilaya.isEmpty()) {
				listWilaya = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				if (refAdresseDto != null && refAdresseDto.getPaysId() != null) {
					list = nomenclatureService.findNcCompositeList(
							refAdresseDto.getPaysId(),
							CursusConstants.NC_WILAYA_NAME);
				} else {
					list = nomenclatureService
							.findByName(CursusConstants.NC_WILAYA_NAME);
				}
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listWilaya.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listWilaya;
	}

	public List<SelectItem> loadCommune() {
		try {
			if (listCommune == null || listCommune.isEmpty()) {
				listCommune = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				if (refAdresseDto != null && refAdresseDto.getDairaId() != null) {
					list = nomenclatureService.findNcCompositeList(
							refAdresseDto.getDairaId(),
							CursusConstants.NC_COMMUNE_NAME);
				} else {
					list = nomenclatureService
							.findByName(CursusConstants.NC_COMMUNE_NAME);

				}
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listCommune.add(selectItem);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCommune;
	}

	public void paysChanged() {

	}

	public void wilayaChanged() {

	}

	public void dairaChanged() {

	}

	// public void setDefaultValues() {
	// try {
	// refAdresseDto.setCommuneId(comboBckBean.getDefaultCommune());
	// refAdresseDto.setDairaId(comboBckBean.getDefaultDaira());
	// refAdresseDto.setWilayaId(comboBckBean.getDefaultWilaya());
	// refAdresseDto.setPaysId(comboBckBean.getDefaultPays());
	// refAdresseDto
	// .setTypeAdresseId(comboBckBean.getDefaultTypeAdresse());
	//
	// refTelephoneDto.setNatureTelephoneId(comboBckBean
	// .getDefaultNatTel());
	// refTelephoneDto
	// .setTypeTelephoneId(comboBckBean.getDefaultTypeTel());
	//
	// refAdresseElectroniqueDto
	// .setNatureAdresseElectroniqueId(comboBckBean
	// .getDefaultNatureAdresseElectronique());
	// refAdresseElectroniqueDto.setTypeAdresseElectroniqueId(comboBckBean
	// .getDefaultTypeMail());
	// } catch (Exception e) {
	// log.info("erreur " + e.getMessage());
	// }
	// }
	/**
	 * [CoordonneeBean.refAdresseDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the refAdresseDto
	 */
	public RefAdresseDto getRefAdresseDto() {
		return refAdresseDto;
	}

	/**
	 * [CoordonneeBean.refAdresseDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param refAdresseDto
	 *            the refAdresseDto to set
	 */
	public void setRefAdresseDto(RefAdresseDto refAdresseDto) {
		this.refAdresseDto = refAdresseDto;
	}

	/**
	 * [CoordonneeBean.refTelephoneDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the refTelephoneDto
	 */
	public RefTelephoneDto getRefTelephoneDto() {
		return refTelephoneDto;
	}

	/**
	 * [CoordonneeBean.refTelephoneDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param refTelephoneDto
	 *            the refTelephoneDto to set
	 */
	public void setRefTelephoneDto(RefTelephoneDto refTelephoneDto) {
		this.refTelephoneDto = refTelephoneDto;
	}

	/**
	 * [CoordonneeBean.refAdresseElectroniqueDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the refAdresseElectroniqueDto
	 */
	public RefAdresseElectroniqueDto getRefAdresseElectroniqueDto() {
		return refAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeBean.refAdresseElectroniqueDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param refAdresseElectroniqueDto
	 *            the refAdresseElectroniqueDto to set
	 */
	public void setRefAdresseElectroniqueDto(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		this.refAdresseElectroniqueDto = refAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeBean.listRefCoordonneeDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the listRefCoordonneeDto
	 */
	public List<RefCoordonneeDto> getListRefCoordonneeDto() {
		return listRefCoordonneeDto;
	}

	/**
	 * [CoordonneeBean.listRefCoordonneeDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param listRefCoordonneeDto
	 *            the listRefCoordonneeDto to set
	 */
	public void setListRefCoordonneeDto(
			List<RefCoordonneeDto> listRefCoordonneeDto) {
		this.listRefCoordonneeDto = listRefCoordonneeDto;
	}

	/**
	 * [CoordonneeBean.listRefTelephoneDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the listRefTelephoneDto
	 */
	public List<RefTelephoneDto> getListRefTelephoneDto() {
		return listRefTelephoneDto;
	}

	/**
	 * [CoordonneeBean.listRefTelephoneDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param listRefTelephoneDto
	 *            the listRefTelephoneDto to set
	 */
	public void setListRefTelephoneDto(List<RefTelephoneDto> listRefTelephoneDto) {
		this.listRefTelephoneDto = listRefTelephoneDto;
	}

	/**
	 * [CoordonneeBean.listRefAdresseElectroniqueDto] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the listRefAdresseElectroniqueDto
	 */
	public List<RefAdresseElectroniqueDto> getListRefAdresseElectroniqueDto() {
		return listRefAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeBean.listRefAdresseElectroniqueDto] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param listRefAdresseElectroniqueDto
	 *            the listRefAdresseElectroniqueDto to set
	 */
	public void setListRefAdresseElectroniqueDto(
			List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto) {
		this.listRefAdresseElectroniqueDto = listRefAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeBean.entity] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * [CoordonneeBean.entity] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	// /**
	// * [CoordonneeBean.idf] Getter
	// * @author BELDI Jamel on : 28 mai 2014 08:53:52
	// * @return the idf
	// */
	// public String getIdf() {
	// return idf;
	// }
	// /**
	// * [CoordonneeBean.idf] Setter
	// * @author BELDI Jamel on : 28 mai 2014 08:53:52
	// * @param idf the idf to set
	// */
	// public void setIdf(String idf) {
	// this.idf = idf;
	// }
	// /**
	// * [CoordonneeBean.id] Getter
	// * @author BELDI Jamel on : 28 mai 2014 08:53:52
	// * @return the id
	// */
	// public String getId() {
	// return id;
	// }
	// /**
	// * [CoordonneeBean.id] Setter
	// * @author BELDI Jamel on : 28 mai 2014 08:53:52
	// * @param id the id to set
	// */
	// public void setId(String id) {
	// this.id = id;
	// }
	/**
	 * [CoordonneeBean.idEntity] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @return the idEntity
	 */
	public Integer getIdEntity() {
		return idEntity;
	}

	/**
	 * [CoordonneeBean.idEntity] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 08:53:52
	 * @param idEntity
	 *            the idEntity to set
	 */
	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [CoordonneeBean.sessionBean] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 09:02:07
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * [CoordonneeBean.sessionBean] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 09:02:07
	 * @param sessionBean
	 *            the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * [CoordonneeBean.individuIdentifiant] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [CoordonneeBean.individuIdentifiant] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {

		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [CoordonneeBean.structureIdentifiant] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @return the structureIdentifiant
	 */
	public String getStructureIdentifiant() {
		return structureIdentifiant;
	}

	/**
	 * [CoordonneeBean.structureIdentifiant] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @param structureIdentifiant
	 *            the structureIdentifiant to set
	 */
	public void setStructureIdentifiant(String structureIdentifiant) {
		this.structureIdentifiant = structureIdentifiant;
	}

	/**
	 * [CoordonneeBean.etabIdentifiant] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @return the etabIdentifiant
	 */
	public String getEtabIdentifiant() {
		return etabIdentifiant;
	}

	/**
	 * [CoordonneeBean.etabIdentifiant] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @param etabIdentifiant
	 *            the etabIdentifiant to set
	 */
	public void setEtabIdentifiant(String etabIdentifiant) {
		this.etabIdentifiant = etabIdentifiant;
	}

	/**
	 * [CoordonneeBean.lieuIdentifiant] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @return the lieuIdentifiant
	 */
	public String getLieuIdentifiant() {
		return lieuIdentifiant;
	}

	/**
	 * [CoordonneeBean.lieuIdentifiant] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 14:54:38
	 * @param lieuIdentifiant
	 *            the lieuIdentifiant to set
	 */
	public void setLieuIdentifiant(String lieuIdentifiant) {
		this.lieuIdentifiant = lieuIdentifiant;
	}

	/**
	 * [CoordonneeBean.listTypeAdresse] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listTypeAdresse
	 */
	public List<SelectItem> getListTypeAdresse() {
		try {
			if (listTypeAdresse == null || listTypeAdresse.isEmpty()) {
				listTypeAdresse = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_TYPE_ADRESSE_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listTypeAdresse.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTypeAdresse;
	}

	/**
	 * [CoordonneeBean.listTypeAdresse] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listTypeAdresse
	 *            the listTypeAdresse to set
	 */
	public void setListTypeAdresse(List<SelectItem> listTypeAdresse) {
		this.listTypeAdresse = listTypeAdresse;
	}

	/**
	 * [CoordonneeBean.listPays] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listPays
	 */
	public List<SelectItem> getListPays() {
		try {
			if (listPays == null || listPays.isEmpty()) {
				listPays = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_PAYS_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listPays.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listPays;
	}

	/**
	 * [CoordonneeBean.listPays] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listPays
	 *            the listPays to set
	 */
	public void setListPays(List<SelectItem> listPays) {
		this.listPays = listPays;
	}

	/**
	 * [CoordonneeBean.listNatTel] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listNatTel
	 */
	public List<SelectItem> getListNatTel() {
		try {
			if (listNatTel == null || listNatTel.isEmpty()) {
				listNatTel = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_NAT_TEL_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listNatTel.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listNatTel;
	}

	/**
	 * [CoordonneeBean.listNatTel] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listNatTel
	 *            the listNatTel to set
	 */
	public void setListNatTel(List<SelectItem> listNatTel) {
		this.listNatTel = listNatTel;
	}

	/**
	 * [CoordonneeBean.listTypeTel] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listTypeTel
	 */
	public List<SelectItem> getListTypeTel() {
		try {
			if (listTypeTel == null || listTypeTel.isEmpty()) {
				listTypeTel = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_TYPE_TEL_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listTypeTel.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTypeTel;
	}

	/**
	 * [CoordonneeBean.listTypeTel] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listTypeTel
	 *            the listTypeTel to set
	 */
	public void setListTypeTel(List<SelectItem> listTypeTel) {
		this.listTypeTel = listTypeTel;
	}

	/**
	 * [CoordonneeBean.listTypeMail] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listTypeMail
	 */
	public List<SelectItem> getListTypeMail() {
		try {
			if (listTypeMail == null || listTypeMail.isEmpty()) {
				listTypeMail = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_TYPE_MAIL_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listTypeMail.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTypeMail;
	}

	/**
	 * [CoordonneeBean.listTypeMail] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listTypeMail
	 *            the listTypeMail to set
	 */
	public void setListTypeMail(List<SelectItem> listTypeMail) {
		this.listTypeMail = listTypeMail;
	}

	/**
	 * [CoordonneeBean.listNatureAdresseElectronique] Getter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @return the listNatureAdresseElectronique
	 */
	public List<SelectItem> getListNatureAdresseElectronique() {
		try {
			if (listNatureAdresseElectronique == null
					|| listNatureAdresseElectronique.isEmpty()) {
				listNatureAdresseElectronique = new ArrayList<SelectItem>();
				List<NomenclatureDto> list = null;
				list = nomenclatureService
						.findByName(CursusConstants.NC_NAT_EMAIL_NAME);
				for (NomenclatureDto nomenclatureDto : list) {
					SelectItem selectItem = new SelectItem(
							nomenclatureDto.getId(),
							nomenclatureDto.getLibelleLongFr());
					listNatureAdresseElectronique.add(selectItem);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listNatureAdresseElectronique;
	}

	/**
	 * [CoordonneeBean.listNatureAdresseElectronique] Setter
	 * 
	 * @author BELDI Jamel on : 28 mai 2014 18:13:15
	 * @param listNatureAdresseElectronique
	 *            the listNatureAdresseElectronique to set
	 */
	public void setListNatureAdresseElectronique(
			List<SelectItem> listNatureAdresseElectronique) {
		this.listNatureAdresseElectronique = listNatureAdresseElectronique;
	}

	/**
	 * [CoordonneeBean.individuBean] Getter
	 * 
	 * @author BELDI Jamel on : 3 juin 2014 17:45:39
	 * @return the individuBean
	 */
	public IndividuBean getIndividuBean() {
		return individuBean;
	}

	/**
	 * [CoordonneeBean.individuBean] Setter
	 * 
	 * @author BELDI Jamel on : 3 juin 2014 17:45:39
	 * @param individuBean
	 *            the individuBean to set
	 */
	public void setIndividuBean(IndividuBean individuBean) {
		if (individuBean != null && individuIdentifiant == null) {
			setIndividuIdentifiant(individuBean.getIndividuIdentifiant());
		}

		this.individuBean = individuBean;
	}

	public Integer getIdIndividu() {
		return idIndividu;
	}

	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	public RefTelephoneService getRefTelephoneService() {
		return refTelephoneService;
	}

	public void setRefTelephoneService(RefTelephoneService refTelephoneService) {
		this.refTelephoneService = refTelephoneService;
	}

	public Integer getIdStructure() {
		return idStructure;
	}

	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	public Integer getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}

	public RefAdresseService getRefAdresseService() {
		return refAdresseService;
	}

	public void setRefAdresseService(RefAdresseService refAdresseService) {
		this.refAdresseService = refAdresseService;
	}

	public RefAdresseElectroniqueService getRefAdresseElectroniqueService() {
		return refAdresseElectroniqueService;
	}

	public void setRefAdresseElectroniqueService(
			RefAdresseElectroniqueService refAdresseElectroniqueService) {
		this.refAdresseElectroniqueService = refAdresseElectroniqueService;
	}

	/**
	 * [CoordonneeBean.nomenclatureService] Getter 
	 * @author MAKERRI Sofiane on : 2 déc. 2014  09:24:44
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [CoordonneeBean.nomenclatureService] Setter 
	 * @author MAKERRI Sofiane on : 2 déc. 2014  09:24:44
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}
	
	

}
