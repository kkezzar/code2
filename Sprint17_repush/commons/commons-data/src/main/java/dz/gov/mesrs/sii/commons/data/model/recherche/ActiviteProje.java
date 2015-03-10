package dz.gov.mesrs.sii.commons.data.model.recherche;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;


/**
 * @author slimane ramdane   on : 02 fev. 2015 15:33:48
 */
@Entity
@Table(name = "activite_proje", schema = "recherche")
public class ActiviteProje implements java.io.Serializable , Identifiable<Long>  {
	

	/**
	 * serialVersionUID 
	 * @author slimane ramdane  on : 01 fev. 2015 15:30:03
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	public ActiviteProje() {
		
	}

	
	@Id
	@SequenceGenerator(name="activite_proje_id_seq_gen", sequenceName="recherche.activite_proje_id_seq")
	@GeneratedValue(generator="activite_proje_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	

	@Transient
	@Override
	public Long getIdenfiant() {
		return this.getId();
	}

	


	/**
	 * [Identifiable<Integer>.getIdentifiantName] Method 
	 * Overridden By : @author sram  on : 02 fev. 2015  11:02:27
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	
	
}
