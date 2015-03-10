package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAffectDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefGroupeService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * 
 * @author yselmane on : 13 mai 2014 11:45:35
 */
@ManagedBean(name = "affectDocumentBean")
@ViewScoped
public class AffectDocumentBean implements Serializable {

	private static final Log log = LogFactory.getLog(AffectDocumentBean.class);
	private static final long serialVersionUID = 1L;

	private RefAffectDocumentDto refAffectDocumentDto;

	private List<RefAffectDocumentDto> listRefAffectDocumentStructureDto;
	private List<RefAffectDocumentDto> listRefAffectDocumentGroupeDto;
	private List<RefAffectDocumentDto> listRefAffectDocumentIndividuDto;

	private String idfEntity;

	private ResourceBundle bundle;
	private static final String MESSAGES_FILE_NAME = "commonmsgs";
	private ResourceBundle affectationBundle;

	@ManagedProperty(value = "#{refAffectDocumentServiceImpl}")
	private RefAffectDocumentService refAffectDocumentServiceImpl;

	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;

	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;

	@ManagedProperty(value = "#{refGroupeServiceImpl}")
	private RefGroupeService refGroupeServiceImpl;

	private RefAdresseDto refAdresseDto;

	@ManagedProperty(value = "#{comboBckBean}")
	private ComboBckBean comboBckBean;

	@ManagedProperty(value = "#{affectDocIndividuCrudBean}")
	private AffectDocIndividuCrudBean affectDocIndividuCrudBean;

	@ManagedProperty(value = "#{affectDocStructureCrudBean}")
	private AffectDocStructureCrudBean affectDocStructureCrudBean;

	@ManagedProperty(value = "#{affectDocGroupeCrudBean}")
	private AffectDocGroupeCrudBean affectDocGroupeCrudBean;

