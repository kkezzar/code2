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
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "dossier_medical", schema = "grh")
public class DossierMedical implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private DossierEmploye dossierEmploye;
	private Nomenclature nomenclature;
	private Date dateSurvenue;
	private String observation;
	private Boolean longDuree;
	private Boolean antecedent;
	private Boolean allergie;
	private Boolean pathologie;
	
	public DossierMedical() {
	}

	public DossierMedical(Integer id, DossierEmploye dossierEmploye) {
		this.id = id;
		this.dossierEmploye = dossierEmploye;
	}

	

	@Id
	@SequenceGenerator(name = "grh_dossier_medical_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_dossier_medical_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employe", nullable = false)
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "libelle")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	

	@Temporal(TemporalType.DATE)
	@Column(name = "date_survenue", length = 13)
	public Date getDateSurvenue() {
		return this.dateSurvenue;
	}

	public void setDateSurvenue(Date dateSurvenue) {
		this.dateSurvenue = dateSurvenue;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Column(name = "long_duree")
	public Boolean getLongDuree() {
		return this.longDuree;
	}

	public void setLongDuree(Boolean longDuree) {
		this.longDuree = longDuree;
	}

	@Column(name = "antecedent")
	public Boolean getAntecedent() {
		return antecedent;
	}

	
	public void setAntecedent(Boolean antecedent) {
		this.antecedent = antecedent;
	}

	@Column(name = "allergie")
	public Boolean getAllergie() {
		return allergie;
	}

	public void setAllergie(Boolean allergie) {
		this.allergie = allergie;
	}

	@Column(name = "pathologie")
	public Boolean getPathologie() {
		return pathologie;
	}

	public void setPathologie(Boolean pathologie) {
		this.pathologie = pathologie;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allergie == null) ? 0 : allergie.hashCode());
		result = prime * result
				+ ((antecedent == null) ? 0 : antecedent.hashCode());
		result = prime * result
				+ ((dateSurvenue == null) ? 0 : dateSurvenue.hashCode());
		result = prime * result
				+ ((dossierEmploye == null) ? 0 : dossierEmploye.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((longDuree == null) ? 0 : longDuree.hashCode());
		result = prime * result
				+ ((nomenclature == null) ? 0 : nomenclature.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		result = prime * result
				+ ((pathologie == null) ? 0 : pathologie.hashCode());
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
		DossierMedical other = (DossierMedical) obj;
		if (allergie == null) {
			if (other.allergie != null)
				return false;
		} else if (!allergie.equals(other.allergie))
			return false;
		if (antecedent == null) {
			if (other.antecedent != null)
				return false;
		} else if (!antecedent.equals(other.antecedent))
			return false;
		if (dateSurvenue == null) {
			if (other.dateSurvenue != null)
				return false;
		} else if (!dateSurvenue.equals(other.dateSurvenue))
			return false;
		if (dossierEmploye == null) {
			if (other.dossierEmploye != null)
				return false;
		} else if (!dossierEmploye.equals(other.dossierEmploye))
			return false;
		if (id != other.id)
			return false;
		if (longDuree == null) {
			if (other.longDuree != null)
				return false;
		} else if (!longDuree.equals(other.longDuree))
			return false;
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (pathologie == null) {
			if (other.pathologie != null)
				return false;
		} else if (!pathologie.equals(other.pathologie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DossierMedical [id=" + id + ", dossierEmploye="
				+ dossierEmploye + ", nomenclature=" + nomenclature
				+ ", dateSurvenue=" + dateSurvenue + ", observation="
				+ observation + ", longDuree=" + longDuree + ", antecedent="
				+ antecedent + ", allergie=" + allergie + ", pathologie="
				+ pathologie + "]";
	}
	
	
	
}
