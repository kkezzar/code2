/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.cursus.NoteEvaluationControleContinuDto.java] 
 * @author MAKERRI Sofiane on : 29 sept. 2014  17:09:49
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane on : 29 sept. 2014 17:09:49
 */
public class NoteEvaluationControleContinuDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:09:53
	 */
	private static final long serialVersionUID = 1L;
	private Long evaluationControleContinuId;
	private Integer anneeAcademiqueId;
	private Integer oofId;
	private Integer periodeId;
	private Integer associationGroupePedagogiqueId;
	private Integer apId;
	private String apCode;
	private Integer rattachementMcId;
	private String rattachementMcMcLibelleFr;
	private String rattachementMcUeLibelleFr;
	private Boolean comptablise;
	private String ncTypeAppreciationLlFr;
	private String ncTypeAppreciationLlAr;
	private String ncTypeAppreciationCode;

	private Integer affectationGpId;
	private Integer groupePedagogiqueId;
	private Integer diaId;
	private String numeroInscription;
	private Integer dossierEtudiantId;
	private String numeroMatricule;
	// Etudiant
	private Integer individuId;
	private String individuIdentifiant;
	private String etudiantNomArabe;
	private String etudiantNomLatin;
	private String etudiantPrenomArabe;
	private String etudiantPrenomLatin;
	private Date etudiantDateNaissance;

	private long id;
	private Double note;
	private Double coefficient;
	private Date dateEvaluation;
	private Boolean absent;
	private String observation;
	private Double somCoefficient;
	private Double somNote;
	private Double moyenneAp;
	private String formatCoefficient;
	private String formatMoyenneAp;
	private String formatNote;

	/**
	 * [NoteEvaluationControleContinuDto.NoteEvaluationControleContinuDto()]
	 * Constructor
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:09:49
	 */
	public NoteEvaluationControleContinuDto() {
		super();
	}

	public NoteEvaluationControleContinuDto(String numeroMatricule,
			String etudiantNomLatin, String etudiantPrenomLatin,
			Date etudiantDateNaissance) {
		this.numeroMatricule = numeroMatricule;
		this.etudiantNomLatin = etudiantNomLatin;
		this.etudiantPrenomLatin = etudiantPrenomLatin;
		this.etudiantDateNaissance = etudiantDateNaissance;
	}

	public NoteEvaluationControleContinuDto(
			NoteEvaluationControleContinuDto noteEvaluationControleContinuDto) {
		if (noteEvaluationControleContinuDto != null) {
			this.numeroMatricule = noteEvaluationControleContinuDto.getNumeroMatricule();
			this.etudiantNomLatin = noteEvaluationControleContinuDto.getEtudiantNomLatin();
			this.etudiantPrenomLatin = noteEvaluationControleContinuDto.getEtudiantPrenomLatin();
			this.etudiantDateNaissance = noteEvaluationControleContinuDto.getEtudiantDateNaissance();
			this.note = noteEvaluationControleContinuDto.getNote();
			this.absent = noteEvaluationControleContinuDto.getAbsent();
		}
	}

	/**
	 * [NoteEvaluationControleContinuDto.evaluationControleContinuId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the evaluationControleContinuId
	 */
	public Long getEvaluationControleContinuId() {
		return evaluationControleContinuId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.evaluationControleContinuId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param evaluationControleContinuId
	 *            the evaluationControleContinuId to set
	 */
	public void setEvaluationControleContinuId(
			Long evaluationControleContinuId) {
		this.evaluationControleContinuId = evaluationControleContinuId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationLlFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the ncTypeAppreciationLlFr
	 */
	public String getNcTypeAppreciationLlFr() {
		return ncTypeAppreciationLlFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationLlFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param ncTypeAppreciationLlFr
	 *            the ncTypeAppreciationLlFr to set
	 */
	public void setNcTypeAppreciationLlFr(String ncTypeAppreciationLlFr) {
		this.ncTypeAppreciationLlFr = ncTypeAppreciationLlFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationLlAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the ncTypeAppreciationLlAr
	 */
	public String getNcTypeAppreciationLlAr() {
		return ncTypeAppreciationLlAr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationLlAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param ncTypeAppreciationLlAr
	 *            the ncTypeAppreciationLlAr to set
	 */
	public void setNcTypeAppreciationLlAr(String ncTypeAppreciationLlAr) {
		this.ncTypeAppreciationLlAr = ncTypeAppreciationLlAr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the ncTypeAppreciationCode
	 */
	public String getNcTypeAppreciationCode() {
		return ncTypeAppreciationCode;
	}

	/**
	 * [NoteEvaluationControleContinuDto.ncTypeAppreciationCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param ncTypeAppreciationCode
	 *            the ncTypeAppreciationCode to set
	 */
	public void setNcTypeAppreciationCode(String ncTypeAppreciationCode) {
		this.ncTypeAppreciationCode = ncTypeAppreciationCode;
	}

	/**
	 * [NoteEvaluationControleContinuDto.diaId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the diaId
	 */
	public Integer getDiaId() {
		return diaId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.diaId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param diaId
	 *            the diaId to set
	 */
	public void setDiaId(Integer diaId) {
		this.diaId = diaId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.numeroInscription] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the numeroInscription
	 */
	public String getNumeroInscription() {
		return numeroInscription;
	}

	/**
	 * [NoteEvaluationControleContinuDto.numeroInscription] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param numeroInscription
	 *            the numeroInscription to set
	 */
	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	/**
	 * [NoteEvaluationControleContinuDto.dossierEtudiantId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the dossierEtudiantId
	 */
	public Integer getDossierEtudiantId() {
		return dossierEtudiantId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.dossierEtudiantId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param dossierEtudiantId
	 *            the dossierEtudiantId to set
	 */
	public void setDossierEtudiantId(Integer dossierEtudiantId) {
		this.dossierEtudiantId = dossierEtudiantId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.numeroMatricule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [NoteEvaluationControleContinuDto.numeroMatricule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param numeroMatricule
	 *            the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [NoteEvaluationControleContinuDto.individuId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:54:34
	 * @return the individuId
	 */
	public Integer getIndividuId() {
		return individuId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.individuId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:54:34
	 * @param individuId
	 *            the individuId to set
	 */
	public void setIndividuId(Integer individuId) {
		this.individuId = individuId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantNomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the etudiantNomArabe
	 */
	public String getEtudiantNomArabe() {
		return etudiantNomArabe;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantNomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param etudiantNomArabe
	 *            the etudiantNomArabe to set
	 */
	public void setEtudiantNomArabe(String etudiantNomArabe) {
		this.etudiantNomArabe = etudiantNomArabe;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantNomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the etudiantNomLatin
	 */
	public String getEtudiantNomLatin() {
		return etudiantNomLatin;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantNomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param etudiantNomLatin
	 *            the etudiantNomLatin to set
	 */
	public void setEtudiantNomLatin(String etudiantNomLatin) {
		this.etudiantNomLatin = etudiantNomLatin;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantPrenomArabe] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the etudiantPrenomArabe
	 */
	public String getEtudiantPrenomArabe() {
		return etudiantPrenomArabe;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantPrenomArabe] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param etudiantPrenomArabe
	 *            the etudiantPrenomArabe to set
	 */
	public void setEtudiantPrenomArabe(String etudiantPrenomArabe) {
		this.etudiantPrenomArabe = etudiantPrenomArabe;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantPrenomLatin] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the etudiantPrenomLatin
	 */
	public String getEtudiantPrenomLatin() {
		return etudiantPrenomLatin;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantPrenomLatin] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param etudiantPrenomLatin
	 *            the etudiantPrenomLatin to set
	 */
	public void setEtudiantPrenomLatin(String etudiantPrenomLatin) {
		this.etudiantPrenomLatin = etudiantPrenomLatin;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantDateNaissance] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the etudiantDateNaissance
	 */
	public Date getEtudiantDateNaissance() {
		return etudiantDateNaissance;
	}

	/**
	 * [NoteEvaluationControleContinuDto.etudiantDateNaissance] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param etudiantDateNaissance
	 *            the etudiantDateNaissance to set
	 */
	public void setEtudiantDateNaissance(Date etudiantDateNaissance) {
		this.etudiantDateNaissance = etudiantDateNaissance;
	}

	/**
	 * [NoteEvaluationControleContinuDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [NoteEvaluationControleContinuDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [NoteEvaluationControleContinuDto.note] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the note
	 */
	public Double getNote() {
		return note;
	}

	/**
	 * [NoteEvaluationControleContinuDto.note] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param note
	 *            the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
	}

	/**
	 * [NoteEvaluationControleContinuDto.absent] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the absent
	 */
	public Boolean getAbsent() {
		return absent;
	}

	/**
	 * [NoteEvaluationControleContinuDto.absent] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param absent
	 *            the absent to set
	 */
	public void setAbsent(Boolean absent) {
		this.absent = absent;
	}

	/**
	 * [NoteEvaluationControleContinuDto.observation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [NoteEvaluationControleContinuDto.observation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 29 sept. 2014 17:38:54
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [NoteEvaluationControleContinuDto.affectationGpId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:47:50
	 * @return the affectationGpId
	 */
	public Integer getAffectationGpId() {
		return affectationGpId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.affectationGpId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 1 oct. 2014 15:47:50
	 * @param affectationGpId
	 *            the affectationGpId to set
	 */
	public void setAffectationGpId(Integer affectationGpId) {
		this.affectationGpId = affectationGpId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.individuIdentifiant] Getter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:02:57
	 * @return the individuIdentifiant
	 */
	public String getIndividuIdentifiant() {
		return individuIdentifiant;
	}

	/**
	 * [NoteEvaluationControleContinuDto.individuIdentifiant] Setter
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 14:02:57
	 * @param individuIdentifiant
	 *            the individuIdentifiant to set
	 */
	public void setIndividuIdentifiant(String individuIdentifiant) {
		this.individuIdentifiant = individuIdentifiant;
	}

	/**
	 * [NoteEvaluationControleContinuDto.anneeAcademiqueId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.anneeAcademiqueId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.oofId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.oofId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @param oofId the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.periodeId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.periodeId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:04:41
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.comptablise] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:08:10
	 * @return the comptablise
	 */
	public Boolean getComptablise() {
		return comptablise;
	}

	/**
	 * [NoteEvaluationControleContinuDto.comptablise] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:08:10
	 * @param comptablise the comptablise to set
	 */
	public void setComptablise(Boolean comptablise) {
		this.comptablise = comptablise;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:28:30
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:28:30
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.associationGroupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:38:04
	 * @return the associationGroupePedagogiqueId
	 */
	public Integer getAssociationGroupePedagogiqueId() {
		return associationGroupePedagogiqueId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.associationGroupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  10:38:04
	 * @param associationGroupePedagogiqueId the associationGroupePedagogiqueId to set
	 */
	public void setAssociationGroupePedagogiqueId(
			Integer associationGroupePedagogiqueId) {
		this.associationGroupePedagogiqueId = associationGroupePedagogiqueId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.apId] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  13:41:20
	 * @return the apId
	 */
	public Integer getApId() {
		return apId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.apId] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  13:41:20
	 * @param apId the apId to set
	 */
	public void setApId(Integer apId) {
		this.apId = apId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.groupePedagogiqueId] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  12:14:58
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}

	/**
	 * [NoteEvaluationControleContinuDto.groupePedagogiqueId] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  12:14:58
	 * @param groupePedagogiqueId the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}

	
	/**
	 * [NoteEvaluationControleContinuDto.apCode] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:25:03
	 * @return the apCode
	 */
	public String getApCode() {
		return apCode;
	}

	/**
	 * [NoteEvaluationControleContinuDto.apCode] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:25:03
	 * @param apCode the apCode to set
	 */
	public void setApCode(String apCode) {
		this.apCode = apCode;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcMcLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @return the rattachementMcMcLibelleFr
	 */
	public String getRattachementMcMcLibelleFr() {
		return rattachementMcMcLibelleFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcMcLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @param rattachementMcMcLibelleFr the rattachementMcMcLibelleFr to set
	 */
	public void setRattachementMcMcLibelleFr(String rattachementMcMcLibelleFr) {
		this.rattachementMcMcLibelleFr = rattachementMcMcLibelleFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcUeLibelleFr] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @return the rattachementMcUeLibelleFr
	 */
	public String getRattachementMcUeLibelleFr() {
		return rattachementMcUeLibelleFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.rattachementMcUeLibelleFr] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @param rattachementMcUeLibelleFr the rattachementMcUeLibelleFr to set
	 */
	public void setRattachementMcUeLibelleFr(String rattachementMcUeLibelleFr) {
		this.rattachementMcUeLibelleFr = rattachementMcUeLibelleFr;
	}

	/**
	 * [NoteEvaluationControleContinuDto.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @return the coefficient
	 */
	public Double getCoefficient() {
		return coefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.dateEvaluation] Getter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @return the dateEvaluation
	 */
	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	/**
	 * [NoteEvaluationControleContinuDto.dateEvaluation] Setter 
	 * @author MAKERRI Sofiane on : 15 oct. 2014  16:02:55
	 * @param dateEvaluation the dateEvaluation to set
	 */
	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	/**
	 * [NoteEvaluationControleContinuDto.somCoefficient] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:37:19
	 * @return the somCoefficient
	 */
	public Double getSomCoefficient() {
		return somCoefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.somCoefficient] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:37:19
	 * @param somCoefficient the somCoefficient to set
	 */
	public void setSomCoefficient(Double somCoefficient) {
		this.somCoefficient = somCoefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.somNote] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:37:19
	 * @return the somNote
	 */
	public Double getSomNote() {
		return somNote;
	}

	/**
	 * [NoteEvaluationControleContinuDto.somNote] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:37:19
	 * @param somNote the somNote to set
	 */
	public void setSomNote(Double somNote) {
		this.somNote = somNote;
	}

	/**
	 * [NoteEvaluationControleContinuDto.moyenneAp] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:51:32
	 * @return the moyenneAp
	 */
	public Double getMoyenneAp() {
		return moyenneAp;
	}

	/**
	 * [NoteEvaluationControleContinuDto.moyenneAp] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:51:32
	 * @param moyenneAp the moyenneAp to set
	 */
	public void setMoyenneAp(Double moyenneAp) {
		this.moyenneAp = moyenneAp;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatMoyenneAp] Getter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:27:27
	 * @return the formatMoyenneAp
	 */
	public String getFormatMoyenneAp() {
		return formatMoyenneAp;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatMoyenneAp] Setter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:27:27
	 * @param formatMoyenneAp the formatMoyenneAp to set
	 */
	public void setFormatMoyenneAp(String formatMoyenneAp) {
		this.formatMoyenneAp = formatMoyenneAp;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatCoefficient] Getter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:27:50
	 * @return the formatCoefficient
	 */
	public String getFormatCoefficient() {
		return formatCoefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatCoefficient] Setter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:27:50
	 * @param formatCoefficient the formatCoefficient to set
	 */
	public void setFormatCoefficient(String formatCoefficient) {
		this.formatCoefficient = formatCoefficient;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatNote] Getter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:31:15
	 * @return the formatNote
	 */
	public String getFormatNote() {
		return formatNote;
	}

	/**
	 * [NoteEvaluationControleContinuDto.formatNote] Setter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  14:31:15
	 * @param formatNote the formatNote to set
	 */
	public void setFormatNote(String formatNote) {
		this.formatNote = formatNote;
	}
	
	
	

}
