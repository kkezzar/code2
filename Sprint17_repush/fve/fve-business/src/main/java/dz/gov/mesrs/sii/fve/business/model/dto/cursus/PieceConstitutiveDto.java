/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.PieceConstitutiveDto.java] 
 * @author MAKERRI Sofiane on : 21 mai 2014  13:32:27
 */
package dz.gov.mesrs.sii.fve.business.model.dto.cursus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MAKERRI Sofiane on : 21 mai 2014 13:32:27
 */
public class PieceConstitutiveDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:32:34
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idDossier;
	private String refTypeDossier;
	private Integer idTypeDossier;
	private String numeroMatricule;
	private String refIndividu;
	private Boolean fournie;
	private Date dateFourniture;
	private String observation;
	private Integer ncTypePieceId;
	private String ncTypePieceCode;
	private String ncTypePieceLibelleLongFr;
	private String ncTypePieceLibelleLongAr;
	private String ncTypePieceLibelleCourtFr;
	private String ncTypePieceLibelleCourtAr;

	private Integer refTypePieceId;
	private Boolean refTypePieceOgligatoire;
	private Integer refTypePieceRang;
	private Boolean refTypePieceAFournir;
	private String refTypePieceNombre;

	private Integer ncTypeDossierId;
	private String ncTypeDossierCode;
	private String ncTypeDossierLibelleLongFr;
	private String ncTypeDossierLibelleLongAr;
	private String ncTypeDossierLibelleCourtFr;
	private String ncTypeDossierLibelleCourtAr;

	/**
	 * [PieceConstitutiveDto.PieceConstitutiveDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:32:27
	 */
	public PieceConstitutiveDto() {
		super();
	}

	/**
	 * [PieceConstitutiveDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [PieceConstitutiveDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [PieceConstitutiveDto.refTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the refTypeDossier
	 */
	public String getRefTypeDossier() {
		return refTypeDossier;
	}

	/**
	 * [PieceConstitutiveDto.refTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param refTypeDossier
	 *            the refTypeDossier to set
	 */
	public void setRefTypeDossier(String refTypeDossier) {
		this.refTypeDossier = refTypeDossier;
	}

	/**
	 * [PieceConstitutiveDto.numeroMatricule] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}

	/**
	 * [PieceConstitutiveDto.numeroMatricule] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param numeroMatricule
	 *            the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}

	/**
	 * [PieceConstitutiveDto.refIndividu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the refIndividu
	 */
	public String getRefIndividu() {
		return refIndividu;
	}

	/**
	 * [PieceConstitutiveDto.refIndividu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param refIndividu
	 *            the refIndividu to set
	 */
	public void setRefIndividu(String refIndividu) {
		this.refIndividu = refIndividu;
	}

	/**
	 * [PieceConstitutiveDto.fournie] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the fournie
	 */
	public Boolean getFournie() {
		return fournie;
	}

	/**
	 * [PieceConstitutiveDto.fournie] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param fournie
	 *            the fournie to set
	 */
	public void setFournie(Boolean fournie) {
		this.fournie = fournie;
	}

	/**
	 * [PieceConstitutiveDto.dateFourniture] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the dateFourniture
	 */
	public Date getDateFourniture() {
		return dateFourniture;
	}

	/**
	 * [PieceConstitutiveDto.dateFourniture] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param dateFourniture
	 *            the dateFourniture to set
	 */
	public void setDateFourniture(Date dateFourniture) {
		this.dateFourniture = dateFourniture;
	}

	/**
	 * [PieceConstitutiveDto.observation] Getter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * [PieceConstitutiveDto.observation] Setter
	 * 
	 * @author MAKERRI Sofiane on : 21 mai 2014 13:41:35
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * [PieceConstitutiveDto.idTypeDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 16:09:51
	 * @return the idTypeDossier
	 */
	public Integer getIdTypeDossier() {
		return idTypeDossier;
	}

	/**
	 * [PieceConstitutiveDto.idTypeDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 mai 2014 16:09:51
	 * @param idTypeDossier
	 *            the idTypeDossier to set
	 */
	public void setIdTypeDossier(Integer idTypeDossier) {
		this.idTypeDossier = idTypeDossier;
	}


	/**
	 * [PieceConstitutiveDto.idDossier] Getter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 10:27:05
	 * @return the idDossier
	 */
	public Integer getIdDossier() {
		return idDossier;
	}

	/**
	 * [PieceConstitutiveDto.idDossier] Setter
	 * 
	 * @author MAKERRI Sofiane on : 6 nov. 2014 10:27:05
	 * @param idDossier
	 *            the idDossier to set
	 */
	public void setIdDossier(Integer idDossier) {
		this.idDossier = idDossier;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceId
	 */
	public Integer getNcTypePieceId() {
		return ncTypePieceId;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceId
	 *            the ncTypePieceId to set
	 */
	public void setNcTypePieceId(Integer ncTypePieceId) {
		this.ncTypePieceId = ncTypePieceId;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceCode
	 */
	public String getNcTypePieceCode() {
		return ncTypePieceCode;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceCode
	 *            the ncTypePieceCode to set
	 */
	public void setNcTypePieceCode(String ncTypePieceCode) {
		this.ncTypePieceCode = ncTypePieceCode;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceLibelleLongFr
	 */
	public String getNcTypePieceLibelleLongFr() {
		return ncTypePieceLibelleLongFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceLibelleLongFr
	 *            the ncTypePieceLibelleLongFr to set
	 */
	public void setNcTypePieceLibelleLongFr(String ncTypePieceLibelleLongFr) {
		this.ncTypePieceLibelleLongFr = ncTypePieceLibelleLongFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleLongAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceLibelleLongAr
	 */
	public String getNcTypePieceLibelleLongAr() {
		return ncTypePieceLibelleLongAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleLongAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceLibelleLongAr
	 *            the ncTypePieceLibelleLongAr to set
	 */
	public void setNcTypePieceLibelleLongAr(String ncTypePieceLibelleLongAr) {
		this.ncTypePieceLibelleLongAr = ncTypePieceLibelleLongAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleCourtFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceLibelleCourtFr
	 */
	public String getNcTypePieceLibelleCourtFr() {
		return ncTypePieceLibelleCourtFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleCourtFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceLibelleCourtFr
	 *            the ncTypePieceLibelleCourtFr to set
	 */
	public void setNcTypePieceLibelleCourtFr(String ncTypePieceLibelleCourtFr) {
		this.ncTypePieceLibelleCourtFr = ncTypePieceLibelleCourtFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleCourtAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @return the ncTypePieceLibelleCourtAr
	 */
	public String getNcTypePieceLibelleCourtAr() {
		return ncTypePieceLibelleCourtAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypePieceLibelleCourtAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 4 déc. 2014 11:04:11
	 * @param ncTypePieceLibelleCourtAr
	 *            the ncTypePieceLibelleCourtAr to set
	 */
	public void setNcTypePieceLibelleCourtAr(String ncTypePieceLibelleCourtAr) {
		this.ncTypePieceLibelleCourtAr = ncTypePieceLibelleCourtAr;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the refTypePieceId
	 */
	public Integer getRefTypePieceId() {
		return refTypePieceId;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param refTypePieceId
	 *            the refTypePieceId to set
	 */
	public void setRefTypePieceId(Integer refTypePieceId) {
		this.refTypePieceId = refTypePieceId;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceOgligatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the refTypePieceOgligatoire
	 */
	public Boolean getRefTypePieceOgligatoire() {
		return refTypePieceOgligatoire;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceOgligatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param refTypePieceOgligatoire
	 *            the refTypePieceOgligatoire to set
	 */
	public void setRefTypePieceOgligatoire(Boolean refTypePieceOgligatoire) {
		this.refTypePieceOgligatoire = refTypePieceOgligatoire;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceRang] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the refTypePieceRang
	 */
	public Integer getRefTypePieceRang() {
		return refTypePieceRang;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceRang] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param refTypePieceRang
	 *            the refTypePieceRang to set
	 */
	public void setRefTypePieceRang(Integer refTypePieceRang) {
		this.refTypePieceRang = refTypePieceRang;
	}


	/**
	 * [PieceConstitutiveDto.refTypePieceNombre] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the refTypePieceNombre
	 */
	public String getRefTypePieceNombre() {
		return refTypePieceNombre;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceNombre] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param refTypePieceNombre
	 *            the refTypePieceNombre to set
	 */
	public void setRefTypePieceNombre(String refTypePieceNombre) {
		this.refTypePieceNombre = refTypePieceNombre;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierId
	 */
	public Integer getNcTypeDossierId() {
		return ncTypeDossierId;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierId
	 *            the ncTypeDossierId to set
	 */
	public void setNcTypeDossierId(Integer ncTypeDossierId) {
		this.ncTypeDossierId = ncTypeDossierId;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierCode
	 */
	public String getNcTypeDossierCode() {
		return ncTypeDossierCode;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierCode
	 *            the ncTypeDossierCode to set
	 */
	public void setNcTypeDossierCode(String ncTypeDossierCode) {
		this.ncTypeDossierCode = ncTypeDossierCode;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierLibelleLongFr
	 */
	public String getNcTypeDossierLibelleLongFr() {
		return ncTypeDossierLibelleLongFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierLibelleLongFr
	 *            the ncTypeDossierLibelleLongFr to set
	 */
	public void setNcTypeDossierLibelleLongFr(String ncTypeDossierLibelleLongFr) {
		this.ncTypeDossierLibelleLongFr = ncTypeDossierLibelleLongFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleLongAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierLibelleLongAr
	 */
	public String getNcTypeDossierLibelleLongAr() {
		return ncTypeDossierLibelleLongAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleLongAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierLibelleLongAr
	 *            the ncTypeDossierLibelleLongAr to set
	 */
	public void setNcTypeDossierLibelleLongAr(String ncTypeDossierLibelleLongAr) {
		this.ncTypeDossierLibelleLongAr = ncTypeDossierLibelleLongAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleCourtFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierLibelleCourtFr
	 */
	public String getNcTypeDossierLibelleCourtFr() {
		return ncTypeDossierLibelleCourtFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleCourtFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierLibelleCourtFr
	 *            the ncTypeDossierLibelleCourtFr to set
	 */
	public void setNcTypeDossierLibelleCourtFr(String ncTypeDossierLibelleCourtFr) {
		this.ncTypeDossierLibelleCourtFr = ncTypeDossierLibelleCourtFr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleCourtAr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @return the ncTypeDossierLibelleCourtAr
	 */
	public String getNcTypeDossierLibelleCourtAr() {
		return ncTypeDossierLibelleCourtAr;
	}

	/**
	 * [PieceConstitutiveDto.ncTypeDossierLibelleCourtAr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 22 déc. 2014 09:51:25
	 * @param ncTypeDossierLibelleCourtAr
	 *            the ncTypeDossierLibelleCourtAr to set
	 */
	public void setNcTypeDossierLibelleCourtAr(String ncTypeDossierLibelleCourtAr) {
		this.ncTypeDossierLibelleCourtAr = ncTypeDossierLibelleCourtAr;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceAFournir] Getter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  11:47:26
	 * @return the refTypePieceAFournir
	 */
	public Boolean getRefTypePieceAFournir() {
		return refTypePieceAFournir;
	}

	/**
	 * [PieceConstitutiveDto.refTypePieceAFournir] Setter 
	 * @author MAKERRI Sofiane on : 22 déc. 2014  11:47:26
	 * @param refTypePieceAFournir the refTypePieceAFournir to set
	 */
	public void setRefTypePieceAFournir(Boolean refTypePieceAFournir) {
		this.refTypePieceAFournir = refTypePieceAFournir;
	}
	
	

}
