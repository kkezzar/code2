/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationDto.java] 
 * @author BELBRIK Oualid on : 19 02 2014  12:02:19
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;


/**
 * @author BELBRIK Oualid  on : 17 03 2014  14:02:19
 */
public class RefReservationDto {

	private int id;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private Integer typeRsrv;
	private String objet;
	private String observation;
	
	/***** Responsable reservation*****/
	private String idResponsable;
	private String nomLatinResponsable;
	private String prenomLatinResponsable;
	private String nomarabeResponsable;
	private String prenomArabeResponsable;

	/***** Structure *****/
	private Integer idStructure;
	private String llStructureLatin;
	private String llStructureArabe;
	/***** Individu *****/
	private Integer idIndividu;
	private String nomLatin;
	private String prenomLatin;
	private String nomarabe;
	private String prenomArabe;
	/***** Groupe *****/
	private Integer idGroupe;
	private String llGroupe;
	private String llGroupeArabe;

	/***** Lieu *****/
	private Integer idLieu;
	private String designationLieu;
	private String designationArabeLieu;
	
	
	/***** Equipement *****/
	private Integer idEquipement;
	private String designationEquipement;
	
	/*** Current Situation **/
	private Date dateSituation;
	private String llSituationAr;
	private String llSituationFr;
	
	
	
	/**
	 * [RefReservationDto.id] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * [RefReservationDto.id] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * [RefReservationDto.dateDebut] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * [RefReservationDto.dateDebut] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * [RefReservationDto.dateFin] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * [RefReservationDto.dateFin] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * [RefReservationDto.heureDebut] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}
	/**
	 * [RefReservationDto.heureDebut] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * [RefReservationDto.heureFin] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}
	/**
	 * [RefReservationDto.heureFin] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	/**
	 * [RefReservationDto.typeRsrv] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the typeRsrv
	 */
	public Integer getTypeRsrv() {
		return typeRsrv;
	}
	/**
	 * [RefReservationDto.typeRsrv] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param typeRsrv the typeRsrv to set
	 */
	public void setTypeRsrv(Integer typeRsrv) {
		this.typeRsrv = typeRsrv;
	}
	/**
	 * [RefReservationDto.objet] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}
	/**
	 * [RefReservationDto.objet] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	/**
	 * [RefReservationDto.idEntite] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idEntite
	 */
	
