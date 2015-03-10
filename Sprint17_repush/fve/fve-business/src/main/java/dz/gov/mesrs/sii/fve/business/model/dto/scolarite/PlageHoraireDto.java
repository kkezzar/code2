package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.Date;

/**
 * 
 * @author BELDI Jamel on : 7 oct. 2014 17:46:51
 */

public class PlageHoraireDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 7 oct. 2014 17:46:44
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code;
	private Short numero;
	private String libelleFr;
	private String libelleAr;
	private Date heureDebut;
	private Date heureFin;
	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;

	// private RefEtablissement refEtablissement;
	private Integer etablissementId;
	private String llEtablissementArabe;
	private String llEtablissementLatin;

	public PlageHoraireDto() {
	}

	/**
	 * [PlageHoraireDto.id] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [PlageHoraireDto.id] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [PlageHoraireDto.code] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [PlageHoraireDto.code] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [PlageHoraireDto.numero] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the numero
	 */
	public Short getNumero() {
		return numero;
	}

	/**
	 * [PlageHoraireDto.numero] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param numero the numero to set
	 */
	public void setNumero(Short numero) {
		this.numero = numero;
	}

	/**
	 * [PlageHoraireDto.libelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [PlageHoraireDto.libelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [PlageHoraireDto.libelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [PlageHoraireDto.libelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [PlageHoraireDto.heureDebut] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}

	/**
	 * [PlageHoraireDto.heureDebut] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * [PlageHoraireDto.heureFin] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}

	/**
	 * [PlageHoraireDto.heureFin] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	/**
	 * [PlageHoraireDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [PlageHoraireDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [PlageHoraireDto.anneeAcademiqueCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [PlageHoraireDto.anneeAcademiqueCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [PlageHoraireDto.etablissementId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the etablissementId
	 */
	public Integer getEtablissementId() {
		return etablissementId;
	}

	/**
	 * [PlageHoraireDto.etablissementId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param etablissementId the etablissementId to set
	 */
	public void setEtablissementId(Integer etablissementId) {
		this.etablissementId = etablissementId;
	}

	/**
	 * [PlageHoraireDto.llEtablissementArabe] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * [PlageHoraireDto.llEtablissementArabe] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param llEtablissementArabe the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * [PlageHoraireDto.llEtablissementLatin] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * [PlageHoraireDto.llEtablissementLatin] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:27:44
	 * @param llEtablissementLatin the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	
}
