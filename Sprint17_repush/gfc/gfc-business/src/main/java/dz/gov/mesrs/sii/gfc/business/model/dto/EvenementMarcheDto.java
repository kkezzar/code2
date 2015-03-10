package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class EvenementMarcheDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private MarcheDto marche;
	private Date dateEvenement;
	private NomenclatureDto type;
	private String description;
	private String impact;
	private String mesurePrise;
	private String observation;
	private SituationEntiteDto situation;

	public EvenementMarcheDto() {
		type = new NomenclatureDto();

	}

	public EvenementMarcheDto(Integer id, MarcheDto marche, Date dateEvenement, NomenclatureDto type) {
		this.id = id;
		this.marche = marche;
		this.dateEvenement = dateEvenement;
		this.type = type;
	}

	public EvenementMarcheDto(Integer id, MarcheDto marche, Date dateEvenement, NomenclatureDto type,
			String description, String impact, String mesurePrise, String observation, SituationEntiteDto situation) {
		this.id = id;
		this.marche = marche;
		this.dateEvenement = dateEvenement;
		this.type = type;
		this.description = description;
		this.impact = impact;
		this.mesurePrise = mesurePrise;
		this.observation = observation;
		this.situation = situation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MarcheDto getMarche() {
		return this.marche;
	}

	public void setMarche(MarcheDto marche) {
		this.marche = marche;
	}

	public Date getDateEvenement() {
		return this.dateEvenement;
	}

	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}

	public NomenclatureDto getType() {
		return this.type;
	}

	public void setType(NomenclatureDto type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImpact() {
		return this.impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getMesurePrise() {
		return this.mesurePrise;
	}

	public void setMesurePrise(String mesurePrise) {
		this.mesurePrise = mesurePrise;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

}