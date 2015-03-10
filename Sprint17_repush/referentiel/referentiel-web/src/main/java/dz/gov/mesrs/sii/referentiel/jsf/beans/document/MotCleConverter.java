package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * 
 * @author yselmane on : 8 avr. 2014 18:13:41
 */
@ManagedBean(name = "motCleConverter")
@RequestScoped
public class MotCleConverter implements Converter, Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author SELMANE Yazid on : 4 f�vr. 2014 12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(MotCleConverter.class);
	private static ResourceBundle bundleDocument;

	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;

	/**
	 * 
	 * [MotCleConverter.MotCleConverter()] Constructor
	 * 
	 * @author yselmane on : 3 avr. 2014 09:34:51
	 */
	public MotCleConverter() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDocument = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.DOCUMENT_MESSAGES_FILE_NAME);
	}

	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String submittedValue) {
		log.info("getAsObject submittedValue: id===" + submittedValue);
		if (submittedValue == null || submittedValue.trim().equals("null")
				|| submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				NomenclatureDto nomenclatureDto = nomenclatureServiceImpl
						.findById(Integer.parseInt(submittedValue.trim()));

				return nomenclatureDto;

			} catch (NumberFormatException exception) {
				FacesMessage msg = new FacesMessage();

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleDocument.getString("document_warn_info")
						+ ": "
						+ bundleDocument
								.getString("document_error_conversion_mot_cle"));

				return null;
			}
		}

	}

	/**
	 * 
	 * [MotCleConverter.completeMotCle] Method
	 * 
	 * @author yselmane on : 3 avr. 2014 09:36:18
	 * @param query
	 * @return list of suggestions of MOT CLE
	 */

	public List<NomenclatureDto> completeMotCle(String query) {

		List<NomenclatureDto> suggestions = new ArrayList<NomenclatureDto>();
		try {
			suggestions = nomenclatureServiceImpl.findByNcNameLikeLibellefr(
					UtilConstant.NC_MOT_CLE_NAME, query);

			log.info("resultat --- " + suggestions == null);
		} catch (Exception e) {
			log.info("exception --->" + e.getMessage());
			return suggestions;
		}

		return suggestions;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value == null || value.equals("")) {
			return "";
		} else {

			if (value instanceof NomenclatureDto) {
				return (((NomenclatureDto) value).getId() + "");
			}

			else {
				return value.toString();
			}
		}

	}

}
