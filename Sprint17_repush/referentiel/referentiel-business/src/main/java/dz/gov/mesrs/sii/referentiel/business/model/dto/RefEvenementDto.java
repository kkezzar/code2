package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

/**
 * @author BELDI Jamel on : 13 f�vr. 2014 10:29:34
 */
public class RefEvenementDto implements java.io.Serializable {
	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:29:52
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String identifiant;
	private String objet;
	private String description;
	private Date dateDebut;
	private Date dateFin;
	private Date heure;
	private Integer nbreJours;
	private String observation;

	// private Nomenclature nomenclatureByFrequenceRepetition;
	private Integer frequenceRepetitionId;
	private String frequenceRepetitionLibelleLongFr;
	private String frequenceRepetitionLibelleLongAr;
	private String frequenceRepetitionLibelleCourtFr;
	private String frequenceRepetitionLibelleCourtAr;
	private String frequenceRepetitionCode;
	// private Nomenclature nomenclatureByType;
	private Integer typeId;
	private String typeLibelleLongFr;
	private String typeLibelleLongAr;
	private String typeLibelleCourtFr;
	private String typeLibelleCourtAr;
	private String typeCode;
	/**
	 * Current Situation
	 * 
	 */
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	/********** etablissement****************/
	private Integer idEtablissement;
	
	
	public RefEvenementDto() {
		
	}
	
	

	/**
	 * [RefEvenementDto.id] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:55:48
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * [RefEvenementDto.id] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  11:55:48
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * [RefEvenementDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the id
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [RefEvenementDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param id
	 *            the id to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [RefEvenementDto.objet] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * [RefEvenementDto.objet] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param objet
	 *            the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * [RefEvenementDto.description] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [RefEvenementDto.description] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [RefEvenementDto.dateDebut] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [RefEvenementDto.dateDebut] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [RefEvenementDto.dateFin] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [RefEvenementDto.dateFin] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [RefEvenementDto.heure] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the heure
	 */
	public Date getHeure() {
		return heure;
	}

	/**
	 * [RefEvenementDto.heure] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param heure
	 *            the heure to set
	 */
	public void setHeure(Date heure) {
		this.heure = heure;
	}

	/**
	 * [RefEvenementDto.nbreJours] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the nbreJours
	 */
	public Integer getNbreJours() {
		return nbreJours;
	}

	/**
	 * [RefEvenementDto.nbreJours] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param nbreJours
	 *            the nbreJours to set
	 */
	public void setNbreJours(Integer nbreJours) {
		this.nbreJours = nbreJours;
	}

