package dz.gov.mesrs.sii.fve.business.model.dto.bac;


public class FicheVoeuxBachelierDto implements java.io.Serializable {
	

	/**
	 * serialVersionUID 
	 * @author  Rafik LAIB  on : 21 mai 2014  14:01:46
	 */
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int idDossierBachelier;
	private String refCodeFiliere;
	private int rang;
	
	public FicheVoeuxBachelierDto() {
	}

	/**
	 * [FicheVoeuxBachelierDto.id] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [FicheVoeuxBachelierDto.id] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [FicheVoeuxBachelierDto.idDossierBachelier] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @return the idDossierBachelier
	 */
	public int getIdDossierBachelier() {
		return idDossierBachelier;
	}

	/**
	 * [FicheVoeuxBachelierDto.idDossierBachelier] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @param idDossierBachelier the idDossierBachelier to set
	 */
	public void setIdDossierBachelier(int idDossierBachelier) {
		this.idDossierBachelier = idDossierBachelier;
	}

	/**
	 * [FicheVoeuxBachelierDto.refCodeFiliere] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @return the refCodeFiliere
	 */
	public String getRefCodeFiliere() {
		return refCodeFiliere;
	}

	/**
	 * [FicheVoeuxBachelierDto.refCodeFiliere] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @param refCodeFiliere the refCodeFiliere to set
	 */
	public void setRefCodeFiliere(String refCodeFiliere) {
		this.refCodeFiliere = refCodeFiliere;
	}

	/**
	 * [FicheVoeuxBachelierDto.rang] Getter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}

	/**
	 * [FicheVoeuxBachelierDto.rang] Setter 
	 * @author  Rafik LAIB on : 22 mai 2014  14:23:27
	 * @param rang the rang to set
	 */
	public void setRang(int rang) {
		this.rang = rang;
	}
	
	
}
