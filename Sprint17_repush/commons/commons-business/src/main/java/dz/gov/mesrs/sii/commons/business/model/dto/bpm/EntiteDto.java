/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class EntiteDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 16:03:47
	 */
	private static final long serialVersionUID = 91255335725944966L;
	private int id;
	private String code;
	private String libelle;

	// Domaine
	private int domaineId;
	private String domaineCode;
	private String domaineLibelleFr;
	
	public EntiteDto() {
	}

	/**
	 * [EntiteDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EntiteDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EntiteDto.code] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [EntiteDto.code] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [EntiteDto.libelle] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [EntiteDto.libelle] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:44:24
	 * @param libelle
	 *            the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [EntiteDto.domaineId] Getter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @return the domaineId
	 */
	public int getDomaineId() {
		return domaineId;
	}

	/**
	 * [EntiteDto.domaineId] Setter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @param domaineId the domaineId to set
	 */
	public void setDomaineId(int domaineId) {
		this.domaineId = domaineId;
	}

	/**
	 * [EntiteDto.domaineCode] Getter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @return the domaineCode
	 */
	public String getDomaineCode() {
		return domaineCode;
	}

	/**
	 * [EntiteDto.domaineCode] Setter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @param domaineCode the domaineCode to set
	 */
	public void setDomaineCode(String domaineCode) {
		this.domaineCode = domaineCode;
	}

	/**
	 * [EntiteDto.domaineLibelleFr] Getter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @return the domaineLibelleFr
	 */
	public String getDomaineLibelleFr() {
		return domaineLibelleFr;
	}

	/**
	 * [EntiteDto.domaineLibelleFr] Setter 
	 * @author rlaib on : 21 janv. 2015  16:18:41
	 * @param domaineLibelleFr the domaineLibelleFr to set
	 */
	public void setDomaineLibelleFr(String domaineLibelleFr) {
		this.domaineLibelleFr = domaineLibelleFr;
	}

	
}
