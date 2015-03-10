package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 7 oct. 2014 17:24:06 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:37:58
 */
@Entity
@Table(name = "jour", schema = "nc")
public class Jour implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:38:05
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Boolean estOuvert;
//	private Set<CelluleHoraire> celluleHoraires = new HashSet<CelluleHoraire>(0);

	public Jour() {
	}

	public Jour(int id, String code) {
		this.id = id;
		this.code = code;
	}

	
	@Id
	@SequenceGenerator(name="jour_id_seq_gen", sequenceName="nc.jour_id_seq")
	@GeneratedValue(generator="jour_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "libelle_fr")
	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	@Column(name = "libelle_ar")
	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	@Column(name = "est_ouvert")
	public Boolean getEstOuvert() {
		return this.estOuvert;
	}

	public void setEstOuvert(Boolean estOuvert) {
		this.estOuvert = estOuvert;
	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jour")
//	public Set<CelluleHoraire> getCelluleHoraires() {
//		return this.celluleHoraires;
//	}
//
//	public void setCelluleHoraires(Set<CelluleHoraire> celluleHoraires) {
//		this.celluleHoraires = celluleHoraires;
//	}

}
