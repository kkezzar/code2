/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefAdresseElectroniqueDto.java] 
 * @author MAKERRI Sofiane on : 19 janv. 2014  10:20:47
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author MAKERRI Sofiane  on : 19 janv. 2014  10:20:47
 */
@XmlRootElement(name = "RefAdresseElectroniqueDto")
public class RefAdresseElectroniqueDto implements Serializable {
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 janv. 2014  14:00:52
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nomAdresse;
	//private Nomenclature nomenclatureByTypeAdresseElectronique;
	private Integer typeAdresseElectroniqueId;
	private String typeAdresseElectroniqueLibelleLongFr;
	private String typeAdresseElectroniqueLibelleLongAr;
	private String typeAdresseElectroniqueLibelleCourtFr;
	private String typeAdresseElectroniqueLibelleCourtAr;
	private String typeAdresseElectroniqueCode;
	//private Nomenclature nomenclatureByNatureAdresseElectronique;
	private Integer natureAdresseElectroniqueId;
	private String natureAdresseElectroniqueLibelleLongFr;
	private String natureAdresseElectroniqueLibelleLongAr;
	private String natureAdresseElectroniqueLibelleCourtFr;
	private String natureAdresseElectroniqueLibelleCourtAr;
	private String natureAdresseElectroniqueCode;

	/******** coordonnees ********/
	private Integer idIndividu;
	private Integer idStructure;
	private Integer idLieu;
	private Integer idEtablissement;
	private boolean listeRouge;
	private boolean principal;
	private Integer typeCoordonnee;
	

	/**
	 * [RefAdresseElectroniqueDto.RefAdresseElectroniqueDto()] Constructor
	 * @author MAKERRI Sofiane  on : 19 janv. 2014  10:20:47	
	 */
	public RefAdresseElectroniqueDto() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * [RefAdresseElectroniqueDto.id] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * [RefAdresseElectroniqueDto.id] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * [RefAdresseElectroniqueDto.nomAdresse] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the nomAdresse
	 */
	public String getNomAdresse() {
		return nomAdresse;
	}


