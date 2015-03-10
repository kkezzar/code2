package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "axe", schema = "recherche")
public class Axe implements java.io.Serializable , Identifiable<Long>  {
	

	private static final long serialVersionUID = 1L;
	private Long id;
	private Nomenclature axeNomenclature;
	private String description;
	private Programme programme;
		
	public Axe() {
		
	}

	/**
	 * [Axe.id] Getter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="axe_id_seq_gen", sequenceName="recherche.axe_id_seq")
	@GeneratedValue(generator="axe_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Axe.id] Setter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [Axe.axeNomenclature] Getter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @return the axeNomenclature
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_axe", nullable = false)
	public Nomenclature getAxeNomenclature() {
		return axeNomenclature;
	}

	/**
	 * [Axe.axeNomenclature] Setter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @param axeNomenclature the axeNomenclature to set
	 */
	public void setAxeNomenclature(Nomenclature axeNomenclature) {
		this.axeNomenclature = axeNomenclature;
	}

	/**
	 * [Axe.description] Getter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @return the description
	 */
	@Column(name = "description",length=255)
	public String getDescription() {
		return description;
	}

	/**
	 * [Axe.description] Setter 
	 * @author rlaib on : 27 janv. 2015  12:24:11
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [Axe.programme] Getter 
	 * @author rlaib on : 27 janv. 2015  12:37:01
	 * @return the programme
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programme", nullable = false)
	public Programme getProgramme() {
		return programme;
	}

	/**
	 * [Axe.programme] Setter 
	 * @author rlaib on : 27 janv. 2015  12:37:01
	 * @param programme the programme to set
	 */
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}
	

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  12:24:56
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 27 janv. 2015  12:24:56
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}
	
	
}
