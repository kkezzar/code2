/**
 * [dz.gov.mesrs.sii.referentiel.jsf.beans.adresse.CoordonneeMgrBean.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  14:05:04
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.coordonnee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
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
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 16 avr. 2014 11:43:03
 */
@ManagedBean(name = "coordonneeMgrBean")
@ViewScoped
public class CoordonneeMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 14:02:59
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{refCoordonneeServiceImpl}")
	private RefCoordonneeService refCoordonneeServiceImpl;
	@ManagedProperty(value = "#{refAdresseServiceImpl}")
	private RefAdresseService refAdresseServiceImpl;
	@ManagedProperty(value = "#{refTelephoneServiceImpl}")
	private RefTelephoneService refTelephoneServiceImpl;
	@ManagedProperty(value = "#{refAdresseElectroniqueServiceImpl}")
	private RefAdresseElectroniqueService refAdresseElectroniqueServiceImpl;
	private static final Log log = LogFactory.getLog(CoordonneeMgrBean.class);
	private RefAdresseDto refAdresseDto;
	private RefTelephoneDto refTelephoneDto;
	private RefAdresseElectroniqueDto refAdresseElectroniqueDto;
	private List<RefCoordonneeDto> listRefCoordonneeDto;
	private List<RefAdresseDto> listRefAdresseDto;
	private List<RefTelephoneDto> listRefTelephoneDto;
	private List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto;
	private String entity;
//	private String idf;
//	private String id;
	private Integer idEntity;
	private ResourceBundle bundle;
	private ResourceBundle corBundle;
	@ManagedProperty(value = "#{coordonneeCrudBean}")
	private CoordonneeCrudBean coordonneeCrudBean;
	private List<SelectItem> listWilaya;
	private List<SelectItem> listDaira;
	private List<SelectItem> listCommune;
	private List<NomenclatureDto> ncWilayaLis;
	private List<NomenclatureDto> ncDairaList;
	private List<NomenclatureDto> ncCommuneList;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;
	@ManagedProperty(value = "#{coordonneeBckBean}")
	private CoordonneeBckBean coordonneeBckBean;
	private Boolean readMode;

	// pour la gestion de changement des natures de telephone
	private String maskTelephone = "";
	List<NomenclatureDto> listMaskTelephone;

	/**
	 * [CoordonneeMgrBean.CoordonneeMgrBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:05:04
	 */
	public CoordonneeMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		refAdresseDto = new RefAdresseDto();
		refTelephoneDto = new RefTelephoneDto();
		refAdresseElectroniqueDto = new RefAdresseElectroniqueDto();
		// setDefaultValues();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);

		corBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COORDONNEESS_MESSAGES_FILE_NAME);

