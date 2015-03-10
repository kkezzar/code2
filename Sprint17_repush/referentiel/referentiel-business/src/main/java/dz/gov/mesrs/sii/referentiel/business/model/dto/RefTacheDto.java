package dz.gov.mesrs.sii.referentiel.business.model.dto;

/**
 * @author BELDI Jamel on : 13 fevr. 2014 10:39:00
 */
public class RefTacheDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:07
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String titre;
	private String description;
	private Boolean active;

	// private RefEvenement evenement;
	private Integer evenementId;
	private String evenementObjet;
	
	// private Nomenclature nomenclatureByType;
	private Integer typeId;
	private String typeLibelleLongFr;
	private String typeLibelleLongAr;
	private String typeLibelleCourtFr;
	private String typeLibelleCourtAr;
	private String typeCode;

	public RefTacheDto() {
	}

	/**
	 * [RefTacheDto.id] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RefTacheDto.id] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [RefTacheDto.titre] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * [RefTacheDto.titre] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @param titre
	 *            the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * [RefTacheDto.description] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * [RefTacheDto.description] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * [RefTacheDto.active] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * [RefTacheDto.active] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:39:22
	 * @param active
	 *            the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	
	/**
	 * [RefTacheDto.typeId] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * [RefTacheDto.typeId] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * [RefTacheDto.typeLibelleLongFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeLibelleLongFr
	 */
	public String getTypeLibelleLongFr() {
		return typeLibelleLongFr;
	}

	/**
	 * [RefTacheDto.typeLibelleLongFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeLibelleLongFr
	 *            the typeLibelleLongFr to set
	 */
	public void setTypeLibelleLongFr(String typeLibelleLongFr) {
		this.typeLibelleLongFr = typeLibelleLongFr;
	}

	/**
	 * [RefTacheDto.typeLibelleLongAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeLibelleLongAr
	 */
	public String getTypeLibelleLongAr() {
		return typeLibelleLongAr;
	}

	/**
	 * [RefTacheDto.typeLibelleLongAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeLibelleLongAr
	 *            the typeLibelleLongAr to set
	 */
	public void setTypeLibelleLongAr(String typeLibelleLongAr) {
		this.typeLibelleLongAr = typeLibelleLongAr;
	}

	/**
	 * [RefTacheDto.typeLibelleCourtFr] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeLibelleCourtFr
	 */
	public String getTypeLibelleCourtFr() {
		return typeLibelleCourtFr;
	}

	/**
	 * [RefTacheDto.typeLibelleCourtFr] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeLibelleCourtFr
	 *            the typeLibelleCourtFr to set
	 */
	public void setTypeLibelleCourtFr(String typeLibelleCourtFr) {
		this.typeLibelleCourtFr = typeLibelleCourtFr;
	}

	/**
	 * [RefTacheDto.typeLibelleCourtAr] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeLibelleCourtAr
	 */
	public String getTypeLibelleCourtAr() {
		return typeLibelleCourtAr;
	}

	/**
	 * [RefTacheDto.typeLibelleCourtAr] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeLibelleCourtAr
	 *            the typeLibelleCourtAr to set
	 */
	public void setTypeLibelleCourtAr(String typeLibelleCourtAr) {
		this.typeLibelleCourtAr = typeLibelleCourtAr;
	}

	/**
	 * [RefTacheDto.typeCode] Getter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * [RefTacheDto.typeCode] Setter
	 * 
	 * @author BELDI Jamel on : 13 fevr. 2014 10:50:13
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * [RefTacheDto.evenementId] Getter 
	 * @author BELDI Jamel on : 13 fevr. 2014  11:10:47
	 * @return the evenementId
	 */
	public Integer getEvenementId() {
		return evenementId;
	}

	/**
	 * [RefTacheDto.evenementId] Setter 
	 * @author BELDI Jamel on : 13 fevr. 2014  11:10:47
	 * @param evenementId the evenementId to set
	 */
	public void setEvenementId(Integer evenementId) {
		this.evenementId = evenementId;
	}

	/**
	 * [RefTacheDto.evenementobjet] Getter 
	 * @author BELDI Jamel on : 13 fevr. 2014  11:10:47
	 * @return the evenementobjet
	 */
	public String getEvenementObjet() {
		return evenementObjet;
	}

	/**
	 * [RefTacheDto.evenementobjet] Setter 
	 * @author BELDI Jamel on : 13 fevr. 2014  11:10:47
	 * @param evenementobjet the evenementobjet to set
	 */
	public void setEvenementObjet(String evenementObjet) {
		this.evenementObjet = evenementObjet;
	}

	
	
}
