/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;



import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDecision;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class TypeDecisionI18nDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  16:00:05
	 */
	private static final long serialVersionUID = 164190950523049212L;
	private int id;
	private String langue;
	private String libelle;
	private TypeDecision typeDecision;
	
	public TypeDecisionI18nDto() {
	}

	/**
	 * [TypeDecisionI18nDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeDecisionI18nDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeDecisionI18nDto.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [TypeDecisionI18nDto.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [TypeDecisionI18nDto.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [TypeDecisionI18nDto.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [TypeDecisionI18nDto.typeDecision] Getter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @return the typeDecision
	 */
	public TypeDecision getTypeDecision() {
		return typeDecision;
	}

	/**
	 * [TypeDecisionI18nDto.typeDecision] Setter 
	 * @author rlaib on : 29 avr. 2014  16:00:00
	 * @param typeDecision the typeDecision to set
	 */
	public void setTypeDecision(TypeDecision typeDecision) {
		this.typeDecision = typeDecision;
	}

	
}
