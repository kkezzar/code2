package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;



public class DiplomeFinEtudeEditionDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	//private DiplomeFinEtude diplomeFinEtude;
	private Integer diplomeFinEtudeId;
	private Boolean estValide;
	private Date dateEdition;
	private String refTypeImpression;
	private String service;
	private String observation;
	private String agent;


	public DiplomeFinEtudeEditionDto() {
	}


	/**
	 * [DiplomeFinEtudeEditionDto.id] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.id] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	

	/**
	 * [DiplomeFinEtudeEditionDto.estValide] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the estValide
	 */
	public Boolean getEstValide() {
		return estValide;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.estValide] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param estValide the estValide to set
	 */
	public void setEstValide(Boolean estValide) {
		this.estValide = estValide;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.dateEdition] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the dateEdition
	 */
	public Date getDateEdition() {
		return dateEdition;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.dateEdition] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param dateEdition the dateEdition to set
	 */
	public void setDateEdition(Date dateEdition) {
		this.dateEdition = dateEdition;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.refTypeImpression] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the refTypeImpression
	 */
	public String getRefTypeImpression() {
		return refTypeImpression;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.refTypeImpression] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param refTypeImpression the refTypeImpression to set
	 */
	public void setRefTypeImpression(String refTypeImpression) {
		this.refTypeImpression = refTypeImpression;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.service] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the service
	 */
	public String getService() {
		return service;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.service] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.observation] Getter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.observation] Setter 
	 * @author BELBRIK Oualid on : 30 sept. 2014  19:40:04
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.diplomeFinEtudeId] Getter 
	 * @author BELBRIK Oualid on : 13 oct. 2014  17:13:12
	 * @return the diplomeFinEtudeId
	 */
	public Integer getDiplomeFinEtudeId() {
		return diplomeFinEtudeId;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.diplomeFinEtudeId] Setter 
	 * @author BELBRIK Oualid on : 13 oct. 2014  17:13:12
	 * @param diplomeFinEtudeId the diplomeFinEtudeId to set
	 */
	public void setDiplomeFinEtudeId(Integer diplomeFinEtudeId) {
		this.diplomeFinEtudeId = diplomeFinEtudeId;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.agent] Getter 
	 * @author BELBRIK Oualid on : 20 oct. 2014  16:06:08
	 * @return the agent
	 */
	public String getAgent() {
		return agent;
	}


	/**
	 * [DiplomeFinEtudeEditionDto.agent] Setter 
	 * @author BELBRIK Oualid on : 20 oct. 2014  16:06:08
	 * @param agent the agent to set
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	

	

}
