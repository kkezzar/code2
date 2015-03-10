package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;

public class ProjetPartenaireDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private PartenaireDto partenaireDto; 
	private ProjetDto projetDto;
	private String description;
	
	public ProjetPartenaireDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public PartenaireDto getPartenaireDto() {
		return partenaireDto;
	}

	public void setPartenaireDto(PartenaireDto partenaireDto) {
		this.partenaireDto = partenaireDto;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	
}
