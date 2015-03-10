/**
 * 
 */
package dz.gov.mesrs.sii.fve.web.jsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

//import dz.gov.mesrs.sii.referentiel.ws.service.Nomenclature;
import dz.gov.mesrs.sii.fve.business.model.dto.bac.DroitAccessWilayaDto;

@FacesConverter(forClass = DroitAccessWilayaDto.class, value = "wilayaConverter")
public class WilayaConverter implements Serializable, Converter {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 juil. 2014 14:25:09
	 */
	private static final long serialVersionUID = 1L;

	public WilayaConverter() {
		super();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		if (component instanceof PickList) {
			Object dualList = ((PickList) component).getValue();
			DualListModel dl = (DualListModel) dualList;
			for (Object o : dl.getSource()) {
				String id = ((DroitAccessWilayaDto) o).getRefCodeWilaya();
				if (value.equals(id)) {
					ret = o;
					break;
				}
			}
			if (ret == null)
				for (Object o : dl.getTarget()) {
					String id = ((DroitAccessWilayaDto) o).getRefCodeWilaya();
					if (value.equals(id)) {
						ret = o;
						break;
					}
				}
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String str = null;
		if (value instanceof DroitAccessWilayaDto) {
			str = ((DroitAccessWilayaDto) value).getRefCodeWilaya();
		}
		if (str == null) {
			str = "";
		}
		return str;
	}

}
