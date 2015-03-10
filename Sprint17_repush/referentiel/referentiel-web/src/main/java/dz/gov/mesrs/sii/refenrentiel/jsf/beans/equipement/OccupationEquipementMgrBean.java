/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.OccupationLieuBean.java] 
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
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 19 f�vr. 2014 13:47:10
 */
@ManagedBean(name = "OccupationEquipementMgrBean")
@RequestScoped
public class OccupationEquipementMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [OccupationLieuBean.OccupationLieuBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 2 janv. 2014 13:47:10
	 */
	private List<RefOccupationLieuDto> listRefStructureDto;
	private List<RefOccupationLieuDto> listRefIndividuDto;
	private List<RefOccupationLieuDto> listRefGroupeDto;
	private List<RefOccupationLieuDto> listRefLieuDto;
	@ManagedProperty(value = "#{refOccupationLieuServiceImpl}")
	private RefOccupationLieuService refOccupationLieuServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	private RefOccupationLieuDto refOccupationLieuDto;
	private ResourceBundle bundle;
	private ResourceBundle eBundle;
	private static final Log log = LogFactory
			.getLog(OccupationEquipementMgrBean.class);
	@ManagedProperty("#{param.idf}")
	private String idf;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty(value = "#{equipementCrudBean}")
	private EquipementCrudBean equipementCrudBean;
	@ManagedProperty("#{param.idr}")
	private String idr;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;
	private List<SelectItem> listIndividuDto;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	private boolean lancerRecherche;
	

	public OccupationEquipementMgrBean() {
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
		refOccupationLieuDto = new RefOccupationLieuDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		eBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.EQUIPEMENT_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		load();
		setLancerRecherche(false);

	}

	/**
	 * [OccupationLieuBean.refOccupationLieuServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 13:50:32
	 * @return the refOccupationLieuServiceImpl
	 */
	public RefOccupationLieuService getRefOccupationLieuServiceImpl() {
		return refOccupationLieuServiceImpl;
	}

	/**
	 * [OccupationLieuBean.refOccupationLieuServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 13:50:32
	 * @param refOccupationLieuServiceImpl
	 *            the refOccupationLieuServiceImpl to set
	 */
	public void setRefOccupationLieuServiceImpl(
			RefOccupationLieuService refOccupationLieuServiceImpl) {
		this.refOccupationLieuServiceImpl = refOccupationLieuServiceImpl;
	}

	/**
	 * [OccupationEquipementMgrBean.equipementBckBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 16:10:24
	 * @return the equipementBckBean
	 */
	public EquipementCrudBean getEquipementCrudBean() {
		return equipementCrudBean;
	}

	/**
	 * [OccupationEquipementMgrBean.equipementBckBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 16:10:24
	 * @param equipementBckBean
	 *            the equipementBckBean to set
	 */
	public void setEquipementCrudBean(EquipementCrudBean equipementCrudBean) {
		this.equipementCrudBean = equipementCrudBean;
	}

	/**
	 * [OccupationEquipementMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 16:10:27
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		equipementCrudBean.setEditAllow(editAllow);
		equipementCrudBean.setCreateAllow(createAllow);
		equipementCrudBean.setDeleteAllow(deleteAllow);
	}

	public void load() {
		try {
			List<RefOccupationLieuDto> result = null;
			if (idf != null) {
				int _id = RefUtilConstant.strToInt(idf);
				if (_id == -1) {
					return;
				}
				result = refOccupationLieuServiceImpl.findByIdEquipement(_id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of Occupation is null");
				setListRefStructureDto(null);
				setListRefIndividuDto(null);
				setListRefGroupeDto(null);
				setListRefLieuDto(null);

			} else {

				listRefStructureDto = new ArrayList<RefOccupationLieuDto>();
				listRefIndividuDto = new ArrayList<RefOccupationLieuDto>();
				listRefGroupeDto = new ArrayList<RefOccupationLieuDto>();
				listRefLieuDto = new ArrayList<RefOccupationLieuDto>();

				for (RefOccupationLieuDto currentRefOccupationLieuDto : result) {
					if (currentRefOccupationLieuDto.getIdStructure() != null) {
						listRefStructureDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdIndividu() != null) {
						listRefIndividuDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdGroupe() != null) {
						listRefGroupeDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdLieu() != null) {
						listRefLieuDto.add(currentRefOccupationLieuDto);
					}
				}
				log.info("list of structure OccupationLieu:"
						+ listRefStructureDto.size());
				log.info("list of individu OccupationLieu:"
						+ listRefIndividuDto.size());
				log.info("list of groupe OccupationLieu:"
						+ listRefGroupeDto.size());
				log.info("list of lieu OccupationLieu:" + listRefLieuDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}

	/**
	 * [OccupationLieuBean.listRefLieuDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 16:09:29
	 * @return the listRefLieuDto
	 */
	public List<RefOccupationLieuDto> getListRefLieuDto() {
		return listRefLieuDto;
	}

	public void setListRefLieuDto(List<RefOccupationLieuDto> listRefLieuDto) {
		this.listRefLieuDto = listRefLieuDto;
	}

	/**
	 * [OccupationLieuBean.listRefStructureDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 16:09:29
	 * @return the listRefStructureDto
	 */
	public List<RefOccupationLieuDto> getListRefStructureDto() {
		return listRefStructureDto;
	}

	/**
	 * [OccupationLieuBean.listRefStructureDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 16:09:29
	 * @param listRefStructureDto
	 *            the listRefStructureDto to set
	 */
	public void setListRefStructureDto(
			List<RefOccupationLieuDto> listRefStructureDto) {
		this.listRefStructureDto = listRefStructureDto;
	}

	/**
	 * [OccupationLieuBean.listRefIndividuDto] Getter
	 * 
	 * @author BELBRIK Oualid on :19 f�vr. 2014 17:51:32
	 * @return the listRefIndividuDto
	 */
	public List<RefOccupationLieuDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [OccupationLieuBean.listRefIndividuDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:51:32
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(
			List<RefOccupationLieuDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [OccupationLieuBean.listRefGroupeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:51:32
	 * @return the listRefGroupeDto
	 */
	public List<RefOccupationLieuDto> getListRefGroupeDto() {
		return listRefGroupeDto;
	}

	/**
	 * [OccupationLieuBean.refStructureServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 11:18:37
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [OccupationLieuBean.refStructureServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 11:18:37
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [OccupationLieuBean.listRefGroupeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:51:32
	 * @param listRefGroupeDto
	 *            the listRefGroupeDto to set
	 */
	public void setListRefGroupeDto(List<RefOccupationLieuDto> listRefGroupeDto) {
		this.listRefGroupeDto = listRefGroupeDto;
	}

	public void addStructure() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdStructure() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdLieu(null);
				refOccupationLieuDto.setIdEquipement(_id);
				log.info("saving structure partenariat id = "
						+ refOccupationLieuDto.getIdStructure());

				refOccupationLieuServiceImpl.save(refOccupationLieuDto);
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

	public void deleteStructure(
			RefOccupationLieuDto selectedStructureOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedStructureOccupationLieuDto != null)
				&& (selectedStructureOccupationLieuDto.getIdStructure() != null)) {
			try {
				log.info("deleting structure partenariat id = "
						+ selectedStructureOccupationLieuDto.getIdStructure());
				refOccupationLieuServiceImpl
						.delete(selectedStructureOccupationLieuDto);
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

	public void addGroupe() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdGroupe() != null)) {
			try {
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdLieu(null);
				refOccupationLieuDto.setIdEquipement(_id);
				log.info("saving groupe partenariat id = "
						+ refOccupationLieuDto.getIdGroupe());
				refOccupationLieuServiceImpl.save(refOccupationLieuDto);
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

	public void deleteGroupe(
			RefOccupationLieuDto selectedGroupeOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedGroupeOccupationLieuDto != null)
				&& (selectedGroupeOccupationLieuDto.getIdGroupe() != null)) {
			try {
				log.info("deleting groupe partenariat id = "
						+ selectedGroupeOccupationLieuDto.getIdGroupe());
				refOccupationLieuServiceImpl
						.delete(selectedGroupeOccupationLieuDto);
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

	public void addIndividu() {
		FacesMessage msg = new FacesMessage();
		setLancerRecherche(false);
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdIndividu() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdLieu(null);
				refOccupationLieuDto.setIdEquipement(_id);
				log.info("saving Individu partenariat id = "
						+ refOccupationLieuDto.getIdIndividu());

				refOccupationLieuServiceImpl.save(refOccupationLieuDto);
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

	public void deleteIndividu(
			RefOccupationLieuDto selectedIndividuOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedIndividuOccupationLieuDto != null)
				&& (selectedIndividuOccupationLieuDto.getIdIndividu() != null)) {
			try {
				log.info("deleting individu partenariat id = "
						+ selectedIndividuOccupationLieuDto.getIdIndividu());
				refOccupationLieuServiceImpl
						.delete(selectedIndividuOccupationLieuDto);
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

	public void addLieu() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdLieu() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdEquipement(_id);
				log.info("saving Lieu partenariat id = "
						+ refOccupationLieuDto.getIdIndividu());

				refOccupationLieuServiceImpl.save(refOccupationLieuDto);
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

	public void deleteLieu(RefOccupationLieuDto selectedLieuOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedLieuOccupationLieuDto != null)
				&& (selectedLieuOccupationLieuDto.getIdLieu() != null)) {
			try {
				log.info("deleting individu partenariat id = "
						+ selectedLieuOccupationLieuDto.getIdIndividu());
				refOccupationLieuServiceImpl
						.delete(selectedLieuOccupationLieuDto);
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
	 * [OccupationLieuBean.refOccupationLieuDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 15:51:43
	 * @return the refOccupationLieuDto
	 */
	public RefOccupationLieuDto getRefOccupationLieuDto() {
		return refOccupationLieuDto;
	}

	/**
	 * [OccupationLieuBean.refOccupationLieuDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 15:51:43
	 * @param refOccupationLieuDto
	 *            the refOccupationLieuDto to set
	 */
	public void setRefOccupationLieuDto(
			RefOccupationLieuDto refOccupationLieuDto) {
		this.refOccupationLieuDto = refOccupationLieuDto;
	}

	/**
	 * onStructureCancel, Cancel modify the signature date and reference
	 * 
	 * @author BELDI Jamel on : 19 f�vr. 2014 15:51:43
	 * @param event
	 *            the RowEditEvent
	 */
	public void onSignatureCancel(RowEditEvent event) {

	}

	/**
	 * [OccupationEquipementMgrBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [OccupationEquipementMgrBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [OccupationEquipementMgrBean.idf] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [OccupationEquipementMgrBean.idf] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
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
	 * [OccupationEquipementMgrBean.searchValue] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [OccupationEquipementMgrBean.searchValue] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
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
	 * [OccupationEquipementMgrBean.editMode] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [OccupationEquipementMgrBean.editMode] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:39:03
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

	public String getIdr() {
		return idr;
	}

	public void setIdr(String idr) {
		if ((idr != null) && (idr.equals("null"))) {
			this.idr = null;

		} else {
			this.idr = idr;
		}
	}

	public String getEntitiname() {
		return entitiname;
	}

	public void setEntitiname(String entitiname) {
		if ((entitiname != null) && (entitiname.equals("null"))) {
			this.entitiname = null;

		} else {
			this.entitiname = entitiname;
		}
	}

	public void save() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			log.info("Mgr Bean saveAffectation");
			refOccupationLieuDto.setId(Integer.parseInt(idr));
			refOccupationLieuDto.setIdEquipement(_id);
			refOccupationLieuServiceImpl.update(refOccupationLieuDto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
		}
	}
	
	public List<SelectItem> getListIndividuDto() {
		if (lancerRecherche==true){
		if (listIndividuDto == null || listIndividuDto.isEmpty()) {
			listIndividuDto = new ArrayList<SelectItem>();
			List<RefIndividuDto> list = refIndividuServiceImpl.findAll();
			for (RefIndividuDto refIndividuDto : list) {
				SelectItem selectItem = new SelectItem(
						refIndividuDto.getId(),
						refIndividuDto.getNomLatin()+" "+refIndividuDto.getPrenomLatin());
				listIndividuDto.add(selectItem);
			}

		}
		}
		return listIndividuDto;
	}

	public void setListIndividuDto(List<SelectItem> listIndividuDto) {
		this.listIndividuDto = listIndividuDto;
	}

	/**
	 * [OccupationEquipementMgrBean.refIndividuServiceImpl] Getter 
	 * @author BELBRIK Oualid on : 30 nov. 2014  15:47:36
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [OccupationEquipementMgrBean.refIndividuServiceImpl] Setter 
	 * @author BELBRIK Oualid on : 30 nov. 2014  15:47:36
	 * @param refIndividuServiceImpl the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [OccupationEquipementMgrBean.lancerRecherche] Getter 
	 * @author BELBRIK Oualid on : 30 nov. 2014  16:24:33
	 * @return the lancerRecherche
	 */
	public boolean isLancerRecherche() {
		return lancerRecherche;
	}

	/**
	 * [OccupationEquipementMgrBean.lancerRecherche] Setter 
	 * @author BELBRIK Oualid on : 30 nov. 2014  16:24:33
	 * @param lancerRecherche the lancerRecherche to set
	 */
	public void setLancerRecherche(boolean lancerRecherche) {
		this.lancerRecherche = lancerRecherche;
	}

	
	public void activerRecherche() {
		setLancerRecherche(true);
		getListIndividuDto();
	}
	
	
}
