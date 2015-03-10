package dz.gov.mesrs.sii.gfc.business.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dz.gov.mesrs.sii.referentiel.business.model.dto.RefIndividuDto;

public class AgentComptableDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:48
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefIndividuDto refIndividu;
	private String code;
	private String decisionNomination;
	private Date dateNomination;
	private Date dateFinNomination;

	// private List<RegieDto> regies = new ArrayList<RegieDto>(0);
	private List<CompteDto> comptes = new ArrayList<CompteDto>(0);
	private List<AffectationEtabStrAgentComptableDto> affectationEtabStrAgentComptables = new ArrayList<AffectationEtabStrAgentComptableDto>(
			0);

	public AgentComptableDto() {
	}

	public AgentComptableDto(Integer id, RefIndividuDto refIndividu) {
		this.id = id;
		this.refIndividu = refIndividu;
	}

	public AgentComptableDto(Integer id, RefIndividuDto refIndividu, String code, String decisionNomination,
			Date dateNomination, Date dateFinNomination, List<RegieDto> regies, List<CompteDto> comptes,
			List<AffectationEtabStrAgentComptableDto> affectationEtabStrAgentComptables) {
		this.id = id;
		this.refIndividu = refIndividu;
		this.code = code;
		this.decisionNomination = decisionNomination;
		this.dateNomination = dateNomination;
		this.dateFinNomination = dateFinNomination;
		// this.regies = regies;
		this.comptes = comptes;
		this.affectationEtabStrAgentComptables = affectationEtabStrAgentComptables;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RefIndividuDto getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividuDto refIndividu) {
		this.refIndividu = refIndividu;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDecisionNomination() {
		return this.decisionNomination;
	}

	public void setDecisionNomination(String decisionNomination) {
		this.decisionNomination = decisionNomination;
	}

	public Date getDateNomination() {
		return this.dateNomination;
	}

	public void setDateNomination(Date dateNomination) {
		this.dateNomination = dateNomination;
	}

	public Date getDateFinNomination() {
		return this.dateFinNomination;
	}

	public void setDateFinNomination(Date dateFinNomination) {
		this.dateFinNomination = dateFinNomination;
	}

	// public List<RegieDto> getRegies() {
	// return this.regies;
	// }
	//
	// public void setRegies(List<RegieDto> regies) {
	// this.regies = regies;
	// }
	//

	public List<CompteDto> getComptes() {
		return this.comptes;
	}

	public void setComptes(List<CompteDto> comptes) {
		this.comptes = comptes;
	}

	public List<AffectationEtabStrAgentComptableDto> getAffectationEtabStrAgentComptables() {
		return this.affectationEtabStrAgentComptables;
	}

	public void setAffectationEtabStrAgentComptables(
			List<AffectationEtabStrAgentComptableDto> affectationEtabStrAgentComptables) {
		this.affectationEtabStrAgentComptables = affectationEtabStrAgentComptables;
	}
}
