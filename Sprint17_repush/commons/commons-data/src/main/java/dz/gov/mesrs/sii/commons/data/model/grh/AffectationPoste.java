package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

@Entity
@Table(name = "affectation_emploi", schema = "grh")
public class AffectationPoste implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = -7528151573339185426L;

	private Long id;
	private DossierEmploye employe;
	private PosteEmploi poste;
	private Date dateDebutAffectation;
	private Date dateFinAffectation;

	public AffectationPoste() {
		super();
	}

	@Id
	@SequenceGenerator(name = "grh.affectation_emploi_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh.affectation_emploi_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "dossier_employe", nullable = false)
	public DossierEmploye getEmploye() {
		return employe;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "poste_emploi", nullable = false)
	public PosteEmploi getPoste() {
		return poste;
	}

	@Column(name = "date_debut_affectation", nullable = false)
	public Date getDateDebutAffectation() {
		return dateDebutAffectation;
	}

	@Column(name = "date_fin_affectation", nullable = true)
	public Date getDateFinAffectation() {
		return dateFinAffectation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmploye(DossierEmploye employe) {
		this.employe = employe;
	}

	public void setPoste(PosteEmploi poste) {
		this.poste = poste;
	}

	public void setDateDebutAffectation(Date dateDebutAffectation) {
		this.dateDebutAffectation = dateDebutAffectation;
	}

	public void setDateFinAffectation(Date dateFinAffectation) {
		this.dateFinAffectation = dateFinAffectation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebutAffectation == null) ? 0 : dateDebutAffectation.hashCode());
		result = prime * result + ((dateFinAffectation == null) ? 0 : dateFinAffectation.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((poste == null) ? 0 : poste.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffectationPoste other = (AffectationPoste) obj;
		if (dateDebutAffectation == null) {
			if (other.dateDebutAffectation != null)
				return false;
		} else if (!dateDebutAffectation.equals(other.dateDebutAffectation))
			return false;
		if (dateFinAffectation == null) {
			if (other.dateFinAffectation != null)
				return false;
		} else if (!dateFinAffectation.equals(other.dateFinAffectation))
			return false;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (poste == null) {
			if (other.poste != null)
				return false;
		} else if (!poste.equals(other.poste))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AffectationPoste [id=" + id + ", employe=" + employe.getMatricule() + ", poste=" + poste.getCode()
				+ ", dateDebutAffectation=" + dateDebutAffectation + ", dateFinAffectation=" + dateFinAffectation + "]";
	}

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
