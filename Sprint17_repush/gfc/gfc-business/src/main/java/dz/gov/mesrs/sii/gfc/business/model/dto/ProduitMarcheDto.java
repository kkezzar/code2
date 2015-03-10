package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

public class ProduitMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private Integer idProduit;
	private Float quantite;
	private Nomenclature unite;
	private BigDecimal prixUnitaire;
	private Float tauxTva;
	private BigDecimal montantTtc;
	private List<DetailRealisationProduitDto> detailRealisationProduits = new ArrayList<DetailRealisationProduitDto>();

	public ProduitMarcheDto() {
		unite = new Nomenclature();
	}

	public ProduitMarcheDto(Integer id, MarcheDto marche, Integer idProduit, Float quantite, Nomenclature unite,
			BigDecimal montantTtc) {
		this.id = id;
		this.marche = marche;
		this.idProduit = idProduit;
		this.quantite = quantite;
		this.unite = unite;
		this.montantTtc = montantTtc;
	}

	public ProduitMarcheDto(Integer id, MarcheDto marche, Integer idProduit, Float quantite, Nomenclature unite,
			BigDecimal prixUnitaire, Float tauxTva, BigDecimal montantTtc,
			List<DetailRealisationProduitDto> detailRealisationProduits) {
		this.id = id;
		this.marche = marche;
		this.idProduit = idProduit;
		this.quantite = quantite;
		this.unite = unite;
		this.prixUnitaire = prixUnitaire;
		this.tauxTva = tauxTva;
		this.montantTtc = montantTtc;
		this.detailRealisationProduits = detailRealisationProduits;
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

	public Integer getIdProduit() {
		return this.idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

	public Float getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Float quantite) {
		this.quantite = quantite;
	}

	public Nomenclature getUnite() {
		return this.unite;
	}

	public void setUnite(Nomenclature unite) {
		this.unite = unite;
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

	public List<DetailRealisationProduitDto> getDetailRealisationProduits() {
		return this.detailRealisationProduits;
	}

	public void setDetailRealisationProduits(List<DetailRealisationProduitDto> detailRealisationProduits) {
		this.detailRealisationProduits = detailRealisationProduits;
	}
}