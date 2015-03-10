package dz.gov.mesrs.sii.referentiel.business.model.dto;
public class RefFileConfigDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 19 mars 2014  15:17:51
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	private String url;
	private String type;
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
	
	public RefFileConfigDto() {
	}

	/**
	 * [RefFileConfig.id] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefFileConfig.id] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefFileConfig.name] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * [RefFileConfig.name] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * [RefFileConfig.url] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * [RefFileConfig.url] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * [RefFileConfig.type] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * [RefFileConfig.type] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * [RefFileConfig.defaultFile] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @return the defaultFile
	 */
	public String getDefaultFile() {
		return defaultFile;
	}

	/**
	 * [RefFileConfig.defaultFile] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:18:42
	 * @param defaultFile the defaultFile to set
	 */
	public void setDefaultFile(String defaultFile) {
		this.defaultFile = defaultFile;
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
	 * @author BELDI Jamel on : 20 mars 2014  11:34:43
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [RefFileConfigDto.idDomaine] Setter 
	 * @author BELDI Jamel on : 20 mars 2014  11:34:43
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

	

}
