package dz.gov.mesrs.sii.grh.business.model.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author BELDI Jamel
 * 
 */

public class CatalogueFormationDto implements java.io.Serializable
		 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dateCreation;
	private String objet;
	private Boolean statut;
	private List<FormationCatalogueDto> formationCatalogueDtos ;
	public CatalogueFormationDto() {
	}

	public CatalogueFormationDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Boolean getStatut() {
		return statut;
	}

	public void setStatut(Boolean statut) {
		this.statut = statut;
	}

	public List<FormationCatalogueDto> getFormationCatalogueDtos() {
		return formationCatalogueDtos;
	}

	public void setFormationCatalogueDtos(
			List<FormationCatalogueDto> formationCatalogueDtos) {
		this.formationCatalogueDtos = formationCatalogueDtos;
	}

	
}
