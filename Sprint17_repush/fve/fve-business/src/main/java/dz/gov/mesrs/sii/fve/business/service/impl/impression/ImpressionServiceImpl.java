/**
 * [dz.gov.mesrs.sii.fve.business.service.impl.impression.ImpressionServiceImpl.java] 
 * @author BELDI Jamel on : 18 juin 2014  15:27:19
 */
package dz.gov.mesrs.sii.fve.business.service.impl.impression;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.service.impression.ImpressionService;

/**
 * @author BELDI Jamel  on : 18 juin 2014  15:27:19
 */
@Service("impressionService")
public class ImpressionServiceImpl implements ImpressionService {
	private static final Log log = LogFactory
			.getLog(ImpressionServiceImpl.class);
//	public static String urlJrxml = "C:/Users/jbeldi/jasper/source/";
//	public static String urlJasper = "C:/Users/jbeldi/jasper/";
//	public static String urlPDF = "C:/Users/jbeldi/jasper/rapport/";
//	public static String urlImages = "C:/Users/jbeldi/jasper/images/";//get chemin relatif
	
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	
	/**
	 * [ImpressionServiceImpl.ImpressionServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 18 juin 2014  15:27:19	
	 */
	public ImpressionServiceImpl() {
		
	}

	/**
	 * print PDF Without Connection
	 */
	public void printPDFWithoutConnection(String inPutfileJasper,
			Map<String, Object> params, String outPutFile) {
//		String urlFile = urlJasper + inPutfileJasper;
//		String filePDF = urlPDF+ outPutFile;
		
		try {
		
			InputStream is = new FileInputStream(inPutfileJasper);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, new JREmptyDataSource()); 
			
			JRPdfExporter pdfExporter = new JRPdfExporter(); 
			pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint); 
			pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File(outPutFile));
			pdfExporter.exportReport(); 

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(JRException e){
			e.printStackTrace();
		}

	}

	
	/**
	 * print PDF With DB Connection
	 */
	public void printPDFWithConnection(String inPutfileJasper,
			Map<String, Object> params, String outPutFile) {
//
//		String urlFile = urlJasper + inPutfileJasper;
//		String filePDF = urlPDF+ outPutFile;
		
		try {
		
			InputStream is = new FileInputStream(inPutfileJasper);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, establishConnection()); 
			
			JRPdfExporter pdfExporter = new JRPdfExporter(); 
			pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint); 
			pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File(outPutFile));
			pdfExporter.exportReport(); 
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(JRException e){
			e.printStackTrace();
		}

	}

	
	/**
	 *print PDF With JAVA Data Source 
	 */
	public void printPDFWithDataSource(String inPutfileJasper,
			Map<String, Object> params, Collection collection, String outPutFile) {

//		String urlFile = urlJasper + inPutfileJasper;
//		String filePDF = urlPDF+ outPutFile;
		
		try {
		
			InputStream is = new FileInputStream(inPutfileJasper);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, new JRBeanCollectionDataSource(collection)); 
			
			JRPdfExporter pdfExporter = new JRPdfExporter(); 
			pdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint); 
			pdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File(outPutFile));
			pdfExporter.exportReport(); 
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(JRException e){
			e.printStackTrace();
		}

	}

	
	/**
	 * view PDF Without Connection
	 */
	public byte[] viewPDFWithoutConnection(String inPutfileJasper,
			Map<String, Object> params) {
		
		try {
//			String sourceJRXML = urlJrxml + inPutfileJasper; //ordrePaiement.jrxml;
//			params.put("image1", urlImages+"mfpe.png");
//			params.put("image2", urlImages+"fondsJaponais.png");
//			params.put("image3", urlImages+"banqueMondiale.png");
			//charger rapport
			JasperDesign jasperDesign = JRXmlLoader.load(inPutfileJasper);
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			//Execution du rapport
		       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		      // - Cruation du rapport au format PDF
		      //JasperExportManager.exportReportToPdfFile(jasperPrint, filePDF);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			return bytes;		 	 
		
		}catch(JRException e){
			e.printStackTrace();
			return null;
		}	
	}

	/**
	 * 
	 * [ImpressionServiceImpl.establishConnection] Method 
	 * @author BELDI Jamel  on : 18 juin 2014  15:43:02
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static Connection establishConnection() throws ClassNotFoundException {
		Connection connection = null;
      //TODO A REMPLACER les donnues en dur
		try {
			Class.forName("org.postgresql.Driver");
			String postgresqlURL = "jdbc:postgresql://192.168.3.127:5432/ref_db";
			connection = DriverManager.getConnection(postgresqlURL, "dev","dev2013");
			connection.setAutoCommit(false);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return connection;

	}

	/**
	 * [ImpressionServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 18 juin 2014  15:42:56
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [ImpressionServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 18 juin 2014  15:42:56
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * view PD FWith DataSource
	 */
	public byte[] viewPDFWithDataSource(String inPutfileJasper,
			Map<String, Object> params, Collection collection) {
		try {
//			String sourceJRXML = urlJrxml + inPutfileJasper; //ordrePaiement.jrxml;
//			params.put("image1", urlImages+"mfpe.png");
//			params.put("image2", urlImages+"fondsJaponais.png");
//			params.put("image3", urlImages+"banqueMondiale.png");
			//charger rapport
			JasperDesign jasperDesign = JRXmlLoader.load(inPutfileJasper);
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			//Execution du rapport
		       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(collection));
		      // - Cruation du rapport au format PDF
		      //JasperExportManager.exportReportToPdfFile(jasperPrint, filePDF);
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			return bytes;		 	 
		
		}catch(JRException e){
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public byte[] createLogs(Collection<DossierInscriptionAdministrativeDto> etudiants, String directory, String user) throws IOException {
		
		StringBuilder urlTemp = new StringBuilder(directory);
		urlTemp.append("/").append("impression_attestation").append(generateCode(6)).append(".log");
		File sortie = new File(urlTemp.toString());
		log.info("Created a temp file for log");

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(sortie));
			bw.write("Utilisateur:"+user +"\n" );
			bw.write("Date:"+(new Date()).toString() +"\n" );
			bw.write("Veuillez verifier les champs null"+"\n");
			StringBuilder ligne = new StringBuilder();
			ligne.append("Annee Acdemique").append("==" )
            .append("Numero Inscription").append("===" )
             .append( "Nom Arabe").append("==" )
                	.append("Prenom Arabe").append("==" )
                	.append("Date Naissance").append("==" )
             .append("Lieu Naissance").append("==" )
             .append("Domaine arabe").append("==" )
                	.append("Filiere Arabe").append("==" )
                	.append("Niveau Arabe");
        	bw.write(ligne.toString()+"\n");
            for (DossierInscriptionAdministrativeDto dossierInscriptionAdministrativeDto : etudiants) {
             ligne = new StringBuilder();
            	ligne.append(dossierInscriptionAdministrativeDto.getAnneeAcademiqueCode()).append(":" )
                .append(dossierInscriptionAdministrativeDto.getNumeroInscription()).append(":" )
                 .append( dossierInscriptionAdministrativeDto.getIndividuNomArabe()).append(":" )
                    	.append(dossierInscriptionAdministrativeDto.getIndividuPrenomArabe()).append(":" )
                    	.append(dossierInscriptionAdministrativeDto.getIndividuDateNaissance()).append(":" )
                 .append(dossierInscriptionAdministrativeDto.getIndividuLieuNaissance()).append(":" )
                 .append(dossierInscriptionAdministrativeDto.getRefLibelleDomaineArabe()).append(":" )
                    	.append(dossierInscriptionAdministrativeDto.getRefLibelleFiliereArabe()).append(":" )
                    	.append(dossierInscriptionAdministrativeDto.getRefLibelleNiveauArabe());
            	bw.write(ligne.toString()+"\n");
			}
			
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			throw e;
		}
		byte[] bFile = new byte[(int) sortie.length()];

		try {

			FileInputStream lecteur = new FileInputStream(sortie);

			// convert file into array of bytes

			lecteur.read(bFile);
			lecteur.close();
            sortie.delete();
			return bFile;
		} catch (IOException e) {
			throw e;

		}

	}
private String generateCode(int length) {
		
		String code = null;
		
			java.util.Random rn = new java.util.Random();
			int rnInt = rn.nextInt();
			code = new Integer(rnInt).toString();
			while ((code.length() < length + 1)) {
				rnInt = rn.nextInt();
				code = new Integer(rnInt).toString();
			}
			if (rnInt < 0) {

				code = code.substring(1, length + 1);
				// maChaine contient bien un entier positif
			} else {
				code = code.substring(0, length);
			}
			
		
		return code;
	}
}
