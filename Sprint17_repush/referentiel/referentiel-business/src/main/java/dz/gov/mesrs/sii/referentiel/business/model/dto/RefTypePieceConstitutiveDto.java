/**
 * [dz.gov.mesrs.sii.referentiel.business.model.dto.RefTypePieceConstitutiveDto.java] 
 * @author MAKERRI Sofiane on : 20 mai 2014  15:37:22
 */
package dz.gov.mesrs.sii.referentiel.business.model.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author MAKERRI Sofiane  on : 20 mai 2014  15:37:22
 */
@XmlRootElement(name = "RefTypePieceConstitutiveDto")
public class RefTypePieceConstitutiveDto implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 20 mai 2014  15:37:37
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codeTypeDossier;
	private Integer idTypeDossier;
	private String ncLlLatinTypeDossier;
	private String ncLlArabeTypeDossier;
	private String ncLcLatinTypeDossier;
	private String ncLlLatinTypePiece;
	private String ncLlArabeTypePiece;
	private String ncLcLatinTypePiece;
	private String codeTypePiece;
	private Integer idTypePiece;
	private Integer rang;
	private Date dateDebut;
	private Date dateFin;
	private Boolean obligatoire;
	private Boolean aFournir;
	private Integer nombre;

	/**
	 * [RefTypePieceConstitutiveDto.RefTypePieceConstitutiveDto()] Constructor
	 * @author MAKERRI Sofiane  on : 20 mai 2014  15:37:22	
	 */
	public RefTypePieceConstitutiveDto() {
		super();
	}

	/**
	 * [RefTypePieceConstitutiveDto.id] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefTypePieceConstitutiveDto.id] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlLatinTypeDossier] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the ncLlLatinTypeDossier
	 */
	public String getNcLlLatinTypeDossier() {
		return ncLlLatinTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlLatinTypeDossier] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param ncLlLatinTypeDossier the ncLlLatinTypeDossier to set
	 */
	public void setNcLlLatinTypeDossier(String ncLlLatinTypeDossier) {
		this.ncLlLatinTypeDossier = ncLlLatinTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLcLatinTypeDossier] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the ncLcLatinTypeDossier
	 */
	public String getNcLcLatinTypeDossier() {
		return ncLcLatinTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLcLatinTypeDossier] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param ncLcLatinTypeDossier the ncLcLatinTypeDossier to set
	 */
	public void setNcLcLatinTypeDossier(String ncLcLatinTypeDossier) {
		this.ncLcLatinTypeDossier = ncLcLatinTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlLatinTypePiece] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the ncLlLatinTypePiece
	 */
	public String getNcLlLatinTypePiece() {
		return ncLlLatinTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlLatinTypePiece] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param ncLlLatinTypePiece the ncLlLatinTypePiece to set
	 */
	public void setNcLlLatinTypePiece(String ncLlLatinTypePiece) {
		this.ncLlLatinTypePiece = ncLlLatinTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLcLatinTypePiece] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the ncLcLatinTypePiece
	 */
	public String getNcLcLatinTypePiece() {
		return ncLcLatinTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLcLatinTypePiece] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param ncLcLatinTypePiece the ncLcLatinTypePiece to set
	 */
	public void setNcLcLatinTypePiece(String ncLcLatinTypePiece) {
		this.ncLcLatinTypePiece = ncLcLatinTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.rang] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the rang
	 */
	public Integer getRang() {
		return rang;
	}

	/**
	 * [RefTypePieceConstitutiveDto.rang] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param rang the rang to set
	 */
	public void setRang(Integer rang) {
		this.rang = rang;
	}

	/**
	 * [RefTypePieceConstitutiveDto.dateDebut] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * [RefTypePieceConstitutiveDto.dateDebut] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * [RefTypePieceConstitutiveDto.dateFin] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * [RefTypePieceConstitutiveDto.dateFin] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * [RefTypePieceConstitutiveDto.obligatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the obligatoire
	 */
	public Boolean getObligatoire() {
		return obligatoire;
	}

	/**
	 * [RefTypePieceConstitutiveDto.obligatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param obligatoire the obligatoire to set
	 */
	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	/**
	 * [RefTypePieceConstitutiveDto.aFournir] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the aFournir
	 */
	public Boolean getAFournir() {
		return aFournir;
	}

	/**
	 * [RefTypePieceConstitutiveDto.aFournir] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param aFournir the aFournir to set
	 */
	public void setAFournir(Boolean aFournir) {
		this.aFournir = aFournir;
	}

	/**
	 * [RefTypePieceConstitutiveDto.nombre] Getter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @return the nombre
	 */
	public Integer getNombre() {
		return nombre;
	}

	/**
	 * [RefTypePieceConstitutiveDto.nombre] Setter 
	 * @author MAKERRI Sofiane on : 20 mai 2014  15:39:30
	 * @param nombre the nombre to set
	 */
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

	/**
	 * [RefTypePieceConstitutiveDto.codeTypeDossier] Getter 
	 * @author MAKERRI Sofiane on : 21 mai 2014  17:04:19
	 * @return the codeTypeDossier
	 */
	public String getCodeTypeDossier() {
		return codeTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.codeTypeDossier] Setter 
	 * @author MAKERRI Sofiane on : 21 mai 2014  17:04:19
	 * @param codeTypeDossier the codeTypeDossier to set
	 */
	public void setCodeTypeDossier(String codeTypeDossier) {
		this.codeTypeDossier = codeTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.codeTypePiece] Getter 
	 * @author MAKERRI Sofiane on : 21 mai 2014  17:05:35
	 * @return the codeTypePiece
	 */
	public String getCodeTypePiece() {
		return codeTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.codeTypePiece] Setter 
	 * @author MAKERRI Sofiane on : 21 mai 2014  17:05:35
	 * @param codeTypePiece the codeTypePiece to set
	 */
	public void setCodeTypePiece(String codeTypePiece) {
		this.codeTypePiece = codeTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlArabeTypeDossier] Getter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  12:30:09
	 * @return the ncLlArabeTypeDossier
	 */
	public String getNcLlArabeTypeDossier() {
		return ncLlArabeTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlArabeTypeDossier] Setter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  12:30:09
	 * @param ncLlArabeTypeDossier the ncLlArabeTypeDossier to set
	 */
	public void setNcLlArabeTypeDossier(String ncLlArabeTypeDossier) {
		this.ncLlArabeTypeDossier = ncLlArabeTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlArabeTypePiece] Getter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  12:41:15
	 * @return the ncLlArabeTypePiece
	 */
	public String getNcLlArabeTypePiece() {
		return ncLlArabeTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.ncLlArabeTypePiece] Setter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  12:41:15
	 * @param ncLlArabeTypePiece the ncLlArabeTypePiece to set
	 */
	public void setNcLlArabeTypePiece(String ncLlArabeTypePiece) {
		this.ncLlArabeTypePiece = ncLlArabeTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.idTypeDossier] Getter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  15:57:10
	 * @return the idTypeDossier
	 */
	public Integer getIdTypeDossier() {
		return idTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.idTypeDossier] Setter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  15:57:10
	 * @param idTypeDossier the idTypeDossier to set
	 */
	public void setIdTypeDossier(Integer idTypeDossier) {
		this.idTypeDossier = idTypeDossier;
	}

	/**
	 * [RefTypePieceConstitutiveDto.idTypePiece] Getter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  16:13:31
	 * @return the idTypePiece
	 */
	public Integer getIdTypePiece() {
		return idTypePiece;
	}

	/**
	 * [RefTypePieceConstitutiveDto.idTypePiece] Setter 
	 * @author MAKERRI Sofiane on : 25 mai 2014  16:13:31
	 * @param idTypePiece the idTypePiece to set
	 */
	public void setIdTypePiece(Integer idTypePiece) {
		this.idTypePiece = idTypePiece;
	}

	
	
	

}
