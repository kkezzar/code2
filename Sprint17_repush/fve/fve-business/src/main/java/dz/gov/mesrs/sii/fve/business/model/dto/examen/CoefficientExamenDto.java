/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.notation.ParametrageCoefficientExamenDto.java] 
 * @author MAKERRI Sofiane on : 5 janv. 2015  09:24:16
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 5 janv. 2015  09:24:16
 */

public class CoefficientExamenDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 5 janv. 2015  09:24:20
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Integer oofId;
	private String ofLibelleFr;
	private Integer periodeId;
	private String periodeLibelleFr;
	private String niveauLibelleFr;
	private Integer rattachementMcId;
	private String mcLibelleFr;
	private double mcCredit;
	private double mcCoefficient;
	private String ueLibelleFr;
	private double coefficientExamen;
	private double coefficientControleContinu;
	private double coefficientControleIntermediaire;
	private String anneeAcademiqueCode;
	private short anneeAcademiquePremiereAnnee;
	private short anneeAcademiqueDeuxiemeAnnee;
	
	public CoefficientExamenDto() {
		
	}

	/**
	 * [ParametrageCoefficientExamenDto.id] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [ParametrageCoefficientExamenDto.id] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [ParametrageCoefficientExamenDto.oofId] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.oofId] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param oofId the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientExamen] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the coefficientExamen
	 */
	public double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientExamen] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param coefficientExamen the coefficientExamen to set
	 */
	public void setCoefficientExamen(double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the coefficientControleContinu
	 */
	public double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param coefficientControleContinu the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @return the coefficientControleIntermediaire
	 */
	public double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [ParametrageCoefficientExamenDto.coefficientControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:25:22
	 * @param coefficientControleIntermediaire the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

	/**
	 * [ParametrageCoefficientExamenDto.rattachementMcId] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:42:15
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.rattachementMcId] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  09:42:15
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @param mcLibelleFr the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcCredit] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @return the mcCredit
	 */
	public double getMcCredit() {
		return mcCredit;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcCredit] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @param mcCredit the mcCredit to set
	 */
	public void setMcCredit(double mcCredit) {
		this.mcCredit = mcCredit;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcCoefficient] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @return the mcCoefficient
	 */
	public double getMcCoefficient() {
		return mcCoefficient;
	}

	/**
	 * [ParametrageCoefficientExamenDto.mcCoefficient] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @param mcCoefficient the mcCoefficient to set
	 */
	public void setMcCoefficient(double mcCoefficient) {
		this.mcCoefficient = mcCoefficient;
	}

	/**
	 * [ParametrageCoefficientExamenDto.ueLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.ueLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:43:30
	 * @param ueLibelleFr the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.ofLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @return the ofLibelleFr
	 */
	public String getOfLibelleFr() {
		return ofLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.ofLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @param ofLibelleFr the ofLibelleFr to set
	 */
	public void setOfLibelleFr(String ofLibelleFr) {
		this.ofLibelleFr = ofLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.periodeLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @return the periodeLibelleFr
	 */
	public String getPeriodeLibelleFr() {
		return periodeLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.periodeLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @param periodeLibelleFr the periodeLibelleFr to set
	 */
	public void setPeriodeLibelleFr(String periodeLibelleFr) {
		this.periodeLibelleFr = periodeLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.niveauLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @return the niveauLibelleFr
	 */
	public String getNiveauLibelleFr() {
		return niveauLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.niveauLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:52:04
	 * @param niveauLibelleFr the niveauLibelleFr to set
	 */
	public void setNiveauLibelleFr(String niveauLibelleFr) {
		this.niveauLibelleFr = niveauLibelleFr;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiqueCode] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:57:30
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		anneeAcademiqueCode = anneeAcademiquePremiereAnnee + "/" + anneeAcademiqueDeuxiemeAnnee;
		return anneeAcademiqueCode;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiqueCode] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  12:57:30
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiquePremiereAnnee] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  13:09:26
	 * @return the anneeAcademiquePremiereAnnee
	 */
	public short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiquePremiereAnnee] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  13:09:26
	 * @param anneeAcademiquePremiereAnnee the anneeAcademiquePremiereAnnee to set
	 */
	public void setAnneeAcademiquePremiereAnnee(short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiqueDeuxiemeAnnee] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  13:09:26
	 * @return the anneeAcademiqueDeuxiemeAnnee
	 */
	public short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	/**
	 * [ParametrageCoefficientExamenDto.anneeAcademiqueDeuxiemeAnnee] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  13:09:26
	 * @param anneeAcademiqueDeuxiemeAnnee the anneeAcademiqueDeuxiemeAnnee to set
	 */
	public void setAnneeAcademiqueDeuxiemeAnnee(short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}
	
	

}
