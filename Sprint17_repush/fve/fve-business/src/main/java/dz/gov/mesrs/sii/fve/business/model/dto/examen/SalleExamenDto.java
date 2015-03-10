package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author BELDI Jamel on : 14 oct. 2014 15:52:08
 */
public class SalleExamenDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:30:46
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	// private RefLieu refLieu;
	private Integer refLieuId;
	private String refLieuLibelle;
	private Integer refLieuTypeId;
	private String refLieuTypeLibelle;
	private Short refLieuCapacite;
	// private ExamenSession examenSession;
	private Long examenSessionId;
	private Integer rattachementMcId;
	private Integer ueId;
	private Integer mcId;
	private String mcLibelleFr;
	private String ueLibelleFr;
	private Date dateExamen;
	private Date heureDebut;
	private Date heureFin;
	private String refTypeExamen;
	private String refTypeExamenLibelle;
	// planning
	private Long planningSessionId;
	private Long sessionToReplaceId;
	private Integer ncTypeSessionId;
	private String ncTypeSessionCode;
	private String ncTypeSessionLibelleFr;
	private Integer niveauId;
	private Integer ouvertureOffreFormationId;
	private List<InscriptionExamenDto> etudiants;
	private List<ResponsableExamenDto> responsables;

	public SalleExamenDto() {
	}

	public SalleExamenDto(int id) {
		this.id = id;
	}

	/**
	 * [SalleExamenDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [SalleExamenDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [SalleExamenDto.refLieuId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the refLieuId
	 */
	public Integer getRefLieuId() {
		return refLieuId;
	}

	/**
	 * [SalleExamenDto.refLieuId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param refLieuId
	 *            the refLieuId to set
	 */
	public void setRefLieuId(Integer refLieuId) {
		this.refLieuId = refLieuId;
	}

	/**
	 * [SalleExamenDto.refLieuLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the refLieuLibelle
	 */
	public String getRefLieuLibelle() {
		return refLieuLibelle;
	}

	/**
	 * [SalleExamenDto.refLieuLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param refLieuLibelle
	 *            the refLieuLibelle to set
	 */
	public void setRefLieuLibelle(String refLieuLibelle) {
		this.refLieuLibelle = refLieuLibelle;
	}

	/**
	 * [SalleExamenDto.examenSessionId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the examenSessionId
	 */
	public Long getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [SalleExamenDto.examenSessionId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param examenSessionId
	 *            the examenSessionId to set
	 */
	public void setExamenSessionId(Long examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [SalleExamenDto.rattachementMcId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [SalleExamenDto.rattachementMcId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [SalleExamenDto.ueId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the ueId
	 */
	public Integer getUeId() {
		return ueId;
	}

	/**
	 * [SalleExamenDto.ueId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param ueId
	 *            the ueId to set
	 */
	public void setUeId(Integer ueId) {
		this.ueId = ueId;
	}

	/**
	 * [SalleExamenDto.mcId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the mcId
	 */
	public Integer getMcId() {
		return mcId;
	}

	/**
	 * [SalleExamenDto.mcId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param mcId
	 *            the mcId to set
	 */
	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	/**
	 * [SalleExamenDto.mcLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [SalleExamenDto.mcLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param mcLibelleFr
	 *            the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [SalleExamenDto.ueLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @return the ueLibelleFr
	 */
	public String getUeLibelleFr() {
		return ueLibelleFr;
	}

	/**
	 * [SalleExamenDto.ueLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 15:52:32
	 * @param ueLibelleFr
	 *            the ueLibelleFr to set
	 */
	public void setUeLibelleFr(String ueLibelleFr) {
		this.ueLibelleFr = ueLibelleFr;
	}

	/**
	 * [SalleExamenDto.refLieuTypeId] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:22:28
	 * @return the refLieuTypeId
	 */
	public Integer getRefLieuTypeId() {
		return refLieuTypeId;
	}

	/**
	 * [SalleExamenDto.refLieuTypeId] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:22:28
	 * @param refLieuTypeId
	 *            the refLieuTypeId to set
	 */
	public void setRefLieuTypeId(Integer refLieuTypeId) {
		this.refLieuTypeId = refLieuTypeId;
	}

	/**
	 * [SalleExamenDto.refLieuTypeLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:22:28
	 * @return the refLieuTypeLibelle
	 */
	public String getRefLieuTypeLibelle() {
		return refLieuTypeLibelle;
	}

	/**
	 * [SalleExamenDto.refLieuTypeLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 14 oct. 2014 18:22:28
	 * @param refLieuTypeLibelle
	 *            the refLieuTypeLibelle to set
	 */
	public void setRefLieuTypeLibelle(String refLieuTypeLibelle) {
		this.refLieuTypeLibelle = refLieuTypeLibelle;
	}

	/**
	 * [SalleExamenDto.etudiants] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:53:35
	 * @return the etudiants
	 */
	public List<InscriptionExamenDto> getEtudiants() {
		return etudiants;
	}

	/**
	 * [SalleExamenDto.etudiants] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:53:35
	 * @param etudiants
	 *            the etudiants to set
	 */
	public void setEtudiants(List<InscriptionExamenDto> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [SalleExamenDto.responsables] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:53:35
	 * @return the responsables
	 */
	public List<ResponsableExamenDto> getResponsables() {
		return responsables;
	}

	/**
	 * [SalleExamenDto.responsables] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:53:35
	 * @param responsables
	 *            the responsables to set
	 */
	public void setResponsables(List<ResponsableExamenDto> responsables) {
		this.responsables = responsables;
	}

	/**
	 * [SalleExamenDto.dateExamen] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @return the dateExamen
	 */
	public Date getDateExamen() {
		return dateExamen;
	}

	/**
	 * [SalleExamenDto.dateExamen] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @param dateExamen
	 *            the dateExamen to set
	 */
	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	/**
	 * [SalleExamenDto.heureDebut] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}

	/**
	 * [SalleExamenDto.heureDebut] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @param heureDebut
	 *            the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * [SalleExamenDto.heureFin] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}

	/**
	 * [SalleExamenDto.heureFin] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @param heureFin
	 *            the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	/**
	 * [SalleExamenDto.refTypeExamen] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @return the refTypeExamen
	 */
	public String getRefTypeExamen() {
		return refTypeExamen;
	}

	/**
	 * [SalleExamenDto.refTypeExamen] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @param refTypeExamen
	 *            the refTypeExamen to set
	 */
	public void setRefTypeExamen(String refTypeExamen) {
		this.refTypeExamen = refTypeExamen;
	}

	/**
	 * [SalleExamenDto.refTypeExamenLibelle] Getter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @return the refTypeExamenLibelle
	 */
	public String getRefTypeExamenLibelle() {
		return refTypeExamenLibelle;
	}

	/**
	 * [SalleExamenDto.refTypeExamenLibelle] Setter
	 * 
	 * @author BELDI Jamel on : 20 oct. 2014 09:58:43
	 * @param refTypeExamenLibelle
	 *            the refTypeExamenLibelle to set
	 */
	public void setRefTypeExamenLibelle(String refTypeExamenLibelle) {
		this.refTypeExamenLibelle = refTypeExamenLibelle;
	}

	/**
	 * [SalleExamenDto.refLieuCapacite] Getter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 09:49:39
	 * @return the refLieuCapacite
	 */
	public Short getRefLieuCapacite() {
		return refLieuCapacite;
	}

	/**
	 * [SalleExamenDto.refLieuCapacite] Setter
	 * 
	 * @author BELDI Jamel on : 10 nov. 2014 09:49:39
	 * @param refLieuCapacite
	 *            the refLieuCapacite to set
	 */
	public void setRefLieuCapacite(Short refLieuCapacite) {
		this.refLieuCapacite = refLieuCapacite;
	}

	/**
	 * [SalleExamenDto.planningSessionId] Getter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:22:28
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [SalleExamenDto.planningSessionId] Setter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:22:28
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @return the ncTypeSessionId
	 */
	public Integer getNcTypeSessionId() {
		return ncTypeSessionId;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @param ncTypeSessionId
	 *            the ncTypeSessionId to set
	 */
	public void setNcTypeSessionId(Integer ncTypeSessionId) {
		this.ncTypeSessionId = ncTypeSessionId;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @return the ncTypeSessionCode
	 */
	public String getNcTypeSessionCode() {
		return ncTypeSessionCode;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @param ncTypeSessionCode
	 *            the ncTypeSessionCode to set
	 */
	public void setNcTypeSessionCode(String ncTypeSessionCode) {
		this.ncTypeSessionCode = ncTypeSessionCode;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionLibelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @return the ncTypeSessionLibelleFr
	 */
	public String getNcTypeSessionLibelleFr() {
		return ncTypeSessionLibelleFr;
	}

	/**
	 * [SalleExamenDto.ncTypeSessionLibelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 17 nov. 2014 17:54:28
	 * @param ncTypeSessionLibelleFr
	 *            the ncTypeSessionLibelleFr to set
	 */
	public void setNcTypeSessionLibelleFr(String ncTypeSessionLibelleFr) {
		this.ncTypeSessionLibelleFr = ncTypeSessionLibelleFr;
	}

	/**
	 * [SalleExamenDto.ouvertureOffreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:22:28
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [SalleExamenDto.ouvertureOffreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:22:28
	 * @param ouvertureOffreFormationId
	 *            the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [SalleExamenDto.niveauId] Getter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:25:14
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [SalleExamenDto.niveauId] Setter
	 * 
	 * @author BELDI Jamel on : 12 nov. 2014 11:25:14
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [SalleExamenDto.sessionToReplaceId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:32:14
	 * @return the sessionToReplaceId
	 */
	public Long getSessionToReplaceId() {
		return sessionToReplaceId;
	}

	/**
	 * [SalleExamenDto.sessionToReplaceId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 nov. 2014 12:32:14
	 * @param sessionToReplaceId
	 *            the sessionToReplaceId to set
	 */
	public void setSessionToReplaceId(Long sessionToReplaceId) {
		this.sessionToReplaceId = sessionToReplaceId;
	}
	
	@Override
	public String toString() {
		return refLieuLibelle;
	}

}
