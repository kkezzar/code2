package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefIndividu;

/**
 * Regisseur generated by hbm2java
 */
@Entity
@Table(name = "regisseur", schema = "gfc")
public class Regisseur implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:53
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Regie regie;
	private RefIndividu refIndividu;
	private String code;
	private String decisionNomination;
	private Date dateNomination;
	private Date dateFinNomination;

	public Regisseur() {
	}

	public Regisseur(Integer id, RefIndividu refIndividu) {
		this.id = id;
		this.refIndividu = refIndividu;
	}

	public Regisseur(Integer id, Regie regie, RefIndividu refIndividu, String code, String decisionNomination,
			Date dateNomination, Date dateFinNomination) {
		this.id = id;
		this.regie = regie;
		this.refIndividu = refIndividu;
		this.code = code;
		this.decisionNomination = decisionNomination;
		this.dateNomination = dateNomination;
		this.dateFinNomination = dateFinNomination;
	}

	@SequenceGenerator(name = "regisseur_id_seq", sequenceName = "gfc.regisseur_id_seq")
	@Id
	@GeneratedValue(generator = "regisseur_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_regie")
	public Regie getRegie() {
		return this.regie;
	}

	public void setRegie(Regie regie) {
		this.regie = regie;
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

	@Column(name = "decision_nomination", length = 50)
	public String getDecisionNomination() {
		return this.decisionNomination;
	}

	public void setDecisionNomination(String decisionNomination) {
		this.decisionNomination = decisionNomination;
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