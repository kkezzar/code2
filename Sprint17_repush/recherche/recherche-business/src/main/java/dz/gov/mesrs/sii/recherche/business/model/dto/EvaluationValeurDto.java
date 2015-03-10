/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.recherche.EvaluationChercheur;
import dz.gov.mesrs.sii.commons.data.model.recherche.GrilleEvaluationDetail;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

public class EvaluationValeurDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double note;
	private String observation;
	// Evaluation chercheur
	private Long evaluationChercheurId;
	// Grille evaluation detail
	private Long grilleEvaluationId;
	private Long grilleEvaluationDetailId;
	private Integer grilleEvaluationDetailNcChapitreId;
	private String grilleEvaluationDetailNcChapitreIntituleFr;
	private Integer grilleEvaluationDetailNcCritereId;
	private String grilleEvaluationDetailNcCritereIntituleFr;
	private Integer grilleEvaluationDetailNcAppreciationId;
	private String grilleEvaluationDetailNcAppreciationIntituleFr;
	private String grilleEvaluationDetailNumero;
	// NC Niveau appreciation
	private Integer ncNiveauAppreciationId;
	private String ncNiveauAppreciationIntituleFr;
	
	public EvaluationValeurDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Long getEvaluationChercheurId() {
		return evaluationChercheurId;
	}

	public void setEvaluationChercheurId(Long evaluationChercheurId) {
		this.evaluationChercheurId = evaluationChercheurId;
	}

	public Long getGrilleEvaluationId() {
		return grilleEvaluationId;
	}

	public void setGrilleEvaluationId(Long grilleEvaluationId) {
		this.grilleEvaluationId = grilleEvaluationId;
	}

	public Long getGrilleEvaluationDetailId() {
		return grilleEvaluationDetailId;
	}

	public void setGrilleEvaluationDetailId(Long grilleEvaluationDetailId) {
		this.grilleEvaluationDetailId = grilleEvaluationDetailId;
	}

	public Integer getGrilleEvaluationDetailNcChapitreId() {
		return grilleEvaluationDetailNcChapitreId;
	}

	public void setGrilleEvaluationDetailNcChapitreId(
			Integer grilleEvaluationDetailNcChapitreId) {
		this.grilleEvaluationDetailNcChapitreId = grilleEvaluationDetailNcChapitreId;
	}

	public String getGrilleEvaluationDetailNcChapitreIntituleFr() {
		return grilleEvaluationDetailNcChapitreIntituleFr;
	}

	public void setGrilleEvaluationDetailNcChapitreIntituleFr(
			String grilleEvaluationDetailNcChapitreIntituleFr) {
		this.grilleEvaluationDetailNcChapitreIntituleFr = grilleEvaluationDetailNcChapitreIntituleFr;
	}

	public Integer getGrilleEvaluationDetailNcCritereId() {
		return grilleEvaluationDetailNcCritereId;
	}

	public void setGrilleEvaluationDetailNcCritereId(
			Integer grilleEvaluationDetailNcCritereId) {
		this.grilleEvaluationDetailNcCritereId = grilleEvaluationDetailNcCritereId;
	}

	public String getGrilleEvaluationDetailNcCritereIntituleFr() {
		return grilleEvaluationDetailNcCritereIntituleFr;
	}

	public void setGrilleEvaluationDetailNcCritereIntituleFr(
			String grilleEvaluationDetailNcCritereIntituleFr) {
		this.grilleEvaluationDetailNcCritereIntituleFr = grilleEvaluationDetailNcCritereIntituleFr;
	}

	public String getGrilleEvaluationDetailNumero() {
		return grilleEvaluationDetailNumero;
	}

	public void setGrilleEvaluationDetailNumero(String grilleEvaluationDetailNumero) {
		this.grilleEvaluationDetailNumero = grilleEvaluationDetailNumero;
	}

	public Integer getNcNiveauAppreciationId() {
		return ncNiveauAppreciationId;
	}

	public void setNcNiveauAppreciationId(Integer ncNiveauAppreciationId) {
		this.ncNiveauAppreciationId = ncNiveauAppreciationId;
	}

	public Integer getGrilleEvaluationDetailNcAppreciationId() {
		return grilleEvaluationDetailNcAppreciationId;
	}

	public void setGrilleEvaluationDetailNcAppreciationId(
			Integer grilleEvaluationDetailNcAppreciationId) {
		this.grilleEvaluationDetailNcAppreciationId = grilleEvaluationDetailNcAppreciationId;
	}

	public String getGrilleEvaluationDetailNcAppreciationIntituleFr() {
		return grilleEvaluationDetailNcAppreciationIntituleFr;
	}

	public void setGrilleEvaluationDetailNcAppreciationIntituleFr(
			String grilleEvaluationDetailNcAppreciationIntituleFr) {
		this.grilleEvaluationDetailNcAppreciationIntituleFr = grilleEvaluationDetailNcAppreciationIntituleFr;
	}

	public String getNcNiveauAppreciationIntituleFr() {
		return ncNiveauAppreciationIntituleFr;
	}

	public void setNcNiveauAppreciationIntituleFr(
			String ncNiveauAppreciationIntituleFr) {
		this.ncNiveauAppreciationIntituleFr = ncNiveauAppreciationIntituleFr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((evaluationChercheurId == null) ? 0 : evaluationChercheurId
						.hashCode());
		result = prime
				* result
				+ ((grilleEvaluationDetailId == null) ? 0
						: grilleEvaluationDetailId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((ncNiveauAppreciationId == null) ? 0
						: ncNiveauAppreciationId.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvaluationValeurDto other = (EvaluationValeurDto) obj;
		if (evaluationChercheurId == null) {
			if (other.evaluationChercheurId != null)
				return false;
		} else if (!evaluationChercheurId.equals(other.evaluationChercheurId))
			return false;
		if (grilleEvaluationDetailId == null) {
			if (other.grilleEvaluationDetailId != null)
				return false;
		} else if (!grilleEvaluationDetailId
				.equals(other.grilleEvaluationDetailId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ncNiveauAppreciationId == null) {
			if (other.ncNiveauAppreciationId != null)
				return false;
		} else if (!ncNiveauAppreciationId.equals(other.ncNiveauAppreciationId))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationValeurDto [note=" + note + ", observation="
				+ observation + ", grilleEvaluationDetailNcChapitreIntituleFr="
				+ grilleEvaluationDetailNcChapitreIntituleFr
				+ ", grilleEvaluationDetailNcCritereIntituleFr="
				+ grilleEvaluationDetailNcCritereIntituleFr
				+ ", grilleEvaluationDetailNumero="
				+ grilleEvaluationDetailNumero
				+ ", ncNiveauAppreciationIntituleFr="
				+ ncNiveauAppreciationIntituleFr + "]";
	}
	
	
}
