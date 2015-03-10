package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author rlaib  on : 2 oct. 2014  13:58:25
 */
@Entity
@Table(name = "param_type", schema = "lmd", uniqueConstraints = {
		@UniqueConstraint(columnNames = "libelle"),
		@UniqueConstraint(columnNames = "code") })
public class ParamType implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private String code;
	private String libelle;
	private String masque;

	public ParamType() {
	}

	@SequenceGenerator(name="param_type_id_seq_gen", sequenceName="lmd.param_type_id_seq")
	@Id @GeneratedValue(generator="param_type_id_seq_gen")
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
	@Column(name = "code", unique = true, nullable = false, length = 20)
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
	 * [ParamType.masque] Getter 
	 * @author rlaib on : 6 oct. 2014  17:13:24
	 * @return the masque
	 */
	@Column(name = "masque", length = 50)
	public String getMasque() {
		return masque;
	}

	/**
	 * [ParamType.masque] Setter 
	 * @author rlaib on : 6 oct. 2014  17:13:24
	 * @param masque the masque to set
	 */
	public void setMasque(String masque) {
		this.masque = masque;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.ParamType.hashCode] Method 
	 * @author rlaib  on : 6 oct. 2014  17:13:31
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((masque == null) ? 0 : masque.hashCode());
		return result;
	}

	/**
	 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.ParamType.equals] Method 
	 * @author rlaib  on : 6 oct. 2014  17:13:31
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
		ParamType other = (ParamType) obj;
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
		if (masque == null) {
			if (other.masque != null)
				return false;
		} else if (!masque.equals(other.masque))
			return false;
		return true;
	}

	
	
}
