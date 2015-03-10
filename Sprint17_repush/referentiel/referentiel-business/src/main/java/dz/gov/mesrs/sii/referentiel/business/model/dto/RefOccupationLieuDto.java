/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefOccupationDto.java] 
 * @author BELBRIK Oualid on : 19 02 2014  12:02:19
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

/**
 * @author BELBRIK Oualid  on : 19 02 2014  12:02:19
 */
public class RefOccupationLieuDto {

	private Integer id;
	private Date dateDebut;
	private Date dateFin;
	private Boolean provisoire;
	/***** TypeOccupation ****/
	private Integer idTypeOccupation;
	private String codeTypeOccupation;
	private String llTypeOccupationFr;
	private String llTypeOccupationAr;

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
		
	
	/**
	 * [RefOccupationDto.id] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefOccupationDto.id] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * [RefOccupationLieuDto.dateDebut] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * [RefOccupationLieuDto.dateDebut] Setter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * [RefOccupationLieuDto.dateFin] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * [RefOccupationLieuDto.dateFin] Setter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * [RefOccupationLieuDto.idTypeOccupation] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @return the idTypeOccupation
	 */
	public Integer getIdTypeOccupation() {
		return idTypeOccupation;
	}
	/**
	 * [RefOccupationLieuDto.idTypeOccupation] Setter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @param idTypeOccupation the idTypeOccupation to set
	 */
	public void setIdTypeOccupation(Integer idTypeOccupation) {
		this.idTypeOccupation = idTypeOccupation;
	}
	/**
	 * [RefOccupationLieuDto.llTypeOccupationFr] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @return the llTypeOccupationFr
	 */
	public String getLlTypeOccupationFr() {
		return llTypeOccupationFr;
	}
	/**
	 * [RefOccupationLieuDto.llTypeOccupationFr] Setter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @param llTypeOccupationFr the llTypeOccupationFr to set
	 */
	public void setLlTypeOccupationFr(String llTypeOccupationFr) {
		this.llTypeOccupationFr = llTypeOccupationFr;
	}
	/**
	 * [RefOccupationLieuDto.llTypeOccupationAr] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @return the llTypeOccupationAr
	 */
	public String getLlTypeOccupationAr() {
		return llTypeOccupationAr;
	}
	/**
	 * [RefOccupationLieuDto.llTypeOccupationAr] Setter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  15:40:14
	 * @param llTypeOccupationAr the llTypeOccupationAr to set
	 */
	public void setLlTypeOccupationAr(String llTypeOccupationAr) {
		this.llTypeOccupationAr = llTypeOccupationAr;
	}

	
	/**
	 * [RefOccupationDto.llStructureLatin] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}
	/**
	 * [RefOccupationDto.llStructureLatin] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}
	/**
	 * [RefOccupationDto.llStructureArabe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefOccupationDto.llStructureArabe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefOccupationDto.nomLatin] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the nomLatin
	 */
	public String getNomLatin() {
		return nomLatin;
	}
	/**
	 * [RefOccupationDto.nomLatin] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param nomLatin the nomLatin to set
	 */
	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}
	/**
	 * [RefOccupationDto.prenomLatin] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the prenomLatin
	 */
	public String getPrenomLatin() {
		return prenomLatin;
	}
	/**
	 * [RefOccupationDto.prenomLatin] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param prenomLatin the prenomLatin to set
	 */
	public void setPrenomLatin(String prenomLatin) {
		this.prenomLatin = prenomLatin;
	}
	/**
	 * [RefOccupationDto.nomarabe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the nomarabe
	 */
	public String getNomarabe() {
		return nomarabe;
	}
	/**
	 * [RefOccupationDto.nomarabe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param nomarabe the nomarabe to set
	 */
	public void setNomarabe(String nomarabe) {
		this.nomarabe = nomarabe;
	}
	/**
	 * [RefOccupationDto.prenomArabe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the prenomArabe
	 */
	public String getPrenomArabe() {
		return prenomArabe;
	}
	/**
	 * [RefOccupationDto.prenomArabe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param prenomArabe the prenomArabe to set
	 */
	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}
	/**
	 * [RefOccupationDto.llGroupe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefOccupationDto.llGroupe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefOccupationDto.llGroupeArabe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefOccupationDto.llGroupeArabe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  12:09:04
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	/**
	 * [RefOccupationDto.idStructure] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefOccupationDto.idStructure] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefOccupationDto.idIndividu] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}
	/**
	 * [RefOccupationDto.idIndividu] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	/**
	 * [RefOccupationDto.idGroupe] Getter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefOccupationDto.idGroupe] Setter 
	 * @author BELBRIK Oualid on : 19 02 2014  17:47:09
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefOccupationDto.idTypeOccupation] Getter 
	 * @author BELBRIK Oualid on : 19 f�vr. 2014  13:02:25
	 * @return the idTypeOccupation
	 */
	
	
	public String getCodeTypeOccupation() {
		return codeTypeOccupation;
	}
	public void setCodeTypeOccupation(String codeTypeOccupation) {
		this.codeTypeOccupation = codeTypeOccupation;
	}
	
	/**
	 * [RefOccupationLieuDto.designationLieu] Getter 
	 * @author BELBRIK Oualid on : 13 mars 2014  16:00:39
	 * @return the designationLieu
	 */
	public String getDesignationLieu() {
		return designationLieu;
	}
	/**
	 * [RefOccupationLieuDto.designationLieu] Setter 
	 * @author BELBRIK Oualid on : 13 mars 2014  16:00:39
	 * @param designationLieu the designationLieu to set
	 */
	public void setDesignationLieu(String designationLieu) {
		this.designationLieu = designationLieu;
	}
	/**
	 * [RefOccupationLieuDto.designationArabeLieu] Getter 
	 * @author BELBRIK Oualid on : 13 mars 2014  16:00:39
	 * @return the designationArabeLieu
	 */
	public String getDesignationArabeLieu() {
		return designationArabeLieu;
	}
	/**
	 * [RefOccupationLieuDto.designationArabeLieu] Setter 
	 * @author BELBRIK Oualid on : 13 mars 2014  16:00:39
	 * @param designationArabeLieu the designationArabeLieu to set
	 */
	public void setDesignationArabeLieu(String designationArabeLieu) {
		this.designationArabeLieu = designationArabeLieu;
	}
	public Integer getIdLieu() {
		return idLieu;
	}
	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}
	/**
	 * [RefOccupationLieuDto.idEquipement] Getter 
	 * @author BELBRIK Oualid on : 11 mars 2014  11:39:20
	 * @return the idEquipement
	 */
	public Integer getIdEquipement() {
		return idEquipement;
	}
	/**
	 * [RefOccupationLieuDto.idEquipement] Setter 
	 * @author BELBRIK Oualid on : 11 mars 2014  11:39:20
	 * @param idEquipement the idEquipement to set
	 */
	public void setIdEquipement(Integer idEquipement) {
		this.idEquipement = idEquipement;
	}
	/**
	 * [RefOccupationLieuDto.designationEquipement] Getter 
	 * @author BELBRIK Oualid on : 11 mars 2014  11:39:20
	 * @return the designationEquipement
	 */
	public String getDesignationEquipement() {
		return designationEquipement;
	}
	/**
	 * [RefOccupationLieuDto.designationEquipement] Setter 
	 * @author BELBRIK Oualid on : 11 mars 2014  11:39:20
	 * @param designationEquipement the designationEquipement to set
	 */
	public void setDesignationEquipement(String designationEquipement) {
		this.designationEquipement = designationEquipement;
	}
	public Boolean getProvisoire() {
		return provisoire;
	}
	public void setProvisoire(Boolean provisoire) {
		this.provisoire = provisoire;
	}
	
	
	
}
