/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfConverter.java] 
 * @author rlaib on : 2/02/ 2014  15:01:16
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationDto;

/**
 * @author rlaib  on : 2/02/ 2014  15:01:16
 */
@FacesConverter(value="ofConverter")
public class OfConverter implements Converter {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(OffreFormationDto.class);
	
	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value){
		
		log.debug("get as object value : {} ", value);
		OffreFormationDto of = new OffreFormationDto();
		of.setCode(value);
		return of;
	}
	
	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value){
		
		log.debug("get as String value : {} ", value);
		OffreFormationDto of = (OffreFormationDto) value;
		log.debug("get as String value : {} ", of.getCodeInt());
		
		return of.getCodeInt();
	}
	
	
}
