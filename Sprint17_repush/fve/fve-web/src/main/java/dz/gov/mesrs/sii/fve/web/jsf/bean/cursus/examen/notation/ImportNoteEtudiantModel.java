/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation.ImportNoteEtudiantModel.java] 
 * @author MAKERRI Sofiane on : 12 oct. 2014  15:36:51
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.cursus.examen.notation;

import java.io.Serializable;

/**
 * @author MAKERRI Sofiane on : 12 oct. 2014 15:36:51
 */
public class ImportNoteEtudiantModel implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:37:09
	 */
	private static final long serialVersionUID = 1L;
	private String numeroMatricule;
	private String etudiantNomLatin;
	private String etudiantPrenomLatin;
	private String etudiantDateNaissance;
	private Double note;

	/**
	 * [ImportNoteEtudiantModel.ImportNoteEtudiantModel()] Constructor
	 * 
	 * @author MAKERRI Sofiane on : 12 oct. 2014 15:36:51
	 */
	public ImportNoteEtudiantModel() {
		super();
	}

	/**
	 * [ImportNoteEtudiantModel.numeroMatricule] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @return the numeroMatricule
	 */
	public String getNumeroMatricule() {
		return numeroMatricule;
	}


	/**
	 * [ImportNoteEtudiantModel.numeroMatricule] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @param numeroMatricule the numeroMatricule to set
	 */
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantNomLatin] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @return the etudiantNomLatin
	 */
	public String getEtudiantNomLatin() {
		return etudiantNomLatin;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantNomLatin] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @param etudiantNomLatin the etudiantNomLatin to set
	 */
	public void setEtudiantNomLatin(String etudiantNomLatin) {
		this.etudiantNomLatin = etudiantNomLatin;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantPrenomLatin] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @return the etudiantPrenomLatin
	 */
	public String getEtudiantPrenomLatin() {
		return etudiantPrenomLatin;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantPrenomLatin] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:38:44
	 * @param etudiantPrenomLatin the etudiantPrenomLatin to set
	 */
	public void setEtudiantPrenomLatin(String etudiantPrenomLatin) {
		this.etudiantPrenomLatin = etudiantPrenomLatin;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantDateNaissance] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:43:10
	 * @return the etudiantDateNaissance
	 */
	public String getEtudiantDateNaissance() {
		return etudiantDateNaissance;
	}


	/**
	 * [ImportNoteEtudiantModel.etudiantDateNaissance] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:43:10
	 * @param etudiantDateNaissance the etudiantDateNaissance to set
	 */
	public void setEtudiantDateNaissance(String etudiantDateNaissance) {
		this.etudiantDateNaissance = etudiantDateNaissance;
	}


	/**
	 * [ImportNoteEtudiantModel.note] Getter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:43:10
	 * @return the note
	 */
	public Double getNote() {
		return note;
	}


	/**
	 * [ImportNoteEtudiantModel.note] Setter 
	 * @author MAKERRI Sofiane on : 12 oct. 2014  15:43:10
	 * @param note the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
	}


	
	
	

}
