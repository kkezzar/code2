package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

/**
 * @author BELDI Jamel on : 12 mai 2014 14:12:02
 */

public class AnneeAcademiqueDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 12 mai 2014 14:09:19
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	private short premiereAnnee;
	private short deuxiemeAnnee;
	private String code;
	private Date dateDebut;
	private Date dateFin;
	

	public AnneeAcademiqueDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public short getPremiereAnnee() {
		return this.premiereAnnee;
	}

	public void setPremiereAnnee(short premiereAnnee) {
		this.premiereAnnee = premiereAnnee;
	}

	public short getDeuxiemeAnnee() {
		return this.deuxiemeAnnee;
	}

	public void setDeuxiemeAnnee(short deuxiemeAnnee) {
		this.deuxiemeAnnee = deuxiemeAnnee;
	}

	/**
	 * [AnneeAcademiqueDto.dateDebut] Getter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:39:16
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [AnneeAcademiqueDto.dateDebut] Setter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:39:16
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [AnneeAcademiqueDto.dateFin] Getter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:39:16
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [AnneeAcademiqueDto.dateFin] Setter 
	 * @author MAKERRI Sofiane on : 21 janv. 2015  09:39:16
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
	

}
