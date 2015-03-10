/**
 * [dz.gov.mesrs.sii.refenrentiel.jsf.beans.affectation.DocumentConverter.java] 
 * @author SELMANE Yazid on : 4 f�vr. 2014  12:08:58
 */
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
import javax.faces.convert.ConverterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

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

	private static ResourceBundle bundleDocument;
	
	@ManagedProperty(value = "#{refDocumentServiceImpl}")
	private RefDocumentService refDocumentServiceImpl;

	/**
	 * [DocumentConverter.DocumentConverter()] Constructor
	 * 
	 * @author SELMANE Yazid on : 4 mars 2014 12:08:58
	 */
	public DocumentConverter() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDocument = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.DOCUMENT_MESSAGES_FILE_NAME);
	}

	public RefDocumentService getRefDocumentServiceImpl() {
		return refDocumentServiceImpl;
	}

	public void setRefDocumentServiceImpl(
			RefDocumentService refDocumentServiceImpl) {
		this.refDocumentServiceImpl = refDocumentServiceImpl;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String submittedValue) {
		log.info("getAsObject submittedValue:" + submittedValue);
		if (submittedValue.trim().equals("")) {
			return "";
		} else {
			try {
				RefDocumentDto refDocumentDto = refDocumentServiceImpl
						.findById(Integer.parseInt(submittedValue.trim()));
				return refDocumentDto;

			} catch (NumberFormatException exception) {

				FacesMessage msg = new FacesMessage();

				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleDocument.getString("document_warn_info")
						+ ": "
						+ bundleDocument
								.getString("document_error_conversion_document"));			
				
				throw new ConverterException(msg);
				
				//FacesContext.getCurrentInstance().addMessage(null, msg);
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
			 documentSearch.setIdentifiant(query);	
			suggestions = refDocumentServiceImpl.findAdvanced(documentSearch, null);
	

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

}
