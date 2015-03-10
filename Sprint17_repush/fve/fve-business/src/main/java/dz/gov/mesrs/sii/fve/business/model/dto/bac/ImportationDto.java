package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.Date;

public class ImportationDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	private static final long serialVersionUID = 5595401399463125298L;
	private int id;
	private Date dateDebutImportation;
	private Date dateFinImportation;
	private String anneeBac;
	private String situation;
	private int nbDepart;
	private int nbImportes;
	private int nbIgnores;
	private String codeTypeImportation;
	private String libelleTypeImportation;
	private int idTypeImportation;
	
	public ImportationDto() {
	}

	/**
	 * [ImportationDto.id] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [ImportationDto.id] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * [ImportationDto.dateDebutImportation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  11:09:16
	 * @return the dateDebutImportation
	 */
	public Date getDateDebutImportation() {
		return dateDebutImportation;
	}

	/**
	 * [ImportationDto.dateDebutImportation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  11:09:16
	 * @param dateDebutImportation the dateDebutImportation to set
	 */
	public void setDateDebutImportation(Date dateDebutImportation) {
		this.dateDebutImportation = dateDebutImportation;
	}

	/**
	 * [ImportationDto.dateFinImportation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  11:09:16
	 * @return the dateFinImportation
	 */
	public Date getDateFinImportation() {
		return dateFinImportation;
	}

	/**
	 * [ImportationDto.dateFinImportation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  11:09:16
	 * @param dateFinImportation the dateFinImportation to set
	 */
	public void setDateFinImportation(Date dateFinImportation) {
		this.dateFinImportation = dateFinImportation;
	}

	/**
	 * [ImportationDto.anneeBac] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @return the anneeBac
	 */
	public String getAnneeBac() {
		return anneeBac;
	}

	/**
	 * [ImportationDto.anneeBac] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @param anneeBac the anneeBac to set
	 */
	public void setAnneeBac(String anneeBac) {
		this.anneeBac = anneeBac;
	}

	/**
	 * [ImportationDto.situation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * [ImportationDto.situation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}
	

	/**
	 * [ImportationDto.nbDepart] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @return the nbDepart
	 */
	public int getNbDepart() {
		return nbDepart;
	}

	/**
	 * [ImportationDto.nbDepart] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @param nbDepart the nbDepart to set
	 */
	public void setNbDepart(int nbDepart) {
		this.nbDepart = nbDepart;
	}

	/**
	 * [ImportationDto.nbImportes] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @return the nbImportes
	 */
	public int getNbImportes() {
		return nbImportes;
	}

	/**
	 * [ImportationDto.nbImportes] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @param nbImportes the nbImportes to set
	 */
	public void setNbImportes(int nbImportes) {
		this.nbImportes = nbImportes;
	}

	/**
	 * [ImportationDto.nbIgnores] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @return the nbIgnores
	 */
	public int getNbIgnores() {
		return nbIgnores;
	}

	/**
	 * [ImportationDto.nbIgnores] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  14:07:39
	 * @param nbIgnores the nbIgnores to set
	 */
	public void setNbIgnores(int nbIgnores) {
		this.nbIgnores = nbIgnores;
	}

	/**
	 * [ImportationDto.codeTypeImportation] Getter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:14:52
	 * @return the codeTypeImportation
	 */
	public String getCodeTypeImportation() {
		return codeTypeImportation;
	}

	/**
	 * [ImportationDto.codeTypeImportation] Setter 
	 * @author  Rafik LAIB on : 25 mai 2014  15:14:52
	 * @param codeTypeImportation the codeTypeImportation to set
	 */
	public void setCodeTypeImportation(String codeTypeImportation) {
		this.codeTypeImportation = codeTypeImportation;
	}

	/**
	 * [ImportationDto.libelleTypeImportation] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @return the libelleTypeImportation
	 */
	public String getLibelleTypeImportation() {
		return libelleTypeImportation;
	}

	/**
	 * [ImportationDto.libelleTypeImportation] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:12:13
	 * @param libelleTypeImportation the libelleTypeImportation to set
	 */
	public void setLibelleTypeImportation(String libelleTypeImportation) {
		this.libelleTypeImportation = libelleTypeImportation;
	}

	
	/**
	 * [ImportationDto.idTypeImportation] Getter 
	 * @author  Rafik LAIB on : 1 aout 2014  12:26:30
	 * @return the idTypeImportation
	 */
	public int getIdTypeImportation() {
		return idTypeImportation;
	}

	/**
	 * [ImportationDto.idTypeImportation] Setter 
	 * @author  Rafik LAIB on : 1 aout 2014  12:26:30
	 * @param idTypeImportation the idTypeImportation to set
	 */
	public void setIdTypeImportation(int idTypeImportation) {
		this.idTypeImportation = idTypeImportation;
	}
	
	
	
}
