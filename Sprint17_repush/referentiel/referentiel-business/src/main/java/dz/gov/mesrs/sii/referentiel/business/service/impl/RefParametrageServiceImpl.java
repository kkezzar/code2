/**
 * [dz.gov.mesrs.sii.referentiel.business.service.impl.RefParametrageServiceImpl.java] 
 * @author BELDI Jamel on : 19 mars 2014  18:41:13
 */
package dz.gov.mesrs.sii.referentiel.business.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dz.gov.mesrs.sii.commons.data.dao.referentiel.RefFileConfigDao;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFileConfig;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefFileConfigDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefParamDto;
import dz.gov.mesrs.sii.referentiel.business.service.RefParametrageService;
import dz.gov.mesrs.sii.referentiel.business.util.RefUtilConstant;

/**
 * @author BELDI Jamel  on : 19 mars 2014  18:41:13
 */
@Service ("refParametrageServiceImpl")
public class RefParametrageServiceImpl implements RefParametrageService {
	private static final Logger log = LoggerFactory.getLogger(RefParametrageServiceImpl.class.getName());
	@Autowired
	@Qualifier("mapper")
	private org.dozer.Mapper mapper;
	@Autowired
	@Qualifier("refFileConfigDaoImpl")
	private RefFileConfigDao refFileConfigDao ;
	/**
	 * [RefParametrageServiceImpl.RefParametrageServiceImpl()] Constructor
	 * @author BELDI Jamel  on : 19 mars 2014  18:41:13	
	 */
	public RefParametrageServiceImpl() {
		
	}

	/**
	 * [RefParametrageServiceImpl.mapper] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @return the mapper
	 */
	public org.dozer.Mapper getMapper() {
		return mapper;
	}

