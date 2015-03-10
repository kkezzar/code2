package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yselmane on : 11 mai 2014 10:12:42
 */
public class RefAffectDocumentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateDebut;
	private Date dateFin;
	private Boolean estAuteur;
	private String observation;

	/**
	 * RefDocument
	 */
	private String idDocument;
	private String identifiantDocument;
	private String titreDocument;
	private String objetDocument;

	/**
	 * RefIndividu
	 */
	private Integer idIndividu;
	private String identifiantIndividu;
	private String nomLtIndividu;
	private String prenomLtIndividu;
	private Date dateNaissanceIndividu;

	/**
	 * RefGroupe
	 */
	private Integer idGroupe;
	private String identifiantGroupe;
	private String lcGroupe;
	private String lcGroupeArabe;
	private String llGroupe;
	private String llGroupeArabe;

	/**
	 * RefStructure
	 */
	private Integer idStructure;
	private String identifiantStructure;
	private String lcStructureArabe;
	private String lcStructureLatin;
	private String llStructureArabe;
	private String llStructureLatin;

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
	 * infos adresse, nom mappees avec BO
	 */
	private String paysIndividuLongFr;
	private String wilayaIndividuLongFr;
	private String dairaIndividuLongFr;
	private String communeIndividuLongFr;
	private String adresseIndividuLongFr;	
	private String numeroTelephoneIndividu;

	
	
	/**
	 * [RefAffectDocumentDto.id] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * [RefAffectDocumentDto.id] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * [RefAffectDocumentDto.dateDebut] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	/**
	 * [RefAffectDocumentDto.dateDebut] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	/**
	 * [RefAffectDocumentDto.dateFin] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}
	/**
	 * [RefAffectDocumentDto.dateFin] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	/**
	 * [RefAffectDocumentDto.estAuteur] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the estAuteur
	 */
	public Boolean getEstAuteur() {
		return estAuteur;
	}
	/**
	 * [RefAffectDocumentDto.estAuteur] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param estAuteur the estAuteur to set
	 */
	public void setEstAuteur(Boolean estAuteur) {
		this.estAuteur = estAuteur;
	}
	/**
	 * [RefAffectDocumentDto.observation] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * [RefAffectDocumentDto.observation] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param observation the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * [RefAffectDocumentDto.idDocument] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the idDocument
	 */
	public String getIdDocument() {
		return idDocument;
	}
	/**
	 * [RefAffectDocumentDto.idDocument] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param idDocument the idDocument to set
	 */
	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}
	/**
	 * [RefAffectDocumentDto.identifiantDocument] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the identifiantDocument
	 */
	public String getIdentifiantDocument() {
		return identifiantDocument;
	}
	/**
	 * [RefAffectDocumentDto.identifiantDocument] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param identifiantDocument the identifiantDocument to set
	 */
	public void setIdentifiantDocument(String identifiantDocument) {
		this.identifiantDocument = identifiantDocument;
	}
	/**
	 * [RefAffectDocumentDto.titreDocument] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the titreDocument
	 */
	public String getTitreDocument() {
		return titreDocument;
	}
	/**
	 * [RefAffectDocumentDto.titreDocument] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param titreDocument the titreDocument to set
	 */
	public void setTitreDocument(String titreDocument) {
		this.titreDocument = titreDocument;
	}
	/**
	 * [RefAffectDocumentDto.objetDocument] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the objetDocument
	 */
	public String getObjetDocument() {
		return objetDocument;
	}
	/**
	 * [RefAffectDocumentDto.objetDocument] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param objetDocument the objetDocument to set
	 */
	public void setObjetDocument(String objetDocument) {
		this.objetDocument = objetDocument;
	}
	/**
	 * [RefAffectDocumentDto.identifiantIndividu] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the identifiantIndividu
	 */
	public String getIdentifiantIndividu() {
		return identifiantIndividu;
	}
	/**
	 * [RefAffectDocumentDto.identifiantIndividu] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param identifiantIndividu the identifiantIndividu to set
	 */
	public void setIdentifiantIndividu(String identifiantIndividu) {
		this.identifiantIndividu = identifiantIndividu;
	}
	/**
	 * [RefAffectDocumentDto.nomLtIndividu] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the nomLtIndividu
	 */
	public String getNomLtIndividu() {
		return nomLtIndividu;
	}
	/**
	 * [RefAffectDocumentDto.nomLtIndividu] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param nomLtIndividu the nomLtIndividu to set
	 */
	public void setNomLtIndividu(String nomLtIndividu) {
		this.nomLtIndividu = nomLtIndividu;
	}
	/**
	 * [RefAffectDocumentDto.prenomLtIndividu] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the prenomLtIndividu
	 */
	public String getPrenomLtIndividu() {
		return prenomLtIndividu;
	}
	/**
	 * [RefAffectDocumentDto.prenomLtIndividu] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param prenomLtIndividu the prenomLtIndividu to set
	 */
	public void setPrenomLtIndividu(String prenomLtIndividu) {
		this.prenomLtIndividu = prenomLtIndividu;
	}
	

	
	

	/**
	 * [RefAffectDocumentDto.idGroupe] Getter 
	 * @author yselmane on : 15 mai 2014  14:05:09
	 * @return the idGroupe
	 */
	public Integer getIdGroupe() {
		return idGroupe;
	}
	/**
	 * [RefAffectDocumentDto.idGroupe] Setter 
	 * @author yselmane on : 15 mai 2014  14:05:09
	 * @param idGroupe the idGroupe to set
	 */
	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}
	/**
	 * [RefAffectDocumentDto.idStructure] Getter 
	 * @author yselmane on : 15 mai 2014  14:05:09
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}
	/**
	 * [RefAffectDocumentDto.idStructure] Setter 
	 * @author yselmane on : 15 mai 2014  14:05:09
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}
	/**
	 * [RefAffectDocumentDto.idIndividu] Getter 
	 * @author yselmane on : 15 mai 2014  13:43:38
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}
	/**
	 * [RefAffectDocumentDto.idIndividu] Setter 
	 * @author yselmane on : 15 mai 2014  13:43:38
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}
	/**
	 * [RefAffectDocumentDto.dateNaissanceIndividu] Getter 
	 * @author yselmane on : 14 mai 2014  14:12:41
	 * @return the dateNaissanceIndividu
	 */
	public Date getDateNaissanceIndividu() {
		return dateNaissanceIndividu;
	}
	/**
	 * [RefAffectDocumentDto.dateNaissanceIndividu] Setter 
	 * @author yselmane on : 14 mai 2014  14:12:41
	 * @param dateNaissanceIndividu the dateNaissanceIndividu to set
	 */
	public void setDateNaissanceIndividu(Date dateNaissanceIndividu) {
		this.dateNaissanceIndividu = dateNaissanceIndividu;
	}
	/**
	 * [RefAffectDocumentDto.identifiantGroupe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the identifiantGroupe
	 */
	public String getIdentifiantGroupe() {
		return identifiantGroupe;
	}
	/**
	 * [RefAffectDocumentDto.identifiantGroupe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param identifiantGroupe the identifiantGroupe to set
	 */
	public void setIdentifiantGroupe(String identifiantGroupe) {
		this.identifiantGroupe = identifiantGroupe;
	}
	/**
	 * [RefAffectDocumentDto.lcGroupe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the lcGroupe
	 */
	public String getLcGroupe() {
		return lcGroupe;
	}
	/**
	 * [RefAffectDocumentDto.lcGroupe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param lcGroupe the lcGroupe to set
	 */
	public void setLcGroupe(String lcGroupe) {
		this.lcGroupe = lcGroupe;
	}
	/**
	 * [RefAffectDocumentDto.lcGroupeArabe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the lcGroupeArabe
	 */
	public String getLcGroupeArabe() {
		return lcGroupeArabe;
	}
	/**
	 * [RefAffectDocumentDto.lcGroupeArabe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param lcGroupeArabe the lcGroupeArabe to set
	 */
	public void setLcGroupeArabe(String lcGroupeArabe) {
		this.lcGroupeArabe = lcGroupeArabe;
	}
	/**
	 * [RefAffectDocumentDto.llGroupe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the llGroupe
	 */
	public String getLlGroupe() {
		return llGroupe;
	}
	/**
	 * [RefAffectDocumentDto.llGroupe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param llGroupe the llGroupe to set
	 */
	public void setLlGroupe(String llGroupe) {
		this.llGroupe = llGroupe;
	}
	/**
	 * [RefAffectDocumentDto.llGroupeArabe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the llGroupeArabe
	 */
	public String getLlGroupeArabe() {
		return llGroupeArabe;
	}
	/**
	 * [RefAffectDocumentDto.llGroupeArabe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param llGroupeArabe the llGroupeArabe to set
	 */
	public void setLlGroupeArabe(String llGroupeArabe) {
		this.llGroupeArabe = llGroupeArabe;
	}
	/**
	 * [RefAffectDocumentDto.identifiantStructure] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the identifiantStructure
	 */
	public String getIdentifiantStructure() {
		return identifiantStructure;
	}
	/**
	 * [RefAffectDocumentDto.identifiantStructure] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param identifiantStructure the identifiantStructure to set
	 */
	public void setIdentifiantStructure(String identifiantStructure) {
		this.identifiantStructure = identifiantStructure;
	}
	/**
	 * [RefAffectDocumentDto.lcStructureArabe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the lcStructureArabe
	 */
	public String getLcStructureArabe() {
		return lcStructureArabe;
	}
	/**
	 * [RefAffectDocumentDto.lcStructureArabe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param lcStructureArabe the lcStructureArabe to set
	 */
	public void setLcStructureArabe(String lcStructureArabe) {
		this.lcStructureArabe = lcStructureArabe;
	}
	/**
	 * [RefAffectDocumentDto.lcStructureLatin] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the lcStructureLatin
	 */
	public String getLcStructureLatin() {
		return lcStructureLatin;
	}
	/**
	 * [RefAffectDocumentDto.lcStructureLatin] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param lcStructureLatin the lcStructureLatin to set
	 */
	public void setLcStructureLatin(String lcStructureLatin) {
		this.lcStructureLatin = lcStructureLatin;
	}
	/**
	 * [RefAffectDocumentDto.llStructureArabe] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the llStructureArabe
	 */
	public String getLlStructureArabe() {
		return llStructureArabe;
	}
	/**
	 * [RefAffectDocumentDto.llStructureArabe] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param llStructureArabe the llStructureArabe to set
	 */
	public void setLlStructureArabe(String llStructureArabe) {
		this.llStructureArabe = llStructureArabe;
	}
	/**
	 * [RefAffectDocumentDto.llStructureLatin] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the llStructureLatin
	 */
	public String getLlStructureLatin() {
		return llStructureLatin;
	}
	/**
	 * [RefAffectDocumentDto.llStructureLatin] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param llStructureLatin the llStructureLatin to set
	 */
	public void setLlStructureLatin(String llStructureLatin) {
		this.llStructureLatin = llStructureLatin;
	}
	/**
	 * [RefAffectDocumentDto.roleId] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * [RefAffectDocumentDto.roleId] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * [RefAffectDocumentDto.roleCode] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * [RefAffectDocumentDto.roleCode] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleLongFr] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleLibelleLongFr
	 */
	public String getRoleLibelleLongFr() {
		return roleLibelleLongFr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleLongFr] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleLibelleLongFr the roleLibelleLongFr to set
	 */
	public void setRoleLibelleLongFr(String roleLibelleLongFr) {
		this.roleLibelleLongFr = roleLibelleLongFr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleLongAr] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleLibelleLongAr
	 */
	public String getRoleLibelleLongAr() {
		return roleLibelleLongAr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleLongAr] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleLibelleLongAr the roleLibelleLongAr to set
	 */
	public void setRoleLibelleLongAr(String roleLibelleLongAr) {
		this.roleLibelleLongAr = roleLibelleLongAr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleCourtFr] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleLibelleCourtFr
	 */
	public String getRoleLibelleCourtFr() {
		return roleLibelleCourtFr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleCourtFr] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleLibelleCourtFr the roleLibelleCourtFr to set
	 */
	public void setRoleLibelleCourtFr(String roleLibelleCourtFr) {
		this.roleLibelleCourtFr = roleLibelleCourtFr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleCourtAr] Getter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @return the roleLibelleCourtAr
	 */
	public String getRoleLibelleCourtAr() {
		return roleLibelleCourtAr;
	}
	/**
	 * [RefAffectDocumentDto.roleLibelleCourtAr] Setter 
	 * @author yselmane on : 11 mai 2014  14:02:07
	 * @param roleLibelleCourtAr the roleLibelleCourtAr to set
	 */
	public void setRoleLibelleCourtAr(String roleLibelleCourtAr) {
		this.roleLibelleCourtAr = roleLibelleCourtAr;
	}
	/**
	 * [RefAffectDocumentDto.paysIndividuLongFr] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the paysIndividuLongFr
	 */
	public String getPaysIndividuLongFr() {
		return paysIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.paysIndividuLongFr] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param paysIndividuLongFr the paysIndividuLongFr to set
	 */
	public void setPaysIndividuLongFr(String paysIndividuLongFr) {
		this.paysIndividuLongFr = paysIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.wilayaIndividuLongFr] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the wilayaIndividuLongFr
	 */
	public String getWilayaIndividuLongFr() {
		return wilayaIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.wilayaIndividuLongFr] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param wilayaIndividuLongFr the wilayaIndividuLongFr to set
	 */
	public void setWilayaIndividuLongFr(String wilayaIndividuLongFr) {
		this.wilayaIndividuLongFr = wilayaIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.dairaIndividuLongFr] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the dairaIndividuLongFr
	 */
	public String getDairaIndividuLongFr() {
		return dairaIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.dairaIndividuLongFr] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param dairaIndividuLongFr the dairaIndividuLongFr to set
	 */
	public void setDairaIndividuLongFr(String dairaIndividuLongFr) {
		this.dairaIndividuLongFr = dairaIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.communeIndividuLongFr] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the communeIndividuLongFr
	 */
	public String getCommuneIndividuLongFr() {
		return communeIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.communeIndividuLongFr] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param communeIndividuLongFr the communeIndividuLongFr to set
	 */
	public void setCommuneIndividuLongFr(String communeIndividuLongFr) {
		this.communeIndividuLongFr = communeIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.adresseIndividuLongFr] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the adresseIndividuLongFr
	 */
	public String getAdresseIndividuLongFr() {
		return adresseIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.adresseIndividuLongFr] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param adresseIndividuLongFr the adresseIndividuLongFr to set
	 */
	public void setAdresseIndividuLongFr(String adresseIndividuLongFr) {
		this.adresseIndividuLongFr = adresseIndividuLongFr;
	}
	/**
	 * [RefAffectDocumentDto.numeroTelephoneIndividu] Getter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @return the numeroTelephoneIndividu
	 */
	public String getNumeroTelephoneIndividu() {
		return numeroTelephoneIndividu;
	}
	/**
	 * [RefAffectDocumentDto.numeroTelephoneIndividu] Setter 
	 * @author yselmane on : 14 mai 2014  11:09:02
	 * @param numeroTelephoneIndividu the numeroTelephoneIndividu to set
	 */
	public void setNumeroTelephoneIndividu(String numeroTelephoneIndividu) {
		this.numeroTelephoneIndividu = numeroTelephoneIndividu;
	}
	
	
	
	
	

}
