package dz.gov.mesrs.sii.fve.business.model.dto.cursus;



import java.util.Date;


public class FormationDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	
	private String refCodeTypeFormation;
	private String typeFormationLibelleLongFr;
	private String typeFormationLibelleLongAr;
	
	private String refCodeSpecialite;
	
	private String libelleFr;
	private String libelleAr;
	private String oraganismeFr;
	private String oraganismeAr;
	private Date dateDebut;
	private Date dateFin;
	
	private Integer idDossierEtudiant;
	private String numeroMatricule;
	private String refIndividu;

	private Integer idReleveDeNotes;

	public FormationDto() {
	}

	/**
	 * [FormationDto.id] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [FormationDto.id] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [FormationDto.code] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [FormationDto.code] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [FormationDto.refCodeTypeFormation] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the refCodeTypeFormation
	 */
	public String getRefCodeTypeFormation() {
		return refCodeTypeFormation;
	}

	/**
	 * [FormationDto.refCodeTypeFormation] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param refCodeTypeFormation the refCodeTypeFormation to set
	 */
	public void setRefCodeTypeFormation(String refCodeTypeFormation) {
		this.refCodeTypeFormation = refCodeTypeFormation;
	}

	/**
	 * [FormationDto.typeFormationLibelleLongFr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the typeFormationLibelleLongFr
	 */
	public String getTypeFormationLibelleLongFr() {
		return typeFormationLibelleLongFr;
	}

	/**
	 * [FormationDto.typeFormationLibelleLongFr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param typeFormationLibelleLongFr the typeFormationLibelleLongFr to set
	 */
	public void setTypeFormationLibelleLongFr(String typeFormationLibelleLongFr) {
		this.typeFormationLibelleLongFr = typeFormationLibelleLongFr;
	}

	/**
	 * [FormationDto.typeFormationLibelleLongAr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the typeFormationLibelleLongAr
	 */
	public String getTypeFormationLibelleLongAr() {
		return typeFormationLibelleLongAr;
	}

	/**
	 * [FormationDto.typeFormationLibelleLongAr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param typeFormationLibelleLongAr the typeFormationLibelleLongAr to set
	 */
	public void setTypeFormationLibelleLongAr(String typeFormationLibelleLongAr) {
		this.typeFormationLibelleLongAr = typeFormationLibelleLongAr;
	}

	/**
	 * [FormationDto.refCodeSpecialite] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the refCodeSpecialite
	 */
	public String getRefCodeSpecialite() {
		return refCodeSpecialite;
	}

	/**
	 * [FormationDto.refCodeSpecialite] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param refCodeSpecialite the refCodeSpecialite to set
	 */
	public void setRefCodeSpecialite(String refCodeSpecialite) {
		this.refCodeSpecialite = refCodeSpecialite;
	}

	/**
	 * [FormationDto.libelleFr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the libelleFr
	 */
	public String getLibelleFr() {
		return libelleFr;
	}

	/**
	 * [FormationDto.libelleFr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param libelleFr the libelleFr to set
	 */
	public void setLibelleFr(String libelleFr) {
		this.libelleFr = libelleFr;
	}

	/**
	 * [FormationDto.libelleAr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the libelleAr
	 */
	public String getLibelleAr() {
		return libelleAr;
	}

	/**
	 * [FormationDto.libelleAr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param libelleAr the libelleAr to set
	 */
	public void setLibelleAr(String libelleAr) {
		this.libelleAr = libelleAr;
	}

	/**
	 * [FormationDto.oraganismeFr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the oraganismeFr
	 */
	public String getOraganismeFr() {
		return oraganismeFr;
	}

	/**
	 * [FormationDto.oraganismeFr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param oraganismeFr the oraganismeFr to set
	 */
	public void setOraganismeFr(String oraganismeFr) {
		this.oraganismeFr = oraganismeFr;
	}

	/**
	 * [FormationDto.oraganismeAr] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the oraganismeAr
	 */
	public String getOraganismeAr() {
		return oraganismeAr;
	}

	/**
	 * [FormationDto.oraganismeAr] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param oraganismeAr the oraganismeAr to set
	 */
	public void setOraganismeAr(String oraganismeAr) {
		this.oraganismeAr = oraganismeAr;
	}

	/**
	 * [FormationDto.dateDebut] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [FormationDto.dateDebut] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [FormationDto.dateFin] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [FormationDto.dateFin] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [FormationDto.idDossierEtudiant] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the idDossierEtudiant
	 */
	public Integer getIdDossierEtudiant() {
		return idDossierEtudiant;
	}

	/**
	 * [FormationDto.idDossierEtudiant] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param idDossierEtudiant the idDossierEtudiant to set
	 */
	public void setIdDossierEtudiant(Integer idDossierEtudiant) {
		this.idDossierEtudiant = idDossierEtudiant;
	}

	/**
	 * [FormationDto.numeroMatricule] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [FormationDto.numeroMatricule] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [FormationDto.refIndividu] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the refIndividu
	 */
	public String getRefIndividu() {
		return refIndividu;
	}

	/**
	 * [FormationDto.refIndividu] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param refIndividu the refIndividu to set
	 */
	public void setRefIndividu(String refIndividu) {
		this.refIndividu = refIndividu;
	}

	/**
	 * [FormationDto.idReleveDeNotes] Getter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @return the idReleveDeNotes
	 */
	public Integer getIdReleveDeNotes() {
		return idReleveDeNotes;
	}

	/**
	 * [FormationDto.idReleveDeNotes] Setter 
	 * @author yselmane on : 3 juin 2014  15:23:12
	 * @param idReleveDeNotes the idReleveDeNotes to set
	 */
	public void setIdReleveDeNotes(Integer idReleveDeNotes) {
		this.idReleveDeNotes = idReleveDeNotes;
	}

}
