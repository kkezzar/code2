/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReader.java] 
 * @author  Rafik LAIB on : 29 mai 2014  09:57:29
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
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

import dz.gov.mesrs.sii.fve.business.model.dto.bac.AffectationBachelierDto;
/**
 * @author  Rafik LAIB  on : 29 mai 2014  09:57:29
 */
public class FileReaderAFF {
	
			public static  List<AffectationBachelierDto> affectationBachelierDtos;
			public void processOneSheet(String filename) throws Exception {
		
					OPCPackage pkg = OPCPackage.open(filename);
					XSSFReader r = new XSSFReader( pkg );
					SharedStringsTable sst = r.getSharedStringsTable();
					XMLReader parser = fetchSheetParser(sst);

					// rId2 found by processing the Workbook
					// Seems to either be rId# or rSheet#
					InputStream sheet1 = r.getSheet("rId1");
					InputSource sheetSource = new InputSource(sheet1);
					parser.parse(sheetSource);
					sheet1.close();
			}

			public void processAllSheets(String filename) throws Exception {
				
					OPCPackage pkg = OPCPackage.open(filename);
					XSSFReader r = new XSSFReader( pkg );
					SharedStringsTable sst = r.getSharedStringsTable();
					XMLReader parser = fetchSheetParser(sst);

					Iterator<InputStream> sheets = r.getSheetsData();
					
					while(sheets.hasNext()) {
						
							System.out.println("Processing new sheet:\n");
							InputStream sheet = sheets.next();
							InputSource sheetSource = new InputSource(sheet);
							parser.parse(sheetSource);
							sheet.close();
							System.out.println("");
					}
			}

			public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
							
					XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
					ContentHandler handler = new SheetHandler(sst);
					parser.setContentHandler(handler);
					return parser;
			}

			/** 
			 * See org.xml.sax.helpers.DefaultHandler javadocs 
			 */
			private static class SheetHandler extends DefaultHandler {
				
					private SharedStringsTable sst;
					private String lastContents;
					private boolean nextIsString;
					private List<String> rowValues;
					private long  rowIndex;
				
					private SheetHandler(SharedStringsTable sst) {
							this.sst = sst;
					}
		
					public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {

						// c => cell
						if(name.equals("c")) {
							// Print the cell reference
//							System.out.print(attributes.getValue("r") + " - ");
							// Figure out if the value is an index in the SST
							String cellType = attributes.getValue("t");
							if(cellType != null && cellType.equals("s")) {
										nextIsString = true;
							} else {
										nextIsString = false;
							}
						} else if ("row".equals(name)) {
							
							rowIndex = Long.valueOf(attributes.getValue("r"));
//							System.out.println( "<------------ START " + name + " Row N� : " + rowIndex);
							rowValues = new ArrayList<String>();
		                }
						
						// 	Clear contents cache
						lastContents = "";
					}
		
					public void endElement(String uri, String localName, String name) throws SAXException {

						// Process the last contents as required.
						//Do now, as characters() may be called more than once
						if(nextIsString) {
							int idx = Integer.parseInt(lastContents);
							lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
							nextIsString = false;
						}

						// v => contents of a cell
						// Output after we've seen the string contents
						if(name.equals("v")) {
//								System.out.println(lastContents);
								if(rowIndex> 1 )  {
										if((lastContents != null) && (! lastContents.trim().equals("")) && (! lastContents.trim().equals("NULL")) && (! lastContents.trim().equals("null")))
												rowValues.add(lastContents);
										else
											rowValues.add("");
								}else {
									affectationBachelierDtos = new ArrayList<AffectationBachelierDto>();
								}
						} else if ("row".equals(name)) {
//							System.out.println(name + "END --------------->");
							if(rowIndex> 1 )  {
								affectationBachelierDtos.add(listToAffectationBachelierDto(rowValues));
							}
							
	                }
						
					}

					public void characters(char[] ch, int start, int length) throws SAXException {
							lastContents += new String(ch, start, length);
					}
					
					private AffectationBachelierDto listToAffectationBachelierDto(List<String> _list){
						
						AffectationBachelierDto result = null;
						if((_list  == null) || (_list.size() < 15))
							return null;
						else {
						
								result = new AffectationBachelierDto();
								result.setMatriculeBachelier(_list.get(0).trim());
								result.setRefCodeEtablissement(_list.get(4).trim());
								result.setRefCodeFiliere(_list.get(5).trim());
								result.setNumeroChoix(_list.get(6).trim());
								result.setNoteAffectation(_list.get(7).trim());
								result.setRefCodeEtablissementRecours(_list.get(8).trim());
								result.setRefCodeFiliereRecours(_list.get(9).trim());
								result.setRefCodeEtablissementOrientation(_list.get(10).trim());
								result.setRefCodeFiliereOrientation(_list.get(11).trim());
								result.setRefCodeTypeAffectation(_list.get(12).trim());
								result.setRefCodeEtablissementAffectation(_list.get(13).trim());
								result.setRefCodeFiliereAffectation(_list.get(14).trim());
								
							}
						return result;
					}
	}
}
