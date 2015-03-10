package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

/**
 * @author BELBRIK
 * 
 */
public class FiliereDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4782647772870785675L;
	private Integer id;
	private String identifiant;
	private NomenclatureDto nomenclatureByFiliereDto;
	private Double tauxservi;
	private Double montant;
	private RubriqueDto rubriqueDto;

	public FiliereDto() {
		super();
		nomenclatureByFiliereDto = new NomenclatureDto();
		rubriqueDto = new RubriqueDto();
	}

	/**
	 * [FiliereDto.id] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * [FiliereDto.id] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * [FiliereDto.tauxservi] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @return the tauxservi
	 */
	public Double getTauxservi() {
		return tauxservi;
	}

	/**
	 * [FiliereDto.tauxservi] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @param tauxservi the tauxservi to set
	 */
	public void setTauxservi(Double tauxservi) {
		this.tauxservi = tauxservi;
	}

	/**
	 * [FiliereDto.montant] Getter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @return the montant
	 */
	public Double getMontant() {
		return montant;
	}

	/**
	 * [FiliereDto.montant] Setter 
	 * @author BELBRIK Oualid on : 17 nov. 2014  18:07:33
	 * @param montant the montant to set
	 */
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	/**
	

	/**
	 * [FiliereDto.identifiant] Getter 
	 * @author BELBRIK Oualid on : 23 nov. 2014  16:54:22
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * [FiliereDto.rubrique] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  19:20:59
	 * @return the rubrique
	 */
	public RubriqueDto getRubriqueDto() {
		return rubriqueDto;
	}

	/**
	 * [FiliereDto.rubrique] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  19:20:59
	 * @param rubrique the rubrique to set
	 */
	public void setRubriqueDto(RubriqueDto rubriqueDto) {
		this.rubriqueDto = rubriqueDto;
	}

	/**
	 * [FiliereDto.identifiant] Setter 
	 * @author BELBRIK Oualid on : 23 nov. 2014  16:54:22
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * [FiliereDto.nomenclatureByFiliereDto] Getter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:28:05
	 * @return the nomenclatureByFiliereDto
	 */
	public NomenclatureDto getNomenclatureByFiliereDto() {
		return nomenclatureByFiliereDto;
	}

	/**
	 * [FiliereDto.nomenclatureByFiliereDto] Setter 
	 * @author BELBRIK Oualid on : 24 nov. 2014  17:28:05
	 * @param nomenclatureByFiliereDto the nomenclatureByFiliereDto to set
	 */
	public void setNomenclatureByFiliereDto(NomenclatureDto nomenclatureByFiliereDto) {
		this.nomenclatureByFiliereDto = nomenclatureByFiliereDto;
	}

	


	
}
