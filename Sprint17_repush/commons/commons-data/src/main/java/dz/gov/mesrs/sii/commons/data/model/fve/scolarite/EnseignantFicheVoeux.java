/**
 * [dz.gov.mesrs.sii.fve.business.model.bo.scolarite.EnseignantFicheVoeux.java] 
 * @author rlaib on : 12 oct. 2014  14:03:13
 */
package dz.gov.mesrs.sii.commons.data.model.fve.scolarite;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.ParcoursType;
import dz.gov.mesrs.sii.commons.data.model.grh.DossierEmploye;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;

/**
 * @author rlaib  on : 12 oct. 2014  14:03:13
 */
@Entity
@Table(name = "enseignant_fiche_voeux", schema = "fve_scolarite")
public class EnseignantFicheVoeux implements java.io.Serializable  {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:05:26
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private DossierEmploye enseignant; 
	private AnneeAcademique anneeAcademique;
	private Nomenclature periode; 
	private SituationEntite situation;
	private RefEtablissement refEtablissement;
	private EnseignantFicheServices enseignantFicheServices;
	private Set<EnseignantVoeu> enseignantVoeux = new HashSet<EnseignantVoeu>(0);
	
	public EnseignantFicheVoeux() {
		
	}

	/**
	 * [EnseignantFicheVoeux.id] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the id
	 */
	@SequenceGenerator(name="enseignant_fiche_voeux_id_seq_gen", sequenceName="fve_scolarite.enseignant_fiche_voeux_id_seq")
	@Id @GeneratedValue(generator="enseignant_fiche_voeux_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantFicheVoeux.id] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantFicheVoeux.enseignant] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the enseignant
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enseignant", nullable = false)
	public DossierEmploye getEnseignant() {
		return enseignant;
	}

	/**
	 * [EnseignantFicheVoeux.enseignant] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param enseignant the enseignant to set
	 */
	public void setEnseignant(DossierEmploye enseignant) {
		this.enseignant = enseignant;
	}

	/**
	 * [EnseignantFicheVoeux.anneeAcademique] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the anneeAcademique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeux.anneeAcademique] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [EnseignantFicheVoeux.periode] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the Nomenclature
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_periode", nullable = false)
	public Nomenclature getPeriode() {
		return periode;
	}

	/**
	 * [EnseignantFicheVoeux.periode] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param Nomenclature the periode to set
	 */
	public void setPeriode(Nomenclature periode) {
		this.periode = periode;
	}

	/**
	 * [EnseignantFicheVoeux.situation] Getter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @return the situation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return situation;
	}

	/**
	 * [EnseignantFicheVoeux.situation] Setter 
	 * @author rlaib on : 12 oct. 2014  14:12:13
	 * @param situation the situation to set
	 */
	public void setSituation(SituationEntite situation) {
		this.situation = situation;
	}

	/**
	 * [EnseignantFicheVoeux.refEtablissement] Getter 
	 * @author rlaib on : 18 oct. 2014  10:34:24
	 * @return the refEtablissement
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getRefEtablissement() {
		return refEtablissement;
	}

	/**
	 * [EnseignantFicheVoeux.refEtablissement] Setter 
	 * @author rlaib on : 18 oct. 2014  10:34:24
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setRefEtablissement(RefEtablissement refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	/**
	 * [EnseignantFicheVoeux.enseignantFicheServices] Getter 
	 * @author rlaib on : 26 oct. 2014  10:20:36
	 * @return the enseignantFicheServices
	 */
	@OneToOne(mappedBy = "enseignantFicheVoeux",fetch = FetchType.EAGER)
	public EnseignantFicheServices getEnseignantFicheServices() {
		return enseignantFicheServices;
	}

	/**
	 * [EnseignantFicheVoeux.enseignantFicheServices] Setter 
	 * @author rlaib on : 26 oct. 2014  10:20:36
	 * @param enseignantFicheServices the enseignantFicheServices to set
	 */
	public void setEnseignantFicheServices(
			EnseignantFicheServices enseignantFicheServices) {
		this.enseignantFicheServices = enseignantFicheServices;
	}

	
	/**
	 * [EnseignantFicheVoeux.enseignantVoeux] Getter 
	 * @author Rafik on : 8 déc. 2014  20:08:51
	 * @return the enseignantVoeux
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enseignantFicheVoeux", targetEntity= EnseignantVoeu.class)
	public Set<EnseignantVoeu> getEnseignantVoeux() {
		return enseignantVoeux;
	}

	/**
	 * [EnseignantFicheVoeux.enseignantVoeux] Setter 
	 * @author Rafik on : 8 déc. 2014  20:08:51
	 * @param enseignantVoeux the enseignantVoeux to set
	 */
	public void setEnseignantVoeux(Set<EnseignantVoeu> enseignantVoeux) {
		this.enseignantVoeux = enseignantVoeux;
	}


}
