package dz.gov.mesrs.sii.referentiel.business.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;




public class UtilConfig {

	private static String FILE_CONFIG =  getClassPath(UtilConfig.class)+"/ldapconfig.properties";
	public static String REGEX = "\\|";
	private String locale = "fr";

	
		
	public static String getFilesPath() {
		String report = "";
		report = loadPropretiesValue("file.path");
		return report;
	}

	public static String getFilesParticipationsLocalePath() {
		String report = "";
		report = getPath()
				+ loadPropretiesValue("file.participation.locale.path");
		return report;
	}
	
	public static String getFilesEtatPaiementSalairePath() {
		String report = "";
		report = getPath()
				+ loadPropretiesValue("file.etatPaiementSalaire.path");
		return report;
	}

	public static String getFilesCopieJoreLocalePath() {
		String report = "";
		report = getPath() + loadPropretiesValue("file.copie.jort");
		return report;
	}

	public static String getFilesContratPath() {
		String report = "";
		report = getPath() + loadPropretiesValue("file.contrat");
		return report;
	}
	
	public static String getFilesAchatPath() {
		String report = "";
		report = getPath() + loadPropretiesValue("file.achat");
		return report;
	}
	public static String getFilesDepensePath() {
		String report = "";
		report = getPath() + loadPropretiesValue("file.depense");
		return report;
	}
	public static String getFilesFichePresenceJourPath() {
		String report = "";
		report = getPath() + loadPropretiesValue("file.fichePresenceJour");
		return report;
	}


