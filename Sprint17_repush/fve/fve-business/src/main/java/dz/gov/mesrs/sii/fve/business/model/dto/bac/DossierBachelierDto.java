package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.Date;

public class DossierBachelierDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	private static final long serialVersionUID = 5595401399463125298L;
	private int id;
	private String matricule;
	private String nomAr;
	private String prenomAr;
	private String nomFr;
	private String prenomFr;
	private Date dateNaissance;
	private String moyenneBac;
	private String prenomPere;
	private String nomPrenomMere;
	private String telephone;
	private String adresseResidence;
	private String refCodeSexe;
	private String refCodeWilayaNaissance;
	private String refCodeWilayaBac;
	private String refCodeWilayaResidence;
	private String refCodeSerieBac;
	private String libelleVilleNaissance;
	private String libelleSerieBac;
	private int tag;
	private int idImportation;
	private int idImportationAffectation;
	private int idDossierEtudiant;
	private String annee;
	private Boolean estClassique;
	
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
	
	private String libelleFiliereAffectation;
	private String codeFiliereInscription;
	private String photoEtudiant;
	
	public DossierBachelierDto() {
	}

	/**
	 * [DossierBachelierDto.id] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DossierBachelierDto.id] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DossierBachelierDto.matricule] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * [DossierBachelierDto.matricule] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * [DossierBachelierDto.nomAr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the nomAr
	 */
	public String getNomAr() {
		return nomAr;
	}

	/**
	 * [DossierBachelierDto.nomAr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param nomAr the nomAr to set
	 */
	public void setNomAr(String nomAr) {
		this.nomAr = nomAr;
	}

	/**
	 * [DossierBachelierDto.prenomAr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the prenomAr
	 */
	public String getPrenomAr() {
		return prenomAr;
	}

	/**
	 * [DossierBachelierDto.prenomAr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param prenomAr the prenomAr to set
	 */
	public void setPrenomAr(String prenomAr) {
		this.prenomAr = prenomAr;
	}

	/**
	 * [DossierBachelierDto.nomFr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the nomFr
	 */
	public String getNomFr() {
		return nomFr;
	}

	/**
	 * [DossierBachelierDto.nomFr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param nomFr the nomFr to set
	 */
	public void setNomFr(String nomFr) {
		this.nomFr = nomFr;
	}

	/**
	 * [DossierBachelierDto.prenomFr] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the prenomFr
	 */
	public String getPrenomFr() {
		return prenomFr;
	}

	/**
	 * [DossierBachelierDto.prenomFr] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param prenomFr the prenomFr to set
	 */
	public void setPrenomFr(String prenomFr) {
		this.prenomFr = prenomFr;
	}

	/**
	 * [DossierBachelierDto.dateNaissance] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * [DossierBachelierDto.dateNaissance] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * [DossierBachelierDto.moyenneBac] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:58
	 * @return the moyenneBac
	 */
	public String getMoyenneBac() {
		return moyenneBac;
	}

	/**
	 * [DossierBachelierDto.moyenneBac] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param moyenneBac the moyenneBac to set
	 */
	public void setMoyenneBac(String moyenneBac) {
		this.moyenneBac = moyenneBac;
	}

	/**
	 * [DossierBachelierDto.prenomPere] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the prenomPere
	 */
	public String getPrenomPere() {
		return prenomPere;
	}

	/**
	 * [DossierBachelierDto.prenomPere] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param prenomPere the prenomPere to set
	 */
	public void setPrenomPere(String prenomPere) {
		this.prenomPere = prenomPere;
	}

	/**
	 * [DossierBachelierDto.nomPrenomMere] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the nomPrenomMere
	 */
	public String getNomPrenomMere() {
		return nomPrenomMere;
	}

	/**
	 * [DossierBachelierDto.nomPrenomMere] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param nomPrenomMere the nomPrenomMere to set
	 */
	public void setNomPrenomMere(String nomPrenomMere) {
		this.nomPrenomMere = nomPrenomMere;
	}

	/**
	 * [DossierBachelierDto.telephone] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * [DossierBachelierDto.telephone] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * [DossierBachelierDto.adresseResidence] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the adresseResidence
	 */
	public String getAdresseResidence() {
		return adresseResidence;
	}

	/**
	 * [DossierBachelierDto.adresseResidence] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param adresseResidence the adresseResidence to set
	 */
	public void setAdresseResidence(String adresseResidence) {
		this.adresseResidence = adresseResidence;
	}

	/**
	 * [DossierBachelierDto.refCodeSexe] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the refCodeSexe
	 */
	public String getRefCodeSexe() {
		return refCodeSexe;
	}

	/**
	 * [DossierBachelierDto.refCodeSexe] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param refCodeSexe the refCodeSexe to set
	 */
	public void setRefCodeSexe(String refCodeSexe) {
		this.refCodeSexe = refCodeSexe;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaNaissance] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the refCodeWilayaNaissance
	 */
	public String getRefCodeWilayaNaissance() {
		return refCodeWilayaNaissance;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaNaissance] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param refCodeWilayaNaissance the refCodeWilayaNaissance to set
	 */
	public void setRefCodeWilayaNaissance(String refCodeWilayaNaissance) {
		this.refCodeWilayaNaissance = refCodeWilayaNaissance;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaBac] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the refCodeWilayaBac
	 */
	public String getRefCodeWilayaBac() {
		return refCodeWilayaBac;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaBac] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param refCodeWilayaBac the refCodeWilayaBac to set
	 */
	public void setRefCodeWilayaBac(String refCodeWilayaBac) {
		this.refCodeWilayaBac = refCodeWilayaBac;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaResidence] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the refCodeWilayaResidence
	 */
	public String getRefCodeWilayaResidence() {
		return refCodeWilayaResidence;
	}

	/**
	 * [DossierBachelierDto.refCodeWilayaResidence] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param refCodeWilayaResidence the refCodeWilayaResidence to set
	 */
	public void setRefCodeWilayaResidence(String refCodeWilayaResidence) {
		this.refCodeWilayaResidence = refCodeWilayaResidence;
	}

	/**
	 * [DossierBachelierDto.refCodeSerieBac] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the refCodeSerieBac
	 */
	public String getRefCodeSerieBac() {
		return refCodeSerieBac;
	}

	/**
	 * [DossierBachelierDto.refCodeSerieBac] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param refCodeSerieBac the refCodeSerieBac to set
	 */
	public void setRefCodeSerieBac(String refCodeSerieBac) {
		this.refCodeSerieBac = refCodeSerieBac;
	}

	/**
	 * [DossierBachelierDto.libelleVilleNaissance] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the libelleVilleNaissance
	 */
	public String getLibelleVilleNaissance() {
		return libelleVilleNaissance;
	}

	/**
	 * [DossierBachelierDto.libelleVilleNaissance] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param libelleVilleNaissance the libelleVilleNaissance to set
	 */
	public void setLibelleVilleNaissance(String libelleVilleNaissance) {
		this.libelleVilleNaissance = libelleVilleNaissance;
	}

	/**
	 * [DossierBachelierDto.libelleSerieBac] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  16:27:59
	 * @return the libelleSerieBac
	 */
	public String getLibelleSerieBac() {
		return libelleSerieBac;
	}
	

	/**
	 * [DossierBachelierDto.libelleSerieBac] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  16:27:59
	 * @param libelleSerieBac the libelleSerieBac to set
	 */
	public void setLibelleSerieBac(String libelleSerieBac) {
		this.libelleSerieBac = libelleSerieBac;
	}

	/**
	 * [DossierBachelierDto.tag] Getter 
	 * @author rlaib on : 13 juil. 2014  10:29:26
	 * @return the tag
	 */
	public int getTag() {
		return tag;
	}

	/**
	 * [DossierBachelierDto.tag] Setter 
	 * @author rlaib on : 13 juil. 2014  10:29:26
	 * @param tag the tag to set
	 */
	public void setTag(int tag) {
		this.tag = tag;
	}

	/**
	 * [DossierBachelierDto.idImportation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the idImportation
	 */
	public int getIdImportation() {
		return idImportation;
	}

	/**
	 * [DossierBachelierDto.idImportation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param idImportation the idImportation to set
	 */
	public void setIdImportation(int idImportation) {
		this.idImportation = idImportation;
	}

	/**
	 * [DossierBachelierDto.idImportationAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:51:26
	 * @return the idImportationAffectation
	 */
	public int getIdImportationAffectation() {
		return idImportationAffectation;
	}

	/**
	 * [DossierBachelierDto.idImportationAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:51:26
	 * @param idImportationAffectation the idImportationAffectation to set
	 */
	public void setIdImportationAffectation(int idImportationAffectation) {
		this.idImportationAffectation = idImportationAffectation;
	}

	/**
	 * [DossierBachelierDto.idDossierEtudiant] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @return the idDossierEtudiant
	 */
	public int getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [DossierBachelierDto.idDossierEtudiant] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  12:39:59
	 * @param idDossierEtudiant the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(int idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [DossierBachelierDto.annee] Getter 
	 * @author rlaib on : 16 juin 2014  09:47:33
	 * @return the annee
	 */
	public String getAnnee() {
		return annee;
	}

	/**
	 * [DossierBachelierDto.annee] Setter 
	 * @author rlaib on : 16 juin 2014  09:47:33
	 * @param annee the annee to set
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * [DossierBachelierDto.estClassique] Getter 
	 * @author rlaib on : 24 juin 2014  16:40:36
	 * @return the estClassique
	 */
	public Boolean getEstClassique() {
		return estClassique;
	}

	/**
	 * [DossierBachelierDto.estClassique] Setter 
	 * @author rlaib on : 24 juin 2014  16:40:36
	 * @param estClassique the estClassique to set
	 */
	public void setEstClassique(Boolean estClassique) {
		this.estClassique = estClassique;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissement] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissement] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliere] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliere] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	/**
	 * [DossierBachelierDto.numeroChoix] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the numeroChoix
	 */
	public String getNumeroChoix() {
		return numeroChoix;
	}

	/**
	 * [DossierBachelierDto.numeroChoix] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param numeroChoix the numeroChoix to set
	 */
	public void setNumeroChoix(String numeroChoix) {
		this.numeroChoix = numeroChoix;
	}

	/**
	 * [DossierBachelierDto.noteAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the noteAffectation
	 */
	public String getNoteAffectation() {
		return noteAffectation;
	}

	/**
	 * [DossierBachelierDto.noteAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param noteAffectation the noteAffectation to set
	 */
	public void setNoteAffectation(String noteAffectation) {
		this.noteAffectation = noteAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementRecours] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeEtablissementRecours
	 */
	public String getRefCodeEtablissementRecours() {
		return refCodeEtablissementRecours;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementRecours] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeEtablissementRecours the refCodeEtablissementRecours to set
	 */
	public void setRefCodeEtablissementRecours(String refCodeEtablissementRecours) {
		this.refCodeEtablissementRecours = refCodeEtablissementRecours;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereRecours] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeFiliereRecours
	 */
	public String getRefCodeFiliereRecours() {
		return refCodeFiliereRecours;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereRecours] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeFiliereRecours the refCodeFiliereRecours to set
	 */
	public void setRefCodeFiliereRecours(String refCodeFiliereRecours) {
		this.refCodeFiliereRecours = refCodeFiliereRecours;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementOrientation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeEtablissementOrientation
	 */
	public String getRefCodeEtablissementOrientation() {
		return refCodeEtablissementOrientation;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementOrientation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeEtablissementOrientation the refCodeEtablissementOrientation to set
	 */
	public void setRefCodeEtablissementOrientation(
			String refCodeEtablissementOrientation) {
		this.refCodeEtablissementOrientation = refCodeEtablissementOrientation;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereOrientation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeFiliereOrientation
	 */
	public String getRefCodeFiliereOrientation() {
		return refCodeFiliereOrientation;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereOrientation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeFiliereOrientation the refCodeFiliereOrientation to set
	 */
	public void setRefCodeFiliereOrientation(String refCodeFiliereOrientation) {
		this.refCodeFiliereOrientation = refCodeFiliereOrientation;
	}

	/**
	 * [DossierBachelierDto.refCodeTypeAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeTypeAffectation
	 */
	public String getRefCodeTypeAffectation() {
		return refCodeTypeAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeTypeAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeTypeAffectation the refCodeTypeAffectation to set
	 */
	public void setRefCodeTypeAffectation(String refCodeTypeAffectation) {
		this.refCodeTypeAffectation = refCodeTypeAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeEtablissementAffectation
	 */
	public String getRefCodeEtablissementAffectation() {
		return refCodeEtablissementAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeEtablissementAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeEtablissementAffectation the refCodeEtablissementAffectation to set
	 */
	public void setRefCodeEtablissementAffectation(
			String refCodeEtablissementAffectation) {
		this.refCodeEtablissementAffectation = refCodeEtablissementAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @return the refCodeFiliereAffectation
	 */
	public String getRefCodeFiliereAffectation() {
		return refCodeFiliereAffectation;
	}

	/**
	 * [DossierBachelierDto.refCodeFiliereAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:36:10
	 * @param refCodeFiliereAffectation the refCodeFiliereAffectation to set
	 */
	public void setRefCodeFiliereAffectation(String refCodeFiliereAffectation) {
		this.refCodeFiliereAffectation = refCodeFiliereAffectation;
	}

	/**
	 * [DossierBachelierDto.libelleFiliereAffectation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:48:09
	 * @return the libelleFiliereAffectation
	 */
	public String getLibelleFiliereAffectation() {
		return libelleFiliereAffectation;
	}

	/**
	 * [DossierBachelierDto.libelleFiliereAffectation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:48:09
	 * @param libelleFiliereAffectation the libelleFiliereAffectation to set
	 */
	public void setLibelleFiliereAffectation(String libelleFiliereAffectation) {
		this.libelleFiliereAffectation = libelleFiliereAffectation;
	}

	/**
	 * [DossierBachelierDto.codeFiliereInscription] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:50:21
	 * @return the codeFiliereInscription
	 */
	public String getCodeFiliereInscription() {
		return codeFiliereInscription;
	}

	/**
	 * [DossierBachelierDto.codeFiliereInscription] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  00:50:21
	 * @param codeFiliereInscription the codeFiliereInscription to set
	 */
	public void setCodeFiliereInscription(String codeFiliereInscription) {
		this.codeFiliereInscription = codeFiliereInscription;
	}

	/**
	 * [DossierBachelierDto.photoEtudiant] Getter 
	 * @author rlaib on : 9 nov. 2014  15:18:51
	 * @return the photoEtudiant
	 */
	public String getPhotoEtudiant() {
		return photoEtudiant;
	}

	/**
	 * [DossierBachelierDto.photoEtudiant] Setter 
	 * @author rlaib on : 9 nov. 2014  15:18:51
	 * @param photoEtudiant the photoEtudiant to set
	 */
	public void setPhotoEtudiant(String photoEtudiant) {
		this.photoEtudiant = photoEtudiant;
	}

	
	
}
