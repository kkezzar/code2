/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.NcListMgrBean.java] 
 * @author MAKERRI Sofiane on : 21 janv. 2014  12:07:28
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
 */
@ManagedBean(name = "ncNamesBckBean")
@RequestScoped
public class NcNamesBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:54
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(NcNamesBckBean.class);
	@ManagedProperty(value = "#{ncNamesServiceImpl}")
	private NcNamesService ncNamesServiceImpl;
	private List<NcNamesDto> listNcNamesDto;
	private ResourceBundle bundle;
	private ResourceBundle ncBundle;
	private boolean disableItem;
	//private boolean updateMode;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	@ManagedProperty("#{param.idNc}")
	private String idNc;
	private NcNamesDto ncNamesDto;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private List<SelectItem> listItems;

	/**
	 * [NcListMgrBean.NcListMgrBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:07:28
	 */
	public NcNamesBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		ncBundle = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.NC_MESSAGES_FILE_NAME);

	}

	@PostConstruct
	public void init() {

		if ((idNc == null) || (idNc.isEmpty())) {
			ncNamesDto = new NcNamesDto();
		} else {
			try {
				Integer id = Integer.parseInt(idNc);
				
				ncNamesDto = ncNamesServiceImpl.findById(id);
				loadNcItems(ncNamesDto.getNomNomenclature());
			} catch (NumberFormatException e) {
				ncNamesDto = new NcNamesDto();
			}

		}
	}

	/**
	 * [NcNamesBckBean.loadNcItems] Method 
	 * @author MAKERRI Sofiane  on : 7 mai 2014  16:31:50
	 * @param name
	 */
	public void loadNcItems(String name) {
		if (name == null) {
			setListItems(null);
		}
		listItems = new ArrayList<SelectItem>();
		List<NomenclatureDto> list = nomenclatureServiceImpl.findByName(name);
		for (NomenclatureDto nomenclatureDto : list) {
			SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
					nomenclatureDto.getLibelleLongFr());
			listItems.add(selectItem);
		}

	}

	/**
	 * [NcNamesMgrBean.ncNamesServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:37:52
	 * @return the ncNamesService
	 */
	public NcNamesService getNcNamesServiceImpl() {
		return ncNamesServiceImpl;
	}

	/**
	 * [NcNamesMgrBean.ncNamesServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:37:52
	 * @param ncNamesService
	 *            the ncNamesService to set
	 */
	public void setNcNamesServiceImpl(NcNamesService ncNamesServiceImpl) {
		this.ncNamesServiceImpl = ncNamesServiceImpl;
	}

	/**
	 * [NcNamesMgrBean.listNcNamesDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:38:50
	 * @return the listNcNamesDto
	 */
	public List<NcNamesDto> getListNcNamesDto() {
		return listNcNamesDto;
	}

	/**
	 * [NcNamesMgrBean.listNcNamesDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:38:50
	 * @param listNcNamesDto
	 *            the listNcNamesDto to set
	 */
	public void setListNcNamesDto(List<NcNamesDto> listNcNamesDto) {
		this.listNcNamesDto = listNcNamesDto;
	}

	/**
	 * [NcListMgrBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:12:48
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [NcListMgrBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 12:12:48
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [NcNamesMgrBean.ncNamesDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:40:13
	 * @return the ncNamesDto
	 */
	public NcNamesDto getNcNamesDto() {
		return ncNamesDto;
	}

	/**
	 * [NcNamesMgrBean.ncNamesDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 janv. 2014 17:40:13
	 * @param ncNamesDto
	 *            the ncNamesDto to set
	 */
	public void setNcNamesDto(NcNamesDto ncNamesDto) {
		this.ncNamesDto = ncNamesDto;
	}

	/**
	 * [NcNamesMgrBean.save] Method save NcNamesDto entity if this entity does
	 * not already exist
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 11:17:41
	 */
	public void save() {
		FacesMessage msg = new FacesMessage();
		try {
			if (idNc == null) {
				NcNamesDto ncDtoExist = ncNamesServiceImpl
						.findByName(ncNamesDto.getNomNomenclature().trim());
				if (ncDtoExist != null) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundle.getString("error_echec") + ": "
							+ ncBundle.getString("nc_error_name_already_exist"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return;
				}

			}
			if (idNc != null) {
				ncNamesServiceImpl.update(ncNamesDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_modification"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;

			} else {
				ncNamesDto = ncNamesServiceImpl.save(ncNamesDto);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundle.getString("msg_success") + ": "
						+ bundle.getString("msg_success_enregistrement"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				setIdNc(ncNamesDto.getId().toString());
			}
		} catch (Exception e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.info(e.getMessage());
			return;
		}

	}

	/**
	 * [NcNamesMgrBean.ncBundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 15:45:51
	 * @return the ncBundle
	 */
	public ResourceBundle getNcBundle() {
		return ncBundle;
	}

	/**
	 * [NcNamesMgrBean.ncBundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 15:45:51
	 * @param ncBundle
	 *            the ncBundle to set
	 */
	public void setNcBundle(ResourceBundle ncBundle) {
		this.ncBundle = ncBundle;
	}

	/**
	 * [NcNamesMgrBean.disableItem] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 16:41:55
	 * @return the disableItem
	 */
	public boolean isDisableItem() {
		disableItem = false;
		try {
			if (idNc != null) {
				if (nomenclatureServiceImpl.existIdNc(Integer.parseInt(idNc))) {
					disableItem = true;
				}
			}
		} catch (Exception e) {
			disableItem = false;
		}

		return disableItem;
	}

	/**
	 * [NcNamesMgrBean.disableItem] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 16:41:55
	 * @param disableItem
	 *            the disableItem to set
	 */
	public void setDisableItem(boolean disableItem) {
		this.disableItem = disableItem;
	}

	/**
	 * [NcNamesBckBean.back] Method
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 12:31:56
	 * @return
	 */
	public String back() {
		return "ncNamesSearch";
	}

	/**
	 * [NcNamesBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 2 f�vr. 2014 15:38:24
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [NcNamesBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 2 f�vr. 2014 15:38:24
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * [NcNamesBckBean.idNc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 f�vr. 2014 08:23:09
	 * @return the idNc
	 */
	public String getIdNc() {
		return idNc;
	}

	/**
	 * [NcNamesBckBean.idNc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 f�vr. 2014 08:23:09
	 * @param idNc
	 *            the idNc to set
	 */
	public void setIdNc(String idNc) {
		if ((idNc != null) && (idNc.equals("null"))) {
			this.idNc = null;
		} else
			this.idNc = idNc;
	}

	/**
	 * [NcNamesBckBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 f�vr. 2014 11:41:32
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [NcNamesBckBean.nomenclatureServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 f�vr. 2014 11:41:32
	 * @param nomenclatureServiceImpl
	 *            the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * [NcNamesBckBean.toNcItems] Method
	 * 
	 * @author MAKERRI Sofiane on : 16 f�vr. 2014 11:32:27
	 * @return
	 */
	public String toNcItems() {
		return "toNcItemEdit";
	}

	/**
	 * [NcNamesBckBean.listItems] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 11:52:22
	 * @return the listItems
	 */
	public List<SelectItem> getListItems() {
		return listItems;
	}

	/**
	 * [NcNamesBckBean.listItems] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 avr. 2014 11:52:22
	 * @param listItems
	 *            the listItems to set
	 */
	public void setListItems(List<SelectItem> listItems) {
		this.listItems = listItems;
	}
	
	/**
	 * [NcNamesBckBean.addNcName] Method 
	 * @author MAKERRI Sofiane  on : 28 avr. 2014  12:15:26
	 */
	public void addNcName() {
		ncNamesDto = new NcNamesDto();
	}

}
