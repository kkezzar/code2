/**
 * [dz.gov.mesrs.sii.commons.data.model.fve.examen.NoteEliminatoire.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:20:24
 */
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

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OuvertureOffreFormation;
import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.Periode;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:20:24
 */
@Entity
@Table(name = "note_eliminatoire", schema = "fve_examen")
public class NoteEliminatoire implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private OuvertureOffreFormation oof;
	private Periode periode;
	private ExamenSession examenSession;
	private double noteEliminatoire;
	private double noteEliminatoireAjuste;

	public NoteEliminatoire() {
		super();
	}

	/**
	 * [NoteEliminatoire.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @return the id
	 */
	@SequenceGenerator(name = "note_eliminatoire_id_seq", sequenceName = "fve_examen.note_eliminatoire_id_seq")
	@Id
	@GeneratedValue(generator = "note_eliminatoire_id_seq")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * [NoteEliminatoire.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [NoteEliminatoire.oof] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @return the oof
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_oof", nullable = false)
	public OuvertureOffreFormation getOof() {
		return oof;
	}

	/**
	 * [NoteEliminatoire.oof] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @param oof
	 *            the oof to set
	 */
	public void setOof(OuvertureOffreFormation oof) {
		this.oof = oof;
	}

	/**
	 * [NoteEliminatoire.periode] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [NoteEliminatoire.periode] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @param periode
	 *            the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}


	/**
	 * [NoteEliminatoire.examenSession] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:54:43
	 * @return the examenSession
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen_session", nullable = false)
	public ExamenSession getExamenSession() {
		return examenSession;
	}

	/**
	 * [NoteEliminatoire.examenSession] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:54:43
	 * @param examenSession the examenSession to set
	 */
	public void setExamenSession(ExamenSession examenSession) {
		this.examenSession = examenSession;
	}

	/**
	 * [NoteEliminatoire.noteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @return the noteEliminatoire
	 */
	@Column(name = "note_eliminatoire", nullable = false)
	public double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [NoteEliminatoire.noteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:22:13
	 * @param noteEliminatoire
	 *            the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	/**
	 * [NoteEliminatoire.noteEliminatoireAjuste] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:30:30
	 * @return the noteEliminatoireAjuste
	 */
	@Column(name = "note_eliminatoire_ajuste")
	public double getNoteEliminatoireAjuste() {
		return noteEliminatoireAjuste;
	}

	/**
	 * [NoteEliminatoire.noteEliminatoireAjuste] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:30:30
	 * @param noteEliminatoireAjuste the noteEliminatoireAjuste to set
	 */
	public void setNoteEliminatoireAjuste(double noteEliminatoireAjuste) {
		this.noteEliminatoireAjuste = noteEliminatoireAjuste;
	}
	
	

}
