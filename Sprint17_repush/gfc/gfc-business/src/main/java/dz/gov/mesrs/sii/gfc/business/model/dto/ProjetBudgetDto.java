package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class ProjetBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:42
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private NomenclatureDto typeBudget;
	private ProjetBudgetDto projetBudget;
	private NomenclatureDto uniteMonetaire;
	private ExerciceBudgetaireDto exerciceBudgetaire;
	private SituationEntiteDto situation;
	private String identite;
	private String description;

	private Date dateReceptionNoteOrientation;

	private Date dateDebut;
	private Date dateFin;

	private Date dateEnvoiMF;

	private String observation;

	private List<DetailsProjetBudgetDto> detailsProjetBudgets = new ArrayList<DetailsProjetBudgetDto>(0);
	private List<DetailsProjetBudgetEtablissementDto> detailsProjetBudgetsEtablissement = new ArrayList<DetailsProjetBudgetEtablissementDto>(
			0);
	private List<ProjetBudgetDto> projetBudgets = new ArrayList<ProjetBudgetDto>(0);
	private List<FicheBesoinsDto> fichesBesoins = new ArrayList<FicheBesoinsDto>(0);
	private List<DecisionBudgetDto> decisionBudgets = new ArrayList<DecisionBudgetDto>(0);

	public ProjetBudgetDto() {
		exerciceBudgetaire = new ExerciceBudgetaireDto();
		typeBudget = new NomenclatureDto();
		uniteMonetaire = new NomenclatureDto();
		situation = new SituationEntiteDto();
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NomenclatureDto getTypeBudget() {
		return this.typeBudget;
	}

	public void setTypeBudget(NomenclatureDto typeBudget) {
		this.typeBudget = typeBudget;
	}

	public ProjetBudgetDto getProjetBudget() {
		return this.projetBudget;
	}

	public void setProjetBudget(ProjetBudgetDto projetBudget) {
		this.projetBudget = projetBudget;
	}

	public NomenclatureDto getUniteMonetaire() {
		return this.uniteMonetaire;
	}

	public void setUniteMonetaire(NomenclatureDto uniteMonetaire) {
		this.uniteMonetaire = uniteMonetaire;
	}

	public ExerciceBudgetaireDto getExerciceBudgetaire() {
		return this.exerciceBudgetaire;
	}

	public void setExerciceBudgetaire(ExerciceBudgetaireDto exerciceBudgetaire) {
		this.exerciceBudgetaire = exerciceBudgetaire;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public String getIdentite() {
		return this.identite;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateReceptionNoteOrientation() {
		return dateReceptionNoteOrientation;
	}

	public void setDateReceptionNoteOrientation(Date dateReceptionNoteOrientation) {
		this.dateReceptionNoteOrientation = dateReceptionNoteOrientation;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailsProjetBudgetDto> getDetailsProjetBudgets() {
		return this.detailsProjetBudgets;
	}

	public void setDetailsProjetBudgets(List<DetailsProjetBudgetDto> detailsProjetBudgets) {
		this.detailsProjetBudgets = detailsProjetBudgets;
	}

	public List<DetailsProjetBudgetEtablissementDto> getDetailsProjetBudgetsEtablissement() {
		return detailsProjetBudgetsEtablissement;
	}

	public void setDetailsProjetBudgetsEtablissement(
			List<DetailsProjetBudgetEtablissementDto> detailsProjetBudgetsEtablissement) {
		this.detailsProjetBudgetsEtablissement = detailsProjetBudgetsEtablissement;
	}

	public List<ProjetBudgetDto> getProjetBudgets() {
		return this.projetBudgets;
	}

	public void setProjetBudgets(List<ProjetBudgetDto> projetBudgets) {
		this.projetBudgets = projetBudgets;
	}

	public List<FicheBesoinsDto> getFichesBesoins() {
		return this.fichesBesoins;
	}

	public void setFichesBesoins(List<FicheBesoinsDto> fichesBesoins) {
		this.fichesBesoins = fichesBesoins;
	}

	public List<DecisionBudgetDto> getDecisionBudgets() {
		return this.decisionBudgets;
	}

	public void setDecisionBudgets(List<DecisionBudgetDto> decisionBudgets) {
		this.decisionBudgets = decisionBudgets;
	}

	public Date getDateEnvoiMF() {
		return dateEnvoiMF;
	}

	public void setDateEnvoiMF(Date dateEnvoiMF) {
		this.dateEnvoiMF = dateEnvoiMF;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateEnvoiMF == null) ? 0 : dateEnvoiMF.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result
				+ ((dateReceptionNoteOrientation == null) ? 0 : dateReceptionNoteOrientation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((exerciceBudgetaire == null) ? 0 : exerciceBudgetaire.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identite == null) ? 0 : identite.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((projetBudget == null) ? 0 : projetBudget.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
		result = prime * result + ((typeBudget == null) ? 0 : typeBudget.hashCode());
		result = prime * result + ((uniteMonetaire == null) ? 0 : uniteMonetaire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetBudgetDto other = (ProjetBudgetDto) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateEnvoiMF == null) {
			if (other.dateEnvoiMF != null)
				return false;
		} else if (!dateEnvoiMF.equals(other.dateEnvoiMF))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (dateReceptionNoteOrientation == null) {
			if (other.dateReceptionNoteOrientation != null)
				return false;
		} else if (!dateReceptionNoteOrientation.equals(other.dateReceptionNoteOrientation))
			return false;
		if (decisionBudgets == null) {
			if (other.decisionBudgets != null)
				return false;
		} else if (!decisionBudgets.equals(other.decisionBudgets))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (detailsProjetBudgets == null) {
			if (other.detailsProjetBudgets != null)
				return false;
		} else if (!detailsProjetBudgets.equals(other.detailsProjetBudgets))
			return false;
		if (exerciceBudgetaire == null) {
			if (other.exerciceBudgetaire != null)
				return false;
		} else if (!exerciceBudgetaire.equals(other.exerciceBudgetaire))
			return false;
		if (fichesBesoins == null) {
			if (other.fichesBesoins != null)
				return false;
		} else if (!fichesBesoins.equals(other.fichesBesoins))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identite == null) {
			if (other.identite != null)
				return false;
		} else if (!identite.equals(other.identite))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (projetBudget == null) {
			if (other.projetBudget != null)
				return false;
		} else if (!projetBudget.equals(other.projetBudget))
			return false;
		if (projetBudgets == null) {
			if (other.projetBudgets != null)
				return false;
		} else if (!projetBudgets.equals(other.projetBudgets))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		if (typeBudget == null) {
			if (other.typeBudget != null)
				return false;
		} else if (!typeBudget.equals(other.typeBudget))
			return false;
		if (uniteMonetaire == null) {
			if (other.uniteMonetaire != null)
				return false;
		} else if (!uniteMonetaire.equals(other.uniteMonetaire))
			return false;
		return true;
	}

}
