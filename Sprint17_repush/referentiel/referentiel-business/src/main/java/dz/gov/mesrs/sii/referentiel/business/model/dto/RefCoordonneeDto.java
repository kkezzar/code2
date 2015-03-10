/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefCoordonneeDto.java] 
 * @author MAKERRI Sofiane on : 15 janv. 2014  12:10:36
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:10:36
 */
@XmlRootElement(name = "RefCoordonneeDto")
public class RefCoordonneeDto implements Serializable {
	
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 19 janv. 2014  14:01:29
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private boolean listeRouge;
	private boolean principal;
	private Integer typeCoordonnee;
	/******** ncTypeCoordonnee ********/
	private Short idTypeCoordonnee;
	private String llTypeCoordonneeArabe;
	private String llTypeCoordonneeLatin;
	/******** refIndividu ********/
	private Integer idIndividu;
	/******** refStructure ********/
	private Integer idStructure;
	/******** refLieu ********/
	private Integer idLieu;
	/******** refEtablissement ********/
	private Integer idEtablissement;
	
	
	/**
	 * [RefCoordonneeDto.RefCoordonneeDto()] Constructor
	 * @author MAKERRI Sofiane  on : 15 janv. 2014  12:10:36	
	 */
	public RefCoordonneeDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * [RefCoordonneeDto.id] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefCoordonneeDto.id] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefCoordonneeDto.listeRouge] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the listeRouge
	 */
	public boolean getListeRouge() {
		return listeRouge;
	}

	/**
	 * [RefCoordonneeDto.listeRouge] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param listeRouge the listeRouge to set
	 */
	public void setListeRouge(boolean listeRouge) {
		this.listeRouge = listeRouge;
	}

	/**
	 * [RefCoordonneeDto.principal] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the principal
	 */
	public boolean getPrincipal() {
		return principal;
	}

	/**
	 * [RefCoordonneeDto.principal] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param principal the principal to set
	 */
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	/**
	 * [RefCoordonneeDto.idTypeCoordonnee] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the idTypeCoordonnee
	 */
	public Short getIdTypeCoordonnee() {
		return idTypeCoordonnee;
	}

	/**
	 * [RefCoordonneeDto.idTypeCoordonnee] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param idTypeCoordonnee the idTypeCoordonnee to set
	 */
	public void setIdTypeCoordonnee(Short idTypeCoordonnee) {
		this.idTypeCoordonnee = idTypeCoordonnee;
	}

	/**
	 * [RefCoordonneeDto.llTypeCoordonneeArabe] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the llTypeCoordonneeArabe
	 */
	public String getLlTypeCoordonneeArabe() {
		return llTypeCoordonneeArabe;
	}

	/**
	 * [RefCoordonneeDto.llTypeCoordonneeArabe] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param llTypeCoordonneeArabe the llTypeCoordonneeArabe to set
	 */
	public void setLlTypeCoordonneeArabe(String llTypeCoordonneeArabe) {
		this.llTypeCoordonneeArabe = llTypeCoordonneeArabe;
	}

	/**
	 * [RefCoordonneeDto.llTypeCoordonneeLatin] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the llTypeCoordonneeLatin
	 */
	public String getLlTypeCoordonneeLatin() {
		return llTypeCoordonneeLatin;
	}

	/**
	 * [RefCoordonneeDto.llTypeCoordonneeLatin] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param llTypeCoordonneeLatin the llTypeCoordonneeLatin to set
	 */
	public void setLlTypeCoordonneeLatin(String llTypeCoordonneeLatin) {
		this.llTypeCoordonneeLatin = llTypeCoordonneeLatin;
	}

	/**
	 * [RefCoordonneeDto.idIndividu] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the idIndividu
	 */
	public Integer getIdIndividu() {
		return idIndividu;
	}

	/**
	 * [RefCoordonneeDto.idIndividu] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param idIndividu the idIndividu to set
	 */
	public void setIdIndividu(Integer idIndividu) {
		this.idIndividu = idIndividu;
	}

	
	/**
	 * [RefCoordonneeDto.idStructure] Getter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @return the idStructure
	 */
	public Integer getIdStructure() {
		return idStructure;
	}

	/**
	 * [RefCoordonneeDto.idStructure] Setter 
	 * @author MAKERRI Sofiane on : 15 janv. 2014  12:17:34
	 * @param idStructure the idStructure to set
	 */
	public void setIdStructure(Integer idStructure) {
		this.idStructure = idStructure;
	}

	public Integer getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(Integer idLieu) {
		this.idLieu = idLieu;
	}

	/**
	 * [RefCoordonneeDto.refEtablissement] Getter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  17:48:13
	 * @return the refEtablissement
	 */
	public Integer getIdEtablissement() {
		return idEtablissement;
	}

	/**
	 * [RefCoordonneeDto.refEtablissement] Setter 
	 * @author MAKERRI Sofiane on : 10 avr. 2014  17:48:13
	 * @param refEtablissement the refEtablissement to set
	 */
	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}

	/**
	 * [RefCoordonneeDto.typeCoordonnee] Getter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  08:59:36
	 * @return the typeCoordonnee
	 */
	public Integer getTypeCoordonnee() {
		return typeCoordonnee;
	}

	/**
	 * [RefCoordonneeDto.typeCoordonnee] Setter 
	 * @author MAKERRI Sofiane on : 6 mai 2014  08:59:36
	 * @param typeCoordonnee the typeCoordonnee to set
	 */
	public void setTypeCoordonnee(Integer typeCoordonnee) {
		this.typeCoordonnee = typeCoordonnee;
	}
	
	

}
