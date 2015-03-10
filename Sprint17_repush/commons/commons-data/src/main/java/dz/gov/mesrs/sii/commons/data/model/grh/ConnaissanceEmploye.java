package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;

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
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "connaissance_employe", schema = "grh")
public class ConnaissanceEmploye implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 4952197573134793553L;

	private Long id;

	private Nomenclature type;

	private DossierEmploye employe;
	
	private String description;

	public ConnaissanceEmploye() {
		super();
	}
	
	@Id
	@SequenceGenerator(name = "grh.connaissance_employe_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh.connaissance_employe_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type")
	public Nomenclature getType() {
		return type;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dossier_employe")
	public DossierEmploye getEmploye() {
		return employe;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(Nomenclature type) {
		this.type = type;
	}

	public void setEmploye(DossierEmploye employe) {
		this.employe = employe;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ConnaissanceEmploye [id=" + id + ", type=" + type + ", employe=" + employe.getMatricule()
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ConnaissanceEmploye other = (ConnaissanceEmploye) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
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
