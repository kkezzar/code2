package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
/**
 * 
 * @author BELDI Jamel
 *
 */

@Entity
@Table(name = "conge_employe", schema = "grh")
public class CongeEmploye implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntite situationEntite;
	private Nomenclature nomenclatureByTypeConge;
	private DossierEmploye dossierEmploye;
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
	public CongeEmploye() {
	}

	public CongeEmploye(Long id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "grh_conge_employe_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_conge_employe_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation")
	public SituationEntite getSituationEntite() {
		return this.situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_conge")
	public Nomenclature getNomenclatureByTypeConge() {
		return this.nomenclatureByTypeConge;
	}

	public void setNomenclatureByTypeConge(Nomenclature nomenclatureByTypeConge) {
		this.nomenclatureByTypeConge = nomenclatureByTypeConge;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", length = 13)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet")
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column(name = "nbre_jour_demande", precision = 17, scale = 17)
	public Double getNbreJourDemande() {
		return this.nbreJourDemande;
	}

	public void setNbreJourDemande(Double nbreJourDemande) {
		this.nbreJourDemande = nbreJourDemande;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_resultat", length = 13)
	public Date getDateResultat() {
		return this.dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	@Column(name = "resultat")
	public Boolean getResultat() {
		return this.resultat;
	}

	public void setResultat(Boolean resultat) {
		this.resultat = resultat;
	}

	@Column(name = "motif_refus")
	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut_accepte", length = 13)
	public Date getDateDebutAccepte() {
		return this.dateDebutAccepte;
	}

	public void setDateDebutAccepte(Date dateDebutAccepte) {
		this.dateDebutAccepte = dateDebutAccepte;
	}

	@Column(name = "nbre_jour_accepte", precision = 17, scale = 17)
	public Double getNbreJourAccepte() {
		return this.nbreJourAccepte;
	}

	public void setNbreJourAccepte(Double nbreJourAccepte) {
		this.nbreJourAccepte = nbreJourAccepte;
	}

	@Column(name = "motif_annulation")
	public String getMotifAnnulation() {
		return this.motifAnnulation;
	}

	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_reprise_reelle", length = 13)
	public Date getDateRepriseReelle() {
		return this.dateRepriseReelle;
	}

	public void setDateRepriseReelle(Date dateRepriseReelle) {
		this.dateRepriseReelle = dateRepriseReelle;
	}

	@Column(name = "motif_reprise")
	public String getMotifReprise() {
		return this.motifReprise;
	}

	public void setMotifReprise(String motifReprise) {
		this.motifReprise = motifReprise;
	}

	@Column(name = "nbre_jour_reel", precision = 17, scale = 17)
	public Double getNbreJourReel() {
		return this.nbreJourReel;
	}

	public void setNbreJourReel(Double nbreJourReel) {
		this.nbreJourReel = nbreJourReel;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_reprise", length = 13)
	public Date getDateReprise() {
		return dateReprise;
	}

	public void setDateReprise(Date dateReprise) {
		this.dateReprise = dateReprise;
	}

	public Double getSoldeConge() {
		return soldeConge;
	}

	@Column(name = "solde_conge", precision = 17, scale = 17)
	public void setSoldeConge(Double soldeConge) {
		this.soldeConge = soldeConge;
	}

	
	@Column(name = "annee_grh")
	public String getAnneeGrh() {
		return anneeGrh;
	}

	public void setAnneeGrh(String anneeGrh) {
		this.anneeGrh = anneeGrh;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_reprise_accepte", length = 13)
	public Date getDateRepriseAccepte() {
		return dateRepriseAccepte;
	}

	public void setDateRepriseAccepte(Date dateRepriseAccepte) {
		this.dateRepriseAccepte = dateRepriseAccepte;
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
