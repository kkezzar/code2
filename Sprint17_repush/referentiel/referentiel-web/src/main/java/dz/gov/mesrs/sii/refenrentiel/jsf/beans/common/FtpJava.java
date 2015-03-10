package dz.gov.mesrs.sii.refenrentiel.jsf.beans.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;

/**
 * Classe de test de l'upload des fichier dans un server FTP
 * pour le moment c'est juste un TEST!!!!
 * @author yselmane  on : 9 avr. 2014  10:28:10
 */
class FtpJava {
	public static void main(String[] args) throws IOException {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		boolean result;
		String ftpServerAddress = "192.168.3.10";
		String userName = "test";
		String password = "test";

		try {
			client.connect(ftpServerAddress);
			result = client.login(userName, password);

			if (result == true) {
				System.out.println("Successfully logged in!");
			} else {
				System.out.println("Login Fail!");
				return;
			}
			client.setFileType(FTP.BINARY_FILE_TYPE);

			client.changeWorkingDirectory("/");

			File file = new File("E:/A5.pdf");
			String testName = file.getName();
			fis = new FileInputStream(file);

			// Upload file to the ftp server
			result = client.storeFile(testName, fis);

			if (result == true) {
				System.out.println("File  "+testName +" is uploaded successfully");
			} else {
				System.out.println("File "+testName +" uploading failed");
			}
			client.logout();
		} catch (FTPConnectionClosedException e) {
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (FTPConnectionClosedException e) {
				System.out.println(e);
			}
		}
	}
}
