/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationConverter.java] 
 * @author BELDI Jamel on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel  on : 4 f�vr. 2014  12:08:58
 */
@ManagedBean(name="affectationConverter")
@RequestScoped
public class AffectationConverter implements Converter,Serializable {
	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 4 f�vr. 2014  12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AffectationConverter.class);
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	/**
	 * [AffectationConverter.AffectationConverter()] Constructor
	 * @author BELDI Jamel  on : 4 f�vr. 2014  12:08:58	
	 */
	public AffectationConverter() {
		
	}

	
	/**
	 * [AffectationConverter.refIndividuServiceImpl] Getter 
	 * @author BELDI Jamel on : 4 f�vr. 2014  12:45:08
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}


	/**
	 * [AffectationConverter.refIndividuServiceImpl] Setter 
	 * @author BELDI Jamel on : 4 f�vr. 2014  12:45:08
	 * @param refIndividuServiceImpl the refIndividuServiceImpl to set
	 */
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
	            	RefAffectationDto refAffectationDto = new RefAffectationDto();
					refAffectationDto.setIdIndividu(refIndividuDto.getId());
					refAffectationDto.setNomLtIndividu(refIndividuDto.getNomLatin());
					refAffectationDto.setPrenomLtIndividu(refIndividuDto.getPrenomLatin());
	                 return refAffectationDto;
	                  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid individu"));  
	            }  
	        }  
	  
	          
	}

	/**
	 * [AffectationMgrBean.completeAffectationIndividu] Method 
	 * @author BELDI Jamel  on : 4 f�vr. 2014  11:43:09
	 * @param query
	 * @return list of suggestions of INDIVIDUS
	  * */
	public List<RefAffectationDto> completeAffectationIndividu(String query){
		log.info("completeAffectationIndividu");
		List<RefAffectationDto> suggestions = new ArrayList<RefAffectationDto>();
		try{
			List<RefIndividuDto> result  = refIndividuServiceImpl.findGeneric(query);
			for(RefIndividuDto refIndividuDto : result){
				RefAffectationDto refAffectationDto = new RefAffectationDto();
				refAffectationDto.setIdIndividu(refIndividuDto.getId());
				refAffectationDto.setNomLtIndividu(refIndividuDto.getNomLatin());
				refAffectationDto.setPrenomLtIndividu(refIndividuDto.getPrenomLatin());
				suggestions.add(refAffectationDto);
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
