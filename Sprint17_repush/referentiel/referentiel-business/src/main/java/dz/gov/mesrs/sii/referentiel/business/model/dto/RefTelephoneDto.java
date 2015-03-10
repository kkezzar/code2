/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefTelephoneDto.java] 
 * @author MAKERRI Sofiane on : 16 janv. 2014  15:47:11
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author MAKERRI Sofiane  on : 16 janv. 2014  15:47:11
 */
@XmlRootElement(name = "RefTelephoneDto")
public class RefTelephoneDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 janv. 2014  14:01:47
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String numeroTelephone;
	//private Nomenclature nomenclatureByNatureTelephone;
	private Integer natureTelephoneId;
	private String natureTelephoneLibelleLongFr;
	private String natureTelephoneLibelleLongAr;
	private String natureTelephoneLibelleCourtFr;
	private String natureTelephoneLibelleCourtAr;
	private String natureTelephoneCode;
	//private Nomenclature nomenclatureByTypeTelephone;
	private Integer typeTelephoneId;
	private String typeTelephoneLibelleLongFr;
	private String typeTelephoneLibelleLongAr;
	private String typeTelephoneLibelleCourtFr;
	private String typeTelephoneLibelleCourtAr;
	private String typeTelephoneCode;

	/******** coordonnees ********/
	private Integer idIndividu;
	private Integer idStructure;
	private Integer idLieu;
	private Integer idEtablissement;
	private boolean listeRouge;
	private boolean principal;
	private Integer typeCoordonnee;

	/**
	 * [RefTelephoneDto.RefTelephoneDto()] Constructor
	 * @author MAKERRI Sofiane  on : 16 janv. 2014  15:47:11	
	 */
	public RefTelephoneDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [RefTelephoneDto.id] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefTelephoneDto.id] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefTelephoneDto.numeroTelephone] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the numeroTelephone
	 */
	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	/**
	 * [RefTelephoneDto.numeroTelephone] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param numeroTelephone the numeroTelephone to set
	 */
	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}

	

	/**
	 * [RefTelephoneDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [RefTelephoneDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	/**
	 * [RefTelephoneDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [RefTelephoneDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	/**
	 * [RefTelephoneDto.listeRouge] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the listeRouge
	 */
	public boolean getListeRouge() {
		return listeRouge;
	}

	/**
	 * [RefTelephoneDto.listeRouge] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param listeRouge the listeRouge to set
	 */
	public void setListeRouge(boolean listeRouge) {
		this.listeRouge = listeRouge;
	}

	/**
	 * [RefTelephoneDto.principal] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the principal
	 */
	public boolean getPrincipal() {
		return principal;
	}

	/**
	 * [RefTelephoneDto.principal] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	/**
	 * [RefTelephoneDto.typeCoordonnee] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @return the typeCoordonnee
	 */
	public Integer getTypeCoordonnee() {
		return typeCoordonnee;
	}

	/**
	 * [RefTelephoneDto.typeCoordonnee] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  15:49:44
	 * @param typeCoordonnee the typeCoordonnee to set
	 */
	public void setTypeCoordonnee(Integer typeCoordonnee) {
		this.typeCoordonnee = typeCoordonnee;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneId
	 */
	public Integer getNatureTelephoneId() {
		return natureTelephoneId;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneId the natureTelephoneId to set
	 */
	public void setNatureTelephoneId(Integer natureTelephoneId) {
		this.natureTelephoneId = natureTelephoneId;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneLibelleLongFr
	 */
	public String getNatureTelephoneLibelleLongFr() {
		return natureTelephoneLibelleLongFr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneLibelleLongFr the natureTelephoneLibelleLongFr to set
	 */
	public void setNatureTelephoneLibelleLongFr(String natureTelephoneLibelleLongFr) {
		this.natureTelephoneLibelleLongFr = natureTelephoneLibelleLongFr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneLibelleLongAr
	 */
	public String getNatureTelephoneLibelleLongAr() {
		return natureTelephoneLibelleLongAr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneLibelleLongAr the natureTelephoneLibelleLongAr to set
	 */
	public void setNatureTelephoneLibelleLongAr(String natureTelephoneLibelleLongAr) {
		this.natureTelephoneLibelleLongAr = natureTelephoneLibelleLongAr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneLibelleCourtFr
	 */
	public String getNatureTelephoneLibelleCourtFr() {
		return natureTelephoneLibelleCourtFr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneLibelleCourtFr the natureTelephoneLibelleCourtFr to set
	 */
	public void setNatureTelephoneLibelleCourtFr(
			String natureTelephoneLibelleCourtFr) {
		this.natureTelephoneLibelleCourtFr = natureTelephoneLibelleCourtFr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneLibelleCourtAr
	 */
	public String getNatureTelephoneLibelleCourtAr() {
		return natureTelephoneLibelleCourtAr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneLibelleCourtAr the natureTelephoneLibelleCourtAr to set
	 */
	public void setNatureTelephoneLibelleCourtAr(
			String natureTelephoneLibelleCourtAr) {
		this.natureTelephoneLibelleCourtAr = natureTelephoneLibelleCourtAr;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the natureTelephoneCode
	 */
	public String getNatureTelephoneCode() {
		return natureTelephoneCode;
	}

	/**
	 * [RefTelephoneDto.natureTelephoneCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param natureTelephoneCode the natureTelephoneCode to set
	 */
	public void setNatureTelephoneCode(String natureTelephoneCode) {
		this.natureTelephoneCode = natureTelephoneCode;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneId
	 */
	public Integer getTypeTelephoneId() {
		return typeTelephoneId;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneId the typeTelephoneId to set
	 */
	public void setTypeTelephoneId(Integer typeTelephoneId) {
		this.typeTelephoneId = typeTelephoneId;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneLibelleLongFr
	 */
	public String getTypeTelephoneLibelleLongFr() {
		return typeTelephoneLibelleLongFr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneLibelleLongFr the typeTelephoneLibelleLongFr to set
	 */
	public void setTypeTelephoneLibelleLongFr(String typeTelephoneLibelleLongFr) {
		this.typeTelephoneLibelleLongFr = typeTelephoneLibelleLongFr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneLibelleLongAr
	 */
	public String getTypeTelephoneLibelleLongAr() {
		return typeTelephoneLibelleLongAr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneLibelleLongAr the typeTelephoneLibelleLongAr to set
	 */
	public void setTypeTelephoneLibelleLongAr(String typeTelephoneLibelleLongAr) {
		this.typeTelephoneLibelleLongAr = typeTelephoneLibelleLongAr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneLibelleCourtFr
	 */
	public String getTypeTelephoneLibelleCourtFr() {
		return typeTelephoneLibelleCourtFr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneLibelleCourtFr the typeTelephoneLibelleCourtFr to set
	 */
	public void setTypeTelephoneLibelleCourtFr(String typeTelephoneLibelleCourtFr) {
		this.typeTelephoneLibelleCourtFr = typeTelephoneLibelleCourtFr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneLibelleCourtAr
	 */
	public String getTypeTelephoneLibelleCourtAr() {
		return typeTelephoneLibelleCourtAr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneLibelleCourtAr the typeTelephoneLibelleCourtAr to set
	 */
	public void setTypeTelephoneLibelleCourtAr(String typeTelephoneLibelleCourtAr) {
		this.typeTelephoneLibelleCourtAr = typeTelephoneLibelleCourtAr;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @return the typeTelephoneCode
	 */
	public String getTypeTelephoneCode() {
		return typeTelephoneCode;
	}

	/**
	 * [RefTelephoneDto.typeTelephoneCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:00:33
	 * @param typeTelephoneCode the typeTelephoneCode to set
	 */
	public void setTypeTelephoneCode(String typeTelephoneCode) {
		this.typeTelephoneCode = typeTelephoneCode;
	}

	public Integer getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}

	/**
	 * [RefTelephoneDto.idfEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:27:06
	 * @return the idfEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefTelephoneDto.idfEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:27:06
	 * @param idfEtablissement the idfEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	
	

}
