package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
/**
 * @author BELBRIK
 * 
 */
public class RubriqueDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4782647772870785675L;
	private Integer id;
	private NomenclatureDto nomenclatureByRubriqueDto;
	private NomenclatureDto nomenclatureByTypeRubriqueDto;
	private NomenclatureDto nomenclatureByPeriodiciteDto;
	private String decret;
	private Double taux;
	private Double montant;
	private Boolean commune;
	private Boolean soumise_securite;
	private Boolean TauxOuMantant;
	private String identifiant;
	private List<FiliereDto> filiereDtos;
	public RubriqueDto() {
		super();
		nomenclatureByRubriqueDto = new NomenclatureDto();
		nomenclatureByTypeRubriqueDto = new NomenclatureDto();
		nomenclatureByPeriodiciteDto = new NomenclatureDto();
	}

	/**
	 * [RubriqueDto.id] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [RubriqueDto.id] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	

	/**
	 * [RubriqueDto.decret] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the decret
	 */
	public String getDecret() {
		return decret;
	}

	/**
	 * [RubriqueDto.decret] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param decret the decret to set
	 */
	public void setDecret(String decret) {
		this.decret = decret;
	}

	/**
	 * [RubriqueDto.taux] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * [RubriqueDto.taux] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param taux the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}

	/**
	 * [RubriqueDto.montant] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * [RubriqueDto.montant] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	 * [RubriqueDto.commune] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the commune
	 */
	public Boolean getCommune() {
		return commune;
	}

	/**
	 * [RubriqueDto.commune] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param commune the commune to set
	 */
	public void setCommune(Boolean commune) {
		this.commune = commune;
	}

	/**
	 * [RubriqueDto.soumise_securite] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @return the soumise_securite
	 */
	public Boolean getSoumise_securite() {
		return soumise_securite;
	}

	/**
	 * [RubriqueDto.soumise_securite] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  16:29:36
	 * @param soumise_securite the soumise_securite to set
	 */
	public void setSoumise_securite(Boolean soumise_securite) {
		this.soumise_securite = soumise_securite;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public List<FiliereDto> getFiliereDtos() {
		return filiereDtos;
	}

	public void setFiliereDtos(List<FiliereDto> filiereDtos) {
		this.filiereDtos = filiereDtos;
	}

	/**
	 * [RubriqueDto.nomenclatureByRubriqueDto] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @return the nomenclatureByRubriqueDto
	 */
	public NomenclatureDto getNomenclatureByRubriqueDto() {
		return nomenclatureByRubriqueDto;
	}

	/**
	 * [RubriqueDto.nomenclatureByRubriqueDto] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @param nomenclatureByRubriqueDto the nomenclatureByRubriqueDto to set
	 */
	public void setNomenclatureByRubriqueDto(
			NomenclatureDto nomenclatureByRubriqueDto) {
		this.nomenclatureByRubriqueDto = nomenclatureByRubriqueDto;
	}

	/**
	 * [RubriqueDto.nomenclatureByTypeRubriqueDto] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @return the nomenclatureByTypeRubriqueDto
	 */
	public NomenclatureDto getNomenclatureByTypeRubriqueDto() {
		return nomenclatureByTypeRubriqueDto;
	}

	/**
	 * [RubriqueDto.nomenclatureByTypeRubriqueDto] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @param nomenclatureByTypeRubriqueDto the nomenclatureByTypeRubriqueDto to set
	 */
	public void setNomenclatureByTypeRubriqueDto(
			NomenclatureDto nomenclatureByTypeRubriqueDto) {
		this.nomenclatureByTypeRubriqueDto = nomenclatureByTypeRubriqueDto;
	}

	/**
	 * [RubriqueDto.nomenclatureByPeriodiciteDto] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @return the nomenclatureByPeriodiciteDto
	 */
	public NomenclatureDto getNomenclatureByPeriodiciteDto() {
		return nomenclatureByPeriodiciteDto;
	}

	/**
	 * [RubriqueDto.nomenclatureByPeriodiciteDto] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:34:45
	 * @param nomenclatureByPeriodiciteDto the nomenclatureByPeriodiciteDto to set
	 */
	public void setNomenclatureByPeriodiciteDto(
			NomenclatureDto nomenclatureByPeriodiciteDto) {
		this.nomenclatureByPeriodiciteDto = nomenclatureByPeriodiciteDto;
	}

	public Boolean getTauxOuMantant() {
		return TauxOuMantant;
	}

	public void setTauxOuMantant(Boolean tauxOuMantant) {
		TauxOuMantant = tauxOuMantant;
	}

	
	
	
}
