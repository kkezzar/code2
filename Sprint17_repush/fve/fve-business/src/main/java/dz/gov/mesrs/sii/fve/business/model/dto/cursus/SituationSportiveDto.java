package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



import java.util.Date;


public class SituationSportiveDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 21 mai 2014  08:57:01
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idDossierEtudiant;
	private Date dateDebut;
	private Date dateFin;
	private String observation;
	private String refCodeDisciplineSportive;
	private String refLlLatinDisciplineSportive;
	private String refLlArabeDisciplineSportive;

	public SituationSportiveDto() {
	}

	/**
	 * [SituationSportiveDto.id] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [SituationSportiveDto.id] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [SituationSportiveDto.idDossierEtudiant] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the idDossierEtudiant
	 */
	public Integer getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [SituationSportiveDto.idDossierEtudiant] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param idDossierEtudiant the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(Integer idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [SituationSportiveDto.dateDebut] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [SituationSportiveDto.dateDebut] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [SituationSportiveDto.dateFin] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [SituationSportiveDto.dateFin] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [SituationSportiveDto.observation] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [SituationSportiveDto.observation] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [SituationSportiveDto.refCodeDisciplineSportive] Getter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @return the refCodeDisciplineSportive
	 */
	public String getRefCodeDisciplineSportive() {
		return refCodeDisciplineSportive;
	}

	/**
	 * [SituationSportiveDto.refCodeDisciplineSportive] Setter 
	 * @author BELDI Jamel on : 21 mai 2014  08:57:30
	 * @param refCodeDisciplineSportive the refCodeDisciplineSportive to set
	 */
	public void setRefCodeDisciplineSportive(String refCodeDisciplineSportive) {
		this.refCodeDisciplineSportive = refCodeDisciplineSportive;
	}

	/**
	 * [SituationSportiveDto.refLlLatinDisciplineSportive] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:55:00
	 * @return the refLlLatinDisciplineSportive
	 */
	public String getRefLlLatinDisciplineSportive() {
		return refLlLatinDisciplineSportive;
	}

	/**
	 * [SituationSportiveDto.refLlLatinDisciplineSportive] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:55:00
	 * @param refLlLatinDisciplineSportive the refLlLatinDisciplineSportive to set
	 */
	public void setRefLlLatinDisciplineSportive(String refLlLatinDisciplineSportive) {
		this.refLlLatinDisciplineSportive = refLlLatinDisciplineSportive;
	}

	/**
	 * [SituationSportiveDto.refLlArabeDisciplineSportive] Getter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:55:00
	 * @return the refLlArabeDisciplineSportive
	 */
	public String getRefLlArabeDisciplineSportive() {
		return refLlArabeDisciplineSportive;
	}

	/**
	 * [SituationSportiveDto.refLlArabeDisciplineSportive] Setter 
	 * @author MAKERRI Sofiane on : 29 mai 2014  15:55:00
	 * @param refLlArabeDisciplineSportive the refLlArabeDisciplineSportive to set
	 */
	public void setRefLlArabeDisciplineSportive(String refLlArabeDisciplineSportive) {
		this.refLlArabeDisciplineSportive = refLlArabeDisciplineSportive;
	}


	

}
