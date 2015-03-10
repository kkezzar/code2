package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

/**
 * Classe de type DTO permet de représenter une atome pédagogique
 * 
 * @author Kheireddine OMRANI
 * 
 */
public class AtomePedagogiqueDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private Integer matiereConstitutiveId;
	private String mcLibelleFr;
	private Double volumeHoraire;
	private Double seuil;
	private Integer groupesPrevus;
	private Double pourcentage;
	private Integer ncTypeApId;
	private String ncTypeApLibelleLongFr;
	private String ncTypeApCode;
	private Integer ncModeEnseignementApId;
	private String ncModeEnseignementApLibelleLongFr;
	private String ncModeEnseignementApCode;

	public AtomePedagogiqueDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getSeuil() {
		return this.seuil;
	}

	public void setSeuil(Double seuil) {
		this.seuil = seuil;
	}

	public Double getVolumeHoraire() {
		return this.volumeHoraire;
	}

	public void setVolumeHoraire(Double volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	public Integer getGroupesPrevus() {
		return this.groupesPrevus;
	}

	public void setGroupesPrevus(Integer groupesPrevus) {
		this.groupesPrevus = groupesPrevus;
	}

	public Integer getMatiereConstitutiveId() {
		return matiereConstitutiveId;
	}

	public void setMatiereConstitutiveId(Integer matiereConstitutiveId) {
		this.matiereConstitutiveId = matiereConstitutiveId;
	}

	/**
	 * [AtomePedagogiqueDto.pourcentage] Getter
	 * 
	 * @author BELBRIK Oualid on : 16 sept. 2014 18:43:18
	 * @return the pourcentage
	 */
	public Double getPourcentage() {
		return pourcentage;
	}

	/**
	 * [AtomePedagogiqueDto.pourcentage] Setter
	 * 
	 * @author BELBRIK Oualid on : 16 sept. 2014 18:43:18
	 * @param pourcentage
	 *            the pourcentage to set
	 */
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	/**
	 * [AtomePedagogiqueDto.mcLibelleFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 16:20:33
	 * @return the mcLibelleFr
	 */
	public String getMcLibelleFr() {
		return mcLibelleFr;
	}

	/**
	 * [AtomePedagogiqueDto.mcLibelleFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 7 oct. 2014 16:20:33
	 * @param mcLibelleFr
	 *            the mcLibelleFr to set
	 */
	public void setMcLibelleFr(String mcLibelleFr) {
		this.mcLibelleFr = mcLibelleFr;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncTypeApId
	 */
	public Integer getNcTypeApId() {
		return ncTypeApId;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncTypeApId
	 *            the ncTypeApId to set
	 */
	public void setNcTypeApId(Integer ncTypeApId) {
		this.ncTypeApId = ncTypeApId;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncTypeApLibelleLongFr
	 */
	public String getNcTypeApLibelleLongFr() {
		return ncTypeApLibelleLongFr;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncTypeApLibelleLongFr
	 *            the ncTypeApLibelleLongFr to set
	 */
	public void setNcTypeApLibelleLongFr(String ncTypeApLibelleLongFr) {
		this.ncTypeApLibelleLongFr = ncTypeApLibelleLongFr;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncTypeApCode
	 */
	public String getNcTypeApCode() {
		return ncTypeApCode;
	}

	/**
	 * [AtomePedagogiqueDto.ncTypeApCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncTypeApCode
	 *            the ncTypeApCode to set
	 */
	public void setNcTypeApCode(String ncTypeApCode) {
		this.ncTypeApCode = ncTypeApCode;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncModeEnseignementApId
	 */
	public Integer getNcModeEnseignementApId() {
		return ncModeEnseignementApId;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncModeEnseignementApId
	 *            the ncModeEnseignementApId to set
	 */
	public void setNcModeEnseignementApId(Integer ncModeEnseignementApId) {
		this.ncModeEnseignementApId = ncModeEnseignementApId;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApLibelleLongFr] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncModeEnseignementApLibelleLongFr
	 */
	public String getNcModeEnseignementApLibelleLongFr() {
		return ncModeEnseignementApLibelleLongFr;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApLibelleLongFr] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncModeEnseignementApLibelleLongFr
	 *            the ncModeEnseignementApLibelleLongFr to set
	 */
	public void setNcModeEnseignementApLibelleLongFr(String ncModeEnseignementApLibelleLongFr) {
		this.ncModeEnseignementApLibelleLongFr = ncModeEnseignementApLibelleLongFr;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApCode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @return the ncModeEnseignementApCode
	 */
	public String getNcModeEnseignementApCode() {
		return ncModeEnseignementApCode;
	}

	/**
	 * [AtomePedagogiqueDto.ncModeEnseignementApCode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 23 nov. 2014 12:20:15
	 * @param ncModeEnseignementApCode
	 *            the ncModeEnseignementApCode to set
	 */
	public void setNcModeEnseignementApCode(String ncModeEnseignementApCode) {
		this.ncModeEnseignementApCode = ncModeEnseignementApCode;
	}

}
