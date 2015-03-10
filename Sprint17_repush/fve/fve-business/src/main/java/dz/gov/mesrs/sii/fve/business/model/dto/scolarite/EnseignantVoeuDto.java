/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto.java] 
 * @author rlaib on : 12 oct. 2014  15:06:05
 */
package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

/**
 * @author rlaib  on : 12 oct. 2014  15:06:05
 */
public class EnseignantVoeuDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 oct. 2014  16:09:39
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int priorite;
	private String description;
	private boolean editable;
	// Fiche voeux
	private int idFicheVoeux;
	private int order;
	
	public EnseignantVoeuDto() {
		
	}

	/**
	 * [EnseignantVoeuDto.id] Getter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [EnseignantVoeuDto.id] Setter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [EnseignantVoeuDto.idFicheVoeux] Getter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @return the idFicheVoeux
	 */
	public int getIdFicheVoeux() {
		return idFicheVoeux;
	}

	/**
	 * [EnseignantVoeuDto.idFicheVoeux] Setter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @param idFicheVoeux the idFicheVoeux to set
	 */
	public void setIdFicheVoeux(int idFicheVoeux) {
		this.idFicheVoeux = idFicheVoeux;
	}
	/**
	 * [EnseignantVoeuDto.priorite] Getter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @return the priorite
	 */
	public int getPriorite() {
		return priorite;
	}

	/**
	 * [EnseignantVoeuDto.priorite] Setter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @param priorite the priorite to set
	 */
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	/**
	 * [EnseignantVoeuDto.description] Getter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [EnseignantVoeuDto.description] Setter 
	 * @author rlaib on : 12 oct. 2014  15:12:43
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [EnseignantVoeuDto.editable] Getter 
	 * @author rlaib on : 15 oct. 2014  18:27:56
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * [EnseignantVoeuDto.editable] Setter 
	 * @author rlaib on : 15 oct. 2014  18:27:56
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * [EnseignantVoeuDto.order] Getter 
	 * @author rlaib on : 23 oct. 2014  18:04:36
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * [EnseignantVoeuDto.order] Setter 
	 * @author rlaib on : 23 oct. 2014  18:04:36
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	
}
