/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;

/**
 * @author rlaib on : 29 avr. 2014 15:30:20
 */
public class SituationEntiteDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author rlaib on : 29 avr. 2014 15:53:43
	 */
	private static final long serialVersionUID = 54952825420906123L;
	private int id;
	private int idSituation;
	private int idEntite;
	private String libelleSituation;
	private String codeEntite;
	private String codeSituation;

	public SituationEntiteDto() {

	}

	/**
	 * [SituationEntiteDto.id] Getter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:53:38
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [SituationEntiteDto.id] Setter
	 * 
	 * @author rlaib on : 29 avr. 2014 15:53:38
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [SituationEntiteDto.idSituation] Getter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 17:58:55
	 * @return the idSituation
	 */
	public int getIdSituation() {
		return idSituation;
	}

	/**
	 * [SituationEntiteDto.idSituation] Setter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 17:58:55
	 * @param idSituation
	 *            the idSituation to set
	 */
	public void setIdSituation(int idSituation) {
		this.idSituation = idSituation;
	}

	/**
	 * [SituationEntiteDto.idEntite] Getter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 17:58:55
	 * @return the idEntite
	 */
	public int getIdEntite() {
		return idEntite;
	}

	/**
	 * [SituationEntiteDto.idEntite] Setter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 17:58:55
	 * @param idEntite
	 *            the idEntite to set
	 */
	public void setIdEntite(int idEntite) {
		this.idEntite = idEntite;
	}

	/**
	 * [SituationEntiteDto.libelleSituation] Getter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:00:15
	 * @return the libelleSituation
	 */
	public String getLibelleSituation() {
		return libelleSituation;
	}

	/**
	 * [SituationEntiteDto.libelleSituation] Setter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:00:15
	 * @param libelleSituation
	 *            the libelleSituation to set
	 */
	public void setLibelleSituation(String libelleSituation) {
		this.libelleSituation = libelleSituation;
	}

	/**
	 * [SituationEntiteDto.codeEntite] Getter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:00:15
	 * @return the codeEntite
	 */
	public String getCodeEntite() {
		return codeEntite;
	}

	/**
	 * [SituationEntiteDto.codeEntite] Setter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:00:15
	 * @param codeEntite
	 *            the codeEntite to set
	 */
	public void setCodeEntite(String codeEntite) {
		this.codeEntite = codeEntite;
	}

	/**
	 * [SituationEntiteDto.codeSituation] Getter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:02:34
	 * @return the codeSituation
	 */
	public String getCodeSituation() {
		return codeSituation;
	}

	/**
	 * [SituationEntiteDto.codeSituation] Setter
	 * 
	 * @author Rafik LAIB on : 2 mai 2014 18:02:34
	 * @param codeSituation
	 *            the codeSituation to set
	 */
	public void setCodeSituation(String codeSituation) {
		this.codeSituation = codeSituation;
	}

}
