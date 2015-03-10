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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefGroupe;


/**
 * @author rlaib  on : 15 d�c. 2014  15:33:48
 */
@Entity
@Table(name = "groupe", schema = "recherche")
public class Groupe implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RefGroupe refGroupe;
	private Structure structure;
	
		
	public Groupe() {
		
	}
	
	/**
	 * [Groupe.Groupe()] Constructor
	 * @author rlaib  on : 15 d�c. 2014  15:54:27
	 * @param refGroupe
	 * @param structure	
	 */
	public Groupe(RefGroupe refGroupe, Structure structure) {
		super();
		this.refGroupe = refGroupe;
		this.structure = structure;
	}
	


	/**
	 * [Groupe.id] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="groupe_id_seq_gen", sequenceName="recherche.groupe_id_seq")
	@GeneratedValue(generator="groupe_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Groupe.id] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * [Groupe.refGroupe] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:53:23
	 * @return the refGroupe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ref_groupe", nullable = false)
	public RefGroupe getRefGroupe() {
		return refGroupe;
	}
	

	/**
	 * [Groupe.refGroupe] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:53:23
	 * @param refGroupe the refGroupe to set
	 */
	public void setRefGroupe(RefGroupe refGroupe) {
		this.refGroupe = refGroupe;
	}
	

	/**
	 * [Groupe.structure] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:53:23
	 * @return the structure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public Structure getStructure() {
		return structure;
	}
	

	/**
	 * [Groupe.structure] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:53:23
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	
	
	
	
	
	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}
	

	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * @author Rafik  on : 17 déc. 2014  20:37:37
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
