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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "programme_formation_grh", schema = "grh")
public class ProgrammeFormationGrh implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String intitule;
	private String objectif;
	private Date dateDebut;
	private Date dateFin;
	private String code;
	private Set<CycleFormation> cycleFormations = new HashSet<CycleFormation>(0);

	public ProgrammeFormationGrh() {
	}

	public ProgrammeFormationGrh(Long id) {
		this.id = id;
	}

	

	@Id
	@SequenceGenerator(name="programme_formation_grh_id_seq_gen", sequenceName="grh.programme_formation_grh_id_seq")
	@GeneratedValue(generator="programme_formation_grh_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "intitule")
	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Column(name = "objectif")
	public String getObjectif() {
		return this.objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
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

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "programmeFormationGrh", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<CycleFormation> getCycleFormations() {
		return this.cycleFormations;
	}

	public void setCycleFormations(Set<CycleFormation> cycleFormations) {
		this.cycleFormations = cycleFormations;
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
