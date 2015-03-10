package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author rlaib  on : 2 oct. 2014  13:58:25
 */
@Entity
@Table(name = "periode_param", schema = "lmd", uniqueConstraints = {
		@UniqueConstraint(columnNames = "libelle"),
		@UniqueConstraint(columnNames = "code") })
public class PeriodeParam implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelle;
	private String valeur;
	private String valeurParDefaut;
	private Periode periode;
	private AnneeAcademique anneeAcademique;
	private ParamType paramType; 

	public PeriodeParam() {
	}

	@SequenceGenerator(name="periode_param_id_seq_gen", sequenceName="lmd.periode_param_id_seq")
	@Id @GeneratedValue(generator="periode_param_id_seq_gen")
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * [PeriodeParam.code] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the code
	 */
	@Column(name = "code", unique = true, nullable = false, length = 50)
	public String getCode() {
		return code;
	}

	/**
	 * [PeriodeParam.code] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * [PeriodeParam.libelle] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the libelle
	 */
	@Column(name = "libelle", unique = true, nullable = false, length = 150)
	public String getLibelle() {
		return libelle;
	}

	/**
	 * [PeriodeParam.libelle] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * [PeriodeParam.valeur] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the valeur
	 */
	@Column(name = "valeur", nullable = false, length = 50)
	public String getValeur() {
		return valeur;
	}

	/**
	 * [PeriodeParam.valeur] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param valeur the valeur to set
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	/**
	 * [PeriodeParam.valeurParDefaut] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the valeurParDefaut
	 */
	@Column(name = "valeur_par_defaut", nullable = false, length = 50)
	public String getValeurParDefaut() {
		return valeurParDefaut;
	}

	/**
	 * [PeriodeParam.valeurParDefaut] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param valeurParDefaut the valeurParDefaut to set
	 */
	public void setValeurParDefaut(String valeurParDefaut) {
		this.valeurParDefaut = valeurParDefaut;
	}

	/**
	 * [PeriodeParam.periode] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the periode
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periode", nullable = false)
	public Periode getPeriode() {
		return periode;
	}

	/**
	 * [PeriodeParam.periode] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param periode the periode to set
	 */
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	/**
	 * [PeriodeParam.anneeAcademique] Getter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @return the anneeAcademique
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_annee_academique", nullable = false)
	public AnneeAcademique getAnneeAcademique() {
		return anneeAcademique;
	}

	/**
	 * [PeriodeParam.anneeAcademique] Setter 
	 * @author rlaib on : 2 oct. 2014  13:50:17
	 * @param anneeAcademique the anneeAcademique to set
	 */
	public void setAnneeAcademique(AnneeAcademique anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}
	
	/**
	 * [PeriodeParam.paramType] Getter 
	 * @author rlaib on : 6 oct. 2014  17:29:13
	 * @return the paramType
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_type", nullable = false)
	public ParamType getParamType() {
		return paramType;
	}

	/**
	 * [PeriodeParam.paramType] Setter 
	 * @author rlaib on : 6 oct. 2014  17:29:13
	 * @param paramType the paramType to set
	 */
	public void setParamType(ParamType paramType) {
		this.paramType = paramType;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.PeriodeParam.hashCode] Method 
	 * @author rlaib  on : 6 oct. 2014  11:15:28
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
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.PeriodeParam.equals] Method 
	 * @author rlaib  on : 6 oct. 2014  11:15:28
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
		PeriodeParam other = (PeriodeParam) obj;
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
