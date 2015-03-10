package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.CoefficientExamenDto;

/**
 * Classe de type DTO représentant un rattachement d'une MC à une UE.
 * 
 * @author Kheireddine OMRANI
 * 
 */
public class RattachementMcDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;
	
	private Integer ueId;
	private String ueCode;
	private String ueLibelleFr;
	private String ueLibelleAr;
	private String ueRefCodeType;
	private String ueRefLibelleType;
	private Integer ueCredits;
	private Integer mcId;
	private String mcCode;
	private String mcLibelleFr;
	private String mcLibelleAr;
	private String mcRefCodeType;
	private String mcRefLibelleType;
	private Integer mcCredits;
	private Integer mcCoefficient;
	private Boolean mcOptionnel;
    private double mcMoyenneGenerale;
    private double coefficientExamen;
	private double coefficientControleContinu;
	private double coefficientControleIntermediaire;
    
	private Integer groupAChoixId;
	private Integer groupAChoixNombreMax;
	private Integer groupAChoixNombreMin;
	
	private double coefficient;
	private double credit;
	private double noteObtension;
	private double noteDeBase;
	private double noteEliminatoire;

	private boolean optionnelle;
	private boolean aChoix;
	private List<CoefficientExamenDto> coefficientExamenDtos; 
	

	public RattachementMcDto() {
//		this.noteObtension = 10d;
//		this.noteDeBase = 20d;
//		this.noteEliminatoire = 0d;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUeId() {
		return ueId;
	}

	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	public String getUeCode() {
		return ueCode;
	}

	public void setUeCode(String ueCode) {
		this.ueCode = ueCode;
	}

	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	public String getUeLibelleAr() {
		return ueLibelleAr;
	}

	public void setUeLibelleAr(String ueLibelleAr) {
		this.ueLibelleAr = ueLibelleAr;
	}
	
	public String getUeRefCodeType() {
		return ueRefCodeType;
	}

	public void setUeRefCodeType(String ueRefCodeType) {
		this.ueRefCodeType = ueRefCodeType;
	}

	public String getUeRefLibelleType() {
		return ueRefLibelleType;
	}

	public void setUeRefLibelleType(String ueRefLibelleType) {
		this.ueRefLibelleType = ueRefLibelleType;
	}

	
	public Integer getMcId() {
		return mcId;
	}

	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	public String getMcCode() {
		return mcCode;
	}

	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}

	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	public String getMcLibelleAr() {
		return mcLibelleAr;
	}

	public void setMcLibelleAr(String mcLibelleAr) {
		this.mcLibelleAr = mcLibelleAr;
	}

	public String getMcRefCodeType() {
		return mcRefCodeType;
	}

	public void setMcRefCodeType(String mcRefCodeType) {
		this.mcRefCodeType = mcRefCodeType;
	}

	public String getMcRefLibelleType() {
		return mcRefLibelleType;
	}

	public void setMcRefLibelleType(String mcRefLibelleType) {
		this.mcRefLibelleType = mcRefLibelleType;
	}	
	
	public Integer getGroupAChoixId() {
		return groupAChoixId;
	}

	public void setGroupAChoixId(Integer groupAChoixId) {
		this.groupAChoixId = groupAChoixId;
	}

	public Integer getGroupAChoixNombreMax() {
		return groupAChoixNombreMax;
	}

	public void setGroupAChoixNombreMax(Integer groupAChoixNombreMax) {
		this.groupAChoixNombreMax = groupAChoixNombreMax;
	}

	public Integer getGroupAChoixNombreMin() {
		return groupAChoixNombreMin;
	}

	public void setGroupAChoixNombreMin(Integer groupAChoixNombreMin) {
		this.groupAChoixNombreMin = groupAChoixNombreMin;
	}
		
	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getNoteObtension() {
		return noteObtension;
	}

	public void setNoteObtension(double noteObtension) {
		this.noteObtension = noteObtension;
	}

	public double getNoteDeBase() {
		return noteDeBase;
	}

	public void setNoteDeBase(double noteDeBase) {
		this.noteDeBase = noteDeBase;
	}

	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	public boolean getOptionnelle() {
		return optionnelle;
	}

	public void setOptionnelle(boolean optionnelle) {
		this.optionnelle = optionnelle;
	}

	public boolean getaChoix() {
		return aChoix;
	}

	public void setaChoix(boolean aChoix) {
		this.aChoix = aChoix;
	}

	/**
	 * [RattachementMcDto.ueCredits] Getter 
	 * @author BELDI Jamel on : 16 juil. 2014  11:17:13
	 * @return the ueCredits
	 */
	public Integer getUeCredits() {
		return ueCredits;
	}

	/**
	 * [RattachementMcDto.ueCredits] Setter 
	 * @author BELDI Jamel on : 16 juil. 2014  11:17:13
	 * @param ueCredits the ueCredits to set
	 */
	public void setUeCredits(Integer ueCredits) {
		this.ueCredits = ueCredits;
	}

	/**
	 * [RattachementMcDto.mcCredits] Getter 
	 * @author BELDI Jamel on : 16 juil. 2014  11:17:13
	 * @return the mcCredits
	 */
	public Integer getMcCredits() {
		return mcCredits;
	}

	/**
	 * [RattachementMcDto.mcCredits] Setter 
	 * @author BELDI Jamel on : 16 juil. 2014  11:17:13
	 * @param mcCredits the mcCredits to set
	 */
	public void setMcCredits(Integer mcCredits) {
		this.mcCredits = mcCredits;
	}

	/**
	 * [RattachementMcDto.mcCoefficient] Getter 
	 * @author BELBRIK Oualid on : 15 sept. 2014  20:02:45
	 * @return the mcCoefficient
	 */
	public Integer getMcCoefficient() {
		return mcCoefficient;
	}

	/**
	 * [RattachementMcDto.mcCoefficient] Setter 
	 * @author BELBRIK Oualid on : 15 sept. 2014  20:02:45
	 * @param mcCoefficient the mcCoefficient to set
	 */
	public void setMcCoefficient(Integer mcCoefficient) {
		this.mcCoefficient = mcCoefficient;
	}

	/**
	 * [RattachementMcDto.mcOptionnel] Getter 
	 * @author BELBRIK Oualid on : 15 sept. 2014  20:03:27
	 * @return the mcOptionnel
	 */
	public Boolean getMcOptionnel() {
		return mcOptionnel;
	}

	/**
	 * [RattachementMcDto.mcOptionnel] Setter 
	 * @author BELBRIK Oualid on : 15 sept. 2014  20:03:27
	 * @param mcOptionnel the mcOptionnel to set
	 */
	public void setMcOptionnel(Boolean mcOptionnel) {
		this.mcOptionnel = mcOptionnel;
	}



	/**
	 * [RattachementMcDto.mcMoyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  17:22:58
	 * @return the mcMoyenneGenerale
	 */
	public double getMcMoyenneGenerale() {
		return mcMoyenneGenerale;
	}

	/**
	 * [RattachementMcDto.mcMoyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  17:22:58
	 * @param mcMoyenneGenerale the mcMoyenneGenerale to set
	 */
	public void setMcMoyenneGenerale(double mcMoyenneGenerale) {
		this.mcMoyenneGenerale = mcMoyenneGenerale;
	}


	/**
	 * [RattachementMcDto.credit] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:18:12
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * [RattachementMcDto.credit] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:18:12
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [RattachementMcDto.coefficientExamen] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @return the coefficientExamen
	 */
	public double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [RattachementMcDto.coefficientExamen] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @param coefficientExamen the coefficientExamen to set
	 */
	public void setCoefficientExamen(double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [RattachementMcDto.coefficientControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @return the coefficientControleContinu
	 */
	public double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [RattachementMcDto.coefficientControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @param coefficientControleContinu the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [RattachementMcDto.coefficientControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @return the coefficientControleIntermediaire
	 */
	public double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [RattachementMcDto.coefficientControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  16:58:39
	 * @param coefficientControleIntermediaire the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

	/**
	 * [RattachementMcDto.coefficientExamenDtos] Getter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  09:41:46
	 * @return the coefficientExamenDtos
	 */
	public List<CoefficientExamenDto> getCoefficientExamenDtos() {
		return coefficientExamenDtos;
	}

	/**
	 * [RattachementMcDto.coefficientExamenDtos] Setter 
	 * @author MAKERRI Sofiane on : 6 janv. 2015  09:41:46
	 * @param coefficientExamenDtos the coefficientExamenDtos to set
	 */
	public void setCoefficientExamenDtos(
			List<CoefficientExamenDto> coefficientExamenDtos) {
		this.coefficientExamenDtos = coefficientExamenDtos;
	}

   
	
}
