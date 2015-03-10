package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:37
 */

public class PlanningSessionDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 17 sept. 2014 16:17:50
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// private Periode periode;
	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;
	// private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	// private OuvertureOffreFormation ouvertureOffreFormation;
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;
	// private Niveau niveau;
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private Integer niveauId;
	private String niveauCode;
	private String niveauLibelleLongLt;
	// private SituationEntite situationEntite;
	private Integer situationId;
	private String situationCode;
	private String situationLibelleFr;
	private String situationLibelleAr;

	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private Integer numeroSession;
	private Integer ncTypeSessionId;
	private String ncTypeSessionCode;
	private String ncTypeSessionLibelleFr;
	// private Integer refIdTypeSession;
	// private String refCodeTypeSession;
	// private String refTypeSessionSessionLibelleFr;
	private Date dateCreation;
	private Date datePublication;
	private Date dateCloture;
	private String refCodeEtablissement;
	private boolean avecControleContinu;
	private boolean avecControleIntermediaire;
	private boolean notesValide;
	private double coefficient;
	private Integer sessionARemplacerId;
	private String sessionARemplacerIntitule;
	private boolean avecControle;
	private double coefficientNoteEliminatoire;

	private Set<ExamenSessionDto> examenSessionDtos = new HashSet<ExamenSessionDto>(0);

	public PlanningSessionDto() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [PlanningSessionDto.anneeAcademiqueId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:15
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [PlanningSessionDto.anneeAcademiqueId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:15
	 * @param anneeAcademiqueId
	 *            the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [PlanningSessionDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:15
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [PlanningSessionDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:15
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [PlanningSessionDto.idPeriode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [PlanningSessionDto.idPeriode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @param idPeriode
	 *            the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [PlanningSessionDto.codePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [PlanningSessionDto.codePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @param codePeriode
	 *            the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [PlanningSessionDto.libellePeriode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [PlanningSessionDto.libellePeriode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:36:57
	 * @param libellePeriode
	 *            the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	/**
	 * [PlanningSessionDto.ouvertureOffreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [PlanningSessionDto.ouvertureOffreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @param ouvertureOffreFormationId
	 *            the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [PlanningSessionDto.offreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [PlanningSessionDto.offreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @param offreFormationId
	 *            the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [PlanningSessionDto.offreFormationCode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [PlanningSessionDto.offreFormationCode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @param offreFormationCode
	 *            the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [PlanningSessionDto.offreFormationLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	/**
	 * [PlanningSessionDto.offreFormationLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @param offreFormationLibelleFr
	 *            the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	/**
	 * [PlanningSessionDto.offreFormationLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	/**
	 * [PlanningSessionDto.offreFormationLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:39:12
	 * @param offreFormationLibelleAr
	 *            the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	/**
	 * [PlanningSessionDto.cycleId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [PlanningSessionDto.cycleId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [PlanningSessionDto.cycleCode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [PlanningSessionDto.cycleCode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [PlanningSessionDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [PlanningSessionDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [PlanningSessionDto.niveauId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [PlanningSessionDto.niveauId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [PlanningSessionDto.niveauCode] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [PlanningSessionDto.niveauCode] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [PlanningSessionDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [PlanningSessionDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:42:35
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	/**
	 * [PlanningSessionDto.situationId] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @return the situationId
	 */
	public Integer getSituationId() {
		return situationId;
	}

	/**
	 * [PlanningSessionDto.situationId] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @param situationId
	 *            the situationId to set
	 */
	public void setSituationId(Integer situationId) {
		this.situationId = situationId;
	}

	/**
	 * [PlanningSessionDto.situationLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @return the situationLibelleFr
	 */
	public String getSituationLibelleFr() {
		return situationLibelleFr;
	}

	/**
	 * [PlanningSessionDto.situationLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @param situationLibelleFr
	 *            the situationLibelleFr to set
	 */
	public void setSituationLibelleFr(String situationLibelleFr) {
		this.situationLibelleFr = situationLibelleFr;
	}

	/**
	 * [PlanningSessionDto.situationLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @return the situationLibelleAr
	 */
	public String getSituationLibelleAr() {
		return situationLibelleAr;
	}

	/**
	 * [PlanningSessionDto.situationLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 18 sept. 2014 10:40:03
	 * @param situationLibelleAr
	 *            the situationLibelleAr to set
	 */
	public void setSituationLibelleAr(String situationLibelleAr) {
		this.situationLibelleAr = situationLibelleAr;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNumeroSession() {
		return this.numeroSession;
	}

	public void setNumeroSession(Integer numeroSession) {
		this.numeroSession = numeroSession;
	}

	// public String getRefCodeTypeSession() {
	// return this.refCodeTypeSession;
	// }
	//
	// public void setRefCodeTypeSession(String refCodeTypeSession) {
	// this.refCodeTypeSession = refCodeTypeSession;
	// }

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	

	

	/**
	 * [PlanningSessionDto.examenSessionDtos] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  09:51:05
	 * @return the examenSessionDtos
	 */
	public Set<ExamenSessionDto> getExamenSessionDtos() {
		return examenSessionDtos;
	}

	/**
	 * [PlanningSessionDto.examenSessionDtos] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  09:51:05
	 * @param examenSessionDtos the examenSessionDtos to set
	 */
	public void setExamenSessionDtos(Set<ExamenSessionDto> examenSessionDtos) {
		this.examenSessionDtos = examenSessionDtos;
	}

	/**
	 * [PlanningSessionDto.situationCode] Getter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 16:54:56
	 * @return the situationCode
	 */
	public String getSituationCode() {
		return situationCode;
	}

	/**
	 * [PlanningSessionDto.situationCode] Setter
	 * 
	 * @author BELDI Jamel on : 28 sept. 2014 16:54:56
	 * @param situationCode
	 *            the situationCode to set
	 */
	public void setSituationCode(String situationCode) {
		this.situationCode = situationCode;
	}

	/**
	 * [PlanningSessionDto.refTypeSessionSessionLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 19:21:25
	 * @return the refTypeSessionSessionLibelleFr
	 */
	// public String getRefTypeSessionSessionLibelleFr() {
	// return refTypeSessionSessionLibelleFr;
	// }

	/**
	 * [PlanningSessionDto.refTypeSessionSessionLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 19 oct. 2014 19:21:25
	 * @param refTypeSessionSessionLibelleFr
	 *            the refTypeSessionSessionLibelleFr to set
	 */
	// public void setRefTypeSessionSessionLibelleFr(
	// String refTypeSessionSessionLibelleFr) {
	// this.refTypeSessionSessionLibelleFr = refTypeSessionSessionLibelleFr;
	// }

	/**
	 * [PlanningSessionDto.refCodeEtablissement] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 17:05:00
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [PlanningSessionDto.refCodeEtablissement] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 17:05:00
	 * @param refCodeEtablissement
	 *            the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [PlanningSessionDto.avecControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:56:38
	 * @return the avecControleContinu
	 */
	public boolean getAvecControleContinu() {
		return avecControleContinu;
	}

	/**
	 * [PlanningSessionDto.avecControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:56:38
	 * @param avecControleContinu
	 *            the avecControleContinu to set
	 */
	public void setAvecControleContinu(boolean avecControleContinu) {
		this.avecControleContinu = avecControleContinu;
	}

	/**
	 * [PlanningSessionDto.notesValide] Getter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 10:29:37
	 * @return the notesValide
	 */
	public boolean getNotesValide() {
		return notesValide;
	}

	/**
	 * [PlanningSessionDto.notesValide] Setter
	 * 
	 * @author MAKERRI Sofiane on : 5 nov. 2014 10:29:37
	 * @param notesValide
	 *            the notesValide to set
	 */
	public void setNotesValide(boolean notesValide) {
		this.notesValide = notesValide;
	}

	/**
	 * [PlanningSessionDto.coefficient] Getter
	 * 
	 * @author MAKERRI Sofiane on : 13 nov. 2014 12:38:24
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [PlanningSessionDto.coefficient] Setter
	 * 
	 * @author MAKERRI Sofiane on : 13 nov. 2014 12:38:24
	 * @param coefficient
	 *            the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [PlanningSessionDto.sessionARemplacerId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:33:44
	 * @return the sessionARemplacerId
	 */
	public Integer getSessionARemplacerId() {
		return sessionARemplacerId;
	}

	/**
	 * [PlanningSessionDto.sessionARemplacerId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:33:44
	 * @param sessionARemplacerId
	 *            the sessionARemplacerId to set
	 */
	public void setSessionARemplacerId(Integer sessionARemplacerId) {
		this.sessionARemplacerId = sessionARemplacerId;
	}

	/**
	 * [PlanningSessionDto.sessionARemplacerIntitule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:33:44
	 * @return the sessionARemplacerIntitule
	 */
	public String getSessionARemplacerIntitule() {
		return sessionARemplacerIntitule;
	}

	/**
	 * [PlanningSessionDto.sessionARemplacerIntitule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 16 nov. 2014 09:33:44
	 * @param sessionARemplacerIntitule
	 *            the sessionARemplacerIntitule to set
	 */
	public void setSessionARemplacerIntitule(String sessionARemplacerIntitule) {
		this.sessionARemplacerIntitule = sessionARemplacerIntitule;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @return the ncTypeSessionId
	 */
	public Integer getNcTypeSessionId() {
		return ncTypeSessionId;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @param ncTypeSessionId
	 *            the ncTypeSessionId to set
	 */
	public void setNcTypeSessionId(Integer ncTypeSessionId) {
		this.ncTypeSessionId = ncTypeSessionId;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @return the ncTypeSessionCode
	 */
	public String getNcTypeSessionCode() {
		return ncTypeSessionCode;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @param ncTypeSessionCode
	 *            the ncTypeSessionCode to set
	 */
	public void setNcTypeSessionCode(String ncTypeSessionCode) {
		this.ncTypeSessionCode = ncTypeSessionCode;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionLibelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @return the ncTypeSessionLibelleFr
	 */
	public String getNcTypeSessionLibelleFr() {
		return ncTypeSessionLibelleFr;
	}

	/**
	 * [PlanningSessionDto.ncTypeSessionLibelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 08:35:31
	 * @param ncTypeSessionLibelleFr
	 *            the ncTypeSessionLibelleFr to set
	 */
	public void setNcTypeSessionLibelleFr(String ncTypeSessionLibelleFr) {
		this.ncTypeSessionLibelleFr = ncTypeSessionLibelleFr;
	}

	/**
	 * [PlanningSessionDto.avecControleIntermediaire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 15:30:35
	 * @return the avecControleIntermediaire
	 */
	public boolean getAvecControleIntermediaire() {
		return avecControleIntermediaire;
	}

	/**
	 * [PlanningSessionDto.avecControleIntermediaire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 janv. 2015 15:30:35
	 * @param avecControleIntermediaire
	 *            the avecControleIntermediaire to set
	 */
	public void setAvecControleIntermediaire(boolean avecControleIntermediaire) {
		this.avecControleIntermediaire = avecControleIntermediaire;
	}

	/**
	 * [PlanningSessionDto.avecControle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 10:57:48
	 * @return the avecControle
	 */
	public boolean isAvecControle() {
		avecControle = (avecControleContinu || avecControleIntermediaire);
		return avecControle;
	}

	/**
	 * [PlanningSessionDto.avecControle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 janv. 2015 10:57:48
	 * @param avecControle
	 *            the avecControle to set
	 */
	public void setAvecControle(boolean avecControle) {
		this.avecControle = avecControle;
	}

	/**
	 * [PlanningSessionDto.coefficientNoteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:21:56
	 * @return the coefficientNoteEliminatoire
	 */
	public double getCoefficientNoteEliminatoire() {
		return coefficientNoteEliminatoire;
	}

	/**
	 * [PlanningSessionDto.coefficientNoteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:21:56
	 * @param coefficientNoteEliminatoire the coefficientNoteEliminatoire to set
	 */
	public void setCoefficientNoteEliminatoire(double coefficientNoteEliminatoire) {
		this.coefficientNoteEliminatoire = coefficientNoteEliminatoire;
	}



}
