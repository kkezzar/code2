/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.EtatEquipement.java] 
 * @author BELBRIK Oualid on : 19 02. 2014 17:47:10
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.equipement;

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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtatEquipementDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEtatEquipementService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 17 mars. 2014 13:47:10
 */
@ManagedBean(name = "etatequipementMgrBean")
@RequestScoped
public class EtatEquipementMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 22 mars. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [EtatEquipement.EtatEquipement()] Constructor
	 * 
	 * 
	 */
	private List<RefEtatEquipementDto> listRefEtatEquipementDto;

	@ManagedProperty(value = "#{refEtatEquipementServiceImpl}")
	private RefEtatEquipementService refEtatEquipementServiceImpl;
	private RefEtatEquipementDto refEtatEquipementDto;
	private ResourceBundle bundle;
	private ResourceBundle eBundle;
	private static final Log log = LogFactory
			.getLog(EtatEquipementMgrBean.class);
	@ManagedProperty("#{param.idf}")
	private String idf;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty(value = "#{etatEquipCrudBean}")
	private EtatEquipCrudBean etatEquipCrudBean;

	public EtatEquipementMgrBean() {
		super();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Boolean b = (Boolean) request.getAttribute("editMode");
		Integer id = (Integer) request.getAttribute("id");
		if (b != null) {
			this.editMode = b;
		}
		if (id != null) {
			this.idf = id.toString();
		}
		log.info("var request*******************************************editMode: "
				+ b + " idf:" + idf);
		refEtatEquipementDto = new RefEtatEquipementDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		eBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.EQUIPEMENT_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		load();

	}

	/**
	 * [EtatEquipement.refEtatEquipementServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 13:50:32
	 * @param refEtatEquipementServiceImpl
	 *            the refEtatEquipementServiceImpl to set
	 */
	public void setRefEtatEquipementServiceImpl(
			RefEtatEquipementService refEtatEquipementServiceImpl) {
		this.refEtatEquipementServiceImpl = refEtatEquipementServiceImpl;
	}

	public void load() {
		try {
			List<RefEtatEquipementDto> result = null;
			if (idf != null) {
				int _id = RefUtilConstant.strToInt(idf);
				if (_id == -1) {
					return;
				}
				result = refEtatEquipementServiceImpl.findByIdEquipement(_id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of etat is null");
				setListRefEtatEquipementDto(null);

			} else {

				listRefEtatEquipementDto = new ArrayList<RefEtatEquipementDto>();

				for (RefEtatEquipementDto currentRefEtatEquipementDto : result) {
					if (currentRefEtatEquipementDto.getIdEquipement() != null) {
						listRefEtatEquipementDto
								.add(currentRefEtatEquipementDto);
					}

				}
				log.info("list of etat Equipement:"
						+ listRefEtatEquipementDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}

	/**
	 * [EtatEquipement.listRefEtatEquipementDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 16:09:29
	 * @return the listRefEtatEquipementDto
	 */
	public List<RefEtatEquipementDto> getListRefEtatEquipementDto() {
		return listRefEtatEquipementDto;
	}

	/**
	 * [EtatEquipement.listRefEtatEquipementDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 16:09:29
	 * @param listRefEtatEquipementDto
	 *            the listRefEtatEquipementDto to set
	 */
	public void setListRefEtatEquipementDto(
			List<RefEtatEquipementDto> listRefEtatEquipementDto) {
		this.listRefEtatEquipementDto = listRefEtatEquipementDto;
	}

	public void addEtat() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		if (refEtatEquipementDto != null) {
			try {
				refEtatEquipementDto.setIdEquipement(_id);
				log.info("saving etat id = ");
				refEtatEquipementServiceImpl.save(refEtatEquipementDto);
				load();
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
	 * [EtatEquipement.refEtatEquipementDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 15:51:43
	 * @return the refEtatEquipementDto
	 */
	public RefEtatEquipementDto getRefEtatEquipementDto() {
		return refEtatEquipementDto;
	}

	/**
	 * [EtatEquipement.refEtatEquipementDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 15:51:43
	 * @param refEtatEquipementDto
	 *            the refEtatEquipementDto to set
	 */
	public void setRefEtatEquipementDto(
			RefEtatEquipementDto refEtatEquipementDto) {
		this.refEtatEquipementDto = refEtatEquipementDto;
	}

	/**
	 * onStructureCancel, Cancel modify the signature date and reference
	 * 
	 * @author BELDI Jamel on : 17 mars. 2014 15:51:43
	 * @param event
	 *            the RowEditEvent
	 */
	public void onSignatureCancel(RowEditEvent event) {

	}

	/**
	 * [EtatEquipementMgrBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [EtatEquipementMgrBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [EtatEquipementMgrBean.idf] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [EtatEquipementMgrBean.idf] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @param idf
	 *            the idf to set
	 */
	public void setIdf(String idf) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Integer id = (Integer) request.getAttribute("id");
		log.info("var request*******************************************id: "
				+ id);
		if (id != null) {
			this.idf = id.toString();
		} else {

			// this.identifiant = identifiant;
			if ((idf != null) && (idf.equals("null"))) {
				this.idf = null;

			} else {
				this.idf = idf;
			}
		}
	}

	/**
	 * [EtatEquipementMgrBean.searchValue] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [EtatEquipementMgrBean.searchValue] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else
			this.searchValue = searchValue;
	}

	/**
	 * [EtatEquipementMgrBean.editMode] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [EtatEquipementMgrBean.editMode] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:39:03
	 * @param editMode
	 *            the editMode to set
	 */
	public void setEditMode(boolean editMode) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Boolean b = (Boolean) request.getAttribute("editMode");
		log.info("var request in set editMode: " + b);
		if (b != null) {
			this.editMode = b;
		} else {
			this.editMode = editMode;
		}
	}

	/**
	 * [EtatEquipementMgrBean.etatEquipCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 17:52:22
	 * @return the etatEquipCrudBean
	 */
	public EtatEquipCrudBean getEtatEquipCrudBean() {
		return etatEquipCrudBean;
	}

	/**
	 * [EtatEquipementMgrBean.etatEquipCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 17:52:22
	 * @param etatEquipCrudBean
	 *            the etatEquipCrudBean to set
	 */
	public void setEtatEquipCrudBean(EtatEquipCrudBean etatEquipCrudBean) {
		this.etatEquipCrudBean = etatEquipCrudBean;
	}

	/**
	 * [EtatEquipementMgrBean.refEtatEquipementServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 17:52:22
	 * @return the refEtatEquipementServiceImpl
	 */
	public RefEtatEquipementService getRefEtatEquipementServiceImpl() {
		return refEtatEquipementServiceImpl;
	}

	/**
	 * [EtatEquipementMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 17:52:47
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		etatEquipCrudBean.setEditAllow(editAllow);
		etatEquipCrudBean.setCreateAllow(createAllow);
		etatEquipCrudBean.setDeleteAllow(deleteAllow);
	}

	public void deleteEtat(
			RefEtatEquipementDto selectedEquipementEtatEquipementDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedEquipementEtatEquipementDto != null)
				&& (selectedEquipementEtatEquipementDto.getIdEquipement() != null)) {
			try {
				log.info("deleting etat id = "
						+ selectedEquipementEtatEquipementDto.getIdEquipement());
				refEtatEquipementServiceImpl
						.delete(selectedEquipementEtatEquipementDto);
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

}
