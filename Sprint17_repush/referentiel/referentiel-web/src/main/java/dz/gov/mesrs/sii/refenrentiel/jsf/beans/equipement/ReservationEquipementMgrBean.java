/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.ReservationLieuBean.java] 
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
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefReservationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 17 mars. 2014 13:47:10
 */
@ManagedBean(name = "ReservationEquipementMgrBean")
@RequestScoped
public class ReservationEquipementMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [ReservationLieuBean.ReservationLieuBean()] Constructor
	 * 
	 * @author BELBRIK Oualid on : 2 janv. 2014 13:47:10
	 */
	private List<RefReservationDto> listRefStructureDto;
	private List<RefReservationDto> listRefIndividuDto;
	private List<RefReservationDto> listRefGroupeDto;
	private List<RefReservationDto> listRefLieuDto;
	@ManagedProperty(value = "#{refReservationServiceImpl}")
	private RefReservationService refReservationServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	private RefReservationDto refReservationDto;
	private ResourceBundle bundle;
	private ResourceBundle eBundle;
	private static final Log log = LogFactory
			.getLog(ReservationEquipementMgrBean.class);
	@ManagedProperty("#{param.idf}")
	private String idf;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty("#{param.idr}")
	private String idr;
	private Integer idrev;
	@ManagedProperty("#{param.objetr}")
	private String objetr;
	@ManagedProperty("#{param.lieur}")
	private String lieur;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;
	@ManagedProperty(value = "#{reservationEquipCrudBean}")
	private ReservationEquipCrudBean reservationEquipCrudBean;
	private List<SelectItem> listIndividuDto;
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	private boolean lancerRecherche;

	public ReservationEquipementMgrBean() {
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
		refReservationDto = new RefReservationDto();
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
	 * [ReservationLieuBean.refReservationServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 13:50:32
	 * @return the refReservationServiceImpl
	 */
	public RefReservationService getRefReservationServiceImpl() {
		return refReservationServiceImpl;
	}

	/**
	 * [ReservationLieuBean.refReservationServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 13:50:32
	 * @param refReservationServiceImpl
	 *            the refReservationServiceImpl to set
	 */
	public void setRefReservationServiceImpl(
			RefReservationService refReservationServiceImpl) {
		this.refReservationServiceImpl = refReservationServiceImpl;
	}

	public void load() {
		try {
			List<RefReservationDto> result = null;
			if (idf != null) {
				int _id = RefUtilConstant.strToInt(idf);
				if (_id == -1) {
					return;
				}
				result = refReservationServiceImpl.findByIdEquipement(_id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of Reservation is null");
				setListRefStructureDto(null);
				setListRefIndividuDto(null);
				setListRefGroupeDto(null);
				setListRefLieuDto(null);

			} else {

				listRefStructureDto = new ArrayList<RefReservationDto>();
				listRefIndividuDto = new ArrayList<RefReservationDto>();
				listRefGroupeDto = new ArrayList<RefReservationDto>();
				listRefLieuDto = new ArrayList<RefReservationDto>();

				for (RefReservationDto currentRefReservationDto : result) {
					if (currentRefReservationDto.getIdStructure() != null) {
						listRefStructureDto.add(currentRefReservationDto);
					} else if (currentRefReservationDto.getIdIndividu() != null) {
						listRefIndividuDto.add(currentRefReservationDto);
					} else if (currentRefReservationDto.getIdGroupe() != null) {
						listRefGroupeDto.add(currentRefReservationDto);
					} else if (currentRefReservationDto.getIdLieu() != null) {
						listRefLieuDto.add(currentRefReservationDto);
					}
				}
				log.info("list of structure ReservationEquipement:"
						+ listRefStructureDto.size());
				log.info("list of individu ReservationEquipement:"
						+ listRefIndividuDto.size());
				log.info("list of groupe ReservationEquipement:"
						+ listRefGroupeDto.size());
				log.info("list of lieu ReservationEquipement:"
						+ listRefLieuDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}

	/**
	 * [ReservationLieuBean.listRefLieuDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 16:09:29
	 * @return the listRefLieuDto
	 */
	public List<RefReservationDto> getListRefLieuDto() {
		return listRefLieuDto;
	}

	public void setListRefLieuDto(List<RefReservationDto> listRefLieuDto) {
		this.listRefLieuDto = listRefLieuDto;
	}

	/**
	 * [ReservationLieuBean.listRefStructureDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 16:09:29
	 * @return the listRefStructureDto
	 */
	public List<RefReservationDto> getListRefStructureDto() {
		return listRefStructureDto;
	}

	/**
	 * [ReservationLieuBean.listRefStructureDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 16:09:29
	 * @param listRefStructureDto
	 *            the listRefStructureDto to set
	 */
	public void setListRefStructureDto(
			List<RefReservationDto> listRefStructureDto) {
		this.listRefStructureDto = listRefStructureDto;
	}

	/**
	 * [ReservationLieuBean.listRefIndividuDto] Getter
	 * 
	 * @author BELBRIK Oualid on :17 mars. 2014 17:51:32
	 * @return the listRefIndividuDto
	 */
	public List<RefReservationDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [ReservationLieuBean.listRefIndividuDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:51:32
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<RefReservationDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [ReservationLieuBean.listRefGroupeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:51:32
	 * @return the listRefGroupeDto
	 */
	public List<RefReservationDto> getListRefGroupeDto() {
		return listRefGroupeDto;
	}

	/**
	 * [ReservationLieuBean.refStructureServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 11:18:37
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [ReservationLieuBean.refStructureServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 11:18:37
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [ReservationLieuBean.listRefGroupeDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:51:32
	 * @param listRefGroupeDto
	 *            the listRefGroupeDto to set
	 */
	public void setListRefGroupeDto(List<RefReservationDto> listRefGroupeDto) {
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
		if ((refReservationDto != null)
				&& (refReservationDto.getIdStructure() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdLieu(null);
				refReservationDto.setIdEquipement(_id);
				log.info("saving structure id = "
						+ refReservationDto.getIdStructure());

				refReservationServiceImpl.save(refReservationDto);
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
			RefReservationDto selectedStructureReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedStructureReservationLieuDto != null)
				&& (selectedStructureReservationLieuDto.getIdStructure() != null)) {
			try {
				log.info("deleting structure id = "
						+ selectedStructureReservationLieuDto.getIdStructure());
				refReservationServiceImpl
						.delete(selectedStructureReservationLieuDto);
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
		if ((refReservationDto != null)
				&& (refReservationDto.getIdGroupe() != null)) {
			try {
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdLieu(null);
				refReservationDto.setIdEquipement(_id);
				log.info("saving groupe id = "
						+ refReservationDto.getIdGroupe());
				refReservationServiceImpl.save(refReservationDto);
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

	public void deleteGroupe(RefReservationDto selectedGroupeReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedGroupeReservationLieuDto != null)
				&& (selectedGroupeReservationLieuDto.getIdGroupe() != null)) {
			try {
				log.info("deleting groupe id = "
						+ selectedGroupeReservationLieuDto.getIdGroupe());
				refReservationServiceImpl
						.delete(selectedGroupeReservationLieuDto);
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
		if ((refReservationDto != null)
				&& (refReservationDto.getIdIndividu() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdLieu(null);
				refReservationDto.setIdEquipement(_id);
				log.info("saving Individu partenariat id = "
						+ refReservationDto.getIdIndividu());

				refReservationServiceImpl.save(refReservationDto);
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
			RefReservationDto selectedIndividuReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedIndividuReservationLieuDto != null)
				&& (selectedIndividuReservationLieuDto.getIdIndividu() != null)) {
			try {
				log.info("deleting individu partenariat id = "
						+ selectedIndividuReservationLieuDto.getIdIndividu());
				refReservationServiceImpl
						.delete(selectedIndividuReservationLieuDto);
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
		if ((refReservationDto != null)
				&& (refReservationDto.getIdLieu() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdEquipement(_id);
				log.info("saving Lieu id = "
						+ refReservationDto.getIdIndividu());

				refReservationServiceImpl.save(refReservationDto);
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

	public void deleteLieu(RefReservationDto selectedLieuReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedLieuReservationLieuDto != null)
				&& (selectedLieuReservationLieuDto.getIdLieu() != null)) {
			try {
				log.info("deleting individu id = "
						+ selectedLieuReservationLieuDto.getIdIndividu());
				refReservationServiceImpl
						.delete(selectedLieuReservationLieuDto);
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
	 * Validate reservation equipement
	 * 
	 * @author BELBRIK Oualid on : 19 mars. 2014 15:51:43
	 * 
	 */

	public void validateReservation(
			RefReservationDto selectedLieuReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ eBundle.getString("equipement_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refReservationDto != null)
				&& (refReservationDto.getIdLieu() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdEquipement(_id);
				log.info("saving Lieu id = "
						+ refReservationDto.getIdIndividu());

				refReservationServiceImpl.save(refReservationDto);
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
	 * [ReservationLieuBean.refReservationDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 15:51:43
	 * @return the refReservationDto
	 */
	public RefReservationDto getRefReservationDto() {
		return refReservationDto;
	}

	/**
	 * [ReservationLieuBean.refReservationDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 15:51:43
	 * @param refReservationDto
	 *            the refReservationDto to set
	 */
	public void setRefReservationDto(RefReservationDto refReservationDto) {
		this.refReservationDto = refReservationDto;
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
	 * [ReservationEquipementMgrBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ReservationEquipementMgrBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ReservationEquipementMgrBean.idf] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [ReservationEquipementMgrBean.idf] Setter
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
	 * [ReservationEquipementMgrBean.searchValue] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ReservationEquipementMgrBean.searchValue] Setter
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
	 * [ReservationEquipementMgrBean.editMode] Getter
	 * 
	 * @author BELBRIK Oualid on : 17 mars. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [ReservationEquipementMgrBean.editMode] Setter
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
	 * [ReservationEquipementMgrBean.idr] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 09:52:28
	 * @return the idr
	 */
	public String getIdr() {
		return idr;
	}

	/**
	 * [ReservationEquipementMgrBean.idr] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 09:52:28
	 * @param idr
	 *            the idr to set
	 */
	public void setIdr(String idr) {
		if ((idr != null) && (idr.equals("null"))) {
			this.idr = null;

		} else {
			this.idr = idr;
		}
		int _id = RefUtilConstant.strToInt(idr);
		if(_id != -1) {
		     setIdrev(_id);
		}
	}

	/**
	 * [ReservationEquipementMgrBean.objetr] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 10:41:03
	 * @return the objetr
	 */
	public String getObjetr() {
		return objetr;
	}

	/**
	 * [ReservationEquipementMgrBean.objetr] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 10:41:03
	 * @param objetr
	 *            the objetr to set
	 */
	public void setObjetr(String objetr) {
		if ((objetr != null) && (objetr.equals("null"))) {
			this.objetr = null;

		} else {
			this.objetr = objetr;
		}
	}

	/**
	 * [ReservationEquipementMgrBean.lieur] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 10:41:03
	 * @return the lieur
	 */
	public String getLieur() {
		return lieur;
	}

	/**
	 * [ReservationEquipementMgrBean.lieur] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 10:41:03
	 * @param lieur
	 *            the lieur to set
	 */
	public void setLieur(String lieur) {
		if ((lieur != null) && (lieur.equals("null"))) {
			this.lieur = null;

		} else {
			this.lieur = lieur;
		}
	}

	/**
	 * [ReservationEquipementMgrBean.entitiname] Getter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 12:05:41
	 * @return the entitiname
	 */
	public String getEntitiname() {
		return entitiname;
	}

	/**
	 * [ReservationEquipementMgrBean.entitiname] Setter
	 * 
	 * @author BELBRIK Oualid on : 22 mars 2014 12:05:41
	 * @param entitiname
	 *            the entitiname to set
	 */
	public void setEntitiname(String entitiname) {
		if ((entitiname != null) && (entitiname.equals("null"))) {
			this.entitiname = null;

		} else {
			this.entitiname = entitiname;
		}
	}

	/**
	 * [ReservationEquipementMgrBean.reservationEquipCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 18:01:08
	 * @return the reservationEquipCrudBean
	 */
	public ReservationEquipCrudBean getReservationEquipCrudBean() {
		return reservationEquipCrudBean;
	}

	/**
	 * [ReservationEquipementMgrBean.reservationEquipCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 18:01:08
	 * @param reservationEquipCrudBean
	 *            the reservationEquipCrudBean to set
	 */
	public void setReservationEquipCrudBean(
			ReservationEquipCrudBean reservationEquipCrudBean) {
		this.reservationEquipCrudBean = reservationEquipCrudBean;
	}

	/**
	 * [ReservationEquipementMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 18:01:35
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		reservationEquipCrudBean.setEditAllow(editAllow);
		reservationEquipCrudBean.setCreateAllow(createAllow);
		reservationEquipCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [ReservationEquipementMgrBean.idrev] Getter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  09:19:37
	 * @return the idrev
	 */
	public Integer getIdrev() {
		return idrev;
	}

	/**
	 * [ReservationEquipementMgrBean.idrev] Setter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  09:19:37
	 * @param idrev the idrev to set
	 */
	public void setIdrev(Integer idrev) {
		this.idrev = idrev;
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


	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}


	public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}


	public boolean isLancerRecherche() {
		return lancerRecherche;
	}


	public void setLancerRecherche(boolean lancerRecherche) {
		this.lancerRecherche = lancerRecherche;
	}

	
	public void activerRecherche() {
		setLancerRecherche(true);
		getListIndividuDto();
	}
	

}
