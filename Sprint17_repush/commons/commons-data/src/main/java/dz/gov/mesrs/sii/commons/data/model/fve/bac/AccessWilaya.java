package dz.gov.mesrs.sii.commons.data.model.fve.bac;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;

/**
 * The persistent class for the access_wilaya database table.
 * 
 */
@Entity
@Table(name = "access_wilaya", schema = "bac")
@NamedQuery(name = "AccessWilaya.findAll", query = "SELECT a FROM AccessWilaya a")
public class AccessWilaya implements Serializable {
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "access_wilaya_id_seq", sequenceName = "bac.access_wilaya_id_seq", initialValue = 1)
	@Id
	@GeneratedValue(generator = "access_wilaya_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	// bi-directional many-to-one association to AccessWilaya
	// --ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique")
	private AnneeAcademique anneeAcademique;

	@Column(name = "ref_code_domaine")
	private String refCodeDomaine;

	@Column(name = "ref_code_etablissement")
	private String refCodeEtablissement;

	@Column(name = "ref_code_filiere")
	private String refCodeFiliere;

	// bi-directional many-to-one association to DroitAccessWilaya
	@OneToMany(mappedBy = "accessWilaya", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DroitAccessWilaya> droitAccessWilaya;

	public AccessWilaya() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	public String getRefCodeDomaine() {
		return this.refCodeDomaine;
	}

	public void setRefCodeDomaine(String refCodeDomaine) {
		this.refCodeDomaine = refCodeDomaine;
	}

	public String getRefCodeEtablissement() {
		return this.refCodeEtablissement;
	}

	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	public String getRefCodeFiliere() {
		return this.refCodeFiliere;
	}

	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	public List<DroitAccessWilaya> getDroitAccessWilaya() {
		return droitAccessWilaya;
	}

	public void setDroitAccessWilaya(List<DroitAccessWilaya> droitAccessWilaya) {
		this.droitAccessWilaya = droitAccessWilaya;
	}

	public DroitAccessWilaya addDroitAccessWilaya(DroitAccessWilaya droitAccessWilaya) {
		getDroitAccessWilaya().add(droitAccessWilaya);
		droitAccessWilaya.setAccessWilaya(this);

		return droitAccessWilaya;
	}

	public DroitAccessWilaya removeDroitAccessWilaya(DroitAccessWilaya droitAccessWilaya) {
		getDroitAccessWilaya().remove(droitAccessWilaya);
		droitAccessWilaya.setAccessWilaya(null);

		return droitAccessWilaya;
	}

}