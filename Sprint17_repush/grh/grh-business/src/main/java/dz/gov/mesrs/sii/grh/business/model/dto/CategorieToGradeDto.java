package dz.gov.mesrs.sii.grh.business.model.dto;

public class CategorieToGradeDto extends
		dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CategorieProfessionnelleDto categorieProfessionnelle;

	private GradeDto grade;

	public CategorieToGradeDto() {
	}

	public CategorieToGradeDto(CategorieProfessionnelleDto categorie,
			GradeDto grade) {
		this.id.setEntityId1(categorie.getId());
		this.id.setEntityId2(grade.getId());

	}

	/**
	 * @return the categorieProfessionnelle
	 */
	public CategorieProfessionnelleDto getCategorieProfessionnelle() {
		return categorieProfessionnelle;
	}

	/**
	 * @param categorieProfessionnelle
	 *            the categorieProfessionnelle to set
	 */
	public void setCategorieProfessionnelle(
			CategorieProfessionnelleDto categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	/**
	 * @return the grade
	 */
	public GradeDto getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(GradeDto grade) {
		this.grade = grade;
	}

}
