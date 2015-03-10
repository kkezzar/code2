package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.EtapeDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EtapeValidationDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dateReponce;
	private String observation;
	private NomenclatureDto reponceDto;
	private EtapeDto etapeCircuitDto;
	private ProjetDto projetDto;
	private DemandeBudgetDto demandeBudgetDto;
	
	public EtapeValidationDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Date getDateReponce() {
		return dateReponce;
	}

	public void setDateReponce(Date dateReponce) {
		this.dateReponce = dateReponce;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	
	

	

	public DemandeBudgetDto getDemandeBudgetDto() {
		return demandeBudgetDto;
	}

	public void setDemandeBudgetDto(DemandeBudgetDto demandeBudgetDto) {
		this.demandeBudgetDto = demandeBudgetDto;
	}

	public EtapeDto getEtapeCircuitDto() {
		return etapeCircuitDto;
	}

	public void setEtapeCircuitDto(EtapeDto etapeCircuitDto) {
		this.etapeCircuitDto = etapeCircuitDto;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	public NomenclatureDto getReponceDto() {
		return reponceDto;
	}

	public void setReponceDto(NomenclatureDto reponceDto) {
		this.reponceDto = reponceDto;
	}

	

	
	
	
	
	
}
