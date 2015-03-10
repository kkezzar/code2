package dz.gov.mesrs.sii.commons.data.model.fve.bac;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the priorite_serie_bac database table.
 * 
 */
@Entity
@Table(name="priorite_serie_bac", schema = "bac")
@NamedQuery(name="PrioriteSerieBac.findAll", query="SELECT p FROM PrioriteSerieBac p")
public class PrioriteSerieBac implements Serializable {
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "priorite_serie_bac_id_seq", sequenceName = "bac.priorite_serie_bac_id_seq", initialValue = 1)
	@Id @GeneratedValue(generator = "priorite_serie_bac_id_seq")
	private int id;

	private Double moyenne;

	private Integer priorite;

	@Column(name="ref_code_serie_bac")
	private String refCodeSerieBac;

	//bi-directional many-to-one association to NoteAccessFiliere
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy="prioriteSerieBac")
	private List<NoteAccessFiliere> noteAccessFilieres;

	//bi-directional many-to-one association to CapaciteEtab
	@ManyToOne
	@JoinColumn(name="id_capacite_etab")
	private CapaciteEtab capaciteEtab;

	public PrioriteSerieBac() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getMoyenne() {
		return this.moyenne;
	}

	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

	public Integer getPriorite() {
		return this.priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public String getRefCodeSerieBac() {
		return this.refCodeSerieBac;
	}

	public void setRefCodeSerieBac(String refCodeSerieBac) {
		this.refCodeSerieBac = refCodeSerieBac;
	}

	public List<NoteAccessFiliere> getNoteAccessFilieres() {
		return this.noteAccessFilieres;
	}

	public void setNoteAccessFilieres(List<NoteAccessFiliere> noteAccessFilieres) {
		this.noteAccessFilieres = noteAccessFilieres;
	}

	public NoteAccessFiliere addNoteAccessFiliere(NoteAccessFiliere noteAccessFiliere) {
		getNoteAccessFilieres().add(noteAccessFiliere);
		noteAccessFiliere.setPrioriteSerieBac(this);

		return noteAccessFiliere;
	}

	public NoteAccessFiliere removeNoteAccessFiliere(NoteAccessFiliere noteAccessFiliere) {
		getNoteAccessFilieres().remove(noteAccessFiliere);
		noteAccessFiliere.setPrioriteSerieBac(null);

		return noteAccessFiliere;
	}

	public CapaciteEtab getCapaciteEtab() {
		return this.capaciteEtab;
	}

	public void setCapaciteEtab(CapaciteEtab capaciteEtab) {
		this.capaciteEtab = capaciteEtab;
	}

}