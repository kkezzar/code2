/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

public class EvaluationChercheurDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateEvaluation;
	private Date dateDebutPeriode;
	private Date dateFinPeriode;
	private String appreciation;
	
	// Grille Evaluation
	private Long grilleEvaluationId;
	private String grilleEvaluationCode;
	private String grilleEvaluationIntituleFr;
	
	// Individu
	private Integer refIndividuId;
	private String refIndividuIdentifiant;
	private String refIndividuNomLatin;
	private String refIndividuPrenomLatin;
	
	public EvaluationChercheurDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Date getDateDebutPeriode() {
		return dateDebutPeriode;
	}

	public void setDateDebutPeriode(Date dateDebutPeriode) {
		this.dateDebutPeriode = dateDebutPeriode;
	}

	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
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

	public Integer getRefIndividuId() {
		return refIndividuId;
	}

	public void setRefIndividuId(Integer refIndividuId) {
		this.refIndividuId = refIndividuId;
	}

	public String getRefIndividuIdentifiant() {
		return refIndividuIdentifiant;
	}

	public void setRefIndividuIdentifiant(String refIndividuIdentifiant) {
		this.refIndividuIdentifiant = refIndividuIdentifiant;
	}

	public String getRefIndividuNomLatin() {
		return refIndividuNomLatin;
	}

	public void setRefIndividuNomLatin(String refIndividuNomLatin) {
		this.refIndividuNomLatin = refIndividuNomLatin;
	}

	public String getRefIndividuPrenomLatin() {
		return refIndividuPrenomLatin;
	}

	public void setRefIndividuPrenomLatin(String refIndividuPrenomLatin) {
		this.refIndividuPrenomLatin = refIndividuPrenomLatin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appreciation == null) ? 0 : appreciation.hashCode());
		result = prime
				* result
				+ ((dateDebutPeriode == null) ? 0 : dateDebutPeriode.hashCode());
		result = prime * result
				+ ((dateEvaluation == null) ? 0 : dateEvaluation.hashCode());
		result = prime * result
				+ ((dateFinPeriode == null) ? 0 : dateFinPeriode.hashCode());
		result = prime
				* result
				+ ((grilleEvaluationId == null) ? 0 : grilleEvaluationId
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((refIndividuId == null) ? 0 : refIndividuId.hashCode());
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
		EvaluationChercheurDto other = (EvaluationChercheurDto) obj;
		if (appreciation == null) {
			if (other.appreciation != null)
				return false;
		} else if (!appreciation.equals(other.appreciation))
			return false;
		if (dateDebutPeriode == null) {
			if (other.dateDebutPeriode != null)
				return false;
		} else if (!dateDebutPeriode.equals(other.dateDebutPeriode))
			return false;
		if (dateEvaluation == null) {
			if (other.dateEvaluation != null)
				return false;
		} else if (!dateEvaluation.equals(other.dateEvaluation))
			return false;
		if (dateFinPeriode == null) {
			if (other.dateFinPeriode != null)
				return false;
		} else if (!dateFinPeriode.equals(other.dateFinPeriode))
			return false;
		if (grilleEvaluationId == null) {
			if (other.grilleEvaluationId != null)
				return false;
		} else if (!grilleEvaluationId.equals(other.grilleEvaluationId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refIndividuId == null) {
			if (other.refIndividuId != null)
				return false;
		} else if (!refIndividuId.equals(other.refIndividuId))
			return false;
		return true;
	}


}
