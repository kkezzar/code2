package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class PrestationMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private String description;
	private NomenclatureDto type;
	private Integer quantite;
	private BigDecimal prixUnitaire;
	private Float tauxTva;
	private BigDecimal montantTtc;
	private List<DetailRealisationPrestationDto> detailRealisationPrestations = new ArrayList<DetailRealisationPrestationDto>();

	public PrestationMarcheDto() {
		type = new NomenclatureDto();
	}

	public PrestationMarcheDto(Integer id, MarcheDto marche, String description, NomenclatureDto type,
			Integer quantite, BigDecimal prixUnitaire, BigDecimal montantTtc) {
		this.id = id;
		this.marche = marche;
		this.description = description;
		this.type = type;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.montantTtc = montantTtc;
	}

	public PrestationMarcheDto(Integer id, MarcheDto marche, String description, NomenclatureDto type,
			Integer quantite, BigDecimal prixUnitaire, Float tauxTva, BigDecimal montantTtc,
			List<DetailRealisationPrestationDto> detailRealisationPrestations) {
		this.id = id;
		this.marche = marche;
		this.description = description;
		this.type = type;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.tauxTva = tauxTva;
		this.montantTtc = montantTtc;
		this.detailRealisationPrestations = detailRealisationPrestations;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MarcheDto getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheDto marche) {
		this.marche = marche;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NomenclatureDto getType() {
		return this.type;
	}

	public void setType(NomenclatureDto type) {
		this.type = type;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Float getTauxTva() {
		return this.tauxTva;
	}

	public void setTauxTva(Float tauxTva) {
		this.tauxTva = tauxTva;
	}

	public BigDecimal getMontantTtc() {
		return this.montantTtc;
	}

	public void setMontantTtc(BigDecimal montantTtc) {
		this.montantTtc = montantTtc;
	}

	public List<DetailRealisationPrestationDto> getDetailRealisationPrestations() {
		return this.detailRealisationPrestations;
	}

	public void setDetailRealisationPrestations(List<DetailRealisationPrestationDto> detailRealisationPrestations) {
		this.detailRealisationPrestations = detailRealisationPrestations;
	}
}
