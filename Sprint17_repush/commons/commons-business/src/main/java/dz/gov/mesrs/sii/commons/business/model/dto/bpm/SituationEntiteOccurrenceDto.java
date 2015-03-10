/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

import java.util.Date;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class SituationEntiteOccurrenceDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:55
	 */
	private static final long serialVersionUID = -3567831470720574121L;
	private int id;
	private int idSituation;
	private int idOccurrence;
	private Date dateSituation;
	private String libelleSituation;
	private String styleCssSituation;
	private Integer refCompteId;
	private String nomUtilisateur;
	private String prenomUtilisateur;

	public SituationEntiteOccurrenceDto() {

	}

	/**
	 * [SituationEntiteOccurrenceDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [SituationEntiteOccurrenceDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [SituationEntiteOccurrenceDto.idSituation] Getter
	 * 
	 * @author rlaib on : 5 mai 2014 17:36:02
	 * @return the idSituation
	 */
	public int getIdSituation() {
		return idSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.idSituation] Setter
	 * 
	 * @author rlaib on : 5 mai 2014 17:36:02
	 * @param idSituation
	 *            the idSituation to set
	 */
	public void setIdSituation(int idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.idOccurrence] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @return the idOccurrence
	 */
	public int getIdOccurrence() {
		return idOccurrence;
	}

	/**
	 * [SituationEntiteOccurrenceDto.idOccurrence] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @param idOccurrence
	 *            the idOccurrence to set
	 */
	public void setIdOccurrence(int idOccurrence) {
		this.idOccurrence = idOccurrence;
	}

	/**
	 * [SituationEntiteOccurrenceDto.date_situation] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @return the date_situation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.date_situation] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:54:50
	 * @param date_situation
	 *            the date_situation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.libelleSituation] Getter
	 * 
	 * @author Rafik LAIB on : 18 mai 2014 14:23:00
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.libelleSituation] Setter
	 * 
	 * @author Rafik LAIB on : 18 mai 2014 14:23:00
	 * @param libelleSituation
	 *            the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.styleCssSituation] Getter
	 * 
	 * @author rlaib on : 20 oct. 2014 14:13:17
	 * @return the styleCssSituation
	 */
	public String getStyleCssSituation() {
		return styleCssSituation;
	}

	/**
	 * [SituationEntiteOccurrenceDto.styleCssSituation] Setter
	 * 
	 * @author rlaib on : 20 oct. 2014 14:13:17
	 * @param styleCssSituation
	 *            the styleCssSituation to set
	 */
	public void setStyleCssSituation(String styleCssSituation) {
		this.styleCssSituation = styleCssSituation;
	}

	public Integer getRefCompteId() {
		return refCompteId;
	}

	public void setRefCompteId(Integer refCompteId) {
		this.refCompteId = refCompteId;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateSituation == null) ? 0 : dateSituation.hashCode());
		result = prime * result + id;
		result = prime * result + idOccurrence;
		result = prime * result + idSituation;
		result = prime * result
				+ ((refCompteId == null) ? 0 : refCompteId.hashCode());
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
		SituationEntiteOccurrenceDto other = (SituationEntiteOccurrenceDto) obj;
		if (dateSituation == null) {
			if (other.dateSituation != null)
				return false;
		} else if (!dateSituation.equals(other.dateSituation))
			return false;
		if (id != other.id)
			return false;
		if (idOccurrence != other.idOccurrence)
			return false;
		if (idSituation != other.idSituation)
			return false;
		if (refCompteId == null) {
			if (other.refCompteId != null)
				return false;
		} else if (!refCompteId.equals(other.refCompteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SituationEntiteOccurrenceDto [dateSituation=" + dateSituation
				+ ", libelleSituation=" + libelleSituation
				+ ", nomUtilisateur=" + nomUtilisateur + ", prenomUtilisateur="
				+ prenomUtilisateur + "]";
	}

	
}
