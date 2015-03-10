package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * AgentComptable generated by hbm2java
 */
@Entity
@Table(name = "agent_comptable", schema = "gfc")
public class AgentComptable implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:31:48
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefIndividu refIndividu;
	private String code;
	private String decisionNomination;
	private Date dateNomination;
	private Date dateFinNomination;
	private List<Regie> regies = new ArrayList<Regie>(0);
	private List<Compte> comptes = new ArrayList<Compte>(0);
	private List<AffectationEtabStrAgentComptable> affectationEtabStrAgentComptables = new ArrayList<AffectationEtabStrAgentComptable>(
			0);

	public AgentComptable() {
	}

	public AgentComptable(Integer id, RefIndividu refIndividu) {
		this.id = id;
		this.refIndividu = refIndividu;
	}

	public AgentComptable(Integer id, RefIndividu refIndividu, String code, String decisionNomination,
			Date dateNomination, Date dateFinNomination, List<Regie> regies, List<Compte> comptes,
			List<AffectationEtabStrAgentComptable> affectationEtabStrAgentComptables) {
		this.id = id;
		this.refIndividu = refIndividu;
		this.code = code;
		this.decisionNomination = decisionNomination;
		this.dateNomination = dateNomination;
		this.dateFinNomination = dateFinNomination;
		this.regies = regies;
		this.comptes = comptes;
		this.affectationEtabStrAgentComptables = affectationEtabStrAgentComptables;
	}

	@SequenceGenerator(name = "agent_comptable_id_seq", sequenceName = "gfc.agent_comptable_id_seq")
	@Id
	@GeneratedValue(generator = "agent_comptable_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_individu", nullable = false)
	public RefIndividu getRefIndividu() {
		return this.refIndividu;
	}

	public void setRefIndividu(RefIndividu refIndividu) {
		this.refIndividu = refIndividu;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "decision_nomination", length = 200)
	public String getDecisionNomination() {
		return this.decisionNomination;
	}

	public void setDecisionNomination(String decisionNominiation) {
		this.decisionNomination = decisionNominiation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_nomination", length = 29)
	public Date getDateNomination() {
		return this.dateNomination;
	}

	public void setDateNomination(Date dateNomination) {
		this.dateNomination = dateNomination;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_fin_nomination", length = 29)
	public Date getDateFinNomination() {
		return this.dateFinNomination;
	}

	public void setDateFinNomination(Date dateFinNomination) {
		this.dateFinNomination = dateFinNomination;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentComptable")
	public List<Regie> getRegies() {
		return this.regies;
	}

	public void setRegies(List<Regie> regies) {
		this.regies = regies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentComptable")
	public List<Compte> getComptes() {
		return this.comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agentComptable", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public List<AffectationEtabStrAgentComptable> getAffectationEtabStrAgentComptables() {
		return this.affectationEtabStrAgentComptables;
	}

	public void setAffectationEtabStrAgentComptables(
			List<AffectationEtabStrAgentComptable> affectationEtabStrAgentComptables) {
		this.affectationEtabStrAgentComptables = affectationEtabStrAgentComptables;
	}

	@Transient
	@Override
	public Integer getIdenfiant() {
		return id;
	}

	@Transient
	@Override
	public String getIdentifiantName() {
		return "id";
	}

}
