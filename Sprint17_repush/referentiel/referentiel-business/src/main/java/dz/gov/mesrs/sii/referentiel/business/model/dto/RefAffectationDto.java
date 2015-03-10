/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefAffectationDto.java] 
 * @author jbeldi on : 15 janv. 2014  14:17:51
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jbeldi on : 15 janv. 2014 14:17:51
 */
public class RefAffectationDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:18:05
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateDebut;
	private Date dateFin;
	/**
	 * RefIndividu
	 */
	private Integer idIndividu;
	private String nomLtIndividu;
	private String prenomLtIndividu;
	/**
	 * RefGroupe
	 */
	private Integer idGroupe;
	private String lcGroupe;
	private String lcGroupeArabe;
	private String llGroupe;
	private String llGroupeArabe;
	private String identifiantGroupe;
	private Integer groupeIdRefEtablissement;
	private String groupeLlFrRefEtablissement;
	private String groupeCodeRefEtablissement;
	private String groupeAncienCodeRefEtablissement;
	private String groupeLlArRefEtablissement;
	/**
	 * RefGroupeAffecte
	 */
	private Integer idGroupeAffecte;
	private String lcGroupeAffecte;
	private String lcGroupeArabeAffecte;
	private String llGroupeAffecte;
	private String llGroupeArabeAffecte;
	/**
	 * RefStructure
	 */
	private Integer idStructure;
	private String identifiantStructure;
	private String lcStructureArabe;
	private String lcStructureLatin;
	private String llStructureArabe;
	private String llStructureLatin;
	private Integer structureIdRefEtablissement;
	private String structureLlFrRefEtablissement;
	private String structureCodeRefEtablissement;
	private String structureAncienCodeRefEtablissement;
	private String structureLlArRefEtablissement;

	/**
	 * RefDomaineLMD
	 */
	private Integer idDomaine;
	private String identifiantDomaine;
	private String lcDomaineArabe;
	private String lcDomaine;
	private String llDomaineArabe;
	private String llDomaine;
	
	/**
	 * RefFiliereLMD
	 */
	private Integer idFiliere;
	private String identifiantFiliere;
	private String lcFiliereArabe;
	private String lcFiliere;
	private String llFiliereArabe;
	private String llFiliere;
	
	
	/**
	 * ncRole
	 */
	private Integer roleId;
	private String roleCode;
	private String roleLibelleLongFr;
	private String roleLibelleLongAr;
	private String roleLibelleCourtFr;
	private String roleLibelleCourtAr;
	
	/**
	 * RefLieu
	 */
	private Integer idLieu;
	private String DesignationLieu;
	
	/***
	 * RefEvenement
	 */
	private Integer idEvenement;
	private String objetEvenement;
	private String descriptionEvenement;
	
	/**
	 * RefEtablissement
	 */
	private Integer idRefEtablissement;
	private String llFrRefEtablissement;
	private String codeRefEtablissement;
	private String llArRefEtablissement;
	private String ancienCodeRefEtablissement;
	
	
	/**
	 * [RefAffectationDto.roleCode] Getter 
	 * @author BELDI Jamel on : 22 janv. 2014  12:12:46
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * [RefAffectationDto.roleCode] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:12:46
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * [RefAffectationDto.roleId] Getter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * [RefAffectationDto.roleId] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * [RefAffectationDto.roleLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @return the roleLibelleLongFr
	 */
	public String getRoleLibelleLongFr() {
		return roleLibelleLongFr;
	}

	/**
	 * [RefAffectationDto.roleLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @param roleLibelleLongFr
	 *            the roleLibelleLongFr to set
	 */
	public void setRoleLibelleLongFr(String roleLibelleLongFr) {
		this.roleLibelleLongFr = roleLibelleLongFr;
	}

	/**
	 * [RefAffectationDto.roleLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @return the roleLibelleLongAr
	 */
	public String getRoleLibelleLongAr() {
		return roleLibelleLongAr;
	}

	/**
	 * [RefAffectationDto.roleLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @param roleLibelleLongAr
	 *            the roleLibelleLongAr to set
	 */
	public void setRoleLibelleLongAr(String roleLibelleLongAr) {
		this.roleLibelleLongAr = roleLibelleLongAr;
	}

	/**
	 * [RefAffectationDto.roleLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @return the roleLibelleCourtFr
	 */
	public String getRoleLibelleCourtFr() {
		return roleLibelleCourtFr;
	}

	/**
	 * [RefAffectationDto.roleLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @param roleLibelleCourtFr
	 *            the roleLibelleCourtFr to set
	 */
	public void setRoleLibelleCourtFr(String roleLibelleCourtFr) {
		this.roleLibelleCourtFr = roleLibelleCourtFr;
	}

	/**
	 * [RefAffectationDto.roleLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @return the roleLibelleCourtAr
	 */
	public String getRoleLibelleCourtAr() {
		return roleLibelleCourtAr;
	}

	/**
	 * [RefAffectationDto.roleLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 22 janv. 2014 12:11:29
	 * @param roleLibelleCourtAr
	 *            the roleLibelleCourtAr to set
	 */
	public void setRoleLibelleCourtAr(String roleLibelleCourtAr) {
		this.roleLibelleCourtAr = roleLibelleCourtAr;
	}

	/**
	 * [RefAffectationDto.RefAffectationDto()] Constructor
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:17:51
	 */
	public RefAffectationDto() {
		super();
	}

	/**
	 * [RefAffectationDto.identifiant] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the identifiant
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefAffectationDto.identifiant] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param identifiant
	 *            the identifiant to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefAffectationDto.dateDebut] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [RefAffectationDto.dateDebut] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [RefAffectationDto.dateFin] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [RefAffectationDto.dateFin] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	

	/**
	 * [RefAffectationDto.nomLtIndividu] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the nomLtIndividu
	 */
	public String getNomLtIndividu() {
		return nomLtIndividu;
	}

	/**
	 * [RefAffectationDto.nomLtIndividu] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param nomLtIndividu
	 *            the nomLtIndividu to set
	 */
	public void setNomLtIndividu(String nomLtIndividu) {
		this.nomLtIndividu = nomLtIndividu;
	}

	/**
	 * [RefAffectationDto.prenomLtIndividu] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the prenomLtIndividu
	 */
	public String getPrenomLtIndividu() {
		return prenomLtIndividu;
	}

	/**
	 * [RefAffectationDto.prenomLtIndividu] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param prenomLtIndividu
	 *            the prenomLtIndividu to set
	 */
	public void setPrenomLtIndividu(String prenomLtIndividu) {
		this.prenomLtIndividu = prenomLtIndividu;
	}

	

	/**
	 * [RefAffectationDto.lcGroupe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}

	/**
	 * [RefAffectationDto.lcGroupe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcGroupe
	 *            the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}

	/**
	 * [RefAffectationDto.lcGroupeArabe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}

	/**
	 * [RefAffectationDto.lcGroupeArabe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcGroupeArabe
	 *            the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}

	/**
	 * [RefAffectationDto.llGroupe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}

	/**
	 * [RefAffectationDto.llGroupe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llGroupe
	 *            the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}

	/**
	 * [RefAffectationDto.llGroupeArabe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}

	/**
	 * [RefAffectationDto.llGroupeArabe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llGroupeArabe
	 *            the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}

	

	/**
	 * [RefAffectationDto.lcGroupeAffecte] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcGroupeAffecte
	 */
	public String getLcGroupeAffecte() {
		return lcGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.lcGroupeAffecte] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcGroupeAffecte
	 *            the lcGroupeAffecte to set
	 */
	public void setLcGroupeAffecte(String lcGroupeAffecte) {
		this.lcGroupeAffecte = lcGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.lcGroupeArabeAffecte] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcGroupeArabeAffecte
	 */
	public String getLcGroupeArabeAffecte() {
		return lcGroupeArabeAffecte;
	}

	/**
	 * [RefAffectationDto.lcGroupeArabeAffecte] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcGroupeArabeAffecte
	 *            the lcGroupeArabeAffecte to set
	 */
	public void setLcGroupeArabeAffecte(String lcGroupeArabeAffecte) {
		this.lcGroupeArabeAffecte = lcGroupeArabeAffecte;
	}

	/**
	 * [RefAffectationDto.llGroupeAffecte] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llGroupeAffecte
	 */
	public String getLlGroupeAffecte() {
		return llGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.llGroupeAffecte] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llGroupeAffecte
	 *            the llGroupeAffecte to set
	 */
	public void setLlGroupeAffecte(String llGroupeAffecte) {
		this.llGroupeAffecte = llGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.llGroupeArabeAffecte] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llGroupeArabeAffecte
	 */
	public String getLlGroupeArabeAffecte() {
		return llGroupeArabeAffecte;
	}

	/**
	 * [RefAffectationDto.llGroupeArabeAffecte] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llGroupeArabeAffecte
	 *            the llGroupeArabeAffecte to set
	 */
	public void setLlGroupeArabeAffecte(String llGroupeArabeAffecte) {
		this.llGroupeArabeAffecte = llGroupeArabeAffecte;
	}


	/**
	 * [RefAffectationDto.lcStructureArabe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcStructureArabe
	 */
	public String getLcStructureArabe() {
		return lcStructureArabe;
	}

	/**
	 * [RefAffectationDto.lcStructureArabe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcStructureArabe
	 *            the lcStructureArabe to set
	 */
	public void setLcStructureArabe(String lcStructureArabe) {
		this.lcStructureArabe = lcStructureArabe;
	}

	/**
	 * [RefAffectationDto.lcStructureLatin] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the lcStructureLatin
	 */
	public String getLcStructureLatin() {
		return lcStructureLatin;
	}

	/**
	 * [RefAffectationDto.lcStructureLatin] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param lcStructureLatin
	 *            the lcStructureLatin to set
	 */
	public void setLcStructureLatin(String lcStructureLatin) {
		this.lcStructureLatin = lcStructureLatin;
	}

	/**
	 * [RefAffectationDto.llStructureArabe] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}

	/**
	 * [RefAffectationDto.llStructureArabe] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llStructureArabe
	 *            the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}

	/**
	 * [RefAffectationDto.llStructureLatin] Getter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}

	/**
	 * [RefAffectationDto.llStructureLatin] Setter
	 * 
	 * @author jbeldi on : 15 janv. 2014 14:35:00
	 * @param llStructureLatin
	 *            the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}


	/**
	 * [RefAffectationDto.designationLieu] Getter 
	 * @author BELBRIK Oualid on : 16 fï¿½vr. 2014  12:57:02
	 * @return the designationLieu
	 */
	public String getDesignationLieu() {
		return DesignationLieu;
	}

	/**
	 * [RefAffectationDto.designationLieu] Setter 
	 * @author BELBRIK Oualid on : 16 fï¿½vr. 2014  12:57:02
	 * @param designationLieu the designationLieu to set
	 */
	public void setDesignationLieu(String designationLieu) {
		DesignationLieu = designationLieu;
	}

	

	/**
	 * [RefAffectationDto.objetEvenement] Getter 
	 * @author BELDI Jamel on : 11 mars 2014  09:54:35
	 * @return the objetEvenement
	 */
	public String getObjetEvenement() {
		return objetEvenement;
	}

	/**
	 * [RefAffectationDto.objetEvenement] Setter 
	 * @author BELDI Jamel on : 11 mars 2014  09:54:35
	 * @param objetEvenement the objetEvenement to set
	 */
	public void setObjetEvenement(String objetEvenement) {
		this.objetEvenement = objetEvenement;
	}

	/**
	 * [RefAffectationDto.descriptionEvenement] Getter 
	 * @author BELDI Jamel on : 11 mars 2014  09:54:35
	 * @return the descriptionEvenement
	 */
	public String getDescriptionEvenement() {
		return descriptionEvenement;
	}

	/**
	 * [RefAffectationDto.descriptionEvenement] Setter 
	 * @author BELDI Jamel on : 11 mars 2014  09:54:35
	 * @param descriptionEvenement the descriptionEvenement to set
	 */
	public void setDescriptionEvenement(String descriptionEvenement) {
		this.descriptionEvenement = descriptionEvenement;
	}

	/**
	 * [RefAffectationDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:48:30
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [RefAffectationDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:48:30
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	/**
	 * [RefAffectationDto.idGroupe] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}

	/**
	 * [RefAffectationDto.idGroupe] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	/**
	 * [RefAffectationDto.idGroupeAffecte] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @return the idGroupeAffecte
	 */
	public Integer getIdGroupeAffecte() {
		return idGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.idGroupeAffecte] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @param idGroupeAffecte the idGroupeAffecte to set
	 */
	public void setIdGroupeAffecte(Integer idGroupeAffecte) {
		this.idGroupeAffecte = idGroupeAffecte;
	}

	/**
	 * [RefAffectationDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [RefAffectationDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	

	/**
	 * [RefAffectationDto.idLieu] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @return the idLieu
	 */
	public Integer getIdLieu() {
		return idLieu;
	}

	/**
	 * [RefAffectationDto.idLieu] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @param idLieu the idLieu to set
	 */
	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}

	/**
	 * [RefAffectationDto.idEvenement] Getter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @return the idEvenement
	 */
	public Integer getIdEvenement() {
		return idEvenement;
	}

	/**
	 * [RefAffectationDto.idEvenement] Setter 
	 * @author MAKERRI Sofiane on : 8 mai 2014  15:56:04
	 * @param idEvenement the idEvenement to set
	 */
	public void setIdEvenement(Integer idEvenement) {
		this.idEvenement = idEvenement;
	}

	/**
	 * [RefAffectationDto.idDomaine] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the idDomaine
	 */
	public Integer getIdDomaine() {
		return idDomaine;
	}

	/**
	 * [RefAffectationDto.idDomaine] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param idDomaine the idDomaine to set
	 */
	public void setIdDomaine(Integer idDomaine) {
		this.idDomaine = idDomaine;
	}

	/**
	 * [RefAffectationDto.identifiantDomaine] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the identifiantDomaine
	 */
	public String getIdentifiantDomaine() {
		return identifiantDomaine;
	}

	/**
	 * [RefAffectationDto.identifiantDomaine] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param identifiantDomaine the identifiantDomaine to set
	 */
	public void setIdentifiantDomaine(String identifiantDomaine) {
		this.identifiantDomaine = identifiantDomaine;
	}

	/**
	 * [RefAffectationDto.lcDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the lcDomaineArabe
	 */
	public String getLcDomaineArabe() {
		return lcDomaineArabe;
	}

	/**
	 * [RefAffectationDto.lcDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param lcDomaineArabe the lcDomaineArabe to set
	 */
	public void setLcDomaineArabe(String lcDomaineArabe) {
		this.lcDomaineArabe = lcDomaineArabe;
	}

	/**
	 * [RefAffectationDto.lcDomaine] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the lcDomaine
	 */
	public String getLcDomaine() {
		return lcDomaine;
	}

	/**
	 * [RefAffectationDto.lcDomaine] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param lcDomaine the lcDomaine to set
	 */
	public void setLcDomaine(String lcDomaine) {
		this.lcDomaine = lcDomaine;
	}

	/**
	 * [RefAffectationDto.llDomaineArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the llDomaineArabe
	 */
	public String getLlDomaineArabe() {
		return llDomaineArabe;
	}

	/**
	 * [RefAffectationDto.llDomaineArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param llDomaineArabe the llDomaineArabe to set
	 */
	public void setLlDomaineArabe(String llDomaineArabe) {
		this.llDomaineArabe = llDomaineArabe;
	}

	/**
	 * [RefAffectationDto.llDomaine] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the llDomaine
	 */
	public String getLlDomaine() {
		return llDomaine;
	}

	/**
	 * [RefAffectationDto.llDomaine] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param llDomaine the llDomaine to set
	 */
	public void setLlDomaine(String llDomaine) {
		this.llDomaine = llDomaine;
	}

	/**
	 * [RefAffectationDto.idFiliere] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the idFiliere
	 */
	public Integer getIdFiliere() {
		return idFiliere;
	}

	/**
	 * [RefAffectationDto.idFiliere] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param idFiliere the idFiliere to set
	 */
	public void setIdFiliere(Integer idFiliere) {
		this.idFiliere = idFiliere;
	}

	/**
	 * [RefAffectationDto.identifiantFiliere] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the identifiantFiliere
	 */
	public String getIdentifiantFiliere() {
		return identifiantFiliere;
	}

	/**
	 * [RefAffectationDto.identifiantFiliere] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param identifiantFiliere the identifiantFiliere to set
	 */
	public void setIdentifiantFiliere(String identifiantFiliere) {
		this.identifiantFiliere = identifiantFiliere;
	}

	/**
	 * [RefAffectationDto.lcFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the lcFiliereArabe
	 */
	public String getLcFiliereArabe() {
		return lcFiliereArabe;
	}

	/**
	 * [RefAffectationDto.lcFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param lcFiliereArabe the lcFiliereArabe to set
	 */
	public void setLcFiliereArabe(String lcFiliereArabe) {
		this.lcFiliereArabe = lcFiliereArabe;
	}

	/**
	 * [RefAffectationDto.lcFiliere] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the lcFiliere
	 */
	public String getLcFiliere() {
		return lcFiliere;
	}

	/**
	 * [RefAffectationDto.lcFiliere] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param lcFiliere the lcFiliere to set
	 */
	public void setLcFiliere(String lcFiliere) {
		this.lcFiliere = lcFiliere;
	}

	/**
	 * [RefAffectationDto.llFiliereArabe] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the llFiliereArabe
	 */
	public String getLlFiliereArabe() {
		return llFiliereArabe;
	}

	/**
	 * [RefAffectationDto.llFiliereArabe] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param llFiliereArabe the llFiliereArabe to set
	 */
	public void setLlFiliereArabe(String llFiliereArabe) {
		this.llFiliereArabe = llFiliereArabe;
	}

	/**
	 * [RefAffectationDto.llFiliere] Getter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @return the llFiliere
	 */
	public String getLlFiliere() {
		return llFiliere;
	}

	/**
	 * [RefAffectationDto.llFiliere] Setter 
	 * @author BELBRIK Oualid on : 29 sept. 2014  20:10:39
	 * @param llFiliere the llFiliere to set
	 */
	public void setLlFiliere(String llFiliere) {
		this.llFiliere = llFiliere;
	}


	/**
	 * [RefAffectationDto.identifiantGroupe] Getter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  13:29:50
	 * @return the identifiantGroupe
	 */
	public String getIdentifiantGroupe() {
		return identifiantGroupe;
	}

	/**
	 * [RefAffectationDto.identifiantGroupe] Setter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  13:29:50
	 * @param identifiantGroupe the identifiantGroupe to set
	 */
	public void setIdentifiantGroupe(String identifiantGroupe) {
		this.identifiantGroupe = identifiantGroupe;
	}

	/**
	 * [RefAffectationDto.identifiantStructure] Getter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  13:29:50
	 * @return the identifiantStructure
	 */
	public String getIdentifiantStructure() {
		return identifiantStructure;
	}

	/**
	 * [RefAffectationDto.identifiantStructure] Setter 
	 * @author MAKERRI Sofiane on : 29 janv. 2015  13:29:50
	 * @param identifiantStructure the identifiantStructure to set
	 */
	public void setIdentifiantStructure(String identifiantStructure) {
		this.identifiantStructure = identifiantStructure;
	}

	

	/**
	 * [RefAffectationDto.groupeIdRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the groupeIdRefEtablissement
	 */
	public Integer getGroupeIdRefEtablissement() {
		return groupeIdRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeIdRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param groupeIdRefEtablissement the groupeIdRefEtablissement to set
	 */
	public void setGroupeIdRefEtablissement(Integer groupeIdRefEtablissement) {
		this.groupeIdRefEtablissement = groupeIdRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeLlFrRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the groupeLlFrRefEtablissement
	 */
	public String getGroupeLlFrRefEtablissement() {
		return groupeLlFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeLlFrRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param groupeLlFrRefEtablissement the groupeLlFrRefEtablissement to set
	 */
	public void setGroupeLlFrRefEtablissement(String groupeLlFrRefEtablissement) {
		this.groupeLlFrRefEtablissement = groupeLlFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeCodeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the groupeCodeRefEtablissement
	 */
	public String getGroupeCodeRefEtablissement() {
		return groupeCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeCodeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param groupeCodeRefEtablissement the groupeCodeRefEtablissement to set
	 */
	public void setGroupeCodeRefEtablissement(String groupeCodeRefEtablissement) {
		this.groupeCodeRefEtablissement = groupeCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureIdRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the structureIdRefEtablissement
	 */
	public Integer getStructureIdRefEtablissement() {
		return structureIdRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureIdRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param structureIdRefEtablissement the structureIdRefEtablissement to set
	 */
	public void setStructureIdRefEtablissement(Integer structureIdRefEtablissement) {
		this.structureIdRefEtablissement = structureIdRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureLlFrRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the structureLlFrRefEtablissement
	 */
	public String getStructureLlFrRefEtablissement() {
		return structureLlFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureLlFrRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param structureLlFrRefEtablissement the structureLlFrRefEtablissement to set
	 */
	public void setStructureLlFrRefEtablissement(
			String structureLlFrRefEtablissement) {
		this.structureLlFrRefEtablissement = structureLlFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureCodeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @return the structureCodeRefEtablissement
	 */
	public String getStructureCodeRefEtablissement() {
		return structureCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureCodeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:08:16
	 * @param structureCodeRefEtablissement the structureCodeRefEtablissement to set
	 */
	public void setStructureCodeRefEtablissement(
			String structureCodeRefEtablissement) {
		this.structureCodeRefEtablissement = structureCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.idRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @return the idRefEtablissement
	 */
	public Integer getIdRefEtablissement() {
		if(idGroupe != null) {
			idRefEtablissement = groupeIdRefEtablissement;
		} else {
			idRefEtablissement = structureIdRefEtablissement;
		}
		return idRefEtablissement;
	}

	/**
	 * [RefAffectationDto.idRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @param idRefEtablissement the idRefEtablissement to set
	 */
	public void setIdRefEtablissement(Integer idRefEtablissement) {
		this.idRefEtablissement = idRefEtablissement;
	}

	/**
	 * [RefAffectationDto.llFrRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @return the llFrRefEtablissement
	 */
	public String getLlFrRefEtablissement() {
		if(idGroupe != null) {
			llFrRefEtablissement = groupeLlFrRefEtablissement;
		} else {
			llFrRefEtablissement = structureLlFrRefEtablissement;
		}
		return llFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.llFrRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @param llFrRefEtablissement the llFrRefEtablissement to set
	 */
	public void setLlFrRefEtablissement(String llFrRefEtablissement) {
		this.llFrRefEtablissement = llFrRefEtablissement;
	}

	/**
	 * [RefAffectationDto.codeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @return the codeRefEtablissement
	 */
	public String getCodeRefEtablissement() {
		if(idGroupe != null) {
			codeRefEtablissement = groupeCodeRefEtablissement;
		} else {
			codeRefEtablissement = structureCodeRefEtablissement;
		}
		return codeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.codeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:07:10
	 * @param codeRefEtablissement the codeRefEtablissement to set
	 */
	public void setCodeRefEtablissement(String codeRefEtablissement) {
		this.codeRefEtablissement = codeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeAncienCodeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the groupeAncienCodeRefEtablissement
	 */
	public String getGroupeAncienCodeRefEtablissement() {
		return groupeAncienCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeAncienCodeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param groupeAncienCodeRefEtablissement the groupeAncienCodeRefEtablissement to set
	 */
	public void setGroupeAncienCodeRefEtablissement(
			String groupeAncienCodeRefEtablissement) {
		this.groupeAncienCodeRefEtablissement = groupeAncienCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeLlArRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the groupeLlArRefEtablissement
	 */
	public String getGroupeLlArRefEtablissement() {
		return groupeLlArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.groupeLlArRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param groupeLlArRefEtablissement the groupeLlArRefEtablissement to set
	 */
	public void setGroupeLlArRefEtablissement(String groupeLlArRefEtablissement) {
		this.groupeLlArRefEtablissement = groupeLlArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureAncienCodeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the structureAncienCodeRefEtablissement
	 */
	public String getStructureAncienCodeRefEtablissement() {
		return structureAncienCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureAncienCodeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param structureAncienCodeRefEtablissement the structureAncienCodeRefEtablissement to set
	 */
	public void setStructureAncienCodeRefEtablissement(
			String structureAncienCodeRefEtablissement) {
		this.structureAncienCodeRefEtablissement = structureAncienCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureLlArRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the structureLlArRefEtablissement
	 */
	public String getStructureLlArRefEtablissement() {
		return structureLlArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.structureLlArRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param structureLlArRefEtablissement the structureLlArRefEtablissement to set
	 */
	public void setStructureLlArRefEtablissement(
			String structureLlArRefEtablissement) {
		this.structureLlArRefEtablissement = structureLlArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.llArRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the llArRefEtablissement
	 */
	public String getLlArRefEtablissement() {
		if(idGroupe != null) {
			llArRefEtablissement = groupeLlArRefEtablissement;
		} else {
			llArRefEtablissement = structureLlArRefEtablissement;
		}
		return llArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.llArRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param llArRefEtablissement the llArRefEtablissement to set
	 */
	public void setLlArRefEtablissement(String llArRefEtablissement) {
		this.llArRefEtablissement = llArRefEtablissement;
	}

	/**
	 * [RefAffectationDto.ancienCodeRefEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @return the ancienCodeRefEtablissement
	 */
	public String getAncienCodeRefEtablissement() {
		if(idGroupe != null) {
			ancienCodeRefEtablissement = groupeAncienCodeRefEtablissement;
		} else {
			ancienCodeRefEtablissement = structureAncienCodeRefEtablissement;
		}
		return ancienCodeRefEtablissement;
	}

	/**
	 * [RefAffectationDto.ancienCodeRefEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 3 févr. 2015  09:23:21
	 * @param ancienCodeRefEtablissement the ancienCodeRefEtablissement to set
	 */
	public void setAncienCodeRefEtablissement(String ancienCodeRefEtablissement) {
		this.ancienCodeRefEtablissement = ancienCodeRefEtablissement;
	}

}
