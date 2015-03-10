package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean (name="oFSearchBBean")
@ViewScoped
public class OFSearchBBean {
	
	@ManagedProperty(value="#{oFManagerBean}")
	private OFManagerBean oFManagerBean;

	
	
	
	public OFManagerBean getoFManagerBean() {
		return oFManagerBean;
	}

	public void setoFManagerBean(OFManagerBean oFManagerBean) {
		this.oFManagerBean = oFManagerBean;
	}
	

	
	
	
	

}
