package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class PromotionEmployeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclature;
	private Date dateProposition;
	private Integer mois;
	private Integer annee;
	private String observation;
	private Set<PromotionProposeDto> promotionProposes = new HashSet<PromotionProposeDto>(
			0);

	public PromotionEmployeDto() {
	}

	public PromotionEmployeDto(Integer id) {
		this.id = id;
	}

	public PromotionEmployeDto(Integer id, NomenclatureDto nomenclature,
			Date dateProposition, Integer mois, Integer annee,
			String observation, Set<PromotionProposeDto> promotionProposes) {
		this.id = id;
		this.nomenclature = nomenclature;
		this.dateProposition = dateProposition;
		this.mois = mois;
		this.annee = annee;
		this.observation = observation;
		this.promotionProposes = promotionProposes;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nomenclature
	 */
	public NomenclatureDto getNomenclature() {
		return nomenclature;
	}

	/**
	 * @param nomenclature
	 *            the nomenclature to set
	 */
	public void setNomenclature(NomenclatureDto nomenclature) {
		this.nomenclature = nomenclature;
	}

	/**
	 * @return the dateProposition
	 */
	public Date getDateProposition() {
		return dateProposition;
	}

	/**
	 * @param dateProposition
	 *            the dateProposition to set
	 */
	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}

	/**
	 * @return the mois
	 */
	public Integer getMois() {
		return mois;
	}

	/**
	 * @param mois
	 *            the mois to set
	 */
	public void setMois(Integer mois) {
		this.mois = mois;
	}

	/**
	 * @return the annee
	 */
	public Integer getAnnee() {
		return annee;
	}

	/**
	 * @param annee
	 *            the annee to set
	 */
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the promotionProposes
	 */
	public Set<PromotionProposeDto> getPromotionProposes() {
		return promotionProposes;
	}

	/**
	 * @param promotionProposes
	 *            the promotionProposes to set
	 */
	public void setPromotionProposes(Set<PromotionProposeDto> promotionProposes) {
		this.promotionProposes = promotionProposes;
	}

}
