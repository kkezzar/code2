package dz.gov.mesrs.sii.commons.data.model.gfc;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * DetailRealisationProduit generated by hbm2java
 */
@Entity
@Table(name = "detail_realisation_produit", schema = "gfc")
public class DetailRealisationProduit implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private DocumentRealisationMarche documentRealisationMarche;
	private ProduitMarche produitMarche;
	private BigDecimal quantite;
	private BigDecimal montantRealise;
	private Integer idBonLivraison;
	private String referenceBonLivraison;
	private String livrePar;
	private String observation;

	public DetailRealisationProduit() {
	}

	public DetailRealisationProduit(Integer id, DocumentRealisationMarche documentRealisationMarche,
			ProduitMarche produitMarche, BigDecimal quantite, BigDecimal montantRealise, String referenceBonLivraison,
			String livrePar) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.produitMarche = produitMarche;
		this.quantite = quantite;
		this.montantRealise = montantRealise;
		this.referenceBonLivraison = referenceBonLivraison;
		this.livrePar = livrePar;
	}

	public DetailRealisationProduit(Integer id, DocumentRealisationMarche documentRealisationMarche,
			ProduitMarche produitMarche, BigDecimal quantite, BigDecimal montantRealise, Integer idBonLivraison,
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

	@SequenceGenerator(name = "detail_realisation_produit_id_seq", sequenceName = "gfc.detail_realisation_produit_id_seq")
	@Id
	@GeneratedValue(generator = "detail_realisation_produit_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_document_realisation", nullable = false)
	public DocumentRealisationMarche getDocumentRealisationMarche() {
		return this.documentRealisationMarche;
	}

	public void setDocumentRealisationMarche(DocumentRealisationMarche documentRealisationMarche) {
		this.documentRealisationMarche = documentRealisationMarche;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produit_marche", nullable = false)
	public ProduitMarche getProduitMarche() {
		return this.produitMarche;
	}

	public void setProduitMarche(ProduitMarche produitMarche) {
		this.produitMarche = produitMarche;
	}

	@Column(name = "quantite", nullable = false, precision = 10)
	public BigDecimal getQuantite() {
		return this.quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	@Column(name = "montant_realise", nullable = false, precision = 10)
	public BigDecimal getMontantRealise() {
		return this.montantRealise;
	}

	public void setMontantRealise(BigDecimal montantRealise) {
		this.montantRealise = montantRealise;
	}

	@Column(name = "id_bon_livraison")
	public Integer getIdBonLivraison() {
		return this.idBonLivraison;
	}

	public void setIdBonLivraison(Integer idBonLivraison) {
		this.idBonLivraison = idBonLivraison;
	}

	@Column(name = "reference_bon_livraison", nullable = false, length = 50)
	public String getReferenceBonLivraison() {
		return this.referenceBonLivraison;
	}

	public void setReferenceBonLivraison(String referenceBonLivraison) {
		this.referenceBonLivraison = referenceBonLivraison;
	}

	@Column(name = "livre_par", nullable = false, length = 200)
	public String getLivrePar() {
		return this.livrePar;
	}

	public void setLivrePar(String livrePar) {
		this.livrePar = livrePar;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
