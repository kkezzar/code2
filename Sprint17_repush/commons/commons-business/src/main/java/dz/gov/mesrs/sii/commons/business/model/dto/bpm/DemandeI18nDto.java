package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;

public class DemandeI18nDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 24 avr. 2014  10:41:53
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Integer demandeId;
	private String objet;
	private String observations;
	private String langue;
    //Common info Demande
	private String code;
	private Date dateDemande;
	private Integer typeDemandeId;
	private Integer offreFormationId;
	private Integer idSituation;
	private Date dateCreation;
	
	//offre
	private Integer ofId ;
	private String ofCode ;
	private String ofRefCodeDomaine ;
	private String ofRefCodeFiliere ;
	private String ofRefCodeSpecialite;
	private String ofRefCodeCycle ;
	private String  ofRefCodeTypeFormation ;
	private String ofRefCodeVocation ;
	private String ofRefCodeEtablissement ;


	private String ofLibelleCourt;
	private String ofSpecialisation;
	private String ofDescription;
	private String ofLibelleDomaine;
	private String ofLibelleFiliere;
	private String ofLibelleSpecialite;
	private String ofLibelleCycle;
	private String ofLibelleVocation;
	private String ofLibelleTypeFormation;
	private String ofLibelleSituation;
	//Situation
	private String libelleSituation;
	
	public DemandeI18nDto() {
	}

	public DemandeI18nDto(int id) {
		this.id = id;
	}

	public DemandeI18nDto(int id, Integer demandeId, String objet,
			String observations, String langue) {
		this.id = id;
		this.demandeId = demandeId;
		this.objet = objet;
		this.observations = observations;
		this.langue = langue;
	}

	/**
	 * [DemandeI18nDto.id] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [DemandeI18nDto.id] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [DemandeI18nDto.demandeId] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @return the demandeId
	 */
	public Integer getDemandeId() {
		return demandeId;
	}

	/**
	 * [DemandeI18nDto.demandeId] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @param demandeId the demandeId to set
	 */
	public void setDemandeId(Integer demandeId) {
		this.demandeId = demandeId;
	}

	/**
	 * [DemandeI18nDto.objet] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * [DemandeI18nDto.objet] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * [DemandeI18nDto.observations] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * [DemandeI18nDto.observations] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * [DemandeI18nDto.langue] Getter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [DemandeI18nDto.langue] Setter 
	 * @author BELDI Jamel on : 24 avr. 2014  10:42:21
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [DemandeI18nDto.code] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [DemandeI18nDto.code] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [DemandeI18nDto.dateDemande] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @return the dateDemande
	 */
	public Date getDateDemande() {
		return dateDemande;
	}

	/**
	 * [DemandeI18nDto.dateDemande] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @param dateDemande the dateDemande to set
	 */
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}

	/**
	 * [DemandeI18nDto.typeDemandeId] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @return the typeDemandeId
	 */
	public Integer getTypeDemandeId() {
		return typeDemandeId;
	}

	/**
	 * [DemandeI18nDto.typeDemandeId] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @param typeDemandeId the typeDemandeId to set
	 */
	public void setTypeDemandeId(Integer typeDemandeId) {
		this.typeDemandeId = typeDemandeId;
	}

	/**
	 * [DemandeI18nDto.offreFormationId] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [DemandeI18nDto.offreFormationId] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [DemandeI18nDto.idSituation] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @return the idSituation
	 */
	public Integer getIdSituation() {
		return idSituation;
	}

	/**
	 * [DemandeI18nDto.idSituation] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  09:47:43
	 * @param idSituation the idSituation to set
	 */
	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [DemandeI18nDto.ofId] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofId
	 */
	public Integer getOfId() {
		return ofId;
	}

	/**
	 * [DemandeI18nDto.ofId] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofId the ofId to set
	 */
	public void setOfId(Integer ofId) {
		this.ofId = ofId;
	}

	/**
	 * [DemandeI18nDto.ofCode] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofCode
	 */
	public String getOfCode() {
		return ofCode;
	}

	/**
	 * [DemandeI18nDto.ofCode] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofCode the ofCode to set
	 */
	public void setOfCode(String ofCode) {
		this.ofCode = ofCode;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeDomaine] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeDomaine
	 */
	public String getOfRefCodeDomaine() {
		return ofRefCodeDomaine;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeDomaine] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeDomaine the ofRefCodeDomaine to set
	 */
	public void setOfRefCodeDomaine(String ofRefCodeDomaine) {
		this.ofRefCodeDomaine = ofRefCodeDomaine;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeFiliere] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeFiliere
	 */
	public String getOfRefCodeFiliere() {
		return ofRefCodeFiliere;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeFiliere] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeFiliere the ofRefCodeFiliere to set
	 */
	public void setOfRefCodeFiliere(String ofRefCodeFiliere) {
		this.ofRefCodeFiliere = ofRefCodeFiliere;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeSpecialite] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeSpecialite
	 */
	public String getOfRefCodeSpecialite() {
		return ofRefCodeSpecialite;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeSpecialite] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeSpecialite the ofRefCodeSpecialite to set
	 */
	public void setOfRefCodeSpecialite(String ofRefCodeSpecialite) {
		this.ofRefCodeSpecialite = ofRefCodeSpecialite;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeCycle] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeCycle
	 */
	public String getOfRefCodeCycle() {
		return ofRefCodeCycle;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeCycle] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeCycle the ofRefCodeCycle to set
	 */
	public void setOfRefCodeCycle(String ofRefCodeCycle) {
		this.ofRefCodeCycle = ofRefCodeCycle;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeTypeFormation] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeTypeFormation
	 */
	public String getOfRefCodeTypeFormation() {
		return ofRefCodeTypeFormation;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeTypeFormation] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeTypeFormation the ofRefCodeTypeFormation to set
	 */
	public void setOfRefCodeTypeFormation(String ofRefCodeTypeFormation) {
		this.ofRefCodeTypeFormation = ofRefCodeTypeFormation;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeVocation] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeVocation
	 */
	public String getOfRefCodeVocation() {
		return ofRefCodeVocation;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeVocation] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeVocation the ofRefCodeVocation to set
	 */
	public void setOfRefCodeVocation(String ofRefCodeVocation) {
		this.ofRefCodeVocation = ofRefCodeVocation;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeEtablissement] Getter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @return the ofRefCodeEtablissement
	 */
	public String getOfRefCodeEtablissement() {
		return ofRefCodeEtablissement;
	}

	/**
	 * [DemandeI18nDto.ofRefCodeEtablissement] Setter 
	 * @author BELDI Jamel on : 27 avr. 2014  14:55:35
	 * @param ofRefCodeEtablissement the ofRefCodeEtablissement to set
	 */
	public void setOfRefCodeEtablissement(String ofRefCodeEtablissement) {
		this.ofRefCodeEtablissement = ofRefCodeEtablissement;
	}

	/**
	 * [DemandeI18nDto.dateCreation] Getter 
	 * @author BELDI Jamel on : 29 avr. 2014  11:01:08
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [DemandeI18nDto.dateCreation] Setter 
	 * @author BELDI Jamel on : 29 avr. 2014  11:01:08
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	private String ofLibelle;
	/**
	 * [DemandeI18nDto.ofLibelle] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelle
	 */
	public String getOfLibelle() {
		return ofLibelle;
	}

	/**
	 * [DemandeI18nDto.ofLibelle] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelle the ofLibelle to set
	 */
	public void setOfLibelle(String ofLibelle) {
		this.ofLibelle = ofLibelle;
	}

	/**
	 * [DemandeI18nDto.ofLibelleCourt] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleCourt
	 */
	public String getOfLibelleCourt() {
		return ofLibelleCourt;
	}

	/**
	 * [DemandeI18nDto.ofLibelleCourt] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleCourt the ofLibelleCourt to set
	 */
	public void setOfLibelleCourt(String ofLibelleCourt) {
		this.ofLibelleCourt = ofLibelleCourt;
	}

	/**
	 * [DemandeI18nDto.ofSpecialisation] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofSpecialisation
	 */
	public String getOfSpecialisation() {
		return ofSpecialisation;
	}

	/**
	 * [DemandeI18nDto.ofSpecialisation] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofSpecialisation the ofSpecialisation to set
	 */
	public void setOfSpecialisation(String ofSpecialisation) {
		this.ofSpecialisation = ofSpecialisation;
	}

	/**
	 * [DemandeI18nDto.ofDescription] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofDescription
	 */
	public String getOfDescription() {
		return ofDescription;
	}

	/**
	 * [DemandeI18nDto.ofDescription] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofDescription the ofDescription to set
	 */
	public void setOfDescription(String ofDescription) {
		this.ofDescription = ofDescription;
	}

	/**
	 * [DemandeI18nDto.ofLibelleDomaine] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleDomaine
	 */
	public String getOfLibelleDomaine() {
		return ofLibelleDomaine;
	}

	/**
	 * [DemandeI18nDto.ofLibelleDomaine] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleDomaine the ofLibelleDomaine to set
	 */
	public void setOfLibelleDomaine(String ofLibelleDomaine) {
		this.ofLibelleDomaine = ofLibelleDomaine;
	}

	/**
	 * [DemandeI18nDto.ofLibelleFiliere] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleFiliere
	 */
	public String getOfLibelleFiliere() {
		return ofLibelleFiliere;
	}

	/**
	 * [DemandeI18nDto.ofLibelleFiliere] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleFiliere the ofLibelleFiliere to set
	 */
	public void setOfLibelleFiliere(String ofLibelleFiliere) {
		this.ofLibelleFiliere = ofLibelleFiliere;
	}

	/**
	 * [DemandeI18nDto.ofLibelleSpecialite] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleSpecialite
	 */
	public String getOfLibelleSpecialite() {
		return ofLibelleSpecialite;
	}

	/**
	 * [DemandeI18nDto.ofLibelleSpecialite] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleSpecialite the ofLibelleSpecialite to set
	 */
	public void setOfLibelleSpecialite(String ofLibelleSpecialite) {
		this.ofLibelleSpecialite = ofLibelleSpecialite;
	}

	/**
	 * [DemandeI18nDto.ofLibelleCycle] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleCycle
	 */
	public String getOfLibelleCycle() {
		return ofLibelleCycle;
	}

	/**
	 * [DemandeI18nDto.ofLibelleCycle] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleCycle the ofLibelleCycle to set
	 */
	public void setOfLibelleCycle(String ofLibelleCycle) {
		this.ofLibelleCycle = ofLibelleCycle;
	}

	/**
	 * [DemandeI18nDto.ofLibelleVocation] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleVocation
	 */
	public String getOfLibelleVocation() {
		return ofLibelleVocation;
	}

	/**
	 * [DemandeI18nDto.ofLibelleVocation] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleVocation the ofLibelleVocation to set
	 */
	public void setOfLibelleVocation(String ofLibelleVocation) {
		this.ofLibelleVocation = ofLibelleVocation;
	}

	/**
	 * [DemandeI18nDto.ofLibelleTypeFormation] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleTypeFormation
	 */
	public String getOfLibelleTypeFormation() {
		return ofLibelleTypeFormation;
	}

	/**
	 * [DemandeI18nDto.ofLibelleTypeFormation] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleTypeFormation the ofLibelleTypeFormation to set
	 */
	public void setOfLibelleTypeFormation(String ofLibelleTypeFormation) {
		this.ofLibelleTypeFormation = ofLibelleTypeFormation;
	}

	/**
	 * [DemandeI18nDto.ofLibelleSituation] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @return the ofLibelleSituation
	 */
	public String getOfLibelleSituation() {
		return ofLibelleSituation;
	}

	/**
	 * [DemandeI18nDto.ofLibelleSituation] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:27:43
	 * @param ofLibelleSituation the ofLibelleSituation to set
	 */
	public void setOfLibelleSituation(String ofLibelleSituation) {
		this.ofLibelleSituation = ofLibelleSituation;
	}

	/**
	 * [DemandeI18nDto.libelleSituation] Getter 
	 * @author BELDI Jamel on : 4 mai 2014  11:54:12
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [DemandeI18nDto.libelleSituation] Setter 
	 * @author BELDI Jamel on : 4 mai 2014  11:54:12
	 * @param libelleSituation the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}
	
	
}
