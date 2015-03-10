package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author rlaib  on : 29 oct. 2014  11:53:19
 */
@Entity
@Table(name = "type_seance", schema = "fve_scolarite")
public class TypeSeance implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:49
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	
	public TypeSeance() {
		
	}

	/**
	 * [TypeSeance.id] Getter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @return the id
	 */
	@Id
	@SequenceGenerator(name="type_seance_id_seq_gen", sequenceName="fve_scolarite.type_seance_id_seq")
	@GeneratedValue(generator="type_seance_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	/**
	 * [TypeSeance.id] Setter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeSeance.code] Getter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @return the code
	 */
	@Column(name = "code", nullable=false)
	public String getCode() {
		return code;
	}

	/**
	 * [TypeSeance.code] Setter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [TypeSeance.libelleFr] Getter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @return the libelleFr
	 */
	@Column(name = "libelle_fr", nullable=false)
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [TypeSeance.libelleFr] Setter 
	 * @author rlaib on : 29 oct. 2014  11:51:00
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}
	
	
}
