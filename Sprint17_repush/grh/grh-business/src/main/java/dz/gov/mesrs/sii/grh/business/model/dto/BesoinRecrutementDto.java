package dz.gov.mesrs.sii.grh.business.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class BesoinRecrutementDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4314079070934717224L;
	private Integer id;
	private RefEtablissementDto refEtablissementDto;
	private NomenclatureDto nomenclatureByTypeRecrutementDto;
	private Date dateOuverture;
	private String objet;
	private NomenclatureDto nomenclatureByModeRecrutementDto;
	private Date dateDepot;
	private Date dateRecrutement;
	private Date datePublication;
	private String observation;
	private Boolean cloture;
	private Date dateCloture;
	private List<DetailRecrutementDto> detailRecrutementDtos;
	private SituationEntiteDto situationEntiteDto;

	public BesoinRecrutementDto() {
		refEtablissementDto = new RefEtablissementDto();
		nomenclatureByTypeRecrutementDto = new NomenclatureDto();
		nomenclatureByModeRecrutementDto = new NomenclatureDto();
	}

	public BesoinRecrutementDto(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateOuverture
	 */
	public Date getDateOuverture() {
		return dateOuverture;
	}

	/**
	 * @param dateOuverture
	 *            the dateOuverture to set
	 */
	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	/**
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * @param objet
	 *            the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}

	/**
	 * @return the dateDepot
	 */
	public Date getDateDepot() {
		return dateDepot;
	}

	/**
	 * @param dateDepot
	 *            the dateDepot to set
	 */
	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	/**
	 * @return the dateRecrutement
	 */
	public Date getDateRecrutement() {
		return dateRecrutement;
	}

	/**
	 * @param dateRecrutement
	 *            the dateRecrutement to set
	 */
	public void setDateRecrutement(Date dateRecrutement) {
		this.dateRecrutement = dateRecrutement;
	}

	/**
	 * @return the datePublication
	 */
	public Date getDatePublication() {
		return datePublication;
	}

	/**
	 * @param datePublication
	 *            the datePublication to set
	 */
	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}

	/**
	 * @return the cloture
	 */
	public Boolean getCloture() {
		return cloture;
	}

	/**
	 * @param cloture
	 *            the cloture to set
	 */
	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}

	/**
	 * @return the dateCloture
	 */
	public Date getDateCloture() {
		return dateCloture;
	}

	/**
	 * @param dateCloture
	 *            the dateCloture to set
	 */
	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public RefEtablissementDto getRefEtablissementDto() {
		return refEtablissementDto;
	}

	public void setRefEtablissementDto(RefEtablissementDto refEtablissementDto) {
		this.refEtablissementDto = refEtablissementDto;
	}

	public NomenclatureDto getNomenclatureByTypeRecrutementDto() {
		return nomenclatureByTypeRecrutementDto;
	}

	public void setNomenclatureByTypeRecrutementDto(NomenclatureDto nomenclatureByTypeRecrutementDto) {
		this.nomenclatureByTypeRecrutementDto = nomenclatureByTypeRecrutementDto;
	}

	public NomenclatureDto getNomenclatureByModeRecrutementDto() {
		return nomenclatureByModeRecrutementDto;
	}

	public void setNomenclatureByModeRecrutementDto(NomenclatureDto nomenclatureByModeRecrutementDto) {
		this.nomenclatureByModeRecrutementDto = nomenclatureByModeRecrutementDto;
	}

	public List<DetailRecrutementDto> getDetailRecrutementDtos() {
		return detailRecrutementDtos;
	}

	public void setDetailRecrutementDtos(List<DetailRecrutementDto> detailRecrutementDtos) {
		this.detailRecrutementDtos = detailRecrutementDtos;
	}

	/**
	 * [BesoinRecrutementDto.situationEntiteDto] Getter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 12:36:56
	 * @return the situationEntiteDto
	 */
	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	/**
	 * [BesoinRecrutementDto.situationEntiteDto] Setter
	 * 
	 * @author BELDI Jamel on : 18 déc. 2014 12:36:56
	 * @param situationEntiteDto
	 *            the situationEntiteDto to set
	 */
	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

}
