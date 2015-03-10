package dz.gov.mesrs.sii.commons.data.model.grh;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "categorie_niveau", schema = "grh")
public class CategorieNiveau implements Serializable {

	private static final long serialVersionUID = -1641404580520801996L;

	private Long id;
	private Nomenclature niveau;
	private CategorieProfessionnelle categorieProfessionnelle;

	public CategorieNiveau() {
		super();
	}

	@Id
	@SequenceGenerator(name = "grade_categorie_niveau_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_categorie_niveau_seq")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "nc_niveau")
	public Nomenclature getNiveau() {
		return niveau;
	}

	@ManyToOne
	@JoinColumn(name = "categorie_professionnelle")
	public CategorieProfessionnelle getCategorieProfessionnelle() {
		return categorieProfessionnelle;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setNiveau(Nomenclature niveau) {
		this.niveau = niveau;
	}

	public void setCategorieProfessionnelle(CategorieProfessionnelle categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorieProfessionnelle == null) ? 0 : categorieProfessionnelle.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
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
		CategorieNiveau other = (CategorieNiveau) obj;
		if (categorieProfessionnelle == null) {
			if (other.categorieProfessionnelle != null)
				return false;
		} else if (!categorieProfessionnelle.equals(other.categorieProfessionnelle))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategorieNiveau [id=" + id + ", niveau=" + niveau + ", categorieProfessionnelle="
				+ categorieProfessionnelle.getId() + "]";
	}

}
