package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class DistinctionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5148131002287477809L;
	private Integer id;
	private NomenclatureDto nomenclatureByDistinctionDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDistinction;

	public DistinctionDto() {
		nomenclatureByDistinctionDto = new NomenclatureDto();
		dossierEmployeDto = new DossierEmployeDto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByDistinctionDto() {
		return nomenclatureByDistinctionDto;
	}

	public void setNomenclatureByDistinctionDto(NomenclatureDto nomenclatureByDistinctionDto) {
		this.nomenclatureByDistinctionDto = nomenclatureByDistinctionDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateDistinction() {
		return dateDistinction;
	}

	public void setDateDistinction(Date dateDistinction) {
		this.dateDistinction = dateDistinction;
	}

}
