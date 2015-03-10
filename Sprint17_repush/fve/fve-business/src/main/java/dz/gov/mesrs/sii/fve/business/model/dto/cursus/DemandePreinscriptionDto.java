package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NcNamesDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class DemandePreinscriptionDto implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4923282850997158869L;

    private long id;
    private Integer individuId;
    private Integer titreAccesId;
    private DecisionDemandePreinscriptionDto decisionDto;
    private RefIndividuDto individuDto;
    private RefAdresseDto adresseDto;
    private RefAdresseElectroniqueDto adresseElectroniqueDto;
    private TitreAccesDto titreAccesDto;
    private Date date;
    // Generee , validee , rejetee
    private NcNamesDto statut;
    private Integer anneeAcademiqueId;
    private Integer cycleId;
    private Integer coordonneesId;
    private Integer niveauId;
    private Integer filiereId;
    private Integer specialiteId;
    private Integer etablissementId;
    private Integer domaineId;
    private String libelleFiliere;
    private String libelleDomaine;
    private String libelleSpecialite;
    private String libelleEtablissement;
    private String libelleNiveau;
    private String libelleCycle;
    private String codeEtablissement;
    private String nomDemandeur;
    private String prenomDemandeur;
    private Boolean generee;
    private Integer typeCoordonnee;
    private Integer nomenclatureByTypeAdresseId;
    private Integer nomenclatureByTypeAdresseElectroniqueId;
    private Integer nomenclatureByNatureAdresseElectroniqueId;
    private String situationCode;
    private String typeTitreLibelleLongFr;
    private String mentionLibelleLongFr;
    private String langueEtrangere1LibelleLongFr;
    private String langueEtrangere2LibelleLongFr;
    private String specilaliteLibelleLongFr;
    private String libellePaysObtentionLongFr;
    private String libelleDecision;
    private String civiliteLibelleLongFr;
    private String nationaliteLibelleLongFr;
    private String libelleAnneeAcademique;
    private String libelleAnneeAcademique1;
    private String libelleAnneeAcademique2;

    public DemandePreinscriptionDto() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Integer getIndividuId() {
	return individuId;
    }

    public void setIndividuId(Integer individuId) {
	this.individuId = individuId;
    }

    public Integer getTitreAccesId() {
	return titreAccesId;
    }

    public void setTitreAccesId(Integer titreAccesId) {
	this.titreAccesId = titreAccesId;
    }

    public DecisionDemandePreinscriptionDto getDecisionDto() {
	return decisionDto;
    }

    public void setDecisionDto(DecisionDemandePreinscriptionDto decisionDto) {
	this.decisionDto = decisionDto;
    }

    public RefIndividuDto getIndividuDto() {
	return individuDto;
    }

    public void setIndividuDto(RefIndividuDto individuDto) {
	this.individuDto = individuDto;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public NcNamesDto getStatut() {
	return statut;
    }

    public void setStatut(NcNamesDto statut) {
	this.statut = statut;
    }

    public Integer getAnneeAcademiqueId() {
	return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
	this.anneeAcademiqueId = anneeAcademiqueId;
    }

    public Integer getCycleId() {
	return cycleId;
    }

    public void setCycleId(Integer cycleId) {
	this.cycleId = cycleId;
    }

    public Integer getNiveauId() {
	return niveauId;
    }

    public void setNiveauId(Integer niveauId) {
	this.niveauId = niveauId;
    }

    public Integer getFiliereId() {
	return filiereId;
    }

    public void setFiliereId(Integer filiereId) {
	this.filiereId = filiereId;
    }

    public Integer getSpecialiteId() {
	return specialiteId;
    }

    public void setSpecialiteId(Integer specialiteId) {
	this.specialiteId = specialiteId;
    }

    public Integer getEtablissementId() {
	return etablissementId;
    }

    public void setEtablissementId(Integer etablissementId) {
	this.etablissementId = etablissementId;
    }

    public Integer getDomaineId() {
	return domaineId;
    }

    public void setDomaineId(Integer domaineId) {
	this.domaineId = domaineId;
    }

    public String getLibelleFiliere() {
	return libelleFiliere;
    }

    public void setLibelleFiliere(String libelleFiliere) {
	this.libelleFiliere = libelleFiliere;
    }

    public String getLibelleDomaine() {
	return libelleDomaine;
    }

    public void setLibelleDomaine(String libelleDomaine) {
	this.libelleDomaine = libelleDomaine;
    }

    public String getLibelleSpecialite() {
	return libelleSpecialite;
    }

    public void setLibelleSpecialite(String libelleSpecialite) {
	this.libelleSpecialite = libelleSpecialite;
    }

    public String getLibelleEtablissement() {
	return libelleEtablissement;
    }

    public void setLibelleEtablissement(String libelleEtablissement) {
	this.libelleEtablissement = libelleEtablissement;
    }

    public String getLibelleNiveau() {
	return libelleNiveau;
    }

    public void setLibelleNiveau(String libelleNiveau) {
	this.libelleNiveau = libelleNiveau;
    }

    public String getCodeEtablissement() {
	return codeEtablissement;
    }

    public void setCodeEtablissement(String codeEtablissement) {
	this.codeEtablissement = codeEtablissement;
    }

    public String getNomDemandeur() {
	return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
	this.nomDemandeur = nomDemandeur;
    }

    public String getPrenomDemandeur() {
	return prenomDemandeur;
    }

    public void setPrenomDemandeur(String prenomDemandeur) {
	this.prenomDemandeur = prenomDemandeur;
    }

    public Boolean getGeneree() {
	return generee;
    }

    public void setGeneree(Boolean generee) {
	this.generee = generee;
    }

    public RefAdresseDto getAdresseDto() {
	return adresseDto;
    }

    public void setAdresseDto(RefAdresseDto adresseDto) {
	this.adresseDto = adresseDto;
    }

    public RefAdresseElectroniqueDto getAdresseElectroniqueDto() {
	return adresseElectroniqueDto;
    }

    public void setAdresseElectroniqueDto(RefAdresseElectroniqueDto adresseElectroniqueDto) {
	this.adresseElectroniqueDto = adresseElectroniqueDto;
    }

    public TitreAccesDto getTitreAccesDto() {
	return titreAccesDto;
    }

    public void setTitreAccesDto(TitreAccesDto titreAccesDto) {
	this.titreAccesDto = titreAccesDto;
    }

    public Integer getCoordonneesId() {
	return coordonneesId;
    }

    public void setCoordonneesId(Integer coordonneesId) {
	this.coordonneesId = coordonneesId;
    }

    public Integer getTypeCoordonnee() {
	return typeCoordonnee;
    }

    public void setTypeCoordonnee(Integer typeCoordonnee) {
	this.typeCoordonnee = typeCoordonnee;
    }

    public Integer getNomenclatureByTypeAdresseId() {
	return nomenclatureByTypeAdresseId;
    }

    public void setNomenclatureByTypeAdresseId(Integer nomenclatureByTypeAdresseId) {
	this.nomenclatureByTypeAdresseId = nomenclatureByTypeAdresseId;
    }

    public Integer getNomenclatureByTypeAdresseElectroniqueId() {
	return nomenclatureByTypeAdresseElectroniqueId;
    }

    public void setNomenclatureByTypeAdresseElectroniqueId(Integer nomenclatureByTypeAdresseElectroniqueId) {
	this.nomenclatureByTypeAdresseElectroniqueId = nomenclatureByTypeAdresseElectroniqueId;
    }

    public Integer getNomenclatureByNatureAdresseElectroniqueId() {
	return nomenclatureByNatureAdresseElectroniqueId;
    }

    public void setNomenclatureByNatureAdresseElectroniqueId(Integer nomenclatureByNatureAdresseElectroniqueId) {
	this.nomenclatureByNatureAdresseElectroniqueId = nomenclatureByNatureAdresseElectroniqueId;
    }

    public String getSituationCode() {
	return situationCode;
    }

    public void setSituationCode(String situationCode) {
	this.situationCode = situationCode;
    }

    public String getTypeTitreLibelleLongFr() {
	return typeTitreLibelleLongFr;
    }

    public void setTypeTitreLibelleLongFr(String typeTitreLibelleLongFr) {
	this.typeTitreLibelleLongFr = typeTitreLibelleLongFr;
    }

    public String getMentionLibelleLongFr() {
	return mentionLibelleLongFr;
    }

    public void setMentionLibelleLongFr(String mentionLibelleLongFr) {
	this.mentionLibelleLongFr = mentionLibelleLongFr;
    }

    public String getLangueEtrangere1LibelleLongFr() {
	return langueEtrangere1LibelleLongFr;
    }

    public void setLangueEtrangere1LibelleLongFr(String langueEtrangere1LibelleLongFr) {
	this.langueEtrangere1LibelleLongFr = langueEtrangere1LibelleLongFr;
    }

    public String getLangueEtrangere2LibelleLongFr() {
	return langueEtrangere2LibelleLongFr;
    }

    public void setLangueEtrangere2LibelleLongFr(String langueEtrangere2LibelleLongFr) {
	this.langueEtrangere2LibelleLongFr = langueEtrangere2LibelleLongFr;
    }

    public String getSpecilaliteLibelleLongFr() {
	return specilaliteLibelleLongFr;
    }

    public void setSpecilaliteLibelleLongFr(String specilaliteLibelleLongFr) {
	this.specilaliteLibelleLongFr = specilaliteLibelleLongFr;
    }

    public String getLibellePaysObtentionLongFr() {
	return libellePaysObtentionLongFr;
    }

    public void setLibellePaysObtentionLongFr(String libellePaysObtentionLongFr) {
	this.libellePaysObtentionLongFr = libellePaysObtentionLongFr;
    }

    public String getLibelleDecision() {
	return libelleDecision;
    }

    public void setLibelleDecision(String libelleDecision) {
	this.libelleDecision = libelleDecision;
    }

    public String getCiviliteLibelleLongFr() {
	return civiliteLibelleLongFr;
    }

    public void setCiviliteLibelleLongFr(String civiliteLibelleLongFr) {
	this.civiliteLibelleLongFr = civiliteLibelleLongFr;
    }

    public String getNationaliteLibelleLongFr() {
	return nationaliteLibelleLongFr;
    }

    public void setNationaliteLibelleLongFr(String nationaliteLibelleLongFr) {
	this.nationaliteLibelleLongFr = nationaliteLibelleLongFr;
    }

    public String getLibelleAnneeAcademique() {
	if (StringUtils.isEmpty(libelleAnneeAcademique1)) {
	    return "";
	} else {
	    return libelleAnneeAcademique1 + "/" + libelleAnneeAcademique2;
	}

    }

    public void setLibelleAnneeAcademique(String libelleAnneeAcademique) {
	this.libelleAnneeAcademique = libelleAnneeAcademique;
    }

    public String getLibelleCycle() {
	return libelleCycle;
    }

    public void setLibelleCycle(String libelleCycle) {
	this.libelleCycle = libelleCycle;
    }

    public String getLibelleAnneeAcademique1() {
	return libelleAnneeAcademique1;
    }

    public void setLibelleAnneeAcademique1(String libelleAnneeAcademique1) {
	this.libelleAnneeAcademique1 = libelleAnneeAcademique1;
    }

    public String getLibelleAnneeAcademique2() {
	return libelleAnneeAcademique2;
    }

    public void setLibelleAnneeAcademique2(String libelleAnneeAcademique2) {
	this.libelleAnneeAcademique2 = libelleAnneeAcademique2;
    }

}
