package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;



public class OffreFormationStructureDto implements java.io.Serializable {

	private static final long serialVersionUID = -5467261719100730696L;
	private int id;
	
	// Offre de formation
	private int idOffreFormation;
	private String codeOffreFormation;
	private String libelleOffreFormation;
	
	// Etablissement
	private int idEtablissement;
	private String codeEtablissement;
	private String libelleEtablissement;
	
	// Structure 
	private int idStructure;
	private String codeStructure;
	private int idTypeStructure;
	private String libelleStructure;
	
	public OffreFormationStructureDto() {
	
	}

	/**
	 * [OffreFormationStructureDto.id] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [OffreFormationStructureDto.id] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [OffreFormationStructureDto.idOffreFormation] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the idOffreFormation
	 */
	public int getIdOffreFormation() {
		return idOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.idOffreFormation] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param idOffreFormation the idOffreFormation to set
	 */
	public void setIdOffreFormation(int idOffreFormation) {
		this.idOffreFormation = idOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.codeOffreFormation] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the codeOffreFormation
	 */
	public String getCodeOffreFormation() {
		return codeOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.codeOffreFormation] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param codeOffreFormation the codeOffreFormation to set
	 */
	public void setCodeOffreFormation(String codeOffreFormation) {
		this.codeOffreFormation = codeOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.libelleOffreFormation] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the libelleOffreFormation
	 */
	public String getLibelleOffreFormation() {
		return libelleOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.libelleOffreFormation] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param libelleOffreFormation the libelleOffreFormation to set
	 */
	public void setLibelleOffreFormation(String libelleOffreFormation) {
		this.libelleOffreFormation = libelleOffreFormation;
	}

	/**
	 * [OffreFormationStructureDto.idEtablissement] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the idEtablissement
	 */
	public int getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.idEtablissement] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(int idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.codeEtablissement] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the codeEtablissement
	 */
	public String getCodeEtablissement() {
		return codeEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.codeEtablissement] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param codeEtablissement the codeEtablissement to set
	 */
	public void setCodeEtablissement(String codeEtablissement) {
		this.codeEtablissement = codeEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.libelleEtablissement] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the libelleEtablissement
	 */
	public String getLibelleEtablissement() {
		return libelleEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.libelleEtablissement] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param libelleEtablissement the libelleEtablissement to set
	 */
	public void setLibelleEtablissement(String libelleEtablissement) {
		this.libelleEtablissement = libelleEtablissement;
	}

	/**
	 * [OffreFormationStructureDto.idStructure] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the idStructure
	 */
	public int getIdStructure() {
		return idStructure;
	}

	/**
	 * [OffreFormationStructureDto.idStructure] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(int idStructure) {
		this.idStructure = idStructure;
	}

	/**
	 * [OffreFormationStructureDto.codeStructure] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the codeStructure
	 */
	public String getCodeStructure() {
		return codeStructure;
	}

	/**
	 * [OffreFormationStructureDto.codeStructure] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param codeStructure the codeStructure to set
	 */
	public void setCodeStructure(String codeStructure) {
		this.codeStructure = codeStructure;
	}

	/**
	 * [OffreFormationStructureDto.idTypeStructure] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the idTypeStructure
	 */
	public int getIdTypeStructure() {
		return idTypeStructure;
	}

	/**
	 * [OffreFormationStructureDto.idTypeStructure] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param idTypeStructure the idTypeStructure to set
	 */
	public void setIdTypeStructure(int idTypeStructure) {
		this.idTypeStructure = idTypeStructure;
	}

	/**
	 * [OffreFormationStructureDto.libelleStructure] Getter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @return the libelleStructure
	 */
	public String getLibelleStructure() {
		return libelleStructure;
	}

	/**
	 * [OffreFormationStructureDto.libelleStructure] Setter 
	 * @author rlaib on : 8 oct. 2014  16:12:23
	 * @param libelleStructure the libelleStructure to set
	 */
	public void setLibelleStructure(String libelleStructure) {
		this.libelleStructure = libelleStructure;
	}
	
	
}
