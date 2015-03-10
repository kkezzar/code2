package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

// Generated 15 juil. 2014 16:41:51 by Hibernate Tools 3.6.0

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
 * Cycle generated by hbm2java
 */
@Entity
@Table(name = "cycle", schema = "lmd", uniqueConstraints = {
		@UniqueConstraint(columnNames = "libelle_long_lt"),
		@UniqueConstraint(columnNames = "code") })
public class Cycle implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 16 juil. 2014  09:48:21
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelleLongLt;
	private String libelleLongAr;
	private String libelleCourtLt;
	private String libelleCourtAr;
	private int rang;
	private boolean statut;
	private String refCodeTypeFormation;
	private String refCodeTypePassage;
	private Set<Niveau> niveaux = new HashSet<Niveau>(0);

	public Cycle() {
	}

	public Cycle(int id, String code, String libelleLongLt, int rang,
			boolean statut, String refCodeTypeFormation) {
		this.id = id;
		this.code = code;
		this.libelleLongLt = libelleLongLt;
		this.rang = rang;
		this.statut = statut;
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	public Cycle(int id, String code, String libelleLongLt,
			String libelleLongAr, String libelleCourtLt, String libelleCourtAr,
			int rang, boolean statut, String refCodeTypeFormation,
			String refCodeTypePassage, Set<Niveau> niveaus) {
		this.id = id;
		this.code = code;
		this.libelleLongLt = libelleLongLt;
		this.libelleLongAr = libelleLongAr;
		this.libelleCourtLt = libelleCourtLt;
		this.libelleCourtAr = libelleCourtAr;
		this.rang = rang;
		this.statut = statut;
		this.refCodeTypeFormation = refCodeTypeFormation;
		this.refCodeTypePassage = refCodeTypePassage;
		this.niveaux = niveaus;
	}

	@SequenceGenerator(name="cycle_id_seq_gen", sequenceName="lmd.cycle_id_seq")
	@Id @GeneratedValue(generator="cycle_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 2)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "libelle_long_lt", unique = true, nullable = false, length = 150)
	public String getLibelleLongLt() {
		return this.libelleLongLt;
	}

	public void setLibelleLongLt(String libelleLongLt) {
		this.libelleLongLt = libelleLongLt;
	}

	@Column(name = "libelle_long_ar", length = 150)
	public String getLibelleLongAr() {
		return this.libelleLongAr;
	}

	public void setLibelleLongAr(String libelleLongAr) {
		this.libelleLongAr = libelleLongAr;
	}

	@Column(name = "libelle_court_lt", length = 50)
	public String getLibelleCourtLt() {
		return this.libelleCourtLt;
	}

	public void setLibelleCourtLt(String libelleCourtLt) {
		this.libelleCourtLt = libelleCourtLt;
	}

	@Column(name = "libelle_court_ar", length = 50)
	public String getLibelleCourtAr() {
		return this.libelleCourtAr;
	}

	public void setLibelleCourtAr(String libelleCourtAr) {
		this.libelleCourtAr = libelleCourtAr;
	}

	@Column(name = "rang", nullable = false)
	public int getRang() {
		return this.rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	@Column(name = "statut", nullable = false)
	public boolean isStatut() {
		return this.statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	@Column(name = "ref_code_type_formation", nullable = false, length = 10)
	public String getRefCodeTypeFormation() {
		return this.refCodeTypeFormation;
	}

	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	@Column(name = "ref_code_type_passage", length = 10)
	public String getRefCodeTypePassage() {
		return this.refCodeTypePassage;
	}

	public void setRefCodeTypePassage(String refCodeTypePassage) {
		this.refCodeTypePassage = refCodeTypePassage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cycle")
	public Set<Niveau> getNiveaux() {
		return this.niveaux;
	}

	public void setNiveaux(Set<Niveau> niveaux) {
		this.niveaux = niveaux;
	}

}
