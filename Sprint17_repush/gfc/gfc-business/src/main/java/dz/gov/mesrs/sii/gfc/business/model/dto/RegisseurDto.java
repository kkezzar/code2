package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class RegisseurDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:53
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RegieDto regie;
	private RefIndividuDto refIndividu;
	private String code;
	private String decisionNomination;
	private Date dateNomination;
	private Date dateFinNomination;

	public RegisseurDto() {
		regie = new RegieDto();
		refIndividu = new RefIndividuDto();
	}

	public RegisseurDto(Integer id, RefIndividuDto refIndividu) {
		this.id = id;
		this.refIndividu = refIndividu;
	}

	public RegisseurDto(Integer id, RegieDto regie, RefIndividuDto refIndividu, String code, String decisionNomination,
			Date dateNomination, Date dateFinNomination) {
		this.id = id;
		this.regie = regie;
		this.refIndividu = refIndividu;
		this.code = code;
		this.decisionNomination = decisionNomination;
		this.dateNomination = dateNomination;
		this.dateFinNomination = dateFinNomination;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RegieDto getRegie() {
		return this.regie;
	}

	public void setRegie(RegieDto regie) {
		this.regie = regie;
	}

	public RefIndividuDto getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividuDto refIndividu) {
		this.refIndividu = refIndividu;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDecisionNomination() {
		return this.decisionNomination;
	}

	public void setDecisionNomination(String decisionNomination) {
		this.decisionNomination = decisionNomination;
	}

	public Date getDateNomination() {
		return this.dateNomination;
	}

	public void setDateNomination(Date dateNomination) {
		this.dateNomination = dateNomination;
	}

	public Date getDateFinNomination() {
		return this.dateFinNomination;
	}

	public void setDateFinNomination(Date dateFinNomination) {
		this.dateFinNomination = dateFinNomination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dateFinNomination == null) ? 0 : dateFinNomination.hashCode());
		result = prime * result + ((dateNomination == null) ? 0 : dateNomination.hashCode());
		result = prime * result + ((decisionNomination == null) ? 0 : decisionNomination.hashCode());
		result = prime * result + id;
		result = prime * result + ((refIndividu == null) ? 0 : refIndividu.hashCode());
		result = prime * result + ((regie == null) ? 0 : regie.hashCode());
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
		RegisseurDto other = (RegisseurDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateFinNomination == null) {
			if (other.dateFinNomination != null)
				return false;
		} else if (!dateFinNomination.equals(other.dateFinNomination))
			return false;
		if (dateNomination == null) {
			if (other.dateNomination != null)
				return false;
		} else if (!dateNomination.equals(other.dateNomination))
			return false;
		if (decisionNomination == null) {
			if (other.decisionNomination != null)
				return false;
		} else if (!decisionNomination.equals(other.decisionNomination))
			return false;
		if (id != other.id)
			return false;
		if (refIndividu == null) {
			if (other.refIndividu != null)
				return false;
		} else if (!refIndividu.equals(other.refIndividu))
			return false;
		if (regie == null) {
			if (other.regie != null)
				return false;
		} else if (!regie.equals(other.regie))
			return false;
		return true;
	}

}