	/**
	 * [RefParametrageServiceImpl.mapper] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param mapper the mapper to set
	 */
	public void setMapper(org.dozer.Mapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	/**
	 * [RefParametrageServiceImpl.refFileConfigDao] Getter 
	 * @author BELDI Jamel on : 20 mars 2014  13:00:55
	 * @return the refFileConfigDao
	 */
	public RefFileConfigDao getRefFileConfigDao() {
		return refFileConfigDao;
	}

	/**
	 * [RefParametrageServiceImpl.refFileConfigDao] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  13:00:55
	 * @param refFileConfigDao the refFileConfigDao to set
	 */
	public void setRefFileConfigDao(RefFileConfigDao refFileConfigDao) {
		this.refFileConfigDao = refFileConfigDao;
	}

	/**
	 * [RefParametrageServiceImpl.findResourcesBundle] find  list of resources IHM
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param idDomain, idEntity, idLanguage
	 * @return the list of RefFileConfigDto
	 */
	@Override
	public List<RefFileConfigDto> findResourcesBundle(Integer idDomain,
			Integer idEntity, Integer idLanguage) {
		
		List<RefFileConfigDto> refFileConfigListDto = new ArrayList<RefFileConfigDto>();
		try {
			
			List<RefFileConfig> refFileConfigList = refFileConfigDao
					.findByEntity(idDomain, idEntity, idLanguage);
			if (refFileConfigList != null && !refFileConfigList.isEmpty()) {
				log.debug("contrat list success with size = {} ", new Object[]{refFileConfigList.size()});
				
				for (RefFileConfig refFileConfig : refFileConfigList) {
					RefFileConfigDto refFileConfigDto = new RefFileConfigDto();
					mapper.map(refFileConfig, refFileConfigDto);
					refFileConfigListDto.add(refFileConfigDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFileConfigListDto;
	}

	/**
	 * [RefParametrageServiceImpl.findResourcesConfig] find  list of resources Configuration
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param idDomain, idConfiguration
	 * @return the list of RefFileConfigDto
	 */
	@Override
	public List<RefFileConfigDto> findResourcesConfig(Integer idDomain,
			Integer idConfiguration) {
		
		List<RefFileConfigDto> refFileConfigListDto = new ArrayList<RefFileConfigDto>();
		try {
			RefFileConfig refFileConfig = new RefFileConfig();
			List<RefFileConfig> refFileConfigList = refFileConfigDao
					.findByConfiguration(idDomain, idConfiguration);
			if (refFileConfigList != null && !refFileConfigList.isEmpty()) {
				log.debug("contrat list success with size = {} ", new Object[]{refFileConfigList.size()});
				for (RefFileConfig currentRefFileConfig : refFileConfigList) {
					RefFileConfigDto currentRefFileConfigDto = new RefFileConfigDto();
					mapper.map(currentRefFileConfig, currentRefFileConfigDto);
					refFileConfigListDto.add(currentRefFileConfigDto);
				}

			}
		} catch (RuntimeException e) {
			log.error("get failed", e);
			throw e;
		}
		return refFileConfigListDto;
	
	}

	
	/**
	 * [RefParametrageServiceImpl.viewParams] view list of parameters
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param RefFileConfigDto
	 * @return the list of refParamDto
	 */
	@Override
	public List<RefParamDto> viewParams(RefFileConfigDto refFileConfigDto) throws FileNotFoundException {
		List<RefParamDto> result = null;
		  try {
		if( refFileConfigDto!=null && refFileConfigDto.getIdDomaine()==1851){
		result = viewParamsRefrentiel(refFileConfigDto);
		}else{
			throw new FileNotFoundException();
		}
	} catch (FileNotFoundException e) {
		throw e;
	}
			 
		return result;
	}
	
	/**
	 * [RefParametrageServiceImpl.updateParamValue] Modify value of RefParamDto in the file
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param RefParamDto
	 * @return void
	 * @throws IOException 
	 */
	public void updateParamValue(RefParamDto refParamDto) throws IOException {

		
		try {
			 try {
					if( refParamDto!=null && refParamDto.getIdDomaine()==1851){
						updateParamValueReferentiel(refParamDto);
					}else{
						throw new FileNotFoundException();
					}
				} catch (FileNotFoundException e) {
					throw e;
				}
			} catch (IOException e) {
				throw e;
				
			}
		

	}
	
	


	
	/**
	 * [RefParametrageServiceImpl.initDefaultConfig] Method 
	 * @author BELDI Jamel  on : 24 mars 2014  15:51:06
	 * @param RefFileConfigDto
	 * @return
	 */
	public void initDefaultConfig(RefFileConfigDto refFileConfigDto) throws IOException {
		try {
			 try {
					if( refFileConfigDto!=null && refFileConfigDto.getIdDomaine()==1851){
						initDefaultConfigReferentiel(refFileConfigDto);
					}else{
						throw new FileNotFoundException();
					}
				} catch (FileNotFoundException e) {
					throw e;
				}
			} catch (IOException e) {
				throw e;
				
			}
		
		
	}

	
	/**
	 * [RefParametrageServiceImpl.findById] Find FileConfig BY Id
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param Id
	 * @return the RefFileConfigDto
	 */
	@Override
	public RefFileConfigDto findById(Integer id) {
		RefFileConfigDto refFileConfigDto = null;
		try{
			RefFileConfig refFileConfig = refFileConfigDao.findById(id);
			if(refFileConfig != null){
				refFileConfigDto = new RefFileConfigDto();
 				 mapper.map(refFileConfig, refFileConfigDto);
				log.info("success get dto: "+refFileConfigDto.getId());
				return refFileConfigDto;
			}
		}catch(Exception e){
			log.error("get failed", e);
			
		}
		return refFileConfigDto;
	}
	
	
	/**
	 * Generates a numeric string code with a defined length
	 * 
	 * @return the generated code
	 */
	private String generateCode(int length) {
		log.info("Executing operation generateCode");
		String code = null;
		try {
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
			log.info("The genrated Code: " + code);
		} catch (Exception e) {
			log.info("Exception: " + e);
		}
		return code;
	}
	
	
	/**
	 * [RefParametrageServiceImpl.renameFile] Method 
	 * @author BELDI Jamel  on : 24 mars 2014  15:51:06
	 * @param originalFileName
	 * @param renamedFileName
	 * @return
	 */
	private void renameFile(String originalFileName,
	            String renamedFileName) {
	 
	        File originalFile = new File(originalFileName);
	        /**
	         * Check if file exists
	         */
	        boolean fileExists = originalFile.exists();
	 
	        /**
	         * Check if it is a directory
	         */
	        boolean isDirectory = originalFile.isDirectory();
	 
	        /**
	         * If file does not exist, return
	         */
	        if (!fileExists) {
	 
	            log.info("File does not exist: " + originalFileName);
	            log.info("Rename Operation Aborted.");
	            return ;
	        }
	        /**
	         * If file is a directory, return
	         */
	        if (isDirectory) {
	        	log.info("The parameter you have provided is a directory: "
	                            + originalFileName);
	        	log.info("Rename Operation Aborted.");
	            return ;
	        }
	 
	        File renamedFile = new File(renamedFileName);
	 
	        boolean renamed = originalFile.renameTo(renamedFile);
	        if (renamed) {
	        	log.info("File Has Been Renamed Successfully.");
	        	log.info("Original File Name: " + originalFileName);
	        	log.info("Rename File Name: " + renamedFileName);
	            return ;
	        } else {
	        	log.info("Error occurred while trying to rename the file.");
	            return ;
	        }
	}
	

	
	
	
	/**
	 * [RefParametrageServiceImpl.initDefaultConfigReferentiel] Method 
	 * @author BELDI Jamel  on : 24 mars 2014  15:51:06
	 * @param RefFileConfigDto
	 * @return
	 */
	private void initDefaultConfigReferentiel(RefFileConfigDto refFileConfigDto) throws IOException {
		Scanner scanner;
		try {
			//File entree = new File(refParamDto.getUrlFile());
			String directory=new File(refFileConfigDto.getUrl()).getParent();
			StringBuilder urlTemp = new StringBuilder(directory);
			urlTemp.append("/").append(refFileConfigDto.getName()).append("_temp").append(generateCode(6)).append(".properties");
			File sortie = new File(urlTemp.toString());
			StringBuilder urlDefault = new StringBuilder(directory);
			urlDefault.append("/").append(refFileConfigDto.getName()).append("-default").append(".properties");
			 log.info("Created a temp file for the new parameters");
			scanner = new Scanner( new FileInputStream(urlDefault.toString()), "UTF-8");
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(sortie));
				while (scanner.hasNextLine()) {
				    String line = scanner.nextLine();
				   // log.info(line);
				     bw.write(line+"\n");
				     bw.flush();
				
				}
				bw.close();
				scanner.close();
				log.info("delete old:"+new File(refFileConfigDto.getUrl()).delete());
				log.info("rename new:"+sortie.getPath()+" to "+refFileConfigDto.getUrl());
				renameFile(sortie.getPath(), refFileConfigDto.getUrl());
				
					log.info("old file deleted and remplaced with the default parameters file ");
				
				 
				
						} catch (FileNotFoundException e) {
							throw e;	
							}
			} catch (IOException e) {
				throw e;
				
			}
		
		
	}
	
	/**
	 * [RefParametrageServiceImpl.viewParamsRefrentiel] view list of parameters REFERENTIEl
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param RefFileConfigDto
	 * @return the list of refParamDto
	 */
	private List<RefParamDto> viewParamsRefrentiel(RefFileConfigDto refFileConfigDto) throws FileNotFoundException {
		List<RefParamDto>  result = null;
			Scanner scanner;
			try {
			//System.setProperty( "file.encoding", "UTF-8" );
			scanner = new Scanner( new FileInputStream(refFileConfigDto.getUrl()));
			result = new ArrayList<RefParamDto>();
			
			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    //log.info(line);
			    StringTokenizer param = new StringTokenizer(line, "=");
			    if(param.countTokens()==2 ){
			    	RefParamDto refParamDto= new RefParamDto();
					mapper.map(refFileConfigDto, refParamDto);
			    	refParamDto.setKey(param.nextToken().trim());
			    	String value=param.nextToken().trim();
			    	refParamDto.setValue(encodingUTF8(value));
			    	result.add(refParamDto);
			    }
			   
			}
			 
			scanner.close();
			} catch (FileNotFoundException e) {
			throw e;	
			}
			return result;
		
	}
	
	/**
	 * [RefParametrageServiceImpl.updateParamValue] Modify value of RefParamDto in the file
	 * @author BELDI Jamel on : 19 mars 2014  18:47:52
	 * @param RefParamDto
	 * @return void
	 * @throws IOException 
	 */
	private void updateParamValueReferentiel(RefParamDto refParamDto) throws IOException {

		Scanner scanner;
		try {
			//File entree = new File(refParamDto.getUrlFile());
			String directory=new File(refParamDto.getUrlFile()).getParent();
			StringBuilder urlTemp = new StringBuilder(directory);
			urlTemp.append("/").append(refParamDto.getNameFile()).append("_temp").append(generateCode(6)).append(".properties");
			File sortie = new File(urlTemp.toString());
			 log.info("Created a temp file for the new parameters");
			scanner = new Scanner( new FileInputStream(refParamDto.getUrlFile()), "UTF-8");
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(sortie));
				while (scanner.hasNextLine()) {
				    String line = scanner.nextLine();
				   // log.info(line);
				 if(line.startsWith(refParamDto.getKey()+"=")){
					
				     bw.write(refParamDto.getKey()+"="+decodingUTF8(refParamDto.getValue())+"\n");
				     bw.flush();
				     log.info("ligne found and modified");
				 }else{
				     bw.write(line+"\n");
				     bw.flush();
				 }
				}
				bw.close();
				scanner.close();
				log.info("delete old:"+new File(refParamDto.getUrlFile()).delete());
				log.info("rename new:"+sortie.getPath()+" to "+refParamDto.getUrlFile());
				renameFile(sortie.getPath(), refParamDto.getUrlFile());
					log.info("old file deleted and remplaced with the new parameters");
				
				 
				
						} catch (FileNotFoundException e) {
							throw e;	
							}
			} catch (IOException e) {
				throw e;
				
			}
		

	}

