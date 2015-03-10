/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.NomenclatureBckBean.java] 
 * @author MAKERRI Sofiane on : 20 janv. 2014  12:12:31
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcHistoryDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcHistoryService;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:31
 */
@ManagedBean(name = "ncBckBean")
@RequestScoped
public class NcBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:54
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(NcBckBean.class);
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	@ManagedProperty(value = "#{ncNamesServiceImpl}")
	private NcNamesService ncNamesServiceImpl;
	@ManagedProperty(value = "#{ncHistoryServiceImpl}")
	private NcHistoryService ncHistoryServiceImpl;
	private List<NomenclatureDto> listNomenclatureDto;
	private ResourceBundle bundle;
	private ResourceBundle ncBundle;
	private NomenclatureDto nomenclatureDto;
	private NomenclatureDto oldNomenclatureDto;
	private int idNomenclature;
	private boolean updateMode;
	private boolean disableItem;
	private boolean hasReference;
	private List<SelectItem> listNcReference;
	@ManagedProperty("#{param.idNc}")
	private String idNc;
	@ManagedProperty("#{param.idItem}")
	private String idItem;
	private String oldCode;
	private String oldLlLatin;
	private String oldLlArabe;
	private boolean codeChange;
	private boolean llLatinChange;
	private boolean llArabeChange;
	private NcNamesDto infoNcName;

	@PostConstruct
	public void init() {
		if ((idItem == null) || (idItem.isEmpty())) {
			nomenclatureDto = new NomenclatureDto();
			nomenclatureDto.setStatus(true);

			Integer id = RefUtilConstant.strToInt(idNc);
			if (id != -1) {
				NcNamesDto ncNamesDto = ncNamesServiceImpl.findById(id);
				if (ncNamesDto != null) {
					nomenclatureDto.setNcName(ncNamesDto.getNomNomenclature());
					nomenclatureDto.setIdNcNames(ncNamesDto.getId());
					nomenclatureDto.setNcNameReference(ncNamesDto
							.getNomNomenclatureRef());
					hasReference = ncNamesServiceImpl
							.hasReference(nomenclatureDto.getIdNcNames());
				}
			}

		} else {
			Integer id = RefUtilConstant.strToInt(idItem);
			if (id != -1) {
				nomenclatureDto = nomenclatureServiceImpl.findById(id);
				hasReference = ncNamesServiceImpl.hasReference(nomenclatureDto
						.getIdNcNames());
			} else {
				nomenclatureDto = new NomenclatureDto();
			}

		}

	}

	/**
	 * [NomenclatureBckBean.ncNamesServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:38:13
	 * @return the ncNamesServiceImpl
	 */
	public NcNamesService getNcNamesServiceImpl() {
		return ncNamesServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.ncNamesServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:38:13
	 * @param ncNamesServiceImpl
	 *            the ncNamesServiceImpl to set
	 */
	public void setNcNamesServiceImpl(NcNamesService ncNamesServiceImpl) {
		this.ncNamesServiceImpl = ncNamesServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.NomenclatureBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:31
	 */
	public NcBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		ncBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.NC_MESSAGES_FILE_NAME);
	}

	/**
	 * [NomenclatureBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.listNomenclatureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @return the listNomenclatureDto
	 */
	public List<NomenclatureDto> getListNomenclatureDto() {
		return listNomenclatureDto;
	}

	/**
	 * [NomenclatureBckBean.listNomenclatureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @param listNomenclatureDto
	 *            the listNomenclatureDto to set
	 */
	public void setListNomenclatureDto(List<NomenclatureDto> listNomenclatureDto) {
		this.listNomenclatureDto = listNomenclatureDto;
	}

	/**
	 * [NomenclatureBckBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:28:25
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [NomenclatureBckBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:28:25
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [NomenclatureBckBean.idNomenclature] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 12:17:39
	 * @return the idNomenclature
	 */
	public int getIdNomenclature() {
		return idNomenclature;
	}

	/**
	 * [NomenclatureBckBean.idNomenclature] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 12:17:39
	 * @param idNomenclature
	 *            the idNomenclature to set
	 */
	public void setIdNomenclature(int idNomenclature) {
		this.idNomenclature = idNomenclature;
	}

	/**
	 * [NomenclatureBckBean.updateMode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 14:25:53
	 * @return the updateMode
	 */
	public boolean isUpdateMode() {
		return updateMode;
	}

	/**
	 * [NomenclatureBckBean.updateMode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 14:25:53
	 * @param updateMode
	 *            the updateMode to set
	 */
	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}

	/**
	 * [NomenclatureBckBean.addItem] Method add an entry of item nomenclature
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 16:53:40
	 */
	public void saveItem() {

		FacesMessage msg = new FacesMessage();
		boolean updateSuccess = false;
		boolean addHistory = false;
		try {

			if (codeChange) {
				NomenclatureDto ncDto = nomenclatureServiceImpl.findByCode(
						nomenclatureDto.getIdNcNames(),
						nomenclatureDto.getCode());
				if (ncDto != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ ncBundle.getString("nc_error_code_already_exist"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				if (idItem != null)
					addHistory = true;
			}
			if (llLatinChange) {
				NomenclatureDto ncDto = nomenclatureServiceImpl.findByLlLatin(
						nomenclatureDto.getIdNcNames(),
						nomenclatureDto.getLibelleLongFr());
				if (ncDto != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ ncBundle
									.getString("nc_error_ll_latin_already_exist"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				if (idItem != null)
					addHistory = true;
			}

			if (llArabeChange) {
				NomenclatureDto ncDto = nomenclatureServiceImpl.findByLlArabe(
						nomenclatureDto.getIdNcNames(),
						nomenclatureDto.getLibelleLongAr());
				if (ncDto != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec")
							+ ": "
							+ ncBundle
									.getString("nc_error_ll_arabe_already_exist"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}
				if (idItem != null)
					addHistory = true;
			}
			
			/***** update default value *********/
			if(nomenclatureDto.getDefaultValue() && !nomenclatureDto.getStatus()) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundle.getString("error_echec")
						+ ": "
						+ ncBundle
								.getString("nc_error_default_for_inactif"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}

			if (idItem != null) {
				
				NcNamesDto ncNamesDto = ncNamesServiceImpl
						.findById(nomenclatureDto.getIdNcNames());
				ncNamesDto.setIdDefaultValue(nomenclatureDto.getIdDefaultValue());
				ncNamesServiceImpl.update(ncNamesDto);

				nomenclatureServiceImpl.update(nomenclatureDto);

				updateSuccess = true;
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else {
				nomenclatureDto = nomenclatureServiceImpl.save(nomenclatureDto);
				NcNamesDto ncNamesDto = ncNamesServiceImpl
						.findById(nomenclatureDto.getIdNcNames());
				ncNamesDto.setIdDefaultValue(nomenclatureDto.getIdDefaultValue());
				ncNamesServiceImpl.update(ncNamesDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setIdItem(nomenclatureDto.getId().toString());

			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());

		}

		if (addHistory && updateSuccess) {
			try {
				saveHistory();
			} catch (RuntimeException e) {
				throw e;
			}
		}

	}

	/**
	 * [NomenclatureBckBean.ncBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 16:43:12
	 * @return the ncBundle
	 */
	public ResourceBundle getNcBundle() {
		return ncBundle;
	}

	/**
	 * [NomenclatureBckBean.ncBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 16:43:12
	 * @param ncBundle
	 *            the ncBundle to set
	 */
	public void setNcBundle(ResourceBundle ncBundle) {
		this.ncBundle = ncBundle;
	}

	/**
	 * [NomenclatureBckBean.disableItem] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 17:56:46
	 * @return the disableItem
	 */
	public boolean isDisableItem() {
		disableItem = false;
		if (idItem != null && !idItem.trim().isEmpty()) {
			disableItem = true;
		}
		return disableItem;
	}

	/**
	 * [NomenclatureBckBean.disableItem] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 17:56:46
	 * @param disableItem
	 *            the disableItem to set
	 */
	public void setDisableItem(boolean disableItem) {
		this.disableItem = disableItem;
	}

	/**
	 * [NomenclatureBckBean.onFlowProcess] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 10:30:56
	 * @param event
	 * @return
	 */
	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step:" + event.getOldStep());
		log.info("Next step:" + event.getNewStep());
		return event.getNewStep();

	}

	/**
	 * [NomenclatureBckBean.hasReference] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:15:22
	 * @return the hasReference
	 */
	public boolean isHasReference() {
		return hasReference;
	}

	/**
	 * [NomenclatureBckBean.hasReference] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:15:22
	 * @param hasReference
	 *            the hasReference to set
	 */
	public void setHasReference(boolean hasReference) {
		this.hasReference = hasReference;
	}

	/**
	 * [NomenclatureBckBean.listNcReference] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:28:42
	 * @return the listNcReference
	 */
	public List<SelectItem> getListNcReference() {
		listNcReference = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl
				.findNcReference(nomenclatureDto.getNcName());
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			listNcReference.add(selectItem);
		}
		return listNcReference;
	}

	/**
	 * [NomenclatureBckBean.listNcReference] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:28:42
	 * @param listNcReference
	 *            the listNcReference to set
	 */
	public void setListNcReference(List<SelectItem> listNcReference) {
		this.listNcReference = listNcReference;
	}

	/**
	 * [NomenclatureBckBean.ncHistoryServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2014 16:52:37
	 * @return the ncHistoryServiceImpl
	 */
	public NcHistoryService getNcHistoryServiceImpl() {
		return ncHistoryServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.ncHistoryServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 janv. 2014 16:52:37
	 * @param ncHistoryServiceImpl
	 *            the ncHistoryServiceImpl to set
	 */
	public void setNcHistoryServiceImpl(NcHistoryService ncHistoryServiceImpl) {
		this.ncHistoryServiceImpl = ncHistoryServiceImpl;
	}

	/**
	 * [NomenclatureBckBean.saveHistory] Method save the history item after each
	 * update
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 16:53:18
	 */
	private void saveHistory() {

		if (codeChange) {
			log.info("Nc code has been changed");
			saveHistoryItem("code", oldCode);
		}

		if (llArabeChange) {
			log.info("Nc libelle long arabe has been changed");
			saveHistoryItem("LibelleLongAr", oldLlArabe);
		}

		if (llLatinChange) {
			log.info("Nc libelle long latin has been changed");
			saveHistoryItem("LibelleLongFr", oldLlLatin);
		}
		// setOldNomenclatureDto(new NomenclatureDto(nomenclatureDto));
	}

	/**
	 * [NomenclatureBckBean.saveHistoryItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 16:53:24
	 * @param attribut
	 * @param oldValue
	 */
	private void saveHistoryItem(String attribut, String oldValue) {
		log.info("add item history");
		NcHistoryDto ncHistoryDto = new NcHistoryDto();
		ncHistoryDto.setNomAttribut(attribut);
		ncHistoryDto.setDateMaj(new Date());
		ncHistoryDto.setValeurAncienne(oldValue);
		ncHistoryDto.setIdNomenclature(nomenclatureDto.getId());
		ncHistoryServiceImpl.save(ncHistoryDto);
	}

	/**
	 * [NomenclatureBckBean.nomenclatureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:37:18
	 * @return the nomenclatureDto
	 */
	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	/**
	 * [NomenclatureBckBean.nomenclatureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:37:18
	 * @param nomenclatureDto
	 *            the nomenclatureDto to set
	 */
	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	/**
	 * [NomenclatureBckBean.oldNomenclatureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:37:18
	 * @return the oldNomenclatureDto
	 */
	public NomenclatureDto getOldNomenclatureDto() {
		return oldNomenclatureDto;
	}

	/**
	 * [NomenclatureBckBean.oldNomenclatureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:37:18
	 * @param oldNomenclatureDto
	 *            the oldNomenclatureDto to set
	 */
	public void setOldNomenclatureDto(NomenclatureDto oldNomenclatureDto) {
		this.oldNomenclatureDto = oldNomenclatureDto;
	}

	/**
	 * 
	 */
	public void addItem() {
		nomenclatureDto = new NomenclatureDto();
	}

	/**
	 * [NomenclatureBckBean.idNc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:12:39
	 * @return the idNc
	 */
	public String getIdNc() {
		return idNc;
	}

	/**
	 * [NomenclatureBckBean.idNc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:12:39
	 * @param idNc
	 *            the idNc to set
	 */
	public void setIdNc(String idNc) {
		this.idNc = idNc;
	}

	/**
	 * [NomenclatureBckBean.idItem] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:13:26
	 * @return the idItem
	 */
	public String getIdItem() {
		return idItem;
	}

	/**
	 * [NomenclatureBckBean.idItem] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:13:26
	 * @param idItem
	 *            the idItem to set
	 */
	public void setIdItem(String idItem) {
		if (idItem != null && idItem.equals("null")) {
			this.idItem = null;
		} else
			this.idItem = idItem;
	}

	/**
	 * [NcBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 12:32:14
	 * @return
	 */
	public String back() {
		return "toSearch";
	}

	/**
	 * [NcBckBean.codeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:04:40
	 * @param event
	 */
	public void codeChanged(ValueChangeEvent event) {
		codeChange = true;
		if (event != null && event.getOldValue() != null) {
			if (!event.getOldValue().equals(event.getNewValue())) {
				setOldCode(event.getOldValue().toString());
			}
		}
	}

	/**
	 * [NcBckBean.llLatinChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:04:37
	 * @param event
	 */
	public void llLatinChanged(ValueChangeEvent event) {
		llLatinChange = true;
		if (event != null && event.getOldValue() != null) {
			if (!event.getOldValue().equals(event.getNewValue())) {
				setOldLlLatin(event.getOldValue().toString());
			}
		}
	}

	/**
	 * [NcBckBean.llArabeChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:04:34
	 * @param event
	 */
	public void llArabeChanged(ValueChangeEvent event) {
		llArabeChange = true;
		if (event != null && event.getOldValue() != null) {
			if (!event.getOldValue().equals(event.getNewValue())) {
				setOldLlArabe(event.getOldValue().toString());
			}
		}
	}

	/**
	 * [NcBckBean.oldCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @return the oldCode
	 */
	public String getOldCode() {
		return oldCode;
	}

	/**
	 * [NcBckBean.oldCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @param oldCode
	 *            the oldCode to set
	 */
	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	/**
	 * [NcBckBean.oldLlLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @return the oldLlLatin
	 */
	public String getOldLlLatin() {
		return oldLlLatin;
	}

	/**
	 * [NcBckBean.oldLlLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @param oldLlLatin
	 *            the oldLlLatin to set
	 */
	public void setOldLlLatin(String oldLlLatin) {
		this.oldLlLatin = oldLlLatin;
	}

	/**
	 * [NcBckBean.oldLlArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @return the oldLlArabe
	 */
	public String getOldLlArabe() {
		return oldLlArabe;
	}

	/**
	 * [NcBckBean.oldLlArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:02:26
	 * @param oldLlArabe
	 *            the oldLlArabe to set
	 */
	public void setOldLlArabe(String oldLlArabe) {
		this.oldLlArabe = oldLlArabe;
	}

	/**
	 * [NcBckBean.codeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @return the codeChange
	 */
	public boolean isCodeChange() {
		return codeChange;
	}

	/**
	 * [NcBckBean.codeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @param codeChange
	 *            the codeChange to set
	 */
	public void setCodeChange(boolean codeChange) {
		this.codeChange = codeChange;
	}

	/**
	 * [NcBckBean.llLatinChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @return the llLatinChange
	 */
	public boolean isLlLatinChange() {
		return llLatinChange;
	}

	/**
	 * [NcBckBean.llLatinChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @param llLatinChange
	 *            the llLatinChange to set
	 */
	public void setLlLatinChange(boolean llLatinChange) {
		this.llLatinChange = llLatinChange;
	}

	/**
	 * [NcBckBean.llArabeChange] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @return the llArabeChange
	 */
	public boolean isLlArabeChange() {
		return llArabeChange;
	}

	/**
	 * [NcBckBean.llArabeChange] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 14:30:10
	 * @param llArabeChange
	 *            the llArabeChange to set
	 */
	public void setLlArabeChange(boolean llArabeChange) {
		this.llArabeChange = llArabeChange;
	}

	/**
	 * [NcBckBean.toNewItem] Method
	 * 
	 * @author MAKERRI Sofiane on : 5 f�vr. 2014 16:50:23
	 */
	public void toNewItem() {
		nomenclatureDto = new NomenclatureDto();
		nomenclatureDto.setStatus(true);
		setIdItem(null);
		Integer id = RefUtilConstant.strToInt(idNc);
		if (id != -1) {
			NcNamesDto ncNamesDto = ncNamesServiceImpl.findById(id);
			if (ncNamesDto != null) {
				nomenclatureDto.setNcName(ncNamesDto.getNomNomenclature());
				nomenclatureDto.setIdNcNames(ncNamesDto.getId());
				nomenclatureDto.setNcNameReference(ncNamesDto
						.getNomNomenclatureRef());
				hasReference = ncNamesServiceImpl.hasReference(nomenclatureDto
						.getIdNcNames());
			}
		}

	}

	/**
	 * [NcBckBean.infoNcName] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2014 12:23:44
	 * @return the infoNcName
	 */
	public NcNamesDto getInfoNcName() {
		try {
			Integer id = RefUtilConstant.strToInt(idNc);
			if (id != -1) {
				infoNcName = ncNamesServiceImpl.findById(id);
			}
		} catch (Exception e) {

		}
		return infoNcName;
	}

	/**
	 * [NcBckBean.infoNcName] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 f�vr. 2014 12:23:44
	 * @param infoNcName
	 *            the infoNcName to set
	 */
	public void setInfoNcName(NcNamesDto infoNcName) {
		this.infoNcName = infoNcName;
	}

}
