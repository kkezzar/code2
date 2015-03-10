/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.utils.FileReader.java] 
 * @author  Rafik LAIB on : 29 mai 2014  09:57:29
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.utils;
import java.io.InputStream;
import java.text.ParseException;
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

import dz.gov.mesrs.sii.fve.business.model.dto.bac.DossierBachelierDto;
/**
 * @author  Rafik LAIB  on : 29 mai 2014  09:57:29
 */
public class FileReaderNotes {
	
			public static  List<DossierBachelierDto> dossierBachelierDtos;
	
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
//					private String lastContents;
					private StringBuffer contents;
					private boolean nextIsString;
					private List<String> rowValues;
					private long  rowIndex;
				
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss a");
					private boolean nextIsBool;
					private boolean nextIsDate;
					private boolean nextIsDateTime;
					private boolean nextIsTime;
							   
					private SheetHandler(SharedStringsTable sst) {
							this.sst = sst;
							this.contents = new StringBuffer();
					}
		
					public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
						// c => cell
						if(name.equals("c")) {
									// Print the cell reference
//									System.out.print(attributes.getValue("r") + " - ");
									// Figure out if the value is an index in the SST
									String cellType = attributes.getValue("t");
									if(cellType != null && cellType.equals("s")) {
										nextIsString = true;
									} else {
										nextIsString = false;
//										 nextIsBool = ("b".equals(cellType));
//										 nextIsString = ("s".equals(cellType));
										 String cellSomething = attributes.getValue("s");
										 nextIsDate = ("1".equals(cellSomething) || "2".equals(cellSomething)); 
										 nextIsTime = ("3".equals(cellSomething)); 
										 nextIsDateTime = ("4".equals(cellSomething));	
									}

									 
						} else if ("row".equals(name)) {
							
								rowIndex = Long.valueOf(attributes.getValue("r"));
//								System.out.println( "<------------ START " + name + " Row N° : " + rowIndex);
								rowValues = new ArrayList<String>();
							}
						 // 	Clear contents cache
						contents.setLength(0);
					}
		
					public void endElement(String uri, String localName, String name) throws SAXException {

						String thisStr = null;
						// Process the last contents as required.
						//Do now, as characters() may be called more than once
						if(nextIsString) {
							
							String sstIndex = contents.toString();
							try {
									int idx = Integer.parseInt(sstIndex);
							        thisStr = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
							        nextIsString = false;
							}
							catch (NumberFormatException ex) {
										System.out.println("Pgmr err, lastContents is not int: " + sstIndex);
							}
//							String sstIndex = contents.toString();
//							int idx = Integer.parseInt(sstIndex);
//							contents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
//							nextIsString = false;
						} else if(nextIsDate) {
									// Actually an integer
									double daysSince = Double.parseDouble(contents.toString());
									Date d = DateUtil.getJavaDate(daysSince);
									thisStr = simpleDateFormat.format(d);
							}
							else {
									thisStr = contents.toString();
							}
						// v => contents of a cell
						// Output after we've seen the string contents
						if(name.equals("v")) {
								System.out.println(thisStr);
								if(rowIndex> 1 )  {
										if((thisStr != null) && (! thisStr.trim().equals("")) && (! thisStr.trim().equals("NULL")) && (! thisStr.trim().equals("null")))
												rowValues.add(thisStr);
										else
											rowValues.add("");
								}else {
									dossierBachelierDtos = new ArrayList<DossierBachelierDto>();
								}
						} else if ("row".equals(name)) {
							System.out.println(name + "END --------------->");
							if(rowIndex> 1 )  {
									dossierBachelierDtos.add(listToDossierBachelierDto(rowValues));
							}
	                }
					}

					public void characters(char[] ch, int start, int length)
					       throws SAXException {
					         contents.append(ch, start, length);
					}
//					public void characters(char[] ch, int start, int length) throws SAXException {
//							lastContents += new String(ch, start, length);
//					}
					
					private DossierBachelierDto listToDossierBachelierDto(List<String> _list){
						
						DossierBachelierDto result = null;
						if((_list  == null) || (_list.size() < 18))
							return null;
						else {
						
								result = new DossierBachelierDto();
								result.setMatricule(_list.get(0).trim());
								result.setNomAr(_list.get(1).trim());
								result.setPrenomAr(_list.get(2).trim());
								result.setNomFr(_list.get(3).trim());
								result.setPrenomFr(_list.get(4).trim());
								result.setRefCodeSexe(_list.get(5).trim());
								result.setRefCodeSerieBac(_list.get(6).trim());
								result.setLibelleSerieBac(_list.get(7).trim());
								result.setRefCodeWilayaNaissance(_list.get(8).trim());
								result.setLibelleVilleNaissance(_list.get(9).trim());
								try {
									result.setDateNaissance(simpleDateFormat.parse(_list.get(10)));
									
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								}
								result.setMoyenneBac(_list.get(11).trim());
								result.setRefCodeWilayaBac(_list.get(12).trim());
								result.setPrenomPere(_list.get(13).trim());
								result.setNomPrenomMere(_list.get(14).trim());
								result.setTelephone(_list.get(15).trim());
								result.setRefCodeWilayaResidence(_list.get(16).trim());
								result.setAdresseResidence(_list.get(17).trim());
								
							}
						return result;
					}
	}
}
