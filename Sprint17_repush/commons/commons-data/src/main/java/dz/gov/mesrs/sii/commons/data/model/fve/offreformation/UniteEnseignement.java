package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "ue", schema = "lmd")
public class UniteEnseignement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private String libelleFr;
	private String abreviationFr;
	private String libelleAr;
	private String abreviationAr;
	//private String refCodeTypeUe;
	//private String refCodeNatureUe;
	private String refCodeStatutUe;
	//private String refCodeCaractereUe;
	private Integer credits;
	private String refCodeAppreciation;
	private Double volumeHorairePerso;
	private Double volumeHorairePres;
	private Double volumeHoraireHebdo;
	private Date dateCreation;
	private Date dateModification;
	private Date dateDebutValidite;
	private Date dateFinValidite;
	private Boolean estValide;
	private Boolean estPubliee;
	private Boolean estOuverte;
	private Boolean estArchivee;
	private Boolean estSupprimee;
	private String objectifs;
	private String prerequis;
	private String contenu;
	private String competences;
	private String recommandations;
	private String organisation;
	private String evaluation;
	private String aidesEtudiant;
	private String admission;
	private String publicCible;
	private String observation;
	private Boolean libre;
	private Nomenclature ncNatureUe;
	private Nomenclature ncCaractereUe;
	private Nomenclature ncAppreciationUe;

	private Set<RattachementMc> rattachementMcs = new HashSet<RattachementMc>(0);

	private Set<RepartitionUe> repartitionUes = new HashSet<RepartitionUe>(
			0);
//	private Set<GroupePedagogique> groupePedagogiques = new HashSet<GroupePedagogique>(0);

	
	public UniteEnseignement() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "libelle_fr", nullable = false, length = 150)
	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	@Column(name = "abreviation_fr", length = 120)
	public String getAbreviationFr() {
		return this.abreviationFr;
	}

	public void setAbreviationFr(String abreviationFr) {
		this.abreviationFr = abreviationFr;
	}

	@Column(name = "libelle_ar", length = 150)
	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	@Column(name = "abreviation_ar", length = 120)
	public String getAbreviationAr() {
		return this.abreviationAr;
	}

	public void setAbreviationAr(String abreviationAr) {
		this.abreviationAr = abreviationAr;
	}

//	@Column(name = "ref_code_type_ue", length = 50)
//	public String getRefCodeTypeUe() {
//		return this.refCodeTypeUe;
//	}
//
//	public void setRefCodeTypeUe(String refCodeTypeUe) {
//		this.refCodeTypeUe = refCodeTypeUe;
//	}
//	
//	@Column(name = "ref_code_caractere_ue", length = 50)
//	public String getRefCodeCaractereUe() {
//		return this.refCodeCaractereUe;
//	}
//
//	public void setRefCodeCaractereUe(String refCodeCaractereUe) {
//		this.refCodeCaractereUe = refCodeCaractereUe;
//	}

