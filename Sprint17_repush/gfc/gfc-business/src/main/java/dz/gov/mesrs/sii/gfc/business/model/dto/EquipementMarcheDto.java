package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEquipementDto;

public class EquipementMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private RefEquipementDto equipement;
	private Integer quantite;
	private BigDecimal prixUnitaire;
	private Float tauxTva;
	private BigDecimal montantTtc;
	private List<DetailRealisationEquipementDto> detailRealisationEquipements = new ArrayList<DetailRealisationEquipementDto>();

	public EquipementMarcheDto() {
		equipement = new RefEquipementDto();
	}

	public EquipementMarcheDto(Integer id, RefEquipementDto equipement, Integer quantite, BigDecimal prixUnitaire,
			BigDecimal montantTtc) {
		this.id = id;
		this.equipement = equipement;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.montantTtc = montantTtc;
	}

	public EquipementMarcheDto(Integer id, MarcheDto marche, RefEquipementDto equipement, Integer quantite,
			BigDecimal prixUnitaire, Float tauxTva, BigDecimal montantTtc,
			List<DetailRealisationEquipementDto> detailRealisationEquipements) {
		this.id = id;
		this.marche = marche;
		this.equipement = equipement;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.tauxTva = tauxTva;
		this.montantTtc = montantTtc;
		this.detailRealisationEquipements = detailRealisationEquipements;
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

	public RefEquipementDto getEquipement() {
		return this.equipement;
	}

	public void setEquipement(RefEquipementDto equipement) {
		this.equipement = equipement;
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

	public List<DetailRealisationEquipementDto> getDetailRealisationEquipements() {
		return this.detailRealisationEquipements;
	}

	public void setDetailRealisationEquipements(List<DetailRealisationEquipementDto> detailRealisationEquipements) {
		this.detailRealisationEquipements = detailRealisationEquipements;
	}
}