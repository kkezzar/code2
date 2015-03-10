package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

public class EvaluationProjetDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dateEvaluation;
	private Date dateFinPeriode;
	private String description;
	private String appreciationGlobale;
	private Integer nbBrevet;
	private Integer nbPublication;
	private String resultatAtteint;
	private Double tauxRealisation;
	private ProjetDto projetDto;
	
	public EvaluationProjetDto() {
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

	public Date getDateFinPeriode() {
		return dateFinPeriode;
	}

	public void setDateFinPeriode(Date dateFinPeriode) {
		this.dateFinPeriode = dateFinPeriode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppreciationGlobale() {
		return appreciationGlobale;
	}

	public void setAppreciationGlobale(String appreciationGlobale) {
		this.appreciationGlobale = appreciationGlobale;
	}

	public Integer getNbBrevet() {
		return nbBrevet;
	}

	public void setNbBrevet(Integer nbBrevet) {
		this.nbBrevet = nbBrevet;
	}

	public Integer getNbPublication() {
		return nbPublication;
	}

	public void setNbPublication(Integer nbPublication) {
		this.nbPublication = nbPublication;
	}

	public String getResultatAtteint() {
		return resultatAtteint;
	}

	public void setResultatAtteint(String resultatAtteint) {
		this.resultatAtteint = resultatAtteint;
	}

	public Double getTauxRealisation() {
		return tauxRealisation;
	}

	public void setTauxRealisation(Double tauxRealisation) {
		this.tauxRealisation = tauxRealisation;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	
	
}
