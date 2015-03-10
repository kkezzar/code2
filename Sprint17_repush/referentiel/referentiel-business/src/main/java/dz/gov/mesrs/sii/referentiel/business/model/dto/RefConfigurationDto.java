package dz.gov.mesrs.sii.referentiel.business.model.dto;




/**
 * @author BELDI Jamel  on : 19 mars 2014  15:06:38
 */
public class RefConfigurationDto implements java.io.Serializable {
	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 19 mars 2014  15:06:10
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer idDomaine;
	private String nomDomaine;
	/**
	 * [RefConfigurationDto.id] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefConfigurationDto.id] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefConfigurationDto.name] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * [RefConfigurationDto.name] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * [RefConfigurationDto.idDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}
	/**
	 * [RefConfigurationDto.idDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}
	/**
	 * [RefConfigurationDto.nomDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}
	/**
	 * [RefConfigurationDto.nomDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:06:22
	 * @param nomDomaine the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}
	

}
