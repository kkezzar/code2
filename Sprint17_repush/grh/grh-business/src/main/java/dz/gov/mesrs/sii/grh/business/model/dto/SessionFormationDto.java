package dz.gov.mesrs.sii.grh.business.model.dto;



import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefLieuDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

/**
 * 
 * @author BELDI Jamel
 *
 */

public class SessionFormationDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SituationEntiteDto situationEntiteDto;
	private RefStructureDto refStructureDto;
	private CycleFormationDto cycleFormationDto;
	private FormationCatalogueDto formationCatalogueDto;
	private RefLieuDto refLieuDto;
	private String code;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private Boolean externe;
	private String lieuExterne;
	private Integer nbParticipant;
	private Date dateEvaluation;
	private Boolean satisfaisant;
	private String observation;
	private List<InscriptionSessionFormationDto> inscriptionSessionFormationDtos;
	private List<FormateurSessionFormationDto> formateurSessionFormationDtos;
	private List<EvaluationSessionFormationDto> evaluationSessionFormationDtos;
	private Integer nbInscrit;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituationEntiteDto getSituationEntiteDto() {
		return situationEntiteDto;
	}

	public void setSituationEntiteDto(SituationEntiteDto situationEntiteDto) {
		this.situationEntiteDto = situationEntiteDto;
	}

	public RefStructureDto getRefStructureDto() {
		return refStructureDto;
	}

	public void setRefStructureDto(RefStructureDto refStructureDto) {
		this.refStructureDto = refStructureDto;
	}

	public CycleFormationDto getCycleFormationDto() {
		return cycleFormationDto;
	}

	public void setCycleFormationDto(CycleFormationDto cycleFormationDto) {
		this.cycleFormationDto = cycleFormationDto;
	}

	public RefLieuDto getRefLieuDto() {
		return refLieuDto;
	}

	public void setRefLieuDto(RefLieuDto refLieuDto) {
		this.refLieuDto = refLieuDto;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Boolean getExterne() {
		return externe;
	}

	public void setExterne(Boolean externe) {
		this.externe = externe;
	}

	public String getLieuExterne() {
		return lieuExterne;
	}

	public void setLieuExterne(String lieuExterne) {
		this.lieuExterne = lieuExterne;
	}

	public Integer getNbParticipant() {
		return nbParticipant;
	}

	public void setNbParticipant(Integer nbParticipant) {
		this.nbParticipant = nbParticipant;
	}

	public Date getDateEvaluation() {
		return dateEvaluation;
	}

	public void setDateEvaluation(Date dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}

	public Boolean getSatisfaisant() {
		return satisfaisant;
	}

	public void setSatisfaisant(Boolean satisfaisant) {
		this.satisfaisant = satisfaisant;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<InscriptionSessionFormationDto> getInscriptionSessionFormationDtos() {
		return inscriptionSessionFormationDtos;
	}

	public void setInscriptionSessionFormationDtos(
			List<InscriptionSessionFormationDto> inscriptionSessionFormationDtos) {
		this.inscriptionSessionFormationDtos = inscriptionSessionFormationDtos;
	}

	public List<FormateurSessionFormationDto> getFormateurSessionFormationDtos() {
		return formateurSessionFormationDtos;
	}

	public void setFormateurSessionFormationDtos(
			List<FormateurSessionFormationDto> formateurSessionFormationDtos) {
		this.formateurSessionFormationDtos = formateurSessionFormationDtos;
	}

	public List<EvaluationSessionFormationDto> getEvaluationSessionFormationDtos() {
		return evaluationSessionFormationDtos;
	}

	public void setEvaluationSessionFormationDtos(
			List<EvaluationSessionFormationDto> evaluationSessionFormationDtos) {
		this.evaluationSessionFormationDtos = evaluationSessionFormationDtos;
	}

	
	
	public SessionFormationDto() {
	}

	public SessionFormationDto(Long id) {
		this.id = id;
	}

	public FormationCatalogueDto getFormationCatalogueDto() {
		return formationCatalogueDto;
	}

	public void setFormationCatalogueDto(FormationCatalogueDto formationCatalogueDto) {
		this.formationCatalogueDto = formationCatalogueDto;
	}

	public Integer getNbInscrit() {
		int nbInscrit = 0;
		if(this.inscriptionSessionFormationDtos!=null){
			
		for (InscriptionSessionFormationDto inscription : this.inscriptionSessionFormationDtos) {
			if(inscription.getResultat()!=null && inscription.getResultat()==true ){
				nbInscrit = nbInscrit+1;
			}
		}
		}
		return nbInscrit;
	}

	public void setNbInscrit(Integer nbInscrit) {
		this.nbInscrit = nbInscrit;
	}

	

	
	
}
