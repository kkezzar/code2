
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.domainelmd;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineLMDDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;


@ManagedBean(name = "domainelmdBckBean")
@SessionScoped
public class DomainelmdBckBean implements Serializable {

	

	private static final long serialVersionUID = 1L;

	private List<RefDomaineLMDDto > listRefDomaineLMDDto;
		private String searchValue;
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;
	private RefDomaineLMDDto refDomaineLMDDto;
	private boolean entityChange;
	private ResourceBundle bundle;
	private static final Log log = LogFactory.getLog(DomainelmdBckBean.class);

	public DomainelmdBckBean() {
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

	public List<RefDomaineLMDDto> getListRefDomaineLMDDto() {
		return listRefDomaineLMDDto;
	}

	public void setListRefDomaineLMDDto(List<RefDomaineLMDDto> listRefDomaineLMDDto) {
		this.listRefDomaineLMDDto = listRefDomaineLMDDto;
	}


	public RefDomaineLMDDto getRefDomaineLMDDto() {
		return refDomaineLMDDto;
	}


	public void setRefDomaineLMDDto(RefDomaineLMDDto refDomaineLMDDto) {
		this.refDomaineLMDDto = refDomaineLMDDto;
	}
	
	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String back() {
		return "domainelmdSearch";
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
