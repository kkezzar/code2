package dz.gov.mesrs.sii.fve.web.jsf.bean.document;

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
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import dz.gov.mesrs.sii.commons.business.util.FileUtility;
import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierEtudiantDto;
import dz.gov.mesrs.sii.fve.business.service.cursus.DossierEtudiantService;
import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.dossier.DossierEtudiantBean;
import dz.gov.mesrs.sii.referentiel.business.util.ConfigHolder;

/**
 * managed bean pour le chargement et la visualisation de la photo etudiant
 * 
 * @author yselmane on : 24 juin 2014 17:09:48
 */
@ManagedBean(name = "photoBeanFve")
@RequestScoped
public class PhotoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PhotoBean.class);

	private final ResourceBundle bundleDocument;

	/**
	 * Service proxy de la gestion des PPM.
	 */
	@ManagedProperty(value = "#{configHolder}")
	private ConfigHolder configHolder;

	@ManagedProperty("#{param.editionMode}")
	private String editionMode;

	@ManagedProperty("#{param.dossierEtudiantId}")
	private String dossierEtudiantId;

	@ManagedProperty("#{param.url}")
	private String url;

	private StreamedContent photo;

	@ManagedProperty(value = "#{dossierEtudiantService}")
	private DossierEtudiantService dossierEtudiantService;

	private DossierEtudiantDto dossierEtudiantDto;

	private String folder_photo;
	private String folder_temp;
	private String allow_types_photo;
	private String limit_size_photo;
	private String height_photo;
	private String width_photo;

	@ManagedProperty("#{dossierEtudiantBean}")
	private DossierEtudiantBean dossierEtudiantBean;

	private boolean photo_attache_capture = false;

	private CroppedImage croppedImage;

	// -------------------------

	// private Date dateDocument;
	/**
	 * M�thode Postconstruct du managed Bean [DocumentComponentBean.init] Method
	 * 
	 * @author yselmane on : 3 avr. 2014 09:31:03
	 */
	@PostConstruct
	public void init() {

		// charger les params des documents

		try {

			loadParamPhoto();

			if (dossierEtudiantId != null && !dossierEtudiantId.equals("null") && !dossierEtudiantId.isEmpty()) {

				dossierEtudiantDto = dossierEtudiantService.findById(Integer.parseInt(dossierEtudiantId));

				if (dossierEtudiantDto != null) {
					if (url == null || url.equals("null") || url.isEmpty()) {
						url = dossierEtudiantDto.getPhoto();
						if (url != null && url.trim().isEmpty()) {
							url = null;
						}
					}

					// si le champ photo est a blanc et non null
					if (dossierEtudiantDto.getPhoto() != null && dossierEtudiantDto.getPhoto().trim().isEmpty()) {
						dossierEtudiantDto.setPhoto(null);
					}
				}

			} else {
				dossierEtudiantDto = new DossierEtudiantDto();
			}

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * Constructueur du managed Bean comosant attachement documents
	 * [DocumentComponentBean.DocumentComponentBean()] Constructor
	 * 
	 * @author yselmane on : 3 avr. 2014 09:30:07
	 */

	public PhotoBean() {
		super();
		FacesContext facesContext = FacesContext.getCurrentInstance();

		bundleDocument = facesContext.getApplication().getResourceBundle(facesContext,
				DocUtilConstants.DOCUMENT_BUNDLE_MSG_NAME);

	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	/**
	 * @todo
	 * @author Mounir.MESSAOUDI on : 22 juil. 2014 13:58:32
	 */
	public void crop() {
		System.err.println("crop--------------------------");
		if (croppedImage == null) {

			return;
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		String arquivo = scontext.getRealPath("/temp/");
		System.err.println("---------->" + arquivo);
		String fileUrl = FileUtility.getUidFileName("photo-cropped.png");

		File targetFolder = new File(folder_temp);

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(targetFolder, fileUrl));
			imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
			imageOutput.flush();
			imageOutput.close();
			setUrl(fileUrl);
			dossierEtudiantDto.setPhoto(fileUrl);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Success", "Cropping finished."));
	}

	/**
	 * [PhotoBean.dossierEtudiantService] Getter
	 * 
	 * @author yselmane on : 23 juin 2014 17:00:42
	 * @return the dossierEtudiantService
	 */
	public DossierEtudiantService getDossierEtudiantService() {
		return dossierEtudiantService;
	}

	/**
	 * [PhotoBean.dossierEtudiantService] Setter
	 * 
	 * @author yselmane on : 23 juin 2014 17:00:42
	 * @param dossierEtudiantService
	 *            the dossierEtudiantService to set
	 */
	public void setDossierEtudiantService(DossierEtudiantService dossierEtudiantService) {
		this.dossierEtudiantService = dossierEtudiantService;
	}

	/**
	 * charger les param�tres des documents
	 * [DocumentComponentBean.loadParamDocument] Method
	 * 
	 * @author yselmane on : 20 avr. 2014 16:51:10
	 */
	private void loadParamPhoto() throws Exception {
		folder_photo = configHolder.getPhotoUploadFolder() + "/";
		folder_temp = configHolder.getDocumentTempFolder() + "/";

		// @TODO
		// ServletContext servletContext = (ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext().getContext();
		// folder_temp = servletContext.getRealPath(File.separator + "temp" +
		// File.separator) + "\\";

		// System.err.println("handle: url----------" + arquivoFoto);
		allow_types_photo = configHolder.getPhotoAllowType();
		limit_size_photo = configHolder.getPhotoMaxSize();
		height_photo = configHolder.getPhotoHeight();
		width_photo = configHolder.getPhotoWidth();

	}

	// parametres des documents

	public String getFolder_photo() {
		return folder_photo;
	}

	public void setFolder_photo(String folder_photo) {
		this.folder_photo = folder_photo;
	}

	public String getFolder_temp() {
		return folder_temp;
	}

	public void setFolder_temp(String folder_temp) {
		this.folder_temp = folder_temp;
	}

	public String getAllow_types_photo() {
		return allow_types_photo;
	}

	public void setAllow_types_photo(String allow_types_photo) {
		this.allow_types_photo = allow_types_photo;
	}

	public String getLimit_size_photo() {
		return limit_size_photo;
	}

	public void setLimit_size_photo(String limit_size_photo) {
		this.limit_size_photo = limit_size_photo;
	}

	public String getHeight_photo() {
		return height_photo;
	}

	public void setHeight_photo(String height_photo) {
		this.height_photo = height_photo;
	}

	public String getWidth_photo() {
		return width_photo;
	}

	public void setWidth_photo(String width_photo) {
		this.width_photo = width_photo;
	}

	public String getEditionMode() {
		return editionMode;
	}

	public void setEditionMode(String editionMode) {
		this.editionMode = editionMode;
	}

	public boolean getPhoto_attache_capture() {
		return photo_attache_capture;
	}

	public void setPhoto_attache_capture(boolean photo_attache_capture) {
		this.photo_attache_capture = photo_attache_capture;
	}

	// identifiant of dossier etudiant

	/**
	 * [PhotoBean.dossierEtudiantId] Getter
	 * 
	 * @author yselmane on : 23 juin 2014 16:55:11
	 * @return the dossierEtudiantId
	 */
	public String getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [PhotoBean.dossierEtudiantId] Setter
	 * 
	 * @author yselmane on : 23 juin 2014 16:55:11
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(String dossierEtudiantId) {
		if ((dossierEtudiantId != null) && ((dossierEtudiantId.equals("null")) || dossierEtudiantId.isEmpty())) {
			this.dossierEtudiantId = null;
		} else
			this.dossierEtudiantId = dossierEtudiantId;

	}

	// url photo

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if ((url != null) && (url.equals("null"))) {
			this.url = null;
		} else
			this.url = url;
	}

	public void setConfigHolder(ConfigHolder configHolder) {
		this.configHolder = configHolder;
	}

	public ConfigHolder getConfigHolder() {
		return configHolder;
	}

	/**
	 * [PhotoBean.dossierEtudiantDto] Getter
	 * 
	 * @author yselmane on : 23 juin 2014 17:02:09
	 * @return the dossierEtudiantDto
	 */
	public DossierEtudiantDto getDossierEtudiantDto() {
		return dossierEtudiantDto;
	}

	/**
	 * [PhotoBean.dossierEtudiantDto] Setter
	 * 
	 * @author yselmane on : 23 juin 2014 17:02:09
	 * @param dossierEtudiantDto
	 *            the dossierEtudiantDto to set
	 */
	public void setDossierEtudiantDto(DossierEtudiantDto dossierEtudiantDto) {
		this.dossierEtudiantDto = dossierEtudiantDto;
	}

	/**
	 * evenement affichage photo upload�e [PhotoBean.getPhoto] Method
	 * 
	 * @author yselmane on : 23 juin 2014 18:44:16
	 * @return
	 */
	public StreamedContent getPhoto() {

		FacesMessage msg = new FacesMessage();
		String path = null;

		try {
			if (url != null && !url.equals("null") && !url.trim().isEmpty()) {

				path = folder_photo + url;// dossierEtudiantDto.getPhoto();

				if (!Files.exists(Paths.get(path))) {
					path = folder_temp + url;// dossierEtudiantDto.getPhoto();
				}

				if (!Files.exists(Paths.get(path))) {
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					msg.setSummary(bundleDocument.getString("document_error_open_photo"));
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return null;
				}

				String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);

				InputStream inputStream = new FileInputStream(path);
				photo = new DefaultStreamedContent(inputStream, contentType);

				return photo;
			}
			// else {
			//
			//
			// String
			// resources=FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			//
			// String contentType = FacesContext.getCurrentInstance()
			// .getExternalContext().getMimeType(resources+"/resources/images/boy.png");
			//
			// InputStream inputStream = new
			// FileInputStream("/resources/images/boy.png");
			//
			// StreamedContent photo = new DefaultStreamedContent(inputStream,
			// contentType);
			//
			// return photo;
			// }

		} catch (FileNotFoundException | NumberFormatException e) {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument.getString("document_error_open_photo"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
			this.photo = null;

		}

		return photo;
	}

	/**
	 * Evenement capture d'une nouvelle photo par webcam
	 * 
	 * @author Mounir.MESSAOUDI on : 22 juil. 2014 13:04:10
	 * @param captureEvent
	 */
	public void oncapture(CaptureEvent captureEvent) {
		FacesMessage msg = new FacesMessage();

		// recharger les parametres de la photo
		try {
			loadParamPhoto();
		} catch (Exception e) {
			e.printStackTrace();
		}

		byte[] data = captureEvent.getData();

		File targetFolder = new File(folder_temp);

		// generer un nom de fichier unique
		String fileUrl = FileUtility.getUidFileName("photo-cam.png");

		// ServletContext servletContext = (ServletContext)
		// FacesContext.getCurrentInstance().getExternalContext().getContext();
		// String arquivoFoto = servletContext.getRealPath(File.separator +
		// "temp" +File.separator + fileUrl);

		// System.err.println("handle: url----------" + arquivoFoto);

		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(targetFolder, fileUrl));

			// imageOutput = new FileImageOutputStream(new File(arquivoFoto));

			imageOutput.write(data, 0, data.length);
			imageOutput.close();

			//
			setUrl(fileUrl);
			dossierEtudiantDto.setPhoto(fileUrl);
			this.photo_attache_capture = true;
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary(bundleDocument.getString("document_success_upload_file"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (IOException e) {
			this.photo_attache_capture = false;

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument.getString("document_error_in_writing_captured_image"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}

	private void criaArquivo(String arquivo, byte[] dados) {
		FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(new File(arquivo));
			imageOutput.write(dados, 0, dados.length);
			imageOutput.close();
		} catch (FileNotFoundException ex) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Caminho n�o encontrado!", "Erro"));
		} catch (IOException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar arquivo!", "Erro"));
		}
	}

	/**
	 * evenement attacher une photo [PhotoBean.handleFileUpload] Method
	 * 
	 * @author yselmane on : 23 juin 2014 18:44:00
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {

		FacesMessage msg = new FacesMessage();

		try {
			// recharger les parametres de la photo
			try {
				loadParamPhoto();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// this.setUrl( (String) event.getComponent().getAttributes()
			// .get("url") );
			//
			// this.setDossierEtudiantId ( (String)
			// event.getComponent().getAttributes()
			// .get("dossierEtudiantId") );

			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// ServletContext scontext = (ServletContext)
			// facesContext.getExternalContext().getContext();
			// String arquivo = scontext.getRealPath("/upload/");
			// System.err.println("---------->" + arquivo + " ----" +
			// folder_temp);

			File targetFolder = new File(folder_temp);
			// File targetFolder = new File(arquivo + "\\");

			InputStream inputStream = event.getFile().getInputstream();

			// generer un nom de fichier unique
			String fileUrl = FileUtility.getUidFileName(event.getFile().getFileName());

			OutputStream out = new FileOutputStream(new File(targetFolder, fileUrl));

			int read = 0;
			byte[] bytes = new byte[4096];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			inputStream.close();
			out.flush();
			out.close();

			setUrl(fileUrl);

			dossierEtudiantDto.setPhoto(fileUrl);
			this.photo_attache_capture = true;

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
	 * Reessayer l'operation de prise de photo par webcam/Attachement
	 * 
	 * @author Mounir.MESSAOUDI on : 17 juil. 2014 11:29:45
	 */
	public void tryaAainPhoto() {
		this.photo_attache_capture = false;
	}

	/**
	 * saubegarder une photo etudiant dans la base et dans le systeme de
	 * fichiers [PhotoBean.savePhoto] Method
	 * 
	 * @author yselmane on : 23 juin 2014 18:43:29
	 */
	public void savePhoto() {

		FacesMessage msg = new FacesMessage();

		System.err.println("save photo: url----------" + url);

		try {

			// if not uploading file
			if (url == null || url.equals("null") || url.isEmpty()) {

				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleDocument.getString("document_warn_info") + ": "
						+ bundleDocument.getString("document_warn_charge_photo"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;
			}

			System.err.println("etudiantid:" + this.dossierEtudiantId);

			if (this.dossierEtudiantId == null || this.dossierEtudiantId.equals("null")
					|| this.dossierEtudiantId.isEmpty()) {

				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				msg.setSummary(bundleDocument.getString("document_error_passage_parametres_photo"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

				return;

			}

			dossierEtudiantDto = dossierEtudiantService.findById(Integer.parseInt(dossierEtudiantId));

			DossierEtudiantDto oldDossierEtudiant = dossierEtudiantService
					.findById(Integer.parseInt(dossierEtudiantId));

			if (oldDossierEtudiant.getPhoto() == null || oldDossierEtudiant.getPhoto().trim().isEmpty()) {

				dossierEtudiantDto.setPhoto(url);
				dossierEtudiantService.insertOrUpdate(dossierEtudiantDto);
				System.out.println("--------oldDossierEtudiant---------->>>" + url);
				moveFile(url);
				System.err.println("-----------------file moved------");
				// deleteTempFileByName(url);// probleme en ours d'utilisation

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
						+ bundleDocument.getString("document_msg_success_photo_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;

			}

			if (!oldDossierEtudiant.getPhoto().equals(url)) {

				// String oldUrl = oldDossierEtudiant.getPhoto();

				dossierEtudiantDto.setPhoto(url);
				dossierEtudiantService.insertOrUpdate(dossierEtudiantDto);
				System.out.println("-------------------->>>" + url);
				moveFile(url);

				// deleteFileByName(oldUrl);// probleme en ours d'utilisation

				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary(bundleDocument.getString("document_msg_success") + ": "
						+ bundleDocument.getString("document_msg_success_photo_saved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e2) {

			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(bundleDocument.getString("document_error_echec") + ": "
					+ bundleDocument.getString("document_error_persistence_photo"));
			FacesContext.getCurrentInstance().addMessage(null, msg);

			log.info(e2.getMessage());
		}
	}

	/**
	 * deplacer la photo du dossier temporaire vers le dossier photos
	 * [PhotoBean.moveFile] Method
	 * 
	 * @author yselmane on : 23 juin 2014 18:43:05
	 * @param url
	 * @throws IOException
	 */
	public void moveFile(String url) throws IOException {

		System.out.println("folder_temp : " + folder_temp);
		Path Source = Paths.get(folder_temp + url);
		Path Destination = Paths.get(folder_photo + url);
		System.out.println("folder_photo : " + folder_photo);

		if (Files.exists(Source)) {
			// Files.move(Source, Destination,
			// StandardCopyOption.REPLACE_EXISTING);
			// cpie du fichiet au lieu de deplaacer car il est enn coiurs
			// d'utilisation
			Files.copy(Source, Destination, StandardCopyOption.REPLACE_EXISTING);
		}

	}

	/**
	 * supprimer un fihier de nom: nomFichier [PhotoBean.deleteFileByName]
	 * Method
	 * 
	 * @author yselmane on : 23 juin 2014 18:42:22
	 * @param nomFichier
	 * @throws IOException
	 */
	public void deleteFileByName(String nomFichier) throws IOException {
		if (nomFichier != null && !nomFichier.isEmpty()) {
			Path target = Paths.get(folder_photo + nomFichier);
			Files.deleteIfExists(target);
		}

	}

	public void deleteTempFileByName(String nomFichier) throws IOException {
		if (nomFichier != null && !nomFichier.isEmpty()) {
			Path target = Paths.get(folder_temp + nomFichier);

			Files.deleteIfExists(target);
		}

	}

	public DossierEtudiantBean getDossierEtudiantBean() {
		return dossierEtudiantBean;
	}

	public void setDossierEtudiantBean(DossierEtudiantBean dossierEtudiantBean) {
		if (dossierEtudiantBean != null && dossierEtudiantId == null) {
			setDossierEtudiantId(dossierEtudiantBean.getDossierEtudiantId());
		}
		this.dossierEtudiantBean = dossierEtudiantBean;
	}

}
