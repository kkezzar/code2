package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

// Generated 12 mai 2014 12:43:56 by Hibernate Tools 3.6.0

import java.util.Date;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:12:54
 */

public class OuvertureOffreFormationDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 14:12:35
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	//private String refCodeEtablissement;
	private Integer effectifMin;
	private Integer effectifMax;
	private Date dateOuverture;
	private boolean estOuverte;
	private Date dateFermeture;
	// private OffreFormation offreFormation;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationlibelle;
	private String ofLibelleLongFr;
	private String ofLibelleLongAr;
	private String ofNumeroArrete;
	private String ofRefCodeDomaine;
	private String ofRefLibelleDomaine;
	private String ofRefCodeFiliere;
	private String ofRefLibelleFiliere;
	private String ofRefCodeSpecialite;
	private String ofRefLibelleSpecialite;
	private String ofRefCodeCycle;
	private String ofRefCodeTypeFormation;
	private String ofRefCodeVocation;
	private Date ofDateArrete;
	private Date ofDateCreation;
	
	// private AnneeAcademiqueDto anneeAcademique;
	private Integer anneeAcademiqueId;
	private Short anneeAcademiquePremiereAnnee;
	private Short anneeAcademiqueDeuxiemeAnnee;
	private String refLibelleEtablissement;
	
	//Cycle
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	
	// Domaine - Filiere  - Specialite
		private Integer idDomaine;
		private String codeDomaine;
		private String libelleDomaine;
		private Integer idFiliere;
		private String codeFiliere;
		private String libelleFiliere;
		private Integer idSpecialite;
		private String codeSpecialite;
		private String libelleSpecialite;
		private String libelleSpecialiteAr;
		/***** Etablissement *****/
		private Integer idEtablissement;
		private String codeEtablissement;
		private String lcEtablissementArabe;
		private String lcEtablissementLatin;
		private String llEtablissementArabe;
		private String llEtablissementLatin;
	
	public OuvertureOffreFormationDto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [OuvertureOffreFormationDto.effectifMin] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:16:46
	 * @return the effectifMin
	 */
	public Integer getEffectifMin() {
		return effectifMin;
	}

	/**
	 * [OuvertureOffreFormationDto.effectifMin] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:16:46
	 * @param effectifMin
	 *            the effectifMin to set
	 */
	public void setEffectifMin(Integer effectifMin) {
		this.effectifMin = effectifMin;
	}

	/**
	 * [OuvertureOffreFormationDto.effectifMax] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:16:46
	 * @return the effectifMax
	 */
	public Integer getEffectifMax() {
		return effectifMax;
	}

	/**
	 * [OuvertureOffreFormationDto.effectifMax] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 17:16:46
	 * @param effectifMax
	 *            the effectifMax to set
	 */
	public void setEffectifMax(Integer effectifMax) {
		this.effectifMax = effectifMax;
	}

	/**
	 * [OuvertureOffreFormationDto.dateOuverture] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @return the dateOuverture
	 */
	public Date getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * [OuvertureOffreFormationDto.dateOuverture] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @param dateOuverture
	 *            the dateOuverture to set
	 */
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	/**
	 * [OuvertureOffreFormationDto.estOuverte] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @return the estOuverte
	 */
	public boolean isEstOuverte() {
		return estOuverte;
	}

	/**
	 * [OuvertureOffreFormationDto.estOuverte] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @param estOuverte
	 *            the estOuverte to set
	 */
	public void setEstOuverte(boolean estOuverte) {
		this.estOuverte = estOuverte;
	}

	/**
	 * [OuvertureOffreFormationDto.dateFermeture] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @return the dateFermeture
	 */
	public Date getDateFermeture() {
		return dateFermeture;
	}

	/**
	 * [OuvertureOffreFormationDto.dateFermeture] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:54:12
	 * @param dateFermeture
	 *            the dateFermeture to set
	 */
	public void setDateFermeture(Date dateFermeture) {
		this.dateFermeture = dateFermeture;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @param offreFormationId
	 *            the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationCode] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationCode] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @param offreFormationCode
	 *            the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiquePremiereAnnee] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @return the anneeAcademiquePremiereAnnee
	 */
	public Short getAnneeAcademiquePremiereAnnee() {
		return anneeAcademiquePremiereAnnee;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiquePremiereAnnee] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @param anneeAcademiquePremiereAnnee
	 *            the anneeAcademiquePremiereAnnee to set
	 */
	public void setAnneeAcademiquePremiereAnnee(
			Short anneeAcademiquePremiereAnnee) {
		this.anneeAcademiquePremiereAnnee = anneeAcademiquePremiereAnnee;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiqueDeuxiemeAnnee] Getter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @return the anneeAcademiqueDeuxiemeAnnee
	 */
	public Short getAnneeAcademiqueDeuxiemeAnnee() {
		return anneeAcademiqueDeuxiemeAnnee;
	}

	/**
	 * [OuvertureOffreFormationDto.anneeAcademiqueDeuxiemeAnnee] Setter
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 15:55:59
	 * @param anneeAcademiqueDeuxiemeAnnee
	 *            the anneeAcademiqueDeuxiemeAnnee to set
	 */
	public void setAnneeAcademiqueDeuxiemeAnnee(
			Short anneeAcademiqueDeuxiemeAnnee) {
		this.anneeAcademiqueDeuxiemeAnnee = anneeAcademiqueDeuxiemeAnnee;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationlibelle] Getter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 15:57:43
	 * @return the offreFormationlibelle
	 */
	public String getOffreFormationlibelle() {
		return offreFormationlibelle;
	}

	/**
	 * [OuvertureOffreFormationDto.offreFormationlibelle] Setter
	 * 
	 * @author BELDI Jamel on : 13 mai 2014 15:57:43
	 * @param offreFormationlibelle
	 *            the offreFormationlibelle to set
	 */
	public void setOffreFormationlibelle(String offreFormationlibelle) {
		this.offreFormationlibelle = offreFormationlibelle;
	}

	/**
	 * [OuvertureOffreFormationDto.ofNumeroArrete] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofNumeroArrete
	 */
	public String getOfNumeroArrete() {
		return ofNumeroArrete;
	}

	/**
	 * [OuvertureOffreFormationDto.ofNumeroArrete] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofNumeroArrete
	 *            the ofNumeroArrete to set
	 */
	public void setOfNumeroArrete(String ofNumeroArrete) {
		this.ofNumeroArrete = ofNumeroArrete;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeDomaine
	 */
	public String getOfRefCodeDomaine() {
		return ofRefCodeDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeDomaine
	 *            the ofRefCodeDomaine to set
	 */
	public void setOfRefCodeDomaine(String ofRefCodeDomaine) {
		this.ofRefCodeDomaine = ofRefCodeDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeFiliere] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeFiliere
	 */
	public String getOfRefCodeFiliere() {
		return ofRefCodeFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeFiliere] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeFiliere
	 *            the ofRefCodeFiliere to set
	 */
	public void setOfRefCodeFiliere(String ofRefCodeFiliere) {
		this.ofRefCodeFiliere = ofRefCodeFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeSpecialite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeSpecialite
	 */
	public String getOfRefCodeSpecialite() {
		return ofRefCodeSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeSpecialite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeSpecialite
	 *            the ofRefCodeSpecialite to set
	 */
	public void setOfRefCodeSpecialite(String ofRefCodeSpecialite) {
		this.ofRefCodeSpecialite = ofRefCodeSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeCycle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeCycle
	 */
	public String getOfRefCodeCycle() {
		return ofRefCodeCycle;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeCycle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeCycle
	 *            the ofRefCodeCycle to set
	 */
	public void setOfRefCodeCycle(String ofRefCodeCycle) {
		this.ofRefCodeCycle = ofRefCodeCycle;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeTypeFormation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeTypeFormation
	 */
	public String getOfRefCodeTypeFormation() {
		return ofRefCodeTypeFormation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeTypeFormation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeTypeFormation
	 *            the ofRefCodeTypeFormation to set
	 */
	public void setOfRefCodeTypeFormation(String ofRefCodeTypeFormation) {
		this.ofRefCodeTypeFormation = ofRefCodeTypeFormation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeVocation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofRefCodeVocation
	 */
	public String getOfRefCodeVocation() {
		return ofRefCodeVocation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefCodeVocation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofRefCodeVocation
	 *            the ofRefCodeVocation to set
	 */
	public void setOfRefCodeVocation(String ofRefCodeVocation) {
		this.ofRefCodeVocation = ofRefCodeVocation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofDateArrete] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofDateArrete
	 */
	public Date getOfDateArrete() {
		return ofDateArrete;
	}

	/**
	 * [OuvertureOffreFormationDto.ofDateArrete] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofDateArrete
	 *            the ofDateArrete to set
	 */
	public void setOfDateArrete(Date ofDateArrete) {
		this.ofDateArrete = ofDateArrete;
	}

	/**
	 * [OuvertureOffreFormationDto.ofDateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @return the ofDateCreation
	 */
	public Date getOfDateCreation() {
		return ofDateCreation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofDateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 juil. 2014 16:04:50
	 * @param ofDateCreation
	 *            the ofDateCreation to set
	 */
	public void setOfDateCreation(Date ofDateCreation) {
		this.ofDateCreation = ofDateCreation;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleDomaine] Getter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @return the ofRefLibelleDomaine
	 */
	public String getOfRefLibelleDomaine() {
		return ofRefLibelleDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleDomaine] Setter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @param ofRefLibelleDomaine the ofRefLibelleDomaine to set
	 */
	public void setOfRefLibelleDomaine(String ofRefLibelleDomaine) {
		this.ofRefLibelleDomaine = ofRefLibelleDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleFiliere] Getter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @return the ofRefLibelleFiliere
	 */
	public String getOfRefLibelleFiliere() {
		return ofRefLibelleFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleFiliere] Setter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @param ofRefLibelleFiliere the ofRefLibelleFiliere to set
	 */
	public void setOfRefLibelleFiliere(String ofRefLibelleFiliere) {
		this.ofRefLibelleFiliere = ofRefLibelleFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleSpecialite] Getter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @return the ofRefLibelleSpecialite
	 */
	public String getOfRefLibelleSpecialite() {
		return ofRefLibelleSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.ofRefLibelleSpecialite] Setter 
	 * @author MAKERRI Sofiane on : 7 juil. 2014  16:38:37
	 * @param ofRefLibelleSpecialite the ofRefLibelleSpecialite to set
	 */
	public void setOfRefLibelleSpecialite(String ofRefLibelleSpecialite) {
		this.ofRefLibelleSpecialite = ofRefLibelleSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.refLibelleEtablissement] Getter 
	 * @author BELDI Jamel on : 10 sept. 2014  15:51:18
	 * @return the refLibelleEtablissement
	 */
	public String getRefLibelleEtablissement() {
		return refLibelleEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.refLibelleEtablissement] Setter 
	 * @author BELDI Jamel on : 10 sept. 2014  15:51:18
	 * @param refLibelleEtablissement the refLibelleEtablissement to set
	 */
	public void setRefLibelleEtablissement(String refLibelleEtablissement) {
		this.refLibelleEtablissement = refLibelleEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleId] Getter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleId] Setter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @param cycleId the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleCode] Getter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleCode] Setter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @param cycleCode the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleLibelleLongLt] Getter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [OuvertureOffreFormationDto.cycleLibelleLongLt] Setter 
	 * @author BELDI Jamel on : 15 sept. 2014  17:06:35
	 * @param cycleLibelleLongLt the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [OuvertureOffreFormationDto.ofLibelleLongFr] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  10:30:33
	 * @return the ofLibelleLongFr
	 */
	public String getOfLibelleLongFr() {
		return ofLibelleLongFr;
	}

	/**
	 * [OuvertureOffreFormationDto.ofLibelleLongFr] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  10:30:33
	 * @param ofLibelleLongFr the ofLibelleLongFr to set
	 */
	public void setOfLibelleLongFr(String ofLibelleLongFr) {
		this.ofLibelleLongFr = ofLibelleLongFr;
	}

	/**
	 * [OuvertureOffreFormationDto.idDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.idDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.codeDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the codeDomaine
	 */
	public String getCodeDomaine() {
		return codeDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.codeDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param codeDomaine the codeDomaine to set
	 */
	public void setCodeDomaine(String codeDomaine) {
		this.codeDomaine = codeDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleDomaine] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the libelleDomaine
	 */
	public String getLibelleDomaine() {
		return libelleDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleDomaine] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param libelleDomaine the libelleDomaine to set
	 */
	public void setLibelleDomaine(String libelleDomaine) {
		this.libelleDomaine = libelleDomaine;
	}

	/**
	 * [OuvertureOffreFormationDto.idFiliere] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the idFiliere
	 */
	public Integer getIdFiliere() {
		return idFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.idFiliere] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(Integer idFiliere) {
		this.idFiliere = idFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.codeFiliere] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the codeFiliere
	 */
	public String getCodeFiliere() {
		return codeFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.codeFiliere] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param codeFiliere the codeFiliere to set
	 */
	public void setCodeFiliere(String codeFiliere) {
		this.codeFiliere = codeFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleFiliere] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the libelleFiliere
	 */
	public String getLibelleFiliere() {
		return libelleFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleFiliere] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param libelleFiliere the libelleFiliere to set
	 */
	public void setLibelleFiliere(String libelleFiliere) {
		this.libelleFiliere = libelleFiliere;
	}

	/**
	 * [OuvertureOffreFormationDto.idSpecialite] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the idSpecialite
	 */
	public Integer getIdSpecialite() {
		return idSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.idSpecialite] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param idSpecialite the idSpecialite to set
	 */
	public void setIdSpecialite(Integer idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.codeSpecialite] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the codeSpecialite
	 */
	public String getCodeSpecialite() {
		return codeSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.codeSpecialite] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param codeSpecialite the codeSpecialite to set
	 */
	public void setCodeSpecialite(String codeSpecialite) {
		this.codeSpecialite = codeSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleSpecialite] Getter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @return the libelleSpecialite
	 */
	public String getLibelleSpecialite() {
		return libelleSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleSpecialite] Setter 
	 * @author MAKERRI Sofiane on : 27 nov. 2014  15:28:17
	 * @param libelleSpecialite the libelleSpecialite to set
	 */
	public void setLibelleSpecialite(String libelleSpecialite) {
		this.libelleSpecialite = libelleSpecialite;
	}

	/**
	 * [OuvertureOffreFormationDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.codeEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.codeEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [OuvertureOffreFormationDto.lcEtablissementArabe] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the lcEtablissementArabe
	 */
	public String getLcEtablissementArabe() {
		return lcEtablissementArabe;
	}

	/**
	 * [OuvertureOffreFormationDto.lcEtablissementArabe] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param lcEtablissementArabe the lcEtablissementArabe to set
	 */
	public void setLcEtablissementArabe(String lcEtablissementArabe) {
		this.lcEtablissementArabe = lcEtablissementArabe;
	}

	/**
	 * [OuvertureOffreFormationDto.lcEtablissementLatin] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the lcEtablissementLatin
	 */
	public String getLcEtablissementLatin() {
		return lcEtablissementLatin;
	}

	/**
	 * [OuvertureOffreFormationDto.lcEtablissementLatin] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param lcEtablissementLatin the lcEtablissementLatin to set
	 */
	public void setLcEtablissementLatin(String lcEtablissementLatin) {
		this.lcEtablissementLatin = lcEtablissementLatin;
	}

	/**
	 * [OuvertureOffreFormationDto.llEtablissementArabe] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * [OuvertureOffreFormationDto.llEtablissementArabe] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param llEtablissementArabe the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * [OuvertureOffreFormationDto.llEtablissementLatin] Getter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * [OuvertureOffreFormationDto.llEtablissementLatin] Setter 
	 * @author MAKERRI Sofiane on : 30 nov. 2014  12:27:13
	 * @param llEtablissementLatin the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * [OuvertureOffreFormationDto.ofLibelleLongAr] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  15:46:08
	 * @return the ofLibelleLongAr
	 */
	public String getOfLibelleLongAr() {
		return ofLibelleLongAr;
	}

	/**
	 * [OuvertureOffreFormationDto.ofLibelleLongAr] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  15:46:08
	 * @param ofLibelleLongAr the ofLibelleLongAr to set
	 */
	public void setOfLibelleLongAr(String ofLibelleLongAr) {
		this.ofLibelleLongAr = ofLibelleLongAr;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleSpecialiteAr] Getter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  15:46:41
	 * @return the libelleSpecialiteAr
	 */
	public String getLibelleSpecialiteAr() {
		return libelleSpecialiteAr;
	}

	/**
	 * [OuvertureOffreFormationDto.libelleSpecialiteAr] Setter 
	 * @author MAKERRI Sofiane on : 18 févr. 2015  15:46:41
	 * @param libelleSpecialiteAr the libelleSpecialiteAr to set
	 */
	public void setLibelleSpecialiteAr(String libelleSpecialiteAr) {
		this.libelleSpecialiteAr = libelleSpecialiteAr;
	}

	
	

}