//		HttpServletRequest request = (HttpServletRequest) context
//				.getExternalContext().getRequest();
//
//		String entityname = (String) request.getAttribute("entity");
//		log.info("var request*******************************************entityname: "
//				+ entityname);
//		if (entityname != null) {
//			this.entity = entityname;
//		}
//
//		idEntity = (Integer) request.getAttribute("id");
//
//		log.info("var request*******************************************id: "
//				+ idEntity);

	}

	@PostConstruct
	public void init() {
		readMode = (Boolean) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("readMode");
		idEntity = (Integer) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("idEntity");
		entity = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getFlash().get("entity");
		if(readMode == null) {
			readMode = false;
		}
		if (getListRefCoordonneeDto() == null
				|| !coordonneeBckBean.getId().equals(idEntity)) {
			loadCoordonnee();
		}
		if (getListRefAdresseDto() == null
				|| ( coordonneeBckBean.getId() != null && !coordonneeBckBean.getId().equals(idEntity))) {
			if (!readMode) {
				loadWilaya();
			}
			loadAdresses();
		}
		if (getListRefAdresseElectroniqueDto() == null
				|| !coordonneeBckBean.getId().equals(idEntity)) {
			loadAdressesElectronique();
		}
		if (getListRefTelephoneDto() == null
				|| !coordonneeBckBean.getId().equals(idEntity)) {
			loadTelephones();
		}

		setDefaultValues();
		coordonneeBckBean.setId(idEntity);
	}

	/**
	 * [CoordonneeMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 13:23:30
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		coordonneeCrudBean.setEditAllow(editAllow);
		coordonneeCrudBean.setCreateAllow(createAllow);
		coordonneeCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [CoordonneeMgrBean.refTelephoneServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 16:38:14
	 * @return the refTelephoneServiceImpl
	 */
	public RefTelephoneService getRefTelephoneServiceImpl() {
		return refTelephoneServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refTelephoneServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 16:38:14
	 * @param refTelephoneServiceImpl
	 *            the refTelephoneServiceImpl to set
	 */
	public void setRefTelephoneServiceImpl(
			RefTelephoneService refTelephoneServiceImpl) {
		this.refTelephoneServiceImpl = refTelephoneServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @return the refAdresseServiceImpl
	 */
	public RefAdresseService getRefAdresseServiceImpl() {
		return refAdresseServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @param refAdresseServiceImpl
	 *            the refAdresseServiceImpl to set
	 */
	public void setRefAdresseServiceImpl(RefAdresseService refAdresseServiceImpl) {
		this.refAdresseServiceImpl = refAdresseServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @return the refAdresseDto
	 */
	public RefAdresseDto getRefAdresseDto() {
		return refAdresseDto;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @param refAdresseDto
	 *            the refAdresseDto to set
	 */
	public void setRefAdresseDto(RefAdresseDto refAdresseDto) {
		this.refAdresseDto = refAdresseDto;
	}

	/**
	 * [CoordonneeMgrBean.loadCoordonnee] Method
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 08:48:22
	 */
	public void loadCoordonnee() {
		try {
			log.info("load adresse list for entity = " + getEntity()
					+ " with id = " + idEntity);
			List<RefCoordonneeDto> result = null;
			if (getEntity() == null || idEntity == null) {
				coordonneeBckBean.setListRefCoordonneeDto(null);
				return;
			}
			if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				result = refCoordonneeServiceImpl.findByRefIndividuId(idEntity);
			} else if (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				result = refCoordonneeServiceImpl
						.findByRefStructureId(idEntity);
			} else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				result = refCoordonneeServiceImpl.findByRefLieuId(idEntity);

			} else if (getEntity().equals(
					UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				result = refCoordonneeServiceImpl
						.findByRefEtablissementId(idEntity);
			}
			if (result == null /* || result.isEmpty() */) {
				log.info("RefAdresseDto is null or empty");
				coordonneeBckBean.setListRefCoordonneeDto(null);
				// setListRefAdresseDto(null);

			} else {
				log.info("RefAdresseDto list found with size = "
						+ result.size());
				// setListRefAdresseDto(result);
				coordonneeBckBean.setListRefCoordonneeDto(result);

			}
		} catch (RuntimeException e) {
			setListRefAdresseDto(null);
			throw e;
		}
	}

	/**
	 * [CoordonneeMgrBean.loadAdresses] Method Load a list of address of entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:52
	 */
	public void loadAdresses() {
		try {
			log.info("load adresse list for entity = " + getEntity()
					+ " with id = " + idEntity);
			List<RefAdresseDto> result = null;
			result = refAdresseServiceImpl
					.findByRefCoordonnee(getListRefCoordonneeDto());
			/*
			 * if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
			 * result = refAdresseServiceImpl.findByRefIndividuId(identifiant);
			 * 
			 * } else if
			 * (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) { result
			 * = refAdresseServiceImpl .findByRefStructureId(identifiant); }
			 * else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
			 * result = refAdresseServiceImpl.findByRefLieuId(identifiant); }
			 * else if (getEntity().equals(
			 * UtilConstant.ETABLISSEMENT_ENTITY_NAME)) { result =
			 * refAdresseServiceImpl .findByRefEtablissementId(identifiant); }
			 */
			if (result == null /* || result.isEmpty() */) {
				log.info("RefAdresseDto is null or empty");
				coordonneeBckBean.setListRefAdresseDto(null);
				// setListRefAdresseDto(null);

			} else {
				log.info("RefAdresseDto list found with size = "
						+ result.size());
				// setListRefAdresseDto(result);
				coordonneeBckBean.setListRefAdresseDto(result);

			}
		} catch (RuntimeException e) {
			setListRefAdresseDto(null);
			throw e;
		}
	}

	/**
	 * [CoordonneeMgrBean.loadTelephones] Method load a list phone of entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:46
	 */
	public void loadTelephones() {
		try {
			log.info("load telephonne list for entity = " + getEntity()
					+ " with id = " + idEntity);
			List<RefTelephoneDto> result = null;
			result = refTelephoneServiceImpl
					.findByRefCoordonnee(getListRefCoordonneeDto());
			/*
			 * if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
			 * 
			 * result = refTelephoneServiceImpl
			 * .findByRefIndividuId(identifiant); } else if
			 * (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) { result
			 * = refTelephoneServiceImpl .findByRefStructureId(identifiant); }
			 * else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
			 * result = refTelephoneServiceImpl.findByRefLieuId(identifiant); }
			 * else if (getEntity().equals(
			 * UtilConstant.ETABLISSEMENT_ENTITY_NAME)) { result =
			 * refTelephoneServiceImpl .findByRefEtablissementId(identifiant); }
			 */

			if (result == null /* || result.isEmpty() */) {
				log.info("RefTelephoneDto is null or empty");
				// setListRefTelephoneDto(null);
				coordonneeBckBean.setListRefTelephoneDto(null);

			} else {
				log.info("RefTelephoneDto list found with size = "
						+ result.size());
				// setListRefTelephoneDto(result);
				coordonneeBckBean.setListRefTelephoneDto(result);

			}
		} catch (RuntimeException e) {
			// setListRefTelephoneDto(null);
			coordonneeBckBean.setListRefTelephoneDto(null);
			throw e;
		}
	}

	/**
	 * [CoordonneeMgrBean.loadAdressesElectronique] Method load a a list of
	 * Electronic address of entity
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:52:22
	 */
	public void loadAdressesElectronique() {
		try {
			log.info("load adresseElectronique list for entity = "
					+ getEntity() + " with id = " + idEntity);
			List<RefAdresseElectroniqueDto> result = null;
			result = refAdresseElectroniqueServiceImpl
					.findByRefCoordonnee(getListRefCoordonneeDto());

			/*
			 * if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
			 * 
			 * result = refAdresseElectroniqueServiceImpl
			 * .findByRefIndividuId(identifiant);
			 * 
			 * } else if
			 * (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) { result
			 * = refAdresseElectroniqueServiceImpl
			 * .findByRefStructureId(identifiant); } else if
			 * (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) { result =
			 * refAdresseElectroniqueServiceImpl .findByRefLieuId(identifiant);
			 * } else if (getEntity().equals(
			 * UtilConstant.ETABLISSEMENT_ENTITY_NAME)) { result =
			 * refAdresseElectroniqueServiceImpl
			 * .findByRefEtablissementId(identifiant); }
			 */

			if (result == null /* || result.isEmpty() */) {
				log.info("RefAdresseElectroniqueDto is null or empty");
				// setListRefAdresseElectroniqueDto(null);
				coordonneeBckBean.setListRefAdresseElectroniqueDto(null);

			} else {
				log.info("RefAdresseElectroniqueDto list found with size = "
						+ result.size());
				coordonneeBckBean.setListRefAdresseElectroniqueDto(result);
				// setListRefAdresseElectroniqueDto(result);

			}
		} catch (RuntimeException e) {
			// setListRefAdresseElectroniqueDto(null);
			coordonneeBckBean.setListRefAdresseElectroniqueDto(null);
			throw e;
		}
	}

	/**
	 * [CoordonneeMgrBean.refAdresseListDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @return the refAdresseListDto
	 */
	public List<RefAdresseDto> getListRefAdresseDto() {
		/*
		 * if (listRefAdresseDto == null) { loadAdresses(); }
		 */
		listRefAdresseDto = coordonneeBckBean.getListRefAdresseDto();
		/*
		 * if(listRefAdresseDto != null && listRefAdresseDto.isEmpty()) {
		 * listRefAdresseDto = null; }
		 */
		return listRefAdresseDto;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseListDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:09:10
	 * @param refAdresseListDto
	 *            the refAdresseListDto to set
	 */
	public void setListRefAdresseDto(List<RefAdresseDto> listRefAdresseDto) {
		this.listRefAdresseDto = listRefAdresseDto;
	}

	/**
	 * [CoordonneeMgrBean.deleteAdresse] Method delete address of entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:38
	 * @param refAdresseDto
	 */
	public void deleteAdresse(RefAdresseDto refAdresseDto) {
		FacesMessage msg = new FacesMessage();
		if (refAdresseDto != null) {
			try {
				log.info("deleting adresse id = " + refAdresseDto.getId());
				refAdresseServiceImpl.delete(refAdresseDto);
				// loadAdresses();
				coordonneeBckBean.deleteAdresseDto(refAdresseDto);
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
	 * [CoordonneeMgrBean.addAdresse] Method add address to entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:33
	 */
	public void addAdresse(String dialogName) {
		RequestContext.getCurrentInstance().execute(dialogName+".hide()");
		FacesMessage msg = new FacesMessage();
		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if ((refAdresseDto != null)
				&& (refAdresseDto.getTypeAdresseId() != null)
				&& (refAdresseDto.getPaysId() != null)) {
			try {

				log.info("saving adresse for entity = " + getEntity()
						+ " with id = " + idEntity);
				if (verifyAddressExisting(refAdresseDto)) {
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundle.getString("warn_info") + ": "
							+ corBundle.getString("adresse_existe"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				setAdresseRelationShip();

				refAdresseServiceImpl.save(refAdresseDto);
				coordonneeBckBean.addAdresseDto(refAdresseDto);
				// loadAdresses();
				initAdresse();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
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
	 * [CoordonneeMgrBean.addTelephone] Method add phone to entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:25
	 */
	public void addTelephone(String dialogName) {

		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName+".hide()");
		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refTelephoneDto != null)
				&& (refTelephoneDto.getNatureTelephoneId() != null)
				&& (refTelephoneDto.getTypeTelephoneId() != null)) {
			try {

				log.info("saving telephone for entity = " + getEntity()
						+ " with id = " + idEntity);
				if (verifyPhoneExisting(refTelephoneDto)) {
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundle.getString("warn_info") + ": "
							+ corBundle.getString("telephone_existe"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				setTelephoneRelationShip();
				refTelephoneServiceImpl.save(refTelephoneDto);
				coordonneeBckBean.addTelephoneDto(refTelephoneDto);
				// loadTelephones();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
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
	 * [CoordonneeMgrBean.deleteTelephone] Method delete phone of entity
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:51:18
	 * @param refTelephoneDto
	 */
	public void deleteTelephone(RefTelephoneDto refTelephoneDto) {
		FacesMessage msg = new FacesMessage();
		if (refTelephoneDto != null) {
			try {
				log.info("deleting Telephone id = " + refTelephoneDto.getId());
				refTelephoneServiceImpl.delete(refTelephoneDto);
				coordonneeBckBean.deleteTelephoneDto(refTelephoneDto);
				// loadTelephones();
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
	 * [CoordonneeMgrBean.addAdresseElectronique] Method add Electronic address
	 * to entity
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 13:39:40
	 */
	public void addAdresseElectronique(String dialogName) {
		FacesMessage msg = new FacesMessage();
		RequestContext.getCurrentInstance().execute(dialogName+".hide()");
		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_identifiant_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (getEntity() == null || getEntity().trim().isEmpty()) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_entity_null"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (idEntity == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ corBundle.getString("coordonnee_error_identifiant_null"));
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

				if (refAdresseElectroniqueDto
						.getNatureAdresseElectroniqueId()
						.equals(Integer.parseInt(corBundle
								.getString("adresse_electronique_nature_idEmail")))
						&& !validateMail(refAdresseElectroniqueDto
								.getNomAdresse())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(corBundle
							.getString("adresse_electronique_invalid"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				if (verifyElectronicAddressExisting(refAdresseElectroniqueDto)) {
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundle.getString("warn_info")
							+ ": "
							+ corBundle
									.getString("adresse_electronique_existe"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				setAdresseElectroniqueRelationShip();

				refAdresseElectroniqueServiceImpl
						.save(refAdresseElectroniqueDto);
				coordonneeBckBean
						.addAdresseElectroniqueDto(refAdresseElectroniqueDto);

				// loadAdressesElectronique();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
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
	 * [CoordonneeMgrBean.deleteAdresseElectronique] Method delete Electronic
	 * address of entity
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:55:59
	 * @param refAdresseElectroniqueDto
	 */
	public void deleteAdresseElectronique(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		FacesMessage msg = new FacesMessage();
		if (refAdresseElectroniqueDto != null) {
			try {
				log.info("deleting adresseElectronique id = "
						+ refAdresseElectroniqueDto.getId());
				refAdresseElectroniqueServiceImpl
						.delete(refAdresseElectroniqueDto);
				// loadAdressesElectronique();
				coordonneeBckBean
						.deleteAdresseElectroniqueDto(refAdresseElectroniqueDto);
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
	 * [CoordonneeMgrBean.setAdresseRelationShip] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:01:20
	 */
	public void setAdresseRelationShip() {
		if (refAdresseDto != null) {
			refAdresseDto.setIdIndividu(null);
			refAdresseDto.setIdStructure(null);
			refAdresseDto.setIdLieu(null);
			refAdresseDto.setIdEtablissement(null);
			refAdresseDto
					.setTypeCoordonnee(UtilConstant.COORDONNEE_ADRESSE_TYPE);
			if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				refAdresseDto.setIdIndividu(idEntity);
			} else if (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				refAdresseDto.setIdStructure(idEntity);
			} else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				refAdresseDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				refAdresseDto.setIdEtablissement(idEntity);
			}
		}
	}

	/**
	 * [CoordonneeMgrBean.setTelephoneRelationShip] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:01:25
	 */
	public void setTelephoneRelationShip() {
		if (refTelephoneDto != null) {
			refTelephoneDto.setIdIndividu(null);
			refTelephoneDto.setIdStructure(null);
			refTelephoneDto.setIdLieu(null);
			refTelephoneDto.setIdEtablissement(null);
			refTelephoneDto
					.setTypeCoordonnee(UtilConstant.COORDONNEE_TELEPHONE_TYPE);
			if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				refTelephoneDto.setIdIndividu(idEntity);
			} else if (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				refTelephoneDto.setIdStructure(idEntity);
			} else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				refTelephoneDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				refTelephoneDto.setIdEtablissement(idEntity);
			}
		}
	}

	/**
	 * [CoordonneeMgrBean.setAdresseElectroniqueRelationShip] Method
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 11:06:07
	 */

	public void setAdresseElectroniqueRelationShip() {
		if (refAdresseElectroniqueDto != null) {
			refAdresseElectroniqueDto.setIdIndividu(null);
			refAdresseElectroniqueDto.setIdStructure(null);
			refAdresseElectroniqueDto.setIdLieu(null);
			refAdresseElectroniqueDto.setIdEtablissement(null);
			refAdresseElectroniqueDto
					.setTypeCoordonnee(UtilConstant.COORDONNEE_EMAIL_TYPE);
			if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdIndividu(idEntity);
			} else if (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdStructure(idEntity);
			} else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdLieu(idEntity);
			} else if (getEntity().equals(
					UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				refAdresseElectroniqueDto.setIdEtablissement(idEntity);
			}
		}
	}

	/**
	 * [CoordonneeMgrBean.entity] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:48:48
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * [CoordonneeMgrBean.entity] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:48:48
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * [CoordonneeMgrBean.identifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:48:48
	 * @return the identifiant
	 */
	/*
	 * public String getIdentifiant() { return identifiant; }
	 */

	/**
	 * [CoordonneeMgrBean.identifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 janv. 2014 14:48:48
	 * @param identifiant
	 *            the identifiant to set
	 */
	/*
	 * public void setIdentifiant(String identifiant) { if (this.identifiant ==
	 * null && identifiant != null && !identifiant.equals("null")) {
	 * this.identifiant = identifiant; int _id =
	 * UtilConstant.strToInt(identifiant); if(_id != -1) { this.id = _id; } } }
	 */

	/**
	 * [CoordonneeMgrBean.listRefTelephoneDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 16:35:45
	 * @return the listRefTelephoneDto
	 */
	public List<RefTelephoneDto> getListRefTelephoneDto() {
		/*
		 * if (listRefTelephoneDto == null) { loadTelephones(); }
		 */
		listRefTelephoneDto = coordonneeBckBean.getListRefTelephoneDto();
		/*
		 * if(listRefTelephoneDto != null && listRefTelephoneDto.isEmpty()) {
		 * listRefTelephoneDto = null; }
		 */
		return listRefTelephoneDto;
	}

	/**
	 * [CoordonneeMgrBean.listRefTelephoneDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 16:35:45
	 * @param listRefTelephoneDto
	 *            the listRefTelephoneDto to set
	 */
	public void setListRefTelephoneDto(List<RefTelephoneDto> listRefTelephoneDto) {
		this.listRefTelephoneDto = listRefTelephoneDto;
	}

	/**
	 * [CoordonneeMgrBean.refTelephoneDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:01:35
	 * @return the refTelephoneDto
	 */
	public RefTelephoneDto getRefTelephoneDto() {
		return refTelephoneDto;
	}

	/**
	 * [CoordonneeMgrBean.refTelephoneDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 janv. 2014 17:01:35
	 * @param refTelephoneDto
	 *            the refTelephoneDto to set
	 */
	public void setRefTelephoneDto(RefTelephoneDto refTelephoneDto) {
		this.refTelephoneDto = refTelephoneDto;
	}

	/**
	 * [CoordonneeMgrBean.listRefAdresseElectroniqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:51:02
	 * @return the listRefAdresseElectroniqueDto
	 */
	public List<RefAdresseElectroniqueDto> getListRefAdresseElectroniqueDto() {
		/*
		 * if (listRefAdresseElectroniqueDto == null) {
		 * loadAdressesElectronique(); }
		 */
		listRefAdresseElectroniqueDto = coordonneeBckBean
				.getListRefAdresseElectroniqueDto();
		/*
		 * if(listRefAdresseElectroniqueDto != null &&
		 * listRefAdresseElectroniqueDto.isEmpty()) {
		 * listRefAdresseElectroniqueDto = null; }
		 */
		return listRefAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeMgrBean.listRefAdresseElectroniqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:51:02
	 * @param listRefAdresseElectroniqueDto
	 *            the listRefAdresseElectroniqueDto to set
	 */
	public void setListRefAdresseElectroniqueDto(
			List<RefAdresseElectroniqueDto> listRefAdresseElectroniqueDto) {
		this.listRefAdresseElectroniqueDto = listRefAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseElectroniqueServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:53:14
	 * @return the refAdresseElectroniqueServiceImpl
	 */
	public RefAdresseElectroniqueService getRefAdresseElectroniqueServiceImpl() {
		return refAdresseElectroniqueServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseElectroniqueServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:53:14
	 * @param refAdresseElectroniqueServiceImpl
	 *            the refAdresseElectroniqueServiceImpl to set
	 */
	public void setRefAdresseElectroniqueServiceImpl(
			RefAdresseElectroniqueService refAdresseElectroniqueServiceImpl) {
		this.refAdresseElectroniqueServiceImpl = refAdresseElectroniqueServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseElectroniqueDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:58:15
	 * @return the refAdresseElectroniqueDto
	 */
	public RefAdresseElectroniqueDto getRefAdresseElectroniqueDto() {
		return refAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeMgrBean.refAdresseElectroniqueDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 janv. 2014 10:58:15
	 * @param refAdresseElectroniqueDto
	 *            the refAdresseElectroniqueDto to set
	 */
	public void setRefAdresseElectroniqueDto(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		this.refAdresseElectroniqueDto = refAdresseElectroniqueDto;
	}

	/**
	 * [CoordonneeMgrBean.initAdresse] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 févr. 2014 11:33:14
	 */
	public void initAdresse() {
		refAdresseDto = new RefAdresseDto();
	}

	/**
	 * [CoordonneeMgrBean.coordonneeCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 12:48:47
	 * @return the coordonneeCrudBean
	 */
	public CoordonneeCrudBean getCoordonneeCrudBean() {
		return coordonneeCrudBean;
	}

	/**
	 * [CoordonneeMgrBean.coordonneeCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 12:48:47
	 * @param coordonneeCrudBean
	 *            the coordonneeCrudBean to set
	 */
	public void setCoordonneeCrudBean(CoordonneeCrudBean coordonneeCrudBean) {
		this.coordonneeCrudBean = coordonneeCrudBean;
	}

	/**
	 * [CoordonneeMgrBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:06:27
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:06:27
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.listWilaya] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:13:02
	 * @return the listWilaya
	 */
	public List<SelectItem> getListWilaya() {
		return listWilaya;
	}

	/**
	 * [CoordonneeMgrBean.listWilaya] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:13:02
	 * @param listWilaya
	 *            the listWilaya to set
	 */
	public void setListWilaya(List<SelectItem> listWilaya) {
		this.listWilaya = listWilaya;
	}

	/**
	 * [CoordonneeMgrBean.listCommune] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:12:14
	 * @return the listCommune
	 */
	public List<SelectItem> getListCommune() {
		return loadCommune();
	}

	/**
	 * [CoordonneeMgrBean.listCommune] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:12:14
	 * @param listCommune
	 *            the listCommune to set
	 */
	public void setListCommune(List<SelectItem> listCommune) {
		this.listCommune = listCommune;
	}

	/**
	 * [CoordonneeMgrBean.listDaira] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:12:28
	 * @return the listDaira
	 */
	public List<SelectItem> getListDaira() {
		return loadDaira();
	}

	/**
	 * [CoordonneeMgrBean.listDaira] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:12:28
	 * @param listDaira
	 *            the listDaira to set
	 */
	public void setListDaira(List<SelectItem> listDaira) {
		this.listDaira = listDaira;
	}

	/**
	 * [CoordonneeMgrBean.loadDaira] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:45:27
	 * @return
	 */
	public List<SelectItem> loadDaira() {
		if (listDaira == null || listDaira.isEmpty()) {
			listDaira = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = null;
			if (refAdresseDto != null && refAdresseDto.getWilayaId() != null) {
				list = nomenclatureServiceImpl
						.findNcCompositeList(refAdresseDto.getWilayaId(),
								UtilConstant.NC_DAIRA_NAME);
			} else {
				list = nomenclatureServiceImpl
						.findByName(UtilConstant.NC_DAIRA_NAME);

			}
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listDaira.add(selectItem);
			}
		}
		return listDaira;
	}

	/**
	 * [CoordonneeMgrBean.loadWilaya] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:49:23
	 * @return
	 */
	public List<SelectItem> loadWilaya() {
		if (listWilaya == null || listWilaya.isEmpty()) {
			listWilaya = new ArrayList<SelectItem>();
			ncWilayaLis = null;
			if (refAdresseDto != null && refAdresseDto.getPaysId() != null) {
				ncWilayaLis = nomenclatureServiceImpl.findNcCompositeList(
						refAdresseDto.getPaysId(), UtilConstant.NC_WILAYA_NAME);
			} else {
				ncWilayaLis = nomenclatureServiceImpl
						.findByName(UtilConstant.NC_WILAYA_NAME);
			}
			for (NomenclatureDto nomenclatureDto : ncWilayaLis) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listWilaya.add(selectItem);
			}
		}
		return listWilaya;
	}

	/**
	 * [CoordonneeMgrBean.loadCommune] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 10:51:31
	 * @return
	 */
	public List<SelectItem> loadCommune() {
		if (listCommune == null || listCommune.isEmpty()) {
			listCommune = new ArrayList<SelectItem>();
			List<NomenclatureDto> list = null;
			if (refAdresseDto != null && refAdresseDto.getDairaId() != null) {
				list = nomenclatureServiceImpl.findNcCompositeList(
						refAdresseDto.getDairaId(),
						UtilConstant.NC_COMMUNE_NAME);
			} else {
				list = nomenclatureServiceImpl
						.findByName(UtilConstant.NC_COMMUNE_NAME);

			}
			for (NomenclatureDto nomenclatureDto : list) {
				SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
						nomenclatureDto.getLibelleLongFr());
				listCommune.add(selectItem);
			}
		}
		return listCommune;
	}

	/**
	 * [CoordonneeMgrBean.paysChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 12:54:44
	 */
	public void paysChanged() {

	}

	/**
	 * [CoordonneeMgrBean.wilayaChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 09:22:25
	 */
	public void wilayaChanged() {
		if (ncWilayaLis != null && refAdresseDto.getWilayaId() != null) {
			for (NomenclatureDto nc : ncWilayaLis) {
				if (nc.getId().equals(refAdresseDto.getWilayaId())) {
					ncCommuneList = nc.getNomenclatureDtos();
					if (ncCommuneList != null) {
						for (NomenclatureDto nomenclatureDto : ncCommuneList) {
							SelectItem selectItem = new SelectItem(
									nomenclatureDto.getId(),
									nomenclatureDto.getLibelleLongFr());
							listCommune.add(selectItem);
						}
					}
				}
			}
		}
	}

	/**
	 * [CoordonneeMgrBean.dairaChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 avr. 2014 12:54:50
	 */
	public void dairaChanged() {

	}

	/**
	 * [CoordonneeMgrBean.comboBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:53:51
	 * @return the comboBckBean
	 */
	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	/**
	 * [CoordonneeMgrBean.comboBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:53:51
	 * @param comboBckBean
	 *            the comboBckBean to set
	 */
	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	/**
	 * [CoordonneeMgrBean.setDefaultValues] Method
	 * 
	 * @author MAKERRI Sofiane on : 29 avr. 2014 12:56:15
	 */
	public void setDefaultValues() {
		try {
			refAdresseDto.setCommuneId(comboBckBean.getDefaultCommune());
			refAdresseDto.setDairaId(comboBckBean.getDefaultDaira());
			refAdresseDto.setWilayaId(comboBckBean.getDefaultWilaya());
			refAdresseDto.setPaysId(comboBckBean.getDefaultPays());
			refAdresseDto
					.setTypeAdresseId(comboBckBean.getDefaultTypeAdresse());

			refTelephoneDto.setNatureTelephoneId(comboBckBean
					.getDefaultNatTel());
			refTelephoneDto
					.setTypeTelephoneId(comboBckBean.getDefaultTypeTel());

			refAdresseElectroniqueDto
					.setNatureAdresseElectroniqueId(comboBckBean
							.getDefaultNatureAdresseElectronique());
			refAdresseElectroniqueDto.setTypeAdresseElectroniqueId(comboBckBean
					.getDefaultTypeMail());
		} catch (Exception e) {
			log.info("erreur " + e.getMessage());
		}
	}

	/**
	 * [CoordonneeMgrBean.coordonneeBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 mai 2014 15:26:42
	 * @return the coordonneeBckBean
	 */
	public CoordonneeBckBean getCoordonneeBckBean() {
		return coordonneeBckBean;
	}

	/**
	 * [CoordonneeMgrBean.coordonneeBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 mai 2014 15:26:42
	 * @param coordonneeBckBean
	 *            the coordonneeBckBean to set
	 */
	public void setCoordonneeBckBean(CoordonneeBckBean coordonneeBckBean) {
		this.coordonneeBckBean = coordonneeBckBean;
	}

	/**
	 * [CoordonneeMgrBean.listRefCoordonneeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 08:34:30
	 * @return the listRefCoordonneeDto
	 */
	public List<RefCoordonneeDto> getListRefCoordonneeDto() {
		listRefCoordonneeDto = coordonneeBckBean.getListRefCoordonneeDto();
		return listRefCoordonneeDto;
	}

	/**
	 * [CoordonneeMgrBean.listRefCoordonneeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 08:34:30
	 * @param listRefCoordonneeDto
	 *            the listRefCoordonneeDto to set
	 */
	public void setListRefCoordonneeDto(
			List<RefCoordonneeDto> listRefCoordonneeDto) {
		this.listRefCoordonneeDto = listRefCoordonneeDto;
	}

	/**
	 * [CoordonneeMgrBean.refCoordonneeServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 08:42:10
	 * @return the refCoordonneeServiceImpl
	 */
	public RefCoordonneeService getRefCoordonneeServiceImpl() {
		return refCoordonneeServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.refCoordonneeServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 mai 2014 08:42:10
	 * @param refCoordonneeServiceImpl
	 *            the refCoordonneeServiceImpl to set
	 */
	public void setRefCoordonneeServiceImpl(
			RefCoordonneeService refCoordonneeServiceImpl) {
		this.refCoordonneeServiceImpl = refCoordonneeServiceImpl;
	}

	/**
	 * [CoordonneeMgrBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 12:12:29
	 * @return the id
	 */
	/*
	 * public Integer idEntity { return id; }
	 */

	/**
	 * [CoordonneeMgrBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 mai 2014 12:12:29
	 * @param id
	 *            the id to set
	 */
	/*
	 * public void setId(Integer id) { this.id = id; }
	 */

	/**
	 * [CoordonneeMgrBean.idf] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 mai 2014 12:37:17
	 * @return the idf
	 */
//	public String getIdf() {
//		return idf;
//	}

	/**
	 * [CoordonneeMgrBean.idf] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 mai 2014 12:37:17
	 * @param idf
	 *            the idf to set
	 */
//	public void setIdf(String idf) {
//		this.idf = idf;
//		if (idf != null && !idf.trim().equals("null")) {
//			int _id = UtilConstant.strToInt(idf);
//			if (_id != -1) {
//				setIdEntity(_id);
//			}
//		}
//	}

	/**
	 * [CoordonneeMgrBean.idEntity] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 14:22:37
	 * @return the idEntity
	 */
	public Integer getIdEntity() {
		return idEntity;
	}

	/**
	 * [CoordonneeMgrBean.idEntity] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 14:22:37
	 * @param idEntity
	 *            the idEntity to set
	 */
	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [CoordonneeMgrBean.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 14:23:04
	 * @return the id
	 */
//	public String getId() {
//		return id;
//	}

	/**
	 * [CoordonneeMgrBean.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 mai 2014 14:23:04
	 * @param id
	 *            the id to set
	 */
//	public void setId(String id) {
//		this.id = id;
//
//		if (id != null && !id.trim().equals("null")) {
//			setIdf(id);
//			int _id = UtilConstant.strToInt(id);
//			if (_id != -1) {
//				setIdEntity(_id);
//			}
//		}
//	}

	/**
	 * [CoordonneeMgrBean.verifyPhoneExisting] Method verify Phone Existing
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 11:17:05
	 * @param refTelephoneDto
	 * @return true if exist
	 */
	private boolean verifyPhoneExisting(RefTelephoneDto refTelephoneDto) {
		if (listRefTelephoneDto != null && !listRefTelephoneDto.isEmpty()) {
			for (RefTelephoneDto element : listRefTelephoneDto) {
				if (element.getNumeroTelephone().equals(
						refTelephoneDto.getNumeroTelephone())
						&& element.getNatureTelephoneId().equals(
								refTelephoneDto.getNatureTelephoneId())) {
					return true;
				}

			}
		}

		return false;

	}

	/**
	 * [CoordonneeMgrBean.verifyElectronicAddressExisting] Method verify
	 * Electronic Address Existing
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 11:25:01
	 * @param refAdresseElectroniqueDto
	 * @return true if exist
	 */
	private boolean verifyElectronicAddressExisting(
			RefAdresseElectroniqueDto refAdresseElectroniqueDto) {
		if (listRefAdresseElectroniqueDto != null
				&& !listRefAdresseElectroniqueDto.isEmpty()) {
			for (RefAdresseElectroniqueDto element : listRefAdresseElectroniqueDto) {
				if (element.getNomAdresse().equals(
						refAdresseElectroniqueDto.getNomAdresse())
						&& element.getNatureAdresseElectroniqueId().equals(
								refAdresseElectroniqueDto
										.getNatureAdresseElectroniqueId())) {
					return true;
				}

			}
		}

		return false;

	}

	/**
	 * [CoordonneeMgrBean.verifyAddressExisting] Method verify Address Existing
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 11:25:06
	 * @param refAdresseDto
	 * @return true if exist
	 */
	private boolean verifyAddressExisting(RefAdresseDto refAdresseDto) {

		if (listRefAdresseDto != null && !listRefAdresseDto.isEmpty()) {
			for (RefAdresseDto element : listRefAdresseDto) {
				if (element.getPaysId().equals(refAdresseDto.getPaysId())
						&& element.getTypeAdresseId().equals(
								refAdresseDto.getTypeAdresseId())
						&& element.getLibelleAdresseLatin().equals(
								refAdresseDto.getLibelleAdresseLatin())) {
					return true;
				}

			}
		}

		return false;

	}

	/**
	 * [CoordonneeMgrBean.natureAdresseElectronicChanged] Method
	 * 
	 * @author BELDI Jamel on : 8 juil. 2014 12:46:15
	 */
	public void natureAdresseElectronicChanged() {

	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateMail(final String hex) {
		String EMAIL_PATTERN = corBundle
				.getString("adresse_electronique_email_pattern") /*
																 * "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
																 * +
																 * "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
																 */;
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		;
		Matcher matcher;

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 juil. 2014 11:03:13
	 * @return the mask
	 */
	public String getMaskTelephone() {
		return maskTelephone;
	}

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 20 juil. 2014 11:03:07
	 * @param maskTelephone
	 */
	public void setMaskTelephone(String maskTelephone) {
		this.maskTelephone = maskTelephone;
	}

	/**
	 * Evenement declanche apres le chanchement de la nature de telephone
	 * 
	 * @author Mounir.MESSAOUDI on : 20 juil. 2014 11:03:42
	 */
	public void telephoneNatureChanged() {
		int typeTelId = refTelephoneDto.getNatureTelephoneId();
		if (this.listMaskTelephone == null)
			this.listMaskTelephone = nomenclatureServiceImpl
					.findByName(UtilConstant.NC_MASK_TEL_NAME);

		for (NomenclatureDto l : this.listMaskTelephone) {
			if (l.getIdReference() == typeTelId) {
				this.maskTelephone = l.getCode();
			}
		}
		refTelephoneDto.setNumeroTelephone(null);
	}

	/**
	 * [CoordonneeMgrBean.ncWilayaLis] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @return the ncWilayaLis
	 */
	public List<NomenclatureDto> getNcWilayaLis() {
		return ncWilayaLis;
	}

	/**
	 * [CoordonneeMgrBean.ncWilayaLis] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @param ncWilayaLis
	 *            the ncWilayaLis to set
	 */
	public void setNcWilayaLis(List<NomenclatureDto> ncWilayaLis) {
		this.ncWilayaLis = ncWilayaLis;
	}

	/**
	 * [CoordonneeMgrBean.ncDairaList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @return the ncDairaList
	 */
	public List<NomenclatureDto> getNcDairaList() {
		return ncDairaList;
	}

	/**
	 * [CoordonneeMgrBean.ncDairaList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @param ncDairaList
	 *            the ncDairaList to set
	 */
	public void setNcDairaList(List<NomenclatureDto> ncDairaList) {
		this.ncDairaList = ncDairaList;
	}

	/**
	 * [CoordonneeMgrBean.ncCommuneList] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @return the ncCommuneList
	 */
	public List<NomenclatureDto> getNcCommuneList() {
		return ncCommuneList;
	}

	/**
	 * [CoordonneeMgrBean.ncCommuneList] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:04:30
	 * @param ncCommuneList
	 *            the ncCommuneList to set
	 */
	public void setNcCommuneList(List<NomenclatureDto> ncCommuneList) {
		this.ncCommuneList = ncCommuneList;
	}

	/**
	 * [CoordonneeMgrBean.readMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:53:50
	 * @return the readMode
	 */
	public Boolean getReadMode() {
		return readMode;
	}

	/**
	 * [CoordonneeMgrBean.readMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 janv. 2015 11:53:50
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(Boolean readMode) {
		this.readMode = readMode;
	}

}
