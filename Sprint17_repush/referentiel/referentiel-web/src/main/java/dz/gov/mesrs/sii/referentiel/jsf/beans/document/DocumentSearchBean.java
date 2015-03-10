/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author yselmane
 * 
 */
@ManagedBean(name = "documentSearchBean")
@ViewScoped
public class DocumentSearchBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(DocumentSearchBean.class);

	private static ResourceBundle bundleDocument;

	private String folder_upload;
	private String folder_temp;

	private String searchValue;

	private String searchAdv;
	
	private String searchListNomenclat;

	private String separator = "|";

	@ManagedProperty(value = "#{refDocumentServiceImpl}")
	private RefDocumentService refDocumentServiceImpl;

	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;
	
	@ManagedProperty(value = "#{nomenclatureServiceImpl}")
	private NomenclatureService nomenclatureServiceImpl;


	private RefDocumentDto refDocumentDto;
	private RefDocumentDto searchDto;

	private List<RefDocumentDto> listRefDocumentDto;

	private String idfDoc;

	private List<NomenclatureDto> nomenclatureDtoList;

	/**
	 * Méthode de chargement du managed bean recherche document après l'appel du
	 * constructeur [DocumentSearchBean.init] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:19:57
	 */
	@PostConstruct
	public void init() {

		if (this.searchValue != null && !this.searchValue.equals("null")&&  !this.searchValue.isEmpty()) {
			listRefDocumentDto = refDocumentServiceImpl
					.findGeneric(this.searchValue);
		} else {

			System.err.println("searchAdv-----" + searchAdv);
			System.err.println("searchListNomenclat-----" + searchListNomenclat);
			
			
			if (searchAdv != null && !searchAdv.equals("null") && !searchAdv.isEmpty()) {
				searchDto = this.construireSearchDto(searchAdv);

			}
			
			if (searchListNomenclat != null && !searchListNomenclat.equals("null")  && !searchListNomenclat.isEmpty() ) {
				nomenclatureDtoList = this.construireListNomenclatureDto(searchListNomenclat);
			}
			
			if((searchDto!=null && !searchDtoIsEmpty(searchDto))
					|| (nomenclatureDtoList!=null && !nomenclatureDtoList.isEmpty())){
				
				listRefDocumentDto = refDocumentServiceImpl.findAdvanced(
					searchDto, nomenclatureDtoList);
			}
						
		}

		this.loadParamDocument();
		
		if (searchDto == null)
			searchDto = new RefDocumentDto();

	}

	/**
	 * Constructueur du managedBean Recherche Document
	 * [DocumentSearchBean.DocumentSearchBean()] Constructor
	 * 
	 * @author yselmane on : 8 avr. 2014 18:19:22
	 */
	public DocumentSearchBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		bundleDocument = facesContext.getApplication().getResourceBundle(
				facesContext, RefUtilConstant.DOCUMENT_MESSAGES_FILE_NAME);

		

	}

	/**
	 * charger les paramètres des documents
	 * [DocumentComponentBean.loadParamDocument] Method
	 * 
	 * @author yselmane on : 20 avr. 2014 16:51:10
	 */
	private void loadParamDocument() {
        	folder_upload = configHolder.getDocumentUploadFolder() + "/";
        	folder_temp = configHolder.getDocumentTempFolder() + "/";

	}

	public String getSearchValue() {

		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		if ((searchValue != null) && (searchValue.equals("null"))) {
			this.searchValue = null;
		} else
			this.searchValue = searchValue;
	}

	public String getSearchAdv() {
		return searchAdv;
	}

	public void setSearchAdv(String searchAdv) {
		if ((searchAdv != null) && (searchAdv.equals("null"))) {
			this.searchAdv = null;
		}
		this.searchAdv = searchAdv;
	}
	
	
	public String getSearchListNomenclat() {
		return searchListNomenclat;
	}

	
	public void setSearchListNomenclat(String searchListNomenclat) {
		if ((searchListNomenclat != null) && (searchListNomenclat.equals("null"))) {
			this.searchListNomenclat = null;
		}
		this.searchListNomenclat = searchListNomenclat;
	}

	public List<RefDocumentDto> getListRefDocumentDto() {	
		return listRefDocumentDto;
	}

	public void setListRefDocumentDto(List<RefDocumentDto> listRefDocumentDto) {
		this.listRefDocumentDto = listRefDocumentDto;
	}

	public RefDocumentDto getRefDocumentDto() {
		return refDocumentDto;

	}

	public void setRefDocumentDto(RefDocumentDto refDocumentDto) {
		this.refDocumentDto = refDocumentDto;
	}

	public String getIdfDoc() {

		return idfDoc;
	}

	public void setIdfDoc(String idfDoc) {
		if ((idfDoc != null) && (idfDoc.equals("null"))) {
			this.idfDoc = null;
		} else
			this.idfDoc = idfDoc;
	}

	/**
	 * Naviguer à la page de consultation du document
	 * [DocumentSearchBean.toDetailsDocument] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:17:50
	 * @return outcome de nouveau document
	 */
	public String toDetailsDocument() {
		return "toDetailsDocument";
	}

	/**
	 * naviguer à la page d'ajout d'un nouveau document
	 * [DocumentSearchBean.toNewDocument] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:16:55
	 * @return outcome de nouveau document
	 */
	public String toNewDocument() {
		return "toNewDocument";
	}

	/**
	 * naviguer à la page de modification du document
	 * [DocumentSearchBean.toEditDocument] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:20:59
	 * @return outcome edit document
	 */
	public String toEditDocument() {

		return "toEditDocument";
	}

	/**
	 * naviguer à la page de recherche du document
	 * [DocumentSearchBean.toSearchDocument] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:21:37
	 * @return outcome recherche document
	 */
	public String toSearchDocument() {
		return "toSearchDocument";
	}

	/**
	 * naviguer à la page de recherche du document, retour en arrière
	 * [DocumentSearchBean.back] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:22:04
	 * @return outcome retour en arrière
	 */
	public String back() {
		return "toSearchDocument";
	}

	/**
	 * Supprimer les doublons dans une liste de mots clés
	 * [DocumentComponentBean.deleteDuplicateMotsCles] Method
	 * 
	 * @author yselmane on : 3 avr. 2014 12:35:04
	 */

	public void deleteDuplicateMotsCles() {

		if (nomenclatureDtoList != null && !nomenclatureDtoList.isEmpty()) {

			Object[] copie = nomenclatureDtoList.toArray();

			for (Object current : copie) {
				if (nomenclatureDtoList.indexOf(current) != nomenclatureDtoList
						.lastIndexOf(current)) {
					nomenclatureDtoList.remove(nomenclatureDtoList
							.lastIndexOf(current));
				}
			}

		}

	}

	public RefDocumentService getRefDocumentServiceImpl() {
		return refDocumentServiceImpl;
	}

	public void setRefDocumentServiceImpl(
			RefDocumentService refDocumentServiceImpl) {
		this.refDocumentServiceImpl = refDocumentServiceImpl;
	}

	public RefDocumentDto getSearchDto() {
		return searchDto;
	}

	public void setSearchDto(RefDocumentDto searchDto) {
		this.searchDto = searchDto;
	}

	public List<NomenclatureDto> getNomenclatureDtoList() {
		return nomenclatureDtoList;
	}

	public void setNomenclatureDtoList(List<NomenclatureDto> nomenclatureDtoList) {
		this.nomenclatureDtoList = nomenclatureDtoList;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
	    this.configHolder = configHolder;
	}
	
	public ConfigHolder getConfigHolder() {
	    return configHolder;
	}
		
	/**
	 * [DocumentSearchBean.nomenclatureServiceImpl] Getter 
	 * @author yselmane on : 18 mai 2014  16:45:48
	 * @return the nomenclatureServiceImpl
	 */
	public NomenclatureService getNomenclatureServiceImpl() {
		return nomenclatureServiceImpl;
	}

	/**
	 * [DocumentSearchBean.nomenclatureServiceImpl] Setter 
	 * @author yselmane on : 18 mai 2014  16:45:48
	 * @param nomenclatureServiceImpl the nomenclatureServiceImpl to set
	 */
	public void setNomenclatureServiceImpl(
			NomenclatureService nomenclatureServiceImpl) {
		this.nomenclatureServiceImpl = nomenclatureServiceImpl;
	}

	/**
	 * Method Find the list of Document by the the search input
	 * [DocumentSearchBean.findGeneric] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:14:18
	 */
	public void findGeneric() {
		FacesMessage msg = new FacesMessage();
		log.info("Find generic Document ----->>>>>>>>>");
		setSearchAdv(null);
		setSearchListNomenclat(null);
		searchDto =  new RefDocumentDto();
		nomenclatureDtoList = new ArrayList<NomenclatureDto>();
		
		try {
			List<RefDocumentDto> result = refDocumentServiceImpl.findGeneric(
					SessionValues.getIdfEtablissement(), this.searchValue);
			if (result == null || result.isEmpty()) {
				setListRefDocumentDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleDocument.getString("document_warn_info")
						+ ": "
						+ bundleDocument
								.getString("document_warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				setListRefDocumentDto(result);
			}

		} catch (Exception e) {
			setListRefDocumentDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument.getString("document_error_echec")
					+ ": "
					+ bundleDocument
							.getString("document_error_recherche_generic"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	/**
	 * Method to Find the list of individu by the the advanced search
	 * [DocumentSearchBean.findAdvanced] Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:15:28
	 */
	public void findAdvanced() {
		FacesMessage msg = new FacesMessage();
		log.info("Find Advanced Document ----->>>>>>>>>");

		setSearchValue(null);

		setSearchAdv(this.documentDtoString(searchDto));
		setSearchListNomenclat(this.nomenclatureListDtoString(nomenclatureDtoList));
		
		try {
			
			if(!searchDtoIsEmpty(searchDto) || (nomenclatureDtoList!=null && !nomenclatureDtoList.isEmpty())){
				deleteDuplicateMotsCles();
	
				List<RefDocumentDto> result = refDocumentServiceImpl.findAdvanced(
						SessionValues.getLcLatinEtablissement(), searchDto,
						nomenclatureDtoList);
	
				if (result == null || result.isEmpty()) {
					setListRefDocumentDto(null);
					msg.setSeverity(FacesMessage.SEVERITY_WARN);
					msg.setSummary(bundleDocument.getString("document_warn_info")
							+ ": "
							+ bundleDocument
									.getString("document_warn_info_aucun_result"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					setListRefDocumentDto(result);
	
				}
			}
			else{
				setListRefDocumentDto(null);
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleDocument.getString("document_warn_info")
						+ ": "
						+ bundleDocument
								.getString("document_warn_info_aucun_result"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
				
		} catch (Exception e) {
			setListRefDocumentDto(null);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument.getString("document_error_echec")
					+ ": "
					+ bundleDocument
							.getString("document_error_recherche_advanced"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * Renvoie le fichier à ouvrir ou à télécharger [DocumentSearchBean.getFile]
	 * Method
	 * 
	 * @author yselmane on : 8 avr. 2014 18:15:59
	 * @return un fichier
	 */
	public StreamedContent getFile() {

		FacesMessage msg = new FacesMessage();

		if (idfDoc != null && !idfDoc.isEmpty()) {
			refDocumentDto = refDocumentServiceImpl.findById(Integer
					.parseInt(idfDoc));
		}

		if (refDocumentDto != null && refDocumentDto.getUrl() != null
				&& !refDocumentDto.getUrl().isEmpty()) {

			String path = folder_upload + refDocumentDto.getUrl();

			String contentType = FacesContext.getCurrentInstance()
					.getExternalContext().getMimeType(path);
			try {
				return new DefaultStreamedContent(new FileInputStream(path),
						contentType, "document_"
								+ refDocumentDto.getTitreDocument());

			} catch (FileNotFoundException e) {
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary(bundleDocument
						.getString("document_error_download_file"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				e.printStackTrace();
				return new DefaultStreamedContent();
			}
		} else {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument
					.getString("document_error_empty_file"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return new DefaultStreamedContent();
		}

	}

	
	/**
	 * Supprime les fichiers temporaires non uploadé
	 * [DocumentSearchBean.deleteTempFiles] Method 
	 * @author yselmane  on : 19 mai 2014  09:43:45
	 */
	public void deleteTempFiles() {

		Path dir = Paths.get(folder_temp);
		FacesMessage msg = new FacesMessage();

		long countSize = 0;
		int countFiles = 0;

		try {
			DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
			for (Path file : ds) {

				long tmp = Files.size(file);

				Files.deleteIfExists(file);

				countSize += tmp;
				countFiles++;
			}

			msg.setSeverity(FacesMessage.SEVERITY_INFO);

			msg.setSummary(bundleDocument.getString("document_warn_info")
					+ ": "
					+ countFiles
					+ " "
					+ bundleDocument
							.getString("document_succes_delete_temporary_files")
					+ " "
					+ countSize
					/ 1024
					+ " KO "
					+ bundleDocument
							.getString("document_succes_volume_recupere"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (IOException e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument
					.getString("document_error_delete_temporary_files"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	
	/**
	 * Reconstruire le DTO de recherche avancée à partir de stringtokenzier
	 * [DocumentSearchBean.construireSearchDto] Method 
	 * @author yselmane  on : 19 mai 2014  09:37:07
	 * @param token
	 * @return le Document Dto de recherche
	 */
	public RefDocumentDto construireSearchDto(String token) {

		StringTokenizer tokenizer = new StringTokenizer(token, separator, false);
		RefDocumentDto docResult = new RefDocumentDto();

		Object nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement() : null;
	
		if (nextToken != null) {// titre
			if (!((String) nextToken).equals(" "))
				docResult.setNomEntite((String) nextToken);
			
		}

		nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement(): null;
		if (nextToken != null) {// titre
			if (!((String) nextToken).equals(" "))
				docResult.setTitreDocument((String) nextToken);
			
		}
		
		nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement()	: null;
		if (nextToken != null) {// reference
			if (!((String) nextToken).equals(" "))
				docResult.setReferenceDocument((String) nextToken);
			
		}
	
		nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement()	: null;

		if (nextToken != null) {// classement
			if (!((String) nextToken).equals("0"))
				docResult.setClassementId(Integer
						.parseInt(((String) nextToken)));
		}

		nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement()	: null;
		if (nextToken != null) {// type
			if (!((String) nextToken).equals("0"))
				docResult.setTypeDocumentId(Integer
						.parseInt(((String) nextToken)));
		}

	   		nextToken = (tokenizer.hasMoreElements()) ? tokenizer.nextElement()	: null;
		if (nextToken != null) {// nature
			if (!((String) nextToken).equals("0"))
				docResult.setNatureDocumentId(Integer.parseInt(((String) nextToken)));
			
		}

		return docResult;
	}

	
	/**
	 * Transformer le DTO de recherche avancée en une chaine pour la passer en paramètre en mode Request
	 * [DocumentSearchBean.documentDtoString] Method 
	 * @author yselmane  on : 19 mai 2014  09:38:55
	 * @param refDocumentDto
	 * @return 
	 */
	public String documentDtoString(RefDocumentDto refDocumentDto) {

		StringBuffer buffer = new StringBuffer();
		
		// verification si refDocumentDto à construire est null
		boolean bool= false;
		
		if(refDocumentDto.getNomEntite() != null && !refDocumentDto.getNomEntite().isEmpty()) {
			buffer.append(refDocumentDto.getNomEntite());
			bool=true;
		}else buffer.append(" ");
		
		buffer.append(separator);
		
		if (refDocumentDto.getTitreDocument() != null && !refDocumentDto.getTitreDocument().isEmpty()){
			buffer.append(refDocumentDto.getTitreDocument());
			bool=true;
		}else buffer.append(" ");
				
		buffer.append(separator);
		
		if(refDocumentDto.getReferenceDocument() != null && !refDocumentDto.getReferenceDocument().isEmpty() ){
			buffer.append(refDocumentDto.getReferenceDocument());
			bool=true;
		}else buffer.append(" ");
		
		buffer.append(separator);
		
		if(refDocumentDto.getClassementId() != null ){
			buffer.append(refDocumentDto.getClassementId());
			bool=true;
		}else buffer.append("0");
				
		buffer.append(separator);
		
		if(refDocumentDto.getTypeDocumentId() != null ){
			buffer.append( refDocumentDto.getTypeDocumentId());
			bool=true;
		}else buffer.append("0");
	
		buffer.append(separator);
		
		if(refDocumentDto.getNatureDocumentId() != null ){
			buffer.append(refDocumentDto.getNatureDocumentId());	
			bool=true;
		}else buffer.append("0");
	
		buffer.append(separator);

		System.err.println("token --------------" + buffer.toString());
		
		return bool? buffer.toString():null;
	}
	
	
	/**
	 * Transformer la liste des mots clés en String pour la passer en paramètre en mode Request
	 * [DocumentSearchBean.nomenclatureListDtoString] Method 
	 * @author yselmane  on : 19 mai 2014  09:40:35
	 * @param nomenclatureDtoList
	 * @return
	 */
	public String nomenclatureListDtoString(List<NomenclatureDto> nomenclatureDtoList) {

		StringBuffer buffer = new StringBuffer();
		
		if(nomenclatureDtoList != null && !nomenclatureDtoList.isEmpty())
		  for (NomenclatureDto nomenclatureDto : nomenclatureDtoList) {
			buffer.append(nomenclatureDto.getId() != null ? nomenclatureDto.getId() : " ");
			buffer.append(separator);
		  }
		
		System.err.println("token --------------" + buffer.toString());
		return buffer.toString().isEmpty()? null:buffer.toString();
	}
	
	
	/**
	 * Reconstruire la liste des mots clé à partir de la StringTokenzier
	 * [DocumentSearchBean.construireListNomenclatureDto] Method 
	 * @author yselmane  on : 19 mai 2014  09:41:19
	 * @param token
	 * @return
	 */
	public List<NomenclatureDto> construireListNomenclatureDto(String token) {

		StringTokenizer tokenizer = new StringTokenizer(token, separator, false);
	
		Object nextToken; 
		List<NomenclatureDto> list= new ArrayList<NomenclatureDto>();
		while (tokenizer.hasMoreElements()){
			nextToken=  tokenizer.nextElement() ;
			
			if (!((String) nextToken).equals(" ")){
				NomenclatureDto n= nomenclatureServiceImpl.findById(Integer.parseInt((String) nextToken));
			    list.add(n);			
		     }
	   }
		
	 return list.isEmpty()? null:list;
	 
	}

	
	/**
	 * Test si l'objet de recherche document est vide de paramètre
	 * [DocumentSearchBean.searchDtoIsEmpty] Method 
	 * @author yselmane  on : 20 mai 2014  11:36:06
	 * @param refDocumentDto
	 * @return vrai ou faux
	 */
	public boolean searchDtoIsEmpty(RefDocumentDto refDocumentDto){
		boolean bool= true;
		
		if(refDocumentDto.getTitreDocument()!=null && !refDocumentDto.getTitreDocument().isEmpty() )
			bool =false;
		
		if(refDocumentDto.getReferenceDocument()!=null && !refDocumentDto.getReferenceDocument().isEmpty() )
			bool =false;
		
		if(refDocumentDto.getNomEntite()!=null && !refDocumentDto.getNomEntite().isEmpty() )
			bool =false;
		
		if(refDocumentDto.getClassementId()!=null )
			bool =false;
		
		if(refDocumentDto.getNatureDocumentId()!=null )
			bool =false;
		
		if(refDocumentDto.getTypeDocumentId()!=null)
			bool =false;							
			
		return bool;
	}
}
