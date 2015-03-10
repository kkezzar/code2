package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;


import java.util.Date;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:48:33
 */

public class CelluleHoraireDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:49:21
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	//private PlageHoraire plageHoraire;
	private Integer plageHoraireId;
	private String plageHoraireCode;
	private Short plageHoraireNumero;
	private String plageHoraireLibelleFr;
	private String plageHoraireLibelleAr;
	private Date plageHoraireHeureDebut;
	private Date plageHoraireHeureFin;
	//private Jour jour;
	private Integer jourId;
	private String jourCode;
	private String jourLibelleFr;
	private String jourLibelleAr;

	public CelluleHoraireDto() {
	}

	/**
	 * [CelluleHoraireDto.id] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [CelluleHoraireDto.id] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireId
	 */
	public Integer getPlageHoraireId() {
		return plageHoraireId;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireId the plageHoraireId to set
	 */
	public void setPlageHoraireId(Integer plageHoraireId) {
		this.plageHoraireId = plageHoraireId;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireCode
	 */
	public String getPlageHoraireCode() {
		return plageHoraireCode;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireCode the plageHoraireCode to set
	 */
	public void setPlageHoraireCode(String plageHoraireCode) {
		this.plageHoraireCode = plageHoraireCode;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireNumero] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireNumero
	 */
	public Short getPlageHoraireNumero() {
		return plageHoraireNumero;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireNumero] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireNumero the plageHoraireNumero to set
	 */
	public void setPlageHoraireNumero(Short plageHoraireNumero) {
		this.plageHoraireNumero = plageHoraireNumero;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireLibelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireLibelleFr
	 */
	public String getPlageHoraireLibelleFr() {
		return plageHoraireLibelleFr;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireLibelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireLibelleFr the plageHoraireLibelleFr to set
	 */
	public void setPlageHoraireLibelleFr(String plageHoraireLibelleFr) {
		this.plageHoraireLibelleFr = plageHoraireLibelleFr;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireLibelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireLibelleAr
	 */
	public String getPlageHoraireLibelleAr() {
		return plageHoraireLibelleAr;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireLibelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireLibelleAr the plageHoraireLibelleAr to set
	 */
	public void setPlageHoraireLibelleAr(String plageHoraireLibelleAr) {
		this.plageHoraireLibelleAr = plageHoraireLibelleAr;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireHeureDebut] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireHeureDebut
	 */
	public Date getPlageHoraireHeureDebut() {
		return plageHoraireHeureDebut;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireHeureDebut] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireHeureDebut the plageHoraireHeureDebut to set
	 */
	public void setPlageHoraireHeureDebut(Date plageHoraireHeureDebut) {
		this.plageHoraireHeureDebut = plageHoraireHeureDebut;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireHeureFin] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the plageHoraireHeureFin
	 */
	public Date getPlageHoraireHeureFin() {
		return plageHoraireHeureFin;
	}

	/**
	 * [CelluleHoraireDto.plageHoraireHeureFin] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param plageHoraireHeureFin the plageHoraireHeureFin to set
	 */
	public void setPlageHoraireHeureFin(Date plageHoraireHeureFin) {
		this.plageHoraireHeureFin = plageHoraireHeureFin;
	}

	/**
	 * [CelluleHoraireDto.jourId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the jourId
	 */
	public Integer getJourId() {
		return jourId;
	}

	/**
	 * [CelluleHoraireDto.jourId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param jourId the jourId to set
	 */
	public void setJourId(Integer jourId) {
		this.jourId = jourId;
	}

	/**
	 * [CelluleHoraireDto.jourCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the jourCode
	 */
	public String getJourCode() {
		return jourCode;
	}

	/**
	 * [CelluleHoraireDto.jourCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param jourCode the jourCode to set
	 */
	public void setJourCode(String jourCode) {
		this.jourCode = jourCode;
	}

	/**
	 * [CelluleHoraireDto.jourLibelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the jourLibelleFr
	 */
	public String getJourLibelleFr() {
		return jourLibelleFr;
	}

	/**
	 * [CelluleHoraireDto.jourLibelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param jourLibelleFr the jourLibelleFr to set
	 */
	public void setJourLibelleFr(String jourLibelleFr) {
		this.jourLibelleFr = jourLibelleFr;
	}

	/**
	 * [CelluleHoraireDto.jourLibelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @return the jourLibelleAr
	 */
	public String getJourLibelleAr() {
		return jourLibelleAr;
	}

	/**
	 * [CelluleHoraireDto.jourLibelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:28:15
	 * @param jourLibelleAr the jourLibelleAr to set
	 */
	public void setJourLibelleAr(String jourLibelleAr) {
		this.jourLibelleAr = jourLibelleAr;
	}

	
	

	

}
