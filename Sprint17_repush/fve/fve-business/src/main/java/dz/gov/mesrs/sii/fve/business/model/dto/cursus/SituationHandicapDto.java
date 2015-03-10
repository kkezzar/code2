package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



import java.util.Date;


public class SituationHandicapDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 21 mai 2014  08:56:11
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idDossierEtudiant;
	private Date dateDebut;
	private Date dateFin;
	private String observation;
	private String refCodeTypeHandicap;
	private String refLlLatinTypeHandicap;
	private String refLlArabeTypeHandicap;

	public SituationHandicapDto() {
	}

	public SituationHandicapDto(int id) {
		this.id = id;
	}

	/**
	 * [SituationHandicapDto.id] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [SituationHandicapDto.id] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [SituationHandicapDto.idDossierEtudiant] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the idDossierEtudiant
	 */
	public Integer getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [SituationHandicapDto.idDossierEtudiant] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param idDossierEtudiant the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(Integer idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [SituationHandicapDto.dateDebut] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [SituationHandicapDto.dateDebut] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [SituationHandicapDto.dateFin] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [SituationHandicapDto.dateFin] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [SituationHandicapDto.observation] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [SituationHandicapDto.observation] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [SituationHandicapDto.refCodeTypeHandicap] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @return the refCodeTypeHandicap
	 */
	public String getRefCodeTypeHandicap() {
		return refCodeTypeHandicap;
	}

	/**
	 * [SituationHandicapDto.refCodeTypeHandicap] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:56:35
	 * @param refCodeTypeHandicap the refCodeTypeHandicap to set
	 */
	public void setRefCodeTypeHandicap(String refCodeTypeHandicap) {
		this.refCodeTypeHandicap = refCodeTypeHandicap;
	}

	/**
	 * [SituationHandicapDto.refLlLatinTypeHandicap] Getter 
	 * @author MAKERRI Sofiane on : 27 mai 2014  12:07:49
	 * @return the refLlLatinTypeHandicap
	 */
	public String getRefLlLatinTypeHandicap() {
		return refLlLatinTypeHandicap;
	}

	/**
	 * [SituationHandicapDto.refLlLatinTypeHandicap] Setter 
	 * @author MAKERRI Sofiane on : 27 mai 2014  12:07:49
	 * @param refLlLatinTypeHandicap the refLlLatinTypeHandicap to set
	 */
	public void setRefLlLatinTypeHandicap(String refLlLatinTypeHandicap) {
		this.refLlLatinTypeHandicap = refLlLatinTypeHandicap;
	}

	/**
	 * [SituationHandicapDto.refLlArabeTypeHandicap] Getter 
	 * @author MAKERRI Sofiane on : 27 mai 2014  12:08:19
	 * @return the refLlArabeTypeHandicap
	 */
	public String getRefLlArabeTypeHandicap() {
		return refLlArabeTypeHandicap;
	}

	/**
	 * [SituationHandicapDto.refLlArabeTypeHandicap] Setter 
	 * @author MAKERRI Sofiane on : 27 mai 2014  12:08:19
	 * @param refLlArabeTypeHandicap the refLlArabeTypeHandicap to set
	 */
	public void setRefLlArabeTypeHandicap(String refLlArabeTypeHandicap) {
		this.refLlArabeTypeHandicap = refLlArabeTypeHandicap;
	}

	

}
