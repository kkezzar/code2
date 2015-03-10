package dz.gov.mesrs.sii.grh.business.model.dto;



import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class MedicamentDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private NomenclatureDto nomenclatureDto;
	private VisiteMedicaleDto visiteMedicaleDto;
	private String posologie;
	private Integer nbJour;

	public MedicamentDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureDto() {
		return nomenclatureDto;
	}

	public void setNomenclatureDto(NomenclatureDto nomenclatureDto) {
		this.nomenclatureDto = nomenclatureDto;
	}

	public VisiteMedicaleDto getVisiteMedicaleDto() {
		return visiteMedicaleDto;
	}

	public void setVisiteMedicaleDto(VisiteMedicaleDto visiteMedicaleDto) {
		this.visiteMedicaleDto = visiteMedicaleDto;
	}

	public String getPosologie() {
		return posologie;
	}

	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}

	public Integer getNbJour() {
		return nbJour;
	}

	public void setNbJour(Integer nbJour) {
		this.nbJour = nbJour;
	}

	
	
	
	
	
}
