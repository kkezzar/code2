package dz.gov.mesrs.sii.commons.data.model.gfc;

// Generated 24 nov. 2014 16:52:54 by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dz.gov.mesrs.sii.commons.data.model.Identifiable;
import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefEtablissement;
import dz.gov.mesrs.sii.commons.data.model.referentiel.RefStructure;

/**
 * Fonds generated by hbm2java
 */
@Entity
@Table(name = "fonds", schema = "gfc")
public class Fonds implements java.io.Serializable, Identifiable<Integer> {

	/**
	 * @author Mounir.MESSAOUDI on : 24 nov. 2014 17:33:11
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private RefStructure structure;
	private RefEtablissement etablissement;
	private SituationEntite situation;
	private String abreviation;
	private String intituleFr;
	private String intituleAr;
	private Date dateCreation;
	private Date dateCloture;
	private String observation;
	private List<AffectationEtabChapitreArticle> affectationEtabChapitreArticles = new ArrayList<AffectationEtabChapitreArticle>(
			0);

	public Fonds() {
	}

	public Fonds(Integer id, RefStructure refStructure, RefEtablissement refEtablissement,
			SituationEntite situationEntite, String intituleFr, String intituleAr) {
		this.id = id;
		this.structure = refStructure;
		this.etablissement = refEtablissement;
		this.situation = situationEntite;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
	}

	public Fonds(Integer id, RefStructure refStructure, RefEtablissement refEtablissement,
			SituationEntite situationEntite, String abreviation, String intituleFr, String intituleAr,
			Date dateCreation, Date dateCloture, String observation,
			List<AffectationEtabChapitreArticle> affectationEtabChapitreArticles) {
		this.id = id;
		this.structure = refStructure;
		this.etablissement = refEtablissement;
		this.situation = situationEntite;
		this.abreviation = abreviation;
		this.intituleFr = intituleFr;
		this.intituleAr = intituleAr;
		this.dateCreation = dateCreation;
		this.dateCloture = dateCloture;
		this.observation = observation;
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
	}

	@SequenceGenerator(name = "fonds_id_seq", sequenceName = "gfc.fonds_id_seq")
	@Id
	@GeneratedValue(generator = "fonds_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_structure", nullable = false)
	public RefStructure getStructure() {
		return this.structure;
	}

	public void setStructure(RefStructure refStructure) {
		this.structure = refStructure;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etablissement", nullable = false)
	public RefEtablissement getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(RefEtablissement refEtablissement) {
		this.etablissement = refEtablissement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituation() {
		return this.situation;
	}

	public void setSituation(SituationEntite situationEntite) {
		this.situation = situationEntite;
	}

	@Column(name = "abreviation", length = 50)
	public String getAbreviation() {
		return this.abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	@Column(name = "intitule_fr", nullable = false, length = 200)
	public String getIntituleFr() {
		return this.intituleFr;
	}

	public void setIntituleFr(String intituleFr) {
		this.intituleFr = intituleFr;
	}

	@Column(name = "intitule_ar", nullable = false, length = 200)
	public String getIntituleAr() {
		return this.intituleAr;
	}

	public void setIntituleAr(String intituleAr) {
		this.intituleAr = intituleAr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 29)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_cloture", length = 29)
	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	@Column(name = "observation", length = 200)
	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "affectation_fonds_chapitre_article", schema = "gfc", joinColumns = { @JoinColumn(name = "id_fonds", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "id_affectation_etab_chapitre_article", nullable = false, updatable = false) })
	public List<AffectationEtabChapitreArticle> getAffectationEtabChapitreArticles() {
		return this.affectationEtabChapitreArticles;
	}

	public void setAffectationEtabChapitreArticles(List<AffectationEtabChapitreArticle> affectationEtabChapitreArticles) {
		this.affectationEtabChapitreArticles = affectationEtabChapitreArticles;
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