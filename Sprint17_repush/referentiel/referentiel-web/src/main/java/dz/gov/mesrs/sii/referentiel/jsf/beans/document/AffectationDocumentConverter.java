/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.AffectationConverter.java] 
 * @author BELDI Jamel on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

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

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefAdresseService;
import dz.gov.mesrs.sii.referentiel.business.service.RefIndividuService;
import dz.gov.mesrs.sii.referentiel.business.service.RefTelephoneService;

@ManagedBean(name = "affectationDocumentConverter")
@RequestScoped
public class AffectationDocumentConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(AffectationDocumentConverter.class);
	@ManagedProperty(value = "#{refIndividuServiceImpl}")
	private RefIndividuService refIndividuServiceImpl;

	@ManagedProperty(value = "#{refAdresseServiceImpl}")
	private RefAdresseService refAdresseServiceImpl;
	
	@ManagedProperty(value = "#{refTelephoneServiceImpl}")
	private RefTelephoneService refTelephoneServiceImpl;

	public AffectationDocumentConverter() {

	}

	
	
	
	
	
	/**
	 * [AffectationDocumentConverter.refIndividuServiceImpl] Getter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @return the refIndividuServiceImpl
	 */
	public RefIndividuService getRefIndividuServiceImpl() {
		return refIndividuServiceImpl;
	}




	/**
	 * [AffectationDocumentConverter.refIndividuServiceImpl] Setter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @param refIndividuServiceImpl the refIndividuServiceImpl to set
	 */
	public void setRefIndividuServiceImpl(RefIndividuService refIndividuServiceImpl) {
		this.refIndividuServiceImpl = refIndividuServiceImpl;
	}






	/**
	 * [AffectationDocumentConverter.refAdresseServiceImpl] Getter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @return the refAdresseServiceImpl
	 */
	public RefAdresseService getRefAdresseServiceImpl() {
		return refAdresseServiceImpl;
	}






	/**
	 * [AffectationDocumentConverter.refAdresseServiceImpl] Setter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @param refAdresseServiceImpl the refAdresseServiceImpl to set
	 */
	public void setRefAdresseServiceImpl(RefAdresseService refAdresseServiceImpl) {
		this.refAdresseServiceImpl = refAdresseServiceImpl;
	}






	/**
	 * [AffectationDocumentConverter.refTelephoneServiceImpl] Getter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @return the refTelephoneServiceImpl
	 */
	public RefTelephoneService getRefTelephoneServiceImpl() {
		return refTelephoneServiceImpl;
	}






	/**
	 * [AffectationDocumentConverter.refTelephoneServiceImpl] Setter 
	 * @author yselmane on : 14 mai 2014  11:15:51
	 * @param refTelephoneServiceImpl the refTelephoneServiceImpl to set
	 */
	public void setRefTelephoneServiceImpl(
			RefTelephoneService refTelephoneServiceImpl) {
		this.refTelephoneServiceImpl = refTelephoneServiceImpl;
	}






	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String submittedValue) {
		log.info("getAsObject submittedValue:" + submittedValue);
		if (submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				RefIndividuDto refIndividuDto = refIndividuServiceImpl
						.findById(Integer.parseInt(submittedValue));
				RefAffectDocumentDto refAffectDocumentDto = new RefAffectDocumentDto();
				refAffectDocumentDto.setIdentifiantIndividu(refIndividuDto
						.getIdentifiant());
				refAffectDocumentDto.setIdIndividu(refIndividuDto
						.getId());
				refAffectDocumentDto.setNomLtIndividu(refIndividuDto
						.getNomLatin());
				refAffectDocumentDto.setPrenomLtIndividu(refIndividuDto
						.getPrenomLatin());
				
				refAffectDocumentDto.setDateNaissanceIndividu(refIndividuDto
						.getDateNaissance());

				if (refAffectDocumentDto != null
						&& refAffectDocumentDto.getIdentifiantIndividu() != null) {
					RefAdresseDto refAdresseDto = refAdresseServiceImpl
							.findPrincipalAdresseForIndividu(refAffectDocumentDto
									.getIdentifiantIndividu());
					
					RefTelephoneDto refTelephoneDto = refTelephoneServiceImpl
							.findPrincipalTelephoneForIndividu(refAffectDocumentDto
									.getIdentifiantIndividu());
					if (refAdresseDto != null) {
						refAffectDocumentDto.setAdresseIndividuLongFr(refAdresseDto
								.getLibelleAdresseLatin());
						refAffectDocumentDto.setWilayaIndividuLongFr(refAdresseDto
								.getWilayaLibelleLongFr());
						refAffectDocumentDto.setDairaIndividuLongFr(refAdresseDto
								.getDairaLibelleLongFr());
						refAffectDocumentDto.setCommuneIndividuLongFr(refAdresseDto
								.getCommuneLibelleLongFr());
						refAffectDocumentDto.setPaysIndividuLongFr(refAdresseDto
								.getPaysLibelleLongFr());
					}
					
					if (refTelephoneDto != null) {
						refAffectDocumentDto.setNumeroTelephoneIndividu(refTelephoneDto
								.getNumeroTelephone());
					}

				}

				return refAffectDocumentDto;

			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid individu"));
			}
		}

	}

	public List<RefAffectDocumentDto> completeAffectationIndividu(String query) {
		log.info("completeAffectationIndividu");
		List<RefAffectDocumentDto> suggestions = new ArrayList<RefAffectDocumentDto>();
		try {
			List<RefIndividuDto> result = refIndividuServiceImpl
					.findGeneric(query);
			for (RefIndividuDto refIndividuDto : result) {
				RefAffectDocumentDto refAffectDocumentDto = new RefAffectDocumentDto();
				refAffectDocumentDto.setIdentifiantIndividu(refIndividuDto
						.getIdentifiant());
				refAffectDocumentDto.setIdIndividu(refIndividuDto
						.getId());
				refAffectDocumentDto.setNomLtIndividu(refIndividuDto
						.getNomLatin());
				refAffectDocumentDto.setPrenomLtIndividu(refIndividuDto
						.getPrenomLatin());				
				refAffectDocumentDto.setDateNaissanceIndividu(refIndividuDto
						.getDateNaissance());
				suggestions.add(refAffectDocumentDto);
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
				return (((RefIndividuDto) value).getId().toString());

			}

			else {
				return value.toString();
			}
		}
	}

}
