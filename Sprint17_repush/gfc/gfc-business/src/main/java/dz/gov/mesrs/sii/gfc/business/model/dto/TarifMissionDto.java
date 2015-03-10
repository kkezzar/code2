package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class TarifMissionDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private NomenclatureDto typeTarif;
	private BigDecimal fraisRestauration;
	private BigDecimal fraisHebergement;
	private BigDecimal totalJournalier;

	private CategorieProfessionnelleDto categorieMin;
	private CategorieProfessionnelleDto categorieMax;

	private Boolean fonctionSuperieure;
	private Boolean missionEtrange;

	private Boolean actif;
	private String observation;
	

	private List<DossierMissionDto> dossierMissions = new ArrayList<DossierMissionDto>(0);

	private SituationEntiteDto situation;
	private String labelTarif;
	

	public TarifMissionDto() {
		typeTarif=new NomenclatureDto();
		categorieMin=new CategorieProfessionnelleDto();
		categorieMax= new CategorieProfessionnelleDto();
		situation = new SituationEntiteDto();
	}

	public TarifMissionDto(Integer id, NomenclatureDto typeTarif, BigDecimal fraisRestauration,
			BigDecimal fraisHebergement, BigDecimal totalJournalier, boolean actif) {
		this.id = id;
		this.typeTarif = typeTarif;
		this.fraisRestauration = fraisRestauration;
		this.fraisHebergement = fraisHebergement;
		this.totalJournalier = totalJournalier;
		this.actif = actif;
	}

	public TarifMissionDto(Integer id, NomenclatureDto typeTarif, BigDecimal fraisRestauration,
			BigDecimal fraisHebergement, BigDecimal totalJournalier, CategorieProfessionnelleDto categorieMin,
			CategorieProfessionnelleDto categorieMax, Boolean fonctionSuperieure, Boolean missionEtrange, Boolean actif,
			String observation, SituationEntiteDto situation, List<DossierMissionDto> dossierMissions) {
		this.id = id;
		this.typeTarif = typeTarif;
		this.fraisRestauration = fraisRestauration;
		this.fraisHebergement = fraisHebergement;
		this.totalJournalier = totalJournalier;
		this.categorieMin = categorieMin;
		this.categorieMax = categorieMax;
		this.fonctionSuperieure = fonctionSuperieure;
		this.missionEtrange = missionEtrange;
		this.actif = actif;
		this.observation = observation;
		this.situation = situation;
		this.dossierMissions = dossierMissions;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getTypeTarif() {
		return this.typeTarif;
	}

	public void setTypeTarif(NomenclatureDto typeTarif) {
		this.typeTarif = typeTarif;
	}

	public BigDecimal getFraisRestauration() {
		return this.fraisRestauration;
	}

	public void setFraisRestauration(BigDecimal fraisRestauration) {
		this.fraisRestauration = fraisRestauration;
	}

	public BigDecimal getFraisHebergement() {
		return this.fraisHebergement;
	}

	public void setFraisHebergement(BigDecimal fraisHebergement) {
		this.fraisHebergement = fraisHebergement;
	}

	public BigDecimal getTotalJournalier() {
		return this.totalJournalier;
	}

	public void setTotalJournalier(BigDecimal totalJournalier) {
		this.totalJournalier = totalJournalier;
	}

	

	public Boolean getFonctionSuperieure() {
		return this.fonctionSuperieure;
	}

	public void setFonctionSuperieure(Boolean fonctionSuperieure) {
		this.fonctionSuperieure = fonctionSuperieure;
	}

	public Boolean getMissionEtrange() {
		return this.missionEtrange;
	}

	public void setMissionEtrange(Boolean missionEtrange) {
		this.missionEtrange = missionEtrange;
	}

	public Boolean getActif() {
		return this.actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
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

	public List<DossierMissionDto> getDossierMissions() {
		return this.dossierMissions;
	}

	public void setDossierMissions(List<DossierMissionDto> dossierMissions) {
		this.dossierMissions = dossierMissions;
	}

	/**
	 * @return the categorieMin
	 */
	public CategorieProfessionnelleDto getCategorieMin() {
		return categorieMin;
	}

	/**
	 * @param categorieMin the categorieMin to set
	 */
	public void setCategorieMin(CategorieProfessionnelleDto categorieMin) {
		this.categorieMin = categorieMin;
	}

	/**
	 * @return the categorieMax
	 */
	public CategorieProfessionnelleDto getCategorieMax() {
		return categorieMax;
	}

	/**
	 * @param categorieMax the categorieMax to set
	 */
	public void setCategorieMax(CategorieProfessionnelleDto categorieMax) {
		this.categorieMax = categorieMax;
	}

	/**
	 * @return the labelTarif
	 */
	public String getLabelTarif() {
		return labelTarif;
	}

	/**
	 * @param labelTarif the labelTarif to set
	 */
	public void setLabelTarif(String labelTarif) {
		this.labelTarif = labelTarif;
	}
	
}