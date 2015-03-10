package dz.gov.mesrs.sii.refenrentiel.jsf.beans.etatvalidation;

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
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.data.model.referentiel.RefCompte;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefContrat;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEvenement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefReservation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;
import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSituationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.service.RefSituationService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author obelbrik
 * 
 */
/**
 * @author BELDI Jamel  on : 8 juil. 2014  16:35:40
 */
@ManagedBean(name = "situationMgrBean")
@ViewScoped
public class SituationMgrBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchInput;
	private ResourceBundle bundle;
	private ResourceBundle evBundle;
	@ManagedProperty(value = "#{refSituationServiceImpl}")
	private RefSituationService refSituationServiceImpl;
	private static final Log log = LogFactory.getLog(SituationMgrBean.class);
	private RefSituationDto refSituationDto;
	private List<RefSituationDto> listRefAffectationSituationDto;
	private Integer idEntity;
	private String entity;
	private boolean updateMode;
	private boolean readMode;
	@ManagedProperty(value = "#{situationBckBean}")
	private SituationBckBean situationBckBean;
	private List<SelectItem> listeTypeSituation;
	@ManagedProperty(value = "#{situationCrudBean}")
	private SituationCrudBean situationCrudBean;
	private String param;

	@ManagedProperty(value = "#{refParametrageServiceImpl}")
	private RefParametrageService refParametrageServiceImpl;

	/**
	 * [SituationMgrBean.refParametrageServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @return the refParametrageServiceImpl
	 */
	public RefParametrageService getRefParametrageServiceImpl() {
		return refParametrageServiceImpl;
	}

	/**
	 * [SituationMgrBean.refParametrageServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 7 avr. 2014 14:59:45
	 * @param refParametrageServiceImpl
	 *            the refParametrageServiceImpl to set
	 */
	public void setRefParametrageServiceImpl(
			RefParametrageService refParametrageServiceImpl) {
		this.refParametrageServiceImpl = refParametrageServiceImpl;
	}

	/**
	 * [SituationMgrBean.SituationMgrBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:19:25
	 */
	public SituationMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		evBundle = context.getApplication().getResourceBundle(context,
			RefUtilConstant.ETAT_VALIDATION_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		try {
			readMode = (Boolean) FacesContext.getCurrentInstance()
					.getExternalContext().getFlash().get("readMode");
			idEntity = (Integer) FacesContext.getCurrentInstance()
					.getExternalContext().getFlash().get("idEntity");
			entity = (String) FacesContext.getCurrentInstance()
					.getExternalContext().getFlash().get("entity");

		log.info("*******************************************idEntity: " + idEntity);
		log.info("var request*******************************************entity: "
				+ entity);
		} catch(Exception e) {
			
		}
		
	}

	public String getSearchInput() {

		return searchInput;
	}

	/**
	 * [SituationMgrBean.setSearchInput] Method Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:20:24
	 * @param searchInput
	 */
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	/**
	 * @return the refSituationDto
	 */
	public RefSituationDto getRefSituationDto() {
		if (refSituationDto == null) {
			if (situationBckBean.getRefSituationDto() != null) {
				refSituationDto = situationBckBean.getRefSituationDto();
			} else {
				refSituationDto = new RefSituationDto();
			}
		}

		return refSituationDto;
	}

	/**
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		if (updateMode == false) {
			if (situationBckBean.getRefSituationDto() != null) {
				updateMode = true;
			}
		}
		return updateMode;
	}

	/**
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * @return the readMode
	 */
	public boolean isReadMode() {
		return readMode;
	}

	/**
	 * @param readMode
	 *            the readMode to set
	 */
	public void setReadMode(boolean readMode) {
		this.readMode = readMode;
	}

	/**
	 * 
	 * @param event
	 * @return
	 */
	public String toDetails() {
		setReadMode(true);
		setUpdateMode(false);
		situationBckBean.setListRefSituationDto(null);
		return "toDetails";
	}

	/**
	 * navigate to new Situation page
	 * 
	 * @return outcome
	 */
	public String toNew() {
		setReadMode(true);
		setUpdateMode(false);
		situationBckBean.setListRefSituationDto(null);
		situationBckBean.setRefSituationDto(null);
		return "toNew";
	}

	/**
	 * 
	 * navigate to edit Situation page
	 * 
	 * @return
	 */
	public String toEdit() {
		setReadMode(false);
		setUpdateMode(true);
		situationBckBean.setListRefSituationDto(null);
		return "toEdit";
	}

	/**
	 * [SituationMgrBean.findGeneric] Method Find the list of Situation by the
	 * the search input
	 * 
	 * @author jbeldi on : 15 janv. 2014 11:18:14
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		try {
			List<RefSituationDto> result = refSituationServiceImpl
					.findGeneric(this.searchInput);
			if (result == null || result.isEmpty()) {
				situationBckBean.setListRefSituationDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				situationBckBean.setListRefSituationDto(result);

			}
		} catch (Exception e) {
			situationBckBean.setListRefSituationDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * [SituationMgrBean.refSituationServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @return the refSituationServiceImpl
	 */
	public RefSituationService getRefSituationServiceImpl() {
		return refSituationServiceImpl;
	}

	/**
	 * [SituationMgrBean.refSituationServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param refSituationServiceImpl
	 *            the refSituationServiceImpl to set
	 */
	public void setRefSituationServiceImpl(
			RefSituationService refSituationServiceImpl) {
		this.refSituationServiceImpl = refSituationServiceImpl;
	}

	/**
	 * [SituationMgrBean.SituationBckBean] Getter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @return the SituationBckBean
	 */
	public SituationBckBean getSituationBckBean() {
		return situationBckBean;
	}

	/**
	 * [SituationMgrBean.SituationBckBean] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param EtatequipementBckBean
	 *            the SituationBckBean to set
	 */
	public void setSituationBckBean(SituationBckBean situationBckBean) {
		this.situationBckBean = situationBckBean;
	}

	/**
	 * [SituationMgrBean.refSituationDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 29 janv. 2014 09:42:23
	 * @param refSituationDto
	 *            the refSituationDto to set
	 */
	public void setRefSituationDto(RefSituationDto refSituationDto) {
		this.refSituationDto = refSituationDto;
	}

	public String getEntity() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String entityname = (String) request.getAttribute("entity");
		if (entityname != null) {
			return entityname;
		} else {
			return entity;
		}

	}

	public void setEntity(String entity) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String entityname = (String) request.getAttribute("entity");
		log.info("var request in set entity: " + entityname);
		if (entityname != null) {
			this.entity = entityname;
		} else {
			this.entity = entity;
		}

	}

	/**
	 * [SituationMgrBean.idf] Getter
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 17:30:51
	 * @return the idf
	 */
	/*public Integer getId() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		Integer _id = (Integer) request.getAttribute("id");
		if(_id != null) {
			this.idEntity = _id;
		} 
		return idEntity;
	}*/
	
	

	/**
	 * [SituationMgrBean.idEntity] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  14:37:29
	 * @return the idEntity
	 */
	public Integer getIdEntity() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		
		Integer _id = (Integer) request.getAttribute("id");
		if(_id != null) {
			this.idEntity = _id;
		} 
		return idEntity;
	}

	/**
	 * [SituationMgrBean.idEntity] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  14:37:29
	 * @param idEntity the idEntity to set
	 */
	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [SituationMgrBean.id] Setter
	 * 
	 * @author BELDI Jamel on : 26 f�vr. 2014 17:30:51
	 * @param id
	 *            the id to set
	 */
	/*public void setId(Integer id) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer _id = (Integer) request.getAttribute("id");

		this.id = _id;

	}*/

	/**
	 * [SituationMgrBean.listRefAffectationSituationDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 30 janv. 2014 18:46:49
	 * @return the listRefAffectationSituationDto
	 */
	public List<RefSituationDto> getListRefAffectationSituationDto() {
		FacesMessage msg = new FacesMessage();

		try {
			if (getEntity() != null
					&& getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.INDIVIDU_ENTITY_NAME,
								getIdEntity());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.STRUCTURE_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.GROUPE_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.GROUPE_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.CONTRAT_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.CONTRAT_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.COMPTE_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.COMPTE_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.LIEU_ENTITY_NAME, getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.EQUIPEMENT_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.EQUIPEMENT_ENTITY_NAME,
								getIdEntity());

			}

			else if (getEntity() != null
					&& getEntity().equals(UtilConstant.EVENEMENT_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.EVENEMENT_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.RESERVATION_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.RESERVATION_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.DOMAINELMD_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.DOMAINELMD_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(
							UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.ETABLISSEMENT_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.FILIERELMD_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.FILIERELMD_ENTITY_NAME,
								getIdEntity());

			} else if (getEntity() != null
					&& getEntity().equals(
							UtilConstant.SPECIALITELMD_ENTITY_NAME)) {
				listRefAffectationSituationDto = refSituationServiceImpl
						.findSituations(UtilConstant.SPECIALITELMD_ENTITY_NAME,
								getIdEntity());

			}

		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return listRefAffectationSituationDto;
	}

	/**
	 * [SituationMgrBean.listRefAffectationSituationDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 30 janv. 2014 18:46:49
	 * @param listRefAffectationSituationDto
	 *            the listRefAffectationSituationDto to set
	 */
	public void setListRefAffectationSituationDto(
			List<RefSituationDto> listRefAffectationSituationDto) {
		this.listRefAffectationSituationDto = listRefAffectationSituationDto;
	}

	public void addSituation() {
		FacesMessage msg = new FacesMessage();

		try {

			if (getEntity() == null || getEntity().trim().isEmpty()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec")
						+ ": "
						+ evBundle
								.getString("etatvalidation_error_entity_null"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (getIdEntity() == null) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec")
						+ ": "
						+ evBundle
								.getString("etatvalidation_error_identifiant_null"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			if (verifyCurrentSituation(refSituationDto)) {
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("error_echec")
						+ ": "
						+ evBundle
								.getString("etatvalidation_error_aucune_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			
			if (getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefIndividu.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto
						.setNomEntite(RefStructure.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.GROUPE_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefGroupe.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.CONTRAT_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefContrat.class.getSimpleName());
			}

			else if (getEntity().equals(UtilConstant.COMPTE_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefCompte.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefLieu.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.EVENEMENT_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto
						.setNomEntite(RefEvenement.class.getSimpleName());
			} else if (getEntity().equals(UtilConstant.EQUIPEMENT_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefEquipement.class
						.getSimpleName());
			} else if (getEntity().equals(UtilConstant.RESERVATION_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefReservation.class
						.getSimpleName());
			} else if (getEntity().equals(
					UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefEtablissement.class
						.getSimpleName());
			} else if (getEntity().equals(UtilConstant.DOMAINELMD_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefDomaineLMD.class
						.getSimpleName());
			} else if (getEntity().equals(UtilConstant.FILIERELMD_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefFiliereLmd.class
						.getSimpleName());
			} else if (getEntity().equals(
					UtilConstant.SPECIALITELMD_ENTITY_NAME)) {
				refSituationDto.setIdEntite(getIdEntity());
				refSituationDto.setNomEntite(RefSpecialiteLmd.class
						.getSimpleName());
			}
			refSituationServiceImpl.save(refSituationDto);
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

	public void beforeAdd(ActionEvent event) {
		// setIdf((String) event.getComponent().getAttributes().get("idf"));
		log.info("beforeAdd:" + this.idEntity);
		this.refSituationDto = new RefSituationDto();

	}

	/**
	 * [SituationMgrBean.listeTypeSituation] Getter
	 * 
	 * @author BELBRIK Oualid on : 1 f�vr. 2014 13:17:34
	 * @return the listeTypeSituation
	 */
	public List<SelectItem> getListeTypeSituation() {
		FacesMessage msg = new FacesMessage();
		List<RefSituationDto> result = new ArrayList<RefSituationDto>();
		listeTypeSituation = new ArrayList<SelectItem>();
		try {
			if (getEntity() != null
					&& getEntity().equals(UtilConstant.INDIVIDU_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefIndividu.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.STRUCTURE_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefStructure.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.GROUPE_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefGroupe.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.CONTRAT_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefContrat.class
								.getSimpleName());
			}

			else if (getEntity() != null
					&& getEntity().equals(UtilConstant.COMPTE_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefCompte.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.LIEU_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefLieu.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.EVENEMENT_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefEvenement.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.EQUIPEMENT_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefEquipement.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.RESERVATION_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefReservation.class
								.getSimpleName());

			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.DOMAINELMD_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefDomaineLMD.class
								.getSimpleName());
			} else if (getEntity() != null
					&& getEntity().equals(UtilConstant.FILIERELMD_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefFiliereLmd.class
								.getSimpleName());

			} else if (getEntity() != null
					&& getEntity().equals(
							UtilConstant.SPECIALITELMD_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefSpecialiteLmd.class
								.getSimpleName());

			} else if (getEntity() != null
					&& getEntity().equals(
							UtilConstant.ETABLISSEMENT_ENTITY_NAME)) {
				result = refSituationServiceImpl
						.findListSituationByEntityName(RefEtablissement.class

						.getSimpleName());
			}

			for (RefSituationDto refSituationDto : result) {
				if (refSituationDto.getTypeSituationId() != null
						&& !refSituationDto
								.getTypeSituationId()
								.equals(54/*Integer.valueOf(Integer
										.parseInt(refParametrageServiceImpl
												.getParamConfiguration(
														UtilConstant.SITUATION_CREE_ID_KEY,
														UtilConstant.CONFIGURATION_UTIL)
												.getValue()))*/)) {
					SelectItem selectItem = new SelectItem(
							refSituationDto.getEntiteSituationId(),
							refSituationDto.getTypeSituationLibelleLongFr());
					listeTypeSituation.add(selectItem);
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return listeTypeSituation;
	}

	/**
	 * [SituationMgrBean.listeTypeSituation] Setter
	 * 
	 * @author BELBRIK Oualid on : 1 f�vr. 2014 13:17:34
	 * @param listeTypeSituation
	 *            the listeTypeSituation to set
	 */
	public void setListeTypeSituation(List<SelectItem> listeTypeSituation) {
		this.listeTypeSituation = listeTypeSituation;
	}

	/**
	 * [SituationMgrBean.situationCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 15:01:54
	 * @return the situationCrudBean
	 */
	public SituationCrudBean getSituationCrudBean() {
		return situationCrudBean;
	}

	/**
	 * [SituationMgrBean.situationCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 mars 2014 15:01:54
	 * @param situationCrudBean
	 *            the situationCrudBean to set
	 */
	public void setSituationCrudBean(SituationCrudBean situationCrudBean) {
		this.situationCrudBean = situationCrudBean;
	}

	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		situationCrudBean.setEditAllow(editAllow);
		situationCrudBean.setCreateAllow(createAllow);
		situationCrudBean.setDeleteAllow(deleteAllow);
	}

//	public void param(String entity, String idf) {
//		if (!entity.trim().isEmpty() && !idf.trim().isEmpty()) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			HttpServletRequest request = (HttpServletRequest) context
//					.getExternalContext().getRequest();
//
//			//request.setAttribute("id", idf);
//			//request.setAttribute("entity", entity);
//			int _id = UtilConstant.strToInt(idf);
//			if (_id != -1) {
//				this.idEntity = _id;
//			}
//
//			this.entity = entity;
//			// getListRefAffectationSituationDto();
//		}
//	}

	/**
	 * [SituationMgrBean.param] Getter
	 * 
	 * @author BELBRIK Oualid on : 31 mars 2014 18:56:40
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * [SituationMgrBean.param] Setter
	 * 
	 * @author BELBRIK Oualid on : 31 mars 2014 18:56:40
	 * @param param
	 *            the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

	
	 /**
	 * [SituationMgrBean.verifyCurrentSituation] Method verify Current Situation
	 * @author BELDI Jamel  on : 8 juil. 2014  16:35:47
	 * @param refSituationDto
	 * @return
	 */
	private boolean verifyCurrentSituation(RefSituationDto refSituationDto){
	 if(listRefAffectationSituationDto!=null && !listRefAffectationSituationDto.isEmpty()){
		 RefSituationDto currentSituation = listRefAffectationSituationDto.get(/*listRefAffectationSituationDto.size()-1*/0);
		 if(currentSituation.getEntiteSituationId().equals(refSituationDto.getEntiteSituationId())){
			 return true;
		}
	 }
	 return false;
	 }
}
