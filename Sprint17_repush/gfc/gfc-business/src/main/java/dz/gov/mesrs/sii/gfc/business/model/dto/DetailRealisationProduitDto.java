package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

public class DetailRealisationProduitDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private DocumentRealisationMarcheDto documentRealisationMarche;
	private ProduitMarcheDto produitMarche;
	private BigDecimal quantite;
	private BigDecimal montantRealise;
	private Integer idBonLivraison;
	private String referenceBonLivraison;
	private String livrePar;
	private String observation;

	public DetailRealisationProduitDto() {
		produitMarche = new ProduitMarcheDto();
	}

	public DetailRealisationProduitDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche,
			ProduitMarcheDto produitMarche, BigDecimal quantite, BigDecimal montantRealise,
			String referenceBonLivraison, String livrePar) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.produitMarche = produitMarche;
		this.quantite = quantite;
		this.montantRealise = montantRealise;
		this.referenceBonLivraison = referenceBonLivraison;
		this.livrePar = livrePar;
	}

	public DetailRealisationProduitDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche,
			ProduitMarcheDto produitMarche, BigDecimal quantite, BigDecimal montantRealise, Integer idBonLivraison,
			String referenceBonLivraison, String livrePar, String observation) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.produitMarche = produitMarche;
		this.quantite = quantite;
		this.montantRealise = montantRealise;
		this.idBonLivraison = idBonLivraison;
		this.referenceBonLivraison = referenceBonLivraison;
		this.livrePar = livrePar;
		this.observation = observation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DocumentRealisationMarcheDto getDocumentRealisationMarche() {
		return this.documentRealisationMarche;
	}

	public void setDocumentRealisationMarche(DocumentRealisationMarcheDto documentRealisationMarche) {
		this.documentRealisationMarche = documentRealisationMarche;
	}

	public ProduitMarcheDto getProduitMarche() {
		return this.produitMarche;
	}

	public void setProduitMarche(ProduitMarcheDto produitMarche) {
		this.produitMarche = produitMarche;
	}

	public BigDecimal getQuantite() {
		return this.quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getMontantRealise() {
		return this.montantRealise;
	}

	public void setMontantRealise(BigDecimal montantRealise) {
		this.montantRealise = montantRealise;
	}

	public Integer getIdBonLivraison() {
		return this.idBonLivraison;
	}

	public void setIdBonLivraison(Integer idBonLivraison) {
		this.idBonLivraison = idBonLivraison;
	}

	public String getReferenceBonLivraison() {
		return this.referenceBonLivraison;
	}

	public void setReferenceBonLivraison(String referenceBonLivraison) {
		this.referenceBonLivraison = referenceBonLivraison;
	}

	public String getLivrePar() {
		return this.livrePar;
	}

	public void setLivrePar(String livrePar) {
		this.livrePar = livrePar;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}