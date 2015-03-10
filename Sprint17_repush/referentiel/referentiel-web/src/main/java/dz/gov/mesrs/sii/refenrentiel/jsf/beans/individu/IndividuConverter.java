/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.IndividuConverter.java] 
 * @author BELDI Jamel on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.individu;

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
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;

/**
 * @author BELDI Jamel  on : 4 f�vr. 2014  12:08:58
 */
@ManagedBean(name="individuConverter")
@RequestScoped
public class IndividuConverter implements Converter,Serializable {
	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 4 f�vr. 2014  12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(IndividuConverter.class);
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	/**
	 * [IndividuConverter.IndividuConverter()] Constructor
	 * @author BELDI Jamel  on : 4 f�vr. 2014  12:08:58	
	 */
	public IndividuConverter() {
		
	}

	
	/**
	 * [IndividuConverter.refIndividuServiceImpl] Getter 
	 * @author BELDI Jamel on : 4 f�vr. 2014  12:45:08
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}


	/**
	 * [IndividuConverter.refIndividuServiceImpl] Setter 
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
	            try {  	                
	            	RefIndividuDto refIndividuDto = refIndividuServiceImpl.findById(Integer.valueOf(submittedValue));
	            	

	                 return refIndividuDto;
	                  
	  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de conversion", "Individu invalid"));  
	            }  
	        }  
	  
	          
	}

	/**
	 * [AffectationMgrBean.completeAffectationIndividu] Method 
	 * @author BELDI Jamel  on : 4 f�vr. 2014  11:43:09
	 * @param query
	 * @return list of suggestions of INDIVIDUS
	  * */
	public List<RefIndividuDto> completIndividu(String query){
		log.info("completIndividu");
		List<RefIndividuDto> suggestions = new ArrayList<RefIndividuDto>();
		try{
			 suggestions  = refIndividuServiceImpl.findByNameOrSurname(query);
			
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
        } else { if(value instanceof RefIndividuDto ){  
            return((RefIndividuDto) value).getIdentifiant();
        }else {
        	return value.toString();
        }
        }  
	}

}