	/***
	 * test
	 * [RefParametrageServiceImpl.main] Method 
	 * @author BELDI Jamel  on : 24 mars 2014  15:53:34
	 * @param arg
	 */
	
	public static void main (String [] arg){
		
		String text = "toute ééééé eeeeuè à // @ &" ;// "\\u0642\\u0627\\u0626\\u0645\\u0629\\u0627\\u0644\\u0647\\u064A\\u0627\\u0643\\u0644";
				text = StringEscapeUtils.unescapeJava(text);
				System.out.println("text " + text);
				text  = StringEscapeUtils.escapeJava(text);
				System.out.println("unicode " + text);
//		RefParametrageServiceImpl me= new RefParametrageServiceImpl();
////		System.setProperty( "file.encoding", "UTF-8" );
////		String url = "C:/Users/jbeldi/git/R2-SII-MESRS-SRC/referentiel/webreferentiel/target/classes/test-resources.properties";
//		String line= "key=\\u0642\\u0627\\u0626\\u0645\\u0629\\u0627\\u0644\\u0647\\u064A\\u0627\\u0643\\u0644";
//		StringTokenizer param = new StringTokenizer(line, "=");
//		param.nextToken().trim();
//		String arabe = param.nextToken().trim();
//		//String francais="Liste des r�les";
//		
//		String arabeUTF8 = me.encodingUTF8(arabe);
//		
//		//String francaisUTF8 = me.encodingUTF8(francais);
//		
//		System.out.println(arabeUTF8);
		
		//System.out.println(francaisUTF8);
//		//String url ="C:/Users/jbeldi/git/R2-SII-MESRS-SRC/referentiel/fwkreferentiel/target/classes/ldapconfig.properties";
//		System.out.println(url);
//		RefFileConfigDto refFileConfigDto = new RefFileConfigDto();
//		refFileConfigDto.setUrl(url);
//		refFileConfigDto.setIdDomaine(1851);
//		RefParametrageServiceImpl me= new RefParametrageServiceImpl();
//		try {
//			me.viewParams(refFileConfigDto);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		

//		System.out.println(url);
//		RefParamDto refParamDto = new RefParamDto();
//		refParamDto.setUrlFile(url);
//		refParamDto.setKey("authentification_password_required");
//		refParamDto.setValue("value");
//		refParamDto.setNameFile("test-resources");
//		RefParametrageServiceImpl me= new RefParametrageServiceImpl();
//		try {
//			me.updateParamValue(refParamDto);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		
//		RefParametrageServiceImpl me= new RefParametrageServiceImpl();
//		me.renameFile("D:/test.txt", "D:/test2.txt");
		
	}

