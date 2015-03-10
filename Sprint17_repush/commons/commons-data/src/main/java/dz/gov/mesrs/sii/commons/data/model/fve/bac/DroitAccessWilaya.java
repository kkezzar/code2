package dz.gov.mesrs.sii.commons.data.model.fve.bac;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the droit_access_wilaya database table.
 * 
 */
@Entity
@Table(name="droit_access_wilaya", schema = "bac")
@NamedQuery(name="DroitAccessWilaya.findAll", query="SELECT d FROM DroitAccessWilaya d")
public class DroitAccessWilaya implements Serializable {
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "droit_access_wilaya_id_seq", sequenceName = "bac.droit_access_wilaya_id_seq", initialValue = 1)
	@Id @GeneratedValue(generator = "droit_access_wilaya_id_seq")
	private int id;

	@Column(name="ref_code_wilaya")
	private String refCodeWilaya;

	//bi-directional many-to-one association to AccessWilaya
	//--ManyToOne
	 @ManyToOne(cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
	@JoinColumn(name="id_access_wilaya")
	private AccessWilaya accessWilaya;

	public DroitAccessWilaya() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefCodeWilaya() {
		return this.refCodeWilaya;
	}

	public void setRefCodeWilaya(String refCodeWilaya) {
		this.refCodeWilaya = refCodeWilaya;
	}

	public AccessWilaya getAccessWilaya() {
		return this.accessWilaya;
	}

	public void setAccessWilaya(AccessWilaya accessWilaya) {
		this.accessWilaya = accessWilaya;
	}

}