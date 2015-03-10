/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu.MoyenneControleContinuModel.java] 
 * @author MAKERRI Sofiane on : 14 oct. 2014  13:22:35
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.controlecontinu;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.AffectationGroupePedagogiqueDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.NoteEvaluationControleContinuDto;

/**
 * @author MAKERRI Sofiane  on : 14 oct. 2014  13:22:35
 */
public class MoyenneControleContinuModel implements Serializable{

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 14 oct. 2014  13:22:46
	 */
	private static final long serialVersionUID = 1L;
	private NoteEvaluationControleContinuDto evalCC;
	private AffectationGroupePedagogiqueDto affGroupePedagogique;
	List<NoteApGroupModel> noteApList;
	private Double moyenneMc;
	private String formatMoyenneMc;

	/**
	 * [MoyenneControleContinuModel.MoyenneControleContinuModel()] Constructor
	 * @author MAKERRI Sofiane  on : 14 oct. 2014  13:22:35	
	 */
	public MoyenneControleContinuModel() {
		super();
	}

	/**
	 * [MoyenneControleContinuModel.evalCC] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  13:25:37
	 * @return the evalCC
	 */
	public NoteEvaluationControleContinuDto getEvalCC() {
		return evalCC;
	}

	/**
	 * [MoyenneControleContinuModel.evalCC] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  13:25:37
	 * @param evalCC the evalCC to set
	 */
	public void setEvalCC(NoteEvaluationControleContinuDto evalCC) {
		this.evalCC = evalCC;
	}

	/**
	 * [MoyenneControleContinuModel.affGroupePedagogique] Getter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  15:59:00
	 * @return the affGroupePedagogique
	 */
	public AffectationGroupePedagogiqueDto getAffGroupePedagogique() {
		return affGroupePedagogique;
	}
	/**
	 * [MoyenneControleContinuModel.affGroupePedagogique] Setter 
	 * @author MAKERRI Sofiane on : 14 oct. 2014  15:59:00
	 * @param affGroupePedagogique the affGroupePedagogique to set
	 */
	public void setAffGroupePedagogique(
			AffectationGroupePedagogiqueDto affGroupePedagogique) {
		this.affGroupePedagogique = affGroupePedagogique;
	}

	/**
	 * [MoyenneControleContinuModel.noteApList] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:34:20
	 * @return the noteApList
	 */
	public List<NoteApGroupModel> getNoteApList() {
		return noteApList;
	}

	/**
	 * [MoyenneControleContinuModel.noteApList] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  08:34:20
	 * @param noteApList the noteApList to set
	 */
	public void setNoteApList(List<NoteApGroupModel> noteApList) {
		this.noteApList = noteApList;
	}

	/**
	 * [MoyenneControleContinuModel.moyenneMc] Getter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  10:26:22
	 * @return the moyenneMc
	 */
	public Double getMoyenneMc() {
		return moyenneMc;
	}

	

	/**
	 * [MoyenneControleContinuModel.moyenneMc] Setter 
	 * @author MAKERRI Sofiane on : 16 oct. 2014  10:26:22
	 * @param moyenneMc the moyenneMc to set
	 */
	public void setMoyenneMc(Double moyenneMc) {
		this.moyenneMc = moyenneMc;
	}

	/**
	 * [MoyenneControleContinuModel.formatMoyenneMc] Getter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  12:29:20
	 * @return the formatMoyenneMc
	 */
	public String getFormatMoyenneMc() {
		return formatMoyenneMc;
	}

	/**
	 * [MoyenneControleContinuModel.formatMoyenneMc] Setter 
	 * @author MAKERRI Sofiane on : 1 nov. 2014  12:29:20
	 * @param formatMoyenneMc the formatMoyenneMc to set
	 */
	public void setFormatMoyenneMc(String formatMoyenneMc) {
		this.formatMoyenneMc = formatMoyenneMc;
	}

}
