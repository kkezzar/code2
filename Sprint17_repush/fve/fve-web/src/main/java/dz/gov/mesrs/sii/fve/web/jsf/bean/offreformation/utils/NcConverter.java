/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils.NcConverter.java] 
 * @author rlaib on : 2/02/ 2014  15:14:18
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.slf4j.LoggerFactory;

import dz.gov.mesrs.sii.fve.web.jsf.bean.offreformation.of.OfSupportBean;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author rlaib  on : 2/02/ 2014 15:14:18
 */
@ManagedBean(name="ncConverter")
@FacesConverter(value="ncConverter", forClass=NomenclatureDto.class)
public class NcConverter implements Converter, Serializable {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(NomenclatureDto.class);
	
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		
		 if (submittedValue.trim().equals("")) {
	            			return null;
	       
		 } else {
	        	
			 			OfSupportBean sup = new OfSupportBean();
			 			
			 			List<NomenclatureDto> listDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listDomainesDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listFilieresDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listSpecialitesDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listTypesFormationDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listCyclesDtos = new ArrayList<NomenclatureDto>();
			 			List<NomenclatureDto> listVocationsDtos = new ArrayList<NomenclatureDto>();
			 			
			 			try {
			 							listDomainesDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_DOMAINES);
			 							listFilieresDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_FILIERES);
			 							listSpecialitesDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_SPECIALITES);
			 							listTypesFormationDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_TYPE_FORMATION);
			 							listCyclesDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_CYCLE);
			 							listVocationsDtos = sup.getNomenclatureLmdDictionary().get(OfConstants.NC_NAME_VOCATION);
			 							
			 							listDtos.addAll(listDomainesDtos);
			 							listDtos.addAll(listFilieresDtos);
			 							listDtos.addAll(listSpecialitesDtos);
			 							listDtos.addAll(listTypesFormationDtos);
			 							listDtos.addAll(listCyclesDtos);
			 							listDtos.addAll(listVocationsDtos);
			 							
			 							for (NomenclatureDto d : listDtos) {
			 							if (d.getCode().equals(submittedValue)) {
			 									return d;
	                    }
	                }

	            } catch(NumberFormatException exception) {
	            	
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
	            }
	        }
		 return null;

	}
	
	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value){
		
		//log.debug("get as String value : {} ", value);
		
		//NomenclatureDto nc = (NomenclatureDto) value;
		
	
		//log.debug("get as String value : {} ", nc.getCode());
		
	    // return nc.getCode() != null ? String.valueOf(nc.getCode()) : "";
		
        return value instanceof NomenclatureDto?((NomenclatureDto)value).getCode().toString() : ""; 

		
	}
	

	
}