package dz.gov.mesrs.sii.commons.data.model.fve.examen;

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
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.AnneeAcademique;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Niveau;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;
import dz.gov.mesrs.sii.commons.data.model.referentiel.Nomenclature;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:37
 */
@Entity
@Table(name = "planning_session", schema = "fve_examen")
public class PlanningSession implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 17 sept. 2014 16:17:50
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Periode periode;
	private AnneeAcademique anneeAcademique;
	private OuvertureOffreFormation ouvertureOffreFormation;
	private Niveau niveau;
	private SituationEntite situationEntite;
	private String intitule;
	private Date dateDebut;
	private Date dateFin;
	private int numeroSession;
	private Date dateCreation;
	private Date datePublication;
	private Date dateCloture;
	private boolean avecControleContinu;
	private boolean avecControleIntermediaire;
	private boolean notesValide;
	private double coefficient;
	private PlanningSession sessionARemplacer;
	private Nomenclature ncTypeSession;
	private Set<ExamenSession> examenSessions = new HashSet<ExamenSession>(0);
	private double coefficientNoteEliminatoire;
	
	public PlanningSession() {
	}
	
	public PlanningSession(long id) {
		this.id=id;
	}
	
	public PlanningSession(long id, Periode periode,
			AnneeAcademique anneeAcademique,
			OuvertureOffreFormation ouvertureOffreFormation, Niveau niveau,
			SituationEntite situationEntite, String intitule, Date dateDebut,
			Date dateFin, int numeroSession) {
		this.id = id;
		this.periode = periode;
		this.anneeAcademique = anneeAcademique;
		this.ouvertureOffreFormation = ouvertureOffreFormation;
		this.niveau = niveau;
		this.situationEntite = situationEntite;
		this.intitule = intitule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.numeroSession = numeroSession;
	}

	public PlanningSession(int id, Periode periode,
			AnneeAcademique anneeAcademique,
			OuvertureOffreFormation ouvertureOffreFormation, Niveau niveau,
			SituationEntite situationEntite, String intitule, Date dateDebut,
			Date dateFin, int numeroSession, String refCodeTypeSession,
			Date dateCreation, Date datePublication, Date dateCloture,
			Set<ExamenSession> examenSessions) {
		this.id = id;
		this.periode = periode;
		this.anneeAcademique = anneeAcademique;
		this.ouvertureOffreFormation = ouvertureOffreFormation;
		this.niveau = niveau;
		this.situationEntite = situationEntite;
		this.intitule = intitule;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.numeroSession = numeroSession;
		this.dateCreation = dateCreation;
		this.datePublication = datePublication;
		this.dateCloture = dateCloture;
		this.examenSessions = examenSessions;
	}

	@Id
	@SequenceGenerator(name = "planning_session_id_seq_gen", sequenceName = "fve_examen.planning_session_id_seq")
	@GeneratedValue(generator = "planning_session_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return this.periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return this.anneeAcademique;
	}

	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ouverture_offre", nullable = false)
	public OuvertureOffreFormation getOuvertureOffreFormation() {
		return this.ouvertureOffreFormation;
	}

	public void setOuvertureOffreFormation(
			OuvertureOffreFormation ouvertureOffreFormation) {
		this.ouvertureOffreFormation = ouvertureOffreFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_niveau", nullable = false)
	public Niveau getNiveau() {
		return this.niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_situation", nullable = false)
	public SituationEntite getSituationEntite() {
		return this.situationEntite;
	}

	public void setSituationEntite(SituationEntite situationEntite) {
		this.situationEntite = situationEntite;
	}

	@Column(name = "intitule", nullable = false)
	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_debut", nullable = false, length = 13)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_fin", nullable = false, length = 13)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "numero_session", nullable = false)
	public int getNumeroSession() {
		return this.numeroSession;
	}

	public void setNumeroSession(int numeroSession) {
		this.numeroSession = numeroSession;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 35)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_publication", length = 35)
	public Date getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_cloture", length = 35)
	public Date getDateCloture() {
		return this.dateCloture;
	}

	public void setDateCloture(Date dateCloture) {
		this.dateCloture = dateCloture;
	}

	/**
	 * [PlanningSession.avecControleContinu] Getter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:55:29
	 * @return the avecControleContinu
	 */
	@Column(name = "avec_controle_continu")
	public boolean getAvecControleContinu() {
		return avecControleContinu;
	}

	/**
	 * [PlanningSession.avecControleContinu] Setter
	 * 
	 * @author MAKERRI Sofiane on : 25 oct. 2014 11:55:29
	 * @param avecControleContinu
	 *            the avecControleContinu to set
	 */
	public void setAvecControleContinu(boolean avecControleContinu) {
		this.avecControleContinu = avecControleContinu;
	}
	

	/**
	 * [PlanningSession.avecControleIntermediaire] Getter 
	 * @author MAKERRI Sofiane on : 7 janv. 2015  15:29:43
	 * @return the avecControleIntermediaire
	 */
	@Column(name = "avec_controle_intermediaire")
	public boolean isAvecControleIntermediaire() {
		return avecControleIntermediaire;
	}

	/**
	 * [PlanningSession.avecControleIntermediaire] Setter 
	 * @author MAKERRI Sofiane on : 7 janv. 2015  15:29:43
	 * @param avecControleIntermediaire the avecControleIntermediaire to set
	 */
	public void setAvecControleIntermediaire(boolean avecControleIntermediaire) {
		this.avecControleIntermediaire = avecControleIntermediaire;
	}

	/**
	 * [PlanningSession.coefficient] Getter 
	 * @author MAKERRI Sofiane on : 13 nov. 2014  12:37:41
	 * @return the coefficient
	 */
	@Column(name = "coefficient")
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * [PlanningSession.coefficient] Setter 
	 * @author MAKERRI Sofiane on : 13 nov. 2014  12:37:41
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * [PlanningSession.notesValide] Getter 
	 * @author MAKERRI Sofiane on : 5 nov. 2014  10:28:21
	 * @return the notesValide
	 */
	@Column(name = "notes_valide")
	public boolean getNotesValide() {
		return notesValide;
	}

	/**
	 * [PlanningSession.notesValide] Setter 
	 * @author MAKERRI Sofiane on : 5 nov. 2014  10:28:21
	 * @param notesValide the notesValide to set
	 */
	public void setNotesValide(boolean notesValide) {
		this.notesValide = notesValide;
	}

	

	/**
	 * [PlanningSession.examenSessions] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  09:46:46
	 * @return the examenSessions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planningSession")
	public Set<ExamenSession> getExamenSessions() {
		return examenSessions;
	}

	/**
	 * [PlanningSession.examenSessions] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  09:46:46
	 * @param examenSessions the examenSessions to set
	 */
	public void setExamenSessions(Set<ExamenSession> examenSessions) {
		this.examenSessions = examenSessions;
	}

	/**
	 * [PlanningSession.sessionARemplacer] Getter 
	 * @author MAKERRI Sofiane on : 16 nov. 2014  09:31:50
	 * @return the sessionARemplacer
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_session_a_remplacer")
	public PlanningSession getSessionARemplacer() {
		return sessionARemplacer;
	}

	/**
	 * [PlanningSession.sessionARemplacer] Setter 
	 * @author MAKERRI Sofiane on : 16 nov. 2014  09:31:50
	 * @param sessionARemplacer the sessionARemplacer to set
	 */
	public void setSessionARemplacer(PlanningSession sessionARemplacer) {
		this.sessionARemplacer = sessionARemplacer;
	}

	/**
	 * [PlanningSession.ncTypeSession] Getter 
	 * @author MAKERRI Sofiane on : 17 nov. 2014  08:33:32
	 * @return the ncTypeSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nc_type_session")
	public Nomenclature getNcTypeSession() {
		return ncTypeSession;
	}

	/**
	 * [PlanningSession.ncTypeSession] Setter 
	 * @author MAKERRI Sofiane on : 17 nov. 2014  08:33:32
	 * @param ncTypeSession the ncTypeSession to set
	 */
	public void setNcTypeSession(Nomenclature ncTypeSession) {
		this.ncTypeSession = ncTypeSession;
	}

	/**
	 * [PlanningSession.coefficientNoteEliminatoire] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:22:54
	 * @return the coefficientNoteEliminatoire
	 */
	@Column(name = "coefficient_note_eliminatoire")
	public double getCoefficientNoteEliminatoire() {
		return coefficientNoteEliminatoire;
	}

	/**
	 * [PlanningSession.coefficientNoteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:22:54
	 * @param coefficientNoteEliminatoire the coefficientNoteEliminatoire to set
	 */
	public void setCoefficientNoteEliminatoire(double coefficientNoteEliminatoire) {
		this.coefficientNoteEliminatoire = coefficientNoteEliminatoire;
	}
	
	

}
