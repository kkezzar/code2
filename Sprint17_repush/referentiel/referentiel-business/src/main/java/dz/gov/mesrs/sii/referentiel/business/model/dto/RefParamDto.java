package dz.gov.mesrs.sii.referentiel.business.model.dto;

import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "RefParamDto")
public class RefParamDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 19 mars 2014  15:17:51
	 */
	private static final long serialVersionUID = 1L;

	private Integer idFile;
	private String nameFile;
	private String urlFile;
	private String typeFile;
	private String defaultFile;
	private Integer idEntity;
	private String nameEntity;
	private Integer idLanguage;
	private String codeLanguage;
	private String libelleLongFrLanguage;
	private String libelleCourtFrLanguage;
	
	
	
	private Integer idConfiguration;
	private String nameConfiguration;
	
	private Integer idDomaine;
	private String nomDomaine;
	
	private String key;
	private String value;
	
	public RefParamDto() {
	}


	

	/**
	 * [RefFileConfig.idEntity] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the idEntity
	 */
	public Integer getIdEntity() {
		return idEntity;
	}

	/**
	 * [RefFileConfig.idEntity] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param idEntity the idEntity to set
	 */
	public void setIdEntity(Integer idEntity) {
		this.idEntity = idEntity;
	}

	/**
	 * [RefFileConfig.nameEntity] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the nameEntity
	 */
	public String getNameEntity() {
		return nameEntity;
	}

	/**
	 * [RefFileConfig.nameEntity] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param nameEntity the nameEntity to set
	 */
	public void setNameEntity(String nameEntity) {
		this.nameEntity = nameEntity;
	}

	/**
	 * [RefFileConfig.idLanguage] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the idLanguage
	 */
	public Integer getIdLanguage() {
		return idLanguage;
	}

	/**
	 * [RefFileConfig.idLanguage] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param idLanguage the idLanguage to set
	 */
	public void setIdLanguage(Integer idLanguage) {
		this.idLanguage = idLanguage;
	}

	/**
	 * [RefFileConfig.codeLanguage] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the codeLanguage
	 */
	public String getCodeLanguage() {
		return codeLanguage;
	}

	/**
	 * [RefFileConfig.codeLanguage] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param codeLanguage the codeLanguage to set
	 */
	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	/**
	 * [RefFileConfig.libelleLongFrLanguage] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the libelleLongFrLanguage
	 */
	public String getLibelleLongFrLanguage() {
		return libelleLongFrLanguage;
	}

	/**
	 * [RefFileConfig.libelleLongFrLanguage] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param libelleLongFrLanguage the libelleLongFrLanguage to set
	 */
	public void setLibelleLongFrLanguage(String libelleLongFrLanguage) {
		this.libelleLongFrLanguage = libelleLongFrLanguage;
	}

	/**
	 * [RefFileConfig.libelleCourtFrLanguage] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the libelleCourtFrLanguage
	 */
	public String getLibelleCourtFrLanguage() {
		return libelleCourtFrLanguage;
	}

	/**
	 * [RefFileConfig.libelleCourtFrLanguage] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param libelleCourtFrLanguage the libelleCourtFrLanguage to set
	 */
	public void setLibelleCourtFrLanguage(String libelleCourtFrLanguage) {
		this.libelleCourtFrLanguage = libelleCourtFrLanguage;
	}

	/**
	 * [RefFileConfig.idConfiguration] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the idConfiguration
	 */
	public Integer getIdConfiguration() {
		return idConfiguration;
	}

	/**
	 * [RefFileConfig.idConfiguration] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param idConfiguration the idConfiguration to set
	 */
	public void setIdConfiguration(Integer idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	/**
	 * [RefFileConfig.nameConfiguration] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the nameConfiguration
	 */
	public String getNameConfiguration() {
		return nameConfiguration;
	}

	/**
	 * [RefFileConfig.nameConfiguration] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param nameConfiguration the nameConfiguration to set
	 */
	public void setNameConfiguration(String nameConfiguration) {
		this.nameConfiguration = nameConfiguration;
	}

	/**
	 * [RefFileConfigDto.idDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:34:38
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [RefFileConfigDto.idDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:34:38
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}

	/**
	 * [RefFileConfigDto.nomDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:34:38
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}

	/**
	 * [RefFileConfigDto.nomDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:34:38
	 * @param nomDomaine the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}




	/**
	 * [RefParamDto.idFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the idFile
	 */
	public Integer getIdFile() {
		return idFile;
	}




	/**
	 * [RefParamDto.idFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param idFile the idFile to set
	 */
	public void setIdFile(Integer idFile) {
		this.idFile = idFile;
	}




	/**
	 * [RefParamDto.nameFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the nameFile
	 */
	public String getNameFile() {
		return nameFile;
	}




	/**
	 * [RefParamDto.nameFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param nameFile the nameFile to set
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}




	/**
	 * [RefParamDto.urlFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the urlFile
	 */
	public String getUrlFile() {
		return urlFile;
	}




	/**
	 * [RefParamDto.urlFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param urlFile the urlFile to set
	 */
	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}




	/**
	 * [RefParamDto.typeFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the typeFile
	 */
	public String getTypeFile() {
		return typeFile;
	}




	/**
	 * [RefParamDto.typeFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param typeFile the typeFile to set
	 */
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}




	




	/**
	 * [RefParamDto.key] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the key
	 */
	public String getKey() {
		return key;
	}




	/**
	 * [RefParamDto.key] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}




	/**
	 * [RefParamDto.value] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @return the value
	 */
	public String getValue() {
		return value;
	}




	/**
	 * [RefParamDto.value] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:47:26
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}




	/**
	 * [RefParamDto.defaultFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:51:27
	 * @return the defaultFile
	 */
	public String getDefaultFile() {
		return defaultFile;
	}




	/**
	 * [RefParamDto.defaultFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:51:27
	 * @param defaultFile the defaultFile to set
	 */
	public void setDefaultFile(String defaultFile) {
		this.defaultFile = defaultFile;
	}


	

}
