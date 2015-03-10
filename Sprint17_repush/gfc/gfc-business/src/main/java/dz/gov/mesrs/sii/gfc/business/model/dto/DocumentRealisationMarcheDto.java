package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class DocumentRealisationMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private Date dateDocument;
	private NomenclatureDto typeDocument;
	private BigDecimal montantHt;
	private BigDecimal montantTva;
	private BigDecimal montantTtc;
	private String observation;
	private SituationEntiteDto situation;
	private List<DetailRealisationEquipementDto> detailRealisationEquipements = new ArrayList<DetailRealisationEquipementDto>();
	private List<DetailRealisationPrestationDto> detailRealisationPrestations = new ArrayList<DetailRealisationPrestationDto>();
	private List<DetailRealisationProduitDto> detailRealisationProduits = new ArrayList<DetailRealisationProduitDto>();
	private DossierPaiementDto dossierPaiement;

	public DocumentRealisationMarcheDto() {
		marche = new MarcheDto();
		typeDocument = new NomenclatureDto();
	}

	public DocumentRealisationMarcheDto(Integer id, MarcheDto marche, NomenclatureDto typeDocument,
			BigDecimal montantHt, BigDecimal montantTva, BigDecimal montantTtc) {
		this.id = id;
		this.marche = marche;
		this.typeDocument = typeDocument;
		this.montantHt = montantHt;
		this.montantTva = montantTva;
		this.montantTtc = montantTtc;
	}

	public DocumentRealisationMarcheDto(Integer id, MarcheDto marche, NomenclatureDto typeDocument,
			BigDecimal montantHt, BigDecimal montantTva, BigDecimal montantTtc, String observation,
			SituationEntiteDto situation, List<DetailRealisationEquipementDto> detailRealisationEquipements,
			List<DetailRealisationPrestationDto> detailRealisationPrestations,
			List<DetailRealisationProduitDto> detailRealisationProduits, DossierPaiementDto dossierPaiement) {
		this.id = id;
		this.marche = marche;
		this.typeDocument = typeDocument;
		this.montantHt = montantHt;
		this.montantTva = montantTva;
		this.montantTtc = montantTtc;
		this.observation = observation;
		this.situation = situation;
		this.detailRealisationEquipements = detailRealisationEquipements;
		this.detailRealisationPrestations = detailRealisationPrestations;
		this.detailRealisationProduits = detailRealisationProduits;
		this.dossierPaiement = dossierPaiement;
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

	public Date getDateDocument() {
		return dateDocument;
	}

	public void setDateDocument(Date dateDocument) {
		this.dateDocument = dateDocument;
	}

	public NomenclatureDto getTypeDocument() {
		return this.typeDocument;
	}

	public void setTypeDocument(NomenclatureDto typeDocument) {
		this.typeDocument = typeDocument;
	}

	public BigDecimal getMontantHt() {
		return this.montantHt;
	}

	public void setMontantHt(BigDecimal montantHt) {
		this.montantHt = montantHt;
	}

	public BigDecimal getMontantTva() {
		return this.montantTva;
	}

	public void setMontantTva(BigDecimal montantTva) {
		this.montantTva = montantTva;
	}

	public BigDecimal getMontantTtc() {
		return this.montantTtc;
	}

	public void setMontantTtc(BigDecimal montantTtc) {
		this.montantTtc = montantTtc;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public List<DetailRealisationEquipementDto> getDetailRealisationEquipements() {
		return this.detailRealisationEquipements;
	}

	public void setDetailRealisationEquipements(List<DetailRealisationEquipementDto> detailRealisationEquipements) {
		this.detailRealisationEquipements = detailRealisationEquipements;
	}

	public List<DetailRealisationPrestationDto> getDetailRealisationPrestations() {
		return this.detailRealisationPrestations;
	}

	public void setDetailRealisationPrestations(List<DetailRealisationPrestationDto> detailRealisationPrestations) {
		this.detailRealisationPrestations = detailRealisationPrestations;
	}

	public List<DetailRealisationProduitDto> getDetailRealisationProduits() {
		return this.detailRealisationProduits;
	}

	public void setDetailRealisationProduits(List<DetailRealisationProduitDto> detailRealisationProduits) {
		this.detailRealisationProduits = detailRealisationProduits;
	}

	public DossierPaiementDto getDossierPaiement() {
		return this.dossierPaiement;
	}

	public void setDossierPaiement(DossierPaiementDto dossierPaiement) {
		this.dossierPaiement = dossierPaiement;
	}
}