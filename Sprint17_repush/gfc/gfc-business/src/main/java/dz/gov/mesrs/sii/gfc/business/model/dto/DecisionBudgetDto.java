package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;

public class DecisionBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:22
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private ProjetBudgetDto projetBudget;
	private RefEtablissementDto etablissement;
	private NomenclatureDto uniteMonetaire;
	private SituationEntiteDto situation;
	private String reference;
	private Date dateDecision;
	private String intituleFr;
	private String intituleAr;
	private BigDecimal montant;
	private Date dateSiganture;
	private String observation;
	private List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets = new ArrayList<DetailsNotificationDecisionBudgetDto>(
			0);
	private List<RepartitionBudgetDto> repartitionBudgets = new ArrayList<RepartitionBudgetDto>(0);

	public DecisionBudgetDto() {
	}

	public DecisionBudgetDto(Integer id, ProjetBudgetDto projetBudget, RefEtablissementDto refEtablissement,
			NomenclatureDto nomenclature, SituationEntiteDto situationEntite, String reference, Date dateDecision,
			String intituleFr, BigDecimal montant) {
		this.id = id;
		this.projetBudget = projetBudget;
		this.etablissement = refEtablissement;
		this.uniteMonetaire = nomenclature;
		this.situation = situationEntite;
		this.reference = reference;
		this.dateDecision = dateDecision;
		this.intituleFr = intituleFr;
		this.montant = montant;
	}

	public DecisionBudgetDto(Integer id, ProjetBudgetDto projetBudget, RefEtablissementDto refEtablissement,
			NomenclatureDto nomenclature, SituationEntiteDto situationEntite, String reference, Date dateDecision,
			String intituleFr, String intituleAr, BigDecimal montant, Date dateSiganture, String observation,
			List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets,
			List<RepartitionBudgetDto> repartitionBudgets) {
		this.id = id;
		this.projetBudget = projetBudget;
		this.etablissement = refEtablissement;
		this.uniteMonetaire = nomenclature;
		this.situation = situationEntite;
		this.reference = reference;
		this.dateDecision = dateDecision;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.montant = montant;
		this.dateSiganture = dateSiganture;
		this.observation = observation;
		this.detailsNotificationDecisionBudgets = detailsNotificationDecisionBudgets;
		this.repartitionBudgets = repartitionBudgets;

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjetBudgetDto getProjetBudget() {
		return this.projetBudget;
	}

	public void setProjetBudget(ProjetBudgetDto projetBudget) {
		this.projetBudget = projetBudget;
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

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Date getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Date dateDecision) {
		this.dateDecision = dateDecision;
	}

	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@Column(name = "intitule_ar", length = 200)
	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public Date getDateSiganture() {
		return this.dateSiganture;
	}

	public void setDateSiganture(Date dateSiganture) {
		this.dateSiganture = dateSiganture;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailsNotificationDecisionBudgetDto> getDetailsNotificationDecisionBudgets() {
		return this.detailsNotificationDecisionBudgets;
	}

	public void setDetailsNotificationDecisionBudgets(
			List<DetailsNotificationDecisionBudgetDto> detailsNotificationDecisionBudgets) {
		this.detailsNotificationDecisionBudgets = detailsNotificationDecisionBudgets;
	}

	public List<RepartitionBudgetDto> getRepartitionBudgets() {
		return this.repartitionBudgets;
	}

	public void setRepartitionBudgets(List<RepartitionBudgetDto> repartitionBudgets) {
		this.repartitionBudgets = repartitionBudgets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDecision == null) ? 0 : dateDecision.hashCode());
		result = prime * result + ((dateSiganture == null) ? 0 : dateSiganture.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());

		result = prime * result + ((uniteMonetaire == null) ? 0 : uniteMonetaire.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((projetBudget == null) ? 0 : projetBudget.hashCode());
		result = prime * result + ((etablissement == null) ? 0 : etablissement.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		DecisionBudgetDto other = (DecisionBudgetDto) obj;
		if (dateDecision == null) {
			if (other.dateDecision != null)
				return false;
		} else if (!dateDecision.equals(other.dateDecision))
			return false;
		if (dateSiganture == null) {
			if (other.dateSiganture != null)
				return false;
		} else if (!dateSiganture.equals(other.dateSiganture))
			return false;
		if (detailsNotificationDecisionBudgets == null) {
			if (other.detailsNotificationDecisionBudgets != null)
				return false;
		} else if (!detailsNotificationDecisionBudgets.equals(other.detailsNotificationDecisionBudgets))
			return false;
		if (id != other.id)
			return false;
		if (intituleAr == null) {
			if (other.intituleAr != null)
				return false;
		} else if (!intituleAr.equals(other.intituleAr))
			return false;
		if (intituleFr == null) {
			if (other.intituleFr != null)
				return false;
		} else if (!intituleFr.equals(other.intituleFr))
			return false;
		if (montant == null) {
			if (other.montant != null)
				return false;
		} else if (!montant.equals(other.montant))
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
		if (projetBudget == null) {
			if (other.projetBudget != null)
				return false;
		} else if (!projetBudget.equals(other.projetBudget))
			return false;
		if (etablissement == null) {
			if (other.etablissement != null)
				return false;
		} else if (!etablissement.equals(other.etablissement))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (repartitionBudgets == null) {
			if (other.repartitionBudgets != null)
				return false;
		} else if (!repartitionBudgets.equals(other.repartitionBudgets))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		return true;
	}

}
