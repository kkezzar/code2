package dz.gov.mesrs.sii.commons.data.model.grh;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.ModelBaseRelationship;


@Entity
@Table(name="categorie_grade")
@AttributeOverrides({
		@AttributeOverride(name="id.entityId1", column=@Column(name="id_categorie_professionnelle")),
		@AttributeOverride(name="id.entityId2", column=@Column(name="id_grade"))
})
public class CategorieToGrade  extends ModelBaseRelationship  implements Identifiable<ModelBaseRelationship.Id>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(targetEntity=CategorieProfessionnelle.class, fetch=FetchType.LAZY)
	@JoinColumn(name="id_categorie_professionnelle",  insertable=false, updatable=false)
	private CategorieProfessionnelle categorieProfessionnelle;
	
	@ManyToOne(targetEntity=Grade.class, fetch=FetchType.LAZY)
	@JoinColumn(name="id_grade",  insertable=false, updatable=false)
	private Grade grade;
	
	
	
	
	public CategorieToGrade(){		
	}
	
	public CategorieToGrade(CategorieProfessionnelle categorie, Grade grade){
		this.id.setEntityId1(categorie.getId());
		this.id.setEntityId2(grade.getId());
		
	
	}

	/**
	 * @return the categorieProfessionnelle
	 */
	public CategorieProfessionnelle getCategorieProfessionnelle() {
		return categorieProfessionnelle;
	}

	/**
	 * @param categorieProfessionnelle the categorieProfessionnelle to set
	 */
	public void setCategorieProfessionnelle(
			CategorieProfessionnelle categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	/**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	@Transient
	@Override
	public Id getIdenfiant() {
		return this.getId();
	}
	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}
	

}
