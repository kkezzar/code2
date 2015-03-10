package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SectionDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:34:20
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String intituleFrCourt;
	private String intituleArCourt;
	private String description;
	private List<TitreDto> titres = new ArrayList<TitreDto>(0);
	private List<SousSectionDto> sousSections = new ArrayList<SousSectionDto>(0);

	public SectionDto() {
	}

	public SectionDto(Integer id) {
		this.id = id;
	
	}
	public SectionDto(Integer id, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public SectionDto(Integer id, String code, String intituleFr,
			String intituleAr, String description, List<TitreDto> titres,
			List<SousSectionDto> sousSections) {
		this.id = id;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.titres = titres;
		this.sousSections = sousSections;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TitreDto> getTitres() {
		return this.titres;
	}

	public void setTitres(List<TitreDto> titres) {
		this.titres = titres;
	}

	public List<SousSectionDto> getSousSections() {
		return this.sousSections;
	}

	public void setSousSections(List<SousSectionDto> sousSections) {
		this.sousSections = sousSections;
	}


	/**
	 * @return the intituleFrCourt
	 */
	public String getIntituleFrCourt() {
		return intituleFrCourt;
	}

	/**
	 * @param intituleFrCourt the intituleFrCourt to set
	 */
	public void setIntituleFrCourt(String intituleFrCourt) {
		this.intituleFrCourt = intituleFrCourt;
	}

	/**
	 * @return the intituleArCourt
	 */
	public String getIntituleArCourt() {
		return intituleArCourt;
	}

	/**
	 * @param intituleArCourt the intituleArCourt to set
	 */
	public void setIntituleArCourt(String intituleArCourt) {
		this.intituleArCourt = intituleArCourt;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionDto other = (SectionDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (sousSections == null) {
			if (other.sousSections != null)
				return false;
		} else if (!sousSections.equals(other.sousSections))
			return false;
		if (titres == null) {
			if (other.titres != null)
				return false;
		} else if (!titres.equals(other.titres))
			return false;
		return true;
	}

}
