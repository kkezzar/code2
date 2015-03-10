/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.PartenaireBean.java] 
 * @author MAKERRI Sofiane on : 2 janv. 2014  13:47:10
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.RowEditEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefPartenaireService;
import dz.gov.mesrs.sii.referentiel.business.service.RefStructureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 2 janv. 2014 13:47:10
 */
@ManagedBean(name = "partenaireMgrBean")
@ViewScoped
public class PartenaireMgrBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 14:24:15
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [PartenaireBean.PartenaireBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 13:47:10
	 */
	private List<RefPartenaireDto> listRefStructureDto;
	private List<RefPartenaireDto> listRefIndividuDto;
	private List<RefPartenaireDto> listRefGroupeDto;
	@ManagedProperty(value = "#{refPartenaireServiceImpl}")
	private RefPartenaireService refPartenaireServiceImpl;
	@ManagedProperty(value = "#{refStructureServiceImpl}")
	private RefStructureService refStructureServiceImpl;
	private RefPartenaireDto refPartenaireDto;
	private ResourceBundle bundle;
	private ResourceBundle cBundle;
	private static final Log log = LogFactory.getLog(PartenaireMgrBean.class);
	/*
	 * @ManagedProperty("#{param.idf}") private String idf;
	 */
	private String id;
	private Integer idContrat;
	private String searchValue;
	private boolean editMode;
	@ManagedProperty(value = "#{partenaireCrudBean}")
	private PartenaireCrudBean partenaireCrudBean;
	@ManagedProperty(value = "#{signatureCrudBean}")
	private SignatureCrudBean signatureCrudBean;

	public PartenaireMgrBean() {
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
			this.id = id.toString();
			this.idContrat = id;
		}
		log.info("var request*******************************************editMode: "
				+ b + " idc:" + id);
		refPartenaireDto = new RefPartenaireDto();
		bundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		cBundle = context.getApplication().getResourceBundle(context,
				RefUtilConstant.CONTRAT_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		load();

	}

	/**
	 * [PartenaireBean.refPartenaireServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 13:50:32
	 * @return the refPartenaireServiceImpl
	 */
	public RefPartenaireService getRefPartenaireServiceImpl() {
		return refPartenaireServiceImpl;
	}

	/**
	 * [PartenaireBean.refPartenaireServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 13:50:32
	 * @param refPartenaireServiceImpl
	 *            the refPartenaireServiceImpl to set
	 */
	public void setRefPartenaireServiceImpl(
			RefPartenaireService refPartenaireServiceImpl) {
		this.refPartenaireServiceImpl = refPartenaireServiceImpl;
	}

	public void load() {
		try {
			List<RefPartenaireDto> result = null;
			if (idContrat != null) {
				result = refPartenaireServiceImpl.findByIdContrat(idContrat);
			}
			if (result == null || result.isEmpty()) {
				log.info("list of partenaire is null");

			} else {

				listRefStructureDto = new ArrayList<RefPartenaireDto>();
				listRefIndividuDto = new ArrayList<RefPartenaireDto>();
				listRefGroupeDto = new ArrayList<RefPartenaireDto>();

				for (RefPartenaireDto currentRefPartenaireDto : result) {
					if (currentRefPartenaireDto.getIdStructure() != null) {
						listRefStructureDto.add(currentRefPartenaireDto);
					} else if (currentRefPartenaireDto.getIdIndividu() != null) {
						listRefIndividuDto.add(currentRefPartenaireDto);
					} else if (currentRefPartenaireDto.getIdGroupe() != null) {
						listRefGroupeDto.add(currentRefPartenaireDto);
					}
				}
				log.info("list of structure partenaire:"
						+ listRefStructureDto.size());
				log.info("list of individu partenaire:"
						+ listRefIndividuDto.size());
				log.info("list of groupe partenaire:" + listRefGroupeDto.size());

			}
		} catch (Exception e) {
			log.info(e.getMessage());

		}

	}

	/**
	 * [PartenaireBean.listRefStructureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 16:09:29
	 * @return the listRefStructureDto
	 */
	public List<RefPartenaireDto> getListRefStructureDto() {
		return listRefStructureDto;
	}

	/**
	 * [PartenaireBean.listRefStructureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 16:09:29
	 * @param listRefStructureDto
	 *            the listRefStructureDto to set
	 */
	public void setListRefStructureDto(
			List<RefPartenaireDto> listRefStructureDto) {
		this.listRefStructureDto = listRefStructureDto;
	}

	/**
	 * [PartenaireBean.listRefIndividuDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 17:51:32
	 * @return the listRefIndividuDto
	 */
	public List<RefPartenaireDto> getListRefIndividuDto() {
		return listRefIndividuDto;
	}

	/**
	 * [PartenaireBean.listRefIndividuDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 17:51:32
	 * @param listRefIndividuDto
	 *            the listRefIndividuDto to set
	 */
	public void setListRefIndividuDto(List<RefPartenaireDto> listRefIndividuDto) {
		this.listRefIndividuDto = listRefIndividuDto;
	}

	/**
	 * [PartenaireBean.listRefGroupeDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 17:51:32
	 * @return the listRefGroupeDto
	 */
	public List<RefPartenaireDto> getListRefGroupeDto() {
		return listRefGroupeDto;
	}

	/**
	 * [PartenaireBean.refStructureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:18:37
	 * @return the refStructureServiceImpl
	 */
	public RefStructureService getRefStructureServiceImpl() {
		return refStructureServiceImpl;
	}

	/**
	 * [PartenaireBean.refStructureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 11:18:37
	 * @param refStructureServiceImpl
	 *            the refStructureServiceImpl to set
	 */
	public void setRefStructureServiceImpl(
			RefStructureService refStructureServiceImpl) {
		this.refStructureServiceImpl = refStructureServiceImpl;
	}

	/**
	 * [PartenaireBean.listRefGroupeDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 janv. 2014 17:51:32
	 * @param listRefGroupeDto
	 *            the listRefGroupeDto to set
	 */
	public void setListRefGroupeDto(List<RefPartenaireDto> listRefGroupeDto) {
		this.listRefGroupeDto = listRefGroupeDto;
	}

	/**
	 * [PartenaireMgrBean.addStructure] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:30:47
	 */
	public void addStructure() {
		FacesMessage msg = new FacesMessage();
		
		if( idContrat == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ cBundle.getString("contrat_partenaire_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refPartenaireDto != null)
				&& (refPartenaireDto.getIdStructure() != null)) {
			try {
				refPartenaireDto.setIdGroupe(null);
				refPartenaireDto.setIdIndividu(null);
				refPartenaireDto.setIdContrat(idContrat);
				log.info("saving structure partenariat id = "
						+ refPartenaireDto.getIdStructure());

				refPartenaireServiceImpl.save(refPartenaireDto);
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
	 * [PartenaireMgrBean.deleteStructure] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:30:33
	 * @param selectedStructurePartenaireDto
	 */
	public void deleteStructure(RefPartenaireDto selectedStructurePartenaireDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedStructurePartenaireDto != null)
				&& (selectedStructurePartenaireDto.getIdStructure() != null)) {
			try {
				log.info("deleting structure partenariat id = "
						+ selectedStructurePartenaireDto.getIdStructure());
				refPartenaireServiceImpl.delete(selectedStructurePartenaireDto);
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
	 * [PartenaireMgrBean.addGroupe] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:31:07
	 */
	public void addGroupe() {
		FacesMessage msg = new FacesMessage();
		if( idContrat == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ cBundle.getString("contrat_partenaire_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refPartenaireDto != null)
				&& (refPartenaireDto.getIdGroupe() != null)) {
			try {
				refPartenaireDto.setIdStructure(null);
				refPartenaireDto.setIdIndividu(null);
				refPartenaireDto.setIdContrat(idContrat);
				log.info("saving groupe partenariat id = "
						+ refPartenaireDto.getIdGroupe());
				refPartenaireServiceImpl.save(refPartenaireDto);
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
	 * [PartenaireMgrBean.deleteGroupe] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:32:37
	 * @param selectedGroupePartenaireDto
	 */
	public void deleteGroupe(RefPartenaireDto selectedGroupePartenaireDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedGroupePartenaireDto != null)
				&& (selectedGroupePartenaireDto.getIdGroupe() != null)) {
			try {
				log.info("deleting groupe partenariat id = "
						+ selectedGroupePartenaireDto.getIdGroupe());
				refPartenaireServiceImpl.delete(selectedGroupePartenaireDto);
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
	 * [PartenaireMgrBean.addIndividu] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:32:49
	 */
	public void addIndividu() {
		FacesMessage msg = new FacesMessage();
		if( idContrat == null) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ cBundle.getString("contrat_partenaire_error_id"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if ((refPartenaireDto != null)
				&& (refPartenaireDto.getIdIndividu() != null)) {
			try {
				refPartenaireDto.setIdGroupe(null);
				refPartenaireDto.setIdStructure(null);
				refPartenaireDto.setIdContrat(idContrat);
				log.info("saving individu partenariat id = "
						+ refPartenaireDto.getIdIndividu());
				refPartenaireServiceImpl.save(refPartenaireDto);
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
	 * [PartenaireMgrBean.deleteIndividu] Method 
	 * @author MAKERRI Sofiane  on : 12 mai 2014  11:32:53
	 * @param selectedIndividuPartenaireDto
	 */
	public void deleteIndividu(RefPartenaireDto selectedIndividuPartenaireDto) {
		FacesMessage msg = new FacesMessage();
		if ((selectedIndividuPartenaireDto != null)
				&& (selectedIndividuPartenaireDto.getIdIndividu() != null)) {
			try {
				log.info("deleting individu partenariat id = "
						+ selectedIndividuPartenaireDto.getIdIndividu());
				refPartenaireServiceImpl.delete(selectedIndividuPartenaireDto);
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
	 * [PartenaireBean.refPartenaireDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 15:51:43
	 * @return the refPartenaireDto
	 */
	public RefPartenaireDto getRefPartenaireDto() {
		return refPartenaireDto;
	}

	/**
	 * [PartenaireBean.refPartenaireDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 janv. 2014 15:51:43
	 * @param refPartenaireDto
	 *            the refPartenaireDto to set
	 */
	public void setRefPartenaireDto(RefPartenaireDto refPartenaireDto) {
		this.refPartenaireDto = refPartenaireDto;
	}

	/**
	 * onStructureEdit, Confirm the modification of the signature date and
	 * reference
	 * 
	 * @author BELDI Jamel on : 5 janv. 2014 15:51:43
	 * @param event
	 *            the RowEditEvent
	 */
	public void onSignatureEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage();
		try {
			log.info("Edit Signature contrat:"
					+ ((RefPartenaireDto) event.getObject()).getReference());
			RefPartenaireDto dto = (RefPartenaireDto) event.getObject();
			log.info(" contrat:" + dto.getIdContrat());
			log.info(" structure:" + dto.getIdStructure());
			log.info(" signature: " + dto.getDateSiganture() + " reference: "
					+ dto.getReference());
			refPartenaireServiceImpl.update(dto);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_modification"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * onStructureCancel, Cancel modify the signature date and reference
	 * 
	 * @author BELDI Jamel on : 5 janv. 2014 15:51:43
	 * @param event
	 *            the RowEditEvent
	 */
	public void onSignatureCancel(RowEditEvent event) {

	}

	/**
	 * [PartenaireMgrBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [PartenaireMgrBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [PartenaireMgrBean.idf] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
	 * @return the idf
	 */
	/*
	 * public String getIdf() { return idf; }
	 */

	/**
	 * [PartenaireMgrBean.idf] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
	 * @param idf
	 *            the idf to set
	 */
	/*
	 * public void setIdf(String idf) { FacesContext context =
	 * FacesContext.getCurrentInstance(); HttpServletRequest request =
	 * (HttpServletRequest)context.getExternalContext().getRequest(); String id=
	 * (String) request.getAttribute("idf");
	 * log.info("var request in set id: "+id ); if(id!=null){ this.idf = id; }
	 * else{ if ((idf != null) && (idf.equals("null"))) { this.idf = null; }
	 * else this.idf = idf; } }
	 */

	/**
	 * [PartenaireMgrBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [PartenaireMgrBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 f�vr. 2014 17:03:32
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
	 * [PartenaireMgrBean.editMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 11:39:03
	 * @return the editMode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * [PartenaireMgrBean.editMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 f�vr. 2014 11:39:03
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
	 * [PartenaireMgrBean.partenaireCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:02:05
	 * @return the partenaireCrudBean
	 */
	public PartenaireCrudBean getPartenaireCrudBean() {
		return partenaireCrudBean;
	}

	/**
	 * [PartenaireMgrBean.partenaireCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:02:05
	 * @param partenaireCrudBean
	 *            the partenaireCrudBean to set
	 */
	public void setPartenaireCrudBean(PartenaireCrudBean partenaireCrudBean) {
		this.partenaireCrudBean = partenaireCrudBean;
	}

	/**
	 * [PartenaireMgrBean.signatureCrudBean] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:23:04
	 * @return the signatureCrudBean
	 */
	public SignatureCrudBean getSignatureCrudBean() {
		return signatureCrudBean;
	}

	/**
	 * [PartenaireMgrBean.signatureCrudBean] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:23:04
	 * @param signatureCrudBean
	 *            the signatureCrudBean to set
	 */
	public void setSignatureCrudBean(SignatureCrudBean signatureCrudBean) {
		this.signatureCrudBean = signatureCrudBean;
	}

	/**
	 * [PartenaireMgrBean.partenaireCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:22:43
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void partenaireCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		partenaireCrudBean.setEditAllow(editAllow);
		partenaireCrudBean.setCreateAllow(createAllow);
		partenaireCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [PartenaireMgrBean.signatureCrud] Method
	 * 
	 * @author MAKERRI Sofiane on : 22 mars 2014 11:22:56
	 * @param createAllow
	 * @param editAllow
	 * @param deleteAllow
	 */
	public void signatureCrud(boolean createAllow, boolean editAllow,
			boolean deleteAllow) {
		signatureCrudBean.setEditAllow(editAllow);
		signatureCrudBean.setCreateAllow(createAllow);
		signatureCrudBean.setDeleteAllow(deleteAllow);
	}

	/**
	 * [PartenaireMgrBean.idContrat] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  10:42:58
	 * @return the idContrat
	 */
	public Integer getIdContrat() {
		return idContrat;
	}

	/**
	 * [PartenaireMgrBean.idContrat] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  10:42:58
	 * @param idContrat the idContrat to set
	 */
	public void setIdContrat(Integer idContrat) {
		this.idContrat = idContrat;
	}

	/**
	 * [PartenaireMgrBean.id] Getter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  11:49:32
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * [PartenaireMgrBean.id] Setter 
	 * @author MAKERRI Sofiane on : 18 mai 2014  11:49:32
	 * @param id the id to set
	 */
	public void setId(String id) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Integer _id = (Integer) request.getAttribute("id");
		log.info("var request in set id: " + _id);
		if (_id != null) {
			this.id = _id.toString();
			this.idContrat = _id;
		} else {
			if ((id != null) && (id.equals("null"))) {
				this.id = null;
			} else {
				this.id = id;
				int __id = RefUtilConstant.strToInt(id);
				if(__id != -1) {
					this.idContrat = __id;
				}
				
			}
		}
	}
	
	

}
