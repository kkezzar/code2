package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class ConjointDto implements Serializable {

	private static final long serialVersionUID = 8519728692401830016L;
	private Integer id;
	private RefStructureDto refStructureDto;
	private RefIndividuDto refIndividuDto;
	private NomenclatureDto nomenclatureByStatutDto;
	private DossierEmployeDto dossierEmployeDto;

	public ConjointDto() {
		refStructureDto = new RefStructureDto();
		refIndividuDto = new RefIndividuDto();
		nomenclatureByStatutDto = new NomenclatureDto();
		dossierEmployeDto = new DossierEmployeDto();
	}

	public ConjointDto(Integer id) {
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

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	public RefIndividuDto getRefIndividuDto() {
		return refIndividuDto;
	}

	public void setRefIndividuDto(RefIndividuDto refIndividuDto) {
		this.refIndividuDto = refIndividuDto;
	}

	public NomenclatureDto getNomenclatureByStatutDto() {
		return nomenclatureByStatutDto;
	}

	public void setNomenclatureByStatutDto(NomenclatureDto nomenclatureByStatutDto) {
		this.nomenclatureByStatutDto = nomenclatureByStatutDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

}
