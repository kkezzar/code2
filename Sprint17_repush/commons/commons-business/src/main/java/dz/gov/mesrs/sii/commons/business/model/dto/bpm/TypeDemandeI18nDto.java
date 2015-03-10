/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.habilitation.ActionDto.java] 
 * @author rlaib on : 29 avr. 2014  15:30:20
 */
package dz.gov.mesrs.sii.commons.business.model.dto.bpm;


import dz.gov.mesrs.sii.commons.data.model.bpm.TypeDemande;

/**
 * @author rlaib  on : 29 avr. 2014  15:30:20
 */
public class TypeDemandeI18nDto implements java.io.Serializable {
	
	/**
	 * serialVersionUID 
	 * @author rlaib  on : 29 avr. 2014  16:02:01
	 */
	private static final long serialVersionUID = -734708056788740366L;
	private int id;
	private String langue;
	private String libelle;
	private TypeDemande typeDemande;
	
	public TypeDemandeI18nDto() {
	}

	/**
	 * [TypeDemandeI18nDto.id] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [TypeDemandeI18nDto.id] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [TypeDemandeI18nDto.langue] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * [TypeDemandeI18nDto.langue] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * [TypeDemandeI18nDto.libelle] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [TypeDemandeI18nDto.libelle] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [TypeDemandeI18nDto.typeDemande] Getter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @return the typeDemande
	 */
	public TypeDemande getTypeDemande() {
		return typeDemande;
	}

	/**
	 * [TypeDemandeI18nDto.typeDemande] Setter 
	 * @author rlaib on : 29 avr. 2014  16:01:51
	 * @param typeDemande the typeDemande to set
	 */
	public void setTypeDemande(TypeDemande typeDemande) {
		this.typeDemande = typeDemande;
	}

	
}
