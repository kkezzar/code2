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
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

@Entity
@Table(name = "niveau_categorie")
@AttributeOverrides({
		@AttributeOverride(name = "id.entityId1", column = @Column(name = "id_categorie_professionnelle")),
		@AttributeOverride(name = "id.entityId2", column = @Column(name = "id_niveau")) })
public class CategorieToNiveau extends ModelBaseRelationship implements Identifiable<ModelBaseRelationship.Id> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = CategorieProfessionnelle.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categorie_professionnelle", insertable = false, updatable = false)
	private CategorieProfessionnelle categorieProfessionnelle;

	@ManyToOne(targetEntity = Nomenclature.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_niveau", insertable = false, updatable = false)
	private Nomenclature niveau;

	public CategorieToNiveau() {
	}

	public CategorieToNiveau(CategorieProfessionnelle categorie, Nomenclature niveau) {
		this.id.setEntityId1(categorie.getId());
		this.id.setEntityId2(niveau.getId());

	}

	/**
	 * @return the categorieProfessionnelle
	 */
	public CategorieProfessionnelle getCategorieProfessionnelle() {
		return categorieProfessionnelle;
	}

	/**
	 * @param categorieProfessionnelle
	 *            the categorieProfessionnelle to set
	 */
	public void setCategorieProfessionnelle(CategorieProfessionnelle categorieProfessionnelle) {
		this.categorieProfessionnelle = categorieProfessionnelle;
	}

	/**
	 * @return the niveau
	 */
	public Nomenclature getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau
	 *            the niveau to set
	 */
	public void setNiveau(Nomenclature niveau) {
		this.niveau = niveau;
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
