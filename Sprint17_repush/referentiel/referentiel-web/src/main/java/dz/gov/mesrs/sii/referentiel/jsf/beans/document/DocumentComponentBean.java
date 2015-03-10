package dz.gov.mesrs.sii.referentiel.jsf.beans.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.FileUtility;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefMotCleService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * ManagedBean pour le composant d'attachement des documents.Il est à noter que
 * des librairies Java 7 sont utilisées java.nio
 * 
 * @author Yazid SELMANE
 * @version sprint 1.6
 * @since 2014-03-31
 * 
 */

@ManagedBean(name = "documentComponentBean")
@ViewScoped
public class DocumentComponentBean implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(DocumentComponentBean.class);

    private ResourceBundle bundleDocument;

    @ManagedProperty(value = "#{refDocumentServiceImpl}")
    private RefDocumentService refDocumentServiceImpl;

    @ManagedProperty(value = "#{refMotCleServiceImpl}")
    private RefMotCleService refMotCleServiceImpl;

    @ManagedProperty(value = "#{configHolder}")
    private ConfigHolder configHolder;

    @ManagedProperty(value = "#{nomenclatureServiceImpl}")
    private NomenclatureService nomenclatureServiceImpl;

    private RefDocumentDto refDocumentDto;

    private List<RefDocumentDto> listRefDocumentDto;

    private List<RefMotCleDto> listRefMotCleDto;

    private RefDocumentDto attachDocumentDto;

    private RefDocumentDto refParentDocumentDto;

    private RefMotCleDto refMotCleDto;

    private NomenclatureDto nomenclatureDto;

    private List<NomenclatureDto> nomenclatureDtoList;

    private Boolean mode;

    private String idfDoc;

    private String idfDocAttach;

    private Integer idEntity;

    private String entity;

    private StreamedContent file;

    private StreamedContent fileAttache;

    private String folder_upload;
    private String folder_temp;
    private String allow_types;
    private String limit_size;

    @ManagedProperty(value = "#{documentCrudBean}")
    private DocumentCrudBean documentCrudBean;

    /**
     * Méthode Postconstruct du managed Bean [DocumentComponentBean.init] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:31:03
     */
    @PostConstruct
    public void init() {

	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//	readMode = (Boolean) FacesContext.getCurrentInstance()
//			.getExternalContext().getFlash().get("readMode");
	idEntity = (Integer) FacesContext.getCurrentInstance()
			.getExternalContext().getFlash().get("idEntity");
	entity = (String) FacesContext.getCurrentInstance()
			.getExternalContext().getFlash().get("entity");
	// charger les params des documents
	loadParamDocument();
	try {

//	    if (identifiant == null) {
//		identifiant = (String) request.getAttribute("identifiant");
//	    }
//
//	    if (entity == null) {
//		entity = (String) request.getAttribute("entity");
//	    }

	    System.err.println("identifiant------" + idEntity + " entity------" + entity);

	    if (idEntity != null) {

		if (listRefDocumentDto == null || listRefDocumentDto.isEmpty())
		    listRefDocumentDto = refDocumentServiceImpl.findDocumentsOfEntity(
			    DocumentUtility.entityToBo(entity), idEntity+"");

	    }

	    if (idfDocAttach != null && !idfDocAttach.equals("null") && !idfDocAttach.isEmpty()) {

		attachDocumentDto = refDocumentServiceImpl.findById(Integer.parseInt(idfDocAttach));

		if (listRefMotCleDto == null || listRefMotCleDto.isEmpty()) {
		    fillMotsCles();
		    motcleToNomenclature();
		}

		this.mode = true;

	    } else {
		refParentDocumentDto = new RefDocumentDto();
		attachDocumentDto = new RefDocumentDto();
		idfDocAttach = null;
		nomenclatureDto = new NomenclatureDto();

		setMode(false);
	    }

	} catch (Exception e) {

	}

    }

    /**
     * Constructueur du managed Bean comosant attachement documents
     * [DocumentComponentBean.DocumentComponentBean()] Constructor
     * 
     * @author yselmane on : 3 avr. 2014 09:30:07
     */

    public DocumentComponentBean() {
	super();
	FacesContext facesContext = FacesContext.getCurrentInstance();

	bundleDocument = facesContext.getApplication().getResourceBundle(facesContext,
		RefUtilConstant.DOCUMENT_MESSAGES_FILE_NAME);

	nomenclatureDto = new NomenclatureDto();

	nomenclatureDtoList = new ArrayList<NomenclatureDto>();

    }

    /**
     * Retourne la date d'aujourd'hui [DocumentEditBean.getCurrentDate] Method
     * 
     * @author yselmane on : 22 avr. 2014 17:42:10
     * @return today Date
     */
    public Date getCurrentDate() {
	return new Date();
    }

    public List<NomenclatureDto> getNomenclatureDtoList() {
	return nomenclatureDtoList;
    }

    public void setNomenclatureDtoList(List<NomenclatureDto> nomenclatureDtoList) {
	this.nomenclatureDtoList = nomenclatureDtoList;
    }

    public List<RefDocumentDto> getListRefDocumentDto() {
	return listRefDocumentDto;
    }

    public void setListRefDocumentDto(List<RefDocumentDto> listRefDocumentDto) {
	this.listRefDocumentDto = listRefDocumentDto;
    }

    /**
     * @return the refDocumentDto
     */
    public RefDocumentDto getRefDocumentDto() {

	return refDocumentDto;

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
	allow_types = configHolder.getDocumentAllowTypes();
	limit_size = configHolder.getDocumentMaxSize();
    }

    public NomenclatureDto getNomenclatureDto() {
	return nomenclatureDto;
    }

    public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
	this.nomenclatureDto = nomenclatureDto;
    }

    public void setRefDocumentDto(RefDocumentDto refDocumentDto) {
	this.refDocumentDto = refDocumentDto;
    }

    public RefDocumentDto getAttachDocumentDto() {

	return attachDocumentDto;
    }

    public void setAttachDocumentDto(RefDocumentDto attachDocumentDto) {
	this.attachDocumentDto = attachDocumentDto;
    }

    public RefDocumentDto getRefParentDocumentDto() {

	return refParentDocumentDto;
    }

    public void setRefParentDocumentDto(RefDocumentDto refParentDocumentDto) {
	this.refParentDocumentDto = refParentDocumentDto;
    }

    public void setRefMotCleDto(RefMotCleDto refMotCleDto) {
	this.refMotCleDto = refMotCleDto;
    }

    public RefMotCleDto getRefMotCleDto() {
	return refMotCleDto;
    }

    public Boolean getMode() {
	return mode;
    }

    public void setMode(Boolean mode) {
	this.mode = mode;
    }

    public String getFolder_file() {
	return folder_upload;
    }

    public void setFolder_file(String folder_file) {
	this.folder_upload = folder_file;
    }

    // paramètres des documents

    public String getFolder_upload() {
	return folder_upload;
    }

    public void setFolder_upload(String folder_upload) {
	this.folder_upload = folder_upload;
    }

    public String getFolder_temp() {
	return folder_temp;
    }

    public void setFolder_temp(String folder_temp) {
	this.folder_temp = folder_temp;
    }

    public String getAllow_types() {
	return allow_types;
    }

    public void setAllow_types(String allow_types) {
	this.allow_types = allow_types;
    }

    public String getLimit_size() {
	return limit_size;
    }

    public void setLimit_size(String limit_size) {
	this.limit_size = limit_size;
    }

    // identifiant of document table resultat

    public NomenclatureService getNomenclatureServiceImpl() {
	return nomenclatureServiceImpl;
    }

    public void setNomenclatureServiceImpl(NomenclatureService nomenclatureServiceImpl) {
	this.nomenclatureServiceImpl = nomenclatureServiceImpl;
    }

    public void setIdfDocAttach(String idfDocAttach) {
	if ((idfDocAttach != null) && (idfDocAttach.equals("null"))) {
	    this.idfDocAttach = null;
	} else
	    this.idfDocAttach = idfDocAttach;
    }

    public String getIdfDocAttach() {
	return idfDocAttach;
    }

    // identifiant of document attach

    public void setIdfDoc(String idfDoc) {
	if ((idfDoc != null) && (idfDoc.equals("null"))) {
	    this.idfDoc = null;
	} else
	    this.idfDoc = idfDoc;
    }

    public String getIdfDoc() {
	return idfDoc;
    }

    // identifiant of entity

    /*
     * public String getIdentifiant() { return identifiant; }
     * 
     * public void setIdentifiant(String identifiant) { if ((identifiant !=
     * null) && (identifiant.equals("null"))) { this.identifiant = null; } else
     * this.identifiant = identifiant; }
     */

    // entity name

    public String getEntity() {
	return entity;
    }

    public void setEntity(String entity) {
	if ((entity != null) && (entity.equals("null"))) {
	    this.entity = null;
	} else
	    this.entity = entity;
    }

    // document service

    public RefDocumentService getRefDocumentServiceImpl() {
	return refDocumentServiceImpl;
    }

    public void setRefDocumentServiceImpl(RefDocumentService refDocumentServiceImpl) {
	this.refDocumentServiceImpl = refDocumentServiceImpl;
    }

    // mots cles services

    public RefMotCleService getRefMotCleServiceImpl() {
	return refMotCleServiceImpl;
    }

    public void setRefMotCleServiceImpl(RefMotCleService refMotCleServiceImpl) {
	this.refMotCleServiceImpl = refMotCleServiceImpl;
    }

    public List<RefMotCleDto> getListRefMotCleDto() {

	return listRefMotCleDto;
    }

    public void setListRefMotCleDto(List<RefMotCleDto> listRefMotCleDto) {
	this.listRefMotCleDto = listRefMotCleDto;
    }

    /**
     * Charger la liste des mots clés d'un document
     * [DocumentComponentBean.fillMotsCles] Method
     * 
     * @author yselmane on : 2 avr. 2014 16:49:51
     */
    public void fillMotsCles() {
	log.info("id document:" + idfDocAttach);

	if (idfDocAttach != null && !idfDocAttach.isEmpty()) {
	    listRefMotCleDto = refMotCleServiceImpl.findMotsClesDocument(idfDocAttach);
	}

    }

    public void motcleToNomenclature() {

	if (idfDocAttach != null && !idfDocAttach.isEmpty()) {
	    nomenclatureDtoList = refMotCleServiceImpl.findNomenclatureMotsClesDocument(idfDocAttach);

	}
    }

    /**
     * Méthode de téléchargement ou d'ouverture d'un fichier
     * [DocumentComponentBean.getFile] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:29:05
     * @return
     * @throws FileNotFoundException
     */
    public StreamedContent getFile() throws FileNotFoundException {

	String path = null;
	FacesMessage msg = new FacesMessage();

	refDocumentDto = refDocumentServiceImpl.findById(Integer.parseInt(idfDoc));

	if (refDocumentDto != null && refDocumentDto.getUrl() != null && !refDocumentDto.getUrl().isEmpty()) {

	    path = folder_upload + refDocumentDto.getUrl();

	    if (!Files.exists(Paths.get(path))) {
		path = folder_temp + refDocumentDto.getUrl();
	    }

	} else {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_empty_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    this.file = null;

	}

	String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
	try {

	    InputStream inputStream = new FileInputStream(path);
	    file = new DefaultStreamedContent(inputStream, contentType, "document_" + refDocumentDto.getUrl());

	} catch (FileNotFoundException e) {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_download_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    e.printStackTrace();
	    this.file = null;
	}

	return file;

    }

    /**
     * Méthode de téléchargement ou d'ouverture d'un fichier dans la table des
     * la liste des document [DocumentComponentBean.getFileAttache] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:27:11
     * @return Fichier à télécharger
     * @throws FileNotFoundException
     */
    public StreamedContent getFileAttache() throws FileNotFoundException {

	String path = null;
	FacesMessage msg = new FacesMessage();

	attachDocumentDto = refDocumentServiceImpl.findById(Integer.parseInt(idfDocAttach));

	if (attachDocumentDto != null && attachDocumentDto.getUrl() != null && !attachDocumentDto.getUrl().isEmpty()) {

	    path = folder_upload + attachDocumentDto.getUrl();

	    if (!Files.exists(Paths.get(path))) {
		path = folder_temp + attachDocumentDto.getUrl();
	    }

	} else {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_empty_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    this.fileAttache = null;
	}

	String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
	try {

	    InputStream inputStream = new FileInputStream(path);
	    fileAttache = new DefaultStreamedContent(inputStream, contentType, "document_" + attachDocumentDto.getUrl());

	} catch (FileNotFoundException e) {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_download_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    e.printStackTrace();
	    this.fileAttache = null;
	}

	return fileAttache;

    }

    /**
     * Evénement de suppression d'un document
     * [DocumentComponentBean.removeDocument] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:25:57
     * @param selectedDocumentDto
     */

    public void removeDocument(RefDocumentDto selectedDocumentDto) {

	FacesMessage msg = new FacesMessage();

	if ((selectedDocumentDto != null) && (selectedDocumentDto.getId() != null)) {
	    try {
		log.info("deleting  document  " + selectedDocumentDto.getId());

		// delete file physically

		String nomFichier = selectedDocumentDto.getUrl();

		File file = new File(folder_upload + nomFichier);

		if (file.exists() && file.isFile()) {
		    boolean isdeleted = file.delete();
		    System.err.println("is deleted-------" + isdeleted);
		}

		// deleting instance document and its mots cles

		refDocumentServiceImpl.remove(selectedDocumentDto);

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_success_delete_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// rafraichir la listes des documents
		listRefDocumentDto = refDocumentServiceImpl.findDocumentsOfEntity(DocumentUtility.entityToBo(entity),
				idEntity+"");

	    } catch (Exception e) {
		log.info(e.getMessage());
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
			+ bundleDocument.getString("document_error_delete_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	}

    }

    /**
     * Evénement d'attchement d'un fichier
     * [DocumentComponentBean.handleFileUpload] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:25:07
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {

	FacesMessage msg = new FacesMessage();

	try {

	    File targetFolder = new File(folder_temp);

	    InputStream inputStream = event.getFile().getInputstream();

	    // generer un nom de fichier unique
	    String fileUrl = FileUtility.getUidFileName(event.getFile().getFileName());

	    System.err.println("url----------" + fileUrl);

	    OutputStream out = new FileOutputStream(new File(targetFolder, fileUrl));

	    int read = 0;
	    byte[] bytes = new byte[4096];

	    while ((read = inputStream.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	    }

	    inputStream.close();
	    out.flush();
	    out.close();

	    attachDocumentDto.setUrl(fileUrl);

	    msg.setSeverity(FacesMessage.SEVERITY_INFO);
	    msg.setSummary(bundleDocument.getString("document_success_upload_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);

	} catch (IOException e) {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_upload_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    e.printStackTrace();
	}

    }

    /**
     * Méthode de sauvegarde ou mise à jours des informations d'un document
     * [DocumentComponentBean.saveDocument] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:24:25
     */
    public void saveDocument() {

	FacesMessage msg = new FacesMessage();

	try {

	    if (refParentDocumentDto != null) {
		attachDocumentDto.setIdParentDocument(refParentDocumentDto.getId());
		attachDocumentDto.setTitreParentDocument(refParentDocumentDto.getTitreDocument());
		attachDocumentDto.setReferenceParentDocument(refParentDocumentDto.getReferenceDocument());
		attachDocumentDto.setUrlParentDocument(refParentDocumentDto.getUrl());
	    } else {
		attachDocumentDto.setIdParentDocument(null);
	    }

	    if (attachDocumentDto.getDateDocument().after(new Date())) {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_date_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return;
	    }

	    // if url null ie: file not uploading
	    if (attachDocumentDto == null || attachDocumentDto.getUrl() == null || attachDocumentDto.getUrl().isEmpty()) {

		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary(bundleDocument.getString("document_warn_info") + ": "
			+ bundleDocument.getString("document_warn_charge_file"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return;
	    }

	    if (idEntity == null) {

		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
			+ bundleDocument.getString("document_error_passage_params"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return;
	    }

	    if (this.idfDocAttach != null && !this.idfDocAttach.equals("null") && !this.idfDocAttach.isEmpty()) {

		log.info(" ---------- update Document ----");

		RefDocumentDto old = refDocumentServiceImpl.findById(Integer.parseInt(idfDocAttach));
		String oldFile = null;

		// restaure old file name
		if (old != null && !old.getUrl().equals(attachDocumentDto.getUrl())) {
		    oldFile = old.getUrl();

		}

		int presentYear = Calendar.getInstance().get(Calendar.YEAR);

		Calendar c = Calendar.getInstance();
		c.setTime(old.getDateCreation());
		int pastYear = c.get(Calendar.YEAR);

		// old.getDateCreation().getYear() != new Date().getYear()

		if (old.getTypeDocumentId().intValue() != attachDocumentDto.getTypeDocumentId().intValue()
			|| presentYear != pastYear) {

		    String code = refDocumentServiceImpl.generateCodeDocument(attachDocumentDto);

		    // pour précaution seulement
		    RefDocumentDto trouve = refDocumentServiceImpl.findDocumentByCode(code);
		    if (trouve != null && trouve.getId() != attachDocumentDto.getId()) {
			code = refDocumentServiceImpl.generateCodeDocument(attachDocumentDto);
		    }

		    attachDocumentDto.setIdentifiant(code);

		}

		// delete old mots cle
		refMotCleServiceImpl.removeMotsClesDocument(idfDocAttach);

		// add new mots cles
		addMotsClesOnSave();

		attachDocumentDto.setIdEtablissement(SessionValues.getIdEtablissement());

		attachDocumentDto.setDateCreation(new Date());

		refDocumentServiceImpl.update(attachDocumentDto);

		// delete old file
		deleteFileByName(oldFile);

		// deplacer de temp vers upload dossier
		moveFile(attachDocumentDto.getUrl());

		setIdfDocAttach(attachDocumentDto.getId() + "");

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_msg_success_modification"));

		FacesContext.getCurrentInstance().addMessage(null, msg);

		// System.err.println(Calendar.getInstance().YEAR+"");

	    } else {

		log.info(" ---------- verifier unicité document ------ ");

		Boolean attachDocumentDtoFound = refDocumentServiceImpl.findByRefDocumentDto(attachDocumentDto);

		// si document trouve avec le meme titre ou reference
		if (attachDocumentDtoFound) {
		    msg.setSeverity(FacesMessage.SEVERITY_WARN);
		    msg.setSummary(bundleDocument.getString("document_warn_info") + ": "
			    + bundleDocument.getString("document_warn_already_exist"));
		    FacesContext.getCurrentInstance().addMessage(null, msg);

		    setIdfDocAttach(attachDocumentDto.getId() + "");

		    return;

		}

		log.debug("-----save document----");

		setMode(true);

		// generer un identifiant pour document

		attachDocumentDto.setDateCreation(new Date());

		// deplacer de temp vers upload dossier
		moveFile(attachDocumentDto.getUrl());

		// affect entity to document
		attachDocumentDto.setNomEntite(DocumentUtility.entityToBo(entity));

		attachDocumentDto.setIdentifiantEntite(idEntity+"");

		attachDocumentDto.setIdEtablissement(SessionValues.getIdEtablissement());

		String code = refDocumentServiceImpl.generateCodeDocument(attachDocumentDto);

		// pour précaution seulement
		RefDocumentDto trouve = refDocumentServiceImpl.findDocumentByCode(code);
		if (trouve != null && trouve.getId() != attachDocumentDto.getId()) {
		    code = refDocumentServiceImpl.generateCodeDocument(attachDocumentDto);
		}

		attachDocumentDto.setIdentifiant(code);

		Integer idInstance = refDocumentServiceImpl.save(attachDocumentDto);

		attachDocumentDto.setId(idInstance.toString());

		setIdfDocAttach(idInstance.toString());

		addMotsClesOnSave();

		// rafraichir la liste des documents
		listRefDocumentDto = refDocumentServiceImpl.findDocumentsOfEntity(DocumentUtility.entityToBo(entity),
				idEntity+"");

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_msg_success_enregistrement"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	    }// end save doc

	} catch (Exception e) {

	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
		    + bundleDocument.getString("document_error_persistence_document"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);

	    log.info(e.getMessage());
	}
    }

    /**
     * Méthode de déplacement d'un fichier du dossier temporaire vers le dossier
     * upload [DocumentComponentBean.moveFile] Method
     * 
     * @author yselmane on : 13 avr. 2014 10:23:01
     * @param url
     *            path du fichier
     */
    public void moveFile(String url) {

	try {

	    Path Source = Paths.get(folder_temp + url);
	    Path Destination = Paths.get(folder_upload + url);
	    if (Files.exists(Source)) {
		Files.move(Source, Destination, StandardCopyOption.REPLACE_EXISTING);
	    }
	} catch (Exception e) {

	}

    }

    /**
     * Evénement de suppression d'un mot clé d'un document
     * [DocumentComponentBean.removeMotCle] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:23:23
     * @param selectedMotCleDto
     */
    public void removeMotCle(RefMotCleDto selectedMotCleDto) {
	FacesMessage msg = new FacesMessage();
	if ((selectedMotCleDto != null) && (selectedMotCleDto.getId() > 0)) {
	    try {
		log.info("deleting mot cle document id = " + selectedMotCleDto.getId());

		refMotCleServiceImpl.remove(selectedMotCleDto);

		fillMotsCles();

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_success_delete_mot_cle"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    } catch (Exception e) {
		log.info(e.getMessage());
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
			+ bundleDocument.getString("document_error_delete_mot_cle"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	}

    }

    /**
     * Supprimer les doublons dans une liste de mots clés
     * [DocumentComponentBean.deleteDuplicateMotsCles] Method
     * 
     * @author yselmane on : 3 avr. 2014 12:35:04
     */
    public void deleteDuplicateMotsCles() {

	if (nomenclatureDtoList != null && !nomenclatureDtoList.isEmpty()) {
	    System.out.println("taille Liste mots cles besfore " + nomenclatureDtoList.size());

	    Object[] copie = nomenclatureDtoList.toArray();

	    for (Object current : copie) {
		if (nomenclatureDtoList.indexOf(current) != nomenclatureDtoList.lastIndexOf(current)) {
		    nomenclatureDtoList.remove(nomenclatureDtoList.lastIndexOf(current));
		}
	    }

	    System.out.println("taille Liste mots cles after " + nomenclatureDtoList.size());
	}
    }

    /**
     * Méthode d'ajout d'une liste de mots clés à un document, dans le composant
     * attachement des documents [DocumentComponentBean.addMotsClesOnSave]
     * Method
     * 
     * @author yselmane on : 3 avr. 2014 09:21:32
     */
    public void addMotsClesOnSave() {
	FacesMessage msg = new FacesMessage();

	try {
	    if (nomenclatureDtoList != null && !nomenclatureDtoList.isEmpty() && idfDocAttach != null) {

		deleteDuplicateMotsCles();

		for (NomenclatureDto current : nomenclatureDtoList) {

		    RefMotCleDto currentRefMotCleDto = new RefMotCleDto();
		    currentRefMotCleDto.setIdDocument(idfDocAttach);
		    currentRefMotCleDto.setMotCleId(current.getId());

		    refMotCleServiceImpl.save(currentRefMotCleDto);
		}

		fillMotsCles();

	    }

	} catch (Exception e) {
	    log.info(e.getMessage());
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
		    + bundleDocument.getString("document_error_ajout_mots_cles"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	nomenclatureDto = new NomenclatureDto();
    }

    /**
     * Suppression d'un fichier à partir de son nom
     * [DocumentComponentBean.deleteFileByName] Method
     * 
     * @author yselmane on : 9 avr. 2014 14:07:50
     * @param nomFichier
     */
    public void deleteFileByName(String nomFichier) throws IOException {
	if (nomFichier != null && !nomFichier.isEmpty()) {
	    Path target = Paths.get(folder_upload + nomFichier);
	    Files.delete(target);

	}

    }

    /**
     * Evenement de fermeture de la boite de dialogue du composant d'attachement
     * des documents [DocumentComponentBean.close] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:20:58
     */
    public void close() {

	attachDocumentDto = null;
	refParentDocumentDto = null;
	refDocumentDto = null;
	setIdfDocAttach(null);
	setIdfDoc(null);
    }

    /**
     * Evenement d'ouverture de la boite de dialogue dans le composant
     * attachement documents [DocumentComponentBean.open] Method
     * 
     * @author yselmane on : 3 avr. 2014 09:20:13
     * @param selectedDocumentDto
     */
    public void open(RefDocumentDto selectedDocumentDto) {

	setAttachDocumentDto(selectedDocumentDto);
	setIdfDocAttach(selectedDocumentDto.getId());

    }

    /**
     * [DocumentComponentResultBean.documentCrudBean] Getter
     * 
     * @author MAKERRI Sofiane on : 24 mars 2014 16:39:04
     * @return the documentCrudBean
     */
    public DocumentCrudBean getDocumentCrudBean() {
	return documentCrudBean;
    }

    /**
     * [DocumentComponentResultBean.documentCrudBean] Setter
     * 
     * @author MAKERRI Sofiane on : 24 mars 2014 16:39:04
     * @param documentCrudBean
     *            the documentCrudBean to set
     */
    public void setDocumentCrudBean(DocumentCrudBean documentCrudBean) {
	this.documentCrudBean = documentCrudBean;
    }

    /**
     * [DocumentComponentResultBean.crud] Method
     * 
     * @author MAKERRI Sofiane on : 24 mars 2014 16:39:45
     * @param createAllow
     * @param editAllow
     * @param deleteAllow
     */
    public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
	documentCrudBean.setEditAllow(editAllow);
	documentCrudBean.setCreateAllow(createAllow);
	documentCrudBean.setDeleteAllow(deleteAllow);
    }

 

    public void setConfigHolder(ConfigHolder configHolder) {
	this.configHolder = configHolder;
    }

    public ConfigHolder getConfigHolder() {
	return configHolder;
    }
}
