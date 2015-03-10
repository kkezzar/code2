/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefDomaineDto.java] 
 * @author BELBRIK Oualid on : 09 fevr. 2014 11:42:34
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;


/**
 * @author BELBRIK Oualid  on : 24 fevr. 2014  11:42:34
 */
public class RefHoraireAccessDto {
	private int id;
	private String identifiant;
	private String nom;
	private Date heureDebut;
	private Date heureFin;

	/**
	 * [RefDomaineDto.RefDomaineDto()] Constructor
	 * @author BELBRIK Oualid  on : 24 fevr. 2014 11:42:34	
	 */
	public RefHoraireAccessDto() {
		
	}


	/**
	 * [RefHoraireAccessDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefHoraireAccessDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefHoraireAccessDto.nom] Getter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * [RefHoraireAccessDto.nom] Setter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * [RefHoraireAccessDto.heureDebut] Getter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}

	/**
	 * [RefHoraireAccessDto.heureDebut] Setter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * [RefHoraireAccessDto.heureFin] Getter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}

	/**
	 * [RefHoraireAccessDto.heureFin] Setter 
	 * @author BELBRIK Oualid on : 24 fevr. 2014  12:21:31
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


}
