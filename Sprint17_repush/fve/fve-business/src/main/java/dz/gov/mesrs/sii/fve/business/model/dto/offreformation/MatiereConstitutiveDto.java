package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

/**
 * Classe de type DTO permet de representer une matiere constitutive
 * 
 * @author Kheireddine OMRANI
 * 
 */
public class MatiereConstitutiveDto implements java.io.Serializable {

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
	private Double credits;
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
	private Double coefficient;
	private Boolean libre;
	private double noteEliminatoire;
	private double noteBase;
	private double noteObtention;

	public MatiereConstitutiveDto() {
		this.dateCreation = new Date();
		this.dateModification = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleFr() {
		return libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public String getAbreviationFr() {
		return abreviationFr;
	}

	public void setAbreviationFr(String abreviationFr) {
		this.abreviationFr = abreviationFr;
	}

	public String getLibelleAr() {
		return libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public String getAbreviationAr() {
		return abreviationAr;
	}

	public void setAbreviationAr(String abreviationAr) {
		this.abreviationAr = abreviationAr;
	}

	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public String getDescriptionAr() {
		return descriptionAr;
	}

	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}

	public Double getCredits() {
		return credits;
	}

	public void setCredits(Double credits) {
		this.credits = credits;
	}

	public Double getVolumeHorairePerso() {
		return volumeHorairePerso;
	}

	public void setVolumeHorairePerso(Double volumeHorairePerso) {
		this.volumeHorairePerso = volumeHorairePerso;
	}

	public Double getVolumeHorairePres() {
		return volumeHorairePres;
	}

	public void setVolumeHorairePres(Double volumeHorairePres) {
		this.volumeHorairePres = volumeHorairePres;
	}

	public String getRefCodeTypeMc() {
		return refCodeTypeMc;
	}

	public void setRefCodeTypeMc(String refCodeTypeMc) {
		this.refCodeTypeMc = refCodeTypeMc;
	}

	public String getRefCodeModeEvaluation() {
		return refCodeModeEvaluation;
	}

	public void setRefCodeModeEvaluation(String refCodeModeEvaluation) {
		this.refCodeModeEvaluation = refCodeModeEvaluation;
	}

	public String getRefCodeLangue() {
		return refCodeLangue;
	}

	public void setRefCodeLangue(String refCodeLangue) {
		this.refCodeLangue = refCodeLangue;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Boolean getEstValide() {
		return estValide;
	}

	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}

	public Boolean getEstPubliee() {
		return estPubliee;
	}

	public void setEstPubliee(Boolean estPubliee) {
		this.estPubliee = estPubliee;
	}

	public Boolean getEstArchivee() {
		return estArchivee;
	}

	public void setEstArchivee(Boolean estArchivee) {
		this.estArchivee = estArchivee;
	}

	public Boolean getEstSupprimee() {
		return estSupprimee;
	}

	public void setEstSupprimee(Boolean estSupprimee) {
		this.estSupprimee = estSupprimee;
	}

	public String getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	public String getConnaissancesRequises() {
		return connaissancesRequises;
	}

	public void setConnaissancesRequises(String connaissancesRequises) {
		this.connaissancesRequises = connaissancesRequises;
	}

	public String getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(String recommandations) {
		this.recommandations = recommandations;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getAidesEtudiant() {
		return aidesEtudiant;
	}

	public void setAidesEtudiant(String aidesEtudiant) {
		this.aidesEtudiant = aidesEtudiant;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public String getPublicCible() {
		return publicCible;
	}

	public void setPublicCible(String publicCible) {
		this.publicCible = publicCible;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [MatiereConstitutiveDto.optionnel] Getter
	 * 
	 * @author BELBRIK Oualid on : 9 sept. 2014 17:34:51
	 * @return the optionnel
	 */
	public Boolean getOptionnel() {
		return optionnel;
	}

	/**
	 * [MatiereConstitutiveDto.optionnel] Setter
	 * 
	 * @author BELBRIK Oualid on : 9 sept. 2014 17:34:51
	 * @param optionnel
	 *            the optionnel to set
	 */
	public void setOptionnel(Boolean optionnel) {
		this.optionnel = optionnel;
	}

	/**
	 * [MatiereConstitutiveDto.coefficient] Getter
	 * 
	 * @author BELBRIK Oualid on : 15 sept. 2014 18:57:52
	 * @return the coefficient
	 */
	public Double getCoefficient() {
		return coefficient;
	}

	/**
	 * [MatiereConstitutiveDto.coefficient] Setter
	 * 
	 * @author BELBRIK Oualid on : 15 sept. 2014 18:57:52
	 * @param coefficient
	 *            the coefficient to set
	 */
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [MatiereConstitutiveDto.libre] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 sept. 2014 12:19:01
	 * @return the libre
	 */
	public Boolean getLibre() {
		return libre;
	}

	/**
	 * [MatiereConstitutiveDto.libre] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 sept. 2014 12:19:01
	 * @param libre
	 *            the libre to set
	 */
	public void setLibre(Boolean libre) {
		this.libre = libre;
	}

	/**
	 * [MatiereConstitutiveDto.noteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:18:40
	 * @return the noteEliminatoire
	 */
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [MatiereConstitutiveDto.noteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:18:40
	 * @param noteEliminatoire
	 *            the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	/**
	 * [MatiereConstitutiveDto.noteBase] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:23:11
	 * @return the noteBase
	 */
	public double getNoteBase() {
		return noteBase;
	}

	/**
	 * [MatiereConstitutiveDto.noteBase] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:23:11
	 * @param noteBase
	 *            the noteBase to set
	 */
	public void setNoteBase(double noteBase) {
		this.noteBase = noteBase;
	}

	/**
	 * [MatiereConstitutiveDto.noteObtention] Getter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:23:11
	 * @return the noteObtention
	 */
	public double getNoteObtention() {
		return noteObtention;
	}

	/**
	 * [MatiereConstitutiveDto.noteObtention] Setter
	 * 
	 * @author MAKERRI Sofiane on : 20 nov. 2014 16:23:11
	 * @param noteObtention
	 *            the noteObtention to set
	 */
	public void setNoteObtention(double noteObtention) {
		this.noteObtention = noteObtention;
	}

}
