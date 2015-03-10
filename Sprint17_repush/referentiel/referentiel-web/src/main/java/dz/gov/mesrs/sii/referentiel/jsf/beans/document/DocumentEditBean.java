/**
 * 
 */
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.ComboBckBean;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.FileUtility;
import dz.gov.mesrs.sii.refenrentiel.jsf.beans.common.SessionValues;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefMotCleDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefVersionDocumentDto;
import dz.gov.mesrs.sii.referentiel.business.service.NomenclatureService;
import dz.gov.mesrs.sii.referentiel.business.service.RefDocumentService;
import dz.gov.mesrs.sii.referentiel.business.service.RefMotCleService;
import dz.gov.mesrs.sii.referentiel.business.service.RefVersionDocumentService;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * Managed Bean Gerer les documents. Il est à noter que des librairies Java 7
 * sont utilisées java.nio
 * 
 * @author yselmane on : 8 avr. 2014 18:23:53
 */
@ManagedBean(name = "documentEditBean")
@ViewScoped
public class DocumentEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(DocumentEditBean.class);

    private TreeNode root;
    private TreeNode selectedNode;

    private String folder_upload;
    private String folder_temp;
    private String allow_types;
    private String limit_size;

    private static ResourceBundle bundleDocument;

    private String searchValue;

    private String searchAdv;

    private String searchListNomenclat;

    @ManagedProperty(value = "#{refDocumentServiceImpl}")
    private RefDocumentService refDocumentServiceImpl;

    @ManagedProperty(value = "#{refMotCleServiceImpl}")
    private RefMotCleService refMotCleServiceImpl;

    @ManagedProperty(value = "#{nomenclatureServiceImpl}")
    private NomenclatureService nomenclatureServiceImpl;

    @ManagedProperty(value = "#{refVersionDocumentServiceImpl}")
    private RefVersionDocumentService refVersionDocumentServiceImpl;

    @ManagedProperty(value = "#{configHolder}")
    private ConfigHolder configHolder;

    private String idfDoc;

    private String idfVersion;

    private boolean editMode;

    private RefDocumentDto refDocumentDto;

    private RefDocumentDto refParentDocumentDto;

    private NomenclatureDto nomenclatureDto;

    private List<NomenclatureDto> nomenclatureDtoList;

    private RefMotCleDto refMotCleDto;

    private List<RefMotCleDto> listRefMotCleDto;

    private RefVersionDocumentDto refVersionDocumentDto;

    private List<RefVersionDocumentDto> listRefVersionDocumentDto;

    private Boolean mode;

    private StreamedContent file;

    private StreamedContent fileVersion;

    @ManagedProperty(value = "#{comboBckBean}")
    private ComboBckBean comboBckBean;

    @ManagedProperty(value = "#{documentCrudBean}")
    private DocumentCrudBean documentCrudBean;

    public TreeNode getRoot() {
	return root;
    }

    public TreeNode getSelectedNode() {
	return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
	this.selectedNode = selectedNode;
    }

    // public listClassement(){
    // List<NomenclatureDto> list = nomenclatureServiceImpl
    // .findByName(nc_name);
    // for (NomenclatureDto nomenclatureDto : list) {
    // SelectItem selectItem = new SelectItem(nomenclatureDto.getId(),
    // nomenclatureDto.getLibelleLongFr());
    // nomenclatureDtoList.add(selectItem);
    // }
    // return nomenclatureDtoList;
    // }

    /**
     * 
     * [DocumentEditBean.DocumentEditBean()] Constructor
     * 
     * @author yselmane on : 8 avr. 2014 18:24:22
     */
    public DocumentEditBean() {
	super();

	FacesContext facesContext = FacesContext.getCurrentInstance();
	bundleDocument = facesContext.getApplication().getResourceBundle(facesContext,
		RefUtilConstant.DOCUMENT_MESSAGES_FILE_NAME);

	refVersionDocumentDto = new RefVersionDocumentDto();

	nomenclatureDto = new NomenclatureDto();

	nomenclatureDtoList = new ArrayList<NomenclatureDto>();

    }

    /**
     * 
     * [DocumentEditBean.init] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:24:27
     */
    @PostConstruct
    public void init() {

	this.loadParamDocument();

	root = new DefaultTreeNode("Root", null);
	TreeNode node0 = new DefaultTreeNode("Administratif", root);
	TreeNode node1 = new DefaultTreeNode("Recherche", root);

	TreeNode node00 = new DefaultTreeNode("Administratif 1.1", node0);
	TreeNode node01 = new DefaultTreeNode("Administratif 1.2", node0);

	TreeNode node10 = new DefaultTreeNode("Recherche 1.1", node1);

	node1.getChildren().add(new DefaultTreeNode("Recherche 1.1"));
	node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
	node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
	node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
	node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
	root.getChildren().add(new DefaultTreeNode("Technique"));

	if (idfDoc != null && !idfDoc.equals("null") && !idfDoc.isEmpty()) {

	    refDocumentDto = refDocumentServiceImpl.findById(Integer.parseInt(idfDoc));

	    if (refDocumentDto != null && refDocumentDto.getIdParentDocument() != null) {

		refParentDocumentDto = refDocumentServiceImpl.findById(Integer.parseInt(refDocumentDto
			.getIdParentDocument()));

	    }

	    if (listRefMotCleDto == null || listRefMotCleDto.isEmpty()) {
		fillMotsCles();
		motcleToNomenclature();
	    }

	    if (listRefVersionDocumentDto == null || listRefVersionDocumentDto.isEmpty()) {
		listRefVersionDocumentDto = refVersionDocumentServiceImpl.findVersionsOfDocument(idfDoc);
	    }

	    this.setMode(true);

	} else {
	    refDocumentDto = new RefDocumentDto();
	    setDefaultValues();
	    refParentDocumentDto = new RefDocumentDto();
	    refVersionDocumentDto = new RefVersionDocumentDto();
	    nomenclatureDto = new NomenclatureDto();
	    setMode(false);
	}

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

    /**
     * Retourne la date d'aujourd'hui [DocumentEditBean.getCurrentDate] Method
     * 
     * @author yselmane on : 22 avr. 2014 17:42:10
     * @return today Date
     */
    public Date getCurrentDate() {
	return new Date();
    }

    public void setConfigHolder(ConfigHolder configHolder) {
	this.configHolder = configHolder;
    }

    public ConfigHolder getConfigHolder() {
	return configHolder;
    }

    public RefDocumentService getRefDocumentServiceImpl() {
	return refDocumentServiceImpl;
    }

    public void setRefDocumentServiceImpl(RefDocumentService refDocumentServiceImpl) {
	this.refDocumentServiceImpl = refDocumentServiceImpl;
    }

    public RefMotCleService getRefMotCleServiceImpl() {
	return refMotCleServiceImpl;
    }

    public void setRefMotCleServiceImpl(RefMotCleService refMotCleServiceImpl) {
	this.refMotCleServiceImpl = refMotCleServiceImpl;
    }

    public RefVersionDocumentService getRefVersionDocumentServiceImpl() {
	return refVersionDocumentServiceImpl;
    }

    public void setRefVersionDocumentServiceImpl(RefVersionDocumentService refVersionDocumentServiceImpl) {
	this.refVersionDocumentServiceImpl = refVersionDocumentServiceImpl;
    }

    //

    public NomenclatureService getNomenclatureServiceImpl() {
	return nomenclatureServiceImpl;
    }

    public void setNomenclatureServiceImpl(NomenclatureService nomenclatureServiceImpl) {
	this.nomenclatureServiceImpl = nomenclatureServiceImpl;
    }

    public List<NomenclatureDto> getNomenclatureDtoList() {
	return nomenclatureDtoList;
    }

    public void setNomenclatureDtoList(List<NomenclatureDto> nomenclatureDtoList) {
	this.nomenclatureDtoList = nomenclatureDtoList;
    }

    public String getIdfDoc() {

	return idfDoc;
    }

    // id document
    public void setIdfDoc(String idfDoc) {
	if ((idfDoc != null) && (idfDoc.equals("null"))) {
	    this.idfDoc = null;
	    setMode(false);
	} else {
	    this.idfDoc = idfDoc;
	}
    }

    public String getSearchValue() {
	return searchValue;
    }

    public String getIdfVersion() {
	return idfVersion;
    }

    public void setIdfVersion(String idfVersion) {
	if ((idfVersion != null) && (idfVersion.equals("null"))) {
	    this.idfVersion = null;
	} else {
	    this.idfVersion = idfVersion;
	}
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

    // parametre de recherche generique
    public void setSearchValue(String searchValue) {
	if ((searchValue != null) && (searchValue.equals("null"))) {
	    this.searchValue = null;
	} else
	    this.searchValue = searchValue;
    }

    public RefDocumentDto getRefDocumentDto() {
	return refDocumentDto;

    }

    public void setRefDocumentDto(RefDocumentDto refDocumentDto) {
	this.refDocumentDto = refDocumentDto;
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

    public List<RefVersionDocumentDto> getListRefVersionDocumentDto() {

	return listRefVersionDocumentDto;
    }

    public void setListRefVersionDocumentDto(List<RefVersionDocumentDto> listRefVersionDocumentDto) {
	this.listRefVersionDocumentDto = listRefVersionDocumentDto;
    }

    public RefVersionDocumentDto getRefVersionDocumentDto() {

	if (refVersionDocumentDto == null) {
	    refVersionDocumentDto = new RefVersionDocumentDto();
	}
	return refVersionDocumentDto;
    }

    public void setRefVersionDocumentDto(RefVersionDocumentDto refVersionDocumentDto) {
	this.refVersionDocumentDto = refVersionDocumentDto;
    }

    public List<RefMotCleDto> getListRefMotCleDto() {

	return listRefMotCleDto;
    }

    public void setListRefMotCleDto(List<RefMotCleDto> listRefMotCleDto) {
	this.listRefMotCleDto = listRefMotCleDto;
    }

    public Boolean getMode() {
	return mode;
    }

    public void setMode(Boolean mode) {
	this.mode = mode;
    }

    public NomenclatureDto getNomenclatureDto() {
	return nomenclatureDto;
    }

    public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
	this.nomenclatureDto = nomenclatureDto;
    }

    /**
     * Navigue vers la page de recherche des documents
     * [DocumentEditBean.toSearch] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:26:03
     * @return outcome pour la page de recherche de document
     */
    public String toSearch() {

	return "toSearchDocument";
    }

    /**
     * retour en arrière vers la page de recherche documents
     * [DocumentEditBean.back] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:26:43
     * @return outcome recherche document
     */
    public String back() {

	return "toSearchDocument";
    }

    public boolean isEditMode() {
	return editMode;
    }

    public void setEditMode(boolean editMode) {
	this.editMode = editMode;
    }

    /**
     * Enregistrement ou mise à jour des informations d'un document
     * [DocumentEditBean.saveDocument] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:27:34
     */
    public void saveDocument() {

	FacesMessage msg = new FacesMessage();

	try {

	    if (refParentDocumentDto != null) {
		refDocumentDto.setIdParentDocument(refParentDocumentDto.getId());
		refDocumentDto.setTitreParentDocument(refParentDocumentDto.getTitreDocument());
		refDocumentDto.setReferenceParentDocument(refParentDocumentDto.getReferenceDocument());
		refDocumentDto.setUrlParentDocument(refParentDocumentDto.getUrl());
	    } else {
		refDocumentDto.setIdParentDocument(null);
	    }

	    // si date document est dans le future
	    if (refDocumentDto.getDateDocument().after(new Date())) {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_date_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return;

	    }

	    if (refDocumentDto == null || refDocumentDto.getUrl() == null || refDocumentDto.getUrl().isEmpty()) {

		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		msg.setSummary(bundleDocument.getString("document_warn_info") + ": "
			+ bundleDocument.getString("document_warn_charge_file"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return;
	    }

	    if (this.idfDoc != null && !this.idfDoc.equals("null") && !this.idfDoc.equals("")) {

		log.info(" ---------- update Document ----");

		RefDocumentDto old = refDocumentServiceImpl.findById(Integer.parseInt(idfDoc));

		String oldFile = null;

		// supprimer l'ancien fichier physique s'il est mis à jour
		if (old != null && !old.getUrl().equals(refDocumentDto.getUrl())) {
		    oldFile = old.getUrl();

		}

		// Voir si typ de document a changé, pour regénérer
		// l'identifiant

		int presentYear = Calendar.getInstance().get(Calendar.YEAR);

		Calendar c = Calendar.getInstance();
		c.setTime(old.getDateCreation());
		int pastYear = c.get(Calendar.YEAR);

		// old.getDateCreation().getYear() != new Date().getYear()

		if (old.getTypeDocumentId().intValue() != refDocumentDto.getTypeDocumentId().intValue()
			|| presentYear != pastYear) {

		    String code = refDocumentServiceImpl.generateCodeDocument(refDocumentDto);

		    // pour précaution seulement
		    RefDocumentDto trouve = refDocumentServiceImpl.findDocumentByCode(code);
		    if (trouve != null && trouve.getId() != refDocumentDto.getId()) {
			code = refDocumentServiceImpl.generateCodeDocument(refDocumentDto);
		    }

		    refDocumentDto.setIdentifiant(code);

		}

		refDocumentDto.setDateCreation(new Date());

		refDocumentDto.setIdEtablissement(SessionValues.getIdEtablissement());

		// delete old mots cle
		refMotCleServiceImpl.removeMotsClesDocument(idfDoc);

		// add new mots cles
		addMotsClesOnSave();

		refDocumentServiceImpl.update(refDocumentDto);

		// delete old file
		deleteFileByName(oldFile);

		// deplacer de temp vers upload dossier
		moveFile(refDocumentDto.getUrl());

		setMode(true);

		setIdfDoc(refDocumentDto.getId() + "");

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_msg_success_modification"));

		FacesContext.getCurrentInstance().addMessage(null, msg);

	    } else {

		// Nouveau document

		Boolean refDocumentDtoFound = refDocumentServiceImpl.findByRefDocumentDto(refDocumentDto);

		// si document existe avec meme titre ou reference
		/*
		 * if (refDocumentDtoFound) {
		 * msg.setSeverity(FacesMessage.SEVERITY_WARN);
		 * msg.setSummary(bundleDocument
		 * .getString("document_warn_info") + ": " + bundleDocument
		 * .getString("document_warn_already_exist"));
		 * FacesContext.getCurrentInstance().addMessage(null, msg);
		 * 
		 * setIdfDoc(refDocumentDto.getId() + "");
		 * 
		 * return; }
		 */

		log.info(" ---------- save Document ------ ");

		setMode(true);

		refDocumentDto.setDateCreation(new Date());

		// deplacer de temp vers upload dossier
		moveFile(refDocumentDto.getUrl());

		refDocumentDto.setIdEtablissement(SessionValues.getIdEtablissement());

		String code = refDocumentServiceImpl.generateCodeDocument(refDocumentDto);

		// pour precaution seulement
		RefDocumentDto trouve = refDocumentServiceImpl.findDocumentByCode(code);
		if (trouve != null && trouve.getId() != refDocumentDto.getId()) {
		    code = refDocumentServiceImpl.generateCodeDocument(refDocumentDto);
		}

		refDocumentDto.setIdentifiant(code);

		Integer idInstance = refDocumentServiceImpl.save(refDocumentDto);

		refDocumentDto.setId(idInstance.toString());

		setIdfDoc(refDocumentDto.getId() + "");

		addMotsClesOnSave();

		// Ajouter la version 1.0 du document
		refVersionDocumentDto.setIdDocument(refDocumentDto.getId());
		refVersionDocumentDto.setDateVersion(new Date());
		refVersionDocumentDto.setNumVersion("1.0");
		refVersionDocumentDto.setUrl(refDocumentDto.getUrl());
		refVersionDocumentDto.setObservation("La version initiale.");
		refVersionDocumentServiceImpl.save(refVersionDocumentDto);

		listRefVersionDocumentDto = refVersionDocumentServiceImpl
			.findVersionsOfDocument(refDocumentDto.getId());

		refVersionDocumentDto = new RefVersionDocumentDto();

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
     * Ouverture ou téléchargment d'un document [DocumentEditBean.getFile]
     * Method
     * 
     * @author yselmane on : 8 avr. 2014 18:28:16
     * @return fichier physique du document
     */
    public StreamedContent getFile() {

	String path = null;
	FacesMessage msg = new FacesMessage();

	nomenclatureDto = new NomenclatureDto();

	if (refDocumentDto != null && refDocumentDto.getUrl() != null && !refDocumentDto.getUrl().isEmpty()) {

	    path = folder_upload + refDocumentDto.getUrl();

	    if (!Files.exists(Paths.get(path))) {
		path = folder_temp + refDocumentDto.getUrl();
	    }

	} else {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_empty_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    return new DefaultStreamedContent();
	}

	String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
	try {

	    FileInputStream fis = new FileInputStream(path);
	    file = new DefaultStreamedContent(fis, contentType, "document_" + refDocumentDto.getTitreDocument());

	} catch (FileNotFoundException e) {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_download_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    e.printStackTrace();
	}

	return file;
    }

    /**
     * 
     * [DocumentEditBean.setFileVersion] Method
     * 
     * @author yselmane on : 18 juin 2014 16:31:51
     * @param fileVersion
     */
    public void setFileVersion(StreamedContent fileVersion) {

	this.fileVersion = fileVersion;
    }

    /**
     * Télécharger ou ouvrir le fichier version d'un document
     * [DocumentEditBean.getFileVersion] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:28:50
     * @return fichier version du document
     * @throws FileNotFoundException
     */
    public StreamedContent getFileVersion() throws FileNotFoundException {

	FacesMessage msg = new FacesMessage();

	if (idfVersion != null && !idfVersion.isEmpty()) {
	    refVersionDocumentDto = refVersionDocumentServiceImpl.findById(Integer.parseInt(idfVersion));
	}

	if (refVersionDocumentDto != null && refVersionDocumentDto.getUrl() != null
		&& !refVersionDocumentDto.getUrl().isEmpty()) {

	    String path = folder_upload + refVersionDocumentDto.getUrl();

	    if (!Files.exists(Paths.get(path))) {
		path = folder_temp + refVersionDocumentDto.getUrl();
	    }

	    String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
	    try {

		InputStream inputStream = new FileInputStream(path);

		this.fileVersion = new DefaultStreamedContent(inputStream, contentType, "document_" + "-"
			+ refVersionDocumentDto.getNumVersion() + refVersionDocumentDto.getUrl());

	    } catch (Exception e) {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_download_file"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.fileVersion = null;
	    }

	} else {
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_empty_file"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    this.fileVersion = null;
	}

	return fileVersion;

    }

    /**
     * evénement de chargement d'un fichier [DocumentEditBean.handleFileUpload]
     * Method
     * 
     * @author yselmane on : 8 avr. 2014 18:29:20
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {

	FacesMessage msg = new FacesMessage();

	try {

	    File targetFolder = new File(folder_temp);
	    InputStream inputStream = event.getFile().getInputstream();
	    String fileUrl = FileUtility.getUidFileName(event.getFile().getFileName());

	    System.err.println("fileurl--------------" + fileUrl);
	    OutputStream out = new FileOutputStream(new File(targetFolder, fileUrl));

	    int read = 0;
	    byte[] bytes = new byte[4096];

	    while ((read = inputStream.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	    }

	    inputStream.close();
	    out.flush();
	    out.close();

	    refDocumentDto.setUrl(fileUrl);

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
     * suppression d'un fichier à partir de son nom
     * [DocumentEditBean.deleteFileByName] Method
     * 
     * @author yselmane on : 9 avr. 2014 13:51:04
     */

    public void deleteFileByName(String nomFichier) throws IOException {

	if (nomFichier != null && !nomFichier.isEmpty()) {
	    Path target = Paths.get(folder_upload + nomFichier);
	    Files.delete(target);

	}

    }

    /**
     * suppression physique d'un fichier [DocumentEditBean.deleteFile] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:29:48
     */

    public void deleteFile() {
	if (refDocumentDto != null && refDocumentDto.getUrl() != null && !refDocumentDto.getUrl().isEmpty()) {

	    String nomFichier = refDocumentDto.getUrl();

	    File file = new File(folder_upload + nomFichier);

	    FacesMessage msg = new FacesMessage();

	    if (file.exists() && file.isFile()) {
		boolean isdeleted = file.delete();

		if (isdeleted)
		    refDocumentDto.setUrl(null);

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_success_delete_file"));

		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

	}

    }

    /**
     * Evemnement avant l'affichage d ela boite de dialogue
     * [DocumentEditBean.beforeAdd] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:30:34
     */
    public void beforeAdd() {
	log.info("beforeAdd");
	// this.refMotCleDto = new RefMotCleDto();
	// log.info("beforeAdd:" + refMotCleDto.getMotCleLibelleLongFr());
    }

    /**
     * Suppression d'un mot clé [DocumentEditBean.removeMotCle] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:30:56
     * @param selectedMotCleDto
     *            le mot clé selectionné
     */

    public void removeMotCle(RefMotCleDto selectedMotCleDto) {
	FacesMessage msg = new FacesMessage();
	if ((selectedMotCleDto != null) && (selectedMotCleDto.getId() > 0)) {
	    try {
		log.info("deleting mot cle document id = " + selectedMotCleDto.getId());

		refMotCleServiceImpl.remove(selectedMotCleDto);

		// apres suppression, charger a liste des mots cles
		listRefMotCleDto = refMotCleServiceImpl.findMotsClesDocument(idfDoc);

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
     * Méthode d'ajout d'un mot clé [DocumentEditBean.addMotCle] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:31:28
     */
    public void addMotCle() {
	FacesMessage msg = new FacesMessage();

	try {
	    if (nomenclatureDto != null && idfDoc != null) {

		RefMotCleDto existMotCleDto = refMotCleServiceImpl.findMotCleDocument(idfDoc, nomenclatureDto.getId());

		if (existMotCleDto != null) {

		    msg.setSeverity(FacesMessage.SEVERITY_WARN);
		    msg.setSummary(bundleDocument.getString("document_warn_info") + ": "
			    + bundleDocument.getString("document_warn_mot_cle_already_exist"));
		    FacesContext.getCurrentInstance().addMessage(null, msg);

		} else {

		    refMotCleDto = new RefMotCleDto();
		    refMotCleDto.setIdDocument(idfDoc);
		    refMotCleDto.setMotCleId(nomenclatureDto.getId());

		    refMotCleServiceImpl.save(refMotCleDto);

		    // apres save, charger a liste des mots cles
		    listRefMotCleDto = refMotCleServiceImpl.findMotsClesDocument(idfDoc);

		    msg.setSeverity(FacesMessage.SEVERITY_INFO);
		    msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			    + bundleDocument.getString("document_success_selected_mot_cle"));
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
     * Méthode d'ajout d'une version d'un document
     * [DocumentEditBean.addVersionToDocument] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:31:58
     */
    public void addVersionToDocument() {
	FacesMessage msg = new FacesMessage();

	try {

	    if (idfDoc != null && !idfDoc.isEmpty()) {

		refVersionDocumentDto.setIdDocument(idfDoc);

		refVersionDocumentServiceImpl.save(refVersionDocumentDto);

		// deplacer de temp vers upload dossier
		moveFile(refVersionDocumentDto.getUrl());

		// apres save, charger a liste des versions
		listRefVersionDocumentDto = refVersionDocumentServiceImpl.findVersionsOfDocument(idfDoc);
		refVersionDocumentDto = new RefVersionDocumentDto();
		RequestContext.getCurrentInstance().reset("form:addVersionPanel");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_msg_success_enregistrement"));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	    } else {
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
			+ bundleDocument.getString("document_error_save_version_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

	} catch (Exception e) {
	    log.info(e.getMessage());
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
		    + bundleDocument.getString("document_error_save_version_document"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// refVersionDocumentDto = new RefVersionDocumentDto();
    }

    /**
     * Suppression d'une version d'un document
     * [DocumentEditBean.removeVersionDocument] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:32:23
     * @param selectedVersionDocumentDto
     */

    public void removeVersionDocument(RefVersionDocumentDto selectedVersionDocumentDto) {

	FacesMessage msg = new FacesMessage();

	System.err.println("ref id doc " + selectedVersionDocumentDto.getIdDocument());

	if ((selectedVersionDocumentDto != null) && (selectedVersionDocumentDto.getId() > 0)) {
	    try {
		log.info("deleting version document  " + selectedVersionDocumentDto.getId());

		// delete file physically

		String nomFichier = selectedVersionDocumentDto.getUrl();

		File file = new File(folder_upload + nomFichier);

		if (file.exists() && file.isFile()) {
		    boolean isdeleted = file.delete();
		    System.err.println("is deleted-------" + isdeleted);
		}

		// deleting instance version

		refVersionDocumentServiceImpl.remove(selectedVersionDocumentDto);

		// apres suppression, charger a liste des versions
		listRefVersionDocumentDto = refVersionDocumentServiceImpl.findVersionsOfDocument(idfDoc);

		refVersionDocumentDto = new RefVersionDocumentDto();

		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
			+ bundleDocument.getString("document_success_delete_version_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    } catch (Exception e) {
		log.info(e.getMessage());
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
			+ bundleDocument.getString("document_error_delete_version_document"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	}

    }

    /*
	 * 
	 */
    /**
     * Evénement charger versionfichier du composant uploadfile -
     * versiondocument [DocumentEditBean.handleFileUploadVersion] Method
     * 
     * @author yselmane on : 8 avr. 2014 18:32:45
     * @param event
     */
    public void handleFileUploadVersion(FileUploadEvent event) {

	FacesMessage msg = new FacesMessage();

	try {

	    File targetFolder = new File(folder_temp);
	    InputStream inputStream = event.getFile().getInputstream();
	    String fileUrl = FileUtility.getUidFileName(event.getFile().getFileName());

	    OutputStream out = new FileOutputStream(new File(targetFolder, fileUrl));

	    refVersionDocumentDto.setUrl(fileUrl);

	    int read = 0;
	    byte[] bytes = new byte[4096];

	    while ((read = inputStream.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	    }

	    inputStream.close();
	    out.flush();
	    out.close();

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
	    e.printStackTrace();
	}

    }

    /**
     * Charger la liste des mots clés d'un document
     * [DocumentComponentBean.fillMotsCles] Method
     * 
     * @author yselmane on : 2 avr. 2014 16:49:51
     */
    public void fillMotsCles() {
	log.info("id document:" + idfDoc);

	if (idfDoc != null && !idfDoc.isEmpty()) {
	    listRefMotCleDto = refMotCleServiceImpl.findMotsClesDocument(idfDoc);
	}

    }

    public void motcleToNomenclature() {

	if (idfDoc != null && !idfDoc.isEmpty()) {
	    nomenclatureDtoList = refMotCleServiceImpl.findNomenclatureMotsClesDocument(idfDoc);

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
	    if (nomenclatureDtoList != null && !nomenclatureDtoList.isEmpty() && idfDoc != null) {

		deleteDuplicateMotsCles();

		for (NomenclatureDto current : nomenclatureDtoList) {

		    RefMotCleDto currentRefMotCleDto = new RefMotCleDto();
		    currentRefMotCleDto.setIdDocument(idfDoc);
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
     * [DocumentEditBean.setDefaultValues] Method
     * 
     * @author MAKERRI Sofiane on : 29 avr. 2014 12:16:49
     */
    public void setDefaultValues() {
	refDocumentDto.setClassementId(comboBckBean.getDefaultClassement());
	refDocumentDto.setTypeDocumentId(comboBckBean.getDefaultTypeDocument());
	refDocumentDto.setNatureDocumentId(comboBckBean.getDefaultNatureDocument());
	refDocumentDto.setLangueId(comboBckBean.getDefaultLangue());
    }

    /**
     * [DocumentEditBean.comboBckBean] Getter
     * 
     * @author MAKERRI Sofiane on : 29 avr. 2014 12:15:03
     * @return the comboBckBean
     */
    public ComboBckBean getComboBckBean() {
	return comboBckBean;
    }

    /**
     * [DocumentEditBean.comboBckBean] Setter
     * 
     * @author MAKERRI Sofiane on : 29 avr. 2014 12:15:03
     * @param comboBckBean
     *            the comboBckBean to set
     */
    public void setComboBckBean(ComboBckBean comboBckBean) {
	this.comboBckBean = comboBckBean;
    }

    public DocumentCrudBean getDocumentCrudBean() {
	return documentCrudBean;
    }

    public void setDocumentCrudBean(DocumentCrudBean documentCrudBean) {
	this.documentCrudBean = documentCrudBean;
    }

    public void crud(boolean createAllow, boolean editAllow, boolean deleteAllow) {
	documentCrudBean.setEditAllow(editAllow);
	documentCrudBean.setCreateAllow(createAllow);
	documentCrudBean.setDeleteAllow(deleteAllow);
    }

}
