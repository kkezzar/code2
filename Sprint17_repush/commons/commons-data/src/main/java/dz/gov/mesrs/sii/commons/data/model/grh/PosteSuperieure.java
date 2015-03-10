package dz.gov.mesrs.sii.commons.data.model.grh;

// Generated 21 oct. 2014 11:36:27 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * PosteSuperieure generated by hbm2java
 */
@Entity
@Table(name = "poste_superieure", schema = "grh")
public class PosteSuperieure implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private CategorieProfessionnelle categorieProfessionnelle;
	private Nomenclature nomenclature;
	private Corps corps;
	private Long bonificationIndiciaire;
	private Set<DetailRecrutement> detailRecrutements = new HashSet<DetailRecrutement>(0);

	public PosteSuperieure() {
	}

	public PosteSuperieure(Integer id) {
		this.id = id;
	}

	public PosteSuperieure(Integer id, CategorieProfessionnelle categorieProfessionnelle, Nomenclature nomenclature,
			Corps corps, Long bonificationIndiciaire, Set<DetailRecrutement> detailRecrutements) {
		this.id = id;
		this.categorieProfessionnelle = categorieProfessionnelle;
		this.nomenclature = nomenclature;
		this.corps = corps;
		this.bonificationIndiciaire = bonificationIndiciaire;
		this.detailRecrutements = detailRecrutements;
	}

	@Id
	@SequenceGenerator(name = "grh_poste_superieure_id_poste_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_poste_superieure_id_poste_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categorie")
	public CategorieProfessionnelle getCategorieProfessionnelle() {
		return this.categorieProfessionnelle;
	}

	public void setCategorieProfessionnelle(CategorieProfessionnelle categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "poste_superieur")
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "corps")
	public Corps getCorps() {
		return this.corps;
	}

	public void setCorps(Corps corps) {
		this.corps = corps;
	}

	@Column(name = "bonification_indiciaire")
	public Long getBonificationIndiciaire() {
		return this.bonificationIndiciaire;
	}

	public void setBonificationIndiciaire(Long bonificationIndiciaire) {
		this.bonificationIndiciaire = bonificationIndiciaire;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "posteSuperieure")
	public Set<DetailRecrutement> getDetailRecrutements() {
		return this.detailRecrutements;
	}

	public void setDetailRecrutements(Set<DetailRecrutement> detailRecrutements) {
		this.detailRecrutements = detailRecrutements;
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
	public String toString() {
		return "PosteSuperieure [id=" + id + ", categorieProfessionnelle=" + categorieProfessionnelle
				+ ", nomenclature=" + nomenclature + ", corps=" + corps + ", bonificationIndiciaire="
				+ bonificationIndiciaire + ", detailRecrutements=" + detailRecrutements + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonificationIndiciaire == null) ? 0 : bonificationIndiciaire.hashCode());
		result = prime * result + ((categorieProfessionnelle == null) ? 0 : categorieProfessionnelle.hashCode());
		result = prime * result + ((corps == null) ? 0 : corps.hashCode());
		result = prime * result + ((detailRecrutements == null) ? 0 : detailRecrutements.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomenclature == null) ? 0 : nomenclature.hashCode());
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
		PosteSuperieure other = (PosteSuperieure) obj;
		if (bonificationIndiciaire == null) {
			if (other.bonificationIndiciaire != null)
				return false;
		} else if (!bonificationIndiciaire.equals(other.bonificationIndiciaire))
			return false;
		if (categorieProfessionnelle == null) {
			if (other.categorieProfessionnelle != null)
				return false;
		} else if (!categorieProfessionnelle.equals(other.categorieProfessionnelle))
			return false;
		if (corps == null) {
			if (other.corps != null)
				return false;
		} else if (!corps.equals(other.corps))
			return false;
		if (detailRecrutements == null) {
			if (other.detailRecrutements != null)
				return false;
		} else if (!detailRecrutements.equals(other.detailRecrutements))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomenclature == null) {
			if (other.nomenclature != null)
				return false;
		} else if (!nomenclature.equals(other.nomenclature))
			return false;
		return true;
	}

}