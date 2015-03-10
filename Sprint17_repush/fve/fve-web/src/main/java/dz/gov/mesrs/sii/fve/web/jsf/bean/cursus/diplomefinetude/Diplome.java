/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.diplomefinetude.DiplomeConverter.java] 
 * @author MAKERRI Sofiane on : 23 févr. 2015  10:15:43
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.diplomefinetude;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.CycleDiplomeDto;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.OrderCritere;

/**
 * @author MAKERRI Sofiane on : 23 févr. 2015 10:15:43
 */
@ManagedBean(name = "diplome")
@ViewScoped
@FacesConverter(forClass = OrderCritere.class, value = "diplome")
public class Diplome implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:15:50
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String libelleFr;
	private String libelleAr;

	public Diplome() {
		super();
	}
	
	/**
	 * [DiplomeConverter.DiplomeConverter()] Constructor
	 * @author MAKERRI Sofiane  on : 23 févr. 2015  10:25:01
	 * @param cycleDiplomeDto	
	 */
	public Diplome(CycleDiplomeDto cycleDiplomeDto) {
		super();
		if(cycleDiplomeDto != null) {
			this.code = cycleDiplomeDto.getDiplomeCode();
			this.libelleAr = cycleDiplomeDto.getDiplomeLibelleAr();
			this.libelleFr = cycleDiplomeDto.getDiplomeLibelle();
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			if (dl != null) {
				for (Object o : dl.getSource()) {
					String id = ((Diplome) o).getLibelleFr();
					if (value.equals(id)) {
						ret = o;
						break;
					}
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String id = ((Diplome) o).getLibelleFr();
					if (value.equals(id)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String str = null;
		if (value instanceof Diplome) {
			str = ((Diplome) value).getLibelleFr();
		}
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * [DiplomeConverter.libelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:18:23
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [DiplomeConverter.libelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:18:23
	 * @param libelleFr
	 *            the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [DiplomeConverter.libelleAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:18:23
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [DiplomeConverter.libelleAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 févr. 2015 10:18:23
	 * @param libelleAr
	 *            the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [Diplome.code] Getter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  10:49:22
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [Diplome.code] Setter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  10:49:22
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
