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
@Table(name = "vaccin", schema = "grh")
public class Vaccin implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Nomenclature nomenclature;
	private DossierEmploye dossierEmploye;
	private Date dateVaccin;
	private Date dateRappel;

	public Vaccin() {
	}

	public Vaccin(Integer id) {
		this.id = id;
	}

	public Vaccin(Integer id, Nomenclature nomenclature, DossierEmploye dossierEmploye, Date dateVaccin, Date dateRappel) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.dossierEmploye = dossierEmploye;
		this.dateVaccin = dateVaccin;
		this.dateRappel = dateRappel;
	}

	@Id
	@SequenceGenerator(name = "grh_vaccin_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_vaccin_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "libelle")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employe")
	public DossierEmploye getDossierEmploye() {
		return this.dossierEmploye;
	}

	public void setDossierEmploye(DossierEmploye dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_vaccin", length = 13)
	public Date getDateVaccin() {
		return this.dateVaccin;
	}

	public void setDateVaccin(Date dateVaccin) {
		this.dateVaccin = dateVaccin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_rappel", length = 13)
	public Date getDateRappel() {
		return this.dateRappel;
	}

	public void setDateRappel(Date dateRappel) {
		this.dateRappel = dateRappel;
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
				+ ((dateRappel == null) ? 0 : dateRappel.hashCode());
		result = prime * result
				+ ((dateVaccin == null) ? 0 : dateVaccin.hashCode());
		result = prime * result
				+ ((dossierEmploye == null) ? 0 : dossierEmploye.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((nomenclature == null) ? 0 : nomenclature.hashCode());
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
		Vaccin other = (Vaccin) obj;
		if (dateRappel == null) {
			if (other.dateRappel != null)
				return false;
		} else if (!dateRappel.equals(other.dateRappel))
			return false;
		if (dateVaccin == null) {
			if (other.dateVaccin != null)
				return false;
		} else if (!dateVaccin.equals(other.dateVaccin))
			return false;
		if (dossierEmploye == null) {
			if (other.dossierEmploye != null)
				return false;
		} else if (!dossierEmploye.equals(other.dossierEmploye))
			return false;
		if (id != other.id)
			return false;
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vaccin [id=" + id + ", nomenclature=" + nomenclature
				+ ", dossierEmploye=" + dossierEmploye + ", dateVaccin="
				+ dateVaccin + ", dateRappel=" + dateRappel + "]";
	}
	
	
}
