/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.NomenclatureMgrBean.java] 
 * @author MAKERRI Sofiane on : 20 janv. 2014  12:12:31
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FlowEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NcNamesService;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:31
 */
@ManagedBean(name = "ncSearchBckBean")
@RequestScoped
public class NcSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:54
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(NcSearchBckBean.class);
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	@ManagedProperty(value = "#{ncNamesServiceImpl}")
	private NcNamesService ncNamesServiceImpl;
	private List<NomenclatureDto> listNomenclatureDto;
	private ResourceBundle bundle;
	private NomenclatureDto nomenclatureDto;
	private int idNomenclature;
	private String nomNomenclature;
	@ManagedProperty("#{param.idNc}")
	private String idNc;

	@PostConstruct
	public void init() {
		Integer id = RefUtilConstant.strToInt(idNc);
		if (id != -1) {
			listNomenclatureDto = nomenclatureServiceImpl.findByIdNc(id);
		}

	}

	/**
	 * [NomenclatureMgrBean.ncNamesServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:38:13
	 * @return the ncNamesServiceImpl
	 */
	public NcNamesService getNcNamesServiceImpl() {
		return ncNamesServiceImpl;
	}

	/**
	 * [NomenclatureMgrBean.ncNamesServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 12:38:13
	 * @param ncNamesServiceImpl
	 *            the ncNamesServiceImpl to set
	 */
	public void setNcNamesServiceImpl(NcNamesService ncNamesServiceImpl) {
		this.ncNamesServiceImpl = ncNamesServiceImpl;
	}

	/**
	 * [NomenclatureMgrBean.NomenclatureMgrBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:12:31
	 */
	public NcSearchBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);

	}

	/**
	 * [NomenclatureMgrBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 08:33:08
	 */
	public void findGeneric() {

		FacesMessage msg = new FacesMessage();
		try {

			List<NomenclatureDto> result = nomenclatureServiceImpl
					.findByIdNc(idNomenclature);
			if (result == null || result.isEmpty()) {
				setListNomenclatureDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListNomenclatureDto(result);

			}

		} catch (RuntimeException e) {
			setListNomenclatureDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			throw e;
		}

	}

	/**
	 * [NomenclatureMgrBean.nomenclatureServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [NomenclatureMgrBean.nomenclatureServiceImpl] Setter
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
	 * [NomenclatureMgrBean.listNomenclatureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @return the listNomenclatureDto
	 */
	public List<NomenclatureDto> getListNomenclatureDto() {
		return listNomenclatureDto;
	}

	/**
	 * [NomenclatureMgrBean.listNomenclatureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 janv. 2014 12:09:48
	 * @param listNomenclatureDto
	 *            the listNomenclatureDto to set
	 */
	public void setListNomenclatureDto(List<NomenclatureDto> listNomenclatureDto) {
		this.listNomenclatureDto = listNomenclatureDto;
	}

	/**
	 * [NomenclatureMgrBean.bundle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:28:25
	 * @return the bundle
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

	/**
	 * [NomenclatureMgrBean.bundle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2014 12:28:25
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * [NomenclatureMgrBean.idNomenclature] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 12:17:39
	 * @return the idNomenclature
	 */
	public int getIdNomenclature() {
		return idNomenclature;
	}

	/**
	 * [NomenclatureMgrBean.idNomenclature] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 12:17:39
	 * @param idNomenclature
	 *            the idNomenclature to set
	 */
	public void setIdNomenclature(int idNomenclature) {
		this.idNomenclature = idNomenclature;
	}

	/**
	 * [NomenclatureMgrBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 14:24:41
	 * @return
	 */
	public String toNew() {
		return "toNew";
	}

	/**
	 * [NomenclatureMgrBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 14:24:54
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [NomenclatureMgrBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 janv. 2014 14:25:05
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}


	/**
	 * [NomenclatureMgrBean.nomNomenclature] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 14:48:07
	 * @return the nomNomenclature
	 */
	public String getNomNomenclature() {
		return nomNomenclature;
	}

	/**
	 * [NomenclatureMgrBean.nomNomenclature] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 janv. 2014 14:48:07
	 * @param nomNomenclature
	 *            the nomNomenclature to set
	 */
	public void setNomNomenclature(String nomNomenclature) {
		this.nomNomenclature = nomNomenclature;
	}

	/**
	 * [NomenclatureMgrBean.nomenclatureDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:53:09
	 * @return the nomenclatureDto
	 */
	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	/**
	 * [NomenclatureMgrBean.nomenclatureDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 janv. 2014 11:53:09
	 * @param nomenclatureDto
	 *            the nomenclatureDto to set
	 */
	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	/**
	 * [NomenclatureMgrBean.idNc] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:01:29
	 * @return the idNc
	 */
	public String getIdNc() {
		return idNc;
	}

	/**
	 * [NomenclatureMgrBean.idNc] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 f�vr. 2014 11:01:29
	 * @param idNc
	 *            the idNc to set
	 */
	public void setIdNc(String idNc) {
		Integer id = RefUtilConstant.strToInt(idNc);
		if (id != -1) {
			setIdNomenclature(id);
		}

		this.idNc = idNc;
	}
	
	
	/**
	 * [NcSearchBckBean.ncChanged] Method 
	 * @author MAKERRI Sofiane  on : 30 mars 2014  10:26:50
	 */
	public void ncChanged() {
		findGeneric();
	}

}
