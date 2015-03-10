/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.nomenclature.TypePieceSearchBckBean.java] 
 * @author MAKERRI Sofiane on : 25 mai 2014  09:25:01
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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefTypePieceConstitutiveService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author MAKERRI Sofiane on : 25 mai 2014 09:25:01
 */
@ManagedBean(name = "typePieceSearchBckBean")
@RequestScoped
public class TypePieceSearchBckBean implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:25:12
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{refTypePieceConstitutiveServiceImpl}")
	private RefTypePieceConstitutiveService refTypePieceConstitutiveServiceImpl;
	private List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto;
	private ResourceBundle bundle;
	@ManagedProperty("#{param.searchValue}")
	private String searchValue;
	private Integer idTypeDossier;
	@ManagedProperty("#{param.idTd}")
	private String idTd;

	/**
	 * [TypePieceSearchBckBean.TypePieceSearchBckBean()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:25:01
	 */
	public TypePieceSearchBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
	}

	@PostConstruct
	public void init() {
		Integer id = RefUtilConstant.strToInt(idTd);
		if (id != -1) {
			listRefTypePieceConstitutiveDto = refTypePieceConstitutiveServiceImpl
					.findByIdTypeDossier(id);
		}

	}

	/**
	 * [TypePieceSearchBckBean.refTypePieceConstitutiveServiceImpl] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:02
	 * @return the refTypePieceConstitutiveServiceImpl
	 */
	public RefTypePieceConstitutiveService getRefTypePieceConstitutiveServiceImpl() {
		return refTypePieceConstitutiveServiceImpl;
	}

	/**
	 * [TypePieceSearchBckBean.refTypePieceConstitutiveServiceImpl] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:02
	 * @param refTypePieceConstitutiveServiceImpl
	 *            the refTypePieceConstitutiveServiceImpl to set
	 */
	public void setRefTypePieceConstitutiveServiceImpl(
			RefTypePieceConstitutiveService refTypePieceConstitutiveServiceImpl) {
		this.refTypePieceConstitutiveServiceImpl = refTypePieceConstitutiveServiceImpl;
	}

	/**
	 * [TypePieceSearchBckBean.listRefTypePieceConstitutiveDto] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:02
	 * @return the listRefTypePieceConstitutiveDto
	 */
	public List<RefTypePieceConstitutiveDto> getListRefTypePieceConstitutiveDto() {
		return listRefTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceSearchBckBean.listRefTypePieceConstitutiveDto] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:02
	 * @param listRefTypePieceConstitutiveDto
	 *            the listRefTypePieceConstitutiveDto to set
	 */
	public void setListRefTypePieceConstitutiveDto(
			List<RefTypePieceConstitutiveDto> listRefTypePieceConstitutiveDto) {
		this.listRefTypePieceConstitutiveDto = listRefTypePieceConstitutiveDto;
	}

	/**
	 * [TypePieceSearchBckBean.searchValue] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:55
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * [TypePieceSearchBckBean.searchValue] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:28:55
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = "";
		} else {
			this.searchValue = searchValue;
		}
	}

	/**
	 * [TypePieceSearchBckBean.idTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:42:13
	 * @return the idTypeDossier
	 */
	public Integer getIdTypeDossier() {
		return idTypeDossier;
	}

	/**
	 * [TypePieceSearchBckBean.idTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:42:13
	 * @param idTypeDossier
	 *            the idTypeDossier to set
	 */
	public void setIdTypeDossier(Integer idTypeDossier) {
		this.idTypeDossier = idTypeDossier;
	}

	/**
	 * [TypePieceSearchBckBean.idTd] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:42:13
	 * @return the idTd
	 */
	public String getIdTd() {
		return idTd;
	}

	/**
	 * [TypePieceSearchBckBean.idTd] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:42:13
	 * @param idTd
	 *            the idTd to set
	 */
	public void setIdTd(String idTd) {
		if ((idTd != null) && (idTd.equals("null"))) {
			this.idTd = null;
		} else {
			int _id = RefUtilConstant.strToInt(idTd);
			if (_id != -1) {
				setIdTypeDossier(_id);
			}
			this.idTd = idTd;
		}

	}

	/**
	 * [TypePieceSearchBckBean.findGeneric] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:43:36
	 */
	public void findGeneric() {

		FacesMessage msg = new FacesMessage();
		try {
			List<RefTypePieceConstitutiveDto> result = refTypePieceConstitutiveServiceImpl
					.findByIdTypeDossier(idTypeDossier);
			if (result == null || result.isEmpty()) {
				setListRefTypePieceConstitutiveDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundle.getString("warn_info") + ": "
						+ bundle.getString("warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefTypePieceConstitutiveDto(result);

			}

		} catch (RuntimeException e) {
			setListRefTypePieceConstitutiveDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			throw e;
		}

	}

	/**
	 * [TypePieceSearchBckBean.typeDossierChanged] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 09:43:31
	 */
	public void typeDossierChanged() {
		findGeneric();
	}

	/**
	 * [TypePieceSearchBckBean.toDetails] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 10:13:31
	 * @return
	 */
	public String toDetails() {
		return "toDetails";
	}

	/**
	 * [TypePieceSearchBckBean.toEdit] Method
	 * 
	 * @author MAKERRI Sofiane on : 25 mai 2014 10:13:42
	 * @return
	 */
	public String toEdit() {
		return "toEdit";
	}

	/**
	 * [TypePieceSearchBckBean.toNew] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 10:45:37
	 * @return
	 */
	public String toNew() {
		if (idTypeDossier != null) {
			return "toNew";
		}
		return null;
	}

	/**
	 * [TypePieceSearchBckBean.delete] Method
	 * 
	 * @author MAKERRI Sofiane on : 26 mai 2014 10:02:26
	 * @param current
	 */
	public void delete(RefTypePieceConstitutiveDto current) {
		FacesMessage msg = new FacesMessage();
		try {
			refTypePieceConstitutiveServiceImpl.remove(current);
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundle.getString("msg_success") + ": "
					+ bundle.getString("msg_success_suppression"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			findGeneric();

		} catch (RuntimeException e) {
			setListRefTypePieceConstitutiveDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundle.getString("error_echec") + ": "
					+ bundle.getString("error_echec_inconnue"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			throw e;
		}
	}

}
