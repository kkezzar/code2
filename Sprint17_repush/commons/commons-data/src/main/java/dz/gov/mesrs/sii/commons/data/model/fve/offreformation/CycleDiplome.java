package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

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

/**
 * @author rlaib  on : 24 nov. 2014  11:21:49
 */
@Entity
@Table(name = "cycle_diplome", schema = "lmd")
public class CycleDiplome implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 24 nov. 2014  11:18:47
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Cycle cycle;
	private Nomenclature diplome;
	private String sens;
	private Integer rang;

	public CycleDiplome() {
	}

	/**
	 * [CycleDiplome.id] Getter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @return the id
	 */
	@SequenceGenerator(name="cycle_diplome_id_seq_gen", sequenceName="lmd.cycle_diplome_id_seq")
	@Id @GeneratedValue(generator="cycle_diplome_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [CycleDiplome.id] Setter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [CycleDiplome.cycle] Getter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @return the cycle
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cycle", nullable = false)
	public Cycle getCycle() {
		return cycle;
	}

	/**
	 * [CycleDiplome.cycle] Setter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @param cycle the cycle to set
	 */
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	/**
	 * [CycleDiplome.diplome] Getter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @return the diplome
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diplome", nullable = false)
	public Nomenclature getDiplome() {
		return diplome;
	}

	/**
	 * [CycleDiplome.diplome] Setter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @param diplome the diplome to set
	 */
	public void setDiplome(Nomenclature diplome) {
		this.diplome = diplome;
	}

	/**
	 * [CycleDiplome.sens] Getter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @return the sens
	 */
	@Column(name = "sens", nullable = false, length = 3)
	public String getSens() {
		return sens;
	}

	/**
	 * [CycleDiplome.sens] Setter 
	 * @author rlaib on : 24 nov. 2014  11:18:54
	 * @param sens the sens to set
	 */
	public void setSens(String sens) {
		this.sens = sens;
	}

	/**
	 * [CycleDiplome.getIdenfiant] Method 
	 * @author rlaib  on : 24 nov. 2014  11:27:08
	 * @return
	 */
	@Transient
	@Override
	public Integer getIdenfiant() {
		return getId();
	}

	/**
	 * [CycleDiplome.getIdentifiantName] Method 
	 * @author rlaib  on : 24 nov. 2014  11:27:08
	 * @return
	 */
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

	/**
	 * [CycleDiplome.rang] Getter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:22:01
	 * @return the rang
	 */
	@Column(name = "rang")
	public Integer getRang() {
		return rang;
	}

	/**
	 * [CycleDiplome.rang] Setter 
	 * @author MAKERRI Sofiane on : 23 févr. 2015  15:22:01
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}

	

}
