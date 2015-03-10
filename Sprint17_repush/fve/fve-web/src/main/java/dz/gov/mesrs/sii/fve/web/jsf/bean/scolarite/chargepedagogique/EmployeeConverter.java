/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique.employeeConverter.java] 
 * @author rlaib on : 11 nov. 2014  11:31:27
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.DossierEmployeDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author rlaib  on : 11 nov. 2014  11:31:27
 */
@FacesConverter(value="employeeConverter", forClass=NomenclatureDto.class)
public class EmployeeConverter implements Converter {
 
	private EnseignantFicheVoeuxService enseignantFicheVoeuxService;
    
	public EmployeeConverter(EnseignantFicheVoeuxService service) {
		this.enseignantFicheVoeuxService = service;
	}
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
	                // retourner le dossier employe par ID
            		return enseignantFicheVoeuxService.getEnseignantById(Long.parseLong(value));
            	
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Dossier employ� invalide."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((DossierEmployeDto) object).getId());
        }
        else {
            return null;
        }
    }  
}     