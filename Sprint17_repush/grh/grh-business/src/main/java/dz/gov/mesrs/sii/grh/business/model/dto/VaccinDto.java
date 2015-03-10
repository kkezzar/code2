package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class VaccinDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclatureDto;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateVaccin;
	private Date dateRappel;

	public VaccinDto() {
	}

	public VaccinDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public Date getDateVaccin() {
		return dateVaccin;
	}

	public void setDateVaccin(Date dateVaccin) {
		this.dateVaccin = dateVaccin;
	}

	public Date getDateRappel() {
		return dateRappel;
	}

	public void setDateRappel(Date dateRappel) {
		this.dateRappel = dateRappel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateRappel == null) ? 0 : dateRappel.hashCode());
		result = prime * result
				+ ((dateVaccin == null) ? 0 : dateVaccin.hashCode());
		result = prime
				* result
				+ ((dossierEmployeDto == null) ? 0 : dossierEmployeDto
						.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((nomenclatureDto == null) ? 0 : nomenclatureDto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VaccinDto other = (VaccinDto) obj;
		if (dateRappel == null) {
			if (other.dateRappel != null)
				return false;
		} else if (!dateRappel.equals(other.dateRappel))
			return false;
		if (dateVaccin == null) {
			if (other.dateVaccin != null)
				return false;
		} else if (!dateVaccin.equals(other.dateVaccin))
			return false;
		if (dossierEmployeDto == null) {
			if (other.dossierEmployeDto != null)
				return false;
		} else if (!dossierEmployeDto.equals(other.dossierEmployeDto))
			return false;
		if (id != other.id)
			return false;
		if (nomenclatureDto == null) {
			if (other.nomenclatureDto != null)
				return false;
		} else if (!nomenclatureDto.equals(other.nomenclatureDto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VaccinDto [id=" + id + ", nomenclatureDto=" + nomenclatureDto
				+ ", dossierEmployeDto=" + dossierEmployeDto + ", dateVaccin="
				+ dateVaccin + ", dateRappel=" + dateRappel + "]";
	}

	
	
	
}
