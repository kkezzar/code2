
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.lieu;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;


@ManagedBean(name = "lieuBckBean")
@SessionScoped
public class LieuBckBean implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private List<RefLieuDto > listRefLieuDto;
	/** @ManagedProperty("#{param.searchValue}")**/
	private String searchValue;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private RefLieuDto refLieuDto;
	private boolean entityChange;
	private ResourceBundle bundle;
	private boolean typelieuissalle;
	private static final Log log = LogFactory.getLog(LieuBckBean.class);

	public LieuBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		setTypelieuissalle(false);
	}
	
	
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	public List<RefLieuDto> getListRefLieuDto() {
		return listRefLieuDto;
	}

	public void setListRefLieuDto(List<RefLieuDto> listRefLieuDto) {
		this.listRefLieuDto = listRefLieuDto;
	}


	public RefLieuDto getRefLieuDto() {
		return refLieuDto;
	}


	public void setRefLieuDto(RefLieuDto refLieuDto) {
		this.refLieuDto = refLieuDto;
	}
	
	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "lieuSearch";
	}
	
	public boolean isEntityChange() {
		return entityChange;
	}

	public void setEntityChange(boolean entityChange) {
		this.entityChange = entityChange;
	}
	public void entityChanged(ValueChangeEvent event) {
		entityChange = true;
	}
	
	/**
	 * listener on change Type lieu
	 * @return 
	 */
	public void onChangeTypeLieu() {
		if (refLieuDto!=null && refLieuDto.getTypelieuLibelleLongAr().equals("Salle")) {
	    setTypelieuissalle(true);
		}
		else{
		setTypelieuissalle(false);
		}
		
	}


	public boolean isTypelieuissalle() {
		return typelieuissalle;
	}


	public void setTypelieuissalle(boolean typelieuissalle) {
		this.typelieuissalle = typelieuissalle;
	}


}
