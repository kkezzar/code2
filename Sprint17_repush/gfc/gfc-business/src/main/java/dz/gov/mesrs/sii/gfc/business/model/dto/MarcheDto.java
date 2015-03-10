package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefContratDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class MarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String intituleFr;
	private String intituleAr;
	private RefContratDto contrat;
	private RefIndividuDto contact;
	private NomenclatureDto type;

	private RefEtablissementDto etablissement;
	private RefStructureDto structure;

	private BigDecimal montant;
	private BigDecimal montantEngage;
	private NomenclatureDto modePassation;
	private Date dateApprobation;
	private Date datePublication;
	private Date dateSignature;
	private Boolean prestation;
	private Boolean equipement;
	private Boolean produit;
	private Date dateOds;
	private String observation;

	private SituationEntiteDto situation;

	private NomenclatureDto typeCloture;

	private Date dateCloture;
	private Date dateProvisoire;
	private Date dateReceptionDefinitive;
	private Date dateDebutGarantie;
	private Date dateFinGarantie;

	private List<EvenementMarcheDto> evenementMarches = new ArrayList<EvenementMarcheDto>();
	private List<DocumentRealisationMarcheDto> documentRealisationMarches = new ArrayList<DocumentRealisationMarcheDto>();
	private List<ProduitMarcheDto> produitMarches = new ArrayList<ProduitMarcheDto>();
	private List<PrestationMarcheDto> prestationMarches = new ArrayList<PrestationMarcheDto>();
	private List<EquipementMarcheDto> equipementMarches = new ArrayList<EquipementMarcheDto>();
	private List<EngagementMarcheDto> engagementMarches = new ArrayList<EngagementMarcheDto>();

	public MarcheDto() {
		type = new NomenclatureDto();

		modePassation = new NomenclatureDto();
		situation = new SituationEntiteDto();
		contrat = new RefContratDto();
		contact = new RefIndividuDto();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RefContratDto getContrat() {
		return this.contrat;
	}

	public void setContrat(RefContratDto contrat) {
		this.contrat = contrat;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public NomenclatureDto getType() {
		return this.type;
	}

	public void setType(NomenclatureDto type) {
		this.type = type;
	}

	public RefEtablissementDto getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public RefStructureDto getStructure() {
		return structure;
	}

	public void setStructure(RefStructureDto structure) {
		this.structure = structure;
	}

	public RefIndividuDto getContact() {
		return this.contact;
	}

	public void setContact(RefIndividuDto contact) {
		this.contact = contact;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public BigDecimal getMontantEngage() {
		return this.montantEngage;
	}

	public void setMontantEngage(BigDecimal montantEngage) {
		this.montantEngage = montantEngage;
	}

	public NomenclatureDto getModePassation() {
		return this.modePassation;
	}

	public void setModePassation(NomenclatureDto modePassation) {
		this.modePassation = modePassation;
	}

	public Date getDateApprobation() {
		return this.dateApprobation;
	}

	public void setDateApprobation(Date dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public Boolean getPrestation() {
		return this.prestation;
	}

	public void setPrestation(Boolean prestation) {
		this.prestation = prestation;
	}

	public Boolean getEquipement() {
		return this.equipement;
	}

	public void setEquipement(Boolean equipement) {
		this.equipement = equipement;
	}

	public Boolean getProduit() {
		return this.produit;
	}

	public void setProduit(Boolean produit) {
		this.produit = produit;
	}

	public Date getDateOds() {
		return this.dateOds;
	}

	public void setDateOds(Date dateOds) {
		this.dateOds = dateOds;
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

	public NomenclatureDto getTypeCloture() {
		return this.typeCloture;
	}

	public void setTypeCloture(NomenclatureDto typeCloture) {
		this.typeCloture = typeCloture;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public Date getDateProvisoire() {
		return this.dateProvisoire;
	}

	public void setDateProvisoire(Date dateProvisoire) {
		this.dateProvisoire = dateProvisoire;
	}

	public Date getDateReceptionDefinitive() {
		return this.dateReceptionDefinitive;
	}

	public void setDateReceptionDefinitive(Date dateReceptionDefinitive) {
		this.dateReceptionDefinitive = dateReceptionDefinitive;
	}

	public Date getDateDebutGarantie() {
		return this.dateDebutGarantie;
	}

	public void setDateDebutGarantie(Date dateDebutGarantie) {
		this.dateDebutGarantie = dateDebutGarantie;
	}

	public Date getDateFinGarantie() {
		return this.dateFinGarantie;
	}

	public void setDateFinGarantie(Date dateFinGarantie) {
		this.dateFinGarantie = dateFinGarantie;
	}

	public List<EvenementMarcheDto> getEvenementMarches() {
		return this.evenementMarches;
	}

	public void setEvenementMarches(List<EvenementMarcheDto> evenementMarches) {
		this.evenementMarches = evenementMarches;
	}

	public List<DocumentRealisationMarcheDto> getDocumentRealisationMarches() {
		return this.documentRealisationMarches;
	}

	public void setDocumentRealisationMarches(List<DocumentRealisationMarcheDto> documentRealisationMarches) {
		this.documentRealisationMarches = documentRealisationMarches;
	}

	public List<ProduitMarcheDto> getProduitMarches() {
		return this.produitMarches;
	}

	public void setProduitMarches(List<ProduitMarcheDto> produitMarches) {
		this.produitMarches = produitMarches;
	}

	public List<PrestationMarcheDto> getPrestationMarches() {
		return this.prestationMarches;
	}

	public void setPrestationMarches(List<PrestationMarcheDto> prestationMarches) {
		this.prestationMarches = prestationMarches;
	}

	public List<EquipementMarcheDto> getEquipementMarches() {
		return this.equipementMarches;
	}

	public void setEquipementMarches(List<EquipementMarcheDto> equipementMarches) {
		this.equipementMarches = equipementMarches;
	}

	public List<EngagementMarcheDto> getEngagementMarches() {
		return this.engagementMarches;
	}

	public void setEngagementMarches(List<EngagementMarcheDto> engagementMarches) {
		this.engagementMarches = engagementMarches;
	}
}