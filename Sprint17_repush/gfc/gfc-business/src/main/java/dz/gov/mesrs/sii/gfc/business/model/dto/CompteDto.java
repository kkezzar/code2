package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import dz.gov.mesrs.sii.commons.business.model.dto.bpm.SituationEntiteDto;
import dz.gov.mesrs.sii.referentiel.business.model.dto.NomenclatureDto;

public class CompteDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:32:10
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private AgentComptableDto agentComptable;
	private NomenclatureDto type;
	private SituationEntiteDto situation;
	private String numero;
	private String intituleFr;
	private String intituleAr;
	private Date dateOuverture;
	private Date dateCloture;
	private Date dateCreation;
	private Date dateModification;
	private String observation;
	private List<RegieDto> regiesForIdCompteCcp = new ArrayList<RegieDto>(0);
	private List<RegieDto> regiesForIdCompteDepot = new ArrayList<RegieDto>(0);

	public CompteDto() {
		type = new NomenclatureDto();
		situation = new SituationEntiteDto();
		agentComptable = new AgentComptableDto();
	}

	public CompteDto(Integer id, NomenclatureDto type, SituationEntiteDto situation, String numero, String intituleFr,
			String intituleAr, Date dateOuverture) {
		this.id = id;
		this.type = type;
		this.situation = situation;
		this.numero = numero;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateOuverture = dateOuverture;
	}

	public CompteDto(Integer id, AgentComptableDto agentComptable, NomenclatureDto type, SituationEntiteDto situation,
			String numero, String intituleFr, String intituleAr, Date dateOuverture, Date dateCloture,
			Date dateCreation, Date dateModification, String observation, List<RegieDto> regiesForIdCompteCcp,
			List<RegieDto> regiesForIdCompteDepot) {
		this.id = id;
		this.agentComptable = agentComptable;
		this.type = type;
		this.situation = situation;
		this.numero = numero;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateOuverture = dateOuverture;
		this.dateCloture = dateCloture;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.observation = observation;
		this.regiesForIdCompteCcp = regiesForIdCompteCcp;
		this.regiesForIdCompteDepot = regiesForIdCompteDepot;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AgentComptableDto getAgentComptable() {
		return this.agentComptable;
	}

	public void setAgentComptable(AgentComptableDto agentComptable) {
		this.agentComptable = agentComptable;
	}

	public NomenclatureDto getType() {
		return this.type;
	}

	public void setType(NomenclatureDto type) {
		this.type = type;
	}

	public SituationEntiteDto getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntiteDto situation) {
		this.situation = situation;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public Date getDateOuverture() {
		return this.dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<RegieDto> getRegiesForIdCompteCcp() {
		return this.regiesForIdCompteCcp;
	}

	public void setRegiesForIdCompteCcp(List<RegieDto> regiesForIdCompteCcp) {
		this.regiesForIdCompteCcp = regiesForIdCompteCcp;
	}

	public List<RegieDto> getRegiesForIdCompteDepot() {
		return this.regiesForIdCompteDepot;
	}

	public void setRegiesForIdCompteDepot(List<RegieDto> regiesForIdCompteDepot) {
		this.regiesForIdCompteDepot = regiesForIdCompteDepot;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agentComptable == null) ? 0 : agentComptable.hashCode());
		result = prime * result
				+ ((dateCloture == null) ? 0 : dateCloture.hashCode());
		result = prime * result
				+ ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime
				* result
				+ ((dateModification == null) ? 0 : dateModification.hashCode());
		result = prime * result
				+ ((dateOuverture == null) ? 0 : dateOuverture.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((intituleAr == null) ? 0 : intituleAr.hashCode());
		result = prime * result
				+ ((intituleFr == null) ? 0 : intituleFr.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		result = prime
				* result
				+ ((regiesForIdCompteCcp == null) ? 0 : regiesForIdCompteCcp
						.hashCode());
		result = prime
				* result
				+ ((regiesForIdCompteDepot == null) ? 0
						: regiesForIdCompteDepot.hashCode());
		result = prime * result
				+ ((situation == null) ? 0 : situation.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteDto other = (CompteDto) obj;
		if (agentComptable == null) {
			if (other.agentComptable != null)
				return false;
		} else if (!agentComptable.equals(other.agentComptable))
			return false;
		if (dateCloture == null) {
			if (other.dateCloture != null)
				return false;
		} else if (!dateCloture.equals(other.dateCloture))
			return false;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (dateModification == null) {
			if (other.dateModification != null)
				return false;
		} else if (!dateModification.equals(other.dateModification))
			return false;
		if (dateOuverture == null) {
			if (other.dateOuverture != null)
				return false;
		} else if (!dateOuverture.equals(other.dateOuverture))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (regiesForIdCompteCcp == null) {
			if (other.regiesForIdCompteCcp != null)
				return false;
		} else if (!regiesForIdCompteCcp.equals(other.regiesForIdCompteCcp))
			return false;
		if (regiesForIdCompteDepot == null) {
			if (other.regiesForIdCompteDepot != null)
				return false;
		} else if (!regiesForIdCompteDepot.equals(other.regiesForIdCompteDepot))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
