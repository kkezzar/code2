/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto.java] 
 * @author rlaib on : 12 oct. 2014  15:06:05
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

/**
 * @author rlaib  on : 12 oct. 2014  15:06:05
 */
public class TypeSeanceDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:19
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	
	public TypeSeanceDto() {
		
	}

	/**
	 * [TypeSeanceDto.id] Getter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeSeanceDto.id] Setter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeSeanceDto.code] Getter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [TypeSeanceDto.code] Setter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [TypeSeanceDto.libelleFr] Getter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [TypeSeanceDto.libelleFr] Setter 
	 * @author rlaib on : 29 oct. 2014  11:59:46
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	
}
