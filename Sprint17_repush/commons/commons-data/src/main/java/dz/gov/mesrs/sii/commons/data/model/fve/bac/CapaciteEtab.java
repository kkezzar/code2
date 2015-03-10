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
 * The persistent class for the capacite_etab database table.
 * 
 */
@Entity
@Table(name = "capacite_etab", schema = "bac")
@NamedQuery(name = "CapaciteEtab.findAll", query = "SELECT c FROM CapaciteEtab c")
public class CapaciteEtab implements Serializable {
	private static final long serialVersionUID = 1L;


	@SequenceGenerator(name = "capacite_etab_id_seq", sequenceName = "bac.capacite_etab_id_seq", initialValue = 1)
	@Id @GeneratedValue(generator = "capacite_etab_id_seq")
	private int id;

	@Column(name = "capacite_accueil")
	private Integer capaciteAccueil;

	@Column(name = "capacite_transfert")
	private Integer capaciteTransfert;

	//bi-directional many-to-one association to AccessWilaya
	//--ManyToOne
	 @ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="id_annee_academique")
	private AnneeAcademique anneeAcademique;

	@Column(name = "ref_code_domaine")
	private String refCodeDomaine;

	@Column(name = "ref_code_etablissement")
	private String refCodeEtablissement;

	@Column(name = "ref_code_filiere")
	private String refCodeFiliere;

	// bi-directional many-to-one association to PrioriteSerieBac
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "capaciteEtab")
	private List<PrioriteSerieBac> prioriteSerieBac;

	public CapaciteEtab() {
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

	public List<PrioriteSerieBac> getPrioriteSerieBac() {
		return prioriteSerieBac;
	}

	public void setPrioriteSerieBac(List<PrioriteSerieBac> prioriteSerieBac) {
		this.prioriteSerieBac = prioriteSerieBac;
	}

	public Integer getCapaciteAccueil() {
		return this.capaciteAccueil;
	}

	public void setCapaciteAccueil(Integer capaciteAccueil) {
		this.capaciteAccueil = capaciteAccueil;
	}

	public Integer getCapaciteTransfert() {
		return this.capaciteTransfert;
	}

	public void setCapaciteTransfert(Integer capaciteTransfert) {
		this.capaciteTransfert = capaciteTransfert;
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

}