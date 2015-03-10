package dz.gov.mesrs.sii.commons.data.model.fve.cursus;

// Generated 14 juil. 2014 15:17:29 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import dz.gov.mesrs.sii.commons.data.model.bpm.SituationEntite;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;

/**
 * @author BELDI Jamel  on : 14 juil. 2014  15:21:39
 */
@Entity
@Table(name = "parcours_individualise", schema = "cursus")
public class ParcoursIndividualise implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 14 juil. 2014  15:20:45
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private SituationEntite situationEntite;
	private Date dateCreation;
	private Periode periode;
	private Set<ParcoursIndividualiseMatiere> parcoursIndividualiseMatieres = new HashSet<ParcoursIndividualiseMatiere>(
			0);

	public ParcoursIndividualise() {
	}

	public ParcoursIndividualise(int id,
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.id = id;
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	public ParcoursIndividualise(int id,
			DossierInscriptionAdministrative dossierInscriptionAdministrative,
			SituationEntite situationEntite, Date dateCreation,
			Set<ParcoursIndividualiseMatiere> parcoursIndividualiseMatieres) {
		this.id = id;
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
		this.situationEntite = situationEntite;
		this.dateCreation = dateCreation;
		this.parcoursIndividualiseMatieres = parcoursIndividualiseMatieres;
	}

	@Id
	@SequenceGenerator(name="parcours_individualise_id_seq_gen", sequenceName="cursus.parcours_individualise_id_seq")
	@GeneratedValue(generator="parcours_individualise_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inscription_administrative", nullable = false)
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return this.dossierInscriptionAdministrative;
	}

	public void setDossierInscriptionAdministrative(
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation")
	public SituationEntite getSituationEntite() {
		return this.situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 35)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcoursIndividualise")
	public Set<ParcoursIndividualiseMatiere> getParcoursIndividualiseMatieres() {
		return this.parcoursIndividualiseMatieres;
	}

	public void setParcoursIndividualiseMatieres(
			Set<ParcoursIndividualiseMatiere> parcoursIndividualiseMatieres) {
		this.parcoursIndividualiseMatieres = parcoursIndividualiseMatieres;
	}

	/**
	 * 
	 * [ParcoursIndividualise.getPeriode] Method 
	 * @author BELDI Jamel  on : 7 sept. 2014  17:26:12
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode")
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * 
	 * [ParcoursIndividualise.setPeriode] Method 
	 * @author BELDI Jamel  on : 7 sept. 2014  17:26:20
	 * @param periode
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	
}
