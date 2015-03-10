package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class DossierPaiementDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private DocumentRealisationMarcheDto documentRealisationMarche;
	private String objet;
	private Date dateDossier;
	private NomenclatureDto modePaiement;
	private String referenceBancaire;
	private SituationEntiteDto situation;

	private String observation;

	public DossierPaiementDto() {
		modePaiement = new NomenclatureDto();
		documentRealisationMarche = new DocumentRealisationMarcheDto();
	}

	public DossierPaiementDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche, String objet,
			Date dateDossier, NomenclatureDto modePaiement, String referenceBancaire) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.objet = objet;
		this.dateDossier = dateDossier;
		this.modePaiement = modePaiement;
		this.referenceBancaire = referenceBancaire;
	}

	public DossierPaiementDto(Integer id, DocumentRealisationMarcheDto documentRealisationMarche, String objet,
			Date dateDossier, NomenclatureDto modePaiement, String referenceBancaire, SituationEntiteDto situation) {
		this.id = id;
		this.documentRealisationMarche = documentRealisationMarche;
		this.objet = objet;
		this.dateDossier = dateDossier;
		this.modePaiement = modePaiement;
		this.referenceBancaire = referenceBancaire;
		this.situation = situation;
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

	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateDossier() {
		return this.dateDossier;
	}

	public void setDateDossier(Date dateDossier) {
		this.dateDossier = dateDossier;
	}

	public NomenclatureDto getModePaiement() {
		return this.modePaiement;
	}

	public void setModePaiement(NomenclatureDto modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getReferenceBancaire() {
		return this.referenceBancaire;
	}

	public void setReferenceBancaire(String referenceBancaire) {
		this.referenceBancaire = referenceBancaire;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}