/**
 * 
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import dz.gov.mesrs.sii.commons.data.util.UtilConstant;

/**
 * @author jbeldi
 * 
 */
@XmlRootElement(name = "RefContratDto")
public class RefContratDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 */
	/**
	 * info refContrat
	 * 
	 */
	private Integer id;
	private String identifiant;
	private String objetContratFr;
	private Date dateCreation;
	private Date dateDebutValidite;
	private Date dateFinValidite;
	private Short duree;
	private BigDecimal montantHt;
	private BigDecimal montantTva;
	private BigDecimal montantTtc;
	private String objetContratAr;
	private String observation;
	private Boolean avenant;
	private Boolean reconductible;
	private String referenceDocumentaire;
	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	/**
	 * Nature Contrat
	 */
	// private Nomenclature nomenclatureByNatureContrat;
	private Integer natureId;
	private String natureLibelleLongFr;
	private String natureLibelleLongAr;
	private String natureLibelleCourtFr;
	private String natureLibelleCourtAr;
	private String natureCode;

	// private Nomenclature nomenclatureByDiscipline;
	private Integer disciplineId;
	private String disciplineLibelleLongFr;
	private String disciplineLibelleLongAr;
	private String disciplineLibelleCourtFr;
	private String disciplineLibelleCourtAr;
	private String disciplineCode;

	// private Nomenclature nomenclatureByUniteMonetaire;
	private Integer uniteMonitaireId;
	private String uniteMonitaireLibelleLongFr;
	private String uniteMonitaireLibelleLongAr;
	private String uniteMonitaireLibelleCourtFr;
	private String uniteMonitaireLibelleCourtAr;
	private String uniteMonitaireCode;

	/**
	 * Service
	 */
	private Integer idStructure;
	private String llStructureArabe;
	private String llStructureLatin;
	/**
	 * Etablissement
	 */
	private Integer idEtablissement;
	private String llEtablissementArabe;
	private String llEtablissementLatin;
	/**
	 * Contrat of avenant
	 */
	private Integer idContrat;
	private String objetContratAvenantFr;
	private String refDocContratAvenant;
	private String montantHtContrat;
	private String montantTvaContrat;
	private String montantTtcContrat;

	private Date dateDebutContrat;
	private Date dateFinContrat;
	private String dureeContrat;

	/**
	 * Nature Contrat
	 */
	// private Nomenclature nomenclatureByTypeAvenant;
	private Integer typeAvenantId;
	private String typeAvenantLibelleLongFr;
	private String typeAvenantLibelleLongAr;
	private String typeAvenantLibelleCourtFr;
	private String typeAvenantLibelleCourtAr;
	private String typeAvenantCode;
	/***** etablissement *********/
	private String idfEtablissement;

	public Date getDateDebutContrat() {
		return dateDebutContrat;
	}

	public void setDateDebutContrat(Date dateDebutContrat) {
		this.dateDebutContrat = dateDebutContrat;
	}
	
	

	/**
	 * [RefContratDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:52:13
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefContratDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:52:13
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateFinContrat() {
		return dateFinContrat;
	}

	public void setDateFinContrat(Date dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
	}

	public String getDureeContrat() {
		return dureeContrat;
	}

	public void setDureeContrat(String dureeContrat) {
		this.dureeContrat = dureeContrat;
	}

	public String getMontantHtContrat() {
		return montantHtContrat;
	}

	public void setMontantHtContrat(String montantHtContrat) {
		this.montantHtContrat = montantHtContrat;
	}

	public String getMontantTvaContrat() {
		return montantTvaContrat;
	}

	public void setMontantTvaContrat(String montantTvaContrat) {
		this.montantTvaContrat = montantTvaContrat;
	}

	public String getMontantTtcContrat() {
		return montantTtcContrat;
	}

	public void setMontantTtcContrat(String montantTtcContrat) {
		this.montantTtcContrat = montantTtcContrat;
	}

	public RefContratDto() {

	}

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the objetContratFr
	 */
	public String getObjetContratFr() {
		return objetContratFr;
	}

	/**
	 * @param objetContratFr
	 *            the objetContratFr to set
	 */
	public void setObjetContratFr(String objetContratFr) {
		this.objetContratFr = objetContratFr;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateDebutValidite
	 */
	public Date getDateDebutValidite() {
		return dateDebutValidite;
	}

	/**
	 * @param dateDebutValidite
	 *            the dateDebutValidite to set
	 */
	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	/**
	 * @return the dateFinValidite
	 */
	public Date getDateFinValidite() {
		return dateFinValidite;
	}

	/**
	 * @param dateFinValidite
	 *            the dateFinValidite to set
	 */
	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	/**
	 * @return the duree
	 */
	public Short getDuree() {
		return duree;
	}

	/**
	 * @param duree
	 *            the duree to set
	 */
	public void setDuree(Short duree) {
		this.duree = duree;
	}

	/**
	 * @return the montantHt
	 */
	public BigDecimal getMontantHt() {
		return montantHt;
	}

	/**
	 * @param montantHt
	 *            the montantHt to set
	 */
	public void setMontantHt(BigDecimal montantHt) {
		this.montantHt = montantHt;
	}

	/**
	 * @return the montantTva
	 */
	public BigDecimal getMontantTva() {
		return montantTva;
	}

	/**
	 * @param montantTva
	 *            the montantTva to set
	 */
	public void setMontantTva(BigDecimal montantTva) {
		this.montantTva = montantTva;
	}

	/**
	 * @return the montantTtc
	 */
	public BigDecimal getMontantTtc() {
		return montantTtc;
	}

	/**
	 * @param montantTtc
	 *            the montantTtc to set
	 */
	public void setMontantTtc(BigDecimal montantTtc) {
		this.montantTtc = montantTtc;
	}

	/**
	 * @return the objetContratAr
	 */
	public String getObjetContratAr() {
		return objetContratAr;
	}

	/**
	 * @param objetContratAr
	 *            the objetContratAr to set
	 */
	public void setObjetContratAr(String objetContratAr) {
		this.objetContratAr = objetContratAr;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the avenant
	 */
	public Boolean getAvenant() {
		return avenant;
	}

	/**
	 * @param avenant
	 *            the avenant to set
	 */
	public void setAvenant(Boolean avenant) {
		this.avenant = avenant;
	}

	/**
	 * @return the reconductible
	 */
	public Boolean getReconductible() {
		return reconductible;
	}

	/**
	 * @param reconductible
	 *            the reconductible to set
	 */
	public void setReconductible(Boolean reconductible) {
		this.reconductible = reconductible;
	}

	/**
	 * @return the referenceDocumentaire
	 */
	public String getReferenceDocumentaire() {
		return referenceDocumentaire;
	}

	/**
	 * @param referenceDocumentaire
	 *            the referenceDocumentaire to set
	 */
	public void setReferenceDocumentaire(String referenceDocumentaire) {
		this.referenceDocumentaire = referenceDocumentaire;
	}

	/**
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * @param dateSituation
	 *            the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * @param llSituationAr
	 *            the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * @param llSituationFr
	 *            the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}

	/**
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * @param idStructure
	 *            the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	/**
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * @param llStructureArabe
	 *            the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * @param llStructureLatin
	 *            the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}

	/**
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * @param idEtablissement
	 *            the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * @return the llEtablissementArabe
	 */
	public String getLlEtablissementArabe() {
		return llEtablissementArabe;
	}

	/**
	 * @param llEtablissementArabe
	 *            the llEtablissementArabe to set
	 */
	public void setLlEtablissementArabe(String llEtablissementArabe) {
		this.llEtablissementArabe = llEtablissementArabe;
	}

	/**
	 * @return the llEtablissementLatin
	 */
	public String getLlEtablissementLatin() {
		return llEtablissementLatin;
	}

	/**
	 * @param llEtablissementLatin
	 *            the llEtablissementLatin to set
	 */
	public void setLlEtablissementLatin(String llEtablissementLatin) {
		this.llEtablissementLatin = llEtablissementLatin;
	}

	/**
	 * @return the idContrat
	 */
	public Integer getIdContrat() {
		return idContrat;
	}

	/**
	 * @param idContrat
	 *            the idContrat to set
	 */
	public void setIdContrat(Integer idContrat) {
		this.idContrat = idContrat;
	}

	/**
	 * @return the objetContratAvenantFr
	 */
	public String getObjetContratAvenantFr() {
		return objetContratAvenantFr;
	}

	/**
	 * @param objetContratAvenantFr
	 *            the objetContratAvenantFr to set
	 */
	public void setObjetContratAvenantFr(String objetContratAvenantFr) {
		this.objetContratAvenantFr = objetContratAvenantFr;
	}

	/**
	 * @return the refDocContratAvenant
	 */
	public String getRefDocContratAvenant() {
		return refDocContratAvenant;
	}

	/**
	 * @param refDocContratAvenant
	 *            the refDocContratAvenant to set
	 */
	public void setRefDocContratAvenant(String refDocContratAvenant) {
		this.refDocContratAvenant = refDocContratAvenant;
	}

	public void setIdEtablissement(int idMotherStructure, Integer idStructure) {
		// TODO Auto-generated method stub
		this.idStructure = idStructure;
	}

	/**
	 * [RefContratDto.natureId] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureId
	 */
	public Integer getNatureId() {
		return natureId;
	}

	/**
	 * [RefContratDto.natureId] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureId
	 *            the natureId to set
	 */
	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
	}

	/**
	 * [RefContratDto.natureLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureLibelleLongFr
	 */
	public String getNatureLibelleLongFr() {
		return natureLibelleLongFr;
	}

	/**
	 * [RefContratDto.natureLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureLibelleLongFr
	 *            the natureLibelleLongFr to set
	 */
	public void setNatureLibelleLongFr(String natureLibelleLongFr) {
		this.natureLibelleLongFr = natureLibelleLongFr;
	}

	/**
	 * [RefContratDto.natureLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureLibelleLongAr
	 */
	public String getNatureLibelleLongAr() {
		return natureLibelleLongAr;
	}

	/**
	 * [RefContratDto.natureLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureLibelleLongAr
	 *            the natureLibelleLongAr to set
	 */
	public void setNatureLibelleLongAr(String natureLibelleLongAr) {
		this.natureLibelleLongAr = natureLibelleLongAr;
	}

	/**
	 * [RefContratDto.natureLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureLibelleCourtFr
	 */
	public String getNatureLibelleCourtFr() {
		return natureLibelleCourtFr;
	}

	/**
	 * [RefContratDto.natureLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureLibelleCourtFr
	 *            the natureLibelleCourtFr to set
	 */
	public void setNatureLibelleCourtFr(String natureLibelleCourtFr) {
		this.natureLibelleCourtFr = natureLibelleCourtFr;
	}

	/**
	 * [RefContratDto.natureLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureLibelleCourtAr
	 */
	public String getNatureLibelleCourtAr() {
		return natureLibelleCourtAr;
	}

	/**
	 * [RefContratDto.natureLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureLibelleCourtAr
	 *            the natureLibelleCourtAr to set
	 */
	public void setNatureLibelleCourtAr(String natureLibelleCourtAr) {
		this.natureLibelleCourtAr = natureLibelleCourtAr;
	}

	/**
	 * [RefContratDto.natureCode] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the natureCode
	 */
	public String getNatureCode() {
		return natureCode;
	}

	/**
	 * [RefContratDto.natureCode] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param natureCode
	 *            the natureCode to set
	 */
	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}

	/**
	 * [RefContratDto.disciplineId] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineId
	 */
	public Integer getDisciplineId() {
		return disciplineId;
	}

	/**
	 * [RefContratDto.disciplineId] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineId
	 *            the disciplineId to set
	 */
	public void setDisciplineId(Integer disciplineId) {
		this.disciplineId = disciplineId;
	}

	/**
	 * [RefContratDto.disciplineLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineLibelleLongFr
	 */
	public String getDisciplineLibelleLongFr() {
		return disciplineLibelleLongFr;
	}

	/**
	 * [RefContratDto.disciplineLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineLibelleLongFr
	 *            the disciplineLibelleLongFr to set
	 */
	public void setDisciplineLibelleLongFr(String disciplineLibelleLongFr) {
		this.disciplineLibelleLongFr = disciplineLibelleLongFr;
	}

	/**
	 * [RefContratDto.disciplineLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineLibelleLongAr
	 */
	public String getDisciplineLibelleLongAr() {
		return disciplineLibelleLongAr;
	}

	/**
	 * [RefContratDto.disciplineLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineLibelleLongAr
	 *            the disciplineLibelleLongAr to set
	 */
	public void setDisciplineLibelleLongAr(String disciplineLibelleLongAr) {
		this.disciplineLibelleLongAr = disciplineLibelleLongAr;
	}

	/**
	 * [RefContratDto.disciplineLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineLibelleCourtFr
	 */
	public String getDisciplineLibelleCourtFr() {
		return disciplineLibelleCourtFr;
	}

	/**
	 * [RefContratDto.disciplineLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineLibelleCourtFr
	 *            the disciplineLibelleCourtFr to set
	 */
	public void setDisciplineLibelleCourtFr(String disciplineLibelleCourtFr) {
		this.disciplineLibelleCourtFr = disciplineLibelleCourtFr;
	}

	/**
	 * [RefContratDto.disciplineLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineLibelleCourtAr
	 */
	public String getDisciplineLibelleCourtAr() {
		return disciplineLibelleCourtAr;
	}

	/**
	 * [RefContratDto.disciplineLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineLibelleCourtAr
	 *            the disciplineLibelleCourtAr to set
	 */
	public void setDisciplineLibelleCourtAr(String disciplineLibelleCourtAr) {
		this.disciplineLibelleCourtAr = disciplineLibelleCourtAr;
	}

	/**
	 * [RefContratDto.disciplineCode] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the disciplineCode
	 */
	public String getDisciplineCode() {
		return disciplineCode;
	}

	/**
	 * [RefContratDto.disciplineCode] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param disciplineCode
	 *            the disciplineCode to set
	 */
	public void setDisciplineCode(String disciplineCode) {
		this.disciplineCode = disciplineCode;
	}

	/**
	 * [RefContratDto.uniteMonitaireId] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireId
	 */
	public Integer getUniteMonitaireId() {
		return uniteMonitaireId;
	}

	/**
	 * [RefContratDto.uniteMonitaireId] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireId
	 *            the uniteMonitaireId to set
	 */
	public void setUniteMonitaireId(Integer uniteMonitaireId) {
		this.uniteMonitaireId = uniteMonitaireId;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireLibelleLongFr
	 */
	public String getUniteMonitaireLibelleLongFr() {
		return uniteMonitaireLibelleLongFr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireLibelleLongFr
	 *            the uniteMonitaireLibelleLongFr to set
	 */
	public void setUniteMonitaireLibelleLongFr(
			String uniteMonitaireLibelleLongFr) {
		this.uniteMonitaireLibelleLongFr = uniteMonitaireLibelleLongFr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireLibelleLongAr
	 */
	public String getUniteMonitaireLibelleLongAr() {
		return uniteMonitaireLibelleLongAr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireLibelleLongAr
	 *            the uniteMonitaireLibelleLongAr to set
	 */
	public void setUniteMonitaireLibelleLongAr(
			String uniteMonitaireLibelleLongAr) {
		this.uniteMonitaireLibelleLongAr = uniteMonitaireLibelleLongAr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireLibelleCourtFr
	 */
	public String getUniteMonitaireLibelleCourtFr() {
		return uniteMonitaireLibelleCourtFr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireLibelleCourtFr
	 *            the uniteMonitaireLibelleCourtFr to set
	 */
	public void setUniteMonitaireLibelleCourtFr(
			String uniteMonitaireLibelleCourtFr) {
		this.uniteMonitaireLibelleCourtFr = uniteMonitaireLibelleCourtFr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireLibelleCourtAr
	 */
	public String getUniteMonitaireLibelleCourtAr() {
		return uniteMonitaireLibelleCourtAr;
	}

	/**
	 * [RefContratDto.uniteMonitaireLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireLibelleCourtAr
	 *            the uniteMonitaireLibelleCourtAr to set
	 */
	public void setUniteMonitaireLibelleCourtAr(
			String uniteMonitaireLibelleCourtAr) {
		this.uniteMonitaireLibelleCourtAr = uniteMonitaireLibelleCourtAr;
	}

	/**
	 * [RefContratDto.uniteMonitaireCode] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @return the uniteMonitaireCode
	 */
	public String getUniteMonitaireCode() {
		return uniteMonitaireCode;
	}

	/**
	 * [RefContratDto.uniteMonitaireCode] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 12:21:54
	 * @param uniteMonitaireCode
	 *            the uniteMonitaireCode to set
	 */
	public void setUniteMonitaireCode(String uniteMonitaireCode) {
		this.uniteMonitaireCode = uniteMonitaireCode;
	}

	/**
	 * [RefContratDto.typeAvenantId] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantId
	 */
	public Integer getTypeAvenantId() {
		return typeAvenantId;
	}

	/**
	 * [RefContratDto.typeAvenantId] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantId
	 *            the typeAvenantId to set
	 */
	public void setTypeAvenantId(Integer typeAvenantId) {
		this.typeAvenantId = typeAvenantId;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantLibelleLongFr
	 */
	public String getTypeAvenantLibelleLongFr() {
		return typeAvenantLibelleLongFr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantLibelleLongFr
	 *            the typeAvenantLibelleLongFr to set
	 */
	public void setTypeAvenantLibelleLongFr(String typeAvenantLibelleLongFr) {
		this.typeAvenantLibelleLongFr = typeAvenantLibelleLongFr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantLibelleLongAr
	 */
	public String getTypeAvenantLibelleLongAr() {
		return typeAvenantLibelleLongAr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantLibelleLongAr
	 *            the typeAvenantLibelleLongAr to set
	 */
	public void setTypeAvenantLibelleLongAr(String typeAvenantLibelleLongAr) {
		this.typeAvenantLibelleLongAr = typeAvenantLibelleLongAr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantLibelleCourtFr
	 */
	public String getTypeAvenantLibelleCourtFr() {
		return typeAvenantLibelleCourtFr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantLibelleCourtFr
	 *            the typeAvenantLibelleCourtFr to set
	 */
	public void setTypeAvenantLibelleCourtFr(String typeAvenantLibelleCourtFr) {
		this.typeAvenantLibelleCourtFr = typeAvenantLibelleCourtFr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantLibelleCourtAr
	 */
	public String getTypeAvenantLibelleCourtAr() {
		return typeAvenantLibelleCourtAr;
	}

	/**
	 * [RefContratDto.typeAvenantLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantLibelleCourtAr
	 *            the typeAvenantLibelleCourtAr to set
	 */
	public void setTypeAvenantLibelleCourtAr(String typeAvenantLibelleCourtAr) {
		this.typeAvenantLibelleCourtAr = typeAvenantLibelleCourtAr;
	}

	/**
	 * [RefContratDto.typeAvenantCode] Getter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @return the typeAvenantCode
	 */
	public String getTypeAvenantCode() {
		return typeAvenantCode;
	}

	/**
	 * [RefContratDto.typeAvenantCode] Setter
	 * 
	 * @author BELDI Jamel on : 6 f�vr. 2014 14:44:11
	 * @param typeAvenantCode
	 *            the typeAvenantCode to set
	 */
	public void setTypeAvenantCode(String typeAvenantCode) {
		this.typeAvenantCode = typeAvenantCode;
	}

	/**
	 * [RefContratDto.idfEtablissement] Getter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 16:35:05
	 * @return the idfEtablissement
	 */
	public String getIdfEtablissement() {
		return idfEtablissement;
	}

	/**
	 * [RefContratDto.idfEtablissement] Setter
	 * 
	 * @author MAKERRI Sofiane on : 14 avr. 2014 16:35:05
	 * @param idfEtablissement
	 *            the idfEtablissement to set
	 */
	public void setIdfEtablissement(String idfEtablissement) {
		this.idfEtablissement = idfEtablissement;
	}
	
	

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(identifiant != null ? identifiant : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(objetContratFr != null ? objetContratFr : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(dateCreation != null ? dateCreation : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(dateDebutValidite != null ? dateDebutValidite : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(dateFinValidite != null ? dateFinValidite : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(duree != null ? duree : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(montantHt != null ? montantHt : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(montantTva != null ? montantTva : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(montantTtc != null ? montantTtc : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(objetContratAr != null ? objetContratAr : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(observation != null ? observation : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(avenant != null ? avenant : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(reconductible != null ? reconductible : "");
		buffer.append(UtilConstant.CHAR_SEPARATOR);
		buffer.append(referenceDocumentaire != null ? referenceDocumentaire
				: "");
		return buffer.toString();
	}

}
