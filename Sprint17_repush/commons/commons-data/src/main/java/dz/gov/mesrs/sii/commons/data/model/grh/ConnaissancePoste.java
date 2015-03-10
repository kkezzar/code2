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
@Table(name = "connaissance_poste", schema = "grh")
public class ConnaissancePoste implements Serializable, Identifiable<Long> {

	private static final long serialVersionUID = -3851625825963124899L;

	private Long id;
	private String motsCles;
	private String description;
	private Nomenclature typeConnaissance;
	private PosteEmploi poste;

	public ConnaissancePoste() {
		super();
	}

	@Id
	@SequenceGenerator(name = "grh.connaissance_poste_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh.connaissance_poste_id_seq")
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
	@JoinColumn(name = "type_connaissance", nullable = false)
	public Nomenclature getTypeConnaissance() {
		return typeConnaissance;
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

	public void setTypeConnaissance(Nomenclature typeConnaissance) {
		this.typeConnaissance = typeConnaissance;
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
		result = prime * result + ((typeConnaissance == null) ? 0 : typeConnaissance.hashCode());
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
		ConnaissancePoste other = (ConnaissancePoste) obj;
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
		if (typeConnaissance == null) {
			if (other.typeConnaissance != null)
				return false;
		} else if (!typeConnaissance.equals(other.typeConnaissance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConnaissancePoste [id=" + id + ", motsCles=" + motsCles + ", description=" + description
				+ ", typeConnaissance=" + typeConnaissance + "]";
	}

}
