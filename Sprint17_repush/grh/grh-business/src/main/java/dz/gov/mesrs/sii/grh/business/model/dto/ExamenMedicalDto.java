package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class ExamenMedicalDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private NomenclatureDto nomenclatureDto;
	private VisiteMedicaleDto visiteMedicaleDto;
	private Date dateExamen;
	private String resume;
	
	public ExamenMedicalDto() {
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

	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	
	
	
	
	
}
