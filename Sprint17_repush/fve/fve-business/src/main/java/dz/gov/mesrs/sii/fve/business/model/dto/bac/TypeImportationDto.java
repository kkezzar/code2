package dz.gov.mesrs.sii.fve.business.model.dto.bac;


/**
 * @author  Rafik LAIB  on : 22 mai 2014  14:14:02
 */
public class TypeImportationDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	
	public TypeImportationDto() {
	}

	/**
	 * [TypeImportationDto.id] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:42
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeImportationDto.id] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:42
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeImportationDto.code] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [TypeImportationDto.code] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [TypeImportationDto.libelleFr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [TypeImportationDto.libelleFr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [TypeImportationDto.libelleAr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [TypeImportationDto.libelleAr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:13:43
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}
	
}
