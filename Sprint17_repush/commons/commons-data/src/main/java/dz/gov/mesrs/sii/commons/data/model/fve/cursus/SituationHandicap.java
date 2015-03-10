package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 21 mai 2014 09:47:29 by Hibernate Tools 3.6.0

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

/**
 * SituationHandicap generated by hbm2java
 */
@Entity
@Table(name = "situation_handicap", schema = "cursus")
public class SituationHandicap implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 21 mai 2014  09:53:52
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private DossierEtudiant dossierEtudiant;
	private Date dateDebut;
	private Date dateFin;
	private String observation;
	private String refCodeTypeHandicap;

	public SituationHandicap() {
	}

	public SituationHandicap(int id) {
		this.id = id;
	}

	public SituationHandicap(int id, DossierEtudiant dossierEtudiant,
			Date dateDebut, Date dateFin, String observation,
			String refCodeTypeHandicap) {
		this.id = id;
		this.dossierEtudiant = dossierEtudiant;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.observation = observation;
		this.refCodeTypeHandicap = refCodeTypeHandicap;
	}

	@SequenceGenerator(name="situationHandicap_id_seq_gen", sequenceName="cursus.situation_handicap_id_seq")
	@Id @GeneratedValue(generator="situationHandicap_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_etudiant")
	public DossierEtudiant getDossierEtudiant() {
		return this.dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
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

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Column(name = "ref_code_type_handicap", length = 30)
	public String getRefCodeTypeHandicap() {
		return this.refCodeTypeHandicap;
	}

	public void setRefCodeTypeHandicap(String refCodeTypeHandicap) {
		this.refCodeTypeHandicap = refCodeTypeHandicap;
	}

}