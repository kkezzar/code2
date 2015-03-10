
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.filierelmd;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFiliereLmdDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;


@ManagedBean(name = "filierelmdBckBean")
@SessionScoped
public class FilierelmdBckBean implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private List<RefFiliereLmdDto > listRefFiliereLmdDto;
		private String searchValue;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private RefFiliereLmdDto refFiliereLmdDto;
	private boolean entityChange;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(FilierelmdBckBean.class);

	public FilierelmdBckBean() {
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

	public List<RefFiliereLmdDto> getListRefFiliereLmdDto() {
		return listRefFiliereLmdDto;
	}

	public void setListRefFiliereLmdDto(List<RefFiliereLmdDto> listRefFiliereLmdDto) {
		this.listRefFiliereLmdDto = listRefFiliereLmdDto;
	}


	public RefFiliereLmdDto getRefFiliereLmdDto() {
		return refFiliereLmdDto;
	}


	public void setRefFiliereLmdDto(RefFiliereLmdDto refFiliereLmdDto) {
		this.refFiliereLmdDto = refFiliereLmdDto;
	}
	
	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "FiliereLmdSearch";
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
