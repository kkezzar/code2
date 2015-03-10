/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationConverter.java] 
 * @author BELDI Jamel on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.contrat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefPartenaireDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * 
 * @author BELDI Jamel
 *
 */
@ManagedBean(name="partenaireIndividuConverter")
@RequestScoped
public class PartenaireIndividuConverter implements Converter,Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PartenaireIndividuConverter.class);
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;

	public PartenaireIndividuConverter() {
		
	}

	
	
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}


	public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}


	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		log.info("getAsObject submittedValue:"+submittedValue);
		 if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else { 
	        	int id = RefUtilConstant.strToInt(submittedValue);
	        	if(id == -1) {
	        		return null;
	        	}
	            try {  	                
	            	RefIndividuDto refIndividuDto = refIndividuServiceImpl.findById(id);
	            	RefPartenaireDto refPartenaireDto = new RefPartenaireDto();
	            	refPartenaireDto.setIdIndividu(refIndividuDto.getId());
	            	refPartenaireDto.setNomLatin(refIndividuDto.getNomLatin());
	            	refPartenaireDto.setPrenomLatin(refIndividuDto.getPrenomLatin());
	                 return refPartenaireDto;
	                  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid individu"));  
	            }  
	        }  
	  
	          
	}

	
	public List<RefPartenaireDto> completePartenaireIndividu(String query){
		
		List<RefPartenaireDto> suggestions = new ArrayList<RefPartenaireDto>();
		try{
			List<RefIndividuDto> result  = refIndividuServiceImpl.findByNameOrSurname(query);
			for(RefIndividuDto refIndividuDto : result){
				RefPartenaireDto refPartenaireDto = new RefPartenaireDto();
            	refPartenaireDto.setIdIndividu(refIndividuDto.getId());
            	refPartenaireDto.setNomLatin(refIndividuDto.getNomLatin());
            	refPartenaireDto.setPrenomLatin(refIndividuDto.getPrenomLatin());
				suggestions.add(refPartenaireDto);
			}
		}catch(Exception e){
			log.info(e.getMessage());
		}
		
		return suggestions;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		log.info("getAsString Object:"+value);
		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return value.toString();
        }  
	}

}
