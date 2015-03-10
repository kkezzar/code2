package dz.gov.mesrs.sii.recherche.business.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.commons.data.model.recherche.EtapeValidation;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class ProjetDto implements Serializable {

	/**
	 * serialVersionUID 
	 * @author rlaib  on : 28 janv. 2015  10:54:51
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;
	private String intituleFr;
	private String intituleAr;
	private Date dateDebutReel;
	private Date dateFinReel;
	private Date dateDebutPrev;
	private Date dateFinPrev;
	private String objectif;
	private String justification;
	private Date dateCloture;
	private String observation;
	private Date dateIdentification;
	private SituationEntiteDto situationDto;
	private NomenclatureDto typeClotureDto;
	private ProgrammeDto programeDto;
	private RefEtablissementDto etablissementDto;
	private StructureDto  structureDto;
	private List<MotcleDto> motclesDto;
	private List<ActiviteProjetDto> activiteProjetsDto;
	private ThemeDto themeDto;
	private GroupeDto groupeDto;
	private List<IndEvaluationDto> indEvaluationsDto;
	private List<ProjetPartenaireDto> projetPartenairesDto;
	private List<EtapeValidationDto>  etapeValidationsDto;
	
	public ProjetDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getIntituleFr() {
		return intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	public String getIntituleAr() {
		return intituleAr;
	}


	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}


	public Date getDateDebutReel() {
		return dateDebutReel;
	}


	public void setDateDebutReel(Date dateDebutReel) {
		this.dateDebutReel = dateDebutReel;
	}



	public Date getDateFinReel() {
		return dateFinReel;
	}



	public void setDateFinReel(Date dateFinReel) {
		this.dateFinReel = dateFinReel;
	}



	public Date getDateDebutPrev() {
		return dateDebutPrev;
	}



	public void setDateDebutPrev(Date dateDebutPrev) {
		this.dateDebutPrev = dateDebutPrev;
	}



	public Date getDateFinPrev() {
		return dateFinPrev;
	}



	public void setDateFinPrev(Date dateFinPrev) {
		this.dateFinPrev = dateFinPrev;
	}



	public String getObjectif() {
		return objectif;
	}



	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}



	public String getJustification() {
		return justification;
	}



	public void setJustification(String justification) {
		this.justification = justification;
	}



	public Date getDateCloture() {
		return dateCloture;
	}



	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}



	public String getObservation() {
		return observation;
	}



	public void setObservation(String observation) {
		this.observation = observation;
	}



	public Date getDateIdentification() {
		return dateIdentification;
	}



	public void setDateIdentification(Date dateIdentification) {
		this.dateIdentification = dateIdentification;
	}



	public SituationEntiteDto getSituationDto() {
		return situationDto;
	}



	public void setSituationDto(SituationEntiteDto situationDto) {
		this.situationDto = situationDto;
	}



	public NomenclatureDto getTypeClotureDto() {
		return typeClotureDto;
	}



	public void setTypeClotureDto(NomenclatureDto typeClotureDto) {
		this.typeClotureDto = typeClotureDto;
	}



	public ProgrammeDto getProgrameDto() {
		return programeDto;
	}



	public void setProgrameDto(ProgrammeDto programeDto) {
		this.programeDto = programeDto;
	}



	public RefEtablissementDto getEtablissementDto() {
		return etablissementDto;
	}



	public void setEtablissementDto(RefEtablissementDto etablissementDto) {
		this.etablissementDto = etablissementDto;
	}



	
	public StructureDto getStructureDto() {
		return structureDto;
	}

	public void setStructureDto(StructureDto structureDto) {
		this.structureDto = structureDto;
	}

	public List<MotcleDto> getMotclesDto() {
		return motclesDto;
	}



	public void setMotclesDto(List<MotcleDto> motclesDto) {
		this.motclesDto = motclesDto;
	}



	public List<ActiviteProjetDto> getActiviteProjetsDto() {
		return activiteProjetsDto;
	}



	public void setActiviteProjetsDto(List<ActiviteProjetDto> activiteProjetsDto) {
		this.activiteProjetsDto = activiteProjetsDto;
	}



	public ThemeDto getThemeDto() {
		return themeDto;
	}



	public void setThemeDto(ThemeDto themeDto) {
		this.themeDto = themeDto;
	}



	public GroupeDto getGroupeDto() {
		return groupeDto;
	}



	public void setGroupeDto(GroupeDto groupeDto) {
		this.groupeDto = groupeDto;
	}



	public List<IndEvaluationDto> getIndEvaluationsDto() {
		return indEvaluationsDto;
	}



	public void setIndEvaluationsDto(List<IndEvaluationDto> indEvaluationsDto) {
		this.indEvaluationsDto = indEvaluationsDto;
	}



	public List<ProjetPartenaireDto> getProjetPartenairesDto() {
		return projetPartenairesDto;
	}



	public void setProjetPartenairesDto(
			List<ProjetPartenaireDto> projetPartenairesDto) {
		this.projetPartenairesDto = projetPartenairesDto;
	}

	public List<EtapeValidationDto> getEtapeValidationsDto() {
		return etapeValidationsDto;
	}

	public void setEtapeValidationsDto(List<EtapeValidationDto> etapeValidationsDto) {
		this.etapeValidationsDto = etapeValidationsDto;
	}

	
	
	
}


