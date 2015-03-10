/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.OccupationLieuBean.java] 
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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefEquipementService;
import dz.gov.mesrs.sii.referentiel.business.service.RefOccupationLieuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELBRIK Oualid on : 19 f�vr. 2014 13:47:10
 */
@ManagedBean(name = "occupationLieuMgrBean")
@RequestScoped
public class OccupationLieuMgrBean implements Serializable {

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
	private List<RefOccupationLieuDto> listRefEquipementDto;
	private List<RefOccupationLieuDto> listRefIndividuDto;
	private List<RefOccupationLieuDto> listRefGroupeDto;
	@ManagedProperty(value = "#{refOccupationLieuServiceImpl}")
	private RefOccupationLieuService refOccupationLieuServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	@ManagedProperty(value = "#{refEquipementServiceImpl}")
	private RefEquipementService refEquipementServiceImpl;
	private RefOccupationLieuDto refOccupationLieuDto;
	private ResourceBundle bundle;
	private ResourceBundle lBundle;
	private static final Log log = LogFactory
			.getLog(OccupationLieuMgrBean.class);
	@ManagedProperty("#{param.idf}")
	private String idf;
	@ManagedProperty("#{param.idr}")
	private String idr;
	@ManagedProperty("#{param.entitiname}")
	private String entitiname;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.editMode}")
	private boolean editMode;
	@ManagedProperty(value = "#{lieuOccupationCrudBean}")
	private LieuOccupationCrudBean lieuOccupationCrudBean;
	@ManagedProperty(value = "#{lieuEquipementCrudBean}")
	private LieuEquipementCrudBean lieuEquipementCrudBean;

	public OccupationLieuMgrBean() {
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
		lBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.LIEU_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {
		load();

	}

	/**
	 * [OccupationLieuMgrBean.lieuOccupationCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:09:28
	 * @return the lieuOccupationCrudBean
	 */
	public LieuOccupationCrudBean getLieuOccupationCrudBean() {
		return lieuOccupationCrudBean;
	}

	/**
	 * [OccupationLieuMgrBean.lieuOccupationCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:09:29
	 * @param lieuOccupationCrudBean
	 *            the lieuOccupationCrudBean to set
	 */
	public void setLieuOccupationCrudBean(
			LieuOccupationCrudBean lieuOccupationCrudBean) {
		this.lieuOccupationCrudBean = lieuOccupationCrudBean;
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

	public void load() {
		try {
			List<RefOccupationLieuDto> result = null;
			if (idf != null) {
				int _id = RefUtilConstant.strToInt(idf);
				if (_id == -1) {
					return;
				}
				result = refOccupationLieuServiceImpl.findByIdLieu(_id);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of OccupationLieu is null");
				setListRefStructureDto(null);
				setListRefEquipementDto(null);
				setListRefIndividuDto(null);
				setListRefGroupeDto(null);

			} else {

				listRefStructureDto = new ArrayList<RefOccupationLieuDto>();
				listRefEquipementDto = new ArrayList<RefOccupationLieuDto>();
				listRefIndividuDto = new ArrayList<RefOccupationLieuDto>();
				listRefGroupeDto = new ArrayList<RefOccupationLieuDto>();

				for (RefOccupationLieuDto currentRefOccupationLieuDto : result) {
					if (currentRefOccupationLieuDto.getIdStructure() != null) {
						listRefStructureDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdIndividu() != null) {
						listRefIndividuDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdGroupe() != null) {
						listRefGroupeDto.add(currentRefOccupationLieuDto);
					} else if (currentRefOccupationLieuDto.getIdEquipement() != null) {
						listRefEquipementDto.add(currentRefOccupationLieuDto);
					}
				}
				log.info("list of structure OccupationLieu:"
						+ listRefStructureDto.size());
				log.info("list of individu OccupationLieu:"
						+ listRefIndividuDto.size());
				log.info("list of groupe OccupationLieu:"
						+ listRefGroupeDto.size());
				log.info("list of equipement OccupationLieu:"
						+ listRefEquipementDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

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
	 * [OccupationLieuBean.listRefEquipementDto] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 mars. 2014 13:09:29
	 * @return the listRefEquipementDto
	 */
	public List<RefOccupationLieuDto> getListRefEquipementDto() {
		return listRefEquipementDto;
	}

	/**
	 * [OccupationLieuBean.listRefEquipementDto] Setter
	 * 
	 * @author BELBRIK Oualid on :24 mars. 2014 16:09:29
	 * @param listRefEquipementDto
	 *            the listRefEquipementDto to set
	 */
	public void setListRefEquipementDto(
			List<RefOccupationLieuDto> listRefEquipementDto) {
		this.listRefEquipementDto = listRefEquipementDto;
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
	 * [OccupationLieuBean.refEquipementServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 11:18:37
	 * @param refEquipementServiceImpl
	 *            the refEquipementServiceImpl to set
	 */
	public void setRefEquipementServiceImpl(
			RefEquipementService refEquipementServiceImpl) {
		this.refEquipementServiceImpl = refEquipementServiceImpl;
	}

	/**
	 * [OccupationLieuBean.refEquipementServiceImpl] Getter
	 * 
	 * @author BELBRIK Oualid on : 24 mars. 2014 11:18:37
	 * @return the refEquipementServiceImpl
	 */
	public RefEquipementService getRefEquipementServiceImpl() {
		return refEquipementServiceImpl;
	}

	/**
	 * [OccupationLieuBean.refStructureServiceImpl] Setter
	 * 
	 * @author BELBRIK Oualid on : 24 mars. 2014 11:18:37
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdStructure() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdEquipement(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdLieu(_id);
				log.info("saving structure id = "
						+ refOccupationLieuDto.getIdStructure());
				// verify date begin and date end
				if ((refOccupationLieuDto.getIdTypeOccupation() == 1601)
						&& (refOccupationLieuDto.getDateFin() == null)) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle
							.getString("provisoir_date_fin_obligatoire"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					if ((refOccupationLieuDto.getDateFin() != null)
							&& (refOccupationLieuDto.getDateDebut()
									.after(refOccupationLieuDto.getDateFin()))) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle
								.getString("date_debut_superieur_fin"));
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						refOccupationLieuServiceImpl.save(refOccupationLieuDto);
						load();
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundle.getString("msg_success")
								+ ": "
								+ bundle.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
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
			RefOccupationLieuDto selectedStructureOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedStructureOccupationLieuDto != null)
				&& (selectedStructureOccupationLieuDto.getIdStructure() != null)) {
			try {
				log.info("deleting structure id = "
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdGroupe() != null)) {
			try {
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdEquipement(null);
				refOccupationLieuDto.setIdLieu(_id);
				log.info("saving groupe id = "
						+ refOccupationLieuDto.getIdGroupe());
				// verify date begin and date end
				if ((refOccupationLieuDto.getIdTypeOccupation() == 1601)
						&& (refOccupationLieuDto.getDateFin() == null)) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle
							.getString("provisoir_date_fin_obligatoire"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					if ((refOccupationLieuDto.getDateFin() != null)
							&& (refOccupationLieuDto.getDateDebut()
									.after(refOccupationLieuDto.getDateFin()))) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle
								.getString("date_debut_superieur_fin"));
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						refOccupationLieuServiceImpl.save(refOccupationLieuDto);
						load();
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundle.getString("msg_success")
								+ ": "
								+ bundle.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
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

	public void deleteGroupe(
			RefOccupationLieuDto selectedGroupeOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedGroupeOccupationLieuDto != null)
				&& (selectedGroupeOccupationLieuDto.getIdGroupe() != null)) {
			try {
				log.info("deleting groupe id = "
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
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdIndividu() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdEquipement(null);
				refOccupationLieuDto.setIdLieu(_id);
				log.info("saving Individu id = "
						+ refOccupationLieuDto.getIdIndividu());
				// verify date begin and date end
				if ((refOccupationLieuDto.getIdTypeOccupation() == 1601)
						&& (refOccupationLieuDto.getDateFin() == null)) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle
							.getString("provisoir_date_fin_obligatoire"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					if ((refOccupationLieuDto.getDateFin() != null)
							&& (refOccupationLieuDto.getDateDebut()
									.after(refOccupationLieuDto.getDateFin()))) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle
								.getString("date_debut_superieur_fin"));
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						refOccupationLieuServiceImpl.save(refOccupationLieuDto);
						load();
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundle.getString("msg_success")
								+ ": "
								+ bundle.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
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

	public void deleteIndividu(
			RefOccupationLieuDto selectedIndividuOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedIndividuOccupationLieuDto != null)
				&& (selectedIndividuOccupationLieuDto.getIdIndividu() != null)) {
			try {
				log.info("deleting individu id = "
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

	public void addEquipement() {
		FacesMessage msg = new FacesMessage();
		int _id = RefUtilConstant.strToInt(idf);
		if (_id == -1) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refOccupationLieuDto != null)
				&& (refOccupationLieuDto.getIdEquipement() != null)) {
			try {
				refOccupationLieuDto.setIdGroupe(null);
				refOccupationLieuDto.setIdStructure(null);
				refOccupationLieuDto.setIdIndividu(null);
				refOccupationLieuDto.setIdLieu(_id);
				log.info("saving Equipement id = "
						+ refOccupationLieuDto.getIdEquipement());
				// verify date begin and date end
				if ((refOccupationLieuDto.getIdTypeOccupation() == 1601)
						&& (refOccupationLieuDto.getDateFin() == null)) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle
							.getString("provisoir_date_fin_obligatoire"));
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} else {
					if ((refOccupationLieuDto.getDateFin() != null)
							&& (refOccupationLieuDto.getDateDebut()
									.after(refOccupationLieuDto.getDateFin()))) {
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						msg.setSummary(bundle
								.getString("date_debut_superieur_fin"));
						FacesContext.getCurrentInstance().addMessage(null, msg);

					} else {
						refOccupationLieuServiceImpl.save(refOccupationLieuDto);
						load();
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						msg.setSummary(bundle.getString("msg_success")
								+ ": "
								+ bundle.getString("msg_success_enregistrement"));
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
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

	public void deleteEquipement(
			RefOccupationLieuDto selectedEquipementOccupationLieuDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedEquipementOccupationLieuDto != null)
				&& (selectedEquipementOccupationLieuDto.getIdEquipement() != null)) {
			try {
				log.info("deleting Equipement id = "
						+ selectedEquipementOccupationLieuDto.getIdEquipement());
				refOccupationLieuServiceImpl
						.delete(selectedEquipementOccupationLieuDto);
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
	 * [OccupationLieuMgrBean.bundle] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [OccupationLieuMgrBean.bundle] Setter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [OccupationLieuMgrBean.idf] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the idf
	 */
	public String getIdf() {
		return idf;
	}

	/**
	 * [OccupationLieuMgrBean.idf] Setter
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
	 * [OccupationLieuMgrBean.searchValue] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [OccupationLieuMgrBean.searchValue] Setter
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
	 * [OccupationLieuMgrBean.editMode] Getter
	 * 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [OccupationLieuMgrBean.editMode] Setter
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

	/**
	 * [OccupationLieuMgrBean.lieuEquipementCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:16:54
	 * @return the lieuEquipementCrudBean
	 */
	public LieuEquipementCrudBean getLieuEquipementCrudBean() {
		return lieuEquipementCrudBean;
	}

	/**
	 * [OccupationLieuMgrBean.lieuEquipementCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:16:54
	 * @param lieuEquipementCrudBean
	 *            the lieuEquipementCrudBean to set
	 */
	public void setLieuEquipementCrudBean(
			LieuEquipementCrudBean lieuEquipementCrudBean) {
		this.lieuEquipementCrudBean = lieuEquipementCrudBean;
	}

	/**
	 * [OccupationLieuMgrBean.occupationCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:09:44
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void occupationCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		lieuOccupationCrudBean.setEditAllow(editAllow);
		lieuOccupationCrudBean.setCreateAllow(createAllow);
		lieuOccupationCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [OccupationLieuMgrBean.equipementCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 mars 2014 10:17:32
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void equipementCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		lieuEquipementCrudBean.setEditAllow(editAllow);
		lieuEquipementCrudBean.setCreateAllow(createAllow);
		lieuEquipementCrudBean.setDeleteAllow(deleteAllow);
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
					+ lBundle.getString("lieu_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			log.info("Mgr Bean saveOccupation");
			refOccupationLieuDto.setId(Integer.parseInt(idr));
			refOccupationLieuDto.setIdLieu(_id);

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
}
