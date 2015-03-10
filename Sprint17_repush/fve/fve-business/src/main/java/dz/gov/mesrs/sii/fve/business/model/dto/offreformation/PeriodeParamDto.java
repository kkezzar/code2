package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.io.Serializable;


/**
 * @author rlaib  on : 2 oct. 2014  13:58:08
 */
public class PeriodeParamDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private String libelle;
	private Object valeur;
	private Object valeurParDefaut;
	
	// Periode 
	private int idPeriode;
	private String libellePeriode;

	// Annee Academique
	private int idAnneeAcademique;
	private String premiereAnnee;
	private String deuxiemeAnnee;
	
	// Annee Academique
	private int idTypeParam;
	private String codeTypeParam;
	private String libelleTypeParam;
	private String masqueTypeParam;
	
	public PeriodeParamDto() {
	}

	/**
	 * [PeriodeParamDto.id] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * [PeriodeParamDto.id] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [PeriodeParamDto.code] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * [PeriodeParamDto.code] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [PeriodeParamDto.libelle] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [PeriodeParamDto.libelle] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [PeriodeParamDto.valeur] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the valeur
	 */
	public Object getValeur() {
		return valeur;
	}

	/**
	 * [PeriodeParamDto.valeur] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param valeur the valeur to set
	 */
	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}

	/**
	 * [PeriodeParamDto.valeurParDefaut] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the valeurParDefaut
	 */
	public Object getValeurParDefaut() {
		return valeurParDefaut;
	}

	/**
	 * [PeriodeParamDto.valeurParDefaut] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param valeurParDefaut the valeurParDefaut to set
	 */
	public void setValeurParDefaut(Object valeurParDefaut) {
		this.valeurParDefaut = valeurParDefaut;
	}

	/**
	 * [PeriodeParamDto.idPeriode] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the idPeriode
	 */
	public int getIdPeriode() {
		return idPeriode;
	}

	/**
	 * [PeriodeParamDto.idPeriode] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param idPeriode the idPeriode to set
	 */
	public void setIdPeriode(int idPeriode) {
		this.idPeriode = idPeriode;
	}

	/**
	 * [PeriodeParamDto.libellePeriode] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the libellePeriode
	 */
	public String getLibellePeriode() {
		return libellePeriode;
	}

	/**
	 * [PeriodeParamDto.libellePeriode] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param libellePeriode the libellePeriode to set
	 */
	public void setLibellePeriode(String libellePeriode) {
		this.libellePeriode = libellePeriode;
	}

	/**
	 * [PeriodeParamDto.idAnneeAcademique] Getter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @return the idAnneeAcademique
	 */
	public int getIdAnneeAcademique() {
		return idAnneeAcademique;
	}

	/**
	 * [PeriodeParamDto.idAnneeAcademique] Setter 
	 * @author rlaib on : 2 oct. 2014  13:57:56
	 * @param idAnneeAcademique the idAnneeAcademique to set
	 */
	public void setIdAnneeAcademique(int idAnneeAcademique) {
		this.idAnneeAcademique = idAnneeAcademique;
	}

	/**
	 * [PeriodeParamDto.premiereAnnee] Getter 
	 * @author rlaib on : 2 oct. 2014  14:02:24
	 * @return the premiereAnnee
	 */
	public String getPremiereAnnee() {
		return premiereAnnee;
	}

	/**
	 * [PeriodeParamDto.premiereAnnee] Setter 
	 * @author rlaib on : 2 oct. 2014  14:02:24
	 * @param premiereAnnee the premiereAnnee to set
	 */
	public void setPremiereAnnee(String premiereAnnee) {
		this.premiereAnnee = premiereAnnee;
	}

	/**
	 * [PeriodeParamDto.deuxiemeAnnee] Getter 
	 * @author rlaib on : 2 oct. 2014  14:02:24
	 * @return the deuxiemeAnnee
	 */
	public String getDeuxiemeAnnee() {
		return deuxiemeAnnee;
	}

	/**
	 * [PeriodeParamDto.deuxiemeAnnee] Setter 
	 * @author rlaib on : 2 oct. 2014  14:02:24
	 * @param deuxiemeAnnee the deuxiemeAnnee to set
	 */
	public void setDeuxiemeAnnee(String deuxiemeAnnee) {
		this.deuxiemeAnnee = deuxiemeAnnee;
	}
	
	/**
	 * [PeriodeParamDto.idTypeParam] Getter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @return the idTypeParam
	 */
	public int getIdTypeParam() {
		return idTypeParam;
	}

	/**
	 * [PeriodeParamDto.idTypeParam] Setter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @param idTypeParam the idTypeParam to set
	 */
	public void setIdTypeParam(int idTypeParam) {
		this.idTypeParam = idTypeParam;
	}

	/**
	 * [PeriodeParamDto.codeTypeParam] Getter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @return the codeTypeParam
	 */
	public String getCodeTypeParam() {
		return codeTypeParam;
	}

	/**
	 * [PeriodeParamDto.codeTypeParam] Setter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @param codeTypeParam the codeTypeParam to set
	 */
	public void setCodeTypeParam(String codeTypeParam) {
		this.codeTypeParam = codeTypeParam;
	}

	/**
	 * [PeriodeParamDto.libelleTypeParam] Getter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @return the libelleTypeParam
	 */
	public String getLibelleTypeParam() {
		return libelleTypeParam;
	}

	/**
	 * [PeriodeParamDto.libelleTypeParam] Setter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @param libelleTypeParam the libelleTypeParam to set
	 */
	public void setLibelleTypeParam(String libelleTypeParam) {
		this.libelleTypeParam = libelleTypeParam;
	}

	/**
	 * [PeriodeParamDto.masqueTypeParam] Getter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @return the masqueTypeParam
	 */
	public String getMasqueTypeParam() {
		return masqueTypeParam;
	}

	/**
	 * [PeriodeParamDto.masqueTypeParam] Setter 
	 * @author rlaib on : 6 oct. 2014  17:32:09
	 * @param masqueTypeParam the masqueTypeParam to set
	 */
	public void setMasqueTypeParam(String masqueTypeParam) {
		this.masqueTypeParam = masqueTypeParam;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto.hashCode] Method 
	 * @author rlaib  on : 6 oct. 2014  11:16:03
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.PeriodeParamDto.equals] Method 
	 * @author rlaib  on : 6 oct. 2014  11:16:03
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodeParamDto other = (PeriodeParamDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	
}