	/**
	 * [RefReservationDto.idTypeReservation] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idTypeReservation
	 */
	
	
	/**
	 * [RefReservationDto.idStructure] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefReservationDto.idStructure] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefReservationDto.llStructureLatin] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}
	/**
	 * [RefReservationDto.llStructureLatin] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}
	/**
	 * [RefReservationDto.llStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefReservationDto.llStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefReservationDto.idIndividu] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}
	/**
	 * [RefReservationDto.idIndividu] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	/**
	 * [RefReservationDto.nomLatin] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the nomLatin
	 */
	public String getNomLatin() {
		return nomLatin;
	}
	/**
	 * [RefReservationDto.nomLatin] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param nomLatin the nomLatin to set
	 */
	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}
	/**
	 * [RefReservationDto.prenomLatin] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the prenomLatin
	 */
	public String getPrenomLatin() {
		return prenomLatin;
	}
	/**
	 * [RefReservationDto.prenomLatin] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param prenomLatin the prenomLatin to set
	 */
	public void setPrenomLatin(String prenomLatin) {
		this.prenomLatin = prenomLatin;
	}
	/**
	 * [RefReservationDto.nomarabe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the nomarabe
	 */
	public String getNomarabe() {
		return nomarabe;
	}
	/**
	 * [RefReservationDto.nomarabe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param nomarabe the nomarabe to set
	 */
	public void setNomarabe(String nomarabe) {
		this.nomarabe = nomarabe;
	}
	/**
	 * [RefReservationDto.prenomArabe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the prenomArabe
	 */
	public String getPrenomArabe() {
		return prenomArabe;
	}
	/**
	 * [RefReservationDto.prenomArabe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param prenomArabe the prenomArabe to set
	 */
	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}
	/**
	 * [RefReservationDto.idGroupe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefReservationDto.idGroupe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefReservationDto.llGroupe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefReservationDto.llGroupe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefReservationDto.llGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefReservationDto.llGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	/**
	 * [RefReservationDto.idLieu] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the idLieu
	 */
	public Integer getIdLieu() {
		return idLieu;
	}
	/**
	 * [RefReservationDto.idLieu] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param idLieu the idLieu to set
	 */
	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}
	/**
	 * [RefReservationDto.designationLieu] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the designationLieu
	 */
	public String getDesignationLieu() {
		return designationLieu;
	}
	/**
	 * [RefReservationDto.designationLieu] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param designationLieu the designationLieu to set
	 */
	public void setDesignationLieu(String designationLieu) {
		this.designationLieu = designationLieu;
	}
	/**
	 * [RefReservationDto.designationArabeLieu] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @return the designationArabeLieu
	 */
	public String getDesignationArabeLieu() {
		return designationArabeLieu;
	}
	/**
	 * [RefReservationDto.designationArabeLieu] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  14:29:53
	 * @param designationArabeLieu the designationArabeLieu to set
	 */
	public void setDesignationArabeLieu(String designationArabeLieu) {
		this.designationArabeLieu = designationArabeLieu;
	}
	/**
	 * [RefReservationDto.idEquipement] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  15:16:48
	 * @return the idEquipement
	 */
	public Integer getIdEquipement() {
		return idEquipement;
	}
	/**
	 * [RefReservationDto.idEquipement] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  15:16:48
	 * @param idEquipement the idEquipement to set
	 */
	public void setIdEquipement(Integer idEquipement) {
		this.idEquipement = idEquipement;
	}
	/**
	 * [RefReservationDto.designationEquipement] Getter 
	 * @author BELBRIK Oualid on : 17 mars 2014  15:16:48
	 * @return the designationEquipement
	 */
	public String getDesignationEquipement() {
		return designationEquipement;
	}
	/**
	 * [RefReservationDto.designationEquipement] Setter 
	 * @author BELBRIK Oualid on : 17 mars 2014  15:16:48
	 * @param designationEquipement the designationEquipement to set
	 */
	public void setDesignationEquipement(String designationEquipement) {
		this.designationEquipement = designationEquipement;
	}
	/**
	 * [RefReservationDto.observation] Getter 
	 * @author BELBRIK Oualid on : 18 mars 2014  17:38:25
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * [RefReservationDto.observation] Setter 
	 * @author BELBRIK Oualid on : 18 mars 2014  17:38:25
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * [RefReservationDto.idResponsable] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @return the idResponsable
	 */
	public String getIdResponsable() {
		return idResponsable;
	}
	/**
	 * [RefReservationDto.idResponsable] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @param idResponsable the idResponsable to set
	 */
	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}
	/**
	 * [RefReservationDto.nomLatinResponsable] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @return the nomLatinResponsable
	 */
	public String getNomLatinResponsable() {
		return nomLatinResponsable;
	}
	/**
	 * [RefReservationDto.nomLatinResponsable] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @param nomLatinResponsable the nomLatinResponsable to set
	 */
	public void setNomLatinResponsable(String nomLatinResponsable) {
		this.nomLatinResponsable = nomLatinResponsable;
	}
	/**
	 * [RefReservationDto.prenomLatinResponsable] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @return the prenomLatinResponsable
	 */
	public String getPrenomLatinResponsable() {
		return prenomLatinResponsable;
	}
	/**
	 * [RefReservationDto.prenomLatinResponsable] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @param prenomLatinResponsable the prenomLatinResponsable to set
	 */
	public void setPrenomLatinResponsable(String prenomLatinResponsable) {
		this.prenomLatinResponsable = prenomLatinResponsable;
	}
	/**
	 * [RefReservationDto.nomarabeResponsable] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @return the nomarabeResponsable
	 */
	public String getNomarabeResponsable() {
		return nomarabeResponsable;
	}
	/**
	 * [RefReservationDto.nomarabeResponsable] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @param nomarabeResponsable the nomarabeResponsable to set
	 */
	public void setNomarabeResponsable(String nomarabeResponsable) {
		this.nomarabeResponsable = nomarabeResponsable;
	}
	/**
	 * [RefReservationDto.prenomArabeResponsable] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @return the prenomArabeResponsable
	 */
	public String getPrenomArabeResponsable() {
		return prenomArabeResponsable;
	}
	/**
	 * [RefReservationDto.prenomArabeResponsable] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  15:28:41
	 * @param prenomArabeResponsable the prenomArabeResponsable to set
	 */
	public void setPrenomArabeResponsable(String prenomArabeResponsable) {
		this.prenomArabeResponsable = prenomArabeResponsable;
	}
	/**
	 * [RefReservationDto.dateSituation] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @return the dateSituation
	 */
	public Date getDateSituation() {
		return dateSituation;
	}
	/**
	 * [RefReservationDto.dateSituation] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @param dateSituation the dateSituation to set
	 */
	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}
	/**
	 * [RefReservationDto.llSituationAr] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @return the llSituationAr
	 */
	public String getLlSituationAr() {
		return llSituationAr;
	}
	/**
	 * [RefReservationDto.llSituationAr] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @param llSituationAr the llSituationAr to set
	 */
	public void setLlSituationAr(String llSituationAr) {
		this.llSituationAr = llSituationAr;
	}
	/**
	 * [RefReservationDto.llSituationFr] Getter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @return the llSituationFr
	 */
	public String getLlSituationFr() {
		return llSituationFr;
	}
	/**
	 * [RefReservationDto.llSituationFr] Setter 
	 * @author BELBRIK Oualid on : 19 mars 2014  17:57:47
	 * @param llSituationFr the llSituationFr to set
	 */
	public void setLlSituationFr(String llSituationFr) {
		this.llSituationFr = llSituationFr;
	}
	
}
