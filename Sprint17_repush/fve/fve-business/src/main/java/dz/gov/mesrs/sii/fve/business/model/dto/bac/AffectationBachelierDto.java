package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.Date;


public class AffectationBachelierDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idImportation;
	private String matriculeBachelier;
	private String refCodeEtablissement;
	private String refCodeFiliere;
	private String numeroChoix;
	private String noteAffectation;
	private String refCodeEtablissementRecours;
	private String refCodeFiliereRecours;
	private String refCodeEtablissementOrientation;
	private String refCodeFiliereOrientation;
	private String refCodeTypeAffectation;
	private String refCodeEtablissementAffectation;
	private String refCodeFiliereAffectation;
	
	// Dossier bachelier
	private int idDossierBachelier;
	private String nomArBachelier;
	private String prenomArBachelier;
	private String nomFrBachelier;
	private String prenomFrBachelier;
	private Date dateNaissanceBachelier;
	private String libelleVilleNaissanceBachelier;
	private String prenomPereBachelier;
	private String nomPrenomMereBachelier;
	private String refCodeSexeBachelier;


	public AffectationBachelierDto() {
	}

	/**
	 * [AffectationBachelierDto.id] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [AffectationBachelierDto.id] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [AffectationBachelierDto.idImportation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the idImportation
	 */
	public int getIdImportation() {
		return idImportation;
	}

	/**
	 * [AffectationBachelierDto.idImportation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param idImportation the idImportation to set
	 */
	public void setIdImportation(int idImportation) {
		this.idImportation = idImportation;
	}

	/**
	 * [AffectationBachelierDto.matriculeBachelier] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the matriculeBachelier
	 */
	public String getMatriculeBachelier() {
		return matriculeBachelier;
	}

	/**
	 * [AffectationBachelierDto.matriculeBachelier] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param matriculeBachelier the matriculeBachelier to set
	 */
	public void setMatriculeBachelier(String matriculeBachelier) {
		this.matriculeBachelier = matriculeBachelier;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissement] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissement] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliere] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliere] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	/**
	 * [AffectationBachelierDto.numeroChoix] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the numeroChoix
	 */
	public String getNumeroChoix() {
		return numeroChoix;
	}

	/**
	 * [AffectationBachelierDto.numeroChoix] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param numeroChoix the numeroChoix to set
	 */
	public void setNumeroChoix(String numeroChoix) {
		this.numeroChoix = numeroChoix;
	}

	/**
	 * [AffectationBachelierDto.noteAffectation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the noteAffectation
	 */
	public String getNoteAffectation() {
		return noteAffectation;
	}

	/**
	 * [AffectationBachelierDto.noteAffectation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param noteAffectation the noteAffectation to set
	 */
	public void setNoteAffectation(String noteAffectation) {
		this.noteAffectation = noteAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementRecours] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeEtablissementRecours
	 */
	public String getRefCodeEtablissementRecours() {
		return refCodeEtablissementRecours;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementRecours] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeEtablissementRecours the refCodeEtablissementRecours to set
	 */
	public void setRefCodeEtablissementRecours(String refCodeEtablissementRecours) {
		this.refCodeEtablissementRecours = refCodeEtablissementRecours;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereRecours] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeFiliereRecours
	 */
	public String getRefCodeFiliereRecours() {
		return refCodeFiliereRecours;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereRecours] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeFiliereRecours the refCodeFiliereRecours to set
	 */
	public void setRefCodeFiliereRecours(String refCodeFiliereRecours) {
		this.refCodeFiliereRecours = refCodeFiliereRecours;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementOrientation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeEtablissementOrientation
	 */
	public String getRefCodeEtablissementOrientation() {
		return refCodeEtablissementOrientation;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementOrientation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeEtablissementOrientation the refCodeEtablissementOrientation to set
	 */
	public void setRefCodeEtablissementOrientation(
			String refCodeEtablissementOrientation) {
		this.refCodeEtablissementOrientation = refCodeEtablissementOrientation;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereOrientation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeFiliereOrientation
	 */
	public String getRefCodeFiliereOrientation() {
		return refCodeFiliereOrientation;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereOrientation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeFiliereOrientation the refCodeFiliereOrientation to set
	 */
	public void setRefCodeFiliereOrientation(String refCodeFiliereOrientation) {
		this.refCodeFiliereOrientation = refCodeFiliereOrientation;
	}

	/**
	 * [AffectationBachelierDto.refCodeTypeAffectation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeTypeAffectation
	 */
	public String getRefCodeTypeAffectation() {
		return refCodeTypeAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeTypeAffectation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeTypeAffectation the refCodeTypeAffectation to set
	 */
	public void setRefCodeTypeAffectation(String refCodeTypeAffectation) {
		this.refCodeTypeAffectation = refCodeTypeAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementAffectation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeEtablissementAffectation
	 */
	public String getRefCodeEtablissementAffectation() {
		return refCodeEtablissementAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeEtablissementAffectation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeEtablissementAffectation the refCodeEtablissementAffectation to set
	 */
	public void setRefCodeEtablissementAffectation(
			String refCodeEtablissementAffectation) {
		this.refCodeEtablissementAffectation = refCodeEtablissementAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereAffectation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @return the refCodeFiliereAffectation
	 */
	public String getRefCodeFiliereAffectation() {
		return refCodeFiliereAffectation;
	}

	/**
	 * [AffectationBachelierDto.refCodeFiliereAffectation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:27:03
	 * @param refCodeFiliereAffectation the refCodeFiliereAffectation to set
	 */
	public void setRefCodeFiliereAffectation(String refCodeFiliereAffectation) {
		this.refCodeFiliereAffectation = refCodeFiliereAffectation;
	}

	/**
	 * [AffectationBachelierDto.idDossierBachelier] Getter 
	 * @author rlaib on : 24 juil. 2014  13:45:24
	 * @return the idDossierBachelier
	 */
	public int getIdDossierBachelier() {
		return idDossierBachelier;
	}

	/**
	 * [AffectationBachelierDto.idDossierBachelier] Setter 
	 * @author rlaib on : 24 juil. 2014  13:45:24
	 * @param idDossierBachelier the idDossierBachelier to set
	 */
	public void setIdDossierBachelier(int idDossierBachelier) {
		this.idDossierBachelier = idDossierBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomArBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the nomArBachelier
	 */
	public String getNomArBachelier() {
		return nomArBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomArBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param nomArBachelier the nomArBachelier to set
	 */
	public void setNomArBachelier(String nomArBachelier) {
		this.nomArBachelier = nomArBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomArBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the prenomArBachelier
	 */
	public String getPrenomArBachelier() {
		return prenomArBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomArBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param prenomArBachelier the prenomArBachelier to set
	 */
	public void setPrenomArBachelier(String prenomArBachelier) {
		this.prenomArBachelier = prenomArBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomFrBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the nomFrBachelier
	 */
	public String getNomFrBachelier() {
		return nomFrBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomFrBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param nomFrBachelier the nomFrBachelier to set
	 */
	public void setNomFrBachelier(String nomFrBachelier) {
		this.nomFrBachelier = nomFrBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomFrBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the prenomFrBachelier
	 */
	public String getPrenomFrBachelier() {
		return prenomFrBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomFrBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param prenomFrBachelier the prenomFrBachelier to set
	 */
	public void setPrenomFrBachelier(String prenomFrBachelier) {
		this.prenomFrBachelier = prenomFrBachelier;
	}

	/**
	 * [AffectationBachelierDto.dateNaissanceBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the dateNaissanceBachelier
	 */
	public Date getDateNaissanceBachelier() {
		return dateNaissanceBachelier;
	}

	/**
	 * [AffectationBachelierDto.dateNaissanceBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param dateNaissanceBachelier the dateNaissanceBachelier to set
	 */
	public void setDateNaissanceBachelier(Date dateNaissanceBachelier) {
		this.dateNaissanceBachelier = dateNaissanceBachelier;
	}

	/**
	 * [AffectationBachelierDto.libelleVilleNaissanceBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the libelleVilleNaissanceBachelier
	 */
	public String getLibelleVilleNaissanceBachelier() {
		return libelleVilleNaissanceBachelier;
	}

	/**
	 * [AffectationBachelierDto.libelleVilleNaissanceBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param libelleVilleNaissanceBachelier the libelleVilleNaissanceBachelier to set
	 */
	public void setLibelleVilleNaissanceBachelier(
			String libelleVilleNaissanceBachelier) {
		this.libelleVilleNaissanceBachelier = libelleVilleNaissanceBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomPereBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the prenomPereBachelier
	 */
	public String getPrenomPereBachelier() {
		return prenomPereBachelier;
	}

	/**
	 * [AffectationBachelierDto.prenomPereBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param prenomPereBachelier the prenomPereBachelier to set
	 */
	public void setPrenomPereBachelier(String prenomPereBachelier) {
		this.prenomPereBachelier = prenomPereBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomPrenomMereBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @return the nomPrenomMereBachelier
	 */
	public String getNomPrenomMereBachelier() {
		return nomPrenomMereBachelier;
	}

	/**
	 * [AffectationBachelierDto.nomPrenomMereBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  12:47:44
	 * @param nomPrenomMereBachelier the nomPrenomMereBachelier to set
	 */
	public void setNomPrenomMereBachelier(String nomPrenomMereBachelier) {
		this.nomPrenomMereBachelier = nomPrenomMereBachelier;
	}

	/**
	 * [AffectationBachelierDto.refCodeSexeBachelier] Getter 
	 * @author rlaib on : 31 juil. 2014  13:01:26
	 * @return the refCodeSexeBachelier
	 */
	public String getRefCodeSexeBachelier() {
		return refCodeSexeBachelier;
	}

	/**
	 * [AffectationBachelierDto.refCodeSexeBachelier] Setter 
	 * @author rlaib on : 31 juil. 2014  13:01:26
	 * @param refCodeSexeBachelier the refCodeSexeBachelier to set
	 */
	public void setRefCodeSexeBachelier(String refCodeSexeBachelier) {
		this.refCodeSexeBachelier = refCodeSexeBachelier;
	}
	
	
}
