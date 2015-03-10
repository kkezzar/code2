package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;



public class AttestationFinEtudeEditionDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	//private AttestationFinEtude attestationFinEtude;
	private Integer attestationFinEtudeId;
	private Boolean estValide;
	private Date dateEdition;
	private String refTypeImpression;
	private String service;
	private String observation;
	private String agent;


	public AttestationFinEtudeEditionDto() {
	}


	/**
	 * [attestationFinEtudeEditionDto.id] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * [attestationFinEtudeEditionDto.id] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	

	/**
	 * [attestationFinEtudeEditionDto.estValide] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the estValide
	 */
	public Boolean getEstValide() {
		return estValide;
	}


	/**
	 * [attestationFinEtudeEditionDto.estValide] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param estValide the estValide to set
	 */
	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}


	/**
	 * [attestationFinEtudeEditionDto.dateEdition] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the dateEdition
	 */
	public Date getDateEdition() {
		return dateEdition;
	}


	/**
	 * [attestationFinEtudeEditionDto.dateEdition] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param dateEdition the dateEdition to set
	 */
	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}


	/**
	 * [attestationFinEtudeEditionDto.refTypeImpression] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the refTypeImpression
	 */
	public String getRefTypeImpression() {
		return refTypeImpression;
	}


	/**
	 * [attestationFinEtudeEditionDto.refTypeImpression] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param refTypeImpression the refTypeImpression to set
	 */
	public void setRefTypeImpression(String refTypeImpression) {
		this.refTypeImpression = refTypeImpression;
	}


	/**
	 * [attestationFinEtudeEditionDto.service] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the service
	 */
	public String getService() {
		return service;
	}


	/**
	 * [attestationFinEtudeEditionDto.service] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}


	/**
	 * [attestationFinEtudeEditionDto.observation] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}


	/**
	 * [attestationFinEtudeEditionDto.observation] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}


	/**
	 * [attestationFinEtudeEditionDto.attestationFinEtudeId] Getter 
	 * @author BELBRIK Oualid on : 13 oct. 2014  17:13:12
	 * @return the attestationFinEtudeId
	 */
	public Integer getattestationFinEtudeId() {
		return attestationFinEtudeId;
	}


	/**
	 * [attestationFinEtudeEditionDto.attestationFinEtudeId] Setter 
	 * @author BELBRIK Oualid on : 13 oct. 2014  17:13:12
	 * @param attestationFinEtudeId the attestationFinEtudeId to set
	 */
	public void setAttestationFinEtudeId(Integer attestationFinEtudeId) {
		this.attestationFinEtudeId = attestationFinEtudeId;
	}


	/**
	 * [attestationFinEtudeEditionDto.agent] Getter 
	 * @author BELBRIK Oualid on : 20 oct. 2014  16:06:08
	 * @return the agent
	 */
	public String getAgent() {
		return agent;
	}


	/**
	 * [attestationFinEtudeEditionDto.agent] Setter 
	 * @author BELBRIK Oualid on : 20 oct. 2014  16:06:08
	 * @param agent the agent to set
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	

	

}
