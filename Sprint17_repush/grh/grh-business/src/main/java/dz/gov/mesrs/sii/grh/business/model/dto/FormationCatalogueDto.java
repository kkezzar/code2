package dz.gov.mesrs.sii.grh.business.model.dto;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 * 
 */

public class FormationCatalogueDto implements java.io.Serializable
		{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclatureByThemeDto;
	private NomenclatureDto nomenclatureByMethodeDto;
	private CatalogueFormationDto catalogueFormationDto;
	private String code;
	private String intitule;
	private String objectif;
	private Integer duree;
	private Integer maximunParticipant;

	public FormationCatalogueDto() {
	}

	public FormationCatalogueDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByThemeDto() {
		return nomenclatureByThemeDto;
	}

	public void setNomenclatureByThemeDto(NomenclatureDto nomenclatureByThemeDto) {
		this.nomenclatureByThemeDto = nomenclatureByThemeDto;
	}

	public NomenclatureDto getNomenclatureByMethodeDto() {
		return nomenclatureByMethodeDto;
	}

	public void setNomenclatureByMethodeDto(NomenclatureDto nomenclatureByMethodeDto) {
		this.nomenclatureByMethodeDto = nomenclatureByMethodeDto;
	}

	public CatalogueFormationDto getCatalogueFormationDto() {
		return catalogueFormationDto;
	}

	public void setCatalogueFormationDto(CatalogueFormationDto catalogueFormationDto) {
		this.catalogueFormationDto = catalogueFormationDto;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Integer getMaximunParticipant() {
		return maximunParticipant;
	}

	public void setMaximunParticipant(Integer maximunParticipant) {
		this.maximunParticipant = maximunParticipant;
	}

	
}
