/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.NoteModel.java] 
 * @author MAKERRI Sofiane on : 18 oct. 2014  17:44:15
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;
import java.util.List;

import dz.gov.mesrs.sii.fve.business.model.dto.cursus.DossierInscriptionAdministrativeDto;
import dz.gov.mesrs.sii.fve.business.model.dto.examen.InscriptionExamenDto;

/**
 * @author MAKERRI Sofiane  on : 18 oct. 2014  17:44:15
 */
public class NoteEtudiantModel implements Serializable {

	/**
	 * serialVersionUID 
	 * @author MAKERRI Sofiane  on : 18 oct. 2014  17:44:19
	 */
	private static final long serialVersionUID = 1L;
	private DossierInscriptionAdministrativeDto etudiant;
	private List<InscriptionExamenDto> notes;
	private Double moyenne;

	/**
	 * [NoteModel.NoteModel()] Constructor
	 * @author MAKERRI Sofiane  on : 18 oct. 2014  17:44:15	
	 */
	public NoteEtudiantModel() {
		super();
	}

	/**
	 * [NoteModel.etudiant] Getter 
	 * @author MAKERRI Sofiane on : 18 oct. 2014  17:45:19
	 * @return the etudiant
	 */
	public DossierInscriptionAdministrativeDto getEtudiant() {
		return etudiant;
	}


	/**
	 * [NoteModel.etudiant] Setter 
	 * @author MAKERRI Sofiane on : 18 oct. 2014  17:45:19
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(DossierInscriptionAdministrativeDto etudiant) {
		this.etudiant = etudiant;
	}


	/**
	 * [NoteModel.notes] Getter 
	 * @author MAKERRI Sofiane on : 18 oct. 2014  17:45:19
	 * @return the notes
	 */
	public List<InscriptionExamenDto> getNotes() {
		return notes;
	}


	/**
	 * [NoteModel.notes] Setter 
	 * @author MAKERRI Sofiane on : 18 oct. 2014  17:45:19
	 * @param notes the notes to set
	 */
	public void setNotes(List<InscriptionExamenDto> notes) {
		this.notes = notes;
	}


	/**
	 * [NoteEtudiantModel.moyenne] Getter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:01:56
	 * @return the moyenne
	 */
	public Double getMoyenne() {
		return moyenne;
	}


	/**
	 * [NoteEtudiantModel.moyenne] Setter 
	 * @author MAKERRI Sofiane on : 19 oct. 2014  08:01:56
	 * @param moyenne the moyenne to set
	 */
	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}
	
	
	
	

}
