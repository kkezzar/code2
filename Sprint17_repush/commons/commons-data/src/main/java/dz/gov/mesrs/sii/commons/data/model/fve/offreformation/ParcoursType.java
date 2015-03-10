package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parcours_type", schema = "lmd")
public class ParcoursType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private OffreFormation offreFormation;
	private String code;
	private Map<String,ParcoursTypeI18n> i18n = new HashMap<String, ParcoursTypeI18n>();
	private Set<RepartitionUe> repartitionUes = new HashSet<RepartitionUe>(0);
	
	public ParcoursType() {
	}

	@SequenceGenerator(name="parcours_type_id_seq_gen", sequenceName="lmd.parcours_type_id_seq")
	@Id @GeneratedValue(generator="parcours_type_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_offre_formation", nullable = false)
	public OffreFormation getOffreFormation() {
		return this.offreFormation;
	}

	public void setOffreFormation(OffreFormation offreFormation) {
		this.offreFormation = offreFormation;
	}

	@Column(name = "code", nullable = false, length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parcoursType", targetEntity= ParcoursTypeI18n.class)
	@MapKey(name="langue")
	public Map<String,ParcoursTypeI18n> getI18n() {
		return i18n;
	}

	public void setI18n(Map<String,ParcoursTypeI18n> i18n) {
		this.i18n = i18n;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcoursType")
	public Set<RepartitionUe> getRepartitionUes() {
		return repartitionUes;
	}

	public void setRepartitionUes(Set<RepartitionUe> repartitionUes) {
		this.repartitionUes = repartitionUes;
	}


}
