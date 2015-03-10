/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique.GroupeInfo.java] 
 * @author MAKERRI Sofiane on : 9 sept. 2014  09:45:30
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.grouepedagogique;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.GroupePedagogiqueDto;

/**
 * @author MAKERRI Sofiane  on : 9 sept. 2014  09:45:30
 */
public class GroupeInfo implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 9 sept. 2014  09:45:46
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private GroupePedagogiqueDto groupePedagogiqueDto;
	private List<Etudiant> etudiants;
	

	/**
	 * [GroupeInfo.GroupeInfo()] Constructor
	 * @author MAKERRI Sofiane  on : 9 sept. 2014  09:45:30	
	 */
	public GroupeInfo() {
		super();
	}
	
	public GroupeInfo(GroupePedagogiqueDto groupePedagogiqueDto, List<Etudiant> etudiants) {
		super();
		this.groupePedagogiqueDto = groupePedagogiqueDto;
		this.etudiants = etudiants;
	}


	/**
	 * [GroupeInfo.groupePedagogiqueDto] Getter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  09:50:04
	 * @return the groupePedagogiqueDto
	 */
	public GroupePedagogiqueDto getGroupePedagogiqueDto() {
		return groupePedagogiqueDto;
	}


	/**
	 * [GroupeInfo.groupePedagogiqueDto] Setter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  09:50:04
	 * @param groupePedagogiqueDto the groupePedagogiqueDto to set
	 */
	public void setGroupePedagogiqueDto(GroupePedagogiqueDto groupePedagogiqueDto) {
		this.groupePedagogiqueDto = groupePedagogiqueDto;
	}


	/**
	 * [GroupeInfo.etudiants] Getter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  09:50:04
	 * @return the etudiants
	 */
	public List<Etudiant> getEtudiants() {
		return etudiants;
	}


	/**
	 * [GroupeInfo.etudiants] Setter 
	 * @author MAKERRI Sofiane on : 9 sept. 2014  09:50:04
	 * @param etudiants the etudiants to set
	 */
	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * [GroupeInfo.id] Getter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  11:08:39
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [GroupeInfo.id] Setter 
	 * @author MAKERRI Sofiane on : 11 oct. 2014  11:08:39
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

}
