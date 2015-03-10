package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;

public class DetailRealisationPrestationDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private DocumentRealisationMarcheDto documentRealisationMarche;
	private PrestationMarcheDto prestationMarche;
	private Integer quantite;
	private BigDecimal montantRealise;
	private String observation;

	public DetailRealisationPrestationDto() {
		prestationMarche = new PrestationMarcheDto();
	}

	public DetailRealisationPrestationDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche,
			PrestationMarcheDto prestationMarche, Integer quantite, BigDecimal montantRealise) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.prestationMarche = prestationMarche;
		this.quantite = quantite;
		this.montantRealise = montantRealise;
	}

	public DetailRealisationPrestationDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche,
			PrestationMarcheDto prestationMarche, Integer quantite, BigDecimal montantRealise, String observation) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.prestationMarche = prestationMarche;
		this.quantite = quantite;
		this.montantRealise = montantRealise;
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

	public PrestationMarcheDto getPrestationMarche() {
		return this.prestationMarche;
	}

	public void setPrestationMarche(PrestationMarcheDto prestationMarche) {
		this.prestationMarche = prestationMarche;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getMontantRealise() {
		return this.montantRealise;
	}

	public void setMontantRealise(BigDecimal montantRealise) {
		this.montantRealise = montantRealise;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}