	/**
	 * [RefEvenementDto.observation] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [RefEvenementDto.observation] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:30:11
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionId] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionId
	 */
	public Integer getFrequenceRepetitionId() {
		return frequenceRepetitionId;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionId] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionId
	 *            the frequenceRepetitionId to set
	 */
	public void setFrequenceRepetitionId(Integer frequenceRepetitionId) {
		this.frequenceRepetitionId = frequenceRepetitionId;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionLibelleLongFr
	 */
	public String getFrequenceRepetitionLibelleLongFr() {
		return frequenceRepetitionLibelleLongFr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionLibelleLongFr
	 *            the frequenceRepetitionLibelleLongFr to set
	 */
	public void setFrequenceRepetitionLibelleLongFr(
			String frequenceRepetitionLibelleLongFr) {
		this.frequenceRepetitionLibelleLongFr = frequenceRepetitionLibelleLongFr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionLibelleLongAr
	 */
	public String getFrequenceRepetitionLibelleLongAr() {
		return frequenceRepetitionLibelleLongAr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionLibelleLongAr
	 *            the frequenceRepetitionLibelleLongAr to set
	 */
	public void setFrequenceRepetitionLibelleLongAr(
			String frequenceRepetitionLibelleLongAr) {
		this.frequenceRepetitionLibelleLongAr = frequenceRepetitionLibelleLongAr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionLibelleCourtFr
	 */
	public String getFrequenceRepetitionLibelleCourtFr() {
		return frequenceRepetitionLibelleCourtFr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionLibelleCourtFr
	 *            the frequenceRepetitionLibelleCourtFr to set
	 */
	public void setFrequenceRepetitionLibelleCourtFr(
			String frequenceRepetitionLibelleCourtFr) {
		this.frequenceRepetitionLibelleCourtFr = frequenceRepetitionLibelleCourtFr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionLibelleCourtAr
	 */
	public String getFrequenceRepetitionLibelleCourtAr() {
		return frequenceRepetitionLibelleCourtAr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionLibelleCourtAr
	 *            the frequenceRepetitionLibelleCourtAr to set
	 */
	public void setFrequenceRepetitionLibelleCourtAr(
			String frequenceRepetitionLibelleCourtAr) {
		this.frequenceRepetitionLibelleCourtAr = frequenceRepetitionLibelleCourtAr;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionCode] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the frequenceRepetitionCode
	 */
	public String getFrequenceRepetitionCode() {
		return frequenceRepetitionCode;
	}

	/**
	 * [RefEvenementDto.frequenceRepetitionCode] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param frequenceRepetitionCode
	 *            the frequenceRepetitionCode to set
	 */
	public void setFrequenceRepetitionCode(String frequenceRepetitionCode) {
		this.frequenceRepetitionCode = frequenceRepetitionCode;
	}

	/**
	 * [RefEvenementDto.typeId] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * [RefEvenementDto.typeId] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * [RefEvenementDto.typeLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeLibelleLongFr
	 */
	public String getTypeLibelleLongFr() {
		return typeLibelleLongFr;
	}

	/**
	 * [RefEvenementDto.typeLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeLibelleLongFr
	 *            the typeLibelleLongFr to set
	 */
	public void setTypeLibelleLongFr(String typeLibelleLongFr) {
		this.typeLibelleLongFr = typeLibelleLongFr;
	}

	/**
	 * [RefEvenementDto.typeLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeLibelleLongAr
	 */
	public String getTypeLibelleLongAr() {
		return typeLibelleLongAr;
	}

	/**
	 * [RefEvenementDto.typeLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeLibelleLongAr
	 *            the typeLibelleLongAr to set
	 */
	public void setTypeLibelleLongAr(String typeLibelleLongAr) {
		this.typeLibelleLongAr = typeLibelleLongAr;
	}

	/**
	 * [RefEvenementDto.typeLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeLibelleCourtFr
	 */
	public String getTypeLibelleCourtFr() {
		return typeLibelleCourtFr;
	}

	/**
	 * [RefEvenementDto.typeLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeLibelleCourtFr
	 *            the typeLibelleCourtFr to set
	 */
	public void setTypeLibelleCourtFr(String typeLibelleCourtFr) {
		this.typeLibelleCourtFr = typeLibelleCourtFr;
	}

	/**
	 * [RefEvenementDto.typeLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeLibelleCourtAr
	 */
	public String getTypeLibelleCourtAr() {
		return typeLibelleCourtAr;
	}

	/**
	 * [RefEvenementDto.typeLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeLibelleCourtAr
	 *            the typeLibelleCourtAr to set
	 */
	public void setTypeLibelleCourtAr(String typeLibelleCourtAr) {
		this.typeLibelleCourtAr = typeLibelleCourtAr;
	}

	/**
	 * [RefEvenementDto.typeCode] Getter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * [RefEvenementDto.typeCode] Setter
	 * 
	 * @author BELDI Jamel on : 13 f�vr. 2014 10:35:14
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * [RefEvenementDto.dateSituation] Getter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}

	/**
	 * [RefEvenementDto.dateSituation] Setter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	/**
	 * [RefEvenementDto.llSituationAr] Getter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}

	/**
	 * [RefEvenementDto.llSituationAr] Setter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}

	/**
	 * [RefEvenementDto.llSituationFr] Getter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}

	/**
	 * [RefEvenementDto.llSituationFr] Setter 
	 * @author BELDI Jamel on : 13 f�vr. 2014  10:57:09
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}



	/**
	 * [RefEvenementDto.idEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:20:34
	 * @return the idEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}



	/**
	 * [RefEvenementDto.idEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:20:34
	 * @param idEtablissement the idEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	
	

}
