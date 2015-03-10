package dz.gov.mesrs.sii.grh.business.model.dto;


import java.util.Date;

public class PromotionProposeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private GradeDto grade;
	private PromotionEmployeDto promotionEmploye;
	private DossierEmployeDto dossierEmploye;
	private Date dateEffet;
	private Date dateRetenue;
	private Date dateDecesion;

	public PromotionProposeDto() {
	}

	public PromotionProposeDto(Integer id) {
		this.id = id;
	}

	public PromotionProposeDto(Integer id, GradeDto grade,
			PromotionEmployeDto promotionEmploye, DossierEmployeDto dossierEmploye,
			Date dateEffet, Date dateRetenue, Date dateDecesion) {
		this.id = id;
		this.grade = grade;
		this.promotionEmploye = promotionEmploye;
		this.dossierEmploye = dossierEmploye;
		this.dateEffet = dateEffet;
		this.dateRetenue = dateRetenue;
		this.dateDecesion = dateDecesion;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the grade
	 */
	public GradeDto getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(GradeDto grade) {
		this.grade = grade;
	}

	/**
	 * @return the promotionEmploye
	 */
	public PromotionEmployeDto getPromotionEmploye() {
		return promotionEmploye;
	}

	/**
	 * @param promotionEmploye the promotionEmploye to set
	 */
	public void setPromotionEmploye(PromotionEmployeDto promotionEmploye) {
		this.promotionEmploye = promotionEmploye;
	}

	/**
	 * @return the dossierEmploye
	 */
	public DossierEmployeDto getDossierEmploye() {
		return dossierEmploye;
	}

	/**
	 * @param dossierEmploye the dossierEmploye to set
	 */
	public void setDossierEmploye(DossierEmployeDto dossierEmploye) {
		this.dossierEmploye = dossierEmploye;
	}

	/**
	 * @return the dateEffet
	 */
	public Date getDateEffet() {
		return dateEffet;
	}

	/**
	 * @param dateEffet the dateEffet to set
	 */
	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	/**
	 * @return the dateRetenue
	 */
	public Date getDateRetenue() {
		return dateRetenue;
	}

	/**
	 * @param dateRetenue the dateRetenue to set
	 */
	public void setDateRetenue(Date dateRetenue) {
		this.dateRetenue = dateRetenue;
	}

	/**
	 * @return the dateDecesion
	 */
	public Date getDateDecesion() {
		return dateDecesion;
	}

	/**
	 * @param dateDecesion the dateDecesion to set
	 */
	public void setDateDecesion(Date dateDecesion) {
		this.dateDecesion = dateDecesion;
	}

	
}
