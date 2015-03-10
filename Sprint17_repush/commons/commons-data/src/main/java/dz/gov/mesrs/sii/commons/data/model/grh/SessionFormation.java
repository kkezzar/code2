package dz.gov.mesrs.sii.commons.data.model.grh;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefLieu;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "session_formation", schema = "grh")
public class SessionFormation implements java.io.Serializable , Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntite situationEntite;
	private RefStructure refStructure;
	private CycleFormation cycleFormation;
	private FormationCatalogue formationCatalogue;
	private RefLieu refLieu;
	private String code;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private Boolean externe;
	private String lieuExterne;
	private Integer nbParticipant;
	private Date dateEvaluation;
	private Boolean satisfaisant;
	private String observation;
	private Set<InscriptionSessionFormation> inscriptionSessionFormations = new HashSet<InscriptionSessionFormation>(0);
	private Set<FormateurSessionFormation> formateurSessionFormations = new HashSet<FormateurSessionFormation>(0);
	private Set<EvaluationSessionFormation> evaluationSessionFormations = new HashSet<EvaluationSessionFormation>(0);
	
	public SessionFormation() {
	}

	public SessionFormation(Long id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name="session_formation_id_seq_gen", sequenceName="grh.session_formation_id_seq")
	@GeneratedValue(generator="session_formation_id_seq_gen")
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
	@JoinColumn(name = "organisme_formation")
	public RefStructure getRefStructure() {
		return this.refStructure;
	}

	public void setRefStructure(RefStructure refStructure) {
		this.refStructure = refStructure;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_formation")
	public CycleFormation getCycleFormation() {
		return this.cycleFormation;
	}

	public void setCycleFormation(CycleFormation cycleFormation) {
		this.cycleFormation = cycleFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lieu_interne")
	public RefLieu getRefLieu() {
		return this.refLieu;
	}

	public void setRefLieu(RefLieu refLieu) {
		this.refLieu = refLieu;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "intitule")
	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "externe")
	public Boolean getExterne() {
		return this.externe;
	}

	public void setExterne(Boolean externe) {
		this.externe = externe;
	}

	@Column(name = "lieu_externe")
	public String getLieuExterne() {
		return this.lieuExterne;
	}

	public void setLieuExterne(String lieuExterne) {
		this.lieuExterne = lieuExterne;
	}

	@Column(name = "nb_participant")
	public Integer getNbParticipant() {
		return this.nbParticipant;
	}

	public void setNbParticipant(Integer nbParticipant) {
		this.nbParticipant = nbParticipant;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_evaluation", length = 13)
	public Date getDateEvaluation() {
		return this.dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	@Column(name = "satisfaisant")
	public Boolean getSatisfaisant() {
		return this.satisfaisant;
	}

	public void setSatisfaisant(Boolean satisfaisant) {
		this.satisfaisant = satisfaisant;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionFormation", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<InscriptionSessionFormation> getInscriptionSessionFormations() {
		return this.inscriptionSessionFormations;
	}

	public void setInscriptionSessionFormations(Set<InscriptionSessionFormation> inscriptionSessionFormations) {
		this.inscriptionSessionFormations = inscriptionSessionFormations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionFormation",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<FormateurSessionFormation> getFormateurSessionFormations() {
		return this.formateurSessionFormations;
	}

	public void setFormateurSessionFormations(Set<FormateurSessionFormation> formateurSessionFormations) {
		this.formateurSessionFormations = formateurSessionFormations;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionFormation",cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<EvaluationSessionFormation> getEvaluationSessionFormations() {
		return evaluationSessionFormations;
	}

	public void setEvaluationSessionFormations(
			Set<EvaluationSessionFormation> evaluationSessionFormations) {
		this.evaluationSessionFormations = evaluationSessionFormations;
	}
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formation_catalogue")
	public FormationCatalogue getFormationCatalogue() {
		return formationCatalogue;
	}

	public void setFormationCatalogue(FormationCatalogue formationCatalogue) {
		this.formationCatalogue = formationCatalogue;
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
