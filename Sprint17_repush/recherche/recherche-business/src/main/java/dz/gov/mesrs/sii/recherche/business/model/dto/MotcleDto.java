package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class MotcleDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private NomenclatureDto motCleNomenclatureDto;
	private ProjetDto projetDto;
	
	public MotcleDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getMotCleNomenclatureDto() {
		return motCleNomenclatureDto;
	}

	public void setMotCleNomenclatureDto(NomenclatureDto motCleNomenclatureDto) {
		this.motCleNomenclatureDto = motCleNomenclatureDto;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	
	
	
	
}
