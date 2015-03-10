package dz.gov.mesrs.sii.referentiel.business.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigHolder {

	@Value("${doc.upload.url}")
	private String documentUploadFolder;
	@Value("${doc.temp.url}")
	private String documentTempFolder;
	@Value("${doc.file.allow.types}")
	private String documentAllowTypes;
	@Value("${doc.file.max.size}")
	private String documentMaxSize;
	@Value("${photo.url}")
	private String photoUploadFolder;
	@Value("${photo.file.allow.types}")
	private String photoAllowType;
	@Value("${photo.file.max.size}")
	private String photoMaxSize;
	@Value("${photo.height}")
	private String photoHeight;
	@Value("${photo.width}")
	private String photoWidth;
	//TODO mettre en properties apres refactor extrernalisation des properties
//	@Value("${help.site.url}")
	private String helpSiteUrl = "http://192.168.3.135/help/index.php";

	public String getDocumentUploadFolder() {
		return documentUploadFolder;
	}

	public String getDocumentTempFolder() {
		return documentTempFolder;
	}

	public String getDocumentAllowTypes() {
		return documentAllowTypes;
	}

	public String getDocumentMaxSize() {
		return documentMaxSize;
	}

	public String getPhotoUploadFolder() {
		return photoUploadFolder;
	}

	public String getPhotoAllowType() {
		return photoAllowType;
	}

	public String getPhotoMaxSize() {
		return photoMaxSize;
	}

	public String getPhotoHeight() {
		return photoHeight;
	}

	public String getPhotoWidth() {
		return photoWidth;
	}

	public String getHelpSiteUrl() {
		return helpSiteUrl;
	}
	

}
