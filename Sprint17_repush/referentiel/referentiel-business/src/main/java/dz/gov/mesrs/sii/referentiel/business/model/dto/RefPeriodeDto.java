/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefPeriodeDto.java] 
 * @author MAKERRI Sofiane on : 25 mars 2014  16:41:36
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane  on : 25 mars 2014  16:41:36
 */
public class RefPeriodeDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 25 mars 2014  16:41:54
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private Boolean periodique;

	/**
	 * [RefPeriodeDto.RefPeriodeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 25 mars 2014  16:41:36	
	 */
	public RefPeriodeDto() {
		super();
	}

	/**
	 * [RefPeriodeDto.id] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefPeriodeDto.id] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefPeriodeDto.identifiant] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefPeriodeDto.identifiant] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefPeriodeDto.nom] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * [RefPeriodeDto.nom] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * [RefPeriodeDto.dateDebut] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [RefPeriodeDto.dateDebut] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [RefPeriodeDto.dateFin] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [RefPeriodeDto.dateFin] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [RefPeriodeDto.periodique] Getter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @return the periodique
	 */
	public Boolean getPeriodique() {
		return periodique;
	}

	/**
	 * [RefPeriodeDto.periodique] Setter 
	 * @author MAKERRI Sofiane on : 25 mars 2014  16:42:40
	 * @param periodique the periodique to set
	 */
	public void setPeriodique(Boolean periodique) {
		this.periodique = periodique;
	}
	
	

}
