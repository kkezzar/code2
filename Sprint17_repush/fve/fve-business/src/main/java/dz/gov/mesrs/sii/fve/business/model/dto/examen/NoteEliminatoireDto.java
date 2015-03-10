/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEliminatoireDto.java] 
 * @author MAKERRI Sofiane on : 18 janv. 2015  12:32:14
 */
package dz.gov.mesrs.sii.fve.business.model.dto.examen;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane on : 18 janv. 2015 12:32:14
 */

public class NoteEliminatoireDto implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:32:19
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Integer oofId;
	private Integer periodeId;
	private Integer rattachementMcId;
	private Integer rattachementMcCredit;
	private Integer rattachementMcCoefficient;
	private String rattachementMcLibelle;
	private Long planningSessionId;
	private String planningSessionLibelle;
	private String planningSessionType;
	private Long examenSessionId;
	private double noteEliminatoire;
	private Double noteEliminatoireAjuste;

	public NoteEliminatoireDto() {
		super();
	}

	/**
	 * [NoteEliminatoireDto.NoteEliminatoireDto()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 20 janv. 2015 09:58:25
	 * @param oofId
	 * @param periodeId
	 * @param planningSessionId
	 */
	public NoteEliminatoireDto(Integer oofId, Integer periodeId,
			Long examenSessionId,  Long planningSessionId) {
		this.oofId = oofId;
		this.periodeId = periodeId;
		this.examenSessionId = examenSessionId;
		this.planningSessionId= planningSessionId;
	}

	/**
	 * [NoteEliminatoireDto.id] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * [NoteEliminatoireDto.id] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * [NoteEliminatoireDto.oofId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the oofId
	 */
	public Integer getOofId() {
		return oofId;
	}

	/**
	 * [NoteEliminatoireDto.oofId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param oofId
	 *            the oofId to set
	 */
	public void setOofId(Integer oofId) {
		this.oofId = oofId;
	}

	/**
	 * [NoteEliminatoireDto.periodeId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the periodeId
	 */
	public Integer getPeriodeId() {
		return periodeId;
	}

	/**
	 * [NoteEliminatoireDto.periodeId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param periodeId
	 *            the periodeId to set
	 */
	public void setPeriodeId(Integer periodeId) {
		this.periodeId = periodeId;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the rattachementMcId
	 */
	public Integer getRattachementMcId() {
		return rattachementMcId;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param rattachementMcId
	 *            the rattachementMcId to set
	 */
	public void setRattachementMcId(Integer rattachementMcId) {
		this.rattachementMcId = rattachementMcId;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionId] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the planningSessionId
	 */
	public Long getPlanningSessionId() {
		return planningSessionId;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionId] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param planningSessionId
	 *            the planningSessionId to set
	 */
	public void setPlanningSessionId(Long planningSessionId) {
		this.planningSessionId = planningSessionId;
	}

	/**
	 * [NoteEliminatoireDto.noteEliminatoire] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @return the noteEliminatoire
	 */
	public Double getNoteEliminatoire() {
		return noteEliminatoire;
	}

	/**
	 * [NoteEliminatoireDto.noteEliminatoire] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 12:33:34
	 * @param noteEliminatoire
	 *            the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(Double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @return the rattachementMcLibelle
	 */
	public String getRattachementMcLibelle() {
		return rattachementMcLibelle;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @param rattachementMcLibelle
	 *            the rattachementMcLibelle to set
	 */
	public void setRattachementMcLibelle(String rattachementMcLibelle) {
		this.rattachementMcLibelle = rattachementMcLibelle;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionLibelle] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @return the planningSessionLibelle
	 */
	public String getPlanningSessionLibelle() {
		return planningSessionLibelle;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionLibelle] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @param planningSessionLibelle
	 *            the planningSessionLibelle to set
	 */
	public void setPlanningSessionLibelle(String planningSessionLibelle) {
		this.planningSessionLibelle = planningSessionLibelle;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionType] Getter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @return the planningSessionType
	 */
	public String getPlanningSessionType() {
		return planningSessionType;
	}

	/**
	 * [NoteEliminatoireDto.planningSessionType] Setter
	 * 
	 * @author MAKERRI Sofiane on : 18 janv. 2015 13:00:36
	 * @param planningSessionType
	 *            the planningSessionType to set
	 */
	public void setPlanningSessionType(String planningSessionType) {
		this.planningSessionType = planningSessionType;
	}

	/**
	 * [NoteEliminatoireDto.examenSessionId] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:55:47
	 * @return the examenSessionId
	 */
	public Long getExamenSessionId() {
		return examenSessionId;
	}

	/**
	 * [NoteEliminatoireDto.examenSessionId] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  10:55:47
	 * @param examenSessionId the examenSessionId to set
	 */
	public void setExamenSessionId(Long examenSessionId) {
		this.examenSessionId = examenSessionId;
	}

	/**
	 * [NoteEliminatoireDto.noteEliminatoireAjuste] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:31:13
	 * @return the noteEliminatoireAjuste
	 */
	public Double getNoteEliminatoireAjuste() {
		if(noteEliminatoireAjuste == null || noteEliminatoireAjuste == 0) {
			noteEliminatoireAjuste = noteEliminatoire;
		}
		return noteEliminatoireAjuste;
	}

	/**
	 * [NoteEliminatoireDto.noteEliminatoireAjuste] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:31:13
	 * @param noteEliminatoireAjuste the noteEliminatoireAjuste to set
	 */
	public void setNoteEliminatoireAjuste(Double noteEliminatoireAjuste) {
		this.noteEliminatoireAjuste = noteEliminatoireAjuste;
	}

	/**
	 * [NoteEliminatoireDto.noteEliminatoire] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:31:22
	 * @param noteEliminatoire the noteEliminatoire to set
	 */
	public void setNoteEliminatoire(double noteEliminatoire) {
		this.noteEliminatoire = noteEliminatoire;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcCredit] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:39:33
	 * @return the rattachementMcCredit
	 */
	public Integer getRattachementMcCredit() {
		return rattachementMcCredit;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcCredit] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:39:33
	 * @param rattachementMcCredit the rattachementMcCredit to set
	 */
	public void setRattachementMcCredit(Integer rattachementMcCredit) {
		this.rattachementMcCredit = rattachementMcCredit;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcCoefficient] Getter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:39:33
	 * @return the rattachementMcCoefficient
	 */
	public Integer getRattachementMcCoefficient() {
		return rattachementMcCoefficient;
	}

	/**
	 * [NoteEliminatoireDto.rattachementMcCoefficient] Setter 
	 * @author MAKERRI Sofiane on : 20 janv. 2015  11:39:33
	 * @param rattachementMcCoefficient the rattachementMcCoefficient to set
	 */
	public void setRattachementMcCoefficient(Integer rattachementMcCoefficient) {
		this.rattachementMcCoefficient = rattachementMcCoefficient;
	}
	
	

}
