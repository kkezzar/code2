package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.util.List;

/**
 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:56:32
 */
public class PrioriteSerieBacDto implements java.io.Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 14 aout 2014 11:54:37
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Integer idCapaciteEtab;
	private Integer priorite;
	private Double moyenne;

	// SerieBac
	private String refCodeSerieBac;
	private String serieBacLibelleLt;
	private String serieBacLibelleAr;

	private List<NoteAccessFiliereDto> noteAccessFilieresDto;

	public PrioriteSerieBacDto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCapaciteEtab() {
		return idCapaciteEtab;
	}

	public void setIdCapaciteEtab(Integer idCapaciteEtab) {
		this.idCapaciteEtab = idCapaciteEtab;
	}

	public Integer getPriorite() {
		return this.priorite;
	}

	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	public Double getMoyenne() {
		return this.moyenne;
	}

	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

	public String getRefCodeSerieBac() {
		return this.refCodeSerieBac;
	}

	public void setRefCodeSerieBac(String refCodeSerieBac) {
		this.refCodeSerieBac = refCodeSerieBac;
	}

	public List<NoteAccessFiliereDto> getNoteAccessFilieresDto() {
		return this.noteAccessFilieresDto;
	}

	public void setNoteAccessFilieresDto(
			List<NoteAccessFiliereDto> noteAccessFilieresDto) {
		this.noteAccessFilieresDto = noteAccessFilieresDto;
	}

	public String getSerieBacLibelleLt() {
		return serieBacLibelleLt;
	}

	public void setSerieBacLibelleLt(String serieBacLibelleLt) {
		this.serieBacLibelleLt = serieBacLibelleLt;
	}

	public String getSerieBacLibelleAr() {
		return serieBacLibelleAr;
	}

	public void setSerieBacLibelleAr(String serieBacLibelleAr) {
		this.serieBacLibelleAr = serieBacLibelleAr;
	}

}
