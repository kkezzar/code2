package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class ActiviteProjetDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer numero;
	private String description;
	private Date dateDebutPrev;
	private Date dateFinPrev;
	private Date dateFinReel;
	private String resultatAttendu;
	private String resultatAtteint;
	private NomenclatureDto activiteNomenclatureDto;
	private ProjetDto projetDto;
	
	public ActiviteProjetDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutPrev() {
		return dateDebutPrev;
	}

	public void setDateDebutPrev(Date dateDebutPrev) {
		this.dateDebutPrev = dateDebutPrev;
	}

	public Date getDateFinPrev() {
		return dateFinPrev;
	}

	public void setDateFinPrev(Date dateFinPrev) {
		this.dateFinPrev = dateFinPrev;
	}

	public Date getDateFinReel() {
		return dateFinReel;
	}

	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}

	public String getResultatAttendu() {
		return resultatAttendu;
	}

	public void setResultatAttendu(String resultatAttendu) {
		this.resultatAttendu = resultatAttendu;
	}

	public NomenclatureDto getActiviteNomenclatureDto() {
		return activiteNomenclatureDto;
	}

	public void setActiviteNomenclatureDto(NomenclatureDto activiteNomenclatureDto) {
		this.activiteNomenclatureDto = activiteNomenclatureDto;
	}

	public ProjetDto getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projetDto = projetDto;
	}

	public String getResultatAtteint() {
		return resultatAtteint;
	}

	public void setResultatAtteint(String resultatAtteint) {
		this.resultatAtteint = resultatAtteint;
	}

	
	
	
	
}
