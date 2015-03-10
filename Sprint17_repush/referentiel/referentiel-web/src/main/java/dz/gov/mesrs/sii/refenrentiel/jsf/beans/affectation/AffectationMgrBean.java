/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationMgrBean.java] 
 * @author jbeldi on : 15 janv. 2014  14:15:26
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author jbeldi on : 15 janv. 2014 14:15:26
 */
/**
 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:31:50
 */
/**
 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:31:52
 */
/**
 * @author MAKERRI Sofiane on : 7 janv. 2015 13:31:55
 */
@ManagedBean(name = "affectationMgrBean")
@ViewScoped
public class AffectationMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:15:32
	 */
	private static final Log log = LogFactory.getLog(AffectationMgrBean.class);
	private static final long serialVersionUID = 1L;
	private RefAffectationDto refAffectationDto;
	private List<RefAffectationDto> listRefAffectationStructureDto;
	private List<RefAffectationDto> listRefAffectationGroupeDto;
	private List<RefAffectationDto> listRefAffectationIndividuDto;
	private List<RefAffectationDto> listRefAffectationDomaineLMDDto;
	private List<RefAffectationDto> listRefAffectationFiliereLMDDto;

	private String entity;
	private Integer idEntity;
	// private String idf;
	// private Integer id;
	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	@ManagedProperty(value = "#{refAffectationServiceImpl}")
	private RefAffectationService refAffectationServiceImpl;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	@ManagedProperty(value = "#{affecGroupeCrudBean}")
	private AffecGroupeCrudBean affecGroupeCrudBean;
	@ManagedProperty(value = "#{affecStructureCrudBean}")
	private AffecStructureCrudBean affecStructureCrudBean;
	@ManagedProperty(value = "#{affecIndividuCrudBean}")
	private AffecIndividuCrudBean affecIndividuCrudBean;
	@ManagedProperty(value = "#{affecDomaineLMDCrudBean}")
	private AffecDomaineLMDCrudBean affecDomaineLMDCrudBean;
	@ManagedProperty(value = "#{affecFiliereLMDCrudBean}")
	private AffecFiliereLMDCrudBean affecFiliereLMDCrudBean;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	private Boolean readMode;

	/**
	 * [AffectationMgrBean.AffectationMgrBean()] Constructor
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:15:26
	 */
	public AffectationMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context,
				AffectationMgrBean.MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// HttpServletRequest request = (HttpServletRequest) context
		// .getExternalContext().getRequest();
		//
		// try {
		// String entityname = (String) request.getAttribute("entity");
		//
		// log.info("var request*******************************************entityname: "
		// + entityname);
		// if (entityname != null) {
		// this.entity = entityname;
		// }
		//
		// if (id == null) {
		// id = (Integer) request.getAttribute("id");
		// }
		//
		// log.info("var request*******************************************id: "
		// + id);
		// if (id == null) {
		// String idParam = request.getParameter("id");
		// int _id = RefUtilConstant.strToInt(idParam);
		// if (_id != -1) {
		// id = new Integer(_id);
		// }
		// }
		// load();
		// } catch (Exception e) {
		//
		// }
		readMode = (Boolean) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("readMode");
		idEntity = (Integer) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("idEntity");
		entity = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("entity");
		/******
		 * MAKERRI SOFIANE A OPTIMISER : charger les affectation selon les droit
		 * et permission
		 ******/
		loadListRefAffectationGroupeDto();
		loadListRefAffectationIndividuDto();
		loadListRefAffectationStructureDto();
		if (entity != null && entity.equals("structure")) {
			loadListRefAffectationDomaineLMDDto();
			loadListRefAffectationFiliereLMDDto();
		}

	}

	/**
	 * [AffectationMgrBean.loadListRefAffectationStructureDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:05:57
	 */
	public void loadListRefAffectationStructureDto() {
		log.info("entity:" + entity);
		log.info("idEntity:" + idEntity);
		FacesMessage msg = new FacesMessage();
		try {
			if (idEntity != null) {
				listRefAffectationStructureDto = refAffectationServiceImpl
						.findStructures(entity, idEntity);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.refAffectationDto] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:52:39
	 * @return the refAffectationDto
	 */
	public RefAffectationDto getRefAffectationDto() {
		if (refAffectationDto == null) {
			refAffectationDto = new RefAffectationDto();
			setDefaultValues();

		}
		return refAffectationDto;
	}

	/**
	 * [AffectationMgrBean.refAffectationDto] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:52:39
	 * @param refAffectationDto
	 *            the refAffectationDto to set
	 */
	public void setRefAffectationDto(RefAffectationDto refAffectationDto) {
		this.refAffectationDto = refAffectationDto;
	}

	/**
	 * [AffectationMgrBean.listRefAffectationStructureDto] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:40:18
	 * @return the listRefAffectationStructureDto
	 */
	public List<RefAffectationDto> getListRefAffectationStructureDto() {
		/*
		 * log.info("entity:" + entity); log.info("identifiant:" + identifiant);
		 * FacesMessage msg = new FacesMessage(); try { if(identifiant!=null &&
		 * !identifiant.isEmpty()){ listRefAffectationStructureDto =
		 * refAffectationServiceImpl .findStructures(entity, identifiant); } }
		 * catch (Exception e) { log.info(e.getMessage());
		 * msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		 * msg.setSummary(bundle.getString("error_echec") + ": " +
		 * bundle.getString("error_echec_inconnue"));
		 * FacesContext.getCurrentInstance().addMessage(null, msg); }
		 */
		return listRefAffectationStructureDto;
	}

	/**
	 * [AffectationMgrBean.listRefAffectationStructureDto] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:40:18
	 * @param listRefAffectationDto
	 *            the listRefAffectationStructureDto to set
	 */
	public void setListRefAffectationStructureDto(
			List<RefAffectationDto> listRefAffectationStructureDto) {
		this.listRefAffectationStructureDto = listRefAffectationStructureDto;
	}

	/**
	 * [AffectationMgrBean.listRefAffectationGroupeDto] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:40:18
	 * @return the listRefAffectationGroupeDto
	 */
	public List<RefAffectationDto> getListRefAffectationGroupeDto() {
		return listRefAffectationGroupeDto;
	}

	/**
	 * [AffectationMgrBean.loadListRefAffectationGroupeDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:04:54
	 */
	private void loadListRefAffectationGroupeDto() {
		log.info("entity:" + entity);
		log.info("id:" + idEntity);
		FacesMessage msg = new FacesMessage();
		try {
			listRefAffectationGroupeDto = refAffectationServiceImpl
					.findGroupes(entity, idEntity);
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.listRefAffectationGroupeDto] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:40:18
	 * @param listRefAffectationDto
	 *            the listRefAffectationGroupeDto to set
	 */
	public void setListRefAffectationGroupeDto(
			List<RefAffectationDto> listRefAffectationGroupeDto) {
		this.listRefAffectationGroupeDto = listRefAffectationGroupeDto;
	}

	/**
	 * [AffectationMgrBean.entity] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:47:40
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * [AffectationMgrBean.entity] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:47:40
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		String entityname = (String) request.getAttribute("entity");
		log.info("var request*******************************************entityname: "
				+ entityname);
		if (entityname != null) {
			this.entity = entityname;
		} else {

			this.entity = entity;
		}
	}

	/**
	 * [AffectationMgrBean.identifiant] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:47:40
	 * @return the identifiant
	 */
	/*
	 * public String getIdentifiant() { return identifiant; }
	 */

	/**
	 * [AffectationMgrBean.refAffectationServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 15:36:30
	 * @return the refAffectationServiceImpl
	 */
	public RefAffectationService getRefAffectationServiceImpl() {
		return refAffectationServiceImpl;
	}

	/**
	 * [AffectationMgrBean.refAffectationServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 15:36:30
	 * @param refAffectationServiceImpl
	 *            the refAffectationServiceImpl to set
	 */
	public void setRefAffectationServiceImpl(
			RefAffectationService refAffectationServiceImpl) {
		this.refAffectationServiceImpl = refAffectationServiceImpl;
	}

	/**
	 * [AffectationMgrBean.identifiant] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 15:47:40
	 * @param identifiant
	 *            the identifiant to set
	 */
	/*
	 * public void setIdentifiant(String identifiant) { FacesContext context =
	 * FacesContext.getCurrentInstance(); HttpServletRequest request =
	 * (HttpServletRequest) context .getExternalContext().getRequest();
	 * 
	 * Integer _id = (Integer) request.getAttribute("identifiant");
	 * log.info("var request*******************************************id: " +
	 * id); if (_id != null) {
	 * 
	 * this.id = _id;
	 * 
	 * } else {
	 * 
	 * this.id = null; }
	 * 
	 * 
	 * }
	 */

	// /**
	// * [AffectationMgrBean.params] Method
	// * setter the entity and Id params
	// * @author BELDI Jamel on : 16 janv. 2014 08:50:02
	// * @param entity
	// * @param identifiant
	// * @return
	// */
	// public String params(String entity, String identifiant){
	// setEntity(entity);
	// setIdentifiant(identifiant);
	// return null;
	// }
	//
	// /**
	// * [AffectationMgrBean.params] Setter
	// * @author BELDI Jamel on : 19 janv. 2014 11:03:13
	// * @param params the params to set
	// */
	// public void setParams(String params) {
	// this.params = params;
	// }

	/**
	 * [AffectationMgrBean.addAffectation] Method Add Affectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 09:53:10
	 */
	public void addAffectation() {
		FacesMessage msg = new FacesMessage();

		try {

			if (entity != null && entity.equals("individu")) {
				refAffectationDto.setIdIndividu(idEntity);
			} else if (entity != null && entity.equals("groupe")) {

				if (refAffectationDto.getIdGroupe() != null) {
					refAffectationDto.setIdGroupeAffecte(idEntity);
				} else {
					refAffectationDto.setIdGroupe(idEntity);
				}
			} else if (entity != null && entity.equals("structure")) {
				refAffectationDto.setIdStructure(idEntity);

			} else if (entity != null && entity.equals("evenement")) {
				refAffectationDto.setIdEvenement(idEntity);
			}

		}

		catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.addGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:11:54
	 * @param dialogName
	 */
	public void addGroupe(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName + ".hide()");
		try {
			addAffectation();
			if (idEntity != null) {
				refAffectationServiceImpl.save(refAffectationDto);
				loadListRefAffectationGroupeDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "Identifiant entitïé " + entity + " est NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	/**
	 * [AffectationMgrBean.addDomaineLMD] Method 
	 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:35:20
	 * @param dialogName
	 */
	public void addDomaineLMD(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName + ".hide()");
		try {
			addAffectation();
			if (idEntity != null) {
				refAffectationServiceImpl.save(refAffectationDto);
				loadListRefAffectationDomaineLMDDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "Identifiant entitïé " + entity + " est NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	
	/**
	 * [AffectationMgrBean.addFiliereLMD] Method 
	 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:35:45
	 * @param dialogName
	 */
	public void addFiliereLMD(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName + ".hide()");
		try {
			addAffectation();
			if (idEntity != null) {
				refAffectationServiceImpl.save(refAffectationDto);
				loadListRefAffectationFiliereLMDDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "Identifiant entitïé " + entity + " est NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.addStructure] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:12:27
	 * @param dialogName
	 */
	public void addStructure(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName + ".hide()");
		try {
			addAffectation();
			if (idEntity != null) {
				refAffectationServiceImpl.save(refAffectationDto);
				loadListRefAffectationStructureDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "Identifiant entitïé " + entity + " est NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.addIndividu] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:12:48
	 * @param dialogName
	 */
	public void addIndividu(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName + ".hide()");
		try {
			addAffectation();
			if (idEntity != null) {
				refAffectationServiceImpl.save(refAffectationDto);
				loadListRefAffectationIndividuDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "Identifiant entitïé " + entity + " est NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.deleteAffectation] Method delete an affectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 09:52:27
	 * @param selectedRefAffectationDto
	 */
	public void deleteAffectation(RefAffectationDto selectedRefAffectationDto) {
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				log.info("deleting affectation id = "
						+ selectedRefAffectationDto.getId());
				refAffectationServiceImpl.delete(selectedRefAffectationDto);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}

	}

	/**
	 * [AffectationMgrBean.deleteGroupe] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:17:11
	 * @param selectedRefAffectationDto
	 */
	public void deleteGroupe(RefAffectationDto selectedRefAffectationDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				deleteAffectation(selectedRefAffectationDto);
				loadListRefAffectationGroupeDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	
	/**
	 * [AffectationMgrBean.deleteDomaineLMD] Method 
	 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:36:26
	 * @param selectedRefAffectationDto
	 */
	public void deleteDomaineLMD(RefAffectationDto selectedRefAffectationDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				deleteAffectation(selectedRefAffectationDto);
				loadListRefAffectationDomaineLMDDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	
	/**
	 * [AffectationMgrBean.deleteFiliereLMD] Method 
	 * @author MAKERRI Sofiane  on : 7 janv. 2015  13:36:44
	 * @param selectedRefAffectationDto
	 */
	public void deleteFiliereLMD(RefAffectationDto selectedRefAffectationDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				deleteAffectation(selectedRefAffectationDto);
				loadListRefAffectationFiliereLMDDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	/**
	 * [AffectationMgrBean.deleteStructure] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:17:40
	 * @param selectedRefAffectationDto
	 */
	public void deleteStructure(RefAffectationDto selectedRefAffectationDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				deleteAffectation(selectedRefAffectationDto);
				loadListRefAffectationStructureDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	/**
	 * [AffectationMgrBean.deleteIndividu] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:18:41
	 * @param selectedRefAffectationDto
	 */
	public void deleteIndividu(RefAffectationDto selectedRefAffectationDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectationDto != null)
				&& (selectedRefAffectationDto.getId() != 0)) {
			try {
				deleteAffectation(selectedRefAffectationDto);
				loadListRefAffectationIndividuDto();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_suppression"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("error_echec_inconnue"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

	/**
	 * [AffectationMgrBean.beforeAdd] Method init refAffectationDto to add new
	 * Affectation
	 * 
	 * @author BELDI Jamel on : 16 janv. 2014 09:52:27
	 */
	public void beforeAdd() {
		log.info("beforeAdd");
		this.refAffectationDto = new RefAffectationDto();
		setDefaultValues();
		log.info("beforeAdd:" + refAffectationDto.getDateDebut());
	}

	/**
	 * [AffectationMgrBean.refIndividuServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 4 fï¿½vr. 2014 11:44:26
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [AffectationMgrBean.refIndividuServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 4 fï¿½vr. 2014 11:44:26
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	// /**
	// * [AffectationMgrBean.completeAffectationIndividu] Method
	// * @author BELDI Jamel on : 4 fï¿½vr. 2014 11:43:09
	// * @param query
	// * @return list of suggestions of INDIVIDUS
	// * */
	// public List<RefAffectationDto> completeAffectationIndividu(String query){
	// log.info("completeAffectationIndividu");
	// List<RefAffectationDto> suggestions = new ArrayList<RefAffectationDto>();
	// try{
	// List<RefIndividuDto> result = refIndividuServiceImpl.findGeneric(query);
	// for(RefIndividuDto refIndividuDto : result){
	// RefAffectationDto refAffectationDto = new RefAffectationDto();
	// refAffectationDto.setIdentifiantIndividu(refIndividuDto.getIdentifiant());
	// refAffectationDto.setNomLtIndividu(refIndividuDto.getNomLatin());
	// refAffectationDto.setPrenomLtIndividu(refIndividuDto.getPrenomLatin());
	// suggestions.add(refAffectationDto);
	// }
	// }catch(Exception e){
	// log.info(e.getMessage());
	// }
	//
	// return suggestions;
	// }

	/**
	 * [AffectationMgrBean.listRefAffectationIndividuDto] Getter
	 * 
	 * @author BELDI Jamel on : 4 fï¿½vr. 2014 14:10:14
	 * @return the listRefAffectationIndividuDto
	 */
	public List<RefAffectationDto> getListRefAffectationIndividuDto() {
		return listRefAffectationIndividuDto;
	}

	/**
	 * [AffectationMgrBean.loadListRefAffectationIndividuDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:13:51
	 */
	public void loadListRefAffectationIndividuDto() {
		log.info("entity:" + entity);
		log.info("id:" + idEntity);
		FacesMessage msg = new FacesMessage();
		try {
			if (idEntity != null) {
				listRefAffectationIndividuDto = refAffectationServiceImpl
						.findIndividus(entity, idEntity);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.listRefAffectationIndividuDto] Setter
	 * 
	 * @author BELDI Jamel on : 4 fï¿½vr. 2014 14:10:14
	 * @param listRefAffectationIndividuDto
	 *            the listRefAffectationIndividuDto to set
	 */
	public void setListRefAffectationIndividuDto(
			List<RefAffectationDto> listRefAffectationIndividuDto) {
		this.listRefAffectationIndividuDto = listRefAffectationIndividuDto;
	}

	/**
	 * [AffectationMgrBean.affecGroupeCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:02:31
	 * @return the affecGroupeCrudBean
	 */
	public AffecGroupeCrudBean getAffecGroupeCrudBean() {
		return affecGroupeCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecGroupeCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:02:31
	 * @param affecGroupeCrudBean
	 *            the affecGroupeCrudBean to set
	 */
	public void setAffecGroupeCrudBean(AffecGroupeCrudBean affecGroupeCrudBean) {
		this.affecGroupeCrudBean = affecGroupeCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecStructureCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:07:42
	 * @return the affecStructureCrudBean
	 */
	public AffecStructureCrudBean getAffecStructureCrudBean() {
		return affecStructureCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecStructureCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:07:42
	 * @param affecStructureCrudBean
	 *            the affecStructureCrudBean to set
	 */
	public void setAffecStructureCrudBean(
			AffecStructureCrudBean affecStructureCrudBean) {
		this.affecStructureCrudBean = affecStructureCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecIndividuCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:07:42
	 * @return the affecIndividuCrudBean
	 */
	public AffecIndividuCrudBean getAffecIndividuCrudBean() {
		return affecIndividuCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecIndividuCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:07:42
	 * @param affecIndividuCrudBean
	 *            the affecIndividuCrudBean to set
	 */
	public void setAffecIndividuCrudBean(
			AffecIndividuCrudBean affecIndividuCrudBean) {
		this.affecIndividuCrudBean = affecIndividuCrudBean;
	}

	/**
	 * [AffectationMgrBean.groupeCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:02:51
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void groupeCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affecGroupeCrudBean.setEditAllow(editAllow);
		affecGroupeCrudBean.setCreateAllow(createAllow);
		affecGroupeCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [AffectationMgrBean.domaineCrud] Method
	 * 
	 * @author BELBRIK Oualid on : 30 sept 2014 10:02:51
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void domaineLMDCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affecDomaineLMDCrudBean.setEditAllow(editAllow);
		affecDomaineLMDCrudBean.setCreateAllow(createAllow);
		affecDomaineLMDCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [AffectationMgrBean.filiereCrud] Method
	 * 
	 * @author BELBRIK Oualid on : 30 sept 2014 16:02:51
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void filiereLMDCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affecFiliereLMDCrudBean.setEditAllow(editAllow);
		affecFiliereLMDCrudBean.setCreateAllow(createAllow);
		affecFiliereLMDCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [AffectationMgrBean.structureCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:08:10
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void structureCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affecStructureCrudBean.setEditAllow(editAllow);
		affecStructureCrudBean.setCreateAllow(createAllow);
		affecStructureCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [AffectationMgrBean.individuCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 17:08:34
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void individuCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affecIndividuCrudBean.setEditAllow(editAllow);
		affecIndividuCrudBean.setCreateAllow(createAllow);
		affecIndividuCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [AffectationMgrBean.setDefaultValues] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 14:22:59
	 */
	public void setDefaultValues() {
		refAffectationDto.setRoleId(comboBckBean.getDefaultRole());
	}

	/**
	 * [AffectationMgrBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 14:26:18
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [AffectationMgrBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 14:26:18
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [AffectationMgrBean.listRefAffectationDomaineLMDDto] Getter
	 * 
	 * @author obelbrik on : 29 sept. 2014 20:40:18
	 * @return the listRefAffectationDomaineLMDDto
	 */
	public List<RefAffectationDto> getListRefAffectationDomaineLMDDto() {
		return listRefAffectationDomaineLMDDto;
	}

	/**
	 * [AffectationMgrBean.loadListRefAffectationDomaineLMDDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:32:22
	 */
	public void loadListRefAffectationDomaineLMDDto() {
		log.info("entity:" + entity);
		log.info("id:" + idEntity);
		FacesMessage msg = new FacesMessage();
		try {
			listRefAffectationDomaineLMDDto = refAffectationServiceImpl
					.findDomaines(entity, idEntity);
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.listRefAffectationDomaineLMDDto] Setter
	 * 
	 * @author obelbrik on : 29 sept. 2014 20:40:18
	 * @param listRefAffectationDto
	 *            the listRefAffectationDomaineLMDDto to set
	 */
	public void setListRefAffectationDomaineLMDDto(
			List<RefAffectationDto> listRefAffectationDomaineLMDDto) {
		this.listRefAffectationDomaineLMDDto = listRefAffectationDomaineLMDDto;
	}

	/**
	 * [AffectationMgrBean.listRefAffectationFiliereLMDDto] Getter
	 * 
	 * @author obelbrik on : 30 sept. 2014 16:40:18
	 * @return the listRefAffectationFiliereLMDDto
	 */
	public List<RefAffectationDto> getListRefAffectationFiliereLMDDto() {
		return listRefAffectationFiliereLMDDto;
	}

	/**
	 * [AffectationMgrBean.loadListRefAffectationFiliereLMDDto] Method
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 13:32:57
	 */
	public void loadListRefAffectationFiliereLMDDto() {
		log.info("entity:" + entity);
		log.info("id:" + idEntity);
		FacesMessage msg = new FacesMessage();
		try {
			listRefAffectationFiliereLMDDto = refAffectationServiceImpl
					.findFilieres(entity, idEntity);
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * [AffectationMgrBean.listRefAffectationFiliereLMDDto] Setter
	 * 
	 * @author obelbrik on : 30 sept. 2014 16:40:18
	 * @param listRefAffectationDto
	 *            the listRefAffectationFiliereLMDDto to set
	 */
	public void setListRefAffectationFiliereLMDDto(
			List<RefAffectationDto> listRefAffectationFiliereLMDDto) {
		this.listRefAffectationFiliereLMDDto = listRefAffectationFiliereLMDDto;
	}

	/**
	 * [AffectationMgrBean.affecDomaineCrudBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 12:00:18
	 * @return the affecDomaineCrudBean
	 */
	public AffecDomaineLMDCrudBean getAffecDomaineLMDCrudBean() {
		return affecDomaineLMDCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecDomaineCrudBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 12:00:18
	 * @param affecDomaineCrudBean
	 *            the affecDomaineCrudBean to set
	 */
	public void setAffecDomaineLMDCrudBean(
			AffecDomaineLMDCrudBean affecDomaineLMDCrudBean) {
		this.affecDomaineLMDCrudBean = affecDomaineLMDCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecFiliereLMDCrudBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 17:10:34
	 * @return the affecFiliereLMDCrudBean
	 */
	public AffecFiliereLMDCrudBean getAffecFiliereLMDCrudBean() {
		return affecFiliereLMDCrudBean;
	}

	/**
	 * [AffectationMgrBean.affecFiliereLMDCrudBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 sept. 2014 17:10:34
	 * @param affecFiliereLMDCrudBean
	 *            the affecFiliereLMDCrudBean to set
	 */
	public void setAffecFiliereLMDCrudBean(
			AffecFiliereLMDCrudBean affecFiliereLMDCrudBean) {
		this.affecFiliereLMDCrudBean = affecFiliereLMDCrudBean;
	}

	/**
	 * [AffectationMgrBean.idEntity] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 09:01:20
	 * @return the idEntity
	 */
	public Integer getIdEntity() {
		return idEntity;
	}

	/**
	 * [AffectationMgrBean.idEntity] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 09:01:20
	 * @param idEntity
	 *            the idEntity to set
	 */
	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [AffectationMgrBean.readMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 09:03:45
	 * @return the readMode
	 */
	public Boolean getReadMode() {
		return readMode;
	}

	/**
	 * [AffectationMgrBean.readMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 09:03:45
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(Boolean readMode) {
		this.readMode = readMode;
	}

}
