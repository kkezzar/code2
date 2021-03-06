package dz.gov.mesrs.sii.commons.data.model.fve.bac;

// Generated 22 mai 2014 12:17:42 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TypeImportation generated by hbm2java
 */
@Entity
@Table(name = "type_importation", schema = "bac", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class TypeImportation implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 22 mai 2014  12:19:54
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleFr;
	private String libelleAr;
	private Set<Importation> importations = new HashSet<Importation>(0);

	public TypeImportation() {
	}

	public TypeImportation(int id, String code, String libelleFr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
	}

	public TypeImportation(int id, String code, String libelleFr,
			String libelleAr, Set<Importation> importations) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
		this.libelleAr = libelleAr;
		this.importations = importations;
	}

	@SequenceGenerator(name="type_importation_id_seq_gen", sequenceName="bac.type_importation_id_seq")
	@Id @GeneratedValue(generator="type_importation_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 4)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "libelle_fr", nullable = false, length = 100)
	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	@Column(name = "libelle_ar", length = 100)
	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeImportation")
	public Set<Importation> getImportations() {
		return this.importations;
	}

	public void setImportations(Set<Importation> importations) {
		this.importations = importations;
	}

}
