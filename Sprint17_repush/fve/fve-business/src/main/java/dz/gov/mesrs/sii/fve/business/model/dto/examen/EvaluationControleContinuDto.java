/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.EvaluationControleContinuDto.java] 
 * @author MAKERRI Sofiane on : 29 sept. 2014  17:08:58
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane  on : 29 sept. 2014  17:08:58
 */
public class EvaluationControleContinuDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  17:09:09
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer groupePedagogiqueId;
	private String gpNomAffichage; 
	private String ncTypeEvalLlFr;
	private String ncTypeEvalLlAr;
	private Integer ncTypeEvalId;
	private String ncTypeEvalCode;
	private Integer anneeAcademiqueId;
	private Integer situationEntiteId;
	private String situationLibelle;
	private Date dateEvaluation;
	private Double coefficient;
	private Boolean comptablise;
	private Integer apId;
	private String apCode;
	private Integer rattachementMcId;
	private Integer rattachementMcMcId;
	private String rattachementMcLibelle;
	private Integer rattachementMcUeId;
	private String rattachementMcUeLibelle;
	private Double rattachementMcNoteDeBase;
	private String refCodeTypeAp;
	private String objectif;
	private String competence;
	private Integer associationGroupePedagogiqueId;
	private Integer oofId;
	private Integer periodeId;
	
	
	
	

	/**
	 * [EvaluationControleContinuDto.EvaluationControleContinuDto()] Constructor
	 * @author MAKERRI Sofiane  on : 29 sept. 2014  17:08:58	
	 */
	public EvaluationControleContinuDto() {
		super();
	}
	
	public EvaluationControleContinuDto(Integer anneeAcademiqueId,Integer oofId,
	Integer periodeId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
		this.oofId = oofId;
		this.periodeId = periodeId;
	}



	/**
	 * [EvaluationControleContinuDto.id] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * [EvaluationControleContinuDto.id] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * [EvaluationControleContinuDto.groupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.groupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param groupePedagogiqueId the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.gpNomAffichage] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the gpNomAffichage
	 */
	public String getGpNomAffichage() {
		return gpNomAffichage;
	}



	/**
	 * [EvaluationControleContinuDto.gpNomAffichage] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param gpNomAffichage the gpNomAffichage to set
	 */
	public void setGpNomAffichage(String gpNomAffichage) {
		this.gpNomAffichage = gpNomAffichage;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalLlFr] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the ncTypeEvalLlFr
	 */
	public String getNcTypeEvalLlFr() {
		return ncTypeEvalLlFr;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalLlFr] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param ncTypeEvalLlFr the ncTypeEvalLlFr to set
	 */
	public void setNcTypeEvalLlFr(String ncTypeEvalLlFr) {
		this.ncTypeEvalLlFr = ncTypeEvalLlFr;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalLlAr] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the ncTypeEvalLlAr
	 */
	public String getNcTypeEvalLlAr() {
		return ncTypeEvalLlAr;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalLlAr] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param ncTypeEvalLlAr the ncTypeEvalLlAr to set
	 */
	public void setNcTypeEvalLlAr(String ncTypeEvalLlAr) {
		this.ncTypeEvalLlAr = ncTypeEvalLlAr;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalCode] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the ncTypeEvalCode
	 */
	public String getNcTypeEvalCode() {
		return ncTypeEvalCode;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalCode] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param ncTypeEvalCode the ncTypeEvalCode to set
	 */
	public void setNcTypeEvalCode(String ncTypeEvalCode) {
		this.ncTypeEvalCode = ncTypeEvalCode;
	}



	/**
	 * [EvaluationControleContinuDto.anneeAcademiqueId] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.anneeAcademiqueId] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.situationEntiteId] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the situationEntiteId
	 */
	public Integer getSituationEntiteId() {
		return situationEntiteId;
	}



	/**
	 * [EvaluationControleContinuDto.situationEntiteId] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param situationEntiteId the situationEntiteId to set
	 */
	public void setSituationEntiteId(Integer situationEntiteId) {
		this.situationEntiteId = situationEntiteId;
	}



	/**
	 * [EvaluationControleContinuDto.dateEvaluation] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the dateEvaluation
	 */
	public Date getDateEvaluation() {
		return dateEvaluation;
	}



	/**
	 * [EvaluationControleContinuDto.dateEvaluation] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param dateEvaluation the dateEvaluation to set
	 */
	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}



	/**
	 * [EvaluationControleContinuDto.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the coefficient
	 */
	public Double getCoefficient() {
		return coefficient;
	}



	/**
	 * [EvaluationControleContinuDto.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}



	/**
	 * [EvaluationControleContinuDto.comptabliise] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the comptabliise
	 */
	public Boolean getComptablise() {
		return comptablise;
	}



	/**
	 * [EvaluationControleContinuDto.comptabliise] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param comptabliise the comptabliise to set
	 */
	public void setComptablise(Boolean comptablise) {
		this.comptablise = comptablise;
	}



	/**
	 * [EvaluationControleContinuDto.apId] Getter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @return the apId
	 */
	public Integer getApId() {
		return apId;
	}



	/**
	 * [EvaluationControleContinuDto.apId] Setter 
	 * @author MAKERRI Sofiane on : 29 sept. 2014  17:30:55
	 * @param apId the apId to set
	 */
	public void setApId(Integer apId) {
		this.apId = apId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcId] Getter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcId] Setter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcLibelle] Getter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @return the rattachementMcLibelle
	 */
	public String getRattachementMcLibelle() {
		return rattachementMcLibelle;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcLibelle] Setter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @param rattachementMcLibelle the rattachementMcLibelle to set
	 */
	public void setRattachementMcLibelle(String rattachementMcLibelle) {
		this.rattachementMcLibelle = rattachementMcLibelle;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcUeLibelle] Getter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @return the rattachementMcUeLibelle
	 */
	public String getRattachementMcUeLibelle() {
		return rattachementMcUeLibelle;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcUeLibelle] Setter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @param rattachementMcUeLibelle the rattachementMcUeLibelle to set
	 */
	public void setRattachementMcUeLibelle(String rattachementMcUeLibelle) {
		this.rattachementMcUeLibelle = rattachementMcUeLibelle;
	}



	/**
	 * [EvaluationControleContinuDto.refCodeTypeAp] Getter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @return the refCodeTypeAp
	 */
	public String getRefCodeTypeAp() {
		return refCodeTypeAp;
	}



	/**
	 * [EvaluationControleContinuDto.refCodeTypeAp] Setter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  11:36:06
	 * @param refCodeTypeAp the refCodeTypeAp to set
	 */
	public void setRefCodeTypeAp(String refCodeTypeAp) {
		this.refCodeTypeAp = refCodeTypeAp;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalId] Getter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  18:14:07
	 * @return the ncTypeEvalId
	 */
	public Integer getNcTypeEvalId() {
		return ncTypeEvalId;
	}



	/**
	 * [EvaluationControleContinuDto.ncTypeEvalId] Setter 
	 * @author MAKERRI Sofiane on : 30 sept. 2014  18:14:07
	 * @param ncTypeEvalId the ncTypeEvalId to set
	 */
	public void setNcTypeEvalId(Integer ncTypeEvalId) {
		this.ncTypeEvalId = ncTypeEvalId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcMcId] Getter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  10:35:26
	 * @return the rattachementMcMcId
	 */
	public Integer getRattachementMcMcId() {
		return rattachementMcMcId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcMcId] Setter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  10:35:26
	 * @param rattachementMcMcId the rattachementMcMcId to set
	 */
	public void setRattachementMcMcId(Integer rattachementMcMcId) {
		this.rattachementMcMcId = rattachementMcMcId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcUeId] Getter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  10:35:26
	 * @return the rattachementMcUeId
	 */
	public Integer getRattachementMcUeId() {
		return rattachementMcUeId;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcUeId] Setter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  10:35:26
	 * @param rattachementMcUeId the rattachementMcUeId to set
	 */
	public void setRattachementMcUeId(Integer rattachementMcUeId) {
		this.rattachementMcUeId = rattachementMcUeId;
	}



	/**
	 * [EvaluationControleContinuDto.objectif] Getter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  14:52:55
	 * @return the objectif
	 */
	public String getObjectif() {
		return objectif;
	}



	/**
	 * [EvaluationControleContinuDto.objectif] Setter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  14:52:55
	 * @param objectif the objectif to set
	 */
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}



	/**
	 * [EvaluationControleContinuDto.competence] Getter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  14:52:55
	 * @return the competence
	 */
	public String getCompetence() {
		return competence;
	}



	/**
	 * [EvaluationControleContinuDto.competence] Setter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  14:52:55
	 * @param competence the competence to set
	 */
	public void setCompetence(String competence) {
		this.competence = competence;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcNoteDeBase] Getter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  15:11:50
	 * @return the rattachementMcNoteDeBase
	 */
	public Double getRattachementMcNoteDeBase() {
		return rattachementMcNoteDeBase;
	}



	/**
	 * [EvaluationControleContinuDto.rattachementMcNoteDeBase] Setter 
	 * @author MAKERRI Sofiane on : 1 oct. 2014  15:11:50
	 * @param rattachementMcNoteDeBase the rattachementMcNoteDeBase to set
	 */
	public void setRattachementMcNoteDeBase(Double rattachementMcNoteDeBase) {
		this.rattachementMcNoteDeBase = rattachementMcNoteDeBase;
	}



	/**
	 * [EvaluationControleContinuDto.associationGroupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  12:05:46
	 * @return the associationGroupePedagogiqueId
	 */
	public Integer getAssociationGroupePedagogiqueId() {
		return associationGroupePedagogiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.associationGroupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  12:05:46
	 * @param associationGroupePedagogiqueId the associationGroupePedagogiqueId to set
	 */
	public void setAssociationGroupePedagogiqueId(
			Integer associationGroupePedagogiqueId) {
		this.associationGroupePedagogiqueId = associationGroupePedagogiqueId;
	}



	/**
	 * [EvaluationControleContinuDto.oofId] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  17:21:48
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}



	/**
	 * [EvaluationControleContinuDto.oofId] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  17:21:48
	 * @param oofId the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}



	/**
	 * [EvaluationControleContinuDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  17:21:48
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}



	/**
	 * [EvaluationControleContinuDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  17:21:48
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [EvaluationControleContinuDto.apCode] Getter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  14:15:08
	 * @return the apCode
	 */
	public String getApCode() {
		return apCode;
	}

	/**
	 * [EvaluationControleContinuDto.apCode] Setter 
	 * @author MAKERRI Sofiane on : 13 oct. 2014  14:15:08
	 * @param apCode the apCode to set
	 */
	public void setApCode(String apCode) {
		this.apCode = apCode;
	}

	/**
	 * [EvaluationControleContinuDto.situationLibelle] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  15:25:15
	 * @return the situationLibelle
	 */
	public String getSituationLibelle() {
		return situationLibelle;
	}

	/**
	 * [EvaluationControleContinuDto.situationLibelle] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  15:25:15
	 * @param situationLibelle the situationLibelle to set
	 */
	public void setSituationLibelle(String situationLibelle) {
		this.situationLibelle = situationLibelle;
	}
	
	
	
	
	

}
