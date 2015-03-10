package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.util.Date;

public class GenerationDiplomeDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Oualid BELBRIK  on : 21 octobre 2014  14:01:46
	 */
	private static final long serialVersionUID = 5595401399463125298L;
	private int id;
	private String anneeAcademique;
	private String situation;
	private String refCodeEtablissement;
	private int nbBilans;
	private int nbDiplomes;
	private Date dateDebutGeneration;
	private Date dateFinGeneration;
	public GenerationDiplomeDto() {
	}

	/**
	 * [GenerationDto.id] Getter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [GenerationDto.id] Setter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [GenerationDto.anneeAcademique] Getter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @return the anneeAcademique
	 */
	public String getAnneeAcademique() {
		return anneeAcademique;
	}

	/**
	 * [GenerationDto.anneeAcademique] Setter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(String anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	/**
	 * [GenerationDto.situation] Getter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [GenerationDto.situation] Setter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	/**
	 * [GenerationDto.refCodeEtablissement] Getter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @return the refCodeEtablissement
	 */
	public String getRefCodeEtablissement() {
		return refCodeEtablissement;
	}

	/**
	 * [GenerationDto.refCodeEtablissement] Setter 
	 * @author obelbrik on : 30 octobre 2014  10:37:44
	 * @param refCodeEtablissement the refCodeEtablissement to set
	 */
	public void setRefCodeEtablissement(String refCodeEtablissement) {
		this.refCodeEtablissement = refCodeEtablissement;
	}

	/**
	 * [GenerationDto.nbBilans] Getter 
	 * @author obelbrik on : 30 octobre 2014  12:13:34
	 * @return the nbBilans
	 */
	public int getNbBilans() {
		return nbBilans;
	}

	/**
	 * [GenerationDto.nbBilans] Setter 
	 * @author obelbrik on : 30 octobre 2014  12:13:34
	 * @param nbBilans the nbBilans to set
	 */
	public void setNbBilans(int nbBilans) {
		this.nbBilans = nbBilans;
	}

	/**
	 * [GenerationDto.nbDiplomes] Getter 
	 * @author obelbrik on : 30 octobre 2014  12:13:34
	 * @return the nbDiplomes
	 */
	public int getNbDiplomes() {
		return nbDiplomes;
	}

	/**
	 * [GenerationDto.nbDiplomes] Setter 
	 * @author obelbrik on : 30 octobre 2014  12:13:34
	 * @param nbDiplomes the nbDiplomes to set
	 */
	public void setNbDiplomes(int nbDiplomes) {
		this.nbDiplomes = nbDiplomes;
	}

	
	/**
	 * [GenerationDto.dateDebutGeneration] Getter 
	 * @author obelbrik on : 23 octobre 2014  10:31:28
	 * @return the dateDebutGeneration
	 */
	public Date getDateDebutGeneration() {
		return dateDebutGeneration;
	}

	/**
	 * [GenerationDto.dateDebutGeneration] Setter 
	 * @author obelbrik on : 23 octobre 2014  10:31:28
	 * @param dateDebutGeneration the dateDebutGeneration to set
	 */
	public void setDateDebutGeneration(Date dateDebutGeneration) {
		this.dateDebutGeneration = dateDebutGeneration;
	}

	/**
	 * [GenerationDto.dateFinGeneration] Getter 
	 * @author obelbrik on : 23 octobre  2014  10:31:28
	 * @return the dateFinGeneration
	 */
	public Date getDateFinGeneration() {
		return dateFinGeneration;
	}

	/**
	 * [GenerationDto.dateFinGeneration] Setter 
	 * @author obelbrik on : 23 octobre 2014  10:31:28
	 * @param dateFinGeneration the dateFinGeneration to set
	 */
	public void setDateFinGeneration(Date dateFinGeneration) {
		this.dateFinGeneration = dateFinGeneration;
	}

	
	
	
}
