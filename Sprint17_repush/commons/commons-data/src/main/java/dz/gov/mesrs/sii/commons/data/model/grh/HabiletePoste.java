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
@Table(name = "habilete_poste", schema = "grh")
public class HabiletePoste implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = -8398928356242524509L;
	private Long id;
	private String motsCles;
	private String description;
	private Nomenclature typeHabilete;
	private Nomenclature niveauHabilete;
	private PosteEmploi poste;

	public HabiletePoste() {
		super();
	}

	@Id
	@SequenceGenerator(name = "grh.habilete_poste_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh.habilete_poste_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	@Column(name = "mots_cles")
	public String getMotsCles() {
		return motsCles;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_habilete", nullable = false)
	public Nomenclature getTypeHabilete() {
		return typeHabilete;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "niveau_habilete", nullable = false)
	public Nomenclature getNiveauHabilete() {
		return niveauHabilete;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "poste_emploi", nullable = false)
	public PosteEmploi getPoste() {
		return poste;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMotsCles(String motsCles) {
		this.motsCles = motsCles;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTypeHabilete(Nomenclature typeHabilete) {
		this.typeHabilete = typeHabilete;
	}

	public void setNiveauHabilete(Nomenclature niveauHabilete) {
		this.niveauHabilete = niveauHabilete;
	}

	public void setPoste(PosteEmploi poste) {
		this.poste = poste;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motsCles == null) ? 0 : motsCles.hashCode());
		result = prime * result + ((niveauHabilete == null) ? 0 : niveauHabilete.hashCode());
		result = prime * result + ((typeHabilete == null) ? 0 : typeHabilete.hashCode());
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
		HabiletePoste other = (HabiletePoste) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motsCles == null) {
			if (other.motsCles != null)
				return false;
		} else if (!motsCles.equals(other.motsCles))
			return false;
		if (niveauHabilete == null) {
			if (other.niveauHabilete != null)
				return false;
		} else if (!niveauHabilete.equals(other.niveauHabilete))
			return false;
		if (typeHabilete == null) {
			if (other.typeHabilete != null)
				return false;
		} else if (!typeHabilete.equals(other.typeHabilete))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HabiletePoste [id=" + id + ", motsCles=" + motsCles + ", description=" + description
				+ ", typeHabilete=" + typeHabilete + ", niveauHabilete=" + niveauHabilete + "]";
	}

}
