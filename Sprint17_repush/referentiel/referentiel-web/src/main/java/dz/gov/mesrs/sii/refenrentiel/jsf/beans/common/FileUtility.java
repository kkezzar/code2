package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.io.File;


 /**
  * 
  * @author yselmane  on : 8 avr. 2014  14:24:58
  */
public class FileUtility {
	
	public FileUtility() {
	}

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
}
