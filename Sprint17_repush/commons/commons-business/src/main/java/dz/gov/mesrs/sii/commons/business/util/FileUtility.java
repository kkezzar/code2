package dz.gov.mesrs.sii.commons.business.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;


 /**
  * 
  * @author yselmane  on : 8 avr. 2014  14:24:58
  */
public class FileUtility {
	
	public FileUtility() {
	}
	public static String REGEX = "\\|";
	private String locale = "fr";
	/**
	 * Renvoie l'extension d'un fichier  à partir du fichier
	 * [FileUtility.getExtension] Method 
	 * @author yselmane  on : 8 avr. 2014  14:32:27
	 * @param docFile
	 * @return Extension du fichier
	 */
	public static String getExtension(File docFile) {
	
		if (docFile==null || docFile.getName().isEmpty()) return "";
		
		String fileName = docFile.getName();
		int indexDot = fileName.lastIndexOf('.');
		String extention = fileName.substring(indexDot + 1);
		return extention;
	}

	/**
	 * Renvoie le nom fichier sans son extension à partir du fichier
	 * [FileUtility.getExtensionOff] Method 
	 * @author yselmane  on : 8 avr. 2014  14:33:18
	 * @param docFile
	 * @return Nom du fichier sans l'extension
	 */
	public static String getExtensionOff(File docFile) {
		
		if (docFile==null || docFile.getName().isEmpty()) return "";
		
		String fileName = docFile.getName();
		int indexDot = fileName.lastIndexOf('.');
		String extentionOff = fileName.substring(0, indexDot);
		return extentionOff;
	}

	/**
	 * Renvoie l'extension d'un fichier  à partir du nom du fichier
	 * [FileUtility.getExtension] Method 
	 * @author yselmane  on : 8 avr. 2014  14:34:04
	 * @param fileName
	 * @return Extension du fichier
	 */
	public static String getExtension(String fileName) {
		
		if (fileName==null || fileName.isEmpty()) return "";
		
		int indexDot = fileName.lastIndexOf('.');
		String extentionOff = "";
		if (indexDot > 0) {
			extentionOff = fileName.substring(indexDot + 1);
		}
		return extentionOff;
	}

	/**
	 * Renvoie le nom fichier sans son extension à partir du nom du fichier
	 * [FileUtility.getExtensionOff] Method 
	 * @author yselmane  on : 8 avr. 2014  14:34:53
	 * @param fileName
	 * @return
	 */
	public static String getExtensionOff(String fileName) {
		
		if (fileName==null || fileName.isEmpty()) return "";
		
		int indexDot = fileName.lastIndexOf('.');
		String extentionOff = "";
		if (indexDot > 0) {
			extentionOff = fileName.substring(0, indexDot);
		}
		return extentionOff;
	}

	/**
	 * Renvoie un nom de fichier unique à  partir d'un nom de fichier
	 * [FileUtility.getUidFileName] Method 
	 * @author yselmane  on : 8 avr. 2014  14:35:52
	 * @param fileName
	 * @return unique nom de fichier
	 */
	public static synchronized String getUidFileName(String fileName) {
		// Debug.println("FileUtil","getUidFileName","fileName="+fileName);
		UID uid = new UID();
		String unique = uid.getUidTimeStamp();
		// Debug.println("FileUtil","getUidFileName","unique="+unique);
		String fileSourceName = getExtensionOff(fileName);
		// Debug.println("FileUtil","getUidFileName","fileSourceName="+fileSourceName);
		String fileComposeName = fileSourceName + "_" + unique;
		// Debug.println("FileUtil","getUidFileName","fileComposeName="+fileComposeName);
		String fileUniqueName = fileComposeName +"."+ getExtension(fileName);
		// Debug.println("FileUtil","getUidFileName","fileUniqueName="+fileUniqueName);
		return fileUniqueName;
	}
	
	
	
	/**
	 * Renvoie un nom de fichier unique à partir du fichier lui même
	 * [FileUtility.getUidFileName] Method 
	 * @author yselmane  on : 8 avr. 2014  14:52:51
	 * @param fileName
	 * @return unique nom de fichier à partir du fichier
	 */
	public static synchronized String getUidFileName(File fileName) {
		// Debug.println("FileUtil","getUidFileName","fileName="+fileName);
		UID uid = new UID();
		String unique = uid.getUidTimeStamp();
		// Debug.println("FileUtil","getUidFileName","unique="+unique);
		String fileSourceName = getExtensionOff(fileName);
		// Debug.println("FileUtil","getUidFileName","fileSourceName="+fileSourceName);
				
		String fileComposeName = fileSourceName + "_" + unique;
		// Debug.println("FileUtil","getUidFileName","fileComposeName="+fileComposeName);
		String fileUniqueName = fileComposeName +"."+ getExtension(fileName);
		// Debug.println("FileUtil","getUidFileName","fileUniqueName="+fileUniqueName);
		return fileUniqueName;
	}
	
	/**
	 * Calcule le temps en milliseconde d'une façon unique
	 * [FileUtility.getUidTime] Method 
	 * @author yselmane  on : 9 avr. 2014  14:27:08
	 * @return unique nom en milliseconde
	 */
	public static synchronized String getUidTime() {
		
		UID uid = new UID();
		String unique = uid.getUidTimeStamp();
		
		return unique;
	}
	
	
	
	


	public static String loadPropretiesValue(String key, String filename) {

		String value = "";
		try {
			Properties fileProperties = new Properties();
			
			FileInputStream in = new FileInputStream(filename);
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
		if (extension.equalsIgnoreCase("pdf")) {
			contentType = "application/pdf";
		}
		if (extension.equalsIgnoreCase("txt")
				|| extension.equalsIgnoreCase("text")|| extension.equalsIgnoreCase("log")) {
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
