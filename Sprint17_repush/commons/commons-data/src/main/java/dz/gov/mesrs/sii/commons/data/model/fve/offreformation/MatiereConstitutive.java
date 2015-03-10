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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mc", schema = "lmd")
public class MatiereConstitutive implements java.io.Serializable {

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
	private String descriptionFr;
	private String descriptionAr;
	private Integer credits;
	private Double volumeHorairePerso;
	private Double volumeHorairePres;
	private String refCodeTypeMc;
	private String refCodeModeEvaluation;
	private String refCodeLangue;
	private Date dateCreation;
	private Date dateModification;
	private Boolean optionnel;
	private Boolean estValide;
	private Boolean estPubliee;
	private Boolean estArchivee;
	private Boolean estSupprimee;
	private String objectifs;
	private String contenu;
	private String competences;
	private String connaissancesRequises;
	private String recommandations;
	private String organisation;
	private String aidesEtudiant;
	private String admission;
	private String publicCible;
	private String observation;
	private Integer coefficient;
	private Boolean libre;
	private double noteEliminatoire;
	private double noteBase;
	private double noteObtention;

	private Set<AtomePedagogique> atomePedagogiques = new HashSet<AtomePedagogique>(0);
	private Set<RattachementMc> rattachementMcs = new HashSet<RattachementMc>(0);

	// private Set<GroupePedagogique> groupePedagogiques = new
	// HashSet<GroupePedagogique>(0);

	public MatiereConstitutive() {
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

	@Column(name = "abreviation_fr", length = 20)
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

	@Column(name = "abreviation_ar", length = 20)
	public String getAbreviationAr() {
		return this.abreviationAr;
	}

	public void setAbreviationAr(String abreviationAr) {
		this.abreviationAr = abreviationAr;
	}

	@Column(name = "description_fr")
	public String getDescriptionFr() {
		return this.descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	@Column(name = "description_ar")
	public String getDescriptionAr() {
		return this.descriptionAr;
	}

	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}

	@Column(name = "credits")
	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
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

	@Column(name = "ref_code_type_mc", length = 50)
	public String getRefCodeTypeMc() {
		return this.refCodeTypeMc;
	}

	public void setRefCodeTypeMc(String refCodeTypeMc) {
		this.refCodeTypeMc = refCodeTypeMc;
	}

	@Column(name = "ref_code_mode_evaluation", length = 50)
	public String getRefCodeModeEvaluation() {
		return this.refCodeModeEvaluation;
	}

	public void setRefCodeModeEvaluation(String refCodeModeEvaluation) {
		this.refCodeModeEvaluation = refCodeModeEvaluation;
	}

	@Column(name = "ref_code_langue", length = 50)
	public String getRefCodeLangue() {
		return this.refCodeLangue;
	}

	public void setRefCodeLangue(String refCodeLangue) {
		this.refCodeLangue = refCodeLangue;
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

	@Column(name = "est_valide")
	public Boolean getEstValide() {
		return this.estValide;
	}

	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}

	@Column(name = "optionnel")
	public Boolean getOptionnel() {
		return this.optionnel;
	}

	public void setOptionnel(Boolean optionnel) {
		this.optionnel = optionnel;
	}

	@Column(name = "est_publiee")
	public Boolean getEstPubliee() {
		return this.estPubliee;
	}

	public void setEstPubliee(Boolean estPubliee) {
		this.estPubliee = estPubliee;
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

	@Column(name = "connaissances_requises")
	public String getConnaissancesRequises() {
		return this.connaissancesRequises;
	}

	public void setConnaissancesRequises(String connaissancesRequises) {
		this.connaissancesRequises = connaissancesRequises;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "matiereConstitutive")
	public Set<AtomePedagogique> getAtomePedagogiques() {
		return this.atomePedagogiques;
	}

	public void setAtomePedagogiques(Set<AtomePedagogique> atomePedagogiques) {
		this.atomePedagogiques = atomePedagogiques;
	}

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "mc")
	// public Set<GroupePedagogique> getGroupePedagogiques() {
	// return groupePedagogiques;
	// }
	//
	// public void setGroupePedagogiques(Set<GroupePedagogique>
	// groupePedagogiques) {
	// this.groupePedagogiques = groupePedagogiques;
	// }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "matiereConstitutive")
	public Set<RattachementMc> getRattachementMcs() {
		return rattachementMcs;
	}

	public void setRattachementMcs(Set<RattachementMc> rattachementMcs) {
		this.rattachementMcs = rattachementMcs;
	}

	@Column(name = "coefficient")
	public Integer getCoefficient() {
		return this.coefficient;
	}

	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "libre")
	public Boolean getLibre() {
		return this.libre;
	}

	public void setLibre(Boolean libre) {
		this.libre = libre;
	}

	/**
	 * [MatiereConstitutive.noteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:18:02
	 * @return the noteEliminatoire
	 */
	@Column(name = "note_eliminatoire")
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [MatiereConstitutive.noteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:18:02
	 * @param noteEliminatoire
	 *            the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	/**
	 * [MatiereConstitutive.noteBase] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:22:23
	 * @return the noteBase
	 */
	@Column(name = "note_base")
	public double getNoteBase() {
		return noteBase;
	}

	/**
	 * [MatiereConstitutive.noteBase] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:22:23
	 * @param noteBase
	 *            the noteBase to set
	 */
	public void setNoteBase(double noteBase) {
		this.noteBase = noteBase;
	}

	/**
	 * [MatiereConstitutive.noteObtention] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:22:23
	 * @return the noteObtention
	 */
	@Column(name = "note_obtention")
	public double getNoteObtention() {
		return noteObtention;
	}

	/**
	 * [MatiereConstitutive.noteObtention] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:22:23
	 * @param noteObtention
	 *            the noteObtention to set
	 */
	public void setNoteObtention(double noteObtention) {
		this.noteObtention = noteObtention;
	}

}
