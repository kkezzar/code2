package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.Date;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefEtablissementDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.RefStructureDto;

public class AffectationEtabStrAgentComptableDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:41
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefStructureDto refStructure;
	private AgentComptableDto agentComptable;
	private RefEtablissementDto refEtablissement;
	private Date dateAffectation;
	private String observation;
	private String decisionAffectation;
	private Date dateFinAffectation;

	public AffectationEtabStrAgentComptableDto() {
		refEtablissement = new RefEtablissementDto();
		refStructure = new RefStructureDto();
	}

	public AffectationEtabStrAgentComptableDto(Integer id, AgentComptableDto agentComptable,
			RefEtablissementDto refEtablissement, Date dateAffectation, String decisionAffectation) {
		this.id = id;
		this.agentComptable = agentComptable;
		this.refEtablissement = refEtablissement;
		this.dateAffectation = dateAffectation;
		this.decisionAffectation = decisionAffectation;
	}

	public AffectationEtabStrAgentComptableDto(Integer id, RefStructureDto refStructure,
			AgentComptableDto agentComptable, RefEtablissementDto refEtablissement, Date dateAffectation,
			String observation, String decisionAffectation, Date dateFinAffectation) {
		this.id = id;
		this.refStructure = refStructure;
		this.agentComptable = agentComptable;
		this.refEtablissement = refEtablissement;
		this.dateAffectation = dateAffectation;
		this.observation = observation;
		this.decisionAffectation = decisionAffectation;
		this.dateFinAffectation = dateFinAffectation;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RefStructureDto getRefStructure() {
		return this.refStructure;
	}

	public void setRefStructure(RefStructureDto refStructure) {
		this.refStructure = refStructure;
	}

	public AgentComptableDto getAgentComptable() {
		return this.agentComptable;
	}

	public void setAgentComptable(AgentComptableDto agentComptable) {
		this.agentComptable = agentComptable;
	}

	public RefEtablissementDto getRefEtablissement() {
		return this.refEtablissement;
	}

	public void setRefEtablissement(RefEtablissementDto refEtablissement) {
		this.refEtablissement = refEtablissement;
	}

	public Date getDateAffectation() {
		return this.dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDecisionAffectation() {
		return this.decisionAffectation;
	}

	public void setDecisionAffectation(String decisionAffectation) {
		this.decisionAffectation = decisionAffectation;
	}

	public Date getDateFinAffectation() {
		return this.dateFinAffectation;
	}

	public void setDateFinAffectation(Date dateFinAffectation) {
		this.dateFinAffectation = dateFinAffectation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentComptable == null) ? 0 : agentComptable.hashCode());
		result = prime * result + ((dateAffectation == null) ? 0 : dateAffectation.hashCode());
		result = prime * result + ((dateFinAffectation == null) ? 0 : dateFinAffectation.hashCode());
		result = prime * result + ((decisionAffectation == null) ? 0 : decisionAffectation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((refEtablissement == null) ? 0 : refEtablissement.hashCode());
		result = prime * result + ((refStructure == null) ? 0 : refStructure.hashCode());
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
		AffectationEtabStrAgentComptableDto other = (AffectationEtabStrAgentComptableDto) obj;
		if (agentComptable == null) {
			if (other.agentComptable != null)
				return false;
		} else if (!agentComptable.equals(other.agentComptable))
			return false;
		if (dateAffectation == null) {
			if (other.dateAffectation != null)
				return false;
		} else if (!dateAffectation.equals(other.dateAffectation))
			return false;
		if (dateFinAffectation == null) {
			if (other.dateFinAffectation != null)
				return false;
		} else if (!dateFinAffectation.equals(other.dateFinAffectation))
			return false;
		if (decisionAffectation == null) {
			if (other.decisionAffectation != null)
				return false;
		} else if (!decisionAffectation.equals(other.decisionAffectation))
			return false;
		if (id != other.id)
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
		return true;
	}

}
