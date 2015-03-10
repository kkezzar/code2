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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * 
 * @author BELDI Jamel on : 25 déc. 2014 10:08:06
 */
@Entity
@Table(name = "mutation", schema = "grh")
public class Mutation implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 25 déc. 2014 10:12:54
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private SituationEntite situationEntite;
	private RefEtablissement etabDemande;
	private DossierEmploye dossierEmploye;
	private RefEtablissement etabOrigine;
	private Date dateDemande;
	private String objet;
	private Date dateSouhaite;
	private String observation;
	private Date dateResultat;
	private Boolean accepte;
	private Boolean motif;
	private Date dateAccordEtabOrigine;
	private Date dateAccordEtabAccueil;
	private Date dateEffet;
	private Date dateDecision;
	private Boolean necessiteService;

	public Mutation() {
	}

	@Id
	@SequenceGenerator(name = "grh_mutation_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_mutation_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
	@JoinColumn(name = "etab_demande", nullable = false)
	public RefEtablissement getEtabDemande() {
		return this.etabDemande;
	}

	public void setEtabDemande(RefEtablissement etabDemande) {
		this.etabDemande = etabDemande;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etab_origine", nullable = false)
	public RefEtablissement getEtabOrigine() {
		return this.etabOrigine;
	}

	public void setEtabOrigine(RefEtablissement etabOrigine) {
		this.etabOrigine = etabOrigine;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande", length = 13)
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet", nullable = false)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_souhaite", length = 13)
	public Date getDateSouhaite() {
		return this.dateSouhaite;
	}

	public void setDateSouhaite(Date dateSouhaite) {
		this.dateSouhaite = dateSouhaite;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_resultat", length = 13)
	public Date getDateResultat() {
		return this.dateResultat;
	}

	public void setDateResultat(Date dateResultat) {
		this.dateResultat = dateResultat;
	}

	@Column(name = "accepte")
	public Boolean getAccepte() {
		return this.accepte;
	}

	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}

	@Column(name = "motif")
	public Boolean getMotif() {
		return this.motif;
	}

	public void setMotif(Boolean motif) {
		this.motif = motif;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_accord_etab_origine", length = 13)
	public Date getDateAccordEtabOrigine() {
		return this.dateAccordEtabOrigine;
	}

	public void setDateAccordEtabOrigine(Date dateAccordEtabOrigine) {
		this.dateAccordEtabOrigine = dateAccordEtabOrigine;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_accord_etab_accueil", length = 13)
	public Date getDateAccordEtabAccueil() {
		return this.dateAccordEtabAccueil;
	}

	public void setDateAccordEtabAccueil(Date dateAccordEtabAccueil) {
		this.dateAccordEtabAccueil = dateAccordEtabAccueil;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_effet", length = 13)
	public Date getDateEffet() {
		return this.dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_decision", length = 13)
	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	@Column(name = "necessite_service")
	public Boolean getNecessiteService() {
		return this.necessiteService;
	}

	public void setNecessiteService(Boolean necessiteService) {
		this.necessiteService = necessiteService;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
