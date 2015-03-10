/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.DocumentConverter.java] 
 * @author SELMANE Yazid on : 4 f�vr. 2014  12:08:58
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.document;

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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefMotCleService;

/**
 * @author SELMANE Yazid on : 4 févr. 2014 12:08:58
 */
@ManagedBean(name = "documentConverter")
@RequestScoped
public class DocumentConverter implements Converter, Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author SELMANE Yazid on : 4 mars 2014 12:44:50
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DocumentConverter.class);

	private ResourceBundle bundleDocument;

	@ManagedProperty("#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureService;

	@ManagedProperty("#{refDocumentServiceImpl}")
	private RefDocumentService refDocumentService;

	@ManagedProperty("#{refMotCleServiceImpl}")
	private RefMotCleService refMotCleServiceImpl;

	/**
	 * [DocumentConverter.DocumentConverter()] Constructor
	 * 
	 * @author SELMANE Yazid on : 4 mars 2014 12:08:58
	 */
	public DocumentConverter() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDocument = facesContext.getApplication().getResourceBundle(
				facesContext, DocUtilConstants.DOCUMENT_BUNDLE_MSG_NAME);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String submittedValue) {
		log.info("getAsObject submittedValue:" + submittedValue);
		if (submittedValue == null || submittedValue.trim().isEmpty()) {
			return null;
		} else {
			try {
				RefDocumentDto refDocumentDto = refDocumentService
						.findById(Integer.parseInt(submittedValue
								.trim()));
				return refDocumentDto;

			} catch (Exception exception) {

				FacesMessage msg = new FacesMessage();

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleDocument.getString("document_warn_info")
						+ ": "
						+ bundleDocument
								.getString("document_error_conversion_document"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return null;

			}
		}

	}

	/**
	 * @author SELMANE Yazid on : 4 mars 2014 11:43:09
	 * @param query
	 * @return list of suggestions of DOCUMENT
	 * */
	public List<RefDocumentDto> completeDocumentLie(String query) {
		log.info("completeDocumentLie");
		List<RefDocumentDto> suggestions = new ArrayList<RefDocumentDto>();
		try {

			RefDocumentDto documentSearch = new RefDocumentDto();
			documentSearch.setTitreDocument(query);
			documentSearch.setReferenceDocument(query);

			suggestions = refDocumentService
					.findAdvanced(documentSearch, null);

		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return suggestions;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		// log.info("getAsString Object:" + value);
		if (value == null || value.equals("")) {
			return "";
		} else {

			if (value instanceof RefDocumentDto) {
				return (((RefDocumentDto) value).getId());

			}

			else {
				return value.toString();
			}
		}

	}

	public void handleUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage();

		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary(bundleDocument.getString("document_warn_info")
				+ ": "
				+ bundleDocument
						.getString("document_success_unselected_document_lie"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void handleSelect(SelectEvent event) {

		FacesMessage msg = new FacesMessage();

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_warn_info")
				+ ": "
				+ bundleDocument
						.getString("document_success_selected_document_lie"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * [DocumentConverter.nomenclatureService] Getter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @return the nomenclatureService
	 */
	public NomenclatureService getNomenclatureService() {
		return nomenclatureService;
	}

	/**
	 * [DocumentConverter.nomenclatureService] Setter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @param nomenclatureService the nomenclatureService to set
	 */
	public void setNomenclatureService(NomenclatureService nomenclatureService) {
		this.nomenclatureService = nomenclatureService;
	}

	/**
	 * [DocumentConverter.refDocumentService] Getter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @return the refDocumentService
	 */
	public RefDocumentService getRefDocumentService() {
		return refDocumentService;
	}

	/**
	 * [DocumentConverter.refDocumentService] Setter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @param refDocumentService the refDocumentService to set
	 */
	public void setRefDocumentService(RefDocumentService refDocumentService) {
		this.refDocumentService = refDocumentService;
	}

	/**
	 * [DocumentConverter.refMotCleServiceImpl] Getter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @return the refMotCleServiceImpl
	 */
	public RefMotCleService getRefMotCleServiceImpl() {
		return refMotCleServiceImpl;
	}

	/**
	 * [DocumentConverter.refMotCleServiceImpl] Setter 
	 * @author rlaib on : 20 nov. 2014  17:03:24
	 * @param refMotCleServiceImpl the refMotCleServiceImpl to set
	 */
	public void setRefMotCleServiceImpl(RefMotCleService refMotCleServiceImpl) {
		this.refMotCleServiceImpl = refMotCleServiceImpl;
	}

	
}
