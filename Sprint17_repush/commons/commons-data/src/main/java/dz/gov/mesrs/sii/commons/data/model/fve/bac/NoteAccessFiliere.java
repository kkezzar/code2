package dz.gov.mesrs.sii.commons.data.model.fve.bac;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the note_access_filiere database table.
 * 
 */
@Entity
@Table(name="note_access_filiere", schema = "bac")
@NamedQuery(name="NoteAccessFiliere.findAll", query="SELECT n FROM NoteAccessFiliere n")
public class NoteAccessFiliere implements Serializable {
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "note_access_filiere_id_seq", sequenceName = "bac.note_access_filiere_id_seq", initialValue = 1)
	@Id @GeneratedValue(generator = "note_access_filiere_id_seq")
	private int id;

	@Column(name="note_access")
	private Double noteAccess;

	@Column(name="ref_code_matiere")
	private String refCodeMatiere;

	//bi-directional many-to-one association to PrioriteSerieBac
	@ManyToOne
	@JoinColumn(name="id_priorite_serie_bac")
	private PrioriteSerieBac prioriteSerieBac;

	public NoteAccessFiliere() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getNoteAccess() {
		return this.noteAccess;
	}

	public void setNoteAccess(Double noteAccess) {
		this.noteAccess = noteAccess;
	}

	public String getRefCodeMatiere() {
		return this.refCodeMatiere;
	}

	public void setRefCodeMatiere(String refCodeMatiere) {
		this.refCodeMatiere = refCodeMatiere;
	}

	public PrioriteSerieBac getPrioriteSerieBac() {
		return this.prioriteSerieBac;
	}

	public void setPrioriteSerieBac(PrioriteSerieBac prioriteSerieBac) {
		this.prioriteSerieBac = prioriteSerieBac;
	}

}