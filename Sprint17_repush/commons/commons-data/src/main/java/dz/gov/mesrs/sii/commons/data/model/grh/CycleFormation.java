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

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "cycle_formation", schema = "grh")
public class CycleFormation implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntite situationEntite;
	private ProgrammeFormationGrh programmeFormationGrh;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private String objectif;
	private String code;
	private Set<SessionFormation> sessionFormations = new HashSet<SessionFormation>(0);
	private Set<GradeCycleFormation> gradeCycleFormations = new HashSet<GradeCycleFormation>(0);

	public CycleFormation() {
	}

	public CycleFormation(Long id) {
		this.id = id;
	}

	
	@Id
	@SequenceGenerator(name="cycle_formation_id_seq_gen", sequenceName="grh.cycle_formation_id_seq")
	@GeneratedValue(generator="cycle_formation_id_seq_gen")
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
	@JoinColumn(name = "programme_formation")
	public ProgrammeFormationGrh getProgrammeFormationGrh() {
		return this.programmeFormationGrh;
	}

	public void setProgrammeFormationGrh(ProgrammeFormationGrh programmeFormationGrh) {
		this.programmeFormationGrh = programmeFormationGrh;
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

	@Column(name = "objectif")
	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cycleFormation", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<SessionFormation> getSessionFormations() {
		return this.sessionFormations;
	}

	public void setSessionFormations(Set<SessionFormation> sessionFormations) {
		this.sessionFormations = sessionFormations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cycleFormation", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<GradeCycleFormation> getGradeCycleFormations() {
		return this.gradeCycleFormations;
	}

	public void setGradeCycleFormations(Set<GradeCycleFormation> gradeCycleFormations) {
		this.gradeCycleFormations = gradeCycleFormations;
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
