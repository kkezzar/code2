package dz.gov.mesrs.sii.grh.business.model.dto;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

/**
 * 
 * @author BELDI Jamel on : 2 déc. 2014 09:50:44
 */
public class DiplomeEmployeDto implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 2 déc. 2014 09:50:48
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclatureByTypeDiplomeDto;
	private RefEtablissementDto refEtablissementDto;
	private String intitule;
	private Integer anneeObtention;
	private DossierEmployeDto dossierEmployeDto;

	public DiplomeEmployeDto() {
		super();
		nomenclatureByTypeDiplomeDto = new NomenclatureDto();
		refEtablissementDto = new RefEtablissementDto();
	}

	public DiplomeEmployeDto(int id, NomenclatureDto nomenclatureByTypeDiplomeDto,
			RefEtablissementDto refEtablissementDto, String intitule, int anneeObtention) {
		super();
		this.id = id;
		this.nomenclatureByTypeDiplomeDto = nomenclatureByTypeDiplomeDto;
		this.refEtablissementDto = refEtablissementDto;
		this.intitule = intitule;
		this.anneeObtention = anneeObtention;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByTypeDiplomeDto() {
		return nomenclatureByTypeDiplomeDto;
	}

	public void setNomenclatureByTypeDiplomeDto(NomenclatureDto nomenclatureByTypeDiplomeDto) {
		this.nomenclatureByTypeDiplomeDto = nomenclatureByTypeDiplomeDto;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Integer getAnneeObtention() {
		return anneeObtention;
	}

	public void setAnneeObtention(Integer anneeObtention) {
		this.anneeObtention = anneeObtention;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

}
