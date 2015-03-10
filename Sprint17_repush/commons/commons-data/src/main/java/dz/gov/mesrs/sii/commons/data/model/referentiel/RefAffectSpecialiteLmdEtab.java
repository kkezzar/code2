package dz.gov.mesrs.sii.commons.data.model.referentiel;

// créated 25 08 2014. 12:05:59 by OualidBELBRIK 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RefAffectSpecialiteLmdEtab generated by hbm2java
 */
@Entity
@Table(name = "ref_affect_specialite_lmd_etab", schema = "ppm")
public class RefAffectSpecialiteLmdEtab implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELBRIK Oualid  on : 20 08. 2014  11:12:56
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private RefEtablissement refEtablissement;
	private RefSpecialiteLmd refSpecialiteLmd;
	private Date date;
	private String observation;
	private RefGroupe refGroupe;

	public RefAffectSpecialiteLmdEtab() {
	}

	public RefAffectSpecialiteLmdEtab(int id, RefEtablissement refEtablissement,
			RefSpecialiteLmd refSpecialiteLmd, Date date, RefGroupe refGroupe) {
		this.id = id;
		this.refEtablissement = refEtablissement;
		this.refSpecialiteLmd = refSpecialiteLmd;
		this.date = date;
		this.refGroupe = refGroupe;
		
	}

	public RefAffectSpecialiteLmdEtab(int id, RefEtablissement refEtablissement,
			RefSpecialiteLmd refSpecialiteLmd, Date date, String observation) {
		this.id = id;
		this.refEtablissement = refEtablissement;
		this.refSpecialiteLmd = refSpecialiteLmd;
		this.date = date;
		this.observation = observation;
	}

	
		@Id
	@Column(name = "id", unique = true, nullable = false)
	@SequenceGenerator(name = "seq_affect_specialite_lmd_etab", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_affect_specialite_lmd_etab")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specialitelmd", nullable = false)
	public RefSpecialiteLmd getRefSpecialiteLmd() {
		return this.refSpecialiteLmd;
	}

	public void setRefSpecialiteLmd(RefSpecialiteLmd refSpecialiteLmd) {
		this.refSpecialiteLmd = refSpecialiteLmd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 29)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupe")
	public RefGroupe getRefGroupe() {
		return this.refGroupe;
	}

	public void setRefGroupe(RefGroupe refGroupe) {
		this.refGroupe = refGroupe;
	}

}