package dz.gov.mesrs.sii.fve.business.model.dto.cursus;




public class LigneReleveDeNotesDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int idReleveDeNotes;
	private String libelleMatiereFr;
	private String libelleMatiereAr;
	private Double note;
	private Double noteBase;
	private Short rang;

	public LigneReleveDeNotesDto() {
	}

	public LigneReleveDeNotesDto(int id) {
		this.id = id;
	}

	/**
	 * [LigneReleveDeNotesDto.id] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [LigneReleveDeNotesDto.id] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	

	

	/**
	 * [LigneReleveDeNotesDto.idReleveDeNotes] Getter 
	 * @author yselmane on : 28 mai 2014  08:47:57
	 * @return the idReleveDeNotes
	 */
	public int getIdReleveDeNotes() {
		return idReleveDeNotes;
	}

	/**
	 * [LigneReleveDeNotesDto.idReleveDeNotes] Setter 
	 * @author yselmane on : 28 mai 2014  08:47:57
	 * @param idReleveDeNotes the idReleveDeNotes to set
	 */
	public void setIdReleveDeNotes(int idReleveDeNotes) {
		this.idReleveDeNotes = idReleveDeNotes;
	}

	/**
	 * [LigneReleveDeNotesDto.libelleMatiereFr] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the libelleMatiereFr
	 */
	public String getLibelleMatiereFr() {
		return libelleMatiereFr;
	}

	/**
	 * [LigneReleveDeNotesDto.libelleMatiereFr] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param libelleMatiereFr the libelleMatiereFr to set
	 */
	public void setLibelleMatiereFr(String libelleMatiereFr) {
		this.libelleMatiereFr = libelleMatiereFr;
	}

	/**
	 * [LigneReleveDeNotesDto.libelleMatiereAr] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the libelleMatiereAr
	 */
	public String getLibelleMatiereAr() {
		return libelleMatiereAr;
	}

	/**
	 * [LigneReleveDeNotesDto.libelleMatiereAr] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param libelleMatiereAr the libelleMatiereAr to set
	 */
	public void setLibelleMatiereAr(String libelleMatiereAr) {
		this.libelleMatiereAr = libelleMatiereAr;
	}

	/**
	 * [LigneReleveDeNotesDto.note] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the note
	 */
	public Double getNote() {
		return note;
	}

	/**
	 * [LigneReleveDeNotesDto.note] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param note the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
	}

	/**
	 * [LigneReleveDeNotesDto.noteBase] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the noteBase
	 */
	public Double getNoteBase() {
		return noteBase;
	}

	/**
	 * [LigneReleveDeNotesDto.noteBase] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param noteBase the noteBase to set
	 */
	public void setNoteBase(Double noteBase) {
		this.noteBase = noteBase;
	}

	/**
	 * [LigneReleveDeNotesDto.rang] Getter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @return the rang
	 */
	public Short getRang() {
		return rang;
	}

	/**
	 * [LigneReleveDeNotesDto.rang] Setter 
	 * @author yselmane on : 22 mai 2014  09:52:11
	 * @param rang the rang to set
	 */
	public void setRang(Short rang) {
		this.rang = rang;
	}

	
	
}
