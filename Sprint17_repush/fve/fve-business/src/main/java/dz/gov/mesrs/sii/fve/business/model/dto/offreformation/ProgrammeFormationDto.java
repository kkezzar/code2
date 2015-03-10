package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

// Generated 7 janv. 2014 14:52:53 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * ProgrammeFormationDto generated by hbm2java
 */
public class ProgrammeFormationDto implements java.io.Serializable {

	private int id;
	private String code;
	private String libelleAr;
	private String libelleFr;
	private Set<OffreFormationDto> offreFormationDtos = new HashSet<OffreFormationDto>(
			0);

	public ProgrammeFormationDto() {
	}

	public ProgrammeFormationDto(int id, String code, String libelleFr) {
		this.id = id;
		this.code = code;
		this.libelleFr = libelleFr;
	}

	public ProgrammeFormationDto(int id, String code, String libelleAr,
			String libelleFr, Set<OffreFormationDto> offreFormationDtos) {
		this.id = id;
		this.code = code;
		this.libelleAr = libelleAr;
		this.libelleFr = libelleFr;
		this.offreFormationDtos = offreFormationDtos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleAr() {
		return this.libelleAr;
	}

	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	public String getLibelleFr() {
		return this.libelleFr;
	}

	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	public Set<OffreFormationDto> getOffreFormationDtos() {
		return this.offreFormationDtos;
	}

	public void setOffreFormationDtos(Set<OffreFormationDto> offreFormationDtos) {
		this.offreFormationDtos = offreFormationDtos;
	}

}