	public static String loadPropretiesValue(String key) {

		String value = "";
		try {
			Properties fileProperties = new Properties();
			
			FileInputStream in = new FileInputStream(FILE_CONFIG);
			fileProperties.load(in);
			in.close();
			value = fileProperties.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	

	public static void createDirectory(String outputDirectory) {
		File rep = new File(outputDirectory);
		if (!rep.exists()) {
			rep.mkdirs();
		}
	}

	public static String getPath() {
		String path = "";
		path = loadPropretiesValue("url.base");
		return path;
	}

	public static String getNameFile(String path) {
		try {
			int lastSlash = path.lastIndexOf("/");
			int index_ext = path.length();
			String filename = path.substring(lastSlash + 1, index_ext);
			return filename;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getContentTypeFromExtension(String extension) {
		String contentType = null;
		if (extension.equalsIgnoreCase("pdf")) {
			contentType = "application/pdf";

		}
		if (extension.equalsIgnoreCase("txt")) {
			contentType = "text/plain";

		}
		if (extension.equalsIgnoreCase("rtf")) {
			contentType = "application/rtf";

		}
		if (extension.equalsIgnoreCase("html")) {
			contentType = "text/html";

		}
		if (extension.equalsIgnoreCase("xml")) {
			contentType = "text/xml";

		}
		if (extension.equalsIgnoreCase("doc")
				|| extension.equalsIgnoreCase("docx")) {
			contentType = "application/word";
		}
		if (extension.equalsIgnoreCase("xls")
				|| extension.equalsIgnoreCase("xlsx")) {
			contentType = "application/vnd.ms-excel";
		}
		if (extension.equalsIgnoreCase("csv")) {
			contentType = "application/csv";

		}
		return contentType;
	}

	public static String getContentType(String extension) {
		String contentType = null;
		if (extension.equalsIgnoreCase("html")
				|| extension.equalsIgnoreCase("htm")) {
			contentType = "text/html";
		}
		if (extension.equalsIgnoreCase("txt")
				|| extension.equalsIgnoreCase("text")) {
			contentType = "text/plain";
		}
		if (extension.equalsIgnoreCase("gif")) {
			contentType = "image/gif";
		}
		if (extension.equalsIgnoreCase("ief")) {
			contentType = "image/ief";
		}
		if (extension.equalsIgnoreCase("jpeg")
				|| extension.equalsIgnoreCase("jpg")
				|| extension.equalsIgnoreCase("jpe")) {
			contentType = "image/jpeg";
		}
		if (extension.equalsIgnoreCase("tif")
				|| extension.equalsIgnoreCase("tiff")) {
			contentType = "image/tiff";
		}
		if (extension.equalsIgnoreCase("png")) {
			contentType = "image/png";
		}
		if (extension.equalsIgnoreCase("xwd")) {
			contentType = "image/x-xwindowdump";
		}
		if (extension.equalsIgnoreCase("ai")
				|| extension.equalsIgnoreCase("eps")
				|| extension.equalsIgnoreCase("ps")) {
			contentType = "application/postscript";
		}
		if (extension.equalsIgnoreCase("rtf")) {
			contentType = "application/rtf";
		}
		if (extension.equalsIgnoreCase("tex")) {
			contentType = "application/x-tex";
		}
		if (extension.equalsIgnoreCase("texinfo")
				|| extension.equalsIgnoreCase("texi")) {
			contentType = "application/x-texinfo";
		}
		if (extension.equalsIgnoreCase("t") || extension.equalsIgnoreCase("tr")
				|| extension.equalsIgnoreCase("roff")) {
			contentType = "application/x-troff";
		}
		if (extension.equalsIgnoreCase("au")) {
			contentType = "audio/basic";
		}
		if (extension.equalsIgnoreCase("midi")
				|| extension.equalsIgnoreCase("mid")) {
			contentType = "audio/midi";
		}
		if (extension.equalsIgnoreCase("aifc")) {
			contentType = "audio/x-aifc";
		}
		if (extension.equalsIgnoreCase("aif")
				|| extension.equalsIgnoreCase("aiff")) {
			contentType = "audio/x-aiff";
		}
		if (extension.equalsIgnoreCase("mpeg")
				|| extension.equalsIgnoreCase("mpg")) {
			contentType = "audio/x-mpeg";
		}
		if (extension.equalsIgnoreCase("wav")) {
			contentType = "audio/x-wav";
		}
		if (extension.equalsIgnoreCase("mpeg")
				|| extension.equalsIgnoreCase("mpg")
				|| extension.equalsIgnoreCase("mpe")) {
			contentType = "video/mpeg";
		}
		if (extension.equalsIgnoreCase("qt")
				|| extension.equalsIgnoreCase("mov")) {
			contentType = "video/quicktime";
		}

		if (extension.equalsIgnoreCase("avi")) {
			contentType = "video/x-msvideo";
		}
		if (extension.equalsIgnoreCase("pdf")) {
			contentType = "application/pdf";
		}
		if (extension.equalsIgnoreCase("doc")) {
			contentType = "application/msword";
		}
		if (extension.equalsIgnoreCase("csv")) {
			contentType = "text/comma-separated-values";
		}
		if (extension.equalsIgnoreCase("xls")) {
			contentType = "application/vnd.ms-excel";
		}
		if (extension.equalsIgnoreCase("docx")) {
			contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}
		if (extension.equalsIgnoreCase("xlsx")) {
			contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
		System.out.println("--> contentType ::  " + contentType);
		return contentType;

	}

	public static String renameFile(String filePath) {

		int indexExtension = filePath.lastIndexOf(".");
		String extension = filePath.substring(indexExtension + 1);

		String fileName = filePath.substring(0, indexExtension);

		int i = 2;
		filePath = fileName + " (" + i + ")." + extension;
		File file = new File(filePath);

		while (file.exists()) {
			i++;
			filePath = fileName + " (" + i + ")." + extension;
			file = new File(filePath);
		}
		return filePath;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	public static String getProjetRealPath(Class classe) {
		// URL returned "/C:/Program%20Files/Tomcat%207.0/webapps/REC_SIGSEP/WEB-INF/classes/"
		try {
		URL r = classe.getResource("/");
		System.out.println(r);
		String filePath = r.getFile();
		System.out.println(filePath);
		String result = new File(new File(filePath).getParent()).getParent();
		// path decoded "/C:/Program Files/Tomcat 7.0/webapps/REC_SIGSEP"
		String decoded = URLDecoder.decode(result, "UTF-8");
		if (decoded.startsWith("/")) {
		    // path "C:/Program Files/Tomcat 7.0/webapps/REC_SIGSEP"
		    decoded = decoded.replaceFirst("/", "");
		}
		return decoded;
         } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getClassPath(Class classe) {
		// URL returned "/C:/Program%20Files/Tomcat%207.0/webapps/REC_SIGSEP/WEB-INF/classes/"
		try {
		URL r = classe.getResource("/");
		
		String filePath = r.getFile();
		
		String result = new  File(filePath).getPath();
		// path decoded "/C:/Program Files/Tomcat 7.0/webapps/REC_SIGSEP"
		String decoded = URLDecoder.decode(result, "UTF-8");
		if (decoded.startsWith("/")) {
		    // path "C:/Program Files/Tomcat 7.0/webapps/REC_SIGSEP"
		    decoded = decoded.replaceFirst("/", "");
		}
		return decoded;
         } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
