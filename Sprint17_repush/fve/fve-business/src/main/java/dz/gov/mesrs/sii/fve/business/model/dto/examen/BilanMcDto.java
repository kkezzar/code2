/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.BilanMcDto.java] 
 * @author MAKERRI Sofiane on : 27 oct. 2014  09:37:45
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:45
 */
public class BilanMcDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:52
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Long bilanUeId;
	private Long bilanSessionId;
	private Integer rattachementMcId;
	private String mcLibelleFr;
	private String mcCode;
	private Double moyenneControleContinu;
	private Double noteControleIntermediaire;
	private double coefficient;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private Double coefficientControleContinu;
	private Double coefficientExamen;
	private Double coefficientControleIntermediaire;
	private double noteExamen;
	private double noteSession;
	private double noteJury;
	private Double noteJuryToDisplay;
	private double moyenneGenerale;
	private boolean dette;
	private double mcNoteObtention;
	private boolean mcAcquis;
	private boolean withoutControleContinu;
	private double noteEliminatoire;
	

	/**
	 * [BilanMcDto.BilanMcDto()] Constructor
	 * @author MAKERRI Sofiane  on : 27 oct. 2014  09:37:45	
	 */
	public BilanMcDto() {
		super();
	}

	/**
	 * [BilanMcDto.id] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [BilanMcDto.id] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanMcDto.bilanUeId] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the bilanUeId
	 */
	public Long getBilanUeId() {
		return bilanUeId;
	}

	/**
	 * [BilanMcDto.bilanUeId] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param bilanUeId the bilanUeId to set
	 */
	public void setBilanUeId(Long bilanUeId) {
		this.bilanUeId = bilanUeId;
	}

	/**
	 * [BilanMcDto.rattachementMcId] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [BilanMcDto.rattachementMcId] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	
	/**
	 * [BilanMcDto.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [BilanMcDto.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}


	/**
	 * [BilanMcDto.noteExamen] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the noteExamen
	 */
	public double getNoteExamen() {
		return noteExamen;
	}

	/**
	 * [BilanMcDto.noteExamen] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param noteExamen the noteExamen to set
	 */
	public void setNoteExamen(double noteExamen) {
		this.noteExamen = noteExamen;
	}

	/**
	 * [BilanMcDto.dette] Getter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @return the dette
	 */
	public boolean getDette() {
		return dette;
	}

	/**
	 * [BilanMcDto.dette] Setter 
	 * @author MAKERRI Sofiane on : 27 oct. 2014  09:39:24
	 * @param dette the dette to set
	 */
	public void setDette(boolean dette) {
		this.dette = dette;
	}

	/**
	 * [BilanMcDto.mcLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:52:54
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [BilanMcDto.mcLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:52:54
	 * @param mcLibelleFr the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [BilanMcDto.mcCode] Getter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:52:54
	 * @return the mcCode
	 */
	public String getMcCode() {
		return mcCode;
	}

	/**
	 * [BilanMcDto.mcCode] Setter 
	 * @author MAKERRI Sofiane on : 28 oct. 2014  13:52:54
	 * @param mcCode the mcCode to set
	 */
	public void setMcCode(String mcCode) {
		this.mcCode = mcCode;
	}

	/**
	 * [BilanMcDto.credit] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:02:05
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanMcDto.credit] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:02:05
	 * @param credit the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanMcDto.creditObtenu] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:21:10
	 * @return the creditObtenu
	 */
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanMcDto.creditObtenu] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  10:21:10
	 * @param creditObtenu the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanMcDto.bilanSessionId] Getter 
	 * @author MAKERRI Sofiane on : 8 nov. 2014  10:42:59
	 * @return the bilanSessionId
	 */
	public Long getBilanSessionId() {
		return bilanSessionId;
	}

	/**
	 * [BilanMcDto.bilanSessionId] Setter 
	 * @author MAKERRI Sofiane on : 8 nov. 2014  10:42:59
	 * @param bilanSessionId the bilanSessionId to set
	 */
	public void setBilanSessionId(Long bilanSessionId) {
		this.bilanSessionId = bilanSessionId;
	}

	/**
	 * [BilanMcDto.moyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  14:04:17
	 * @return the moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [BilanMcDto.moyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  14:04:17
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * [BilanMcDto.noteJury] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  10:05:41
	 * @return the noteJury
	 */
	public double getNoteJury() {
		return noteJury;
	}

	/**
	 * [BilanMcDto.noteJury] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  10:05:41
	 * @param noteJury the noteJury to set
	 */
	public void setNoteJury(double noteJury) {
		this.noteJury = noteJury;
	}

	/**
	 * [BilanMcDto.mcNoteObtention] Getter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  16:58:18
	 * @return the mcNoteObtention
	 */
	public double getMcNoteObtention() {
		return mcNoteObtention;
	}

	/**
	 * [BilanMcDto.mcNoteObtention] Setter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  16:58:18
	 * @param mcNoteObtention the mcNoteObtention to set
	 */
	public void setMcNoteObtention(double mcNoteObtention) {
		this.mcNoteObtention = mcNoteObtention;
	}

	/**
	 * [BilanMcDto.noteJuryToDisplay] Getter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  17:45:43
	 * @return the noteJuryToDisplay
	 */
	public Double getNoteJuryToDisplay() {
		if(noteJury != noteExamen) {
			noteJuryToDisplay = noteJury;
		}
		return noteJuryToDisplay;
	}

	/**
	 * [BilanMcDto.noteJuryToDisplay] Setter 
	 * @author MAKERRI Sofiane on : 17 déc. 2014  17:45:43
	 * @param noteJuryToDisplay the noteJuryToDisplay to set
	 */
	public void setNoteJuryToDisplay(Double noteJuryToDisplay) {
		this.noteJuryToDisplay = noteJuryToDisplay;
	}

	/**
	 * [BilanMcDto.mcAcquis] Getter 
	 * @author MAKERRI Sofiane on : 21 déc. 2014  12:41:08
	 * @return the mcAcquis
	 */
	public boolean isMcAcquis() {
		// 	mcAcquis = (moyenneGenerale >= mcNoteObtention ? true : false);
		return mcAcquis;
	}

	/**
	 * [BilanMcDto.mcAcquis] Setter 
	 * @author MAKERRI Sofiane on : 21 déc. 2014  12:41:08
	 * @param mcAcquis the mcAcquis to set
	 */
	public void setMcAcquis(boolean mcAcquis) {
		this.mcAcquis = mcAcquis;
	}

	/**
	 * [BilanMcDto.withoutControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  15:02:51
	 * @return the withoutControleContinu
	 */
	public boolean isWithoutControleContinu() {
		withoutControleContinu = (moyenneControleContinu == null ? true : false);
		return withoutControleContinu;
	}

	/**
	 * [BilanMcDto.withoutControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  15:02:51
	 * @param withoutControleContinu the withoutControleContinu to set
	 */
	public void setWithoutControleContinu(boolean withoutControleContinu) {
		this.withoutControleContinu = withoutControleContinu;
	}



	/**
	 * [BilanMcDto.coefficientControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:29:23
	 * @return the coefficientControleContinu
	 */
	public Double getCoefficientControleContinu() {
		return coefficientControleContinu;
	}

	/**
	 * [BilanMcDto.coefficientControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:29:23
	 * @param coefficientControleContinu the coefficientControleContinu to set
	 */
	public void setCoefficientControleContinu(Double coefficientControleContinu) {
		this.coefficientControleContinu = coefficientControleContinu;
	}

	/**
	 * [BilanMcDto.coefficientExamen] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:01:05
	 * @return the coefficientExamen
	 */
	public Double getCoefficientExamen() {
		return coefficientExamen;
	}

	/**
	 * [BilanMcDto.coefficientExamen] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:01:05
	 * @param coefficientExamen the coefficientExamen to set
	 */
	public void setCoefficientExamen(Double coefficientExamen) {
		this.coefficientExamen = coefficientExamen;
	}

	/**
	 * [BilanMcDto.coefficientControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:08:11
	 * @return the coefficientControleIntermediaire
	 */
	public Double getCoefficientControleIntermediaire() {
		return coefficientControleIntermediaire;
	}

	/**
	 * [BilanMcDto.coefficientControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 5 janv. 2015  17:08:11
	 * @param coefficientControleIntermediaire the coefficientControleIntermediaire to set
	 */
	public void setCoefficientControleIntermediaire(
			Double coefficientControleIntermediaire) {
		this.coefficientControleIntermediaire = coefficientControleIntermediaire;
	}

	/**
	 * [BilanMcDto.moyenneControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:25:39
	 * @return the moyenneControleContinu
	 */
	public Double getMoyenneControleContinu() {
		return moyenneControleContinu;
	}

	/**
	 * [BilanMcDto.moyenneControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:25:39
	 * @param moyenneControleContinu the moyenneControleContinu to set
	 */
	public void setMoyenneControleContinu(Double moyenneControleContinu) {
		this.moyenneControleContinu = moyenneControleContinu;
	}

	/**
	 * [BilanMcDto.noteControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:26:38
	 * @return the noteControleIntermediaire
	 */
	public Double getNoteControleIntermediaire() {
		return noteControleIntermediaire;
	}

	/**
	 * [BilanMcDto.noteControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  12:26:38
	 * @param noteControleIntermediaire the noteControleIntermediaire to set
	 */
	public void setNoteControleIntermediaire(Double noteControleIntermediaire) {
		this.noteControleIntermediaire = noteControleIntermediaire;
	}

	/**
	 * [BilanMcDto.noteSession] Getter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  14:07:17
	 * @return the noteSession
	 */
	public double getNoteSession() {
		return noteSession;
	}

	/**
	 * [BilanMcDto.noteSession] Setter 
	 * @author MAKERRI Sofiane on : 11 janv. 2015  14:07:17
	 * @param noteSession the noteSession to set
	 */
	public void setNoteSession(double noteSession) {
		this.noteSession = noteSession;
	}

	/**
	 * [BilanMcDto.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:57:38
	 * @return the creditAcquis
	 */
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanMcDto.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:57:38
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}

	/**
	 * [BilanMcDto.noteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:37:26
	 * @return the noteEliminatoire
	 */
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [BilanMcDto.noteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  15:37:26
	 * @param noteEliminatoire the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	


}
