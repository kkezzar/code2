package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EnfantDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private NomenclatureDto nomenclatureByLienFiliationDto;
	private DossierEmployeDto dossierEmployeDto;
	private String nomLatin;
	private String prenomLatin;
	private String nomArabe;
	private String prenomArabe;
	private Boolean avecDifficulte;
	private Date dateNaissance;

	public EnfantDto() {
		nomenclatureByLienFiliationDto = new NomenclatureDto();
		dossierEmployeDto = new DossierEmployeDto();
	}

	public EnfantDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getNomenclatureByLienFiliationDto() {
		return nomenclatureByLienFiliationDto;
	}

	public void setNomenclatureByLienFiliationDto(NomenclatureDto nomenclatureByLienFiliationDto) {
		this.nomenclatureByLienFiliationDto = nomenclatureByLienFiliationDto;
	}

	public DossierEmployeDto getDossierEmployeDto() {
		return dossierEmployeDto;
	}

	public void setDossierEmployeDto(DossierEmployeDto dossierEmployeDto) {
		this.dossierEmployeDto = dossierEmployeDto;
	}

	public String getNomLatin() {
		return nomLatin;
	}

	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}

	public String getPrenomLatin() {
		return prenomLatin;
	}

	public void setPrenomLatin(String prenomLatin) {
		this.prenomLatin = prenomLatin;
	}

	public String getNomArabe() {
		return nomArabe;
	}

	public void setNomArabe(String nomArabe) {
		this.nomArabe = nomArabe;
	}

	public String getPrenomArabe() {
		return prenomArabe;
	}

	public void setPrenomArabe(String prenomArabe) {
		this.prenomArabe = prenomArabe;
	}

	public Boolean getAvecDifficulte() {
		return avecDifficulte;
	}

	public void setAvecDifficulte(Boolean avecDifficulte) {
		this.avecDifficulte = avecDifficulte;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
