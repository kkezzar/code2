package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;



import java.util.Date;

/**
 * 
 * @author BELDI Jamel  on : 7 oct. 2014  17:49:29
 */

public class AffectationLieuStructureDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 7 oct. 2014  17:49:48
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//private Nomenclature nomenclatureByIdNcPeriodicite;
	private Integer periodiciteId;
	private String periodiciteLibelleLongFr;
	private String periodiciteLibelleLongAr;	
	private String periodiciteCode;
	//private Nomenclature nomenclatureByIdNcPeriode;
	private Integer periodeId;
	private String periodeLibelleLongFr;
	private String periodeLibelleLongAr;	
	private String periodeCode;
	//private RefStructure refStructure;
	private Integer refStructureId;	
	private Integer refStructureTypeId;	
	private String llStructureArabe;
	private String llStructureLatin;
	private String refStructureTypeLibelle;	
	//private RefLieu refLieu;
	private Integer refLieuId;
	private String refLieuDesignation;
	private String refLieudesignationArabe;
	private Integer refLieuTypeId;
	private String refLieuTypeLibelleLongFr;
	private String refLieuTypeLibelleLongAr;	
	private String refLieuTypelieuCode;
	
	//private CelluleHoraireDto celluleHoraire;
	private Integer celluleHoraireId;
	private Integer plageHoraireId;
	private String plageHoraireCode;
	private short plageHoraireNumero;
	private String plageHoraireLibelleFr;
	private String plageHoraireLibelleAr;
	private Date plageHoraireHeureDebut;
	private Date plageHoraireHeureFin;
	//private Jour jour;
	private Integer jourId;
	private String jourCode;
	private String jourLibelleFr;
	private String jourLibelleAr;
	
	//private AnneeAcademique anneeAcademique;
	private Integer anneeAcademiqueId;
	private String anneeAcademiqueCode;
	
	//private OuvertureOffreFormation ouvertureOffreFormation;
	private Integer ouvertureOffreFormationId;
	private Integer offreFormationId;
	private String offreFormationCode;
	private String offreFormationLibelleFr;
	private String offreFormationLibelleAr;
	
	private String refCodeEtablissement;

	public AffectationLieuStructureDto() {
	}

	/**
	 * [AffectationLieuStructureDto.id] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [AffectationLieuStructureDto.id] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodiciteId
	 */
	public Integer getPeriodiciteId() {
		return periodiciteId;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodiciteId the periodiciteId to set
	 */
	public void setPeriodiciteId(Integer periodiciteId) {
		this.periodiciteId = periodiciteId;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodiciteLibelleLongFr
	 */
	public String getPeriodiciteLibelleLongFr() {
		return periodiciteLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodiciteLibelleLongFr the periodiciteLibelleLongFr to set
	 */
	public void setPeriodiciteLibelleLongFr(String periodiciteLibelleLongFr) {
		this.periodiciteLibelleLongFr = periodiciteLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodiciteLibelleLongAr
	 */
	public String getPeriodiciteLibelleLongAr() {
		return periodiciteLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodiciteLibelleLongAr the periodiciteLibelleLongAr to set
	 */
	public void setPeriodiciteLibelleLongAr(String periodiciteLibelleLongAr) {
		this.periodiciteLibelleLongAr = periodiciteLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodiciteCode
	 */
	public String getPeriodiciteCode() {
		return periodiciteCode;
	}

	/**
	 * [AffectationLieuStructureDto.periodiciteCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodiciteCode the periodiciteCode to set
	 */
	public void setPeriodiciteCode(String periodiciteCode) {
		this.periodiciteCode = periodiciteCode;
	}

	/**
	 * [AffectationLieuStructureDto.periodeId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [AffectationLieuStructureDto.periodeId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodeId the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [AffectationLieuStructureDto.periodeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodeLibelleLongFr
	 */
	public String getPeriodeLibelleLongFr() {
		return periodeLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.periodeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodeLibelleLongFr the periodeLibelleLongFr to set
	 */
	public void setPeriodeLibelleLongFr(String periodeLibelleLongFr) {
		this.periodeLibelleLongFr = periodeLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.periodeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodeLibelleLongAr
	 */
	public String getPeriodeLibelleLongAr() {
		return periodeLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.periodeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodeLibelleLongAr the periodeLibelleLongAr to set
	 */
	public void setPeriodeLibelleLongAr(String periodeLibelleLongAr) {
		this.periodeLibelleLongAr = periodeLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.periodeCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the periodeCode
	 */
	public String getPeriodeCode() {
		return periodeCode;
	}

	/**
	 * [AffectationLieuStructureDto.periodeCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param periodeCode the periodeCode to set
	 */
	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refStructureId
	 */
	public Integer getRefStructureId() {
		return refStructureId;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refStructureId the refStructureId to set
	 */
	public void setRefStructureId(Integer refStructureId) {
		this.refStructureId = refStructureId;
	}

	/**
	 * [AffectationLieuStructureDto.llStructureArabe] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * [AffectationLieuStructureDto.llStructureArabe] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * [AffectationLieuStructureDto.llStructureLatin] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * [AffectationLieuStructureDto.llStructureLatin] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuId
	 */
	public Integer getRefLieuId() {
		return refLieuId;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuId the refLieuId to set
	 */
	public void setRefLieuId(Integer refLieuId) {
		this.refLieuId = refLieuId;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuDesignation] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuDesignation
	 */
	public String getRefLieuDesignation() {
		return refLieuDesignation;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuDesignation] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuDesignation the refLieuDesignation to set
	 */
	public void setRefLieuDesignation(String refLieuDesignation) {
		this.refLieuDesignation = refLieuDesignation;
	}

	/**
	 * [AffectationLieuStructureDto.refLieudesignationArabe] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieudesignationArabe
	 */
	public String getRefLieudesignationArabe() {
		return refLieudesignationArabe;
	}

	/**
	 * [AffectationLieuStructureDto.refLieudesignationArabe] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieudesignationArabe the refLieudesignationArabe to set
	 */
	public void setRefLieudesignationArabe(String refLieudesignationArabe) {
		this.refLieudesignationArabe = refLieudesignationArabe;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuTypeId
	 */
	public Integer getRefLieuTypeId() {
		return refLieuTypeId;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuTypeId the refLieuTypeId to set
	 */
	public void setRefLieuTypeId(Integer refLieuTypeId) {
		this.refLieuTypeId = refLieuTypeId;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuTypeLibelleLongFr
	 */
	public String getRefLieuTypeLibelleLongFr() {
		return refLieuTypeLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuTypeLibelleLongFr the refLieuTypeLibelleLongFr to set
	 */
	public void setRefLieuTypeLibelleLongFr(String refLieuTypeLibelleLongFr) {
		this.refLieuTypeLibelleLongFr = refLieuTypeLibelleLongFr;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuTypeLibelleLongAr
	 */
	public String getRefLieuTypeLibelleLongAr() {
		return refLieuTypeLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuTypeLibelleLongAr the refLieuTypeLibelleLongAr to set
	 */
	public void setRefLieuTypeLibelleLongAr(String refLieuTypeLibelleLongAr) {
		this.refLieuTypeLibelleLongAr = refLieuTypeLibelleLongAr;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypelieuCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the refLieuTypelieuCode
	 */
	public String getRefLieuTypelieuCode() {
		return refLieuTypelieuCode;
	}

	/**
	 * [AffectationLieuStructureDto.refLieuTypelieuCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param refLieuTypelieuCode the refLieuTypelieuCode to set
	 */
	public void setRefLieuTypelieuCode(String refLieuTypelieuCode) {
		this.refLieuTypelieuCode = refLieuTypelieuCode;
	}

	/**
	 * [AffectationLieuStructureDto.celluleHoraireId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the celluleHoraireId
	 */
	public Integer getCelluleHoraireId() {
		return celluleHoraireId;
	}

	/**
	 * [AffectationLieuStructureDto.celluleHoraireId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param celluleHoraireId the celluleHoraireId to set
	 */
	public void setCelluleHoraireId(Integer celluleHoraireId) {
		this.celluleHoraireId = celluleHoraireId;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireId
	 */
	public Integer getPlageHoraireId() {
		return plageHoraireId;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireId the plageHoraireId to set
	 */
	public void setPlageHoraireId(Integer plageHoraireId) {
		this.plageHoraireId = plageHoraireId;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireCode
	 */
	public String getPlageHoraireCode() {
		return plageHoraireCode;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireCode the plageHoraireCode to set
	 */
	public void setPlageHoraireCode(String plageHoraireCode) {
		this.plageHoraireCode = plageHoraireCode;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireNumero] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireNumero
	 */
	public short getPlageHoraireNumero() {
		return plageHoraireNumero;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireNumero] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireNumero the plageHoraireNumero to set
	 */
	public void setPlageHoraireNumero(short plageHoraireNumero) {
		this.plageHoraireNumero = plageHoraireNumero;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireLibelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireLibelleFr
	 */
	public String getPlageHoraireLibelleFr() {
		return plageHoraireLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireLibelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireLibelleFr the plageHoraireLibelleFr to set
	 */
	public void setPlageHoraireLibelleFr(String plageHoraireLibelleFr) {
		this.plageHoraireLibelleFr = plageHoraireLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireLibelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireLibelleAr
	 */
	public String getPlageHoraireLibelleAr() {
		return plageHoraireLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireLibelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireLibelleAr the plageHoraireLibelleAr to set
	 */
	public void setPlageHoraireLibelleAr(String plageHoraireLibelleAr) {
		this.plageHoraireLibelleAr = plageHoraireLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireHeureDebut] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireHeureDebut
	 */
	public Date getPlageHoraireHeureDebut() {
		return plageHoraireHeureDebut;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireHeureDebut] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireHeureDebut the plageHoraireHeureDebut to set
	 */
	public void setPlageHoraireHeureDebut(Date plageHoraireHeureDebut) {
		this.plageHoraireHeureDebut = plageHoraireHeureDebut;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireHeureFin] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the plageHoraireHeureFin
	 */
	public Date getPlageHoraireHeureFin() {
		return plageHoraireHeureFin;
	}

	/**
	 * [AffectationLieuStructureDto.plageHoraireHeureFin] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param plageHoraireHeureFin the plageHoraireHeureFin to set
	 */
	public void setPlageHoraireHeureFin(Date plageHoraireHeureFin) {
		this.plageHoraireHeureFin = plageHoraireHeureFin;
	}

	/**
	 * [AffectationLieuStructureDto.jourId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the jourId
	 */
	public Integer getJourId() {
		return jourId;
	}

	/**
	 * [AffectationLieuStructureDto.jourId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param jourId the jourId to set
	 */
	public void setJourId(Integer jourId) {
		this.jourId = jourId;
	}

	/**
	 * [AffectationLieuStructureDto.jourCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the jourCode
	 */
	public String getJourCode() {
		return jourCode;
	}

	/**
	 * [AffectationLieuStructureDto.jourCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param jourCode the jourCode to set
	 */
	public void setJourCode(String jourCode) {
		this.jourCode = jourCode;
	}

	/**
	 * [AffectationLieuStructureDto.jourLibelleFr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the jourLibelleFr
	 */
	public String getJourLibelleFr() {
		return jourLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.jourLibelleFr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param jourLibelleFr the jourLibelleFr to set
	 */
	public void setJourLibelleFr(String jourLibelleFr) {
		this.jourLibelleFr = jourLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.jourLibelleAr] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the jourLibelleAr
	 */
	public String getJourLibelleAr() {
		return jourLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.jourLibelleAr] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param jourLibelleAr the jourLibelleAr to set
	 */
	public void setJourLibelleAr(String jourLibelleAr) {
		this.jourLibelleAr = jourLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.anneeAcademiqueId] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the anneeAcademiqueId
	 */
	public Integer getAnneeAcademiqueId() {
		return anneeAcademiqueId;
	}

	/**
	 * [AffectationLieuStructureDto.anneeAcademiqueId] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param anneeAcademiqueId the anneeAcademiqueId to set
	 */
	public void setAnneeAcademiqueId(Integer anneeAcademiqueId) {
		this.anneeAcademiqueId = anneeAcademiqueId;
	}

	/**
	 * [AffectationLieuStructureDto.anneeAcademiqueCode] Getter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @return the anneeAcademiqueCode
	 */
	public String getAnneeAcademiqueCode() {
		return anneeAcademiqueCode;
	}

	/**
	 * [AffectationLieuStructureDto.anneeAcademiqueCode] Setter 
	 * @author BELDI Jamel on : 7 oct. 2014  18:26:31
	 * @param anneeAcademiqueCode the anneeAcademiqueCode to set
	 */
	public void setAnneeAcademiqueCode(String anneeAcademiqueCode) {
		this.anneeAcademiqueCode = anneeAcademiqueCode;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureTypeId] Getter 
	 * @author BELDI Jamel on : 9 oct. 2014  17:30:52
	 * @return the refStructureTypeId
	 */
	public Integer getRefStructureTypeId() {
		return refStructureTypeId;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureTypeId] Setter 
	 * @author BELDI Jamel on : 9 oct. 2014  17:30:52
	 * @param refStructureTypeId the refStructureTypeId to set
	 */
	public void setRefStructureTypeId(Integer refStructureTypeId) {
		this.refStructureTypeId = refStructureTypeId;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureTypeLibelle] Getter 
	 * @author BELDI Jamel on : 9 oct. 2014  17:30:52
	 * @return the refStructureTypeLibelle
	 */
	public String getRefStructureTypeLibelle() {
		return refStructureTypeLibelle;
	}

	/**
	 * [AffectationLieuStructureDto.refStructureTypeLibelle] Setter 
	 * @author BELDI Jamel on : 9 oct. 2014  17:30:52
	 * @param refStructureTypeLibelle the refStructureTypeLibelle to set
	 */
	public void setRefStructureTypeLibelle(String refStructureTypeLibelle) {
		this.refStructureTypeLibelle = refStructureTypeLibelle;
	}

	/**
	 * [AffectationLieuStructureDto.ouvertureOffreFormationId] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @return the ouvertureOffreFormationId
	 */
	public Integer getOuvertureOffreFormationId() {
		return ouvertureOffreFormationId;
	}

	/**
	 * [AffectationLieuStructureDto.ouvertureOffreFormationId] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @param ouvertureOffreFormationId the ouvertureOffreFormationId to set
	 */
	public void setOuvertureOffreFormationId(Integer ouvertureOffreFormationId) {
		this.ouvertureOffreFormationId = ouvertureOffreFormationId;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationId] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @return the offreFormationId
	 */
	public Integer getOffreFormationId() {
		return offreFormationId;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationId] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @param offreFormationId the offreFormationId to set
	 */
	public void setOffreFormationId(Integer offreFormationId) {
		this.offreFormationId = offreFormationId;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationCode] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @return the offreFormationCode
	 */
	public String getOffreFormationCode() {
		return offreFormationCode;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationCode] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @param offreFormationCode the offreFormationCode to set
	 */
	public void setOffreFormationCode(String offreFormationCode) {
		this.offreFormationCode = offreFormationCode;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationLibelleFr] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @return the offreFormationLibelleFr
	 */
	public String getOffreFormationLibelleFr() {
		return offreFormationLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationLibelleFr] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @param offreFormationLibelleFr the offreFormationLibelleFr to set
	 */
	public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
		this.offreFormationLibelleFr = offreFormationLibelleFr;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationLibelleAr] Getter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @return the offreFormationLibelleAr
	 */
	public String getOffreFormationLibelleAr() {
		return offreFormationLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.offreFormationLibelleAr] Setter 
	 * @author BELDI Jamel on : 13 oct. 2014  16:17:53
	 * @param offreFormationLibelleAr the offreFormationLibelleAr to set
	 */
	public void setOffreFormationLibelleAr(String offreFormationLibelleAr) {
		this.offreFormationLibelleAr = offreFormationLibelleAr;
	}

	/**
	 * [AffectationLieuStructureDto.refCodeEtablissement] Getter 
	 * @author BELDI Jamel on : 9 nov. 2014  12:24:22
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [AffectationLieuStructureDto.refCodeEtablissement] Setter 
	 * @author BELDI Jamel on : 9 nov. 2014  12:24:23
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	
	
	

}