	/**
	 * [RefAdresseElectroniqueDto.nomAdresse] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param nomAdresse the nomAdresse to set
	 */
	public void setNomAdresse(String nomAdresse) {
		this.nomAdresse = nomAdresse;
	}



	


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueId
	 */
	public Integer getTypeAdresseElectroniqueId() {
		return typeAdresseElectroniqueId;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueId the typeAdresseElectroniqueId to set
	 */
	public void setTypeAdresseElectroniqueId(Integer typeAdresseElectroniqueId) {
		this.typeAdresseElectroniqueId = typeAdresseElectroniqueId;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueLibelleLongFr
	 */
	public String getTypeAdresseElectroniqueLibelleLongFr() {
		return typeAdresseElectroniqueLibelleLongFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueLibelleLongFr the typeAdresseElectroniqueLibelleLongFr to set
	 */
	public void setTypeAdresseElectroniqueLibelleLongFr(
			String typeAdresseElectroniqueLibelleLongFr) {
		this.typeAdresseElectroniqueLibelleLongFr = typeAdresseElectroniqueLibelleLongFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueLibelleLongAr
	 */
	public String getTypeAdresseElectroniqueLibelleLongAr() {
		return typeAdresseElectroniqueLibelleLongAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueLibelleLongAr the typeAdresseElectroniqueLibelleLongAr to set
	 */
	public void setTypeAdresseElectroniqueLibelleLongAr(
			String typeAdresseElectroniqueLibelleLongAr) {
		this.typeAdresseElectroniqueLibelleLongAr = typeAdresseElectroniqueLibelleLongAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueLibelleCourtFr
	 */
	public String getTypeAdresseElectroniqueLibelleCourtFr() {
		return typeAdresseElectroniqueLibelleCourtFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueLibelleCourtFr the typeAdresseElectroniqueLibelleCourtFr to set
	 */
	public void setTypeAdresseElectroniqueLibelleCourtFr(
			String typeAdresseElectroniqueLibelleCourtFr) {
		this.typeAdresseElectroniqueLibelleCourtFr = typeAdresseElectroniqueLibelleCourtFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueLibelleCourtAr
	 */
	public String getTypeAdresseElectroniqueLibelleCourtAr() {
		return typeAdresseElectroniqueLibelleCourtAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueLibelleCourtAr the typeAdresseElectroniqueLibelleCourtAr to set
	 */
	public void setTypeAdresseElectroniqueLibelleCourtAr(
			String typeAdresseElectroniqueLibelleCourtAr) {
		this.typeAdresseElectroniqueLibelleCourtAr = typeAdresseElectroniqueLibelleCourtAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the typeAdresseElectroniqueCode
	 */
	public String getTypeAdresseElectroniqueCode() {
		return typeAdresseElectroniqueCode;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeAdresseElectroniqueCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param typeAdresseElectroniqueCode the typeAdresseElectroniqueCode to set
	 */
	public void setTypeAdresseElectroniqueCode(String typeAdresseElectroniqueCode) {
		this.typeAdresseElectroniqueCode = typeAdresseElectroniqueCode;
	}


	


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueId] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueId
	 */
	public Integer getNatureAdresseElectroniqueId() {
		return natureAdresseElectroniqueId;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueId] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueId the natureAdresseElectroniqueId to set
	 */
	public void setNatureAdresseElectroniqueId(Integer natureAdresseElectroniqueId) {
		this.natureAdresseElectroniqueId = natureAdresseElectroniqueId;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleLongFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueLibelleLongFr
	 */
	public String getNatureAdresseElectroniqueLibelleLongFr() {
		return natureAdresseElectroniqueLibelleLongFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleLongFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueLibelleLongFr the natureAdresseElectroniqueLibelleLongFr to set
	 */
	public void setNatureAdresseElectroniqueLibelleLongFr(
			String natureAdresseElectroniqueLibelleLongFr) {
		this.natureAdresseElectroniqueLibelleLongFr = natureAdresseElectroniqueLibelleLongFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleLongAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueLibelleLongAr
	 */
	public String getNatureAdresseElectroniqueLibelleLongAr() {
		return natureAdresseElectroniqueLibelleLongAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleLongAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueLibelleLongAr the natureAdresseElectroniqueLibelleLongAr to set
	 */
	public void setNatureAdresseElectroniqueLibelleLongAr(
			String natureAdresseElectroniqueLibelleLongAr) {
		this.natureAdresseElectroniqueLibelleLongAr = natureAdresseElectroniqueLibelleLongAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleCourtFr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueLibelleCourtFr
	 */
	public String getNatureAdresseElectroniqueLibelleCourtFr() {
		return natureAdresseElectroniqueLibelleCourtFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleCourtFr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueLibelleCourtFr the natureAdresseElectroniqueLibelleCourtFr to set
	 */
	public void setNatureAdresseElectroniqueLibelleCourtFr(
			String natureAdresseElectroniqueLibelleCourtFr) {
		this.natureAdresseElectroniqueLibelleCourtFr = natureAdresseElectroniqueLibelleCourtFr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleCourtAr] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueLibelleCourtAr
	 */
	public String getNatureAdresseElectroniqueLibelleCourtAr() {
		return natureAdresseElectroniqueLibelleCourtAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueLibelleCourtAr] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueLibelleCourtAr the natureAdresseElectroniqueLibelleCourtAr to set
	 */
	public void setNatureAdresseElectroniqueLibelleCourtAr(
			String natureAdresseElectroniqueLibelleCourtAr) {
		this.natureAdresseElectroniqueLibelleCourtAr = natureAdresseElectroniqueLibelleCourtAr;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueCode] Getter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @return the natureAdresseElectroniqueCode
	 */
	public String getNatureAdresseElectroniqueCode() {
		return natureAdresseElectroniqueCode;
	}


	/**
	 * [RefAdresseElectroniqueDto.natureAdresseElectroniqueCode] Setter 
	 * @author BELDI Jamel on : 23 janv. 2014  10:13:46
	 * @param natureAdresseElectroniqueCode the natureAdresseElectroniqueCode to set
	 */
	public void setNatureAdresseElectroniqueCode(
			String natureAdresseElectroniqueCode) {
		this.natureAdresseElectroniqueCode = natureAdresseElectroniqueCode;
	}


	/**
	 * [RefAdresseElectroniqueDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}


	/**
	 * [RefAdresseElectroniqueDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}


	/**
	 * [RefAdresseElectroniqueDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}


	/**
	 * [RefAdresseElectroniqueDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}


	/**
	 * [RefAdresseElectroniqueDto.listeRouge] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the listeRouge
	 */
	public boolean getListeRouge() {
		return listeRouge;
	}


	/**
	 * [RefAdresseElectroniqueDto.listeRouge] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param listeRouge the listeRouge to set
	 */
	public void setListeRouge(boolean listeRouge) {
		this.listeRouge = listeRouge;
	}


	/**
	 * [RefAdresseElectroniqueDto.principal] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the principal
	 */
	public boolean getPrincipal() {
		return principal;
	}


	/**
	 * [RefAdresseElectroniqueDto.principal] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeCoordonnee] Getter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @return the typeCoordonnee
	 */
	public Integer getTypeCoordonnee() {
		return typeCoordonnee;
	}


	/**
	 * [RefAdresseElectroniqueDto.typeCoordonnee] Setter 
	 * @author MAKERRI Sofiane on : 19 janv. 2014  14:05:24
	 * @param typeCoordonnee the typeCoordonnee to set
	 */
	public void setTypeCoordonnee(Integer typeCoordonnee) {
		this.typeCoordonnee = typeCoordonnee;
	}


	public Integer getIdLieu() {
		return idLieu;
	}


	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}


	/**
	 * [RefAdresseElectroniqueDto.idfEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:27:32
	 * @return the idfEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}


	/**
	 * [RefAdresseElectroniqueDto.idfEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  16:27:32
	 * @param idfEtablissement the idfEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	
	
	



}
