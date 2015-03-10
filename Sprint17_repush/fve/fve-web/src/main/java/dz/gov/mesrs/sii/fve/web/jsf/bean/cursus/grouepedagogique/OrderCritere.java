/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.OrderCritere.java] 
 * @author MAKERRI Sofiane on : 1 févr. 2015  16:50:35
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;

/**
 * @author MAKERRI Sofiane  on : 1 févr. 2015  16:50:35
 */
@ManagedBean(name = "orderCritere")
@ViewScoped
@FacesConverter(forClass = OrderCritere.class, value = "orderCritere")
public class OrderCritere implements Serializable, Converter{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 1 févr. 2015  16:51:11
	 */
	private static final long serialVersionUID = 1L;
	private String critere;
	private Comparator<DossierInscriptionAdministrativeDto> comparator;
	
	public OrderCritere() {
		super();
	}
	
	/**
	 * [OrderCritere.OrderCritere()] Constructor
	 * @author MAKERRI Sofiane  on : 1 févr. 2015  16:55:12
	 * @param critere
	 * @param checked	
	 */
	public OrderCritere(String critere, Comparator<DossierInscriptionAdministrativeDto> comparator) {
		this.critere = critere;
		this.comparator = comparator;
	}

	/**
	 * [OrderCritere.critere] Getter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  16:51:55
	 * @return the critere
	 */
	public String getCritere() {
		return critere;
	}

	/**
	 * [OrderCritere.critere] Setter 
	 * @author MAKERRI Sofiane on : 1 févr. 2015  16:51:55
	 * @param critere the critere to set
	 */
	public void setCritere(String critere) {
		this.critere = critere;
	}



	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Object o : dl.getSource()) {
				String id = ((OrderCritere) o).getCritere();
				if (value.equals(id)) {
					ret = o;
					break;
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String id = ((OrderCritere) o).getCritere();
					if (value.equals(id)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String str = null;
		if (value instanceof OrderCritere) {
			str = ((OrderCritere) value).getCritere();
		}
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * [OrderCritere.comparator] Getter 
	 * @author MAKERRI Sofiane on : 2 févr. 2015  15:05:52
	 * @return the comparator
	 */
	public Comparator<DossierInscriptionAdministrativeDto> getComparator() {
		return comparator;
	}

	/**
	 * [OrderCritere.comparator] Setter 
	 * @author MAKERRI Sofiane on : 2 févr. 2015  15:05:52
	 * @param comparator the comparator to set
	 */
	public void setComparator(
			Comparator<DossierInscriptionAdministrativeDto> comparator) {
		this.comparator = comparator;
	}
	
	

}
