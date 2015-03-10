package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.Date;

/**
 * 
 * @author BELDI Jamel  on : 22 oct. 2014  12:28:19
 */


public class SeanceEmploiDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 22 oct. 2014  12:11:50
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
//	private AssociationGroupePedagogique associationGroupePedagogique;
	//Association+Mc+Enseignant
	private Integer associationGroupePedagogiqueId;
	private Integer groupePedagogiqueId;
	private String groupePedagogiqueNom;
	private String refCodeTypeAp;
	private Integer rattachementMcId;
	private Integer rattachementMcMcId;
	private String rattachementMcMcCode;
	private String rattachementMcMcLibelleFr;
	private String enseignantNom;
	private String enseignantPrenom;
//	private EmploiDto emploi;

	private Integer emploiId;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;	
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;	
	private Integer periodiciteId;
	private String periodiciteLibelleLongFr;
	private String periodiciteLibelleLongAr;	
	private String periodiciteCode;
	private Integer periodeId;
	private String periodeLibelleLongFr;
	private String periodeLibelleLongAr;	
	private String periodeCode;
	private Integer cycleId;
	private String cycleCode;
	private String cycleLibelleLongLt;
	private Integer niveauId;
	private String niveauCode;
	private String niveauLibelleLongLt;
//	private AffectationLieuStructure affectationLieuStructure;
	private Integer affectationLieuStructureId;	
	private Integer refStructureId;	
	private Integer refStructureTypeId;	
	private String llStructureArabe;
	private String llStructureLatin;
	private String refStructureTypeLibelle;		
	private Integer refLieuId;
	private String refLieuDesignation;
	private String refLieudesignationArabe;
	private Integer refLieuTypeId;
	private String refLieuTypeLibelleLongFr;
	private String refLieuTypeLibelleLongAr;	
	private String refLieuTypelieuCode;	
	//CelluleHoraire
	private Integer celluleHoraireId;
	//private PlageHoraire plageHoraire;
	private Integer plageHoraireId;
	private String plageHoraireCode;
	private Short plageHoraireNumero;
	private String plageHoraireLibelleFr;
	private String plageHoraireLibelleAr;
	private Date plageHoraireHeureDebut;
	private Date plageHoraireHeureFin;
	//private Jour jour;
	private Integer jourId;
	private String jourCode;
	private String jourLibelleFr;
	private String jourLibelleAr;

	public SeanceEmploiDto() {
	}

	public SeanceEmploiDto(Integer jourId, String jourCode, String jourLibelleFr, String jourLibelleAr) {
		this.jourId = jourId;
		this.jourCode = jourCode;
		this.jourLibelleFr = jourLibelleFr;
		this.jourLibelleAr = jourLibelleAr;
	}
	/**
	 * [SeanceEmploi.id] Getter 
	 * @author BELDI Jamel on : 22 oct. 2014  12:28:02
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [SeanceEmploi.id] Setter 
	 * @author BELDI Jamel on : 22 oct. 2014  12:28:02
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [SeanceEmploiDto.celluleHoraireId] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the celluleHoraireId
	 */
	public Integer getCelluleHoraireId() {
		return celluleHoraireId;
	}

	/**
	 * [SeanceEmploiDto.celluleHoraireId] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param celluleHoraireId the celluleHoraireId to set
	 */
	public void setCelluleHoraireId(Integer celluleHoraireId) {
		this.celluleHoraireId = celluleHoraireId;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireId] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireId
	 */
	public Integer getPlageHoraireId() {
		return plageHoraireId;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireId] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireId the plageHoraireId to set
	 */
	public void setPlageHoraireId(Integer plageHoraireId) {
		this.plageHoraireId = plageHoraireId;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireCode] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireCode
	 */
	public String getPlageHoraireCode() {
		return plageHoraireCode;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireCode] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireCode the plageHoraireCode to set
	 */
	public void setPlageHoraireCode(String plageHoraireCode) {
		this.plageHoraireCode = plageHoraireCode;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireNumero] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireNumero
	 */
	public Short getPlageHoraireNumero() {
		return plageHoraireNumero;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireNumero] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireNumero the plageHoraireNumero to set
	 */
	public void setPlageHoraireNumero(Short plageHoraireNumero) {
		this.plageHoraireNumero = plageHoraireNumero;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireLibelleFr] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireLibelleFr
	 */
	public String getPlageHoraireLibelleFr() {
		return plageHoraireLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireLibelleFr] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireLibelleFr the plageHoraireLibelleFr to set
	 */
	public void setPlageHoraireLibelleFr(String plageHoraireLibelleFr) {
		this.plageHoraireLibelleFr = plageHoraireLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireLibelleAr] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireLibelleAr
	 */
	public String getPlageHoraireLibelleAr() {
		return plageHoraireLibelleAr;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireLibelleAr] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireLibelleAr the plageHoraireLibelleAr to set
	 */
	public void setPlageHoraireLibelleAr(String plageHoraireLibelleAr) {
		this.plageHoraireLibelleAr = plageHoraireLibelleAr;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireHeureDebut] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireHeureDebut
	 */
	public Date getPlageHoraireHeureDebut() {
		return plageHoraireHeureDebut;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireHeureDebut] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireHeureDebut the plageHoraireHeureDebut to set
	 */
	public void setPlageHoraireHeureDebut(Date plageHoraireHeureDebut) {
		this.plageHoraireHeureDebut = plageHoraireHeureDebut;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireHeureFin] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the plageHoraireHeureFin
	 */
	public Date getPlageHoraireHeureFin() {
		return plageHoraireHeureFin;
	}

	/**
	 * [SeanceEmploiDto.plageHoraireHeureFin] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param plageHoraireHeureFin the plageHoraireHeureFin to set
	 */
	public void setPlageHoraireHeureFin(Date plageHoraireHeureFin) {
		this.plageHoraireHeureFin = plageHoraireHeureFin;
	}

	/**
	 * [SeanceEmploiDto.jourId] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the jourId
	 */
	public Integer getJourId() {
		return jourId;
	}

	/**
	 * [SeanceEmploiDto.jourId] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param jourId the jourId to set
	 */
	public void setJourId(Integer jourId) {
		this.jourId = jourId;
	}

	/**
	 * [SeanceEmploiDto.jourCode] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the jourCode
	 */
	public String getJourCode() {
		return jourCode;
	}

	/**
	 * [SeanceEmploiDto.jourCode] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param jourCode the jourCode to set
	 */
	public void setJourCode(String jourCode) {
		this.jourCode = jourCode;
	}

	/**
	 * [SeanceEmploiDto.jourLibelleFr] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the jourLibelleFr
	 */
	public String getJourLibelleFr() {
		return jourLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.jourLibelleFr] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param jourLibelleFr the jourLibelleFr to set
	 */
	public void setJourLibelleFr(String jourLibelleFr) {
		this.jourLibelleFr = jourLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.jourLibelleAr] Getter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @return the jourLibelleAr
	 */
	public String getJourLibelleAr() {
		return jourLibelleAr;
	}

	/**
	 * [SeanceEmploiDto.jourLibelleAr] Setter 
	 * @author BELDI Jamel on : 25 oct. 2014  13:59:12
	 * @param jourLibelleAr the jourLibelleAr to set
	 */
	public void setJourLibelleAr(String jourLibelleAr) {
		this.jourLibelleAr = jourLibelleAr;
	}

	
	/**
	 * [SeanceEmploiDto.emploiId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the emploiId
	 */
	public Integer getEmploiId() {
		return emploiId;
	}

	/**
	 * [SeanceEmploiDto.emploiId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param emploiId the emploiId to set
	 */
	public void setEmploiId(Integer emploiId) {
		this.emploiId = emploiId;
	}

	/**
	 * [SeanceEmploiDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [SeanceEmploiDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [SeanceEmploiDto.anneeAcademiqueCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [SeanceEmploiDto.anneeAcademiqueCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [SeanceEmploiDto.ouvertureOffreFormationId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [SeanceEmploiDto.ouvertureOffreFormationId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param ouvertureOffreFormationId the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [SeanceEmploiDto.offreFormationId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [SeanceEmploiDto.offreFormationId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [SeanceEmploiDto.offreFormationCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [SeanceEmploiDto.offreFormationCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [SeanceEmploiDto.offreFormationLibelleFr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.offreFormationLibelleFr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param offreFormationLibelleFr the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.offreFormationLibelleAr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	/**
	 * [SeanceEmploiDto.offreFormationLibelleAr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param offreFormationLibelleAr the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	/**
	 * [SeanceEmploiDto.periodiciteId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodiciteId
	 */
	public Integer getPeriodiciteId() {
		return periodiciteId;
	}

	/**
	 * [SeanceEmploiDto.periodiciteId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodiciteId the periodiciteId to set
	 */
	public void setPeriodiciteId(Integer periodiciteId) {
		this.periodiciteId = periodiciteId;
	}

	/**
	 * [SeanceEmploiDto.periodiciteLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodiciteLibelleLongFr
	 */
	public String getPeriodiciteLibelleLongFr() {
		return periodiciteLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.periodiciteLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodiciteLibelleLongFr the periodiciteLibelleLongFr to set
	 */
	public void setPeriodiciteLibelleLongFr(String periodiciteLibelleLongFr) {
		this.periodiciteLibelleLongFr = periodiciteLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.periodiciteLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodiciteLibelleLongAr
	 */
	public String getPeriodiciteLibelleLongAr() {
		return periodiciteLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.periodiciteLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodiciteLibelleLongAr the periodiciteLibelleLongAr to set
	 */
	public void setPeriodiciteLibelleLongAr(String periodiciteLibelleLongAr) {
		this.periodiciteLibelleLongAr = periodiciteLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.periodiciteCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodiciteCode
	 */
	public String getPeriodiciteCode() {
		return periodiciteCode;
	}

	/**
	 * [SeanceEmploiDto.periodiciteCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodiciteCode the periodiciteCode to set
	 */
	public void setPeriodiciteCode(String periodiciteCode) {
		this.periodiciteCode = periodiciteCode;
	}

	/**
	 * [SeanceEmploiDto.periodeId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [SeanceEmploiDto.periodeId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [SeanceEmploiDto.periodeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodeLibelleLongFr
	 */
	public String getPeriodeLibelleLongFr() {
		return periodeLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.periodeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodeLibelleLongFr the periodeLibelleLongFr to set
	 */
	public void setPeriodeLibelleLongFr(String periodeLibelleLongFr) {
		this.periodeLibelleLongFr = periodeLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.periodeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodeLibelleLongAr
	 */
	public String getPeriodeLibelleLongAr() {
		return periodeLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.periodeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodeLibelleLongAr the periodeLibelleLongAr to set
	 */
	public void setPeriodeLibelleLongAr(String periodeLibelleLongAr) {
		this.periodeLibelleLongAr = periodeLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.periodeCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the periodeCode
	 */
	public String getPeriodeCode() {
		return periodeCode;
	}

	/**
	 * [SeanceEmploiDto.periodeCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param periodeCode the periodeCode to set
	 */
	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	/**
	 * [SeanceEmploiDto.affectationLieuStructureId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the affectationLieuStructureId
	 */
	public Integer getAffectationLieuStructureId() {
		return affectationLieuStructureId;
	}

	/**
	 * [SeanceEmploiDto.affectationLieuStructureId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param affectationLieuStructureId the affectationLieuStructureId to set
	 */
	public void setAffectationLieuStructureId(Integer affectationLieuStructureId) {
		this.affectationLieuStructureId = affectationLieuStructureId;
	}

	/**
	 * [SeanceEmploiDto.refStructureId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refStructureId
	 */
	public Integer getRefStructureId() {
		return refStructureId;
	}

	/**
	 * [SeanceEmploiDto.refStructureId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refStructureId the refStructureId to set
	 */
	public void setRefStructureId(Integer refStructureId) {
		this.refStructureId = refStructureId;
	}

	/**
	 * [SeanceEmploiDto.refStructureTypeId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refStructureTypeId
	 */
	public Integer getRefStructureTypeId() {
		return refStructureTypeId;
	}

	/**
	 * [SeanceEmploiDto.refStructureTypeId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refStructureTypeId the refStructureTypeId to set
	 */
	public void setRefStructureTypeId(Integer refStructureTypeId) {
		this.refStructureTypeId = refStructureTypeId;
	}

	/**
	 * [SeanceEmploiDto.llStructureArabe] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * [SeanceEmploiDto.llStructureArabe] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * [SeanceEmploiDto.llStructureLatin] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * [SeanceEmploiDto.llStructureLatin] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}

	/**
	 * [SeanceEmploiDto.refStructureTypeLibelle] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refStructureTypeLibelle
	 */
	public String getRefStructureTypeLibelle() {
		return refStructureTypeLibelle;
	}

	/**
	 * [SeanceEmploiDto.refStructureTypeLibelle] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refStructureTypeLibelle the refStructureTypeLibelle to set
	 */
	public void setRefStructureTypeLibelle(String refStructureTypeLibelle) {
		this.refStructureTypeLibelle = refStructureTypeLibelle;
	}

	/**
	 * [SeanceEmploiDto.refLieuId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuId
	 */
	public Integer getRefLieuId() {
		return refLieuId;
	}

	/**
	 * [SeanceEmploiDto.refLieuId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuId the refLieuId to set
	 */
	public void setRefLieuId(Integer refLieuId) {
		this.refLieuId = refLieuId;
	}

	/**
	 * [SeanceEmploiDto.refLieuDesignation] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuDesignation
	 */
	public String getRefLieuDesignation() {
		return refLieuDesignation;
	}

	/**
	 * [SeanceEmploiDto.refLieuDesignation] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuDesignation the refLieuDesignation to set
	 */
	public void setRefLieuDesignation(String refLieuDesignation) {
		this.refLieuDesignation = refLieuDesignation;
	}

	/**
	 * [SeanceEmploiDto.refLieudesignationArabe] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieudesignationArabe
	 */
	public String getRefLieudesignationArabe() {
		return refLieudesignationArabe;
	}

	/**
	 * [SeanceEmploiDto.refLieudesignationArabe] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieudesignationArabe the refLieudesignationArabe to set
	 */
	public void setRefLieudesignationArabe(String refLieudesignationArabe) {
		this.refLieudesignationArabe = refLieudesignationArabe;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuTypeId
	 */
	public Integer getRefLieuTypeId() {
		return refLieuTypeId;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuTypeId the refLieuTypeId to set
	 */
	public void setRefLieuTypeId(Integer refLieuTypeId) {
		this.refLieuTypeId = refLieuTypeId;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuTypeLibelleLongFr
	 */
	public String getRefLieuTypeLibelleLongFr() {
		return refLieuTypeLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuTypeLibelleLongFr the refLieuTypeLibelleLongFr to set
	 */
	public void setRefLieuTypeLibelleLongFr(String refLieuTypeLibelleLongFr) {
		this.refLieuTypeLibelleLongFr = refLieuTypeLibelleLongFr;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuTypeLibelleLongAr
	 */
	public String getRefLieuTypeLibelleLongAr() {
		return refLieuTypeLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuTypeLibelleLongAr the refLieuTypeLibelleLongAr to set
	 */
	public void setRefLieuTypeLibelleLongAr(String refLieuTypeLibelleLongAr) {
		this.refLieuTypeLibelleLongAr = refLieuTypeLibelleLongAr;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypelieuCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @return the refLieuTypelieuCode
	 */
	public String getRefLieuTypelieuCode() {
		return refLieuTypelieuCode;
	}

	/**
	 * [SeanceEmploiDto.refLieuTypelieuCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  09:55:26
	 * @param refLieuTypelieuCode the refLieuTypelieuCode to set
	 */
	public void setRefLieuTypelieuCode(String refLieuTypelieuCode) {
		this.refLieuTypelieuCode = refLieuTypelieuCode;
	}

	/**
	 * [SeanceEmploiDto.cycleId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the cycleId
	 */
	public Integer getCycleId() {
		return cycleId;
	}

	/**
	 * [SeanceEmploiDto.cycleId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param cycleId the cycleId to set
	 */
	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	/**
	 * [SeanceEmploiDto.cycleCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the cycleCode
	 */
	public String getCycleCode() {
		return cycleCode;
	}

	/**
	 * [SeanceEmploiDto.cycleCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param cycleCode the cycleCode to set
	 */
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}

	/**
	 * [SeanceEmploiDto.cycleLibelleLongLt] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the cycleLibelleLongLt
	 */
	public String getCycleLibelleLongLt() {
		return cycleLibelleLongLt;
	}

	/**
	 * [SeanceEmploiDto.cycleLibelleLongLt] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param cycleLibelleLongLt the cycleLibelleLongLt to set
	 */
	public void setCycleLibelleLongLt(String cycleLibelleLongLt) {
		this.cycleLibelleLongLt = cycleLibelleLongLt;
	}

	/**
	 * [SeanceEmploiDto.niveauId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the niveauId
	 */
	public Integer getNiveauId() {
		return niveauId;
	}

	/**
	 * [SeanceEmploiDto.niveauId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param niveauId the niveauId to set
	 */
	public void setNiveauId(Integer niveauId) {
		this.niveauId = niveauId;
	}

	/**
	 * [SeanceEmploiDto.niveauCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the niveauCode
	 */
	public String getNiveauCode() {
		return niveauCode;
	}

	/**
	 * [SeanceEmploiDto.niveauCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param niveauCode the niveauCode to set
	 */
	public void setNiveauCode(String niveauCode) {
		this.niveauCode = niveauCode;
	}

	/**
	 * [SeanceEmploiDto.niveauLibelleLongLt] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @return the niveauLibelleLongLt
	 */
	public String getNiveauLibelleLongLt() {
		return niveauLibelleLongLt;
	}

	/**
	 * [SeanceEmploiDto.niveauLibelleLongLt] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:05:16
	 * @param niveauLibelleLongLt the niveauLibelleLongLt to set
	 */
	public void setNiveauLibelleLongLt(String niveauLibelleLongLt) {
		this.niveauLibelleLongLt = niveauLibelleLongLt;
	}

	/**
	 * [SeanceEmploiDto.associationGroupePedagogiqueId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the associationGroupePedagogiqueId
	 */
	public Integer getAssociationGroupePedagogiqueId() {
		return associationGroupePedagogiqueId;
	}

	/**
	 * [SeanceEmploiDto.associationGroupePedagogiqueId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param associationGroupePedagogiqueId the associationGroupePedagogiqueId to set
	 */
	public void setAssociationGroupePedagogiqueId(
			Integer associationGroupePedagogiqueId) {
		this.associationGroupePedagogiqueId = associationGroupePedagogiqueId;
	}

	/**
	 * [SeanceEmploiDto.groupePedagogiqueId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the groupePedagogiqueId
	 */
	public Integer getGroupePedagogiqueId() {
		return groupePedagogiqueId;
	}

	/**
	 * [SeanceEmploiDto.groupePedagogiqueId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param groupePedagogiqueId the groupePedagogiqueId to set
	 */
	public void setGroupePedagogiqueId(Integer groupePedagogiqueId) {
		this.groupePedagogiqueId = groupePedagogiqueId;
	}

	/**
	 * [SeanceEmploiDto.groupePedagogiqueNom] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the groupePedagogiqueNom
	 */
	public String getGroupePedagogiqueNom() {
		return groupePedagogiqueNom;
	}

	/**
	 * [SeanceEmploiDto.groupePedagogiqueNom] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param groupePedagogiqueNom the groupePedagogiqueNom to set
	 */
	public void setGroupePedagogiqueNom(String groupePedagogiqueNom) {
		this.groupePedagogiqueNom = groupePedagogiqueNom;
	}

	/**
	 * [SeanceEmploiDto.refCodeTypeAp] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the refCodeTypeAp
	 */
	public String getRefCodeTypeAp() {
		return refCodeTypeAp;
	}

	/**
	 * [SeanceEmploiDto.refCodeTypeAp] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param refCodeTypeAp the refCodeTypeAp to set
	 */
	public void setRefCodeTypeAp(String refCodeTypeAp) {
		this.refCodeTypeAp = refCodeTypeAp;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param rattachementMcId the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcId] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the rattachementMcMcId
	 */
	public Integer getRattachementMcMcId() {
		return rattachementMcMcId;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcId] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param rattachementMcMcId the rattachementMcMcId to set
	 */
	public void setRattachementMcMcId(Integer rattachementMcMcId) {
		this.rattachementMcMcId = rattachementMcMcId;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcCode] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the rattachementMcMcCode
	 */
	public String getRattachementMcMcCode() {
		return rattachementMcMcCode;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcCode] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param rattachementMcMcCode the rattachementMcMcCode to set
	 */
	public void setRattachementMcMcCode(String rattachementMcMcCode) {
		this.rattachementMcMcCode = rattachementMcMcCode;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcLibelleFr] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the rattachementMcMcLibelleFr
	 */
	public String getRattachementMcMcLibelleFr() {
		return rattachementMcMcLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.rattachementMcMcLibelleFr] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param rattachementMcMcLibelleFr the rattachementMcMcLibelleFr to set
	 */
	public void setRattachementMcMcLibelleFr(String rattachementMcMcLibelleFr) {
		this.rattachementMcMcLibelleFr = rattachementMcMcLibelleFr;
	}

	/**
	 * [SeanceEmploiDto.enseignantNom] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the enseignantNom
	 */
	public String getEnseignantNom() {
		return enseignantNom;
	}

	/**
	 * [SeanceEmploiDto.enseignantNom] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param enseignantNom the enseignantNom to set
	 */
	public void setEnseignantNom(String enseignantNom) {
		this.enseignantNom = enseignantNom;
	}

	/**
	 * [SeanceEmploiDto.enseignantPrenom] Getter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @return the enseignantPrenom
	 */
	public String getEnseignantPrenom() {
		return enseignantPrenom;
	}

	/**
	 * [SeanceEmploiDto.enseignantPrenom] Setter 
	 * @author BELDI Jamel on : 26 oct. 2014  10:19:03
	 * @param enseignantPrenom the enseignantPrenom to set
	 */
	public void setEnseignantPrenom(String enseignantPrenom) {
		this.enseignantPrenom = enseignantPrenom;
	}

	

}
