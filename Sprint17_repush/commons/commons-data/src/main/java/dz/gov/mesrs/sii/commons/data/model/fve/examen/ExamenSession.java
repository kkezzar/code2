package dz.gov.mesrs.sii.commons.data.model.fve.examen;

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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.RattachementMc;

/**
 * 
 * @author BELDI Jamel on : 17 sept. 2014 16:17:13
 */

@Entity
@Table(name = "examen_session", schema = "fve_examen")
public class ExamenSession implements java.io.Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author BELDI Jamel on : 17 sept. 2014 16:17:58
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private RattachementMc rattachementMc;
	private PlanningSession planningSession;
	private Date dateExamen;
	private Date heureDebut;
	private Date heureFin;
	private int duree;
	private Boolean anonymat;
	private Date heureDebutReelle;
	private Date heureFinReelle;
	private String incident;
	private Integer nbCopieRemises;
	private String refTypeExamen;
	private Date dateCreation;
	private String refModeInscription;
	private Date dateExamenReelle;
	private List<SalleExamen> salleExamens;
	private double moyenneSession;

	public ExamenSession() {
	}

	public ExamenSession(int id) {
		this.id = id;
	}

	public ExamenSession(int id, RattachementMc rattachementMc, PlanningSession planningSession, Date dateExamen,
	        Date heureDebut) {
		this.id = id;
		this.rattachementMc = rattachementMc;
		this.planningSession = planningSession;
		this.dateExamen = dateExamen;
		this.heureDebut = heureDebut;

	}

	public ExamenSession(int id, RattachementMc rattachementMc, PlanningSession planningSession, Date dateExamen,
	        Date heureDebut, int duree) {
		this.id = id;
		this.rattachementMc = rattachementMc;
		this.planningSession = planningSession;
		this.dateExamen = dateExamen;
		this.heureDebut = heureDebut;
		this.duree = duree;
	}

	public ExamenSession(int id, RattachementMc rattachementMc, PlanningSession planningSession, Date dateExamen,
	        Date heureDebut, Date heureFin, int duree, Boolean anonymat, Date heureDebutReelle, Date heureFinReelle,
	        String incident, Integer nbCopieRemises, String refTypeExamen, String refLieu, Date dateCreation,
	        String refModeInscription) {
		this.id = id;
		this.rattachementMc = rattachementMc;
		this.planningSession = planningSession;
		this.dateExamen = dateExamen;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.duree = duree;
		this.anonymat = anonymat;
		this.heureDebutReelle = heureDebutReelle;
		this.heureFinReelle = heureFinReelle;
		this.incident = incident;
		this.nbCopieRemises = nbCopieRemises;
		this.refTypeExamen = refTypeExamen;
		this.dateCreation = dateCreation;
		this.refModeInscription = refModeInscription;
	}

	@Id
	@SequenceGenerator(name = "examen_session_id_seq_gen", sequenceName = "fve_examen.examen_session_id_seq")
	@GeneratedValue(generator = "examen_session_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rattachement_mc", nullable = false)
	public RattachementMc getRattachementMc() {
		return this.rattachementMc;
	}

	public void setRattachementMc(RattachementMc rattachementMc) {
		this.rattachementMc = rattachementMc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_planning_session", nullable = false)
	public PlanningSession getPlanningSession() {
		return this.planningSession;
	}

	public void setPlanningSession(PlanningSession planningSession) {
		this.planningSession = planningSession;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_examen", nullable = false, length = 13)
	public Date getDateExamen() {
		return this.dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_debut", nullable = false, length = 15)
	public Date getHeureDebut() {
		return this.heureDebut;
	}

	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_fin", length = 15)
	public Date getHeureFin() {
		return this.heureFin;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

	@Column(name = "duree", nullable = false)
	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	@Column(name = "anonymat")
	public Boolean getAnonymat() {
		return this.anonymat;
	}

	public void setAnonymat(Boolean anonymat) {
		this.anonymat = anonymat;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_debut_reelle", length = 15)
	public Date getHeureDebutReelle() {
		return this.heureDebutReelle;
	}

	public void setHeureDebutReelle(Date heureDebutReelle) {
		this.heureDebutReelle = heureDebutReelle;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "heure_fin_reelle", length = 15)
	public Date getHeureFinReelle() {
		return this.heureFinReelle;
	}

	public void setHeureFinReelle(Date heureFinReelle) {
		this.heureFinReelle = heureFinReelle;
	}

	@Column(name = "incident")
	public String getIncident() {
		return this.incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	@Column(name = "nb_copie_remises")
	public Integer getNbCopieRemises() {
		return this.nbCopieRemises;
	}

	public void setNbCopieRemises(Integer nbCopieRemises) {
		this.nbCopieRemises = nbCopieRemises;
	}

	@Column(name = "ref_type_examen")
	public String getRefTypeExamen() {
		return this.refTypeExamen;
	}

	public void setRefTypeExamen(String refTypeExamen) {
		this.refTypeExamen = refTypeExamen;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", length = 35)
	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column(name = "ref_mode_inscription")
	public String getRefModeInscription() {
		return this.refModeInscription;
	}

	public void setRefModeInscription(String refModeInscription) {
		this.refModeInscription = refModeInscription;
	}

	/**
	 * [ExamenSession.dateExamenReelle] Getter
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 16:52:29
	 * @return the dateExamenReelle
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date_examen_reelle", length = 13)
	public Date getDateExamenReelle() {
		return dateExamenReelle;
	}

	/**
	 * [ExamenSession.dateExamenReelle] Setter
	 * 
	 * @author BELDI Jamel on : 30 sept. 2014 16:52:29
	 * @param dateExamenReelle
	 *            the dateExamenReelle to set
	 */
	public void setDateExamenReelle(Date dateExamenReelle) {
		this.dateExamenReelle = dateExamenReelle;
	}

	/**
	 * [ExamenSession.salleExamens] Getter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:42:05
	 * @return the salleExamens
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "examenSession", cascade = CascadeType.ALL)
	public List<SalleExamen> getSalleExamens() {
		return salleExamens;
	}

	/**
	 * [ExamenSession.salleExamens] Setter
	 * 
	 * @author MAKERRI Sofiane on : 19 nov. 2014 09:42:05
	 * @param salleExamens
	 *            the salleExamens to set
	 */
	public void setSalleExamens(List<SalleExamen> salleExamens) {
		this.salleExamens = salleExamens;
	}

	/**
	 * [ExamenSession.moyenneSession] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  08:26:22
	 * @return the moyenneSession
	 */
	@Column(name = "moyenne_session")
	public double getMoyenneSession() {
		return moyenneSession;
	}

	/**
	 * [ExamenSession.moyenneSession] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  08:26:22
	 * @param moyenneSession the moyenneSession to set
	 */
	public void setMoyenneSession(double moyenneSession) {
		this.moyenneSession = moyenneSession;
	}
	
	

}
