/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReader.java] 
 * @author  Rafik LAIB on : 29 mai 2014  09:57:29
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ImportNoteEtudiantModel;

public class FileReaderExamenNotes {

	//public static List<InscriptionExamenDto> inscriptionExamenDtos;
	public static List<ImportNoteEtudiantModel> importNoteEtudiantModelList;
	
	//public static List<NoteEvaluationControleContinuDto> noteEvalDtos;
	

	/**
	 * [FileReaderExamenNotes.processOneSheet] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:07:17
	 * @param filename
	 * @throws Exception
	 */
	
	public void processOneSheet(String filename) throws Exception {
		
		importNoteEtudiantModelList = new ArrayList<ImportNoteEtudiantModel>();
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);

		// rId2 found by processing the Workbook
		// Seems to either be rId# or rSheet#
		InputStream sheet1 = r.getSheet("rId1");
		InputSource sheetSource = new InputSource(sheet1);
		parser.parse(sheetSource);
		sheet1.close();
	}

	/**
	 * [FileReaderExamenNotes.processAllSheets] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:07:21
	 * @param filename
	 * @throws Exception
	 */
	public void processAllSheets(String filename) throws Exception {
		importNoteEtudiantModelList = new ArrayList<ImportNoteEtudiantModel>();
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);

		Iterator<InputStream> sheets = r.getSheetsData();

		while (sheets.hasNext()) {

			System.out.println("Processing new sheet:\n");
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
			System.out.println("");
		}
	}

	/**
	 * [FileReaderExamenNotes.fetchSheetParser] Method
	 * 
	 * @author MAKERRI Sofiane on : 23 sept. 2014 16:07:25
	 * @param sst
	 * @return
	 * @throws SAXException
	 */
	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {

		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		ContentHandler handler = new SheetHandler(sst);
		parser.setContentHandler(handler);
		return parser;
	}

	/**
	 * See org.xml.sax.helpers.DefaultHandler javadocs
	 */
	private static class SheetHandler extends DefaultHandler {

		private SharedStringsTable sst;
		// private String lastContents;
		private StringBuffer contents;
		private boolean nextIsString;
		private List<String> rowValues;
		private long rowIndex;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		private boolean nextIsDate;

		// private boolean nextIsDateTime;
		// private boolean nextIsTime;

		private SheetHandler(SharedStringsTable sst) {
			this.sst = sst;
			this.contents = new StringBuffer();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
		 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException {
			// c => cell
			if (name.equals("c")) {
				// Print the cell reference
				// System.out.print(attributes.getValue("r") + " - ");
				// Figure out if the value is an index in the SST
				String cellType = attributes.getValue("t");
				if (cellType != null && cellType.equals("s")) {
					nextIsString = true;
				} else {
					nextIsString = false;
					// nextIsBool = ("b".equals(cellType));
					// nextIsString = ("s".equals(cellType));
					String cellSomething = attributes.getValue("s");
					nextIsDate = ("1".equals(cellSomething) || "2"
							.equals(cellSomething));
					// nextIsTime = ("3".equals(cellSomething));
					// nextIsDateTime = ("4".equals(cellSomething));
				}

			} else if ("row".equals(name)) {
				rowIndex = Long.valueOf(attributes.getValue("r"));
				// System.out.println( "<------------ START " + name +
				// " Row N� : " + rowIndex);
				rowValues = new ArrayList<String>();
			}
			// Clear contents cache
			contents.setLength(0);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
		 * java.lang.String, java.lang.String)
		 */
		public void endElement(String uri, String localName, String name)
				throws SAXException {

			String thisStr = null;
			// Process the last contents as required.
			// Do now, as characters() may be called more than once
			if (nextIsString) {

				String sstIndex = contents.toString();
				try {
					int idx = Integer.parseInt(sstIndex);
					thisStr = new XSSFRichTextString(sst.getEntryAt(idx))
							.toString();
					nextIsString = false;
				} catch (NumberFormatException ex) {
					System.out.println("Pgmr err, lastContents is not int: "
							+ sstIndex);
				}
				// String sstIndex = contents.toString();
				// int idx = Integer.parseInt(sstIndex);
				// contents = new
				// XSSFRichTextString(sst.getEntryAt(idx)).toString();
				// nextIsString = false;
			} else if (nextIsDate) {
				// Actually an integer
				double daysSince = Double.parseDouble(contents.toString());
				Date d = DateUtil.getJavaDate(daysSince);
				thisStr = simpleDateFormat.format(d);
			} else {
				thisStr = contents.toString();
			}
			// v => contents of a cell
			// Output after we've seen the string contents
			if (name.equals("v")) {
				System.out.println(thisStr);
				if (rowIndex > 1) {
					if ((thisStr != null) && (!thisStr.trim().equals(""))
							&& (!thisStr.trim().equals("NULL"))
							&& (!thisStr.trim().equals("null")))
						rowValues.add(thisStr);
					else
						rowValues.add("");
				} else {
					importNoteEtudiantModelList = new ArrayList<ImportNoteEtudiantModel>();
				}
			} else if ("row".equals(name)) {
				System.out.println(name + "END --------------->");
				if (rowIndex > 1) {
					ImportNoteEtudiantModel _importNoteEtudiantModel = listToImportNoteEtudiantModel(rowValues);
					if (_importNoteEtudiantModel != null) {
						importNoteEtudiantModelList.add(_importNoteEtudiantModel);
					}
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
		 */
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			contents.append(ch, start, length);
		}

		/**
		 * [SheetHandler.listToDossierBachelierDto] Method
		 * 
		 * @author MAKERRI Sofiane on : 23 sept. 2014 16:08:02
		 * @param _list
		 * @return
		 */
		private ImportNoteEtudiantModel listToImportNoteEtudiantModel(
				List<String> _list) {

			ImportNoteEtudiantModel result = null;
			if (_list == null || _list.size() < 4)
				return null;
			else {
				result = new ImportNoteEtudiantModel();
				if (_list.get(0) != null
						&& _list.get(0).trim().toLowerCase()
								.equals("matricule")) {
					return null;
					/*** sauter le titre ***/
				}
				result.setNumeroMatricule(_list.get(0).trim());
				result.setEtudiantNomLatin(_list.get(1).trim());
				result.setEtudiantPrenomLatin(_list.get(2).trim());
				result.setEtudiantDateNaissance(_list.get(3).trim());
				try {
					result.setNote(new Double(_list.get(4).trim()));
				} catch (Exception e) {
					result.setNote(null);
				}
			}
			return result;
		}
	}
}
