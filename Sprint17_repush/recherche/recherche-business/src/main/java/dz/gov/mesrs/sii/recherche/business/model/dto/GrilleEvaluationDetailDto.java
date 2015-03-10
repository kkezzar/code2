/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;


public class GrilleEvaluationDetailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String numero;
	private String numeroChapitre;
	private String numeroCritere;
	
	// Grille Evaluation
	private Long grilleEvaluationId;
	private String grilleEvaluationCode;
	private String grilleEvaluationIntituleFr;
	
	// Chapitre Evaluation
	private Integer ncChapitreId;
	private String ncChapitreCode;
	private String ncChapitreIntituleFr;
	
	// Critere Evaluation
	private Integer ncCritereId;
	private String ncCritereCode;
	private String ncCritereIntituleFr;
	
	// Appreciation
	private Integer ncAppreciationId;
	private String ncAppreciationCode;
	private String ncAppreciationIntituleFr;
	
	public GrilleEvaluationDetailDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	public String getNumeroChapitre() {
		return numeroChapitre;
	}

	public void setNumeroChapitre(String numeroChapitre) {
		this.numeroChapitre = numeroChapitre;
	}

	public String getNumeroCritere() {
		return numeroCritere;
	}

	public void setNumeroCritere(String numeroCritere) {
		this.numeroCritere = numeroCritere;
	}

	public Long getGrilleEvaluationId() {
		return grilleEvaluationId;
	}

	public void setGrilleEvaluationId(Long grilleEvaluationId) {
		this.grilleEvaluationId = grilleEvaluationId;
	}

	public String getGrilleEvaluationCode() {
		return grilleEvaluationCode;
	}

	public void setGrilleEvaluationCode(String grilleEvaluationCode) {
		this.grilleEvaluationCode = grilleEvaluationCode;
	}

	public String getGrilleEvaluationIntituleFr() {
		return grilleEvaluationIntituleFr;
	}

	public void setGrilleEvaluationIntituleFr(String grilleEvaluationIntituleFr) {
		this.grilleEvaluationIntituleFr = grilleEvaluationIntituleFr;
	}

	public Integer getNcChapitreId() {
		return ncChapitreId;
	}

	public void setNcChapitreId(Integer ncChapitreId) {
		this.ncChapitreId = ncChapitreId;
	}

	public String getNcChapitreCode() {
		return ncChapitreCode;
	}

	public void setNcChapitreCode(String ncChapitreCode) {
		this.ncChapitreCode = ncChapitreCode;
	}

	public String getNcChapitreIntituleFr() {
		return ncChapitreIntituleFr;
	}

	public void setNcChapitreIntituleFr(String ncChapitreIntituleFr) {
		this.ncChapitreIntituleFr = ncChapitreIntituleFr;
	}

	public Integer getNcCritereId() {
		return ncCritereId;
	}

	public void setNcCritereId(Integer ncCritereId) {
		this.ncCritereId = ncCritereId;
	}

	public String getNcCritereCode() {
		return ncCritereCode;
	}

	public void setNcCritereCode(String ncCritereCode) {
		this.ncCritereCode = ncCritereCode;
	}

	public String getNcCritereIntituleFr() {
		return ncCritereIntituleFr;
	}

	public void setNcCritereIntituleFr(String ncCritereIntituleFr) {
		this.ncCritereIntituleFr = ncCritereIntituleFr;
	}

	public Integer getNcAppreciationId() {
		return ncAppreciationId;
	}

	public void setNcAppreciationId(Integer ncAppreciationId) {
		this.ncAppreciationId = ncAppreciationId;
	}

	public String getNcAppreciationCode() {
		return ncAppreciationCode;
	}

	public void setNcAppreciationCode(String ncAppreciationCode) {
		this.ncAppreciationCode = ncAppreciationCode;
	}

	public String getNcAppreciationIntituleFr() {
		return ncAppreciationIntituleFr;
	}

	public void setNcAppreciationIntituleFr(String ncAppreciationIntituleFr) {
		this.ncAppreciationIntituleFr = ncAppreciationIntituleFr;
	}
	
	
}
