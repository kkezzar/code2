package dz.gov.mesrs.sii.commons.data.model.referentiel;

// Generated 19 mars 2014 11:15:09 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * RefEntity generated by hbm2java
 */
@Entity
@Table(name = "ref_entity", schema = "ppm")
public class RefEntity implements java.io.Serializable {

	private int id;
	private RefDomaine refDomaine;
	private String name;
	private Set<RefFileConfig> refFileConfigs = new HashSet<RefFileConfig>(0);

	public RefEntity() {
	}

	public RefEntity(int id, RefDomaine refDomaine, String name) {
		this.id = id;
		this.refDomaine = refDomaine;
		this.name = name;
	}

	public RefEntity(int id, RefDomaine refDomaine, String name,
			Set<RefFileConfig> refFileConfigs) {
		this.id = id;
		this.refDomaine = refDomaine;
		this.name = name;
		this.refFileConfigs = refFileConfigs;
	}

	@Id
	@SequenceGenerator(name="seq_entity", initialValue = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_entity")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "domain", nullable = false)
	public RefDomaine getRefDomaine() {
		return this.refDomaine;
	}

	public void setRefDomaine(RefDomaine refDomaine) {
		this.refDomaine = refDomaine;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "refEntity")
	public Set<RefFileConfig> getRefFileConfigs() {
		return this.refFileConfigs;
	}

	public void setRefFileConfigs(Set<RefFileConfig> refFileConfigs) {
		this.refFileConfigs = refFileConfigs;
	}

}
