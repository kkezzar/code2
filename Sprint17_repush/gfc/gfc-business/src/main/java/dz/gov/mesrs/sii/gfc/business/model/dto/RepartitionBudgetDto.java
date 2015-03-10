package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class RepartitionBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:59
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private DecisionBudgetDto decisionBudget;
	private RefEtablissementDto etablissement;
	private NomenclatureDto uniteMonetaire;
	private SituationEntiteDto situation;
	private BigDecimal montantTotalRecettes;
	private BigDecimal montantTotalDepenses;
	private Date dateVisaCf;
	private Date dateSignatureOrdonnateur;
	private Date dateSignatureWali;
	private String observation;
	private List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets = new ArrayList<DetailsReparatitionBudgetDto>(
			0);
	private List<MouvementBudgetDto> mouvementBudgets = new ArrayList<MouvementBudgetDto>(0);

	public RepartitionBudgetDto() {
	}

	public RepartitionBudgetDto(Integer id, DecisionBudgetDto decisionBudget, RefEtablissementDto refEtablissement,
			NomenclatureDto nomenclature, SituationEntiteDto situationEntite) {
		this.id = id;
		this.decisionBudget = decisionBudget;
		this.etablissement = refEtablissement;
		this.uniteMonetaire = nomenclature;
		this.situation = situationEntite;
	}

	public RepartitionBudgetDto(Integer id, DecisionBudgetDto decisionBudget, RefEtablissementDto refEtablissement,
			NomenclatureDto uniteMonetaire, SituationEntiteDto situation, BigDecimal montantTotalRecettes,
			BigDecimal montantTotalDepenses, Date dateVisaCf, Date dateSignatureOrdonnateur, Date dateSignatureWali,
			String observation, List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets,
			List<MouvementBudgetDto> mouvementBudgets) {
		this.id = id;
		this.decisionBudget = decisionBudget;
		this.etablissement = refEtablissement;
		this.uniteMonetaire = uniteMonetaire;
		this.situation = situation;
		this.montantTotalRecettes = montantTotalRecettes;
		this.montantTotalDepenses = montantTotalDepenses;
		this.dateVisaCf = dateVisaCf;
		this.dateSignatureOrdonnateur = dateSignatureOrdonnateur;
		this.dateSignatureWali = dateSignatureWali;
		this.observation = observation;
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
		this.mouvementBudgets = mouvementBudgets;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DecisionBudgetDto getDecisionBudget() {
		return this.decisionBudget;
	}

	public void setDecisionBudget(DecisionBudgetDto decisionBudget) {
		this.decisionBudget = decisionBudget;
	}

	public RefEtablissementDto getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(RefEtablissementDto etablissement) {
		this.etablissement = etablissement;
	}

	public NomenclatureDto getUniteMonetaire() {
		return this.uniteMonetaire;
	}

	public void setUniteMonetaire(NomenclatureDto uniteMonetaire) {
		this.uniteMonetaire = uniteMonetaire;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public BigDecimal getMontantTotalRecettes() {
		return this.montantTotalRecettes;
	}

	public void setMontantTotalRecettes(BigDecimal montantTotalRecettes) {
		this.montantTotalRecettes = montantTotalRecettes;
	}

	public BigDecimal getMontantTotalDepenses() {
		return this.montantTotalDepenses;
	}

	public void setMontantTotalDepenses(BigDecimal montantTotalDepenses) {
		this.montantTotalDepenses = montantTotalDepenses;
	}

	public Date getDateVisaCf() {
		return this.dateVisaCf;
	}

	public void setDateVisaCf(Date dateVisaCf) {
		this.dateVisaCf = dateVisaCf;
	}

	public Date getDateSignatureOrdonnateur() {
		return this.dateSignatureOrdonnateur;
	}

	public void setDateSignatureOrdonnateur(Date dateSignatureOrdonnateur) {
		this.dateSignatureOrdonnateur = dateSignatureOrdonnateur;
	}

	public Date getDateSignatureWali() {
		return this.dateSignatureWali;
	}

	public void setDateSignatureWali(Date dateSignatureWali) {
		this.dateSignatureWali = dateSignatureWali;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailsReparatitionBudgetDto> getDetailsReparatitionBudgets() {
		return this.detailsReparatitionBudgets;
	}

	public void setDetailsReparatitionBudgets(List<DetailsReparatitionBudgetDto> detailsReparatitionBudgets) {
		this.detailsReparatitionBudgets = detailsReparatitionBudgets;
	}

	public List<MouvementBudgetDto> getMouvementBudgets() {
		return this.mouvementBudgets;
	}

	public void setMouvementBudgets(List<MouvementBudgetDto> mouvementBudgets) {
		this.mouvementBudgets = mouvementBudgets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSignatureOrdonnateur == null) ? 0 : dateSignatureOrdonnateur.hashCode());
		result = prime * result + ((dateSignatureWali == null) ? 0 : dateSignatureWali.hashCode());
		result = prime * result + ((dateVisaCf == null) ? 0 : dateVisaCf.hashCode());
		result = prime * result + ((decisionBudget == null) ? 0 : decisionBudget.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((montantTotalDepenses == null) ? 0 : montantTotalDepenses.hashCode());
		result = prime * result + ((montantTotalRecettes == null) ? 0 : montantTotalRecettes.hashCode());
		result = prime * result + ((uniteMonetaire == null) ? 0 : uniteMonetaire.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
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
		RepartitionBudgetDto other = (RepartitionBudgetDto) obj;
		if (dateSignatureOrdonnateur == null) {
			if (other.dateSignatureOrdonnateur != null)
				return false;
		} else if (!dateSignatureOrdonnateur.equals(other.dateSignatureOrdonnateur))
			return false;
		if (dateSignatureWali == null) {
			if (other.dateSignatureWali != null)
				return false;
		} else if (!dateSignatureWali.equals(other.dateSignatureWali))
			return false;
		if (dateVisaCf == null) {
			if (other.dateVisaCf != null)
				return false;
		} else if (!dateVisaCf.equals(other.dateVisaCf))
			return false;
		if (decisionBudget == null) {
			if (other.decisionBudget != null)
				return false;
		} else if (!decisionBudget.equals(other.decisionBudget))
			return false;
		if (detailsReparatitionBudgets == null) {
			if (other.detailsReparatitionBudgets != null)
				return false;
		} else if (!detailsReparatitionBudgets.equals(other.detailsReparatitionBudgets))
			return false;
		if (id != other.id)
			return false;
		if (montantTotalDepenses == null) {
			if (other.montantTotalDepenses != null)
				return false;
		} else if (!montantTotalDepenses.equals(other.montantTotalDepenses))
			return false;
		if (montantTotalRecettes == null) {
			if (other.montantTotalRecettes != null)
				return false;
		} else if (!montantTotalRecettes.equals(other.montantTotalRecettes))
			return false;
		if (uniteMonetaire == null) {
			if (other.uniteMonetaire != null)
				return false;
		} else if (!uniteMonetaire.equals(other.uniteMonetaire))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		return true;
	}

}