//	@Column(name = "ref_code_nature_ue", length = 50)
//	public String getRefCodeNatureUe() {
//		return this.refCodeNatureUe;
//	}
//
//	public void setRefCodeNatureUe(String refCodeNatureUe) {
//		this.refCodeNatureUe = refCodeNatureUe;
//	}
	
	@Column(name = "ref_code_statut_ue", length = 50)
	public String getRefCodeStatutUe() {
		return this.refCodeStatutUe;
	}

	public void setRefCodeStatutUe(String refCodeStatutUe) {
		this.refCodeStatutUe = refCodeStatutUe;
	}

	@Column(name = "credits")
	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@Column(name = "ref_code_appreciation", length = 50)
	public String getRefCodeAppreciation() {
		return this.refCodeAppreciation;
	}

	public void setRefCodeAppreciation(String refCodeAppreciation) {
		this.refCodeAppreciation = refCodeAppreciation;
	}

	@Column(name = "volume_horaire_perso", precision = 17, scale = 17)
	public Double getVolumeHorairePerso() {
		return this.volumeHorairePerso;
	}

	public void setVolumeHorairePerso(Double volumeHorairePerso) {
		this.volumeHorairePerso = volumeHorairePerso;
	}

	@Column(name = "volume_horaire_pres", precision = 17, scale = 17)
	public Double getVolumeHorairePres() {
		return this.volumeHorairePres;
	}

	public void setVolumeHorairePres(Double volumeHorairePres) {
		this.volumeHorairePres = volumeHorairePres;
	}
	
	@Column(name = "volume_horaire_hebdo", precision = 17, scale = 17)
	public Double getVolumeHoraireHebdo() {
		return this.volumeHoraireHebdo;
	}

	public void setVolumeHoraireHebdo(Double volumeHoraireHebdo) {
		this.volumeHoraireHebdo = volumeHoraireHebdo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modification", length = 29)
	public Date getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_debut_validite", length = 29)
	public Date getDateDebutValidite() {
		return this.dateDebutValidite;
	}

	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fin_validite", length = 29)
	public Date getDateFinValidite() {
		return this.dateFinValidite;
	}

	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	@Column(name = "est_valide")
	public Boolean getEstValide() {
		return this.estValide;
	}

	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}

	@Column(name = "est_publiee")
	public Boolean getEstPubliee() {
		return this.estPubliee;
	}

	public void setEstPubliee(Boolean estPubliee) {
		this.estPubliee = estPubliee;
	}

	@Column(name = "est_ouverte")
	public Boolean getEstOuverte() {
		return this.estOuverte;
	}

	public void setEstOuverte(Boolean estOuverte) {
		this.estOuverte = estOuverte;
	}

	@Column(name = "est_archivee")
	public Boolean getEstArchivee() {
		return this.estArchivee;
	}

	public void setEstArchivee(Boolean estArchivee) {
		this.estArchivee = estArchivee;
	}

	@Column(name = "est_supprimee")
	public Boolean getEstSupprimee() {
		return this.estSupprimee;
	}

	public void setEstSupprimee(Boolean estSupprimee) {
		this.estSupprimee = estSupprimee;
	}

	@Column(name = "objectifs")
	public String getObjectifs() {
		return this.objectifs;
	}

	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}

	@Column(name = "prerequis")
	public String getPrerequis() {
		return this.prerequis;
	}

	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	@Column(name = "contenu")
	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@Column(name = "competences")
	public String getCompetences() {
		return this.competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	@Column(name = "recommandations")
	public String getRecommandations() {
		return this.recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}

	@Column(name = "organisation")
	public String getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	@Column(name = "evaluation")
	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	@Column(name = "aides_etudiant")
	public String getAidesEtudiant() {
		return this.aidesEtudiant;
	}

	public void setAidesEtudiant(String aidesEtudiant) {
		this.aidesEtudiant = aidesEtudiant;
	}

	@Column(name = "admission")
	public String getAdmission() {
		return this.admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	@Column(name = "public_cible")
	public String getPublicCible() {
		return this.publicCible;
	}

	public void setPublicCible(String publicCible) {
		this.publicCible = publicCible;
	}

	@Column(name = "observation")
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uniteEnseignement")
	public Set<RattachementMc> getRattachementMcs() {
		return rattachementMcs;
	}

	public void setRattachementMcs(Set<RattachementMc> rattachementMcs) {
		this.rattachementMcs = rattachementMcs;
	}	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uniteEnseignement")
	public Set<RepartitionUe> getRepartitionUes() {
		return this.repartitionUes;
	}

	public void setRepartitionUes(Set<RepartitionUe> repartitionUes) {
		this.repartitionUes = repartitionUes;
	}
	

	@Column(name = "libre")
	public Boolean getLibre() {
		return this.libre;
	}


	public void setLibre(Boolean libre) {
		this.libre = libre;
	}

	/**
	 * [UniteEnseignement.ncNatureUe] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:46:12
	 * @return the ncNatureUe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_nature", nullable = false)
	public Nomenclature getNcNatureUe() {
		return ncNatureUe;
	}

	/**
	 * [UniteEnseignement.ncNatureUe] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:46:12
	 * @param ncNatureUe the ncNatureUe to set
	 */
	public void setNcNatureUe(Nomenclature ncNatureUe) {
		this.ncNatureUe = ncNatureUe;
	}

	/**
	 * [UniteEnseignement.ncCaractereUe] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:04:03
	 * @return the ncCaractereUe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_caractere", nullable = false)
	public Nomenclature getNcCaractereUe() {
		return ncCaractereUe;
	}

	/**
	 * [UniteEnseignement.ncCaractereUe] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:04:03
	 * @param ncCaractereUe the ncCaractereUe to set
	 */
	public void setNcCaractereUe(Nomenclature ncCaractereUe) {
		this.ncCaractereUe = ncCaractereUe;
	}

	/**
	 * [UniteEnseignement.ncAppreciationUe] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:04:03
	 * @return the ncAppreciationUe
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_appreciation")
	public Nomenclature getNcAppreciationUe() {
		return ncAppreciationUe;
	}

	/**
	 * [UniteEnseignement.ncAppreciationUe] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:04:03
	 * @param ncAppreciationUe the ncAppreciationUe to set
	 */
	public void setNcAppreciationUe(Nomenclature ncAppreciationUe) {
		this.ncAppreciationUe = ncAppreciationUe;
	}

	
	
	
	
}
