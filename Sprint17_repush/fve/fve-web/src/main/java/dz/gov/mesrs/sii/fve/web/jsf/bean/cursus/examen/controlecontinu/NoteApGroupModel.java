/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu.NoteApGroupModel.java] 
 * @author MAKERRI Sofiane on : 16 oct. 2014  08:31:11
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;

/**
 * @author MAKERRI Sofiane  on : 16 oct. 2014  08:31:11
 */
public class NoteApGroupModel implements Serializable{
	
	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  08:31:29
	 */
	private static final long serialVersionUID = 1L;
	NoteEvaluationControleContinuDto moyenneAp;
	List<NoteEvaluationControleContinuDto> noteAp;

	/**
	 * [NoteApGroupModel.NoteApGroupModel()] Constructor
	 * @author MAKERRI Sofiane  on : 16 oct. 2014  08:31:11	
	 */
	public NoteApGroupModel() {
		super();
	}

	/**
	 * [NoteApGroupModel.moyenneAp] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:33:16
	 * @return the moyenneAp
	 */
	public NoteEvaluationControleContinuDto getMoyenneAp() {
		return moyenneAp;
	}

	/**
	 * [NoteApGroupModel.moyenneAp] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:33:16
	 * @param moyenneAp the moyenneAp to set
	 */
	public void setMoyenneAp(NoteEvaluationControleContinuDto moyenneAp) {
		this.moyenneAp = moyenneAp;
	}

	/**
	 * [NoteApGroupModel.noteAp] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:33:16
	 * @return the noteAp
	 */
	public List<NoteEvaluationControleContinuDto> getNoteAp() {
		return noteAp;
	}

	/**
	 * [NoteApGroupModel.noteAp] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:33:16
	 * @param noteAp the noteAp to set
	 */
	public void setNoteAp(List<NoteEvaluationControleContinuDto> noteAp) {
		this.noteAp = noteAp;
	}
	
	
	

}
