/**
 *  
 * @author Mounir.MESSAOUDI on : 12 oct. 2014 11:54:34
 */
package dz.gov.mesrs.sii.fve.web.jsf.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.orderlist.OrderList;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.VoeuEtudiantChoixDto;

/**
 * Converter voeuxEtudiantChoix pour
 * 
 * @author Mounir.MESSAOUDI on : 12 oct. 2014 11:54:34
 */
@FacesConverter(forClass = VoeuEtudiantChoixDto.class, value = "voeuxEtudiantChoixConverter")
public class VoeuxEtudiantChoixConverter implements Converter {

	/**
	 * 
	 * @author Mounir.MESSAOUDI on : 12 oct. 2014 11:55:10
	 */
	public VoeuxEtudiantChoixConverter() {
		super();

	}

	/**
	 * Converter pour le detail de la fiche des voeux
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Object ret = null;
		try {
			if (component instanceof OrderList) {
				List<VoeuEtudiantChoixDto> voeuEtudiantChoixDtos = (List<VoeuEtudiantChoixDto>) ((OrderList) component)
						.getValue();
				// List<VoeuEtudiantChoixDto> voeuEtudiantChoixDtos =
				// voeuEtudiantDto
				// .getVoeuxEtudiantsChoixDto();
				if (voeuEtudiantChoixDtos != null
						&& !voeuEtudiantChoixDtos.isEmpty())
					for (VoeuEtudiantChoixDto voeuEtudiantChoixDto : voeuEtudiantChoixDtos) {
						if (voeuEtudiantChoixDto.getPriorite() == Integer
								.valueOf(value)) {
							ret = voeuEtudiantChoixDto;
						}

					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Converter pour le detail de la fiche des voeux
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String str = null;
		if (value instanceof VoeuEtudiantChoixDto) {
			int i = ((VoeuEtudiantChoixDto) value).getPriorite();
			str = String.valueOf(i);
		}
		if (str == null) {
			str = "";
		}
		return str;
	}

}