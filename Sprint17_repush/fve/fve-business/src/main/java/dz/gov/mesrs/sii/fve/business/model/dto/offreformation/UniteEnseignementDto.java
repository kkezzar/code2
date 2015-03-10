package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

/**
 * Classe de type DTO permet de repr�senter une unit� d'enseignement
 * 
 * @author Kheireddine OMRANI
 *
 */
public class UniteEnseignementDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2082575093869478691L;
	private int id;
	private String code;
	private String libelleFr;
	private String abreviationFr;
	private String libelleAr;
	private String abreviationAr;
	//private String refCodeTypeUe;
	//private String refCodeNatureUe;
	//private String refCodeCaractereUe;
	private String refCodeStatutUe;
	private Double credits;
	//private String refCodeAppreciation;
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
	private String ncNatureUeLlFr;
	private String ncNatureUeLlAr;
	private String ncNatureUeLcFr;
	private String ncNatureUeLcAr;
	private String ncNatureUeCode;
	private Integer ncNatureUeId;
	
	private String ncCaractereUeLlFr;
	private String ncCaractereUeLlAr;
	private String ncCaractereUeLcFr;
	private String ncCaractereUeLcAr;
	private String ncCaractereUeCode;
	private Integer ncCaractereUeId;
	
	private String ncAppreciationUeLlFr;
	private String ncAppreciationUeLlAr;
	private String ncAppreciationUeLcFr;
	private String ncAppreciationUeLcAr;
	private String ncAppreciationUeCode;
	private Integer ncAppreciationUeId;

	
	public UniteEnseignementDto() {
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


//	public String getRefCodeTypeUe() {
//		return refCodeTypeUe;
//	}
//
//
//	public void setRefCodeTypeUe(String refCodeTypeUe) {
//		this.refCodeTypeUe = refCodeTypeUe;
//	}
//
//
//	public String getRefCodeNatureUe() {
//		return refCodeNatureUe;
//	}
//
//
//	public void setRefCodeNatureUe(String refCodeNatureUe) {
//		this.refCodeNatureUe = refCodeNatureUe;
//	}


//	public String getRefCodeCaractereUe() {
//		return refCodeCaractereUe;
//	}
//
//
//	public void setRefCodeCaractereUe(String refCodeCaractereUe) {
//		this.refCodeCaractereUe = refCodeCaractereUe;
//	}


	public String getRefCodeStatutUe() {
		return refCodeStatutUe;
	}


	public void setRefCodeStatutUe(String refCodeStatutUe) {
		this.refCodeStatutUe = refCodeStatutUe;
	}


	public Double getCredits() {
		return credits;
	}


	public void setCredits(Double credits) {
		this.credits = credits;
	}

//	public String getRefCodeAppreciation() {
//		return refCodeAppreciation;
//	}
//
//
//	public void setRefCodeAppreciation(String refCodeAppreciation) {
//		this.refCodeAppreciation = refCodeAppreciation;
//	}


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


	public Date getDateDebutValidite() {
		return dateDebutValidite;
	}


	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}


	public Date getDateFinValidite() {
		return dateFinValidite;
	}


	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
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


	public Boolean getEstOuverte() {
		return estOuverte;
	}


	public void setEstOuverte(Boolean estOuverte) {
		this.estOuverte = estOuverte;
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


	public String getPrerequis() {
		return prerequis;
	}


	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
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


	public String getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
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


	public void setChoisie(boolean checked) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * [UniteEnseignementDto.volumeHoraireHebdo] Getter 
	 * @author BELBRIK Oualid on : 14 ao�t 2014  16:18:09
	 * @return the volumeHoraireHebdo
	 */
	public Double getVolumeHoraireHebdo() {
		return volumeHoraireHebdo;
	}


	/**
	 * [UniteEnseignementDto.volumeHoraireHebdo] Setter 
	 * @author BELBRIK Oualid on : 14 ao�t 2014  16:18:09
	 * @param volumeHoraireHebdo the volumeHoraireHebdo to set
	 */
	public void setVolumeHoraireHebdo(Double volumeHoraireHebdo) {
		this.volumeHoraireHebdo = volumeHoraireHebdo;
	}


	public Boolean getLibre() {
		return libre;
	}


	public void setLibre(Boolean libre) {
		this.libre = libre;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLlFr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @return the ncNatureUeLlFr
	 */
	public String getNcNatureUeLlFr() {
		return ncNatureUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLlFr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @param ncNatureUeLlFr the ncNatureUeLlFr to set
	 */
	public void setNcNatureUeLlFr(String ncNatureUeLlFr) {
		this.ncNatureUeLlFr = ncNatureUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLlAr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @return the ncNatureUeLlAr
	 */
	public String getNcNatureUeLlAr() {
		return ncNatureUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLlAr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @param ncNatureUeLlAr the ncNatureUeLlAr to set
	 */
	public void setNcNatureUeLlAr(String ncNatureUeLlAr) {
		this.ncNatureUeLlAr = ncNatureUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLcFr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @return the ncNatureUeLcFr
	 */
	public String getNcNatureUeLcFr() {
		return ncNatureUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLcFr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @param ncNatureUeLcFr the ncNatureUeLcFr to set
	 */
	public void setNcNatureUeLcFr(String ncNatureUeLcFr) {
		this.ncNatureUeLcFr = ncNatureUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLcAr] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @return the ncNatureUeLcAr
	 */
	public String getNcNatureUeLcAr() {
		return ncNatureUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeLcAr] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @param ncNatureUeLcAr the ncNatureUeLcAr to set
	 */
	public void setNcNatureUeLcAr(String ncNatureUeLcAr) {
		this.ncNatureUeLcAr = ncNatureUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeCode] Getter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @return the ncNatureUeCode
	 */
	public String getNcNatureUeCode() {
		return ncNatureUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeCode] Setter 
	 * @author MAKERRI Sofiane on : 29 oct. 2014  09:47:19
	 * @param ncNatureUeCode the ncNatureUeCode to set
	 */
	public void setNcNatureUeCode(String ncNatureUeCode) {
		this.ncNatureUeCode = ncNatureUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeId] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncNatureUeId
	 */
	public Integer getNcNatureUeId() {
		return ncNatureUeId;
	}


	/**
	 * [UniteEnseignementDto.ncNatureUeId] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncNatureUeId the ncNatureUeId to set
	 */
	public void setNcNatureUeId(Integer ncNatureUeId) {
		this.ncNatureUeId = ncNatureUeId;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLlFr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeLlFr
	 */
	public String getNcCaractereUeLlFr() {
		return ncCaractereUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLlFr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeLlFr the ncCaractereUeLlFr to set
	 */
	public void setNcCaractereUeLlFr(String ncCaractereUeLlFr) {
		this.ncCaractereUeLlFr = ncCaractereUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLlAr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeLlAr
	 */
	public String getNcCaractereUeLlAr() {
		return ncCaractereUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLlAr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeLlAr the ncCaractereUeLlAr to set
	 */
	public void setNcCaractereUeLlAr(String ncCaractereUeLlAr) {
		this.ncCaractereUeLlAr = ncCaractereUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLcFr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeLcFr
	 */
	public String getNcCaractereUeLcFr() {
		return ncCaractereUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLcFr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeLcFr the ncCaractereUeLcFr to set
	 */
	public void setNcCaractereUeLcFr(String ncCaractereUeLcFr) {
		this.ncCaractereUeLcFr = ncCaractereUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLcAr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeLcAr
	 */
	public String getNcCaractereUeLcAr() {
		return ncCaractereUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeLcAr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeLcAr the ncCaractereUeLcAr to set
	 */
	public void setNcCaractereUeLcAr(String ncCaractereUeLcAr) {
		this.ncCaractereUeLcAr = ncCaractereUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeCode] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeCode
	 */
	public String getNcCaractereUeCode() {
		return ncCaractereUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeCode] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeCode the ncCaractereUeCode to set
	 */
	public void setNcCaractereUeCode(String ncCaractereUeCode) {
		this.ncCaractereUeCode = ncCaractereUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeId] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncCaractereUeId
	 */
	public Integer getNcCaractereUeId() {
		return ncCaractereUeId;
	}


	/**
	 * [UniteEnseignementDto.ncCaractereUeId] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncCaractereUeId the ncCaractereUeId to set
	 */
	public void setNcCaractereUeId(Integer ncCaractereUeId) {
		this.ncCaractereUeId = ncCaractereUeId;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLlFr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeLlFr
	 */
	public String getNcAppreciationUeLlFr() {
		return ncAppreciationUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLlFr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeLlFr the ncAppreciationUeLlFr to set
	 */
	public void setNcAppreciationUeLlFr(String ncAppreciationUeLlFr) {
		this.ncAppreciationUeLlFr = ncAppreciationUeLlFr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLlAr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeLlAr
	 */
	public String getNcAppreciationUeLlAr() {
		return ncAppreciationUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLlAr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeLlAr the ncAppreciationUeLlAr to set
	 */
	public void setNcAppreciationUeLlAr(String ncAppreciationUeLlAr) {
		this.ncAppreciationUeLlAr = ncAppreciationUeLlAr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLcFr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeLcFr
	 */
	public String getNcAppreciationUeLcFr() {
		return ncAppreciationUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLcFr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeLcFr the ncAppreciationUeLcFr to set
	 */
	public void setNcAppreciationUeLcFr(String ncAppreciationUeLcFr) {
		this.ncAppreciationUeLcFr = ncAppreciationUeLcFr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLcAr] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeLcAr
	 */
	public String getNcAppreciationUeLcAr() {
		return ncAppreciationUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeLcAr] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeLcAr the ncAppreciationUeLcAr to set
	 */
	public void setNcAppreciationUeLcAr(String ncAppreciationUeLcAr) {
		this.ncAppreciationUeLcAr = ncAppreciationUeLcAr;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeCode] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeCode
	 */
	public String getNcAppreciationUeCode() {
		return ncAppreciationUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeCode] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeCode the ncAppreciationUeCode to set
	 */
	public void setNcAppreciationUeCode(String ncAppreciationUeCode) {
		this.ncAppreciationUeCode = ncAppreciationUeCode;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeId] Getter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @return the ncAppreciationUeId
	 */
	public Integer getNcAppreciationUeId() {
		return ncAppreciationUeId;
	}


	/**
	 * [UniteEnseignementDto.ncAppreciationUeId] Setter 
	 * @author MAKERRI Sofiane on : 25 nov. 2014  14:07:11
	 * @param ncAppreciationUeId the ncAppreciationUeId to set
	 */
	public void setNcAppreciationUeId(Integer ncAppreciationUeId) {
		this.ncAppreciationUeId = ncAppreciationUeId;
	}
	
	

	
}
