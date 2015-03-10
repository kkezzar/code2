/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.IndividuCompteConverter.java] 
 * @author BELDI Jamel on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.refenrentiel.jsf.beans.compte;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefCompteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefCompteService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel  on : 4 f�vr. 2014  12:08:58
 */
/**
 * @author BELDI Jamel on : 12 mars 2014 11:02:25
 */
@ManagedBean(name = "individuCompteConverter")
@RequestScoped
public class IndividuCompteConverter implements Converter, Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 4 f�vr. 2014 12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(IndividuCompteConverter.class);
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;
	@ManagedProperty(value = "#{refCompteServiceImpl}")
	private RefCompteService refCompteServiceImpl;

	/**
	 * [IndividuCompteConverter.IndividuCompteConverter()] Constructor
	 * 
	 * @author BELDI Jamel on : 4 f�vr. 2014 12:08:58
	 */
	public IndividuCompteConverter() {

	}

	/**
	 * [IndividuCompteConverter.refIndividuServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 4 f�vr. 2014 12:45:08
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}

	/**
	 * [IndividuCompteConverter.refIndividuServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 4 f�vr. 2014 12:45:08
	 * @param refIndividuServiceImpl
	 *            the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(
			RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}

	/**
	 * [IndividuCompteConverter.refCompteServiceImpl] Getter
	 * 
	 * @author BELDI Jamel on : 12 mars 2014 11:02:55
	 * @return the refCompteServiceImpl
	 */
	public RefCompteService getRefCompteServiceImpl() {
		return refCompteServiceImpl;
	}

	/**
	 * [IndividuCompteConverter.refCompteServiceImpl] Setter
	 * 
	 * @author BELDI Jamel on : 12 mars 2014 11:02:55
	 * @param refCompteServiceImpl
	 *            the refCompteServiceImpl to set
	 */
	public void setRefCompteServiceImpl(RefCompteService refCompteServiceImpl) {
		this.refCompteServiceImpl = refCompteServiceImpl;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String submittedValue) {
		log.info("getAsObject submittedValue:" + submittedValue);
		if (submittedValue.trim().equals("")) {
			return null;
		}
		int id = RefUtilConstant.strToInt(submittedValue);
		if (id == -1) {
			return null;
		}
		try {
			RefIndividuDto refIndividuDto = refIndividuServiceImpl.findById(id);

			return refIndividuDto;

		} catch (NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erreur de conversion",
					"Individu invalid"));
		}

	}

	/**
	 * [IndividuCompteConverter.completeAffectationIndividu] Method
	 * 
	 * @author BELDI Jamel on : 4 f�vr. 2014 11:43:09
	 * @param query
	 * @return list of suggestions of INDIVIDUS
	 * */
	public List<RefIndividuDto> completIndividu(String query) {
		log.info("completIndividu");
		List<RefIndividuDto> suggestions = new ArrayList<RefIndividuDto>();
		try {
			List<RefIndividuDto> individus = refIndividuServiceImpl
					.findGeneric(query);
			for (RefIndividuDto refIndividuDto : individus) {
				RefCompteDto compte = refCompteServiceImpl
						.findByUser(refIndividuDto.getId());
				if (compte == null) {
					suggestions.add(refIndividuDto);
				}

			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return suggestions;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			if (value instanceof RefIndividuDto) {
				Integer id = ((RefIndividuDto) value).getId();
				if(id == null) {
					return "";
				}
				return id.toString();
			} else {
				return value.toString();
			}
		}
	}

}
