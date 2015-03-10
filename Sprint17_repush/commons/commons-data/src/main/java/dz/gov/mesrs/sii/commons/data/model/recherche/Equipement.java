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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEquipement;


/**
 * @author rlaib  on : 15 déc. 2014  15:33:48
 */
@Entity
@Table(name = "equipement", schema = "recherche")
public class Equipement implements java.io.Serializable  , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 déc. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Structure structure;
	private RefEquipement refEquipement;
	private double tauxUtilisation;
		
	public Equipement() {
		
	}

	/**
	 * [Equipement.Equipement()] Constructor
	 * @author rlaib  on : 15 déc. 2014  16:12:37
	 * @param structure
	 * @param refEquipement
	 * @param tauxUtilisation	
	 */
	public Equipement(Structure structure, RefEquipement refEquipement,
			double tauxUtilisation) {
		super();
		this.structure = structure;
		this.refEquipement = refEquipement;
		this.tauxUtilisation = tauxUtilisation;
	}
	



	/**
	 * [Equipement.id] Getter 
	 * @author rlaib on : 15 déc. 2014  15:30:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="equipement_id_seq_gen", sequenceName="recherche.equipement_id_seq")
	@GeneratedValue(generator="equipement_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Equipement.id] Setter 
	 * @author rlaib on : 15 déc. 2014  15:30:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * [Equipement.structure] Getter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @return the structure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public Structure getStructure() {
		return structure;
	}
	

	/**
	 * [Equipement.structure] Setter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	

	/**
	 * [Equipement.refEquipement] Getter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @return the refEquipement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ref_equipement", nullable = false)
	public RefEquipement getRefEquipement() {
		return refEquipement;
	}
	

	/**
	 * [Equipement.refEquipement] Setter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @param refEquipement the refEquipement to set
	 */
	public void setRefEquipement(RefEquipement refEquipement) {
		this.refEquipement = refEquipement;
	}
	

	/**
	 * [Equipement.tauxUtilisation] Getter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @return the tauxUtilisation
	 */
	@Column(name = "taux_utilisation")
	public double getTauxUtilisation() {
		return tauxUtilisation;
	}
	

	/**
	 * [Equipement.tauxUtilisation] Setter 
	 * @author rlaib on : 15 déc. 2014  16:09:02
	 * @param tauxUtilisation the tauxUtilisation to set
	 */
	public void setTauxUtilisation(double tauxUtilisation) {
		this.tauxUtilisation = tauxUtilisation;
	}

	/**
	 * [Identifiable<Long>.getIdenfiant] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  13:08:33
	 * @return
	 */
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	/**
	 * [Identifiable<Long>.getIdentifiantName] Method 
	 * Overridden By : @author rlaib  on : 1 févr. 2015  13:08:33
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