	/**
	 * Use ConfigHolder instead.
	 */
	@Deprecated
	@Override
	public RefParamDto getParamConfiguration(String key, String configuration) {
		log.info("getParamConfiguration:"+key);
		RefParamDto  refParamDto= new RefParamDto();
		try{
			List<RefFileConfigDto> listFiles= null;
			if(configuration==null){
				return refParamDto;
			}
			else if(configuration.equals(RefUtilConstant.CONFIGURATION_UTIL)){
				listFiles = findResourcesConfig(1851,4);
			}else if(configuration.equals(RefUtilConstant.CONFIGURATION_LDAP)){
				listFiles = findResourcesConfig(1851,2);
			}else if(configuration.equals(RefUtilConstant.CONFIGURATION_BD)){
				listFiles = findResourcesConfig(1851,1);
			}else if(configuration.equals(RefUtilConstant.CONFIGURATION_WS)){
				listFiles = findResourcesConfig(1851,3);
			}else if(configuration.equals(RefUtilConstant.CONFIGURATION_DOC)){
				listFiles = findResourcesConfig(1851,5);
			}
			
			if(listFiles!=null && !listFiles.isEmpty()){
				RefFileConfigDto refFileConfigDto = listFiles.get(0);
				Scanner scanner;
				try {
					scanner = new Scanner( new FileInputStream(refFileConfigDto.getUrl()), "UTF-8");
				} catch (FileNotFoundException e) {
					return refParamDto;
				}
				while (scanner.hasNextLine()) {
				    String line = scanner.nextLine();
				    //log.info(line);
				    StringTokenizer param = new StringTokenizer(line, "=");
				    if(param.countTokens()==2 ){
				    	if(param.nextToken().trim().equals(key)){				 
						mapper.map(refFileConfigDto, refParamDto);
				    	refParamDto.setKey(key);
				    	refParamDto.setValue(param.nextToken().trim());
				    	log.info("Sucess getParamConfiguration:"+refParamDto.getValue());
				    	scanner.close();
				    	return refParamDto;
				    	}
				    	
				    }
				   
				}
				scanner.close();
			}
		
		return refParamDto;
	} catch (RuntimeException e) {
		log.error("get failed", e);
		return refParamDto;
	}
	}
	
	
	private String encodingUTF8(String value){
		
		try {
			if(value!=null){
			String valueUTF8 = new String(StringEscapeUtils.unescapeJava(value)/*value.getBytes("UTF-8"), "UTF-8"*/);
			return valueUTF8;
			}
		} catch (Exception e) {
			return value;
		}
		return value;
	}
	
	 private String decodingUTF8(String value){
		
		try {
			if(value!=null){
			String valueUTF8 = new String(StringEscapeUtils.escapeJava(value)/*value.getBytes("UTF-8"), "UTF-8"*/);
			return valueUTF8;
			}
		} catch (Exception e) {
			return value;
		}
		return value;
	}
}
