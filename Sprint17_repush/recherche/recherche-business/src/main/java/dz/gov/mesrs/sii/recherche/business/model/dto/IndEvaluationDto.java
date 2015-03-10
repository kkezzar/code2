package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class IndEvaluationDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long numero;
	private NomenclatureDto typeAppreciationDto;
	private Double valeurPrev;
	private NomenclatureDto ncIndicateurDto;	
	private ProjetDto projetDto;
	
	public IndEvaluationDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	

	public NomenclatureDto getTypeAppreciationDto() {
		return typeAppreciationDto;
	}

	public void setTypeAppreciationDto(NomenclatureDto typeAppreciationDto) {
		this.typeAppreciationDto = typeAppreciationDto;
	}

	public Double getValeurPrev() {
		return valeurPrev;
	}

	public void setValeurPrev(Double valeurPrev) {
		this.valeurPrev = valeurPrev;
	}

	public NomenclatureDto getNcIndicateurDto() {
		return ncIndicateurDto;
	}

	public void setNcIndicateurDto(NomenclatureDto ncIndicateurDto) {
		this.ncIndicateurDto = ncIndicateurDto;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	
	
	
	
}


