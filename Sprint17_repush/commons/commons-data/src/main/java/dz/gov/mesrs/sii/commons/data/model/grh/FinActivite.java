package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Dossier;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "fin_activite", schema = "grh")
public class FinActivite implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature nomenclature;
	private Nomenclature typeDepartRetraite;
	private DossierEmploye employe;
	private Date dateDemande;
	private String objet;
	private Date dateDepart;
	private Boolean accepte;
	private String motifRefus;
	private Date dateDepartAcceptee;
	private String motifCessation;
	private Date dateCessationFinale;
	private Set<PieceDossierRetraite> pieceDossierRetraites = new HashSet<PieceDossierRetraite>(0);
	private SituationEntite situation;
	private Date dateDecision;
	private Dossier dossieRetraite;

	public FinActivite() {
	}

	public FinActivite(Long id) {
		this.id = id;
	}

	public FinActivite(Long id, Nomenclature nomenclature, DossierEmploye employe, Date dateDemande, String objet,
			Date dateDepart, Boolean accepte, String motifRefus, Date dateDepartAcceptee, String motifCessation,
			Date dateCessationFinale, Set<PieceDossierRetraite> pieceDossierRetraites) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.employe = employe;
		this.dateDemande = dateDemande;
		this.objet = objet;
		this.dateDepart = dateDepart;
		this.accepte = accepte;
		this.motifRefus = motifRefus;
		this.dateDepartAcceptee = dateDepartAcceptee;
		this.motifCessation = motifCessation;
		this.dateCessationFinale = dateCessationFinale;
		this.pieceDossierRetraites = pieceDossierRetraites;
	}

	@Id
	@SequenceGenerator(name = "fin_activite_id_fin_activite_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fin_activite_id_fin_activite_seq")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_cessation")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne
	@JoinColumn(name = "employe", nullable = false)
	public DossierEmploye getEmploye() {
		return this.employe;
	}

	public void setEmploye(DossierEmploye employe) {
		this.employe = employe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_demande")
	public Date getDateDemande() {
		return this.dateDemande;
	}

	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	@Column(name = "objet", length = 256)
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_depart", length = 13)
	public Date getDateDepart() {
		return this.dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Column(name = "accepte")
	public Boolean getAccepte() {
		return this.accepte;
	}

	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}

	@Column(name = "motif_refus", length = 256)
	public String getMotifRefus() {
		return this.motifRefus;
	}

	public void setMotifRefus(String motifRefus) {
		this.motifRefus = motifRefus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_depart_acceptee", length = 13)
	public Date getDateDepartAcceptee() {
		return this.dateDepartAcceptee;
	}

	public void setDateDepartAcceptee(Date dateDepartAcceptee) {
		this.dateDepartAcceptee = dateDepartAcceptee;
	}

	@Column(name = "motif_cessation")
	public String getMotifCessation() {
		return this.motifCessation;
	}

	public void setMotifCessation(String motifCessation) {
		this.motifCessation = motifCessation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_cessation_finale", length = 13)
	public Date getDateCessationFinale() {
		return this.dateCessationFinale;
	}

	public void setDateCessationFinale(Date dateCessationFinale) {
		this.dateCessationFinale = dateCessationFinale;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "finActivite")
	public Set<PieceDossierRetraite> getPieceDossierRetraites() {
		return this.pieceDossierRetraites;
	}

	public void setPieceDossierRetraites(Set<PieceDossierRetraite> pieceDossierRetraites) {
		this.pieceDossierRetraites = pieceDossierRetraites;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_decision", length = 13)
	public Date getDateDecision() {
		return dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_depart_retraite")
	public Nomenclature getTypeDepartRetraite() {
		return typeDepartRetraite;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true, fetch = FetchType.LAZY )
	@JoinColumn(nullable=true,name="dossier_retraite")
	public Dossier getDossieRetraite() {
		return dossieRetraite;
	}

	public void setDossieRetraite(Dossier dossieRetraite) {
		this.dossieRetraite = dossieRetraite;
	}

	public void setTypeDepartRetraite(Nomenclature typeDepartRetraite) {
		this.typeDepartRetraite = typeDepartRetraite;
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
