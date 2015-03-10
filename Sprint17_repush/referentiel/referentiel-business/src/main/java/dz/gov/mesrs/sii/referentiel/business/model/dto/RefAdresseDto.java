/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseDto.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  12:18:08
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:18:08
 */
@XmlRootElement(name = "RefAdresseDto")
public class RefAdresseDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 janv. 2014  14:00:41
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Short boitePostale;
	private String codePostal;
	private Short gpsLatitude;
	private Short gpsLongitude;
	private String libelleAdresseArabe;
	private String libelleAdresseLatin;
	//private Nomenclature nomenclatureByPays;
	private Integer paysId;
	private String paysLibelleLongFr;
	private String paysLibelleLongAr;
	private String paysLibelleCourtFr;
	private String paysLibelleCourtAr;
	private String paysCode;
	//private Nomenclature nomenclatureByDaira;
	private Integer dairaId;
	private String dairaLibelleLongFr;
	private String dairaLibelleLongAr;
	private String dairaLibelleCourtFr;
	private String dairaLibelleCourtAr;
	private String dairaCode;
	//private Nomenclature nomenclatureByCommune;
	private Integer communeId;
	private String communeLibelleLongFr;
	private String communeLibelleLongAr;
	private String communeLibelleCourtFr;
	private String communeLibelleCourtAr;
	private String communeCode;
	//private Nomenclature nomenclatureByWilaya;
	private Integer wilayaId;
	private String wilayaLibelleLongFr;
	private String wilayaLibelleLongAr;
	private String wilayaLibelleCourtFr;
	private String wilayaLibelleCourtAr;
	private String wilayaCode;
	//private Nomenclature nomenclatureByTypeAdresse;
	private Integer typeAdresseId;
	private String typeAdresseLibelleLongFr;
	private String typeAdresseLibelleLongAr;
	private String typeAdresseLibelleCourtFr;
	private String typeAdresseLibelleCourtAr;
	private String typeAdresseCode;

	/******** coordonnees ********/
	private Integer idIndividu;
	private Integer idStructure;
	private Integer idLieu;
	private Integer  idEtablissement;
	private boolean listeRouge;
	private boolean principal;
	private Integer typeCoordonnee;
	
	
	/**
	 * [RefAdresseDto.RefAdresseDto()] Constructor
	 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:18:08	
	 */
	public RefAdresseDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [RefAdresseDto.id] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefAdresseDto.id] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefAdresseDto.boitePostale] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the boitePostale
	 */
	public Short getBoitePostale() {
		return boitePostale;
	}

	/**
	 * [RefAdresseDto.boitePostale] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param boitePostale the boitePostale to set
	 */
	public void setBoitePostale(Short boitePostale) {
		this.boitePostale = boitePostale;
	}

	/**
	 * [RefAdresseDto.gpsLatitude] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the gpsLatitude
	 */
	public Short getGpsLatitude() {
		return gpsLatitude;
	}

	/**
	 * [RefAdresseDto.gpsLatitude] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param gpsLatitude the gpsLatitude to set
	 */
	public void setGpsLatitude(Short gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	/**
	 * [RefAdresseDto.gpsLongitude] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the gpsLongitude
	 */
	public Short getGpsLongitude() {
		return gpsLongitude;
	}

	/**
	 * [RefAdresseDto.gpsLongitude] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param gpsLongitude the gpsLongitude to set
	 */
	public void setGpsLongitude(Short gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	/**
	 * [RefAdresseDto.libelleAdresseArabe] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the libelleAdresseArabe
	 */
	public String getLibelleAdresseArabe() {
		return libelleAdresseArabe;
	}

	/**
	 * [RefAdresseDto.libelleAdresseArabe] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param libelleAdresseArabe the libelleAdresseArabe to set
	 */
	public void setLibelleAdresseArabe(String libelleAdresseArabe) {
		this.libelleAdresseArabe = libelleAdresseArabe;
	}

	/**
	 * [RefAdresseDto.libelleAdresseLatin] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @return the libelleAdresseLatin
	 */
	public String getLibelleAdresseLatin() {
		return libelleAdresseLatin;
	}

	/**
	 * [RefAdresseDto.libelleAdresseLatin] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:27:46
	 * @param libelleAdresseLatin the libelleAdresseLatin to set
	 */
	public void setLibelleAdresseLatin(String libelleAdresseLatin) {
		this.libelleAdresseLatin = libelleAdresseLatin;
	}


	/**
	 * [RefAdresseDto.listeRouge] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @return the listeRouge
	 */
	public boolean getListeRouge() {
		return listeRouge;
	}

	/**
	 * [RefAdresseDto.listeRouge] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @param listeRouge the listeRouge to set
	 */
	public void setListeRouge(boolean listeRouge) {
		this.listeRouge = listeRouge;
	}

	/**
	 * [RefAdresseDto.principal] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @return the principal
	 */
	public boolean getPrincipal() {
		return principal;
	}

	/**
	 * [RefAdresseDto.principal] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	/**
	 * [RefAdresseDto.typeCoordonnee] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @return the typeCoordonnee
	 */
	public Integer getTypeCoordonnee() {
		return typeCoordonnee;
	}

	/**
	 * [RefAdresseDto.typeCoordonnee] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  13:07:15
	 * @param typeCoordonnee the typeCoordonnee to set
	 */
	public void setTypeCoordonnee(Integer typeCoordonnee) {
		this.typeCoordonnee = typeCoordonnee;
	}

	/**
	 * [RefAdresseDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  13:42:24
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [RefAdresseDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  13:42:24
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	/**
	 * [RefAdresseDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  13:42:24
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [RefAdresseDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 16 janv. 2014  13:42:24
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}



	/**
	 * [RefAdresseDto.paysId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysId
	 */
	public Integer getPaysId() {
		return paysId;
	}

	/**
	 * [RefAdresseDto.paysId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysId the paysId to set
	 */
	public void setPaysId(Integer paysId) {
		this.paysId = paysId;
	}

	/**
	 * [RefAdresseDto.paysLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysLibelleLongFr
	 */
	public String getPaysLibelleLongFr() {
		return paysLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.paysLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysLibelleLongFr the paysLibelleLongFr to set
	 */
	public void setPaysLibelleLongFr(String paysLibelleLongFr) {
		this.paysLibelleLongFr = paysLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.paysLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysLibelleLongAr
	 */
	public String getPaysLibelleLongAr() {
		return paysLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.paysLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysLibelleLongAr the paysLibelleLongAr to set
	 */
	public void setPaysLibelleLongAr(String paysLibelleLongAr) {
		this.paysLibelleLongAr = paysLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.paysLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysLibelleCourtFr
	 */
	public String getPaysLibelleCourtFr() {
		return paysLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.paysLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysLibelleCourtFr the paysLibelleCourtFr to set
	 */
	public void setPaysLibelleCourtFr(String paysLibelleCourtFr) {
		this.paysLibelleCourtFr = paysLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.paysLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysLibelleCourtAr
	 */
	public String getPaysLibelleCourtAr() {
		return paysLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.paysLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysLibelleCourtAr the paysLibelleCourtAr to set
	 */
	public void setPaysLibelleCourtAr(String paysLibelleCourtAr) {
		this.paysLibelleCourtAr = paysLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.paysCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the paysCode
	 */
	public String getPaysCode() {
		return paysCode;
	}

	/**
	 * [RefAdresseDto.paysCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param paysCode the paysCode to set
	 */
	public void setPaysCode(String paysCode) {
		this.paysCode = paysCode;
	}

	
	/**
	 * [RefAdresseDto.dairaId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaId
	 */
	public Integer getDairaId() {
		return dairaId;
	}

	/**
	 * [RefAdresseDto.dairaId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaId the dairaId to set
	 */
	public void setDairaId(Integer dairaId) {
		this.dairaId = dairaId;
	}

	/**
	 * [RefAdresseDto.dairaLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaLibelleLongFr
	 */
	public String getDairaLibelleLongFr() {
		return dairaLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaLibelleLongFr the dairaLibelleLongFr to set
	 */
	public void setDairaLibelleLongFr(String dairaLibelleLongFr) {
		this.dairaLibelleLongFr = dairaLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaLibelleLongAr
	 */
	public String getDairaLibelleLongAr() {
		return dairaLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaLibelleLongAr the dairaLibelleLongAr to set
	 */
	public void setDairaLibelleLongAr(String dairaLibelleLongAr) {
		this.dairaLibelleLongAr = dairaLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaLibelleCourtFr
	 */
	public String getDairaLibelleCourtFr() {
		return dairaLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaLibelleCourtFr the dairaLibelleCourtFr to set
	 */
	public void setDairaLibelleCourtFr(String dairaLibelleCourtFr) {
		this.dairaLibelleCourtFr = dairaLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaLibelleCourtAr
	 */
	public String getDairaLibelleCourtAr() {
		return dairaLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.dairaLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaLibelleCourtAr the dairaLibelleCourtAr to set
	 */
	public void setDairaLibelleCourtAr(String dairaLibelleCourtAr) {
		this.dairaLibelleCourtAr = dairaLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.dairaCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the dairaCode
	 */
	public String getDairaCode() {
		return dairaCode;
	}

	/**
	 * [RefAdresseDto.dairaCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param dairaCode the dairaCode to set
	 */
	public void setDairaCode(String dairaCode) {
		this.dairaCode = dairaCode;
	}



	/**
	 * [RefAdresseDto.communeId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeId
	 */
	public Integer getCommuneId() {
		return communeId;
	}

	/**
	 * [RefAdresseDto.communeId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeId the communeId to set
	 */
	public void setCommuneId(Integer communeId) {
		this.communeId = communeId;
	}

	/**
	 * [RefAdresseDto.communeLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeLibelleLongFr
	 */
	public String getCommuneLibelleLongFr() {
		return communeLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.communeLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeLibelleLongFr the communeLibelleLongFr to set
	 */
	public void setCommuneLibelleLongFr(String communeLibelleLongFr) {
		this.communeLibelleLongFr = communeLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.communeLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeLibelleLongAr
	 */
	public String getCommuneLibelleLongAr() {
		return communeLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.communeLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeLibelleLongAr the communeLibelleLongAr to set
	 */
	public void setCommuneLibelleLongAr(String communeLibelleLongAr) {
		this.communeLibelleLongAr = communeLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.communeLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeLibelleCourtFr
	 */
	public String getCommuneLibelleCourtFr() {
		return communeLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.communeLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeLibelleCourtFr the communeLibelleCourtFr to set
	 */
	public void setCommuneLibelleCourtFr(String communeLibelleCourtFr) {
		this.communeLibelleCourtFr = communeLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.communeLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeLibelleCourtAr
	 */
	public String getCommuneLibelleCourtAr() {
		return communeLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.communeLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeLibelleCourtAr the communeLibelleCourtAr to set
	 */
	public void setCommuneLibelleCourtAr(String communeLibelleCourtAr) {
		this.communeLibelleCourtAr = communeLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.communeCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the communeCode
	 */
	public String getCommuneCode() {
		return communeCode;
	}

	/**
	 * [RefAdresseDto.communeCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param communeCode the communeCode to set
	 */
	public void setCommuneCode(String communeCode) {
		this.communeCode = communeCode;
	}

	

	/**
	 * [RefAdresseDto.wilayaId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaId
	 */
	public Integer getWilayaId() {
		return wilayaId;
	}

	/**
	 * [RefAdresseDto.wilayaId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaId the wilayaId to set
	 */
	public void setWilayaId(Integer wilayaId) {
		this.wilayaId = wilayaId;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaLibelleLongFr
	 */
	public String getWilayaLibelleLongFr() {
		return wilayaLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaLibelleLongFr the wilayaLibelleLongFr to set
	 */
	public void setWilayaLibelleLongFr(String wilayaLibelleLongFr) {
		this.wilayaLibelleLongFr = wilayaLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaLibelleLongAr
	 */
	public String getWilayaLibelleLongAr() {
		return wilayaLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaLibelleLongAr the wilayaLibelleLongAr to set
	 */
	public void setWilayaLibelleLongAr(String wilayaLibelleLongAr) {
		this.wilayaLibelleLongAr = wilayaLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaLibelleCourtFr
	 */
	public String getWilayaLibelleCourtFr() {
		return wilayaLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaLibelleCourtFr the wilayaLibelleCourtFr to set
	 */
	public void setWilayaLibelleCourtFr(String wilayaLibelleCourtFr) {
		this.wilayaLibelleCourtFr = wilayaLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaLibelleCourtAr
	 */
	public String getWilayaLibelleCourtAr() {
		return wilayaLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.wilayaLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaLibelleCourtAr the wilayaLibelleCourtAr to set
	 */
	public void setWilayaLibelleCourtAr(String wilayaLibelleCourtAr) {
		this.wilayaLibelleCourtAr = wilayaLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.wilayaCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the wilayaCode
	 */
	public String getWilayaCode() {
		return wilayaCode;
	}

	/**
	 * [RefAdresseDto.wilayaCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param wilayaCode the wilayaCode to set
	 */
	public void setWilayaCode(String wilayaCode) {
		this.wilayaCode = wilayaCode;
	}

	

	/**
	 * [RefAdresseDto.typeAdresseId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseId
	 */
	public Integer getTypeAdresseId() {
		return typeAdresseId;
	}

	/**
	 * [RefAdresseDto.typeAdresseId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseId the typeAdresseId to set
	 */
	public void setTypeAdresseId(Integer typeAdresseId) {
		this.typeAdresseId = typeAdresseId;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseLibelleLongFr
	 */
	public String getTypeAdresseLibelleLongFr() {
		return typeAdresseLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseLibelleLongFr the typeAdresseLibelleLongFr to set
	 */
	public void setTypeAdresseLibelleLongFr(String typeAdresseLibelleLongFr) {
		this.typeAdresseLibelleLongFr = typeAdresseLibelleLongFr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseLibelleLongAr
	 */
	public String getTypeAdresseLibelleLongAr() {
		return typeAdresseLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseLibelleLongAr the typeAdresseLibelleLongAr to set
	 */
	public void setTypeAdresseLibelleLongAr(String typeAdresseLibelleLongAr) {
		this.typeAdresseLibelleLongAr = typeAdresseLibelleLongAr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseLibelleCourtFr
	 */
	public String getTypeAdresseLibelleCourtFr() {
		return typeAdresseLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseLibelleCourtFr the typeAdresseLibelleCourtFr to set
	 */
	public void setTypeAdresseLibelleCourtFr(String typeAdresseLibelleCourtFr) {
		this.typeAdresseLibelleCourtFr = typeAdresseLibelleCourtFr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseLibelleCourtAr
	 */
	public String getTypeAdresseLibelleCourtAr() {
		return typeAdresseLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.typeAdresseLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseLibelleCourtAr the typeAdresseLibelleCourtAr to set
	 */
	public void setTypeAdresseLibelleCourtAr(String typeAdresseLibelleCourtAr) {
		this.typeAdresseLibelleCourtAr = typeAdresseLibelleCourtAr;
	}

	/**
	 * [RefAdresseDto.typeAdresseCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @return the typeAdresseCode
	 */
	public String getTypeAdresseCode() {
		return typeAdresseCode;
	}

	/**
	 * [RefAdresseDto.typeAdresseCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  09:34:12
	 * @param typeAdresseCode the typeAdresseCode to set
	 */
	public void setTypeAdresseCode(String typeAdresseCode) {
		this.typeAdresseCode = typeAdresseCode;
	}

	public Integer getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}

	
	/**
	 * [RefAdresseDto.idfEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:58:51
	 * @return the idfEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefAdresseDto.idfEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:58:51
	 * @param idfEtablissement the idfEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [RefAdresseDto.codePostal] Getter 
	 * @author MAKERRI Sofiane on : 22 juil. 2014  09:06:15
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * [RefAdresseDto.codePostal] Setter 
	 * @author MAKERRI Sofiane on : 22 juil. 2014  09:06:15
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	
	
	
	
	

}