	/**
	 * 
	 * [AffectDocumentBean.AffectDocumentBean()] Constructor
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:16
	 */
	public AffectDocumentBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		bundle = context.getApplication().getResourceBundle(context,
				AffectDocumentBean.MESSAGES_FILE_NAME);
		affectationBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.AFFECTATION_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		load();
	}

	/**
	 * 
	 * [AffectDocumentBean.load] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:55
	 */
	public void load() {

		log.info("idfEntity:" + idfEntity);
		FacesMessage msg = new FacesMessage();
		try {
			if (idfEntity != null && !idfEntity.isEmpty()) {

				listRefAffectDocumentStructureDto = refAffectDocumentServiceImpl
						.findStructuresOfDocument(idfEntity);

				listRefAffectDocumentGroupeDto = refAffectDocumentServiceImpl
						.findGroupesOfDocument(idfEntity);

				listRefAffectDocumentIndividuDto = refAffectDocumentServiceImpl
						.findIndividusOfDocument(idfEntity);
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
	 * 
	 * [AffectDocumentBean.getRefAffectDocumentDto] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:59
	 * @return
	 */
	public RefAffectDocumentDto getRefAffectDocumentDto() {
		if (refAffectDocumentDto == null) {
			refAffectDocumentDto = new RefAffectDocumentDto();
			setDefaultValues();
		}
		return refAffectDocumentDto;
	}

	public void setRefAffectDocumentDto(
			RefAffectDocumentDto refAffectDocumentDto) {
		this.refAffectDocumentDto = refAffectDocumentDto;
	}

	/**
	 * 
	 * [AffectDocumentBean.getListRefAffectDocumentGroupeDto] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:45:05
	 * @return
	 */
	public List<RefAffectDocumentDto> getListRefAffectDocumentGroupeDto() {

		log.info("idfEntity:" + idfEntity);
		FacesMessage msg = new FacesMessage();
		try {
			listRefAffectDocumentGroupeDto = refAffectDocumentServiceImpl
					.findGroupesOfDocument(idfEntity);
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return listRefAffectDocumentGroupeDto;
	}

	public void setListRefAffectDocumentGroupeDto(
			List<RefAffectDocumentDto> listRefAffectDocumentGroupeDto) {
		this.listRefAffectDocumentGroupeDto = listRefAffectDocumentGroupeDto;
	}

	/**
	 * 
	 * [AffectDocumentBean.getListRefAffectDocumentStructureDto] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:45:11
	 * @return
	 */
	public List<RefAffectDocumentDto> getListRefAffectDocumentStructureDto() {

		log.info("idfEntity:" + idfEntity);
		FacesMessage msg = new FacesMessage();
		try {
			listRefAffectDocumentStructureDto = refAffectDocumentServiceImpl
					.findStructuresOfDocument(idfEntity);
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return listRefAffectDocumentStructureDto;
	}

	public void setListRefAffectDocumentStructureDto(
			List<RefAffectDocumentDto> listRefAffectDocumentStructureDto) {
		this.listRefAffectDocumentStructureDto = listRefAffectDocumentStructureDto;
	}

	/**
	 * 
	 * [AffectDocumentBean.getListRefAffectDocumentIndividuDto] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:45:17
	 * @return
	 */
	public List<RefAffectDocumentDto> getListRefAffectDocumentIndividuDto() {

		log.info("idfEntity:" + idfEntity);
		FacesMessage msg = new FacesMessage();
		try {
			if (idfEntity != null && !idfEntity.isEmpty()) {
				listRefAffectDocumentIndividuDto = refAffectDocumentServiceImpl
						.findIndividusOfDocument(idfEntity);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return listRefAffectDocumentIndividuDto;
	}

	public void setListRefAffectDocumentIndividuDto(
			List<RefAffectDocumentDto> listRefAffectDocumentIndividuDto) {
		this.listRefAffectDocumentIndividuDto = listRefAffectDocumentIndividuDto;
	}

	public void setIdfEntity(String idfEntity) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		String id = (String) request.getAttribute("idfEntity");
		log.info("var request*******************************************id: "
				+ id);
		if (id != null) {
			this.idfEntity = id;
		} else {

			// this.idfEntity = idfEntity;
			if ((idfEntity != null) && (idfEntity.equals("null"))) {
				this.idfEntity = null;

			} else {
				this.idfEntity = idfEntity;
			}
		}

	}

	public String getIdfEntity() {
		return idfEntity;
	}

	/**
	 * [AffectDocumentBean.refAdressedto] Getter
	 * 
	 * @author yselmane on : 13 mai 2014 12:35:25
	 * @return the refAdressedto
	 */
	public RefAdresseDto getRefAdresseDto() {
		return refAdresseDto;
	}

	/**
	 * [AffectDocumentBean.refAdressedto] Setter
	 * 
	 * @author yselmane on : 13 mai 2014 12:35:25
	 * @param refAdressedto
	 *            the refAdressedto to set
	 */
	public void setRefAdresseDto(RefAdresseDto refAdresseDto) {
		this.refAdresseDto = refAdresseDto;
	}

	/**
	 * 
	 * [AffectDocumentBean.addAffectation] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:29
	 */
	public void addAffectation() {
		FacesMessage msg = new FacesMessage();

		try {
			// if (refAffectDocumentDto.getDateDebut() == null
			// || (refAffectDocumentDto.getDateFin() != null &&
			// refAffectDocumentDto
			// .getDateDebut().after(
			// refAffectDocumentDto.getDateFin()))) {
			// msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			// msg.setSummary(bundle.getString("error_date_debut_fin"));
			// FacesContext.getCurrentInstance().addMessage(null, msg);
			// } else {

			if (idfEntity != null) {

				refAffectDocumentDto.setIdDocument(idfEntity);
				refAffectDocumentServiceImpl.save(refAffectDocumentDto);
				load();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ "idfEntity entite " + idfEntity + " est a NULL");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			// }
		} catch (javax.persistence.PersistenceException cve) {
			log.info(cve.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ affectationBundle.getString("affectation_echec_unicite"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			log.info(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * 
	 * [AffectDocumentBean.deleteAffectation] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:36
	 * @param selectedRefAffectDocumentDto
	 */
	public void deleteAffectation(
			RefAffectDocumentDto selectedRefAffectDocumentDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedRefAffectDocumentDto != null)
				&& (selectedRefAffectDocumentDto.getId() != 0)) {
			try {
				log.info("deleting affectation id = "
						+ selectedRefAffectDocumentDto.getId());
				refAffectDocumentServiceImpl
						.remove(selectedRefAffectDocumentDto);
				load();
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
	 * 
	 * [AffectDocumentBean.beforeAdd] Method
	 * 
	 * @author yselmane on : 13 mai 2014 11:44:40
	 */
	public void beforeAdd() {
		log.info("beforeAdd");
		this.refAffectDocumentDto = new RefAffectDocumentDto();
		setDefaultValues();
		log.info("beforeAdd:" + refAffectDocumentDto.getDateDebut());
	}

	/**
	 * [AffectDocumentBean.refAffectDocumentServiceImpl] Getter
	 * 
	 * @author yselmane on : 13 mai 2014 08:52:23
	 * @return the refAffectDocumentServiceImpl
	 */
	public RefAffectDocumentService getRefAffectDocumentServiceImpl() {
		return refAffectDocumentServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refAffectDocumentServiceImpl] Setter
	 * 
	 * @author yselmane on : 13 mai 2014 08:52:23
	 * @param refAffectDocumentServiceImpl
	 *            the refAffectDocumentServiceImpl to set
	 */
	public void setRefAffectDocumentServiceImpl(
			RefAffectDocumentService refAffectDocumentServiceImpl) {
		this.refAffectDocumentServiceImpl = refAffectDocumentServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refIndividuServiceImpl] Getter
	 * 
	 * @author yselmane on : 13 mai 2014 08:51:09
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refIndividuServiceImpl] Setter
	 * 
	 * @author yselmane on : 13 mai 2014 08:51:09
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refStructureServiceImpl] Getter
	 * 
	 * @author yselmane on : 13 mai 2014 08:50:56
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refStructureServiceImpl] Setter
	 * 
	 * @author yselmane on : 13 mai 2014 08:50:56
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refGroupeServiceImpl] Getter
	 * 
	 * @author yselmane on : 13 mai 2014 08:51:55
	 * @return the refGroupeServiceImpl
	 */
	public RefGroupeService getRefGroupeServiceImpl() {
		return refGroupeServiceImpl;
	}

	/**
	 * [AffectDocumentBean.refGroupeServiceImpl] Setter
	 * 
	 * @author yselmane on : 13 mai 2014 08:51:55
	 * @param refGroupeServiceImpl
	 *            the refGroupeServiceImpl to set
	 */
	public void setRefGroupeServiceImpl(RefGroupeService refGroupeServiceImpl) {
		this.refGroupeServiceImpl = refGroupeServiceImpl;
	}

	public void setDefaultValues() {
		refAffectDocumentDto.setRoleId(comboBckBean.getDefaultRole());
	}

	public ComboBckBean getComboBckBean() {
		return comboBckBean;
	}

	public void setComboBckBean(ComboBckBean comboBckBean) {
		this.comboBckBean = comboBckBean;
	}

	public AffectDocIndividuCrudBean getAffectDocIndividuCrudBean() {
		return affectDocIndividuCrudBean;
	}

	public void setAffectDocIndividuCrudBean(
			AffectDocIndividuCrudBean affectDocIndividuCrudBean) {
		this.affectDocIndividuCrudBean = affectDocIndividuCrudBean;
	}

	public AffectDocStructureCrudBean getAffectDocStructureCrudBean() {
		return affectDocStructureCrudBean;
	}

	public void setAffectDocStructureCrudBean(
			AffectDocStructureCrudBean affectDocStructureCrudBean) {
		this.affectDocStructureCrudBean = affectDocStructureCrudBean;
	}

	public AffectDocGroupeCrudBean getAffectDocGroupeCrudBean() {
		return affectDocGroupeCrudBean;
	}

	public void setAffectDocGroupeCrudBean(
			AffectDocGroupeCrudBean affectDocGroupeCrudBean) {
		this.affectDocGroupeCrudBean = affectDocGroupeCrudBean;
	}

	public void individuCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affectDocIndividuCrudBean.setEditAllow(editAllow);
		affectDocIndividuCrudBean.setCreateAllow(createAllow);
		affectDocIndividuCrudBean.setDeleteAllow(deleteAllow);
	}

	public void structureCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affectDocStructureCrudBean.setEditAllow(editAllow);
		affectDocStructureCrudBean.setCreateAllow(createAllow);
		affectDocStructureCrudBean.setDeleteAllow(deleteAllow);
	}

	public void groupeCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		affectDocGroupeCrudBean.setEditAllow(editAllow);
		affectDocGroupeCrudBean.setCreateAllow(createAllow);
		affectDocGroupeCrudBean.setDeleteAllow(deleteAllow);
	}
}
