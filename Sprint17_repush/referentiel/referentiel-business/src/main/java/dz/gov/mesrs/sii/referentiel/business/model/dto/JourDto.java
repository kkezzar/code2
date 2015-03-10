package dz.gov.mesrs.sii.referentiel.business.model.dto;



/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:37:58
 */

public class JourDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 * @author BELDI Jamel  on : 7 oct. 2014  17:38:05
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Boolean estOuvert;


	public JourDto() {
	}

	public JourDto(int id, String code) {
		this.id = id;
		this.code = code;
	}

	/**
	 * [JourDto.id] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [JourDto.id] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [JourDto.code] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [JourDto.code] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [JourDto.libelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [JourDto.libelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [JourDto.libelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [JourDto.libelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [JourDto.estOuvert] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @return the estOuvert
	 */
	public Boolean getEstOuvert() {
		return estOuvert;
	}

	/**
	 * [JourDto.estOuvert] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  17:43:08
	 * @param estOuvert the estOuvert to set
	 */
	public void setEstOuvert(Boolean estOuvert) {
		this.estOuvert = estOuvert;
	}

	
}
