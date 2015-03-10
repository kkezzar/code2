package dz.gov.mesrs.sii.commons.data.model.gfc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
 * EvenementMarche generated by hbm2java
 */
@Entity
@Table(name = "evenement_marche", schema = "gfc")
public class EvenementMarche implements java.io.Serializable, Identifiable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Marche marche;
	private Date dateEvenement;
	private Nomenclature type;
	private String description;
	private String impact;
	private String mesurePrise;
	private String observation;
	private SituationEntite situation;

	public EvenementMarche() {
	}

	public EvenementMarche(Integer id, Marche marche, Date dateEvenement, Nomenclature type) {
		this.id = id;
		this.marche = marche;
		this.dateEvenement = dateEvenement;
		this.type = type;
	}

	public EvenementMarche(Integer id, Marche marche, Date dateEvenement, Nomenclature type, String description,
			String impact, String mesurePrise, String observation, SituationEntite situation) {
		this.id = id;
		this.marche = marche;
		this.dateEvenement = dateEvenement;
		this.type = type;
		this.description = description;
		this.impact = impact;
		this.mesurePrise = mesurePrise;
		this.observation = observation;
		this.situation = situation;
	}

	@SequenceGenerator(name = "evenement_marche_id_seq", sequenceName = "gfc.evenement_marche_id_seq")
	@Id
	@GeneratedValue(generator = "evenement_marche_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marche", nullable = false)
	public Marche getMarche() {
		return this.marche;
	}

	public void setMarche(Marche marche) {
		this.marche = marche;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_evenement", nullable = false, length = 29)
	public Date getDateEvenement() {
		return this.dateEvenement;
	}

	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_evenement", nullable = false)
	public Nomenclature getType() {
		return this.type;
	}

	public void setType(Nomenclature type) {
		this.type = type;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "impact", length = 200)
	public String getImpact() {
		return this.impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	@Column(name = "mesure_prise", length = 200)
	public String getMesurePrise() {
		return this.mesurePrise;
	}

	public void setMesurePrise(String mesurePrise) {
		this.mesurePrise = mesurePrise;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}