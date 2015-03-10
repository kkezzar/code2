package dz.gov.mesrs.sii.fve.business.service.impression;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
 
 
public interface  ImpressionService {

public  void printPDFWithoutConnection(String inPutfileJasper,Map<String, Object> params,String outPutFile);
public  void printPDFWithConnection(String inPutfileJasper,Map<String, Object> params, String outPutFile) ;
public  void printPDFWithDataSource(String inPutfileJasper,Map<String, Object> params, Collection collection, String outPutFile);
public  byte[] viewPDFWithoutConnection(String inPutfileJasper,Map<String, Object> params) ;
public  byte[] viewPDFWithDataSource(String inPutfileJasper,Map<String, Object> params, Collection collection) ;
public byte[] createLogs(Collection<DossierInscriptionAdministrativeDto> etudiants, String directory, String user) throws IOException;

}