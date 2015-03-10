package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

/**
 * Classe de type DTO représentant une répartition d'une unités d'enseignement
 * afféctué une offre de formation dans un semestre.
 * 
 * @author Kheireddine OMRANI
 * 
 */
public class RepartitionUeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;

	private Integer parcoursTypeId;
	private String parcoursTypeCode;
	private String parcoursTypeLibelleFr;
	private String parcoursTypeLibelleAr;

	private Integer uniteEnseignementId;
	private String uniteEnseignementCode;
	private String uniteEnseignementLibelleFr;
	private String uniteEnseignementLibelleAr;
	private Integer uniteEnseignementCredits;
	private double ueMoyenneGenerale;
	
	private String refCodeSemestre;

	private Integer groupAChoixId;
	private Integer groupAChoixNombreMax;
	private Integer groupAChoixNombreMin;

	private Double coefficient;
	private Double noteObtension;
	private Double noteDeBase;
	private Double noteEliminatoire;

	private Boolean optionnelle;
	private Boolean aChoix;

	private Integer idPeriode;
	private String codePeriode;
	private String libellePeriode;

	public RepartitionUeDto() {
		this.noteObtension = 10d;
		this.noteDeBase = 20d;
		this.noteEliminatoire = 0d;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParcoursTypeId() {
		return parcoursTypeId;
	}

	public void setParcoursTypeId(Integer parcoursTypeId) {
		this.parcoursTypeId = parcoursTypeId;
	}

	public String getParcoursTypeCode() {
		return parcoursTypeCode;
	}

	public void setParcoursTypeCode(String parcoursTypeCode) {
		this.parcoursTypeCode = parcoursTypeCode;
	}

	public String getParcoursTypeLibelleFr() {
		return parcoursTypeLibelleFr;
	}

	public void setParcoursTypeLibelleFr(String parcoursTypeLibelleFr) {
		this.parcoursTypeLibelleFr = parcoursTypeLibelleFr;
	}

	public String getParcoursTypeLibelleAr() {
		return parcoursTypeLibelleAr;
	}

	public void setParcoursTypeLibelleAr(String parcoursTypeLibelleAr) {
		this.parcoursTypeLibelleAr = parcoursTypeLibelleAr;
	}

	public Integer getUniteEnseignementId() {
		return uniteEnseignementId;
	}

	public void setUniteEnseignementId(Integer uniteEnseignementId) {
		this.uniteEnseignementId = uniteEnseignementId;
	}

	public String getUniteEnseignementCode() {
		return uniteEnseignementCode;
	}

	public void setUniteEnseignementCode(String uniteEnseignementCode) {
		this.uniteEnseignementCode = uniteEnseignementCode;
	}

	public String getUniteEnseignementLibelleFr() {
		return uniteEnseignementLibelleFr;
	}

	public void setUniteEnseignementLibelleFr(String uniteEnseignementLibelleFr) {
		this.uniteEnseignementLibelleFr = uniteEnseignementLibelleFr;
	}

	public String getUniteEnseignementLibelleAr() {
		return uniteEnseignementLibelleAr;
	}

	public void setUniteEnseignementLibelleAr(String uniteEnseignementLibelleAr) {
		this.uniteEnseignementLibelleAr = uniteEnseignementLibelleAr;
	}

	/**
	 * [RepartitionUeDto.uniteEnseignementCredits] Getter
	 * 
	 * @author rlaib on : 13 sept. 2014 10:01:13
	 * @return the uniteEnseignementCredits
	 */
	public Integer getUniteEnseignementCredits() {
		return uniteEnseignementCredits;
	}

	/**
	 * [RepartitionUeDto.uniteEnseignementCredits] Setter
	 * 
	 * @author rlaib on : 13 sept. 2014 10:01:13
	 * @param uniteEnseignementCredits
	 *            the uniteEnseignementCredits to set
	 */
	public void setUniteEnseignementCredits(Integer uniteEnseignementCredits) {
		this.uniteEnseignementCredits = uniteEnseignementCredits;
	}

	public String getRefCodeSemestre() {
		return refCodeSemestre;
	}

	public void setRefCodeSemestre(String refCodeSemestre) {
		this.refCodeSemestre = refCodeSemestre;
	}

	public Integer getGroupAChoixId() {
		return groupAChoixId;
	}

	public void setGroupAChoixId(Integer groupAChoixId) {
		this.groupAChoixId = groupAChoixId;
	}

	public Integer getGroupAChoixNombreMax() {
		return groupAChoixNombreMax;
	}

	public void setGroupAChoixNombreMax(Integer groupAChoixNombreMax) {
		this.groupAChoixNombreMax = groupAChoixNombreMax;
	}

	public Integer getGroupAChoixNombreMin() {
		return groupAChoixNombreMin;
	}

	public void setGroupAChoixNombreMin(Integer groupAChoixNombreMin) {
		this.groupAChoixNombreMin = groupAChoixNombreMin;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Double getNoteObtension() {
		return noteObtension;
	}

	public void setNoteObtension(Double noteObtension) {
		this.noteObtension = noteObtension;
	}

	public Double getNoteDeBase() {
		return noteDeBase;
	}

	public void setNoteDeBase(Double noteDeBase) {
		this.noteDeBase = noteDeBase;
	}

	public Double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	public void setNoteEliminatoire(Double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	public Boolean getOptionnelle() {
		return optionnelle;
	}

	public void setOptionnelle(Boolean optionnelle) {
		this.optionnelle = optionnelle;
	}

	public Boolean getaChoix() {
		return aChoix;
	}

	public void setaChoix(Boolean aChoix) {
		this.aChoix = aChoix;
	}

	/**
	 * [RepartitionUeDto.idPeriode] Getter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @return the idPeriode
	 */
	public Integer getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [RepartitionUeDto.idPeriode] Setter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @param idPeriode
	 *            the idPeriode to set
	 */
	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [RepartitionUeDto.codePeriode] Getter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @return the codePeriode
	 */
	public String getCodePeriode() {
		return codePeriode;
	}

	/**
	 * [RepartitionUeDto.codePeriode] Setter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @param codePeriode
	 *            the codePeriode to set
	 */
	public void setCodePeriode(String codePeriode) {
		this.codePeriode = codePeriode;
	}

	/**
	 * [RepartitionUeDto.libellePeriode] Getter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [RepartitionUeDto.libellePeriode] Setter
	 * 
	 * @author rlaib on : 14 août 2014 16:52:54
	 * @param libellePeriode
	 *            the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	/**
	 * [RepartitionUeDto.ueMoyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  17:35:04
	 * @return the ueMoyenneGenerale
	 */
	public double getUeMoyenneGenerale() {
		return ueMoyenneGenerale;
	}

	/**
	 * [RepartitionUeDto.ueMoyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 21 oct. 2014  17:35:04
	 * @param ueMoyenneGenerale the ueMoyenneGenerale to set
	 */
	public void setUeMoyenneGenerale(double ueMoyenneGenerale) {
		this.ueMoyenneGenerale = ueMoyenneGenerale;
	}


	

}
