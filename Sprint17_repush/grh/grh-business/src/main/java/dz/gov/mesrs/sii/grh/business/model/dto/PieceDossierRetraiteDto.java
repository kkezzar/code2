package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;



public class PieceDossierRetraiteDto implements Serializable {

	private static final long serialVersionUID = 7681763199088411669L;
	private Integer id;
	private FinActiviteDto finActivite;
	private Boolean prepare;
	private Date datePreparation;

	public PieceDossierRetraiteDto() {
	}

	public PieceDossierRetraiteDto(Integer id) {
		this.id = id;
	}

	public PieceDossierRetraiteDto(Integer id, FinActiviteDto finActivite,
			Boolean prepare, Date datePreparation) {
		this.id = id;
		this.finActivite = finActivite;
		this.prepare = prepare;
		this.datePreparation = datePreparation;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the finActivite
	 */
	public FinActiviteDto getFinActivite() {
		return finActivite;
	}

	/**
	 * @param finActivite the finActivite to set
	 */
	public void setFinActivite(FinActiviteDto finActivite) {
		this.finActivite = finActivite;
	}

	/**
	 * @return the prepare
	 */
	public Boolean getPrepare() {
		return prepare;
	}

	/**
	 * @param prepare the prepare to set
	 */
	public void setPrepare(Boolean prepare) {
		this.prepare = prepare;
	}

	/**
	 * @return the datePreparation
	 */
	public Date getDatePreparation() {
		return datePreparation;
	}

	/**
	 * @param datePreparation the datePreparation to set
	 */
	public void setDatePreparation(Date datePreparation) {
		this.datePreparation = datePreparation;
	}

	

}
