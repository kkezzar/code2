package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class StageDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDebut;
	private Date dateFin;
	private Date dateDebutReelle;
	private Date dateFinReelle;
	private String motifDecision;
	private Date dateProlongation;
	private NomenclatureDto nomenclatureByDecisionStageDto;

	public StageDto() {
		nomenclatureByDecisionStageDto = new NomenclatureDto();
	}

	public StageDto(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dossierEmployeDto
	 */
	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	/**
	 * @param dossierEmployeDto
	 *            the dossierEmployeDto to set
	 */
	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the dateDebutReelle
	 */
	public Date getDateDebutReelle() {
		return dateDebutReelle;
	}

	/**
	 * @param dateDebutReelle
	 *            the dateDebutReelle to set
	 */
	public void setDateDebutReelle(Date dateDebutReelle) {
		this.dateDebutReelle = dateDebutReelle;
	}

	/**
	 * @return the dateFinReelle
	 */
	public Date getDateFinReelle() {
		return dateFinReelle;
	}

	/**
	 * @param dateFinReelle
	 *            the dateFinReelle to set
	 */
	public void setDateFinReelle(Date dateFinReelle) {
		this.dateFinReelle = dateFinReelle;
	}

	/**
	 * [StageDto.nomenclatureByDecisionStageDto] Getter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 09:46:05
	 * @return the nomenclatureByDecisionStageDto
	 */
	public NomenclatureDto getNomenclatureByDecisionStageDto() {
		return nomenclatureByDecisionStageDto;
	}

	/**
	 * [StageDto.nomenclatureByDecisionStageDto] Setter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 09:46:05
	 * @param nomenclatureByDecisionStageDto
	 *            the nomenclatureByDecisionStageDto to set
	 */
	public void setNomenclatureByDecisionStageDto(NomenclatureDto nomenclatureByDecisionStageDto) {
		this.nomenclatureByDecisionStageDto = nomenclatureByDecisionStageDto;
	}

	/**
	 * [StageDto.motifDecision] Getter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 10:23:11
	 * @return the motifDecision
	 */
	public String getMotifDecision() {
		return motifDecision;
	}

	/**
	 * [StageDto.motifDecision] Setter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 10:23:11
	 * @param motifDecision
	 *            the motifDecision to set
	 */
	public void setMotifDecision(String motifDecision) {
		this.motifDecision = motifDecision;
	}

	/**
	 * @return the dateProlongation
	 */
	public Date getDateProlongation() {
		return dateProlongation;
	}

	/**
	 * @param dateProlongation
	 *            the dateProlongation to set
	 */
	public void setDateProlongation(Date dateProlongation) {
		this.dateProlongation = dateProlongation;
	}

}
