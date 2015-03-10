package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;


/**
 * 
 * @author BELDI Jamel
 *
 */
public class AnneeGrhDto  {

	private int annee;
	private String  libelle;
	private Date dateDebut;
	private Date dateFin;
	public AnneeGrhDto() {
		
	}
	
	


	public AnneeGrhDto(int annee, String libelle, Date dateDebut, Date dateFin) {
		super();
		this.annee = annee;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}


	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	

}
