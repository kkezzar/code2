package dz.gov.mesrs.sii.referentiel.business.model.dto;






/**
 * @author BELDI Jamel  on : 19 mars 2014  15:05:27
 */
public class RefEntityDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 19 mars 2014  15:03:31
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;	
	private String name;
	private Integer idDomaine;
	private String nomDomaine;
	

	public RefEntityDto() {
	}


	/**
	 * [RefEntityDto.id] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefEntityDto.id] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefEntityDto.name] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * [RefEntityDto.name] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * [RefEntityDto.idDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}


	/**
	 * [RefEntityDto.idDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}


	/**
	 * [RefEntityDto.nomDomaine] Getter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @return the nomDomaine
	 */
	public String getNomDomaine() {
		return nomDomaine;
	}


	/**
	 * [RefEntityDto.nomDomaine] Setter 
	 * @author BELDI Jamel on : 19 mars 2014  15:04:47
	 * @param nomDomaine the nomDomaine to set
	 */
	public void setNomDomaine(String nomDomaine) {
		this.nomDomaine = nomDomaine;
	}

	

	


}
