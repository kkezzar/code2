package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 20 mai 2014 17:49:05 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Cycle;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefDomaineLMD;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefFiliereLmd;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefSpecialiteLmd;

/**
 * 
 */
@Entity
@Table(name = "voeu_etudiant", schema = "cursus")
public class VoeuEtudiant implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MESSAOUDI Mounir on : 28 septembre 2014 09:00:31
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	private DossierEtudiant dossierEtudiant;
	private AnneeAcademique anneeAcademique;

	private Nomenclature typeReinscription;

	private List<VoeuEtudiantChoix> voeuxEtudiantsChoix;

	private OuvertureOffreFormation ouvertureOffreFormation;
	private RefDomaineLMD domaine;
	private RefFiliereLmd filiere;
	private RefSpecialiteLmd specialite;

	private DossierInscriptionAdministrative ancienDossierInsAdmin;
	private DossierInscriptionAdministrative nouveauDossierInsAdmin;

	private RefEtablissement etablissement;

	private Niveau niveau;
	private Cycle cycle;

	private String observationEtudiant;
	private String observationEquipePedagogique;
	private String observationReinscription;

	private Date dateCreation;
	private SituationEntite situationEntite;

	public VoeuEtudiant() {
	}

	public VoeuEtudiant(int id) {
		this.id = id;
	}

	@Id
	@SequenceGenerator(name = "voeu_etudiant_id_seq", sequenceName = "cursus.voeu_etudiant_id_seq", initialValue = 1)
	@GeneratedValue(generator = "voeu_etudiant_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation")
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_etudiant")
	public DossierEtudiant getDossierEtudiant() {
		return dossierEtudiant;
	}

	public void setDossierEtudiant(DossierEtudiant dossierEtudiant) {
		this.dossierEtudiant = dossierEtudiant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique")
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type_reinscription")
	public Nomenclature getTypeReinscription() {
		return typeReinscription;
	}

	public void setTypeReinscription(Nomenclature typeReinscription) {
		this.typeReinscription = typeReinscription;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_ouverture_of")
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_domaine")
	public RefDomaineLMD getDomaine() {
		return domaine;
	}

	public void setDomaine(RefDomaineLMD domaine) {
		this.domaine = domaine;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_filiere")
	public RefFiliereLmd getFiliere() {
		return filiere;
	}

	public void setFiliere(RefFiliereLmd filiere) {
		this.filiere = filiere;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_specialite")
	public RefSpecialiteLmd getSpecialite() {
		return specialite;
	}

	public void setSpecialite(RefSpecialiteLmd specialite) {
		this.specialite = specialite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ancien_inscription_administrative", nullable = false)
	public DossierInscriptionAdministrative getAncienDossierInsAdmin() {
		return ancienDossierInsAdmin;
	}

	public void setAncienDossierInsAdmin(
			DossierInscriptionAdministrative ancienDossierInsAdmin) {
		this.ancienDossierInsAdmin = ancienDossierInsAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nouveau_inscription_administrative")
	public DossierInscriptionAdministrative getNouveauDossierInsAdmin() {
		return nouveauDossierInsAdmin;
	}

	public void setNouveauDossierInsAdmin(
			DossierInscriptionAdministrative nouveauDossierInsAdmin) {
		this.nouveauDossierInsAdmin = nouveauDossierInsAdmin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement")
	public RefEtablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(RefEtablissement etablissement) {
		this.etablissement = etablissement;
	}

	public void setVoeuxEtudiantsChoix(
			List<VoeuEtudiantChoix> voeuxEtudiantsChoix) {
		this.voeuxEtudiantsChoix = voeuxEtudiantsChoix;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_niveau")
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cycle")
	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	@OneToMany(mappedBy = "voeuEtudiant", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
	public List<VoeuEtudiantChoix> getVoeuxEtudiantsChoix() {
		return voeuxEtudiantsChoix;
	}

	public void setVoeuxEtudiantsChoix(ArrayList<VoeuEtudiantChoix> arrayList) {
		this.voeuxEtudiantsChoix = arrayList;
	}

	@Column(name = "observation_etudiant")
	public String getObservationEtudiant() {
		return observationEtudiant;
	}

	public void setObservationEtudiant(String observationEtudiant) {
		this.observationEtudiant = observationEtudiant;
	}

	@Column(name = "observation_equipe_pedagogique")
	public String getObservationEquipePedagogique() {
		return observationEquipePedagogique;
	}

	public void setObservationEquipePedagogique(
			String observationEquipePedagogique) {
		this.observationEquipePedagogique = observationEquipePedagogique;
	}

	@Column(name = "observation_reinscription")
	public String getObservationReinscription() {
		return observationReinscription;
	}

	public void setObservationReinscription(String observationReinscription) {
		this.observationReinscription = observationReinscription;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situation")
	public SituationEntite getSituationEntite() {
		return situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	public VoeuEtudiantChoix addVoeuxEtudiantsChoix(
			VoeuEtudiantChoix voeuEtudiantChoix) {
		getVoeuxEtudiantsChoix().add(voeuEtudiantChoix);
		voeuEtudiantChoix.setVoeuEtudiant(this);

		return voeuEtudiantChoix;
	}

	public VoeuEtudiantChoix removeVoeuxEtudiantsChoix(
			VoeuEtudiantChoix voeuEtudiantsChoix) {
		getVoeuxEtudiantsChoix().remove(voeuEtudiantsChoix);
		voeuEtudiantsChoix.setVoeuEtudiant(null);

		return voeuEtudiantsChoix;
	}
}