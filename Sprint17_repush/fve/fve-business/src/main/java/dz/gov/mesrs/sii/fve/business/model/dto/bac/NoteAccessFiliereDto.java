package dz.gov.mesrs.sii.fve.business.model.dto.bac;

/**
 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:56:14
 */
public class NoteAccessFiliereDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:53:06
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	// PrioriteSerieBac
	private Integer idPrioriteSerieBac;

	private String refCodeMatiere;
	private String matiereLibelleLt;
	private String matiereLibelleAr;

	private Double noteAccess;

	public NoteAccessFiliereDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdPrioriteSerieBac() {
		return idPrioriteSerieBac;
	}

	public void setIdPrioriteSerieBac(Integer idPrioriteSerieBac) {
		this.idPrioriteSerieBac = idPrioriteSerieBac;
	}

	public String getRefCodeMatiere() {
		return refCodeMatiere;
	}

	public void setRefCodeMatiere(String refCodeMatiere) {
		this.refCodeMatiere = refCodeMatiere;
	}

	public String getMatiereLibelleLt() {
		return matiereLibelleLt;
	}

	public void setMatiereLibelleLt(String matiereLibelleLt) {
		this.matiereLibelleLt = matiereLibelleLt;
	}

	public String getMatiereLibelleAr() {
		return matiereLibelleAr;
	}

	public void setMatiereLibelleAr(String matiereLibelleAr) {
		this.matiereLibelleAr = matiereLibelleAr;
	}

	public Double getNoteAccess() {
		return this.noteAccess;
	}

	public void setNoteAccess(Double noteAccess) {
		this.noteAccess = noteAccess;
	}
	
	/**
	 * 
	 */
	public boolean equals(Object o) {
		if (!(o instanceof NoteAccessFiliereDto))
			return false;
		NoteAccessFiliereDto other = (NoteAccessFiliereDto) o;
		if (this.idPrioriteSerieBac == other.idPrioriteSerieBac) {
			if (this.refCodeMatiere != null && other.refCodeMatiere != null
					&& this.refCodeMatiere.equals(other.refCodeMatiere)) {
				return true;
			}
		}
		return false;
	}
}
