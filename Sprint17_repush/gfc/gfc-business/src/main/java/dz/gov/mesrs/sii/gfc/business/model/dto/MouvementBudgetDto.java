package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class MouvementBudgetDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:21
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private NomenclatureDto typeMouvement;
	private RepartitionBudgetDto repartitionBudget;
	private RefStructureDto refStructure;
	private RefEtablissementDto refEtablissement;
	private NomenclatureDto uniteMonitaire;
	private String reference;
	private Date dateDecision;
	private String intituleFr;
	private String intituleAr;
	private BigDecimal montant;
	private Integer idSituation;
	private String observation;
	private Date dateSignature;
	private List<DetailsMouvementBudgetDto> detailsMouvementBudgets = new ArrayList<DetailsMouvementBudgetDto>(0);

	public MouvementBudgetDto() {
		refStructure=new RefStructureDto();
		}

	public MouvementBudgetDto(Integer id, NomenclatureDto typeMouvement,
			RefStructureDto refStructure, RefEtablissementDto refEtablissement,
			NomenclatureDto uniteMonitaire, String reference, Date dateDecision, String intituleFr,
			String intituleAr, BigDecimal montant, Integer idSituation) {
		this.id = id;
		this.typeMouvement = typeMouvement;
		this.refStructure = refStructure;
		this.refEtablissement = refEtablissement;
		this.uniteMonitaire = uniteMonitaire;
		this.reference = reference;
		this.dateDecision = dateDecision;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.montant = montant;
		this.idSituation = idSituation;
	}

	public MouvementBudgetDto(Integer id, NomenclatureDto typeMouvement,
			RepartitionBudgetDto repartitionBudget, RefStructureDto refStructure, RefEtablissementDto refEtablissement,
			NomenclatureDto uniteMonitaire, String reference, Date dateDecision, String intituleFr,
			String intituleAr, BigDecimal montant, Integer idSituation, String observation, Date dateSignature,
			List<DetailsMouvementBudgetDto> detailsMouvementBudgets) {
		this.id = id;
		this.typeMouvement = typeMouvement;
		this.repartitionBudget = repartitionBudget;
		this.refStructure = refStructure;
		this.refEtablissement = refEtablissement;
		this.uniteMonitaire = uniteMonitaire;
		this.reference = reference;
		this.dateDecision = dateDecision;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.montant = montant;
		this.idSituation = idSituation;
		this.observation = observation;
		this.dateSignature= dateSignature;
		this.detailsMouvementBudgets = detailsMouvementBudgets;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public NomenclatureDto getTypeMouvement() {
//		return this.typeMouvement;
//	}
//
//	public void setTypeMouvement(NomenclatureDto typeMouvement) {
//		this.typeMouvement = typeMouvement;
//	}

	public RepartitionBudgetDto getRepartitionBudget() {
		return this.repartitionBudget;
	}

	public void setRepartitionBudget(RepartitionBudgetDto repartitionBudget) {
		this.repartitionBudget = repartitionBudget;
	}

	public RefStructureDto getRefStructure() {
		return this.refStructure;
	}

	public void setRefStructure(RefStructureDto refStructure) {
		this.refStructure = refStructure;
	}

	public RefEtablissementDto getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissementDto refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	public NomenclatureDto getUniteMonitaire() {
		return this.uniteMonitaire;
	}

	public void setUniteMonitaire(NomenclatureDto uniteMonitaire) {
		this.uniteMonitaire = uniteMonitaire;
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

	public Integer getIdSituation() {
		return this.idSituation;
	}

	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<DetailsMouvementBudgetDto> getDetailsMouvementBudgets() {
		return this.detailsMouvementBudgets;
	}

	public void setDetailsMouvementBudgets(List<DetailsMouvementBudgetDto> detailsMouvementBudgets) {
		this.detailsMouvementBudgets = detailsMouvementBudgets;
	}

	/**
	 * @return the dateSignature
	 */
	public Date getDateSignature() {
		return dateSignature;
	}

	/**
	 * @param dateSignature the dateSignature to set
	 */
	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDecision == null) ? 0 : dateDecision.hashCode());
		result = prime * result + ((repartitionBudget == null) ? 0 : repartitionBudget.hashCode());
		result = prime * result + ((detailsMouvementBudgets == null) ? 0 : detailsMouvementBudgets.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idSituation == null) ? 0 : idSituation.hashCode());
		result = prime * result + ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result + ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((montant == null) ? 0 : montant.hashCode());
		result = prime * result
				+ ((typeMouvement == null) ? 0 : typeMouvement.hashCode());
		result = prime * result
				+ ((uniteMonitaire == null) ? 0 : uniteMonitaire.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime * result + ((refStructure == null) ? 0 : refStructure.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
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
		MouvementBudgetDto other = (MouvementBudgetDto) obj;
		if (dateDecision == null) {
			if (other.dateDecision != null)
				return false;
		} else if (!dateDecision.equals(other.dateDecision))
			return false;
		if (repartitionBudget == null) {
			if (other.repartitionBudget != null)
				return false;
		} else if (!repartitionBudget.equals(other.repartitionBudget))
			return false;
		if (detailsMouvementBudgets == null) {
			if (other.detailsMouvementBudgets != null)
				return false;
		} else if (!detailsMouvementBudgets.equals(other.detailsMouvementBudgets))
			return false;
		if (id != other.id)
			return false;
		if (idSituation != other.idSituation)
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
		if (typeMouvement == null) {
			if (other.typeMouvement != null)
				return false;
		} else if (!typeMouvement.equals(other.typeMouvement))
			return false;
		if (uniteMonitaire == null) {
			if (other.uniteMonitaire != null)
				return false;
		} else if (!uniteMonitaire.equals(other.uniteMonitaire))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (refEtablissement == null) {
			if (other.refEtablissement != null)
				return false;
		} else if (!refEtablissement.equals(other.refEtablissement))
			return false;
		if (refStructure == null) {
			if (other.refStructure != null)
				return false;
		} else if (!refStructure.equals(other.refStructure))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}

}
