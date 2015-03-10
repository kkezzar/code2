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

@Entity
@Table(name = "parcours_type_i18n", schema = "lmd")
public class ParcoursTypeI18n implements java.io.Serializable {

	
	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 18 avr. 2014  19:40:09
	 */
	private static final long serialVersionUID = 8171267505847281295L;
	private int id;
	private ParcoursType parcoursType;
	private String langue;
	private String libelle;
	private String libelleCourt;
	
	public ParcoursTypeI18n() {
	}

	@SequenceGenerator(name="parcours_type_i18n_id_seq_gen", sequenceName="lmd.parcours_type_i18n_id_seq")
	@Id @GeneratedValue(generator="parcours_type_i18n_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [ParcoursTypeI18n.parcoursType] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @return the parcoursType
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_parcours_type", nullable = false)
	public ParcoursType getParcoursType() {
		return parcoursType;
	}

	/**
	 * [ParcoursTypeI18n.parcoursType] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @param parcoursType the parcoursType to set
	 */
	public void setParcoursType(ParcoursType parcoursType) {
		this.parcoursType = parcoursType;
	}

	/**
	 * [ParcoursTypeI18n.langue] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @return the langue
	 */
	@Column(name = "langue", nullable = false,length=5)
	public String getLangue() {
		return langue;
	}

	/**
	 * [ParcoursTypeI18n.langue] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [ParcoursTypeI18n.libelle] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @return the libelle
	 */
	@Column(name = "libelle", nullable = false,length=255)
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [ParcoursTypeI18n.libelle] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [ParcoursTypeI18n.libelle_court] Getter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @return the libelle_court
	 */
	@Column(name = "libelle_court",length=100)
	public String getLibelleCourt() {
		return libelleCourt;
	}

	/**
	 * [ParcoursTypeI18n.libelle_court] Setter 
	 * @author  Rafik LAIB on : 18 avr. 2014  19:36:00
	 * @param libelle_court the libelle_court to set
	 */
	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	

}
