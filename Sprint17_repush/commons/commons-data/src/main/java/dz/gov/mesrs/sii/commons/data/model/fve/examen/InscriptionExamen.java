package dz.gov.mesrs.sii.commons.data.model.fve.examen;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.DossierInscriptionAdministrative;

/**
 * 
 * @author BELDI Jamel  on : 17 sept. 2014  16:17:29
 */
@Entity
@Table(name = "inscription_examen", schema = "fve_examen")
public class InscriptionExamen implements java.io.Serializable {

	/**
	 * serialVersionUID 
	 * @author BELDI Jamel  on : 17 sept. 2014  16:18:05
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private SalleExamen salleExamen;
	private DossierInscriptionAdministrative dossierInscriptionAdministrative;
	private boolean copieNonRemise;
	private boolean absent;
	private String motifAbsence;
	private boolean absenceJustifie;
	private Double noteExamen;
	private Double noteJury;
	private Double moyenneControleContinu;
	private Double moyenneGenerale;


	public InscriptionExamen() {
	}

	public InscriptionExamen(int id, SalleExamen salleExamen,
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.id = id;
		this.salleExamen = salleExamen;
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	public InscriptionExamen(int id, SalleExamen salleExamen,
			DossierInscriptionAdministrative dossierInscriptionAdministrative,
			Boolean copieNonRemise, Boolean absent, String motifAbsence,
			Boolean absenceJustifie) {
		this.id = id;
		this.salleExamen = salleExamen;
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
		this.copieNonRemise = copieNonRemise;
		this.absent = absent;
		this.motifAbsence = motifAbsence;
		this.absenceJustifie = absenceJustifie;
	}

	@Id
	@SequenceGenerator(name="inscription_examen_id_seq_gen", sequenceName="fve_examen.inscription_examen_id_seq")
	@GeneratedValue(generator="inscription_examen_id_seq_gen")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_salle_examen", nullable = false)
	public SalleExamen getSalleExamen() {
		return this.salleExamen;
	}

	public void setSalleExamen(SalleExamen salleExamen) {
		this.salleExamen = salleExamen;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dossier_inscription_administrative", nullable = false)
	public DossierInscriptionAdministrative getDossierInscriptionAdministrative() {
		return this.dossierInscriptionAdministrative;
	}

	public void setDossierInscriptionAdministrative(
			DossierInscriptionAdministrative dossierInscriptionAdministrative) {
		this.dossierInscriptionAdministrative = dossierInscriptionAdministrative;
	}

	@Column(name = "copie_non_remise")
	public boolean getCopieNonRemise() {
		return this.copieNonRemise;
	}

	public void setCopieNonRemise(boolean copieNonRemise) {
		this.copieNonRemise = copieNonRemise;
	}

	@Column(name = "absent")
	public boolean getAbsent() {
		return this.absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	@Column(name = "motif_absence")
	public String getMotifAbsence() {
		return this.motifAbsence;
	}

	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

	@Column(name = "absence_justifie")
	public boolean getAbsenceJustifie() {
		return this.absenceJustifie;
	}

	public void setAbsenceJustifie(boolean absenceJustifie) {
		this.absenceJustifie = absenceJustifie;
	}
	
	@Column(name = "note_examen", precision = 17, scale = 17)
	public Double getNoteExamen() {
		return this.noteExamen;
	}

	public void setNoteExamen(Double noteExamen) {
		this.noteExamen = noteExamen;
	}

	@Column(name = "note_jury", precision = 17, scale = 17)
	public Double getNoteJury() {
		return this.noteJury;
	}

	public void setNoteJury(Double noteJury) {
		this.noteJury = noteJury;
	}

	/**
	 * [InscriptionExamen.moyenneControleContinu] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:45:05
	 * @return the moyenneControleContinu
	 */
	@Column(name = "moyenne_cc", precision = 17, scale = 17)
	public Double getMoyenneControleContinu() {
		return moyenneControleContinu;
	}

	/**
	 * [InscriptionExamen.moyenneControleContinu] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:45:05
	 * @param moyenneControleContinu the moyenneControleContinu to set
	 */
	public void setMoyenneControleContinu(Double moyenneControleContinu) {
		this.moyenneControleContinu = moyenneControleContinu;
	}

	/**
	 * [InscriptionExamen.moyenneGenerale] Getter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:45:05
	 * @return the moyenneGenerale
	 */
	@Column(name = "moyenne_generale", precision = 17, scale = 17)
	public Double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	/**
	 * [InscriptionExamen.moyenneGenerale] Setter 
	 * @author MAKERRI Sofiane on : 16 déc. 2014  13:45:05
	 * @param moyenneGenerale the moyenneGenerale to set
	 */
	public void setMoyenneGenerale(Double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}
	
	

}
