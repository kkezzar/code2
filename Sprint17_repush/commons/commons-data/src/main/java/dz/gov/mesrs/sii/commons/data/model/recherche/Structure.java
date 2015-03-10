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
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;


/**
 * @author rlaib  on : 15 d�c. 2014  15:33:48
 */
@Entity
@Table(name = "structure", schema = "recherche")
public class Structure implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 15 d�c. 2014  15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RefStructure refStructure;
		
	public Structure() {
		
	}

	/**
	 * [Structure.id] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="structure_id_seq_gen", sequenceName="recherche.structure_id_seq")
	@GeneratedValue(generator="structure_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * [Structure.id] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:30:11
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [Structure.refStructure] Getter 
	 * @author rlaib on : 15 d�c. 2014  15:44:38
	 * @return the refStructure
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ref_structure", nullable = false)
	public RefStructure getRefStructure() {
		return refStructure;
	}

	/**
	 * [Structure.refStructure] Setter 
	 * @author rlaib on : 15 d�c. 2014  15:44:38
	 * @param refStructure the refStructure to set
	 */
	public void setRefStructure(RefStructure refStructure) {
		this.refStructure = refStructure;
	}

	/**
	 * [Identifiable<Integer>.getIdenfiant] Method 
	 * @author Rafik  on : 17 déc. 2014  20:37:37
	 * @return
	 */
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
