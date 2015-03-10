/**
 *  
 * @author Mounir.MESSAOUDI on : 17 aout 2014 11:32:30
 */
package dz.gov.mesrs.sii.fve.business.model.dto.bac;

import java.io.Serializable;

/**
 * @author Mounir.MESSAOUDI on : 17 aout 2014 11:32:30
 */
public class DroitAccessWilayaDto implements Serializable {

	/**
	 * @author Mounir.MESSAOUDI on : 19 aout 2014 17:34:10
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String refCodeWilaya;
	private String wilayaLibelleLt;
	private String wilayaLibelleAr;

	private Integer idAccessWilaya;

	/**
	 * @author Mounir.MESSAOUDI on : 17 aout 2014 11:34:33
	 */
	public DroitAccessWilayaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRefCodeWilaya() {
		return refCodeWilaya;
	}

	public void setRefCodeWilaya(String refCodeWilaya) {
		this.refCodeWilaya = refCodeWilaya;
	}

	public String getWilayaLibelleLt() {
		return wilayaLibelleLt;
	}

	public void setWilayaLibelleLt(String wilayaLibelleLt) {
		this.wilayaLibelleLt = wilayaLibelleLt;
	}

	public String getWilayaLibelleAr() {
		return wilayaLibelleAr;
	}

	public void setWilayaLibelleAr(String wilayaLibelleAr) {
		this.wilayaLibelleAr = wilayaLibelleAr;
	}

	public Integer getIdAccessWilaya() {
		return idAccessWilaya;
	}

	public void setIdAccessWilaya(Integer idAccessWilaya) {
		this.idAccessWilaya = idAccessWilaya;
	}

	/**
	 * Comparer deux objets de type DroitAccessWilayaDto renvoi true si les
	 * codes wilays ou les id's sont identiques
	 */
	@Override
	public boolean equals(Object v) {
		boolean retVal = false;

		if (v instanceof DroitAccessWilayaDto) {
			DroitAccessWilayaDto ptr = (DroitAccessWilayaDto) v;
			// if(ptr.id == null && this.id == null)
			// return false;

			retVal = ptr.getRefCodeWilaya().equals(this.getRefCodeWilaya());
		}
		return retVal;
	}
	
	@Override
	public String toString() {
		return wilayaLibelleLt + "("+ refCodeWilaya +")";
	}
}
