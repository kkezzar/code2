/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.GroupePedagogiqueDto.java] 
 * @author MAKERRI Sofiane on : 8 juin 2014  10:02:14
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;
import java.util.List;

/**
 * @author MAKERRI Sofiane on : 8 juin 2014 10:02:14
 */
public class GroupePedagogiqueDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:14:57
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * [GroupePedagogiqueDto.GroupePedagogiqueDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:02:14
	 */
	private Integer id;
	private Integer oofId;
	private Integer ofId;
	private String ofCode;
	private String ofLibelleFr;
	private String ofRefCodeDomaine;
	private String ofRefCodeFiliere;
	private String ofRefCodeSpecialite;
	private String ofRefCodeCycle;
	private String ofRefCodeTypeFormation;
	private Integer idAnneeAcademique;
	private String codeAnneeAcademique;
	private Integer ofEffectifMin;
	private Integer ofEffectifMax;
	private Integer capaciteMin;
	private Integer capaciteMax;
	
	private Integer periodeId;
	private String periodeCode;
	private String periodeLibelleLongLt;
	private String periodeLibelleLongAr;
	private String periodeLibelleCourtLt;
	private String periodeLibelleCourtAr;
	private Integer periodeRang;
	private Integer periodeCredit;
	private Integer periodeNiveauId;
	private String periodeNiveauCode;
	private Integer periodeCycleId;
	private String periodeCycleCode;
	

	private Integer ncTypeGroupeId;
	private String ncTypeGroupeCode;
	/**** ref groupe ****/
	private String refLlLatinTypeGroupe;
	private String refLlArabeTypeGroupe;
	private Date dateCreation;
	private Integer idGroupe;
	private String codeGroupe;
	private String lcGroupe;
	private String lcGroupeArabe;
	private String llGroupe;
	private String llGroupeArabe;
	private Integer idEtablissement;
	private String codeEtablissement;
	
	private String refCodeSemestre;
	private String nomAffichage;
	private String nomAffichageArabe;
	private Date dateAssociation;
	private Boolean mcOptionnelle;
	/********** Section ***************/
	private Integer idSection;
	private String codeSection;
	private String nomSection;
	private Integer capaciteMinSection;
	private Integer capaciteMaxSection;
	private Integer rattachementMcIdSection;
	private Boolean sectionSansGroupe;
//	private String refEtablissementCode;
	private List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos;

	/**
	 * [GroupePedagogiqueDto.dateCreation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 12:21:53
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * [GroupePedagogiqueDto.dateCreation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 12:21:53
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public GroupePedagogiqueDto() {
		super();
	}

	/**
	 * [GroupePedagogiqueDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [GroupePedagogiqueDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * [GroupePedagogiqueDto.capaciteMin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @return the capaciteMin
	 */
	public Integer getCapaciteMin() {
		return capaciteMin;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @param capaciteMin
	 *            the capaciteMin to set
	 */
	public void setCapaciteMin(Integer capaciteMin) {
		this.capaciteMin = capaciteMin;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @return the capaciteMax
	 */
	public Integer getCapaciteMax() {
		return capaciteMax;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 10:29:44
	 * @param capaciteMax
	 *            the capaciteMax to set
	 */
	public void setCapaciteMax(Integer capaciteMax) {
		this.capaciteMax = capaciteMax;
	}

	

	/**
	 * [GroupePedagogiqueDto.lcGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.lcGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @param lcGroupe
	 *            the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.lcGroupeArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}

	/**
	 * [GroupePedagogiqueDto.lcGroupeArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @param lcGroupeArabe
	 *            the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}

	/**
	 * [GroupePedagogiqueDto.llGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.llGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @param llGroupe
	 *            the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.llGroupeArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}

	/**
	 * [GroupePedagogiqueDto.llGroupeArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:01:32
	 * @param llGroupeArabe
	 *            the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}

	/**
	 * [GroupePedagogiqueDto.idGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:41:51
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.idGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 juin 2014 17:41:51
	 * @param idGroupe
	 *            the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.ofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofId
	 */
	public Integer getOfId() {
		return ofId;
	}

	/**
	 * [GroupePedagogiqueDto.ofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofId
	 *            the ofId to set
	 */
	public void setOfId(Integer ofId) {
		this.ofId = ofId;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeDomaine] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofRefCodeDomaine
	 */
	public String getOfRefCodeDomaine() {
		return ofRefCodeDomaine;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeDomaine] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofRefCodeDomaine
	 *            the ofRefCodeDomaine to set
	 */
	public void setOfRefCodeDomaine(String ofRefCodeDomaine) {
		this.ofRefCodeDomaine = ofRefCodeDomaine;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeFiliere] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofRefCodeFiliere
	 */
	public String getOfRefCodeFiliere() {
		return ofRefCodeFiliere;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeFiliere] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofRefCodeFiliere
	 *            the ofRefCodeFiliere to set
	 */
	public void setOfRefCodeFiliere(String ofRefCodeFiliere) {
		this.ofRefCodeFiliere = ofRefCodeFiliere;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeSpecialite] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofRefCodeSpecialite
	 */
	public String getOfRefCodeSpecialite() {
		return ofRefCodeSpecialite;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeSpecialite] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofRefCodeSpecialite
	 *            the ofRefCodeSpecialite to set
	 */
	public void setOfRefCodeSpecialite(String ofRefCodeSpecialite) {
		this.ofRefCodeSpecialite = ofRefCodeSpecialite;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeCycle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofRefCodeCycle
	 */
	public String getOfRefCodeCycle() {
		return ofRefCodeCycle;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeCycle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofRefCodeCycle
	 *            the ofRefCodeCycle to set
	 */
	public void setOfRefCodeCycle(String ofRefCodeCycle) {
		this.ofRefCodeCycle = ofRefCodeCycle;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeTypeFormation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofRefCodeTypeFormation
	 */
	public String getOfRefCodeTypeFormation() {
		return ofRefCodeTypeFormation;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeTypeFormation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofRefCodeTypeFormation
	 *            the ofRefCodeTypeFormation to set
	 */
	public void setOfRefCodeTypeFormation(String ofRefCodeTypeFormation) {
		this.ofRefCodeTypeFormation = ofRefCodeTypeFormation;
	}

	/**
	 * [GroupePedagogiqueDto.idAnneeAcademique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 17:22:00
	 * @return the idAnneeAcademique
	 */
	public Integer getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	/**
	 * [GroupePedagogiqueDto.idAnneeAcademique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 17:22:00
	 * @param idAnneeAcademique
	 *            the idAnneeAcademique to set
	 */
	public void setIdAnneeAcademique(Integer idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * [GroupePedagogiqueDto.ofEffectifMin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofEffectifMin
	 */
	public Integer getOfEffectifMin() {
		return ofEffectifMin;
	}

	/**
	 * [GroupePedagogiqueDto.ofEffectifMin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofEffectifMin
	 *            the ofEffectifMin to set
	 */
	public void setOfEffectifMin(Integer ofEffectifMin) {
		this.ofEffectifMin = ofEffectifMin;
	}

	/**
	 * [GroupePedagogiqueDto.ofEffectifMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @return the ofEffectifMax
	 */
	public Integer getOfEffectifMax() {
		return ofEffectifMax;
	}

	/**
	 * [GroupePedagogiqueDto.ofEffectifMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:21:31
	 * @param ofEffectifMax
	 *            the ofEffectifMax to set
	 */
	public void setOfEffectifMax(Integer ofEffectifMax) {
		this.ofEffectifMax = ofEffectifMax;
	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:22:09
	 * @return the ofRefCodeEtablissement
	 */
//	public String getOfRefCodeEtablissement() {
//		return ofRefCodeEtablissement;
//	}

	/**
	 * [GroupePedagogiqueDto.ofRefCodeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:22:09
	 * @param ofRefCodeEtablissement
	 *            the ofRefCodeEtablissement to set
	 */
//	public void setOfRefCodeEtablissement(String ofRefCodeEtablissement) {
//		this.ofRefCodeEtablissement = ofRefCodeEtablissement;
//	}

	/**
	 * [GroupePedagogiqueDto.ofCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:31:57
	 * @return the ofCode
	 */
	public String getOfCode() {
		return ofCode;
	}

	/**
	 * [GroupePedagogiqueDto.ofCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 08:31:57
	 * @param ofCode
	 *            the ofCode to set
	 */
	public void setOfCode(String ofCode) {
		this.ofCode = ofCode;
	}

	/**
	 * [GroupePedagogiqueDto.refCodeSemestre] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 11:28:29
	 * @return the refCodeSemestre
	 */
	public String getRefCodeSemestre() {
		return refCodeSemestre;
	}

	/**
	 * [GroupePedagogiqueDto.refCodeSemestre] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 11:28:29
	 * @param refCodeSemestre
	 *            the refCodeSemestre to set
	 */
	public void setRefCodeSemestre(String refCodeSemestre) {
		this.refCodeSemestre = refCodeSemestre;
	}

	/**
	 * [GroupePedagogiqueDto.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 17:39:09
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [GroupePedagogiqueDto.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 juin 2014 17:39:09
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [GroupePedagogiqueDto.refLlLatinTypeGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 10:35:21
	 * @return the refLlLatinTypeGroupe
	 */
	public String getRefLlLatinTypeGroupe() {
		return refLlLatinTypeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.refLlLatinTypeGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 10:35:21
	 * @param refLlLatinTypeGroupe
	 *            the refLlLatinTypeGroupe to set
	 */
	public void setRefLlLatinTypeGroupe(String refLlLatinTypeGroupe) {
		this.refLlLatinTypeGroupe = refLlLatinTypeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.refLlArabeTypeGroupe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 10:35:21
	 * @return the refLlArabeTypeGroupe
	 */
	public String getRefLlArabeTypeGroupe() {
		return refLlArabeTypeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.refLlArabeTypeGroupe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 10:35:21
	 * @param refLlArabeTypeGroupe
	 *            the refLlArabeTypeGroupe to set
	 */
	public void setRefLlArabeTypeGroupe(String refLlArabeTypeGroupe) {
		this.refLlArabeTypeGroupe = refLlArabeTypeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.codeAnneeAcademique] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 17:53:12
	 * @return the codeAnneeAcademique
	 */
	public String getCodeAnneeAcademique() {
		return codeAnneeAcademique;
	}

	/**
	 * [GroupePedagogiqueDto.codeAnneeAcademique] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 juin 2014 17:53:12
	 * @param codeAnneeAcademique
	 *            the codeAnneeAcademique to set
	 */
	public void setCodeAnneeAcademique(String codeAnneeAcademique) {
		this.codeAnneeAcademique = codeAnneeAcademique;
	}

	/**
	 * [GroupePedagogiqueDto.refCodeEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 08:49:41
	 * @return the refCodeEtablissement
	 */
//	public String getRefCodeEtablissement() {
//		return refCodeEtablissement;
//	}

	/**
	 * [GroupePedagogiqueDto.refCodeEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 15 juin 2014 08:49:41
	 * @param refCodeEtablissement
	 *            the refCodeEtablissement to set
	 */
//	public void setRefCodeEtablissement(String refCodeEtablissement) {
//		this.refCodeEtablissement = refCodeEtablissement;
//	}
	
	

	/**
	 * [GroupePedagogiqueDto.nomAffichage] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:15:50
	 * @return the nomAffichage
	 */
	public String getNomAffichage() {
		return nomAffichage;
	}

	/**
	 * [GroupePedagogiqueDto.codeEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  15:39:12
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [GroupePedagogiqueDto.codeEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  15:39:12
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [GroupePedagogiqueDto.nomAffichage] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:15:50
	 * @param nomAffichage
	 *            the nomAffichage to set
	 */
	public void setNomAffichage(String nomAffichage) {
		this.nomAffichage = nomAffichage;
	}

	/**
	 * [GroupePedagogiqueDto.idSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:43:21
	 * @return the idSection
	 */
	public Integer getIdSection() {
		return idSection;
	}

	/**
	 * [GroupePedagogiqueDto.idSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:43:21
	 * @param idSection
	 *            the idSection to set
	 */
	public void setIdSection(Integer idSection) {
		this.idSection = idSection;
	}

	/**
	 * [GroupePedagogiqueDto.codeSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @return the codeSection
	 */
	public String getCodeSection() {
		return codeSection;
	}

	/**
	 * [GroupePedagogiqueDto.codeSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @param codeSection
	 *            the codeSection to set
	 */
	public void setCodeSection(String codeSection) {
		this.codeSection = codeSection;
	}

	/**
	 * [GroupePedagogiqueDto.nomSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @return the nomSection
	 */
	public String getNomSection() {
		return nomSection;
	}

	/**
	 * [GroupePedagogiqueDto.nomSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @param nomSection
	 *            the nomSection to set
	 */
	public void setNomSection(String nomSection) {
		this.nomSection = nomSection;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMinSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @return the capaciteMinSection
	 */
	public Integer getCapaciteMinSection() {
		return capaciteMinSection;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMinSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @param capaciteMinSection
	 *            the capaciteMinSection to set
	 */
	public void setCapaciteMinSection(Integer capaciteMinSection) {
		this.capaciteMinSection = capaciteMinSection;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMaxSection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @return the capaciteMaxSection
	 */
	public Integer getCapaciteMaxSection() {
		return capaciteMaxSection;
	}

	/**
	 * [GroupePedagogiqueDto.capaciteMaxSection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 juin 2014 08:58:28
	 * @param capaciteMaxSection
	 *            the capaciteMaxSection to set
	 */
	public void setCapaciteMaxSection(Integer capaciteMaxSection) {
		this.capaciteMaxSection = capaciteMaxSection;
	}


	/**
	 * [GroupePedagogiqueDto.dateAssociation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 août 2014 13:48:13
	 * @return the dateAssociation
	 */
	public Date getDateAssociation() {
		return dateAssociation;
	}

	/**
	 * [GroupePedagogiqueDto.dateAssociation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 août 2014 13:48:13
	 * @param dateAssociation
	 *            the dateAssociation to set
	 */
	public void setDateAssociation(Date dateAssociation) {
		this.dateAssociation = dateAssociation;
	}

	


	/**
	 * [GroupePedagogiqueDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeNiveauId] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeNiveauId
	 */
	public Integer getPeriodeNiveauId() {
		return periodeNiveauId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeNiveauId] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeNiveauId the periodeNiveauId to set
	 */
	public void setPeriodeNiveauId(Integer periodeNiveauId) {
		this.periodeNiveauId = periodeNiveauId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeNiveauCode] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeNiveauCode
	 */
	public String getPeriodeNiveauCode() {
		return periodeNiveauCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodeNiveauCode] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeNiveauCode the periodeNiveauCode to set
	 */
	public void setPeriodeNiveauCode(String periodeNiveauCode) {
		this.periodeNiveauCode = periodeNiveauCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCycleId] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeCycleId
	 */
	public Integer getPeriodeCycleId() {
		return periodeCycleId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCycleId] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeCycleId the periodeCycleId to set
	 */
	public void setPeriodeCycleId(Integer periodeCycleId) {
		this.periodeCycleId = periodeCycleId;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCycleCode] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeCycleCode
	 */
	public String getPeriodeCycleCode() {
		return periodeCycleCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCycleCode] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeCycleCode the periodeCycleCode to set
	 */
	public void setPeriodeCycleCode(String periodeCycleCode) {
		this.periodeCycleCode = periodeCycleCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodecode] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodecode
	 */
	public String getPeriodeCode() {
		return periodeCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCode] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodecode the periodecode to set
	 */
	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleLongLt] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeLibelleLongLt
	 */
	public String getPeriodeLibelleLongLt() {
		return periodeLibelleLongLt;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleLongLt] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeLibelleLongLt the periodeLibelleLongLt to set
	 */
	public void setPeriodeLibelleLongLt(String periodeLibelleLongLt) {
		this.periodeLibelleLongLt = periodeLibelleLongLt;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleLongAr] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeLibelleLongAr
	 */
	public String getPeriodeLibelleLongAr() {
		return periodeLibelleLongAr;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleLongAr] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeLibelleLongAr the periodeLibelleLongAr to set
	 */
	public void setPeriodeLibelleLongAr(String periodeLibelleLongAr) {
		this.periodeLibelleLongAr = periodeLibelleLongAr;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleCourtLt] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeLibelleCourtLt
	 */
	public String getPeriodeLibelleCourtLt() {
		return periodeLibelleCourtLt;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleCourtLt] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeLibelleCourtLt the periodeLibelleCourtLt to set
	 */
	public void setPeriodeLibelleCourtLt(String periodeLibelleCourtLt) {
		this.periodeLibelleCourtLt = periodeLibelleCourtLt;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleCourtAr] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeLibelleCourtAr
	 */
	public String getPeriodeLibelleCourtAr() {
		return periodeLibelleCourtAr;
	}

	/**
	 * [GroupePedagogiqueDto.periodeLibelleCourtAr] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeLibelleCourtAr the periodeLibelleCourtAr to set
	 */
	public void setPeriodeLibelleCourtAr(String periodeLibelleCourtAr) {
		this.periodeLibelleCourtAr = periodeLibelleCourtAr;
	}

	/**
	 * [GroupePedagogiqueDto.periodeRang] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeRang
	 */
	public Integer getPeriodeRang() {
		return periodeRang;
	}

	/**
	 * [GroupePedagogiqueDto.periodeRang] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeRang the periodeRang to set
	 */
	public void setPeriodeRang(Integer periodeRang) {
		this.periodeRang = periodeRang;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCredit] Getter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @return the periodeLCredit
	 */
	public Integer getPeriodeCredit() {
		return periodeCredit;
	}

	/**
	 * [GroupePedagogiqueDto.periodeCredit] Setter 
	 * @author MAKERRI Sofiane on : 8 sept. 2014  09:47:52
	 * @param periodeLCredit the periodeLCredit to set
	 */
	public void setPeriodeCredit(Integer periodeCredit) {
		this.periodeCredit = periodeCredit;
	}

	/**
	 * [GroupePedagogiqueDto.ofLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  18:04:10
	 * @return the ofLibelleFr
	 */
	public String getOfLibelleFr() {
		return ofLibelleFr;
	}

	/**
	 * [GroupePedagogiqueDto.ofLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  18:04:10
	 * @param ofLibelleFr the ofLibelleFr to set
	 */
	public void setOfLibelleFr(String ofLibelleFr) {
		this.ofLibelleFr = ofLibelleFr;
	}

	
	/**
	 * [GroupePedagogiqueDto.rattachementMcIdSection] Getter 
	 * @author MAKERRI Sofiane on : 15 sept. 2014  16:07:42
	 * @return the rattachementMcIdSection
	 */
	public Integer getRattachementMcIdSection() {
		return rattachementMcIdSection;
	}

	/**
	 * [GroupePedagogiqueDto.rattachementMcIdSection] Setter 
	 * @author MAKERRI Sofiane on : 15 sept. 2014  16:07:42
	 * @param rattachementMcIdSection the rattachementMcIdSection to set
	 */
	public void setRattachementMcIdSection(Integer rattachementMcIdSection) {
		this.rattachementMcIdSection = rattachementMcIdSection;
	}

	/**
	 * [GroupePedagogiqueDto.nomAffichageArabe] Getter 
	 * @author MAKERRI Sofiane on : 16 sept. 2014  11:08:09
	 * @return the nomAffichageArabe
	 */
	public String getNomAffichageArabe() {
		return nomAffichageArabe;
	}

	/**
	 * [GroupePedagogiqueDto.nomAffichageArabe] Setter 
	 * @author MAKERRI Sofiane on : 16 sept. 2014  11:08:09
	 * @param nomAffichageArabe the nomAffichageArabe to set
	 */
	public void setNomAffichageArabe(String nomAffichageArabe) {
		this.nomAffichageArabe = nomAffichageArabe;
	}



	/**
	 * [GroupePedagogiqueDto.ncTypeGroupeId] Getter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  11:11:39
	 * @return the ncTypeGroupeId
	 */
	public Integer getNcTypeGroupeId() {
		return ncTypeGroupeId;
	}

	/**
	 * [GroupePedagogiqueDto.ncTypeGroupeId] Setter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  11:11:39
	 * @param ncTypeGroupeId the ncTypeGroupeId to set
	 */
	public void setNcTypeGroupeId(Integer ncTypeGroupeId) {
		this.ncTypeGroupeId = ncTypeGroupeId;
	}

	/**
	 * [GroupePedagogiqueDto.ncTypeGroupeCode] Getter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  11:11:39
	 * @return the ncTypeGroupeCode
	 */
	public String getNcTypeGroupeCode() {
		return ncTypeGroupeCode;
	}

	/**
	 * [GroupePedagogiqueDto.ncTypeGroupeCode] Setter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  11:11:39
	 * @param ncTypeGroupeCode the ncTypeGroupeCode to set
	 */
	public void setNcTypeGroupeCode(String ncTypeGroupeCode) {
		this.ncTypeGroupeCode = ncTypeGroupeCode;
	}


	/**
	 * [GroupePedagogiqueDto.codeGroupe] Getter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  14:17:30
	 * @return the codeGroupe
	 */
	public String getCodeGroupe() {
		return codeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.codeGroupe] Setter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  14:17:30
	 * @param codeGroupe the codeGroupe to set
	 */
	public void setCodeGroupe(String codeGroupe) {
		this.codeGroupe = codeGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  14:26:07
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [GroupePedagogiqueDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 2 oct. 2014  14:26:07
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [GroupePedagogiqueDto.mcOptionnelle] Getter 
	 * @author MAKERRI Sofiane on : 6 oct. 2014  15:55:46
	 * @return the mcOptionnelle
	 */
	public Boolean getMcOptionnelle() {
		return mcOptionnelle;
	}

	/**
	 * [GroupePedagogiqueDto.mcOptionnelle] Setter 
	 * @author MAKERRI Sofiane on : 6 oct. 2014  15:55:46
	 * @param mcOptionnelle the mcOptionnelle to set
	 */
	public void setMcOptionnelle(Boolean mcOptionnelle) {
		this.mcOptionnelle = mcOptionnelle;
	}

	/**
	 * [GroupePedagogiqueDto.sectionSansGroupe] Getter 
	 * @author MAKERRI Sofiane on : 9 oct. 2014  17:39:16
	 * @return the sectionSansGroupe
	 */
	public Boolean getSectionSansGroupe() {
		return sectionSansGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.sectionSansGroupe] Setter 
	 * @author MAKERRI Sofiane on : 9 oct. 2014  17:39:16
	 * @param sectionSansGroupe the sectionSansGroupe to set
	 */
	public void setSectionSansGroupe(Boolean sectionSansGroupe) {
		this.sectionSansGroupe = sectionSansGroupe;
	}

	/**
	 * [GroupePedagogiqueDto.affectationGroupePedagogiqueDtos] Getter 
	 * @author MAKERRI Sofiane on : 27 janv. 2015  08:10:55
	 * @return the affectationGroupePedagogiqueDtos
	 */
	public List<AffectationGroupePedagogiqueDto> getAffectationGroupePedagogiqueDtos() {
		return affectationGroupePedagogiqueDtos;
	}

	/**
	 * [GroupePedagogiqueDto.affectationGroupePedagogiqueDtos] Setter 
	 * @author MAKERRI Sofiane on : 27 janv. 2015  08:10:55
	 * @param affectationGroupePedagogiqueDtos the affectationGroupePedagogiqueDtos to set
	 */
	public void setAffectationGroupePedagogiqueDtos(
			List<AffectationGroupePedagogiqueDto> affectationGroupePedagogiqueDtos) {
		this.affectationGroupePedagogiqueDtos = affectationGroupePedagogiqueDtos;
	}

	
	
}
