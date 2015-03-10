package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CorpsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclatureByIdNomenclatureFiliere;
	private StatutDto statut;
	private NomenclatureDto nomenclatureByIdNomenclatureCorps;
	private String label;
	private Set<CarriereDto> carrieres = new HashSet<CarriereDto>(0);
	private Set<DetailRecrutementDto> detailRecrutements = new HashSet<DetailRecrutementDto>(
			0);
	private Set<GradeDto> grades = new HashSet<GradeDto>(0);

	public CorpsDto() {
	}

	public CorpsDto(Integer id, StatutDto statut) {
		this.id = id;
		this.statut = statut;
	}

	public CorpsDto(Integer id,
			NomenclatureDto nomenclatureByIdNomenclatureFiliere,
			StatutDto statut,
			NomenclatureDto nomenclatureByIdNomenclatureCorps, String label,
			Set<CarriereDto> carrieres,
			Set<DetailRecrutementDto> detailRecrutements, Set<GradeDto> grades) {
		this.id = id;
		this.nomenclatureByIdNomenclatureFiliere = nomenclatureByIdNomenclatureFiliere;
		this.statut = statut;
		this.nomenclatureByIdNomenclatureCorps = nomenclatureByIdNomenclatureCorps;
		this.label = label;
		this.carrieres = carrieres;
		this.detailRecrutements = detailRecrutements;
		this.grades = grades;
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
	 * @return the nomenclatureByIdNomenclatureFiliere
	 */
	public NomenclatureDto getNomenclatureByIdNomenclatureFiliere() {
		return nomenclatureByIdNomenclatureFiliere;
	}

	/**
	 * @param nomenclatureByIdNomenclatureFiliere
	 *            the nomenclatureByIdNomenclatureFiliere to set
	 */
	public void setNomenclatureByIdNomenclatureFiliere(
			NomenclatureDto nomenclatureByIdNomenclatureFiliere) {
		this.nomenclatureByIdNomenclatureFiliere = nomenclatureByIdNomenclatureFiliere;
	}

	/**
	 * @return the statut
	 */
	public StatutDto getStatut() {
		return statut;
	}

	/**
	 * @param statut
	 *            the statut to set
	 */
	public void setStatut(StatutDto statut) {
		this.statut = statut;
	}

	/**
	 * @return the nomenclatureByIdNomenclatureCorps
	 */
	public NomenclatureDto getNomenclatureByIdNomenclatureCorps() {
		return nomenclatureByIdNomenclatureCorps;
	}

	/**
	 * @param nomenclatureByIdNomenclatureCorps
	 *            the nomenclatureByIdNomenclatureCorps to set
	 */
	public void setNomenclatureByIdNomenclatureCorps(
			NomenclatureDto nomenclatureByIdNomenclatureCorps) {
		this.nomenclatureByIdNomenclatureCorps = nomenclatureByIdNomenclatureCorps;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the carrieres
	 */
	public Set<CarriereDto> getCarrieres() {
		return carrieres;
	}

	/**
	 * @param carrieres
	 *            the carrieres to set
	 */
	public void setCarrieres(Set<CarriereDto> carrieres) {
		this.carrieres = carrieres;
	}

	/**
	 * @return the detailRecrutements
	 */
	public Set<DetailRecrutementDto> getDetailRecrutements() {
		return detailRecrutements;
	}

	/**
	 * @param detailRecrutements
	 *            the detailRecrutements to set
	 */
	public void setDetailRecrutements(
			Set<DetailRecrutementDto> detailRecrutements) {
		this.detailRecrutements = detailRecrutements;
	}

	/**
	 * @return the grades
	 */
	public Set<GradeDto> getGrades() {
		return grades;
	}

	/**
	 * @param grades
	 *            the grades to set
	 */
	public void setGrades(Set<GradeDto> grades) {
		this.grades = grades;
	}

}
