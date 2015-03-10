
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;





/**
 * 
 * @author BELDI Jamel  on : 1 oct. 2014  17:04:34
 */

public class ResponsableExamenDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 1 oct. 2014  17:14:28
	 */
	private static final long serialVersionUID = 1L;
	private int id;
//	private ExamenSession examenSession;
	private Integer salleExamenId;
	private Integer refLieuId;
	private String refLieuLibelle;
	private Integer refLieuTypeId;
	private String refLieuTypeLibelle;
	private int examenSessionId;
	private Integer rattachementMcId;
	private Integer ueId;
	private String ueCode;
	private String ueLibelleFr;
	private Integer mcId;
	private String mcCode;
	private String mcLibelleFr;	
//	private RefIndividu refIndividu;
	private Integer individuId;
	private String individuIdentifiant;
	private String individuNomArabe;
	private String individuNomLatin;
	private String individuPrenomArabe;
	private String individuPrenomLatin;
	private Date individuDateNaissance;
	private Boolean absent;
	private String motifAbsence;
	private String nomPrenom;

	public ResponsableExamenDto() {
	}

	/**
	 * [AbsenceResponsableDto.id] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [AbsenceResponsableDto.id] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [AbsenceResponsableDto.absent] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @return the absent
	 */
	public Boolean getAbsent() {
		return absent;
	}

	/**
	 * [AbsenceResponsableDto.absent] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @param absent the absent to set
	 */
	public void setAbsent(Boolean absent) {
		this.absent = absent;
	}

	/**
	 * [AbsenceResponsableDto.motifAbsence] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @return the motifAbsence
	 */
	public String getMotifAbsence() {
		return motifAbsence;
	}

	/**
	 * [AbsenceResponsableDto.motifAbsence] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:13:32
	 * @param motifAbsence the motifAbsence to set
	 */
	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

	/**
	 * [AbsenceResponsableDto.individuId] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [AbsenceResponsableDto.individuId] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuId the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [AbsenceResponsableDto.individuIdentifiant] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [AbsenceResponsableDto.individuIdentifiant] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuIdentifiant the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [AbsenceResponsableDto.individuNomArabe] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuNomArabe
	 */
	public String getIndividuNomArabe() {
		return individuNomArabe;
	}

	/**
	 * [AbsenceResponsableDto.individuNomArabe] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuNomArabe the individuNomArabe to set
	 */
	public void setIndividuNomArabe(String individuNomArabe) {
		this.individuNomArabe = individuNomArabe;
	}

	/**
	 * [AbsenceResponsableDto.individuNomLatin] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuNomLatin
	 */
	public String getIndividuNomLatin() {
		return individuNomLatin;
	}

	/**
	 * [AbsenceResponsableDto.individuNomLatin] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuNomLatin the individuNomLatin to set
	 */
	public void setIndividuNomLatin(String individuNomLatin) {
		this.individuNomLatin = individuNomLatin;
	}

	/**
	 * [AbsenceResponsableDto.individuPrenomArabe] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuPrenomArabe
	 */
	public String getIndividuPrenomArabe() {
		return individuPrenomArabe;
	}

	/**
	 * [AbsenceResponsableDto.individuPrenomArabe] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuPrenomArabe the individuPrenomArabe to set
	 */
	public void setIndividuPrenomArabe(String individuPrenomArabe) {
		this.individuPrenomArabe = individuPrenomArabe;
	}

	/**
	 * [AbsenceResponsableDto.individuPrenomLatin] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @return the individuPrenomLatin
	 */
	public String getIndividuPrenomLatin() {
		return individuPrenomLatin;
	}

	/**
	 * [AbsenceResponsableDto.individuPrenomLatin] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:19:49
	 * @param individuPrenomLatin the individuPrenomLatin to set
	 */
	public void setIndividuPrenomLatin(String individuPrenomLatin) {
		this.individuPrenomLatin = individuPrenomLatin;
	}

	/**
	 * [AbsenceResponsableDto.examenSessionId] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the examenSessionId
	 */
	public int getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [AbsenceResponsableDto.examenSessionId] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param examenSessionId the examenSessionId to set
	 */
	public void setExamenSessionId(int examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [AbsenceResponsableDto.rattachementMcId] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [AbsenceResponsableDto.rattachementMcId] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [AbsenceResponsableDto.ueId] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the ueId
	 */
	public Integer getUeId() {
		return ueId;
	}

	/**
	 * [AbsenceResponsableDto.ueId] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param ueId the ueId to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	/**
	 * [AbsenceResponsableDto.ueCode] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the ueCode
	 */
	public String getUeCode() {
		return ueCode;
	}

	/**
	 * [AbsenceResponsableDto.ueCode] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param ueCode the ueCode to set
	 */
	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}

	/**
	 * [AbsenceResponsableDto.ueLibelleFr] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [AbsenceResponsableDto.ueLibelleFr] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param ueLibelleFr the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [AbsenceResponsableDto.mcId] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the mcId
	 */
	public Integer getMcId() {
		return mcId;
	}

	/**
	 * [AbsenceResponsableDto.mcId] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param mcId the mcId to set
	 */
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	/**
	 * [AbsenceResponsableDto.mcCode] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the mcCode
	 */
	public String getMcCode() {
		return mcCode;
	}

	/**
	 * [AbsenceResponsableDto.mcCode] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param mcCode the mcCode to set
	 */
	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}

	/**
	 * [AbsenceResponsableDto.mcLibelleFr] Getter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [AbsenceResponsableDto.mcLibelleFr] Setter 
	 * @author BELDI Jamel on : 1 oct. 2014  17:21:58
	 * @param mcLibelleFr the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [AbsenceResponsableDto.salleExamenId] Getter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @return the salleExamenId
	 */
	public Integer getSalleExamenId() {
		return salleExamenId;
	}

	/**
	 * [AbsenceResponsableDto.salleExamenId] Setter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @param salleExamenId the salleExamenId to set
	 */
	public void setSalleExamenId(Integer salleExamenId) {
		this.salleExamenId = salleExamenId;
	}

	/**
	 * [AbsenceResponsableDto.refLieuId] Getter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @return the refLieuId
	 */
	public Integer getRefLieuId() {
		return refLieuId;
	}

	/**
	 * [AbsenceResponsableDto.refLieuId] Setter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @param refLieuId the refLieuId to set
	 */
	public void setRefLieuId(Integer refLieuId) {
		this.refLieuId = refLieuId;
	}

	/**
	 * [AbsenceResponsableDto.refLieuLibelle] Getter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @return the refLieuLibelle
	 */
	public String getRefLieuLibelle() {
		return refLieuLibelle;
	}

	/**
	 * [AbsenceResponsableDto.refLieuLibelle] Setter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @param refLieuLibelle the refLieuLibelle to set
	 */
	public void setRefLieuLibelle(String refLieuLibelle) {
		this.refLieuLibelle = refLieuLibelle;
	}

	/**
	 * [AbsenceResponsableDto.refLieuTypeId] Getter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @return the refLieuTypeId
	 */
	public Integer getRefLieuTypeId() {
		return refLieuTypeId;
	}

	/**
	 * [AbsenceResponsableDto.refLieuTypeId] Setter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @param refLieuTypeId the refLieuTypeId to set
	 */
	public void setRefLieuTypeId(Integer refLieuTypeId) {
		this.refLieuTypeId = refLieuTypeId;
	}

	/**
	 * [AbsenceResponsableDto.refLieuTypeLibelle] Getter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @return the refLieuTypeLibelle
	 */
	public String getRefLieuTypeLibelle() {
		return refLieuTypeLibelle;
	}

	/**
	 * [AbsenceResponsableDto.refLieuTypeLibelle] Setter 
	 * @author BELDI Jamel on : 16 oct. 2014  15:19:10
	 * @param refLieuTypeLibelle the refLieuTypeLibelle to set
	 */
	public void setRefLieuTypeLibelle(String refLieuTypeLibelle) {
		this.refLieuTypeLibelle = refLieuTypeLibelle;
	}
	
	

	/**
	 * [ResponsableExamenDto.individuDateNaissance] Getter 
	 * @author MAKERRI Sofiane on : 5 févr. 2015  11:00:23
	 * @return the individuDateNaissance
	 */
	public Date getIndividuDateNaissance() {
		return individuDateNaissance;
	}

	/**
	 * [ResponsableExamenDto.individuDateNaissance] Setter 
	 * @author MAKERRI Sofiane on : 5 févr. 2015  11:00:23
	 * @param individuDateNaissance the individuDateNaissance to set
	 */
	public void setIndividuDateNaissance(Date individuDateNaissance) {
		this.individuDateNaissance = individuDateNaissance;
	}
	
	

	/**
	 * [ResponsableExamenDto.nomPrenom] Getter 
	 * @author MAKERRI Sofiane on : 8 févr. 2015  12:17:34
	 * @return the nomPrenom
	 */
	public String getNomPrenom() {
		//nomPrenom =  individuNomLatin + " "  +  individuPrenomLatin;
		return nomPrenom;
	}

	/**
	 * [ResponsableExamenDto.nomPrenom] Setter 
	 * @author MAKERRI Sofiane on : 8 févr. 2015  12:17:34
	 * @param nomPrenom the nomPrenom to set
	 */
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	@Override
	public String toString() {
		return individuNomLatin + " "  +  individuPrenomLatin;
	}
	
}
