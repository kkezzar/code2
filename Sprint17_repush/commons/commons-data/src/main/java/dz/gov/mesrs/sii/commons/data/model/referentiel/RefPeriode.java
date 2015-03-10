package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 20 mars 2014 11:28:42 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RefPeriode generated by hbm2java
 */
@Entity
@Table(name = "ref_periode", schema = "ppm")
public class RefPeriode implements java.io.Serializable {

	private int id;
	private String identifiant;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private Boolean periodique;

	public RefPeriode() {
	}

	public RefPeriode(int id, String identifiant, String nom) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
	}

	public RefPeriode(int id, String identifiant, String nom, Date dateDebut,
			Date dateFin, Boolean periodique) {
		this.id = id;
		this.identifiant = identifiant;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.periodique = periodique;
	}

	@Id
	@SequenceGenerator(name="seq_periode", initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_periode")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "identifiant", nullable = false, length = 15)
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	@Column(name = "nom", nullable = false, length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	@Column(name = "periodique")
	public Boolean getPeriodique() {
		return this.periodique;
	}

	public void setPeriodique(Boolean periodique) {
		this.periodique = periodique;
	}

}
