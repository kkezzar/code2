package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class DossierMedicalDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private DossierEmployeDto dossierEmployeDto;
	private NomenclatureDto nomenclatureDto;
	private Date dateSurvenue;
	private String observation;
	private Boolean longDuree;
	private Boolean antecedent;
	private Boolean allergie;
	private Boolean pathologie;
	
	public DossierMedicalDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public Date getDateSurvenue() {
		return dateSurvenue;
	}

	public void setDateSurvenue(Date dateSurvenue) {
		this.dateSurvenue = dateSurvenue;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Boolean getLongDuree() {
		return longDuree;
	}

	public void setLongDuree(Boolean longDuree) {
		this.longDuree = longDuree;
	}

	public Boolean getAntecedent() {
		return antecedent;
	}

	public void setAntecedent(Boolean antecedent) {
		this.antecedent = antecedent;
	}

	public Boolean getAllergie() {
		return allergie;
	}

	public void setAllergie(Boolean allergie) {
		this.allergie = allergie;
	}

	public Boolean getPathologie() {
		return pathologie;
	}

	public void setPathologie(Boolean pathologie) {
		this.pathologie = pathologie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allergie == null) ? 0 : allergie.hashCode());
		result = prime * result
				+ ((antecedent == null) ? 0 : antecedent.hashCode());
		result = prime * result
				+ ((dateSurvenue == null) ? 0 : dateSurvenue.hashCode());
		result = prime
				* result
				+ ((dossierEmployeDto == null) ? 0 : dossierEmployeDto
						.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((longDuree == null) ? 0 : longDuree.hashCode());
		result = prime * result
				+ ((nomenclatureDto == null) ? 0 : nomenclatureDto.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		result = prime * result
				+ ((pathologie == null) ? 0 : pathologie.hashCode());
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
		DossierMedicalDto other = (DossierMedicalDto) obj;
		if (allergie == null) {
			if (other.allergie != null)
				return false;
		} else if (!allergie.equals(other.allergie))
			return false;
		if (antecedent == null) {
			if (other.antecedent != null)
				return false;
		} else if (!antecedent.equals(other.antecedent))
			return false;
		if (dateSurvenue == null) {
			if (other.dateSurvenue != null)
				return false;
		} else if (!dateSurvenue.equals(other.dateSurvenue))
			return false;
		if (dossierEmployeDto == null) {
			if (other.dossierEmployeDto != null)
				return false;
		} else if (!dossierEmployeDto.equals(other.dossierEmployeDto))
			return false;
		if (id != other.id)
			return false;
		if (longDuree == null) {
			if (other.longDuree != null)
				return false;
		} else if (!longDuree.equals(other.longDuree))
			return false;
		if (nomenclatureDto == null) {
			if (other.nomenclatureDto != null)
				return false;
		} else if (!nomenclatureDto.equals(other.nomenclatureDto))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (pathologie == null) {
			if (other.pathologie != null)
				return false;
		} else if (!pathologie.equals(other.pathologie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DossierMedicalDto [id=" + id + ", dossierEmployeDto="
				+ dossierEmployeDto + ", nomenclatureDto=" + nomenclatureDto
				+ ", dateSurvenue=" + dateSurvenue + ", observation="
				+ observation + ", longDuree=" + longDuree + ", antecedent="
				+ antecedent + ", allergie=" + allergie + ", pathologie="
				+ pathologie + "]";
	}

	
	
	
}
