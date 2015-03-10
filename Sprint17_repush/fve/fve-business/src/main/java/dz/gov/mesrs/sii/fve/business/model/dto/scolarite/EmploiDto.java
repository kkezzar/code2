package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author BELDI Jamel on : 22 oct. 2014 12:28:40
 */

public class EmploiDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:11:27
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	// private Nomenclature nomenclatureByIdNcPeriodicite;
	private Integer periodiciteId;
	private String periodiciteLibelleLongFr;
	private String periodiciteLibelleLongAr;
	private String periodiciteCode;
	// private Nomenclature nomenclatureByIdNcPeriode;
	private Integer periodeId;
	private String periodeLibelleLongFr;
	private String periodeLibelleLongAr;
	private String periodeCode;
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
	private boolean estVerrouille;
	private Date dateVerrouillage;
	private String refCodeEtablissement;
	private Set<SeanceEmploiDto> seanceEmplois = new HashSet<SeanceEmploiDto>(0);

	public EmploiDto() {
	}

	/**
	 * [Emploi.id] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [Emploi.id] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [Emploi.estVerrouille] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @return the estVerrouille
	 */
	public boolean isEstVerrouille() {
		return estVerrouille;
	}

	/**
	 * [Emploi.estVerrouille] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @param estVerrouille
	 *            the estVerrouille to set
	 */
	public void setEstVerrouille(boolean estVerrouille) {
		this.estVerrouille = estVerrouille;
	}

	/**
	 * [Emploi.dateVerrouillage] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @return the dateVerrouillage
	 */
	public Date getDateVerrouillage() {
		return dateVerrouillage;
	}

	/**
	 * [Emploi.dateVerrouillage] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @param dateVerrouillage
	 *            the dateVerrouillage to set
	 */
	public void setDateVerrouillage(Date dateVerrouillage) {
		this.dateVerrouillage = dateVerrouillage;
	}

	/**
	 * [Emploi.seanceEmplois] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @return the seanceEmplois
	 */
	public Set<SeanceEmploiDto> getSeanceEmplois() {
		return seanceEmplois;
	}

	/**
	 * [Emploi.seanceEmplois] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 12:27:05
	 * @param seanceEmplois
	 *            the seanceEmplois to set
	 */
	public void setSeanceEmplois(Set<SeanceEmploiDto> seanceEmplois) {
		this.seanceEmplois = seanceEmplois;
	}

	
	

	/**
	 * [EmploiDto.anneeAcademiqueCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [EmploiDto.anneeAcademiqueCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param anneeAcademiqueCode
	 *            the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [EmploiDto.ouvertureOffreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [EmploiDto.ouvertureOffreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param ouvertureOffreFormationId
	 *            the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [EmploiDto.offreFormationId] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [EmploiDto.offreFormationId] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param offreFormationId
	 *            the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [EmploiDto.offreFormationCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [EmploiDto.offreFormationCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param offreFormationCode
	 *            the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [EmploiDto.offreFormationLibelleFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	/**
	 * [EmploiDto.offreFormationLibelleFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param offreFormationLibelleFr
	 *            the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	/**
	 * [EmploiDto.offreFormationLibelleAr] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	/**
	 * [EmploiDto.offreFormationLibelleAr] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param offreFormationLibelleAr
	 *            the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	/**
	 * [EmploiDto.cycleId] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [EmploiDto.cycleId] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param cycleId
	 *            the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [EmploiDto.cycleCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [EmploiDto.cycleCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param cycleCode
	 *            the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [EmploiDto.cycleLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [EmploiDto.cycleLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param cycleLibelleLongLt
	 *            the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [EmploiDto.niveauId] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [EmploiDto.niveauId] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param niveauId
	 *            the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [EmploiDto.niveauCode] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [EmploiDto.niveauCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param niveauCode
	 *            the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [EmploiDto.niveauLibelleLongLt] Getter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [EmploiDto.niveauLibelleLongLt] Setter
	 * 
	 * @author BELDI Jamel on : 22 oct. 2014 15:17:02
	 * @param niveauLibelleLongLt
	 *            the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	

	/**
	 * [EmploiDto.periodiciteId] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodiciteId
	 */
	public Integer getPeriodiciteId() {
		return periodiciteId;
	}

	/**
	 * [EmploiDto.periodiciteId] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodiciteId the periodiciteId to set
	 */
	public void setPeriodiciteId(Integer periodiciteId) {
		this.periodiciteId = periodiciteId;
	}

	/**
	 * [EmploiDto.periodiciteLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodiciteLibelleLongFr
	 */
	public String getPeriodiciteLibelleLongFr() {
		return periodiciteLibelleLongFr;
	}

	/**
	 * [EmploiDto.periodiciteLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodiciteLibelleLongFr the periodiciteLibelleLongFr to set
	 */
	public void setPeriodiciteLibelleLongFr(String periodiciteLibelleLongFr) {
		this.periodiciteLibelleLongFr = periodiciteLibelleLongFr;
	}

	/**
	 * [EmploiDto.periodiciteLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodiciteLibelleLongAr
	 */
	public String getPeriodiciteLibelleLongAr() {
		return periodiciteLibelleLongAr;
	}

	/**
	 * [EmploiDto.periodiciteLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodiciteLibelleLongAr the periodiciteLibelleLongAr to set
	 */
	public void setPeriodiciteLibelleLongAr(String periodiciteLibelleLongAr) {
		this.periodiciteLibelleLongAr = periodiciteLibelleLongAr;
	}

	/**
	 * [EmploiDto.periodiciteCode] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodiciteCode
	 */
	public String getPeriodiciteCode() {
		return periodiciteCode;
	}

	/**
	 * [EmploiDto.periodiciteCode] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodiciteCode the periodiciteCode to set
	 */
	public void setPeriodiciteCode(String periodiciteCode) {
		this.periodiciteCode = periodiciteCode;
	}

	/**
	 * [EmploiDto.periodeId] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [EmploiDto.periodeId] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [EmploiDto.periodeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodeLibelleLongFr
	 */
	public String getPeriodeLibelleLongFr() {
		return periodeLibelleLongFr;
	}

	/**
	 * [EmploiDto.periodeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodeLibelleLongFr the periodeLibelleLongFr to set
	 */
	public void setPeriodeLibelleLongFr(String periodeLibelleLongFr) {
		this.periodeLibelleLongFr = periodeLibelleLongFr;
	}

	/**
	 * [EmploiDto.periodeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodeLibelleLongAr
	 */
	public String getPeriodeLibelleLongAr() {
		return periodeLibelleLongAr;
	}

	/**
	 * [EmploiDto.periodeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodeLibelleLongAr the periodeLibelleLongAr to set
	 */
	public void setPeriodeLibelleLongAr(String periodeLibelleLongAr) {
		this.periodeLibelleLongAr = periodeLibelleLongAr;
	}

	/**
	 * [EmploiDto.periodeCode] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the periodeCode
	 */
	public String getPeriodeCode() {
		return periodeCode;
	}

	/**
	 * [EmploiDto.periodeCode] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param periodeCode the periodeCode to set
	 */
	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	/**
	 * [EmploiDto.refCodeEtablissement] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [EmploiDto.refCodeEtablissement] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  15:18:52
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [EmploiDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  17:07:52
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [EmploiDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  17:07:52
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	
	
}
