/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.ReservationLieuBean.java] 
 * @author BELBRIK Oualid on : 19 02. 2014 17:47:10
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefReservationDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefReservationService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 23 mars. 2014 13:47:10
 */
@ManagedBean(name = "reservationMgrBean")
@RequestScoped
public class ReservationLieuMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 14:24:15
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
	@ManagedProperty(value = "#{refReservationServiceImpl}")
	private RefReservationService refReservationServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	private RefReservationDto refReservationDto;
	private ResourceBundle bundle;
	private ResourceBundle lBundle;
	private static final Log log = LogFactory
			.getLog(ReservationLieuMgrBean.class);
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
	@ManagedProperty(value = "#{lieuReservationCrudBean}")
	private LieuReservationCrudBean lieuReservationCrudBean;

	public ReservationLieuMgrBean() {
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
		lBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.LIEU_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		load();

	}

	/**
	 * [ReservationLieuBean.refReservationServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 13:50:32
	 * @return the refReservationServiceImpl
	 */
	public RefReservationService getRefReservationServiceImpl() {
		return refReservationServiceImpl;
	}

	/**
	 * [ReservationLieuBean.refReservationServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 13:50:32
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
				result = refReservationServiceImpl.findByIdLieu(_id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of Reservation is null");
				setListRefStructureDto(null);
				setListRefGroupeDto(null);
				setListRefIndividuDto(null);

			} else {

				listRefStructureDto = new ArrayList<RefReservationDto>();
				listRefIndividuDto = new ArrayList<RefReservationDto>();
				listRefGroupeDto = new ArrayList<RefReservationDto>();

				for (RefReservationDto currentRefReservationDto : result) {
					if (currentRefReservationDto.getIdStructure() != null) {
						listRefStructureDto.add(currentRefReservationDto);
					} else if (currentRefReservationDto.getIdIndividu() != null) {
						listRefIndividuDto.add(currentRefReservationDto);
					} else if (currentRefReservationDto.getIdGroupe() != null) {
						listRefGroupeDto.add(currentRefReservationDto);
					}

				}
				log.info("list of structure ReservationEquipement:"
						+ listRefStructureDto.size());
				log.info("list of individu ReservationEquipement:"
						+ listRefIndividuDto.size());
				log.info("list of groupe ReservationEquipement:"
						+ listRefGroupeDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}

	/**
	 * [ReservationLieuBean.listRefStructureDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 16:09:29
	 * @return the listRefStructureDto
	 */
	public List<RefReservationDto> getListRefStructureDto() {
		return listRefStructureDto;
	}

	/**
	 * [ReservationLieuBean.listRefStructureDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 16:09:29
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
	 * @author BELBRIK Oualid on :23 mars. 2014 17:51:32
	 * @return the listRefIndividuDto
	 */
	public List<RefReservationDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [ReservationLieuBean.listRefIndividuDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:51:32
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<RefReservationDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [ReservationLieuBean.listRefGroupeDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:51:32
	 * @return the listRefGroupeDto
	 */
	public List<RefReservationDto> getListRefGroupeDto() {
		return listRefGroupeDto;
	}

	/**
	 * [ReservationLieuBean.refStructureServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 11:18:37
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [ReservationLieuBean.refStructureServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 11:18:37
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
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:51:32
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refReservationDto != null)
				&& (refReservationDto.getIdStructure() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdLieu(_id);
				log.info("saving structure id = "
						+ refReservationDto.getIdStructure());
				// verify date(time) begin and date(time) end

				if (refReservationDto.getDateDebut().after(
						refReservationDto.getDateFin())
						|| refReservationDto.getHeureDebut().after(
								refReservationDto.getHeureFin())
						|| refReservationDto.getHeureDebut().equals(
								refReservationDto.getHeureFin())) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("date_debut_superieur_fin"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					refReservationServiceImpl.save(refReservationDto);
					load();
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					msg.setSummary(bundle.getString("msg_success") + ": "
							+ bundle.getString("msg_success_enregistrement"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} catch (Exception e) {
				log.info(e.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec") + ": "
						+ bundle.getString("enregistrement_existe"));
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
				log.info("deleting structure partenariat id = "
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refReservationDto != null)
				&& (refReservationDto.getIdGroupe() != null)) {
			try {
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdLieu(_id);
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
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refReservationDto != null)
				&& (refReservationDto.getIdIndividu() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdLieu(_id);
				log.info("saving Individu id = "
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
				log.info("deleting individu id = "
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refReservationDto != null)
				&& (refReservationDto.getIdLieu() != null)) {
			try {
				refReservationDto.setIdGroupe(null);
				refReservationDto.setIdStructure(null);
				refReservationDto.setIdIndividu(null);
				refReservationDto.setIdLieu(_id);
				log.info("saving Lieu partenariat id = "
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
	 * @author BELBRIK Oualid on : 23 mars. 2014 15:51:43
	 * @return the refReservationDto
	 */
	public RefReservationDto getRefReservationDto() {
		return refReservationDto;
	}

	/**
	 * [ReservationLieuBean.refReservationDto] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 15:51:43
	 * @param refReservationDto
	 *            the refReservationDto to set
	 */
	public void setRefReservationDto(RefReservationDto refReservationDto) {
		this.refReservationDto = refReservationDto;
	}

	/**
	 * onStructureCancel, Cancel modify the signature date and reference
	 * 
	 * @author BELDI Jamel on : 23 mars. 2014 15:51:43
	 * @param event
	 *            the RowEditEvent
	 */
	public void onSignatureCancel(RowEditEvent event) {

	}

	/**
	 * [ReservationEquipementMgrBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [ReservationEquipementMgrBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [ReservationEquipementMgrBean.idf] Getter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [ReservationEquipementMgrBean.idf] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
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
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [ReservationEquipementMgrBean.searchValue] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:03:32
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
	 * @author BELBRIK Oualid on : 23 mars. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [ReservationEquipementMgrBean.editMode] Setter
	 * 
	 * @author BELBRIK Oualid on : 23 mars. 2014 17:39:03
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
	 * [ReservationLieuMgrBean.lieuReservationCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:50:12
	 * @return the lieuReservationCrudBean
	 */
	public LieuReservationCrudBean getLieuReservationCrudBean() {
		return lieuReservationCrudBean;
	}

	/**
	 * [ReservationLieuMgrBean.lieuReservationCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:50:12
	 * @param lieuReservationCrudBean
	 *            the lieuReservationCrudBean to set
	 */
	public void setLieuReservationCrudBean(
			LieuReservationCrudBean lieuReservationCrudBean) {
		this.lieuReservationCrudBean = lieuReservationCrudBean;
	}

	/**
	 * [ReservationLieuMgrBean.crud] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:50:44
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
		lieuReservationCrudBean.setEditAllow(editAllow);
		lieuReservationCrudBean.setCreateAllow(createAllow);
		lieuReservationCrudBean.setDeleteAllow(deleteAllow);
	}

	public String back() {
		return "lieuReservation";
	}

	public void updateStructure(
			RefReservationDto selectedStructureReservationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedStructureReservationLieuDto != null)
				&& (selectedStructureReservationLieuDto.getIdStructure() != null)) {
			try {
				log.info("update structure reservation id = "
						+ selectedStructureReservationLieuDto.getIdStructure());
				refReservationServiceImpl
						.update(selectedStructureReservationLieuDto);
				load();
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_update"));
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

	public void save() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			log.info("Mgr Bean saveReservation");
			refReservationDto.setId(Integer.parseInt(idr));
			refReservationDto.setIdLieu(_id);

			refReservationServiceImpl.update(refReservationDto);
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

	/**
	 * [ReservationLieuMgrBean.idrev] Getter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  10:48:44
	 * @return the idrev
	 */
	public Integer getIdrev() {
		return idrev;
	}

	/**
	 * [ReservationLieuMgrBean.idrev] Setter 
	 * @author MAKERRI Sofiane on : 13 mai 2014  10:48:44
	 * @param idrev the idrev to set
	 */
	public void setIdrev(Integer idrev) {
		this.idrev = idrev;
	}
	
	

}
