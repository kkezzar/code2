package dz.gov.mesrs.sii.commons.data.model.grh;



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

/**
 * 
 * @author BELDI Jamel
 *
 */
@Entity
@Table(name = "medicament", schema = "grh")
public class Medicament implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature nomenclature;
	private VisiteMedicale visiteMedicale;
	private String posologie;
	private Integer nbJour;

	public Medicament() {
	}

	public Medicament(Long id) {
		this.id = id;
	}

	public Medicament(Long id, Nomenclature nomenclature, VisiteMedicale visiteMedicale, String posologie, Integer nbJour) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.visiteMedicale = visiteMedicale;
		this.posologie = posologie;
		this.nbJour = nbJour;
	}

	@Id
	@SequenceGenerator(name = "grh_medicament_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_medicament_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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
	@JoinColumn(name = "visite_medicale")
	public VisiteMedicale getVisiteMedicale() {
		return this.visiteMedicale;
	}

	public void setVisiteMedicale(VisiteMedicale visiteMedicale) {
		this.visiteMedicale = visiteMedicale;
	}

	@Column(name = "posologie")
	public String getPosologie() {
		return this.posologie;
	}

	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}

	@Column(name = "nb_jour")
	public Integer getNbJour() {
		return this.nbJour;
	}

	public void setNbJour(Integer nbJour) {
		this.nbJour = nbJour;
	}

	
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	

	
	
	
}
