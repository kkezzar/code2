
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.specialitelmd;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefSpecialiteLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;


@ManagedBean(name = "specialitelmdBckBean")
@SessionScoped
public class SpecialitelmdBckBean implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private List<RefSpecialiteLmdDto > listRefSpecialiteLmdDto;
		private String searchValue;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private RefSpecialiteLmdDto refSpecialiteLmdDto;
	private boolean entityChange;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(SpecialitelmdBckBean.class);

	public SpecialitelmdBckBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundle = facesContext.getApplication().getResourceBundle(facesContext,
				RefUtilConstant.COMMON_MESSAGES_FILE_NAME);
		
	}
	
	
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	public List<RefSpecialiteLmdDto> getListRefSpecialiteLmdDto() {
		return listRefSpecialiteLmdDto;
	}

	public void setListRefSpecialiteLmdDto(List<RefSpecialiteLmdDto> listRefSpecialiteLmdDto) {
		this.listRefSpecialiteLmdDto = listRefSpecialiteLmdDto;
	}


	public RefSpecialiteLmdDto getRefSpecialiteLmdDto() {
		return refSpecialiteLmdDto;
	}


	public void setRefSpecialiteLmdDto(RefSpecialiteLmdDto refSpecialiteLmdDto) {
		this.refSpecialiteLmdDto = refSpecialiteLmdDto;
	}
	
	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "SpecialiteLmdSearch";
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
	
	

}
