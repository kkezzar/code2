package dz.gov.mesrs.sii.commons.data.model.grh;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;

/**
 * 
 * @author BELDI Jamel
 * 
 */
@Entity
@Table(name = "catalogue_formation", schema = "grh")
public class CatalogueFormation implements java.io.Serializable,
		Identifiable<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateCreation;
	private String objet;
	private Boolean statut;
	private Set<FormationCatalogue> formationCatalogues = new HashSet<FormationCatalogue>(
			0);

	public CatalogueFormation() {
	}

	public CatalogueFormation(Integer id) {
		this.id = id;
	}

	public CatalogueFormation(Integer id, Date dateCreation, String objet,
			Boolean statut, Set<FormationCatalogue> formationCatalogues) {
		this.id = id;
		this.dateCreation = dateCreation;
		this.objet = objet;
		this.statut = statut;
		this.formationCatalogues = formationCatalogues;
	}
	
	@Id
	@SequenceGenerator(name = "catalogue_formation_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogue_formation_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_creation", length = 13)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column(name = "objet")
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	@Column(name = "statut")
	public Boolean getStatut() {
		return this.statut;
	}

	public void setStatut(Boolean statut) {
		this.statut = statut;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogueFormation", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<FormationCatalogue> getFormationCatalogues() {
		return this.formationCatalogues;
	}

	public void setFormationCatalogues(
			Set<FormationCatalogue> formationCatalogues) {
		this.formationCatalogues = formationCatalogues;
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

}
