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
@Table(name = "examen_medical", schema = "grh")
public class ExamenMedical implements java.io.Serializable, Identifiable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature nomenclature;
	private VisiteMedicale visiteMedicale;
	private Date dateExamen;
	private String resume;

	

	public ExamenMedical() {
	}

	public ExamenMedical(Long id) {
		this.id = id;
	}

	public ExamenMedical(Long id, Nomenclature nomenclature, VisiteMedicale visiteMedicale) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.visiteMedicale = visiteMedicale;
	}

	@Id
	@SequenceGenerator(name = "grh_examen_medical_id_seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grh_examen_medical_id_seq")
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_examen", length = 13)
	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}
	
	@Column(name = "resume")
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
