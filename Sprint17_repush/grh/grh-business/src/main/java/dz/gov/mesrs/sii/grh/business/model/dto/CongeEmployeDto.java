package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
/**
 * 
 * @author BELDI Jamel
 *
 */


public class CongeEmployeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntiteDto situationEntiteDto;
	private NomenclatureDto nomenclatureByTypeCongeDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDemande;
	private String objet;
	private Date dateDebut;
	private Double nbreJourDemande;
	private Date dateResultat;
	private Boolean resultat;
	private String motifRefus;
	private Date dateDebutAccepte;
	private Double nbreJourAccepte;
	private String motifAnnulation;
	private Date dateRepriseReelle;
	private String motifReprise;
	private Double nbreJourReel;
	private Date dateReprise;
	private Date dateRepriseAccepte;	
	private Double soldeConge;
	private String anneeGrh;
	public CongeEmployeDto() {
	}

	public CongeEmployeDto(Long id) {
		this.id = id;
	}
	public CongeEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public NomenclatureDto getNomenclatureByTypeCongeDto() {
		return nomenclatureByTypeCongeDto;
	}

	public void setNomenclatureByTypeCongeDto(
			NomenclatureDto nomenclatureByTypeCongeDto) {
		this.nomenclatureByTypeCongeDto = nomenclatureByTypeCongeDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Double getNbreJourDemande() {
		return nbreJourDemande;
	}

	public void setNbreJourDemande(Double nbreJourDemande) {
		this.nbreJourDemande = nbreJourDemande;
	}

	public Date getDateResultat() {
		return dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	public Boolean getResultat() {
		return resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	public String getMotifRefus() {
		return motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	public Date getDateDebutAccepte() {
		return dateDebutAccepte;
	}

	public void setDateDebutAccepte(Date dateDebutAccepte) {
		this.dateDebutAccepte = dateDebutAccepte;
	}

	public Double getNbreJourAccepte() {
		return nbreJourAccepte;
	}

	public void setNbreJourAccepte(Double nbreJourAccepte) {
		this.nbreJourAccepte = nbreJourAccepte;
	}

	public String getMotifAnnulation() {
		return motifAnnulation;
	}

	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}

	public Date getDateRepriseReelle() {
		return dateRepriseReelle;
	}

	public void setDateRepriseReelle(Date dateRepriseReelle) {
		this.dateRepriseReelle = dateRepriseReelle;
	}

	public String getMotifReprise() {
		return motifReprise;
	}

	public void setMotifReprise(String motifReprise) {
		this.motifReprise = motifReprise;
	}

	public Double getNbreJourReel() {
		return nbreJourReel;
	}

	public void setNbreJourReel(Double nbreJourReel) {
		this.nbreJourReel = nbreJourReel;
	}

	public Date getDateReprise() {
		return dateReprise;
	}

	public void setDateReprise(Date dateReprise) {
		this.dateReprise = dateReprise;
	}

	public Date getDateRepriseAccepte() {
		return dateRepriseAccepte;
	}

	public void setDateRepriseAccepte(Date dateRepriseAccepte) {
		this.dateRepriseAccepte = dateRepriseAccepte;
	}

	public Double getSoldeConge() {
		return soldeConge;
	}

	public void setSoldeConge(Double soldeConge) {
		this.soldeConge = soldeConge;
	}

	public String getAnneeGrh() {
		return anneeGrh;
	}

	public void setAnneeGrh(String anneeGrh) {
		this.anneeGrh = anneeGrh;
	}

	
	
	
}
