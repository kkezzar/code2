package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

public class PrevisionCongeDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -928985053623305549L;
	private Long id;
	private DossierEmployeDto dossierEmployeDto;
	private Date dateDebut;
	private String observation;
	private Double nbreJours;

	public PrevisionCongeDto() {
	}

	public PrevisionCongeDto(Long id) {
		this.id = id;
	}

	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	/**
	 * @return the dossierEmployeDto
	 */
	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	/**
	 * @param dossierEmployeDto
	 *            the dossierEmployeDto to set
	 */
	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the nbreJours
	 */
	public Double getNbreJours() {
		return nbreJours;
	}

	/**
	 * @param nbreJours
	 *            the nbreJours to set
	 */
	public void setNbreJours(Double nbreJours) {
		this.nbreJours = nbreJours;
	}

}
