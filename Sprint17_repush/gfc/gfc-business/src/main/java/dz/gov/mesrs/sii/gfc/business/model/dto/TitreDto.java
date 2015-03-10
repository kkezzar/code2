package dz.gov.mesrs.sii.gfc.business.model.dto;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.List;

public class TitreDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:34:09
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private SousSectionDto sousSection;
	private SectionDto section;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private String intituleFrCourt;
	private String intituleArCourt;
	private String description;
	private List<PartieDto> parties = new ArrayList<PartieDto>(0);
	private List<ChapitreDto> chapitres = new ArrayList<ChapitreDto>(0);

	public TitreDto() {
		section = new SectionDto();
		sousSection = new SousSectionDto();
	}

	public TitreDto(Integer id, SectionDto section, String code, String intituleFr, String intituleAr) {
		this.id = id;
		this.section = section;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public TitreDto(Integer id, SousSectionDto sousSection, SectionDto section, String code, String intituleFr,
			String intituleAr, String description, List<PartieDto> parties, List<ChapitreDto> chapitres) {
		this.id = id;
		this.sousSection = sousSection;
		this.section = section;
		this.code = code;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.description = description;
		this.parties = parties;
		this.chapitres = chapitres;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SousSectionDto getSousSection() {
		return this.sousSection;
	}

	public void setSousSection(SousSectionDto sousSection) {
		this.sousSection = sousSection;
	}

	public SectionDto getSection() {
		return this.section;
	}

	public void setSection(SectionDto section) {
		this.section = section;
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

	public List<PartieDto> getParties() {
		return this.parties;
	}

	public void setParties(List<PartieDto> parties) {
		this.parties = parties;
	}

	public List<ChapitreDto> getChapitres() {
		return this.chapitres;
	}

	public void setChapitres(List<ChapitreDto> chapitres) {
		this.chapitres = chapitres;
	}

	/**
	 * @return the intituleFrCourt
	 */
	public String getIntituleFrCourt() {
		return intituleFrCourt;
	}

	/**
	 * @param intituleFrCourt
	 *            the intituleFrCourt to set
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
	 * @param intituleArCourt
	 *            the intituleArCourt to set
	 */
	public void setIntituleArCourt(String intituleArCourt) {
		this.intituleArCourt = intituleArCourt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + ((sousSection == null) ? 0 : sousSection.hashCode());
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
		TitreDto other = (TitreDto) obj;
		if (chapitres == null) {
			if (other.chapitres != null)
				return false;
		} else if (!chapitres.equals(other.chapitres))
			return false;
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
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (parties == null) {
			if (other.parties != null)
				return false;
		} else if (!parties.equals(other.parties))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (sousSection == null) {
			if (other.sousSection != null)
				return false;
		} else if (!sousSection.equals(other.sousSection))
			return false;
		return true;
	}

}
