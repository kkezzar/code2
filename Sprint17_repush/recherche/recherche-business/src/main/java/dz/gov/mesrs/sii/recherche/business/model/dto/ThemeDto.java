/**
 * [dz.gov.mesrs.sii.recherche.business.model.dto.StructureDto.java] 
 * @author rlaib on : 16 déc. 2014  13:24:10
 */
package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rlaib  on : 16 déc. 2014  13:24:10
 */
public class ThemeDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:17
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	
	// Structure de recherche
	private Long  structureId;
	private String  	structureLibelleLatin;
	
	// Groupe de recherche
	private Long  groupeId;
	private String  	groupeLibelleLatin;
	
	// Type Themes de recherche
	private Integer  ncThemeId;
	private String  	ncThemeIdentifiant;
	private String  	ncThemeLibelleLatin;
		
	public ThemeDto() {
	}

	/**
	 * [ThemeDto.id] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * [ThemeDto.id] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * [ThemeDto.dateDebut] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [ThemeDto.dateDebut] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [ThemeDto.dateFin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [ThemeDto.dateFin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [ThemeDto.description] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [ThemeDto.description] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [ThemeDto.structureId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the structureId
	 */
	public Long getStructureId() {
		return structureId;
	}

	/**
	 * [ThemeDto.structureId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param structureId the structureId to set
	 */
	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}

	/**
	 * [ThemeDto.structureLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the structureLibelleLatin
	 */
	public String getStructureLibelleLatin() {
		return structureLibelleLatin;
	}

	/**
	 * [ThemeDto.structureLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param structureLibelleLatin the structureLibelleLatin to set
	 */
	public void setStructureLibelleLatin(String structureLibelleLatin) {
		this.structureLibelleLatin = structureLibelleLatin;
	}

	/**
	 * [ThemeDto.groupeId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the groupeId
	 */
	public Long getGroupeId() {
		return groupeId;
	}

	/**
	 * [ThemeDto.groupeId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param groupeId the groupeId to set
	 */
	public void setGroupeId(Long groupeId) {
		this.groupeId = groupeId;
	}

	/**
	 * [ThemeDto.groupeLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the groupeLibelleLatin
	 */
	public String getGroupeLibelleLatin() {
		return groupeLibelleLatin;
	}

	/**
	 * [ThemeDto.groupeLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param groupeLibelleLatin the groupeLibelleLatin to set
	 */
	public void setGroupeLibelleLatin(String groupeLibelleLatin) {
		this.groupeLibelleLatin = groupeLibelleLatin;
	}

	/**
	 * [ThemeDto.ncThemeId] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the ncThemeId
	 */
	public Integer getNcThemeId() {
		return ncThemeId;
	}

	/**
	 * [ThemeDto.ncThemeId] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param ncThemeId the ncThemeId to set
	 */
	public void setNcThemeId(Integer ncThemeId) {
		this.ncThemeId = ncThemeId;
	}

	/**
	 * [ThemeDto.ncThemeIdentifiant] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the ncThemeIdentifiant
	 */
	public String getNcThemeIdentifiant() {
		return ncThemeIdentifiant;
	}

	/**
	 * [ThemeDto.ncThemeIdentifiant] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param ncThemeIdentifiant the ncThemeIdentifiant to set
	 */
	public void setNcThemeIdentifiant(String ncThemeIdentifiant) {
		this.ncThemeIdentifiant = ncThemeIdentifiant;
	}

	/**
	 * [ThemeDto.ncThemeLibelleLatin] Getter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @return the ncThemeLibelleLatin
	 */
	public String getNcThemeLibelleLatin() {
		return ncThemeLibelleLatin;
	}

	/**
	 * [ThemeDto.ncThemeLibelleLatin] Setter 
	 * @author rlaib on : 16 déc. 2014  13:54:37
	 * @param ncThemeLibelleLatin the ncThemeLibelleLatin to set
	 */
	public void setNcThemeLibelleLatin(String ncThemeLibelleLatin) {
		this.ncThemeLibelleLatin = ncThemeLibelleLatin;
	}
}
