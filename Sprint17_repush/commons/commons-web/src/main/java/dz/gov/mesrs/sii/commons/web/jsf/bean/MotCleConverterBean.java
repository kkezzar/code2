package dz.gov.mesrs.sii.commons.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dz.gov.mesrs.sii.commons.web.util.DocUtilConstants;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;

/**
 * 
 * @author yselmane on : 8 avr. 2014 18:13:41
 */
@ManagedBean(name = "motCleConverterBean")
@RequestScoped
public class MotCleConverterBean implements Converter, Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author SELMANE Yazid on : 4 f�vr. 2014 12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(MotCleConverterBean.class);

	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	/**
	 * 
	 * [MotCleConverter.MotCleConverter()] Constructor
	 * 
	 * @author yselmane on : 3 avr. 2014 09:34:51
	 */
	public MotCleConverterBean() {
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		log.info("getAsObject submittedValue: id===" + submittedValue);
		if (submittedValue == null || submittedValue.trim().equals("null") || submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				NomenclatureDto nomenclatureDto = nomenclatureService
						.findById((Integer.parseInt(submittedValue.trim())));

				return nomenclatureDto;

			} catch (NumberFormatException exception) {
				exception.printStackTrace();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
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
			suggestions = nomenclatureService.findByNcNameLikeLibellefr(DocUtilConstants.NC_MOT_CLE_NAME, query);

			log.info("resultat --- isnull == " + suggestions == null);
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

	/**
	 * [MotCleConverter.nomenclatureService] Getter
	 * 
	 * @author rlaib on : 20 nov. 2014 17:04:42
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [MotCleConverter.nomenclatureService] Setter
	 * 
	 * @author rlaib on : 20 nov. 2014 17:04:42
	 * @param nomenclatureService
	 *            the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

}
