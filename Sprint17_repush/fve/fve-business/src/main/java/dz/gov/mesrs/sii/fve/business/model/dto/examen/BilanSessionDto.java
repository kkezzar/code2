/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.BilanPeriodeDto.java] 
 * @author MAKERRI Sofiane on : 22 oct. 2014  15:44:59
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.util.CrossTab;
import dz.gov.mesrs.sii.fve.business.util.UtilConstants;

/**
 * @author MAKERRI Sofiane on : 22 oct. 2014 15:44:59
 */
public class BilanSessionDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:08
	 */
	private static final long serialVersionUID = 1L;
	private Integer oofId;
	private long id;
	private Long deliberationSessionId;
	private Date dateDeliberation;

	private Integer periodeId;
	private String periodeLibelleFr;
	// private Long bilanSessionMaxId;
	private BilanSessionDto bilanSessionDtoMax;
	private Integer dossierInscriptionAdministrativeId;
	private Integer individuId;
	private Integer situationId;
	private Long planningSessionId;
	private String psIntitule;
	private Date psDateFin;
	private String psRefCodeTypeSession;

	private String nomArabeEtudiant;
	private String nomLatinEtudiant;
	private String prenomArabeEtudiant;
	private String prenomLatinEtudiant;
	private Date dateNaissanceEtudiant;
	private String lieuNaissanceEtudiant;

	private Integer typeDecisionId;
	private String typeDecisionCode;
	private String typeDecisionLibelleFr;
	private String typeDecisionLibelleAr;
	private Integer mentionId;
	private String mentionCode;
	private String mentionLibelleFr;
	private String mentionLibelleAr;
	private String mentionLibelle;

	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	private Integer refEtablissementId;
	private String refEtablissementCode;
	private String refEtablissementLibelleFr;
	private double moyenne;
	private double credit;
	private double creditObtenu;
	private double creditAcquis;
	private boolean annuel;
	private boolean bilanFinal;
	private String matriculeEtudiant;
	private String numeroInscriptionEtudiant;
	private String sessionIntitule;
	private int type;
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private Integer niveauId;
	private String niveauCode;
	private int niveauRang;
	private String niveauLibelleLongLt;
	private List<BilanUeDto> bilanUes;
	private List<CrossTab> collection;
	private List<BilanSessionDto> sessions;
	private boolean creditMinObtenu;
	private boolean oldSession;
	private List<BilanSessionDto> bilanSessionDtos;
	private Integer dossierEtudiantId;
	private double moyenneGenerale;
	private String formattedMG;
	private boolean passageL1AvecDette;
	private String styleClass;
	private boolean admis;
	private List<BilanSessionDto> bilanAnnuels;
	private Integer bilanParentId;
	private String columnIntitule;
	private String urlPhoto;

	/**
	 * [BilanPeriodeDto.BilanPeriodeDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:44:59
	 */
	public BilanSessionDto() {
		super();
	}

	public BilanSessionDto(long id) {
		this.id = id;
	}

	/**
	 * [BilanPeriodeDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [BilanPeriodeDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [BilanPeriodeDto.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [BilanPeriodeDto.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [BilanPeriodeDto.dossierInscriptionAdministrativeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @return the dossierInscriptionAdministrativeId
	 */
	public Integer getDossierInscriptionAdministrativeId() {
		return dossierInscriptionAdministrativeId;
	}

	/**
	 * [BilanPeriodeDto.dossierInscriptionAdministrativeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @param dossierInscriptionAdministrativeId
	 *            the dossierInscriptionAdministrativeId to set
	 */
	public void setDossierInscriptionAdministrativeId(Integer dossierInscriptionAdministrativeId) {
		this.dossierInscriptionAdministrativeId = dossierInscriptionAdministrativeId;
	}

	/**
	 * [BilanPeriodeDto.typeDecisionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @return the typeDecisionId
	 */
	public Integer getTypeDecisionId() {
		return typeDecisionId;
	}

	/**
	 * [BilanPeriodeDto.typeDecisionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @param typeDecisionId
	 *            the typeDecisionId to set
	 */
	public void setTypeDecisionId(Integer typeDecisionId) {
		this.typeDecisionId = typeDecisionId;
	}

	/**
	 * [BilanPeriodeDto.moyenne] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @return the moyenne
	 */
	public double getMoyenne() {
		return moyenne;
	}

	/**
	 * [BilanPeriodeDto.moyenne] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 15:45:54
	 * @param moyenne
	 *            the moyenne to set
	 */
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	/**
	 * [BilanPeriodeDto.anneeAcademiqueId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:14:55
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [BilanPeriodeDto.anneeAcademiqueId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:14:55
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [BilanPeriodeDto.annuel] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:14:55
	 * @return the annuel
	 */
	public boolean isAnnuel() {
		if (type == UtilConstants.BILAN_TYPE_ANNUEL) {
			annuel = true;
		}
		return annuel;
	}

	/**
	 * [BilanPeriodeDto.annuel] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 oct. 2014 17:14:55
	 * @param annuel
	 *            the annuel to set
	 */
	public void setAnnuel(boolean annuel) {
		this.annuel = annuel;
	}

	/**
	 * [BilanSessionDto.deliberationSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 11:21:03
	 * @return the deliberationSessionId
	 */
	public Long getDeliberationSessionId() {
		return deliberationSessionId;
	}

	/**
	 * [BilanSessionDto.deliberationSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 oct. 2014 11:21:03
	 * @param deliberationSessionId
	 *            the deliberationSessionId to set
	 */
	public void setDeliberationSessionId(Long deliberationSessionId) {
		this.deliberationSessionId = deliberationSessionId;
	}

	/**
	 * [BilanSessionDto.matriculeEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:46:27
	 * @return the matriculeEtudiant
	 */
	public String getMatriculeEtudiant() {
		return matriculeEtudiant;
	}

	/**
	 * [BilanSessionDto.matriculeEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 09:46:27
	 * @param matriculeEtudiant
	 *            the matriculeEtudiant to set
	 */
	public void setMatriculeEtudiant(String matriculeEtudiant) {
		this.matriculeEtudiant = matriculeEtudiant;
	}

	/**
	 * [BilanSessionDto.bilanFinal] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:16:19
	 * @return the bilanFinal
	 */
	public boolean getBilanFinal() {
		return bilanFinal;
	}

	/**
	 * [BilanSessionDto.bilanFinal] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:16:19
	 * @param bilanFinal
	 *            the bilanFinal to set
	 */
	public void setBilanFinal(boolean bilanFinal) {
		this.bilanFinal = bilanFinal;
	}

	/**
	 * [BilanSessionDto.etudiantNomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @return the etudiantNomArabe
	 */
	public String getNomArabeEtudiant() {
		return nomArabeEtudiant;
	}

	/**
	 * [BilanSessionDto.etudiantNomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @param etudiantNomArabe
	 *            the etudiantNomArabe to set
	 */
	public void setNomArabeEtudiant(String nomArabeEtudiant) {
		this.nomArabeEtudiant = nomArabeEtudiant;
	}

	/**
	 * [BilanSessionDto.etudiantNomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @return the etudiantNomLatin
	 */
	public String getNomLatinEtudiant() {
		return nomLatinEtudiant;
	}

	/**
	 * [BilanSessionDto.etudiantNomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @param etudiantNomLatin
	 *            the etudiantNomLatin to set
	 */
	public void setNomLatinEtudiant(String nomLatinEtudiant) {
		this.nomLatinEtudiant = nomLatinEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.prenomArabeEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @return the prenomArabeEtudiant =======
	 *         [BilanSessionDto.etudiantPrenomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @return the etudiantPrenomArabe >>>>>>> branch 'master' of
	 *         ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public String getPrenomArabeEtudiant() {
		return prenomArabeEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.prenomArabeEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @param prenomArabeEtudiant
	 *            the prenomArabeEtudiant to set =======
	 *            [BilanSessionDto.etudiantPrenomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @param etudiantPrenomArabe
	 *            the etudiantPrenomArabe to set >>>>>>> branch 'master' of
	 *            ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public void setPrenomArabeEtudiant(String prenomArabeEtudiant) {
		this.prenomArabeEtudiant = prenomArabeEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.prenomLatinEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @return the prenomLatinEtudiant =======
	 *         [BilanSessionDto.etudiantPrenomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @return the etudiantPrenomLatin >>>>>>> branch 'master' of
	 *         ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public String getPrenomLatinEtudiant() {
		return prenomLatinEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.prenomLatinEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @param prenomLatinEtudiant
	 *            the prenomLatinEtudiant to set =======
	 *            [BilanSessionDto.etudiantPrenomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @param etudiantPrenomLatin
	 *            the etudiantPrenomLatin to set >>>>>>> branch 'master' of
	 *            ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public void setPrenomLatinEtudiant(String prenomLatinEtudiant) {
		this.prenomLatinEtudiant = prenomLatinEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.dateNaissanceEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @return the dateNaissanceEtudiant =======
	 *         [BilanSessionDto.etudiantDateNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @return the etudiantDateNaissance >>>>>>> branch 'master' of
	 *         ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public Date getDateNaissanceEtudiant() {
		return dateNaissanceEtudiant;
	}

	/**
	 * <<<<<<< HEAD [BilanSessionDto.dateNaissanceEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:15:03
	 * @param dateNaissanceEtudiant
	 *            the dateNaissanceEtudiant to set =======
	 *            [BilanSessionDto.etudiantDateNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:37:32
	 * @param etudiantDateNaissance
	 *            the etudiantDateNaissance to set >>>>>>> branch 'master' of
	 *            ssh://ccm@192.168.3.127/~/git/R2-SII-MESRS-SRC
	 */
	public void setDateNaissanceEtudiant(Date dateNaissanceEtudiant) {
		this.dateNaissanceEtudiant = dateNaissanceEtudiant;
	}

	/**
	 * [BilanSessionDto.indivduId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:51:42
	 * @return the indivduId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [BilanSessionDto.indivduId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 15:51:42
	 * @param indivduId
	 *            the indivduId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [BilanSessionDto.type] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:56:53
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * [BilanSessionDto.type] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 08:56:53
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * [BilanSessionDto.sessionIntitule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:26:41
	 * @return the sessionIntitule
	 */
	public String getSessionIntitule() {
		return sessionIntitule;
	}

	/**
	 * [BilanSessionDto.sessionIntitule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 16:26:41
	 * @param sessionIntitule
	 *            the sessionIntitule to set
	 */
	public void setSessionIntitule(String sessionIntitule) {
		this.sessionIntitule = sessionIntitule;
	}

	/**
	 * [BilanSessionDto.planningSessionId] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [BilanSessionDto.planningSessionId] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [BilanSessionDto.psIntitule] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @return the psIntitule
	 */
	public String getPsIntitule() {
		return psIntitule;
	}

	/**
	 * [BilanSessionDto.psIntitule] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @param psIntitule
	 *            the psIntitule to set
	 */
	public void setPsIntitule(String psIntitule) {
		this.psIntitule = psIntitule;
	}

	/**
	 * [BilanSessionDto.psDateFin] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @return the psDateFin
	 */
	public Date getPsDateFin() {
		return psDateFin;
	}

	/**
	 * [BilanSessionDto.psDateFin] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @param psDateFin
	 *            the psDateFin to set
	 */
	public void setPsDateFin(Date psDateFin) {
		this.psDateFin = psDateFin;
	}

	/**
	 * [BilanSessionDto.psRefCodeTypeSession] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @return the psRefCodeTypeSession
	 */
	public String getPsRefCodeTypeSession() {
		return psRefCodeTypeSession;
	}

	/**
	 * [BilanSessionDto.psRefCodeTypeSession] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @param psRefCodeTypeSession
	 *            the psRefCodeTypeSession to set
	 */
	public void setPsRefCodeTypeSession(String psRefCodeTypeSession) {
		this.psRefCodeTypeSession = psRefCodeTypeSession;
	}

	/**
	 * [BilanSessionDto.oofId] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [BilanSessionDto.oofId] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:13:35
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [BilanSessionDto.cycleId] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [BilanSessionDto.cycleId] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [BilanSessionDto.cycleCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [BilanSessionDto.cycleCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [BilanSessionDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [BilanSessionDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [BilanSessionDto.niveauId] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [BilanSessionDto.niveauId] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [BilanSessionDto.niveauCode] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [BilanSessionDto.niveauCode] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [BilanSessionDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [BilanSessionDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELBRIK Oualid on : 26 oct. 2014 12:15:43
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	/**
	 * [BilanSessionDto.typeDecisionCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 18:43:48
	 * @return the typeDecisionCode
	 */
	public String getTypeDecisionCode() {
		return typeDecisionCode;
	}

	/**
	 * [BilanSessionDto.typeDecisionCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 26 oct. 2014 18:43:48
	 * @param typeDecisionCode
	 *            the typeDecisionCode to set
	 */
	public void setTypeDecisionCode(String typeDecisionCode) {
		this.typeDecisionCode = typeDecisionCode;
	}

	/**
	 * [BilanSessionDto.bilanSessionMaxId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 12:35:03
	 * @return the bilanSessionMaxId
	 */
	// public Long getBilanSessionMaxId() {
	// return bilanSessionMaxId;
	// }

	/**
	 * [BilanSessionDto.bilanSessionMaxId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 12:35:03
	 * @param bilanSessionMaxId
	 *            the bilanSessionMaxId to set
	 */
	// public void setBilanSessionMaxId(Long bilanSessionMaxId) {
	// this.bilanSessionMaxId = bilanSessionMaxId;
	// }

	/**
	 * [BilanSessionDto.situationId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:35:53
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [BilanSessionDto.situationId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 27 oct. 2014 17:35:53
	 * @param situationId
	 *            the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	public Date getDateDeliberation() {
		return dateDeliberation;
	}

	public void setDateDeliberation(Date dateDeliberation) {
		this.dateDeliberation = dateDeliberation;
	}

	public String getTypeDecisionLibelleFr() {
		return typeDecisionLibelleFr;
	}

	public void setTypeDecisionLibelleFr(String typeDecisionLibelleFr) {
		this.typeDecisionLibelleFr = typeDecisionLibelleFr;
	}

	public String getTypeDecisionLibelleAr() {
		return typeDecisionLibelleAr;
	}

	public void setTypeDecisionLibelleAr(String typeDecisionLibelleAr) {
		this.typeDecisionLibelleAr = typeDecisionLibelleAr;
	}

	/**
	 * [BilanSessionDto.bilanUes] Getter
	 * 
	 * @author MAKERRI Sofiane on : 8 nov. 2014 13:10:01
	 * @return the bilanUes
	 */
	public List<BilanUeDto> getBilanUes() {
		return bilanUes;
	}

	/**
	 * [BilanSessionDto.bilanUes] Setter
	 * 
	 * @author MAKERRI Sofiane on : 8 nov. 2014 13:10:01
	 * @param bilanUes
	 *            the bilanUes to set
	 */
	public void setBilanUes(List<BilanUeDto> bilanUes) {
		this.bilanUes = bilanUes;
	}

	/**
	 * [BilanSessionDto.periodeLibelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 12:57:54
	 * @return the periodeLibelleFr
	 */
	public String getPeriodeLibelleFr() {
		return periodeLibelleFr;
	}

	/**
	 * [BilanSessionDto.periodeLibelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 oct. 2014 12:57:54
	 * @param periodeLibelleFr
	 *            the periodeLibelleFr to set
	 */
	public void setPeriodeLibelleFr(String periodeLibelleFr) {
		this.periodeLibelleFr = periodeLibelleFr;
	}

	/**
	 * [BilanSessionDto.collection] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 11:57:07
	 * @return the collection
	 */
	public List<CrossTab> getCollection() {
		return collection;
	}

	/**
	 * [BilanSessionDto.collection] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 11:57:07
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(List<CrossTab> collection) {
		this.collection = collection;
	}

	/**
	 * [BilanSessionDto.credit] Getter
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 14:42:16
	 * @return the credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * [BilanSessionDto.credit] Setter
	 * 
	 * @author MAKERRI Sofiane on : 30 oct. 2014 14:42:16
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * [BilanSessionDto.mentionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @return the mentionId
	 */
	public Integer getMentionId() {
		return mentionId;
	}

	/**
	 * [BilanSessionDto.mentionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @param mentionId
	 *            the mentionId to set
	 */
	public void setMentionId(Integer mentionId) {
		this.mentionId = mentionId;
	}

	/**
	 * [BilanSessionDto.mentionCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @return the mentionCode
	 */
	public String getMentionCode() {
		return mentionCode;
	}

	/**
	 * [BilanSessionDto.mentionCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @param mentionCode
	 *            the mentionCode to set
	 */
	public void setMentionCode(String mentionCode) {
		this.mentionCode = mentionCode;
	}

	/**
	 * [BilanSessionDto.mentionLibelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @return the mentionLibelleFr
	 */
	public String getMentionLibelleFr() {
		return mentionLibelleFr;
	}

	/**
	 * [BilanSessionDto.mentionLibelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @param mentionLibelleFr
	 *            the mentionLibelleFr to set
	 */
	public void setMentionLibelleFr(String mentionLibelleFr) {
		this.mentionLibelleFr = mentionLibelleFr;
	}

	/**
	 * [BilanSessionDto.mentionLibelleAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @return the mentionLibelleAr
	 */
	public String getMentionLibelleAr() {
		return mentionLibelleAr;
	}

	/**
	 * [BilanSessionDto.mentionLibelleAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 3 nov. 2014 12:11:54
	 * @param mentionLibelleAr
	 *            the mentionLibelleAr to set
	 */
	public void setMentionLibelleAr(String mentionLibelleAr) {
		this.mentionLibelleAr = mentionLibelleAr;
	}

	/**
	 * [BilanSessionDto.numeroInscriptionEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 09:17:07
	 * @return the numeroInscriptionEtudiant
	 */
	public String getNumeroInscriptionEtudiant() {
		return numeroInscriptionEtudiant;
	}

	/**
	 * [BilanSessionDto.numeroInscriptionEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 9 nov. 2014 09:17:07
	 * @param numeroInscriptionEtudiant
	 *            the numeroInscriptionEtudiant to set
	 */
	public void setNumeroInscriptionEtudiant(String numeroInscriptionEtudiant) {
		this.numeroInscriptionEtudiant = numeroInscriptionEtudiant;
	}

	/**
	 * [BilanSessionDto.creditObtenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 10 nov. 2014 14:57:51
	 * @return the creditObtenu
	 */
	public double getCreditObtenu() {
		return creditObtenu;
	}

	/**
	 * [BilanSessionDto.creditObtenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 10 nov. 2014 14:57:51
	 * @param creditObtenu
	 *            the creditObtenu to set
	 */
	public void setCreditObtenu(double creditObtenu) {
		this.creditObtenu = creditObtenu;
	}

	/**
	 * [BilanSessionDto.session] Getter
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 09:35:12
	 * @return the session
	 */
	public List<BilanSessionDto> getSessions() {
		return sessions;
	}

	/**
	 * [BilanSessionDto.session] Setter
	 * 
	 * @author MAKERRI Sofiane on : 11 nov. 2014 09:35:12
	 * @param session
	 *            the session to set
	 */
	public void setSessions(List<BilanSessionDto> sessions) {
		this.sessions = sessions;
	}

	/**
	 * [BilanSessionDto.lieuNaissanceEtudiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:33:42
	 * @return the lieuNaissanceEtudiant
	 */
	public String getLieuNaissanceEtudiant() {
		return lieuNaissanceEtudiant;
	}

	/**
	 * [BilanSessionDto.lieuNaissanceEtudiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 nov. 2014 15:33:42
	 * @param lieuNaissanceEtudiant
	 *            the lieuNaissanceEtudiant to set
	 */
	public void setLieuNaissanceEtudiant(String lieuNaissanceEtudiant) {
		this.lieuNaissanceEtudiant = lieuNaissanceEtudiant;
	}

	/**
	 * [BilanSessionDto.creditMinObtenu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 09:46:54
	 * @return the creditMinObtenu
	 */
	public boolean isCreditMinObtenu() {
		return creditMinObtenu;
	}

	/**
	 * [BilanSessionDto.creditMinObtenu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 déc. 2014 09:46:54
	 * @param creditMinObtenu
	 *            the creditMinObtenu to set
	 */
	public void setCreditMinObtenu(boolean creditMinObtenu) {
		this.creditMinObtenu = creditMinObtenu;
	}

	/**
	 * [BilanSessionDto.niveauRang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 10:43:01
	 * @return the niveauRang
	 */
	public int getNiveauRang() {
		return niveauRang;
	}

	/**
	 * [BilanSessionDto.niveauRang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 10:43:01
	 * @param niveauRang
	 *            the niveauRang to set
	 */
	public void setNiveauRang(int niveauRang) {
		this.niveauRang = niveauRang;
	}

	/**
	 * [BilanSessionDto.oldSession] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 13:19:30
	 * @return the oldSession
	 */
	public boolean isOldSession() {
		return oldSession;
	}

	/**
	 * [BilanSessionDto.oldSession] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 déc. 2014 13:19:30
	 * @param oldSession
	 *            the oldSession to set
	 */
	public void setOldSession(boolean oldSession) {
		this.oldSession = oldSession;
	}

	/**
	 * [BilanSessionDto.bilanSessionDtos] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 07:49:10
	 * @return the bilanSessionDtos
	 */
	public List<BilanSessionDto> getBilanSessionDtos() {
		return bilanSessionDtos;
	}

	/**
	 * [BilanSessionDto.bilanSessionDtos] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 07:49:10
	 * @param bilanSessionDtos
	 *            the bilanSessionDtos to set
	 */
	public void setBilanSessionDtos(List<BilanSessionDto> bilanSessionDtos) {
		this.bilanSessionDtos = bilanSessionDtos;
	}

	/**
	 * [BilanSessionDto.dossierEtudiantId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:04:05
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [BilanSessionDto.dossierEtudiantId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:04:05
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [BilanSessionDto.moyenneGenerale] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:10:01
	 * @return the moyenneGenerale
	 */
	public double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [BilanSessionDto.moyenneGenerale] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:10:01
	 * @param moyenneGenerale
	 *            the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	/**
	 * [BilanSessionDto.formattedMG] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:10:01
	 * @return the formattedMG
	 */
	public String getFormattedMG() {
		return formattedMG;
	}

	/**
	 * [BilanSessionDto.formattedMG] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:10:01
	 * @param formattedMG
	 *            the formattedMG to set
	 */
	public void setFormattedMG(String formattedMG) {
		this.formattedMG = formattedMG;
	}

	/**
	 * [BilanSessionDto.passageL1AvecDette] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:16:59
	 * @return the passageL1AvecDette
	 */
	public boolean isPassageL1AvecDette() {
		return passageL1AvecDette;
	}

	/**
	 * [BilanSessionDto.passageL1AvecDette] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:16:59
	 * @param passageL1AvecDette
	 *            the passageL1AvecDette to set
	 */
	public void setPassageL1AvecDette(boolean passageL1AvecDette) {
		this.passageL1AvecDette = passageL1AvecDette;
	}

	/**
	 * [BilanSessionDto.styleClass] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:17:30
	 * @return the styleClass
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * [BilanSessionDto.styleClass] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:17:30
	 * @param styleClass
	 *            the styleClass to set
	 */
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/**
	 * [BilanSessionDto.admis] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:44:21
	 * @return the admis
	 */
	public boolean isAdmis() {
		admis = (moyenneGenerale >= 10 ? true : false);
		return admis;
	}

	/**
	 * [BilanSessionDto.admis] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 08:44:21
	 * @param admis
	 *            the admis to set
	 */
	public void setAdmis(boolean admis) {
		this.admis = admis;
	}

	/**
	 * [BilanSessionDto.bilanAnnuels] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 10:12:32 les bilans annuel des
	 *         année précédante
	 * @return the bilanAnnuels
	 */
	public List<BilanSessionDto> getBilanAnnuels() {
		return bilanAnnuels;
	}

	/**
	 * [BilanSessionDto.bilanAnnuels] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 10:12:32
	 * @param bilanAnnuels
	 *            the bilanAnnuels to set
	 */
	public void setBilanAnnuels(List<BilanSessionDto> bilanAnnuels) {
		this.bilanAnnuels = bilanAnnuels;
	}

	/**
	 * [BilanSessionDto.bilanParentId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:16:49
	 * @return the bilanParentId
	 */
	public Integer getBilanParentId() {
		return bilanParentId;
	}

	/**
	 * [BilanSessionDto.bilanParentId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:16:49
	 * @param bilanParentId
	 *            the bilanParentId to set
	 */
	public void setBilanParentId(Integer bilanParentId) {
		this.bilanParentId = bilanParentId;
	}

	/**
	 * [BilanSessionDto.mentionLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 11:18:25
	 * @return the mentionLibelle
	 */
	public String getMentionLibelle() {
		if (mentionLibelleFr != null && !mentionLibelleFr.isEmpty()) {
			mentionLibelle = mentionLibelleFr;
		} else {
			mentionLibelle = "-";
		}
		return mentionLibelle;
	}

	/**
	 * [BilanSessionDto.mentionLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 28 déc. 2014 11:18:25
	 * @param mentionLibelle
	 *            the mentionLibelle to set
	 */
	public void setMentionLibelle(String mentionLibelle) {
		this.mentionLibelle = mentionLibelle;
	}

	/**
	 * [BilanSessionDto.columnIntitule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:44:18
	 * @return the columnIntitule
	 */
	public String getColumnIntitule() {
		if (columnIntitule == null) {
			if (type == UtilConstants.BILAN_TYPE_SESSION) {
				columnIntitule = psIntitule;
			} else if (type == UtilConstants.BILAN_TYPE_PERIODE) {
				columnIntitule = periodeLibelleFr;
			}
		}
		return columnIntitule;
	}

	/**
	 * [BilanSessionDto.columnIntitule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:44:18
	 * @param columnIntitule
	 *            the columnIntitule to set
	 */
	public void setColumnIntitule(String columnIntitule) {
		this.columnIntitule = columnIntitule;
	}

	/**
	 * [BilanSessionDto.bilanSessionDtoMax] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:55:43
	 * @return the bilanSessionDtoMax
	 */
	public BilanSessionDto getBilanSessionDtoMax() {
		return bilanSessionDtoMax;
	}

	/**
	 * [BilanSessionDto.bilanSessionDtoMax] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 déc. 2014 09:55:43
	 * @param bilanSessionDtoMax
	 *            the bilanSessionDtoMax to set
	 */
	public void setBilanSessionDtoMax(BilanSessionDto bilanSessionDtoMax) {
		this.bilanSessionDtoMax = bilanSessionDtoMax;
	}

	/**
	 * [BilanSessionDto.anneeAcademiqueCode] Getter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:46:49
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [BilanSessionDto.anneeAcademiqueCode] Setter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:46:49
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [BilanSessionDto.refEtablissementId] Getter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @return the refEtablissementId
	 */
	public Integer getRefEtablissementId() {
		return refEtablissementId;
	}

	/**
	 * [BilanSessionDto.refEtablissementId] Setter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @param refEtablissementId the refEtablissementId to set
	 */
	public void setRefEtablissementId(Integer refEtablissementId) {
		this.refEtablissementId = refEtablissementId;
	}

	/**
	 * [BilanSessionDto.refEtablissementCode] Getter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @return the refEtablissementCode
	 */
	public String getRefEtablissementCode() {
		return refEtablissementCode;
	}

	/**
	 * [BilanSessionDto.refEtablissementCode] Setter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @param refEtablissementCode the refEtablissementCode to set
	 */
	public void setRefEtablissementCode(String refEtablissementCode) {
		this.refEtablissementCode = refEtablissementCode;
	}

	/**
	 * [BilanSessionDto.refEtablissementLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @return the refEtablissementLibelleFr
	 */
	public String getRefEtablissementLibelleFr() {
		return refEtablissementLibelleFr;
	}

	/**
	 * [BilanSessionDto.refEtablissementLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 30 d�c. 2014  10:50:10
	 * @param refEtablissementLibelleFr the refEtablissementLibelleFr to set
	 */
	public void setRefEtablissementLibelleFr(String refEtablissementLibelleFr) {
		this.refEtablissementLibelleFr = refEtablissementLibelleFr;
	}

	/**
	 * [BilanSessionDto.urlPhoto] Getter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  09:09:07
	 * @return the urlPhoto
	 */
	public String getUrlPhoto() {
		return urlPhoto;
	}

	/**
	 * [BilanSessionDto.urlPhoto] Setter 
	 * @author MAKERRI Sofiane on : 8 janv. 2015  09:09:07
	 * @param urlPhoto the urlPhoto to set
	 */
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	/**
	 * [BilanSessionDto.creditAcquis] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:56:53
	 * @return the creditAcquis
	 */
	public double getCreditAcquis() {
		return creditAcquis;
	}

	/**
	 * [BilanSessionDto.creditAcquis] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2015  15:56:53
	 * @param creditAcquis the creditAcquis to set
	 */
	public void setCreditAcquis(double creditAcquis) {
		this.creditAcquis = creditAcquis;
	}
	
	

}
