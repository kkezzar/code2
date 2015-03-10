package dz.gov.mesrs.sii.fve.business.model.dto.scolarite;

import java.util.List;

public class EmploiModelDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 5 nov. 2014 09:14:47
	 */
	private static final long serialVersionUID = 1L;

	private String plageHoraire;
	private List<String> celluleDimanche;
	private List<String> celluleLundi;
	private List<String> celluleMardi;
	private List<String>  celluleMercredi;
	private List<String>  celluleJeudi;
	private List<String>  celluleVendredi;
	private List<String>  celluleSamedi;

	public EmploiModelDto() {
	}

	/**
	 * [EmploiModelDto.plageHoraire] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  09:16:55
	 * @return the plageHoraire
	 */
	public String getPlageHoraire() {
		return plageHoraire;
	}

	/**
	 * [EmploiModelDto.plageHoraire] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  09:16:55
	 * @param plageHoraire the plageHoraire to set
	 */
	public void setPlageHoraire(String plageHoraire) {
		this.plageHoraire = plageHoraire;
	}

	

	/**
	 * [EmploiModelDto.celluleDimanche] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  11:41:09
	 * @return the celluleDimanche
	 */
	public List<String> getCelluleDimanche() {
		return celluleDimanche;
	}

	/**
	 * [EmploiModelDto.celluleDimanche] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  11:41:09
	 * @param celluleDimanche the celluleDimanche to set
	 */
	public void setCelluleDimanche(List<String> celluleDimanche) {
		this.celluleDimanche = celluleDimanche;
	}

	/**
	 * [EmploiModelDto.celluleLundi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleLundi
	 */
	public List<String> getCelluleLundi() {
		return celluleLundi;
	}

	/**
	 * [EmploiModelDto.celluleLundi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleLundi the celluleLundi to set
	 */
	public void setCelluleLundi(List<String> celluleLundi) {
		this.celluleLundi = celluleLundi;
	}

	/**
	 * [EmploiModelDto.celluleMardi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleMardi
	 */
	public List<String> getCelluleMardi() {
		return celluleMardi;
	}

	/**
	 * [EmploiModelDto.celluleMardi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleMardi the celluleMardi to set
	 */
	public void setCelluleMardi(List<String> celluleMardi) {
		this.celluleMardi = celluleMardi;
	}

	/**
	 * [EmploiModelDto.celluleMercredi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleMercredi
	 */
	public List<String> getCelluleMercredi() {
		return celluleMercredi;
	}

	/**
	 * [EmploiModelDto.celluleMercredi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleMercredi the celluleMercredi to set
	 */
	public void setCelluleMercredi(List<String> celluleMercredi) {
		this.celluleMercredi = celluleMercredi;
	}

	/**
	 * [EmploiModelDto.celluleJeudi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleJeudi
	 */
	public List<String> getCelluleJeudi() {
		return celluleJeudi;
	}

	/**
	 * [EmploiModelDto.celluleJeudi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleJeudi the celluleJeudi to set
	 */
	public void setCelluleJeudi(List<String> celluleJeudi) {
		this.celluleJeudi = celluleJeudi;
	}

	/**
	 * [EmploiModelDto.celluleVendredi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleVendredi
	 */
	public List<String> getCelluleVendredi() {
		return celluleVendredi;
	}

	/**
	 * [EmploiModelDto.celluleVendredi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleVendredi the celluleVendredi to set
	 */
	public void setCelluleVendredi(List<String> celluleVendredi) {
		this.celluleVendredi = celluleVendredi;
	}

	/**
	 * [EmploiModelDto.celluleSamedi] Getter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @return the celluleSamedi
	 */
	public List<String> getCelluleSamedi() {
		return celluleSamedi;
	}

	/**
	 * [EmploiModelDto.celluleSamedi] Setter 
	 * @author BELDI Jamel on : 5 nov. 2014  15:34:51
	 * @param celluleSamedi the celluleSamedi to set
	 */
	public void setCelluleSamedi(List<String> celluleSamedi) {
		this.celluleSamedi = celluleSamedi;
	}

	

	
	
}
